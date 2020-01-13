package com.fh.student.service;

import com.fh.student.bean.po.StudentBean;
import com.fh.student.utils.PageBean;

public interface StudentService {
     StudentBean queryStudentById(Integer id);

    void queryStudentPageList(PageBean<StudentBean> page, StudentBean student);

    void addStudent(StudentBean student);

    void updateStudent(StudentBean student);

    void deleteStudentByIsdel(Integer id);
}
