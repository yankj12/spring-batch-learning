package com.yan.spring.batch.learning.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yan.spring.batch.learning.listener.job.MySampleListener;
import com.yan.spring.batch.learning.listener.step.MyChunkListener;
import com.yan.spring.batch.learning.reader.MyAdapterService;
import com.yan.spring.batch.learning.reader.PrepareDataTasklet;
import com.yan.spring.batch.learning.schema.User;
import com.yan.spring.batch.learning.writer.MyWriterAdapterService;

// tag::setup[]
@Configuration
@EnableBatchProcessing
public class BatchUserScoreConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
	private MySampleListener mySampleListener; //简单的JOB listener
    
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    // end::setup[]

    @Autowired
    public PrepareDataTasklet prepareDataTasklet;
    
    @Autowired
    public MyAdapterService myAdapterService;
    
    @Autowired
    public MyWriterAdapterService myWriterAdapterService;

    @Autowired
    public MyChunkListener myChunkListener;
    
    @Bean
    public Job importUserJob() {
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .listener(mySampleListener)
            .start(prepareData()) //step
            .next(synchData()) //step
            .build();  //构建
    }

    
	 /*
	  * 2.1 step：获取数据
	  */
	@Bean
	public Step prepareData() {
		return this.stepBuilderFactory.get("prepareData")
               .tasklet(prepareDataTasklet)
               .build();
	}
    
	/*
	  * 2.2 Step： 同步数据
	  */
	@Bean
	public Step synchData() {
		return this.stepBuilderFactory.get("synchData")
               .<User, User>chunk(500) // 交事务之前要处理的项目数，达到300时，汇总项目列表将传递到ItemWriter，事务将被提交。
               .reader(itemReader()) // 读取器：这里复用系统的现有服务，并适配reader
               .writer(myWriterAdapterService)  // 写入器： 这里写入数据库
               .allowStartIfComplete(true) // 完成允许重启
               //.startLimit(1) // 只能运行一次。尝试再次运行它 会引发StartLimitExceededException
               .listener(myChunkListener) // Step监听器，此处为通用的Step执行侦听器 ： 无论它是正常结束还是失败， Step开始之前和结束之后进行通知。
               .build(); // 构建
	}
    
 
	/*
	 * 3.读取器，采用重用现有service功能，即ItemReaderAdapter（也可以类似于writer写法自定义）
	 */
	@StepScope
	@Bean
	public ItemReaderAdapter<User> itemReader() {
		ItemReaderAdapter<User> reader = new ItemReaderAdapter<>();

        reader.setTargetObject(myAdapterService);//适配器
        reader.setTargetMethod("nextUser");//适配reader
        
        return reader;
	}
	
}
