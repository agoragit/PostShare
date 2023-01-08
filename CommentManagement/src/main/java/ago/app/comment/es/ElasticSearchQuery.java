package ago.app.comment.es;

import ago.app.comment.base.vo.PostCommentVO;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import cont.EsStatIndexConst;
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

    private final String indexName = EsStatIndexConst.INDEX_post_comment;


    public ErrorResponse createOrUpdateDocument(PostCommentVO postCommentVO, int savableStatus ) throws IOException {

        IndexResponse response = null;
        if(SavableConst.NEW == savableStatus )
        {
            response = elasticsearchClient.index(i -> i.index(indexName)
                                .document(postCommentVO)
              );
        }
        else if ( SavableConst.MODIFY == savableStatus)
        {
            response = elasticsearchClient.index(i -> i.index(indexName).id( postCommentVO.getCommentId() )
                    .document(postCommentVO)
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

    public PostCommentVO getDocumentById(String id) throws IOException {
        PostCommentVO product = null;
        GetResponse<PostCommentVO> response = elasticsearchClient.get(g -> g
                        .index(indexName)
                        .id(id),
                PostCommentVO.class
        );

        if (response.found()) {
            product = response.source();
            product.setCommentId(response.id());
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

    public List<PostCommentVO> searchAllDocuments() throws IOException {

        SearchRequest searchRequest = SearchRequest.of(s -> s.index(indexName));
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, PostCommentVO.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<PostCommentVO> products = new ArrayList<>();
        for (Hit object : hits) {
            PostCommentVO postCommentVO = (PostCommentVO) object.source();
            postCommentVO.setCommentId(object.id());
            products.add( postCommentVO );

        }
        return products;
    }
}
