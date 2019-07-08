package upctx.qi_back_end.util;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import upctx.qi_back_end.domain.Dish;
import upctx.qi_back_end.repository.DishRepository;

import java.util.*;

public class test {
    @Autowired
    private DishRepository dishRepository;

    @Test
    public void sort(){
        Double saltyScore = 1.1;
        Double sourScore = 1.3;
        Double hotScore = 1.2;
        Double sweetScore = 1.4;
        Map<String, Double> scoreMap = new HashMap<>();
        scoreMap.put("saltyScore", saltyScore);
        scoreMap.put("sourScore", sourScore);
        scoreMap.put("hotScore", hotScore);
        scoreMap.put("sweetScore", sweetScore);
        System.out.println(scoreMap.entrySet());
        List<Map.Entry<String,Double>> scoreList = new ArrayList<Map.Entry<String,Double>>(scoreMap.entrySet());
        //升序排序
        Collections.sort(scoreList, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        for(Map.Entry<String,Double> mapping:scoreList){
            System.out.println(mapping.getKey()+":"+mapping.getValue());
        }
        System.out.println(scoreList.get(0).getKey());
        System.out.println(scoreList.get(3).getKey());
    }

    @Test
    public void recommed() {
        Double saltyScore = 1.1;
        Double sourScore = 1.3;
        Double hotScore = 1.2;
        Double sweetScore = 1.4;
        Map<String, Double> scoreMap = new HashMap<>();
        scoreMap.put("saltyScore", saltyScore);
        scoreMap.put("sourScore", sourScore);
        scoreMap.put("hotScore", hotScore);
        scoreMap.put("sweetScore", sweetScore);

        List<Map.Entry<String,Double>> scoreList = new ArrayList<Map.Entry<String,Double>>(scoreMap.entrySet());
        //升序排序
        Collections.sort(scoreList, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        // 最小
        String min = scoreList.get(0).getKey();
        // 最大
        String max = scoreList.get(3).getKey();
        // 去所有菜里找1的前5个菜和2的前5个菜取集合
        Set<Dish> recommend = new HashSet<>();
//        List<Dish> minRecommendList = dishRepository.findByIfDelNot(1,
//                PageRequest.of(0, 5,
//                        Sort.by(Sort.Direction.DESC, min))).getContent();
//        List<Dish> maxRecommendList = dishRepository.findByIfDelNot(1,
//                PageRequest.of(0, 5,
//                        Sort.by(Sort.Direction.ASC, max))).getContent();
//        recommend.addAll(minRecommendList);
//        recommend.addAll(maxRecommendList);
//        System.out.println(recommend);
        System.out.println(dishRepository);
    }
}
