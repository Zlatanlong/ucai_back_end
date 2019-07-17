package upctx.qi_back_end.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import upctx.qi_back_end.domain.Dish;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.exception.MyException;
import upctx.qi_back_end.exception.enums.ResultEnum;
import upctx.qi_back_end.repository.DishRepository;
import upctx.qi_back_end.service.DishService;
import upctx.qi_back_end.util.FileUtil;
import upctx.qi_back_end.util.ResultUtil;

import java.util.*;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private FileUtil fileUtil;

    @Override
    public Result<List<Dish>> getAll() {
        List<Dish> list = dishRepository.findAllIdAndName();
        if (list.isEmpty()) {
            throw new MyException(ResultEnum.DISH_NULL);
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
        return ResultUtil.success(dishRepository.count());
    }

    @Override
    public Result<List<Dish>> getDishPage(Integer start, Integer size) {
        return this.getDishPage(start, size, "id");

    }
    @Override
    public Result<List<Dish>> getDishPage(Integer start, Integer size, String rankStr) {
        if (start < 1) {
            throw new MyException(ResultEnum.PAGE_START_ERROR);
        }
        List<Dish> list = dishRepository.findByIfDelNot(1,
                PageRequest.of(start - 1, size,
                        Sort.by(Sort.Direction.DESC, rankStr))).getContent();
        if (list.isEmpty()) {
            throw new MyException(ResultEnum.DISH_NULL);
        } else {
            return ResultUtil.success(list);
        }
    }

    @Override
    public Result<List<Dish>> getDishHome() {
        List<Dish> list = dishRepository.findByIfHomeShow(1);
        return ResultUtil.success(list);
    }

    @Override
    public Result<Integer> getCountSearch(String sth) {
        return ResultUtil.success(dishRepository.searchCount(sth));
    }

    @Override
    public Result<List<Dish>> getDishPageSearch(String sth, Integer start, Integer size) {
        if (start < 1) {
            throw new MyException(ResultEnum.PAGE_START_ERROR);
        }
        List<Dish> list = dishRepository.search(sth,
                PageRequest.of(start - 1, size,
                        Sort.by(Sort.Direction.DESC, "id"))).getContent();
        if (list.isEmpty()) {
            throw new MyException(ResultEnum.DISH_NULL);
        } else {
            return ResultUtil.success(list);
        }
    }


    @Override
    public Result<Dish> getDish(Integer id) {
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if (optionalDish.isPresent()) {
            return ResultUtil.success(optionalDish.get());
        } else {
            throw new MyException(ResultEnum.DISH_NULL);
        }
    }

    @Override
    public Result<Integer> addDish(Dish dish, MultipartFile file) {
        String url = fileUtil.upload(file);
        if (url != null) {
            dish.setImgUrl(url);
            return ResultUtil.success(dishRepository.save(dish));
        } else {
            throw new MyException(ResultEnum.DISH_FILE_UPLOAD_ERROR);
        }
    }

    @Override
    public Result<Integer> changeDish(Dish dish) {
        return ResultUtil.success(dishRepository.save(dish));
    }

    @Override
    public Result<Integer> delDish(Integer id) {
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if (optionalDish.isPresent()) {
            optionalDish.get().setIfDel(1);
            dishRepository.save(optionalDish.get());
            return ResultUtil.success(0);
        } else {
            throw new MyException(ResultEnum.DISH_NULL);
        }
    }
}
