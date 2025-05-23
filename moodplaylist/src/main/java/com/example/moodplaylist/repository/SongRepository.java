package com.example.moodplaylist.repository;

import com.example.moodplaylist.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByMood(String mood);
}
