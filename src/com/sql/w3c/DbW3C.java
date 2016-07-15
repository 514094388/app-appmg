package com.sql.w3c;

import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sql.DbUtil;

public class DbW3C{
	
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

	private DbUtil dbUtil_zhxy;
	
	public DbUtil getDbUtil_zhxy() {
		return dbUtil_zhxy;
	}
	public void setDbUtil_zhxy(DbUtil dbUtil_zhxy) {
		this.dbUtil_zhxy = dbUtil_zhxy;
	}
	
	public void insertMenu(String menu){
		try {
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String insertMenu(int lev_menu,String menu,String title,String par_id){
		Object args[] = null;
		String id = null;
		String href = null;
		int seq;
		try {
			id = dbUtil_zhxy.nextHex("SYS_MENUS", "ID");
			seq = dbUtil_zhxy.next("SYS_MENUS","SEQUENCE","PAR_ID = '" + par_id + "'");
			switch(lev_menu){
			case 1:href = "WebFrame/Index.dsr?mid=" + id;break;
			case 2:href = "WebFrame/Index.dsr?mid=" + par_id + "&id=" + id;break;
			}
			args = new Object[]{id,href,menu,title,par_id,seq};
			this.dbop_zhxy.update("insert into SYS_MENUS(ID,HREF,NAME,TITLE,PAR_ID,SEQUENCE) VALUES(?,?,?,?,?,?)", args);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	public String insertContent(int lev_menu,String name,String title,String menu_id){
		Object args[] = null;
		String id = null;
		String href = null;
		int seq = 1;
		try {
			id = dbUtil_zhxy.nextHex("CONTENT", "ID");
			switch(lev_menu){
			case 0:seq = dbUtil_zhxy.next("CONTENT","SEQUENCE","MENU_ID = '" + menu_id + "'");;break;
			case 1:seq = dbUtil_zhxy.next("CONTENT","SEQUENCE","PAR_ID = '" + menu_id + "'");;break;
			}
			href = "WebFrame/Index.dsr?id=" + menu_id + "&cid=" + id;
			args = new Object[]{id,href,title,name,menu_id,seq};
			switch(lev_menu){
			case 0:this.dbop_zhxy.update("insert into CONTENT(ID,HREF,TITLE,CONTENT,NAME,MENU_ID,SEQUENCE) VALUES(?,?,?,EMPTY_CLOB(),?,?,?)", args);break;
			case 1:this.dbop_zhxy.update("insert into CONTENT(ID,HREF,TITLE,CONTENT,NAME,PAR_ID,SEQUENCE) VALUES(?,?,?,EMPTY_CLOB(),?,?,?)", args);break;
			}
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public String insertContent(int lev_menu,String name,String title,String par_id,String menu_id,String mid,String content){
		Object args[] = null;
		String id = null;
		String href = null;
		int seq = 1;
		try {
			id = dbUtil_zhxy.nextHex("CONTENT", "ID");
			switch(lev_menu){
			case 0:seq = dbUtil_zhxy.next("CONTENT","SEQUENCE","MENU_ID = '" + menu_id + "'");break;
			case 1:seq = dbUtil_zhxy.next("CONTENT","SEQUENCE","PAR_ID = '" + par_id + "'");break;
			}
			href = "WebFrame/Index.dsr?id=" + menu_id + "&cid=" + id + "&mid=" + mid;
			switch(lev_menu){
			case 0:
				args = new Object[]{id,href,title,content,name,menu_id,seq};
				this.dbop_zhxy.update("insert into CONTENT(ID,HREF,TITLE,CONTENT,NAME,MENU_ID,SEQUENCE) VALUES(?,?,?,?,?,?,?)", args);break;
			case 1:
				args = new Object[]{id,href,title,content,name,par_id,seq};
				this.dbop_zhxy.update("insert into CONTENT(ID,HREF,TITLE,CONTENT,NAME,PAR_ID,SEQUENCE) VALUES(?,?,?,?,?,?,?)", args);break;
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
}
