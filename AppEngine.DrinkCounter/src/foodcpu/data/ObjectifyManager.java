package foodcpu.data;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;


public class ObjectifyManager{
	 static {
		  factory().register(DataUser.class);
		  factory().register(DataDrink.class);
		  factory().register(DataLocation.class);
	 }
	  
	 public static Objectify ofy() {
	        return ObjectifyService.begin();
	    }

	  public static ObjectifyFactory factory() {
	        return ObjectifyService.factory();
	    }
}
