package org.vaadin.marcus.vaadinai;

import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import org.springframework.ai.chat.StreamingChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.vaadin.firitin.components.messagelist.MarkdownMessage;
import org.vaadin.firitin.components.messagelist.MarkdownMessage.Color;

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
				var question = e.getValue();
				var userMessage = new MarkdownMessage(question, "You", Color.AVATAR_PRESETS[1]);
				var assistantMessage = new MarkdownMessage("Assistant", Color.AVATAR_PRESETS[2]);

				messageList.add(userMessage, assistantMessage);

				chatClient.stream(new Prompt(question))
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
