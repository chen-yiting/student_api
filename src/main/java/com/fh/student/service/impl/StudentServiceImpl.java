package com.fh.student.service.impl;

import com.fh.student.bean.po.StudentBean;
import com.fh.student.dao.StudentDao;
import com.fh.student.service.StudentService;
import com.fh.student.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public StudentBean queryStudentById(Integer id) {
        return studentDao.selectById(id);
    }

    @Override
    public void queryStudentPageList(PageBean<StudentBean> page, StudentBean student) {
        long count=studentDao.queryCount(student);
        page.setRecordsFiltered(count);
        page.setRecordsTotal(count);
        List<StudentBean> data=studentDao.queryStudentPageList(page,student);
        page.setData(data);
    }

    @Override
    public void addStudent(StudentBean student) {
        studentDao.insert(student);
    }

    @Override
    public void updateStudent(StudentBean student) {
        studentDao.updateById(student);
    }

    @Override
    public void deleteStudentByIsdel(Integer id) {
        studentDao.deleteStudentByIsdel(id);
    }
}
