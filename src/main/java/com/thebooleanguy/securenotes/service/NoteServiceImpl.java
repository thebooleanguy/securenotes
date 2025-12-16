package com.thebooleanguy.securenotes.service;

import com.thebooleanguy.securenotes.model.Note;
import com.thebooleanguy.securenotes.model.User;
import com.thebooleanguy.securenotes.repository.NoteRepository;
import com.thebooleanguy.securenotes.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository){
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Note createNote(String content) {
        User currentUser = getCurrentAuthenticatedUser();

        Note note = new Note();
        note.setContent(content);
        note.setUser(currentUser);

        return noteRepository.save(note);
    }

    @Override
    public List<Note> getMyNotes() {
        return List.of();
    }

    @Override
    public List<Note> getAllNotesForAdmin() {
        return List.of();
    }

    private User getCurrentAuthenticatedUser() {
        // placeholder for JWT-based user resolution
        return null;
    }
}
