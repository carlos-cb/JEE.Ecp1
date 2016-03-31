package web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import data.daos.CourtDao;
import data.entities.Court;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class Presenter {

	@Autowired
	private CourtDao courtDao;
	
	@Autowired
	private CourtService courtService;
	
	public Presenter() {
    }
	
	@RequestMapping("/home")
	public String home(Model model) {
		return "/home";
	}
	
	@RequestMapping("/court-list")
	public ModelAndView listCourts(Model model) {
		ModelAndView modelAndView = new ModelAndView("/courtList");
		modelAndView.addObject("courtList", courtService.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value ="/create-court", method = RequestMethod.GET)
	public String createCourt(Model model){
		model.addAttribute("court", new Court(courtService.generateId()));
		return "/createCourt";
	}
	
	@RequestMapping(value = "/create-court", method = RequestMethod.POST)
	public String createCourtSubmit(@ModelAttribute("court") Court court, BindingResult bindingResult, Model model){
		if (!bindingResult.hasErrors()) {
            if (courtService.save(court)) {
                model.addAttribute("active", court.isActive());
                return "/createSuccess";
            } else {
                bindingResult.rejectValue("id", "error.court", "Pista ya existente");
            }
        }
        return "/createCourt";
    }
	
	@RequestMapping(value = { "/delete-court/{id}" })
	public String deleteCourt(@PathVariable int id, Model model) {
		courtService.delete(id);
		model.addAttribute("courtList", courtService.findAll());
		return "/courtList";
	}
	

	
	
}
