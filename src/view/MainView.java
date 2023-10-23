package view;

import controller.IController;
import model.exceptions.AppException;
import view.commands.IViewCommand;
import view.commands.MenuViewCommandFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainView implements IMainView {
    IController controller;
    IViewCommand commandMenu;

    public MainView(IController controller) throws AppException {
        this.controller = controller;
        this.commandMenu = MenuViewCommandFactory.build(controller);
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.print("Enter command>");
                String cmd = reader.readLine().strip();
                this.commandMenu.execute(cmd);
            } catch (AppException exception) {
                System.out.println(exception.getMessage());
                break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}