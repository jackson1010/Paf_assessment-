package ibf2022.assessment.paf.batch3.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;

@Repository
public class BeerRepository {

	@Autowired
	private JdbcTemplate sqlTemplate;

	// DO NOT CHANGE THE SIGNATURE OF THIS METHOD
	public List<Style> getStyles() {
		SqlRowSet rs = sqlTemplate.queryForRowSet(DBQueries.GET_ALL_STYLES);
		List<Style> allStyles = new ArrayList<>();

		while (rs.next()) {
			Style style = new Style();
			style.setName(rs.getString("style_name"));
			style.setBeerCount(rs.getInt("beer_count"));
			style.setStyleId(rs.getInt("id"));
			allStyles.add(style);
		}
		return allStyles;
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public List<Beer> getBreweriesByBeer(int id, String styleName) {
		SqlRowSet rs = sqlTemplate.queryForRowSet(DBQueries.GET_ALL_BEER_FROM_STYLE, id, styleName);

		List<Beer> allBeer = new ArrayList<>();
		while (rs.next()) {
			Beer beer = new Beer();
			beer.setBeerId(rs.getInt("beer_id"));
			beer.setBeerName(rs.getString("beer_name"));
			beer.setBeerDescription(rs.getString("beer_description"));
			beer.setBreweryId(rs.getInt("brewery_id"));
			beer.setBreweryName(rs.getString("brewery_name"));
			;
			allBeer.add(beer);
		}
		return allBeer;
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public Optional<Brewery> getBeersFromBrewery(int id) {
		SqlRowSet rs = sqlTemplate.queryForRowSet(DBQueries.GET_BREWERY_BY_ID, id);

		Brewery brewery = new Brewery();
		List<Beer> allBeer = new ArrayList<>();
		while (rs.next()) {
			Beer beer = new Beer();
			beer.setBeerId(rs.getInt("beer_id"));
			beer.setBeerDescription(rs.getString("beer_description"));
			beer.setBeerName(rs.getString("beer_name"));
			allBeer.add(beer);

			brewery.setBreweryId(rs.getInt("brewery_id"));
			brewery.setName(rs.getString("brewery_name"));
			brewery.setDescription(rs.getString("brewery_description"));
			brewery.setAddress1(rs.getString("brewery_address"));
			brewery.setCity(rs.getString("brewery_city"));
			brewery.setPhone(rs.getString("brewery_phone"));
			brewery.setWebsite(rs.getString("brewery_website"));
		}
		brewery.setBeers(allBeer);
		return Optional.of(brewery);
	}
}
