package com.ensa.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ensa.entity.Carte;
import com.ensa.entity.User;
import com.ensa.service.UserService;

@Controller
public class LoginController implements ErrorController{

	@Autowired
    private UserService userService;
	
	
	private static final String PATH = "/error";

	//Login
    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    //Registration GET
    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }
	//Registration POST
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided go to login page");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("admin/home");

        }
        return modelAndView;
    }
    
    
    
    //home
    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName());
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.addObject("user",user);
        modelAndView.addObject("carte",new Carte());
        
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
    
    
    @RequestMapping(value = PATH)
    public String error() {
     return "error";
    }
    
    @PostMapping("/verification")
	public ModelAndView verification(@Valid Carte carte) {
		ModelAndView modelAndView=new ModelAndView();
		if(carte.getNumero().equals("0000000000000000") && carte.getDateExperation().equals("1111") && carte.getOtherNumbers().equals("222")) {
			
			modelAndView.setViewName("admin/success");
		}
		else {
			modelAndView.setViewName("admin/failed");
		}
		return modelAndView;
	}
	
	@GetMapping("/admin/failed")
	public ModelAndView getSuccess() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("admin/failed");
		return modelAndView;
	}
    
    
    
    
    
    
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return PATH;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
