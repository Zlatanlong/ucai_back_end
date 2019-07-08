package upctx.qi_back_end.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upctx.qi_back_end.domain.Dish;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Integer> {
    @Query("select name from Dish where ifDel <> 1")
    List<String> findAllName();

    @Override
    Page<Dish> findAll(Pageable pageable);

    Page<Dish> findByIfDelNot(Integer ifDel, Pageable pageable);

    List<Dish> findByIfHomeShow(Integer ifHomeShow);

    @Query("select dish from Dish dish where dish.ifDel <> 1 and (dish.name like CONCAT('%',?1,'%') or dish.restaurantName like CONCAT('%',?1,'%'))")
    Page<Dish> search(String sth, Pageable pageable);

    @Query("select count(dish) from Dish dish where dish.ifDel <> 1 and (dish.name like CONCAT('%',?1,'%') or dish.restaurantName like CONCAT('%',?1,'%'))")
    Integer searchCount(String sth);

    @Override
    @Query("select count(id) from Dish where ifDel <> 1")
    long count();

    @Query("select avg(totalScore) from Dish where ifDel <> 1 and restaurantId = ?1")
    Double findAvgScoreByRestaurantId(Integer restaurantId);

    List<Dish> findByRestaurantId(Integer restaurantId);

    @Query("select id,name from Dish where ifDel <> 1")
    List<Dish> findAllIdAndName();
}
