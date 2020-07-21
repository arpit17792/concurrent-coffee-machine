package service.api;

import dto.InputDto;

import java.util.Map;
import java.util.Set;

public interface CoffeeMachineService {

    Set<String> run(InputDto inputDto) throws InterruptedException;

}
