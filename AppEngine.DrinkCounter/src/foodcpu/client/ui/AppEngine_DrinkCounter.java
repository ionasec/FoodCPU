package foodcpu.client.ui;

import foodcpu.client.AppController;
import foodcpu.client.ServiceDrink;
import foodcpu.client.ServiceDrinkAsync;
import foodcpu.client.ServiceLogin;
import foodcpu.client.ServiceLoginAsync;
import foodcpu.client.event.DrinkEvent;
import foodcpu.client.event.LoggedInEvent;
import foodcpu.client.event.LoginEvent;
import foodcpu.client.event.SocialEvent;
import foodcpu.client.event.StatsEvent;
import foodcpu.shared.ConfigStatic;
import foodcpu.shared.DataDrink;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AppEngine_DrinkCounter implements EntryPoint {
		
	/**
	 * This is the entry point method.
	 */
	private VerticalPanel content;
	private MenuItem mntmLogout;
	private MenuItem mntmDrink;
	private MenuItem mntmStats;
	private MenuItem mntmSocial;
	private HandlerManager eventBus;
	private ServiceLoginAsync loginService;
	private ServiceDrinkAsync drinkService;
	
	
	public void onModuleLoad() {
		
		 eventBus = new HandlerManager(null);
		 loginService = GWT.create(ServiceLogin.class);
		 drinkService = GWT.create(ServiceDrink.class);
		 AppController appViewer = new AppController(eventBus,loginService,drinkService);
		 		 
		 
		 RootPanel rootPanel = RootPanel.get();
		 MenuBar mainMenu = new MenuBar(false);
		 rootPanel.add(mainMenu, -2, 0);
		 mainMenu.setWidth("100%"); 
		 
		 mntmDrink = new MenuItem("Drink", false, (Command) null);
		 mainMenu.addItem(mntmDrink);
		 mntmStats = new MenuItem("Stats", false, (Command) null);
		 mainMenu.addItem(mntmStats);
		 mntmSocial = new MenuItem("Social", false, (Command) null);
		 mainMenu.addItem(mntmSocial);
		 mntmLogout = new MenuItem("Logout", false, (Command) null);
		 mainMenu.addItem(mntmLogout);
		 bind();
		 
		content = new VerticalPanel();
		rootPanel.add(content, 0, 57);
		content.setSize("100%", "100px");
				
		 
		 appViewer.go(content);
		 
		 
	}
	
	
	  public void bind() {
		  mntmDrink.setScheduledCommand(new Command() {   
		      public void execute() {
		        eventBus.fireEvent(new DrinkEvent());
		      }
		    });

		  mntmStats.setScheduledCommand(new Command() {   
		      public void execute() {
		        eventBus.fireEvent(new StatsEvent());
		      }
		    });

		    
		  mntmSocial.setScheduledCommand(new Command() {   
		      public void execute() {
		        eventBus.fireEvent(new SocialEvent());
		      }
		    });
		  
		  mntmLogout.setScheduledCommand(new Command() {   
		      public void execute() {
		    	  
		    	  loginService.logout(new AsyncCallback<String>() {
		  		        public void onSuccess(String drink) {
		  		        	eventBus.fireEvent(new LoginEvent());
		  		        }
		  		        public void onFailure(Throwable caught) {
		  		            eventBus.fireEvent(new LoginEvent());
		  		        }
		  		    });
		  		
		  		
		        
		      }
		    });

		 }
	
	  
		
	
	
}
