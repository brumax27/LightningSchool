package com.lightning.school.mvc.facade;

import com.lightning.school.mvc.api.in.user.UserCreateIn;
import com.lightning.school.mvc.api.in.user.UserUpdateIn;
import com.lightning.school.mvc.api.out.UserItem;
import com.lightning.school.mvc.delegate.aws.MediaStoreService;
import com.lightning.school.mvc.delegate.crud.UserCrudServiceImpl;
import com.lightning.school.mvc.facade.ControllerException.BadUserException;
import com.lightning.school.mvc.facade.ControllerException.CrudException;
import com.lightning.school.mvc.facade.ControllerException.PasswordInvalidException;
import com.lightning.school.mvc.facade.ControllerException.UserExistedException;
import com.lightning.school.mvc.model.Cours;
import com.lightning.school.mvc.model.Section;
import com.lightning.school.mvc.model.user.User;
import com.lightning.school.mvc.model.user.UserTypeEnum;
import com.lightning.school.mvc.repository.mysql.SectionRepository;
import com.lightning.school.mvc.util.Closures;
import com.lightning.school.mvc.util.PasswordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/api/users")
@Api(description = "Gestion des utilisateurs")
public class UserController {

    private UserCrudServiceImpl userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private MediaStoreService mediaStoreService;
    private SectionRepository sectionRepository;

    public UserController(UserCrudServiceImpl userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MediaStoreService mediaStoreService, SectionRepository sectionRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mediaStoreService = mediaStoreService;
        this.sectionRepository = sectionRepository;
    }

    @GetMapping
    @ApiOperation("Liste les utilisateurs")
    public List<UserItem> getUsers(){
        return userRepository.getAll();
    }

    @GetMapping("/id/{userId}")
    @ApiOperation("Obtien le detail utilisateur")
    public User getUserById(@ApiParam("Id de l'utilisateur")@PathVariable("userId") Integer userId){
        return Closures.opt(() -> userRepository.getByIdUser(userId)).orElseThrow(CrudException::new);
    }

    @GetMapping("/id/{userId}/cours")
    @ApiOperation("Liste les cours d'un utilisateur")
    public List<Cours> getCours(@PathVariable("userId") Integer userId){
        User user = Closures.opt(() -> userRepository.getByIdUser(userId)).orElseThrow(CrudException::new);
        if (UserTypeEnum.TEACHER.equals(user.getUserType())){
            List<Cours> cours = new ArrayList<>();
            List<Section> sections = user.getSections();
            sections.forEach(section -> {
                cours.addAll(section.getCours());
            });
            return cours;
        }
        return findSectionByUSer(user).getCours();
    }

    @GetMapping("/id/{userId}/promo")
    @ApiOperation("recupere la promotion d'un student")
    public Section getSectionByIdStudent(@PathVariable("userId") Integer userId){
        User user = Closures.opt(() -> userRepository.getByIdUser(userId)).orElseThrow(CrudException::new);
        if (!UserTypeEnum.STUDENT.equals(user.getUserType()))
            throw new BadUserException();
        return findSectionByUSer(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creer un utilisateur et renvoi l'url detail de celui ci.")
    public ResponseEntity save(@RequestBody UserCreateIn user, UriComponentsBuilder uriBuilder) {
        if (!PasswordUtil.passwordIsValid(user.getPassword())) {
            throw new PasswordInvalidException();
        }
        UserTypeEnum typeUser = UserTypeEnum.STUDENT;
        if (!UserTypeEnum.STUDENT.equals(user.getTypeUser()))
            typeUser = user.getTypeUser();

        User userMailFind = userRepository.getByMail(user.getMail());
        if (userMailFind != null) {
            throw new UserExistedException(user.getMail());
        }

        User userFinded = new User(user.getMail(), bCryptPasswordEncoder.encode(user.getPassword()), UserTypeEnum.retrieveValueByUserType(typeUser));
        UserItem userItem  = userRepository.save(userFinded);
        URI uri = uriBuilder.path("/api/users/id/{userId}").buildAndExpand(userItem.getId()).toUri();
        return created(uri).body(userItem);
    }

    @PutMapping("/{userId}/edit")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Modifie l'utilisateur")
    public ResponseEntity update(@ApiParam("Id de l'utilisateur")@PathVariable("userId") Integer userId, @RequestBody UserUpdateIn user, UriComponentsBuilder uriBuilder) {

        User userFinded = Closures.opt( () -> userRepository.getByIdUser(userId)).orElseThrow(CrudException::new);

        if (!StringUtils.isEmpty(user.getPassword())) {
            if (!PasswordUtil.passwordIsValid(user.getPassword())) {
                throw new PasswordInvalidException();
            }
            userFinded.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        if (!StringUtils.isEmpty(user.getMail()))
            userFinded.setMail(user.getMail());

        if (!StringUtils.isEmpty(user.getName()))
            userFinded.setName(user.getName());

        if (!StringUtils.isEmpty(user.getSurname()))
            userFinded.setSurname(user.getSurname());

        if (!StringUtils.isEmpty(user.getUserPhoto()))
            userFinded.setUserPhoto(user.getUserPhoto());

        if (user.getUserType()!= null && UserTypeEnum.ADMIN.equals(UserTypeEnum.retrieveTypeUserByValue(userFinded.getTypeUserId()))) {
            userFinded.setTypeUserId(UserTypeEnum.retrieveValueByUserType(user.getUserType()));
        }

        userFinded = userRepository.saveUser(userFinded);
        URI uri = uriBuilder.path("/api/users/id/{userId}").buildAndExpand(userFinded.getUserId()).toUri();
        return created(uri).build();
    }

    @PostMapping("/{userId}/image")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Modifie l'image d'un utilisateur")
    public ResponseEntity update(@ApiParam("Id de l'utilisateur")@PathVariable("userId") Integer userId, @RequestParam("image") MultipartFile image, UriComponentsBuilder uriBuilder) {

        User userFinded = userRepository.getByIdUser(userId);

        if (image != null)
            userFinded.setUserPhoto(mediaStoreService.putMedia(image));

        userFinded = userRepository.saveUser(userFinded);
        URI uri = uriBuilder.path("/api/users/id/{userId}").buildAndExpand(userFinded.getUserId()).toUri();
        return created(uri).build();
    }

    @DeleteMapping("/{userId}/delete")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Supprime un utilisateur")
    public ResponseEntity delete(@ApiParam("Id de l'utilisateur")@PathVariable("userId") Integer pUserId){
        Integer userId = Closures.opt(() -> pUserId).orElseThrow(CrudException::new);
        userRepository.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    private Section findSectionByUSer(User user){
        List<Section> sections = sectionRepository.findAll();
        AtomicReference<Section> sectionFinded = new AtomicReference<>();
        sections.forEach(section -> {
            List<User> users = section.getUsers();
            for (User user1 : users) {
                if (user.getUserId().equals(user1.getUserId()))
                    sectionFinded.set(section);
            }
        });
        return sectionFinded.get();
    }

}
