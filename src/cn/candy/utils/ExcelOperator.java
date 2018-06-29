package cn.candy.utils;

import java.io.OutputStream;
import java.math.BigDecimal;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

@SuppressWarnings("deprecation")
public class ExcelOperator {

	/**
	* 将数据信息写入到Excel表文件 ，采取传入输出流的方式。
	* @param excel Excel表的模型对象 
	* @param out  OutputStream 输出流
	* @throws Exception
	*/
	public void WriteExcel(OutputStream out, ExcelModel... excel) throws Exception {
		try {
			HSSFWorkbook workbook = this.getInitWorkbook(excel);
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 取得填充了数据的工作簿
	 * @param excel ExcelModel Excel表的模型对象
	 * @return HSSFWorkbook 工作簿对象
	 */
	private HSSFWorkbook getInitWorkbook(ExcelModel... excel) {
		// 创建新的Excel 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		for (ExcelModel excel_ : excel) {
			// 在Excel工作簿中建一工作表
			HSSFSheet sheet = null;
			String sheetName = excel_.getSheetName();

			if (sheetName != null)
				sheet = workbook.createSheet(sheetName);
			else
				sheet = workbook.createSheet();
			// short a = 4;
			// short g = 5;
			// short c = 15000;
			// sheet.setColumnWidth(a, c);
			// sheet.setColumnWidth(g, c);

			// 设置表头字体
			HSSFFont font_h = workbook.createFont();
			font_h.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			// 设置格式
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFont(font_h);

			// 设置表格宽度
			sheet.setDefaultColumnWidth(25);// 默认宽度
			// sheet.setColumnWidth(0,6400);//指定某列的宽度，需要是256的倍数 如要设置宽度是25 及25*256=6400

			// 在索引0的位置创建行（最顶端的行）
			HSSFRow row = sheet.createRow((short) 0);

			String[] header = excel_.getHeader();
			if (header != null) {
				for (int i = 0; i < header.length; i++) {
					// 在索引i的位置创建单元格（左上端）
					HSSFCell cell = row.createCell((short) i);
					// 定义单元格为字符串类型
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					// 设置单元格的格式
					cell.setCellStyle(cellStyle);
					// 在单元格中写入表头信息
					cell.setCellValue((String) header[i]);
				}
			}
			String[][] cdata = excel_.getData();
			if (cdata != null) {
				for (int i = 0; i < cdata.length; i++) {
					// 从第二行开始
					HSSFRow row1 = sheet.createRow(i + 1);
					// 6W条分页

					String[] rdata = (String[]) cdata[i];
					// 打印一行数据
					for (int j = 0; j < rdata.length; j++) {
						HSSFCell cell = row1.createCell((short) j);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						Object o = rdata[j];
						// 造型,使写入到表中的数值型对象恢复为数值型，
						// 这样就可以进行运算了
						if (o instanceof BigDecimal) {
							BigDecimal b = (BigDecimal) o;
							cell.setCellValue(b.doubleValue());
						} else if (o instanceof Integer) {
							Integer it = (Integer) o;
							cell.setCellValue(it.intValue());
						} else if (o instanceof Long) {
							Long l = (Long) o;
							cell.setCellValue(l.intValue());
						} else if (o instanceof Double) {
							Double d = (Double) o;
							cell.setCellValue(d.doubleValue());
						} else if (o instanceof Float) {
							Float f = (Float) o;
							cell.setCellValue(f.floatValue());
						} else {
							cell.setCellValue(o + "");
						}
						// 设置链接
						// if(excel_.isHaveLink() && excel_.getLinkCellIndex()==j){
						// HSSFHyperlink link = new HSSFHyperlink(HSSFHyperlink.LINK_DOCUMENT);
						// link.setAddress(excel_.getLinkAddress()[i]);
						// //cell.setHyperlink(link);
						// }
					}
				}
			}
		}
		return workbook;
	}
}
