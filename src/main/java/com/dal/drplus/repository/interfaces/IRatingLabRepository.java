package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.RatingLab;

import java.util.List;

public interface IRatingLabRepository {
    int saveLabRating(RatingLab ratingLab);
    int updateLabRating(RatingLab ratingLab);
    RatingLab findLabRatingById(String ratingId,String labId);
    List<RatingLab> findLabRatingByLabId(String labId);
    int deleteLabRatingById(String ratingId,String labId);
    List<RatingLab> findAll();
    int deleteAll();
}
