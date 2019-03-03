package com.qinyadong.shopping.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
import org.springframework.stereotype.Component;

/**
 * @Author: Yadong Qin
 * @Date: 2019/3/3
 */
@ElasticSimpleJob(
        cron = "0/5 * * * * ?",
        jobName = "itemImportJob",
        jobParameter = "helloworld",
        shardingItemParameters = "0=A,1=B,3=C,4=D",
        shardingTotalCount = 4
)
@Component
public class ItemImportJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("任务名称："+shardingContext.getJobName());
        System.out.println("线程名称："+Thread.currentThread().getName());
        System.out.println("总的分片数"+shardingContext.getShardingTotalCount());
        System.out.println("分片参数："+shardingContext.getShardingParameter());
        System.out.println("任务参数："+shardingContext.getJobParameter());
    }
}
