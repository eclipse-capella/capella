/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ShowHidePhysicalLinks extends ShowHideComponentExchanges {

  @Override
  protected void testOnLogicalComponents(SessionContext context, String idSource) {
    // not applicable
  }

  @Override
  protected void createDelegationLink(XABDiagram xab, String idSource, String idTarget, String id) {
    createLink(xab, idSource, idTarget, id);
  }

  @Override
  protected void createLink(XABDiagram xab, String idSource, String idTarget, String id) {
    xab.createPhysicalLink(idSource, idTarget, id);
  }

  @Override
  protected void insertLink(XABDiagram xab, String id, String containerId) {
    xab.insertPhysicalLink(id, containerId);
  }
  
  @Override
  protected void removeLink(XABDiagram xab, String id, String containerId) {
    xab.removePhysicalLink(id, containerId);
  }

  @Override
  public void createSubComponent(PABDiagram xab, String id, String containerId) {
    xab.createNodeComponent(id, containerId);
  }

  @Override
  public void createDeployedSubComponent(PABDiagram xab, String id, String containerId) {
    xab.createDeployedNodeComponent(id, containerId);
  }

  @Override
  public void removeSubComponent(PABDiagram xab, String id, String containerId) {
    xab.removeNodeComponent(id, containerId);
  }

  @Override
  public void removeDeployedSubComponent(PABDiagram xab, String id, String containerId) {
    xab.removeDeployedNodeComponent(id, containerId);
  }
  
  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_PAB_HIDE_COMPUTED_PL;
  }
}
