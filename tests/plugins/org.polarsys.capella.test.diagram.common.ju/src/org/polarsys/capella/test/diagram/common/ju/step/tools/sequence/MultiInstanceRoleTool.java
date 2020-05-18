/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.step.tools.sequence;

import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.hamcrest.Matcher;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.IHeadlessResult;
import org.polarsys.capella.test.diagram.common.ju.step.tools.AbstractToolStep;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

public final class MultiInstanceRoleTool extends AbstractToolStep<InstanceRole> {

  private MultiInstanceRoleTool(DiagramContext context, String toolName, Part partToInsert) {
    super(context, toolName);
    this.partToInsert = partToInsert;
  }

  private Collection<DDiagramElement> preExecutionElements;
  private Part partToInsert;
  private InstanceRole result;

  public static InstanceRole newInstanceRole(DiagramContext context, String representedPartId) {

    EObject diagramTarget = ((DSemanticDecorator) context.getDiagram()).getTarget();
    BlockArchitecture ba = BlockArchitectureExt.getRootBlockArchitecture(diagramTarget);
    Part part = context.getSessionContext().getSemanticElement(representedPartId);

    String toolName = null;

    /*
     * Check that the tool is used on any of the supported diagram kinds (OES, IS, ES)
     */
    Matcher<String> diagramDescriptionName = null;

    if (ba.eClass() == OaPackage.Literals.OPERATIONAL_ANALYSIS) {
      diagramDescriptionName = equalTo(IDiagramNameConstants.OPERATIONAL_INTERACTION_SCENARIO_DIAGRAM_NAME);
      toolName = IToolNameConstants.TOOL_OES_MULTI_INSTANCEROLE_ENTITYACTOR;
    } else {
      diagramDescriptionName = anyOf(equalTo(IDiagramNameConstants.INTERFACE_SCENARIO),
          equalTo(IDiagramNameConstants.DATA_FLOW_SCENARIO_DIAGRAM_NAME));

      if (ComponentExt.isActor(part.getAbstractType())) {
        toolName = IToolNameConstants.TOOL_SCENARIO_MULTI_INSTANCEROLE_ACTOR;
      } else {
        toolName = IToolNameConstants.TOOL_SCENARIO_MULTI_INSTANCEROLE_COMPONENT;
      }

    }

    assertThat(context.getDiagram().getDescription().getName(), diagramDescriptionName);
    MultiInstanceRoleTool tool = new MultiInstanceRoleTool(context, toolName, part);
    return tool.run();

  }

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.steps.AbstractExecuteToolCmdStep.tool.AbstractExecuteToolCmdTest#initToolArguments()
   */
  @Override
  protected void initToolArguments() {
    DSemanticDecorator containerView = getDiagramContext().getView(getDiagramContext().getDiagramId());
    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER, containerView.getTarget());
    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, containerView);
  }

  @Override
  protected void postRunTest() {

    List<DDiagramElement> now = new ArrayList<DDiagramElement>(
        getDiagramContext().getDiagram().getOwnedDiagramElements());
    now.removeAll(preExecutionElements);

    assertEquals(1, now.size());
    DDiagramElement elem = now.get(0);

    assertTrue(elem.getTarget() instanceof InstanceRole);
    assertSame(partToInsert, ((InstanceRole) elem.getTarget()).getRepresentedInstance());
    result = (InstanceRole) elem.getTarget();
  }

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.steps.AbstractExecuteToolCmdStep.tool.AbstractExecuteToolCmdTest#preTestRun()
   */
  @Override
  protected void preRunTest() {

    // this auto-selects the part to be inserted from the selection dialog
    HeadlessResultOpProvider.INSTANCE.setCurrentOp(createOperation());
    preExecutionElements = new ArrayList<DDiagramElement>(getDiagramContext().getDiagram().getOwnedDiagramElements());
    super.preRunTest();
  }

  /**
   * @return
   */
  protected IHeadlessResult createOperation() {
    return new IHeadlessResult() {
      @Override
      public Object getResult(java.util.Collection<? extends EObject> selections, Map<String, Object> parameters) {
        @SuppressWarnings("unchecked")
        Iterable<? super Part> scope = (Iterable<? super Part>) parameters.get("scope"); //$NON-NLS-1$
        assertThat(scope, hasItem(partToInsert));
        return partToInsert;
      }
    };
  }

  @Override
  public InstanceRole getResult() {
    return result;
  }

}
