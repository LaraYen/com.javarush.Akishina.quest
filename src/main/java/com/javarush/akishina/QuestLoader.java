package com.javarush.akishina;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.akishina.entity.Quest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public final class QuestLoader {

    private final ObjectMapper objectMapper;

    public QuestLoader() {
        this.objectMapper = new ObjectMapper();
    }

    public Quest loadFromResource(String resourcePath) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new IOException("Resource not found: " + resourcePath);
        }
        return objectMapper.readValue(inputStream, Quest.class);
    }

    public Map<String, Quest> loadQuests() throws IOException, URISyntaxException {

        URL resourceUrl = getClass().getClassLoader().getResource(".");
        if (resourceUrl == null) {
            throw new IOException("Папка resources не найдена");
        }

        File resourcesDir = new File(resourceUrl.toURI());
        Map<String, Quest> result = new HashMap<>();
        //List<Quest> results = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        for (File file : Objects.requireNonNull(resourcesDir.listFiles())) {
            if (file.isFile() && file.getName().endsWith("nlo-quest.json")) {
                Quest quest = mapper.readValue(file, Quest.class);
                result.put(quest.getTitle(), quest);
                System.out.println("Обработан файл: " + file.getName());
            }
        }

        return result;

    }



}
