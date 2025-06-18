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

    @GetMapping({"/characters"})
    @ResponseBody
    public ArrayList<Character> retrieveAllCharacters() {
        return this.characterService.characterGETALL();
    }

    @GetMapping({"/users/{user_id}/characters"})
    @ResponseBody
    public ArrayList<Character> charactersByUserId(@PathVariable("user_id") int userId) {
        return this.characterService.characterGETBYUSERID(userId);
    }

    @GetMapping({"/characters/{id}"})
    @ResponseBody
    public Character characterByID(@PathVariable("id") int id) {
        return this.characterService.characterGETBYID(id);
    }

    @PostMapping({"/users/{user_id}/characters"})
    @ResponseBody
    public Character characterPost(@RequestBody Character model, @PathVariable("user_id") int userId) {
        return this.characterService.characterPOST(model, userId);
    }

    @DeleteMapping({"/characters/{character_id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void characterDelete(@PathVariable(name = "character_id") int characterId) {
       this.characterService.characterDELETE(characterId);
    }

    @Generated
    public CharacterController(final CharacterService characterService) {
        this.characterService = characterService;
    }
}
