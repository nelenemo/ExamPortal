package com.exam.examserver.configuration;

import com.exam.examserver.util.ReadJsonFileToJsonObject;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.io.IOException;

@OpenAPIDefinition
@Configuration

public class OpenApiConfig {
    @Bean
    public OpenAPI baseOpenAPI() throws IOException {

        ReadJsonFileToJsonObject readJsonFileToJsonObject=new ReadJsonFileToJsonObject();

        ApiResponse successfulResponseAPI=new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("defult",
                                new Example().value("{\"code\" : 200, \"Status\" : \"Ok! \", \"Message\" : \"Successfully Registered! \" }")
                        ))).description("Successfully Registered");

        ApiResponse badResponseAPI=new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("defult",
                                new Example().value(readJsonFileToJsonObject.read().get("badResponseAPI").toString())
        ))).description("Bad Request");

        ApiResponse internalServerErrorAPI=new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("defult",
                                new Example().value("{\"code\" : 500, \"Status\" : \"Internal Server Error ! \", \"Message\" : \"Internal Server Error! \" }")
                        ))).description("Internal Server Error ");

        Components components=new Components();
        components.addResponses("badRequestAPI", badResponseAPI);
        components.addResponses("internalServerErrorAPI", internalServerErrorAPI);
        components.addResponses("successfulResponseAPI", successfulResponseAPI);

        return  new OpenAPI()
                .components(components)
                .info(new Info().title("Spring Doc").version("1.0.0").description("Spring doc"));

    }

}
