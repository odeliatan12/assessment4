package ibf2022.batch1.csf.assessment.server.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.batch1.csf.assessment.server.models.Comments;
import ibf2022.batch1.csf.assessment.server.utils.Utils;

import static ibf2022.batch1.csf.assessment.server.Constants.*;

@Repository
public class MovieRepository {

	@Autowired
	private MongoTemplate template;

	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	/*
	 * db.comments.find({movieName: ?}).count()
	 */
	public int countComments(String movieName) {

		Query query = Query.query(Criteria.where("movieName").is(movieName));
		Long comment = template.count(query, COLLECTION_COMMENTS);
		Integer result = comment.intValue();
		return result;
	}

	// TODO: Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below
	//
	public Document insertComments(Comments c){
		Document doc = Utils.insertComments(c);
		return template.insert(doc, COLLECTION_COMMENTS);
	}
}
