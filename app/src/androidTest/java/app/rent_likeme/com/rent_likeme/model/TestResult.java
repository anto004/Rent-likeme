package app.rent_likeme.com.rent_likeme.model;

import android.test.AndroidTestCase;

import java.util.Collections;
import java.util.List;

import app.rent_likeme.com.rent_likeme.util.JSONHelper;

/**
 * Created by anto004 on 3/13/18.
 */

public class TestResult extends AndroidTestCase {

    private List<Result> results;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        results = JSONHelper.parseJSONRental(TestUtilities.createJsonString()).getResults();
    }

    public void testSortByDistance() throws Exception {
        for(Result result: results){
            result.latitude = TestUtilities.latitude;
            result.longitude = TestUtilities.longitude;
        }
        Collections.sort(results);
        List<Result> testResults = TestUtilities.createSortedByDistanceList();
        for(int i = 0; i < results.size(); i++) {
            assertEquals("TestSortByDistance: Distance Doesn't match",testResults.get(i).provider.companyName,
                    results.get(i).provider.companyName);
        }
    }

    public void testSortByCompanyName() throws Exception {
        Result.sortByCompanyName(results);
        List<Result> testResults = TestUtilities.createSortedByCompanyName();
        for(int i = 0; i < results.size(); i++) {
            assertEquals("TestSortByCompanyName: Company Name Doesn't match",testResults.get(i).provider.companyName,
                    results.get(i).provider.companyName);
        }
    }

    public void testSortByPriceLowToHigh() throws Exception {
        Result.sortByPriceLowToHigh(results);
        List<Result> testResults = TestUtilities.createSortByPriceLowToHigh();
        for(int i = 0; i < results.size(); i++) {
            assertEquals("TestSortByPriceLowToHigh: Company With Price Doesn't match",testResults.get(i).provider.companyName,
                    results.get(i).provider.companyName);
        }
    }

    public void testSortByPriceHighToLow() throws Exception {
        Result.sortByPriceHighToLow(results);
        List<Result> testResults = TestUtilities.createSortByPriceHighToLow();
        for(int i = 0; i < results.size(); i++) {
            assertEquals("TestSortByPriceHighToLow: Company With Price Doesn't match",testResults.get(i).provider.companyName,
                    results.get(i).provider.companyName);
        }
    }
}
