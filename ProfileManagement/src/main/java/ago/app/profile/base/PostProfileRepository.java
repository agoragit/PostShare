package ago.app.profile.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostProfileRepository extends JpaRepository<PostProfile, Long>, JpaSpecificationExecutor<PostProfile> {

}