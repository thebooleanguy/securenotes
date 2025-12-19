package com.thebooleanguy.securenotes.repository;

import com.thebooleanguy.securenotes.model.Note;
import com.thebooleanguy.securenotes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUser(User user);
}
