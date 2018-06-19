package com.example.vedantiladda.ecommerce.search;

import com.example.vedantiladda.ecommerce.model.ProductDTO;
import com.example.vedantiladda.ecommerce.model.SearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchAPI {

    @GET("/solr/nile_search/select")
    Call<SearchResponse> search(@Query("q") String query,@Query("wt") String wt);
}

