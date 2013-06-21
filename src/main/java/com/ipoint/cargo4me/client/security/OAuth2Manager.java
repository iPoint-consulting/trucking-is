/**
 * 
 */
package com.ipoint.cargo4me.client.security;

/**
 * @author Kirill Shchegolev
 * 
 */
public class OAuth2Manager {

	private static final String SERVER_ADDRESS = "http://test-trucking.appspot.com";

	private static final String AUTHORIZATION_PATH = SERVER_ADDRESS
			+ "/oauth/authorize";

	final static String CLIENT_ID = "Trucking IS";

	final static String SCOPE = "read,write";

	private static String accessToken;

	public static native void autorize(AuthorizationCallback callback) /*-{
		var AUTH_URL = @com.ipoint.cargo4me.client.security.OAuth2Manager::AUTHORIZATION_PATH;
		var CLIENT_ID = @com.ipoint.cargo4me.client.security.OAuth2Manager::CLIENT_ID;
		var SCOPE = @com.ipoint.cargo4me.client.security.OAuth2Manager::SCOPE;
		var req = {
			"authUrl" : AUTH_URL,
			"clientId" : CLIENT_ID,
			"scopes" : [ SCOPE ],
		};
		$wnd.oauth2
				.login(
						req,
						function(token) {
							@com.ipoint.cargo4me.client.security.OAuth2Manager::accessToken = token;
							if (null != callback) {
								callback.@com.ipoint.cargo4me.client.security.AuthorizationCallback::onAuthorize()();
							} else {
								$wnd.alert("Got an OAuth token:\n" + token
										+ "\n" + "Token expires in "
										+ $wnd.oauth2.expiresIn(req) + " ms\n");
							}
						},
						function(error) {
							if (null != callback) {
								callback.@com.ipoint.cargo4me.client.security.AuthorizationCallback::onError(Ljava/lang/String;)(error);
							} else {
								$wnd.alert("Error:\n" + error);
							}

						});

	}-*/;

	public static boolean isAuthorized() {
		return (null == accessToken) ? false : true;
	}

	public static String addAccessTokenToURL(String url) {
		return (url.contains("?")) ? url + "&access_token=" + accessToken : url
				+ "?access_token=" + accessToken;
	}

}
