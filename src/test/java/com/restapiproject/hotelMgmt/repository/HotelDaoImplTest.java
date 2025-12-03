package com.restapiproject.hotelMgmt.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;

import com.restapiproject.hotelMgmt.model.Hotel;
import com.restapiproject.hotelMgmt.util.HotelRowMapper;

public class HotelDaoImplTest {
/*  mock the jdbc template - unit test - no DB
 * 
 *  Mockito - Java mocking framework
 *  -> allows developers to create and manage mock objects
 *  -> simulate the behaviour of real objects
 *  
 *  @Mock -> jdbcTemplate
 *  @InjectMocks - constructs the DAO and inject the mock - jdbcTemplate
 *  
 */
	@Mock  // tell Mockito - crate a mock instance of JdbcTemplate
	private JdbcTemplate jdbcTemplate;
	
	@InjectMocks // create instance of hotelDaoImpl and inject the mock
	private HotelDaoImpl hotelDao;
	
	private Hotel hotel1;
	private Hotel hotel2;
	
	@BeforeEach // run before eacj test
	void setup() {
		MockitoAnnotations.openMocks(this); // initialising Mockito annotations 
		                                    // - @Mock, @InjectMocks in the test instance
		hotel1 = new Hotel(1L, "Hotel A", "Address A", 10, 5, new BigDecimal("100.0"));
		hotel2 = new Hotel(2L, "Hotel B", "Address B", 20, 10, new BigDecimal("200.0"));
	}
	
	// ---- Basic Crud Tests ----
	
	@Test  // mark this method as Test
	void testSave() {
		doAnswer(invocation -> {
			KeyHolder keyHolder = invocation.getArgument(1);
			keyHolder.getKeyList().add(Map.of("GENERATED_KEY", 1L));
			return 1;
		}).when(jdbcTemplate).update(any(), any(KeyHolder.class));
		
		Hotel result = hotelDao.save(hotel1);
		assertNotNull(result);  
		
		// returned object is not null
		// call jdbcTemplate -> update -> 
		// real jdbc -> execute Insert -> fill the KeyHolder -> with generated PK
		// -> getkey() -> getting that key -> set it on hotel instance
		// doAnswer -> intercept the mock call -> tell Mockito to run provided lambda function
		// invocation -> object representing the intercepted method call
		// getArgument(1) -> fetch the second argument
	}
	
	@Test
	void testFindById_Found() {
		//                     (String sql, RowMapper, Object... args)
		when(jdbcTemplate.query(anyString(), any(HotelRowMapper.class), eq(1L)))
		       .thenReturn(Arrays.asList(hotel1));

		Optional<Hotel> result = hotelDao.findById(1L);
		assertTrue(result.isPresent());   // confirm that DAO returned a non empty Optional
		assertEquals("Hotel A", result.get().getName());
	}
	
	@Test
	void testFindById_NotFound() {
		
		when(jdbcTemplate.query(anyString(), any(HotelRowMapper.class), eq(3L)))
		       .thenReturn(Arrays.asList(hotel1));

		Optional<Hotel> result = hotelDao.findById(31L);
		assertFalse(result.isPresent());    //verify that 3L return Optional.empty()
		                                   // verify Dao is correctly handling missing row
	}	
	
	@Test
	void testFindAll() {
		
		when(jdbcTemplate.query(anyString(), any(HotelRowMapper.class)))
		       .thenReturn(Arrays.asList(hotel1,hotel2));

		List<Hotel> result = hotelDao.findAll();
		assertEquals(2,result.size());    // matching result size - 2hotels
	}	
	
	@Test
	void testUpdate() {
		when(jdbcTemplate.update(anyString(),
				anyString(), anyString(), anyInt() , anyInt(), any(BigDecimal.class), anyLong()))
		        .thenReturn(1);
		int rows = hotelDao.update(hotel1);
		assertEquals(1, rows);
	}
	
	@Test
	void testDelete() {
		when(jdbcTemplate.update(anyString(),anyLong()))
		        .thenReturn(1);
		int rows = hotelDao.deleteById(1L);
		assertEquals(1, rows);
	}
	
	// --- Parameterized Test --- run method for multiple inputs
	@ParameterizedTest
	@ValueSource(strings = {"Hotel X","Hotel Y","Hotel Z"})
	void testParameterizedHotelNames(String name) {
		Hotel hotel = new Hotel();
		hotel.setName(name);
		assertNotNull(hotel.getName());
		assertTrue(hotel.getName().startsWith("Hotel"));
		// run the test multiple times - values - in ValueSource - 3
		// For each name - create a new Hotel, set the name, verify name is not null and start with Hotel
	}

	// --- Skip Test ---
	@Disabled("Example Skipped DAO Test")
	@Test
	void testDisableExample() {
		fail("This DAO test is disabled and skipped");
		// test was enabled - it would have been failed
		// how to skip test
	}
	
}