package pl.wasik.damian.spring.app.githubrepotracker.model;

public class GitHubCommitResponse {
    private String sha;

    public GitHubCommitResponse() {
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }
}
