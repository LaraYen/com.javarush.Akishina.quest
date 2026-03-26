package com.javarush.akishina.repository;

import com.javarush.akishina.QuestLoader;
import com.javarush.akishina.entity.Quest;

public class QuestRepository extends BaseRepository<Quest>{

    @Override
    public void initRepo() {
        data = QuestLoader.loadQuests();
    }
}
