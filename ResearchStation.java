/**
 * Representation of a Research Station with control of Radio Dishes
 * @author Faycal Kilali
 * @version 1.0
 */

import java.util.ArrayList;


public class ResearchStation<K, V> {

    private Map<String, RadioDish> radioDishes;
    private RadioDishNames chooseDishName = RadioDishNames.ALPHA;

    private String name;

    private double stationCalibration;




    /**
     * Constructs a Research Station
     */
    ResearchStation(){
        this.stationCalibration = 100.0;
        radioDishes = new Map<String, RadioDish>();
    }

    ResearchStation(String inString){
        this.stationCalibration = 100.0;
        radioDishes = new Map<String, RadioDish>();
        name = inString;
    }

    /**
     * Adds a Radio Dish to the Research Station with a legal name as a key.
     */
    public void addDish() {

        String dictatedName = "";
        RadioDishNames chosenName = RadioDishNames.ZETA;

        for (RadioDishNames c : RadioDishNames.values()) {
            if (c.equals(RadioDishNames.OMEGA)) {
                chooseDishName = RadioDishNames.ALPHA;
                dictatedName = c.getDishName();
                chosenName = c;
                break;
            } else if (c.equals(chooseDishName)) {
                dictatedName = c.getDishName();
                chosenName = c;
                break;
            }
        }

        RadioDish radioDish = new RadioDish();
        radioDish.setName(chosenName.getDishName());

        radioDishes.put(dictatedName, radioDish);

    }

    /**
     * Removes a Radio Dish from the research station
     * @param key the legal name of the Radio Dish
     */
    public void removeDish(String key){
        radioDishes.remove(key);
    }


    /**
     * Requests a particular Radio Dish to begin interpreting
     * @param inChar the character to receive and interpret
     */
    public char listen(RadioDish inDish, char inChar){
        return inDish.receive(inChar);
    }

    /**
     * Returns a list of all the Radio Dishes in the order they were added, by their keys.
     * @return the list of all radio dishes
     */
    public ArrayList<String> getAllRadioDishes(){
        return radioDishes.getKeys();
    }

    public RadioDish getRadioDish(String key){
        return radioDishes.get(key);
    }


    /**
     * Prints out the current information of the station
     */
    public void getStationInfo() {
        System.out.println("Research Station: " + getName());
        System.out.println("Calibration Level: " + getStationCalibration());

        System.out.println("Radio Dishes:");
        ArrayList<String> radioDishesKeys = getAllRadioDishes();
        for (String radioDish : radioDishesKeys) {
            double calibration = getCalibrationForRadioDish(radioDishes.get(radioDish));
            System.out.println("- " + radioDish + ": " + calibration);
        }

        System.out.println(); // Add a newline for better readability
    }

    private double getCalibrationForRadioDish(RadioDish inDish){
        return inDish.getCalibration();
    }


    /**
     * Retrieves the station's calibration
     * @return the level of the station's calibration
     */
    public double getStationCalibration(){
        double numberOfDishes = 0.0;
        double sum = 0.0;
        for (String dishName: getAllRadioDishes()){
            numberOfDishes++;
            sum = sum + radioDishes.get(dishName).getCalibration();
        }
        return sum / numberOfDishes;
    }


    public String getName() {
        return name;
    }

    public void setName(String stationName) {
        this.name = stationName;
    }

    public void setStationCalibration(double stationCalibration) {
        this.stationCalibration = stationCalibration;
    }

    /**
     * Re-calibrates the lowest calibrated radio dish in the station
     */
    public void fixCalibration() {
        ArrayList<String> radioDishesKeys = getAllRadioDishes();

        if (!radioDishes.isEmpty()) {
            String lowestCalibratedDish = radioDishesKeys.get(0);
            double lowestCalibration = radioDishes.get(lowestCalibratedDish).getCalibration();

            // Find the lowest calibrated radio dish
            for (String dishKey : radioDishesKeys) {
                double currentCalibration = radioDishes.get(dishKey).getCalibration();
                if (currentCalibration < lowestCalibration) {
                    lowestCalibration = currentCalibration;
                    lowestCalibratedDish = dishKey;
                }
            }

            // Recalibrate the lowest calibrated radio dish
            radioDishes.get(lowestCalibratedDish).setCalibrating(true); // this'll invoke a 20% increase per turn

            System.out.println("Calibating radio dish" + lowestCalibratedDish);
        } else {
            System.out.println("No radio dishes in the station to fix calibration.");
        }
    }
}
