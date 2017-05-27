package net.blf2.service.impl;

import com.google.gson.Gson;
import net.blf2.dao.MongoOperator;
import net.blf2.entity.InfoTemplateForm;
import net.blf2.util.Consts;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by blf2 on 17-5-25.
 */
@Service("TemplateService")
public class TemplateService {
    @Autowired
    private Gson gson;

    private String databaseName = Consts.MONGO_DATABASE_NAME;
    private String collectionName = Consts.COLLECTION_FOR_FORM;
    private String collectionForResult = Consts.FORM_RESULT_COLLECTION_NAME;

    public boolean insertInfoTemplateForm(InfoTemplateForm infoTemplateForm){
        if(infoTemplateForm == null)
            return false;
        Map<String,Object>dataMap = new HashMap<>();
        dataMap.put("_id",infoTemplateForm.getTemplateId());
        dataMap.put(Consts.DIY_FORM_INFO,gson.toJson(infoTemplateForm));
        return MongoOperator.insertDocument(databaseName,collectionName,dataMap);
    }
    public boolean deleteInfoTemplateFormById(String _id){
        if(_id == null || _id.isEmpty())
            return false;
        Map<String,Object>delMap = new HashMap<>();
        delMap.put("_id",_id);
        return MongoOperator.deleteDocumentById(databaseName,collectionName,delMap);
    }
    public boolean updateInfoTemplate(InfoTemplateForm infoTemplateForm){
        if(infoTemplateForm == null)
            return false;
        Map<String,Object>updMap = new HashMap<>();
        updMap.put("_id",infoTemplateForm.getTemplateId());
        updMap.put(Consts.DIY_FORM_INFO,gson.toJson(infoTemplateForm));
       return MongoOperator.updateDocument(databaseName,collectionName,updMap);
    }
    public InfoTemplateForm queryInfoTemplateById(String infoTemplateId){
        if(infoTemplateId == null)
            return null;
        Map<String,Object>queryMap = new HashMap<>();
        queryMap.put("_id",infoTemplateId);
        Document queryDocument = MongoOperator.findDocumentById(databaseName,collectionName,queryMap);
        if(queryDocument != null && queryDocument.get(Consts.DIY_FORM_INFO) != null){
            InfoTemplateForm infoTemplateForm = gson.fromJson((String)queryDocument.get(Consts.DIY_FORM_INFO),InfoTemplateForm.class);
            return infoTemplateForm;
        }
        return null;
    }
    public List<InfoTemplateForm> queryInfoTemplateFormAll(){
        List<Document> documentListAll = new LinkedList<>();
        List<InfoTemplateForm> infoTemplateFormAll = new LinkedList<>();
        documentListAll = MongoOperator.findAllDocuments(databaseName,collectionName);
        if(documentListAll != null){
            for(Document document : documentListAll){
                if(document != null && document.get(Consts.DIY_FORM_INFO) != null){
                    infoTemplateFormAll.add(gson.fromJson((String)document.get(Consts.DIY_FORM_INFO),InfoTemplateForm.class));
                }
            }
            return infoTemplateFormAll.size() > 0 ? infoTemplateFormAll : null;
        }
        return null;
    }

}
