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
package org.polarsys.capella.test.diagram.common.ju.context;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionOperatorKind;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.constants.IDNDToolNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.tools.AbstractToolStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeWithSelectionTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.ReconnectTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.sequence.CombinedFragmentCreationTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.sequence.MessageCreationTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.sequence.TimerCreationTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class SequenceDiagram extends CommonDiagram {
  BlockArchitectureExt.Type type = null;

  public SequenceDiagram(SessionContext context, DDiagram diagram) {
    super(context, diagram);
  }

  public SequenceDiagram(BlockArchitectureExt.Type type, SessionContext context, DDiagram diagram) {
    super(context, diagram);
    this.type = type;
  }

  public String createActor() {
    return createNodeElement(getDiagramId(), IToolNameConstants.TOOL_CREATE_ACTOR);
  }

  public void insertActor(String id) {
    new InsertRemoveInstanceRoleTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_ACTORS).insert(id);
  }

  public void insertActors(String[] ids) {
    new InsertRemoveInstanceRoleTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_ACTORS).insert(ids);
  }

  public void removeActor(String id) {
    new InsertRemoveInstanceRoleTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_ACTORS).remove(id);
  }

  public void removeActors(String[] ids) {
    new InsertRemoveInstanceRoleTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_ACTORS).remove(ids);
  }

  public String insertState(String containerId, String id) {
    new InsertRemoveStateFragmentStateTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_STATE_MODE, containerId)
        .insert(id);
    return id;
  }

  public String insertAllocatedFunction(String containerId, String id) {
    new InsertRemoveStateFragmentAbstractFunctionTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_ALLOCATED_FUNCTION,
        containerId).insert(id);
    return id;
  }

  public void createFunctionalExchange(String functionalExchange, String source, String target) {
    new MessageCreationTool(this, IToolNameConstants.TOOL_CREATE_FUNCTIONAL_EXCHANGE, functionalExchange, source,
        target).run();
  }

  public void createFunctionalExchangeWithReturnBranch(String functionalExchange, String source, String target) {
    new MessageCreationTool(this, IToolNameConstants.TOOL_CREATE_FUNCTIONAL_EXCHANGE_WITH_RETURN_BRANCH,
        functionalExchange, source, target, true).run();
  }

  public void createArmTimer(String source, String target) {
    String name = IToolNameConstants.TOOL_CREATE_ARM_TIMER;
    new TimerCreationTool(this, name, source, target).run();
  }

  public void cancelArmTimer(String source, String target) {
    String name = IToolNameConstants.TOOL_CREATE_CANCEL_TIMER;
    new TimerCreationTool(this, name, source, target).run();
  }

  public void addMultipleLifeLinesForExistingComponent(String id) {
    String name = IToolNameConstants.TOOL_SCENARIO_MULTI_INSTANCEROLE_COMPONENT;
    new InsertRemovePartTool(this, name).insert(id);
  }

  public void dragAndDropComponentFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_SEQ_DND_COMPONENT);
  }

  public String createCombinedFragment(InteractionOperatorKind operatorKind, String containerId) {
    String name = "";
    boolean selectOperand = false;
    switch (operatorKind) {
    case ALT:
      name = IToolNameConstants.TOOL_CREATE_ALT;
      break;
    case LOOP:
      name = IToolNameConstants.TOOL_CREATE_LOOP;
      break;
    case PAR:
      name = IToolNameConstants.TOOL_CREATE_PAR;
      break;
    default:
      name = IToolNameConstants.TOOL_CREATE_OTHER_COMBINED_FRAGMENT;
      selectOperand = true;
      break;
    }

    DNodeContainer container = new CombinedFragmentCreationTool(this, name, getDiagramId(), containerId, operatorKind,
        selectOperand).run();
    return ((CombinedFragment) container.getTarget()).getId();
  }

  public void ceateReference(String selectedScenarioid, String containerId) {
    new ReferenceCreationTool(this, IToolNameConstants.TOOL_CREATE_REFERENCE, getDiagramId(), containerId,
        selectedScenarioid).run();
  }

  public void ceateDuration(String sourceId, String targetId) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_DURATION, sourceId, targetId);
  }

  public void createOperand(String containerId) {
    new OperandCreationTool(this, IToolNameConstants.TOOL_CREATE_OPERAND, containerId).run();
  }

  public void insertExchangeContext(String id) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_EXCHANGE_CONTEXT).insert(id);
  }

  public List<Part> getContainedParts(String scenarioId) {
    List<Part> result = new ArrayList<>();
    EObject obj = getSessionContext().getSemanticElement(scenarioId);
    if (obj instanceof Scenario) {
      result.addAll(((Scenario) obj).getContainedParts());
    }
    return result;
  }

  @Override
  // Override this method to switch between InstanceRole (appear in the diagram)
  // and Part (appear in the Transfer wizard)
  public Collection<EObject> adaptTool(AbstractToolStep<?> tool, Map<String, Object> parameters,
      Collection<EObject> semanticElements) {
    Collection<EObject> result = new ArrayList<EObject>();
    for (EObject element : semanticElements) {
      if ((element instanceof InstanceRole)) {
        result.add(((InstanceRole) element).getRepresentedInstance());
      } else {
        result.add(element);
      }
    }
    return result;
  }
  
  public void reconnectConstraint(String toolName, String id, String oldTargetId, String newTargetId) {
    new ReconnectTool(this,toolName, id, oldTargetId, newTargetId).run();
  }


  /*
   * TOOLS
   */
  protected class ReferenceCreationTool extends CreateAbstractDNodeWithSelectionTool<DNodeContainer> {
    public ReferenceCreationTool(DiagramContext context, String toolName, String targetContainerId, String containerId,
        String selectedId) {
      super(context, toolName, targetContainerId, containerId, selectedId, DNodeContainer.class, InteractionUse.class);
    }

    @Override
    protected DSemanticDecorator getContainerView() {
      return getDiagramContext().getView(containerViewTarget);
    }
  }

  protected class OperandCreationTool extends CreateAbstractDNodeTool<DNodeContainer> {
    public OperandCreationTool(DiagramContext context, String toolName, String containerId) {
      super(context, toolName, containerId, containerId, DNodeContainer.class, InteractionOperand.class);
    }
  }

  protected static class InsertRemoveInstanceRoleTool extends InsertRemoveTool {
    static EReference representing = InformationPackage.Literals.ABSTRACT_INSTANCE__REPRESENTING_INSTANCE_ROLES;
    static EReference represented = InteractionPackage.Literals.INSTANCE_ROLE__REPRESENTED_INSTANCE;
    static EReference[] features = { represented, representing };

    public InsertRemoveInstanceRoleTool(DiagramContext context, String toolName) {
      super(context, toolName);
      this.insertedReferencedElementsFeatures = features;
    }

    @Override
    protected String[] filterResults(Collection<CapellaElement> objs) {
      DRepresentationDescriptor cRDescriptor = getDiagramContext().getDiagramDescriptor();
      String[] results = objs.stream()
          .filter(x -> !(x instanceof InstanceRole) ? true : x.eContainer() == cRDescriptor.getTarget())
          .map(x -> x.getId()).toArray(size -> new String[size]);
      assertTrue(results.length > 0);
      return results;
    }
  }

  protected static class InsertRemovePartTool extends InsertRemoveTool {
    static EReference representing = InformationPackage.Literals.ABSTRACT_INSTANCE__REPRESENTING_INSTANCE_ROLES;
    static EReference[] features = { representing };

    public InsertRemovePartTool(DiagramContext context, String toolName) {
      super(context, toolName);
      this.insertedReferencedElementsFeatures = features;
    }
  }

  protected static class InsertRemoveStateFragmentAbstractFunctionTool extends InsertRemoveTool {
    static EReference feature = InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION;
    static EReference[] features = { feature };

    public InsertRemoveStateFragmentAbstractFunctionTool(DiagramContext context, String toolName, String containerId) {
      super(context, toolName, containerId);
      this.insertedReferencingElementsFeatures = features;
    }
  }

  protected static class InsertRemoveStateFragmentStateTool extends InsertRemoveTool {
    static EReference representing = InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_STATE;
    static EReference[] features = { representing };

    public InsertRemoveStateFragmentStateTool(DiagramContext context, String toolName, String containerId) {
      super(context, toolName, containerId);
      this.insertedReferencingElementsFeatures = features;
    }
  }

  public BlockArchitectureExt.Type getDiagramBlockArchitecture() {
    return type;
  }

}
