package foodcpu.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class SocialEvent extends GwtEvent<SocialEventHandler> {
  public static Type<SocialEventHandler> TYPE = new Type<SocialEventHandler>();
	  
	  @Override
	  public Type<SocialEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(SocialEventHandler handler) {
	    handler.onSocial(this);
	  }
}
