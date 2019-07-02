package com.lightning.school.mvc.facade;

import com.lightning.school.mvc.api.in.promo.SectionCreateIn;
import com.lightning.school.mvc.api.in.promo.SectionUpdateIn;
import com.lightning.school.mvc.facade.ControllerException.CrudException;
import com.lightning.school.mvc.model.Cours;
import com.lightning.school.mvc.model.Section;
import com.lightning.school.mvc.model.user.User;
import com.lightning.school.mvc.repository.mysql.CoursRepository;
import com.lightning.school.mvc.repository.mysql.SectionRepository;
import com.lightning.school.mvc.repository.mysql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/api/promo")
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
    public List<Section> getAll(){
        return sectionRepository.findAll();
    }

    @GetMapping("/id/{sectionId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Section getId(@PathVariable("sectionId") Integer id){
        return sectionRepository.findById(id).orElseThrow(CrudException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createSection(@RequestBody SectionCreateIn sectionIn, UriComponentsBuilder uriBuilder){

        Section promo = sectionRepository.save(new Section(sectionIn));

        URI uri = uriBuilder.path("/api/promo/id/{sectionId}").buildAndExpand(promo.getSectionId()).toUri();
        return created(uri).build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity updateSection(@RequestBody SectionUpdateIn sectionUpdateIn, UriComponentsBuilder uriBuilder) {

        if (sectionUpdateIn.getId() == null){
            throw new CrudException();
        }
        Section promo = sectionRepository.findById(sectionUpdateIn.getId()).orElseThrow(CrudException::new);

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
