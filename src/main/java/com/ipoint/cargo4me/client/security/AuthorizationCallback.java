/**
 * 
 */
package com.ipoint.cargo4me.client.security;

/**
 * @author Kirill Shchegolev
 * 
 */
public interface AuthorizationCallback {

	void onAuthorize();

	void onError(String error);

}
