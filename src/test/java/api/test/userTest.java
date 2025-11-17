package api.test;

import api.endpoints.UserEndPoints;
import api.payload.user;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/*  int id;
   String firstName;
    String lastName;
    String email;
    String password;
    String phone;
    int userStatus = 0;
    */


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
        Response response=UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");

    }
}
