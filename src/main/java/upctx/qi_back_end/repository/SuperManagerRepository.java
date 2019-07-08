package upctx.qi_back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upctx.qi_back_end.domain.SuperManager;

import java.util.Optional;

public interface SuperManagerRepository extends JpaRepository<SuperManager, Integer> {
    Optional<SuperManager> findByAccount(String account);

    @Override
    @Query("select count(id) from SuperManager where ifDel <> 1")
    long count();
}
