package com.fuzamei.controller;

import com.fuzamei.entity.GoodInfo;
import com.fuzamei.repository.GoodsRepository;
import com.fuzamei.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author ylx
 * Created by ylx on 2018/12/20.
 */
@Slf4j
@RestController
@RequestMapping("/escrud")
public class GoodsController {

    @Autowired
    private GoodsRepository goodsRepository;

    @GetMapping("/save")
    public String save(){
        GoodInfo goodInfo = GoodInfo.builder()
                .id(System.currentTimeMillis())
                .name("商品" + System.currentTimeMillis())
                .description("这是一个测试商品")
                .build();
        GoodInfo save = goodsRepository.save(goodInfo);
        log.info(save.toString());
        return "success";
    }

    //http://localhost:8888/delete?id=123456789
    @GetMapping("delete")
    public String delete(long id){
        goodsRepository.deleteById(id);
        return "success";
    }


    //http://localhost:8888/update?id=123456789&name=haha&description=hahaha
    @GetMapping("/update")
    public String update(long id,String name,String description){
        GoodInfo goodInfo = GoodInfo.builder()
                .id(id)
                .name(name)
                .description(description)
                .build();
        GoodInfo save = goodsRepository.save(goodInfo);
        log.info(save.toString());
        return "success";
    }

    //http://localhost:8888/getOne?id=123456778
    @GetMapping("/getOne")
    private String getOne(long id){
        Optional<GoodInfo> result = goodsRepository.findById(id);
        return result.isPresent() ? result.get().toString() : "fail";
//        return result.orElse("fail").toString();
    }


    //每页数量
    private Integer PAGESIZE = 10;


    //http://localhost:8888/getGoodsList?query=商品
    //http://localhost:8888/getGoodsList?query=商品&pageNumber=1
    //根据关键字"商品"去查询列表，name或者description包含的都查询
    @GetMapping("/getGoodsList")
    public List<GoodInfo> getList(Integer pageNumber,String query){
        if(pageNumber == null){
            pageNumber = 0;
        }

        // 设置分页
        Pageable pageable = PageRequest.of(pageNumber,PAGESIZE);

        QueryBuilder queryBuilder = QueryBuilders
                .boolQuery()
                .should(QueryBuilders.matchQuery("name",query))
                .should(QueryBuilders.matchQuery("description",query));

        Page<GoodInfo> goodsPage = goodsRepository.search(queryBuilder, pageable);

        return goodsPage.getContent();
    }



}
