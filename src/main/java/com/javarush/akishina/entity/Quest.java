package com.javarush.akishina.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quest {
    private String title;

    private String description;
    private int firstSceneId;
    private Map<Integer, Scene> scenesMap;

    public Scene getFirstScene() {
        return scenesMap.get(firstSceneId);
    }

    public Scene getScene(int id) {
        return scenesMap.get(id);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Quest quest = (Quest) o;
        return firstSceneId == quest.firstSceneId && Objects.equals(title, quest.title) && Objects.equals(description, quest.description) && Objects.equals(scenesMap, quest.scenesMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, firstSceneId, scenesMap);
    }
}
