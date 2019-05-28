package com.lightning.school.mvc.facade;

import com.lightning.school.mvc.api.in.user.UserCreateIn;
import com.lightning.school.mvc.api.in.user.UserUpdateIn;
import com.lightning.school.mvc.api.out.UserItem;
import com.lightning.school.mvc.delegate.aws.MediaStoreService;
import com.lightning.school.mvc.delegate.crud.UserCrudServiceImpl;
import com.lightning.school.mvc.facade.ControllerException.PasswordInvalidException;
import com.lightning.school.mvc.model.user.User;
import com.lightning.school.mvc.model.user.UserTypeEnum;
import com.lightning.school.mvc.util.PasswordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/api/users")
@Api(description = "Gestion des utilisateurs")
public class UserController {

    private UserCrudServiceImpl userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private MediaStoreService mediaStoreService;

    @Autowired
    public UserController(UserCrudServiceImpl userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MediaStoreService mediaStoreService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mediaStoreService = mediaStoreService;
    }

    @GetMapping
    @ApiOperation("Liste les utilisateurs")
    public List<UserItem> getUsers(){
        return userRepository.getAll();
    }

    @GetMapping("/id/{userId}")
    @ApiOperation("Obtien le detail utilisateur")
    public User getUserById(@ApiParam("Id de l'utilisateur")@PathVariable("userId") Integer userId){
        return userRepository.getByIdUser(userId);
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

        User userFinded = new User(user.getMail(), bCryptPasswordEncoder.encode(user.getPassword()), UserTypeEnum.retrieveValueByUserType(typeUser));
        UserItem userItem  = userRepository.save(userFinded);
        URI uri = uriBuilder.path("/api/users/id/{userId}").buildAndExpand(userItem.getId()).toUri();
        return created(uri).build();
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("Modifie l'utilisateur")
    public ResponseEntity update(@ApiParam("Id de l'utilisateur")@PathVariable("userId") Integer userId, @RequestParam("file") MultipartFile file, @RequestBody UserUpdateIn user, UriComponentsBuilder uriBuilder) {

        User userFinded = userRepository.getByIdUser(userId);

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
        else if (file != null && user.getUserPhoto() == null)
            userFinded.setUserPhoto(mediaStoreService.putMedia(file));

        if (user.getUserType()!= null && UserTypeEnum.ADMIN.equals(UserTypeEnum.retrieveTypeUserByValue(userFinded.getTypeUserId()))) {
            userFinded.setTypeUserId(UserTypeEnum.retrieveValueByUserType(user.getUserType()));
        }

        userFinded = userRepository.saveUser(userFinded);
        URI uri = uriBuilder.path("/api/users/id/{userId}").buildAndExpand(userFinded.getUserId()).toUri();
        return created(uri).build();
    }

}
