package br.com.inatel.themovieclub.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.inatel.themovieclub.controller.dto.ReviewReadResponse;
import br.com.inatel.themovieclub.controller.dto.ReviewSearchResponse;
import br.com.inatel.themovieclub.controller.form.ReviewRequest;
import br.com.inatel.themovieclub.model.Review;

@Component
public class ReviewMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Review mapToReview(ReviewRequest reviewRequest) {
        return modelMapper.map(reviewRequest, Review.class);
    }

    public void mapToReview(ReviewRequest reviewRequest, Review review) {
        modelMapper.map(reviewRequest, review);
    }

    public ReviewReadResponse mapToReviewReadResponse(Review review) {
        return modelMapper.map(review, ReviewReadResponse.class);
    }

    public Page<ReviewSearchResponse> mapToReviewSearchResponse(Page<Review> reviews) {
        return reviews.map(review -> modelMapper.map(review, ReviewSearchResponse.class));
    }

}
