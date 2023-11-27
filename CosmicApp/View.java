package CosmicApp;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * This is currently a terminal-based implementation of a video game where one has to guess the generated phrase. The game progresses in turns, where each radio dish attempts to interpret the message based on the calibration rules provided.
 * The turns have various rules that make the game interesting and unique. In the future, a GUI interface may be implemented.
 * The CosmicApp.View communicates strictly with CosmicApp.Controller only, back and forth.
 * @author Faycal Kilali
 * @version 1.0
 */
public class View {
    private Controller controller;
    private Scanner scanner;

    private int currentMessagePosition = 0; // To keep track of the current position in the generated message

    private String generatedMessage;
    private int totalMessageLength;
    private int numOfGuesses = 0;

    private Map<String, StringBuilder> interpretedStrings;
    public View(Controller controller){
    this.controller = controller;
    this.scanner = new Scanner(System.in);
    }

    /**
     * Displays information of the current setup of each Research Station
     */
    public void displayAllInfo() {
        System.out.println("Welcome to the overview of all your Research Stations, " + controller.getModel().getUsername() + "!");
        ArrayList<ResearchStation<String, RadioDish>> setup = controller.getAllResearchStations();
        for (ResearchStation<String, RadioDish> station : setup) {
            station.getStationInfo();
        }
    }

    /**
     * Displays information of the current setup of the currently selected research station
     */
    public void displayInfo(ResearchStation<String, RadioDish> station) {
               station.getStationInfo();
    }

    /**
     * Prompts the user for a username
     * @return true if legal username, false otherwise.
     */
    public boolean promptForUsername() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        boolean isLegalUsername = validateUsername(username);

