import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import dto.InputDto;
import dto.OutputDto;
import service.api.CoffeeMachineService;
import service.impl.CoffeeMachineServiceImpl;
import util.InputConvertor;
import org.junit.jupiter.api.Test;
import util.OutputConvertor;

import java.util.Set;

public class CoffeeMachineTest {

    //Test cases
    @Test
    public void testMachine() throws JsonProcessingException, InterruptedException {
        String inputString1 = "{\n" +
                "  \"machine\": {\n" +
                "    \"outlets\": {\n" +
                "      \"count_n\": 3\n" +
                "    },\n" +
                "    \"total_items_quantity\": {\n" +
                "      \"hot_water\": 500,\n" +
                "      \"hot_milk\": 500,\n" +
                "      \"ginger_syrup\": 100,\n" +
                "      \"sugar_syrup\": 100,\n" +
                "      \"tea_leaves_syrup\": 100\n" +
                "    },\n" +
                "    \"beverages\": {\n" +
                "      \"hot_tea\": {\n" +
                "        \"hot_water\": 200,\n" +
                "        \"hot_milk\": 100,\n" +
                "        \"ginger_syrup\": 10,\n" +
                "        \"sugar_syrup\": 10,\n" +
                "        \"tea_leaves_syrup\": 30\n" +
                "      },\n" +
                "      \"hot_coffee\": {\n" +
                "        \"hot_water\": 100,\n" +
                "        \"ginger_syrup\": 30,\n" +
                "        \"hot_milk\": 400,\n" +
                "        \"sugar_syrup\": 50,\n" +
                "        \"tea_leaves_syrup\": 30\n" +
                "      },\n" +
                "      \"black_tea\": {\n" +
                "        \"hot_water\": 300,\n" +
                "        \"ginger_syrup\": 30,\n" +
                "        \"sugar_syrup\": 50,\n" +
                "        \"tea_leaves_syrup\": 30\n" +
                "      },\n" +
                "      \"green_tea\": {\n" +
                "        \"hot_water\": 100,\n" +
                "        \"ginger_syrup\": 30,\n" +
                "        \"sugar_syrup\": 50,\n" +
                "        \"green_mixture\": 30\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        String outputString1 = "{\n" +
                "  \"outputs\":[\n" +
                "    [\"hot_tea is prepared\",\"hot_coffee is prepared\",\"green_tea cannot be prepared because green_mixture is not available\",\"black_tea cannot be prepared because item hot_water is not sufficient\"],\n" +
                "    [\"hot_tea is prepared\",\"black_tea is prepared\",\"green_tea cannot be prepared because green_mixture is not available\",\"hot_coffee cannot be prepared because item hot_water is not sufficient\"],\n" +
                "    [\"hot_coffee is prepared\",\"black_tea is prepared\",\"green_tea cannot be prepared because green_mixture is not available\",\"hot_tea cannot be prepared because item hot_water is not sufficient\"],\n" +
                "    [\"hot_tea is prepared\",\"hot_coffee is prepared\",\"green_tea cannot be prepared because item sugar_syrup is not sufficient\",\"black_tea cannot be prepared because item hot_water is not sufficient\"]\n" +
                "  ]\n" +
                "}";

        InputDto inputDto1 = InputConvertor.convert(inputString1);
        OutputDto outputDto1 = OutputConvertor.convert(outputString1);

        CoffeeMachineService cms = new CoffeeMachineServiceImpl();

        Set<String> actualOutput = cms.run(inputDto1);
        assertEquals(assertTest(actualOutput,outputDto1),true,"Test case Failed");
    }

    private boolean assertTest(Set<String> actualOutput, OutputDto expectedOutput) {
        for(Set set : expectedOutput.getOutputs()) {
            if(set.containsAll(actualOutput)) {
                return true;
            }
        }
        return false;
    }
}
