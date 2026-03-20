import com.javarush.akishina.QuestSession;
import com.javarush.akishina.entity.Action;
import com.javarush.akishina.entity.Outcome;
import com.javarush.akishina.entity.Quest;
import com.javarush.akishina.entity.Scene;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static com.javarush.akishina.SessionAttribute.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class QuestSessionTest {

    private HttpSession session;
    private QuestSession questSession;
    private Quest testQuest;
    private Scene testScene;

    @BeforeEach
    void setUp() {
        session = mock(HttpSession.class);
        questSession = new QuestSession(session);
        testQuest = new Quest("тест", "тест Описание", 1, new HashMap<>());
        testScene = new Scene(1, "тест", "тест описание", Outcome.VICTORY, new Action[0]);
    }

    @Test
    void getUserNameReturnsCorrectValue() {
        when(session.getAttribute(USER_NAME.getAttributeName())).thenReturn("Alice");
        assertEquals("Alice", questSession.getUserName());
    }

    @Test
    void getUserNameReturnsNullIfNotSet() {
        when(session.getAttribute(USER_NAME.getAttributeName())).thenReturn(null);
        assertNull(questSession.getUserName());
    }

    @Test
    void setUserNameStoresValue() {
        questSession.setUserName("Bob");
        verify(session).setAttribute(USER_NAME.getAttributeName(), "Bob");
    }

    @Test
    void getCurrentQuestReturnsCorrectValue() {
        when(session.getAttribute(CURR_QUEST.getAttributeName())).thenReturn(testQuest);
        assertEquals(testQuest, questSession.getCurrentQuest());
    }

    @Test
    void getCurrentQuestReturnsNullIfTypeMismatch() {
        when(session.getAttribute(CURR_QUEST.getAttributeName())).thenReturn("not a quest");
        assertNull(questSession.getCurrentQuest());
    }

    @Test
    void setCurrentQuestStoresValue() {
        questSession.setCurrentQuest(testQuest);
        verify(session).setAttribute(CURR_QUEST.getAttributeName(), testQuest);
    }

    @Test
    void getCurrentSceneReturnsCorrectValue() {

        when(session.getAttribute(CURR_SCENE.getAttributeName())).thenReturn(testScene);
        assertEquals(testScene, questSession.getCurrentScene());
    }

    @Test
    void getCurrentSceneReturnsNullIfTypeMismatch() {
        when(session.getAttribute(CURR_SCENE.getAttributeName())).thenReturn("not a scene");
        assertNull(questSession.getCurrentScene());
    }

    @Test
    void setCurrentSceneStoresValue() {
        questSession.setCurrentScene(testScene);
        verify(session).setAttribute(CURR_SCENE.getAttributeName(), testScene);
    }

    @Test
    void incrementWinQuestIncreasesCounter() {
        when(session.getAttribute(WIN_COUNT.getAttributeName())).thenReturn(5);
        questSession.incrementWinQuest();
        verify(session).setAttribute(WIN_COUNT.getAttributeName(), 6);
    }

    @Test
    void incrementDefeatQuestIncreasesCounter() {
        when(session.getAttribute(DEFEAT_COUNT.getAttributeName())).thenReturn(5);
        questSession.incrementDefeatQuest();
        verify(session).setAttribute(DEFEAT_COUNT.getAttributeName(), 6);
    }

}
