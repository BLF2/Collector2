package net.blf2.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by blf2 on 17-4-5.
 */
public class MongoOperator {
    public static boolean insertDocument(String databaseName,String collectionName,Map<String,Object> dataMap){
        MongoCollection mongoCollection = MongoDbDriver.getMongoCollectionByName(databaseName, collectionName);
        Document document = new Document(dataMap);
        try {
            mongoCollection.insertOne(document);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean deleteDocumentById(String databaseName,String collectionName,Map<String,Object>deleteDataMap){
        MongoCollection mongoCollection = MongoDbDriver.getMongoCollectionByName(databaseName,collectionName);
        Document query = new Document(deleteDataMap);
        try {
            mongoCollection.findOneAndDelete(query);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean updateDocument(String databaseName,String collectionName,Map<String,Object>updateDataMap){
        MongoCollection mongoCollection = MongoDbDriver.getMongoCollectionByName(databaseName,collectionName);
        Document query = new Document();
        query.put("_id",updateDataMap.get("_id"));
        try {
            mongoCollection.replaceOne(query, updateDataMap);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public static Document findDocumentById(String databaseName,String collectionName,Map<String,Object>queryDataMap){
        MongoCollection<Document> mongoCollection = MongoDbDriver.getMongoCollectionByName(databaseName,collectionName);
        Document query = new Document();
        query.put("_id",queryDataMap.get("_id"));
        FindIterable<Document> findIterable;
        try {
            findIterable = mongoCollection.find(query);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        MongoCursor<Document> cursor = findIterable.iterator();
        if(cursor == null)
            return null;
        List<Document> documentList = new LinkedList<Document>();
        while (cursor.hasNext()){
            documentList.add(cursor.next());
        }
        return documentList.size() > 0 ? documentList.get(0) : null;
    }

    public static List<Document> findAllDocuments(String databaseName,String collectionName){
        MongoCollection<Document> mongoCollection = MongoDbDriver.getMongoCollectionByName(databaseName,collectionName);
        FindIterable<Document>findIterable;
        try {
            findIterable = mongoCollection.find();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        MongoCursor<Document> cursor = findIterable.iterator();
        if(cursor == null)
            return null;
        List<Document>documentList = new LinkedList<Document>();
        while (cursor.hasNext()){
            documentList.add(cursor.next());
        }
        return documentList;
    }
    public static List<Document> findAllDocumentsByFilter(String databaseName,String collectionName,Map<String,Object>queryDataMap){
        MongoCollection<Document> mongoCollection = MongoDbDriver.getMongoCollectionByName(databaseName,collectionName);
        Document query = new Document(queryDataMap);
        FindIterable<Document>findIterable;
        try {
            findIterable = mongoCollection.find(query);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        MongoCursor<Document> cursor = findIterable.iterator();
        if(cursor == null)
            return null;
        List<Document>documentList = new LinkedList<Document>();
        while (cursor.hasNext()){
            documentList.add(cursor.next());
        }
        return documentList;
    }
}
