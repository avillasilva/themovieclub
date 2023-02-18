package br.com.inatel.themovieclub.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.inatel.themovieclub.controller.dto.CommentDto;
import br.com.inatel.themovieclub.controller.form.CommentForm;
import br.com.inatel.themovieclub.model.Comment;

@Component
public class CommentMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Comment mapToComment(CommentForm commentRequset) {
        return modelMapper.map(commentRequset, Comment.class);
    }

    public void mapToComment(CommentForm commentRequest, Comment comment) {
        modelMapper.map(commentRequest, comment);
    }

    public CommentDto mapToCommentResponse(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }

    public Page<CommentDto> mapToCommentResponse(Page<Comment> comments) {
        return comments.map(comment -> mapToCommentResponse(comment));
    }

}
