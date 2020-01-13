package com.fh.student.controller;

import com.fh.student.bean.po.StudentBean;
import com.fh.student.service.StudentService;
import com.fh.student.utils.CopyOSSFile;
import com.fh.student.utils.PageBean;
import com.fh.student.utils.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private  final static Logger log=LoggerFactory.getLogger(StudentController.class);
    /*查询*/
    @RequestMapping("queryStudentPageList")
    public PageBean<StudentBean> queryStudentPageList(PageBean<StudentBean> page,StudentBean student){
            studentService.queryStudentPageList(page,student);
            return page;
    }
    /*新增*/
    @RequestMapping("addStudent")
    public ResponseData addStudent(StudentBean student){
        try {
            student.setIpaddr(request.getRemoteAddr());
            student.setIsdel(1);
            studentService.addStudent(student);
            log.info("执行了新增操作，ip为"+request.getRemoteAddr());
            return ResponseData.success("新增成功");

        }catch (Exception e){
            return ResponseData.error(e.getMessage());
        }

    }
    /*回显修改*/
    @RequestMapping("queryStudentById")
    public ResponseData queryStudentById(Integer id){
        try {
            StudentBean student = studentService.queryStudentById(id);
            return ResponseData.success(student);
        }catch (Exception e){
            return ResponseData.error(e.getMessage());
        }
    }
    @RequestMapping("updateStudent")
    public ResponseData updateStudent(StudentBean student){
        try {
            student.setIpaddr(request.getRemoteAddr());
            student.setIsdel(1);
            studentService.updateStudent(student);
            log.info("执行了修改操作，ip为"+request.getRemoteAddr());
            return ResponseData.success("修改成功");
        }catch (Exception e){
            return ResponseData.error(e.getMessage());
        }
    }
    /*删除*/
    @RequestMapping("deleteStudentByIsdel")
    public ResponseData deleteStudentByIsdel(Integer id){
        try {
            studentService.deleteStudentByIsdel(id);
            log.info("执行了删除操作，ip为"+request.getRemoteAddr());
            return ResponseData.success("删除成功");
        }catch (Exception e){
            return ResponseData.error(e.getMessage());
        }
    }
    /*删除*/
    @RequestMapping("uploadFile")
    public ResponseData uploadFile(@RequestParam("studentPhoto") MultipartFile photo ){
        String url = CopyOSSFile.CopyOSSFile(photo, "picture");
        log.info("执行了文件上传操作，ip为"+request.getRemoteAddr());
        return ResponseData.success(url);
    }
}
