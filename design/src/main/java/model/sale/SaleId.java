package model.sale;

import java.util.UUID;
/**
 * Class representing a unique id used to identify each sale
 */
public class SaleId {

        private String value;
        public SaleId(){
            this.value =  UUID.randomUUID().toString();
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "[ " + value + " ]";
        }

        public String getValue() {
            return value;
        }
    }


