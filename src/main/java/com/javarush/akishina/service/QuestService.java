package com.javarush.akishina.service;

import com.javarush.akishina.entity.Action;
import com.javarush.akishina.entity.Quest;
import com.javarush.akishina.entity.Scene;
import com.javarush.akishina.repository.ActionRepository;
import com.javarush.akishina.repository.QuestRepository;
import com.javarush.akishina.repository.SceneRepository;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class QuestService {

    private QuestRepository questRepository = new QuestRepository();
    private SceneRepository sceneRepository = new SceneRepository();
    private ActionRepository actionRepository = new ActionRepository();

    //for tests
    public QuestService(QuestRepository questRepository, SceneRepository sceneRepository, ActionRepository actionRepository) {
        this.questRepository = questRepository;
        this.sceneRepository = sceneRepository;
        this.actionRepository = actionRepository;
    }

    public Scene getFirstSceneByQuestId(String id) {
        Quest quest = getQuestById(id);
        return getSceneById(quest.getFirstSceneId());
    }

    public Action[] getActionsByArrayId(String[] actionIdArray) {

        Action[] actions = new Action[actionIdArray.length];
        for (int i = 0; i < actionIdArray.length; i++) {
            actions[i] = getActionById(actionIdArray[i]);
        }

        return actions;
    }

    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    public List<Scene> getAllScenes() {
        return sceneRepository.findAll();
    }

    public List<Action> getAllActions() {
        return actionRepository.findAll();
    }

    public Quest getQuestById(String id) {
        return questRepository.findById(id);
    }

    public Scene getSceneById(String id) {
        return sceneRepository.findById(id);
    }

    public Action getActionById(String id) {
        return actionRepository.findById(id);
    }
}
