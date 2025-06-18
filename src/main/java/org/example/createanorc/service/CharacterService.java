package org.example.createanorc.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CharacterService {
    private ArrayList<Character> characterModels = new ArrayList();
    private AtomicInteger counter = new AtomicInteger();


    public ArrayList<Character> characterGETALL(int userId) {
        ArrayList<Character> charactersByUser = new ArrayList();

        for(Character character : this.characterModels) {
            if (character.getUserId() == userId) {
                charactersByUser.add(character);
            }
        }

        return charactersByUser;
    }

    public Character characterGETBYID(int userId, int id) throws IndexOutOfBoundsException {
        return (Character)this.characterModels.get(id - 1);
    }

    public Character characterPOST(Character model) {
        int id = this.counter.incrementAndGet();
        model.setId(id);
        this.characterModels.add(model);
        return new Character(id, model.getName(), model.getDescription(), model.getCharacterImagesId(), model.getPromptsCollectionsId(), model.getUserId());
    }

    public void characterDELETE(int userId, int id) throws IndexOutOfBoundsException {
        this.characterModels.remove(id - 1);
    }
}
