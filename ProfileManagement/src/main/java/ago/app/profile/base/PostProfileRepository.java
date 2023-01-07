package ago.app.profile.base;

import ago.app.profile.base.PostProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostProfileRepository extends JpaRepository<PostProfile, Long>, JpaSpecificationExecutor<PostProfile> {

}