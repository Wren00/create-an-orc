package org.example.createanorc.service;

import org.example.createanorc.repository.OrcRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import org.example.createanorc.models.Orc;


@Service
public class OrcService {

    private final OrcRepository orcRepository;

    // GET functions

    public ArrayList<Orc> orcsGETALL() {
        return orcRepository.findAll();
    }

    public Orc orcGETBYID(Long id) throws IndexOutOfBoundsException {
        return orcRepository.getById(id);
    }


    public ArrayList<Orc> orcsGETBYUSERID(Long userId) {
        return orcRepository.getOrcsByUserId(userId);
    }

    // POST functions

    public Orc orcPOST(Orc newOrc, Long userId) {
        newOrc.setUserId(userId);
        return orcRepository.save(newOrc);
        }

    // DELETE functions

    public void orcDELETE(Long id) throws IndexOutOfBoundsException {
            orcRepository.deleteById(id);
    }

    public OrcService(OrcRepository orcRepository) {
        this.orcRepository = orcRepository;
    }

}
