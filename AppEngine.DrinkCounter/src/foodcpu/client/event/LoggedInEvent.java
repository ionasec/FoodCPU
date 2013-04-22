package foodcpu.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class LoggedInEvent extends GwtEvent<LoggedInEventHandler> {
  public static Type<LoggedInEventHandler> TYPE = new Type<LoggedInEventHandler>();
	  
	  @Override
	  public Type<LoggedInEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(LoggedInEventHandler handler) {
	    handler.onLoggedIn(this);
	  }
}
