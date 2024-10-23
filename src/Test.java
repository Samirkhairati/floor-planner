import java.util.ArrayList;
import java.util.List;

public class Test {
    
    // public static void main(String[] args) {
    //     State state1 = new State();
    //     Observer observer = new Observer();

    //     state1.addObserver(observer);
    //     state1.addObserver(new Observer());
    //     state1.setState("New State 1");
    //     state1.setState("New State 2");
    // }
}


class State {
    private String state;
    private List<Observer> observers = new ArrayList<>();

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        notifyObservers();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(state);
        }
    }
}

class Observer {
    public void update(String state) {
        System.out.println("State changed to: " + state);
    }
}

// Observer Interface
interface StateObserver {
    void update(String state);
}