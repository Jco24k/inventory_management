package com.purchase.management.services;

import com.purchase.management.dtos.base.BaseInventoryIncomeHeaderDto;
import com.purchase.management.dtos.create.CreateInventoryIncomeHeaderDto;
import com.purchase.management.dtos.update.UpdateInventoryIncomeHeaderDto;
import com.purchase.management.entities.InventoryIncomeHeader;
import com.purchase.management.entities.Warehouse;
import com.purchase.management.entities.composite.InventoryIncomeDetail;
import com.purchase.management.entities.enums.EIncomeType;
import com.purchase.management.exception.ResourceNotFoundException;
import com.purchase.management.feignclients.ProductManagementFeignClient;
import com.purchase.management.feignclients.UserRestTemplate;
import com.purchase.management.mappers.MapperNotNull;
import com.purchase.management.models.ProviderModel;
import com.purchase.management.models.UserModel;
import com.purchase.management.repositories.InventoryIncomeHeaderRepository;
import com.purchase.management.services.interfaces.IInventoryIncomeDetailService;
import com.purchase.management.services.interfaces.IInventoryIncomeHeaderService;
import com.purchase.management.services.interfaces.IWarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InventoryIncomeHeaderService implements IInventoryIncomeHeaderService {

    private final InventoryIncomeHeaderRepository repository;
    private final ProductManagementFeignClient productManagementService;
    private final IWarehouseService warehouseService;
    private final IInventoryIncomeDetailService inventoryIncomeDetailService;
    private final UserRestTemplate userService;

    public InventoryIncomeHeaderService(InventoryIncomeHeaderRepository repository,
                                        UserRestTemplate userService,
                                        IWarehouseService warehouseService,
                                        @Lazy ProductManagementFeignClient productManagementService,
                                        @Lazy IInventoryIncomeDetailService inventoryIncomeDetailService) {
        this.repository = repository;
        this.userService = userService;
        this.warehouseService = warehouseService;
        this.productManagementService = productManagementService;
        this.inventoryIncomeDetailService = inventoryIncomeDetailService;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<InventoryIncomeHeader> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public InventoryIncomeHeader findOne(Long id){
        return repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("InventoryIncomeHeader not found with id: " + id)
        );
    }

    @Override
    @Transactional()
    public InventoryIncomeHeader create(CreateInventoryIncomeHeaderDto requestDto) {
        validateTypeIncomeInventory(requestDto.getType(), requestDto.getPurchaseOrderId());
        InventoryIncomeHeader newData = new InventoryIncomeHeader();
        getAndVerifyDto(requestDto,newData,true);
        return repository.save(newData);
    }

    @Override
    @Transactional()
    public InventoryIncomeHeader update(UpdateInventoryIncomeHeaderDto requestDto, Long id) {
        validateTypeIncomeInventory(requestDto.getType(), requestDto.getPurchaseOrderId());
        InventoryIncomeHeader dataFound = findOne(id);
        getAndVerifyDto(requestDto,dataFound,false);
        return repository.save(dataFound);
    }

    @Override
    public void delete(Long id) {
        InventoryIncomeHeader dataFound = findOne(id);
        dataFound.setIsActive(false);
        repository.save(dataFound);
    }

    private ModelMapper modelMapperWithoutFks() {
        ModelMapper modelMapper = MapperNotNull.notNullMapper();
         PropertyMap<CreateInventoryIncomeHeaderDto, InventoryIncomeHeader> propertyMap = new
                 PropertyMap<>() {
                     protected void configure() {
                         skip().setInventoryIncomeDetails(null);
                         skip().setProviderId(null);
                         skip().setUserId(null);
                         skip().setWarehouse(null);
                     }
                 };
        modelMapper.addMappings(propertyMap);
        return modelMapper;
    }

    @Override
    public void getAndVerifyDto(BaseInventoryIncomeHeaderDto requestDto, InventoryIncomeHeader entity, Boolean option){
        modelMapperWithoutFks().map(requestDto, entity);
        if(requestDto.getProviderId()!=null){
            ProviderModel providerModel = productManagementService.findOneProvider(requestDto.getProviderId()) ;
            entity.setProviderId(providerModel.getId());
        }
        if(requestDto.getWarehouseId()!=null){
            Warehouse warehouse = warehouseService.findOne(requestDto.getWarehouseId());
            entity.setWarehouse(warehouse);
        }
        if(requestDto.getUserId()!=null) {
            UserModel userModel = userService.findOne(requestDto.getUserId());
            entity.setUserId(userModel.getId());
        }
        if(option){
            CreateInventoryIncomeHeaderDto request = (CreateInventoryIncomeHeaderDto) requestDto;
            if(request.getInventoryIncomeDetailDtos()!=null){
                entity.getInventoryIncomeDetails().clear();
                Set<InventoryIncomeDetail> inventoryIncomeDetails =
                        request.getInventoryIncomeDetailDtos().stream().map(
                                inventoryDetail -> inventoryIncomeDetailService.getAndVerifyDto(inventoryDetail,
                                        new InventoryIncomeDetail(),entity)
                        ).collect(Collectors.toSet());
                entity.setInventoryIncomeDetails(inventoryIncomeDetails);
            }
        }

    }

    void validateTypeIncomeInventory(EIncomeType type,Long purchaseOrderId){
        if (Objects.requireNonNull(type) == EIncomeType.PURCHASE) {
            if (purchaseOrderId == null) {
                throw new IllegalArgumentException("PurchaseOrderId must be not null for PURCHASE type");
            }
        } else {
            if (purchaseOrderId != null) {
                throw new IllegalArgumentException("PurchaseOrderId must be null for non-PURCHASE type");
            }
        }
    }
}
