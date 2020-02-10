package com.cralos.application2.example3.interfaces;

import com.cralos.application2.example3.models.GithubRepo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {
    /**
     * @param username
     * @return
     */
    @GET("users/{user}/starred")
    Observable<List<GithubRepo>> getStarredRepositories(@Path("user") String username);
}
