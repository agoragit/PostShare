package ago.app.post.es;

import ago.app.post.base.vo.PostVO;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import cont.SavableConst;
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

    private final String indexName = "post";


    public ErrorResponse createOrUpdateDocument(PostVO postVO, int savableStatus ) throws IOException {

        IndexResponse response = null;
        if(SavableConst.NEW == savableStatus )
        {
            response = elasticsearchClient.index(i -> i.index(indexName)
                                .document(postVO)
              );
        }
        else if ( SavableConst.MODIFY == savableStatus)
        {
            response = elasticsearchClient.index(i -> i.index(indexName).id( postVO.getPostId() )
                    .document(postVO)
            );
        }
        if( response != null )
        {
        if (response.result().name().equals("Created"))
            {
                return new ErrorResponse(ErrorConstant.SUCCESS, "Created", response.id() );
            } else if (response.result().name().equals("Updated")) {
                return new ErrorResponse(ErrorConstant.SUCCESS, "Updated", response.id() );
            }
        }
        return  new ErrorResponse<Result>( ErrorConstant.ERROR, "Error" );
    }

    public PostVO getDocumentById(String id) throws IOException {
        PostVO product = null;
        GetResponse<PostVO> response = elasticsearchClient.get(g -> g
                        .index(indexName)
                        .id(id),
                PostVO.class
        );

        if (response.found()) {
            product = response.source();
            product.setProfileId(response.id());
        }

        return product;
    }

    public String deleteDocumentById(String id) throws IOException {

        DeleteRequest request = DeleteRequest.of(d -> d.index(indexName).id(id));

        DeleteResponse deleteResponse = elasticsearchClient.delete(request);
        if (Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals("NotFound")) {
            return new StringBuilder("Product with id " + deleteResponse.id() + " has been deleted.").toString();
        }
        System.out.println("Product not found");
        return new StringBuilder("Product with id " + deleteResponse.id() + " does not exist.").toString();

    }

    public List<PostVO> searchAllDocuments() throws IOException {

        SearchRequest searchRequest = SearchRequest.of(s -> s.index(indexName));
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, PostVO.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<PostVO> products = new ArrayList<>();
        for (Hit object : hits) {
            PostVO postProfileVO = (PostVO) object.source();
            postProfileVO.setProfileId(object.id());
            products.add( postProfileVO );

        }
        return products;
    }
}
