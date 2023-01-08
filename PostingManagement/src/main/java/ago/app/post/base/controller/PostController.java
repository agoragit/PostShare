package ago.app.post.base.controller;

import ago.app.post.base.vo.PostVO;
import ago.app.post.es.ElasticSearchQuery;
import cont.SavableConst;
import error.ErrorConstant;
import error.ErrorResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Validated
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private ElasticSearchQuery elasticSearchQuery;

    @SneakyThrows
    @PostMapping
    public ErrorResponse save(@Valid @RequestBody PostVO vO) {

        // todo validate here
        if( StringUtils.isNotNull(vO.getPostId()) )
        {
            return new ErrorResponse(ErrorConstant.ERROR, "Invalid post Data");
        }
        try
        {
            return elasticSearchQuery.createOrUpdateDocument(vO, SavableConst.NEW );
        }
        catch (Exception e)
        {
            return new ErrorResponse(ErrorConstant.ERROR, e.getMessage() );
        }
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        elasticSearchQuery.deleteDocumentById( id );
    }

    @PutMapping("/{id}")
    public ErrorResponse update(@Valid @NotNull @PathVariable("id") String id,
                                @Valid @RequestBody PostVO vO) throws IOException {
        // todo validate here
        if( StringUtils.isNull( vO.getProfileId()) || StringUtils.isNull( id ))
        {
            return new ErrorResponse(ErrorConstant.ERROR, "Invalid post");
        }
        else if ( !id.equalsIgnoreCase(vO.getPostId() ))
        {
            return new ErrorResponse(ErrorConstant.ERROR, "post mismatched");
        }
        try
        {
            return elasticSearchQuery.createOrUpdateDocument(vO, SavableConst.MODIFY);
        }
        catch (Exception e)
        {
            return new ErrorResponse(ErrorConstant.ERROR, e.getMessage() );
        }
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public PostVO getById(@Valid @NotNull @PathVariable("id") String id) {
        return elasticSearchQuery.getDocumentById(id);
    }

}
