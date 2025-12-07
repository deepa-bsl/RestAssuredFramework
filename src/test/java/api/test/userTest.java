package api.test;

import api.endpoints.UserEndPoints;
import api.payload.user;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.given;


public class userTest {
    Faker faker;
    user userPayload;


    @BeforeClass
    public void setupData()
    {
        faker=new Faker();
        userPayload=new user();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUserName(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());


    }

    @Test(priority = 1)
    public void testPostUser()
    {
       /* Response response=UserEndPoints.createUser(userPayload);
        response.then().log().all();
    //    String username=response.jsonPath().getString("username");
      //  System.out.println("created username "+username);
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json"); */
        RestAssured.baseURI ="https://petstore.swagger.io/v2/user";
                Response response=given()
                        .contentType("application/json")
                        .body(userPayload)
                        .when()
                        .post()
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        String username = response.jsonPath().getString("username"); // Replace "username" with the actual JSON path to the username field

        System.out.println("Created Username: " + username);

    }

    @Test(priority = 2)
    public void testGetUser()
    {
        Response response=UserEndPoints.getUser(this.userPayload.getUserName());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 3)
    public void updateUser()
    {

        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response=UserEndPoints.updateUser(this.userPayload.getUserName(),userPayload);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);

   /*     // check data after update
        Response responseAfterupdate=UserEndPoints.getUser(this.userPayload.getUserName());
        response.then().log().all();
        Assert.assertEquals(responseAfterupdate.getStatusCode(),200); */

    }

    @Test(priority = 4)
    public void deleteUser()
    {
        Response response=UserEndPoints.deleteUser(this.userPayload.getUserName());
//        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }

}
