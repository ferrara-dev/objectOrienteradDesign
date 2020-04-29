package view;

import controller.MainController;
import observer.ObservedEvent;
import observer.PropertyChangeEvent;
import view.closedsaleview.ClosedSaleView;
import view.initialview.InitialView;
import view.paymentview.PaymentView;
import view.saleview.SaleView;

import java.awt.*;


/**
 * Class representing the view displayed on the cashier display
 * Holds references to four sub-views that are gone through during
 * a sale occasion.
 * The class is responsible for updating the view, so that the right
 * one is displayed at any given time.
 */
public class CashierView extends View {
    private InitialView initialView;
    private SaleView saleView;
    private PaymentView paymentView;
    private ClosedSaleView closedSaleView;
    private long id = 1L;

    public CashierView() {
        setLayout(new CardLayout());
        initialView = new InitialView();
        saleView = new SaleView();
        paymentView = new PaymentView();
        closedSaleView = new ClosedSaleView();
        add(initialView, initialView.CARD_CONSTRAINT);
        add(saleView, saleView.CARD_CONSTRAINT);
        add(paymentView, PaymentView.CARD_CONSTRAINT);
        add(closedSaleView, ClosedSaleView.CARD_CONSTRAINT);
    }

    public void initDefault(MainController mainController) {
        super.addController(mainController);
        mainController.addObserver(this);
        initialView.initDefault(mainController);
        saleView.initDefault(mainController);
        paymentView.initDefault(mainController);
    }


    @Override
    public void update(String viewConstraint) {
        CardLayout cl = (CardLayout) (this.getLayout());
        cl.show(this, viewConstraint);
    }

    @Override
    public void stateHasChanged(ObservedEvent observedEvent) {
        if (observedEvent instanceof PropertyChangeEvent) {
            String propertyName = ((PropertyChangeEvent) observedEvent).getPropertyName();

            if (propertyName == "active") {
                boolean active = (boolean) ((PropertyChangeEvent) observedEvent).getNewValue();
                if (active == true)
                    update(SaleView.CARD_CONSTRAINT);
            }
            else if (propertyName == "completed"){
                boolean completed = (boolean) ((PropertyChangeEvent) observedEvent).getNewValue();
                if (completed == true)
                    update(PaymentView.CARD_CONSTRAINT);
            }
        }
    }

    @Override
    public long getId() {
        return id;
    }
}
