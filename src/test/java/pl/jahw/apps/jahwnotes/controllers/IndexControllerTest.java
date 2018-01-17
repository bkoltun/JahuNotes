package pl.jahw.apps.jahwnotes.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import pl.jahw.apps.jahwnotes.domain.Note;
import pl.jahw.apps.jahwnotes.services.NoteService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    @Mock
    NoteService noteService;

    @Mock
    Model model;

    IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(noteService);
    }

    @Test
    public void index() {
        //given
        String indexValue = new String("index");

        Set<Note> notes = new HashSet<>();

        Note note = new Note();
        note.setId(2L);
        notes.add(note);
        Note note2 = new Note();
        note.setId(3L);
        notes.add(note2);

        when(noteService.getNotes()).thenReturn(notes);

        ArgumentCaptor<Set<Note>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = indexController.index(model);

        //then
        assertEquals(indexValue, viewName);
        verify(noteService, times(1)).getNotes();
        verify(model, times(1)).addAttribute(eq("notes"), argumentCaptor.capture());

        Set<Note> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}