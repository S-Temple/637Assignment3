package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
    private Range constrainRange;
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { constrainRange = new Range(-10, 20);
    }

    /*
     * testing constructor
     */
    @Test
    public void testContructor_CreatesObj() {
    	Range range = new Range(0.0, 2.0);
    	assertNotNull("No object was created", range);
    }
    
    @Test
    public void testContructor_CreatesObj_whenBoundsAreEqual() {
    	Range range = new Range(0.0, 0.0);
    	assertNotNull("No object was created", range);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testContructor_LowerGreaterThanUpperParameter() {
    	Range range = new Range(3.0, 2.0);
    }
    
    
    /* 
     * Lower bound method tests
     * 
     *  Should always return the lower bound of the range
     */
    @Test
    public void testGetLowerBound() {
    	Range range = new Range(2.0, 5.0);
    	assertEquals("Unexpected behaviour in method getLowerBound",
    			2.0, range.getLowerBound(),0.0000001);
    }
    
    @Test
    public void testGetLowerBound_WithOneNegitiveParameter() {
    	Range range = new Range(-603.0, 5.0);
    	assertEquals("Unexpected behaviour in method getLowerBound with a single negitive paramter",
    			-603.0, range.getLowerBound(),0.0000001);
    }
    
    @Test
    public void testGetLowerBound_WithBothNegitiveParameter() {
    	Range range = new Range(-60.5,-2.7);
    	assertEquals("Unexpected behaviour in method getLowerBound with two negitive parameters",
    			-60.5, range.getLowerBound(),0.0000001);
    }
    
    @Test
    public void testGetLowerBound_ZeroValue() {
    	Range range = new Range(0.0, 10.0);
    	assertEquals("Unexpected behaviour in method getLowerBound",
    			0.0, range.getLowerBound(),0.0000001);
    }
    
    @Test
    public void testGetLowerBound_MinValue() {
    	Range range = new Range(Double.MIN_VALUE, 5.0);
    	assertEquals("Unexpected behaviour in method getLowerBound",
    			Double.MIN_VALUE, range.getLowerBound(),0.0000001);
    }
    
    @Test
    public void testGetLowerBound_NegInfValue() {
    	Range range = new Range(Double.NEGATIVE_INFINITY, 5.0);
    	assertEquals("Unexpected behaviour in method getLowerBound",
    			Double.NEGATIVE_INFINITY, range.getLowerBound(),0.0000001);
    }
    
    
    /* upper bounds method tests 
     * 
     * Should always return the upper bound
     * NOTE:  Seems to be returning lower bound instead
     */
    @Test
    public void testGetUpperBound() {
    	Range range = new Range(2.0, 5.0);
    	assertEquals("Unexpected behaviour in method getUpperBound",
    			5.0, range.getUpperBound(),0.0000001);
    }
    
    @Test
    public void testGetUpperBound_WithLargeValue() {
    	Range range = new Range(2.1, 123456789.5);
    	assertEquals("Unexpected behaviour in method getUpperBound with large upper value",123456789.5, range.getUpperBound(),0.0000001);
    }
    
    @Test
    public void testGetUpperBound_WithMaxValue() {
    	Range range = new Range(5.0,Double.MAX_VALUE);
    	assertEquals("Unexpected behaviour in method getUpperBound with max double value",
    			Double.MAX_VALUE, range.getUpperBound(),0.0000001);
    }
    
    @Test
    public void testGetUpperBound_PosInfValue() {
    	Range range = new Range(5.0, Double.POSITIVE_INFINITY);
    	assertEquals("Unexpected behaviour in method getUpperBound with positive infinity",
    			Double.POSITIVE_INFINITY, range.getUpperBound(),0.0000001);
    }
    
    @Test
    public void testGetUpperBound_negativeValue() {
    	Range range = new Range(-10.0, -5.0);
    	assertEquals("Unexpected behaviour in method getUpperBound",
    			-5.0, range.getUpperBound(),0.0000001);
    }
    
    @Test
    public void testGetUpperBound_zeroValue() {
    	Range range = new Range(-10.0, 0.0);
    	assertEquals("Unexpected behaviour in method getUpperBound",
    			0.0, range.getUpperBound(),0.0000001);
    }

    
    /* 
     * getCentralValue method tests 
     * 
     */
    @Test
    public void testGetCentralValue_WtihRangeNegOneAndOne() {
    	Range range = new Range(-1,1);
        assertEquals("The central value of -1 and 1 should be 0",
        0, range.getCentralValue(), .000000001d);
    }
    
    @Test
    public void testGetCentralValue_NegMedianCalc() {
    	Range range = new Range(-9,0);
    	assertEquals("The central value of -9 and 0 should be -4.5",
    	-4.5, range.getCentralValue(), .000000001d);		    
    }
    
    @Test
    public void testGetCentralValue_PosMedianCalc() {
    	Range range = new Range(45.0,6900.0);
    	assertEquals("The central value of -9 and 0 should be 3427.5",
    	3472.5, range.getCentralValue(), .000000001d);		    
    }
    
    @Test
    public void testGetCentralValue_BoundsEqual() {
    	Range range = new Range(5.0,5.0);
    	assertEquals("The central value of 5 and 5 should be 5",
    	5.0, range.getCentralValue(), .000000001d);		    
    }

    /* contains method tests
     * 
     */
    @Test
    public void testContainsBeyondLower() {
        Range range = new Range(0.0, 10.0);
        assertTrue("Unexpected behaviour in contains method",!range.contains(-1));
    }
    
    @Test
    public void testContainsAtLower() {
        Range range = new Range(0.0, 10.0);
        assertTrue("Unexpected behaviour in contains method",range.contains(0.0));
    }
    
    @Test
    public void testContains() {
        Range range = new Range(0.0, 10.0);
        assertTrue("Unexpected behaviour in contains method",range.contains(5));
    }
    
    @Test
    public void testContainsAtUpper() {
        Range range = new Range(0.0, 10.0);
        assertTrue("Unexpected behaviour in contains method",range.contains(10.0));
    }
    
    @Test
    public void testContainsBeyondUpper() {
        Range range = new Range(0.0, 10.0);
        assertTrue("Unexpected behaviour in contains method",!range.contains(11.0));
    }
    
    /* getLength method tests
     * 
     */
    @Test
    public void testGetLength() {
        Range range = new Range(0.0, 10.0);
        assertEquals("Unexpected behaviour in get length method",10.0, range.getLength(), 0.001);
    }
    
    @Test
    public void testGetLength_WithNegitiveValues() {
        Range range = new Range(-30.0, -10.0);
        assertEquals("get length fails with negitive values",20.0, range.getLength(), 0.001);
    }
    
    @Test
    public void testGetLength_WithNegLowerBound() {
        Range range = new Range(-30.0, 30.0);
        assertEquals("get length fails with negitive lower bound",60.0, range.getLength(), 0.001);
    }
    
    @Test
    public void testGetLength_WithSmallValues() {
        Range range = new Range(0.55555, 0.55556);
        assertEquals("get length fails with length = 0.00001",0.00001, range.getLength(), 0.001);
    }
    
    @Test
    public void testGetLength_ZeroLength() {
        Range range = new Range(10.0, 10.0);
        assertEquals("get length fails with length = 0",0.0, range.getLength(), 0.001);
    }
    
    @Test
    public void testGetLength_WithMaxValue() {
        Range range = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
        assertEquals("get length fails on overflow",Double.MAX_VALUE - Double.MIN_VALUE, range.getLength(), 0.001);
    }
    
    /*
     * equals method tests
     * Fails, seems to only check if lower is equal. 
     * probably relies on getUpperBound which incorrectly returns lower. 
     */
    @Test
    public void testEquals() {
        Range range1 = new Range(0.0, 5.0);
        Range range2 = new Range(0.0, 5.0);
        assertTrue("equals method fails with both lower = 0.0 and upper = 5.0",range1.equals(range2));
    }
    
    @Test
    public void testEquals_Unequal_Upper_Bounds() {
        Range range1 = new Range(0.0, 5.0);
        Range range2 = new Range(0.0, 6.0);
        assertFalse("equals method fails with upper = 5.0 and upper2 = 6.0",range1.equals(range2));  
    }
    
    @Test
    public void testEquals_Unequal_Lower_Bounds() {
        Range range1 = new Range(0.0, 5.0);
        Range range2 = new Range(1.0, 5.0);
        assertFalse("equals method fails with lower = 0.0 and lower2 = 1.0",range1.equals(range2));  
    }
    
    @Test
    public void testEquals_Unequal_Upper_and_lower_bounds() {
        Range range1 = new Range(1.0, 5.0);
        Range range2 = new Range(0.0, 6.0);
        assertFalse("equals method fails with range1 (1.0,5.0) and range2 (0.0,6.0)",range1.equals(range2));  
    }
    
    @Test
    public void testEquals_WithNegOne_FiveAndZero_SixHundred() {
        Range range1 = new Range(-1.0, 5.0);
        Range range2 = new Range(-1.0, 600.0);
        assertFalse("equals method fails with Range(-1.0, 5.0) and Range(-1.0, 600.0)",range1.equals(range2));  
    }
    
    
    
    /*
     * 
     * Constrain method tests
     * Constrain range is = (-10, 20)
     * 
     * Combination of equivalence class (Below range, within range, above range)
     * and boundary class testing (input == lower bound, input == upper bound)
     * + some additional tests on a range that has lower bound == upper bound
     * 
     * Constrain takes a double as an input
     * Should return a value that is within the given range AND is as close to the input value as possible
     * (ie, an input outside of the range should return the ranges upper or lower bound, but an input in the range should return the input)
     * 
     * 
     */
    
    //constraining from below range, should return lower bound
    //Currently fails - returns midpoint/average between upper and lower bound instead
    @Test
    public void testConstrainFromBelowRange() {
		Double result = constrainRange.constrain(-22);
		
		assertEquals("The resulting value should match the lower bound of the range",
				-10, result, .000000001d);
		}
	
    //constraining at lower bound should return lower bound
	@Test
	public void testConstrainAtLowerBound() {
		Double result = constrainRange.constrain(-10);
		
		assertEquals("The resulting value should match the lower bound of the range",
				-10, result, .000000001d);
		}
	
	//constraining an in-range value should return the in-range value
	@Test
	public void testConstrainWithinRange() {
		Double result = constrainRange.constrain(5.53);
		
		assertEquals("The resulting value should match the in range input value",
				5.53, result, .000000001d);
		}
	
	//constraining at upper bound should return upper bound
	@Test
	public void testConstrainAtUpperBound() {
		Double result = constrainRange.constrain(20);
		
		assertEquals("The resulting value should match the upper bound of the range",
				20, result, .000000001d);
		}
	
	//constraining from above range, should return upper bound
	@Test
	public void testConstrainFromAboveRange() {
		Double result = constrainRange.constrain(1000.765);
		
		assertEquals("The resulting value should match the upper bound of the range",
				20, result, .000000001d);
		}
	
	//tests constraining to a range with lower bound = upper bound
	//constrain from below should return the lower bound
	@Test
	public void testConstrainFromBelowUnitRange() {
		constrainRange = new Range(1,1);
		Double result = constrainRange.constrain(-10);
		
		assertEquals("The resulting value should match the lowe/upper bound of the range",
				1, result, .000000001d);
		}
	
	//tests constraining to a range with lower bound = upper bound
		@Test
		public void testConstrainFromWithinUnitRange() {
			constrainRange = new Range(1,1);
			Double result = constrainRange.constrain(1);
			
			assertEquals("The resulting value should match the lower/upper bound of the range",
					1, result, .000000001d);
			}
		
		//constrain from below should return the lower bound
		@Test
		public void testConstrainFromAboveUnitRange() {
			constrainRange = new Range(1,1);
			Double result = constrainRange.constrain(10);
			
			assertEquals("The resulting value should match the lower/upper bound of the range",
					1, result, .000000001d);
			}
    
    @After
    public void tearDown() throws Exception {
    	constrainRange = null;
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}