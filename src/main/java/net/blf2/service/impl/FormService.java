package net.blf2.service.impl;

import com.google.gson.Gson;
import net.blf2.dao.MongoOperator;
import net.blf2.entity.FormInfo;
import net.blf2.util.Consts;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by blf2 on 17-5-4.
 * 自定义表单的操作
 */
@Service("FormService")
public class FormService {
    @Autowired
    private Gson gson;

    private String databaseName = Consts.MONGO_DATABASE_NAME;
    private String collectionName = Consts.COLLECTION_FOR_FORM;

    public boolean insertFormInfo(FormInfo formInfo){
        String formInfoJson = gson.toJson(formInfo);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("_id", UUID.randomUUID().toString());
        dataMap.put(Consts.DIY_FORM_INFO,formInfoJson);
        return MongoOperator.insertDocument(databaseName,collectionName,dataMap);
    }
    public boolean deleteFormInfoById(String formId){
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("_id",formId);
        return MongoOperator.deleteDocumentById(databaseName,collectionName,dataMap);
    }
    public boolean updateFormInfo(FormInfo formInfo){
        Map<String,Object>dataMap = new HashMap<>();
        dataMap.put("_id",formInfo.getFormId());
        dataMap.put(Consts.DIY_FORM_INFO,gson.toJson(formInfo));
        return MongoOperator.updateDocument(databaseName,collectionName,dataMap);
    }
    public FormInfo queryFormInfoById(String formId){
        Map<String,Object>dataMap = new HashMap<>();
        FormInfo formInfo = null;
        dataMap.put("_id",formId);
        Document document = MongoOperator.findDocumentById(databaseName,collectionName,dataMap);
        if(document != null && document.get(Consts.DIY_FORM_INFO) != null){
            formInfo = gson.fromJson((String) document.get(Consts.DIY_FORM_INFO),FormInfo.class);
        }
        return formInfo;
    }
    public List<FormInfo> queryFormInfoAll(){
        return this.queryFormInfoByFilter(null);
    }
    public List<FormInfo> queryFormInfoByFilter(Map<String,Object>filter){
        List<FormInfo> formInfosAll = new LinkedList<>();
        FormInfo formInfo = null;
        List<Document> documentsAll = null;
        if(filter != null)
            documentsAll = MongoOperator.findAllDocumentsByFilter(databaseName,collectionName,filter);
        else
            documentsAll = MongoOperator.findAllDocuments(databaseName,collectionName);
        if(documentsAll == null)
            return null;
        for(Document document : documentsAll){
            if(document != null && document.get(Consts.DIY_FORM_INFO) != null){
                formInfo = gson.fromJson((String) document.get(Consts.DIY_FORM_INFO),FormInfo.class);
            }
            if(formInfo != null)
                formInfosAll.add(formInfo);
            formInfo = null;
        }
        return formInfosAll.isEmpty() ? null : formInfosAll;
    }
}
