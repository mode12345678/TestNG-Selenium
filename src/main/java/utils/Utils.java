package utils;

import java.io.File;
import java.util.Random;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utils {
	public static String randomText(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}

	public static int randomInt(int min, int max) {
		Random r = new Random();

		return r.nextInt((max - min) + 1) + min;
	}

	public static String getUsername() {
		return Utils.parseXml("username");
	}

	public static String getPassword() {
		return Utils.parseXml("password");
	}

	public static String getUrl() {
		return Utils.parseXml("url");
	}

	public static String parseXml(String tag) {
		try {

			File file = new File("config\\config.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("config");
			Node node = nodeList.item(0);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				return eElement.getElementsByTagName(tag).item(0).getTextContent();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	public static ArrayList<String> parseExcel() {
		try {
			File file = new File("test-data\\login.xlsx"); // creating a new file instance
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file

			ArrayList<String> data = new ArrayList<String>();
			List<String> columnNames = Arrays.asList(new String[] { "Username", "Password" });

			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING: // field that represents string cell type
						String field = cell.getStringCellValue();

						if (!columnNames.contains(field)) {
							data.add(field);
						}
						break;
//						case Cell.CELL_TYPE_NUMERIC: // field that represents number cell type
////							System.out.print(cell.getNumericCellValue() + "\t\t\t");
//							break;
					default:
					}
					System.out.println();
				}

			}

			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}