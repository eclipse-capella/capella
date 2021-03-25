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

import java.text.MessageFormat;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.junit.Assert;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.diagram.helpers.DAnnotationHelper;
import org.polarsys.capella.core.diagram.helpers.IRepresentationAnnotationConstants;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.HelperMessages;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.framework.model.GenericModel;

public class ShowHideComponentExchanges extends EmptyProject {

  protected void createLink(XABDiagram xab, String idSource, String idTarget, String id) {
    xab.createComponentExchange(idSource, idTarget, id);
  }
  
  protected void insertLink(XABDiagram xab, String id, String containerId) {
    xab.insertComponentExchange(id, containerId);
  }
  
  protected void removeLink(XABDiagram xab, String id, String containerId) {
    xab.removeComponentExchange(id, containerId);
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

    testCE(context);
  }
  
  protected void testCE(SessionContext context) {
    testCEOnDeployedPhysicalComponents(context, PA__PHYSICAL_SYSTEM);
  }

  /**
   * @param context
   * @param paPhysicalSystem
   */
  private void testOnXAB(SessionContext context, String idSource) {
    PABDiagram xab = PABDiagram.createDiagram(context, idSource);
    
    activateComponentExchangeFilters(xab);
    
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
    activateComponentExchangeFilters(xab);
    
    if (xab.getDiagramType() == Type.LA)
      xab.insertComponent(LA__LOGICAL_CONTEXT__PART_LOGICAL_SYSTEM__LOGICAL_SYSTEM);
    
    xab.createActor(GenericModel.LA_1);
    xab.createActor(GenericModel.LA_2);

    createLink(xab, GenericModel.LA_1, GenericModel.LA_2, GenericModel.CL_1);
    xab.hasView(GenericModel.LA_1);
    xab.hasView(GenericModel.LA_2);
    xab.removeActor(GenericModel.LA_2);
    insertLink(xab, GenericModel.CL_1, GenericModel.LA_1);
    
    DiagramHelper.setSynchronized(xab.getDiagram(), false);
    removeLink(xab, GenericModel.CL_1, GenericModel.LA_1);
    DiagramHelper.setSynchronized(xab.getDiagram(), true);
  }

  protected void testOnLogicalComponents(SessionContext context, String idSource) {
    XABDiagram xab = XABDiagram.createDiagram(context, idSource);
    activateComponentExchangeFilters(xab);
    
    if (xab.getDiagramType() == Type.LA)
      xab.insertComponent(LA__LOGICAL_CONTEXT__PART_LOGICAL_SYSTEM__LOGICAL_SYSTEM);
    
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
    
    DiagramHelper.setSynchronized(xab.getDiagram(), false);
    removeLink(xab, GenericModel.CL_1, GenericModel.LC_2_1);
    DiagramHelper.setSynchronized(xab.getDiagram(), true);
  }

  protected void testOnPhysicalComponents(SessionContext context, String idSource) {
    PABDiagram xab = PABDiagram.createDiagram(context, idSource);
    activateComponentExchangeFilters(xab);

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
    
    DiagramHelper.setSynchronized(xab.getDiagram(), false);
    removeLink(xab, GenericModel.CL_1, GenericModel.LC_2_1);
    DiagramHelper.setSynchronized(xab.getDiagram(), true);
  }

  protected void testOnDeployedPhysicalComponents(SessionContext context, String idSource) {
    PABDiagram xab = PABDiagram.createDiagram(context, idSource);
    activateComponentExchangeFilters(xab);
    
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

    DiagramHelper.setSynchronized(xab.getDiagram(), false);
    removeLink(xab, GenericModel.CL_1, GenericModel.LC_2);
    DiagramHelper.setSynchronized(xab.getDiagram(), true);
  }
  
