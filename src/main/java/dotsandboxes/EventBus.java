package dotsandboxes;
import dotsandboxes.observers.EventType;
import dotsandboxes.observers.IDotsAndBoxesObserver;
import java.util.ArrayList;
import java.util.List;
public class EventBus {
    private List<IDotsAndBoxesObserver> observers = new ArrayList<>();
    private static EventBus instance;
    private EventBus(){}
    public static EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    public void attach(IDotsAndBoxesObserver observer) {
        observers.add(observer);
    }

    public void detach(IDotsAndBoxesObserver observer) {
        observers.remove(observer);
    }


    public void postEvent(EventType eventType, Object eventObject) {
        for (IDotsAndBoxesObserver observer : observers) {
            observer.update(eventType, eventObject);
        }
    }

}
