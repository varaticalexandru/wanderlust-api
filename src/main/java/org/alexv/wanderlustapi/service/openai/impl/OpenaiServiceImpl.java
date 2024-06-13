package org.alexv.wanderlustapi.service.openai.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.OpenAiResponse;
import com.theokanning.openai.assistants.assistant.Assistant;
import com.theokanning.openai.assistants.assistant.AssistantRequest;
import com.theokanning.openai.assistants.message.Message;
import com.theokanning.openai.assistants.message.MessageListSearchParameters;
import com.theokanning.openai.assistants.message.MessageRequest;
import com.theokanning.openai.assistants.run.*;
import com.theokanning.openai.assistants.thread.Thread;
import com.theokanning.openai.assistants.thread.ThreadRequest;
import com.theokanning.openai.completion.chat.ChatResponseFormat;
import com.theokanning.openai.function.FunctionExecutorManager;
import com.theokanning.openai.service.OpenAiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.alexv.wanderlustapi.api.dto.openai.OpenaiRequestDto;
import org.alexv.wanderlustapi.api.dto.openai.OpenaiResponseDto;
import org.alexv.wanderlustapi.api.dto.openai.RecommendationsRequestDto;
import org.alexv.wanderlustapi.api.dto.openai.RecommendationsResponseDto;
import org.alexv.wanderlustapi.client.openai.OpenaiProperties;
import org.alexv.wanderlustapi.client.openai.RecommendationsClient;
import org.alexv.wanderlustapi.service.openai.OpenaiService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OpenaiServiceImpl implements OpenaiService {

    RecommendationsClient recommendationsClient;
    ObjectMapper objectMapper;
    OpenaiProperties openaiProperties;
    ObjectMapper jacksonObjectMapper;
    FunctionExecutorManager executor;
    OpenAiService service;

    @Override
    public RecommendationsResponseDto getRecommendations(RecommendationsRequestDto request) {

        String requestJson;
        try {
            requestJson = objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String escapedRequestJson = requestJson.replace("\"", "\\\"");

        Map<String, String> systemMessage = Map.of(
                "role", "system",
                "content", openaiProperties.getSystemMessage()
        );

        Map<String, String> userMessage = Map.of(
                "role", "system",
                "content", escapedRequestJson
        );

        OpenaiRequestDto openaiRequestDto = OpenaiRequestDto.builder()
                .model(openaiProperties.getModel())
                .responseFormat(openaiProperties.getResponseFormat())
                .topP((Double) openaiProperties.getParameters().get("top_p"))
                .temperature((Double) openaiProperties.getParameters().get("temperature"))
                .maxTokens((Integer) openaiProperties.getParameters().get("max_tokens"))
                .frequencyPenalty((Double) openaiProperties.getParameters().get("frequency_penalty"))
                .presencePenalty((Double) openaiProperties.getParameters().get("presence_penalty"))
                .messages(List.of(systemMessage, userMessage))
                .build();

        OpenaiResponseDto recommendationsResponse = recommendationsClient.getRecommendations(openaiRequestDto);
        String content = recommendationsResponse.getChoices().get(0).getMessage().getContent();

        String recommendations = content.replace("\\", "");
        RecommendationsResponseDto recommendationsResponseDto;
        try {
             recommendationsResponseDto = objectMapper.readValue(recommendations, RecommendationsResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        log.info(recommendationsResponseDto.toString());

        return recommendationsResponseDto;
    }

    @Override
    public RecommendationsResponseDto recommendationsAssistantCall(RecommendationsRequestDto request) {

        String requestJson;
        try {
            requestJson = objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String assistantId = openaiProperties.getAssistantId();
        String threadId = openaiProperties.getThreadId();

        MessageRequest messageRequest = MessageRequest.builder()
                .content(requestJson)
                .build();

        //add message to thread
        service.createMessage(threadId, messageRequest);
        RunCreateRequest runCreateRequest = RunCreateRequest.builder().assistantId(assistantId).build();

        Run run = service.createRun(threadId, runCreateRequest);

        Run retrievedRun = service.retrieveRun(threadId, run.getId());
        while (!(retrievedRun.getStatus().equals("completed"))
                && !(retrievedRun.getStatus().equals("failed"))
                && !(retrievedRun.getStatus().equals("expired"))
                && !(retrievedRun.getStatus().equals("incomplete"))
                && !(retrievedRun.getStatus().equals("requires_action"))) {
            retrievedRun = service.retrieveRun(threadId, run.getId());
        }

        OpenAiResponse<Message> response = service.listMessages(threadId, MessageListSearchParameters.builder()
                .runId(retrievedRun.getId()).build());
        List<Message> messages = response.getData();

        String json = messages.getFirst().getContent().getFirst().getText().getValue();
        String unescapedJson = json.replace("\\\"", "\"");

        log.info(unescapedJson);

        RecommendationsResponseDto recommendationsResponseDto;
        try {
            recommendationsResponseDto = objectMapper.readValue(unescapedJson, RecommendationsResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return recommendationsResponseDto;
    }

    private String createAssistant(OpenAiService service) {

        AssistantRequest assistantRequest = AssistantRequest.builder()
                .model(openaiProperties.getModel()).name("travel assistant")
                .instructions(openaiProperties.getSystemMessage())
                .temperature((Double) openaiProperties.getParameters().get("temperature"))
                .topP((Double) openaiProperties.getParameters().get("top_p"))
                .responseFormat(ChatResponseFormat.JSON_OBJECT)
                .build();

        Assistant assistant = service.createAssistant(assistantRequest);
        String assistantId = assistant.getId();
        return assistantId;
    }

    private String createThread(OpenAiService service) {
        ThreadRequest threadRequest = ThreadRequest.builder().build();
        Thread thread = service.createThread(threadRequest);

        return thread.getId();
    }

}
