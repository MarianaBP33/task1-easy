package com.mariana.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mariana.task1.model.Post;
import com.mariana.task1.model.User;
import com.mariana.task1.repository.PostRepository;
import com.mariana.task1.service.PostService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceUnitTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    public void testGetAllPosts() {
        // Given
        User author = new User(1L, "authorName", "authorPassword");
        List<Post> mockPosts = Arrays.asList(new Post(1L, "Title 1", "Content 1", author),
                new Post(2L, "Title 2", "Content 2", author));
        when(postRepository.findAll()).thenReturn(mockPosts);

        // When
        List<Post> result = postService.getAllPosts();

        // Then
        assertEquals(2, result.size());
        assertEquals("Title 1", result.get(0).getTitle());

    }

    @Test
    public void testGetPostById() {
        long postId = 1L;
        User author = new User(1L, "authorName", "authorPassword");
        Post mockPost = new Post(postId, "Title", "Content", author);
        when(postRepository.findById(postId)).thenReturn(Optional.of(mockPost));

        Optional<Post> result = postService.getPostById(postId);

        assertEquals(mockPost, result.orElse(null));
    }

    @Test
    public void testCreatePost() {
        // Given
        User author = new User(1L, "authorName", "authorPassword");
        Post newPost = new Post(null, "New Title", "New Content", author);
        when(postRepository.save(newPost)).thenReturn(new Post(1L, "New Title", "New Content", author));

        // When
        Post result = postService.createPost(newPost);

        // Then
        assertEquals(1L, result.getId());
        assertEquals("New Title", result.getTitle());
        assertEquals("New Content", result.getBody());
        assertEquals(author, result.getAuthor());
    }

    @Test
    public void testDeletePost() {
        // Given
        long postId = 1L;

        // When
        postService.deletePost(postId);

        // Then
        verify(postRepository, times(1)).deleteById(postId);
    }
}
