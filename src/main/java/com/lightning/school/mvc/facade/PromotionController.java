package com.lightning.school.mvc.facade;

import com.lightning.school.mvc.api.in.promo.SectionCreateIn;
import com.lightning.school.mvc.api.in.promo.SectionUpdateIn;
import com.lightning.school.mvc.facade.ControllerException.CrudException;
import com.lightning.school.mvc.facade.ControllerException.NoDataException;
import com.lightning.school.mvc.model.Cours;
import com.lightning.school.mvc.model.Section;
import com.lightning.school.mvc.model.user.User;
import com.lightning.school.mvc.repository.mysql.CoursRepository;
import com.lightning.school.mvc.repository.mysql.SectionRepository;
import com.lightning.school.mvc.repository.mysql.UserRepository;
import com.lightning.school.mvc.util.Closures;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/api/promo")
@Api(description = "Système de gestion des promotions")
public class PromotionController {

    private SectionRepository sectionRepository;
    private UserRepository userRepository;
    private CoursRepository coursRepository;

    @Autowired
    public PromotionController(SectionRepository sectionRepository, UserRepository userRepository, CoursRepository coursRepository) {
        this.sectionRepository = sectionRepository;
        this.userRepository = userRepository;
        this.coursRepository = coursRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("Liste les promotions")
    public List<Section> getAll(){
        return sectionRepository.findAll();
    }

    @GetMapping("/id/{sectionId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("Récupère la promotion")
    public Section getId(@PathVariable("sectionId") Integer id){
        return sectionRepository.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creer la promotion")
    public ResponseEntity createSection(@RequestBody SectionCreateIn sectionIn, UriComponentsBuilder uriBuilder){
        Integer teacherId = Closures.opt(() -> sectionIn.getTeacherId()).orElseThrow(CrudException::new);
        User user = userRepository.findById(teacherId).orElseThrow(() -> new NoDataException(sectionIn.getTeacherId()));
        if (CollectionUtils.isEmpty(user.getSections())){
            user.setSections(new ArrayList<>(Collections.singleton(new Section(sectionIn))));
        } else {
            user.getSections().add(new Section(sectionIn));
        }

        user = userRepository.save(user);

        URI uri = uriBuilder.path("/api/promo/id/{sectionId}").buildAndExpand(user.getSections().get(user.getSections().size()-1).getSectionId()).toUri();
        return created(uri).build();
    }

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("Edite la promotion")
    public ResponseEntity updateSection(@RequestBody SectionUpdateIn sectionUpdateIn, UriComponentsBuilder uriBuilder) {

        if (sectionUpdateIn.getId() == null){
            throw new CrudException();
        }
        Section promo = sectionRepository.findById(sectionUpdateIn.getId()).get();

        if (!StringUtils.isEmpty(sectionUpdateIn.getSectionLabel()))
            promo.setSectionLabel(sectionUpdateIn.getSectionLabel());

        if (!CollectionUtils.isEmpty(sectionUpdateIn.getUserIds())){
            List<User> users = userRepository.findAllById(sectionUpdateIn.getUserIds());
            if (CollectionUtils.isEmpty(promo.getUsers()))
                promo.setUsers(new ArrayList<>(users));
            else
                promo.getUsers().addAll(users);
        }

        if (!CollectionUtils.isEmpty(sectionUpdateIn.getCoursIds())) {
            List<Cours> cours = coursRepository.findAllById(sectionUpdateIn.getCoursIds());
            if (CollectionUtils.isEmpty(promo.getCours()))
                promo.setCours(new ArrayList<>(cours));
            else
                promo.getCours().addAll(cours);
        }

        promo = sectionRepository.save(promo);

        URI uri = uriBuilder.path("/api/promo/id/{sectionId}").buildAndExpand(promo.getSectionId()).toUri();
        return created(uri).build();
    }
}
