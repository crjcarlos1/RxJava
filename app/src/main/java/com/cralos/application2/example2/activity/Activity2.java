package com.cralos.application2.example2.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cralos.application2.R;
import com.cralos.application2.example2.fragment.FragmentRecyclerView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        showFragmentRV();
    }

    private void showFragmentRV() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentRecyclerView.TAG);
        transaction.replace(R.id.containerFragments, new FragmentRecyclerView(), FragmentRecyclerView.TAG).commit();
    }

}
