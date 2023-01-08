package ago.app.post.es;

import ago.app.post.base.ReactionStat;
import ago.app.post.base.dto.PostDTO;
import ago.app.post.base.vo.PostVO;
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

    private final String indexName = EsStatIndexConst.INDEX_post;
    private final String reactionStatIndex = EsStatIndexConst.INDEX_post_reaction_stat;


    public ErrorResponse createOrUpdateDocument(PostVO postVO, int savableStatus ) throws IOException {

        IndexResponse response = null;
        if(SavableConst.NEW == savableStatus )
        {
            response = elasticsearchClient.index(i -> i.index(indexName)
                                .document(postVO)
              );
            IndexResponse finalResponse = response;
            elasticsearchClient.index(i -> i.index(reactionStatIndex).id( finalResponse.id() )
                    .document(new ReactionStat())
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

    public PostDTO getDocumentById(String id) throws IOException {
        PostDTO product = null;
        GetResponse<PostDTO> response = elasticsearchClient.get(g -> g
                        .index(indexName)
                        .id(id),
                PostDTO.class
        );

        if (response.found()) {
            product = response.source();
            product.setPostId(response.id());
            product.setReactionStat(getReactionStatById(response.id()));
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
    public ReactionStat getReactionStatById(String id) throws IOException {
        ReactionStat product = null;
        GetResponse<ReactionStat> response = elasticsearchClient.get(g -> g
                        .index(reactionStatIndex)
                        .id(id),
                ReactionStat.class
        );

        if (response.found()) {
            product = response.source();
        }

        return product;
    }
    public List<PostDTO> searchAllDocuments() throws IOException {

        SearchRequest searchRequest = SearchRequest.of(s -> s.index(indexName));
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, PostVO.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<PostDTO> products = new ArrayList<>();
        for (Hit object : hits) {
            PostDTO postDTO = (PostDTO) object.source();
            postDTO.setPostId(object.id());
            postDTO.setReactionStat(getReactionStatById(object.id()));

            products.add( postDTO );

        }
        return products;
    }
}
