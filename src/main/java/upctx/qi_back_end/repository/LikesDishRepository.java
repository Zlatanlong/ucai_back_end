package upctx.qi_back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upctx.qi_back_end.domain.LikesDish;

import java.util.List;
import java.util.Optional;

public interface LikesDishRepository extends JpaRepository<LikesDish, Integer> {
    @Query("select likesDish from LikesDish likesDish where likesDish.ifDel <> 1 and userId = ?1")
    Optional<List<LikesDish>> findByUserId(Integer id);

    @Override
    @Query("select count(id) from LikesDish where ifDel <> 1")
    long count();


    @Query("select likesDish from LikesDish likesDish where likesDish.ifDel <> 1 and userId = ?1 and dishId = ?2")
    LikesDish findByUserIdAndDishId(Integer userId, Integer dishId);
}
