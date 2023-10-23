package controller;

import model.adt.stack.StackEmptyAppException;
import model.exceptions.AppException;
import model.state.*;
import model.statements.IStatement;
import model.statements.NoOperationStatement;
import repository.IRepository;

public class Controller implements IController {
    IRepository repository;
    boolean displayFlag;

    public boolean getDisplayFlag() {
        return displayFlag;
    }

    @Override
    public void setDisplayFlag(boolean displayFlag) {
        this.displayFlag = displayFlag;
    }

    public Controller(IRepository repository, boolean displayFlag) {
        this.repository = repository;
        this.displayFlag = displayFlag;
    }

    @Override
    public void oneStep() throws AppException {
        PrgState state = repository.getCrtPrg();
        IStatement statement = state.getExeStack().pop();
        statement.execute(state);
        if (this.displayFlag) {
            this.displayCurrentState();
        }
        this.repository.logProgramState();
    }

    @Override
    public void executeAllSteps() throws AppException {
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

    @Override
    public void displayCurrentState() throws AppException {
        System.out.println(this.repository.getCrtPrg().toString() + "\n");
    }

    @Override
    public void setProgram(IStatement statement) throws AppException {
        this.repository.clear();
        this.repository.add(new PrgState(new ExecutionStack(), new SymTable(), new Output(), statement, new FileTable()));
        this.repository.logProgramState();
        if (this.displayFlag) {
            this.displayCurrentState();
        }
    }
}