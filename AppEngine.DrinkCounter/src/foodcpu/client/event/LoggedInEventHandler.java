package foodcpu.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface LoggedInEventHandler extends EventHandler  {
	void onLoggedIn(LoggedInEvent event);
}
