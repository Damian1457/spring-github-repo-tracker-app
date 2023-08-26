package pl.wasik.damian.spring.app.githubrepotracker.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.wasik.damian.spring.app.githubrepotracker.model.GitHubBranchResponse;
import pl.wasik.damian.spring.app.githubrepotracker.model.GitHubRepoResponse;
import pl.wasik.damian.spring.app.githubrepotracker.model.GitHubRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GitHubRepositoryService {

    private final RestTemplate restTemplate;

    public GitHubRepositoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isUserExists(String username) {
        String url = "https://api.github.com/users/" + username;
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }

    public List<GitHubRepository> getRepositories(String username) {
        String url = "https://api.github.com/users/" + username + "/repos";
        GitHubRepoResponse[] response = restTemplate.getForObject(url, GitHubRepoResponse[].class);
        List<GitHubRepository> repositories = new ArrayList<>();

        for (GitHubRepoResponse repo : response) {
            if (!repo.isFork()) {
                GitHubRepository repository = new GitHubRepository();
                repository.setName(repo.getName());
                repository.setOwnerLogin(repo.getOwner().getLogin());

                List<GitHubBranchResponse> branches = getBranches(username, repo.getName());
                repository.setBranches(branches);

                repositories.add(repository);
            }
        }

        return repositories;
    }

    private List<GitHubBranchResponse> getBranches(String username, String repoName) {
        String url = "https://api.github.com/repos/" + username + "/" + repoName + "/branches";
        GitHubBranchResponse[] response = restTemplate.getForObject(url, GitHubBranchResponse[].class);
        return Arrays.asList(response);
    }
}