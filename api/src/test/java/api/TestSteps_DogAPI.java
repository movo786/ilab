package api;

import cucumber.api.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;
import java.util.List;

public class TestSteps_DogAPI {
	private static final String BASE_URL = "https://dog.ceo";
	private static String BASE_PATH;

	private Response response;
	private RequestSpecification request;

	@Given("I set the random search method")
	public void i_set_the_random_search_method() {
		RestAssured.baseURI = BASE_URL;
		BASE_PATH = "/api/breeds/image/random";
		request = RestAssured.given();
	}

	@When("I get a random breed")
	public void i_get_a_random_breed() {
		response = request.when().get(BASE_PATH);
	}

	@Then("I get a sucessful response")
	public void i_get_a_sucessful_response() {
		response.then().log().all();
		response.then().statusCode(200).assertThat().body("status", equalTo("success"));
	}

	@Given("I set the list all breeds method")
	public void i_set_the_list_all_breeds_method() {
		RestAssured.baseURI = BASE_URL;
		BASE_PATH = "/api/breeds/list/all";
		request = RestAssured.given();
	}

	@When("I get all breeds")
	public void i_get_all_breeds() {
		response = request.when().get(BASE_PATH);
	}

	@Then("Verify list of all breeds contains \"([^\"]*)\"$")
	public void verify_list_of_all_breeds_contains(String breed) {
		response.then().log().all();
		response.then().assertThat().body("message", hasKey(breed));
	}

	@Given("I set the sub breed method as \"([^\"]*)\"$")
	public void i_set_the_sub_breed_method_as(String breed) {
		RestAssured.baseURI = BASE_URL;
		BASE_PATH = "/api/breed/" + breed + "/list";
		request = RestAssured.given();
	}

	@When("I get all sub breeds")
	public void i_get_all_sub_breeds() {
		response = request.when().get(BASE_PATH);
	}

	@Then("Log all sub breeds and images for \"([^\"]*)\"$")
	public void log_all_sub_breeds_and_images_for(String breed) {
		response.then().log().all();
		List<String> ids = response.jsonPath().getList("message");
		RequestSpecification subRequest;
		Response subResponse;
		// iterate through all sub breeds and print image list
		for (String id : ids) {
			BASE_PATH = "api/breed/" + breed + "/" + id + "/images";
			subRequest = RestAssured.given();
			subResponse = subRequest.when().get(BASE_PATH);
			subResponse.then().log().all();
		}
	}
}
