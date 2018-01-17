package pl.jahw.apps.jahwnotes.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NoteCommand {

    private Long id;

    private String title;

    private String category;

    private String text;

}
