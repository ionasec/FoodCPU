package foodcpu.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;


public class LoginManager extends Composite {
	private TextBox textBoxUsername;
	private TextBox textBoxPassword;
	public LoginManager() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		
		Label lblNewLabel = new Label("Sign in to your account");
		verticalPanel.add(lblNewLabel);
		
		FlexTable flexTable = new FlexTable();
		verticalPanel.add(flexTable);
		flexTable.setSize("100%", "100%");
		
		Label lblUsername = new Label("Username:");
		flexTable.setWidget(0, 0, lblUsername);
		lblUsername.setWidth("138px");
		
		textBoxUsername = new TextBox();
		flexTable.setWidget(0, 1, textBoxUsername);
		
		Label lblPassword = new Label("Password:");
		flexTable.setWidget(1, 0, lblPassword);
		
		textBoxPassword = new TextBox();
		flexTable.setWidget(1, 1, textBoxPassword);
		
		CheckBox chckbxRememberMeOn = new CheckBox("Remember me on this machine");
		flexTable.setWidget(2, 1, chckbxRememberMeOn);
		
		Button btnSignIn = new Button("Sign In");
		btnSignIn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (textBoxUsername.getText().length() == 0
						|| textBoxPassword.getText().length() == 0) {
						Window.alert("Username or password is empty."); 
					}
			}
		});
		flexTable.setWidget(3, 1, btnSignIn);
	}

}
