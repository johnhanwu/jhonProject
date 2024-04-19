package org.example.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.example.DAO.UserRepository;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService{
@Autowired
UserRepository userRepository;

    @Override
    @Transactional
    public byte[] excelDownload() {
        List<User> result=userRepository.findAll();

        // 建立 Excel 檔案及工作表
        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet=workbook.createSheet("Data");

        // 建立 Excel 表頭
        Row headerRow=sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("FirstName");
        headerRow.createCell(2).setCellValue("LastName");
        headerRow.createCell(3).setCellValue("年齡");
        headerRow.createCell(4).setCellValue("地址");
        headerRow.createCell(5).setCellValue("身高");
        headerRow.createCell(6).setCellValue("體重");

        int rowNum=1;
        for(User user : result){
            Row row= sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getFirstName());
            row.createCell(2).setCellValue(user.getLastName());
            row.createCell(3).setCellValue(user.getAge());
            row.createCell(4).setCellValue(user.getAddress());
            row.createCell(5).setCellValue(user.getHeight());
            row.createCell(6).setCellValue(user.getWeight());
        }

        try{
            ByteArrayOutputStream file=new ByteArrayOutputStream();
            //FileOutputStream file=new FileOutputStream("user.xsl");
            workbook.write(file);
            return file.toByteArray();
        }catch (IOException e){
                e.printStackTrace();
        }
        return null;
    }
}
