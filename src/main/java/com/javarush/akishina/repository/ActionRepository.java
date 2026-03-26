package com.javarush.akishina.repository;

import com.javarush.akishina.QuestLoader;
import com.javarush.akishina.entity.Action;

public class ActionRepository extends BaseRepository<Action>{
    @Override
    public void initRepo() {
        data = QuestLoader.loadActions();
    }
}
