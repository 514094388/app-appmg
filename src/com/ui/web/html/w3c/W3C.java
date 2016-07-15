package com.ui.web.html.w3c;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sql.w3c.DbW3C;

/*
 * 作为注释注入的请求处理类，要在类前面添加@Controller，声明该类是注释注入的请求处理类，
 * @RequestMapping("XMLWeb")，声明映射的类路径，理论上该路径可以是任意的，没有硬性规定要使用什么值，
 * 但一般选择与映射的类名、方法名相同，以便日后可以快速地根据访问路径找到对应的处理类。
 */
@Controller
@RequestMapping("W3C")
public class W3C {

	@Autowired
	private DbW3C dbW3C;
	
	
	public DbW3C getDbW3C() {
		return dbW3C;
	}

	public void setDbW3C(DbW3C dbW3C) {
		this.dbW3C = dbW3C;
	}

	private Log log = LogFactory.getLog(this.getClass());
	
	private final String par_id = "2";
	/*
	 * 如果方法结束后返回的内容是一个资源路径，则不需要加入@ResponseBody，
	 * 返回的字符串会自动加上配置文件中配置的前缀和后缀，按照前缀+返回值+后缀作为路径去读取页面显示。
	 * 如果返回的内容要直接返回到客户端，则添加@ResponseBody。
	 */
	
