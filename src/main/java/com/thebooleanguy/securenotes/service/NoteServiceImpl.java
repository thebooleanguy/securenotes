package com.thebooleanguy.securenotes.service;

import com.thebooleanguy.securenotes.model.Note;
import com.thebooleanguy.securenotes.model.User;
import com.thebooleanguy.securenotes.repository.NoteRepository;
import com.thebooleanguy.securenotes.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        User currentUser = getCurrentAuthenticatedUser();
        return noteRepository.findByUser(currentUser);
    }

    @Override
    public List<Note> getAllNotesForAdmin() {
        return noteRepository.findAll();
    }

    private User getCurrentAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new RuntimeException("No authenticated user found");
        }

        String username = auth.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
