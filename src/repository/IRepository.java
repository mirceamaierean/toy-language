package repository;

import model.exceptions.AppException;
import model.state.PrgState;

public interface IRepository {
    PrgState getCrtPrg() throws AppException;
    void add(PrgState e);

    void clear();
}
