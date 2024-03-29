package ago.app.user.es;

import ago.app.user.base.vo.PostUserVO;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import cont.EsStatIndexConst;
import error.ErrorConstant;
import error.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ElasticSearchQuery {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    private final String indexName = EsStatIndexConst.INDEX_post_user;


    public ErrorResponse createOrUpdateDocument(PostUserVO postUserVO, long userId) throws IOException {

        IndexResponse response = elasticsearchClient.index(i -> i
                .index(indexName)
                .id(String.valueOf(userId))
                .document(postUserVO)
        );
        if (response.result().name().equals("Created")) {
            return new ErrorResponse(ErrorConstant.SUCCESS, "Created", response.id() );
        } else if (response.result().name().equals("Updated")) {
            return new ErrorResponse(ErrorConstant.SUCCESS, "Updated", response.id() );
        }
        return  new ErrorResponse<Result>( ErrorConstant.ERROR, "Error" );
    }

    public PostUserVO getDocumentById(long userId) throws IOException {
        PostUserVO product = null;
        GetResponse<PostUserVO> response = elasticsearchClient.get(g -> g
                        .index(indexName)
                        .id(String.valueOf(userId)),
                PostUserVO.class
        );

        if (response.found()) {
            product = response.source();
        }

        return product;
    }

    public String deleteDocumentById(String productId) throws IOException {

        DeleteRequest request = DeleteRequest.of(d -> d.index(indexName).id(productId));

        DeleteResponse deleteResponse = elasticsearchClient.delete(request);
        if (Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals("NotFound")) {
            return new StringBuilder("Product with id " + deleteResponse.id() + " has been deleted.").toString();
        }
        System.out.println("Product not found");
        return new StringBuilder("Product with id " + deleteResponse.id() + " does not exist.").toString();

    }

    public List<PostUserVO> searchAllDocuments() throws IOException {

        SearchRequest searchRequest = SearchRequest.of(s -> s.index(indexName));
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, PostUserVO.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<PostUserVO> products = new ArrayList<>();
        for (Hit object : hits) {

            System.out.print(((PostUserVO) object.source()));
            products.add((PostUserVO) object.source());

        }
        return products;
    }
}
