package upctx.qi_back_end.repository;

import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upctx.qi_back_end.domain.Reply;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    @Override
    @Query("select count(id) from Reply where ifDel <> 1")
    long count();

    Page<Reply> findByDishIdAndIfDelNot(Integer dishId, Integer ifDel, Pageable pageable);

    @Query("select count (id) from Reply where ifDel <> 1 and dishId = ?1")
    Integer findCountByDishId(Integer dishId);

    List<Reply> findByToReplyIdAndSeen(Integer id, Boolean seen);

    List<Reply> findByFromUserId(Integer id);
}
