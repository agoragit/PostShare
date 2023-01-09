package ago.app.comment.base.controller;

import ago.app.comment.base.dto.PostCommentDTO;
import ago.app.comment.base.vo.PostCommentUpdateVO;
import ago.app.comment.base.vo.PostCommentVO;
import ago.app.comment.es.ElasticSearchQuery;
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
@RequestMapping("/postComment")
public class PostCommentController {

    @Autowired
    private ElasticSearchQuery elasticSearchQuery;

    @SneakyThrows
    @PostMapping
    public ErrorResponse save(@Valid @RequestBody PostCommentVO vO) {

        // todo validate here
        if( StringUtils.isNotNull(vO.getCommentId()) )
        {
            return new ErrorResponse(ErrorConstant.ERROR, "Invalid Profile Data");
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
                                @Valid @RequestBody PostCommentUpdateVO vO) throws IOException {
        // todo validate here
        if( StringUtils.isNull( vO.getCommentId()) || StringUtils.isNull( id ))
        {
            return new ErrorResponse(ErrorConstant.ERROR, "Invalid profile");
        }
        else if ( !id.equalsIgnoreCase(vO.getCommentId()))
        {
            return new ErrorResponse(ErrorConstant.ERROR, "profile mismatched");
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
    public PostCommentDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return elasticSearchQuery.getDocumentById(id);
    }
}
