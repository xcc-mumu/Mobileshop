package com.example.mobileshopxcc.http.service;

import com.example.mobileshopxcc.http.entity.CategoryEntity;
import com.example.mobileshopxcc.http.entity.HttpResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface CategoryService {


    @GET("cat")
    Observable<HttpResult<List<CategoryEntity>>> getTopList();

    @GET("cat/parent/{parentId}")
    Observable<HttpResult<List<CategoryEntity>>> getSecondList(@Path("parentId") int parentId);

}
