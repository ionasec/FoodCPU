package foodcpu.client.ui;

import java.sql.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;

import foodcpu.client.Presenter;
import foodcpu.client.ServiceLogin;
import foodcpu.client.ServiceLoginAsync;
import foodcpu.client.event.DrinkEvent;
import foodcpu.client.event.LoggedInEvent;

import com.google.gwt.user.client.ui.PasswordTextBox;


public class LoginManager extends Composite implements Presenter {
	private TextBox textBoxUsername;
	private PasswordTextBox textBoxPassword;
	private final DialogBox dialogBox;
	private final HTML serverResponseLabel;
	private final Button closeButton;
	Button btnSignIn;
	Button btnSignUp;
	
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";
	
	private final ServiceLoginAsync loginService;
	private final HandlerManager eventBus;
	private final Label lblNewLabel;
	
	public LoginManager(HandlerManager eventBus, ServiceLoginAsync loginService) {
		this.eventBus = eventBus;
		this.loginService = loginService;

		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		
		lblNewLabel = new Label("Sign in to your account");
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
		
		textBoxPassword = new PasswordTextBox();
		flexTable.setWidget(1, 1, textBoxPassword);
		
		CheckBox chckbxRememberMeOn = new CheckBox("Remember me on this machine");
		flexTable.setWidget(2, 1, chckbxRememberMeOn);
		
		
		dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		serverResponseLabel = new HTML();
		closeButton = new Button("Close");
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		//dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		btnSignIn = new Button("Sign In");
		btnSignUp = new Button("Sign Up");
		
		flexTable.setWidget(3, 0, btnSignUp);
		flexTable.setWidget(3, 1, btnSignIn);
	}
	
	private void bind() {
			// Add a handler to close the DialogBox
			closeButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					dialogBox.hide();
				
				}
			});
		 
			btnSignIn.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (textBoxUsername.getText().length() == 0
							|| textBoxPassword.getText().length() == 0) {
							Window.alert("Username or password is empty.");
												
					}
					else{
						signinService();
					}
				}
			});
			
			btnSignUp.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (textBoxUsername.getText().length() == 0
							|| textBoxPassword.getText().length() == 0) {
							Window.alert("Username or password is empty.");
												
					}
					else{
						signupService();
					}
				}
			});
	 }
	public void signinService()
	{
		loginService.signin(textBoxUsername.getText(),textBoxPassword.getText(),
				new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						lblNewLabel.setText("login Service (signin) error!");
						
					}

					public void onSuccess(String result) {
						if(!result.equalsIgnoreCase("")){
							lblNewLabel.setText("login successful!");
							eventBus.fireEvent(new LoggedInEvent());	
						}
						else
						{
							lblNewLabel.setText("user/password unkown - please try again!");
						}
						
						
				/*		 String sessionID = ""; (Get sessionID from server's response to your login request.);
						 final long DURATION = 1000 * 60 * 60 * 24 * 14; //duration remembering login. 2 weeks in this example.
						 Date expires = new Date(System.currentTimeMillis() + DURATION);
						 Cookies.setCookie("sid", sessionID, expires, null, "/", false);*/
						    
					}
				});	
	}
	
	public void signupService()
	{
		loginService.signup(textBoxUsername.getText(),textBoxPassword.getText(),
				new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
					
						lblNewLabel.setText("login Service (signup) error!");
					}

					public void onSuccess(String result) {
						
						if(!result.equalsIgnoreCase(""))
						{
							lblNewLabel.setText("signup successful!");
							signinService();
						}
						else
						{
							lblNewLabel.setText("signup "+result+" error - please try again!");
						}
						
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
