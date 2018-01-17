package pl.jahw.apps.jahwnotes.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.jahw.apps.jahwnotes.command.NoteCommand;
import pl.jahw.apps.jahwnotes.converters.NoteCommandToNote;
import pl.jahw.apps.jahwnotes.converters.NoteToNoteCommand;
import pl.jahw.apps.jahwnotes.domain.Note;
import pl.jahw.apps.jahwnotes.repositories.NoteRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteToNoteCommand noteToNoteCommand;
    private final NoteCommandToNote noteCommandToNote;

    public NoteServiceImpl(NoteRepository noteRepository, NoteToNoteCommand noteToNoteCommand, NoteCommandToNote noteCommandToNote) {
        this.noteRepository = noteRepository;
        this.noteToNoteCommand = noteToNoteCommand;
        this.noteCommandToNote = noteCommandToNote;
    }

    @Override
    public Set<Note> getNotes() {
        log.debug("Getting all notes (NoteService)");

        Set<Note> notesSet = new HashSet<>();
        noteRepository.findAll().iterator().forEachRemaining(notesSet::add);

        return notesSet;
    }

    @Override
    public Note findById(Long id) {

        log.debug("Getting note by id: " + id);

        Optional<Note> optionalNote = noteRepository.findById(id);

        return optionalNote.get();
    }

    @Override
    public NoteCommand findCommandById(Long id) {
        return noteToNoteCommand.convert(findById(id));
    }

    @Override
    public NoteCommand saveNoteCommand(NoteCommand command) {

        Note detachedNote = noteCommandToNote.convert(command);

        Note savedNote = noteRepository.save(detachedNote);
        log.debug("Saved Note with ID: " + savedNote.getId());

        return noteToNoteCommand.convert(savedNote);

    }

    @Override
    public void deleteById(Long idToDelete) {
        noteRepository.deleteById(idToDelete);
    }
}
