package requests;

import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.builder.HttpClientRequestActionBuilder;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.Message;
import net.minidev.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;

import javax.management.ObjectName;
import java.io.IOException;
import java.util.*;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class ActionsHttpBases{


    TestNGCitrusTestRunner testRunner;
    public ActionsHttpBases(TestNGCitrusTestRunner testRunner) {
        this.testRunner = testRunner;
    }


    //GET


    public <T> T get(final HttpClient client, String endpoint, String Objectid, final TestContext context, final Class<T> clazz) throws IOException {


        testRunner.http(action -> action.client(client)
                .send()
                .get(endpoint + "/" + Objectid)
                .contentType("application/json")
                .header("From", "PJGA"));

        testRunner.http(action -> action.client(client)
                .receive()
                .response(HttpStatus.OK)
                .name("reponseGet"));


        Message receivedMessage = context.getMessageStore().getMessage("receive(API)");

        // Check if the response is a collection
        String payload = receivedMessage.getPayload(String.class);

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(payload);
            JSONObject jsonObject = new JSONObject((Map<String, ?>) obj);
            return (T) jsonObject;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }



    }


    private JSONObject get(HttpClient client, String endpoint, String objectId, String objectname, TestContext context, Class<JSONObject> jsonObjectClass) {
        try {
            return get(client,endpoint,objectId,context,jsonObjectClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject get(final HttpClient client, final String endpoint, final String ObjectId, final String Objectname,final TestContext context){
        return get(client,endpoint,ObjectId,Objectname, context, JSONObject.class);
    }


    //POST


    public <T> T post(final HttpClient client,final String endpoint, final String filePath,final String objectName, final Map<String,Object> headers,final TestContext context,final Class<T> clazz){
        T reponsePost = null;

        testRunner.http(action -> {
            final HttpClientRequestActionBuilder request = action.client(client)
                    .send()
                    .post(endpoint)
                    .contentType("application/json")
                    .payload(new ClassPathResource(filePath))
                    .header("From", "PJGA");
            if (headers != null) {
                for (Map.Entry<String, Object> entry : headers.entrySet()) {
                    request.header(entry.getKey(), entry.getValue());
                }
            }
        });

        testRunner.http(action -> action.client(client).receive().response().name("reponsePost"));

        String codeRetour = context.getMessageStore().getMessage("reponsePost").getHeader("citrus_http_code").toString();

        boolean codeRetourOk;

        if(codeRetour.equals("200")) {
            reponsePost = context.getMessageStore().getMessage("reponsePost").getPayload(clazz);
            codeRetourOk = true;
        } else if (codeRetour.equals("202")  || codeRetour.equals("204")) {
            codeRetourOk = true;
        } else if (codeRetour.equals("204")) {
            codeRetourOk = true;
        } else {
            codeRetourOk = false;
        }

        assertEquals("Le post de" + objectName + "est en échec :" , true, codeRetourOk);

        return reponsePost;
    }


    public JSONObject post(final HttpClient client,final String endpoint,final String filePath,final String objectName, final Map<String,Object> headers,final TestContext context){
        return post(client,endpoint,filePath,objectName,headers,context,JSONObject.class);
    }

    public JSONObject post(final HttpClient client,final String endpoint,final String filePath,final String objectName,final TestContext context){
        return post(client,endpoint,filePath,objectName,null,context,JSONObject.class);
    }

    public <T> T post(final HttpClient client,final String endpoint,final String filePath,final String objectName, final TestContext context, final Class<T> clazz){
        return post(client,endpoint,filePath,objectName,null,context,clazz);
    }




    //PUT



    public <T> T put(final HttpClient client,final String endpoint,final String ObjectId,final String ObjectName,final Object objectPayload,final Map<String,Object> headers,final TestContext context,final Class<T> clazz){
        T reponsePut=null;

        testRunner.http(action-> {
            final HttpClientRequestActionBuilder request = action.client(client)
                    .send()
                    .put(endpoint + "/" + ObjectId)
                    .contentType("application/json")
                    .header("From","PJGA");
            if(objectPayload instanceof String){
                request.payload((String) objectPayload);
            } else {
                request.payload((ClassPathResource) objectPayload);
                    }
            if(headers != null){
                for (Map.Entry<String,Object> entry : headers.entrySet()){
                    request.header(entry.getKey(),entry.getValue());
                }
            }
        });


        testRunner.http(action->action.client(client)
                .receive()
                .response()
                .name("reponsePut"));

        String codeRetour = context.getMessageStore().getMessage("reponsePut").getHeader("citrus_http_status_code").toString();
        boolean codeRetourOk;

        if(codeRetour.equals("200")){
            reponsePut = context.getMessageStore().getMessage("reponsePut").getPayload(clazz);
            codeRetourOk = true;
        } else if (codeRetour.equals("204")){
            codeRetourOk = true;
        }
        else{
            codeRetourOk = false;
        }

        assertEquals(context.getMessageStore().getMessage("reponsePut").toString(),true,codeRetourOk);
        return reponsePut;
    }


    public JSONObject put(final HttpClient client,final String endpoint,final String ObjectId,final String filePath, final String Objectname,final TestContext context){
        return put(client,endpoint,ObjectId, Objectname,new ClassPathResource(filePath),null, context,JSONObject.class);
    }


    public JSONObject put(final HttpClient client,final String endpoint,final String ObjectId,final JSONObject jsonPut, final String Objectname,final TestContext context){
        return put(client,endpoint,ObjectId, Objectname,jsonPut.toString(),null, context,JSONObject.class);
    }


    public <T> T put(final HttpClient client,final String endpoint,final String ObjectId,final JSONObject jsonPut, final String Objectname,final TestContext context,final Class<T> clazz){
        return put(client,endpoint,ObjectId, Objectname,jsonPut.toString(),null, context,clazz);
    }


    //DELETE


    public void delete(final HttpClient client,final String endpoint, final String objectId, final String objectName, final TestContext context){

        testRunner.http(action -> action.client(client)
                .send()
                .delete(endpoint+"/"+objectId)
                .contentType("application/json")
                .header("From","PJGA"));

        testRunner.http(action -> action.client(client)
                .receive().response().name("retour_delete"));

        String codeRetour = context.getMessageStore().getMessage("retour_delete").getHeader("citrus_http_status_code").toString();

        boolean codeRetourOk;

        if(codeRetour.equals("200") || codeRetour.equals("202") ||codeRetour.equals("204")){
            codeRetourOk = true;
        } else{
            codeRetourOk = false;
        }

        assertEquals("Le delete de " + objectName + " est en échec",true,codeRetourOk);
    }

}
