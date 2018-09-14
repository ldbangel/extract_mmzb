package com.kejin.extract.domainservice.mongodb;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

public class MongodbOperateService{

	public static void insert(String str) {
		MongoHelper mongoHelper = new MongoHelper();
		MongoClient mongoClient = mongoHelper.getMongoClient();
		MongoDatabase mongoDatabase = mongoHelper.getMongoDataBase(mongoClient);
		MongoCollection<DBObject> collections = mongoDatabase.getCollection("inviteRelationship", DBObject.class);
		DBObject bson = (DBObject)JSON.parse(str);
		collections.insertOne(bson);
		mongoHelper.closeMongoClient(mongoDatabase, mongoClient);
	}

	public static String select(String name, String idCard, String phone) {
		MongoHelper mongoHelper = new MongoHelper();
		MongoClient mongoClient = mongoHelper.getMongoClient();
		MongoDatabase mongoDatabase = mongoHelper.getMongoDataBase(mongoClient);
		MongoCollection<Document> collections = mongoDatabase.getCollection("inviteRelationship");		
		BasicDBObject query = new BasicDBObject();
		query.put("verifyName", name);
		query.put("verifyPhone", phone);
		query.put("verifyIdcard", idCard);
		FindIterable<Document> iterable = collections.find(query);
		if(null == iterable) {
			return null;
		}
		
		MongoCursor<Document> cursor = iterable.iterator();
		while(cursor.hasNext()) {
			Document user = cursor.next();
			return user.toJson();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "bruceliu");
		map.put("age", 23);
		String str = JSONObject.toJSONString(map);
		insert(str);
	}

}
