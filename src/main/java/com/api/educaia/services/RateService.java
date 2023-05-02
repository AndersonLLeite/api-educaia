package com.api.educaia.services;

import com.api.educaia.models.RateModel;
import com.api.educaia.repositories.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface RateService {
    public void createRateModel(UUID taskID);
    public void updateRateModel(RateModel rateModel, List<Integer> rateAnswers);
    Optional<RateModel> getRateResponseByTaskId(UUID taskId);
    void saveRateResponse(RateModel rateResponse);

}
