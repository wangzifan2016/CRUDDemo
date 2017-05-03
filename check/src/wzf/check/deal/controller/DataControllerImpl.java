package wzf.check.deal.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wzf.check.deal.service.DataServiceImpl;
import wzf.check.entity.Data;
import wzf.framework.Page;

@Controller
@RequestMapping("datecontroller")
public class DataControllerImpl {

	@Resource
	private DataServiceImpl dataservice;
	
	@RequestMapping("add")
	public String add(@RequestParam("addoccupation") String occupation,@RequestParam("addname") String name,@RequestParam("addage") int age,
			HttpSession session){
		
		Data d = new Data();
		d.setOccupation(occupation);
		d.setName(name);
		d.setAge(age);
		this.dataservice.add(d);
		Page<Data> page;
		int pageSize = (int)session.getAttribute("pageSize");
		page=this.dataservice.list(1, pageSize, null);	
		session.setAttribute("page", page);
		return "index";
	}
	
	@RequestMapping("selectall")
	public String all(HttpSession session){
		List<Data> lists = this.dataservice.selectall();
		session.setAttribute("lists", lists);
		return "index";
	}
	
	@RequestMapping("delete")
	public String delete(String id,HttpSession session){
		Integer idn = Integer.parseInt(id);
		Data date = this.dataservice.selectone(idn);
		this.dataservice.deleteone(date);
		Page<Data> page;
		int pageSize = (int)session.getAttribute("pageSize");
		page=this.dataservice.list(1, pageSize, null);	
		session.setAttribute("page", page);
		return "index";
	}
	
	@RequestMapping("edit")
	public String edit(@RequestParam("updateid") int id,@RequestParam("updateoccupation") String occupation,
			@RequestParam("updatename") String name,@RequestParam(name="searchParam",defaultValue="-1") int age,
			HttpSession session){
		Data date = this.dataservice.selectone(id);
		if(occupation==null||"".equals(occupation)){
		}else{
			date.setOccupation(occupation);
		}
		if(name==null||"".equals(name)){
		}else{
			date.setName(name);
		}
		if(age==-1){
		}else{
			date.setAge(age);
		}
		this.dataservice.updateentity(date);
		Page<Data> page;
		int pageSize = (int)session.getAttribute("pageSize");
		page=this.dataservice.list(1, pageSize, null);	
		session.setAttribute("page", page);
		return "index";
	}
	
	@RequestMapping("findone")
	public String findone(@RequestParam(name="searchParam",defaultValue="") String searchParam,HttpSession session){
		Page<Data> page;
		if(searchParam==null || "".equals(searchParam)){
			page=this.dataservice.findbyparm(1, 10, null);	
		}else{
			page=this.dataservice.findbyparm(1, 10, new Object[]{searchParam});
		}
		session.setAttribute("page", page);
		return "index";
	}
	
	@RequestMapping("findmore")
	public String findmore(@RequestParam(name="name",defaultValue="") String name,
			@RequestParam(name="occupation",defaultValue="") String occupation,HttpSession session){
		Page<Data> page;
		if((name==null || "".equals(name))&&(occupation==null || "".equals(occupation))){
			page=this.dataservice.findbyparm(1, 10, null);	
		}else if(name!=null&&occupation==null){
			page=this.dataservice.findmorebyparm(1, 10, new Object[]{name});
		}else if(name==null&&occupation!=null){
			page=this.dataservice.findmorebyparmoccupation(1, 10, new Object[]{occupation});
		}else{
			page=this.dataservice.findmorebyageandname(1, 10, new Object[]{name,occupation});
		}
		session.setAttribute("page", page);
		return "index";
	}
	
	//分页查询
	@RequestMapping("list")
	public String datelist(@RequestParam(name="pageNum", defaultValue="1") int pageNum,HttpSession session,Model model){
		Page<Data> page;
		int state = (int)session.getAttribute("state");
		int pageSize = (int)session.getAttribute("pageSize");
		if(state == 1){
			page=this.dataservice.list(pageNum, pageSize, null);
		}else if(state == 2){
			page=this.dataservice.list(pageNum, pageSize, null);
			Collections.reverse(page.getList());
		}else{
			page=this.dataservice.list(pageNum, pageSize, null);
		}
		session.setAttribute("page", page);
		return "index";
	}
	
