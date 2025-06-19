package org.example.createanorc.controllers;

import java.util.ArrayList;
import lombok.Generated;
import org.example.createanorc.models.Orc;
import org.example.createanorc.service.OrcService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class OrcController {
    private final OrcService orcService;

    @GetMapping({"/orcs"})
    @ResponseBody
    public ArrayList<Orc> retrieveAllOrcs() {
        return this.orcService.orcsGETALL();
    }

    @GetMapping({"/users/{user_id}/orcs"})
    @ResponseBody
    public ArrayList<Orc> orcsByUserId(@PathVariable("user_id") int userId) {
        return this.orcService.orcsGETBYUSERID(userId);
    }

    @GetMapping({"/orcs/{id}"})
    @ResponseBody
    public Orc orcByID(@PathVariable("id") int id) {
        return this.orcService.orcGETBYID(id);
    }

    @PostMapping({"/users/{user_id}/orcs"})
    @ResponseBody
    public Orc orcPost(@RequestBody Orc model, @PathVariable("user_id") int userId) {
        return this.orcService.orcPOST(model, userId);
    }

    @DeleteMapping({"/orcs/{orc_id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void orcDelete(@PathVariable(name = "orc_id") int orcId) {
       this.orcService.orcDELETE(orcId);
    }

    @Generated
    public OrcController(final OrcService characterService) {
        this.orcService = characterService;
    }
}
