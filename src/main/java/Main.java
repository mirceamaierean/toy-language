import controller.Controller;
import controller.IController;
import model.exceptions.AppException;
import repository.IRepository;
import repository.Repository;
import view.IMainView;

import java.io.IOException;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException, AppException {
        String line = "log.txt";

        IRepository repository = new Repository(line);
        IController controller = new Controller(repository, Executors.newFixedThreadPool(2),true);
        view.GUI.MainView.setController(controller);
        IMainView view = new view.GUI.MainView();
        //IMainView view = new view.CLI.MainView(controller);
        view.run(args);
    }
}