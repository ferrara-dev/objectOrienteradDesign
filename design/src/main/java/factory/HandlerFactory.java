package factory;

import service.Handler;
import service.discountservice.discountRequestHandler.BulkDiscountRequestHandler;
import service.discountservice.discountRequestHandler.TotalCostDiscountRequestHandler;
import service.discountservice.discountidentifier.DiscountRuleIdentifier;

/**
 * Enum implementation of a factory pattern used to create handler objects.
 * The handler objects created in this factory are used to create
 * chain of responsibility patterns.
 *
 * All handler classes created in this factory
 * implement the <code> Handler </code> interface.
 */
public enum HandlerFactory {
    DISCOUNT_REQUEST_HANDLER{
        @Override
        public Handler create() {
            return new DiscountRuleIdentifier(
                    new TotalCostDiscountRequestHandler(null),
                    new BulkDiscountRequestHandler(null));
        }
    };
        public abstract Handler create();
}

