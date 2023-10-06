package com.purchase.management.services;

import com.purchase.management.dtos.create.CreatePurchaseOrderHeaderDto;
import com.purchase.management.dtos.update.UpdatePurchaseOrderHeaderDto;
import com.purchase.management.entities.PurchaseOrderHeader;
import com.purchase.management.entities.composite.PurchaseOrderDetail;
import com.purchase.management.exception.ResourceNotFoundException;
import com.purchase.management.feignclients.ProductManagementFeignClient;
import com.purchase.management.feignclients.UserRestTemplate;
import com.purchase.management.mappers.MapperNotNull;
import com.purchase.management.models.ProviderModel;
import com.purchase.management.models.UserModel;
import com.purchase.management.repositories.PurchaseOrderHeaderRepository;
import com.purchase.management.services.interfaces.IPurchaseOrderDetailService;
import com.purchase.management.services.interfaces.IPurchaseOrderHeaderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PurchaseOrderHeaderService implements IPurchaseOrderHeaderService {

    private final PurchaseOrderHeaderRepository repository;
    private final ProductManagementFeignClient productManagementService;
    private final IPurchaseOrderDetailService purchaseOrderDetailService;
    private final UserRestTemplate userService;

    public PurchaseOrderHeaderService(PurchaseOrderHeaderRepository repository,
                                      UserRestTemplate userService,
                                      @Lazy ProductManagementFeignClient productManagementService,
                                      @Lazy IPurchaseOrderDetailService purchaseOrderDetailService) {
        this.repository = repository;
        this.userService = userService;
        this.productManagementService = productManagementService;
        this.purchaseOrderDetailService = purchaseOrderDetailService;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PurchaseOrderHeader> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseOrderHeader findOne(Long id){
        return repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("PurchaseOrderHeader not found with id: " + id)
        );
    }

    @Override
    @Transactional()
    public PurchaseOrderHeader create(CreatePurchaseOrderHeaderDto requestDto) {
        PurchaseOrderHeader newData = new PurchaseOrderHeader();
        getAndVerifyDto(requestDto,newData);
        return repository.save(newData);
    }

    @Override
    @Transactional()
    public PurchaseOrderHeader update(UpdatePurchaseOrderHeaderDto requestDto, Long id) {
        PurchaseOrderHeader dataFound = findOne(id);
        modelMapperWithoutFks().map(requestDto,dataFound);
        return repository.save(dataFound);
    }

    @Override
    public void delete(Long id) {
        PurchaseOrderHeader dataFound = findOne(id);
        dataFound.setIsActive(false);
        repository.save(dataFound);
    }

    private ModelMapper modelMapperWithoutFks() {
        ModelMapper modelMapper = MapperNotNull.notNullMapper();
         PropertyMap<CreatePurchaseOrderHeaderDto, PurchaseOrderHeader> propertyMap = new
                 PropertyMap<>() {
                     protected void configure() {
                         skip().setPurchaseOrderDetails(null);
                         skip().setProviderId(null);
                         skip().setUserId(null);
                     }
                 };
        modelMapper.addMappings(propertyMap);
        return modelMapper;
    }

    @Override
    public void getAndVerifyDto(CreatePurchaseOrderHeaderDto requestDto,PurchaseOrderHeader entity){
        modelMapperWithoutFks().map(requestDto, entity);
        if(requestDto.getProviderId()!=null){
            ProviderModel providerModel = productManagementService.findOneProvider(requestDto.getProviderId()) ;
            entity.setProviderId(providerModel.getId());
        }
        if(requestDto.getUserId()!=null) {
            UserModel userModel = userService.findOne(requestDto.getUserId());
            entity.setUserId(userModel.getId());
        }
        if(requestDto.getPurchaseDetailDtos()!=null){
            entity.getPurchaseOrderDetails().clear();
            Set<PurchaseOrderDetail> purchaseOrderDetails =
                    requestDto.getPurchaseDetailDtos().stream().map(
                            purchaseDetail -> purchaseOrderDetailService.getAndVerifyDto(purchaseDetail,
                                    new PurchaseOrderDetail(),entity)
                    ).collect(Collectors.toSet());
            entity.setPurchaseOrderDetails(purchaseOrderDetails);
        }
    }
}
