/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.merge.ui.viewer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Shell;

import org.polarsys.capella.core.data.interaction.Scenario;

/**
 *
 */
@SuppressWarnings("nls")
public class ScenarioViewer {

  private Shell _mainShell = null;
  
  private ScenarioExplorer _scenarioExplorer = null;
  
  /**
   * 
   */
  public ScenarioViewer() {
    // Nothing to be done here
  }

  /**
   * 
   */
  public ScenarioViewer(Scenario scenario) {
    init();
    setScenario(scenario);
  }
  
  private void create_scenarioExplorer() {
    _scenarioExplorer = new ScenarioExplorer(_mainShell, SWT.CENTER);
    _scenarioExplorer.setVisible(true);    
    _scenarioExplorer.setLayout(new FillLayout());
  }
  
  /**
   * 
   *
   */
  public void init() {
    create_mainShell();
  }
  
  /**
   * This method initializes _mainShell
   */
  private void create_mainShell() {
    _mainShell = new Shell();
    _mainShell.setText("Scenario viewer");
    _mainShell.setVisible(false);
    _mainShell.setLayout(new FillLayout());
    _mainShell.setSize(new Point(716, 312));
    create_scenarioExplorer();
  }
  
  public void setVisible(boolean visible) {
    _mainShell.setVisible(true);
  }
  
  public void setScenario(Scenario scenario) {
    _scenarioExplorer.setScenario(scenario);    
  }
  

}
