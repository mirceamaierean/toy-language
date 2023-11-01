package repository;

import model.exceptions.AppException;
import model.state.PrgState;

import java.util.List;
import java.util.ArrayList;

public class Repository implements IRepository {

    private List<PrgState> repo;

    public Repository() {
        this.repo = new ArrayList<>();
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
    public void clear() {
        this.repo.clear();
    }

    @Override
    public String toString() {
        String result = "";
        for (PrgState p : this.repo)
            result += p.toString() + "\n";
        return "Repository{" +
                "repo=" + result +
                '}';
    }
}
