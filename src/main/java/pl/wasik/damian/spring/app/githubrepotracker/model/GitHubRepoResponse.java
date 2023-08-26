package pl.wasik.damian.spring.app.githubrepotracker.model;

public class GitHubRepoResponse {
    private String name;
    private boolean fork;
    private GitHubRepoOwnerResponse owner;

    public GitHubRepoResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public GitHubRepoOwnerResponse getOwner() {
        return owner;
    }

    public void setOwner(GitHubRepoOwnerResponse owner) {
        this.owner = owner;
    }
}
