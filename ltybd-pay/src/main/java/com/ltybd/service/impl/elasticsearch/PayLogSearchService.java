package com.ltybd.service.impl.elasticsearch;

import com.ltybd.service.IPayLogSearchService;
import com.ltybd.util.DatesUtils;
import com.ltybd.util.JsonModelUtils;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PayLogSearchService implements IPayLogSearchService  {

    private static Logger logger = LoggerFactory.getLogger(PayLogSearchService.class);

    @Autowired
    TransportClient transportClient;


    @Override
    public  Map<String, Object> findOne(Map<String, String> pares) {
        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch("paylog")
                .setTypes("logs");
        for (String key:pares.keySet()){
            searchRequestBuilder.setQuery(QueryBuilders.termQuery(key, pares.get(key)));
        }
        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet()            ;
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits !=null && hits.length == 1){
           return hits[0].getSource();
        }else {
            return null;
        }
    }

    @Override
    public int savePayLog(Object t) {
            IndexResponse indexResponse = transportClient.prepareIndex("paylog", "logs")
                    .setSource(JsonModelUtils.jsonModle(t), XContentType.JSON)
                    .setTimeout(new TimeValue(3000))
                    .execute().actionGet();
             return 0;
    }

    @Override
    public int savePayLog(String jsonStr) {
        IndexResponse indexResponse = transportClient.prepareIndex("paylog", "logs")
                .setSource(jsonStr, XContentType.JSON)
                .setTimeout(new TimeValue(3000))
                .execute().actionGet();
        return 0;
    }
}
