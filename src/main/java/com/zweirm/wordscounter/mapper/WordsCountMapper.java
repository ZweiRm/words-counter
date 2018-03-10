package com.zweirm.wordscounter.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordsCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 获取行内容
        String line = value.toString();

        // 切分单词
        String[] words = line.split(" ");

        // 发送k2 v2
        for (String word : words) {
            context.write(new Text(word), new IntWritable(1));
        }
    }
}
