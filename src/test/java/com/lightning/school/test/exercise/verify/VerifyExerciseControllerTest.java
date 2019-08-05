package com.lightning.school.test.exercise.verify;

import com.lightning.school.mvc.api.in.VerifyExoIn;
import com.lightning.school.mvc.api.in.user.UserLoginIn;
import com.lightning.school.mvc.api.out.ResultExerciseOut;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VerifyExerciseControllerTest {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @LocalServerPort
    private int port;
    private String token;

    @Before
    public void init() {
        RestAssured.port = port;
        RestAssured.baseURI = "https://localhost";
        connect();
    }

    public void connect() {
        UserLoginIn dto = new UserLoginIn();
        dto.setMail("admin@ls.fr");
        dto.setPassword("admin");

        String location =
                given().relaxedHTTPSValidation()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(dto)
                        .when()
                        .post("/api/auth/login")
                        .then()
                        .log().all()
                        .statusCode(202)
                        .extract()
                        .header(AUTHORIZATION_HEADER);
        Assert.assertFalse(location.isEmpty());
        this.token = location;
    }

    @Test
    public void controllerTest(){
        VerifyExoIn in = new VerifyExoIn("1!10|&", null, 3,5);

        ResultExerciseOut resultExerciseOut = given().relaxedHTTPSValidation().log().all().contentType(ContentType.JSON).header(AUTHORIZATION_HEADER, token)
                .body(in).when().post("/api/verify/exercise").then().log().all().statusCode(200).extract().as(ResultExerciseOut.class);

        Assert.assertFalse(resultExerciseOut.getResultExercice());
    }

}
