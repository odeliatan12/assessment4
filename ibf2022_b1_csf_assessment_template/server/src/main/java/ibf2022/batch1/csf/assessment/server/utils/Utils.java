package ibf2022.batch1.csf.assessment.server.utils;

import java.io.Reader;
import java.io.StringReader;

import org.bson.Document;

import ibf2022.batch1.csf.assessment.server.models.Comments;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Utils {

    public static Document insertComments(Comments c){
        Document doc = new Document();
        doc.put("movieName", c.getMovieName());
        doc.put("name", c.getName());
        doc.put("rating", c.getRating());
        doc.put("comment", c.getComment());
        return doc;
    }

    public static JsonObject tocreateJson(String str){
        Reader reader = new StringReader(str);
        JsonReader jsonReader = Json.createReader(reader);
        return jsonReader.readObject();
    }

    public static Comments createComments(JsonObject obj){
        Comments c = new Comments();
        c.setMovieName(obj.getString("movieName"));
        c.setName(obj.getString("name"));
        c.setRating(obj.getInt("rating"));
        c.setComment(obj.getString("comment"));
        return c;
    }
    
}
