package com.javarush.akishina.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Scene scene = (Scene) o;
        return id == scene.id && Objects.equals(title, scene.title) && Objects.equals(description, scene.description) && outcome == scene.outcome && Objects.deepEquals(actions, scene.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, outcome, Arrays.hashCode(actions));
    }
}
