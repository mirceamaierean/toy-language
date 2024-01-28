package model.state;

import javafx.util.Pair;
import model.adt.dictionary.GenericDictionary;

import model.statements.IStatement;

import java.util.List;

public class ProcedureTable extends GenericDictionary<String, Pair<List<String>, IStatement>> implements IProcedureTable {
}