package com.mahrous.mgtrackingdemo.network;


import com.mahrous.mgtrackingdemo.data.AccessTokenGetResponse;
import com.mahrous.mgtrackingdemo.data.Device;
import com.mahrous.mgtrackingdemo.data.GeneralModel;
import com.mahrous.mgtrackingdemo.data.TrackingResponse;
import com.mahrous.mgtrackingdemo.data.UserResponse;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    // Sign Up
    @GET("checkEmail.php")
    Observable<GeneralModel> checkEmail(@Query("email") String email);

    @GET("CheckPhone.php")
    Observable<GeneralModel> checkPhone(@Query("phone") String phone);


    @POST("login.php")
    @FormUrlEncoded
    Observable<UserResponse> login(@Field("email") String email, @Field("email") String password);

    @POST("register.php")
    @FormUrlEncoded
    Observable<GeneralModel> register(@Field("name") String name,
                                      @Field("email") String email,
                                      @Field("phone") String phone,
                                      @Field("password") String password

    );

    @POST("device_add.php")
    @FormUrlEncoded
    Observable<GeneralModel> addDevice(     @Field("pass") String password,
                                            @Field("account") String account,
                                            @Field("serial") String serial,
                                            @Field("firstName") String firstName,
                                            @Field("lastName") String lastName,
                                            @Field("lat") double lat,
                                            @Field("lon") double lon,
                                            @Field("token") String token,
                                            @Field("expireDate") int expireDate,
                                            @Field("userId") int userId

    );


    @GET("access_token_get.php")
    Observable<AccessTokenGetResponse> getAccessToken(@Query("pass") String password,
                                                      @Query("account") String account);
   @POST("tracking.php")
    Observable<TrackingResponse> tracking(@Query("access_token") String access_token,
                                          @Query("imeis") String serial);



    @GET("device_get_all.php")
    Observable<List<Device>> getDevices(@Query("userId") int userId);





}
