package com.javarush.akishina.entity;

import com.fasterxml.jackson.annotation.JsonAlias;

public enum Outcome {

    @JsonAlias("victory")
    VICTORY,

    @JsonAlias("defeat")
    DEFEAT,

    @JsonAlias("none")
    NONE

}
