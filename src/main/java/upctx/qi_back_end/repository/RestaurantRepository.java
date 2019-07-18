package upctx.qi_back_end.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upctx.qi_back_end.domain.Reply;
import upctx.qi_back_end.domain.Restaurant;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Override
    @Query("select count(id) from Restaurant where ifDel <> 1")
    long count();

    @Query("select id,name from Restaurant where ifDel<> 1")
    List<Restaurant> findAllIdAndName();

    Page<Restaurant> findByIfDelNot(Integer ifDel, Pageable pageable);
}
