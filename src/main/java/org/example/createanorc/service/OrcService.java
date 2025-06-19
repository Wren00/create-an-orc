package org.example.createanorc.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import org.example.createanorc.models.Orc;


@Service
public class OrcService {
    private ArrayList<Orc> orcModels = new ArrayList();

    // GET functions

    public ArrayList<Orc> orcsGETALL() {
        return this.orcModels;
    }

    public ArrayList<Orc> orcsGETBYUSERID(int userId) {
        ArrayList<Orc> orcsByUser = new ArrayList();

        for (Orc orc : this.orcModels) {
            if (orc.getUserId() == userId) {
                orcsByUser.add(orc);
            }
        }
        return orcsByUser;
    }

    public Orc orcGETBYID(int id) throws IndexOutOfBoundsException {
        return this.orcModels.get(id - 1);
    }

    // POST functions

    public Orc orcPOST(Orc model, int userId) {
        int newId = orcModels.size() + 1;
        model.setId(newId);
        model.setUserId(userId);
        this.orcModels.add(model);
        return new Orc(newId, model.getName(), model.getDescription(), model.getOrcImageId(), model.getPromptsCollectionId(), model.getUserId());
    }

    // DELETE functions

    public void orcDELETE(int id) throws IndexOutOfBoundsException {
            Orc deletedOrc = this.orcGETBYID(id);

            orcModels.remove(deletedOrc);
    }
}
