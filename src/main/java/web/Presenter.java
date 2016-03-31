package web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import business.controllers.CourtController;
import business.wrapper.CourtState;
import data.daos.CourtDao;


@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class Presenter {

	@Autowired
	private CourtDao courtDao;
	
	@Autowired
	private CourtController courtController;

	
	public Presenter() {
    }
	
	@RequestMapping("/home")
	public String home(Model model) {
		return "/home";
	}
	
	@RequestMapping("/court-list")
	public ModelAndView listCourts(Model model) {
		ModelAndView modelAndView = new ModelAndView("/courtList");
		modelAndView.addObject("courtList", courtController.showCourts());
		return modelAndView;
	}
	
	@RequestMapping(value ="/create-court", method = RequestMethod.GET)
	public String createCourt(Model model){
		model.addAttribute("court", new CourtState(courtController.showCourts().size()+1, true));
		return "/createCourt";
	}
	
	@RequestMapping(value = "/create-court", method = RequestMethod.POST)
	public String createCourtSubmit(@ModelAttribute(value="court") CourtState court, Model model){
		courtController.createCourt(court.getCourtId());
		this.createCourt(model);
		return "/createSuccess";

	}
	
	@RequestMapping(value = { "/delete-court/{id}" })
	public String deleteCourt(@PathVariable int id, Model model) {
		courtDao.delete(id);
		model.addAttribute("courtList", courtController.showCourts());
		return "/courtList";
	}
	

	
	
}
