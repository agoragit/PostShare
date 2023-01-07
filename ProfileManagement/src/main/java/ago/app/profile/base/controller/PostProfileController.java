package ago.app.profile.base.controller;

import ago.app.profile.base.vo.PostProfileUpdateVO;
import ago.app.profile.base.vo.PostProfileVO;
import ago.app.profile.es.ElasticSearchQuery;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public String save(@Valid @RequestBody PostProfileVO vO) {
        return elasticSearchQuery.createOrUpdateDocument(vO);
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        elasticSearchQuery.deleteDocumentById( id );
    }

    @PutMapping("/{id}")
    public String update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody PostProfileUpdateVO vO) throws IOException {
        return elasticSearchQuery.createOrUpdateDocument(vO);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public PostProfileVO getById(@Valid @NotNull @PathVariable("id") String id) {
        return elasticSearchQuery.getDocumentById(id);
    }
}
