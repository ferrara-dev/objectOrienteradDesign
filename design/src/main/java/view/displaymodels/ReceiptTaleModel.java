package view.displaymodels;

import model.amount.Cost;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Class to store the data shown by the <code> ReceiptView </code>
 * //TODO : To be implemented.
 */
public class ReceiptTaleModel extends AbstractTableModel{
    private String[] columns = {"Receipt"};
    private List<Cost> list;
    Object data = new Object[3][columns.length];

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
    /*
    public Class getColumnClass(int col) {

        if(getRowCount() <1) {
            return null;
        }
        return data[0][col].getClass();

    }
    public int getRowCount() {
        return data.length;
    }
    public int getColumnCount() {
        return columns.length;
    }
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
    public boolean isCellEditable(int row, int col){
        return true;
    }
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value; fireTableCellUpdated(row, col);
    }
     */
}
