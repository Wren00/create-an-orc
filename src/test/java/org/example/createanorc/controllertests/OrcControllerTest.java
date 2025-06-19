package org.example.createanorc.controllertests;

import org.example.createanorc.controllers.OrcController;
import org.example.createanorc.models.Orc;
import org.example.createanorc.service.OrcService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrcControllerTest {

    @MockitoBean
    OrcService orcService;

    @Autowired
    private OrcController controller;

    @Test
    void retrieveAllCharacters_whenCalled_returnsCharacterModels() {
        // arrange
        var orcModels = new ArrayList<Orc>();

        when(orcService.orcsGETALL()).thenReturn(orcModels);

        //act
        var results = controller.retrieveAllOrcs();

        // assert
        verify(orcService, times(1)).orcsGETALL();

        assertNotNull(results);
        assertEquals(orcModels, results);
    }

}
