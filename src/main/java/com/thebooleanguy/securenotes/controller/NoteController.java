package com.thebooleanguy.securenotes.controller;

import com.thebooleanguy.securenotes.model.DTOs.CreateNoteRequest;
import com.thebooleanguy.securenotes.model.Note;
import com.thebooleanguy.securenotes.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController (NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateNoteRequest request) {
        return ResponseEntity.ok(noteService.createNote(request.getContent()));
    }


    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Note> getMyNotes() {
        return noteService.getMyNotes();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Note> getAllNotesForAdmin() {
        return noteService.getAllNotesForAdmin();
    }
}
