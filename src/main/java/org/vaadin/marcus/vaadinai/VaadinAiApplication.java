package org.vaadin.marcus.vaadinai;

import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.ai.chat.ChatClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// compile with ./mvnw -DskipTests -Pnative -Pproduction native:compile
@SpringBootApplication
public class VaadinAiApplication {

	@Route("")
	static class AiChat extends VerticalLayout {

		public AiChat(ChatClient chatClient) {
			var messageList = new VerticalLayout();
			var messageInput = new MessageInput();

			messageInput.addSubmitListener(e -> {
				messageList.add(new Paragraph("You: " + e.getValue()));
				messageList.add(new Paragraph("AI: " + chatClient.call(e.getValue())));
			});

			add(messageList, messageInput);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(VaadinAiApplication.class, args);
	}
}
