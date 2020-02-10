package com.cralos.application2.example3.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cralos.application2.R;
import com.cralos.application2.example3.adapters.GithubRecycler;
import com.cralos.application2.example3.models.GithubRepo;
import com.cralos.application2.example3.retrofit.GitHubClient;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentGithub extends Fragment implements View.OnClickListener {
    public static final String TAG = FragmentGithub.class.getSimpleName();

    private Disposable disposable;

    private RecyclerView rvGithub;
    private GithubRecycler recyclerAdapter;

    private EditText edtUserName;
    private Button btnSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_github, container, false);
        initFragmentGithub(view);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearch:
                getData();
                break;
        }
    }

    @Override
    public void onDestroy() {
        if (disposable != null && disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }

    private void initFragmentGithub(View view) {
        rvGithub = view.findViewById(R.id.rvRepos);
        edtUserName = view.findViewById(R.id.edtUserName);
        btnSearch = view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
        recyclerAdapter = new GithubRecycler(getContext());
        setupRecycler();
    }

    private void setupRecycler() {
        rvGithub.setAdapter(recyclerAdapter);
        rvGithub.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void getData() {
        String name = edtUserName.getText().toString();
        if (!TextUtils.isEmpty(name)) {
            getStarredRepo(name);
        }
    }

    private void getStarredRepo(String name) {
        Observable<List<GithubRepo>> observable = GitHubClient.getInstance()
                .getStarredRepos(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<List<GithubRepo>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("RXJAVA", "onSubscribe: ");
                disposable = d;
            }

            @Override
            public void onNext(List<GithubRepo> githubRepos) {
                Log.e("RXJAVA", "onNext: ");
                recyclerAdapter.setGithubRepos(githubRepos);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("RXJAVA", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e("RXJAVA", "onComplete: ");
            }
        });
    }

}
