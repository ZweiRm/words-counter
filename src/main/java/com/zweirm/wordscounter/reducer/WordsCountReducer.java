package com.zweirm.wordscounter.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class WordsCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        // 获取单词(v3)
        String word = key.toString();

        // 遍历values次数组成的集合(v3s)
        Iterator<IntWritable> iterator = values.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            // 数量累积
            count += iterator.next().get();
        }

        // 输出k4 v4
        context.write(new Text(word), new IntWritable(count));
    }
}
