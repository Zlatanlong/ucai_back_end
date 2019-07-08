package upctx.qi_back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upctx.qi_back_end.domain.College;

public interface CollegeRepository extends JpaRepository<College, Integer> {
    @Override
    @Query("select count(id) from College where ifDel <> 1")
    long count();
}
