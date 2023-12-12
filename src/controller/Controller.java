package controller;

import model.exceptions.AppException;
import model.state.*;
import model.statements.IStatement;
import model.statements.NoOperationStatement;
import repository.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class Controller implements IController {
    IRepository repository;
    boolean displayFlag;
    ExecutorService executor;

    public boolean getDisplayFlag() {
        return displayFlag;
    }

    @Override
    public void setDisplayFlag(boolean displayFlag) {
        this.displayFlag = displayFlag;
    }

    public Controller(IRepository repository, ExecutorService executor, boolean displayFlag) {
        this.repository = repository;
        this.executor = executor;
        this.displayFlag = displayFlag;
    }

    @Override
    public void removeCompletedPrograms() {
        this.repository.setProgramsList(this.repository.getProgramsList().stream().filter(PrgState::isNotCompleted).collect(Collectors.toList()));
    }

    @Override
    public void executeOneStep() throws AppException {
        this.removeCompletedPrograms();

        List<Callable<PrgState>> stepList = repository.getProgramsList().stream().map(program -> (Callable<PrgState>) (() -> program.executeOneStep())).toList();
        List<PrgState> newPrograms;

        try {
            newPrograms = executor.invokeAll(stepList).stream().map(future -> {
                try {
                    return future.get();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    System.out.println(e);
                    try {
                        this.setProgram(new NoOperationStatement());
                    } catch (AppException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                return null;
            }).filter(p -> p != null).toList();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        newPrograms.forEach(e -> this.repository.add(e));
        GarbageCollector.runGarbageCollector(this.repository.getProgramsList());
        if (this.displayFlag) {
            this.displayCurrentState();
        }
        this.repository.getProgramsList().forEach(e -> this.repository.logProgramState(e));
    }

    @Override
    public void executeAllSteps() throws AppException {
        while (true) {
            this.removeCompletedPrograms();
            if (this.repository.getProgramsList().size() == 0) {
                break;
            }
            this.executeOneStep();
        }
    }

    @Override
    public void displayCurrentState() throws AppException {
        this.repository.getProgramsList().forEach(program -> System.out.println(program.toString() + "\n"));
    }

    @Override
    public void setProgram(IStatement statement) throws AppException {
        this.repository.clear();
        this.repository.add(new PrgState(new ExecutionStack(), new SymTable(), new Output(), statement, new FileTable(), new Heap()));
        this.repository.logProgramState(this.repository.getProgramsList().get(0));
        if (this.displayFlag) {
            this.displayCurrentState();
        }
    }
}