package com.bawei.zhangjiafu20200221;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author: 张家辅
 * @date: 2020/02/26
 */
public interface MyService {
    @POST("small/user/v1/login")
    @FormUrlEncoded
    Observable<Login_Entity> getLogin(@FieldMap Map<String,String> map);
    @POST("small/user/v1/register")
    @FormUrlEncoded
    Observable<Register_Entity> getRegister(@FieldMap Map<String,String> map);
}
