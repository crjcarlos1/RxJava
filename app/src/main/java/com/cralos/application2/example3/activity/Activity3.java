package com.cralos.application2.example3.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cralos.application2.R;
import com.cralos.application2.example3.fragment.FragmentGithub;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        showFragmentGithub();
    }

    private void showFragmentGithub() {
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.addToBackStack(FragmentGithub.TAG);
        transaction.replace(R.id.containerFragments,new FragmentGithub(),FragmentGithub.TAG).commit();
    }
}
