package model.state;

import javafx.util.Pair;
import model.adt.dictionary.IGenericDictionary;
import model.statements.IStatement;

import java.util.List;

public interface IProcedureTable extends IGenericDictionary<String, Pair<List<String>, IStatement>> {

}