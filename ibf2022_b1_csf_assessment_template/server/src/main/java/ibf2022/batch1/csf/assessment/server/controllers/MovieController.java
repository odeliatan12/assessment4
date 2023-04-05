package ibf2022.batch1.csf.assessment.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.services.MovieService;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

	@Autowired
	private MovieService service;
	
	// TODO: Task 3, Task 4, Task 8
	@GetMapping(path = "/api/search")
	public ResponseEntity<String> getMovieReviewList(
		@RequestParam String query
	){
		JsonArray arr = null;
		List<Review> rev = service.searchReviews(query);
		System.out.println(rev);
		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		for(Review r : rev){
			String movieName = r.getTitle();
			Integer result = service.getCount(movieName);
			arrBuilder.add(Review.toJSON(r, result));
		}
		arr = arrBuilder.build();

		return ResponseEntity
			.status(HttpStatus.OK)
			.contentType(MediaType.APPLICATION_JSON)
			.body(arr.toString());
	}

	@PostMapping(path = "/api/comment")
	public ResponseEntity<String> insertComments(@RequestBody String payload){
		
		String result = service.insertComments(payload);
		return ResponseEntity
			.status(HttpStatus.OK)
			.contentType(MediaType.APPLICATION_JSON)
			.body(result);
	}
	

}
