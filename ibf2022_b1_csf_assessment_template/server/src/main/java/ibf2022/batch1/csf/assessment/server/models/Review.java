package ibf2022.batch1.csf.assessment.server.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

// DO NOT MODIFY THIS CLASS
public class Review {
	// display_title
	private String title;
	// mpaa_rating
	private String rating;
	// byline
	private String byline;
	// headline
	private String headline;
	// summary_short 
	private String summary;
	// link.url
	private String reviewURL;
	// multimedia.src
	private String image = null;

	private int commentCount = 0;

	public void setTitle(String title) { this.title = title; }
	public String getTitle() { return this.title; }

	public void setRating(String rating) { this.rating = rating; }
	public String getRating() { return this.rating; }

	public void setByline(String byline) { this.byline = byline; }
	public String getByline() { return this.byline; }

	public void setHeadline(String headline) { this.headline = headline; }
	public String getHeadline() { return this.headline; }

	public void setSummary(String summary) { this.summary = summary; }
	public String getSummary() { return this.summary; }

	public void setReviewURL(String reviewURL) { this.reviewURL = reviewURL; }
	public String getReviewURL() { return this.reviewURL; }

	public void setImage(String image) { this.image = image; }
	public String getImage() { return this.image; }
	public boolean hasImage() { return null != this.image; }

	public void setCommentCount(int commentCount) { this.commentCount = commentCount; };
	public int getCommentCount() { return this.commentCount; }


	@Override
	public String toString() {
		return "Review{title:%s, rating:%s}".formatted(title, rating);
	}

	public static Review createReview(JsonObject o){
		Review r = new Review();
		r.setTitle(o.getString("display_title"));
		r.setRating(o.getString("mpaa_rating"));
		r.setByline(o.getString("byline"));
		r.setHeadline(o.getString("headline"));
		r.setSummary(o.getString("summary_short"));
		JsonObject link = o.getJsonObject("link");
		r.setReviewURL(link.getString("url"));

		try {
			if( o.getJsonObject("multimedia").containsKey("src") ){
				r.setImage(o.getJsonObject("multimedia").getString("src"));
			} else {
				r.setImage("placeholder.jpg");
			}
		} catch (Exception e) {
			// TODO: handle exception
			r.setImage("placeholder.jpg");
		}
		
		return r;
	}

	public static List<Review> getReviewList(String json) throws IOException{
		List<Review> rev = new LinkedList<>();
		try(InputStream is = new ByteArrayInputStream(json.getBytes())){
			JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
			if(o.getJsonArray("results")!= null){

				System.out.println(o.getJsonArray("results"));
				rev = o.getJsonArray("results").stream()
						.map(v -> (JsonObject)v)
						.map(v -> Review.createReview(v))
						.toList();
			}
		}

		return rev;
	}

	public static JsonObject toJSON(Review r, Integer count){
		return Json.createObjectBuilder()
				.add("display_title", r.getTitle())
				.add("mpaa_rating", r.getRating())
				.add("byline", r.getByline())
				.add("headline", r.getHeadline())
				.add("summary_short", r.getSummary())
				.add("linkUrl", r.getReviewURL())
				.add("multimedia_src", r.getImage())
				.add("count", count)
				.build();
	}
}
