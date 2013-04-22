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

public class ServiceLoginImpl extends RemoteServiceServlet implements ServiceLogin {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1216143309633321930L;
	

	 
	public String signup(String name,String password) throws IllegalArgumentException
	{
		Objectify ofy = ObjectifyManager.ofy();
        
		Iterator<DataUser> q = ofy.query(DataUser.class).filter("username",name).iterator();
		while (q.hasNext()) {
			return "ERROR sign up (user already exist):"+name;
	    }
		
		
	//	String hash = BCrypt.hashpw(password, BCrypt.genSalt());
		
		DataUser newuser = new DataUser(name,password);
		ofy.put(newuser);
		
		if(newuser.getId() == null)
			return "";
		else
			return newuser.getUsername();
	}
	public String signin(String name,String password) throws IllegalArgumentException
	{
		
		Objectify ofy = ObjectifyManager.ofy();
			
		Iterator<DataUser> q = ofy.query(DataUser.class).filter("username",name).filter("password",password).iterator();
		   
	    while (q.hasNext()) {
		   DataUser d = q.next();
		   
		   HttpSession httpSession = getThreadLocalRequest().getSession(true);  
		   httpSession.setAttribute("username", d.getUsername().toString());
		   httpSession.setAttribute("userid", d.getId());
		   
		   return (d.getUsername().toString());
	    }
		   
	    return "";
		
		
	}
	
	public String getUserList() throws IllegalArgumentException
	{
	   Objectify ofy = ObjectifyManager.ofy();
		
	   Iterator<DataUser> q = ofy.query(DataUser.class).fetch().iterator();
	   
	   String result = "";
	   while (q.hasNext()) {
		   DataUser d = q.next();
		   result += (d.getUsername().toString()+"<br>");
	   }
	   
	   return result; 
		
	}

	public String logout() throws IllegalArgumentException{
		// TODO Auto-generated method stub
		getThreadLocalRequest().getSession().invalidate();
		//Cookies.removeCookie("JSESSIONID");
		return "SUCCESS";
	}

	
}
