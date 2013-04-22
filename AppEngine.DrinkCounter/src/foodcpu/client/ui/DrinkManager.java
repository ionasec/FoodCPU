package foodcpu.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SuggestBox;

import foodcpu.client.Presenter;
import foodcpu.client.ServiceDrinkAsync;
import foodcpu.client.ServiceLogin;
import foodcpu.client.ServiceLoginAsync;
import foodcpu.client.event.LoggedInEvent;
import foodcpu.client.event.LoginEvent;
import foodcpu.shared.ConfigStatic;
import foodcpu.shared.DataDrink;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;


public class DrinkManager extends Composite  implements Presenter{
	
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	
	private Button btnNewButton;
	private SuggestBox suggestBoxDrinkName;
	private SuggestBox suggestBoxDrinkLocation;
	private SuggestBox suggestBoxDrinkQuantity;
	private SuggestBox suggestBoxDrinkPrice;
	 	  
	private final ServiceDrinkAsync drinkService;
	private final HandlerManager eventBus;
	  
	public DrinkManager(HandlerManager eventBus, ServiceDrinkAsync drinkService) {
		this.eventBus = eventBus;
		this.drinkService = drinkService;
			
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		
		Label lblTellMeWhat = new Label("Tell me what you're driniking!");
		verticalPanel.add(lblTellMeWhat);
		
		FlexTable flexTable = new FlexTable();
		verticalPanel.add(flexTable);
		flexTable.setSize("100%", "100%");
		
		Label label = new Label("Name:");
		flexTable.setWidget(0, 0, label);
		
		suggestBoxDrinkName = new SuggestBox();
		flexTable.setWidget(0, 1, suggestBoxDrinkName);
		
		Label lblQuantity = new Label("Quantity:");
		flexTable.setWidget(1, 0, lblQuantity);
		
		suggestBoxDrinkQuantity = new SuggestBox();
		flexTable.setWidget(1, 1, suggestBoxDrinkQuantity);
		
		Label lblLocation = new Label("Location:");
		flexTable.setWidget(2, 0, lblLocation);
		
		suggestBoxDrinkLocation = new SuggestBox();
		flexTable.setWidget(2, 1, suggestBoxDrinkLocation);
		
		Label lblPrice = new Label("Price:");
		flexTable.setWidget(3, 0, lblPrice);
		
		suggestBoxDrinkPrice = new SuggestBox();
		flexTable.setWidget(3, 1, suggestBoxDrinkPrice);
		
		btnNewButton = new Button("Drink");
		flexTable.setWidget(4, 1, btnNewButton);
	}

	public void bind() {
		btnNewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				drinkService();
			}
		});
		 
	 }
 
	@Override
	public void go(HasWidgets container) {
		bind();
	    container.clear();
	    container.add(this.asWidget());
		
	}

	public void drinkService()
	{
		DataDrink drink = new DataDrink();
		drink.setName(this.suggestBoxDrinkName.getText());
		drink.setLocation(this.suggestBoxDrinkLocation.getText());
		drink.setPrice(this.suggestBoxDrinkPrice.getText());
		drink.setQuantity(this.suggestBoxDrinkQuantity.getText());
		
		  
		drinkService.addDrink(drink, new AsyncCallback<DataDrink>() {
		        public void onSuccess(DataDrink drink) {
		          eventBus.fireEvent(new LoggedInEvent());
		        }
		        public void onFailure(Throwable caught) {
		          	  eventBus.fireEvent(new LoginEvent());
		         }
		    });
		
	}

}
