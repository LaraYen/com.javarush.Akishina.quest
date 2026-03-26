package com.javarush.akishina.service;

import com.javarush.akishina.entity.Action;
import com.javarush.akishina.entity.Outcome;
import com.javarush.akishina.entity.Quest;
import com.javarush.akishina.entity.Scene;
import com.javarush.akishina.repository.ActionRepository;
import com.javarush.akishina.repository.QuestRepository;
import com.javarush.akishina.repository.SceneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestServiceTest {

    @Mock
    QuestRepository questRepository;

    @Mock
    SceneRepository sceneRepository;

    @Mock
    ActionRepository actionRepository;

    @InjectMocks
    QuestService questService;

    @Test
    void getFirstSceneByQuestIdTest() {

        Quest testQuest = new Quest("1", "тест", "тест", "1-2");
        String[] actions = new String[0];
        Scene expectedScene = new Scene("1-2", "тест сцена", "тест описание", Outcome.NONE, actions, "1");

        when(questRepository.findById("1")).thenReturn(testQuest);
        when(sceneRepository.findById("1-2")).thenReturn(expectedScene);

        Scene actualScene = questService.getFirstSceneByQuestId(testQuest.getId());

        assertEquals(expectedScene, actualScene);

    }

    @Test
    void getActionsByArrayIdTest() {

        String[] actionIds = new String[2];
        actionIds[0] = "1-1-1";
        actionIds[1] = "2-2-2";

        Action actionTest1 = new Action(actionIds[0], "3-2", "тест действие", "3-3");
        Action actionTest2 = new Action(actionIds[1], "4-2", "тест действие2", "4-3");
        Action[] expectedActions = new Action[2];
        expectedActions[0] = actionTest1;
        expectedActions[1] = actionTest2;

        when(actionRepository.findById("1-1-1")).thenReturn(actionTest1);
        when(actionRepository.findById("2-2-2")).thenReturn(actionTest2);

        Action[] actualActions = questService.getActionsByArrayId(actionIds);

        assertArrayEquals(expectedActions, actualActions);
    }
}