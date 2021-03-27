package com.example.userService.services;

import com.example.userService.utils.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

/**
 * Service layer
 * hidden the business logic
 */
@Service
public class PostsService {

    @Autowired
    public RestTemplate restTemplate_1;

    protected Logger logger = Logger.getLogger(PostsService.class.getName());

    /**
     * Post method service.
     *
     * @param id
     * @return
     */
    public Posts getPosts(Long id) {
        logger.info("findByPost() invoked: for id: " + id);
        try {
            return restTemplate_1.getForObject("http://posts-service/api/get/{id}", Posts.class, id);
        } catch (Exception e) {

            logger.severe(e.getClass() + ": " + e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * create post service
     *
     * @param posts
     */
    public void createPosts(Posts posts) {
        logger.info("The request body is: " + posts.toString());

        try {
            ResponseEntity<String> entity = restTemplate_1.postForEntity("http://posts-service/api/posts", posts, String.class);
            logger.info(entity.getBody() + "");
        } catch (Exception e) {
            logger.severe(e.getClass() + ": " + e.getLocalizedMessage());
        }
    }
}
