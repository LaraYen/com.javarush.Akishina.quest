import com.javarush.akishina.QuestLoader;
import com.javarush.akishina.entity.Action;
import com.javarush.akishina.entity.Outcome;
import com.javarush.akishina.entity.Quest;
import com.javarush.akishina.entity.Scene;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class QuestLoaderTest {

    static Map<String, Quest> expectedQuestsMap = new HashMap<>();

    @BeforeAll
    static void setUp() {

        Action[] actions = new Action[3];
        actions[0] = new Action("Победа", 1);
        actions[1] = new Action("Провал", 2);
        actions[2] = new Action("Провал", 3);


        Map<Integer, Scene> scenesMap = new LinkedHashMap<>();
        scenesMap.put(1, new Scene(1, "тест сцена 1", "тест описание сцены 1", Outcome.NONE, actions));
        scenesMap.put(2, new Scene(2, "тест сцена 2", "тест описание сцены 2", Outcome.DEFEAT, new Action[0]));
        scenesMap.put(3, new Scene(3, "тест сцена 3", "тест описание сцены 3", Outcome.VICTORY, new Action[0]));


        Quest test1 = new Quest("тест", "тест описание", 1, scenesMap);
        Quest test2 = new Quest("тест2", "тест описание2", 1, scenesMap);

        expectedQuestsMap.put(test1.getTitle(), test1);
        expectedQuestsMap.put(test2.getTitle(), test2);
    }

    @Test
    void loadQuestsTest() {

        QuestLoader questLoader = new QuestLoader();

        Map<String, Quest> actualQuestsMap = questLoader.loadQuests();
        Assertions.assertEquals(expectedQuestsMap, actualQuestsMap);

    }


}
