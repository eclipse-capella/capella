/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.EABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

import java.util.Random;

public class CreateEABConfigurationItems extends XABDiagramsProject {
  public static final String CONFIGURATION_ITEM = "CONFIGURATION_ITEM";
  
  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    testOnXAB(context, EPBS__EAB_DIAGRAM);
  }

  public void testOnXAB(SessionContext context, String diagramName) {
    EABDiagram diagram = EABDiagram.openDiagram(context, diagramName);
    
    int ciIndex = 0;
    diagram.createConfigurationItem(CONFIGURATION_ITEM + (++ciIndex), diagram.getDiagramId(), BlockArchitectureExt.ConfigurationItemType.COTS);
    diagram.createConfigurationItem(CONFIGURATION_ITEM + (++ciIndex), diagram.getDiagramId(), BlockArchitectureExt.ConfigurationItemType.CS);
    diagram.createConfigurationItem(CONFIGURATION_ITEM + (++ciIndex), diagram.getDiagramId(), BlockArchitectureExt.ConfigurationItemType.HW);
    diagram.createConfigurationItem(CONFIGURATION_ITEM + (++ciIndex), diagram.getDiagramId(), BlockArchitectureExt.ConfigurationItemType.INTERFACE);
    diagram.createConfigurationItem(CONFIGURATION_ITEM + (++ciIndex), diagram.getDiagramId(), BlockArchitectureExt.ConfigurationItemType.NDI);
    diagram.createConfigurationItem(CONFIGURATION_ITEM + (++ciIndex), diagram.getDiagramId(), BlockArchitectureExt.ConfigurationItemType.PRIME_ITEM);
    diagram.createConfigurationItem(CONFIGURATION_ITEM + (++ciIndex), diagram.getDiagramId(), BlockArchitectureExt.ConfigurationItemType.SYSTEM);
    
    Random rand = new Random();
    diagram.createConfigurationItem(CONFIGURATION_ITEM + (++ciIndex), CONFIGURATION_ITEM + (rand.nextInt(ciIndex-1) + 1), BlockArchitectureExt.ConfigurationItemType.COTS);
    diagram.createConfigurationItem(CONFIGURATION_ITEM + (++ciIndex), CONFIGURATION_ITEM + (rand.nextInt(ciIndex-1) + 1), BlockArchitectureExt.ConfigurationItemType.CS);
    diagram.createConfigurationItem(CONFIGURATION_ITEM + (++ciIndex), CONFIGURATION_ITEM + (rand.nextInt(ciIndex-1) + 1), BlockArchitectureExt.ConfigurationItemType.HW);
    diagram.createConfigurationItem(CONFIGURATION_ITEM + (++ciIndex), CONFIGURATION_ITEM + (rand.nextInt(ciIndex-1) + 1), BlockArchitectureExt.ConfigurationItemType.INTERFACE);
    diagram.createConfigurationItem(CONFIGURATION_ITEM + (++ciIndex), CONFIGURATION_ITEM + (rand.nextInt(ciIndex-1) + 1), BlockArchitectureExt.ConfigurationItemType.NDI);
    diagram.createConfigurationItem(CONFIGURATION_ITEM + (++ciIndex), CONFIGURATION_ITEM + (rand.nextInt(ciIndex-1) + 1), BlockArchitectureExt.ConfigurationItemType.PRIME_ITEM);
    diagram.createConfigurationItem(CONFIGURATION_ITEM + (++ciIndex), CONFIGURATION_ITEM + (rand.nextInt(ciIndex-1) + 1), BlockArchitectureExt.ConfigurationItemType.SYSTEM);
  }
  
  
}
