package upctx.qi_back_end.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import upctx.qi_back_end.domain.Dish;
import upctx.qi_back_end.domain.Reply;
import upctx.qi_back_end.domain.Restaurant;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.exception.MyException;
import upctx.qi_back_end.exception.enums.ResultEnum;
import upctx.qi_back_end.repository.DishRepository;
import upctx.qi_back_end.repository.ReplyRepository;
import upctx.qi_back_end.repository.RestaurantRepository;
import upctx.qi_back_end.service.RestaurantService;
import upctx.qi_back_end.util.ResultUtil;

import java.util.*;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @Override
    public Result<List<Restaurant>> getAllRestaurantName() {
        List<Restaurant> list = restaurantRepository.findAllIdAndName();
        if (list.isEmpty()) {
            throw new MyException(ResultEnum.RESTAURANT_NULL);
        } else {
            List<Map<String, Object>> mapList = new ArrayList<>();
            for (Object object : list) {
                Map<String, Object> temp = new HashMap<>();
                Object[] cells = (Object[]) object;
                temp.put("id", cells[0]);
                temp.put("name", cells[1]);
                mapList.add(temp);
            }
            return ResultUtil.success(mapList);
        }
    }

    @Override
    public Result<Integer> getCount() {
        return ResultUtil.success(restaurantRepository.count());
    }

    @Override
    public Result<List<Restaurant>> getRestaurantPage(Integer start, Integer size) {
        if (start < 1) {
            throw new MyException(ResultEnum.PAGE_START_ERROR);
        }
        List<Restaurant> list = restaurantRepository.findByIfDelNot(1, PageRequest.of(start - 1, size, Sort.by(Sort.Direction.DESC, "id"))).getContent();
        if (list.isEmpty()) {
            throw new MyException(ResultEnum.RESTAURANT_NULL);
        } else {
            return ResultUtil.success(list);
        }
    }

    @Override
    public Result<Restaurant> getRestaurant(Integer id) {
        Optional<Restaurant> optional = restaurantRepository.findById(id);
        if (optional.isPresent()) {
            return ResultUtil.success(optional.get());
        } else {
            throw new MyException(ResultEnum.RESTAURANT_NULL);
        }
    }

    @Override
    public Result<Double> getAverageScoreInRestaurant(Integer id) {
        Double avg = dishRepository.findAvgScoreByRestaurantId(id);
        if (avg == null) {
            throw new MyException(ResultEnum.RESTAURANT_DISH_NULL);
        } else {
            return ResultUtil.success(avg);
        }
    }

    @Override
    public Result<Dish> getDishByRestaurant(Integer id) {
        List<Dish> list = dishRepository.findByRestaurantId(id);
        if (list.isEmpty()) {
            throw new MyException(ResultEnum.RESTAURANT_DISH_NULL);
        } else {
            return ResultUtil.success(list);
        }
    }

    @Override
    public Result<List<Reply>> getReplyByRestaurantId(Integer id) {
        Result<Dish> result = getDishByRestaurant(id);
        if (result.getCode() == 0) {
            List<Dish> list = (List<Dish>) result.getData();
            List<Reply> replyList = new ArrayList<>();
            for (Dish dish : list) {
                List<Reply> tempReplyList =
                        replyRepository.findByDishIdAndIfDelNot(dish.getId(), 1,
                                PageRequest.of(0, 1,
                                        Sort.by(Sort.Direction.DESC, "likesCount"))).getContent();
                if (!tempReplyList.isEmpty()) {
                    replyList.addAll(tempReplyList);
                }
            }
            if (replyList.isEmpty()) {
                throw new MyException(ResultEnum.RESTAURANT_REPLY_NULL);
            } else {
                return ResultUtil.success(replyList);
            }
        } else {
            throw new MyException(ResultEnum.RESTAURANT_DISH_NULL);
        }
    }

    @Override
    public Result<Restaurant> addRestaurant(Restaurant restaurant) {
        return ResultUtil.success(restaurantRepository.save(restaurant));
    }

    @Override
    public Result<Restaurant> delRestaurant(Integer id) {
        Optional<Restaurant> optional = restaurantRepository.findById(id);
        if (optional.isPresent()) {
            optional.get().setIfDel(1);
            return ResultUtil.success(restaurantRepository.save(optional.get()));
        } else {
            throw new MyException(ResultEnum.RESTAURANT_NULL);
        }
    }
}
