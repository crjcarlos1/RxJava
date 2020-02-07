package com.cralos.application2.example2.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cralos.application2.example2.interactor.UsersRvInteractorImpl;
import com.cralos.application2.example2.interfaces.UserRvInteractor;
import com.cralos.application2.example2.interfaces.UserRvViewModel;
import com.cralos.application2.example2.models.User;

import java.util.List;

public class UserRvViewModelImpl extends ViewModel implements UserRvViewModel {

    /*interactor*/
    private UserRvInteractor interactor;

    /*users write*/
    private MutableLiveData<List<User>> users;

    /*users read*/
    public LiveData<List<User>> getUsers() {
        return users;
    }

    /*updating write*/
    private MutableLiveData<Boolean> isLoading;

    /*updating read*/
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    /*message write*/
    private MutableLiveData<String> message;

    /*message read*/
    public LiveData<String> getMessage() {
        return message;
    }

    public void init(Context context) {
        users = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
        message = new MutableLiveData<>();
        interactor = new UsersRvInteractorImpl(this, context);
    }

    public void getUsersFromApi() {
        isLoading.setValue(true);
        interactor.getUsers();
    }

    @Override
    public void setUsers(List<User> users) {
        this.isLoading.setValue(false);
        this.users.setValue(users);
    }

    @Override
    public void setMessage(String message) {
        this.isLoading.setValue(false);
        this.message.setValue(message);
    }

}
