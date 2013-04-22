package foodcpu.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;


public class DrinkEvent extends GwtEvent<DrinkEventHandler> {
	  public static Type<DrinkEventHandler> TYPE = new Type<DrinkEventHandler>();
	  
	  @Override
	  public Type<DrinkEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(DrinkEventHandler handler) {
	    handler.onDrink(this);
	  }
	}
