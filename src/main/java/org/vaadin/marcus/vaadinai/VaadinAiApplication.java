package org.vaadin.marcus.vaadinai;

import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.StreamingChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.vaadin.firitin.components.messagelist.MarkdownMessage;

import java.util.Objects;
import java.util.Optional;

// For native image, compile with ./mvnw -DskipTests -Pnative -Pproduction native:compile
@Push
@SpringBootApplication
public class VaadinAiApplication implements AppShellConfigurator {

	@Route("")
	static class AiChat extends VerticalLayout {

		public AiChat(StreamingChatClient chatClient) {
			var messageList = new VerticalLayout();
			var messageInput = new MessageInput();

			messageInput.addSubmitListener(e -> {
				var message = e.getValue();
				var userMessage = new MarkdownMessage(message, "You", 1);
				var assistantMessage = new MarkdownMessage("Assistant", 2);

				messageList.add(userMessage, assistantMessage);

				chatClient.stream(new Prompt(message))
						.map(res -> Optional.ofNullable(res.getResult().getOutput().getContent()).orElse(""))
						.subscribe(assistantMessage::appendMarkdownAsync);
			});

			add(messageList, messageInput);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(VaadinAiApplication.class, args);
	}
}
