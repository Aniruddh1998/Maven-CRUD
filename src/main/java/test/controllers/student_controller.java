package test.controllers;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import test.dao.studentdao;
import test.deans.student;


@Controller
public class student_controller {
	/*
	@RequestMapping(value="/addition", method=RequestMethod.POST)
	public void add(HttpServletRequest request, HttpServletResponse response) {
		int n1 = Integer.parseInt(request.getParameter("num1"));
		int n2 = Integer.parseInt(request.getParameter("num2"));
		int result;
		
		result = n1 + n2;
		
		System.out.println("Addition = "+result);
	}*/
	
	@Autowired
	studentdao dao;
	
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String registration(@ModelAttribute("stud")student stud){
		dao.save(stud);
		
		return "redirect:/welcome"; //page URL
	}
	
	@RequestMapping("/welcome")
	public String wel(Model m) {//We use Model to pass data to other page
		
		List<student> list = dao.getallstudents();
		m.addAttribute("list",list);//passing data while loading welcome.jsp in the form of list
		return "welcome"; //welcome.jsp (page name)
	}
	
	@RequestMapping(value="/deletestud/{id}",method=RequestMethod.GET)
	public String deletestud(@PathVariable int id) { //@PathVariable is used to get value from URL
		dao.delete(id);
		
		return "redirect:/welcome";
	}
	
	@RequestMapping(value="/editstud/{id}",method=RequestMethod.GET)
	public String deletestud(@PathVariable int id,Model m) { //@PathVariable is used to get value from URL
		student s = dao.getstudent(id);
		m.addAttribute("command",s);
		return "edit";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updatestud(@ModelAttribute("stud")student stud){
		dao.update(stud);
		
		return "redirect:/welcome"; //page URL
		
	}
	
	@RequestMapping("/file")
	public String file() {
		return "file"; //file.jsp
	}
	
	@RequestMapping(value="/file_upload",method=RequestMethod.POST)
	public String file_upload(@RequestParam CommonsMultipartFile file) { //CommonsMultipartFile is a data type used to store files
		
		String path="I:\\Class Work\\Serv\\Maven_Demo\\src\\main\\webapp\\WEB-INF\\img";
		String filename=file.getOriginalFilename();
		
		//System.out.println("Path="+path);
		//System.out.println("Filename="+filename);
		
		try {
			byte arr[]=file.getBytes();
			BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(path+"/"+filename));
			
			bout.write(arr);
			bout.flush();
			bout.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login"; //login.jsp
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login_check(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {

		student s = dao.login(email,password);
		if(s!=null) {
			session.setAttribute("username", email); //session start
			return "redirect:/dashboard"; //page URL
		}
		else {
			return "redirect:/login"; //page URL
		}
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(HttpServletRequest request) {
		HttpSession sess = request.getSession(false);
		String email=(String) sess.getAttribute("username");
		if(sess.getAttribute("username")==null){ 
			return "redirect:/login";
		}
		return "dashboard";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession sess = request.getSession(false);
		sess.invalidate(); //session end
		return "redirect:/login"; //login.jsp
	}
	
	@RequestMapping("/cookies")
	public String setcookie(HttpServletRequest request, HttpServletResponse response) {
		//Cookie c = new Cookie("name","value");
		//response.addCookie(c);
		return "cookies";
	}
	
}
