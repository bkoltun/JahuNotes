package pl.jahw.apps.jahwnotes.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.jahw.apps.jahwnotes.command.NoteCommand;
import pl.jahw.apps.jahwnotes.exceptions.NotFoundException;
import pl.jahw.apps.jahwnotes.services.NoteService;

import javax.validation.Valid;

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
    public String saveOrUpdate(@Valid @ModelAttribute("note") NoteCommand command, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->{
                log.debug(objectError.toString());
            });
            return "note/noteform";
        }
        NoteCommand savedCommand = noteService.saveNoteCommand(command);

        return "redirect:/note/" + savedCommand.getId() + "/show/";
    }

    @GetMapping("note/{id}/delete")
    public String deleteById(@PathVariable String id) {

        log.debug("Deleting id: " + id);

        noteService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

}
