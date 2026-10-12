package com.sky.service.impl;

import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired  //Spring 在做自动装配时，只处理那些已经被注册为 Bean 的类里的 @Autowired 成员
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Override
    @Transactional  // 需要在启动类加入开启注解启动事务的注解
    public void saveWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        //插入一条菜品
        dishMapper.insert(dish);
        // 获取Dish的insert的时候得到的id
        Long id = dish.getId();
        //增加口味
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && flavors.size()>0){

            flavors.forEach(flavor -> {
                flavor.setDishId(id);
            });

            dishFlavorMapper.insertBatch(flavors);
        }
    }
}
