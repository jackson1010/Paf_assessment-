package ibf2022.assessment.paf.batch3.repositories;

public class DBQueries {
    public static final String GET_ALL_STYLES = "SELECT styles.id, styles.style_name, COUNT(beers.id) AS beer_count FROM styles LEFT JOIN beers ON styles.id = beers.style_id GROUP BY styles.id ORDER BY beer_count DESC";
    public static final String GET_ALL_BEER_FROM_STYLE = "SELECT b.id AS beer_id, b.name AS beer_name, b.descript AS beer_description, br.id AS brewery_id, br.name AS brewery_name FROM beers b JOIN styles s ON b.style_id = s.id JOIN breweries br ON b.brewery_id = br.id WHERE s.id = ? AND s.style_name = ? ORDER BY beer_name ASC";

    public static final String GET_BREWERY_BY_ID="SELECT breweries.id AS brewery_id, breweries.name AS brewery_name, breweries.descript AS brewery_description, breweries.address1 AS brewery_address, breweries.city AS brewery_city, breweries.phone AS brewery_phone, breweries.website AS brewery_website, beers.id AS beer_id,beers.name AS beer_name, beers.descript AS beer_description FROM breweries JOIN beers ON breweries.id = beers.brewery_id WHERE breweries.id = ? ORDER BY beers.name ASC";


    // "SELECT b.id AS beer_id, b.name AS beer_name, b.descript AS beer_description, br.id AS brewery_id, br.name AS brewery_name FROM beers b JOIN styles s ON b.style_id= s.id JOIN categories c ON s.cat_id= c.id JOIN breweries br ON b.brewery_id= br.id WHERE s.id=? AND s.style_name= ?";

}
