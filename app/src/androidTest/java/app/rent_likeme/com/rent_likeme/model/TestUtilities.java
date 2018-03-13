package app.rent_likeme.com.rent_likeme.model;

import android.test.AndroidTestCase;

import java.util.Arrays;
import java.util.List;

/**
 * Created by anto004 on 3/13/18.
 */

public class TestUtilities extends AndroidTestCase {
    static final double latitude = 34.071228;
    static final double longitude = -118.098309;

    static List<Result> createSortedByDistanceList(){
        List<Result> distanceList;
        Result result1 = new Result(longitude, latitude);
        result1.provider = new Provider();
        result1.provider.companyName = "HERTZ";

        Result result2 = new Result(longitude, latitude);
        result2.provider = new Provider();
        result2.provider.companyName = "ENTERPRISE";

        Result result3 = new Result(longitude, latitude);
        result3.provider = new Provider();
        result3.provider.companyName = "AVIS";

        Result result4 = new Result(longitude, latitude);
        result4.provider = new Provider();
        result4.provider.companyName = "BUDGET";

        Result result5 = new Result(longitude, latitude);
        result5.provider = new Provider();
        result5.provider.companyName = "ENTERPRISE";


        distanceList = Arrays.asList(result1, result2, result3, result4, result5);
        return distanceList;
    }

    public static List<Result> createSortedByCompanyName() {
        List<Result> companyList;
        Result result1 = new Result(longitude, latitude);
        result1.provider = new Provider();
        result1.provider.companyName = "AVIS";

        Result result2 = new Result(longitude, latitude);
        result2.provider = new Provider();
        result2.provider.companyName = "BUDGET";

        Result result3 = new Result(longitude, latitude);
        result3.provider = new Provider();
        result3.provider.companyName = "ENTERPRISE";

        Result result4 = new Result(longitude, latitude);
        result4.provider = new Provider();
        result4.provider.companyName = "ENTERPRISE";

        Result result5 = new Result(longitude, latitude);
        result5.provider = new Provider();
        result5.provider.companyName = "HERTZ";


        companyList = Arrays.asList(result1, result2, result3, result4, result5);
        return companyList;
    }

    public static List<Result> createSortByPriceLowToHigh() {
        List<Result> priceList;
        Result result1 = new Result(longitude, latitude);
        result1.provider = new Provider();
        result1.provider.companyName = "ENTERPRISE";

        Result result2 = new Result(longitude, latitude);
        result2.provider = new Provider();
        result2.provider.companyName = "ENTERPRISE";

        Result result3 = new Result(longitude, latitude);
        result3.provider = new Provider();
        result3.provider.companyName = "AVIS";

        Result result4 = new Result(longitude, latitude);
        result4.provider = new Provider();
        result4.provider.companyName = "BUDGET";

        Result result5 = new Result(longitude, latitude);
        result5.provider = new Provider();
        result5.provider.companyName = "HERTZ";

        priceList = Arrays.asList(result1, result2, result3, result4, result5);
        return priceList;
    }

    public static List<Result> createSortByPriceHighToLow() {
        List<Result> priceList;
        Result result1 = new Result(longitude, latitude);
        result1.provider = new Provider();
        result1.provider.companyName = "HERTZ";

        Result result2 = new Result(longitude, latitude);
        result2.provider = new Provider();
        result2.provider.companyName = "BUDGET";

        Result result3 = new Result(longitude, latitude);
        result3.provider = new Provider();
        result3.provider.companyName = "AVIS";

        Result result4 = new Result(longitude, latitude);
        result4.provider = new Provider();
        result4.provider.companyName = "ENTERPRISE";

        Result result5 = new Result(longitude, latitude);
        result5.provider = new Provider();
        result5.provider.companyName = "ENTERPRISE";

        priceList = Arrays.asList(result1, result2, result3, result4, result5);
        return priceList;
    }

