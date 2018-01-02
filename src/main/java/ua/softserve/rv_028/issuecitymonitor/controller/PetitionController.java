package ua.softserve.rv_028.issuecitymonitor.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.softserve.rv_028.issuecitymonitor.dto.PetitionDto;
import ua.softserve.rv_028.issuecitymonitor.service.PetitionService;

import java.util.List;

@RestController
@RequestMapping("/api/petitions")
public class PetitionController {

    private static final Logger LOGGER = Logger.getLogger(PetitionController.class);

    private final PetitionService petitionService;

    @Autowired
    public PetitionController(PetitionService petitionService) {
        this.petitionService = petitionService;
    }

    @GetMapping
    public List<PetitionDto> getAll(){
        LOGGER.debug("GET request for all users");
        return petitionService.findAll();
    }

    @GetMapping(value = "/{id}")
    public PetitionDto getOne(@PathVariable long id){
        LOGGER.debug("GET request");
        return petitionService.findById(id);
    }

    @PutMapping("/{id}")
    public PetitionDto update(@PathVariable long id, @RequestBody PetitionDto petitionDto){
        LOGGER.debug("PUT request");
        return petitionService.update(petitionDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        LOGGER.debug("DELETE request");
        petitionService.deleteById(id);
    }

    @PostMapping(path = "/add")
    public void createPetition(@RequestBody PetitionDto petitionDto) {
        LOGGER.debug("POST request");
        petitionService.addPetition(petitionDto);
    }

}
