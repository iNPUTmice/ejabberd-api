/**
 * 
 */
package de.gultsch.ejabberd.api.requests;

import com.google.gson.annotations.SerializedName;

import de.gultsch.ejabberd.api.Request;

/**
 * @author muhammadali
 *
 */
public class AddRosteritem implements Request {

	@SerializedName("localuser")
	private String localUser;

	@SerializedName("localserver")
	private String localServer;

	private String user;
	private String server;
	private String nick;
	private String group;
	private String subs;
	/**
	 * @param localUser
	 * @param localServer
	 * @param user
	 * @param server
	 * @param nick
	 * @param group
	 * @param subs
	 */
	public AddRosteritem(String localUser, String localServer, String user,
			String server, String nick, String group, String subs) {
		this.localUser = localUser;
		this.localServer = localServer;
		this.user = user;
		this.server = server;
		this.nick = nick;
		this.group = group;
		this.subs = subs;
	}


}
