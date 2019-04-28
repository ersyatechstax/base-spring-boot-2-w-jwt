package com.main.controller;

import com.main.enums.StatusCode;
import com.main.exception.GeneralException;
import com.main.util.RestUtil;
import com.main.vo.ResponsePageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * created by ersya 30/03/2019
 */
@Slf4j
@Component
public abstract class AbstractPageRequestHandler {

    /**
     * page service injector
     * @return page object from service
     */
    public abstract Page processRequest();

    /**
     * get result page
     * @return @ResponseEntity
     */
    public ResponseEntity<ResponsePageVO> constructPageResult(){
        ResponsePageVO responsePageVO = new ResponsePageVO();
        try {
            Page page = processRequest();
            if(page.getTotalElements() == 0){
                responsePageVO.setTotalElements(0);
                responsePageVO.setPage(0);
                responsePageVO.setTotalPages(0);
                responsePageVO.setMessage(StatusCode.OK.name());
                responsePageVO.setResult(page.getContent());
            }
            else{
                responsePageVO.setTotalElements(page.getTotalElements());
                responsePageVO.setPage(page.getNumber());
                responsePageVO.setTotalPages(page.getTotalPages());
                responsePageVO.setMessage(StatusCode.OK.name());
                responsePageVO.setResult(page.getContent());
            }
        }catch (GeneralException e){
            responsePageVO.setMessage(e.getCode().name());
            responsePageVO.setResult(e.getMessage());
            log.error("ERROR: "+e.getMessage());
        }

        return RestUtil.getJsonResponse(responsePageVO);
    }
}
