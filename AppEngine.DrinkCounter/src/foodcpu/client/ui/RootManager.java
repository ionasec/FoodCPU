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
package foodcpu.client.ui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RootManager implements EntryPoint{
	private VerticalPanel content;
	public void onModuleLoad() {
	
		
		RootPanel rootPanel = RootPanel.get();
		MenuBar mainMenu = new MenuBar(false);
		rootPanel.add(mainMenu, 0, 280);
		mainMenu.setWidth("100%");
		
		/*MenuItem mntmDrink = new MenuItem("Drink", false, new Command() {
			public void execute() {
			}
		});*/
		MenuItem mntmDrink = new MenuItem("Drink", false, (Command) null);
		mntmDrink.setScheduledCommand(new FlowControl(mntmDrink,this));
		mainMenu.addItem(mntmDrink);
		
		MenuItem mntmStats = new MenuItem("Stats", false, (Command) null);
		mntmStats.setScheduledCommand(new FlowControl(mntmStats,this));
		mainMenu.addItem(mntmStats);
		
		MenuItem mntmSocial = new MenuItem("Social", false, (Command) null);
		mntmSocial.setScheduledCommand(new FlowControl(mntmSocial,this));
		mainMenu.addItem(mntmSocial);
		
		
		
		content = new VerticalPanel();
		rootPanel.add(content, 0, 30);
		content.setSize("452px", "100px");
		
		LoginManager c = new LoginManager();
		changeContent(c);
		
	}

	
	public void changeContent(Widget c)
	{
		content.clear();
		content.add(c);
	}
	
}
