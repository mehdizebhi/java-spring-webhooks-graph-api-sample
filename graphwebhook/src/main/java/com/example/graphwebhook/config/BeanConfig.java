package com.example.graphwebhook.config;

import com.azure.identity.ClientSecretCredentialBuilder;
import com.corundumstudio.socketio.SocketIOServer;
import com.example.graphwebhook.core.MicrosoftTeamsGraphApi;
import com.example.graphwebhook.core.MicrosoftTeamsGraphApiImpl;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.requests.GraphServiceClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Configuration
public class BeanConfig {

    @Value("${spring.cloud.azure.active-directory.credential.client-id}")
    private String clientId;

    @Value("${spring.cloud.azure.active-directory.credential.client-secret}")
    private String clientSecret;

    @Value("${spring.cloud.azure.active-directory.profile.tenant-id}")
    private String tenantId;

    @Value("${spring.cloud.azure.active-directory.authorization-clients.apponly.scopes}")
    private String scopes;

    /**
     * @return A configured SocketIO server instance
     */
    @Bean
    public SocketIOServer socketIOServer() {
        var config = new com.corundumstudio.socketio.Configuration();
        config.setHostname("localhost");
        config.setPort(8081);
        return new SocketIOServer(config);
    }

    @Bean
    public GraphServiceClient<Request> appClient() {
        var clientSecretCredential =  new ClientSecretCredentialBuilder()
                .clientId(this.clientId)
                .tenantId(this.tenantId)
                .clientSecret(this.clientSecret)
                .build();

        // Use the .default scope when using app-only auth
        final TokenCredentialAuthProvider authProvider =  new TokenCredentialAuthProvider(
                List.of(this.scopes), clientSecretCredential);

        return GraphServiceClient.builder()
                .authenticationProvider(authProvider)
                .buildClient();
    }

    @Bean
    @Primary
    public MicrosoftTeamsGraphApi graphApi() {
        return new MicrosoftTeamsGraphApiImpl(this.appClient());
    }
}
