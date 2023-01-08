package ago.app.user.controller;

import ago.app.user.base.dto.PostUserDTO;
import ago.app.user.base.service.PostUserService;
import ago.app.user.base.vo.PostUserQueryVO;
import ago.app.user.base.vo.PostUserUpdateVO;
import ago.app.user.base.vo.PostUserVO;
import ago.app.user.es.ElasticSearchQuery;
import error.ErrorConstant;
import error.ErrorResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public ErrorResponse save(@Valid @RequestBody PostUserVO vO) {
        try
        {
            long userId = postUserService.save(vO);
            if ( userId > 0 )
            {
                vO.setUserId(userId);
                return elasticSearchQuery.createOrUpdateDocument(vO, userId);
            }
            else
            {
                return new ErrorResponse(ErrorConstant.ERROR, "error"); // todo validate here
            }
        }
        catch (DataIntegrityViolationException e)
        {
            return new ErrorResponse(ErrorConstant.ERROR, e.getCause().getCause().getMessage() ); // todo validate here
        }
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        postUserService.delete(id);
        elasticSearchQuery.deleteDocumentById( id+"");
    }

    @PutMapping("/{id}")
    public ErrorResponse update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody PostUserUpdateVO vO) {

        try
        {
            postUserService.update(id, vO);
            return elasticSearchQuery.createOrUpdateDocument(vO, id);
        }
        catch (Exception e)
        {
            return new ErrorResponse(ErrorConstant.ERROR, e.getMessage() ); // todo validate here
        }
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
