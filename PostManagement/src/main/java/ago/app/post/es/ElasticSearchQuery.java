package ago.app.profile.es;

import ago.app.profile.base.vo.PostProfileVO;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
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

    private final String indexName = "post_profile";


    public String createOrUpdateDocument(PostProfileVO postProfileVO ) throws IOException {

        IndexResponse response = elasticsearchClient.index(i -> i.index(indexName).id( postProfileVO.getProfileId() )
                            .document(postProfileVO)
          );

        if (response.result().name().equals("Created")) {
            return response.id();
        } else if (response.result().name().equals("Updated")) {
            return new StringBuilder("Document has been successfully updated.").toString();
        }
        return new StringBuilder("Error while performing the operation.").toString();
    }

    public PostProfileVO getDocumentById(String id) throws IOException {
        PostProfileVO product = null;
        GetResponse<PostProfileVO> response = elasticsearchClient.get(g -> g
                        .index(indexName)
                        .id(id),
                PostProfileVO.class
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

    public List<PostProfileVO> searchAllDocuments() throws IOException {

        SearchRequest searchRequest = SearchRequest.of(s -> s.index(indexName));
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, PostProfileVO.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<PostProfileVO> products = new ArrayList<>();
        for (Hit object : hits) {
            PostProfileVO postProfileVO = (PostProfileVO) object.source();
            postProfileVO.setProfileId(object.id());
            products.add( postProfileVO );

        }
        return products;
    }
}
