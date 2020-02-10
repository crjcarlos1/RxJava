package com.cralos.application2.example3.retrofit;

import com.cralos.application2.example3.interfaces.GithubService;
import com.cralos.application2.example3.models.GithubRepo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubClient {

    private static final String GITHUB_BASE_URL = "https://api.github.com/";
    private static GitHubClient instance = null;
    private static GithubService gitHubService;

    private GitHubClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(GITHUB_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gitHubService = retrofit.create(GithubService.class);
    }

    public static GitHubClient getInstance() {
        if (instance == null) {
            instance = new GitHubClient();
        }
        return instance;
    }

    public Observable<List<GithubRepo>> getStarredRepos(String userName) {
        return gitHubService.getStarredRepositories(userName);
    }

}
