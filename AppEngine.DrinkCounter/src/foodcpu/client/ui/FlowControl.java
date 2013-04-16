package foodcpu.client.ui;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class FlowControl implements Command {

	
	//private static FlowControl instance;
	private final MenuItem item;
	private final RootManager root;
	
	public FlowControl(MenuItem item, RootManager root) {
		    this.item = item;
		    this.root = root;
   }
	  
	public void execute() {
		if(item.getText().compareTo("Drink")==0)
		{
			root.changeContent(new DrinkManager());
		} else if(item.getText().compareTo("Stats")==0) {
			root.changeContent(new StatsManager());
		} else {
			root.changeContent(new LoginManager());
		}
			
			
			
		   /* if (token == null) go(new LoginManager());
		    if (token.equals("Drink")) go(new DrinkManager());
		    if (token.equals("Stats")) go(new StatsManager());*/
		    // Can probably make these constants in this class
	}
		
	
	/*public static void (Composite c) {
	    if (instance == null) instance = new FlowControl(); // not sure why we need this yet since everything is static.
	
	    
	    RootPanel.get("application").clear();
	    RootPanel.get("application").getElement().getStyle().setPosition(Position.RELATIVE); // not sure why, but GWT throws an exception without this. Adding to CSS doesn't work.
	    // add, determine height/width, center, then move. height/width are unknown until added to document. Catch-22!
	    RootPanel.get("application").add(c);
	    int left = Window.getClientWidth() / 2 - c.getOffsetWidth() / 2; // find center
	    int top = Window.getClientHeight() / 2 - c.getOffsetHeight() / 2;
	    RootPanel.get("application").setWidgetPosition(c, left, top);
	    History.newItem(c.getTitle()); // TODO: need to change and implement (or override) this method on each screen
	RootManager.
	    }*/	


	
}
