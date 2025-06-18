package org.example.createanorc.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.example.createanorc.models.Character;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CharacterService {
    private ArrayList<Character> characterModels = new ArrayList();
    private AtomicInteger counter = new AtomicInteger();

    // GET functions

    public ArrayList<Character> characterGETALL() {
        return this.characterModels;
    }

    public ArrayList<Character> characterGETBYUSERID(int userId) {
        ArrayList<Character> charactersByUser = new ArrayList();

        for (Character character : this.characterModels) {
            if (character.getUserId() == userId) {
                charactersByUser.add(character);
            }
        }
        return charactersByUser;
    }

    public Character characterGETBYID(int id) throws IndexOutOfBoundsException {
        return this.characterModels.get(id - 1);
    }

    // POST functions

    public Character characterPOST(Character model) {
        int id = this.counter.incrementAndGet();
        model.setId(id);
        this.characterModels.add(model);
        return new Character(id, model.getName(), model.getDescription(), model.getCharacterImageId(), model.getPromptsCollectionId(), model.getUserId());
    }

    // DELETE functions

    public void characterDELETE(int id) throws IndexOutOfBoundsException {
            Character deletedCharacter = this.characterGETBYID(id);

            characterModels.remove(deletedCharacter);
    }
}
