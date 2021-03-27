package com.example.userService.controller;

import com.example.userService.exceptions.AccountNotFoundException;
import com.example.userService.model.DiscussionBoard;
import com.example.userService.repository.DiscussionBoardRepository;
import com.example.userService.services.PostsService;
import com.example.userService.services.UserAccountsService;
import com.example.userService.utils.Account;
import com.example.userService.utils.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * User service controller
 */
@RestController
@RequestMapping("/user")
public class UserServicesController {

    @Autowired
    protected UserAccountsService userAccountsService;

    @Autowired
    protected PostsService postsService;

    @Autowired
    protected DiscussionBoardRepository discussionBoardRepository;

    protected Logger logger = Logger.getLogger(UserServicesController.class.getName());

    public UserServicesController(UserAccountsService userAccountsService) {
        this.userAccountsService = userAccountsService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields("accountNumber", "searchText");
    }

    /**
     * health check method.
     *
     * @return
     */
    @GetMapping("/status")
    public String getStatus() {
        return "200,OK";
    }

    /**
     * implements main services for user service
     *
     * @param accountNumber
     * @return
     */
    @RequestMapping("/check/{accountNumber}")
    public String byNumber(@PathVariable("accountNumber") String accountNumber) {

        logger.info("web-service byNumber() invoked: " + accountNumber);

        Account account = userAccountsService.validUser(accountNumber);

        if (account == null) { // no such account
            System.out.println("Wrong account number!");
            throw new AccountNotFoundException(accountNumber);
        } else {
            logger.info("Owner name: " + account.getOwner());
            System.out.println("Welcome " + account.getOwner());
            System.out.println("Please select your next step: ");
            System.out.println("1)Displaying the contents of the forum. 2)Creating a thread. 3)Add a post. 10)shutdown your program");
            Scanner keyboard = new Scanner(System.in);
            Scanner keyboard_id = new Scanner(System.in);
            String options = "999";

            /**
             * “Signing on” – all this requires is the user entering one of the account numbers that already exists.
             * This requires querying the Accounts service
             * Once this is done, then the following are allowed:
             * Displaying the contents of the forum, thread-by-thread
             * Creating a thread – it has a subject and body
             * Add a post – it has the text of a body and is either added to a thread or replies to another post
             *
             */
            while (!options.equalsIgnoreCase("0")) {
                if (!options.equalsIgnoreCase("10"))
                    System.out.println("Please make your selections.");
                options = keyboard.nextLine();
                /**
                 * Display the forum via id after you login
                 *
                 */
                if (options.equalsIgnoreCase("1")) {
                    System.out.println("Please enter the id.");
                    String id = keyboard_id.nextLine();
                    Long l_id = Long.parseLong(id);
                    Posts posts_result = postsService.getPosts(l_id);
                    logger.info(posts_result.toString());
                    System.out.println("These are thread's content.");

                } else if (options.equalsIgnoreCase("2")) {
                    /**
                     * after login you can create the forum
                     *
                     */
                    Posts post_test = new Posts("This is test subject", "this is test body");
                    postsService.createPosts(post_test);
                    System.out.println("You created a thread.");


                } else if (options.equalsIgnoreCase("3")) {
                    /**
                     *  add discussion board content
                     *
                     */
                    DiscussionBoard discussionBoard = new DiscussionBoard("hw2");
                    try {
                        discussionBoard = discussionBoardRepository.save(new DiscussionBoard("how to finish this homework."));
                        logger.info("Insert data into discussion board database ");
                        return discussionBoard.getId() + " " + discussionBoard.getBody();
                    } catch (Exception e) {
                        logger.info(e.getMessage());
                    }
                    System.out.println("Added a post/discussion board.");
                } else if (options.equalsIgnoreCase("10")) {
                    System.out.println("Thanks " +
                            account.getOwner() + " see you!");
                    return account.toString();
                }
            }

            return account.toString();
        }

    }

}
