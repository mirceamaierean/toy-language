package controller;

import javafx.util.Pair;
import model.adt.dictionary.GenericDictionary;
import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.expressions.BinaryExpression;
import model.expressions.VariableExpression;
import model.state.*;
import model.statements.*;
import model.values.IValue;
import model.values.types.IntegerType;
import repository.IRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class Controller implements IController {
    IRepository repository;
    boolean displayFlag;
    ExecutorService executor;

    public Controller(IRepository repository, ExecutorService executor, boolean displayFlag) {
        this.repository = repository;
        this.executor = executor;
        this.displayFlag = displayFlag;
    }

    public boolean getDisplayFlag() {
        return displayFlag;
    }

    @Override
    public void setDisplayFlag(boolean displayFlag) {
        this.displayFlag = displayFlag;
    }

    @Override
    public void removeCompletedPrograms() {
        this.repository.setProgramsList(
                this.repository.getProgramsList().stream()
                        .filter(PrgState::isNotCompleted)
                        .collect(Collectors.toList()));
    }

    @Override
    public void executeOneStep() throws AppException {
        this.removeCompletedPrograms();
        List<Callable<PrgState>> stepList = repository.getProgramsList().stream()
                .map(program -> (Callable<PrgState>) (program::executeOneStep))
                .toList();

        List<PrgState> newPrograms = null;
        try {
            newPrograms = executor.invokeAll(stepList).stream()
                    .map(future -> {
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
                    })
                    .filter(Objects::nonNull)
                    .toList();
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
            if (this.repository.getProgramsList().isEmpty())
                break;

            this.executeOneStep();
        }
    }

    @Override
    public void displayCurrentState() throws AppException {
        this.repository.getProgramsList().forEach(program -> System.out.println(program.toString() + "\n"));
    }

    @Override
    public void setProgram(IStatement statement) throws AppException {
        statement.typecheck(new GenericDictionary<>());
        this.repository.clear();

        IStatement f1 = new CompositeStatement(
                new VariableDeclarationStatement("v", new IntegerType()),
                new CompositeStatement(
                        new AssignmentStatement("v", new BinaryExpression(new VariableExpression("a"), new VariableExpression("b"), "+")),
                        new PrintStatement(new VariableExpression("v"))
                )
        );
        IStatement f2 = new CompositeStatement(
                new VariableDeclarationStatement("v", new IntegerType()),
                new CompositeStatement(
                        new AssignmentStatement("v", new BinaryExpression(new VariableExpression("a"), new VariableExpression("b"), "*")),
                        new PrintStatement(new VariableExpression("v"))
                )
        );

        ProcedureTable procedureTable = new ProcedureTable();
        procedureTable.insert("sum", new Pair<>(Arrays.asList("a", "b"), f1));
        procedureTable.insert("product", new Pair<>(Arrays.asList("a", "b"), f2));
        SymTable symTable = new SymTable();
        this.repository.add(new PrgState(new ExecutionStack(), new SymTable(), new Output(), statement, new FileTable(), new Heap(), procedureTable));
        this.repository.logProgramState(this.repository.getProgramsList().get(0));
        if (this.displayFlag)
            this.displayCurrentState();
    }

    @Override
    public List<PrgState> getPrgStates() {
        return this.repository.getProgramsList();
    }
}