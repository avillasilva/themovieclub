package br.com.inatel.themovieclub.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.inatel.themovieclub.controller.dto.CommentReadResponse;
import br.com.inatel.themovieclub.controller.form.CommentRequest;
import br.com.inatel.themovieclub.model.Comment;

@Component
public class CommentMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Comment mapToComment(CommentRequest commentRequset) {
        return modelMapper.map(commentRequset, Comment.class);
    }

    public void mapToComment(CommentRequest commentRequest, Comment comment) {
        modelMapper.map(commentRequest, comment);
    }

    public CommentReadResponse mapToCommentResponse(Comment comment) {
        return modelMapper.map(comment, CommentReadResponse.class);
    }

    public Page<CommentReadResponse> mapToCommentResponse(Page<Comment> comments) {
        return comments.map(comment -> mapToCommentResponse(comment));
    }

}
