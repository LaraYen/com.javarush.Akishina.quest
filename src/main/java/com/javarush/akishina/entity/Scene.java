package com.javarush.akishina.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Scene {

    private int id;
    private String title;
    private String description;
    private List<Action> actionsList;

}
