package com.exercise.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static Map<String, String> readExcelData(String filePath) throws IOException{
		Map<String, String> data = new HashMap<String, String>();
		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(fis);
		
		try {
//			fis = new FileInputStream(filePath);
//			workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("Sheet1");
			
			for(Row row : sheet) {
				Cell keyCell = row.getCell(0);
				Cell keyValue = row.getCell(1);
				
			if(keyCell != null && keyValue != null) {
				String key = keyCell.getStringCellValue();
				String value = keyValue.getStringCellValue();
				data.put(key, value);
			}
		}
		}
			finally {
				try {
					if(workbook != null) workbook.close();
					if(fis != null) fis.close();
				}
				catch(IOException e) {
					e.getStackTrace();
				}
			}
		
		return data;
		
	}

}
