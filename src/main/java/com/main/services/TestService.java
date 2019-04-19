package com.main.services;

import com.main.exception.GeneralException;
import com.main.mapper.TestMapper;
import com.main.mapper.converter.TestVOConverter;
import com.main.persistence.domain.Base;
import com.main.persistence.domain.Test;
import com.main.persistence.repository.TestRepository;
import com.main.util.PageBuilder;
import com.main.vo.BaseVO;
import com.main.vo.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * created by ersya 30/03/2019
 */
@Service
public class TestService {
    @Autowired
    TestRepository testRepository;

    @Autowired
    TestVOConverter testVOConverter;

    public Page<Test> findAll(Integer page, Integer limit, String sortBy, String sortDirection){
        Pageable pageable = PageBuilder.buildPage(page,limit,sortBy, sortDirection);
        Page<Test> tests = testRepository.findByCreatedDateBefore(new Date(),pageable);
        return tests;
    }

    public TestVO getDetail(String id){
        Test test = testRepository.findBySecureId(id).orElseThrow(() -> new GeneralException("test not found with that id"));
        return testVOConverter.transferModelToVO(test);
    }

    public BaseVO create(TestVO testVO){
        Test addTest = testRepository.save(TestMapper.INSTANCE.VOToModel(testVO));
        BaseVO response = new BaseVO();
        response.setId(addTest.getSecureId());
        response.setVersion(addTest.getVersion());
        return response;
    }
}
