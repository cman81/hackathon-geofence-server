package com.geofence;

import com.geofence.config.*;
import com.geofence.model.Fence;
import com.geofence.service.FenceService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan({ "com.geofence" })
public class App {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
        new WebConfig(ctx.getBean(FenceService.class));
        ctx.registerShutdownHook();
    }

    //connectionUrl: location of json file
    static List<Map<String, Object>> parseJsonFromUrl(String connectionUrl) {
        try {
            URLConnection request = new URL(connectionUrl).openConnection();
            request.connect();
            return (List) new JSONParser().parse(new InputStreamReader((InputStream) request.getContent()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    static JSONObject getJSONObject(Fence f) {
        JSONObject obj = new JSONObject();

        obj.put("name", f.getName());
        obj.put("type", f.getType());

        return obj;
    }
}