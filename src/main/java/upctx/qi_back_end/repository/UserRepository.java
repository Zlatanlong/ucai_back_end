package upctx.qi_back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upctx.qi_back_end.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByActiveCode(String activeCode);

    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);

    Optional<User> findByOpenid(String openid);

    @Override
    @Query("select user from User user where user.ifDel <> 1")
    List<User> findAll();

    @Override
    @Query("select count(id) from User where ifDel <> 1")
    long count();

    @Query("select name from User where id = ?1")
    String findNameById(Integer fromUserId);
}
