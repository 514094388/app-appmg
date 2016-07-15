package com.ui.web.html;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sql.init.Init;
import com.util.json.JsonHashMap;

/*
 * 作为注释注入的请求处理类，要在类前面添加@Controller，声明该类是注释注入的请求处理类，
 * @RequestMapping("WebFrame")，声明映射的类路径，理论上该路径可以是任意的，没有硬性规定要使用什么值，
 * 但一般选择与映射的类名、方法名相同，以便日后可以快速地根据访问路径找到对应的处理类。
 */
@Controller
@RequestMapping("WebFrame")
public class WebFrame {
	@Autowired
	private Init init;
	public Init getInit() {
		return init;
	}
	public void setInit(Init init) {
		this.init = init;
	}
	private Log log = LogFactory.getLog(this.getClass());
	
	/*
	 * 如果方法结束后返回的内容是一个资源路径，则不需要加入@ResponseBody，
	 * 返回的字符串会自动加上配置文件中配置的前缀和后缀，按照前缀+返回值+后缀作为路径去读取页面显示。
	 * 如果返回的内容要直接返回到客户端，则添加@ResponseBody。
	 */
	@RequestMapping("Index")
	public String index(){
		System.out.println("AppMg类加载器：" + this.getClass().getClassLoader());
		System.out.println("AppMg类加载器1：" + this.getClass().getClassLoader().getParent());
		System.out.println("changs:" + this.getClass().getClassLoader().getSystemClassLoader());
		return "html/Framework";
	}
}
