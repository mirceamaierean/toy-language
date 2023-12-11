package view.commands;

import model.exceptions.AppException;

public interface IViewCommand {
    void execute(String command) throws AppException;

    void addCommand(String command, IViewCommand viewCommand) throws AppException;

    String getDescription(String prefix);
}