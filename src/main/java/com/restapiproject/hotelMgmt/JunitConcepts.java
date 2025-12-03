package com.restapiproject.hotelMgmt;

public class JunitConcepts {
	
/*
 *  open source testing frame work for the java programming language
 *  tool for developers to create tests - units of the code
 *  ensure the quality and maintainability of the Java codes
 *  light weight testing framework, we use it for unit testing
 *  with the help of Junit, we can write automated tests that validate small pieces of code(units) (Classes)
 *  
 *  Benefits:
 *   catch exceptions, enables the document expected behaviour, enables test driven devlopment TDD
 *   
 *  Junit 5 -> more modular approach for creating the unit tests
 *          -> backward compatibility
 *  
 *  Spring Boot - spring-boot-starter-test - bundles junit and test utilities
 *  
 *  
 *  Add Junit dependency -> org.junit.jupiter - scope -> test : does'nt get connected with production
 *  
 *  Junit Test Framework - Architecture :
 *    Modules -> Platform - discovers and run tests
 *            -> Jupiter - new programmig model (annotations, assertions)
 *            -> Vintage - run Juniy 3/4 test on Junit5 platform
 *           
 *    Concepts : test dicovery, execution, lifecycle methods, assertions...
 *    
 *  Junit - class - write test methods - annotate them with @Test annotation
 *        Pattern AAA -> A: Arrange -> prepare data/mocks
 *                       A: Act -> invoke method under test
 *                       A: Assert -> verify the outcome using assertions
 *    
 *   Eg:
 *    
 *    public class Calculator {
 *      public int add (int a, int b) {
 *          return a + b;
 *       }
 *     }
 * 
 *    public class CalculatorTest {
 *    
 *       @Test
 *       void testAdd() {
 *          Calculator calc = new Calculator();
 *          int result = calc.add(12,13);
 *          assertEquals(25, result, "12 + 13 should equal 25");
 *                     // expected result, actual 
 *        }
 *     } 
 *     
 *     
 *  Test Case : represents a specefic scenario or condition to be tested
 *  test cases are implemented as methods within a test class, annotated with @Test
 *  
 *  Provided by Juit - API
 *  
 *  Annotations :
 *  
 *  1. @Test -> Marks a method as a unit Test
 *  2. @BeforeEach -> Executes a method before each test method in a class
 *  3. @AfterEach -> Executes a method after each test method in a class
 *  4. @BeforeAll -> Executes a method once before all test methods in a class
 *  5. @AfterAll -> Executes a method once after all test methods in a class
 *  
 *  Assertions : check whether the expected == actual 
 *  used within test methods to verify outcomes
 *  1. assertEquals(expected, actual) : check if two values are equal or not equal
 *  2. assertTrue(condition) : check if a condition is true
 *  3. assertNotNull(object) : check if object is not null
 *  4. assertThrow(expectedException, executable) : verify that a specific exception is thrown
 *  
 *  Assumptions :
 *  -> skip tests under certain conditions
 *  Assumptions.assumeTrue(...)  *  
 *  
 *  Junit test runners : Junit includes test runners responsible for executing test cases and reporting
 *  
 *  Early bug detection
 *  improved code quality and maintainance
 *  
 *  Junit - Suite Test
 *   -> run a group of test classes together as one suite
 *   -> logical groupings, integration test sets
 *   -> Junit5 > @Suite
 *   
 *  Junit - Parameterized Test 
 *   -> run same test logic with multiple inputs Reduce the duplication and edge cases
 *  Junit 5 provides @ParameterizedTest - @ValueSource @MethodSource
 *  > Define input set, test run once per input - assert expect outcome per input 
 *   
 *   
 * 
 * 
 * */
}
