package pl.jahw.apps.jahwnotes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.jahw.apps.jahwnotes.command.NoteCommand;
import pl.jahw.apps.jahwnotes.domain.Note;

@Component
public class NoteCommandToNote implements Converter<NoteCommand, Note> {
    @Override
    public Note convert(NoteCommand source) {

        if (source == null) return null;

        final Note note = new Note();

        note.setId(source.getId());
        note.setTitle(source.getTitle());
        note.setCategory(source.getCategory());
        note.setText(source.getText());

        return note;
    }
}
