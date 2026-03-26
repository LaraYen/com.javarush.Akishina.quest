package com.javarush.akishina;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.akishina.entity.Action;
import com.javarush.akishina.entity.Quest;
import com.javarush.akishina.entity.Scene;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
public final class QuestLoader {

    private static final ObjectMapper objectMapper = new ObjectMapper() ;
    private static final String QUESTS_PATH = "quests/quests.json";
    private static final String SCENES_PATH = "quests/scenes.json";
    private static final String ACTIONS_PATH = "quests/actions.json";

    public static Map<String, Quest> loadQuests() {

        try (InputStream questsStream = getResourceAsStream(QUESTS_PATH)) {

            return objectMapper.readValue(questsStream,
                    new TypeReference<Map<String, Quest>>() {}
            );
        } catch (IOException e) {
            log.warn("Ошибка при загрузке файла {}", QUESTS_PATH);
        }
        return new HashMap<>();
    }

    public static Map<String, Scene> loadScenes() {

        try (InputStream scenesStream = getResourceAsStream(SCENES_PATH)) {

            return objectMapper.readValue(scenesStream,
                    new TypeReference<Map<String, Scene>>() {}
            );

        } catch (IOException e) {
            log.warn("Ошибка при загрузке файла {}, текст ошибки: {}", SCENES_PATH, e.getMessage());
        }

        return new HashMap<>();
    }

    public static Map<String, Action> loadActions() {

        try (InputStream actionsStream = getResourceAsStream(ACTIONS_PATH)) {

            return objectMapper.readValue(actionsStream,
                    new TypeReference<Map<String, Action>>() {}
            );
        } catch (IOException e) {
            log.warn("Ошибка при загрузке файла {}", ACTIONS_PATH);
        }
        return new HashMap<>();
    }

    private static InputStream getResourceAsStream(String filename) {

        InputStream is = QuestLoader.class.getClassLoader().getResourceAsStream(filename);
        if (is == null) {
            throw new RuntimeException("Файл не найден в ресурсах: " + filename);
        }
        return is;
    }

}
