package view.displaymodels;

import model.amount.Cost;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PaymentTableModel extends AbstractTableModel{
    private String[] columnNames = {"Total cost", "Total VAT ", "To Pay : ", "Discount"};
    private List<Cost> list;

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
