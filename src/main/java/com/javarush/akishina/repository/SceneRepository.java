package com.javarush.akishina.repository;

import com.javarush.akishina.QuestLoader;
import com.javarush.akishina.entity.Scene;

public class SceneRepository extends BaseRepository<Scene>{
    @Override
    public void initRepo() {
        data = QuestLoader.loadScenes();
    }
}
