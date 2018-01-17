package pl.jahw.apps.jahwnotes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.jahw.apps.jahwnotes.command.NoteCommand;
import pl.jahw.apps.jahwnotes.domain.Note;

@Component
public class NoteToNoteCommand implements Converter<Note, NoteCommand> {
    @Override
    public NoteCommand convert(Note source) {

        if (source == null) return null;

        final NoteCommand command = new NoteCommand();

        command.setId(source.getId());
        command.setText(source.getText());
        command.setCategory(source.getCategory());
        command.setTitle(source.getTitle());

        return command;
    }
}
