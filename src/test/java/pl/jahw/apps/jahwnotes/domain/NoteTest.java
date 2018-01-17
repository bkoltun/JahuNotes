package pl.jahw.apps.jahwnotes.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteTest {

    Note note;

    @Before
    public void setUp() {
        note = new Note();

    }

    @Test
    public void getId() {
        Long idValue = new Long(4L);

        note.setId(idValue);

        assertEquals(idValue, note.getId());
    }

    @Test
    public void getTitle() {
        String titleValue = new String("Title");

        note.setTitle(titleValue);

        assertEquals(titleValue, note.getTitle());
    }

    @Test
    public void getCategory() {
        String categoryValue = new String("Category");

        note.setCategory(categoryValue);

        assertEquals(categoryValue, note.getCategory());
    }

    @Test
    public void getText() {
        String textValue = new String("Text");

        note.setText(textValue);

        assertEquals(textValue, note.getText());
    }

}