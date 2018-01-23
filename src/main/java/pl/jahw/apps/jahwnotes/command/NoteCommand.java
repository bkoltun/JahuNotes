package pl.jahw.apps.jahwnotes.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class NoteCommand {


    private Long id;

    @NotBlank
    @Size(min = 3, max = 40)
    private String title;

    @NotBlank
    private String category;

    @NotBlank
    @Size(max = 1000)
    private String text;

}
