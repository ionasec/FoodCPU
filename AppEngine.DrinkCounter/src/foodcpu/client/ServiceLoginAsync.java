package foodcpu.client;



import com.google.gwt.user.client.rpc.AsyncCallback;


public interface ServiceLoginAsync {
	void signin(String name,String password,AsyncCallback<String> callback) throws IllegalArgumentException;
	void signup(String name,String password,AsyncCallback<String> callback) throws IllegalArgumentException;
	void getUserList(AsyncCallback<String> asyncCallback) throws IllegalArgumentException;
	void logout(AsyncCallback<String> asyncCallback) throws IllegalArgumentException;
}
