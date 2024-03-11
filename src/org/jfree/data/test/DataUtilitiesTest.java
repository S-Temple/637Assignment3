package org.jfree.data;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataUtilitiesTest {
    
    private Mockery mockingContext;

    @Before
    public void setUp() {
        mockingContext = new Mockery();
    }

    /*
     *  Tests for calculateColumnTotal
     *  
     *  Should return the sum of all values in a specified column
     */
    @Test
    public void calculateColumnTotal_WithPositiveValues() {
        final Values2D values = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(5.0));
            oneOf(values).getValue(1, 0);
            will(returnValue(10.0));
        }});
        
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(15.0, result, 0.000000001d);
    }
    
    @Test(expected = InvalidParameterException.class)
    public void calculateColumnTotal_WithNullInput() {
    	double result = DataUtilities.calculateColumnTotal(null, 0);
    }
    
    @Test
    public void calculateColumnTotal_WithNegativeValues() {
        final Values2D values = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(-5.0));
            oneOf(values).getValue(1, 0);
            will(returnValue(-10.0));
        }});
        
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(-15.0, result, 0.000000001d);
    }
    
    @Test
    public void calculateColumnTotal_WithMixedValues() {
        final Values2D values = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(-5.0));
            oneOf(values).getValue(1, 0);
            will(returnValue(5.0));
        }});
        
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(0.0, result, 0.000000001d);
    }
    
    @Test
    public void calculateColumnTotal_WithNullValue() {
        final Values2D values = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(null));
            oneOf(values).getValue(1, 0);
            will(returnValue(10.0));
        }});
        
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(10.0, result, 0.000000001d);
    }
    
    @Test
    public void calculateColumnTotal_WithZeroValues() {
        final Values2D values = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(3));
            oneOf(values).getValue(0, 0);
            will(returnValue(0.0));
            oneOf(values).getValue(1, 0);
            will(returnValue(0.0));
            oneOf(values).getValue(2, 0);
            will(returnValue(0.0));
        }});
        
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(0.0, result, 0.000000001d);
    }
    
    @Test
    public void calculateColumnTotal_WithLargeDataset() {
        final Values2D values = mockingContext.mock(Values2D.class);
        final int rowCount = 10000; 
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(rowCount));
            for (int i = 0; i < rowCount; i++) {
                final int rowIndex = i;
                oneOf(values).getValue(rowIndex, 0);
                will(returnValue(1.0)); 
            }
        }});
        
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(rowCount, result, 0.000000001d); 
    }
    
 // With no rows, sum should be zero
    @Test
    public void calculateColumnTotal_WithNoRows() {
        final Values2D values = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(0));
        }});
        
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(0.0, result, 0.000000001d);
    }
    
    @Test
    public void calculateColumnTotal_WithInfinityValues() {
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(Double.POSITIVE_INFINITY));
            oneOf(values).getValue(1, 0);
            will(returnValue(Double.NEGATIVE_INFINITY));
        }});

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertTrue(Double.isNaN(result)); // Expecting NaN since infinity arithmetic should result in NaN
    }
    
    /*
     *  Test for calculateRowTotal
     *  
     *  Should return the sum of all values in a specified row
     */
    @Test
    public void calculateRowTotal_WithMixedValues() {
        final Values2D values = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getColumnCount();
            will(returnValue(3));
            oneOf(values).getValue(0, 0);
            will(returnValue(5.0));
            oneOf(values).getValue(0, 1);
            will(returnValue(-2.5));
            oneOf(values).getValue(0, 2);
            will(returnValue(1.0));
        }});
        
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(3.5, result, 0.000000001d);
    }
    
    @Test(expected = InvalidParameterException.class)
    public void calculateRowTotal_WithNullInput() {
    	double result = DataUtilities.calculateRowTotal(null, 0);
    }

    @Test
    public void calculateRowTotal_WithNullValues() {
        final Values2D values = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getColumnCount();
            will(returnValue(3));
            oneOf(values).getValue(0, 0);
            will(returnValue(null));
            oneOf(values).getValue(0, 1);
            will(returnValue(null));
            oneOf(values).getValue(0, 2);
            will(returnValue(10.0));
        }});
        
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(10.0, result, 0.000000001d);
    }
    
    @Test
    public void calculateRowTotal_WithZeroValues() {
        final Values2D values = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getColumnCount();
            will(returnValue(3));
            oneOf(values).getValue(0, 0);
            will(returnValue(0.0));
            oneOf(values).getValue(0, 1);
            will(returnValue(0.0));
            oneOf(values).getValue(0, 2);
            will(returnValue(10.0));
        }});
        
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(10.0, result, 0.000000001d);
    }
    
    @Test
    public void calculateRowTotal_WithRowIndexOutOfRange() {
        final Values2D values = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getColumnCount();
            will(returnValue(1));
            oneOf(values).getValue(0, 0);
            will(returnValue(10.0));
        }});
        
        double result = DataUtilities.calculateRowTotal(values, 5); // Assuming index 5 is out of range
        assertEquals(0.0, result, 0.000000001d);
    }
    
    //with no columns output should be 0
    @Test
    public void calculateRowTotal_WithNoColumns() {
        final Values2D values = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getColumnCount();
            will(returnValue(0));
        }});
        
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(0, result, 0.000000001d);
    }

    @Test
    public void calculateRowTotal_WithLargeDataset() {
        final Values2D values = mockingContext.mock(Values2D.class);
        final int columnCount = 10000; 
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getColumnCount();
            will(returnValue(columnCount));
            for (int i = 0; i < columnCount; i++) {
                final int columnIndex = i;
                oneOf(values).getValue(0, columnIndex);
                will(returnValue(1.0)); 
            }
        }});
        
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(columnCount, result, 0.000000001d); 
    }
    
    @Test
    public void calculateRowTotal_WithInfinityValues() {
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getColumnCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(Double.POSITIVE_INFINITY));
            oneOf(values).getValue(0, 1);
            will(returnValue(Double.NEGATIVE_INFINITY));
        }});

        double result = DataUtilities.calculateRowTotal(values, 0);
        assertTrue(Double.isNaN(result)); // Expecting NaN since infinity arithmetic should result in NaN
    }

    
    /*
     * 
     *  Tests for createNumberArray
     *  
     * currently the last value of the array is always set to null.
     * Also the method returns an array of doubles instead of Numbers
     *  
     */
    @Test
    public void createNumberArray_WithValidInput() {
        double[] data = {1.0, 2.0, 3.0};
        Number[] result = DataUtilities.createNumberArray(data);
        
        assertNotNull(result);
        assertEquals(3, result.length);
        assertEquals(Number.class, result[0].getClass());
        assertEquals(1.0, result[0].doubleValue(), 0.0);
        assertEquals(2.0, result[1].doubleValue(), 0.0);
        assertEquals(3.0, result[2].doubleValue(), 0.0);
    }

    @Test(expected = InvalidParameterException.class)
    public void createNumberArray_WithNullInput() {
        DataUtilities.createNumberArray(null);
    }
    
    @Test
    public void createNumberArray_WithNegativeValue() {
        double[] data = {-1.0};
        Number[] result = DataUtilities.createNumberArray(data);
        
        assertNotNull(result);
        assertEquals(1, result.length);
        assertEquals(-1.0, result[0].doubleValue(), 0.0);
    }
    
    @Test
    public void createNumberArray_WithZeroValue() {
    	double[] data = {0.0};
        Number[] result = DataUtilities.createNumberArray(data);
        
        assertNotNull(result);
        assertEquals(1, result.length);
        assertEquals(0.0, result[0].doubleValue(), 0.0);
    }
    
    //note last value seems to be  set to null instead of 9999
    @Test
    public void createNumberArray_LargeDataset() {
        int datasetSize = 10000;
    	double[] data = new double[datasetSize];
    	
    	for (int i = 0; i < datasetSize; i++)
    	{
    		data[i] = i;
    		}
    	
        Number[] result = DataUtilities.createNumberArray(data);
        assertNotNull(result);
        assertEquals(datasetSize, result.length);
        
        for (int i = 0; i < datasetSize; i++)
    	{
            assertEquals(i, result[i].doubleValue(), 0.0);
    	}
    }
    
    @Test
    public void createNumberArray_emptyArray() {
        double[] data = {};
        Number[] result = DataUtilities.createNumberArray(data);
        
        assertNotNull(result);
        assertEquals(0, result.length);
    }
    
    @Test
    public void createNumberArray_WithInfinityAndNaN() {
        double[] data = {Double.NEGATIVE_INFINITY, Double.NaN, Double.POSITIVE_INFINITY};
        Number[] result = DataUtilities.createNumberArray(data);

        assertNotNull(result);
        assertEquals(3, result.length);
        assertEquals(Double.NEGATIVE_INFINITY, result[0].doubleValue(), 0.0);
        assertTrue(Double.isNaN(result[1].doubleValue()));
        assertEquals(Double.POSITIVE_INFINITY, result[2].doubleValue(), 0.0);
    }
    
    /*
     *  Test for createNumberArray2D
     */
    @Test
    public void createNumberArray2D_WithValidInput() {
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(2, result[0].length);
        assertEquals(Number.class, result[0][0].getClass());
        assertEquals(1.0, result[0][0].doubleValue(), 0.0);
        assertEquals(2.0, result[0][1].doubleValue(), 0.0);
        assertEquals(3.0, result[1][0].doubleValue(), 0.0);
        assertEquals(4.0, result[1][1].doubleValue(), 0.0);
    }
    
    @Test(expected = InvalidParameterException.class)
    public void createNumberArray2D_WithNullInput() {
        DataUtilities.createNumberArray2D(null);
    }

    
    @Test
    public void createNumberArray2D_WithEmptyArray() {
        double[][] data = new double[0][0];
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNotNull(result);
        assertEquals(0, result.length);
    }
    
    @Test
    public void createNumberArray2D_WithNegativeInput() {
        double[][] data = {{-1.0, -2.0}, {-3.0, -4.0}};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(2, result[0].length);
        assertEquals(-1.0, result[0][0].doubleValue(), 0.0);
        assertEquals(-2.0, result[0][1].doubleValue(), 0.0);
        assertEquals(-3.0, result[1][0].doubleValue(), 0.0);
        assertEquals(-4.0, result[1][1].doubleValue(), 0.0);
    }

    
    @Test
    public void createNumberArray2D_LargeDataset() {
        int datasetSize = 1000;
    	double[][] data = new double[datasetSize][datasetSize];
    	
    	for (int i = 0; i < datasetSize; i++)
    	{
    		for (int j = 0; j < datasetSize; j++)
    		{
    			data[i][j] = i * datasetSize + j;
    		}
    	}
    	
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNotNull(result);
        assertEquals(datasetSize, result.length);
        assertEquals(datasetSize, result[0].length);
        
        for (int i = 0; i < datasetSize; i++)
    	{
        	for (int j = 0; j < datasetSize; j++)
    		{
                assertEquals(i * datasetSize + j, result[i][j].doubleValue(), 0.0);
    		}

    	}
    }
    
    @Test
    public void createNumberArray2D_WithInfinityAndNaN() {
        double[][] data = {{Double.NEGATIVE_INFINITY, Double.NaN}, {Double.POSITIVE_INFINITY, Double.MAX_VALUE}};
        Number[][] result = DataUtilities.createNumberArray2D(data);

        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(2, result[0].length);
        assertEquals(Double.NEGATIVE_INFINITY, result[0][0].doubleValue(), 0.0);
        assertTrue(Double.isNaN(result[0][1].doubleValue()));
        assertEquals(Double.POSITIVE_INFINITY, result[1][0].doubleValue(), 0.0);
        assertEquals(Double.MAX_VALUE, result[1][1].doubleValue(), 0.0);
    }
    
    /*
     * 
     *  Test for getCumulativePercentages
     */
    @Test
    public void getCumulativePercentages_WithValidInput() {
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        
        mockingContext.checking(new Expectations() {{
            allowing(values).getItemCount();
            will(returnValue(3));
            allowing(values).getKey(0);
            will(returnValue("0"));
            allowing(values).getValue(0);
            will(returnValue(1.0));
            allowing(values).getKey(1);
            will(returnValue("1"));
            allowing(values).getValue(1);
            will(returnValue(2.0));
            allowing(values).getKey(2);
            will(returnValue("2"));
            allowing(values).getValue(2);
            will(returnValue(3.0));
        }});
        
        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertNotNull(result);
        assertEquals(3, result.getItemCount());
        assertEquals(0.16666666666666666, result.getValue("0").doubleValue(), 0.000000001d);
        assertEquals(0.5, result.getValue("1").doubleValue(), 0.000000001d);
        assertEquals(1.0, result.getValue("2").doubleValue(), 0.000000001d);
    }
    
    @Test
    public void getCumulativePercentages_WithZeroValues() {
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        
        mockingContext.checking(new Expectations() {{
            allowing(values).getItemCount();
            will(returnValue(3));
            allowing(values).getKey(0);
            will(returnValue("A"));
            allowing(values).getValue(0);
            will(returnValue(0.0));
            allowing(values).getKey(1);
            will(returnValue("B"));
            allowing(values).getValue(1);
            will(returnValue(0.0));
            allowing(values).getKey(2);
            will(returnValue("C"));
            allowing(values).getValue(2);
            will(returnValue(0.5));
            
        }});
        
        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertNotNull(result);
        assertEquals(0.0, result.getValue("A").doubleValue(), 0.000000001d);
        assertEquals(0.0, result.getValue("B").doubleValue(), 0.000000001d);
        assertEquals(1.0, result.getValue("C").doubleValue(), 0.000000001d);
    }

    @Test
    public void getCumulativePercentages_WithZeroTotal() {
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        
        mockingContext.checking(new Expectations() {{
            allowing(values).getItemCount();
            will(returnValue(3));
            allowing(values).getKey(0);
            will(returnValue("A"));
            allowing(values).getValue(0);
            will(returnValue(0.0));
            allowing(values).getKey(1);
            will(returnValue("B"));
            allowing(values).getValue(1);
            will(returnValue(0.0));
            allowing(values).getKey(2);
            will(returnValue("C"));
            allowing(values).getValue(2);
            will(returnValue(0.0));
            
        }});
        
        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertNotNull(result);
        assertEquals(Double.NaN, result.getValue("A").doubleValue(), 0.000000001d);
        assertEquals(Double.NaN, result.getValue("B").doubleValue(), 0.000000001d);
        assertEquals(Double.NaN, result.getValue("C").doubleValue(), 0.000000001d);
    }
   
    @Test(expected = InvalidParameterException.class)
    public void getCumulativePercentages_WithNullInput() {
        DataUtilities.getCumulativePercentages(null);
    }
    
    //NOTE: Behavior isn't fully defined for negative values
    @Test
    public void getCumulativePercentages_WithNegativeValues() {
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        
        mockingContext.checking(new Expectations() {{
            allowing(values).getItemCount();
            will(returnValue(2));
            allowing(values).getKey(0);
            will(returnValue("A"));
            allowing(values).getValue(0);
            will(returnValue(-10.0));
            allowing(values).getKey(1);
            will(returnValue("B"));
            allowing(values).getValue(1);
            will(returnValue(20.0));
        }});
        
        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertNotNull(result);
        
        // Behavior is undefined in javadoc. The below code prints out -0.5 and 0.5 which seems wrong since values are promised to go between 0.0 and 1.0
        System.out.println(result.getValue("A"));
        System.out.println(result.getValue("B"));
    }
    
    @Test
    public void getCumulativePercentages_WithSingleValue() {
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        
        mockingContext.checking(new Expectations() {{
            allowing(values).getItemCount();
            will(returnValue(1));
            allowing(values).getKey(0);
            will(returnValue("A"));
            allowing(values).getValue(0);
            will(returnValue(10.0));
        }});
        
        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertNotNull(result);
        assertEquals(1.0, result.getValue("A").doubleValue(), 0.000000001d);
    }
    
    
    @Test
    public void getCumulativePercentages_WithLargeDataset() {
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        int datasetSize = 10000;
        
        mockingContext.checking(new Expectations() {{
            allowing(values).getItemCount();
            will(returnValue(datasetSize));
            
            for (int i = 0; i < datasetSize; i++) {
            	allowing(values).getKey(i);
                will(returnValue(Integer.toString(i)));
                allowing(values).getValue(i);
                will(returnValue(i));
            }
        }});
        
        double sum = 0;
        for (int i = 0; i < datasetSize; i++) {
        	sum += i;
        }
        
        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertNotNull(result);
        double currentSum = 0;
        for (int i = 0; i < datasetSize; i++) {
        	currentSum += i;
        	assertEquals(currentSum / sum, result.getValue(Integer.toString(i)).doubleValue(), 0.000000001d);
        }
    }
    
    @Test
    public void getCumulativePercentages_WithInfinityValues() {
        final KeyedValues values = mockingContext.mock(KeyedValues.class);

        mockingContext.checking(new Expectations() {{
            allowing(values).getItemCount();
            will(returnValue(3));
            allowing(values).getKey(0);
            will(returnValue("A"));
            allowing(values).getValue(0);
            will(returnValue(Double.POSITIVE_INFINITY));
            allowing(values).getKey(1);
            will(returnValue("B"));
            allowing(values).getValue(1);
            will(returnValue(Double.NEGATIVE_INFINITY));
            allowing(values).getKey(2);
            will(returnValue("C"));
            allowing(values).getValue(2);
            will(returnValue(Double.NaN));
        }});

        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertNotNull(result);
        assertTrue(Double.isNaN(result.getValue("A").doubleValue()));
        assertTrue(Double.isNaN(result.getValue("B").doubleValue()));
        assertTrue(Double.isNaN(result.getValue("C").doubleValue())); // Expecting NaN for invalid calculations
    }

    @Test
    public void getCumulativePercentages_WithNullValues() {
        final KeyedValues values = mockingContext.mock(KeyedValues.class);

        mockingContext.checking(new Expectations() {{
            allowing(values).getItemCount();
            will(returnValue(3));
            allowing(values).getKey(0);
            will(returnValue("A"));
            allowing(values).getValue(0);
            will(returnValue(1));
            allowing(values).getKey(1);
            will(returnValue("B"));
            allowing(values).getValue(1);
            will(returnValue(null));
            allowing(values).getKey(2);
            will(returnValue("C"));
            allowing(values).getValue(2);
            will(returnValue(1));
        }});

        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertNotNull(result);
        assertEquals(0.5, result.getValue("A").doubleValue(), 0.000000001d);
        assertEquals(0.5, result.getValue("B").doubleValue(), 0.000000001d);
        assertEquals(1.0, result.getValue("C").doubleValue(), 0.000000001d);
    }
    
    @After
    public void tearDown() throws Exception {
    	mockingContext = null;
    }
}
