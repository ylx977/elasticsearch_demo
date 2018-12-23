package com.fuzamei.controller;

import com.fuzamei.entity.BankInfo;
import com.fuzamei.entity.GoodInfo;
import com.fuzamei.repository.BankRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ylx on 2018/12/22.
 */
@Slf4j
@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankRepository bankRepository;

    //每页数量
    private Integer PAGESIZE = 10;

    //http://localhost:8888/getGoodsList?query=商品
    //http://localhost:8888/getGoodsList?query=商品&pageNumber=1
    //根据关键字"商品"去查询列表，name或者description包含的都查询
    @GetMapping("/getBankList")
    public List<BankInfo> getList(Integer pageNumber, String query, String query2){
        if(pageNumber == null){
            pageNumber = 0;
        }

        // 设置分页
        Pageable pageable = PageRequest.of(pageNumber,PAGESIZE);

        QueryBuilder queryBuilder = QueryBuilders
                .boolQuery()
                .must(QueryBuilders.matchPhraseQuery("address",query))
                .must(QueryBuilders.matchPhraseQuery("address",query2));

        Page<BankInfo> goodsPage = bankRepository.search(queryBuilder, pageable);

        return goodsPage.getContent();
    }

}
