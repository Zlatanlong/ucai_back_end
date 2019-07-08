package upctx.qi_back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upctx.qi_back_end.domain.DishManager;

import java.util.Optional;

public interface DishManagerRepository extends JpaRepository<DishManager, Integer> {
    Optional<DishManager> findByAccount(String account);

    @Override
    @Query("select count(id) from DishManager where ifDel <> 1")
    long count();
}
