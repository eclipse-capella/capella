/*******************************************************************************
 * Copyright (c) 2016, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.xab;

import java.text.MessageFormat;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.junit.Assert;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.HelperMessages;
import org.polarsys.capella.test.framework.model.GenericModel;

public class ShowHideComponentExchanges extends EmptyProject {

  protected void createLink(XABDiagram xab, String idSource, String idTarget, String id) {
    xab.createComponentExchange(idSource, idTarget, id);
  }

  protected void insertLink(XABDiagram xab, String id, String containerId) {
    xab.insertComponentExchange(id, containerId);
  }

  public void createSubComponent(PABDiagram xab, String id, String containerId) {
    xab.createBehaviorComponent(id, containerId);
  }

  public void createDeployedSubComponent(PABDiagram xab, String id, String containerId) {
    xab.createDeployedBehaviorComponent(id, containerId);
  }

  public void removeSubComponent(PABDiagram xab, String id, String containerId) {
    xab.removeBehaviorComponent(id, containerId);
  }

  public void removeDeployedSubComponent(PABDiagram xab, String id, String containerId) {
    xab.removeDeployedBehaviorComponent(id, containerId);
  }

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(context, PA__PHYSICAL_SYSTEM);
    testOnDeployedPhysicalComponents(context, PA__PHYSICAL_SYSTEM);
    testOnUnDeployedPhysicalComponents(context, PA__PHYSICAL_SYSTEM);

    testOnPhysicalComponents(context, PA__PHYSICAL_SYSTEM);
    testOnActors(context, SA__SYSTEM);
    testOnActors(context, LA__LOGICAL_SYSTEM);
    testOnActors(context, PA__PHYSICAL_SYSTEM);
    testOnLogicalComponents(context, LA__LOGICAL_SYSTEM);

  }

  /**
   * @param context
   * @param paPhysicalSystem
   */
  private void testOnXAB(SessionContext context, String idSource) {
    PABDiagram xab = PABDiagram.createDiagram(context, idSource);
    createSubComponent(xab, GenericModel.LC_1, xab.getDiagramId());
    createSubComponent(xab, GenericModel.LC_2, GenericModel.LC_1);
    createSubComponent(xab, GenericModel.LC_3, GenericModel.LC_2);
    createSubComponent(xab, GenericModel.LC_4, GenericModel.LC_1);

    createLink(xab, GenericModel.LC_2, GenericModel.LC_4, GenericModel.CL_1);
    createDelegationLink(xab, GenericModel.LC_1, GenericModel.LC_2, GenericModel.CL_2);
    createDelegationLink(xab, GenericModel.LC_2, GenericModel.LC_3, GenericModel.CL_3);

    removeSubComponent(xab, GenericModel.LC_1, xab.getDiagramId());
    xab.refreshDiagram();
    xab.hasntView(GenericModel.LC_1);
    xab.hasntView(GenericModel.LC_2);
    xab.hasntView(GenericModel.LC_3);
    xab.hasntView(GenericModel.LC_4);

    xab.setContextualElements(GenericModel.LC_2);
    xab.refreshDiagram();

    xab.hasView(GenericModel.LC_2);
    xab.hasView(GenericModel.LC_4);

    xab.hasntView(GenericModel.LC_1);
    xab.hasntView(GenericModel.LC_3);

  }

  protected void createDelegationLink(XABDiagram xab, String idSource, String idTarget, String id) {
    xab.createComponentExchangeDelegation(idSource, idTarget, id);
  }

  protected void testOnActors(SessionContext context, String idSource) {
    XABDiagram xab = XABDiagram.createDiagram(context, idSource);
    xab.createActor(GenericModel.LA_1);
    xab.createActor(GenericModel.LA_2);

    createLink(xab, GenericModel.LA_1, GenericModel.LA_2, GenericModel.CL_1);
    xab.hasView(GenericModel.LA_1);
    xab.hasView(GenericModel.LA_2);
    xab.removeActor(GenericModel.LA_2);
    insertLink(xab, GenericModel.CL_1, GenericModel.LA_1);
  }

  protected void testOnLogicalComponents(SessionContext context, String idSource) {
    XABDiagram xab = XABDiagram.createDiagram(context, idSource);
    
    activateFilter(xab.getDiagram(), getFilterName());
    
    xab.createComponent(GenericModel.LC_1, LA__LOGICAL_CONTEXT__PART_LOGICAL_SYSTEM__LOGICAL_SYSTEM);
    xab.createComponent(GenericModel.LC_2, LA__LOGICAL_CONTEXT__PART_LOGICAL_SYSTEM__LOGICAL_SYSTEM);
    xab.createComponent(GenericModel.LC_2_1, GenericModel.LC_2);

    createLink(xab, GenericModel.LC_1, GenericModel.LC_2_1, GenericModel.CL_1);
    xab.removeComponent(GenericModel.LC_2_1);
    insertLink(xab, GenericModel.CL_1, GenericModel.LC_1);
    xab.hasView(GenericModel.LC_2_1);
    xab.mustGraphicalOwnedBy(GenericModel.LC_2_1, GenericModel.LC_2);

    xab.removeComponent(GenericModel.LC_1);
    insertLink(xab, GenericModel.CL_1, GenericModel.LC_2_1);
    xab.hasView(GenericModel.LC_1);
  }

  protected void testOnPhysicalComponents(SessionContext context, String idSource) {
    PABDiagram xab = PABDiagram.createDiagram(context, idSource);
    
    activateFilter(xab.getDiagram(), getFilterName());

    // Create a component exchange between two behavior not deployed
    createSubComponent(xab, GenericModel.LC_1, xab.getDiagramId());
    createSubComponent(xab, GenericModel.LC_2, xab.getDiagramId());
    createSubComponent(xab, GenericModel.LC_2_1, GenericModel.LC_2);
    createLink(xab, GenericModel.LC_1, GenericModel.LC_2_1, GenericModel.CL_1);

    // Remove target, insert component exchange should display the target inside its container
    removeSubComponent(xab, GenericModel.LC_2_1, GenericModel.LC_2);
    insertLink(xab, GenericModel.CL_1, GenericModel.LC_1);
    xab.hasView(GenericModel.LC_2_1);
    xab.mustGraphicalOwnedBy(GenericModel.LC_2_1, GenericModel.LC_2);

    // Remove source, insert component exchange should display source in diagram
    removeSubComponent(xab, GenericModel.LC_1, xab.getDiagramId());
    insertLink(xab, GenericModel.CL_1, GenericModel.LC_2_1);
    xab.hasView(GenericModel.LC_1);

  }

  protected void testOnDeployedPhysicalComponents(SessionContext context, String idSource) {
    PABDiagram xab = PABDiagram.createDiagram(context, idSource);

    activateFilter(xab.getDiagram(), getFilterName());
    
    // Create a component exchange between two deployments
    xab.createNodeComponent(GenericModel.LC_1, xab.getDiagramId());
    createDeployedSubComponent(xab, GenericModel.LC_2, GenericModel.LC_1);
    xab.createNodeComponent(GenericModel.LC_3, xab.getDiagramId());
    createDeployedSubComponent(xab, GenericModel.LC_4, GenericModel.LC_3);
    createLink(xab, GenericModel.LC_2, GenericModel.LC_4, GenericModel.CL_1);

    // remove node, node and deployment should be removed
    xab.removeNodeComponent(GenericModel.LC_3, xab.getDiagramId());
    xab.hasntView(GenericModel.LC_3);
    xab.hasntView(GenericModel.LC_4);

    // show component exchange, target should be displayed as a behavior not deployed
    insertLink(xab, GenericModel.CL_1, GenericModel.LC_2);
    xab.hasView(GenericModel.LC_4, IMappingNameConstants.PAB_PHYSICAL_COMPONENT_MAPPING_NAME);
    xab.hasntView(GenericModel.LC_3);

    // show node component, show component exchange, target should be displayed as a behavior deployed
    removeSubComponent(xab, GenericModel.LC_4, xab.getDiagramId());
    xab.insertNodeComponent(GenericModel.LC_3, xab.getDiagramId());
    
    assertFilterActive(xab.getDiagram(), getFilterName());
    
    insertLink(xab, GenericModel.CL_1, GenericModel.LC_2);
    xab.hasView(GenericModel.LC_4, IMappingNameConstants.PAB_PHYSICAL_COMPONENT_DEPLOYMENT_MAPPING_NAME);

  }

  protected void testOnUnDeployedPhysicalComponents(SessionContext context, String idSource) {
    PABDiagram xab = PABDiagram.createDiagram(context, idSource);

    // Activate the filter to hide the computed CE
    activateFilter(xab.getDiagram(), getFilterName());
    
    // Create a component exchange between a behavior and a deployed behavior.
    // if there is no other deployed displayed, target is displayed as undeployed
    createSubComponent(xab, GenericModel.LC_1, xab.getDiagramId());
    xab.createNodeComponent(GenericModel.LC_2, xab.getDiagramId());
    createDeployedSubComponent(xab, GenericModel.LC_3, GenericModel.LC_2);
    createLink(xab, GenericModel.LC_1, GenericModel.LC_3, GenericModel.CL_1);
    removeDeployedSubComponent(xab, GenericModel.LC_3, GenericModel.LC_2);
    
    
    insertLink(xab, GenericModel.CL_1, GenericModel.LC_1);
    xab.hasView(GenericModel.LC_3, IMappingNameConstants.PAB_PHYSICAL_COMPONENT_MAPPING_NAME);
  }
  
  /**
   * Activate the given filter on the given diagram.
   * @param diagram
   * @param filterName
   */
  protected void activateFilter(DDiagram diagram, String filterName) {
    FilterDescription filter = DiagramHelper.getFilterForDiagram(diagram, filterName);
    Assert.assertNotNull(MessageFormat.format(HelperMessages.filterNotFound, filterName, diagram.getName()), filter);
    DiagramHelper.addFilterInDiagram(diagram, filter);
  }
  
  protected String getFilterName(){
    return IFilterNameConstants.FILTER_PAB_HIDE_COMPUTED_CE;
  }
  
  /**
   * Checks that the given filter is activated on the given diagram.
   * @param diagram
   * @param filterName
   */
  protected void assertFilterActive(DDiagram diagram, String filterName){
    FilterDescription filter = DiagramHelper.getFilterForDiagram(diagram, filterName);
    Assert.assertNotNull(MessageFormat.format(HelperMessages.filterNotFound, filterName, diagram.getName()), filter);
    EList<FilterDescription> activatedFilters = diagram.getActivatedFilters();
    assertTrue(activatedFilters.contains(filter));
  }
}
