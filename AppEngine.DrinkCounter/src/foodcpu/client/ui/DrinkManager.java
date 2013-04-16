package foodcpu.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SuggestBox;

public class DrinkManager extends Composite {
	public DrinkManager() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		
		Label lblTellMeWhat = new Label("Tell me what you're driniking!");
		verticalPanel.add(lblTellMeWhat);
		
		FlexTable flexTable = new FlexTable();
		verticalPanel.add(flexTable);
		flexTable.setSize("100%", "100%");
		
		Label label = new Label("Name:");
		flexTable.setWidget(0, 0, label);
		
		SuggestBox suggestBoxDrinkName = new SuggestBox();
		flexTable.setWidget(0, 1, suggestBoxDrinkName);
		
		Label lblQuantity = new Label("Quantity:");
		flexTable.setWidget(1, 0, lblQuantity);
		
		SuggestBox suggestBoxDrinkLocation = new SuggestBox();
		flexTable.setWidget(1, 1, suggestBoxDrinkLocation);
		
		Label lblLocation = new Label("Location:");
		flexTable.setWidget(2, 0, lblLocation);
		
		SuggestBox suggestBoxLocation = new SuggestBox();
		flexTable.setWidget(2, 1, suggestBoxLocation);
		
		Label lblPrice = new Label("Price:");
		flexTable.setWidget(3, 0, lblPrice);
		
		SuggestBox suggestBoxDrinkPrice = new SuggestBox();
		flexTable.setWidget(3, 1, suggestBoxDrinkPrice);
	}

}
