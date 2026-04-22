import dotsandboxes.EventBus;
import dotsandboxes.GameElements.Edge;
import dotsandboxes.GameElements.Grid;
import dotsandboxes.observers.EventType;
import dotsandboxes.observers.IDotsAndBoxesObserver;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class EventBusTest implements IDotsAndBoxesObserver {
    private final List<EventType> messages = new ArrayList<>();

    @Override
    public void update(EventType eventType, Object eventObject) {
        messages.add(eventType);

    }
    public boolean contains(EventType eventType){
        return messages.contains(eventType);
    }
    @Test
    void testDetach() {
        EventBus eventBus = EventBus.getInstance();
        EventBusTest observer = new EventBusTest();
        Grid grid = Grid.getNewBuilder().setRows(5).setColumns(5).build();
        Edge edge = grid.getHorizontalEdges()[0][0];
        eventBus.attach(observer);
        eventBus.postEvent(EventType.CLAIMED_EDGE,edge);
        assertTrue(observer.contains(EventType.CLAIMED_EDGE));
        eventBus.detach(observer);
        eventBus.postEvent(EventType.GAME_DONE,edge);
        assertFalse(observer.contains(EventType.GAME_DONE));
    }
}
