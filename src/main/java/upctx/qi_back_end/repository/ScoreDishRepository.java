package upctx.qi_back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upctx.qi_back_end.domain.ScoreDish;

import java.util.List;
import java.util.Optional;

public interface ScoreDishRepository extends JpaRepository<ScoreDish, Integer> {
    @Override
    @Query("select count(id) from ScoreDish where ifDel <> 1")
    long count();

    List<ScoreDish> findByUserId(Integer userId);

    Optional<ScoreDish> findByUserIdAndDishId(Integer userId, Integer dishId);
}
