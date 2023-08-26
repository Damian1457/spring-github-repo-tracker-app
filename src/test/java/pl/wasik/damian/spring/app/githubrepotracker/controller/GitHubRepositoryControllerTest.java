package pl.wasik.damian.spring.app.githubrepotracker.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.wasik.damian.spring.app.githubrepotracker.model.ErrorResponse;
import pl.wasik.damian.spring.app.githubrepotracker.model.GitHubBranchResponse;
import pl.wasik.damian.spring.app.githubrepotracker.model.GitHubRepository;
import pl.wasik.damian.spring.app.githubrepotracker.service.GitHubRepositoryService;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

public class GitHubRepositoryControllerTest {

    @Mock
    private GitHubRepositoryService githubRepositoryService;

    @InjectMocks
    private GitHubRepositoryController gitHubRepositoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenExistingUser_whenGetRepositories_thenReturnsListOfRepositories() {
        // Given
        String username = "testUser";
        GitHubRepository repository = new GitHubRepository();
        repository.setName("repo1");
        repository.setOwnerLogin("testUser");
        repository.setBranches(Collections.singletonList(new GitHubBranchResponse()));
        repository.setLastCommitShas(Collections.singletonMap("main", "commitSHA"));
        List<GitHubRepository> repositories = Collections.singletonList(repository);

        when(githubRepositoryService.isUserExists(username)).thenReturn(true);
        when(githubRepositoryService.getRepositories(username)).thenReturn(repositories);

        // When
        ResponseEntity<?> responseEntity = gitHubRepositoryController.getRepositories(username);

        // Then
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(repositories, responseEntity.getBody());
    }

    @Test
    void givenNonExistingUser_whenGetRepositories_thenReturnsNotFound() {
        // Given
        String username = "nonExistingUser";

        when(githubRepositoryService.isUserExists(username)).thenReturn(false);

        // When
        ResponseEntity<?> responseEntity = gitHubRepositoryController.getRepositories(username);

        // Then
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assertions.assertEquals("User not found", ((ErrorResponse) responseEntity.getBody()).getMessage());
    }
}