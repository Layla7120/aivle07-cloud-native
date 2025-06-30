package aivlecloudnative.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
