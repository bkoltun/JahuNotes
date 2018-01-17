package pl.jahw.apps.jahwnotes.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.jahw.apps.jahwnotes.domain.Note;

public interface NoteRepository extends CrudRepository<Note, Long> {


}
