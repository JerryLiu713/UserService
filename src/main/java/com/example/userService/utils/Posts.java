package com.example.userService.utils;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Simple pojo class for posts database
 *
 */
@JsonRootName("Posts")
public class Posts {
    protected Long id;
    protected String subject;
    protected String body;

    public Posts(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public Posts() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
