package br.com.inatel.themovieclub.controller.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private Long id;
    private String authorName;
    private String content;
    private OffsetDateTime createdAt;

}
