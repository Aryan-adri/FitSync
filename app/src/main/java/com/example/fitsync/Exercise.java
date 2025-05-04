package com.example.fitsync;

import java.util.List;

public class Exercise {
    private String bodyPart;
    private String equipment;
    private String gifUrl;
    private String id;
    private String name;
    private String target;
    private List<String> secondaryMuscles;
    private List<String> instructions;

    // Getters
    public String getName() { return name; }
    public String getEquipment() { return equipment; }
    public String getGifUrl() { return gifUrl; }
    public String getTarget() { return target; }
    public List<String> getSecondaryMuscles() { return secondaryMuscles; }
    public List<String> getInstructions() { return instructions; }
}


