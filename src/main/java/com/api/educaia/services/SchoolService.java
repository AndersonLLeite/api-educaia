package com.api.educaia.services;

import com.api.educaia.models.SchoolModel;

import java.util.Optional;
import java.util.UUID;

public interface SchoolService {
    SchoolModel createSchool(SchoolModel schoolModel);

    Optional<SchoolModel> getSchoolById(UUID schoolId);
}
