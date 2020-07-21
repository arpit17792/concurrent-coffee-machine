package service.impl;

import dto.InputDto;
import service.api.CoffeeMachineService;

import java.util.*;
import java.util.concurrent.*;

public class CoffeeMachineServiceImpl implements CoffeeMachineService {

    // Map used for ingredient store
    final ConcurrentHashMap<String,Double> ingredientsMap = new ConcurrentHashMap<String, Double>();

    public CoffeeMachineServiceImpl() {
    }

    // Machine running in parallel for all outlets
    public Set<String> run(InputDto inputDto) throws InterruptedException {
        final Set<String> set = new HashSet<String>();
        int outlets = inputDto.getMachine().getOutlets().getCountN();
        ExecutorService executorService = Executors.newFixedThreadPool(outlets);
        setupIngredients(ingredientsMap,inputDto.getMachine().getTotalItemsQuantity());
        final CountDownLatch latch = new CountDownLatch(inputDto.getMachine().getBeverages().size());
        for(final Map.Entry<String, Map<String,Double>> entry : inputDto.getMachine().getBeverages().entrySet()) {
            executorService.execute(new Runnable() {
                public void run() {
                    set.add(prepare(entry.getKey(), entry.getValue()));
                    latch.countDown();
                }
            });
        }
        latch.await();
        return set;
    }

    private String prepare(String product, Map<String, Double> ingredientsReq) {
        //To prevent stale data in concurrent updates
        synchronized (ingredientsMap) {
            Map<String,Double> map = new HashMap<String, Double>();
            for(Map.Entry<String,Double> entry : ingredientsReq.entrySet()) {
                if(!ingredientsMap.containsKey(entry.getKey())) {
                    return product+" cannot be prepared because "+entry.getKey()+" is not available";
                } else if(ingredientsMap.get(entry.getKey())<entry.getValue()) {
                    return product+" cannot be prepared because item "+entry.getKey()+" is not sufficient";
                } else {
                    map.put(entry.getKey(),ingredientsMap.get(entry.getKey())-entry.getValue());
                }
            }
            ingredientsMap.putAll(map);
        }
        return product+" is prepared";
    }

    // This adds the Ingredients
    private void setupIngredients(ConcurrentHashMap<String, Double> ingredientsMap, Map<String, Double> totalItemsQuantity) {
        for(Map.Entry<String,Double> entry : totalItemsQuantity.entrySet()) {
            ingredientsMap.put(entry.getKey(),entry.getValue());
        }
    }
}
