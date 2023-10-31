package view;

import controller.Controller;
import controller.parsers.syntax.SyntaxParser;
import model.exceptions.AppException;
import view.exceptions.ViewException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class View implements  IView {

    Controller controller;
    public View() {
        this.controller = new Controller();
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            try{
                this.showMenu();
                System.out.print("Enter command>");
                String cmd = reader.readLine().strip();
                String[] args = cmd.split(" ");
                if(args.length > 0) {
                    if (args.length > 1 && Objects.equals(args[0].toLowerCase(), "set") && Objects.equals(args[1].toLowerCase(), "program"))
                        this.handleSetProgram(args);
                    else if(Objects.equals(args[0].toLowerCase(), "step"))
                        this.handleSingleStep();
                    else if(Objects.equals(args[0].toLowerCase(), "run"))
                        this.handleRun();
                    else if (Objects.equals(args[0].toLowerCase(), "change"))
                        this.changeDisplayFlag();
                    else if(Objects.equals(args[0].toLowerCase(), "exit"))
                        break;
                    else
                        throw new ViewException("Invalid command!");
                } else {
                    throw new ViewException("Invalid command!");
                }
            } catch (IOException exception) {
                break;
            } catch (AppException exception){
                System.out.println(exception.getMessage());
            }
        }

    }

    private void changeDisplayFlag() {
        this.controller.changeDisplayFlag();
    }
    private void handleSetProgram(String[] args) throws AppException {
        StringBuilder program = new StringBuilder();
        for(int i = 2; i < args.length; ++i){
            program.append(args[i]);
            if (i + 1 < args.length)
                program.append(" ");
        }
        this.controller = new Controller();
        this.controller.setProgram(SyntaxParser.parse(program.toString()));
    }

    private void handleSingleStep() throws AppException {
        this.controller.oneStep();
    }

    private void handleRun() throws AppException {
        this.controller.executeAllSteps();
    }

    private void showMenu() {
        System.out.println("Usage:");
        System.out.println("set program PROGRAM");
        System.out.println("step");
        System.out.println("run");
        System.out.println("change display flag");
        System.out.println("exit");
    }
}