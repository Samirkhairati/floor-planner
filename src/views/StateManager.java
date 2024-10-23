package views;

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

    // Generic State class
    public class State<T> {
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

    public static class Observer<T> {
        public void update(T state) {
            System.out.println("State changed to: " + state);
        }
    }
}
