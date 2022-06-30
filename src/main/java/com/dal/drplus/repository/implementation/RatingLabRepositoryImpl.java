package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.RatingLab;
import com.dal.drplus.repository.interfaces.IRatingLabRepository;

import java.util.List;

public class RatingLabRepositoryImpl implements IRatingLabRepository {

    @Override
    public int saveLabRating(RatingLab ratingLab) {
        return 0;
    }

    @Override
    public int updateLabRating(RatingLab ratingLab) {
        return 0;
    }

    @Override
    public RatingLab findLabRatingById(String ratingId, String labId) {
        return null;
    }

    @Override
    public List<RatingLab> findLabRatingByLabId(String labId) {
        return null;
    }

    @Override
    public int deleteLabRatingById(String ratingId, String labId) {
        return 0;
    }

    @Override
    public List<RatingLab> findAll() {
        return null;
    }

    @Override
    public int deleteAll() {
        return 0;
    }
}
