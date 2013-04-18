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

import foodcpu.client.ServiceLogin;
import foodcpu.data.DataUser;
import foodcpu.data.ObjectifyManager;

import com.google.appengine.api.datastore.QueryResultIterator;
import com.google.gwt.dev.js.rhino.ObjToIntMap.Iterator;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class ServiceLoginImpl extends RemoteServiceServlet implements ServiceLogin {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1216143309633321930L;
	
	
	public String login(String name,String password) throws IllegalArgumentException
	{
		Objectify ofy = ObjectifyManager.ofy();
        
		DataUser newuser = new DataUser(name,password);
		ofy.put(newuser);
		
		if(newuser.getId() == null)
			return "ERROR";
		else
			return newuser.getUsername();
	}
	public String signin(String name,String password) throws IllegalArgumentException
	{
		Objectify ofy = ObjectifyManager.ofy();
		
	   QueryResultIterator<DataUser> iterator = ofy.query(DataUser.class).filter("username",name).filter("password",password).iterator();
		 if(!iterator.hasNext())
			 return "ERROR";
		else
			return iterator.next().getUsername();
		
	}

	
}
