package ago.app.user.controller;

import ago.app.user.base.dto.PostUserDTO;
import ago.app.user.base.service.PostUserService;
import ago.app.user.base.vo.PostUserQueryVO;
import ago.app.user.base.vo.PostUserUpdateVO;
import ago.app.user.base.vo.PostUserVO;
import ago.app.user.es.ElasticSearchQuery;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/postUser")
public class PostUserController {

    @Autowired
    private PostUserService postUserService;

    @Autowired
    private ElasticSearchQuery elasticSearchQuery;

    @SneakyThrows
    @PostMapping
    public String save(@Valid @RequestBody PostUserVO vO) {
        long userId = postUserService.save(vO);
        if (userId > 0 )
        {
            vO.setUserId(userId);
            elasticSearchQuery.createOrUpdateDocument(vO, userId);
        }
        return userId+"";

    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        postUserService.delete(id);
        elasticSearchQuery.deleteDocumentById( id+"");
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody PostUserUpdateVO vO) {
        postUserService.update(id, vO);
        elasticSearchQuery.createOrUpdateDocument(vO, id);
    }

    @GetMapping("/{id}")
    public PostUserDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return postUserService.getById(id);
    }

    @GetMapping
    public Page<PostUserDTO> query(@Valid @RequestBody PostUserQueryVO vO) {
        return postUserService.query(vO);
    }
}
