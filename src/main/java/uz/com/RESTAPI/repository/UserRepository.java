package uz.com.RESTAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.com.RESTAPI.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    //todo: select u.* from user as u where u.user_id = id and u.delet_time is null;
    Optional<User> findByUserIdAndDeletedAtIsNull(Integer userId);

    //todo:
    @Query(value = "select u from user as u where delete_time is null",nativeQuery = true)
    List<User> findAllByDeletedAtIsNull();

    Boolean existsByUserIdAndDeletedAtIsNull(Integer userId);
}
