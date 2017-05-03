package wzf.check.deal.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import wzf.check.deal.service.DataServiceImpl;
import wzf.check.entity.Data;
import wzf.framework.Page;

@Controller
public class IndexControllerImpl {

	@Resource
	private DataServiceImpl dataservice;
	
	@RequestMapping("/")
	public String index(HttpSession session,Model model,HttpServletRequest request){
		int pageSize = 5;
		session.setAttribute("pageSize", 5);
		int state = 1;
		session.setAttribute("state", 1);
		Page<Data> page;
		page=this.dataservice.list(1, pageSize, null);	
		session.setAttribute("page", page);
		return "index";
	}
	
}
