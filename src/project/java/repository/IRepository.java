package repository;

import model.exceptions.AppException;
import model.state.PrgState;

import java.util.List;

public interface IRepository {
    List <PrgState> getProgramsList();

    void setProgramsList(List<PrgState> programs);

    void add(PrgState e);

    void logProgramState(PrgState program);

    void clear();
}
