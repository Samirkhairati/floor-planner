package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Singleton StateManager
public class StateManager implements Serializable {
    private static StateManager instance;

    public State<Integer> keyCode = new State<>(); // Use generic State for int type

    public State<Boolean> showLineGrid = new State<>(true); // Use generic State for boolean type
    public State<Boolean> showDotGrid = new State<>(false); // Use generic State for boolean type

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
    public static class State<T> implements Serializable {
        private T state;
        private List<Observer<T>> observers = new ArrayList<>();

        public State() {
            // Default constructor
        }

        public State(T state) {
            this.state = state;
        }

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

    public static class Observer<T> implements Serializable {
        public void update(T state) {
            System.out.println("State changed to: " + state);
        }
    }
}
