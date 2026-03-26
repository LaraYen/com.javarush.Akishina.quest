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

    private String id;
    private String sceneId;
    private String text;
    private String nextSceneId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return Objects.equals(id, action.id) && Objects.equals(sceneId, action.sceneId) && Objects.equals(text, action.text) && Objects.equals(nextSceneId, action.nextSceneId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sceneId, text, nextSceneId);
    }
}
