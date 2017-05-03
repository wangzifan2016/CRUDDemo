package wzf.check.deal.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wzf.check.deal.dao.DataDaoImpl;
import wzf.check.entity.Data;
import wzf.framework.Page;

@Service
@Transactional(readOnly=false)
public class DataServiceImpl {

	@Resource
	private DataDaoImpl datedao;
	
	public List<Data> selectall(){
		List<Data> lists = this.datedao.selectall();
		return lists;
	}
	
	public Data selectone(Integer id){
		Data date = this.datedao.findone(id);
		return date;
	}
	
	public void deleteone(Data data){
		this.datedao.deleteData(data);
	}
	
	public void add(Data data){
		this.datedao.saveData(data);
	}
	
	public void updateentity(Data data){
		this.datedao.updatedata(data);
	}
	
	@Transactional(readOnly=true)
	public Page<Data> list(int pageNum,int pageSize,Object[] params){
		return this.datedao.finddatalist(pageNum, pageSize, params);
	}
	
	@Transactional(readOnly=true)
	public Page<Data> findbyparm(int pageNum,int pageSize,Object[] params){
		return this.datedao.findpagemorebyparm(pageNum, pageSize, params);
	}
	
	@Transactional(readOnly=true)
	public Page<Data> findbynoid(int pageNum,int pageSize,Object[] params){
		return this.datedao.find(pageNum, pageSize, params);
	}
	
	@Transactional(readOnly=false)
	public Page<Data> findbyid(int pageNum,int pageSize,Object[] params){
		return this.datedao.findpagebyid(pageNum, pageSize, params);
	}
	
	@Transactional(readOnly=true)
	public Page<Data> findmorebyparm(int pageNum,int pageSize,Object[] params){
		return this.datedao.findpagemorebyparm(pageNum, pageSize, params);
	}
	
	@Transactional(readOnly=true)
	public Page<Data> findmorebyageandname(int pageNum,int pageSize,Object[] params){
		return this.datedao.findpagemorebyoccupationandname(pageNum, pageSize, params);
	}
	
	@Transactional(readOnly=true)
	public Page<Data> findmorebyparmoccupation(int pageNum,int pageSize,Object[] params){
		return this.datedao.findpagemorebyparmoccupation(pageNum, pageSize, params);
	}
	
	@Transactional(readOnly = false)
	public HSSFWorkbook createExcel(List<Data> dateList, HttpServletRequest request) {
		// 创建一个webbook，对应一个excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 在webbook中添加一个sheet,对应excel文件中的sheet
		HSSFSheet sheet = workbook.createSheet("详细信息");
		// 设置列宽
		sheet.setColumnWidth(0, 25 * 100);
		sheet.setColumnWidth(1, 35 * 100);
		sheet.setColumnWidth(2, 35 * 100);
		sheet.setColumnWidth(3, 40 * 100);
		// 在sheet中添加表头第0行
		HSSFRow row = sheet.createRow(0);
		// 创建单元格，并设置表头，设置表头居中
		HSSFCellStyle style = workbook.createCellStyle();
		// 创建一个居中格式
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 带边框
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		// 字体增粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 字体大小
		font.setFontHeightInPoints((short) 12);
		// 把字体应用到当前的样式
		style.setFont(font);

		// 单独设置整列居中或居左
		HSSFCellStyle style1 = workbook.createCellStyle();
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);

		HSSFCellStyle style3 = workbook.createCellStyle();
		style3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		HSSFFont hssfFont = workbook.createFont();
		hssfFont.setColor(HSSFFont.COLOR_RED);
		hssfFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style3.setFont(hssfFont);

		HSSFCellStyle style4 = workbook.createCellStyle();
		style4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		HSSFFont hssfFont1 = workbook.createFont();
		hssfFont1.setColor(HSSFFont.COLOR_NORMAL);
		hssfFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style4.setFont(hssfFont1);

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("Id");
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue("职业");
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);

		cell = row.createCell(3);
		cell.setCellValue("年龄");
		cell.setCellStyle(style);

		for (int i = 0; i < dateList.size(); i++) {
			String logTypeDis = "";
			row = sheet.createRow(i + 1);
			Data date = dateList.get(i);
			// 创建单元格，并设置值
			// 编号列居左
			HSSFCell c1 = row.createCell(0);
			c1.setCellStyle(style2);
			c1.setCellValue(date.getId());//存入Id
			
			HSSFCell c2 = row.createCell(1);
			c2.setCellStyle(style1);
			c2.setCellValue(date.getOccupation());//存入职业

			String name = date.getName();
			HSSFCell c3 = row.createCell(2);
			c3.setCellStyle(style1);
			c3.setCellValue(name);//存入姓名

			HSSFCell c4 = row.createCell(3);
			c4.setCellStyle(style1);
			c4.setCellValue(date.getAge());//存入年龄
		}
		return workbook;
	}
}
