import java.util.Random;

/**
 * Representation of a simple Radio Dish
 * @author Faycal Kilali
 * @version 1.0
 */
public class RadioDish {

    private boolean calibrating;
    private double calibration;

    private String name;

    private static final Random random = new Random(); // we only need one random object, for optimization purposes.


    /**
     * Constructor for a Radio Dish that pre-takes in a name
     * @param inName name to assign to Radio Dish
     */
    public void radioDish(String inName){
        name = inName;
        calibration = 100.0;
    }

    public void radioDish(){
        this.name = "Unnamed Dish";
        this.calibration = 100.0;
    }

    /**
     * Accessor Method for current calibration level
     * @return
     */
    public double getCalibration(){
        return calibration;
    }

    /**
     * Mutator method for calibration level
     * @implNote this accessor method has bounds for the values that the calibration can be set to.
     * @param calibration the new calibration level
     */
    public void setCalibration(double calibration) {
        if (calibration <= 0) {
            this.calibration = 0;
        }
        else if (calibration >= 100){
            this.calibration = 100;
        }
        else {
            this.calibration = calibration;
        }
    }


    /**
     * Accessor method for Radio Dish name
     * @return the name of the radio Dish
     */
    public String getName() {
        return name;
    }

    /**
     * Mutator method for the Radio Dish name
     * @param name the new name of the Radio Dish
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Activates the Radio Dish's listening \ receiving capability
     * @param inChar the character to attempt to receive
     * @return the interpreted received character
     */
    public char receive(char inChar){
        Double calibrationRequired = random.nextDouble(0, 100.0); // inclusive of lower bound, exclusive of upper bound.

        if (isCalibrating() == true){
            return '#';
        }
        else if (getCalibration() >= calibrationRequired){
            return inChar;
        }
        else{
            return '#';
        }

    }

    /**
     * Accessor Method for whether the current Radio Dish is calibrating or not
     * @return true if calibrating, false otherwise.
     */
    public boolean isCalibrating() {
        return calibrating;
    }

    /**
     * Mutator method for dictating whether the current Radio Dish is to begin calibrating or not.
     * @param calibrating true for the Radio Dish to begin calibrating, false otherwise
     */
    public void setCalibrating(boolean calibrating) {
        this.calibrating = calibrating;
    }



}
