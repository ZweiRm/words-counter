package com.zweirm.wordscounter.service;

import com.zweirm.wordscounter.controller.WordsController;
import com.zweirm.wordscounter.mapper.WordsCountMapper;
import com.zweirm.wordscounter.reducer.WordsCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WordsService {
    public void driver() throws IOException, ClassNotFoundException, InterruptedException {
        // 创建作业
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        // 指定作业入口
        job.setJarByClass(WordsController.class);

        // 指定Mapper相关参数
        job.setMapperClass(WordsCountMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 指定Reducer相关参数
        job.setReducerClass(WordsCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 指定输入输出位置
        FileInputFormat.setInputPaths(job, new Path("/"));
        FileOutputFormat.setOutputPath(job, new Path("result"));

        // 启动作业
        job.waitForCompletion(true);
    }
}
