package com.example.graphwebhook.service;

import com.example.graphwebhook.core.MicrosoftTeamsGraphApi;
import com.microsoft.graph.logger.DefaultLogger;
import com.microsoft.graph.serializer.DefaultSerializer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class MicrosoftTeamsGraphApiImplTest {

    private @Autowired MicrosoftTeamsGraphApi graphApi;
    public DefaultSerializer serializer;
    private final static String TEAM_ID = "933db7b3-4c3e-4ad1-acec-c5011dbea87b";
    private final static String CHANNEL_ID = "19:4db0361697c74095934dd76b030b6c7a@thread.tacv2";

    @BeforeEach
    public void setUp() {
        serializer = new DefaultSerializer(new DefaultLogger());
    }

    @Test
    void createTeam() {
    }

    @Test
    void myJoinedTeams() {
    }

    @Test
    void membersOfATeam() {
    }

    @Test
    void channelsOfATeamWhichIAmMemberOf() {
    }

    @Test
    void channelInfo() {
    }

    @Test
    void createChannel() {
    }

    @Test
    void appsInATeam() {
    }

    @Test
    void tabsInAChannel() {
    }

    @Test
    void itemsInATeamDrive() {
    }

    @Test
    void createChat() {
    }

    @Test
    void sendChannelMessage() {
        assertTrue(graphApi.sendChannelMessage("This is a test message from FinitX notification bot. please ignore it.", TEAM_ID, CHANNEL_ID));
    }

    @Test
    void getTagsInATeam() {
    }

    @Test
    void getASingleTagInATeam() {
    }

    @Test
    void createATagInATeam() {
    }

    @Test
    void updateATagInATeam() {
    }

    @Test
    void deleteATagInATeam() {
    }

    @Test
    void getsAllMembersOfATagInATeam() {
    }

    @Test
    void getASingleMemberFromATagInATeam() {
    }

    @Test
    void addsAMemberToATagInATeam() {
    }

    @Test
    void deleteAMemberFromATagInATeam() {
    }

    @Test
    void messagesInAChannelWithoutReplies() {
        assertTrue(graphApi.messagesInAChannelWithoutReplies(TEAM_ID, CHANNEL_ID).isPresent());
    }

    @Test
    void messageInAChannel() {
    }

    @Test
    void repliesToAMessageInChannel() {
    }

    @Test
    void replyOfAMessage() {
    }

    @Test
    void appsInstalledForUser() {
    }

    @Test
    void listMembersOfAChat() {
    }

    @Test
    void memberInAChat() {
    }
}