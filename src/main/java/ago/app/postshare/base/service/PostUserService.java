package ago.app.postshare.base.service;

import ago.app.postshare.base.PostUserRepository;
import ago.app.postshare.base.dto.PostUserDTO;
import ago.app.postshare.base.entity.PostUser;
import ago.app.postshare.base.vo.PostUserQueryVO;
import ago.app.postshare.base.vo.PostUserUpdateVO;
import ago.app.postshare.base.vo.PostUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PostUserService {

    @Autowired
    private PostUserRepository postUserRepository;

    public Long save(PostUserVO vO) {
        PostUser bean = new PostUser();
        BeanUtils.copyProperties(vO, bean);
        bean = postUserRepository.save(bean);
        return bean.getUserId();
    }

    public void delete(Long id) {
        postUserRepository.deleteById(id);
    }

    public void update(Long id, PostUserUpdateVO vO) {
        PostUser bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        postUserRepository.save(bean);
    }

    public PostUserDTO getById(Long id) {
        PostUser original = requireOne(id);
        return toDTO(original);
    }

    public Page<PostUserDTO> query(PostUserQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private PostUserDTO toDTO(PostUser original) {
        PostUserDTO bean = new PostUserDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private PostUser requireOne(Long id) {
        return postUserRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
