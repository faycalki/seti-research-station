package CosmicApp;

/**
 * This class is the main entry-point of the program. It assigns the relations for the modular pieces of CosmicApp.View, CosmicApp.Controller, and CosmicApp.Model, and packages the program.
 */

public class CosmicApp {

    private View view;
    private Controller controller;
    private Model model;

    public static void main(String[] args) {
        CosmicApp cosmicApp = new CosmicApp();
        cosmicApp.start();
    }

    public void start() {
        this.view = new View(controller);
        this.model = new Model(controller);
        this.controller = new Controller(view, model);

        // Set the controller in the view and model
        view.setController(controller);
        model.setController(controller);

        // Start the main game loop
        while (true) {
            view.promptForUsername();
            view.configureGame();
            //view.displayTurn();
           // CosmicApp.Command choice = CosmicApp.Command.valueOf(view.promptForChoice());
           // controller.processInput(choice);
        }
    }
}
