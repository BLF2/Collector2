package net.blf2.service.impl;

import com.google.gson.Gson;
import net.blf2.dao.MongoOperator;
import net.blf2.entity.FormInfo;
import net.blf2.entity.FormResult;
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
    private String collectionForResult = Consts.FORM_RESULT_COLLECTION_NAME;

    public boolean insertFormInfo(FormInfo formInfo){
        if(formInfo == null)
            return false;
        String formInfoJson = gson.toJson(formInfo);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("_id", UUID.randomUUID().toString());
        dataMap.put(Consts.DIY_FORM_INFO,formInfoJson);
        return MongoOperator.insertDocument(databaseName,collectionName,dataMap);
    }
    public boolean deleteFormInfoById(String formId){
        if(formId == null || formId.isEmpty())
            return false;
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("_id",formId);
        return MongoOperator.deleteDocumentById(databaseName,collectionName,dataMap);
    }
    public boolean updateFormInfo(FormInfo formInfo){
        if(formInfo == null)
            return false;
        Map<String,Object>dataMap = new HashMap<>();
        dataMap.put("_id",formInfo.getFormId());
        dataMap.put(Consts.DIY_FORM_INFO,gson.toJson(formInfo));
        return MongoOperator.updateDocument(databaseName,collectionName,dataMap);
    }
    public FormInfo queryFormInfoById(String formId){
        if(formId == null || formId.isEmpty())
            return null;
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
        if(filter == null || filter.isEmpty())
            return null;
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
    public boolean insertOrUpdateFormResult(Map<String,Object>dataMap){
        if(dataMap == null || dataMap.isEmpty() || !dataMap.containsKey("_id"))
            return false;
        Map<String,Object>queryMap = new HashMap<>();
        queryMap.put("_id",dataMap.get("_id"));
        Document document = MongoOperator.findDocumentById(databaseName,collectionForResult,queryMap);
        if(document != null && !document.isEmpty()){
            dataMap.remove("_id");
            document.putAll(dataMap);
            return MongoOperator.insertDocument(databaseName,collectionForResult,document);
        }
        return MongoOperator.updateDocument(databaseName,collectionForResult,dataMap);
    }
    public boolean updateFormResult(Map<String,Object>dataMap){
        if(dataMap == null || dataMap.isEmpty() || !dataMap.containsKey("_id"))
            return false;
        return MongoOperator.updateDocument(collectionName,collectionForResult,dataMap);
    }
    public boolean deleteFormResultById(String formResultId){
        if(formResultId == null || formResultId.isEmpty())
            return false;
        Map<String,Object>queryMap = new HashMap<>();
        queryMap.put("_id",formResultId);
        return MongoOperator.deleteDocumentById(databaseName,collectionForResult,queryMap);
    }
    public boolean deleteFormResultByIds(List<String>formResultIds){
        if(formResultIds == null || formResultIds.size() == 0)
            return false;
        int resultNum = 0;
        for(String formResultId : formResultIds){
            if(this.deleteFormResultById(formResultId))
                resultNum++;
        }
        return resultNum > 0 ? true : false;
    }
}
