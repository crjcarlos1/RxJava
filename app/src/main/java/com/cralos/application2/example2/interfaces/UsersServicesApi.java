package com.cralos.application2.example2.interfaces;

import com.cralos.application2.example2.models.User;
import com.cralos.application2.example2.retrofit.EndPoints;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UsersServicesApi {

    /**
     * @return
     */
    @GET(EndPoints.POST)
    Observable<List<User>> getUsers();

}
