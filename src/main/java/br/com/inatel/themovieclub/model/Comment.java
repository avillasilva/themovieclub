package br.com.inatel.themovieclub.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    private Review review;

    @ManyToOne
    private User author;

    @NotBlank
    private String comment;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Comment(User author, String comment, Review review) {
        this.author = author;
        this.comment = comment;
        this.review = review;
        this.createdAt = LocalDateTime.now();
    }

}
