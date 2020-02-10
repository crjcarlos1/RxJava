package com.cralos.application2.example3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cralos.application2.R;
import com.cralos.application2.example3.models.GithubRepo;

import java.util.ArrayList;
import java.util.List;

public class GithubRecycler extends RecyclerView.Adapter<GithubRecycler.ViewHolder> {

    private Context context;
    private List<GithubRepo> githubRepos = new ArrayList<>();

    public GithubRecycler(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_github, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txvRepoName.setText(githubRepos.get(position).getName());
        holder.txvRepoDescription.setText(githubRepos.get(position).getDescription());
        holder.txvLanguage.setText(githubRepos.get(position).getLanguaje());
        holder.txvStars.setText(githubRepos.get(position).getStargazersCount());
    }

    @Override
    public int getItemCount() {
        return githubRepos.size();
    }

    public void setGithubRepos(List<GithubRepo> repos) {
        if (repos == null) {
            return;
        } else {
            githubRepos.clear();
            githubRepos.addAll(repos);
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txvRepoName, txvRepoDescription, txvLanguage, txvStars;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txvRepoName = itemView.findViewById(R.id.txvRepoName);
            txvRepoDescription = itemView.findViewById(R.id.textRepoDescription);
            txvLanguage = itemView.findViewById(R.id.textLanguage);
            txvStars = itemView.findViewById(R.id.textStars);
        }
    }

}
