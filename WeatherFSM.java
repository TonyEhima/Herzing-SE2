import java.util.Random;

public class WeatherFSM {
    // Define possible weather states
    enum State {
        CLEAR, CLOUDY, RAINING, SEVERE_WEATHER
    }

    // Define possible events that can influence weather changes
    enum Event {
        GETTING_WARMER, GETTING_COLDER, HUMIDITY_INCREASING, WIND_INCREASING
    }

    // Define the allowed flow between states
    private static State[] stateFlow = {State.CLEAR, State.CLOUDY, State.RAINING, State.SEVERE_WEATHER};
    // Random number generator for transitions and events
    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        State currentState = State.CLEAR;  // Starting state
        System.out.println("Starting Tony's Weather Simulation for 7 Days\n");
        
        // Loop through 7 simulated days
        for (int day = 1; day <= 7; day++) {
            System.out.println("Day " + day + ":");
            // 5 events per day
            for (int eventCount = 1; eventCount <= 5; eventCount++) {
                int transition = random.nextInt(3); // Randomly select 0, 1, or 2 for state transition
                Event event = getRandomEvent(); // Randomly select an event
                currentState = getNextState(currentState, transition); // Determine next state based on transition
                System.out.println("  Event " + eventCount + ": " + event + " -> Weather: " + currentState);
                Thread.sleep(500); // Pause for half a second to simulate time progression
            }
            System.out.println();
        }
        System.out.println("Simulation Complete.");
    }

    // Randomly selects one of the possible events
    private static Event getRandomEvent() {
        Event[] events = Event.values();
        return events[random.nextInt(events.length)];
    }

    // Determines the next state based on the transition number
    private static State getNextState(State currentState, int transition) {
        int index = getStateIndex(currentState); // Get the index of the current state
        if (transition == 0 && index > 0) {
            return stateFlow[index - 1]; // Move left in the state flow
        } else if (transition == 2 && index < stateFlow.length - 1) {
            return stateFlow[index + 1]; // Move right in the state flow
        }
        return currentState; // No change if transition == 1 or out of bounds
    }

    // Retrieves the index of the current state in the state flow
    private static int getStateIndex(State state) {
        for (int i = 0; i < stateFlow.length; i++) {
            if (stateFlow[i] == state) {
                return i;
            }
        }
        return -1; // Should not occur
    }
}
