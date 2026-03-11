package com.javarush.akishina.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Scene {

    private int id;
    private String title;
    private String description;
    private Outcome outcome;
    private Action[] actions;

    public boolean isFinalScene() {
        return outcome != Outcome.NONE;
    }
}
