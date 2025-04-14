package com.example.moodplaylist.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MoodAnalyzerService {

    private Map<String, String> moodMapping;

    public MoodAnalyzerService() {
        moodMapping = new HashMap<>();

        // Adding words for each mood
        moodMapping.put("happy", "happy");
        moodMapping.put("joy", "happy");
        moodMapping.put("excited", "happy");
        moodMapping.put("cheerful", "happy");
        moodMapping.put("elated", "happy");
        moodMapping.put("delighted", "happy");
        moodMapping.put("optimistic", "happy");

        moodMapping.put("sad", "sad");
        moodMapping.put("low", "sad");
        moodMapping.put("depressed", "sad");
        moodMapping.put("down", "sad");
        moodMapping.put("unhappy", "sad");
        moodMapping.put("gloomy", "sad");
        moodMapping.put("melancholy", "sad");

        moodMapping.put("angry", "angry");
        moodMapping.put("furious", "angry");
        moodMapping.put("rage", "angry");
        moodMapping.put("mad", "angry");
        moodMapping.put("irritated", "angry");
        moodMapping.put("annoyed", "angry");
        moodMapping.put("fuming", "angry");

        moodMapping.put("calm", "calm");
        moodMapping.put("peace", "calm");
        moodMapping.put("relaxed", "calm");
        moodMapping.put("soothing", "calm");
        moodMapping.put("serene", "calm");
        moodMapping.put("chill", "calm");
        moodMapping.put("laid-back", "calm");

        // Add more moods and words as needed
    }

    public String analyzeMood(String input) {
        String lowerInput = input.toLowerCase();

        for (String mood : moodMapping.keySet()) {
            if (lowerInput.contains(mood)) {
                return moodMapping.get(mood);
            }
        }
        return "neutral"; // default mood if no match is found
    }
}
