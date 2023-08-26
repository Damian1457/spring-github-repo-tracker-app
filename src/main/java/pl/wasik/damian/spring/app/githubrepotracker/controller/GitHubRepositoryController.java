package pl.wasik.damian.spring.app.githubrepotracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wasik.damian.spring.app.githubrepotracker.model.ErrorResponse;
import pl.wasik.damian.spring.app.githubrepotracker.model.GitHubRepository;
import pl.wasik.damian.spring.app.githubrepotracker.service.GitHubRepositoryService;

import java.util.List;


@RestController
@RequestMapping("/api")
public class GitHubRepositoryController {

    private final GitHubRepositoryService githubRepositoryService;

    public GitHubRepositoryController(GitHubRepositoryService githubRepositoryService) {
        this.githubRepositoryService = githubRepositoryService;
    }

    @GetMapping("/repositories/{username}")
    public ResponseEntity<?> getRepositories(@PathVariable String username) {
        if (!githubRepositoryService.isUserExists(username)) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
            errorResponse.setMessage("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        List<GitHubRepository> repositories = githubRepositoryService.getRepositories(username);
        return ResponseEntity.ok(repositories);
    }
}