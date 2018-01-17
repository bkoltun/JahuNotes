package pl.jahw.apps.jahwnotes.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.jahw.apps.jahwnotes.converters.NoteCommandToNote;
import pl.jahw.apps.jahwnotes.converters.NoteToNoteCommand;
import pl.jahw.apps.jahwnotes.domain.Note;
import pl.jahw.apps.jahwnotes.repositories.NoteRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NoteServiceImplTest {


    NoteServiceImpl noteService;

    @Mock
    NoteToNoteCommand noteToNoteCommand;

    @Mock
    NoteCommandToNote noteCommandToNote;

    @Mock
    NoteRepository noteRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        noteService = new NoteServiceImpl(noteRepository, noteToNoteCommand, noteCommandToNote);
    }

    @Test
    public void getNotes() throws Exception {
        Note note = new Note();
        HashSet<Note> notesData = new HashSet<>();
        notesData.add(note);

        //When
        when(noteService.getNotes()).thenReturn(notesData);

        Set<Note> notes = noteService.getNotes();
        assertEquals(notes.size(),1);
        verify(noteRepository, times(1)).findAll();
    }

}