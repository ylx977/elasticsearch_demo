package com.fuzamei.repository;

import com.fuzamei.entity.GoodInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ylx
 * Created by ylx on 2018/12/20.
 */
@Repository
public interface GoodsRepository extends ElasticsearchRepository<GoodInfo,Long> {
}
