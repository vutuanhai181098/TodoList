package com.todolist.project.demo.utils;

import com.todolist.project.demo.entities.Task;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Component
public class ExcelReader implements IFileReader{
    private final int COLUMN_INDEX_ID = 0;
    private final int COLUMN_INDEX_TITLE = 1;
    private final int COLUMN_INDEX_STATUS = 2;
    @Override
    public List<Task> readFile(String filePath) {
        List<Task> taskList = new ArrayList<>();
        try {
            ClassPathResource resource = new ClassPathResource(filePath);
            InputStream inputStream = resource.getInputStream();

            Workbook workbook = getWorkbook(inputStream, filePath);
            Sheet sheet = workbook.getSheet("data");
            for (Row row : sheet){
                if(row.getRowNum() == 0){
                    continue;
                }
                Iterator<Cell> cellIterator = row.cellIterator();
                Task task = new Task();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    setTask(task, cell);
                }
                taskList.add(task);
            }
            workbook.close();
            inputStream.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }

    private void setTask(Task task, Cell cell) {
        int columnIndex = cell.getColumnIndex();
        Object cellValue = getCellValue(cell);
        switch (columnIndex){
            case COLUMN_INDEX_ID :
                task.setId(BigDecimal.valueOf((Double) cellValue).intValue());
                break;
            case COLUMN_INDEX_TITLE :
                task.setTitle((String) cellValue);
                break;
            case COLUMN_INDEX_STATUS :
                task.setStatus((Boolean) cellValue);
                break;
        }
    }

    public Workbook getWorkbook(InputStream inputStream, String filePath) throws IOException {
        Workbook workbook = null;
        if (filePath.endsWith("xlsx")){
            workbook = new XSSFWorkbook(inputStream);
        } else if(filePath.endsWith("xls")){
            workbook = new HSSFWorkbook(inputStream);
        } else {
            System.out.println("File isn't in the correct format!");
        }
        return workbook;
    }
    public Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType){
            case NUMERIC :
                cellValue = cell.getNumericCellValue();
                break;
            case STRING :
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN :
                cellValue = cell.getBooleanCellValue();
                break;
        }
        return cellValue;
    }
}
