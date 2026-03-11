package com.javarush.akishina;

import com.javarush.akishina.entity.Quest;
import com.javarush.akishina.entity.Scene;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static com.javarush.akishina.SessionAttribute.*;

public class QuestSession {

    private final HttpSession session;

    public QuestSession(HttpSession session) {
        this.session = session;
    }

    private <T> T getAttribute(SessionAttribute name, Class<T> clazz) {
        Object obj = session.getAttribute(name.getAttributeName());
        if (clazz.isInstance(obj)) {
            return (T) obj;
        }
        return null;
    }

    public HashMap<String, Quest> getQuestsMap() {
        return getAttribute(QUESTS_MAP, HashMap.class);
    }

    public void setQuestsMap(Map<String, Quest> questsMap) {
        session.setAttribute(QUESTS_MAP.getAttributeName(), questsMap);
    }

    public String getUserName() {
        return getAttribute(USER_NAME, String.class);
    }

    public void setUserName(String userName) {
        session.setAttribute(USER_NAME.getAttributeName(), userName);
    }

    public Quest getCurrentQuest() {
        return getAttribute(CURR_QUEST, Quest.class);
    }

    public void setCurrentQuest(Quest quest) {
        session.setAttribute(CURR_QUEST.getAttributeName(), quest);
    }

    public Scene getCurrentScene() {
        return getAttribute(CURR_SCENE, Scene.class);
    }

    public void setCurrentScene(Scene scene) {
        session.setAttribute(CURR_SCENE.getAttributeName(), scene);
    }

    public void setClientIp(String ip) {
        session.setAttribute(CLIENT_IP.getAttributeName(), ip);
    }

    public String getClientIp() {
        return getAttribute(CLIENT_IP, String.class);
    }

    public void setCompletedQuests(int count) {
        session.setAttribute(COMPLETED_QUESTS.getAttributeName(), count);
    }

    public int getCompletedQuests() {
        Integer count = getAttribute(COMPLETED_QUESTS, Integer.class);
        return count != null ? count : 0;
    }

    public void setWinCount(int count) {
        session.setAttribute(WIN_COUNT.getAttributeName(), count);
    }

    public int getWinCount() {
        Integer count = getAttribute(WIN_COUNT, Integer.class);
        return count != null ? count : 0;
    }

    public void setDefeatCount(int count) {
        session.setAttribute(DEFEAT_COUNT.getAttributeName(), count);
    }

    public int getDefeatCount() {
        Integer count = getAttribute(DEFEAT_COUNT, Integer.class);
        return count != null ? count : 0;
    }

    public void incrementCompletedQuest() {
        setCompletedQuests(getCompletedQuests() + 1);
    }

    public void incrementWinQuest() {
        setWinCount(getWinCount() + 1);
    }

    public void incrementDefeatQuest() {
        setDefeatCount(getDefeatCount() + 1);
    }

    public boolean isQuestActive() {
        return getCurrentQuest() != null && getCurrentScene() != null;
    }

    public void removeCurrQuest() {
        session.removeAttribute(CURR_QUEST.getAttributeName());
        session.removeAttribute(CURR_SCENE.getAttributeName());
    }
}
