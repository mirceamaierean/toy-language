package view.GUI.mainWindow;

import controller.IController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Pair;
import model.adt.list.IGenericList;
import model.exceptions.AppException;
import model.state.*;
import model.statements.IStatement;
import model.values.IValue;

import java.util.List;
import java.util.NoSuchElementException;

public class MainWindowController {
    IController controller;
    IHeap currentHeap;
    IOutput currentOut;
    IFileTable currentFileTable;
    ISemaphoreTable currentSemaphoreTable;
    @FXML
    private Label progStatesLabel;
    @FXML
    private ListView<String> progStatesListView;
    @FXML
    private TableView<Pair<Integer, IValue>> heapTableTableView;
    @FXML
    private TableColumn<Pair<Integer, IValue>, Integer> heapAddressColumn;
    @FXML
    private TableColumn<Pair<Integer, IValue>, String> heapValueColumn;
    @FXML
    private ListView<String> outListView;
    @FXML
    private ListView<String> fileTableListView;
    @FXML
    private TableView<Pair<String, IValue>> symTableTableView;
    @FXML
    private TableColumn<Pair<String, IValue>, String> symbolNameColumn;
    @FXML
    private TableColumn<Pair<String, IValue>, String> symbolValueColumn;
    @FXML
    private ListView<String> executionStackListView;
    @FXML
    private Button stepButton;
    @FXML
    private Button runButton;

    @FXML
    private TableView<Pair<Pair<Integer, Integer>, String>> semaphoreTableView;

    @FXML
    private TableColumn<Pair<Pair<Integer, Integer>, String>, Integer> semaphoreTableIdColumn;

    @FXML
    private TableColumn<Pair<Pair<Integer, Integer>, String>, Integer> semaphoreTableCountColumn;

    @FXML
    private TableColumn<Pair<Pair<Integer, Integer>, String>, String> semaphoreTableThreadsColumn;



    public MainWindowController(IController controller) {
        this.controller = controller;
    }

    //TODO after run or last step the programs are removed so nothing is displayed anymore
    public void refresh() {
        int index = this.progStatesListView.getSelectionModel().getSelectedIndex();

        this.progStatesListView.getItems().clear();
        this.heapTableTableView.getItems().clear();
        this.outListView.getItems().clear();
        this.fileTableListView.getItems().clear();
        this.symTableTableView.getItems().clear();
        this.executionStackListView.getItems().clear();
        this.semaphoreTableView.getItems().clear();

        this.progStatesLabel.setText("Program states: " + this.controller.getPrgStates().size());
        for (int i = 0; i < this.controller.getPrgStates().size(); ++i)
            this.progStatesListView.getItems().add("PrgState " + i);

        if (!this.controller.getPrgStates().isEmpty()) {
            this.currentHeap = this.controller.getPrgStates().get(0).getHeap();
            this.currentOut = this.controller.getPrgStates().get(0).getOutput();
            this.currentFileTable = this.controller.getPrgStates().get(0).getFileTable();
            this.currentSemaphoreTable = this.controller.getPrgStates().get(0).getSemaphoreTable();

        }
        if (this.currentHeap != null) {
            this.currentHeap.getMap().forEach((x, y) -> this.heapTableTableView.getItems().add(new Pair<>(x, y)));
        }
        if (this.currentOut != null) {
            this.currentOut.getOutputAsList().forEach(x -> {
                this.outListView.getItems().add(x);
            });
        }
        if (this.currentFileTable != null) {
            this.currentFileTable.getFileList().forEach(x -> {
                this.fileTableListView.getItems().add(x);
            });
        }

        if (this.currentSemaphoreTable != null) {
            List<Pair<Pair<Integer, Integer>, IGenericList<Integer>>> semaphoreList = this.currentSemaphoreTable.getSemaphoreDictionaryAsList();
            semaphoreList.forEach(x -> {
                StringBuilder threadString = new StringBuilder();
                for(Integer elem: x.getValue().getAll()){
                    if(threadString.toString() != ""){
                        threadString.append(",");
                    }
                    threadString.append(elem);
                }
                this.semaphoreTableView.getItems().add(new Pair<>(new Pair<>(x.getKey().getKey(), x.getKey().getValue()), threadString.toString()));
            });
        }

        PrgState currentProgram;

        try {
            if (index >= 0) {
                currentProgram = this.controller.getPrgStates().get(index);
                currentProgram.getSymTable().getMap().forEach((x, y) -> this.symTableTableView.getItems().add(new Pair<>(x, y)));
                List<IStatement> statementList = currentProgram.getExeStack().toList();
                for (int i = statementList.size() - 1; i >= 0; i--) {
                    this.executionStackListView.getItems().add(statementList.get(i).toString());
                }
                this.progStatesListView.getSelectionModel().select(index);
            }
        } catch (NoSuchElementException ignored) {
        } finally {
            this.progStatesListView.refresh();
            this.heapTableTableView.refresh();
            this.outListView.refresh();
            this.fileTableListView.refresh();
            this.symTableTableView.refresh();
            this.executionStackListView.refresh();
            this.semaphoreTableView.refresh();
        }
    }

    @FXML
    public void initialize() {
        this.heapAddressColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        this.heapValueColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().toString()));
        this.symbolNameColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
        this.symbolValueColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().toString()));
        this.semaphoreTableIdColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey().getValue()).asObject());
        this.semaphoreTableCountColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey().getValue()).asObject());
        this.semaphoreTableThreadsColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue()));
        this.refresh();
        this.runButton.setOnAction(actionEvent -> {
            try {
                this.controller.executeAllSteps();
            } catch (AppException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.toString(), ButtonType.OK);
                alert.showAndWait();
            } finally {
                this.refresh();
            }
        });
        this.stepButton.setOnAction(actionEvent -> {
            try {
                if (this.controller.getPrgStates().isEmpty()) {
                    throw new AppException("No program to execute");
                }
                this.controller.executeOneStep();
            } catch (AppException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.toString(), ButtonType.OK);
                alert.showAndWait();
            } finally {
                this.refresh();
            }
        });
        this.progStatesListView.setOnMouseClicked(x -> this.refresh());
    }

}