package com.Int.evs.controller;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import com.Int.evs.bean.CredentialsBean;
import com.Int.evs.bean.ProfileBean;
import com.Int.evs.service.TrialService;
import com.Int.evs.util.Authentication;
import com.Int.evs.util.User;


@RestController
@RequestMapping("")
public class GeneralController {
	
	@Autowired TrialService ts;
	@Autowired User user;
	@Autowired Authentication userAuth; 
	
	@GetMapping("/login")
	public ModelAndView meth1() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value ="/profile", method = RequestMethod.POST)
	public RedirectView meth6(@ModelAttribute("mapping1Form")CredentialsBean credentialsBean,final Model model, final RedirectAttributes redirectAttributes){
		ModelAndView mv = new ModelAndView();
		
		String userType = user.login(credentialsBean);
		if (userType.equals("X"))
			{
				System.out.println("LoginFailed");
			}
		else {
			
		if (userType.equals("A")) {	
		    System.out.println(credentialsBean.getUserId());
		    redirectAttributes.addFlashAttribute("currentUser", credentialsBean);
		    System.out.println("inside/profile");
		    System.out.println(credentialsBean.getUserId());
		    System.out.println(credentialsBean.getPassword());
		    System.out.println(credentialsBean.getUserType());
		    return new RedirectView("/admin");
		    
		}
		else if (userType.equals("E")) {
		    System.out.println(credentialsBean.getUserId());
		    redirectAttributes.addFlashAttribute("currentUser", credentialsBean);
		    System.out.println("inside/profile");
		    System.out.println(credentialsBean.getUserId());
		    System.out.println(credentialsBean.getPassword());
		    System.out.println(credentialsBean.getUserType());
		    return new RedirectView("/eo");
			//mv.setViewName("//eo//profile");
		}
		else if (userType.equals("V")) {
		    System.out.println(credentialsBean.getUserId());
		    redirectAttributes.addFlashAttribute("currentUser", credentialsBean);
		    System.out.println("inside/profile");
		    System.out.println(credentialsBean.getUserId());
		    System.out.println(credentialsBean.getPassword());
		    System.out.println(credentialsBean.getUserType());
		    return new RedirectView("/voter");
			//mv.setViewName("//voter//profile");
		}
	}
		return new RedirectView("");
	}

	@PostMapping("/logout")
	public String logout(@RequestParam("userId") String userId,@RequestParam("password") String password) {
		return String.valueOf(user.logout(userId));
	}
	
	@PostMapping("/changePassword")
	public String Passsword(@ModelAttribute("credentialsBean") CredentialsBean credentialsBean,@RequestParam("newPassword") String newPassword) {
		user.changePassword(credentialsBean,newPassword);
		return "Success";
	}
	
	@GetMapping("/registerView")
	public ModelAndView meth5() {
		return new ModelAndView("register");
	}
	
	@PostMapping("/register")
	public RedirectView meth6(@ModelAttribute("profile") ProfileBean profileBean, final Model model, final RedirectAttributes redirectAttributes) {
		String temp = user.register(profileBean);
		System.out.println(temp);
		CredentialsBean cb_temp = new CredentialsBean();
		cb_temp.setUserId(profileBean.getUserId());
		cb_temp.setPassword(profileBean.getPassword());
		cb_temp.setUserType("V");
		cb_temp.setLoginStatus(1);
		String userType = user.login(cb_temp);
	    redirectAttributes.addFlashAttribute("currentUser", profileBean);
	    return new RedirectView("/voter");
		
	}
	@PostMapping("/voter_auth")
	public String voter_auth(@RequestParam("userId") String userId,@RequestParam("password") String password) {
		
		String userType="V";
		return userAuth.authenticate(userId, password,userType);
	}
	@PostMapping("/admin_auth")
	public String admin_auth(@RequestParam("userId") String userId,@RequestParam("password") String password) {
		String userType="A";
		return userAuth.authenticate(userId, password,userType);
	}
	@PostMapping("/eo_auth")
	public String eo_auth(@RequestParam("userId") String userId,@RequestParam("password") String password) {
		String userType="E";
		return userAuth.authenticate(userId, password,userType);
	}
	
//	@PostMapping(path = "/profile",produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> addEmployee(@ModelAttribute("credentials")CredentialsBean credentialsBean,HttpServletResponse httpServletResponse) 
//	    {       
//			String userType = user.login(credentialsBean);
//			URI location=null;
//			if (userType.equals("X"))
//				{
//					//mv.setViewName("error");
//				System.out.println("LoginFailed");
//				}
//			else {
//				//httpSession=req.getSession();
//				//httpSession.setAttribute("user", credentialsBean);
//				String userId = credentialsBean.getUserId();
//				if (userType.equals("A")) {
//			        //ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(userId).toUri();
//			        
//					location = ServletUriComponentsBuilder.fromHttpUrl("http://localhost:8076/admin").build().toUri();
//			        System.out.println(location);
//			        httpServletResponse.setHeader("Location", "/admin");
//				    httpServletResponse.setStatus(302);
//				}
//				else if (userType.equals("E")) {
//					//mv.setViewName("//eo//profile");
//				}
//				else if (userType.equals("V")) {
//					//mv.setViewName("//voter//profile");
//				}
//			}
//			return ResponseEntity.created(location).build().ok(credentialsBean);
//	    }
	
//	@GetMapping("/profile")
//	public ModelAndView meth3() {
//		return new ModelAndView("profile");
//		ModelAndView mv = new ModelAndView();
//		CredentialsBean temp=null;
//		try {
//		temp = (CredentialsBean)httpSession.getAttribute("user");
//		}
//		catch(Exception e) {
//			System.out.println(e.toString());
//		}
//		mv.addObject("credentialsBean", temp);
//		return mv;
//	}

//	@PostMapping("/add")
//	public ModelAndView meth2(@ModelAttribute("credentials")CredentialsBean credentialsBean) {
//		ts.saveUser(credentialsBean);
//		return new ModelAndView("inserted");
//	}
}
