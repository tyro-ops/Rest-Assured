package org.sayem.api;

import com.jayway.restassured.path.json.JsonPath;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.IOException;

public class Post {
    private TestDataProvider factory;

    @Parameters("dataLocation")
    public Post(@Optional(Repository.DATA_LOCATION)
                             String dataLocation) throws IOException {
        factory = TestDataProvider.create(dataLocation);
    }

    @Test(groups = "post")
    public void postRequest() {

        JsonObject request = Json.createObjectBuilder()
                .add("UserName", factory.data(Repository.USER_NAME))
                .add("Password", factory.data(Repository.PASSWORD))
                .build();

        RestAdapter response = PostAdapter.builder()
                .setContentType(ContentType.JSON)
                .setRequestObject(request)
                .setEndPoint(factory.data(Repository.ENDPOINT))
                .setMethodName(factory.data(Repository.METHOD))
                .build();

        JsonPath jsonPath = response.execute();
        jsonPath.prettyPrint();

    }
}