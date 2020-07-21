package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.OutputDto;

import java.util.List;
import java.util.Set;

public class OutputConvertor {

    public static OutputDto convert(String output) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(output, OutputDto.class);
    }
}
