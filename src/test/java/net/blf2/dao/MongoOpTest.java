package net.blf2.dao;

import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * Created by blf2 on 17-4-5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoOpTest {
    @Test
    public void TestMongoOp(){
        String name = "BLF2";
        String phone = "15800499264";

        String name2 = "ECHO";
        String phone2 = "18753377310";

        String dataBaseName = "app";
        String collectionName = "test_data";

        Map<String,Object>dataMap1 = new HashMap<>();
        dataMap1.put("_id",UUID.randomUUID().toString());
        dataMap1.put("name",name);
        dataMap1.put("phone", phone);

        Map<String,Object>dataMap2 = new HashMap<>();
        dataMap2.put("_id", UUID.randomUUID().toString());
        dataMap2.put("name", name2);
        dataMap2.put("phone", phone2);

        MongoOperator.insertDocument(dataBaseName, collectionName, dataMap1);
        MongoOperator.insertDocument(dataBaseName, collectionName, dataMap2);
        Map<String,Object>queryMap1 = new LinkedHashMap<>();
        queryMap1.put("_id", dataMap1.get("_id"));
        Document queryDocument1 = MongoOperator.findDocumentById(dataBaseName, collectionName, queryMap1);
        Assert.assertNotNull(queryDocument1);
        Assert.assertEquals(queryDocument1.getString("_id"), dataMap1.get("_id"));
        Assert.assertEquals(queryDocument1.getString("name"), dataMap1.get("name"));
        Assert.assertEquals(queryDocument1.getString("phone"), dataMap1.get("phone"));

        dataMap2.put("phone", "15800499264");
        MongoOperator.updateDocument(dataBaseName, collectionName, dataMap2);
        Document queryUpdDoc = MongoOperator.findDocumentById(dataBaseName, collectionName, dataMap2);
        Assert.assertNotNull(queryUpdDoc);
        Assert.assertEquals(queryUpdDoc.getString("phone"), dataMap2.get("phone"));

        Map<String,Object>queryMap = new HashMap<>();
        queryMap.put("phone", "15800499264");
        List<Document> documentListByFiter = MongoOperator.findAllDocumentsByFilter(dataBaseName, collectionName, queryMap);
        Assert.assertNotNull(documentListByFiter);
        Assert.assertEquals(documentListByFiter.size(), 2);

        List<Document>documentsAll = MongoOperator.findAllDocuments(dataBaseName,collectionName);
        Assert.assertNotNull(documentsAll);
        Assert.assertEquals(documentsAll.size(), 2);

        MongoOperator.deleteDocumentById(dataBaseName, collectionName, dataMap1);
        MongoOperator.deleteDocumentById(dataBaseName, collectionName, dataMap2);
        List<Document>documentAll = MongoOperator.findAllDocuments(dataBaseName,collectionName);
        Assert.assertNotNull(documentAll);
        Assert.assertEquals(documentAll.size(),0);

    }
}
