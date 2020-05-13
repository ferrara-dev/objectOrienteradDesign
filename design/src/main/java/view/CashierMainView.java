package view;

import model.sale.saleinformation.salestate.State;
import observer.modelobserver.EventObserver;
import observer.modelobserver.ObservedEvent;
import startup.layer.ViewCreator;
import view.closedsaleview.ClosedSaleMainView;
import view.initialview.InitialMainView;
import view.paymentview.PaymentMainView;
import view.saleview.SaleMainView;

import java.awt.*;
import java.util.ArrayList;


/**
 * Class representing the view displayed on the cashier display
 * Holds references to four sub-views that are gone through during
 * a sale occasion.
 * The class is responsible for updating the view, so that the right
 * one is displayed at any given time.
 */
public class CashierMainView extends MainView {
    private InitialMainView initialView;
    private SaleMainView saleView;
    private PaymentMainView paymentView;
    private ClosedSaleMainView closedSaleView;
    private long id = 1L;

    public CashierMainView(ViewCreator viewCreator) {
        setLayout(new CardLayout());
        initialView = viewCreator.getInitialView();
        saleView = viewCreator.getSaleView();
        paymentView = viewCreator.getPaymentView();
        closedSaleView = viewCreator.getClosedSaleView();

        add(initialView, initialView.CARD_CONSTRAINT);
        add(saleView, saleView.CARD_CONSTRAINT);
        add(paymentView, PaymentMainView.CARD_CONSTRAINT);
        add(closedSaleView, ClosedSaleMainView.CARD_CONSTRAINT);
    }

    public CashierMainView() {
        setLayout(new CardLayout());
        initialView = new InitialMainView();
        saleView = new SaleMainView();
        paymentView = new PaymentMainView();
        closedSaleView = new ClosedSaleMainView();
        add(initialView, initialView.CARD_CONSTRAINT);
        add(saleView, saleView.CARD_CONSTRAINT);
        add(paymentView, PaymentMainView.CARD_CONSTRAINT);
        add(closedSaleView, ClosedSaleMainView.CARD_CONSTRAINT);
    }


    public ArrayList<EventObserver> collectEventObservers(){
        ArrayList<EventObserver> eventObservers = new ArrayList<>();
        eventObservers.add(initialView);
        eventObservers.add(saleView);
        eventObservers.add(paymentView);
        eventObservers.add(closedSaleView);
        eventObservers.add(this);
        return eventObservers;
    }

    @Override
    public void update(String viewConstraint) {
        CardLayout cl = (CardLayout) (this.getLayout());
        cl.show(this, viewConstraint);
    }

    @Override
    public void newEvent(ObservedEvent observedEvent) {
        if(observedEvent.getEventSource() == State.SALE_ACTIVE_STATE)
            update(SaleMainView.CARD_CONSTRAINT);

        else if(observedEvent.getEventSource() == State.SALE_PAYMENT_STATE)
            update(PaymentMainView.CARD_CONSTRAINT);

        else if(observedEvent.getEventSource() == State.SALE_COMPLETE_STATE)
            update(ClosedSaleMainView.CARD_CONSTRAINT);

        else if(observedEvent.getEventSource() == State.REGISTER_ACTIVE_STATE)
            update(InitialMainView.CARD_CONSTRAINT);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public ArrayList<View> collectViews() {
        ArrayList<View> eventObservers = new ArrayList<>();
        eventObservers.addAll(initialView.collectViews());
        eventObservers.addAll(saleView.collectViews());
        eventObservers.addAll(paymentView.collectViews());
        eventObservers.addAll(closedSaleView.collectViews());
        eventObservers.add(saleView);
        eventObservers.add(this);
        return eventObservers;
    }

    public SaleMainView getSaleView() {
        return saleView;
    }

    public ClosedSaleMainView getClosedSaleView() {
        return closedSaleView;
    }

    public InitialMainView getInitialView() {
        return initialView;
    }

    public PaymentMainView getPaymentView() {
        return paymentView;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setClosedSaleView(ClosedSaleMainView closedSaleView) {
        this.closedSaleView = closedSaleView;
    }

    public void setInitialView(InitialMainView initialView) {
        this.initialView = initialView;
    }

    public void setPaymentView(PaymentMainView paymentView) {
        this.paymentView = paymentView;
    }

    public void setSaleView(SaleMainView saleView) {
        this.saleView = saleView;
    }


}
