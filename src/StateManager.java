import java.util.ArrayList;
import java.util.List;

// Singleton StateManager
public class StateManager {
    private static StateManager instance;

    public State<Integer> keyCode = new State<>(); // Use generic State for int type

    private StateManager() {
        // private constructor to prevent instantiation
    }

    public static StateManager getInstance() {
        if (instance == null) {
            instance = new StateManager();
        }
        return instance;
    }
}

// Generic State class
class State<T> {
    private T state;
    private List<Observer<T>> observers = new ArrayList<>();

    public T getState() {
        return state;
    }

    public void setState(T state) {
        this.state = state;
        notifyObservers();
    }

    public void addObserver(Observer<T> observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (Observer<T> observer : observers) {
            observer.update(state);
        }
    }
}

// Generic Observer Interface
interface Observer<T> {
    void update(T state);
}

// Concrete Observer for String state
class StringObserver implements Observer<String> {
    @Override
    public void update(String state) {
        System.out.println("String state changed to: " + state);
    }
}

// Concrete Observer for Integer state
class IntObserver implements Observer<Integer> {
    @Override
    public void update(Integer state) {
        System.out.println("Integer state changed to: " + state);
    }
}
