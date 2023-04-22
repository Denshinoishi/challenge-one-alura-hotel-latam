package com.alura.utiles;

import javax.swing.table.DefaultTableModel;


/*
 * Clase utilitaria para bloquear la edición de columnas en las tablas de busqueda
 */

public class MiTablaModelo extends DefaultTableModel {
	private boolean[] columnasEditables; //Arreglo boleano para definir las columnas editables
	
	public MiTablaModelo(String[] columnaNombres, boolean[] columnasEditables) {
		super(columnaNombres, 0);
		this.columnasEditables = columnasEditables;
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return columnasEditables[column];
	}

}
