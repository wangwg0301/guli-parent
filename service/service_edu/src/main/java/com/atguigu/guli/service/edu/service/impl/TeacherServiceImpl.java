package com.atguigu.guli.service.edu.service.impl;

import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.entity.query.TeacherQuery;
import com.atguigu.guli.service.edu.mapper.TeacherMapper;
import com.atguigu.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author wxg
 * @since 2021-07-02
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public IPage<Teacher> selectPage(Long page, Long limit, TeacherQuery teacherQuery) {
        Page<Teacher> pageModel = new Page<>(page, limit);
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort","id");
       if (teacherQuery ==null){
           return baseMapper.selectPage(pageModel,queryWrapper);
       }
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String joinDateBegin = teacherQuery.getJoinDateBegin();
        String joinDateEnd = teacherQuery.getJoinDateEnd();

        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        if (level!=null){
            queryWrapper.eq("level",level);
        }
        queryWrapper.eq(!StringUtils.isEmpty(joinDateBegin),"join_date",joinDateBegin);
        queryWrapper.le(!StringUtils.isEmpty(joinDateEnd),"join_date",joinDateEnd);

        return baseMapper.selectPage(pageModel,queryWrapper);

    }
}
