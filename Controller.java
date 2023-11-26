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


    public boolean processInput(Command command){

    }

    private void initiateListening(){

    }


    private void changeUsername(String inName){

    }

    private boolean removeDish(){

    }

    private boolean addDish(){

    }






}
