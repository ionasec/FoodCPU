package foodcpu.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.History;

import foodcpu.client.event.DrinkEvent;
import foodcpu.client.event.DrinkEventHandler;
import foodcpu.client.event.LoggedInEvent;
import foodcpu.client.event.LoggedInEventHandler;
import foodcpu.client.event.LoginEvent;
import foodcpu.client.event.LoginEventHandler;
import foodcpu.client.event.SocialEvent;
import foodcpu.client.event.SocialEventHandler;
import foodcpu.client.event.StatsEvent;
import foodcpu.client.event.StatsEventHandler;
import foodcpu.client.ui.DrinkManager;
import foodcpu.client.ui.LoginManager;
import foodcpu.client.ui.SocialManager;
import foodcpu.client.ui.StatsManager;

public class AppController implements Presenter, ValueChangeHandler<String> {
	  private final HandlerManager eventBus;
	  private HasWidgets container;
	  private final ServiceLoginAsync loginService;
	  private final ServiceDrinkAsync drinkService;
	  
	  public AppController(HandlerManager eventBus, ServiceLoginAsync loginService, ServiceDrinkAsync drinkService) {
	    this.eventBus = eventBus;
	    this.loginService = loginService;
	    this.drinkService = drinkService;
	    bind();
	  }
	  
	  private void bind() {
	    History.addValueChangeHandler(this);
	    
	    eventBus.addHandler(DrinkEvent.TYPE,
	        new DrinkEventHandler() {
	          public void onDrink(DrinkEvent event) {
	            doOnDrink();
	          }
	        });  
	    
	    eventBus.addHandler(StatsEvent.TYPE,
		        new StatsEventHandler() {
		          public void onStats(StatsEvent event) {
		            doOnStats();
		          }
		        });  
	    
	    eventBus.addHandler(SocialEvent.TYPE,
		        new SocialEventHandler() {
		          public void onSocial(SocialEvent event) {
		            doOnSocial();
		          }
			
		        });  
	    
	    eventBus.addHandler(LoginEvent.TYPE,
		        new LoginEventHandler() {
		          public void onLogin(LoginEvent event) {
		            doOnLogin();
		          }
		        });
	    
	    eventBus.addHandler(LoggedInEvent.TYPE,
		        new LoggedInEventHandler() {
		          public void onLoggedIn(LoggedInEvent event) {
		            doOnLoggedIn();
		          }
		        });
	  }
	  
	  
	  public void go(final HasWidgets container) {
		    this.container = container;
		    
		    if ("".equals(History.getToken())) {
		      History.newItem("login");
		    }
		    else {
		      History.fireCurrentHistoryState();
		    }
	}
	  
	  private void doOnDrink() {
		    History.newItem("drink");
		  }
		  
	  private void doOnStats() {
		    History.newItem("stats");
		  }
		  
	  private void doOnSocial() {
		    History.newItem("social");
		  }
		  
	  private void doOnLoggedIn() {
		    History.newItem("stats");
		  }
	  
	  private void doOnLogin() {
		    History.newItem("login");
		  }


	  
	  
	public void onValueChange(ValueChangeEvent<String> event) {
	    String token = event.getValue();

	    if (token != null) {
	      Presenter presenter = null;

	      if (token.equals("login")) {
	        presenter = new LoginManager(eventBus,loginService);
	      }
	      else if (token.equals("drink")) {
	        presenter = new DrinkManager(eventBus,drinkService);
	      }
	      else if (token.equals("stats")) {
	        presenter = new StatsManager(eventBus,drinkService);
	      }
	      else if (token.equals("social")) {
		        presenter = new SocialManager(eventBus,drinkService);
		      }

	      if (presenter != null) {
	        presenter.go(container);
	      }
	    }
	}
}