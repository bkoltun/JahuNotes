package pl.jahw.apps.jahwnotes.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jahw.apps.jahwnotes.command.NoteCommand;
import pl.jahw.apps.jahwnotes.services.NoteService;

@Slf4j
@Controller
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("note/{id}/show")
    public String showNoteById(@PathVariable String id, Model model) {

        model.addAttribute("note", noteService.findById(Long.valueOf(id)));
        return "note/show";
    }

    @GetMapping("note/new")
    public String newNote(Model model) {

        log.debug("New note (new button)");
        model.addAttribute("note", new NoteCommand());

        return "note/noteform";
    }

    @GetMapping("note/{id}/update")
    public String updateNote(@PathVariable String id, Model model) {

        model.addAttribute("note", noteService.findById(Long.valueOf(id)));
        return "note/noteform";
    }

    @PostMapping("note")
    public String saveOrUpdate(@ModelAttribute("note") NoteCommand command) {
        NoteCommand savedCommand = noteService.saveNoteCommand(command);

        return "redirect:/note/" + savedCommand.getId() + "/show/";
    }

    @GetMapping("note/{id}/delete")
    public String deleteById(@PathVariable String id) {

        log.debug("Deleting id: " + id);

        noteService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

}
