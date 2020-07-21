import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.InputDto;
import service.impl.CoffeeMachineServiceImpl;

public class Main {

    public static void main(String[] args) {

        String inputString = "{\n" +
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

        ObjectMapper mapper = new ObjectMapper();
        InputDto inputDto=null;
        try {
            inputDto = mapper.readValue(inputString, InputDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        CoffeeMachineServiceImpl cms = new CoffeeMachineServiceImpl();
        long startTime = System.currentTimeMillis();
        try {
            System.out.println(cms.run(inputDto));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()-startTime);
    }
}
