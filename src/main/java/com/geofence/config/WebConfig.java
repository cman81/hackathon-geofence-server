package com.geofence.config;

import com.geofence.model.Fence;
import com.geofence.model.LocationData;
import com.geofence.service.FenceService;
import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;
import org.json.simple.JSONObject;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebConfig {
	
	private FenceService service;
	//private AutoFileSystem autoFileSystem;

	public WebConfig(FenceService service) {
		this.service = service;
		//autoFileSystem = new AutoFileSystem();
		staticFileLocation("/public");
		setupRoutes();
	}
	
	private void setupRoutes() {
		/*
		 * Shows a users timeline or if no user is logged in,
		 *  it will redirect to the public timeline.
		 *  This timeline shows the user's messages as well
		 *  as all the messages of followed users.
		 */

		get("/hello", (req, res) -> "Hello World");

		get("/", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Geofences");
			List<Fence> fences = service.getFences();
			map.put("fences", fences);
			return new ModelAndView(map, "fences.ftl");
		}, new FreeMarkerEngine());
		before("/", (req, res) -> {

		});

		post("/phonematch", (req, res) -> {

			MultiMap<String> params = new MultiMap<String>();
			UrlEncoded.decodeTo(req.body(), params, "UTF-8");

			LocationData d = new LocationData();
			BeanUtils.populate(d, params);

			List<Fence> fences = service.getMatchingFences(d.getLatitude(), d.getLongitude(), d.getRadius());



			if(fences.isEmpty())
				return null;

			//res.body();

			return fences.get(0).getJSON();
			//return;
		});

		post("/match", (req, res) -> {
			Map<String, Object> map = new HashMap<>();

			MultiMap<String> params = new MultiMap<String>();
			UrlEncoded.decodeTo(req.body(), params, "UTF-8");

			LocationData d = new LocationData();
			BeanUtils.populate(d, params);


			map.put("pageTitle", "Matching Geofences");
			List<Fence> fences = service.getMatchingFences(d.getLatitude(), d.getLongitude(), d.getRadius());
			map.put("fences", fences);
			return new ModelAndView(map, "fences.ftl");
		}, new FreeMarkerEngine());


		/*
		 * Registers a new fence.
		 */
		post("/submit", (req, res) -> {
			MultiMap<String> params = new MultiMap<String>();
			UrlEncoded.decodeTo(req.body(), params, "UTF-8");
			Fence f = new Fence();
			BeanUtils.populate(f, params);
			service.submitFence(f);
			res.redirect("/");
			return null;
        });
		/*
		 * Checks if the user is authenticated
		 */
		before("/submit", (req, res) -> {
			// TODO: 4/22/2016 add validation??
		});

		get("/jello", (req, res) -> "Jello World");




	}



	private String renderContent(String htmlFile) {
		try {
			// If you are using maven then your files
			// will be in a folder called resources.
			// getResource() gets that folder
			// and any files you specify.
			URL url = getClass().getResource(htmlFile);

			// Return a String which has all
			// the contents of the file.
			Path path = Paths.get(url.toURI());
			return new String(Files.readAllBytes(path), Charset.defaultCharset());
		} catch (IOException | URISyntaxException e) {
			// Add your own exception handlers here.
		}
		return null;
	}


}
