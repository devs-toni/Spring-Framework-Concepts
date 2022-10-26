package es.local.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.local.spring.entities.User;
import es.local.spring.service.UserService;

@Controller
public class AppController {

	@Autowired
	UserService userService;

	@GetMapping("/")
	public String process() {
		return "index";
	}

	@PostMapping("/persistMessage")
	public String persistMessage(@RequestParam("msg") String msg, HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<String> sesion = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
		if (sesion == null) {
			sesion = new ArrayList<>();
			request.getSession().setAttribute("MY_SESSION_MESSAGES", sesion);
		}
		sesion.add(msg);
		request.getSession().setAttribute("MY_SESSION_MESSAGES", sesion);
		return "redirect:/home";
	}

	@PostMapping("/destroy")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

	@GetMapping("/home")
	public String home(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<String> sesion = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if (sesion == null) {
			sesion = new ArrayList<>();
		}

		model.addAttribute("sessionMessages", sesion);
		model.addAttribute("sessionId", session.getId());
		return "home";
	}

	@GetMapping("/login")
	public String login(HttpSession session, HttpServletRequest request) {

		return "login";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/sendregister")
	public String sendRegister(@Valid User user, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			userService.save(user);

		} else {
			model.addAttribute("error", true);
			return "register";
		}
		return "redirect:/login";

	}
	
	@GetMapping("/sesionManager")
	public String crearSesion (HttpServletRequest request) {
		HttpSession sesion = request.getSession();
		User user = userService.findByUsername(request.getUserPrincipal().getName());
		System.out.println(user.toString());
		request.getSession().setAttribute("usuario", user);	
		return "/register";
	}
}
