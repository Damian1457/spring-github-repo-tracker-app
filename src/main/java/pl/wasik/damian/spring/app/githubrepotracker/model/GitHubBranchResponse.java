package pl.wasik.damian.spring.app.githubrepotracker.model;

public class GitHubBranchResponse {
    private String name;
    private GitHubCommitResponse commit;

    public GitHubBranchResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GitHubCommitResponse getCommit() {
        return commit;
    }

    public void setCommit(GitHubCommitResponse commit) {
        this.commit = commit;
    }
}
