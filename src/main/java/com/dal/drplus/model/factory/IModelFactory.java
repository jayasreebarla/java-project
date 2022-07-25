package com.dal.drplus.model.factory;

import com.dal.drplus.model.IBuilder.IDoctorBuilder;
import com.dal.drplus.model.IEntity.IDoctor;

public interface IModelFactory {
    public IDoctor createDoctor();
    public IDoctor createDoctorUsingBuilder(IDoctorBuilder builder);

    public IDoctorBuilder createDoctorBuilder();
}
