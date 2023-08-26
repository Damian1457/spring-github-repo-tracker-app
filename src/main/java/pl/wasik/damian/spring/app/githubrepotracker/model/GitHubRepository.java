package pl.wasik.damian.spring.app.githubrepotracker.model;

import java.util.List;
import java.util.Map;

public class GitHubRepository {

    private String name;
    private String ownerLogin;
    private List<GitHubBranchResponse> branches;
    private Map<String, String> lastCommitShas;

    public Map<String, String> getLastCommitShas() {
        return lastCommitShas;
    }

    public void setLastCommitShas(Map<String, String> lastCommitShas) {
        this.lastCommitShas = lastCommitShas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public List<GitHubBranchResponse> getBranches() {
        return branches;
    }

    public void setBranches(List<GitHubBranchResponse> branches) {
        this.branches = branches;
    }
}