# Spring AI demo with a Vaadin UI

This is a minimal example of using Vaadin to create a UI for Spring AI.

Check out the [streaming branch](https://github.com/marcushellberg/spring-ai-web/tree/streaming) for a version that uses the OpenAI streaming API.

## Requirements
- OpenAI API key saved as an environment variable `OPENAI_API_KEY`
- Java 21 (or beyond), e.g., by [SDKman](https://sdkman.io) (`sdk u java 21.0.2-tem`)

## Running the app
Run the project using `./mvnw spring-boot:run` and open [http://localhost:8080](http://localhost:8080) in your browser.

You can also create a GraalVM native image using `./mvnw package -Pnative -Pproduction native:compile` and run the resulting native image.
Note that you must use a Graal JDK in this case (`sdk u java 21.0.2-graal`).