    static String createJsonString(){
        return "{  \n" +
                "   \"results\":[  \n" +
                "      {  \n" +
                "         \"provider\":{  \n" +
                "            \"company_code\":\"ET\",\n" +
                "            \"company_name\":\"ENTERPRISE\"\n" +
                "         },\n" +
                "         \"branch_id\":\"IFPS77\",\n" +
                "         \"location\":{  \n" +
                "            \"latitude\":35.97057,\n" +
                "            \"longitude\":-114.58937\n" +
                "         },\n" +
                "         \"address\":{  \n" +
                "            \"line1\":\"3170 HIGHWAY 95 STE A\",\n" +
                "            \"city\":\" BULLHEAD CITY\",\n" +
                "            \"region\":\"AZ\",\n" +
                "            \"country\":\"US\"\n" +
                "         },\n" +
                "         \"cars\":[  \n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"XXAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Special\",\n" +
                "                  \"type\":\"Special\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"34.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"78.97\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"ECAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Economy\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"39.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"90.26\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"CCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Compact\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"41.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"94.77\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"ICAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Intermediate\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"43.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"99.29\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"SCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Standard\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"45.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"103.80\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"FCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Fullsize\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"47.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"108.31\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"PCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Premium\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"49.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"112.83\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"CFAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Compact\",\n" +
                "                  \"type\":\"SUV\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"54.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"124.11\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"IFAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Intermediate\",\n" +
                "                  \"type\":\"SUV\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"59.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"135.40\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"PPAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Premium\",\n" +
                "                  \"type\":\"Pickup (regular cab)\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"79.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"180.54\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"SPAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Standard\",\n" +
                "                  \"type\":\"Pickup (regular cab)\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"79.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"180.54\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"SFAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Standard\",\n" +
                "                  \"type\":\"SUV\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"79.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"180.54\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"MVAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Mini\",\n" +
                "                  \"type\":\"Passenger van\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"79.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"180.54\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"FFAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Fullsize\",\n" +
                "                  \"type\":\"SUV\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"109.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"248.25\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            }\n" +
                "         ]\n" +
                "      },\n" +
                "      {  \n" +
                "         \"provider\":{  \n" +
                "            \"company_code\":\"ZD\",\n" +
                "            \"company_name\":\"BUDGET\"\n" +
                "         },\n" +
                "         \"branch_id\":\"IFPT01\",\n" +
                "         \"location\":{  \n" +
                "            \"latitude\":35.16553,\n" +
                "            \"longitude\":-114.55673\n" +
                "         },\n" +
                "         \"airport\":\"IFP\",\n" +
                "         \"address\":{  \n" +
                "            \"line1\":\"LAUGHLIN/BULLHEAD INT L APO\",\n" +
                "            \"city\":\"BULLHEAD CITY\",\n" +
                "            \"region\":\"AZ\",\n" +
                "            \"country\":\"US\"\n" +
                "         },\n" +
                "         \"cars\":[  \n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"ECAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Economy\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"41.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"111.32\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"CCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Compact\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"42.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"113.83\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"ICAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Intermediate\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"44.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"118.84\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"FCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Fullsize\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"49.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"131.38\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"SCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Standard\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"49.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"131.38\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"IFAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Intermediate\",\n" +
                "                  \"type\":\"SUV\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"60.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"158.97\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"PCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Premium\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"68.00\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"176.54\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"SFAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Standard\",\n" +
                "                  \"type\":\"SUV\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"70.00\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"181.56\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"MVAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Mini\",\n" +
                "                  \"type\":\"Passenger van\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"79.00\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"204.13\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"RFAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Standard Elite\",\n" +
                "                  \"type\":\"SUV\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"81.00\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"209.14\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"LCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Luxury\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"104.38\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  },\n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"84.80\",\n" +
                "                        \"currency\":\"EUR\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"213.23\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"FFAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Fullsize\",\n" +
                "                  \"type\":\"SUV\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"98.00\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"251.78\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"FVAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Fullsize\",\n" +
                "                  \"type\":\"Passenger van\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"100.00\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"256.79\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"PFAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Premium\",\n" +
                "                  \"type\":\"SUV\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"138.00\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"352.09\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            }\n" +
                "         ]\n" +
                "      },\n" +
                "      {  \n" +
                "         \"provider\":{  \n" +
                "            \"company_code\":\"ZE\",\n" +
                "            \"company_name\":\"HERTZ\"\n" +
                "         },\n" +
                "         \"branch_id\":\"IFPT34\",\n" +
                "         \"location\":{  \n" +
                "            \"latitude\":35.15,\n" +
                "            \"longitude\":-114.5667\n" +
                "         },\n" +
                "         \"airport\":\"IFP\",\n" +
                "         \"address\":{  \n" +
                "            \"line1\":\"2550 LAUGHLIN VIEW DR.\",\n" +
                "            \"city\":\"BULLHEAD CITY 864295872\",\n" +
                "            \"region\":\"AZ\",\n" +
                "            \"country\":\"US\"\n" +
                "         },\n" +
                "         \"cars\":[  \n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"ECAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Economy\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"117.50\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"235.00\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"CCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Compact\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"119.85\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"239.70\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"ICAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Intermediate\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"126.90\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"253.80\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"SCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Standard\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"133.48\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"266.96\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"FCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Fullsize\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"138.65\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"277.30\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"PCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Premium\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"152.28\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"304.56\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"IFAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Intermediate\",\n" +
                "                  \"type\":\"SUV\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"152.75\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"305.50\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"SFAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Standard\",\n" +
                "                  \"type\":\"SUV\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"163.09\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"326.18\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"FRAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Fullsize\",\n" +
                "                  \"type\":\"Recreational vehicle\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"177.19\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"354.38\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"STAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Standard\",\n" +
                "                  \"type\":\"Convertible\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"178.60\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"357.20\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"MVAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Mini\",\n" +
                "                  \"type\":\"Passenger van\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"178.60\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"357.20\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"SPAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Standard\",\n" +
                "                  \"type\":\"Pickup (regular cab)\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"399.00\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"798.00\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"EXAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Economy\",\n" +
                "                  \"type\":\"Special\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"399.00\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"798.00\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            }\n" +
                "         ]\n" +
                "      },\n" +
                "      {  \n" +
                "         \"provider\":{  \n" +
                "            \"company_code\":\"ET\",\n" +
                "            \"company_name\":\"ENTERPRISE\"\n" +
                "         },\n" +
                "         \"branch_id\":\"IFPT01\",\n" +
                "         \"location\":{  \n" +
                "            \"latitude\":35.15,\n" +
                "            \"longitude\":-114.5667\n" +
                "         },\n" +
                "         \"airport\":\"IFP\",\n" +
                "         \"address\":{  \n" +
                "            \"line1\":\"2550 LAUGHLIN VIEW DR SUITE 153\",\n" +
                "            \"city\":\" BULLHEAD CITY\",\n" +
                "            \"region\":\"AZ\",\n" +
                "            \"country\":\"US\"\n" +
                "         },\n" +
                "         \"cars\":[  \n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"XXAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Special\",\n" +
                "                  \"type\":\"Special\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"34.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"92.26\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"ECAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Economy\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"39.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"104.79\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"CCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Compact\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"41.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"109.81\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"ICAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Intermediate\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"43.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"114.83\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"SCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Standard\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"45.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"119.84\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"FCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Fullsize\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"47.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"124.85\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"CFAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Compact\",\n" +
                "                  \"type\":\"SUV\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"54.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"142.41\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"IFAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Intermediate\",\n" +
                "                  \"type\":\"SUV\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"59.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"154.94\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"PPAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Premium\",\n" +
                "                  \"type\":\"Pickup (regular cab)\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"79.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"205.09\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"SPAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Standard\",\n" +
                "                  \"type\":\"Pickup (regular cab)\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"79.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"205.09\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"SFAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Standard\",\n" +
                "                  \"type\":\"SUV\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"79.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"205.09\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"MVAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Mini\",\n" +
                "                  \"type\":\"Passenger van\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"79.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"205.09\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"FFAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Fullsize\",\n" +
                "                  \"type\":\"SUV\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"109.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"280.32\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            }\n" +
                "         ]\n" +
                "      },\n" +
                "      {  \n" +
                "         \"provider\":{  \n" +
                "            \"company_code\":\"ZI\",\n" +
                "            \"company_name\":\"AVIS\"\n" +
                "         },\n" +
                "         \"branch_id\":\"IFPT01\",\n" +
                "         \"location\":{  \n" +
                "            \"latitude\":35.15,\n" +
                "            \"longitude\":-114.5667\n" +
                "         },\n" +
                "         \"airport\":\"IFP\",\n" +
                "         \"address\":{  \n" +
                "            \"line1\":\"LAUGHLIN/BULLHEAD INT L APO\",\n" +
                "            \"city\":\"BULLHEAD CITY\",\n" +
                "            \"region\":\"AZ\",\n" +
                "            \"country\":\"US\"\n" +
                "         },\n" +
                "         \"cars\":[  \n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"ECAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Economy\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"55.00\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"110.00\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"CCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Compact\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"56.80\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"113.60\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"ICAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Intermediate\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"61.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"161.48\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"FCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Fullsize\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"66.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"174.01\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"SCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Standard\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"66.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"174.01\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"LCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Luxury\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"100.60\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"201.20\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"IFAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Intermediate\",\n" +
                "                  \"type\":\"SUV\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"77.99\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"201.60\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"PCAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Premium\",\n" +
                "                  \"type\":\"2/4 Door\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"80.00\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"205.14\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"SFAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Standard\",\n" +
                "                  \"type\":\"SUV\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"87.00\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"224.19\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"MVAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Mini\",\n" +
                "                  \"type\":\"Passenger van\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"96.00\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"246.76\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            },\n" +
                "            {  \n" +
                "               \"vehicle_info\":{  \n" +
                "                  \"acriss_code\":\"RFAR\",\n" +
                "                  \"transmission\":\"Automatic\",\n" +
                "                  \"fuel\":\"Unspecified\",\n" +
                "                  \"air_conditioning\":true,\n" +
                "                  \"category\":\"Standard Elite\",\n" +
                "                  \"type\":\"SUV\"\n" +
                "               },\n" +
                "               \"rates\":[  \n" +
                "                  {  \n" +
                "                     \"type\":\"DAILY\",\n" +
                "                     \"price\":{  \n" +
                "                        \"amount\":\"98.00\",\n" +
                "                        \"currency\":\"USD\"\n" +
                "                     }\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"estimated_total\":{  \n" +
                "                  \"amount\":\"251.78\",\n" +
                "                  \"currency\":\"USD\"\n" +
                "               }\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ]\n" +
                "}";
    }
}

