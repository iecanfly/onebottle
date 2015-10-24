package com.gooki.service.impl;

import com.gooki.dao.ItemDAO;
import com.gooki.model.Item;
import com.gooki.service.ItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by iecanfly on 5/16/14.
 */
@Service("itemManager")
public class ItemManagerImpl extends GenericManagerImpl<Item, Long> implements ItemManager {
    ItemDAO itemDAO;

    @Autowired
    public void setItemDAO(ItemDAO itemDAO) {
        this.dao = itemDAO;
        this.itemDAO = itemDAO;
    }
}
