package view.saleview.saleviewcomponent;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CostView extends JPanel{
    JTable jTable;
    DefaultTableModel defaultTableModel;

    public CostView(){
        defaultTableModel = new DefaultTableModel(new String[]{"Running total", "Total VAT", "Price discount"} , 1) ;
        defaultTableModel.setValueAt(0,0,0);
        defaultTableModel.setValueAt(0,0,1);
        defaultTableModel.setValueAt(0,0,2);
        jTable = new JTable(defaultTableModel);

        jTable.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setPreferredSize(new Dimension(380,280));
        add(scrollPane);
    }


    public void runningTotalHasChanged(double runningTotal, double vat, double priceDiscount) {
        jTable.setValueAt(runningTotal,0, 0);
        jTable.setValueAt(vat,0,1);
        jTable.setValueAt(priceDiscount,0,2);
        defaultTableModel.newDataAvailable(new TableModelEvent(defaultTableModel));
    }


}
