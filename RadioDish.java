public class RadioDish {

    private boolean calibrating;
    private double calibration;

    private String name;

    public void radioDish(String inName){
        name = inName;
    }

    public double getCalibration(){
        return calibration;
    }

    public void setCalibration(double calibration) {
        this.calibration = calibration;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char listen(char inChar){

    }

    public boolean isCalibrating() {
        return calibrating;
    }

    public void setCalibrating(boolean calibrating) {
        this.calibrating = calibrating;
    }
}
