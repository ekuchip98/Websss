package com.thunv.controller;

import com.thunv.controller.output.TrademarkOutput;
import com.thunv.dto.TrademarkDTO;
import com.thunv.service.impl.TrademarkService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/trademarks")
public class TrademarkAPI {

    private final TrademarkService trademarkService;

    public TrademarkAPI(TrademarkService trademarkService) {
        this.trademarkService = trademarkService;
    }
    
    @GetMapping
    public ResponseEntity<TrademarkOutput> showTrademarks(@RequestParam(value = "page", required = false) Integer page,
                                                          @RequestParam(value = "limit", required = false) Integer limit){
        TrademarkOutput result = new TrademarkOutput();
        if (page != null && limit != null){
            result.setPage(page);
            Pageable pageable = PageRequest.of(page - 1, limit);
            result.setTotalPage((int) Math.ceil((double) (trademarkService.totalItem()) / limit));
            result.setListResult(trademarkService.findAll(pageable));
        }else {
            result.setListResult(trademarkService.findAll());
        }
        if (StringUtils.isEmpty(result)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TrademarkDTO> getTrademark(@PathVariable long id){
        TrademarkDTO trademarkDTO = trademarkService.findById(id);
        return new ResponseEntity<>(trademarkDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TrademarkDTO> createTrademark(@RequestBody TrademarkDTO model){
        TrademarkDTO trademarkDTO = trademarkService.save(model);
        return new ResponseEntity<>(trademarkDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TrademarkDTO> updateTrademark(@RequestBody TrademarkDTO model, @PathVariable long id){
        model.setId(id);
        TrademarkDTO trademarkDTO = trademarkService.save(model);
        return new ResponseEntity<>(trademarkDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteTrademark(@PathVariable long id){
        trademarkService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteTrademarks(@RequestBody long[] ids){
        trademarkService.delete(ids);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
