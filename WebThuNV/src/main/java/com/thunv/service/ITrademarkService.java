package com.thunv.service;

import com.thunv.dto.TrademarkDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITrademarkService {

    List<TrademarkDTO> findAll();
    List<TrademarkDTO> findAll(Pageable pageable);
    TrademarkDTO findById(long id);
    TrademarkDTO save(TrademarkDTO model);
    void delete(long id);
    void delete(long[] ids);
    int totalItem();
}