        if (isLegalUsername) {
            controller.changeUsername(username);
            return true;
        } else {
            System.out.println("Invalid username. Please enter a legal username.");
            return false;
        }
    }

    /**
     * Validates the entered username
     * @param username the username to validate
     * @return true if the username is legal, false otherwise
     */
    private boolean validateUsername(String username) {
        return !username.isEmpty();
    }


    public void configureGame() {
        System.out.println("Welcome to the game setup, " + controller.getModel().getUsername() + "!");
        System.out.println("Configure your game before starting:");

        while (true) {
            System.out.println("1. Add Station");
            System.out.println("2. Remove Station");
            System.out.println("3. Add Radio Dish to Station");
            System.out.println("4. Remove Radio Dish from Station");
            System.out.println("5. Start Game");
            System.out.println("6. Quit");



            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addStation();
                    break;
                case "2":
                    removeStation();
                    break;
                case "3":
                    addRadioDish();
                    break;
                case "4":
                    removeRadioDish();
                    break;
                case "5":
                    System.out.println("Game configuration complete. Starting the game!");
                    controller.generateMessage();
                    generatedMessage = getGeneratedMessage();
                    currentMessagePosition = 0;
                    numOfGuesses = 0;
                    totalMessageLength = generatedMessage.length();
                    interpretedStrings = new Map<String, StringBuilder>();
                    displayTurn();
                    return;
                case "6":
                    System.out.println("Quitting the game setup.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private void addStation() {
        System.out.print("Enter the name of the new station: ");
        String stationName = scanner.nextLine();
        controller.addResearchStation(stationName);
        System.out.println("Station " + stationName + " added.");
    }

    private void removeStation() {
        System.out.print("Enter the name of the station to remove: ");
        String stationName = scanner.nextLine();
        controller.removeResearchStation(stationName);
        System.out.println("Station " + stationName + " removed.");
    }

    private void addRadioDish() {
        System.out.print("Enter the name of the station to add the radio dish to: ");
        String stationName = scanner.nextLine();
        ArrayList<ResearchStation<String, RadioDish>> setup = controller.getAllResearchStations();
        for (ResearchStation<String, RadioDish> station : setup) {
            if (stationName.equals(station.getName())){
                station.addDish();
            }
        }

        System.out.println("Radio Dish " + " added to Station " + stationName + ".");
    }

    private void removeRadioDish() {
        System.out.print("Enter the name of the station to remove the radio dish from: ");
        String stationName = scanner.nextLine();
        ArrayList<ResearchStation<String, RadioDish>> setup = controller.getAllResearchStations();
        for (ResearchStation<String, RadioDish> station : setup) {
            if (stationName.equals(station.getName())){
                ArrayList<String> keys = station.getAllRadioDishes();
                if (!keys.isEmpty()) {
                    station.removeDish(keys.get(keys.size() - 1));
                }
            }
        }
        System.out.println("Radio Dish " + " removed from Station " + stationName + ".");
    }


    public void displayTurn(){
        // Check if any calibrations are being performed
        ArrayList<ResearchStation<String, RadioDish>> setup = controller.getAllResearchStations();
        for (ResearchStation<String, RadioDish> station : setup) {
            ArrayList<String> keys = station.getAllRadioDishes();
            for (String key: keys){
                if( station.getRadioDish(key).isCalibrating() == true){
                    station.getRadioDish(key).setCalibration(station.getRadioDish(key).getCalibration() + 0.2);
                }
            }
        }
        displayTurnPhaseOne();
    }


    /**
     * Displays the current information of phase one of the turn.
     */
    public void displayTurnPhaseOne() {
        displayAllInfo();
        System.out.println("1. Choose a Research Station");
        System.out.println("2. Guess the phrase");
        System.out.println("3. Give Up");



        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                // logic to choose a station and proceed to phase two
                ResearchStation<String, RadioDish> selectedStation = chooseStation();
                displayTurnPhaseTwo(selectedStation);
                break;
            case "2":
                if (guess() == true){
                    configureGame();
                    break;
                }
                displayTurn();
            case "3":
                System.out.println("You gave up. Returning to the main menu.");
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    /**
     * Displays the current information of the turn (after choosing a particular station out of all stations)
     */
    public void displayTurnPhaseTwo(ResearchStation<String, RadioDish> station) {
        System.out.println("Access granted to station, " + controller.getModel().getUsername() + "!");
        displayInfo(station);
        System.out.println("1. Fix Calibration of a particular radio dish"); // fix calibration of a particular radio dish by 20% a turn
        System.out.println("2. Interpret Message through this station"); // attempt to interpret a message by every radio dish in this station
        System.out.println("3. Return to Overview to select a different station");
        System.out.println("9. Give up");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                fixCalibration(station);
                displayTurn();
                break;
            case "2":
                interpretMessagePerCharacter(station);
                displayTurn();
                break;
            case "3":
                displayTurnPhaseOne();
                break;
            case "9":
                System.out.println("You gave up on this attempt. Returning to the configuration screen.");
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    private ResearchStation<String, RadioDish> chooseStation() {
        // Logic to choose a station from the list
        System.out.println("Choose a station: ");
        ArrayList<ResearchStation<String, RadioDish>> stations = controller.getAllResearchStations();

        for (int i = 0; i < stations.size(); i++) {
            System.out.println((i + 1) + ". " + stations.get(i).getName());
        }

        int stationIndex = Integer.parseInt(scanner.nextLine()) - 1;

        return stations.get(stationIndex);
    }

    private void fixCalibration(ResearchStation<String, RadioDish> station) {
        station.fixCalibration();
        System.out.println("Sent calibration order to station " + station.getName());
    }

    private boolean guess() {
        numOfGuesses++;
        System.out.print("Enter your guess: ");
        String userGuess = scanner.nextLine();
        if (userGuess.equals(getGeneratedMessage())) {
            System.out.println("You've guessed correctly! The message was indeed " + getGeneratedMessage() + "You've guessed " + numOfGuesses + "number of times for this message!");
            return true;
        } else {
            System.out.println("Incorrect guess. The game continues...");
            return false;
        }
    }

    private void interpretMessagePerCharacter(ResearchStation<String, RadioDish> station) {
        String generatedMessage = getGeneratedMessage();

        for (int i = currentMessagePosition; i < totalMessageLength; i++) {
            char currentCharacter = generatedMessage.charAt(i);
            displayInterpretations(station, currentCharacter, i + 1);
            currentMessagePosition++; // Move to the next character in the next turn
            return; // end turn
        }
    }

    private void displayInterpretations(ResearchStation<String, RadioDish> station, char currentCharacter, int currentPosition) {
        ArrayList<String> keys = station.getAllRadioDishes();

        for (String key : keys) {
            RadioDish radioDish = station.getRadioDish(key);
            char interpretedChar = radioDish.receive(currentCharacter);

            // Get the interpreted string for the radio dish or initialize it if necessary
            StringBuilder interpretedString = interpretedStrings.get(key);
            if (interpretedString == null) {
                interpretedString = new StringBuilder();
                interpretedStrings.put(key, interpretedString);
            }

            // Update the interpreted string for the radio dish
            if (currentCharacter == ' ') {
                interpretedString.append(' ');
            } else {
                interpretedString.append(interpretedChar);
            }
        }

//            // Check if the interpreted word is correct and reveal it in the hint
//            if (getGeneratedMessage().contains(interpretedString.toString())) {
//                revealWordInHint(interpretedString.toString(), currentPosition);
//            }
//        }

        // Display overall progress
        displayOverallProgress(currentPosition);
    }

    private void revealWordInHint(String word, int currentPosition) {
        StringBuilder interpretedStringLength = new StringBuilder();

        for (int j = 0; j < currentPosition; j++) {
            char c = getGeneratedMessage().charAt(j);
            if (c == ' ' || word.contains(interpretedStringLength.toString())) {
                interpretedStringLength.append(getGeneratedMessage().charAt(j));
            } else {
                interpretedStringLength.append('#');
            }
        }

        System.out.println("Interpretation hint: " + interpretedStringLength.toString());
        System.out.println("Progress: [" + repeatCharacter('#', currentPosition) + repeatCharacter('-', totalMessageLength - currentPosition) + "]");
    }


    private void displayOverallProgress(int currentPosition) {
        StringBuilder interpretedStringLength = new StringBuilder();

        for (int j = 0; j < currentPosition; j++) {
            char c = getGeneratedMessage().charAt(j);
            if (c == ' '){
                interpretedStringLength.append(getGeneratedMessage().charAt(j));
            }
            else{
                interpretedStringLength.append('#');
            }
        }

        System.out.println("Interpretation hint: " + interpretedStringLength.toString());
        System.out.println("Progress: [" + repeatCharacter('#', currentPosition) + repeatCharacter('-', totalMessageLength - currentPosition) + "]");

        for (String radioDishKey : interpretedStrings.getKeys()) {
            StringBuilder interpretedString = interpretedStrings.get(radioDishKey);

            System.out.println("Radio Dish '" + radioDishKey + "': " + interpretedString.toString());
        }

    }

    private String repeatCharacter(char character, int times) {
        StringBuilder repeatedString = new StringBuilder();
        for (int i = 0; i < times; i++) {
            repeatedString.append(character);
        }
        return repeatedString.toString();
    }

    public String promptForChoice() {
        System.out.print("Enter your choice: "); // user should be able to add a station, set it as the main station (so they can add, remove dishes etc) but can have multiple stations.
        return scanner.nextLine();
    }

    private String getGeneratedMessage(){
        return controller.getGeneratedMessage();
    }

    /**
     * Invokes the creation of a newly generated message and stores it as a variable
     */

    /**
     * Displays the message that the user needs to guess
     * this should be shown at the end of the game, or should be used to tell the user how many letters are in each word.
     */
    public void displayMessage(String message) {
        System.out.println("Generated Message: " + message);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
