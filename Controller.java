import java.util.ArrayList;

/**
 * This class strictly communicates between the View and the Model, as well as vice versa.
 * @author Faycal Kilali
 * @version 1.0
 */
public class Controller {

    View view;
    Model model;


    /**
     * Unnecessary constructor, kept for educational purposes.
     */
    public Controller(){
        view = new View(this);
        model = new Model(this);
    }

    /**
     * Constructs a Controller object with an appropriate Model and View object relation
     * @param inView the view object to be in relation with
     * @param inModel the model object to be in relation with
     */
    public Controller(View inView, Model inModel){
        this.view = inView;
        this.model = inModel;
    }


    /**
     * For particular commands, executes the appropriate response
     * @param command the legal command
     * @return true if successful, false otherwise
     */
    public boolean processInput(Command command){
        switch (command) {
            case ADD_RADIO_DISH:
                return addDish();
            case REMOVE_RADIO_DISH:
                return removeDish();
            case RESTART:
                restart();
                break;
            default:
                return false;
        }
        return false;
    }

    /**
     * Instructs a particular radio dish to listen
     */
    private char initiateListening() {
        return model.initiateListening();
    }

    public void addResearchStation(String inString){
        model.addResearchStation(inString);
    }

    public void removeResearchStation(String inString){
        model.removeResearchStation(inString);
    }

    /**
     * Changes the name of the user
     * @param inName the new username
     */
    public void changeUsername(String inName){
        model.setUsername(inName);
    }

    /**
     * Orders Model to remove a Dish
     * @return true if successful, false otherwise.
     */
    private boolean removeDish(){
        model.removeDish();
        return true;
    }

    /**
     * Orders Model to add a Dish
     * @return true if successful, false otherwise
     */
    private boolean addDish(){
        model.addDish();
        return true;
    }

    /**
     * Takes the user back to the configuration screen
     */
    private void restart(){
        return;
    }


    /**
     * Requests a reset of the Research Station from the Model.
     * @return true of successful reset, false otherwise
     */
    private boolean reset(){
        model.resetResearchStation();
        return true;
    }

    /**
     * Requests a generated message from the Model.
     * @return the generated message
     */
    public String generateMessage(){
        return model.generateMessage();
    }

    /**
     * Requeests the last generated message from the Model
     * @return the generated message
     */
    public String getGeneratedMessage(){
        return model.getGeneratedMessage();
    }

    /**
     * Retrieves the Model
     * @return the Model
     */
    public Model getModel(){
        return model;
    }

public double getStationCalibration(){
        return model.getStationCalibration();
}

    public ArrayList<ResearchStation<String, RadioDish>> getAllResearchStations(){
        return model.getAllResearchStations();
    }




}
