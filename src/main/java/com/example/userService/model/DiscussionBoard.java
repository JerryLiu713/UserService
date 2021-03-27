package com.example.userService.model;

import javax.persistence.*;

/**
 * Simple pojo class for discussion board.
 *
 */
@Entity
@Table(name = "contents")
public class DiscussionBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "body")
    private String body;

    public DiscussionBoard(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DiscussionBoard{" +
                "id=" + id +
                ", body='" + body + '\'' +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public DiscussionBoard() {

    }

}
