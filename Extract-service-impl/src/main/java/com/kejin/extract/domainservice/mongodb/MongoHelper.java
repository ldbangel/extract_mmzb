package com.kejin.extract.domainservice.mongodb;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class MongoHelper {
	
	public MongoHelper(){
		
	}
	
	public MongoClient getMongoClient() {
		MongoClient mongoClient = null;
		//连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址  
		//ServerAddress()两个参数分别为 服务器地址 和 端口  
		ServerAddress serverAddress = new ServerAddress("193.112.162.248",Integer.parseInt("27017"));  
		List<ServerAddress> addrs = new ArrayList<ServerAddress>();  
		addrs.add(serverAddress);  
	    
		//MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码  
		MongoCredential credential = MongoCredential.createScramSha1Credential("adminuser", "admin", "mmzb123".toCharArray());  
		List<MongoCredential> credentials = new ArrayList<MongoCredential>();  
		credentials.add(credential);  
	    
		//通过连接认证获取MongoDB连接  
		mongoClient = new MongoClient(addrs,credentials);  
		
		return mongoClient;
	}
	
	public MongoDatabase getMongoDataBase(MongoClient mongoClient) {
		MongoDatabase mongoDatabase = null;
		if (mongoClient != null) {
			mongoDatabase = mongoClient.getDatabase("admin");
		}
		return mongoDatabase;
	}
	
	public void closeMongoClient(MongoDatabase mongoDatabase, MongoClient mongoClient) {
		if (mongoDatabase != null) {
			mongoDatabase = null;
		}
		if (mongoClient != null) {
			mongoClient.close();
		}
	}
}
