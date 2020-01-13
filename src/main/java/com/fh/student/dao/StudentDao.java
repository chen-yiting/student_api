package com.fh.student.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.student.bean.po.StudentBean;
import com.fh.student.utils.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentDao extends BaseMapper<StudentBean> {
    long queryCount(@Param("student") StudentBean student);

    List<StudentBean> queryStudentPageList(@Param("page")PageBean<StudentBean> page,@Param("student") StudentBean student);

    void deleteStudentByIsdel(Integer id);
}
