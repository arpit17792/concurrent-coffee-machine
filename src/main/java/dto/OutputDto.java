package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class OutputDto {

    List<Set<String>> outputs;

    public OutputDto() {
    }

    public List<Set<String>> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Set<String>> outputs) {
        this.outputs = outputs;
    }
}
