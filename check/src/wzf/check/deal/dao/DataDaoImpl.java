package wzf.check.deal.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import wzf.check.entity.Data;
import wzf.framework.BaseDao;
import wzf.framework.Page;

@Repository
public class DataDaoImpl extends BaseDao<Data, String> {

	@Resource
	private SessionFactory sessionFactory;
	
	public List<Data> selectall(){
		Session session = sessionFactory.getCurrentSession(); 
		String sql = "from Data"; 
		Query que = session.createQuery(sql); 
		List msgs = que.list();
		return msgs;
	}
	
	public void updatedata(Data data){
		try {
			super.update(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Data findone(Integer id){
		Data data = null;
		try {
			data = super.findOne("from Data where Id=?", new Object[]{id});
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void deleteData(Data data){
		try {
			super.delete(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveData(Data data){
		try {
			super.save(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Page<Data> finddatalist(int pageNum, int pageSize,Object[] params){
		String hql;
		hql = "from Data";
		Page<Data> page=new Page<Data>();
		page.setCurrentPageNum(pageNum);
		page.setPageSize(pageSize);
		try {
			page=this.findByPage(pageNum, pageSize, hql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
	public Page<Data> findpagebyparm(int pageNum, int pageSize,Object[] params){
		String hql;
		if(params!=null && params.length>0){
			hql="from Data p where p.name like ?";
			params[0]="%"+params[0]+"%";
			
		}else{
			hql="from Data";
		}
		try {
			Page<Data> page=new Page<Data>();
			page.setCurrentPageNum(pageNum);
			page.setPageSize(pageSize);
			page=this.findByPage(pageNum, pageSize, hql, params);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Page<Data> findpagemorebyparm(int pageNum, int pageSize,Object[] params){
		String hql;
		if(params!=null && params.length>0){
			hql="from Data p where p.name like ?";
			params[0]="%"+params[0]+"%";
			
		}else{
			hql="from Data";
		}
		try {
			Page<Data> page=new Page<Data>();
			page.setCurrentPageNum(pageNum);
			page.setPageSize(pageSize);
			page=this.findByPage(pageNum, pageSize, hql, params);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Page<Data> find(int pageNum, int pageSize,Object[] params){
		String hql;
			hql="from Data";
		try {
			Page<Data> page=new Page<Data>();
			page.setCurrentPageNum(pageNum);
			page.setPageSize(pageSize);
			page=this.findByPage(pageNum, pageSize, hql, params);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Page<Data> findpagemorebyparmoccupation(int pageNum, int pageSize,Object[] params){
		String hql;
		if(params!=null && params.length>0){
			hql="from Data p where p.occupation like ?";
			params[0]="%"+params[0]+"%";
			
		}else{
			hql="from Data";
		}
		try {
			Page<Data> page=new Page<Data>();
			page.setCurrentPageNum(pageNum);
			page.setPageSize(pageSize);
			page=this.findByPage(pageNum, pageSize, hql, params);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Page<Data> findpagebyid(int pageNum, int pageSize,Object[] params){
		String hql;
		int i = Integer.parseInt(String.valueOf(params[0]));
		if(params!=null && params.length>0){
			hql="from Data where Id = ?";
			//params[0] = i;
		}else{
			hql="from Data";
		}
		try {
			Page<Data> page=new Page<Data>();
			page.setCurrentPageNum(pageNum);
			page.setPageSize(pageSize);
			page=this.findByPage(pageNum, pageSize, hql, params);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Page<Data> findpagemorebyoccupationandname(int pageNum, int pageSize,Object[] params){
		String hql;
		if(params!=null && params.length>0){
			hql="from Data p where p.name like ? and p.occupation like ?";
			params[0]="%"+params[0]+"%";
			params[1]="%"+params[1]+"%";
			
		}else{
			hql="from Data";
		}
		try {
			Page<Data> page=new Page<Data>();
			page.setCurrentPageNum(pageNum);
			page.setPageSize(pageSize);
			page=this.findByPage(pageNum, pageSize, hql, params);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
