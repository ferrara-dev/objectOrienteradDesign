package factory;

import observer.EventObserver;
import service.visitor.Visitor;
import service.visitor.cartvisitor.ProductCartVisitor;
import service.visitor.costvisitor.CostVisitor;
import service.visitor.costvisitor.PaymentVisitor;
import service.visitor.statevisitor.SaleStateVisitor;

import java.util.ArrayList;

/**
 * Enum class implementing a factory pattern that is used to
 * provide references to implementations of the <code> Visitor </code>
 * interface.
 *
 * <code> Visitor </code> implementations are used by classes in the service
 * layer to perform operations on model objects implementing the <code> Element </code>
 * interface.
 */
public enum VisitorFactory {
    COST_VISITOR{
        @Override
        public Visitor getVisitor() {
            return CostVisitor.getInstance();
        }
    },
    PRODUCT_CART_VISITOR{
        @Override
        public Visitor getVisitor() {
            return ProductCartVisitor.getInstance();
        }
    },
    SALE_STATE_VISITOR{
        @Override
        public Visitor getVisitor() {
            return SaleStateVisitor.getInstance();
        }
    },
    PAYMENT_VISITOR{
        @Override
        public Visitor getVisitor() {
            return PaymentVisitor.getInstance();
        }
    };

    public abstract Visitor getVisitor();

    public static void initVisitors(ArrayList<EventObserver> eventObservers){
        for(EventObserver eventObserver: eventObservers){
            COST_VISITOR.getVisitor().addObserver(eventObserver);
            PRODUCT_CART_VISITOR.getVisitor().addObserver(eventObserver);
            SALE_STATE_VISITOR.getVisitor().addObserver(eventObserver);
            PAYMENT_VISITOR.getVisitor().addObserver(eventObserver);
        }
    }
}
