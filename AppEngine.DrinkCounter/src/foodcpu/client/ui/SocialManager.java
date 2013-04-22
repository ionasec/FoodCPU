package foodcpu.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlexTable;

import foodcpu.client.Presenter;
import foodcpu.client.ServiceDrinkAsync;
import foodcpu.client.ServiceLogin;
import foodcpu.client.ServiceLoginAsync;
import foodcpu.client.event.LoginEvent;
import foodcpu.shared.ConfigStatic;
import foodcpu.shared.DataDrink;

public class SocialManager extends Composite implements Presenter {
	private FlexTable flexTable;
	
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";
	
	private final ServiceDrinkAsync drinkService;
	private final HandlerManager eventBus;
	private final Label lblThisIsWhat;
	
	public SocialManager(HandlerManager eventBus, ServiceDrinkAsync drinkService) {
		this.eventBus = eventBus;
		this.drinkService = drinkService;

			
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		
		lblThisIsWhat = new Label("This is what you had so far...");
		verticalPanel.add(lblThisIsWhat);
		
		flexTable = new FlexTable();
		verticalPanel.add(flexTable);
		flexTable.setSize("100%", "100%");
		
		fillTable();
	}
	
	private void bind() {
		 
		 
	 }
	public void fillTable()
	{
		drinkService.getAllDrinkList(new AsyncCallback<ArrayList<DataDrink>>() {
			public void onSuccess(ArrayList<DataDrink> result) {
		
			
				lblThisIsWhat.setText("This is what you had so far...");
				
				ArrayList<DataDrink> drinklist = result;
         
				flexTable.removeAllRows();
				
				flexTable.setText(0,0,"username");
				flexTable.setText(0,1,"date");
	        	flexTable.setText(0,2,"drink");
	        	flexTable.setText(0,3,"price");
	        		        	
	        	
		        for (int i = 0; i < result.size(); ++i) {
		        	flexTable.setText(i+1,0,drinklist.get(i).getUsername());
		        	flexTable.setText(i+1,1,drinklist.get(i).getDate().toString());
		        	flexTable.setText(i+1,2,drinklist.get(i).getName());
		        	flexTable.setText(i+1,3,drinklist.get(i).getPrice());
		           
		        }

		          
		 }
      
	      public void onFailure(Throwable caught) {
	    	lblThisIsWhat.setText("Error fetching drink list");
	    	eventBus.fireEvent(new LoginEvent());
	          }
	      });
	}

	@Override
	public void go(HasWidgets container) {
		bind();
	    container.clear();
	    container.add(this.asWidget());
	}

}
