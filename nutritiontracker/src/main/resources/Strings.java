package nutritiontracker.productdb;

import java.util.Objects;

public class Strings {

    public static final String EMPTY = "";
    public static final String ZERO = "0";

    public static boolean isNotEmptyOrZero(String value) {
        if (Objects.isNull(value)) {
            return false;
        }
        if(value.equals(ZERO)) {
            return false;
        }
        if(EMPTY.equals(value.trim())) {
            return false;
        }
        return true;
    }

}