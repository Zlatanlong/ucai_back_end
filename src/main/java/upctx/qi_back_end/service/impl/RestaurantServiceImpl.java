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

import java.math.BigDecimal;
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


    /**
     * 根据餐厅号查询，先通过位置信息获取餐厅号，再查询
     *
     * @param Latitude
     * @param Longitude
     * @return
     */

    @Override
    public Result<Dish> getDishByLocation(Double Latitude, Double Longitude) {
        Integer id = 1;
        //先获取全部餐厅的经纬度,再比较大小，返回餐厅号
        Double latitude = Math.toRadians(Latitude);
        Double longitude = Math.toRadians(Longitude);
        Double minDistance = 400.0;
        Double restaurantLatitude;
        Double restaurantLongitude;
        Double cos, acos;
        Integer count = 0;
        Double distance;
        List<Restaurant> list = restaurantRepository.findAllIdAndLatitudeAndLongitude();
        if (list.isEmpty()) {
            System.out.println("获取的餐厅列表为空");
            throw new MyException(ResultEnum.RESTAURANT_NULL);

        } else {
            for (Object object : list) {
                Object[] cells = (Object[]) object;
                restaurantLatitude = Math.toRadians(Double.valueOf(cells[1].toString()));
                restaurantLongitude = Math.toRadians(Double.valueOf(cells[2].toString()));

                System.out.println("餐厅ID" + cells[0]);
                System.out.println("餐厅经度" + cells[1]);
                System.out.println("餐厅纬度" + cells[2]);

                cos = Math.cos(longitude) * Math.cos(restaurantLongitude) * Math.cos(latitude - restaurantLatitude)
                        + Math.sin(longitude) * Math.sin(restaurantLongitude);
                acos = Math.acos(cos);
                distance = acos;

                System.out.println("距离：" + distance);

                //如果最小距离大于距离，则进入，将距离赋给最小距离

                //当distance 小于minDistance  的时候返回 -1，bug:当结果相等时，会返回NAN，
                if(distance.compareTo(minDistance) > 0 && !Double.isNaN(distance))
                {
                    System.out.println("距离大，不赋值");
                } else if(distance.compareTo(minDistance) < 0 && !Double.isNaN(distance)){
                    minDistance = distance;
                    count++;
                    id = Integer.valueOf(cells[0].toString());
                    System.out.println("count:" + count + "距离小，id赋值");

                }else {
                    //minDistance = minDistance;
                    id = Integer.valueOf(cells[0].toString());
                    System.out.println("等距离，id赋值");
                    break;
                }

            }
            System.out.println("餐厅ID：" + id);
            List<Dish> DishList = dishRepository.findByRestaurantId(id);
            if (DishList.isEmpty()) {
                throw new MyException(ResultEnum.RESTAURANT_DISH_NULL);
            } else {
                return ResultUtil.success(DishList);
            }

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

    /**
     * 测试函数，用来测试获取餐厅经纬度
     * @return
     */
    @Override
    public Result<List<Restaurant>> getLatitude() {
        List<Restaurant> list = restaurantRepository.findAllIdAndLatitudeAndLongitude();

        if (list.isEmpty()) {
            throw new MyException(ResultEnum.RESTAURANT_NULL);
        } else {
            List<Map<String, Object>> mapList = new ArrayList<>();
            for (Object object : list) {
                Map<String, Object> temp = new HashMap<>();
                Object[] cells = (Object[]) object;
                temp.put("id", cells[0]);
                temp.put("latitude", cells[1]);
                temp.put("longitude", cells[2]);
                System.out.println("餐厅ID: " + Integer.valueOf(cells[0].toString()));
                System.out.println("餐厅经度: " + Double.valueOf(cells[1].toString()));
                System.out.println("餐厅纬度: " + Double.valueOf(cells[2].toString()));

                mapList.add(temp);

            }

            return ResultUtil.success(mapList);

        }

    }
}
