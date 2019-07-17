package upctx.qi_back_end.service;

import org.springframework.web.multipart.MultipartFile;
import upctx.qi_back_end.domain.Dish;
import upctx.qi_back_end.domain.result_domain.Result;

import java.util.List;

public interface DishService {
    // 获取全部菜品名和id（搜索用）
    Result<List<Dish>> getAll();

    // 查询菜品总数
    Result<Integer> getCount();

    /**
     * 对楼下的重载
     */
    Result<List<Dish>> getDishPage(Integer start, Integer size);

    /**
     *
     * @param start 起始页,1 开始
     * @param size  一页多少条
     * @param rankStr 根据rankStr获取排序好的页
     * @return
     */
    Result<List<Dish>> getDishPage(Integer start, Integer size, String rankStr);

    Result<List<Dish>> getDishHome();

    // 查询菜品总数
    Result getCountSearch(String sth);

    Result<List<Dish>> getDishPageSearch(String sth, Integer start, Integer size);


    Result<Dish> getDish(Integer id);
    // 获取单个菜品全部信息（菜名/id）（查）

    // 添加菜品
    Result<Integer> addDish(Dish dish, MultipartFile file);

    // 替换菜品
    Result<Integer> changeDish(Dish dish);

    // 删除菜品 标记设为1
    Result<Integer> delDish(Integer id);
}
