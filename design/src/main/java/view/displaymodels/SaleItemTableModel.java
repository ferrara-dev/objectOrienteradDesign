package view.displaymodels;


import model.sale.SaleItem;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class SaleItemTableModel extends AbstractTableModel {
    private String[] columnNames = {"Product", "Price", "Quantity", "Discount"};
    private List<SaleItem> list;


    public SaleItemTableModel() {
        this.list = new ArrayList<>();
    }

    public void addElement(SaleItem element) {
        list.add(element);
        int index = list.size();
        fireTableCellUpdated(index,index);
    }

    public void addElementAt(int row, SaleItem element){
        list.remove(row);
        list.add(row,element);
        fireTableCellUpdated(row,row);
    }

    public int getSize() {
        return list.size();
    }

    public SaleItem getElementAt(int index) {
        return list.get(index);
    }

    public void removeElement(SaleItem element) {
        list.remove(element);
        int index = list.size();
        fireTableCellUpdated(index,index);
    }

    @Override
    public void fireTableDataChanged(){
        super.fireTableChanged(new TableModelEvent(this));
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public void setList(List<SaleItem> myList) {
        this.list = myList;
        fireTableDataChanged();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Indicate the change has happened:
        super.setValueAt(aValue, rowIndex, columnIndex);
        fireTableDataChanged();
    }

    public int getRowCount() {
        int size;
        if (list == null) {
            size = 0;
        } else {
            size = list.size();
        }
        return size;
    }

    public Object getValueAt(int row, int col) {
        Object temp = null;
        if (col == 0) {
            temp = getElementAt(row).getProduct().getName();
        } else if (col == 1) {
            temp = getElementAt(row).getTotalPrice();
        } else if (col == 2) {
            temp = (double) getElementAt(row).getQuantity();
        }
        return temp;
    }

    // needed to show column names in JTable
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int col) {
        if (col == 2 || col ==1) {
            return Double.class;
        } else {
            return String.class;
        }
    }

    public List getDataList(){
        return list;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){

    }

}

