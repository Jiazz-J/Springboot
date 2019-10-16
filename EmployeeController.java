package com.virtusa.vrps.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.virtusa.vrps.models.Application;
import com.virtusa.vrps.services.ApplicationService;

@Controller
public class EmployeeController {
    
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	HttpSession httpSession;
	
	@GetMapping("/employeeHome")
	public String employeeHome() {
	
	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int employeeId=Integer.parseInt(authentication.getName());
		httpSession.setAttribute("userId", employeeId);
		httpSession.setAttribute("role", authentication.getAuthorities());
		
		
		
		System.out.println(Integer.parseInt(httpSession.getAttribute("userId").toString())+"  name  "+authentication.getAuthorities() );
		return "employeeHome";
	}
	
	 @GetMapping("/jobList")
	   public String joblist(Model model) {
		 if(httpSession.getAttribute("userId")==null)
		    	return "login";
		   long referenceId= applicationService.getApplicationByEmployeeId(Integer.parseInt(httpSession.getAttribute("userId").toString()));
		   System.out.print("referenceId"+referenceId);
		   if(referenceId !=0)
		   {
		   model.addAttribute("referenceId", referenceId);
		   return "applied";
		   }
		   else
		   return "jobList";
	   }
		
	 @GetMapping("/createProfile")
		public String createProfile()
		{
		     System.out.print(httpSession.getAttribute("userId"));
		    if(httpSession.getAttribute("userId")!=null)
			return "createProfile";
		    else 
		    	return "login";
			
		}
	 @GetMapping("/applied")
		public String applied(Model model)
		{
		 if(httpSession.getAttribute("userId")==null)
			    	return "login";
		   long referenceId= applicationService.getApplicationByEmployeeId(Integer.parseInt(httpSession.getAttribute("userId").toString()));
		   System.out.print("referenceId"+referenceId);
		   if(referenceId !=0)
		   {
		   model.addAttribute("referenceId", referenceId);
		   return "applied";
		   }
			return "redirect:applied";
			
		}
		
		

	
	
	
	

}
