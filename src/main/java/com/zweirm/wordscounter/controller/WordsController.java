package com.zweirm.wordscounter.controller;

import com.zweirm.wordscounter.domain.Words;
import com.zweirm.wordscounter.repository.WordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WordsController {
    @Autowired
    private WordsRepository wordsRepository;

    @PostMapping(value = "/save")
    public void saveWords(@RequestParam("article") String article) {
        // 按" "划分文章，存入数组
        String[] words = article.split(" ");

        // 创建对应长度的int数组保存HASH
        int[] hashWord = new int[words.length];

        // 遍历存储每个单词的HASH值
        for (int i = 0; i < words.length; i++) {
            hashWord[i] = words[i].hashCode();
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = i; j < words.length; j++) {
                if (hashWord[i] == hashWord[j]) {
                    // 计数器增加
                } else {
                    break;  // ???
                }
            }
        }
    }

    @PostMapping(value = "/count")
    public List<Words> wordsList() {
        return wordsRepository.countAll();
    }
}
