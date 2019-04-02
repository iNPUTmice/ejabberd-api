package de.gultsch.ejabberd.api.hla;

import de.gultsch.ejabberd.api.EjabberdApi;
import de.gultsch.ejabberd.api.Request;
import de.gultsch.ejabberd.api.RequestFailedException;
import de.gultsch.ejabberd.api.requests.*;
import de.gultsch.ejabberd.api.results.Info;
import de.gultsch.ejabberd.api.results.Last;

import java.util.Arrays;
import java.util.Collection;

public class Ejabberd {

    private final EjabberdApi api;

    public Ejabberd() {
        this.api = new EjabberdApi();
    }

    public Ejabberd(EjabberdApi api) {
        this.api = api;
    }

    public Collection<String> getConnectedUsers() throws RequestFailedException {
        return executeAndReturnAsStringCollection(new ConnectedUsers());
    }

    private Collection<String> executeAndReturnAsStringCollection(Request request) throws RequestFailedException {
        return Arrays.asList(api.execute(request, String[].class));
    }

    public Collection<String> getRegisteredUsers(String host) throws RequestFailedException {
        return executeAndReturnAsStringCollection(new RegisteredUsers(host));
    }

    public Collection<String> getRegisteredVhosts() throws RequestFailedException {
        return executeAndReturnAsStringCollection(new RegisteredVhosts());
    }

    public void register(String username, String host, String password) throws RequestFailedException {
        api.executeWithSuccessOrThrow(new Register(username, host, password));
    }

    public void unregister(String username, String host) throws RequestFailedException {
        api.executeWithSuccessOrThrow(new Unregister(username,host));
    }

    public boolean checkAccount(String username, String host) throws RequestFailedException {
        return api.execute(new CheckAccount(username, host));
    }

    public boolean checkPassword(String username, String host, String password) throws RequestFailedException {
        return api.execute(new CheckPassword(username, host, password));
    }

    public void changePassword(String username, String host, String newPassword) throws RequestFailedException {
        api.executeWithSuccessOrThrow(new ChangePassword(username, host, newPassword));
    }
    
    public boolean addRosterItem(String localUser, String localServer, String user, String server, String nick, String group, String subs) throws RequestFailedException{
    	return api.execute(new AddRosteritem(localUser, localServer, user, server, nick, group, subs));
    }

    public boolean createSharedRosterGroup(String host, String group, String name) throws RequestFailedException {
        return api.execute(new SrgCreate(host, group, name));
    }

    public boolean deleteSharedRosterGroup(String host, String group) throws RequestFailedException {
        return api.execute(new SrgDelete(host, group));
    }

    public Collection<Info> getSharedRosterGroupInfo(String host, String group) throws RequestFailedException {
        return Arrays.asList(api.execute(new SrgGetInfo(host, group), Info[].class));
    }

    public boolean addUserToShareRosterGroup(String host, String group, String user, String grouphost) throws RequestFailedException {
        return api.execute(new SrgUserAdd(host, group, user, grouphost));
    }

    public Collection<String> getUserResources(String username, String host) throws RequestFailedException {
        return executeAndReturnAsStringCollection(new UserResources(username, host));
    }

    public boolean sendChatMessage(String from, String to, String subject, String body) throws RequestFailedException {
        return api.execute(new SendMessage(from, to, subject, body));
    }

    public Last getLast(String username, String host) throws RequestFailedException {
        return api.execute(new GetLast(username, host), Last.class);
    }

    public boolean reloadConfig() throws RequestFailedException {
        return api.execute(new ReloadConfig());
    }
}
