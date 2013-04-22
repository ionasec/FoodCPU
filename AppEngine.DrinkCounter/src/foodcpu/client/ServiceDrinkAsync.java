package foodcpu.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import foodcpu.shared.DataDrink;

public interface ServiceDrinkAsync {
	void getDrinkList(AsyncCallback<ArrayList<DataDrink>> callback) throws IllegalArgumentException;
	void getAllDrinkList(AsyncCallback<ArrayList<DataDrink>> callback) throws IllegalArgumentException;
	void addDrink(DataDrink contact, AsyncCallback<DataDrink> callback) throws IllegalArgumentException;
}
