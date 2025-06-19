package org.example.createanorc.controllertests;

import org.example.createanorc.controllers.OrcController;
import org.example.createanorc.models.Orc;
import org.example.createanorc.service.OrcService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrcControllerTest {

    @MockitoBean
    OrcService orcService;

    @Autowired
    private OrcController controller;

    @Test
    void retrieveAllOrcs_whenCalled_returnsOrcModels() {
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

    @Test
    void getOrcById_whenCalledWithValidId_returnsOrc() {
        var id = 1;
        var orc = Orc.builder()
                .id(id)
                .name("TESTORC")
                .description("A test description")
                .orcImageId(1)
                .promptsCollectionId(1)
                .userId(1)
                .build();

        when(orcService.orcGETBYID(id)).thenReturn(orc);

        var results = controller.orcByID(id);

        verify(orcService, times(1)).orcGETBYID(id);

        assertNotNull(results);
        assertEquals(orc, results);
    }

    @Test
    void orcGetById_whenCalledWithNotValidId_throwsException() {
        var id = 100;
        when(orcService.orcGETBYID(id)).thenThrow(new IndexOutOfBoundsException("Bad ID"));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            orcService.orcGETBYID(id);
        });
    }

    //POST tests

    @Test
    void orcPost_whenValidDataIsSubmitted_returnsOrc() {

        var newOrc = Orc.builder()
                .name("TESTORC")
                .description("A test description")
                .orcImageId(1)
                .promptsCollectionId(1)
                .build();

        when(orcService.orcPOST(newOrc, 1)).thenReturn(newOrc);

        var results = controller.orcPost(newOrc, 1);

        verify(orcService, times(1)).orcPOST(newOrc,1);

        assertNotNull(results);
        assertEquals(newOrc, results);
    }

    //DELETE tests

    @Test
    void orcDELETE_whenCalledWithValidId_runsOnce() {
        var id = 1;
        doNothing().when(orcService).orcDELETE(id);

        controller.orcDelete(id);

        verify(orcService, times(1)).orcDELETE(id);
    }

    @Test
    void orcDelete_whenCalledWithNotValidId_throwsException() {
        var id = 100;
        doThrow(new IndexOutOfBoundsException("Bad ID")).when(orcService).orcDELETE(id);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            orcService.orcDELETE(id);
        });
    }

}
