package repository;

import model.exceptions.AppException;
import model.state.PrgState;

import java.util.List;
import java.util.LinkedList;

public class Repository implements IRepository{

    private List<PrgState>  repo;

    public Repository() {
        this.repo = new LinkedList <PrgState>();
    }

    @Override
    public void add(PrgState e) {
        this.repo.add(e);
    }

    @Override
    public PrgState getCrtPrg() throws AppException {
        if (this.repo.size() == 0)
            throw new AppException("No program state left to execute!");
        return this.repo.get(0);
    }

    @Override
    public String toString() {
        return "Repository{" +
                "repo=" + repo +
                '}';
    }
}
