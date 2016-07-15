package com.sql.init;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import oracle.sql.CLOB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.markdown4j.Markdown4jProcessor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;


public class Init {
	
	private JdbcTemplate dbop;
	public JdbcTemplate getDbop() {
		return dbop;
	}
	public void setDbop(JdbcTemplate dbop) {
		this.dbop = dbop;
	}
	
	private JdbcTemplate dbop_zhxy;
	
	public JdbcTemplate getDbop_zhxy() {
		return dbop_zhxy;
	}
	public void setDbop_zhxy(JdbcTemplate dbop_zhxy) {
		this.dbop_zhxy = dbop_zhxy;
	}

//	private Markdown4jProcessor mk4jProcessor;
//	public Markdown4jProcessor getMk4jProcessor() {
//		return mk4jProcessor;
//	}
//	public void setMk4jProcessor(Markdown4jProcessor mk4jProcessor) {
//		this.mk4jProcessor = mk4jProcessor;
//	}
	
	private Log log = LogFactory.getLog(this.getClass());
	public Document initMenus(){
		String sql_Menu = "select t.* from sys_menus t where t.par_id is null order by t.sequence";
		String sql_MenuItems = null;
		List<Map<String, Object>> menus = dbop.queryForList(sql_Menu);
		List<Map<String, Object>> menuItems = null;
		Iterator<?> iter = menus.iterator();
		Iterator<?> iter_MIs = null;
		Map menu = null;
		Map menuItem = null;
		Document menuList = Jsoup.parse("");
		Element body = menuList.body();
		Element div = null;
		Element logo = null;
		Element divMenu = null;
		Element ul_Menu = null;
		Element li_Menu = null;
		Element a_Menu = null;
		Element ul_MenuItem = null;
		Element li_MenuItem = null;
		Element a_MenuItem = null;
		while (iter.hasNext()) {
			menu = (Map) iter.next();
			if(ul_Menu == null){
			    div = body.appendElement("div");
			    div.attr("class","top");
			    logo = div.appendElement("a");
			    logo.attr("class", "logo_l");
			    logo.attr("href", "/");
			    logo.attr("title", "返回首页");
			    divMenu = div.appendElement("div");
			    divMenu.attr("class", "nav_z");
			    ul_Menu = divMenu.appendElement("ul");
			    ul_Menu.attr("id", "navul");
			    ul_Menu.attr("class", "cl");
			}
			li_Menu = ul_Menu.appendElement("li");
			a_Menu = li_Menu.appendElement("a");
			a_Menu.attr("href", (String) menu.get("HREF")==null?"javascript:void(0)":(String) menu.get("HREF"));
			if(menu.get("TITLE") != null)
				a_Menu.attr("title", (String) menu.get("TITLE"));
			if(menu.get("NAME") != null)
				a_Menu.appendText((String) menu.get("NAME"));
			sql_MenuItems = "select t.* from sys_menus t where t.par_id=?";
			menuItems = dbop.queryForList(sql_MenuItems,(String) menu.get("ID"));
			iter_MIs = menuItems.iterator();
			while (iter_MIs.hasNext()) {
				menuItem = (Map) iter_MIs.next();
				if(ul_MenuItem == null){
					ul_MenuItem = li_Menu.appendElement("ul");
				}
				li_MenuItem = ul_MenuItem.appendElement("li");
				a_MenuItem = li_MenuItem.appendElement("a");
				a_MenuItem.attr("href", (String) menuItem.get("HREF")==null?"javascript:void(0)":(String) menuItem.get("HREF"));
				if(menuItem.get("TITLE") != null)
					a_MenuItem.attr("title", (String) menuItem.get("TITLE"));
				if(menuItem.get("NAME") != null)
					a_MenuItem.appendText((String) menuItem.get("NAME"));
			}
			ul_MenuItem = null;
		}
		log.debug(menuList.html());
		return menuList;
	}
	public Document getLeftMenus(String id){
		String sql_1 = "select t.id,t.name from content t where t.menu_id = ? order by t.sequence";
		String sql_2 = "select t.id,t.name,t.href,t.title from content t where t.par_id = ? order by t.sequence";
		List<Map<String, Object>> menus = dbop.queryForList(sql_1,id);
		List<Map<String, Object>> menuItems_1 = null;
		List<Map<String, Object>> menuItems_2 = null;
		Iterator<?> iterator = menus.iterator();
		Iterator<?> iter_MIs_1 = null;
		Iterator<?> iter_MIs_2 = null;
		Map menu = null;
		Map menuItem_1 = null;
		Map menuItem_2 = null;
		Document menuList = Jsoup.parse("");
		Element body = menuList.body();
		Element ul_1 = null;
		Element ul_2 = null;
		Element li_1 = null;
		Element li_2 = null;
		Element a_2 = null;
		Element a_3 = null;
		Element i_2 = null;
		Element ol_2 = null;
		int ol_index = 0;
		Element div_1 = null;
		Element span_1 = null;
		while (iterator.hasNext()) {
			menu = (Map) iterator.next();
			if(ul_1 == null){
				ul_1 = body.appendElement("ul");
				ul_1.attr("class", "expmenu");
			}
			li_1 = ul_1.appendElement("li");
			div_1 = li_1.appendElement("div");
			div_1.attr("class", "header");
			span_1 = div_1.appendElement("span");
			span_1.attr("class", "label");
			span_1.appendText((String) menu.get("NAME"));
			span_1 = div_1.appendElement("span");
			if(menuItems_1 == null){
				span_1.attr("class", "arrow up");
			}else{
				span_1.attr("class", "arrow down");
			}
			menuItems_1 = dbop.queryForList(sql_2,(String) menu.get("ID"));
			iter_MIs_1 = menuItems_1.iterator();
			if(iter_MIs_1.hasNext()){
				span_1 = li_1.appendElement("span");
				span_1.attr("class","no");
				ul_2 = span_1.appendElement("ul");
				ul_2.attr("class", "menu");
				if(menuItem_1 == null){
					ul_2.attr("style", "display:block;");
				}
			}
			while(iter_MIs_1.hasNext()){
				menuItem_1 = (Map) iter_MIs_1.next();
				li_2 = ul_2.appendElement("li");
				a_2 = li_2.appendElement("a");
				a_2.attr("href", (String) menuItem_1.get("HREF")==null?"javascript:void(0)":(String) menuItem_1.get("HREF"));
				if(menuItem_1.get("TITLE") != null)
					a_2.attr("title", (String) menuItem_1.get("TITLE"));
				if(menuItem_1.get("NAME") != null)
					a_2.appendText((String) menuItem_1.get("NAME"));
				String menuItem_1_ID = (String) menuItem_1.get("ID");
				menuItems_2 = dbop.queryForList(sql_2,(String) menuItem_1.get("ID"));
				iter_MIs_2 = menuItems_2.iterator();
				if(iter_MIs_2.hasNext()){
					li_2.attr("onClick", "javascript:ShowMenu(this," + ol_index + ")");
					ol_index++;
				}
				while(iter_MIs_2.hasNext()){
					menuItem_2 = (Map) iter_MIs_2.next();
					if(ol_2 == null){
//						i_2 = a_2.prependElement("i");
//						i_2.appendText("+");
						ol_2 = ul_2.appendElement("ol");
						ol_2.attr("class", "no");
					}
					a_3 = ol_2.appendElement("a");
					a_3.attr("href", (String) menuItem_2.get("HREF")==null?"javascript:void(0)":(String) menuItem_2.get("HREF"));
					if(menuItem_2.get("TITLE") != null)
						a_3.attr("title", (String) menuItem_2.get("TITLE"));
					if(menuItem_2.get("NAME") != null)
						a_3.appendText((String) menuItem_2.get("NAME"));
				}
				ol_2 = null;
			}
        }
		log.debug(menuList.html());
		return menuList;
	}
	public Document getContent(String cid){
		String sql_1 = null;
		String content = null;
		Document docContent = null;
		Element body = null;
		Element div_1 = null;
		Element div_2 = null;
		try {
			sql_1 = "select t.content from content t where t.id = ?";
			content = dbop.queryForObject(sql_1,String.class,cid);
			docContent = Jsoup.parse("");
			body = docContent.body();
			div_1 = body.appendElement("div");
			div_1.attr("class", "content");
			div_2 = div_1.appendElement("div");
			div_2.attr("class", "top_line");
//			div_1.append(mk4jProcessor.process(content));
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		return docContent;
	}
	
}
