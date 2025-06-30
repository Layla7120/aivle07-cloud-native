package aivlecloudnative.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Long> {

    //TODO: view Model 중복 쿼리 정리하기
    @Query(
        value = "select user " +
        "from User user " +
        "where(:id is null or user.id = :id)"
    )
    User viewSubscriptionHistory(Integer id);

    @Query(
        value = "select user " +
        "from User user " +
        "where(:id is null or user.id = :id)"
    )
    User viewContectHistory(Integer id);

    boolean existsByEmail(String email);
}
