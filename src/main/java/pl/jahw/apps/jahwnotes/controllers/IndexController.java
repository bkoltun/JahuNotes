package pl.jahw.apps.jahwnotes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jahw.apps.jahwnotes.services.NoteService;

@Controller
public class IndexController {

    private final NoteService noteService;

    public IndexController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("notes", noteService.getNotes());
        return "index";
    }

}
