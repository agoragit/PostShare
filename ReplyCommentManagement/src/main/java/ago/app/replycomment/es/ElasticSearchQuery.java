package ago.app.replycomment.es;

import ago.app.replycomment.base.dto.PostCommentReplyDTO;
import ago.app.replycomment.base.vo.PostCommentReplyVO;
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

    private final String indexName = EsStatIndexConst.INDEX_post_reply_comment;


    public ErrorResponse createOrUpdateDocument(PostCommentReplyVO postCommentReplyVO, int savableStatus ) throws IOException {

        IndexResponse response = null;
        if(SavableConst.NEW == savableStatus )
        {
            response = elasticsearchClient.index(i -> i.index(indexName)
                                .document(postCommentReplyVO)
              );
        }
        else if ( SavableConst.MODIFY == savableStatus)
        {
            response = elasticsearchClient.index(i -> i.index(indexName).id( postCommentReplyVO.getReplyId())
                    .document(postCommentReplyVO)
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

    public PostCommentReplyDTO getDocumentById(String id) throws IOException {
        PostCommentReplyDTO product = null;
        GetResponse<PostCommentReplyDTO> response = elasticsearchClient.get(g -> g
                        .index(indexName)
                        .id(id),
                PostCommentReplyDTO.class
        );

        if (response.found()) {
            product = response.source();
            product.setReplyId(response.id());
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

    public List<PostCommentReplyVO> searchAllDocuments() throws IOException {

        SearchRequest searchRequest = SearchRequest.of(s -> s.index(indexName));
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, PostCommentReplyVO.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<PostCommentReplyVO> products = new ArrayList<>();
        for (Hit object : hits) {
            PostCommentReplyVO postCommentReplyVO = (PostCommentReplyVO) object.source();
            postCommentReplyVO.setReplyId(object.id());
            products.add( postCommentReplyVO );

        }
        return products;
    }
}
