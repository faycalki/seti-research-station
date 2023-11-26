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
     * If we decide to have multiple research stations in the future for our simulation or to swap between them
     * @param researchStation the research station that'll be our assigned one.
     */
    public void setResearchStation(ResearchStation<String, RadioDish> researchStation) {
        this.researchStation = researchStation;
    }

    /**
     * Resets the Research Station
     * @implSpec This does not reset the order of names or suffixes.
     * @return true if successful, false otherwise.
     */
    public boolean resetResearchStation(){
        ResearchStation<String, RadioDish> newStation = new ResearchStation();
        setResearchStation(newStation);

        if (researchStation.equals(newStation)){
            return true;
        }
        else{
            return false;
        }
    }

}
