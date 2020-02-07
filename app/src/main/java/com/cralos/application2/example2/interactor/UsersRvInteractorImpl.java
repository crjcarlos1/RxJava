package com.cralos.application2.example2.interactor;

import android.content.Context;
import android.util.Log;

import com.cralos.application2.example2.interfaces.UserRvInteractor;
import com.cralos.application2.example2.interfaces.UserRvViewModel;
import com.cralos.application2.example2.interfaces.UsersServicesApi;
import com.cralos.application2.example2.models.User;
import com.cralos.application2.example2.retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class UsersRvInteractorImpl implements UserRvInteractor {

    private UserRvViewModel viewModel;
    private Context context;
    private Retrofit retrofit;
    private UsersServicesApi services;

    public UsersRvInteractorImpl(UserRvViewModel viewModel, Context context) {
        this.viewModel = viewModel;
        this.context = context;
        retrofit = RetrofitClient.getRetrofitInstance();
        services = retrofit.create(UsersServicesApi.class);
    }

    @Override
    public void getUsers() {
        Observable<List<User>> observable = services.getUsers()
                .subscribeOn(Schedulers.io())                   /*se ejecuta en background*/
                .observeOn(AndroidSchedulers.mainThread());     /*se escuchaa en el hilo principal*/
        getUsersList(observable);
    }

    private void getUsersList(Observable<List<User>> observable) {


        observable.subscribe(new Observer<List<User>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("RETROFIT", "onSubscribe: ");
            }

            @Override
            public void onNext(List<User> users) {
                if (users != null) {
                    Log.e("RETROFIT", "SIZE: " + users.size());
                    viewModel.setUsers(users);
                } else
                    Log.e("RETROFIT", "LLISTA VACIA");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("RETROFIT", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e("RETROFIT", "onComplete: ");
            }
        });

        /*
        observable.subscribe(users ->
                viewModel.setUsers(users)
        );*/
    }

}
