package ago.app.postshare.base.controller;

import ago.app.postshare.base.dto.PostUserDTO;
import ago.app.postshare.base.service.PostUserService;
import ago.app.postshare.base.vo.PostUserQueryVO;
import ago.app.postshare.base.vo.PostUserUpdateVO;
import ago.app.postshare.base.vo.PostUserVO;
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

    @PostMapping
    public String save(@Valid @RequestBody PostUserVO vO) {
        return postUserService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        postUserService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody PostUserUpdateVO vO) {
        postUserService.update(id, vO);
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
