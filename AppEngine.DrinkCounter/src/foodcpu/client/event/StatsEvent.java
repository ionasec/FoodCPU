package foodcpu.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class StatsEvent extends GwtEvent<StatsEventHandler> {
  public static Type<StatsEventHandler> TYPE = new Type<StatsEventHandler>();
	  
	  @Override
	  public Type<StatsEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(StatsEventHandler handler) {
	    handler.onStats(this);
	  }
}
