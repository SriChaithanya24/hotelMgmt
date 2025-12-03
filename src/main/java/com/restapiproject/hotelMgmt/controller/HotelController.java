package com.restapiproject.hotelMgmt.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restapiproject.hotelMgmt.dto.HotelDto;
import com.restapiproject.hotelMgmt.exception.ResourceNotFoundException;
import com.restapiproject.hotelMgmt.model.Hotel;
import com.restapiproject.hotelMgmt.service.HotelService;

import jakarta.validation.Valid;

@RestController // marking this class as a REST Controller, 
                // automatically convert responses into JSON 

@RequestMapping("/api/hotels") // Sets a base URL for all endpoints in this class
public class HotelController {
	
	
	private final HotelService hotelService;
	public HotelController(HotelService hotelService) {
		this.hotelService = hotelService;
	}
	
	// helper functions
	// dtoEntity - Entity Hotel
	private Hotel dtoToEntity(HotelDto dto) {
		Hotel h = new Hotel();
		h.setId(dto.getId());
		h.setName(dto.getName());
		h.setAddress(dto.getAddress());
		h.setTotal_rooms(dto.getTotal_rooms());
		h.setAvailable_rooms(dto.getAvailable_rooms());
		h.setPrice_per_night(dto.getPrice_per_night());
		
		return h;
	}
	
	//entityToDto 
	private HotelDto entityToDto(Hotel h) {
		HotelDto dto = new HotelDto();
		dto.setId(h.getId());
		dto.setName(h.getName());
		dto.setAddress(h.getAddress());
		dto.setTotal_rooms(h.getTotal_rooms());
		dto.setAvailable_rooms(h.getAvailable_rooms());
		dto.setPrice_per_night(h.getPrice_per_night());
		
		return dto;		
	}
	
	// GET request
	@GetMapping
	public ResponseEntity<List<HotelDto>> getAll() {
		List<Hotel> list = hotelService.getAllHotels();  //fetch that from db
		// converting list of hotel entities into corresponding Dtos
		List<HotelDto> dtoList = list.stream()
				                .map(this::entityToDto)
				                .collect(Collectors.toList());
		// return HTTP response
		// ResponseEntity - spring wrapper for http response
		// allows you to set status code, headers, body - dto objects
		return ResponseEntity.ok(dtoList);    // 200 OK along with JSON list
	}
	
	// GET request for specific hotel -id
	// /api/hotels/1
    // @PathVariable - tell spring to extract id 
	// from url and assign it to the id variable
	// /api/hotels/4
	
	
	@GetMapping("/{id}")
	public ResponseEntity<HotelDto> getById(@PathVariable Long id) {
		Hotel h = hotelService.getHotelById(id);
		
		return ResponseEntity.ok(entityToDto(h));
		

		// HotelDto dto = entityToDto(h);
		// return ResponseEntity.ok(dto);
	}
	
	// @RequestBody - tells the spring to take data from request body
	// give data in request body -> JSON
	// take data - into dto
	// deserialize it itno Java object
	// @Valid -> spring should check for the non empty values
	// validation fails -> 400 Bad Request
	@PostMapping
	
	public ResponseEntity<HotelDto> create(@Valid @RequestBody HotelDto dto) {
		
		Hotel h = dtoToEntity(dto);
		Hotel created = hotelService.createHotel(h);
		return ResponseEntity.created(URI.create("/api/hotels/" + created.getId()))
				             .body(entityToDto(created));
	}
	
	// Put Request
	@PutMapping("/{id}")
	public ResponseEntity<HotelDto> update(@PathVariable Long id, @Valid @RequestBody HotelDto dto) {
		
		Hotel h = dtoToEntity(dto);
		Hotel updated = hotelService.updateHotel(id, h);
		return ResponseEntity.ok(entityToDto(updated));
	}
	
	// Delete Request
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		hotelService.deleteHotel(id);
		return ResponseEntity.noContent().build();
	}
	
	
	// search hotel by name
	
	@GetMapping("/search")
	public ResponseEntity<List<HotelDto>> searchHotelByName(@RequestParam(name ="name") String name) {
		List<Hotel> list = hotelService.searchHotelByName(name);
		List<HotelDto> dToList = list.stream()
				                     .map(this::entityToDto)
						                .collect(Collectors.toList());
		return ResponseEntity.ok(dToList);
	}
	
	
	
}
