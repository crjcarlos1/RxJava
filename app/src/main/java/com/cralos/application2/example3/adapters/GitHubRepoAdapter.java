package com.cralos.application2.example3.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.cralos.application2.R;
import com.cralos.application2.example3.models.GithubRepo;

import java.util.ArrayList;
import java.util.List;

public class GitHubRepoAdapter extends BaseAdapter {
    private List<GithubRepo> gitHubRepos = new ArrayList<>();

    @Override
    public int getCount() {
        return gitHubRepos.size();
    }

    @Override
    public GithubRepo getItem(int position) {
        if (position < 0 || position >= gitHubRepos.size()) {
            return null;
        } else {
            return gitHubRepos.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = (convertView != null ? convertView : createView(parent));
        final GitHubRepoViewHolder viewHolder = (GitHubRepoViewHolder) view.getTag();
        viewHolder.setGitHubRepo(getItem(position));
        return view;
    }

    public void setGitHubRepos(@Nullable List<GithubRepo> repos) {
        if (repos == null) {
            return;
        }
        gitHubRepos.clear();
        gitHubRepos.addAll(repos);

    }

    private View createView(ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.item_github, parent, false);
        final GitHubRepoViewHolder viewHolder = new GitHubRepoViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    public void add(GithubRepo gitHubRepo) {
        gitHubRepos.add(gitHubRepo);
        notifyDataSetChanged();
    }

    private static class GitHubRepoViewHolder {

        private TextView textRepoName;
        private TextView textRepoDescription;
        private TextView textLanguage;
        private TextView textStars;

        public GitHubRepoViewHolder(View view) {
            textRepoName = (TextView) view.findViewById(R.id.txvRepoName);
            textRepoDescription = (TextView) view.findViewById(R.id.textRepoDescription);
            textLanguage = (TextView) view.findViewById(R.id.textLanguage);
            textStars = (TextView) view.findViewById(R.id.textStars);
        }

        public void setGitHubRepo(GithubRepo gitHubRepo) {
            textRepoName.setText(gitHubRepo.getName());
            textRepoDescription.setText(gitHubRepo.getDescription());
            textLanguage.setText("Language: " + gitHubRepo.getLanguaje());
            textStars.setText("Stars: " + gitHubRepo.getStargazersCount());
        }
    }
}