	@RequestMapping("uplist")
	public String dateuplist(@RequestParam(name="pageNum", defaultValue="1") int pageNum,HttpSession session,Model model){
		Page<Data> page;
		int state = 1;
		session.setAttribute("state", 1);
		int pageSize = (int)session.getAttribute("pageSize");
		page=this.dataservice.list(pageNum, pageSize, null);
		session.setAttribute("page", page);
		return "index";
	}
	
	//分页降序
	@RequestMapping("downlist")
	public String datedownlist(@RequestParam(name="pageNum", defaultValue="1") int pageNum,HttpSession session,Model model){
		Page<Data> page;
		int state = 2;
		session.setAttribute("state", 2);
		int pageSize = (int)session.getAttribute("pageSize");
		page=this.dataservice.list(pageNum, pageSize, null);
		Collections.reverse(page.getList());
		session.setAttribute("page", page);
		return "index";
	}
	
	@RequestMapping("findbyid")
	public String findbyid(@RequestParam(name="id",defaultValue="") int id,HttpSession session){
		Page<Data> page;
		String ids = String.valueOf(id);
		if(ids==null || "".equals(ids)){
			page=this.dataservice.findbynoid(1, 10, null);	
		}else{
			page=this.dataservice.findbyid(1, 10, new Object[]{ids});
		}
		session.setAttribute("page", page);
		return "index";
	}
	
	@RequestMapping("page")
	public String editpage(HttpServletRequest request,HttpSession session){
		String spage = request.getParameter("optionsRadiosinline");
		int pageSize = Integer.parseInt(spage);
		session.setAttribute("pageSize", pageSize);
		Page<Data> page;
		page=this.dataservice.list(1, pageSize, null);
		session.setAttribute("page", page);
		return "index";
	}
	
	@RequestMapping("exportExcelAll")
	public void exportExcelAll(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		//TODO 如需添加条件 
	    //获取需要导出的数据List 
	    List<Data> dataList=this.dataservice.selectall(); 
	      //使用方法生成excle模板样式 
	    HSSFWorkbook workbook = this.dataservice.createExcel(dataList, request); 
	    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); // 定义文件名格式 
	  
	    try { 
	    //定义excle名称 ISO-8859-1防止名称乱码 
	      String msg = new String( 
	          ("数据集_" + format.format(new Date()) + ".xls").getBytes(), 
	          "ISO-8859-1"); 
	      // 以导出时间作为文件名 
	      response.setContentType("application/vnd.ms-excel"); 
	      response.addHeader("Content-Disposition", "attachment;filename="
	          + msg); 
	      workbook.write(response.getOutputStream()); 
	    } catch (IOException e) { 
	      e.printStackTrace();
	    }finally{
	    	try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}
	
	@RequestMapping("exportExcelNow")
	public void exportExcelNow(ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		//TODO 如需添加条件 
	    //获取需要导出的数据List 
		Page page = (Page)session.getAttribute("page");
	    List<Data> dataList= page.getList();
	      //使用方法生成excle模板样式 
	    HSSFWorkbook workbook = this.dataservice.createExcel(dataList, request); 
	    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); // 定义文件名格式 
	  
	    try { 
	    //定义excle名称 ISO-8859-1防止名称乱码 
	      String msg = new String( 
	          ("数据集_" + format.format(new Date()) + ".xls").getBytes(), 
	          "ISO-8859-1"); 
	      // 以导出时间作为文件名 
	      response.setContentType("application/vnd.ms-excel"); 
	      response.addHeader("Content-Disposition", "attachment;filename="
	          + msg); 
	      workbook.write(response.getOutputStream()); 
	    } catch (IOException e) { 
	      e.printStackTrace();
	    }finally{
	    	try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}
}
