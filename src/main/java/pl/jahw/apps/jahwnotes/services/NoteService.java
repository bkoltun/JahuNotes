package pl.jahw.apps.jahwnotes.services;

import pl.jahw.apps.jahwnotes.command.NoteCommand;
import pl.jahw.apps.jahwnotes.domain.Note;

import java.util.Set;

public interface NoteService {

    Set<Note> getNotes();

    Note findById(Long id);

    NoteCommand findCommandById(Long l);

    NoteCommand saveNoteCommand(NoteCommand command);

    void deleteById(Long id);


}
