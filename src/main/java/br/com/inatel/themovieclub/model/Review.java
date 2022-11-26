package br.com.inatel.themovieclub.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User author;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Comment> comments = new ArrayList<>();

    private String title;
    private String content;

    public Review() {
    }

    public Review(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.author = user;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void deleteComment(Long id) {
        for (Comment c : comments) {
            if (c.getId() == id) {
                comments.remove(c);
                return;
            }
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, comments, content, id, title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Review other = (Review) obj;
        return Objects.equals(author, other.author) && Objects.equals(comments, other.comments) && Objects.equals(content, other.content)
                && Objects.equals(id, other.id) && Objects.equals(title, other.title);
    }

}
