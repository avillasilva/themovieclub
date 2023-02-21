package br.com.inatel.themovieclub.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

<<<<<<< HEAD
import br.com.inatel.themovieclub.controller.dto.CommentReadResponse;
import br.com.inatel.themovieclub.controller.form.CommentRequest;
=======
import br.com.inatel.themovieclub.controller.dto.CommentDto;
import br.com.inatel.themovieclub.controller.form.CommentForm;
>>>>>>> ff7f2db208f1ed0e7e3e0bcdb0e2286e37711af5
import br.com.inatel.themovieclub.model.Comment;

@Component
public class CommentMapper {

    @Autowired
    private ModelMapper modelMapper;

<<<<<<< HEAD
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
=======
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
>>>>>>> ff7f2db208f1ed0e7e3e0bcdb0e2286e37711af5
        return comments.map(comment -> mapToCommentResponse(comment));
    }

}
