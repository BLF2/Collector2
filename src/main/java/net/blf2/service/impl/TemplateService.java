package net.blf2.service.impl;

import com.google.gson.Gson;
import net.blf2.dao.MongoOperator;
import net.blf2.entity.*;
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
        List<Document> documentListAll;
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
    public TemplateForPage coverterForTemplate(InfoTemplateForm infoTemplateForm){
        if(infoTemplateForm == null)
            return null;
        TemplateForPage templateForPage = new TemplateForPage();
        templateForPage.setTemplateId(infoTemplateForm.getTemplateId());
        templateForPage.setIntroductionString(infoTemplateForm.getIntroductionString());
        List<TemplateToData> templateToDataList = new LinkedList<>();
        for(InfoTemplate infoTemplate : infoTemplateForm.getInfoTemplateList()){
            TemplateToData templateToData = new TemplateToData();
            templateToData.setItermnName(infoTemplate.getItermName());
            if(infoTemplate.getItermCondition() != null && infoTemplate.getItermCondition().equals("RequiredInformation"))
                templateToData.setRequirement(true);
            String valueClassification = infoTemplate.getValueCondition();
            templateToData.setItermValueClassification(valueClassification);
            if("single".equals(valueClassification) || "multiple".equals(valueClassification)){
                String content = infoTemplate.getConditionValue();
                if(content != null){
                    String[] contentSplit = content.split(",");
                    templateToData.setItermValueList(Arrays.asList(contentSplit));
                }
            }else if("forceKeyValue".equals(valueClassification)){
                String multipleContent = infoTemplate.getConditionValue();
                if(multipleContent != null){
                    String[] singleContentKeyValue = multipleContent.split(",");
                    Map<String,String>kevValueMap = new HashMap<>();
                    for(String keyValue : singleContentKeyValue){
                        String[] keysAndValues = keyValue.split("#");
                        if(keysAndValues.length >= 2){
                            kevValueMap.put(keysAndValues[0],keysAndValues[1]);
                        }
                    }
                    kevValueMap.put("其它","");
                    templateToData.setItermKeyValues(kevValueMap);
                }
            }
            templateToDataList.add(templateToData);
        }
        templateForPage.setTemplateToDataList(templateToDataList);
        return templateForPage;
    }
    public TemplateForPage coverterForTemplateByTemplateId(String templateId){
        InfoTemplateForm infoTemplateForm = this.queryInfoTemplateById(templateId);
        if(infoTemplateForm == null)
            return null;
        return this.coverterForTemplate(infoTemplateForm);
    }
    public List<InfoTemplateForm> queryInfoTemplateFormAllByFilter(Map<String,Object>queryMap){
        List<Document> documentListAll = new LinkedList<>();
        documentListAll = MongoOperator.findAllDocumentsByFilter(databaseName,collectionName,queryMap);
        List<InfoTemplateForm> infoTemplateFormList = new LinkedList<>();
        if(documentListAll != null){
            for(Document document : documentListAll){
                if(document != null && document.get(Consts.DIY_FORM_INFO) != null){
                    infoTemplateFormList.add(gson.fromJson((String)document.get(Consts.DIY_FORM_INFO),InfoTemplateForm.class));
                }
            }
            return infoTemplateFormList.size() > 0 ? infoTemplateFormList : null;
        }
        return null;
    }
    public boolean insertFormResult(FormResult formResult){
        if(formResult == null)
            return false;
        Map<String,Object>resultMap = new HashMap<>();
        resultMap.put("_id",formResult.getTemplateId());
        resultMap.put(Consts.FORM_RESULT_INFO,gson.toJson(formResult));
        MongoOperator.insertDocument(databaseName,collectionForResult,resultMap);
        return true;
    }
    public boolean deleteFromResult(String templateId){
        if(templateId == null)
            return false;
        Map<String,Object>queryMap = new HashMap<>();
        queryMap.put("_id",templateId);
        return MongoOperator.deleteDocumentById(databaseName,collectionForResult,queryMap);
    }
    public FormResult queryFromResult(String templateId){
        if(templateId == null)
            return null;
        Map<String,Object>queryMap = new HashMap<>();
        queryMap.put("_id",templateId);
        Document document =  MongoOperator.findDocumentById(databaseName,collectionForResult,queryMap);
        if(document != null && document.get(Consts.FORM_RESULT_INFO) != null){
            FormResult formResult = gson.fromJson((String)document.get(Consts.FORM_RESULT_INFO),FormResult.class);
            return formResult;
        }
        return null;
    }
    public boolean updateFormResult(FormResult formResult){
        if(formResult == null)
            return false;
        Map<String,Object>resultMap = new HashMap<>();
        resultMap.put("_id",formResult.getTemplateId());
        resultMap.put(Consts.FORM_RESULT_INFO,gson.toJson(formResult));
        return MongoOperator.updateDocument(databaseName,collectionForResult,resultMap);
    }
}
