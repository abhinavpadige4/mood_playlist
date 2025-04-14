package com.example.moodplaylist.controller;

import com.example.moodplaylist.model.Song;
import com.example.moodplaylist.model.UserInput;
import com.example.moodplaylist.repository.SongRepository;
import com.example.moodplaylist.service.MoodAnalyzerService;
import com.example.moodplaylist.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
@CrossOrigin(origins = "http://localhost:3000")
public class SongController {

    @Autowired
    private MoodAnalyzerService moodAnalyzerService;

    @Autowired
    private SongRepository songRepository; // ✅ FIXED: Now it's autowired properly

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    public ResponseEntity<String> addSong(@RequestBody Song song) {
        if (song.getTitle() == null || song.getArtist() == null || song.getMood() == null || song.getLink() == null) {
            return ResponseEntity.badRequest().body("All fields are required");
        }
        songRepository.save(song);
        return ResponseEntity.status(HttpStatus.CREATED).body("Song added successfully");
    }

    @GetMapping("/{mood}")
    public ResponseEntity<Song> getSongByMood(@PathVariable String mood) {
        Song song = songService.getRandomSongByMood(mood);
        if (song == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(song);
    }

    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs() {
        return ResponseEntity.ok(songRepository.findAll());
    }

    @PostMapping("/analyze")
    public ResponseEntity<Song> analyzeAndSendSong(@RequestBody UserInput userInput) {
        String mood = moodAnalyzerService.analyzeMood(userInput.getMessage());
        Song song = songService.getRandomSongByMood(mood);
        if (song == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(song);
    }
}
