package view.GUI.setWindow;

import controller.IController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.exceptions.AppException;
import model.statements.IStatement;
import utils.HardcodedPrograms;
import view.GUI.mainWindow.MainWindowController;

public class SetProgramController {

    public Tab hardcodedTab;
    MainWindowController siblingController;
    IController controller;

    @FXML
    private TextArea error;

    @FXML
    private ListView<IStatement> examplesListView;

    @FXML
    private Button setButton;

    public SetProgramController(IController controller, MainWindowController siblingController) {
        this.controller = controller;
        this.siblingController = siblingController;
    }

    @FXML
    public void initialize() {
        examplesListView.setItems(FXCollections.observableList(HardcodedPrograms.hardcodedPrograms));
        setButton.setOnAction(actionEvent -> {
            try {
                int index = examplesListView.getSelectionModel().getSelectedIndex();
                if (index < 0) {
                    throw new AppException("No index selected");
                } else if (index >= HardcodedPrograms.hardcodedPrograms.size()) {
                    throw new AppException("No program at selected index");
                }
                this.controller.setProgram(HardcodedPrograms.hardcodedPrograms.get(index));
            } catch (AppException e) {
                this.error.setText("Error: " + e);
                this.error.setStyle("-fx-text-fill: red");
            } finally {
                this.siblingController.refresh();
            }
        });
    }
}