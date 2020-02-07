package com.cralos.application2.example2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cralos.application2.R;
import com.cralos.application2.databinding.FragmentRecyclerBinding;
import com.cralos.application2.example2.adapters.UserAdapter;
import com.cralos.application2.example2.viewmodel.UserRvViewModelImpl;
import com.cralos.application2.loader.Loader;

public class FragmentRecyclerView extends Fragment {
    public static final String TAG = FragmentRecyclerView.class.getSimpleName();

    /*loader*/
    private Loader loader;

    /*dataBinding*/
    private FragmentRecyclerBinding binding;

    /*adapter*/
    private UserAdapter adapter;

    /*viewModel*/
    private UserRvViewModelImpl viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler, container, false);
        initFragmentRv();
        return binding.getRoot();
    }

    private void initFragmentRv() {
        viewModel = ViewModelProviders.of(this).get(UserRvViewModelImpl.class);
        viewModel.init(getContext());
        loader = new Loader();
        setupUsers();
        setupLoader();
        setupMessage();

        viewModel.getUsersFromApi();
    }

    private void setupLoader() {

        /*viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                showLoader();
            } else {
                hideLoader();
            }
        });*/
    }

    private void setupMessage() {
       /* viewModel.getMessage().observe(this, message ->
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show()
        );*/
    }

    private void setupUsers() {
        viewModel.getUsers().observe(this, users -> {
            binding.rv.setHasFixedSize(false);
            binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new UserAdapter(getContext(), users);
            binding.rv.setAdapter(adapter);
        });
    }

    private void showLoader() {
        loader.show(getActivity().getSupportFragmentManager(), Loader.TAG);
    }

    private void hideLoader() {
        loader.dismiss();
    }

}
