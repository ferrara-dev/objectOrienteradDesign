package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import model.discount.discounttypes.defaultdiscount.NoPriceDiscount;
import model.discount.discounttypes.pricediscount.TotalCostDiscount;

public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.registerSubtypes(new NamedType(TotalCostDiscount.class, "Truck"));
        MAPPER.registerSubtypes(new NamedType(NoPriceDiscount.class, "Car"));
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public static ObjectMapper getMAPPER() {
        return MAPPER;
    }
}
