package com.atguigu.guli.service.edu.controller.admin;


import com.atguigu.guli.service.base.result.R;
import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.entity.query.TeacherQuery;
import com.atguigu.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师后台 .前端控制器
 * </p>
 *
 * @author wxg
 * @since 2021-07-02
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
@Slf4j
public class TeacherController {

@Autowired
    private TeacherService teacherService;
@ApiOperation("所有讲师列表")
@GetMapping("list")
    public List<Teacher> listAll(){
    log.warn("这是一个警告信息");
    log.error("这是一个错误信息");
    return teacherService.list();
}

@ApiModelProperty(value = "根据ID删除讲师",notes = "根据ID删除讲师")
@DeleteMapping("remove/{id}")
    public R removeById(
            @ApiParam(value = "讲师id",required = true)
            @PathVariable String id){
    boolean result = teacherService.removeById(id);
    if (result){
        return R.ok().message("删除成功");
    }else{
        return R.error().message("删除失败");
    }
}
    @ApiOperation("分页讲师列表")
    @GetMapping("/list/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码", required = true)
                      @PathVariable Long page,
                      @ApiParam(value = "每页记录数", required = true)
                      @PathVariable Long limit,
                        @ApiParam(value ="讲师插寻对象" ,required = false)
                      TeacherQuery teacherQuery){


        IPage<Teacher> pageModel = teacherService.selectPage(page, limit, teacherQuery);
        return  R.ok().data("pageModel",pageModel);
    }
    @ApiOperation("新增讲师")
    @PostMapping("save")
    public R save(@ApiParam(value = "讲课对象",required = true)
                  @RequestBody Teacher teacher){
        boolean result = teacherService.save(teacher);
        if (result){
            return R.ok().message("添加成功");
        }else{
            return R.error().message("添加失败");
        }

    }
    @ApiOperation("更新讲师")
    @PutMapping("update")
    public R updateById(
            @ApiParam(value = "讲师对象",required = true)
            @RequestBody Teacher teacher){
        boolean result =  teacherService.updateById(teacher);
        if (result){
            return R.ok().message("修改成功");
        }else{
            return R.error().message("修改失败");
        }
    }

    @ApiOperation("根据id获取讲师信息")
    @GetMapping("get/{id}")
    public R getById(
            @ApiParam(value = "讲师ID",required = true)
            @PathVariable String id){
    Teacher teacher= teacherService.getById(id);
    if (teacher !=null){
        return R.ok().message("查询成功").data("item",teacher);
    }else{
        return R.error().message("数据不存在");
        }

    }
}
