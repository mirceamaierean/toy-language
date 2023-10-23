package repository;

import model.exceptions.AppException;
import model.state.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    String logFilePath;
    List<PrgState> programs;

    public Repository(String logFilePath) {
        programs = new ArrayList<>();
        this.logFilePath = logFilePath;
    }

    @Override
    public PrgState getCrtPrg() throws AppException {
        try {
            return programs.get(0);
        } catch (IndexOutOfBoundsException exception) {
            throw new AppException("No program selected");
        }
    }

    @Override
    public void add(PrgState program) {
        programs.add(program);
    }

    @Override
    public void logProgramState() {
        if (this.logFilePath != null) {
            PrintWriter logFile = null;
            try {
                logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logFile.println(this.programs.get(0).toString());
            logFile.close();
        }
    }

    @Override
    public void clear() {
        programs = new ArrayList<>();
    }
}