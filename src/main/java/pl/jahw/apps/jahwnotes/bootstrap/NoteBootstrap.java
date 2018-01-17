package pl.jahw.apps.jahwnotes.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.jahw.apps.jahwnotes.domain.Note;
import pl.jahw.apps.jahwnotes.repositories.NoteRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class NoteBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final NoteRepository noteRepository;

    public NoteBootstrap(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        noteRepository.saveAll(getNotes());
        log.debug("Bootstrap data saved. 3 notes.");
    }

    private List<Note> getNotes() {

        List<Note> notes = new ArrayList<>(3);

        Note note1 = new Note();
        note1.setTitle("First Note");
        note1.setText("This is my first note so it can be dummy and short.");
        note1.setCategory("Dummy");
        notes.add(note1);

        Note note2 = new Note();
        note2.setTitle("Second Note");
        note2.setCategory("Event");
        note2.setText("This is my second note. No one expect 2nd note this fast.");
        notes.add(note2);

        Note note3 = new Note();
        note3.setTitle("Third Note");
        note3.setCategory("Thought");
        note3.setText("This is my trhid note. No one expect 3nd note this fast.");
        notes.add(note3);

        return notes;
    }
}
