package com.javarush.akishina;

public enum SessionAttribute {

    USER_NAME("userName"),
    CURR_QUEST("currQuest"),
    CURR_SCENE("currScene"),
    QUESTS_MAP("questsMap"),
    CLIENT_IP("clientIp"),
    COMPLETED_QUESTS("completedQuests"),
    WIN_COUNT("winCount"),
    DEFEAT_COUNT("defeatCount");


    private final String attribute;

    SessionAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getAttributeName() {
        return attribute;
    }

}
