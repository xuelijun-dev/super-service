package com.xue.crawler.service.impl;

import com.xue.crawler.dao.ItemDao;
import com.xue.crawler.pojo.Item;
import com.xue.crawler.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;

    @Override
    @Transactional
    public Item save(Item item) {
        return itemDao.save(item);
    }

    @Override
    public List<Item> findAll(Item item) {
        Example<Item> example = Example.of(item);
        return itemDao.findAll(example);
    }
}
