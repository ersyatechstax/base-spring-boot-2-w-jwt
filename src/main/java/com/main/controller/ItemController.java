package com.main.controller;

import com.main.services.ItemService;
import com.main.vo.ItemVO;
import com.main.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping("/create")
    public ResponseEntity<ResponseVO> createItem(@Valid @RequestBody ItemVO itemVO, Errors errors){
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return itemService.createItem(itemVO);
            }
        };
        return handler.getResultWithValidation(errors);
    }
}
