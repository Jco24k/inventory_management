package com.proyect.employee.employee.services;

import com.proyect.employee.employee.dtos.update.UpdatePermitDto;
import com.proyect.employee.employee.entities.Permission;
import com.proyect.employee.employee.exception.ResourceNotFoundException;
import com.proyect.employee.employee.repositories.PermitRepository;
import com.proyect.employee.employee.services.interfaces.IPermitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Collection;
import java.util.Set;

@Service
public class PermissionService implements IPermitService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PermitRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Collection<Permission> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Permission findOne(Long id) {
        return repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Permit not found with id: " + id)
        );
    }


    @Override
    @Transactional()
    public Permission update(UpdatePermitDto permitDto, Long id) {
        Permission permissionFound = findOne(id);
        modelMapper.map(permitDto, permissionFound);
        return repository.save(permissionFound);
    }

    public Set<Permission> getPermits(Set<Long> permitIds)  {
        Set<Permission> permissions = repository.findByIdIn(permitIds);
        if (permissions.size() != permitIds.size()) {
            List<Long> notFoundPermits = permitIds.stream()
                    .filter(permitId -> permissions.stream().noneMatch(pert -> pert.getId().equals(permitId)))
                    .toList();
            throw new ResourceNotFoundException("Some Permits were not found"+notFoundPermits);
        }
        return permissions;
    }

}
