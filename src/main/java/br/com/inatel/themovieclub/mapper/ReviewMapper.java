package br.com.inatel.themovieclub.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.inatel.themovieclub.controller.dto.ReviewDto;
import br.com.inatel.themovieclub.controller.form.ReviewForm;
import br.com.inatel.themovieclub.model.Review;

@Component
public class ReviewMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Review mapToReview(ReviewForm reviewRequest) {
        return modelMapper.map(reviewRequest, Review.class);
    }

    public void mapToReview(ReviewForm reviewRequest, Review review) {
        modelMapper.map(reviewRequest, review);
    }

    public ReviewDto mapToReviewResponse(Review review) {
        return modelMapper.map(review, ReviewDto.class);
    }

    public Page<ReviewDto> mapToReviewResponse(Page<Review> reviews) {
        return reviews.map(review -> mapToReviewResponse(review));
    }

}