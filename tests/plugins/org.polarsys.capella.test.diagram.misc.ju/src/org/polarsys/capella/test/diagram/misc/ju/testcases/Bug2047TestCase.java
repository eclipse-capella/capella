/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;

public class Bug2047TestCase extends BasicTestCase {

  private static final String projectTestName = "bug2047";
  
  private String labDiagramName =  "[LAB] Logical System"; 
  
  ILogListener logListener = new ILogListener() {
    
    @Override
    public void logging(IStatus status, String plugin) {
      if(status !=  null){
        String pluginId = status.getPlugin();
        if(pluginId != null && (pluginId.startsWith("org.eclipse.sirius") || pluginId.startsWith("org.polarsys.capella"))){
          fail("Exception while refreshing diagram: " + status.getMessage());
        }
      }
    }
  };
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(projectTestName);
  }
  
  @Override
  public void test() throws Exception {
    Session session = getSession(projectTestName);
    assertNotNull(session);
    
    SessionContext context = new SessionContext(session);
    DiagramContext diagramContext = new OpenDiagramStep(context, labDiagramName).run();
    // Add the log listener before the refresh
    Platform.addLogListener(logListener);
   
    // Refresh the diagram
    diagramContext.refreshDiagram();
  }
  
  @Override
  protected void tearDown() throws Exception {
    // Remove the log listener on tear down
    Platform.removeLogListener(logListener);
    super.tearDown();    
  }
}
