package com.lightning.school.mvc.facade;

import com.lightning.school.mvc.api.in.ExerciceIn;
import com.lightning.school.mvc.delegate.aws.MediaStoreService;
import com.lightning.school.mvc.facade.ControllerException.CrudException;
import com.lightning.school.mvc.facade.ControllerException.NoDataException;
import com.lightning.school.mvc.model.Cours;
import com.lightning.school.mvc.model.Media;
import com.lightning.school.mvc.model.Section;
import com.lightning.school.mvc.model.exercice.Exercice;
import com.lightning.school.mvc.repository.mysql.CoursRepository;
import com.lightning.school.mvc.repository.mysql.ExerciceRepository;
import com.lightning.school.mvc.repository.mysql.SectionRepository;
import com.lightning.school.mvc.util.Closures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/api/cours")
public class CoursController {

    private CoursRepository coursRepository;
    private MediaStoreService mediaStoreService;
    private ExerciceRepository exerciceRepository;
    private SectionRepository sectionRepository;

    public CoursController(CoursRepository coursRepository, MediaStoreService mediaStoreService, ExerciceRepository exerciceRepository, SectionRepository sectionRepository) {
        this.coursRepository = coursRepository;
        this.mediaStoreService = mediaStoreService;
        this.exerciceRepository = exerciceRepository;
        this.sectionRepository = sectionRepository;
    }

    @Autowired


    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    private List<Cours> getAll(){
        return coursRepository.findAll();
    }

    @GetMapping("/id/{coursId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private Cours getId(@PathVariable("coursId") Integer id){
        return coursRepository.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createCours(@RequestParam(value = "medias", required = false) MultipartFile[] mediasFile, @RequestParam(value = "cours", required = false) MultipartFile coursFile,
                                      @RequestParam("sectionIds") Integer[] sectionIds, @RequestParam("coursLabel") String coursLabel,
                                      @RequestParam("deadline") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate deadline, UriComponentsBuilder uriBuilder){

        List<Section> sections = sectionRepository.findAllById(Arrays.asList(sectionIds));

        String urlCours = null;
        List<Media> medias = null;

        if (coursFile !=  null)
            urlCours = mediaStoreService.putMedia(coursFile);
        if (!CollectionUtils.isEmpty(Arrays.asList(mediasFile))) {
            medias = Arrays.stream(mediasFile).map(this::createMedia).collect(Collectors.toList());
        }
        LocalDateTime dead = LocalDateTime.from(deadline.atTime(LocalTime.now()));
        Cours cours = new Cours(coursLabel, dead, urlCours, medias);
        final Cours finalCours = coursRepository.save(cours);
        sections.forEach(section -> {
            List<Cours> coursSections = Closures.opt(section::getCours).orElse(new ArrayList<>());
            coursSections.add(finalCours);
            section.setCours(coursSections);
        });
        sectionRepository.saveAll(sections);
        URI uri = uriBuilder.path("/api/cours/id/{coursId}").buildAndExpand(cours.getCoursId()).toUri();
        return created(uri).build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity updateCours(@RequestParam("medias") MultipartFile[] mediasFile, @RequestParam("cours") MultipartFile coursFile,
                                      @RequestParam("coursLabel") String coursLabel, @RequestParam("coursId") Integer coursId, @RequestParam(value = "exoId", required = false) Integer[] exoIds,
                                      UriComponentsBuilder uriBuilder){

        if (coursId == null){
            throw new CrudException();
        }

        Cours cours = coursRepository.findById(coursId).get();
        if (!StringUtils.isEmpty(coursLabel))
            cours.setCoursLabel(coursLabel);

        if (coursFile != null){
            String urlCours =  mediaStoreService.putMedia(coursFile);
            cours.setLinkCours(urlCours);
        }


        if (!CollectionUtils.isEmpty(Arrays.asList(mediasFile))) {
            List<Media> medias = Arrays.stream(mediasFile).map(this::createMedia).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(cours.getMedias())) {
                cours.setMedias(medias);
            } else {
                cours.getMedias().addAll(medias);
            }
        }

        if (exoIds != null && !CollectionUtils.isEmpty(Arrays.asList( exoIds))) {
            List<Exercice> exercices = exerciceRepository.findAllById(Arrays.asList(exoIds));
            if (CollectionUtils.isEmpty(cours.getExercices()))
                cours.setExercices(exercices);
            else
                cours.getExercices().addAll(exercices);
        }

        cours = coursRepository.save(cours);
        URI uri = uriBuilder.path("/api/cours/id/{coursId}").buildAndExpand(cours.getCoursId()).toUri();
        return created(uri).build();
    }

    @PostMapping("/exercices")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createExercice(@RequestBody ExerciceIn in, UriComponentsBuilder uriBuilder){

        Cours cours = coursRepository.findById(in.getCoursId()).get();
        Exercice exo = new Exercice(in);

        if (CollectionUtils.isEmpty(cours.getExercices())){
            cours.setExercices(new ArrayList<>(Collections.singleton(exo)));
        } else {
            cours.getExercices().add(exo);
        }

        cours = coursRepository.save(cours);

        URI uri = uriBuilder.path("/api/cours/id/{coursId}").buildAndExpand(cours.getCoursId()).toUri();
        return created(uri).build();
    }

    @PutMapping("/exercices/edit")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity updateExercice(@RequestBody ExerciceIn in, UriComponentsBuilder uriBuilder){
        Integer exoId = Closures.opt(in::getExerciceId).orElseThrow(CrudException::new);

        Exercice exercice = exerciceRepository.findById(exoId).orElseThrow(() -> new NoDataException(exoId));

        if (!StringUtils.isEmpty(in.getExerciceLabel()))
            exercice.setExerciceLabel(in.getExerciceLabel());

        if (in.getCoeficient() != null &&  in.getCoeficient() > 0f){
            exercice.setCoeficient(in.getCoeficient());
        }

        exercice = exerciceRepository.save(exercice);

        URI uri = uriBuilder.path("/api/exercices/{exoId}").buildAndExpand(exercice.getExerciceId()).toUri();
        return created(uri).build();
    }

    @GetMapping("/exercices/{exoId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Exercice getExerciceById(@PathVariable("exoId") Integer pExoId){
        Integer exoId = Closures.opt(() -> pExoId).orElseThrow(CrudException::new);
        return exerciceRepository.findById(exoId).orElseThrow(() -> new NoDataException(exoId));
    }

    private Media createMedia(MultipartFile file){
        String url = mediaStoreService.putMedia(file);
        return new Media(com.lightning.school.mvc.util.StringUtils.formalizeNameFile(file.getOriginalFilename()), url);
    }

}
