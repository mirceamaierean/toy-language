package controller;

import model.adt.stack.StackEmptyAppException;
import model.exceptions.AppException;
import model.state.*;
import model.statements.IStatement;
import model.statements.NoOperationStatement;
import repository.IRepository;
import repository.Repository;


public class Controller {
    IRepository repository;
    Boolean displayFlag;

    public Controller() {
        this.repository = new Repository();
        displayFlag = true;
    }

    public void changeDisplayFlag() {
        System.out.println("Display flag changed to " + !this.displayFlag);
        this.displayFlag = !this.displayFlag;
    }

    public void oneStep() throws AppException{
        PrgState state = repository.getCrtPrg();
        IStatement statement = state.getExeStack().pop();
        statement.execute(state);
    }

    public void executeAllSteps() throws AppException{
        while (true) {
            try {
                if (this.displayFlag)
                    this.displayCurrentState();
                this.oneStep();
            } catch (StackEmptyAppException exception) {
                break;
            } catch (AppException exception) {
                this.setProgram(new NoOperationStatement());
                throw exception;
            }
        }
    }

    public void displayCurrentState() throws AppException {
        System.out.println(this.repository.getCrtPrg().toString() + "\n");
    }

    public void setProgram(IStatement statement) throws AppException {
        this.repository.clear();
        this.repository.add(new PrgState(new ExecutionStack(), new SymTable(), new Output(), statement));
    }
}