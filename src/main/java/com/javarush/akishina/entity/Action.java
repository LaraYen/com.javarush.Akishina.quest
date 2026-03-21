package com.javarush.akishina.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Action {

    private String text;
    private int nextSceneId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return nextSceneId == action.nextSceneId && Objects.equals(text, action.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, nextSceneId);
    }
}
