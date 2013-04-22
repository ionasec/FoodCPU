/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package foodcpu.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import foodcpu.client.ServiceDrink;
import foodcpu.client.ServiceLogin;
import foodcpu.data.ObjectifyManager;
import foodcpu.shared.ConfigStatic;
import foodcpu.shared.DataDrink;
import foodcpu.shared.DataUser;

import com.google.appengine.api.datastore.QueryResultIterator;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class ServiceDrinkImpl extends RemoteServiceServlet implements ServiceDrink {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1216143309633321931L;
	
	 protected void onBeforeRequestDeserialized(String serializedRequest) {
	
		 HttpSession session = getThreadLocalRequest().getSession(false); 
		 if ((session != null)&&(session.getAttribute("username") != null))
			 return;
		 
		       // Write error to the client, just copy paste
	      this.getThreadLocalResponse().reset();
	      ServletContext servletContext = this.getServletContext();
	      HttpServletResponse response = this.getThreadLocalResponse();
	      try {
	        response.setContentType("text/plain");
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        try {
	          response.getOutputStream().write(ConfigStatic.NOT_LOGGED_IN.getBytes("UTF-8"));
	          response.flushBuffer();
	        } catch (IllegalStateException e) {
	          // Handle the (unexpected) case where getWriter() was previously used
	          response.getWriter().write(ConfigStatic.NOT_LOGGED_IN);
	          response.flushBuffer();
	        }
	      } catch (IOException ex) {
	        servletContext.log(
	          "respondWithUnexpectedFailure failed while sending the previous failure to the client",
	          ex);
	      }
	      //Throw Exception to stop the execution of the Servlet
	      throw new NullPointerException();
	    }
	  
	 
	public DataDrink addDrink(DataDrink drink) throws IllegalArgumentException {
		Objectify ofy = ObjectifyManager.ofy();
		
		String username = "";
		HttpSession session = getThreadLocalRequest().getSession(true); 
		if(session.getAttribute("username") != null)
		{
			username = (String) session.getAttribute("username");
		}
		
		drink.setUsername(username);
		drink.setCurrentDate();
		ofy.put(drink);
			
		// TODO Auto-generated method stub
		return drink;
	}
	@Override
	public ArrayList<DataDrink> getDrinkList() throws IllegalArgumentException {
		
		HttpSession session = getThreadLocalRequest().getSession(false); 
		 if ((session == null)||(session.getAttribute("username") == null))
			 return null;
		
		  String username = (String)session.getAttribute("username");
		
		  ArrayList<DataDrink> drinkList = new ArrayList<DataDrink>();
		  Objectify ofy = ObjectifyManager.ofy();
		  
		  Iterator<DataDrink> q = ofy.query(DataDrink.class).filter("username",username).iterator();
		   	   
		    while(q.hasNext()) { 
		    	DataDrink drink = q.next();          
		    	drinkList.add(drink);
		    }
		    
		    return drinkList;
	}
	
	public ArrayList<DataDrink> getAllDrinkList() throws IllegalArgumentException {
		  ArrayList<DataDrink> drinkList = new ArrayList<DataDrink>();
		    
		  Objectify ofy = ObjectifyManager.ofy();
		  
		  Iterator<DataDrink> q = ofy.query(DataDrink.class).fetch().iterator();
		   	   
		    while(q.hasNext()) { 
		    	DataDrink drink = q.next();          
		    	drinkList.add(drink);
		    }
		    
		    return drinkList;
	}

	
	
}
