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


    SET_NAME,
    ADD_RADIO_DISH,
    REMOVE_RADIO_DISH,
    GENERATE_MESSAGE,
    BEGIN,
    LISTEN,
    CALIBRATE,
    RESTART
    public boolean processInput(Command command){
        switch (command) {
            case ADD_RADIO_DISH:
                // ...
                return addDish();
            case REMOVE_RADIO_DISH:
                return removeDish();
            case RESTART:
                restart();
                break;
            default:
                return false;
        }
    }

    private void initiateListening(){
    }


    public void changeUsername(String inName){
    }

    private boolean removeDish(){
    }

    private boolean addDish(){
    }

    /**
     * Requests a reset of the Research Station from the Model.
     * @return true of successful reset, false otherwise
     */
    private boolean restart(){
        model.resetResearchStation();
    }

    /**
     * Requeests a generated message from the Model.
     * @return the generated message
     */
    public String generateMessage(){
    }






}
