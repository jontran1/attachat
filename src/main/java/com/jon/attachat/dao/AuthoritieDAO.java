package com.jon.attachat.dao;

import com.jon.attachat.entity.Authoritie;

/**
 * AuthoriteDAO interface contians all the functions needed for Authority. 
 * @author jonat
 *
 */
public interface AuthoritieDAO {
	
	public void saveAuthoritie(Authoritie authoritie);
	
	public void deleteAuthoritie(Authoritie authoritie);

}
