/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.merge.ui.viewer;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.TimeLapse;

/**
 */
@SuppressWarnings("nls")
public class ScenarioExplorer extends Composite {
  
  private MessageExplorer _messageExplorer = null;
  
  private MessageEndExplorer _abstractEndExplorer = null;

  private ExecutionExplorer _executionExplorer = null;
  
  private InstanceRoleExplorer _instanceRoleExplorer = null;
  
  private TabFolder _tabFolder = null;

  private Composite _integrity = null;

  private Text textArea = null;
  
  public ScenarioExplorer(Composite parent, int style) {
    super(parent, style);
    initialize();
  }

  private void initialize() {
    this.setLayout(new FillLayout());
    create_tabFolder();
    this.setSize(new Point(570, 286));
  }
  
  
  private void create_messageExplorer() {
    _messageExplorer = new MessageExplorer(_tabFolder, SWT.CENTER);
    _messageExplorer.setVisible(true);    
    _messageExplorer.setLayout(new FillLayout());
  }
  
  private void create_abstractEndExplorer() {
    _abstractEndExplorer = new MessageEndExplorer(_tabFolder, SWT.CENTER);
    _abstractEndExplorer.setVisible(true);    
    _abstractEndExplorer.setLayout(new FillLayout());
  }  

  private void create_executionExplorer() {
    _executionExplorer = new ExecutionExplorer(_tabFolder, SWT.CENTER);
    _executionExplorer.setVisible(true);    
    _executionExplorer.setLayout(new FillLayout());
  }
  
  private void create_instanceRoleExplorer() {
    _instanceRoleExplorer = new InstanceRoleExplorer(_tabFolder, SWT.CENTER);
    _instanceRoleExplorer.setVisible(true);    
    _instanceRoleExplorer.setLayout(new FillLayout());
  }  
  
  public void setScenario(Scenario scenario_p) {
    List<SequenceMessage> messages = scenario_p.getOwnedMessages();
    for (SequenceMessage message : messages) {
      addMessage(message);
    }
    
    List<InteractionFragment> messageEnds = scenario_p.getOwnedInteractionFragments();
    for (InteractionFragment messageEnd : messageEnds) {
      if (messageEnd instanceof AbstractEnd) {
        addAbstractEnd((AbstractEnd) messageEnd);
      }
    }    
    
    List<TimeLapse> executions = scenario_p.getOwnedTimeLapses();
    for (TimeLapse execution : executions) {
      if (execution instanceof Execution) {
        addExecution((Execution) execution);
      }
    }     
    
    List<InstanceRole> instanceRoles = scenario_p.getOwnedInstanceRoles();
    for (InstanceRole instanceRole : instanceRoles) {
      addInstanceRole(instanceRole);
    }   

    textArea.setText(checkIntegrity(scenario_p));
  }
  
  private String checkIntegrity(Scenario scenario_p) {
//FIXME merge.ui must be refactored with new merge algorithm 
//    List<String> errorList = Checker.checkScenarioWithErrors(scenario_p);
//    
    StringBuilder buffer = new StringBuilder();
//    
//    for (String error : errorList) {
//      buffer.append(error);
//      buffer.append(System.getProperty("line.separator"));
//    }
//    
    return buffer.toString();
  } 
  
  /**
   * @param instanceRole_p
   */
  private void addInstanceRole(InstanceRole instanceRole_p) {
    _instanceRoleExplorer.addInstanceRole(instanceRole_p);
    
  }

  /**
   * @param execution_p
   */
  private void addExecution(Execution execution_p) {
    _executionExplorer.addExecution(execution_p); 
  }

  public void addMessage(SequenceMessage value) {
    _messageExplorer.addMessage(value);
  }

  public void addAbstractEnd(AbstractEnd value) {
    _abstractEndExplorer.addAbstractEnd(value);
  }
  
  /**
   * This method initializes _tabFolder 
   *
   */
  private void create_tabFolder() {
    
    _tabFolder = new TabFolder(this, SWT.NONE);
    TabItem tabItem1 = new TabItem(_tabFolder, SWT.NONE);
    create_integrity();    
    tabItem1.setText("Scenario Integrity");
    tabItem1.setControl(_integrity);
    create_messageExplorer();
    TabItem tabItem = new TabItem(_tabFolder, SWT.NONE);
    tabItem.setText("Messages");
    tabItem.setControl(_messageExplorer);
    create_abstractEndExplorer();
    TabItem tabItem2 = new TabItem(_tabFolder, SWT.NONE);
    tabItem2.setText("Abstract Ends");
    tabItem2.setControl(_abstractEndExplorer);    
    create_executionExplorer();    
    TabItem tabItem3 = new TabItem(_tabFolder, SWT.NONE);
    tabItem3.setText("Executions");
    tabItem3.setControl(_executionExplorer);     
    create_instanceRoleExplorer();    
    TabItem tabItem4 = new TabItem(_tabFolder, SWT.NONE);
    tabItem4.setText("Instance Roles");
    tabItem4.setControl(_instanceRoleExplorer);        

  }

/**
 * This method initializes _integrity	
 *
 */
private void create_integrity() {
	GridData gridData = new GridData();
	gridData.horizontalAlignment = GridData.FILL;
	gridData.grabExcessHorizontalSpace = true;
	gridData.grabExcessVerticalSpace = true;
	gridData.verticalAlignment = GridData.FILL;
	_integrity = new Composite(_tabFolder, SWT.NONE);
	_integrity.setLayout(new GridLayout());
	textArea = new Text(_integrity, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
	textArea.setLayoutData(gridData);
}

  
}

