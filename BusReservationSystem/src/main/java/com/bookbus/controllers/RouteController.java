package com.bookbus.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookbus.exceptions.LogException;
import com.bookbus.exceptions.RouteException;
import com.bookbus.models.Route;
import com.bookbus.services.RouteService;

@RestController
public class RouteController {
	
	@Autowired
	private RouteService routeService;

	@PostMapping("/route/admin")
	public ResponseEntity<Route> addRoute(@Valid @RequestBody Route route,@RequestParam(required = false) Integer adminId) throws RouteException, LogException{
		
		Route newRoute= routeService.addRoute(route, adminId);
		
		return new ResponseEntity<Route>(newRoute,HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/route/admin/{routeId}")
	public ResponseEntity<Route> DeleteRoute(@PathVariable("routeId") Integer routeId,@RequestParam(required = false) Integer adminId) throws RouteException, LogException{
		
		Route route = routeService.deleteRoute(routeId, adminId);
		
		return new ResponseEntity<Route>(route,HttpStatus.GONE);
	}
	
	@PutMapping("/route/admin")
	public ResponseEntity<Route> updateRoute(@Valid @RequestBody Route route,@RequestParam(required = false) Integer adminId) throws RouteException, LogException{
		
		Route newRoute= routeService.updateRoute(route, adminId);
		
		return new ResponseEntity<Route>(newRoute,HttpStatus.OK);
	}
	
	@GetMapping("/routes")
	public ResponseEntity<List<Route>> getAllRoutes() throws RouteException{
		
		List<Route> routes = routeService.viewAllRoute();
		
		return new ResponseEntity<List<Route>>(routes,HttpStatus.OK);
	}

	
	@GetMapping("/route/{routeId}")
	public ResponseEntity<Route> getRouteById(@PathVariable("routeId") Integer routeId) throws RouteException{
		
		Route route = routeService.viewRoute(routeId);
		
		return new ResponseEntity<Route>(route,HttpStatus.OK);
	}
	
	
	
}
