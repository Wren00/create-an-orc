package org.example.createanorc.controllers;

import java.util.ArrayList;
import lombok.Generated;
import org.example.createanorc.models.Orc;
import org.example.createanorc.service.OrcService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrcController {
    private final OrcService orcService;

    @GetMapping({"/orcs"})
    @ResponseBody
    public ArrayList<Orc> retrieveAllOrcs() {
        return this.orcService.orcsGETALL();
    }

    @GetMapping({"/users/{user_id}/orcs"})
    @ResponseBody
    public ArrayList<Orc> orcsByUserId(@PathVariable("user_id") Long userId) {
        return this.orcService.orcsGETBYUSERID(userId);
    }

    @GetMapping({"/orcs/{id}"})
    @ResponseBody
    public Orc orcByID(@PathVariable("id") Long id) {
        return this.orcService.orcGETBYID(id);
    }

    @PostMapping({"/users/{user_id}/orcs"})
    @ResponseBody
    public Orc orcPost(@RequestBody Orc model, @PathVariable("user_id") Long userId) {
        return this.orcService.orcPOST(model, userId);
    }

    @DeleteMapping({"/orcs/{orc_id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void orcDelete(@PathVariable(name = "orc_id") Long orcId) {
       this.orcService.orcDELETE(orcId);
    }

    @Generated
    public OrcController(final OrcService characterService) {
        this.orcService = characterService;
    }
}
