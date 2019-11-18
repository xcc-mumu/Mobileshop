package com.example.mobileshopxcc.http.service;

import com.example.mobileshopxcc.http.entity.HttpResult;
import com.example.mobileshopxcc.http.entity.MemberEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface MemberService {
    @FormUrlEncoded
    @POST("member/login2")
    Observable<HttpResult<MemberEntity>> login2(
            @Field("input") String input,
            @Field("password") String password);
    @FormUrlEncoded
    @POST("member")
    Observable<HttpResult<MemberEntity>> register(
            @Field("uname") String uname,
            @Field("password") String  password,
            @Field("email") String email);
    @FormUrlEncoded
    @POST("member/{memberId}")
    rx.Observable changePassword(
            @Path("memberId") String memberId,
            @Query("old_pwd") String  old_pwd,
            @Query("new_pwd") String new_pwd);
}
