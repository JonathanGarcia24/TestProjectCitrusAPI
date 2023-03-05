package requests;

import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.Message;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONUtil;
import net.minidev.json.JSONValue;
import org.eclipse.jetty.util.ajax.JSON;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.*;

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



}