	@RequestMapping("GetW3CContent")
	public String getW3CContent(String fn){
		/**START
		 * 测试代码
		 */
//		if(fn != null && fn.equals("1")){
//			WebClient test_WebClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_8);
//			HtmlPage test_Page_1 = null;
//			Iterable<HtmlElement> iter_Menus = null;
//			Iterator<HtmlElement> itor_1 = null;
//			HtmlElement Ele_1 = null;
//			HtmlElement Ele_2 = null;
//			DomNodeList<HtmlElement> l_a_1 = null;
//			HtmlElement a_1 = null;
//			
//
//			Iterable<HtmlElement> iter_Menus_2 = null;
//			Iterator<HtmlElement> itor_2 = null;
//			
//			HtmlPage test_Page_2 = null;
//			try {
//				String test_Url = "http://www.w3school.com.cn/html/index.asp";
//				test_WebClient.setCssEnabled(false);
//				test_WebClient.setActiveXNative(false);
//				test_WebClient.setAppletEnabled(false);
//				test_Page_1 = test_WebClient.getPage(test_Url);
//				System.out.println("getUrl():" + test_Page_1.getUrl());
//				Ele_1 = test_Page_1.getElementById("course");
//				System.out.println("测试代码获取："+Ele_1.asXml());
//				if(Ele_1.hasHtmlElementWithId("a")){
//					System.out.println("存在Id为a的节点。");
//					a_1 = Ele_1.getElementById("a");
//				}else{
//					System.out.println("不存在Id为a的节点。");
//				}
//				iter_Menus = Ele_1.getChildElements();
//				itor_1 = iter_Menus.iterator();
//				while(itor_1.hasNext()){
//					Ele_2 = itor_1.next();
//					if(Ele_2.getNodeName().equals("ul")){
//						iter_Menus_2 = Ele_2.getChildElements();
//						itor_2 = iter_Menus_2.iterator();
//						while(itor_2.hasNext()){
//							a_1 = itor_2.next().getElementsByTagName("a").get(0);
//							System.out.println("开始打开页面“" + a_1.getTextContent() + "”");
//							test_Page_2 = a_1.click();
//							System.out.println("成功打开页面“" + a_1.getTextContent() + "”");
//							test_Page_2.cleanUp();
//						}
//					}
//				}
//				l_a_1 = test_Page_1.getElementById("course").getElementsByTagName("a");
//				for(int i=0;i<l_a_1.size();i++){
//					a_1 = l_a_1.get(i);
//					System.out.println("开始打开页面“" + a_1.getTextContent() + "”");
//					test_Page_2 = a_1.click();
//					System.out.println("成功打开页面“" + a_1.getTextContent() + "”");
//					test_Page_2.cleanUp();
//				}
//			} catch (FailingHttpStatusCodeException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//				System.out.println("异常FailingHttpStatusCodeException");
//			} catch (MalformedURLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//				System.out.println("异常FailingHttpStatusCodeException");
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//				System.out.println("异常FailingHttpStatusCodeException");
//			}
//		}
		/**END
		 * 测试代码
		 */
		/**STRAT
		 * 正式代码
		 */
		if(fn != null){
			WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_11);
			//HTMLUnit原来使用2.8jar包，现在更改为2.22jar包，备注部分是未修改的
//			webClient.setActiveXNative(true);
//			webClient.setCssEnabled(true);
//			webClient.setAppletEnabled(true);
//			webClient.setThrowExceptionOnScriptError(false);//关闭抛出js脚本的异常，避免页面js脚本运行异常导致程序异常，无法运行。
//			webClient.setJavaScriptEnabled(true);
			//webClient.setJavaScriptTimeout(10000);//页面js脚本运行超过5秒则终止运行，防止页面因js脚本堵塞。
			//webClient.waitForBackgroundJavaScript(10000);
			HtmlPage page_1 = null;
			String url = "http://www.w3school.com.cn";
			DomElement ul_1 = null;
			Iterable<DomElement> iter_Menus = null;
			Iterator<DomElement> itor_1 = null;
			DomElement Ele_1 = null;
			Iterable<DomElement> iter_li_1 = null;
			Iterator<DomElement> itor_li_1 = null;
			DomElement a_1 = null;
			String menu_id = null;
			String menu_id_1 = null;
			
			HtmlPage page_2 = null;
			DomElement ul_2 = null;
			Iterable<DomElement> iter_2 = null;
			Iterator<DomElement> itor_2 = null;
			DomElement Ele_2 = null;
			Iterable<DomElement> iter_li_2 = null;
			Iterator<DomElement> itor_li_2 = null;
			DomElement a_2 = null;
			String menu_id_2 = null;
			
			HtmlPage page_3 = null;
			DomElement mainContent_3 = null;
			try {
				if(fn.equals("1")){
					/**Start
					 * 开始读取W3C全部教程
					 */
						page_1 = webClient.getPage(url);
						ul_1 = page_1.getElementById("navsecond");
						iter_Menus = ul_1.getChildElements();
						itor_1 = iter_Menus.iterator();
						while(itor_1.hasNext()){
							try {
								Ele_1 = itor_1.next();
								if(Ele_1.getNodeName().equals("h2")){
									System.out.println("一级菜单：" + Ele_1.getTextContent());
									menu_id = dbW3C.insertMenu(1,Ele_1.getTextContent(), Ele_1.getAttribute("title"),this.par_id);
								}else if(Ele_1.getNodeName().equals("ul")){
									iter_li_1 = Ele_1.getChildElements();
									itor_li_1 = iter_li_1.iterator();
									while(itor_li_1.hasNext()){
										try {
											a_1 = itor_li_1.next().getElementsByTagName("a").get(0);
											System.out.println("二级菜单：" + a_1.getTextContent());
											menu_id_1 = dbW3C.insertMenu(2,a_1.getTextContent(), a_1.getAttribute("title"), menu_id);
											/**Start
											 * 获取课程目录，保存到Content表中
											 */
											page_2 = a_1.click();
											ul_2 = page_2.getElementById("course");
											iter_2 = ul_2.getChildElements();
											itor_2 = iter_2.iterator();
											while(itor_2.hasNext()){
												Ele_2 = itor_2.next();
												if(Ele_2.getNodeName().equals("h2")){
													System.out.println("三级菜单：" + Ele_2.getTextContent());
													menu_id_2 = dbW3C.insertContent(0,Ele_2.getTextContent(), Ele_2.getAttribute("title"),menu_id_1);
												}else if(Ele_2.getNodeName().equals("ul")){
													iter_li_2 = Ele_2.getChildElements();
													itor_li_2 = iter_li_2.iterator();
													while(itor_li_2.hasNext()){
														try {
															a_2 = itor_li_2.next().getElementsByTagName("a").get(0);
															System.out.println("四级菜单：" + a_2.getTextContent());
															page_3 = a_2.click();
															mainContent_3 = page_3.getElementById("maincontent");
															if(mainContent_3 != null){
//																if(mainContent_3.hasHtmlElementWithId("tpn"))mainContent_3.getElementById("tpn").remove();
//																if(mainContent_3.hasHtmlElementWithId("intro"))mainContent_3.getElementById("intro").remove();
//																if(mainContent_3.hasHtmlElementWithId("bpn"))mainContent_3.getElementById("bpn").remove();
																if(page_3.getElementById("tpn")!=null)page_3.getElementById("tpn").remove();
																if(page_3.getElementById("intro")!=null)page_3.getElementById("intro").remove();
																if(page_3.getElementById("bpn")!=null)page_3.getElementById("bpn").remove();
																dbW3C.insertContent(1,a_2.getTextContent(), a_2.getAttribute("title"), menu_id_2,menu_id_1,menu_id,mainContent_3.asXml());
															}
															page_3.cleanUp();
														} catch (Exception e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}finally{
															a_2 = null;
															page_3 = null;
															mainContent_3 = null;
														}
													}
												}
											}
											page_2.cleanUp();
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}finally{
											page_2 = null;
											ul_2 = null;
											iter_2 = null;
											itor_2 = null;
											Ele_2 = null;
											iter_li_2 = null;
											menu_id_2 = null;
										}
										/**END
										 * 读取课程内容结束
										 */
									}
								}
								page_1.cleanUp();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}finally{
								Ele_1 = null;
								iter_li_1 = null;
								itor_li_1 = null;
								a_1 = null;
								menu_id_1 = null;
							}
						}
					/**End
					 * W3C全部教程读取结束
					 */
				}else if(fn.equals("2")){
					FrameWindow frame1 = null;
					url = "http://localhost:8080/AppMg/WebFrame/Index.dsr";
					url = "http://172.20.4.49:19000/workspace/index.jsp";
					page_1 = webClient.getPage(url);
//					System.out.println(page_1.asXml());
					frame1 = page_1.getFrameByName("Oracle_Enterprise_Performance_Management_System_Workspace__Fusion_Edition_172_20_4_49_19000");
					page_2 = (HtmlPage) frame1.getEnclosedPage();
					System.out.println(page_2.asXml());
//					System.out.println(page_1.getElementsByTagName("script").size());
//					System.out.println(page_1.executeJavaScript(page_1.getElementsByTagName("script").get(1).asXml()));
//					System.out.println(page_1.asXml());
				}
			} catch (FailingHttpStatusCodeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				menu_id = null;
				itor_1 = null;
				iter_Menus = null;
				ul_1 = null;
				url = null;
				webClient = null;
			}
		}
		/**END
		 * 正式代码
		 */
		return "html/w3c/GetW3CContent";
	}
}
