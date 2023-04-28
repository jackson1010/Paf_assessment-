package ibf2022.assessment.paf.batch3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;
import ibf2022.assessment.paf.batch3.services.BeerService;

@Controller
@RequestMapping
public class BeerController {

	@Autowired
	private BeerService service;

	// TODO Task 2 - view 0
	@GetMapping("/")
	public String showBeerStyles(Model model) {
		List<Style> allBeerStyles = service.showBeerStyles();
		model.addAttribute("styles", allBeerStyles);
		return "view0";
	}

	// TODO Task 3 - view 1
	// http://localhost:8080/beer/style/0?styleName=American-Style%20Pale%20Ale

	@GetMapping("/beer/style/{id}")
	public String showBeerStyles(@PathVariable int id, @RequestParam String styleName, Model model) {
		List<Beer> showBeerStyles = service.getBreweriesByBeer(id, styleName);
		model.addAttribute("beers", showBeerStyles);
		model.addAttribute("beerName", styleName);
		return "view1";
	}

	// TODO Task 4 - view 2
	// http://localhost:8080/brewery/1
	@GetMapping("/brewery/{id}")
	public String showBrewery(@PathVariable int id, Model model) {
		Brewery brewery = service.getBrewery(id).get();

		model.addAttribute("brewery", brewery);
		model.addAttribute("breweryName", brewery.getName());
		model.addAttribute("breweryAddress", brewery.getAddress1() + " " + brewery.getCity());

		return "view2";

	}

	// TODO Task 5 - view 2, place order
	@PostMapping("/brewery/{breweryId}/order")
	public String placeOrder(@PathVariable("breweryId") int breweryId,
			@RequestParam("beerQuantity[]") List<Integer> beerQuantities,
			@RequestParam("beerId[]") List<Integer> beerIds, Model model) {

		String orderID= service.insertOrder(breweryId, beerQuantities, beerIds);
		
		model.addAttribute("orderID", orderID);

		return "view3";

	}

}
