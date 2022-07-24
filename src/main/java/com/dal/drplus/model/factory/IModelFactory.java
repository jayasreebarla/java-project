package com.dal.drplus.model.factory;

import com.dal.drplus.model.Builder.DoctorBuilder;
import com.dal.drplus.model.IEntity.IDoctor;

public interface IModelFactory {
    public IDoctor createDoctor();
    public IDoctor createDoctorBuilder(DoctorBuilder builder);
}
