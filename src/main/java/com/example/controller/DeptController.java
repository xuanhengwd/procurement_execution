package com.example.controller;


import com.example.pojo.Dept;
import com.example.service.DeptService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Controller("deptController")
@RequestMapping("/dept")
@CrossOrigin
public class DeptController {

    private final DeptService deptService;

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    @RequestMapping("/deptTest")
    @ResponseBody
    String  deptTest(int x,int y) {
        System.out.println("dept......"+x+" "+y);
        return "depts";
    }
    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping("/selectDeptAll")
    @ResponseBody
    List<Dept> selectDeptAll() {
        List<Dept> depts = deptService.selectDeptAll();
        return depts;
    }

    //分页查询
    @RequestMapping("/selectDeptByPage")
    @ResponseBody
    List<Dept> selectDeptByPage(int curPage,int pageCount) {
        System.out.println(curPage);
        List<Dept> depts = deptService.selectDeptByPage(curPage,pageCount);
        return depts;
    }

    /**
     * 添加部门
     *
     * @param dept
     * @return
     */
    @RequestMapping(value = "/addDept", method = RequestMethod.POST)
    @ResponseBody
    String addDept(Dept dept) {

        deptService.addDept(dept);
        return "true";
    }

    /**
     * 根据id删除部门信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteDeptById", method = RequestMethod.POST)
    @ResponseBody
    String deleteDeptById(int id) {
        deptService.deleteDeptById(id);
        return "true";
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteDeptByIds", method = RequestMethod.POST)
    @ResponseBody
    String deleteDeptByIds(int[] ids) {
        deptService.deleteDeptByIds(ids);
        return "true";
    }


    /**
     * 修改部门信息
     *
     * @param dept
     * @return
     */
    @RequestMapping(value = "/updateDeptById", method = RequestMethod.POST)
    @ResponseBody
    String updateDeptById(Dept dept) {
        deptService.updateDeptById(dept);
        return "true";
    }

    /**
     * 条件查询
     *
     * @param dept
     * @return
     */
    @RequestMapping(value = "/selectDeptByCondition", method = RequestMethod.POST)
    @ResponseBody
    List<Dept> selectDeptByCondition(Dept dept, int curPage, int pageCount) {
        System.out.println(dept);
        List<Dept> depts = deptService.selectDeptByCondition(dept,curPage,pageCount);
        //  System.out.println(depts);
        return depts;
    }

    /**
     * 查询所有部门的数量
     *
     * @return
     */
    @RequestMapping(value = "/deptCount")
    @ResponseBody
    int deptCount() {
        return deptService.deptCount();
    }


    /**
     * Excle 批量导入
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/addDepts", method = RequestMethod.POST)
    @ResponseBody
    String addDepts(MultipartFile file) throws Exception {
        System.out.println("上传文件");
        if (file == null) {
            return "false";
        }
        String fileName = file.getOriginalFilename();
        long size = file.getSize();
        if (fileName == null || ("").equals(fileName) && size == 0)
            return "false";

        System.out.println(fileName);
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        if (!prefix.toLowerCase().contains("xls") && !prefix.toLowerCase().contains("xlsx")) {
            return "false";
        }

        final File excelFile = File.createTempFile(System.currentTimeMillis() + "", prefix);
        file.transferTo(excelFile);


        boolean isExcel2003 = prefix.toLowerCase().endsWith("xls") ? true : false;
        Workbook workbook = null;
        if (isExcel2003) {
            workbook = new HSSFWorkbook(new FileInputStream(excelFile));
        } else {
            workbook = new XSSFWorkbook(new FileInputStream(excelFile));
        }

        //Excel表中的内容
        List<Dept> depts = new ArrayList<>();

        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            Row row = sheet.getRow(i);
            if (row == null)
                continue;
            Dept dept = new Dept();
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                if (cell != null) {
                    cell.setCellType(CellType.STRING);
                    if (j == 0) {
                        dept.setDeptNo(cell.getStringCellValue());
                    } else if (j == 1) {
                        dept.setDeptName(cell.getStringCellValue());
                    } else if (j == 2) {
                        dept.setParentId(Integer.valueOf(cell.getStringCellValue()));
                    } else if (j == 3) {
                        dept.setDeptNature(cell.getStringCellValue());
                    } else if (j == 4) {
                        dept.setRemarks(cell.getStringCellValue());
                    }
                }

            }
            depts.add(dept);
        }
        //删除临时转换的文件
        if (excelFile.exists()) {
            excelFile.delete();
        }
        Boolean flag = deptService.addDepts(depts);

        return flag ? "true" : "false";
    }


}