  protected void testCEOnDeployedPhysicalComponents(SessionContext context, String idSource) {
    PABDiagram xab = PABDiagram.createDiagram(context, idSource);
    activateComponentExchangeFilters(xab);
    
    // Create a component exchange between two deployments
    xab.createNodeComponent(GenericModel.LC_1, xab.getDiagramId());
    createDeployedSubComponent(xab, GenericModel.LC_2, GenericModel.LC_1);
    xab.createNodeComponent(GenericModel.LC_3, xab.getDiagramId());
    createDeployedSubComponent(xab, GenericModel.LC_4, GenericModel.LC_3);
    
    // test styles, check that if another deployed behavior component is not present in diagram,
    // mapping is PAB_PHYSICAL_COMPONENT_MAPPING_NAME
    // even when we have a node deployed component in diagram
    xab.createBehaviorComponent(GenericModel.PC_2_1, xab.getDiagramId());
    xab.createDeployedNodeComponent(GenericModel.PC_3_1, GenericModel.LC_1);
    createLink(xab, GenericModel.LC_2, GenericModel.PC_2_1, GenericModel.CL_4);
    xab.removeDeployedBehaviorComponent(GenericModel.LC_2, GenericModel.LC_1);
    xab.hasntView(GenericModel.LC_2);
    // a deployed behavior (LC4) is present in diagram, mapping is PAB_PHYSICAL_COMPONENT_DEPLOYMENT_MAPPING_NAME
    insertLink(xab, GenericModel.CL_4, GenericModel.PC_2_1);
    xab.hasView(GenericModel.LC_2, IMappingNameConstants.PAB_PHYSICAL_COMPONENT_DEPLOYMENT_MAPPING_NAME);
    xab.removeDeployedBehaviorComponent(GenericModel.LC_2, GenericModel.LC_1);
    xab.hasntView(GenericModel.LC_2);
    xab.removeDeployedBehaviorComponent(GenericModel.LC_4, GenericModel.LC_3);
    // a deployed behavior (LC4) is not present in diagram, mapping is PAB_PHYSICAL_COMPONENT_MAPPING_NAME
    xab.hasntView(GenericModel.LC_4);
    insertLink(xab, GenericModel.CL_4, GenericModel.PC_2_1);
    xab.hasView(GenericModel.LC_2, IMappingNameConstants.PAB_PHYSICAL_COMPONENT_MAPPING_NAME);
  }

  protected void testOnUnDeployedPhysicalComponents(SessionContext context, String idSource) {
    PABDiagram xab = PABDiagram.createDiagram(context, idSource);
    activateComponentExchangeFilters(xab);
    
    // Create a component exchange between a behavior and a deployed behavior.
    // if there is no other deployed displayed, target is displayed as undeployed
    createSubComponent(xab, GenericModel.LC_1, xab.getDiagramId());
    xab.createNodeComponent(GenericModel.LC_2, xab.getDiagramId());
    createDeployedSubComponent(xab, GenericModel.LC_3, GenericModel.LC_2);
    createLink(xab, GenericModel.LC_1, GenericModel.LC_3, GenericModel.CL_1);
    removeDeployedSubComponent(xab, GenericModel.LC_3, GenericModel.LC_2);
    
    insertLink(xab, GenericModel.CL_1, GenericModel.LC_1);
    xab.hasView(GenericModel.LC_3, IMappingNameConstants.PAB_PHYSICAL_COMPONENT_MAPPING_NAME);
    
    DiagramHelper.setSynchronized(xab.getDiagram(), false);
    removeLink(xab, GenericModel.CL_1, GenericModel.LC_1);
    DiagramHelper.setSynchronized(xab.getDiagram(), true);
  }
  
  /**
   * Activate the given filter on the given diagram.
   * @param diagram
   * @param filterName
   */
  protected void activateFilter(DDiagram diagram, String filterName) {
    FilterDescription filter = DiagramHelper.getFilterForDiagram(diagram, filterName);
    Assert.assertNotNull(MessageFormat.format(HelperMessages.filterNotFound, filterName, EObjectExt.getText(diagram)), filter);
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
    Assert.assertNotNull(MessageFormat.format(HelperMessages.filterNotFound, filterName, EObjectExt.getText(diagram)), filter);
    EList<FilterDescription> activatedFilters = diagram.getActivatedFilters();
    assertTrue(activatedFilters.contains(filter));
  }
  
  protected void activateComponentExchangeFilters(XABDiagram xab) {
    AbstractCommand cmd = new AbstractReadWriteCommand() {
      public void run() {
        DAnnotationHelper.deleteAnnotation(IRepresentationAnnotationConstants.DesactivatedFilters, xab.getDiagramDescriptor());
      }
    };
    TestHelper.getExecutionManager(xab.getDiagram()).execute(cmd);
    
    if(xab.getDiagramType() != BlockArchitectureExt.Type.SA) {
      activateFilter(xab.getDiagram(), IMappingNameConstants.HIDE_CE_BY_GROUP);
      activateFilter(xab.getDiagram(), IMappingNameConstants.HIDE_CE_BY_GROUP_ORIENTED);
      activateFilter(xab.getDiagram(), IFilterNameConstants.FILTER_XAB_HIDE_COMPUTED_CE);
      activateFilter(xab.getDiagram(), IFilterNameConstants.FILTER_XAB_HIDE_COMPUTED_PL);
      activateFilter(xab.getDiagram(), IFilterNameConstants.FILTER_XAB_HIDE_SIMPLIFIED_DIAGRAM_BASED_COMPONENT_EXCHANGES);
      activateFilter(xab.getDiagram(), IFilterNameConstants.FILTER_XAB_HIDE_SIMPLIFIED_GROUP_OF_COMPONENT_EXCHANGES_ID);
      activateFilter(xab.getDiagram(), IFilterNameConstants.FILTER_XAB_HIDE_SIMPLIFIED_ORIENTED_GROUPED_COMPONENT_EXCHANGES);
    }
    DiagramHelper.refreshDiagram(xab.getDiagram());
  }
}
