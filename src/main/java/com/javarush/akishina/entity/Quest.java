package com.javarush.akishina.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

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
}
