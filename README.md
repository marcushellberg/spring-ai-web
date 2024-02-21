# Spring AI demo with a Vaadin UI

This is a minimal example of using Vaadin to create a UI for Spring AI.

## Requirements
- OpenAI API key saved as an environment variable `OPENAI_API_KEY`

## Running the app
Run the project using `mvn spring-boot:run` and open [http://localhost:8080](http://localhost:8080) in your browser.

You can also create a GraalVM native image using `mvn package -Pnative -Pproduction native:compile` and run the resulting native image.
