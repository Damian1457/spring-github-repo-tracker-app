package pl.wasik.damian.spring.app.githubrepotracker.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

class GitHubRepositoryServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GitHubRepositoryService gitHubRepositoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenExistingUser_whenIsUserExists_thenReturnsTrue() {
        // Given
        String username = "testUser";
        String url = "https://api.github.com/users/" + username;
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.OK);

        when(restTemplate.getForEntity(url, Object.class)).thenReturn(response);

        // When
        boolean result = gitHubRepositoryService.isUserExists(username);

        // Then
        Assertions.assertTrue(result);
    }

    @Test
    void givenNonExistingUser_whenIsUserExists_thenReturnsFalse() {
        // Given
        String username = "nonExistingUser";
        String url = "https://api.github.com/users/" + username;

        when(restTemplate.getForEntity(url, Object.class)).thenThrow(HttpClientErrorException.NotFound.class);

        // When
        boolean result = gitHubRepositoryService.isUserExists(username);

        // Then
        Assertions.assertFalse(result);
    }
}