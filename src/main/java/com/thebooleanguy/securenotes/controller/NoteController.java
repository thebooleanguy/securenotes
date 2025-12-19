package com.thebooleanguy.securenotes.controller;

import com.thebooleanguy.securenotes.model.Note;
import com.thebooleanguy.securenotes.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController (NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Note note) {
        return ResponseEntity.ok(noteService.createNote(note.getContent()));
    }

    public List<Note> getMyNotes() {
        return noteService.getMyNotes();
    }
}
