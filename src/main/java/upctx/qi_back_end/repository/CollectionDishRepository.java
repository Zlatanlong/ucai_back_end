package upctx.qi_back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upctx.qi_back_end.domain.CollectionDish;

import java.util.List;
import java.util.Optional;

public interface CollectionDishRepository extends JpaRepository<CollectionDish, Integer> {
    Optional<List<CollectionDish>> findByUserId(Integer id);

    @Query("select count(id) from CollectionDish where ifDel <> 1")
    long count();

    CollectionDish findByUserIdAndDishId(Integer userId, Integer dishId);
}
