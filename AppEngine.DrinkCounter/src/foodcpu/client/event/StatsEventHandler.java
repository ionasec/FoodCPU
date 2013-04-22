package foodcpu.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface StatsEventHandler extends EventHandler  {
	void onStats(StatsEvent event);
}
