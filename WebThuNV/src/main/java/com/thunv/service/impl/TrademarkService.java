package com.thunv.service.impl;

import com.thunv.converter.TrademarkConverter;
import com.thunv.dto.TrademarkDTO;
import com.thunv.entity.TrademarkEntity;
import com.thunv.exception.NotFoundException;
import com.thunv.repository.TrademarkRepository;
import com.thunv.service.ITrademarkService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
@Service
public class TrademarkService implements ITrademarkService {

    private final TrademarkRepository trademarkRepository;
    private final TrademarkConverter trademarkConverter;
    private final ProductService productService;

    public TrademarkService(TrademarkRepository trademarkRepository, TrademarkConverter trademarkConverter, ProductService productService) {
        this.trademarkRepository = trademarkRepository;
        this.trademarkConverter = trademarkConverter;
        this.productService = productService;
    }

    @Override
    public List<TrademarkDTO> findAll() {
        List<TrademarkDTO> results = new ArrayList<>();
        List<TrademarkEntity> entities = trademarkRepository.findAll();
        for (TrademarkEntity entity : entities){
            TrademarkDTO trademarkDTO = trademarkConverter.toDTO(entity);
            results.add(trademarkDTO);
        }
        return results;
    }

    @Override
    public List<TrademarkDTO> findAll(Pageable pageable) {
        List<TrademarkDTO> results = new ArrayList<>();
        List<TrademarkEntity> entities = trademarkRepository.findAll(pageable).getContent();
        for (TrademarkEntity entity : entities){
            TrademarkDTO trademarkDTO = trademarkConverter.toDTO(entity);
            results.add(trademarkDTO);
        }
        return results;
    }

    @Override
    public TrademarkDTO findById(long id) {
        TrademarkEntity entity = trademarkRepository.findById(id).orElseThrow(()->new NotFoundException("Không tìm thấy ID: "+ id));
        return trademarkConverter.toDTO(entity);
    }

    @Override
    @Transactional
    public TrademarkDTO save(TrademarkDTO model) {
        TrademarkEntity entity;
        if (model.getId() != null){
            TrademarkEntity odlTrademark = trademarkRepository.getOne(model.getId());
            entity = trademarkConverter.toEntity(odlTrademark, model);
        }else{
            entity = trademarkConverter.toEntity(model);
        }
        entity = trademarkRepository.save(entity);
        return trademarkConverter.toDTO(entity);
    }

    @Override
    @Transactional
    public void delete(long id) {
        TrademarkEntity entity = trademarkRepository.getOne(id);
        if(!StringUtils.isEmpty(entity)){
            entity.getProducts().forEach(product->productService.delete(product.getId()));
            trademarkRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (long id : ids){
            TrademarkEntity entity = trademarkRepository.getOne(id);
            if(!StringUtils.isEmpty(entity)){
                entity.getProducts().forEach(product->productService.delete(product.getId()));
                trademarkRepository.deleteById(id);
            }
        }
    }

    @Override
    public int totalItem() {
        return (int) trademarkRepository.count();
    }
}
