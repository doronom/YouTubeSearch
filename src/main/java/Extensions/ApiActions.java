package Extensions;

import Utilities.CommonOperations;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import static Utilities.HelpersMethods.saveResponseToJson;

public class ApiActions extends CommonOperations {
    @Step("Get api data using api query builder and save the response to a file")
    public static JsonElement getApiUsingQueryBuilder(String endpoint, String query, String fileName, String filePath) {
        String url = String.format("%s/%s?q=%s&token=%s&format=json", getData("Base_api_url"),
                endpoint, query, getData("api_token"));
        Response response = RestAssured.given()
                .get(url);
        String responseBody = response.getBody().asString();
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(responseBody);
        // Save the response to json file
        saveResponseToJson(responseBody, fileName, filePath);
        return jsonElement;
    }


}
