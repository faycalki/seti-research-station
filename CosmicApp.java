/**
 * This class is the main entry-point of the program. It assigns the relations for the modular pieces of View, Controller, and Model, and packages the program.
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
            view.configureGame();
            //view.displayTurn();
           // Command choice = Command.valueOf(view.promptForChoice());
           // controller.processInput(choice);
        }
    }
}
