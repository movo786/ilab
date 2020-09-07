package api;

import cucumber.api.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;
import org.apache.commons.lang3.RandomStringUtils;

public class TestSteps_PetStore {
	private static final String BASE_URL = "https://petstore.swagger.io";
	private static String BASE_PATH;
	private static int petId;

	private Response response;
	private RequestSpecification request;
	private String generatedString;

	@Given("I set the findbystatus method")
	public void i_set_the_findbystatus_method() {
		RestAssured.baseURI = BASE_URL;
		BASE_PATH = "/v2/pet/findByStatus";
		request = RestAssured.given();
	}

	@When("I get the pet list with status \"([^\"]*)\"$")
	public void i_get_the_pet_list_with_status(String status) {
		response = request.when().queryParam("status", status).get(BASE_PATH);
	}

	@Then("Verify that category (\\d+) and name \"([^\"]*)\" exists$")
	public void verify_that_category_and__name_exists(int id, String name) {
		response.then().assertThat().body("category.id", hasItem(id)).and().body("name", hasItem(name));
	}

	@Given("I have a pet with a random name and status \"([^\"]*)\"$")
	public void i_have_a_pet_with_a_random_name_and_status(String status) {
		RestAssured.baseURI = BASE_URL;
		BASE_PATH = "/v2/pet/";
		// Gernerate random name
		generatedString = RandomStringUtils.randomAlphabetic(10);
		//System.out.println(generatedString);
		String jsonBody = "{\n" + "  \"name\": \"" + generatedString + "\",\n" + "  \"status\": \"" + status + "\"\n"
				+ "}";
		request = RestAssured.given().contentType(ContentType.JSON).body(jsonBody);

	}

	@When("I post to api")
	public void i_post_to_api() {
		response = request.when().post(BASE_PATH);
	}

	@Then("Verify pet was added")
	public void verify_pet_was_added() {
		response.then().assertThat().body("name", equalTo(generatedString)).and().body("status", equalTo("available"));
		petId = response.then().extract().path("id");
		System.out.println(petId);
	}

	@Given("I have a petid")
	public void i_have_a_petid() {
		RestAssured.baseURI = BASE_URL;
		System.out.println(petId);
		BASE_PATH = "/v2/pet/" + petId + "/";
		request = RestAssured.given();
		request.log().all();
	}

	@When("I get by petid")
	public void i_get_my_petid() {
		response = request.when().get(BASE_PATH);
		response.then().log().all();

	}

	@Then("Verify my pet has been returned")
	public void verify_my_pet_has_been_returned() {
		response.then().body("id", equalTo(petId));
	}
}
