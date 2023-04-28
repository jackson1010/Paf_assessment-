package ibf2022.assessment.paf.batch3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;
import ibf2022.assessment.paf.batch3.repositories.BeerRepository;
import ibf2022.assessment.paf.batch3.repositories.OrderRepository;

@Service
public class BeerService {

	@Autowired
	private BeerRepository beerRepo;

	@Autowired
	private OrderRepository orderRepo;

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public String placeOrder(/* You can add any number parameters here */) {
		// TODO: Task 5

		return "";
	}

	public List<Style> showBeerStyles() {
		return beerRepo.getStyles();
	}

	public List<Beer> getBreweriesByBeer(int id, String styleName) {
		return beerRepo.getBreweriesByBeer(id, styleName);
	}

	public Optional<Brewery> getBrewery(int id) {
		return beerRepo.getBeersFromBrewery(id);
	}

	public String insertOrder(int breweryId, List<Integer> beerQuantities, List<Integer> beerIds) {
		return orderRepo.insertOrder(breweryId, beerQuantities,beerIds);
	}


}
