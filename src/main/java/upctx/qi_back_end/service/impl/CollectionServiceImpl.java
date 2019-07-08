package upctx.qi_back_end.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upctx.qi_back_end.domain.CollectionDish;
import upctx.qi_back_end.domain.Dish;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.exception.MyException;
import upctx.qi_back_end.exception.enums.ResultEnum;
import upctx.qi_back_end.repository.CollectionDishRepository;
import upctx.qi_back_end.repository.DishRepository;
import upctx.qi_back_end.service.CollectionService;
import upctx.qi_back_end.util.ResultUtil;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private CollectionDishRepository collectionDishRepository;

    @Transactional
    @Override
    public Result<CollectionDish> changeDishCollection(Integer userId, Integer dishId) {
        // 表一
        Optional<Dish> optional = dishRepository.findById(dishId);
        if (!optional.isPresent()) {
            throw new MyException(ResultEnum.DISH_NULL);
        }

        // 表二
        CollectionDish collection = collectionDishRepository.findByUserIdAndDishId(userId, dishId);
        if (collection == null) {
            optional.get().setCollectionCount(optional.get().getCollectionCount() + 1);
            dishRepository.save(optional.get());

            collection = new CollectionDish();
            collection.setUserId(userId);
            collection.setDishId(dishId);
            return ResultUtil.success(collectionDishRepository.save(collection));
        } else {
            if (collection.getIfDel() == 0) {
                optional.get().setCollectionCount(optional.get().getCollectionCount() - 1);
                dishRepository.save(optional.get());

                collection.setIfDel(1);
                return ResultUtil.success(collectionDishRepository.save(collection));
            } else {
                optional.get().setCollectionCount(optional.get().getCollectionCount() + 1);
                dishRepository.save(optional.get());

                collection.setIfDel(0);
                return ResultUtil.success(collectionDishRepository.save(collection));
            }
        }
    }
}
