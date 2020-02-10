package com.cralos.application2.example3.models;

public class GithubRepo {

    private int id;
    private String name;
    private String htlmUrl;
    private String description;
    private String languaje;
    private String stargazersCount;

    public GithubRepo() {
    }

    public GithubRepo(int id, String name, String htlmUrl, String description, String languaje, String stargazersCount) {
        this.id = id;
        this.name = name;
        this.htlmUrl = htlmUrl;
        this.description = description;
        this.languaje = languaje;
        this.stargazersCount = stargazersCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtlmUrl() {
        return htlmUrl;
    }

    public void setHtlmUrl(String htlmUrl) {
        this.htlmUrl = htlmUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguaje() {
        return languaje;
    }

    public void setLanguaje(String languaje) {
        this.languaje = languaje;
    }

    public String getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(String stargazersCount) {
        this.stargazersCount = stargazersCount;
    }
}
