package util.exception.notfoundexception;


import util.datatransferobject.SaleItemDTO;

public final class ProductNotFoundException extends RuntimeException {
    private static final String TAG = "ProductNotFoundException";
    private NotFoundException cause;
    private SaleItemDTO invalidSaleItemDTO;

    public ProductNotFoundException(NotFoundException cause, SaleItemDTO invalidSaleItemDTO){
        super(cause);
        this.cause = cause;
        this.invalidSaleItemDTO = invalidSaleItemDTO;
    }

    @Override
    public NotFoundException getCause() {
        return cause;
    }

    public SaleItemDTO getInvalidSaleItemDTO() {
        return invalidSaleItemDTO;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
