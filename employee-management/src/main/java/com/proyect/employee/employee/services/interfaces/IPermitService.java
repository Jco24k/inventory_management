package com.proyect.employee.employee.services.interfaces;

import com.proyect.employee.employee.dtos.update.UpdatePermitDto;
import com.proyect.employee.employee.entities.Permission;
import java.util.Collection;

public interface IPermitService {

    Collection<Permission> findAll();
    Permission findOne(Long id);
    Permission update(UpdatePermitDto roleDto, Long id) ;
}
