package org.cruxframework.mediamanager.offline.client.entity;


import org.cruxframework.crux.core.client.db.annotation.Store;
import org.cruxframework.mediamanager.core.client.dto.UserDTO;
import org.cruxframework.mediamanager.offline.client.reuse.OfflineEntity;

/**
 * Class description: 
 * @author Bruno.Rafael
 */


@Store(User.STORE_NAME)
public class User extends OfflineEntity<UserDTO> 
//implements AbstractEntity<UserDTO> 
{
	public static final String STORE_NAME = "user";
	private String login;
	private String password;
	
	/*******************************
	 * Methods Abstract
	 ******************************/
	@Override
	public String getStoreName()
	{
		return STORE_NAME;
	}
	@Override
	public UserDTO getDTORepresentation()
	{
		UserDTO dto = new UserDTO();
		dto.setName(getName());
		dto.setLogin(getLogin());
		dto.setPassword(getPassword());
		return dto;
	}
	
	
	
	/*******************************
	 * GETTERS and SETTERS 
	 ******************************/
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}	
}
