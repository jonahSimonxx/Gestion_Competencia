package visuals;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Pais;

public class TableModelPais extends DefaultTableModel {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableModelPais(ArrayList<Pais> paises){
    	   String fila[] = {"Pais"};
    	   setColumnIdentifiers(fila);
    	   for(Pais p : paises){
    		   fila = new String[1];
    		   fila[0] = p.getNomPais();
    		   addRow(fila);
    		   fila = new String[1];
    	   }
       }
}