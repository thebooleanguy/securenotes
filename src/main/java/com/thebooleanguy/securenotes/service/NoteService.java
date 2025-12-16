package com.thebooleanguy.securenotes.service;

import com.thebooleanguy.securenotes.model.Note;

import java.util.List;

public interface NoteService {
    Note createNote(String content);
    List<Note> getMyNotes();
    List<Note> getAllNotesForAdmin();
}
