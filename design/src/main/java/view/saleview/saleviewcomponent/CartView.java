package view.saleview.saleviewcomponent;
import net.miginfocom.swing.MigLayout;
import view.guiutil.SaleItemJTable;
import javax.swing.*;
import java.awt.*;

public class CartView extends JPanel {
    private SaleItemJTable saleItemJTable;

    public CartView() {
        setBorder(BorderFactory.createLineBorder(Color.BLUE,2,true));
        saleItemJTable = new SaleItemJTable();
        setLayout(new MigLayout("fill"));
        add(saleItemJTable.getPanel(),"north, grow");
        addActionListeners();
    }

    public SaleItemJTable getSaleItemJTable() {
        return saleItemJTable;
    }

    private void addActionListeners() {

    }

}
