package ago.app.postshare.base;

import ago.app.postshare.base.entity.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostUserRepository extends JpaRepository<PostUser, Long>, JpaSpecificationExecutor<PostUser> {

}