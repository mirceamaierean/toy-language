package controller;

import model.adt.stack.IGenericStack;
import model.exceptions.AppException;
import model.state.PrgState;
import model.statements.IStatement;
import repository.IRepository;

public class Controller {
    private IRepository repo;

    public Controller(IRepository repo) {
        this.repo = repo;
    }

    public void oneStep(PrgState state) throws AppException {
        IGenericStack<IStatement> stk = state.getExeStack();
        if (stk.isEmpty()) {
            throw new AppException("Program State stack is empty");
        }
        IStatement crtStmt = stk.pop();
        crtStmt.execute(state);
    }

    public void executeAllSteps() throws AppException {
        PrgState prg = repo.getCrtPrg();
        System.out.println(prg);

        while (true)
        {
            try {
                oneStep(prg);
                System.out.println(prg);
            } catch (AppException e) {
                break;
            }
        }
    }
}
