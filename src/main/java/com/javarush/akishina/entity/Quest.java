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
public class Quest {

    private String id;
    private String title;
    private String description;
    private String firstSceneId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Quest quest = (Quest) o;
        return firstSceneId == quest.firstSceneId && Objects.equals(title, quest.title) && Objects.equals(description, quest.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, firstSceneId);
    }
}
