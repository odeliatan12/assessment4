package ibf2022.batch1.csf.assessment.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch1.csf.assessment.server.models.Comments;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.repositories.MovieRepository;
import ibf2022.batch1.csf.assessment.server.utils.Utils;
import jakarta.json.JsonObject;;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;

	public static final String NYTIMESURL = "https://api.nytimes.com/svc/movies/v2/reviews/search.json";

	@Value("${ny.times.apiKey}")
    private String nyTimesApiKey;

	// TODO: Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	public List<Review> searchReviews(String query) {

		ResponseEntity<String> resp = null;
		List<Review> rev = null;
		System.out.println(nyTimesApiKey);
		String nyTimesUrl = UriComponentsBuilder
			.fromUriString(NYTIMESURL)
			.queryParam("query", query)
			.queryParam("api-key", nyTimesApiKey)
			.toUriString();

		System.out.println(nyTimesUrl);
		RestTemplate template = new RestTemplate();
		resp = template.getForEntity(nyTimesUrl, String.class);
		System.out.println(resp);

		try {
			rev = Review.getReviewList(resp.getBody());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rev;
	}

	public Integer getCount(String payload){
		return repository.countComments(payload);
	}

	public String insertComments(String payload){
		JsonObject o = Utils.tocreateJson(payload);
		System.out.println(o);
		Comments c = Utils.createComments(o);
		System.out.println(c);
		String poster = c.getName();
		repository.insertComments(c);
		return poster;
	}

}
