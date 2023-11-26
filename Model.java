public class Model {

    private ResearchStation<String, RadioDish> researchStation;
    private String username;
    private Controller controller;

    public Model(Controller controller){
        this.controller = controller;
        researchStation = new ResearchStation();
        username = "";
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ResearchStation<String, RadioDish> getResearchStation() {
        return researchStation;
    }

    /**
     * If we decide to have multiple research stations in the future for our simulation
     * @param researchStation
     */
    public void setResearchStation(ResearchStation<String, RadioDish> researchStation) {
        this.researchStation = researchStation;
    }
}
