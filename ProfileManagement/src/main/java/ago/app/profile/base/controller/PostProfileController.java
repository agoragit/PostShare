package ago.app.profile.base.controller;

import ago.app.profile.base.vo.PostProfileUpdateVO;
import ago.app.profile.base.vo.PostProfileVO;
import ago.app.profile.es.ElasticSearchQuery;
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
@RequestMapping("/postProfile")
public class PostProfileController {

    @Autowired
    private ElasticSearchQuery elasticSearchQuery;

    @SneakyThrows
    @PostMapping
    public ErrorResponse save(@Valid @RequestBody PostProfileVO vO) {

        // todo validate here
        if( StringUtils.isNotNull(vO.getProfileId()) )
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
                       @Valid @RequestBody PostProfileUpdateVO vO) throws IOException {
        // todo validate here
        if( StringUtils.isNull( vO.getProfileId()) || StringUtils.isNull( id ))
        {
            return new ErrorResponse(ErrorConstant.ERROR, "Invalid profile");
        }
        else if ( !id.equalsIgnoreCase(vO.getProfileId() ))
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
    public PostProfileVO getById(@Valid @NotNull @PathVariable("id") String id) {
        return elasticSearchQuery.getDocumentById(id);
    }
}
