package com.api.educaia.services;

import com.api.educaia.models.RateModel;
import com.api.educaia.repositories.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RateServiceImpl implements RateService{
    @Autowired
    private RateRepository rateRepository;

    @Transactional
    @Override
    public void createRateModel(UUID taskID) {
        RateModel rateModel = new RateModel(taskID);
        rateRepository.save(rateModel);
    }

    @Override
    public void updateRateModel(RateModel rateModel, List<Integer> rateAnswers) {
        rateModel.setRateQuestionsAddVotes(rateAnswers);
        rateRepository.save(rateModel);
    }

    @Override
    public Optional<RateModel> getRateResponseByTaskId(UUID taskId) {
        return rateRepository.findByTaskId(taskId);
    }

    @Override
    public void saveRateResponse(RateModel rateResponse) {
        rateRepository.save(rateResponse);
    }
}
