package com.example.graphwebhook.service;

import com.google.gson.JsonPrimitive;
import com.microsoft.graph.models.*;
import com.microsoft.graph.requests.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;

import java.util.Optional;

@Slf4j
public class MicrosoftTeamsGraphApiImpl implements MicrosoftTeamsGraphApi {

    final private GraphServiceClient<Request> graphServiceClient;

    public MicrosoftTeamsGraphApiImpl(GraphServiceClient<Request> graphServiceClient) {
        this.graphServiceClient = graphServiceClient;
    }

    @Override
    public boolean createTeam(String displayName, String description) {
        Team team = new Team();
        team.additionalDataManager().put("template@odata.bind", new JsonPrimitive("https://graph.microsoft.com/v1.0/teamsTemplates('standard')"));
        team.displayName = displayName;
        team.description = description;

        try {
            graphClient.teams()
                    .buildRequest()
                    .post(team);
            return true;
        } catch (Exception e) {
            log.error("Can not create team: ", e);
            return false;
        }
    }

    @Override
    public Optional<TeamCollectionPage> myJoinedTeams() {
        try {
            TeamCollectionPage joinedTeams = graphClient.me().joinedTeams()
                    .buildRequest()
                    .get();

            return Optional.of(joinedTeams);
        } catch (Exception e) {
            log.error("Can not get my joined teams: ", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<ConversationMemberCollectionPage> membersOfATeam(String teamId) {
        try {
            ConversationMemberCollectionPage members = graphClient.teams(teamId).members()
                    .buildRequest()
                    .get();

            return Optional.of(members);
        } catch (Exception e) {
            log.error("Can not get members of a team: ", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<ChannelCollectionPage> channelsOfATeamWhichIAmMemberOf(String teamId) {
        try {
            ChannelCollectionPage channels = graphClient.teams(teamId).channels()
                    .buildRequest()
                    .get();

            return Optional.of(channels);
        } catch (Exception e) {
            log.error("Can not get channels of a team which i am member of: ", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Channel> channelInfo(String teamId, String channelId) {
        try {
            Channel channel = graphClient.teams(teamId).channels(channelId)
                    .buildRequest()
                    .get();

            return Optional.of(channel);
        } catch (Exception e) {
            log.error("Can not get channel info: ", e);
            return Optional.empty();
        }
    }

    @Override
    public boolean createChannel(String teamId, String displayName, String description) {
        Channel channel = new Channel();
        channel.displayName = displayName;
        channel.description = description;

        try {
            graphClient.teams(teamId).channels()
                    .buildRequest()
                    .post(channel);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public TeamsAppInstallationCollectionPage appsInATeam(String teamId) {
        TeamsAppInstallationCollectionPage installedApps = graphClient.teams(teamId).installedApps()
                .buildRequest()
                .expand("teamsAppDefinition")
                .get();

        return installedApps;
    }

    @Override
    public TeamsTabCollectionPage tabsInAChannel(String teamId, String channelId) {
        TeamsTabCollectionPage tabs = graphClient.teams(teamId).channels(channelId).tabs()
                .buildRequest()
                .expand("teamsApp")
                .get();

        return tabs;
    }

    @Override
    public DriveItemCollectionPage itemsInATeamDrive(String groupIdForTeams, String items) {
        DriveItemCollectionPage children = graphClient.groups(groupIdForTeams).drive().items(items).children()
                .buildRequest()
                .get();

        return children;
    }

    @Override
    public boolean createChat(Chat chat) {
        try {
            graphClient.chats()
                    .buildRequest()
                    .post(chat);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean sendChannelMessage(String content, String teamId, String channelId) {
        ChatMessage chatMessage = new ChatMessage();
        ItemBody body = new ItemBody();
        body.content = content;
        chatMessage.body = body;

        try {
            graphClient.teams(teamId).channels(channelId).messages()
                    .buildRequest()
                    .post(chatMessage);
            return true;
        } catch (Exception e) {
            log.error("Can not send channel message: ", e);
            return false;
        }
    }

    @Override
    public TeamworkTagCollectionPage getTagsInATeam(String teamId) {
        TeamworkTagCollectionPage tags = graphClient.teams(teamId).tags()
                .buildRequest()
                .get();

        return tags;
    }

    @Override
    public TeamworkTag getASingleTagInATeam(String teamId, String teamworkTagId) {
        TeamworkTag teamworkTag = graphClient.teams(teamId).tags(teamworkTagId)
                .buildRequest()
                .get();

        return teamworkTag;
    }

    @Override
    public boolean createATagInATeam(String teamId, TeamworkTag teamworkTag) {
        try {
            graphClient.teams(teamId).tags()
                    .buildRequest()
                    .post(teamworkTag);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateATagInATeam(String teamId, String teamworkTagId, TeamworkTag teamworkTag) {
        try {
            graphClient.teams(teamId).tags(teamworkTagId)
                    .buildRequest()
                    .patch(teamworkTag);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteATagInATeam(String teamId, String teamworkTagId) {
        try {
            graphClient.teams(teamId).tags(teamworkTagId)
                    .buildRequest()
                    .delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public TeamworkTagMemberCollectionPage getsAllMembersOfATagInATeam(String teamId, String teamworkTagId) {
        TeamworkTagMemberCollectionPage members = graphClient.teams(teamId).tags(teamworkTagId).members()
                .buildRequest()
                .get();

        return members;
    }

    @Override
    public TeamworkTagMember getASingleMemberFromATagInATeam(String teamId, String teamworkTagId, String teamworkTagMemberId) {
        TeamworkTagMember teamworkTagMember = graphClient.teams(teamId).tags(teamworkTagId).members(teamworkTagMemberId)
                .buildRequest()
                .get();

        return teamworkTagMember;
    }

    @Override
    public boolean addsAMemberToATagInATeam(String userId, String teamId, String teamworkTagId) {
        TeamworkTagMember teamworkTagMember = new TeamworkTagMember();
        teamworkTagMember.userId = userId;

        try {
            graphClient.teams(teamId).tags(teamworkTagId).members()
                    .buildRequest()
                    .post(teamworkTagMember);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteAMemberFromATagInATeam(String teamId, String teamworkTagId, String teamworkTagMemberId) {
        try {
            graphClient.teams(teamId).tags(teamworkTagId).members(teamworkTagMemberId)
                    .buildRequest()
                    .delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // -----------------------------------------------------
    // Beta Version
    // -----------------------------------------------------

    @Override
    public ChatMessageCollectionPage messagesInAChannelWithoutReplies(String groupIdForTeams, String channelId) {
        ChatMessageCollectionPage messages = graphClient.teams(groupIdForTeams).channels(channelId).messages()
                .buildRequest()
                .get();

        return messages;
    }

    @Override
    public ChatMessage messageInAChannel(String groupIdForTeams, String channelId, String messageId) {
        ChatMessage chatMessage = graphClient.teams(groupIdForTeams).channels(channelId).messages(messageId)
                .buildRequest()
                .get();

        return chatMessage;
    }

    @Override
    public ChatMessageCollectionPage repliesToAMessageInChannel(String groupIdForTeams, String channelId, String messageId) {
        ChatMessageCollectionPage replies = graphClient.teams(groupIdForTeams).channels(channelId).messages(messageId).replies()
                .buildRequest()
                .get();

        return replies;
    }

    @Override
    public ChatMessage replyOfAMessage(String groupIdForTeams, String channelId, String messageId, String replyId) {
        ChatMessage chatMessage = graphClient.teams(groupIdForTeams).channels(channelId).messages(messageId).replies(replyId)
                .buildRequest()
                .get();

        return chatMessage;
    }

    @Override
    public UserScopeTeamsAppInstallationCollectionPage appsInstalledForUser() {
        UserScopeTeamsAppInstallationCollectionPage installedApps = graphClient.me().teamwork().installedApps()
                .buildRequest()
                .expand("teamsApp")
                .get();

        return installedApps;
    }

    @Override
    public ConversationMemberCollectionPage listMembersOfAChat(String chatId) {
        ConversationMemberCollectionPage members = graphClient.chats(chatId).members()
                .buildRequest()
                .get();

        return members;
    }

    @Override
    public ConversationMember memberInAChat(String chatId, String membershipId) {
        ConversationMember conversationMember = graphClient.chats(chatId).members(membershipId)
                .buildRequest()
                .get();

        return conversationMember;
    }
}
