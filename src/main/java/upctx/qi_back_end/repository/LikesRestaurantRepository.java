package upctx.qi_back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upctx.qi_back_end.domain.LikesRestaurant;

import java.util.List;
import java.util.Optional;

public interface LikesRestaurantRepository extends JpaRepository<LikesRestaurant, Integer> {
    Optional<List<LikesRestaurant>> findByUserId(Integer id);

    @Override
    @Query("select count(id) from LikesRestaurant where ifDel <> 1")
    long count();

    LikesRestaurant findByUserIdAndRestaurantId(Integer userId, Integer restaurantId);
}
