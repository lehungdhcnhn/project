package com.example.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.example.entities.Movie;

public class ExelHelper {
	public static String TYPE = ".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel";
	static String[] csvHeader = { "name", "starttime", "content", "length", "member", "idYou" };
	static String SHEET = "Movies";

	public static boolean hasExcelFormat(MultipartFile file) {
		if (TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public static ByteArrayInputStream tutorialsToExcel(List<Movie> movie) {

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);

			// Header
			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < csvHeader.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(csvHeader[col]);
			}

			int rowIdx = 1;
			for (Movie item : movie) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(item.getName());
				row.createCell(1).setCellValue(item.getStartTime());
				row.createCell(2).setCellValue(item.getContent());
				row.createCell(3).setCellValue(item.getLength());
				row.createCell(4).setCellValue(item.getMember());
				row.createCell(5).setCellValue(item.getIdYou());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}

	public static List<Movie> excelToTutorials(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();

			List<Movie> tutorials = new ArrayList<Movie>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cellsInRow = currentRow.iterator();

				Movie movie = new Movie();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:
						movie.setName(currentCell.getStringCellValue());
						break;
					case 1:
						movie.setStartTime(currentCell.getDateCellValue());
						break;
					case 2:
						movie.setContent(currentCell.getStringCellValue());
						break;
					case 3:
						movie.setLength((long) currentCell.getNumericCellValue());
						break;
					case 4:
						movie.setMember(currentCell.getStringCellValue());
						break;
					case 5:
						movie.setIdYou(currentCell.getStringCellValue());
						break;
					default:
						break;
					}
					cellIdx++;
				}
				tutorials.add(movie);
			}
			workbook.close();
			return tutorials;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}

}
