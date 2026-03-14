package com.javarush.akishina;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.akishina.entity.Quest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
public final class QuestLoader {

    private final ObjectMapper objectMapper;

    public QuestLoader() {
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true);
    }

    public Map<String, Quest> loadQuests() {

        Map<String, Quest> result = new HashMap<>();
        List<String> questFileNameList = loadQuestFileNameList();

        if (!questFileNameList.isEmpty()) {
            questFileNameList.forEach(qfn -> {
                Quest quest = parseQuestFromJson(qfn);
                if (quest != null) {
                    result.put(quest.getTitle(), quest);
                }
            });
        }

        return result;
    }

    private List<String> loadQuestFileNameList() {

        List<String> questFiles = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("quests/quest-list.json")){

            if (inputStream == null) {
                log.warn("Файл со списком квестов не найден: quests/quest-list.json");
                return questFiles;
            }
            questFiles = objectMapper.readValue(inputStream, new TypeReference<List<String>>() {});

        } catch (IOException e) {
            log.warn("Ошибка при обработке файла со списком квестов.");
            return null;
        }

        log.debug("Список файлов с квестами: {}", questFiles);
        return questFiles;
    }

    private Quest parseQuestFromJson(String fileName) {

        try (InputStream is = getClass().getClassLoader().getResourceAsStream("quests/" + fileName)) {

            if (is == null) {
                log.warn("Файл квеста не найден: quests/{}", fileName);
                return null;
            }

            return objectMapper.readValue(is, Quest.class);

        } catch (IOException e) {
            log.warn("Ошибка при обработке файла квеста: {}", fileName, e);
            return null;
        }
    }
}
