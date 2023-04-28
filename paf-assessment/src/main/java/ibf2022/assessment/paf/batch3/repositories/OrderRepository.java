package ibf2022.assessment.paf.batch3.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.print.Doc;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.assessment.paf.batch3.models.BeerSimple;
import ibf2022.assessment.paf.batch3.models.Brewery;

@Repository
public class OrderRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	public String insertOrder(int breweryId, List<Integer> beerQuantities, List<Integer> beerIds) {
		List<BeerSimple> orderBeer = new ArrayList<>();

		for (int i = 0; i < beerQuantities.size(); i++) {
			if (beerQuantities.get(i) != null && beerQuantities.get(i) != 0 ) {
				BeerSimple beerSimple = new BeerSimple();
				beerSimple.setBeerId(beerIds.get(i));
				beerSimple.setQuantity(beerQuantities.get(i));
				orderBeer.add(beerSimple);
			}
		}

		UUID uuid = UUID.randomUUID();
		String uuidString = uuid.toString();
		String id = uuidString.substring(0, 8);

		Document document = new Document()
				.append("orderId", id)
				.append("date", LocalDate.now())
				.append("breweryId", breweryId)
				.append("orders", orderBeer);

		mongoTemplate.insert(document, "order");
		return id;

	}

}
