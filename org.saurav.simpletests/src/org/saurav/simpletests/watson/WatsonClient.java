package org.saurav.simpletests.watson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.security.BearerTokenAuthenticator;
import com.ibm.watson.assistant.v1.model.CreateEntity;
import com.ibm.watson.assistant.v1.model.CreateIntent;
import com.ibm.watson.assistant.v1.model.CreateWorkspaceOptions;
import com.ibm.watson.assistant.v1.model.UpdateWorkspaceOptions;
import com.ibm.watson.assistant.v1.model.Workspace;
import com.ibm.watson.assistant.v1.model.WorkspaceCollection;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.CreateSessionOptions;
import com.ibm.watson.assistant.v2.model.DeleteSessionOptions;
import com.ibm.watson.assistant.v2.model.DialogNodeAction;
import com.ibm.watson.assistant.v2.model.MessageInput;
import com.ibm.watson.assistant.v2.model.MessageOptions;
import com.ibm.watson.assistant.v2.model.MessageResponse;
import com.ibm.watson.assistant.v2.model.RuntimeIntent;
import com.ibm.watson.assistant.v2.model.RuntimeResponseGeneric;
import com.ibm.watson.assistant.v2.model.SessionResponse;

/**
 * Test class to play around with the Watson APIs.
 * Both V1 and V2 APIs are tested
 * Code inspired from https://cloud.ibm.com/docs/assistant-data?topic=assistant-data-api-client
 */
public class WatsonClient {

    private Assistant v2Service;
    private com.ibm.watson.assistant.v1.Assistant v1Service;
    private String assistantId = ""; //use the assistant id here

    private String skillId = ""; //use the workspace/skill id here

    public static void main(String args[]) {
        WatsonClient watsonClient = new WatsonClient();

        String apiVersion  ="v2"; //change the API version here

        if("v2".equals(apiVersion)) {
            String sessionId = watsonClient.setupAndGetSession();
            watsonClient.detectActions(sessionId);
            // watsonClient.executeEmptyMessage(sessionId);

            //  watsonClient.detectIntent(sessionId);
        }else if ("v1".equals(apiVersion)) {

            watsonClient.setUpV1Assistant();

          //  watsonClient.executeEmptyMessageV1();

          //  watsonClient.listWorkspaces();

          //  watsonClient.createWorkspaceAysncEmpty();

            watsonClient.updateWorkspace(watsonClient.skillId);
        }




    }

    private String setupAndGetSession() {

        BearerTokenAuthenticator bearerTokenAuthenticator = new BearerTokenAuthenticator(WatsonConstants.BEARER_TOKEN);

            this.v2Service = new Assistant("2021-11-27", bearerTokenAuthenticator);

            //disable SSL and set service url

            HttpConfigOptions options = new HttpConfigOptions.Builder().disableSslVerification(true)
                    .build();

            this.v2Service.setServiceUrl(WatsonConstants.CLOUD_PAK_URL);
            this.v2Service.configureClient(options);

            // Create session.
            CreateSessionOptions createSessionOptions = new CreateSessionOptions.Builder(this.assistantId).build();
            SessionResponse session = v2Service.createSession(createSessionOptions)
                    .execute()
                    .getResult();
            String sessionId = session.getSessionId();
            System.out.println("Session id recieved :: " + sessionId);

        return sessionId;
    }

    private void setUpV1Assistant(){
        BearerTokenAuthenticator bearerTokenAuthenticator = new BearerTokenAuthenticator(WatsonConstants.BEARER_TOKEN);
        this.v1Service = new com.ibm.watson.assistant.v1.Assistant("2021-11-27", bearerTokenAuthenticator);

        HttpConfigOptions options = new HttpConfigOptions.Builder().disableSslVerification(true)
                .build();

        this.v1Service.setServiceUrl(WatsonConstants.CLOUD_PAK_URL);
        this.v1Service.configureClient(options);
    }

    private void listWorkspaces() {
        // listing our workspaces with an instance of the Assistant v1 service
        Response<WorkspaceCollection> response = this.v1Service.listWorkspaces().execute();

        // pulling out the specific API method response, which we can manipulate as usual
        WorkspaceCollection collection = response.getResult();
        System.out.println("My workspaces: " + collection.getWorkspaces());
    }

    private void executeEmptyMessageV1() {
        com.ibm.watson.assistant.v1.model.MessageInput input = new com.ibm.watson.assistant.v1.model.MessageInput();
        input.setText("Hi");
        com.ibm.watson.assistant.v1.model.MessageOptions options = new com.ibm.watson.assistant.v1.model.MessageOptions.Builder("b57888cc-9726-4c13-92d0-e9ae36f43157").input(input).build(); // got the workspace id by executing list workspaces

        // sync
        com.ibm.watson.assistant.v1.model.MessageResponse response = this.v1Service.message(options).execute().getResult();
        System.out.println(response);

    }

