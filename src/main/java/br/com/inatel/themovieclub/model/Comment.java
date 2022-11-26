package br.com.inatel.themovieclub.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Review review;

    @ManyToOne
    private User author;

    private String comment;
    private LocalDateTime createdAt;

    public Comment() {
    }

    public Comment(User author, String comment, Review review) {
        this.author = author;
        this.comment = comment;
        this.review = review;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public User getAuthor() {
        return author;
    }

    public void setComment(String content) {
        this.comment = content;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, comment, createdAt, id, review);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Comment other = (Comment) obj;
        return Objects.equals(author, other.author) && Objects.equals(comment, other.comment) && Objects.equals(createdAt, other.createdAt)
                && Objects.equals(id, other.id) && Objects.equals(review, other.review);
    }

}
