package org.example.createanorc.controllers;

import java.util.ArrayList;
import lombok.Generated;
import org.example.createanorc.models.Character;
import org.example.createanorc.service.CharacterService;
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
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping({"/users/{user_id}/characters"})
    @ResponseBody
    public ArrayList<Character> retrieveAllCharacters(@PathVariable("user_id") int userId) {
        return this.characterService.characterGETALL(userId);
    }

    @GetMapping({"/users/{user_id}/characters/{id}"})
    @ResponseBody
    public Character characterByID(@PathVariable("user_id") int userId, @PathVariable("id") int id) {
        return this.characterService.characterGETBYID(userId, id);
    }

    @PostMapping({"/users/{user_id}/characters"})
    @ResponseBody
    public Character characterPost(@RequestBody Character model, @PathVariable("user_id") int userId) {
        return this.characterService.characterPOST(model);
    }

    @DeleteMapping({"/users/{user_id}/characters/{character_id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void characterDelete(@PathVariable(name = "user_id") int userId, @PathVariable(name = "character_id") int characterId) {
        this.characterService.characterDELETE(userId, characterId);
    }

    @Generated
    public CharacterController(final CharacterService characterService) {
        this.characterService = characterService;
    }
}
