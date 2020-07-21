package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class InputDto {

    @JsonProperty("machine")
    private Machine machine;

    public InputDto() {
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public class Machine {

        @JsonProperty("outlets")
        private Outlets outlets;

        @JsonProperty("total_items_quantity")
        private Map<String,Double> totalItemsQuantity;

        @JsonProperty("beverages")
        private Map<String, Map<String,Double>> beverages;

        public Machine() {
        }

        public Outlets getOutlets() {
            return outlets;
        }

        public void setOutlets(Outlets outlets) {
            this.outlets = outlets;
        }

        public Map<String, Double> getTotalItemsQuantity() {
            return totalItemsQuantity;
        }

        public void setTotalItemsQuantity(Map<String, Double> totalItemsQuantity) {
            this.totalItemsQuantity = totalItemsQuantity;
        }

        public Map<String, Map<String, Double>> getBeverages() {
            return beverages;
        }

        public void setBeverages(Map<String, Map<String, Double>> beverages) {
            this.beverages = beverages;
        }

        public class Outlets {

            @JsonProperty("count_n")
            private Integer countN;

            public Outlets() {
            }

            public Integer getCountN() {
                return countN;
            }

            public void setCountN(Integer countN) {
                this.countN = countN;
            }
        }
    }
}
