package upctx.qi_back_end.service;

import upctx.qi_back_end.domain.Dish;
import upctx.qi_back_end.domain.Reply;
import upctx.qi_back_end.domain.Restaurant;
import upctx.qi_back_end.domain.result_domain.Result;

import java.util.List;

public interface RestaurantService {
    // 获取全部餐厅名和id（查）
    Result<List<Restaurant>> getAllRestaurantName();

    // 获得餐厅总数
    Result<Integer> getCount();

    /**
     * 按页码获取部分餐厅全部信息
     *
     * @param start
     * @param size
     * @return
     */
    Result<List<Restaurant>> getRestaurantPage(Integer start, Integer size);

    // 获取一个餐厅详情
    Result<Restaurant> getRestaurant(Integer id);

    // 全部菜品平均分
    Result<Double> getAverageScoreInRestaurant(Integer id);

    /**
     * 根据餐厅id查询餐厅中的全部菜品
     *
     * @param id
     * @return
     */
    Result<Dish> getDishByRestaurant(Integer id);

    /**
     * 获取餐厅中每个菜赞最多的回复
     *
     * @param id
     * @return
     */
    Result<List<Reply>> getReplyByRestaurantId(Integer id);

    // 添加/替换一个餐厅 超管方法
    Result<Restaurant> addRestaurant(Restaurant restaurant);

    //删除一个餐厅 超管方法
    Result<Restaurant> delRestaurant(Integer id);
}
