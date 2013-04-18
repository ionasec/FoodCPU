package foodcpu.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServiceLoginAsync {
	void login(String name,String password,AsyncCallback<String> callback) throws IllegalArgumentException;
	void signin(String name,String password,AsyncCallback<String> callback) throws IllegalArgumentException;
}