    private void executeEmptyMessage(String sessionId) {

        // Start conversation with empty message.
        MessageOptions messageOptions = new MessageOptions.Builder(this.assistantId, sessionId).build();
        MessageResponse response = this.v2Service.message(messageOptions)
                .execute()
                .getResult();

        // Print the output from dialog, if any. Assumes a single text response.
        System.out.println(response.getOutput()
                .getGeneric()
                .get(0)
                .text());

        // We're done, so we delete the session.
        DeleteSessionOptions deleteSessionOptions = new DeleteSessionOptions.Builder(this.assistantId, sessionId).build();
        v2Service.deleteSession(deleteSessionOptions)
                .execute();

    }

    private void detectIntent(String sessionId) {
        String inputText = "Hi";

        do {
            //send message to assistant

            MessageInput input = new MessageInput.Builder().text(inputText)
                    .build();

            MessageOptions messageOptions = new MessageOptions.Builder(assistantId, sessionId).input(input)
                    .build();

            MessageResponse response = this.v2Service.message(messageOptions)
                    .execute()
                    .getResult();

            //If an intent was detected , print it to the console

            List<RuntimeIntent> responseIntents = response.getOutput()
                    .getIntents();

            if (responseIntents.size() > 0) {
                System.out.println("Detected intent: #" + responseIntents.get(0)
                        .intent());
            }

            // Print the output from dialog, if any. Assumes a single text response.
            List<RuntimeResponseGeneric> responseGeneric = response.getOutput()
                    .getGeneric();
            if (responseGeneric.size() > 0) {
                System.out.println(response.getOutput()
                        .getGeneric()
                        .get(0)
                        .text());

            }
            System.out.print(">> ");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                inputText = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } while (!inputText.equals("quit"));

        // Prompt for next round of input.

    }

    private void detectActions(String sessionId) {

        String currentAction;
        String inputText = "";
        do {
            currentAction = "";

            // Send message to assistant.
            MessageInput input = new MessageInput.Builder().text(inputText)
                    .build();
            MessageOptions messageOptions = new MessageOptions.Builder(this.assistantId, sessionId).input(input)
                    .build();
            MessageResponse response = v2Service.message(messageOptions)
                    .execute()
                    .getResult();

            // Print the output from dialog, if any. Assumes a single text response.
            List<RuntimeResponseGeneric> responseGeneric = response.getOutput()
                    .getGeneric();
            if (responseGeneric.size() > 0) {
                System.out.println(response.getOutput()
                        .getGeneric()
                        .get(0)
                        .text());
            }

            // Check for any actions requested by the assistant.
            List<DialogNodeAction> responseActions = response.getOutput()
                    .getActions();
            if (responseActions != null) {
                if (responseActions.get(0)
                        .getType()
                        .equals("client")) {
                    currentAction = responseActions.get(0)
                            .getName();
                }
            }

            // User asked what time it is, so we output the local system time.
            if (currentAction.equals("display_time")) {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("h:mm:ss a");
                LocalTime time = LocalTime.now();
                System.out.println("The current time is " + time.format(fmt) + ".");
            }

            // If we're not done, prompt for next round of input.
            if (!currentAction.equals("end_conversation")) {
                System.out.print(">> ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                try {
                    inputText = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } while (!currentAction.equals("end_conversation"));

    }

    private void createWorkspaceAysncEmpty(){

       // new CreateEntity.Builder().addValues(new CreateValue().newBuilder().)


        String workspaceName = "API test Saurav";
        String workspaceDescription = "Example workspace created via API";

        CreateWorkspaceOptions options = new CreateWorkspaceOptions.Builder()
                .name(workspaceName)
                .description(workspaceDescription)
                .build();

        Workspace response = this.v1Service.createWorkspace(options).execute().getResult();

        System.out.println("Create workspace resopnse : " + response);

    }

    private void updateWorkspace(String workspaceId) {
       // CreateIntentOptions intentOptions = new CreateIntentOptions.Builder().workspaceId(workspaceId).intent("testintentapi").description("intent created from API").build();

        CreateIntent intent = new CreateIntent.Builder().intent("testintentapi").description("intent created from API").build();

        CreateEntity entity = new CreateEntity.Builder().entity("testentityapi").description("entity created from API").build();


        UpdateWorkspaceOptions updateWorkspaceOptions = new UpdateWorkspaceOptions.Builder().workspaceId(workspaceId).addIntent(intent).addEntity(entity).build();

        Workspace response = this.v1Service.updateWorkspace(updateWorkspaceOptions).execute().getResult();

        System.out.println("Updated workspace response : " + response);

    }

}
