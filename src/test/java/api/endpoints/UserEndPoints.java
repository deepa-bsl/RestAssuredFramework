package api.endpoints;
import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class UserEndPoints {


    public static Response createUser(user payload)
    {
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(routes.post_url);
                return response;
    }


    public static Response getUser(String userName)
    {
        Response response=given()
                .pathParam("username",userName)
                .accept(ContentType.JSON)
                .when()
                .get(routes.get_url);
        return response;
    }

    public static Response updateUser(String userName,user payload)
    {
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",userName)
                .body(payload)
                .when()
                .put(routes.update_url);

        return response;
    }

    public static Response deleteUser(String userName)
    {
        Response response=given()
                .pathParam("username",userName)
                .accept(ContentType.JSON)
                .when()
                .delete(routes.delete_url);
        return response;
    }
}
