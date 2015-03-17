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
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.EdgeMappingSpec;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.filter.Filter;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.filter.FilterKind;
import org.eclipse.sirius.diagram.description.filter.MappingFilter;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.DContainer;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.ui.commands.ICommandService;
import org.polarsys.capella.common.data.activity.Pin;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.common.queries.debug.QueryDebugger;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacommon.ChangeEvent;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateEvent;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacommon.TimeEvent;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.Allocation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.ModellingBlock;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentContext;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd;
import org.polarsys.capella.core.data.fa.ComponentPortKind;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.helpers.information.services.CommunicationLinkExt;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeItemExt;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.PhysicalQuantity;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.AbstractPhysicalComponent;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.diagram.helpers.traceability.DiagramTraceabilityHelper;
import org.polarsys.capella.core.diagram.helpers.traceability.IDiagramTraceability;
import org.polarsys.capella.core.libraries.extendedqueries.QueryIdentifierConstants;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.helpers.AbstractDependenciesPkgExt;
import org.polarsys.capella.core.model.helpers.AssociationExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapabilityRealizationExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveActorsFilter;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.sirius.analysis.activator.SiriusViewActivator;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;
import org.polarsys.capella.core.sirius.analysis.tool.TreeMapSet;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Provides services for all interfaces diagram.
 */
public class CsServices {

  private static CsServices service = null;

  private static final String TRANSITION_TRACEABILITY = "org.polarsys.capella.core.transition.diagram"; //$NON-NLS-1$

  private static final String TRANSITION_TRACEABILITY_COMMAND = TRANSITION_TRACEABILITY;

  public static CsServices getService() {
    if (service == null) {
      service = new CsServices();
    }
    return service;
  }

  public boolean isValidInitializeDiagramFromExistingDiagram(DSemanticDecorator diagram_p) {
    if (!(diagram_p instanceof DRepresentation)) {
      return false;
    }

    return true;
  }

  /**
   * Returns whether the Initialization tool must be show in palette
   * @param diagram_p
   * @return true if hidden
   */
  public boolean isFilterInitializeDiagramFromExistingDiagram(DSemanticDecorator diagram_p) {
    if (!(diagram_p instanceof DRepresentation)) {
      return true;
    }

    IDiagramTraceability handler = DiagramTraceabilityHelper.getService().getTraceabilityHandler((DRepresentation) diagram_p, TRANSITION_TRACEABILITY);
    if (handler == null) {
      return true;
    }

    if (handler.isRealizingable((DRepresentation) diagram_p)) {
      handler.dispose();

      if (diagram_p instanceof DDiagram) {
        // Disable initialization on non-empty diagram

        if (diagram_p instanceof DDiagram) {

          if (DiagramHelper.getService().isA((DDiagram) diagram_p, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
            if (((DDiagram) diagram_p).getOwnedDiagramElements().size() > 1) {
              return true;
            }
          } else if (DiagramHelper.getService().isA((DDiagram) diagram_p, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
            if (((DDiagram) diagram_p).getOwnedDiagramElements().size() > 1) {
              return true;
            }
          } else {
            // Disable initialization on non-empty diagram
            if (!((DDiagram) diagram_p).getOwnedDiagramElements().isEmpty()) {
              return true;
            }
          }

        }
      }
      return false;
    }
    return true;
  }

  public EObject initializeDiagramFromExistingDiagram(DSemanticDecorator diagram_p, DSemanticDecorator sourceDiagram_p) {

    Logger logger = Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM);

    ICommandService commandService = (ICommandService) SiriusViewActivator.getInstance().getWorkbench().getService(ICommandService.class);
    if (commandService == null) {
      logger.error("Cannot access to diagram initialization tool");
      return diagram_p;
    }

    Command command = commandService.getCommand(TRANSITION_TRACEABILITY_COMMAND);
    Map<String, Object> params = new HashMap<String, Object>();

    if (sourceDiagram_p == null) {
      logger.error("Cannot retrieve source diagram for diagram initialization");
      return diagram_p;
    }

    EvaluationContext context = new EvaluationContext(null, Collections.singleton(sourceDiagram_p));
    context.addVariable("TARGET_DIAGRAM", diagram_p);

    try {
      command.executeWithChecks(new ExecutionEvent(command, params, sourceDiagram_p, context));
    } catch (ExecutionException exception_p) {
      logger.error("Errors occured while iagram initialization", exception_p);

    } catch (NotDefinedException exception_p) {
      logger.error("Errors occured while iagram initialization", exception_p);

    } catch (NotEnabledException exception_p) {
      logger.error("Errors occured while iagram initialization", exception_p);

    } catch (NotHandledException exception_p) {
      logger.error("Errors occured while iagram initialization", exception_p);

    }
    return diagram_p;
  }

  /**
   * @param diagram_p
   * @return
   */
  public List<DRepresentation> getScopeInitializeDiagramFromExistingDiagram(DRepresentation diagram_p) {
    List<DRepresentation> scope = new ArrayList<DRepresentation>();
    IDiagramTraceability handler = DiagramTraceabilityHelper.getService().getTraceabilityHandler(diagram_p, TRANSITION_TRACEABILITY);

    Session session = DiagramHelper.getService().getSession(diagram_p);

    for (DView view : session.getOwnedViews()) {
      for (DRepresentation representation : view.getAllRepresentations()) {
        if (handler.isRealizable(representation, diagram_p)) {
          if (!scope.contains(representation)) {
            scope.add(representation);
          }
        }
      }
    }

    handler.dispose();

    return scope;
  }

  /**
   * Returns whether object is contextual element and filter is active
   * @param object_p
   * @param diagram_p
   * @return
   */
  public boolean isFilterContextualElement(EObject object_p, DDiagram diagram_p) {
    if (ContextualDiagramHelper.getService().hasContextualElements(diagram_p)) {
      for (FilterDescription filter : diagram_p.getActivatedFilters()) {
        if (IMappingNameConstants.SHOW_CONTEXTUAL_ELEMENTS.equals(filter.getName())) {
          if (ContextualDiagramHelper.getService().getContextualElements(diagram_p).contains(object_p)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Allows to set variable on the interpreter Should be replaced odesign by org.polarsys.capella.core.sirius.analysis.actions.extensions.SetVariable
   * @param context_p the context
   * @param name_p the name
   * @param value_p the value
   * @return the EObject
   */
  public EObject setInterpreterVariable(EObject context_p, String name_p, EObject value_p) {
    InterpreterUtil.getInterpreter(context_p).setVariable(name_p, value_p);
    return context_p;
  }

  /**
   * Allows to retrieve variable on the interpreter
   * @param context_p the context
   * @param name_p the name
   * @param value_p the value
   * @return the EObject
   */
  public Object getInterpreterVariable(EObject context_p, String name_p) {
    Object result = InterpreterUtil.getInterpreter(context_p).getVariable(name_p);
    return result;
  }

  /**
   * Support for old odesign definition
   */
  @Deprecated
  public EObject setVariableA(EObject context_p, String name_p, EObject value_p) {
    return setInterpreterVariable(context_p, name_p, value_p);
  }

  /**
   * Checks if user has enabled the preference.
   * @param object_p useful only in sirius
   * @return whether if is preference is enabled
   */
  @Deprecated
  public boolean isPreferenceEnabled(EObject object_p, String preference_p) {
    if (object_p instanceof DSemanticDecorator) {
      DSemanticDecorator decorator = (DSemanticDecorator) object_p;
      if ((decorator.getTarget() != null) && (decorator.getTarget() instanceof ModelElement)) {
        return isMultipartMode((ModelElement) decorator.getTarget());
      }
    }
    return true;
  }

  /**
   * Used in sirius to check if multipart mode is enabled
   * @param object, any eobject
   * @return whether if is preference is enabled
   */
  public boolean isMultipartMode(ModelElement object_p) {
    return TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(object_p));
  }

  @Deprecated
  public static final String PREFERENCE_LISTENER_ID = "org.polarsys.capella.core.preferences.Listener"; //$NON-NLS-1$

  @Deprecated
  public static final String PREFERENCE_LISTENER_ID_PREFERENCE_MULTIPART = "multipart.allowed"; //$NON-NLS-1$

  @Deprecated
  public static final String[] PREFERENCE_LISTENER_ID_PREFERENCES = new String[] { PREFERENCE_LISTENER_ID_PREFERENCE_MULTIPART, };

  @Deprecated
  public EObject preferenceListener = null;

  /**
   * Create a wrapper EObject which will be send a notification when the preference change
   * @param object_p
   * @return
   */
  @Deprecated
  public EObject getPreferenceListener(final EObject object_p) {
    return object_p;
  }

  /**
   * Debug purpose only. display a popup message with informations about the given object
   * @param object_p the object
   * @return true, if successful
   */
  public boolean myDebug(EObject object_p) {
    MessageDialog.openInformation(null, object_p.getClass().getName(), object_p.toString());
    return true;
  }

  /**
   * Returns all related capability which are associated to the interface.
   * @param itf_p the given interface
   * @return the related components
   */
  public Collection<Component> getRelatedComponents(Interface itf_p) {
    return InterfaceExt.getRelatedComponents(itf_p);
  }

  public DSemanticDecorator getRelatedPartView(DSemanticDecorator element_p) {
    if ((element_p.getTarget() != null) && ((element_p.getTarget() instanceof Part) || (element_p.getTarget() instanceof Entity))) {
      return element_p;
    }
    if ((element_p.eContainer() != null) && (element_p.eContainer() instanceof DSemanticDecorator)) {
      return getRelatedPartView((DSemanticDecorator) element_p.eContainer());
    }
    return null;
  }

  public InformationsExchanger getRelatedPart(DSemanticDecorator element_p) {
    if ((element_p.getTarget() != null) && ((element_p.getTarget() instanceof Part) || (element_p.getTarget() instanceof Entity))) {
      return (InformationsExchanger) element_p.getTarget();
    }
    if ((element_p.eContainer() != null) && (element_p.eContainer() instanceof DSemanticDecorator)) {
      return getRelatedPart((DSemanticDecorator) element_p.eContainer());
    }
    return null;
  }

  /**
   * Returns all related capability which are associated to the interface.
   * @param itf the given interface
   * @return the related components
   */
  public Collection<CommunicationLinkExchanger> getRelatedExchangers(AbstractExchangeItem item_p) {
    return ExchangeItemExt.getRelatedExchangers(item_p);
  }

  /**
   * Returns all interfaces related to the component.
   * @param root_p the component of the TID table
   * @return interfaces related to the given component for the TID table
   */
  public Collection<Interface> getTIDInterfaces(Component root_p) {
    return getAllRelatedInterfaces(root_p);
  }

  /**
   * Returns label displayed instead of interface name.
   * @param itf_p the given interface
   * @return label of the given interface for the TID table
   */
  public String getTIDInterfaceLabel(Interface itf_p) {
    return itf_p.getName();
  }

  /**
   * Returns label displayed in a cell of the TID.
   * @param itf_p the interface
   * @param line_p the line of the cell
   * @param column_p the column of the cell
   * @return the label which will be display in the cell
   */
  public String getTIDLabel(EObject itf_p, EObject line_p, EObject column_p) {
    Component component = (Component) ((DSemanticDecorator) column_p).getTarget();
    Interface relatedItf = (Interface) ((DSemanticDecorator) line_p).getTarget();

    StringBuffer res = new StringBuffer();

    if (ComponentExt.getAllImplementedInterfaces(component).contains(relatedItf)) {
      res.append(Messages.Diagram_TID_Implemented);
      res.append("\n"); //$NON-NLS-1$
    }
    if (ComponentExt.getAllUsedInterfaces(component).contains(relatedItf)) {
      res.append(Messages.Diagram_TID_Used);
      res.append("\n"); //$NON-NLS-1$
    }
    if (ComponentExt.getAllProvidedInterfaces(component).contains(relatedItf)) {
      res.append(Messages.Diagram_TID_Provided);
      res.append("\n"); //$NON-NLS-1$
    }
    if (ComponentExt.getAllRequiredInterfaces(component).contains(relatedItf)) {
      res.append(Messages.Diagram_TID_Required);
      res.append("\n"); //$NON-NLS-1$
    }

    if (res.length() > 0) {
      res.deleteCharAt(res.length() - 1);
    }

    return res.toString();
  }

  /**
   * Returns whether the given parent is a parent of current element.
   * @param current_p the current EObject
   * @param parent_p the parent EObject
   * @return true, if parent is an ancestor of the current element
   */
  public boolean isAnAncestor(EObject current_p, EObject parent_p) {
    EObject object = current_p;
    for (object = object.eContainer(); object != null; object = object.eContainer()) {
      if (object == parent_p) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns true if e1_p is an ancestor of e2_p and vice versa
   * @param e1_p
   * @param e2_p
   * @return
   */
  public boolean oneIsAncestorAnother(EObject current_p, EObject e1_p, EObject e2_p) {
    return EcoreUtil.isAncestor(e1_p, e2_p) || EcoreUtil.isAncestor(e1_p, e2_p);
  }

  /**
   * Returns the parent component of the element or the blockarchitecture.
   * @param current_p the current element
   * @return the parent component or block architecture
   */
  public EObject getParentContainer(EObject current_p) {
    EObject object = current_p;
    for (object = current_p.eContainer(); object != null; object = object.eContainer()) {
      if ((object instanceof Component) || (object instanceof BlockArchitecture)) {
        return object;
      }
    }
    return null;
  }

  /**
   * Returns the list of parent component or block architecture of the element.
   * @param current_p the current element
   * @return the parent component or block architecture
   */
  public Collection<EObject> getParentContainers(EObject current_p) {
    LinkedList<EObject> elements = new LinkedList<EObject>();
    elements.add(current_p);
    return getParentContainers(elements);
  }

  /**
   * Returns all parents of currents elements
   * @param currents_p the list of elements
   * @return parents component or block architecture
   */
  protected Collection<EObject> getParentContainers(Collection<EObject> currents_p) {
    LinkedList<EObject> toVisits = new LinkedList<EObject>(currents_p);
    Collection<EObject> parents = new java.util.HashSet<EObject>();
    // Access all hierarchy of components and blockarchitectures
    while (toVisits.size() > 0) {
      EObject owner = toVisits.removeFirst();
      EObject parent = getParentContainer(owner);
      if ((parent != null) && !parents.contains(parent)) {
        parents.add(parent);
        toVisits.addLast(parent);
      }
    }
    return parents;
  }

  /**
   * Returns the parent component of the element or the blockarchitecture. If current is a component or a blockarchitecture, returns the given element itself
   * @param current_p the given EObject
   * @return the first parent component or blockarchitecture
   */
  public EObject getFirstParentContainer(EObject current_p) {
    EObject object = current_p;
    for (; object != null; object = object.eContainer()) {
      if ((object instanceof Component) || (object instanceof BlockArchitecture)) {
        return current_p;
      }
    }
    return null;
  }

  /**
   * Returns all components which contains parts of the given component.
   * @param component_p the component
   * @return all components which contains a part of the given component.
   */
  public Collection<Component> getContainersOfParts(Component component_p) {
    Collection<Component> containers = new java.util.HashSet<Component>();

    // Access to all container of parts
    for (Partition partition : component_p.getRepresentingPartitions()) {
      EObject parent = getParentContainer(partition);
      if (parent instanceof Component) {
        containers.add((Component) parent);
      }

      for (DeploymentTarget deploment : PartExt.getDeployingElements((Part) partition)) {
        if (deploment instanceof Part) {
          AbstractType type = (((Part) deploment)).getAbstractType();
          if ((type != null) && (type instanceof Component)) {
            containers.add((Component) type);
          }
        }
      }
    }
    return containers;
  }

  /**
   * Returns all components which contains parts of the given component.
   * @param component_p the component
   * @return all components which contains a part of the given component.
   */
  public Collection<Component> getContainersOfPart(Part part_p) {
    Collection<Component> containers = new java.util.HashSet<Component>();

    // Access to all container of parts
    EObject parent = getParentContainer(part_p);
    if (parent instanceof Component) {
      containers.add((Component) parent);
    }

    for (DeploymentTarget deploment : PartExt.getDeployingElements(part_p)) {
      if (deploment instanceof Part) {
        AbstractType type = (((Part) deploment)).getAbstractType();
        if ((type != null) && (type instanceof Component)) {
          containers.add((Component) type);
        }
      }
    }

    return containers;
  }

  /**
   * Returns whether the given interface can be linked to the current component (interfaces from parents) (requires, implements, uses or provides).
   * @param component_p the given component
   * @param inter_p the given interface
   * @return true, if the interface can be linked to the current component
   */
  public boolean isAvailableInterface(Component component_p, Interface inter_p) {
    return getCCEIInsertInterface(component_p).contains(inter_p);
  }

  /**
   * Returns parents components of parts of the given component.
   * @param component_p the given component
   * @return the parent components by parts of the given component
   */
  public Collection<EObject> getParentContainersByParts(Component component_p) {
    LinkedList<Component> elements = new LinkedList<Component>();
    elements.add(component_p);
    return getParentContainersByParts(elements);
  }

  /**
   * Returns parents components of parts of given objects.
   * @param currents_p the collection of EObject
   * @return the parent components by parts of the given objects.
   */
  public Collection<EObject> getParentContainersByParts(Collection<Component> currents_p) {
    LinkedList<EObject> toVisits = new LinkedList<EObject>(currents_p);
    Collection<EObject> parents = new java.util.HashSet<EObject>();
    // Access all hierarchy of components and blockarchitectures
    while (toVisits.size() > 0) {
      EObject owner = toVisits.removeFirst();
      if (owner instanceof Component) {
        EObject parent = getParentContainer(owner);
        if ((parent != null) && !parents.contains(parent)) {
          parents.add(parent);
          toVisits.addLast(parent);
        }

        for (EObject i : getContainersOfParts((Component) owner)) {
          if ((i != null) && !parents.contains(i)) {
            parents.add(i);
            toVisits.addLast(i);
          }
        }
      }
    }
    return parents;
  }

  /**
   * Returns parents and brothers of parents components of parts of the given object
   */
  public Collection<EObject> getAvailableComponentsByNamespaceOfParts(Component component_p) {
    LinkedList<EObject> ownerParts = new LinkedList<EObject>();
    ownerParts.addAll(getContainersOfParts(component_p));
    return ComponentExt.getAvailableComponentsByNamespaceOfParts(ownerParts);
  }

  /**
   * Returns interfaces available on the Show/Hide interface of the CCII diagram.
   */
  public Collection<Interface> getCCIIInsertInterface(EObject object_p) {
    Collection<Interface> res = getCCEIInsertInterface(object_p);

    if (object_p instanceof Component) {
      res.addAll(getSubDefinedInterfaces((Component) object_p));
      res.addAll(getRelatedInterfaces((Component) object_p));
    } else if (object_p instanceof BlockArchitecture) {
      res.addAll(getSubDefinedInterfaces((BlockArchitecture) object_p));
    }
    return res;
  }

  /**
   * Returns interfaces available on the Show/Hide interface of the IB diagram.
   */
  public Collection<Interface> getIBInsertInterface(EObject object_p) {
    return getCCIIInsertInterface(object_p);
  }

  /**
   * Returns available interfaces which can be added into an external diagram (all interfaces from parents-hierarchy of all part of a component)
   * CCEI-Insert-Interface CCII-Insert-Interface with getSubInterfaces added For an EPBS, returns interfaces related of parents and sub-components and their
   * allocated components For others, returns interfaces of parents components and their allocated components (recursively).
   */
  public Collection<Interface> getCCEIInsertInterface(EObject context_p) {
    // OLD CODE
    Collection<Interface> interfaces = new java.util.HashSet<Interface>();
    Collection<EObject> components = new java.util.HashSet<EObject>();

    // go to the first parent component (which can append when component.eContainer is a package)
    EObject object = getFirstParentContainer(context_p);

    // Add related interfaces
    if (object instanceof ConfigurationItem) {

      components = getParentContainersByParts((Component) object);
      components.add(object);
      components.addAll(getAllSubUsedComponents((ConfigurationItem) object));

      // For all components, add all linked interfaces
      for (EObject subObject : components) {
        if (subObject instanceof Component) {
          Component subComponent = (Component) subObject;
          interfaces.addAll(getRelatedInterfacesFromAllocatedComponent(subComponent));
          interfaces.addAll(getRelatedInterfaces(subComponent));
        }
      }

    } else if (object instanceof Component) {
      // Add components accessible by namespace

      components = getParentContainersByParts((Component) object);

      // For all components, add all defined and linked interfaces
      for (EObject subObject : components) {
        if (subObject instanceof Component) {
          Component subComponent = (Component) subObject;
          interfaces.addAll(getSubDefinedInterfaces(subComponent));
          interfaces.addAll(getRelatedInterfaces(subComponent));
          interfaces.addAll(getInterfacesFromAllocatedComponent(subComponent));

        } else if (subObject instanceof BlockArchitecture) {
          interfaces.addAll(getSubDefinedInterfaces((BlockArchitecture) subObject));
          interfaces.addAll(getInterfacesFromAllocatedArchitecture((BlockArchitecture) subObject));
        }
      }

    } else if (object instanceof EPBSArchitecture) {

      interfaces.addAll(getSubDefinedInterfaces((BlockArchitecture) object));
      components.addAll(getAllSubUsedComponents(getContext((EPBSArchitecture) object)));

      // For all components, add all linked interfaces
      for (EObject subObject : components) {
        if (subObject instanceof Component) {
          Component subComponent = (Component) subObject;
          interfaces.addAll(getRelatedInterfaces(subComponent));
          interfaces.addAll(getRelatedInterfacesFromAllocatedComponent(subComponent));
        }
      }

    } else if (object instanceof BlockArchitecture) {
      interfaces.addAll(getSubDefinedInterfaces((BlockArchitecture) object));
      interfaces.addAll(getInterfacesFromAllocatedArchitecture((BlockArchitecture) object));

    }

    // Remove all allocated interfaces
    removeAllAllocatedInterfaces(interfaces);
    // NEW CODE
    interfaces = (List) QueryDebugger.executeQueryWithInclusionDebug(QueryIdentifierConstants.GET_CCE_INSERT_INTERFACE_FOR_LIB, context_p, interfaces);
    // END CODE REFACTOR
    return interfaces;
  }

  /**
   * Returns all components and its children by generalizable link
   */
  public Collection<GeneralizableElement> getAllSubComponents(Component component_p) {
    Collection<GeneralizableElement> components = GeneralizableElementExt.getAllSubGeneralizableElements(component_p);
    components.add(component_p);
    return components;
  }

  /**
   * Returns related interfaces of components (implements, uses, provides, requires).
   */
  public Collection<CommunicationLink> getRelatedCommunicationLinks(Component component_p) {
    return CommunicationLinkExt.getAllCommunicationLinks(component_p);
  }

  /**
   * Returns related interfaces of components (implements, uses, provides, requires).
   */
  public Collection<Interface> getRelatedInterfaces(Component component_p) {
    Collection<Interface> interfaces = new ArrayList<Interface>();

    if (component_p != null) {
      interfaces.addAll(ComponentExt.getImplementedAndProvidedInterfaces(component_p));
      interfaces.addAll(ComponentExt.getUsedAndRequiredInterfaces(component_p));
    }

    return interfaces;
  }

  /**
   * Returns related interfaces of components (implements, uses, provides, requires).
   */
  public Collection<Interface> getAllRelatedInterfaces(Component component_p) {
    Collection<Interface> interfaces = new ArrayList<Interface>();

    if (component_p != null) {
      interfaces.addAll(ComponentExt.getAllImplementedAndProvidedInterfaces(component_p));
      interfaces.addAll(ComponentExt.getAllUsedAndRequiredInterfaces(component_p));
    }

    return interfaces;
  }

  /**
   * Remove into the given collection all allocated interfaces.
   */
  public void removeAllAllocatedInterfaces(Collection<Interface> interfaces_p) {
    // Remove all allocated interfaces
    Collection<Interface> toRemove = new java.util.HashSet<Interface>();
    for (Interface inter : interfaces_p) {
      if (inter.getAllocatedInterfaces() != null) {
        for (Interface allocated : inter.getAllocatedInterfaces()) {
          toRemove.add(allocated);
        }
      }
    }
    interfaces_p.removeAll(toRemove);
  }

  /**
   * Returns the list with all interfaces which are in allocated components.
   */
  public Collection<Interface> getInterfacesFromAllocatedComponent(Component subComponent_p) {
    Collection<Interface> interfaces = new ArrayList<Interface>();

    // Add interfaces from allocated components
    if (subComponent_p.getAllocatedComponents() != null) {
      LinkedList<Component> allocateds = new LinkedList<Component>();
      for (Component allocated : subComponent_p.getAllocatedComponents()) {
        allocateds.add(allocated); // addAll can throw an exception
      }

      while (allocateds.size() > 0) {
        Component allocated = allocateds.removeFirst();
        interfaces.addAll(getSubDefinedInterfaces(allocated));
        if (allocated.getAllocatedComponents() != null) {
          for (Component suballocated : allocated.getAllocatedComponents()) {
            allocateds.add(suballocated);
          }
        }
      }
    }

    return interfaces;
  }

  /**
   * Fills the list with all interfaces which are in allocated components.
   */
  public Collection<Interface> getRelatedInterfacesFromAllocatedComponent(Component subComponent_p) {
    Collection<Interface> interfaces = new ArrayList<Interface>();

    // Add interfaces from allocated components
    if (subComponent_p.getAllocatedComponents() != null) {
      LinkedList<Component> allocateds = new LinkedList<Component>();
      for (Component allocated : subComponent_p.getAllocatedComponents()) {
        allocateds.add(allocated); // addAll can throw an exception
      }

      while (allocateds.size() > 0) {
        Component allocated = allocateds.removeFirst();
        interfaces.addAll(getRelatedInterfaces(allocated));
      }
    }

    return interfaces;
  }

  /**
   * Fills the list with all interfaces which are in allocated architectures.
   */
  public Collection<Interface> getInterfacesFromAllocatedArchitecture(BlockArchitecture architecture_p) {
    Collection<Interface> interfaces = new ArrayList<Interface>();

    // Add interfaces from allocated components
    if (architecture_p.getAllocatedArchitectures() != null) {
      LinkedList<BlockArchitecture> allocateds = new LinkedList<BlockArchitecture>();
      for (BlockArchitecture allocated : architecture_p.getAllocatedArchitectures()) {
        allocateds.add(allocated); // addAll can throw an exception
      }

      while (allocateds.size() > 0) {
        BlockArchitecture allocated = allocateds.removeFirst();
        interfaces.addAll(getSubDefinedInterfaces(allocated));
        if (allocated.getAllocatedArchitectures() != null) {
          for (BlockArchitecture suballocated : allocated.getAllocatedArchitectures()) {
            allocateds.add(suballocated);
          }
        }
      }
    }

    return interfaces;
  }

  /**
   * Returns whether given components are on the same architecture.
   */
  public boolean isSameArchitecture(NamedElement source_p, NamedElement target_p) {
    BlockArchitecture architectureSource = ComponentExt.getRootBlockArchitecture(source_p);
    BlockArchitecture architectureTarget = ComponentExt.getRootBlockArchitecture(target_p);
    return ((architectureSource == null) && (architectureTarget == null)) || architectureSource.equals(architectureTarget);
  }

  /**
   * Gets the architecture.
   */
  public BlockArchitecture getArchitecture(EObject current_p) {
    return BlockArchitectureExt.getRootBlockArchitecture(current_p);
  }

  /**
   * Returns the current context of a component.
   */
  public Component getContext(Component component_p) {
    EObject parent = getParentContainer(component_p);

    if (parent instanceof BlockArchitecture) {
      return getContext((BlockArchitecture) parent);
    }

    return (Component) parent;
  }

  /**
   * Returns the current context of a component.
   */
  public Component getContext(Part part_p) {
    EObject parent = getParentContainer(part_p);

    if (parent instanceof BlockArchitecture) {
      return getContext((BlockArchitecture) parent);
    }

    return (Component) parent;
  }

  /**
   * Returns the current context of an architecture.
   */
  public Component getContext(BlockArchitecture architecture_p) {
    return BlockArchitectureExt.getContext(architecture_p);
  }

  /**
   * Returns available components which can be added into an internal diagram (all components visible in the current namespace which haven't part)
   * CCII-Insert-Component.
   */
  public Collection<Component> getCCIIInsertComponent(Component component_p) {
    // OLD CODE
    Collection<Component> components = new java.util.HashSet<Component>();

    // Add components accessible by namespace
    components.addAll(ComponentExt.getAvailableComponentsByNamespace(component_p));

    if (!isMultipartMode(component_p)) {
      // Remove component from existing part
      components.removeAll(getSubUsedComponents(component_p));
    }

    // Remove current component and remove all containers of current component
    components.remove(component_p);
    components.removeAll(getParentContainersByParts(component_p));

    if (component_p instanceof PhysicalComponent) {
      components = filterPhysicalComponentsByNature((PhysicalComponent) component_p, components);
    }

    // NEW CODE
    components = (List) QueryDebugger.executeQueryWithInclusionDebug(QueryIdentifierConstants.GET_CCII_INSERT_COMPONENTS_FOR_LIB, component_p, components);
    // END CODE REFACTOR
    return components;
  }

  /**
   * Returns a sub-list of the given components which can be inserted into the given component.
   */
  public Collection<Component> filterPhysicalComponentsByNature(PhysicalComponent component_p, Collection<Component> components_p) {
    Collection<Component> result = new java.util.ArrayList<Component>();
    PhysicalComponentNature natureComponent = component_p.getNature();
    for (Component sub : components_p) {
      if (sub instanceof PhysicalComponent) {
        PhysicalComponentNature nature = ((PhysicalComponent) sub).getNature();
        if ((nature == PhysicalComponentNature.UNSET) || (natureComponent == PhysicalComponentNature.UNSET) || (nature == natureComponent)) {
          result.add(sub);
        }
      }
    }
    return result;
  }

  /**
   * Returns a sub-list of the given components which are only components, not actors.
   */
  // replaced by IQueryFilter RemoveActorsFilter
  public Collection<Component> filterNotActors(Collection<? extends EObject> components_p) {
    Collection<Component> result = new java.util.ArrayList<Component>();
    for (EObject sub : components_p) {
      if ((!(sub instanceof AbstractActor)) && (sub instanceof Component)) {
        if (!result.contains(sub)) {
          result.add((Component) sub);
        }
      }
    }
    return result;
  }

  /**
   * Returns a sub-list of the given components which are only actors.
   */
  // replaced by IQueryFilter KeepRealActorsFilter
  public Collection<Component> filterActors(Collection<? extends EObject> components_p) {
    Collection<Component> result = new java.util.ArrayList<Component>();
    for (EObject sub : components_p) {
      if ((sub instanceof AbstractActor)) {
        result.add((AbstractActor) sub);
      }
    }
    return result;
  }

  /**
   * Returns available components which can be added into an external diagram (all components visible in the current namespace which haven't part)
   * CCEI-Insert-Component.
   */
  public Collection<Component> getCCEIInsertComponent(Component component_p) {
    // OLD CODE
    // Collection<Component> components = new java.util.HashSet<Component>();
    //
    // // Add components accessible by namespace
    // components.addAll(ComponentExt.getAvailableComponentsByNamespace(getParentContainer(component_p)));
    //
    // // Remove component from existing part
    // components.removeAll(ComponentExt.getSubUsedComponents(getContext(component_p)));
    //
    // // Remove current component and remove all containers of current component
    // components.remove(component_p);
    // components.removeAll(getParentContainersByParts(component_p));
    //
    // return components;
    // NEW CODE (REDIRECTION)
    Collection<Component> result = null;
    EObject element = getParentContainer(component_p);
    if (element instanceof Component) {
      result = getCCIIInsertComponent((Component) element);
    } else {
      result = getCCIIInsertComponent(component_p);
    }
    result.remove(component_p);
    return result;
  }

  /**
   * Returns available components which can be added into an external diagram (all components visible in the current namespace which haven't part)
   * CCEI-Insert-Actor.
   */
  public Collection<Component> getCCEIInsertActor(Component component_p) {
    Collection<Component> components = new java.util.HashSet<Component>();

    // Add components accessible by namespace
    components.addAll(ComponentExt.getSubDefinedActors(getArchitecture(component_p)));

    // Remove component from existing part
    components.removeAll(ComponentExt.getSubUsedComponents(getContext(getArchitecture(component_p))));

    // Remove current component and remove all containers of current component
    components.remove(component_p);
    components.removeAll(getParentContainersByParts(component_p));

    return components;
  }

  public Collection<AbstractExchangeItem> getIBShowHideExchangeItems(Component component_p) {
    return CapellaServices.getService().getAllExchangeItems(component_p);
  }

  public Collection<AbstractExchangeItem> getIBReuseExchangeItems(Interface itf_p) {
    return CapellaServices.getService().getAllExchangeItems(itf_p);
  }

  /**
   * Returns available components which can be added into an external diagram (all components visible in the namespace of parts without parents).
   */
  public Collection<? extends EObject> getFilterHideChildComponents(Component component_p) {

    // Get components by namespace (return also a set so it's great for checking a contains(component))
    Collection<EObject> list = getAvailableComponentsByNamespaceOfParts(component_p);

    // Add components of brothers-parts
    // The filter was too restrictive since it was hiding also inner-children of parent of component
    list.addAll(getBrothersComponents(component_p));

    // Remove all hierarchy-container of parts
    list.removeAll(getParentContainersByParts(component_p));

    // Add current element
    list.add(component_p);
    return list;
  }

  /**
   * Gets the brothers components.
   */
  public Collection<Component> getBrothersComponents(Component component_p) {
    Collection<Component> components = new java.util.HashSet<Component>();

    // Add components which are brothers of component-parts
    for (Partition part : component_p.getRepresentingPartitions()) {
      Component container = ComponentExt.getDirectParent(part);
      if (container != null) {
        Component ownerPart = container;
        for (Partition partition : ownerPart.getOwnedPartitions()) {
          if (components.contains(partition.getType())) {
            components.add((Component) partition.getType());
          } else {
            components.add((Component) partition.getType());
          }
        }
      }
    }
    return components;
  }

  /**
   * Returns available components which are accessible by brothers-part CCEI-Show-Hide-Component.
   */
  public Collection<Component> getCCEIShowHideComponent(Component component_p) {
    // OLD CODE
    // Collection<Component> components = getBrothersComponents(component_p);
    // // Remove component
    // components.remove(component_p);
    // components = filterNotActors(components);
    Collection<Component> components =
        (List) QueryInterpretor.executeQuery(org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants.GET_BROTHER_COMPONENTS, component_p,
            new QueryContext(), new RemoveActorsFilter());
    // NEW CODE
    // END CODE REFACTOR
    components =
        (List) QueryDebugger.executeQueryWithInclusionDebug(QueryIdentifierConstants.GET_CCEI_SHOW_HIDE_COMPONENTS_FOR_LIB, component_p,
            new RemoveActorsFilter(), components);

    return components;
  }

  /**
   * Returns available components which are accessible by brothers-part CCEI-Show-Hide-Component.
   */
  public Collection<Component> getCCEIShowHideActors(Component component_p) {
    // OLD CODE
    Collection<Component> components = new ArrayList<Component>();

    // Add actors
    components.addAll(ComponentExt.getSubDefinedActors(getArchitecture(component_p)));
    components = filterActors(components);

    // NEW CODE
    components =
        (List) QueryDebugger.executeQueryWithInclusionDebug(QueryIdentifierConstants.GET_CCEI_SHOW_HIDE_ACTORS_FOR_LIB, getArchitecture(component_p),
            components);
    // END CODE REFACTOR
    return components;
  }

  /**
   * Returns available components which are accessible by brothers-part IB-Show-Hide-Component.
   */
  public Collection<Component> getIBShowHideActors(Component component_p) {
    Collection<Component> components = getCCEIShowHideActors(component_p);
    return components;
  }

  /**
   * Returns available components which are accessible CCII-Show-Hide-Component.
   */
  public Collection<Component> getCCIIShowHideComponent(DSemanticDecorator decorator_p) {
    Collection<Component> components = new ArrayList<Component>();
    if (!(decorator_p.getTarget() instanceof Component)) {
      return new ArrayList<Component>();
    }
    EObject target = getCCIITarget(decorator_p);
    // OLD CODE
    components = getSubComponents(target);
    // NEW CODE
    components = (List) QueryDebugger.executeQueryWithInclusionDebug("GetCCIIShowHideComponent__Lib", target, components);//$NON-NLS-1$
    // END CODE REFACTOR
    return components;
  }

  private Collection<Component> getSubComponents(EObject target) {
    Collection<Component> components = new ArrayList<Component>();
    if (null == target) {
      return components;
    }

    if (target instanceof BlockArchitecture) {
      components.addAll(ComponentExt.getSubDefinedComponents((BlockArchitecture) target));

    } else if (target instanceof Component) {
      components.addAll(ComponentExt.getSubDefinedComponents((Component) target));
      components.addAll(ComponentExt.getSubUsedComponents((Component) target));
    }

    components.remove(target);
    return filterNotActors(components);
  }

  /**
   * Returns available components which are accessible AB-Show-Hide-Component.
   */
  public Collection<? extends CapellaElement> getABShowHideComponent(DSemanticDecorator decorator_p) {
    EObject target = getABTarget(decorator_p);

    // In multipart mode or in EPBS Architecture Blank, we display parts
    if (isMultipartMode((ModelElement) target) || (getComponentType(decorator_p) instanceof ConfigurationItem)) {

      Collection<Partition> components = new ArrayList<Partition>();

      Component targetComponent = null;

      if (target instanceof Component) {
        targetComponent = (Component) target;
      }
      if (target instanceof Part) {
        targetComponent = (Component) ((Part) target).getAbstractType();
      }

      if (decorator_p instanceof DDiagram) {
        if (targetComponent != null) {
          components.addAll(getParts(targetComponent, CsPackage.Literals.COMPONENT, CsPackage.Literals.ABSTRACT_ACTOR));
          components.addAll(targetComponent.getRepresentingPartitions());
        } else if (target instanceof BlockArchitecture) {
          components.addAll(getParts(getContext((BlockArchitecture) target), CsPackage.Literals.COMPONENT, CsPackage.Literals.ABSTRACT_ACTOR));
        }
      } else if (targetComponent != null) {
        components.addAll(getParts(targetComponent, CsPackage.Literals.COMPONENT, CsPackage.Literals.ABSTRACT_ACTOR));
      }

      return components;
    }

    // Mono part, we return all components contained in the part.
    Component root_p = (Component) getComponentType(decorator_p);
    boolean fromDiagram_p = decorator_p instanceof DDiagram;

    if (fromDiagram_p) {
      BlockArchitecture architecture = ComponentExt.getRootBlockArchitecture(root_p);
      return CsServices.getService().getAllSubDefinedComponents(architecture);
    }

    Part part = (Part) decorator_p.getTarget();
    Collection<Component> rsult = PartExt.getComponentsOfParts(ComponentExt.getAllSubUsedParts(root_p, false));
    // Add all children of deployed components
    for (DeployableElement deployed : PartExt.getDeployedElements(part)) {
      if ((deployed instanceof Part) && (((Part) deployed).getAbstractType() instanceof Component)) {
        rsult.addAll(PartExt.getComponentsOfParts(ComponentExt.getAllSubUsedParts((Component) ((Part) deployed).getAbstractType(), false)));
      }
    }
    return rsult;

  }

  /**
   * Returns available components which are accessible CCII-Show-Hide-Component.
   */
  public Collection<Component> getIBShowHideComponent(DSemanticDecorator decorator_p) {
    // OLD CODE
    Collection<Component> components = new HashSet<Component>();
    if (!(decorator_p.getTarget() instanceof Component)) {
      return new ArrayList<Component>();
    }
    EObject target = getIBTarget(decorator_p);
    if (decorator_p instanceof DDiagram) {
      if (target instanceof Component) {
        components.addAll(getBrothersComponents((Component) target));
        components.addAll(ComponentExt.getSubDefinedComponents((Component) target));
        components.addAll(ComponentExt.getSubUsedComponents((Component) target));
        components.add((Component) target);

      } else if (target instanceof BlockArchitecture) {
        components.addAll(ComponentExt.getSubDefinedComponents((BlockArchitecture) target));
      }
    } else if (target instanceof Component) {
      components = new ArrayList<Component>();
      components.addAll(ComponentExt.getSubDefinedComponents((Component) target));
      components.addAll(ComponentExt.getSubUsedComponents((Component) target));
    }
    components = filterNotActors(components);
    // NEW CODE
    components = (List) QueryDebugger.executeQueryWithInclusionDebug(QueryIdentifierConstants.GET_IB_SHOW_HIDE_COMPONENTS_FOR_LIB, decorator_p, components);
    // END CODE REFACTOR
    return components;
  }

  /**
   * Gets the cCII target.
   */
  public EObject getCCIITarget(DSemanticDecorator decorator_p) {
    if (decorator_p instanceof DDiagram) {
      return getParentContainer(decorator_p.getTarget());
    }
    return decorator_p.getTarget();
  }

  /**
   * Gets the AB target.
   */
  public EObject getABTarget(DSemanticDecorator decorator_p) {
    if (decorator_p instanceof DDiagram) {
      if (decorator_p.getTarget() instanceof AbstractActor) {
        return getParentContainer(decorator_p.getTarget());
      }

      ContainerMapping cMapping = FaServices.getFaServices().getMappingABComponent(CsPackage.Literals.COMPONENT, (DDiagram) decorator_p);
      ContainerMapping aMapping = FaServices.getFaServices().getMappingABComponent(CsPackage.Literals.ABSTRACT_ACTOR, (DDiagram) decorator_p);

      for (DDiagramElement element : ((DDiagram) decorator_p).getOwnedDiagramElements()) {
        if ((DiagramServices.getDiagramServices().isMapping(element, cMapping)) || (DiagramServices.getDiagramServices().isMapping(element, aMapping))) {
          if (element.getTarget() == decorator_p.getTarget()) {
            return getParentContainer(decorator_p.getTarget());
          }
          if ((element.getTarget() instanceof Part) && (((Part) element.getTarget()).getAbstractType() == decorator_p.getTarget())) {
            return getParentContainer(decorator_p.getTarget());
          }
        }
      }
      return decorator_p.getTarget();
    }
    return decorator_p.getTarget();
  }

  /**
   * Gets the iB target.
   */
  public EObject getIBTarget(DSemanticDecorator decorator_p) {
    if (decorator_p instanceof DDiagram) {
      if ((decorator_p.getTarget() instanceof AbstractActor) || (decorator_p.getTarget() instanceof System)) {
        return getParentContainer(decorator_p.getTarget());
      }
      for (DDiagramElement element : ((DDiagram) decorator_p).getOwnedDiagramElements()) {
        if (element.getTarget() == decorator_p.getTarget()) {
          return getParentContainer(decorator_p.getTarget());
        }
      }
      return decorator_p.getTarget();
    }
    return decorator_p.getTarget();
  }

  /**
   * @param context
   * @param newSource
   * @param newTarget
   * @return
   */

  public boolean isGeneralizableForReConnect(final EObject context, EObject newSource, EObject newTarget) {

    // return false if source(Class) is not primitive and target(Class) is primitive
    if ((newSource instanceof Class) && (newTarget instanceof Class)) {
      if (((Class) newSource).isIsPrimitive() && !((Class) newTarget).isIsPrimitive()) {
        return false;
      }
    }
    // return false if target(Class) is not primitive and source(Class) is primitive
    if ((newSource instanceof Class) && (newTarget instanceof Class)) {
      if (!((Class) newSource).isIsPrimitive() && ((Class) newTarget).isIsPrimitive()) {
        return false;
      }
    }

    if ((newSource instanceof PhysicalQuantity) && (newTarget instanceof NumericType)) {
      return true;
    }

    if ((context instanceof Generalization) && (newSource instanceof GeneralizableElement) && (newTarget instanceof GeneralizableElement)
        && (newTarget instanceof Class) && !((Class) newTarget).isIsPrimitive() && newSource.eClass().equals(newTarget.eClass())) {

      Generalization generalization = (Generalization) context;

      GeneralizableElement newSourceClazz = (GeneralizableElement) newSource;
      GeneralizableElement newTargetClazz = (GeneralizableElement) newTarget;

      boolean notExistCycle = true;
      boolean superSourcesContainsTarget = CapellaServices.getService().getSuperClassifiers(newSourceClazz, generalization).contains(newTargetClazz);
      boolean subTargetConatinsSource = CapellaServices.getService().getSubClassifiers(newTargetClazz, generalization).contains(newSourceClazz);
      boolean superTargetsContainsSource = CapellaServices.getService().getSuperClassifiers(newTargetClazz, generalization).contains(newSourceClazz);
      boolean subSourcesConatinsTarget = CapellaServices.getService().getSubClassifiers(newSourceClazz, generalization).contains(newTargetClazz);

      if (newSourceClazz.equals(newTargetClazz)) {
        return false;
      }

      if (superTargetsContainsSource) {
        return !subSourcesConatinsTarget;
      }

      if (superSourcesContainsTarget) {
        return !subTargetConatinsSource;
      }

      if (subSourcesConatinsTarget) {
        return superTargetsContainsSource;
      }

      if (!CapellaModelPreferencesPlugin.getDefault().isMultipleInheritanceAllowed()) {
        if (CapellaServices.getService().getSuperClassifiers(newSourceClazz, generalization).size() == 1) {
          return true;
        }
        return !containMultipleIheritence(newSourceClazz, generalization);
      }
      return notExistCycle;
    }

    if (((newSource instanceof PhysicalQuantity) && (newTarget instanceof NumericType))
        || ((newSource instanceof Component) && (newTarget instanceof Component))
        || ((newSource instanceof GeneralizableElement) && (newTarget instanceof GeneralizableElement) && newSource.eClass().equals(newTarget.eClass()))) {

      GeneralizableElement targetClass = (GeneralizableElement) newTarget;
      GeneralizableElement sourceClass = (GeneralizableElement) newSource;

      // check if multiple inheritance is allowed
      // check if target is not already inherited
      // check to avoid inheritance cycle
      boolean existCycle =
          !CapellaServices.getService().getSuperClassifiers(sourceClass).contains(targetClass)
              && !CapellaServices.getService().getSuperClassifiers(targetClass).contains(sourceClass);
      if (!CapellaModelPreferencesPlugin.getDefault().isMultipleInheritanceAllowed()) {
        if (existCycle) {
          if (CapellaServices.getService().getSuperClassifiers(sourceClass).size() == 1) {
            return true;
          }
          // context is Generalization used when reconnect Link is
          // used
          if (context instanceof Generalization) {
            return true;
          }

        }
      } else {
        return existCycle;
      }
    }

    return false;
  }

  /**
   * @param newSourceClazz_p
   * @param generalization_p
   */
  private boolean containMultipleIheritence(GeneralizableElement newSourceClazz_p, Generalization generalization_p) {
    EList<Generalization> generalisations = newSourceClazz_p.getOwnedGeneralizations();
    for (Generalization generalization : generalisations) {
      if (generalization.getSub().equals(newSourceClazz_p)) {
        return true;
      }
    }
    return false;
  }

  public boolean isGeneralizableForReConnectOld(final EObject context, EObject source, EObject target) {

    // return false if source(Class) is not primitive and target(Class) is primitive
    if ((source instanceof Class) && (target instanceof Class)) {
      if (((Class) source).isIsPrimitive() && !((Class) target).isIsPrimitive()) {
        return false;
      }
    }
    // return false if target(Class) is not primitive and source(Class) is primitive
    if ((source instanceof Class) && (target instanceof Class)) {
      if (!((Class) source).isIsPrimitive() && ((Class) target).isIsPrimitive()) {
        return false;
      }
    }

    if ((source instanceof PhysicalQuantity) && (target instanceof NumericType)) {
      return true;
    }

    if ((context instanceof Generalization) && (source instanceof GeneralizableElement) && (target instanceof GeneralizableElement)
        && !((Class) target).isIsPrimitive() && source.eClass().equals(target.eClass())) {

      GeneralizableElement sourceClazz = (GeneralizableElement) source;
      GeneralizableElement targetClazz = (GeneralizableElement) target;

      boolean notExistCycle = true;
      boolean superSourcesContainsTarget = CapellaServices.getService().getSuperClassifiers(sourceClazz).contains(targetClazz);
      boolean subTargetConatinsSource = CapellaServices.getService().getSubClassifiers(targetClazz).contains(sourceClazz);
      boolean superTargetsContainsSource = CapellaServices.getService().getSuperClassifiers(targetClazz).contains(sourceClazz);
      boolean subSourcesConatinsTarget = CapellaServices.getService().getSubClassifiers(sourceClazz).contains(targetClazz);

      if (superSourcesContainsTarget) {
        return !subTargetConatinsSource;
      }

      if (subSourcesConatinsTarget) {
        return CapellaModelPreferencesPlugin.getDefault().isMultipleInheritanceAllowed() && superTargetsContainsSource;
      }

      return notExistCycle;

    }

    if (((source instanceof PhysicalQuantity) && (target instanceof NumericType)) || ((source instanceof Component) && (target instanceof Component))
        || ((source instanceof GeneralizableElement) && (target instanceof GeneralizableElement) && source.eClass().equals(target.eClass()))) {

      GeneralizableElement targetClass = (GeneralizableElement) target;
      GeneralizableElement sourceClass = (GeneralizableElement) source;

      // check if multiple inheritance is allowed
      // check if target is not already inherited
      // check to avoid inheritance cycle
      boolean existCycle =
          !CapellaServices.getService().getSuperClassifiers(sourceClass).contains(targetClass)
              && !CapellaServices.getService().getSuperClassifiers(targetClass).contains(sourceClass);
      if (!CapellaModelPreferencesPlugin.getDefault().isMultipleInheritanceAllowed()) {
        if (existCycle) {
          if (CapellaServices.getService().getSuperClassifiers(sourceClass).size() == 1) {
            return true;
          }
          // context is Generalization used when reconnect Link is
          // used
          if (context instanceof Generalization) {
            return true;
          }

        }
      } else {
        return existCycle;
      }
    }

    return false;
  }

  /**
   * used in Common (tool reconnect generalization in CDB and Interface diagrams)
   * @param generalization_p current generalization
   * @param newSource_p the new source of the generalization
   * @return true if newSource_p can be the source of generalization_p
   */
  public boolean canReconnectGeneralization(EObject generalization_p, EObject subObject_p, EObject targetObject_p) {
    EObject source = subObject_p;
    EObject target = targetObject_p;

    if (!isGeneralizableForReConnect(generalization_p, source, target)) {
      return false;
    }

    if ((source instanceof FinalizableElement) && (target instanceof FinalizableElement)) {
      return !((FinalizableElement) target).isFinal();
    }

    if (!((source instanceof Component) && (target instanceof Component))) {

      source = getParentContainer(source);
      target = getParentContainer(target);

      if ((source instanceof ModellingArchitecture) && (target instanceof ModellingArchitecture)) {

        AbstractDependenciesPkg sourcePkg =
            (AbstractDependenciesPkg) CapellaServices.getService().getParent(subObject_p, CapellacorePackage.Literals.ABSTRACT_DEPENDENCIES_PKG);
        AbstractDependenciesPkg targetPkg =
            (AbstractDependenciesPkg) CapellaServices.getService().getParent(targetObject_p, CapellacorePackage.Literals.ABSTRACT_DEPENDENCIES_PKG);

        if (AbstractDependenciesPkgExt.isADependencyAvailable(sourcePkg, targetPkg)) {
          return true;
        }

      } else if ((source instanceof ModellingArchitecture) || (target instanceof ModellingArchitecture)) {
        return true;
      }
    }

    if ((source instanceof Component) && (target instanceof Component)) {

      if (((Component) source).getTypedElements().size() == 0) {
        return false;
      }

      for (TypedElement element : ((Component) source).getTypedElements()) {
        if (element instanceof Part) {
          if (ComponentExt.getAvailableComponentsByNamespaceOfParts((Part) element).contains(target)) {
            return true;
          }
        }
      }

    }
    return false;
  }

  /**
   * Returns whether the reconnect-source performed between source and target is available (if given target is a brother or an ancestor of given source).
   */
  public boolean linkReconnectSource(EObject current_p, EObject sourceObject_p, EObject targetObject_p) {
    EObject source = sourceObject_p;
    EObject target = targetObject_p;

    if (!((source instanceof Component) || (source instanceof ComponentPort)) || (source instanceof ConfigurationItem)) {
      return false;
    }
    if (!((target instanceof Component) || (target instanceof ComponentPort)) || (target instanceof ConfigurationItem)) {
      return false;
    }
    if ((source instanceof LogicalActor) && (target instanceof Component)) {
      return true;
    }
    if ((target instanceof LogicalActor) && (source instanceof Component)) {
      return true;
    }
    if (source instanceof ComponentPort) {
      source = source.eContainer();
    }
    if (target instanceof ComponentPort) {
      target = target.eContainer();
    }
    if ((source instanceof LogicalActor) && (target instanceof Component)) {
      return true;
    }
    if ((target instanceof LogicalActor) && (source instanceof Component)) {
      return true;
    }

    EObject sAncestor = CapellaServices.getService().getAncestor(source, CsPackage.Literals.COMPONENT);
    EObject tAncestor = CapellaServices.getService().getAncestor(target, CsPackage.Literals.COMPONENT);

    if (sAncestor == tAncestor) {
      return true;
    }

    if (isAnAncestor(source, target)) {
      return true;
    }

    return false;
  }

  /**
   * Returns the list of interfaces defined into a component.
   */
  public Collection<Interface> getSubDefinedInterfaces(Component component_p) {
    return InterfacePkgExt.getAllInterfaces(component_p.getOwnedInterfacePkg());
  }

  /**
   * Returns the list of interfaces defined into the architecture.
   */
  public Collection<Interface> getSubDefinedInterfaces(BlockArchitecture block_p) {
    return InterfacePkgExt.getAllInterfaces(block_p.getOwnedInterfacePkg());
  }

  /**
   * Returns true if the edge represents a sub-link of provided interface.
   */
  public boolean isASubProvidedLink(DEdge edge_p) {

    if ((edge_p.getSourceNode() instanceof DSemanticDecorator) && (edge_p.getTargetNode() instanceof DSemanticDecorator)) {
      ComponentPort port = (ComponentPort) ((DSemanticDecorator) edge_p.getSourceNode()).getTarget();
      Interface inter = (Interface) ((DSemanticDecorator) edge_p.getTargetNode()).getTarget();

      List<ComponentPort> ports = new ArrayList<ComponentPort>();
      List<ComponentPort> portsVisited = new ArrayList<ComponentPort>();
      portsVisited.add(port);
      for (PortAllocation allocation : port.getOutgoingPortAllocations()) {
        if ((allocation.getAllocatedPort() instanceof ComponentPort) && !portsVisited.contains(allocation.getAllocatedPort())) {
          ports.add((ComponentPort) allocation.getAllocatedPort());
        }
      }

      while (ports.size() > 0) {
        port = ports.remove(0);
        if (port.getProvidedInterfaces().contains(inter) && DiagramServices.getDiagramServices().isOnDiagram(edge_p.getParentDiagram(), port)) {
          return false;
        }
        portsVisited.add(port);
        for (PortAllocation allocation : port.getOutgoingPortAllocations()) {
          if ((allocation.getAllocatedPort() instanceof ComponentPort) && !portsVisited.contains(allocation.getAllocatedPort())) {
            ports.add((ComponentPort) allocation.getAllocatedPort());
          }
        }
      }
    }
    return true;
  }

  /**
   * Returns true if the edge represents a super-link of provided interface.
   */
  public boolean isASuperProvidedLink(DEdge edge_p) {

    if ((edge_p.getSourceNode() instanceof DSemanticDecorator) && (edge_p.getTargetNode() instanceof DSemanticDecorator)) {
      ComponentPort port = (ComponentPort) ((DSemanticDecorator) edge_p.getSourceNode()).getTarget();
      Interface inter = (Interface) ((DSemanticDecorator) edge_p.getTargetNode()).getTarget();

      List<ComponentPort> ports = new ArrayList<ComponentPort>();
      List<ComponentPort> portsVisited = new ArrayList<ComponentPort>();
      portsVisited.add(port);
      for (PortAllocation allocation : port.getIncomingPortAllocations()) {
        if ((allocation.getAllocatingPort() instanceof ComponentPort) && !portsVisited.contains(allocation.getAllocatingPort())) {
          ports.add((ComponentPort) allocation.getAllocatingPort());
        }
      }

      while (ports.size() > 0) {
        port = ports.remove(0);
        if (port.getProvidedInterfaces().contains(inter) && DiagramServices.getDiagramServices().isOnDiagram(edge_p.getParentDiagram(), port)) {
          return false;
        }
        portsVisited.add(port);
        for (PortAllocation allocation : port.getOutgoingPortAllocations()) {
          if ((allocation.getAllocatedPort() instanceof ComponentPort) && !portsVisited.contains(allocation.getAllocatedPort())) {
            ports.add((ComponentPort) allocation.getAllocatedPort());
          }
        }
      }
    }
    return true;
  }

  /**
   * Returns true if the edge represents a sub-link of required interface.
   */
  public boolean isASubRequiredLink(DEdge edge_p) {

    if ((edge_p.getSourceNode() instanceof DSemanticDecorator) && (edge_p.getTargetNode() instanceof DSemanticDecorator)) {
      ComponentPort port = (ComponentPort) ((DSemanticDecorator) edge_p.getSourceNode()).getTarget();
      Interface inter = (Interface) ((DSemanticDecorator) edge_p.getTargetNode()).getTarget();

      List<ComponentPort> ports = new ArrayList<ComponentPort>();
      List<ComponentPort> portsVisited = new ArrayList<ComponentPort>();
      portsVisited.add(port);
      for (PortAllocation allocation : port.getOutgoingPortAllocations()) {
        if ((allocation.getAllocatedPort() instanceof ComponentPort) && !portsVisited.contains(allocation.getAllocatedPort())) {
          ports.add((ComponentPort) allocation.getAllocatedPort());
        }
      }

      while (ports.size() > 0) {
        port = ports.remove(0);
        if (port.getRequiredInterfaces().contains(inter) && DiagramServices.getDiagramServices().isOnDiagram(edge_p.getParentDiagram(), port)) {
          return false;
        }
        portsVisited.add(port);
        for (PortAllocation allocation : port.getOutgoingPortAllocations()) {
          if ((allocation.getAllocatedPort() instanceof ComponentPort) && !portsVisited.contains(allocation.getAllocatedPort())) {
            ports.add((ComponentPort) allocation.getAllocatedPort());
          }
        }
      }
    }
    return true;
  }

  /**
   * Returns true if the edge represents a super-link of required interface.
   */
  public boolean isASuperRequiredLink(DEdge edge_p) {

    if ((edge_p.getSourceNode() instanceof DSemanticDecorator) && (edge_p.getTargetNode() instanceof DSemanticDecorator)) {
      ComponentPort port = (ComponentPort) ((DSemanticDecorator) edge_p.getSourceNode()).getTarget();
      Interface inter = (Interface) ((DSemanticDecorator) edge_p.getTargetNode()).getTarget();

      List<ComponentPort> ports = new ArrayList<ComponentPort>();
      List<ComponentPort> portsVisited = new ArrayList<ComponentPort>();
      portsVisited.add(port);
      for (PortAllocation allocation : port.getIncomingPortAllocations()) {
        if ((allocation.getAllocatingPort() instanceof ComponentPort) && !portsVisited.contains(allocation.getAllocatingPort())) {
          ports.add((ComponentPort) allocation.getAllocatingPort());
        }
      }

      while (ports.size() > 0) {
        port = ports.remove(0);
        if (port.getRequiredInterfaces().contains(inter) && DiagramServices.getDiagramServices().isOnDiagram(edge_p.getParentDiagram(), port)) {
          return false;
        }
        portsVisited.add(port);
        for (PortAllocation allocation : port.getOutgoingPortAllocations()) {
          if ((allocation.getAllocatedPort() instanceof ComponentPort) && !portsVisited.contains(allocation.getAllocatedPort())) {
            ports.add((ComponentPort) allocation.getAllocatedPort());
          }
        }
      }
    }
    return true;
  }

  /**
   * Returns parent component of the component.
   */
  protected List<Component> getSuperComponents(Component component_p) {
    List<Component> comps = new ArrayList<Component>();
    EObject item = component_p;
    if (item == null) {
      return comps;
    }
    item = item.eContainer();
    while (item != null) {
      if (item instanceof Component) {
        comps.add((Component) item);
        break;
      }
      item = item.eContainer();
    }
    return comps;
  }

  /**
   * Returns sub components of the component which are used (have a part).
   */
  public List<Component> getSubUsedComponents(Component component_p) {
    return ComponentExt.getSubUsedComponents(component_p);
  }

  /**
   * Returns sub components of the component.
   */
  public Collection<Component> getAllSubUsedComponents(Component component_p) {
    return ComponentExt.getAllSubUsedComponents(component_p);
  }

  /**
   * Returns sub components of the component.
   */
  public List<Component> getAllSubDefinedComponents(Component component_p) {
    List<Component> comps = new ArrayList<Component>();
    LinkedList<Component> subs = new LinkedList<Component>();
    List<Component> internal = new ArrayList<Component>();

    subs.add(component_p);
    while (subs.size() > 0) {
      Component sub = subs.removeFirst();
      internal = ComponentExt.getSubDefinedComponents(sub);
      comps.addAll(internal);
      subs.addAll(internal);
    }
    return comps;
  }

  /**
   * Returns sub components of the component.
   */
  public List<Component> getAllSubDefinedComponents(BlockArchitecture architecture_p) {
    List<Component> comps = new ArrayList<Component>();
    LinkedList<Component> subs = new LinkedList<Component>();
    List<Component> internal = new ArrayList<Component>();

    subs.addAll(ComponentExt.getSubDefinedComponents(architecture_p));
    while (subs.size() > 0) {
      Component sub = subs.removeFirst();
      comps.add(sub);
      internal = ComponentExt.getSubDefinedComponents(sub);
      comps.addAll(internal);
      subs.addAll(internal);
    }
    return comps;
  }

  /**
   * Returns true if the edge represents a sub-link of implemented interface.
   */
  public boolean isASubImplementedLink(DEdge edge_p) {

    if ((edge_p.getSourceNode() instanceof DSemanticDecorator) && (edge_p.getTargetNode() instanceof DSemanticDecorator)) {
      Component comp = (Component) ((DSemanticDecorator) (edge_p.getSourceNode())).getTarget();
      Interface inter = (Interface) ((DSemanticDecorator) (edge_p.getTargetNode())).getTarget();

      List<Component> comps = new ArrayList<Component>();
      List<Component> compsVisited = new ArrayList<Component>();
      comps.addAll(getSuperComponents(comp));

      while (comps.size() > 0) {
        comp = comps.remove(0);
        if (comp.getImplementedInterfaces().contains(inter) && DiagramServices.getDiagramServices().isOnDiagram(edge_p.getParentDiagram(), comp)) {
          return false;
        }
        compsVisited.add(comp);
        for (Component component : getSuperComponents(comp)) {
          if (!compsVisited.contains(comp)) {
            comps.add(component);
          }
        }
      }
    }
    return true;
  }

  /**
   * Returns true if the edge represents a super-link of implemented interface.
   */
  public boolean isASuperImplementedLink(DEdge edge_p) {

    if ((edge_p.getSourceNode() instanceof DSemanticDecorator) && (edge_p.getTargetNode() instanceof DSemanticDecorator)) {
      Component comp = (Component) ((DSemanticDecorator) (edge_p.getSourceNode())).getTarget();
      Interface inter = (Interface) ((DSemanticDecorator) (edge_p.getTargetNode())).getTarget();

      List<Component> comps = new ArrayList<Component>();
      List<Component> compsVisited = new ArrayList<Component>();
      comps.addAll(ComponentExt.getSubUsedComponents(comp));

      while (comps.size() > 0) {
        comp = comps.remove(0);
        if (comp.getImplementedInterfaces().contains(inter) && DiagramServices.getDiagramServices().isOnDiagram(edge_p.getParentDiagram(), comp)) {
          return false;
        }
        compsVisited.add(comp);
        for (Component component : ComponentExt.getSubUsedComponents(comp)) {
          if (!compsVisited.contains(comp)) {
            comps.add(component);
          }
        }
      }
    }
    return true;
  }

  /**
   * Returns true if the edge represents a sub-link of used interface.
   */
  public boolean isASubUsedLink(DEdge edge_p) {

    if ((edge_p.getSourceNode() instanceof DSemanticDecorator) && (edge_p.getTargetNode() instanceof DSemanticDecorator)) {
      Component comp = (Component) ((DSemanticDecorator) (edge_p.getSourceNode())).getTarget();
      Interface inter = (Interface) ((DSemanticDecorator) (edge_p.getTargetNode())).getTarget();

      List<Component> comps = new ArrayList<Component>();
      List<Component> compsVisited = new ArrayList<Component>();
      comps.addAll(getSuperComponents(comp));

      while (comps.size() > 0) {
        comp = comps.remove(0);
        if (comp.getUsedInterfaces().contains(inter) && DiagramServices.getDiagramServices().isOnDiagram(edge_p.getParentDiagram(), comp)) {
          return false;
        }
        compsVisited.add(comp);
        for (Component component : getSuperComponents(comp)) {
          if (!compsVisited.contains(comp)) {
            comps.add(component);
          }
        }
      }
    }
    return true;
  }

  /**
   * Returns true if the edge represents a super-link of used interface.
   */
  public boolean isASuperUsedLink(DEdge edge_p) {

    if ((edge_p.getSourceNode() instanceof DSemanticDecorator) && (edge_p.getTargetNode() instanceof DSemanticDecorator)) {
      Component comp = (Component) ((DSemanticDecorator) (edge_p.getSourceNode())).getTarget();
      Interface inter = (Interface) ((DSemanticDecorator) (edge_p.getTargetNode())).getTarget();

      List<Component> comps = new ArrayList<Component>();
      List<Component> compsVisited = new ArrayList<Component>();
      comps.addAll(ComponentExt.getSubUsedComponents(comp));

      while (comps.size() > 0) {
        comp = comps.remove(0);
        if (comp.getUsedInterfaces().contains(inter) && DiagramServices.getDiagramServices().isOnDiagram(edge_p.getParentDiagram(), comp)) {
          return false;
        }
        compsVisited.add(comp);
        for (Component component : ComponentExt.getSubUsedComponents(comp)) {
          if (!compsVisited.contains(comp)) {
            comps.add(component);
          }
        }
      }
    }
    return true;
  }

  /**
   * Checks if port is an standard port.
   * @param port_p the given componentPort
   * @return true, if is standard port
   */
  public boolean isStandardPort(EObject port_p) {
    return PortExt.isStandardPort(port_p);
  }

  /**
   * Checks if port is a flow port.
   * @param port_p the given componentPort
   * @return true, if is a flow port
   */
  public boolean isFlowPort(EObject port_p) {
    return PortExt.isFlowPort(port_p);
  }

  /**
   * Checks if port is an in flow port.
   * @param port_p the given componentPort
   * @return true, if is in flow port
   */
  public boolean isInFlowPort(EObject port_p) {
    return PortExt.isInFlowPort(port_p);
  }

  /**
   * Checks if port is an out flow port.
   * @param port_p the given componentPort
   * @return true, if is out flow port
   */
  public boolean isOutFlowPort(EObject port_p) {
    return PortExt.isOutFlowPort(port_p);
  }

  /**
   * Returns whether the edge between preSourceView and preTargetView is valid to create a ComponentExchange Should be named isValidCreationConnectionWithPort
   */
  public boolean isValidCreationABComponentExchange(EObject root_p, DSemanticDecorator preSourceView_p, DSemanticDecorator preTargetView_p) {

    boolean sourceValid = true;
    boolean targetValid = true;

    // diagram target
    EObject preSource = preSourceView_p.getTarget();
    EObject preTarget = preTargetView_p.getTarget();

    // component
    EObject sourceComponent = getComponentType(preSourceView_p);
    EObject targetComponent = getComponentType(preTargetView_p);

    // disable same port connection in one part mode
    if ((preSource == preTarget) && !isMultipartMode((ModelElement) sourceComponent)) {
      return false;
    }

    // allow only OutFlowPort with StandardPort/InFlowPort and StandardPort with StandardPort/OutFlowPort
    if (preSource instanceof ComponentPort) {
      ComponentPort port = (ComponentPort) preSource;
      sourceValid = PortExt.isOutFlowPort(port) || PortExt.isStandardPort(port);
    }

    if (preTarget instanceof ComponentPort) {
      ComponentPort port = (ComponentPort) preTarget;
      targetValid = PortExt.isInFlowPort(port) || PortExt.isStandardPort(port);
    }

    // Exclude PhysicalComponent nodes.
    if ((sourceComponent != null) && (sourceComponent instanceof PhysicalComponent)) {
      sourceValid = (sourceValid) ? ((PhysicalComponent) sourceComponent).getNature() != PhysicalComponentNature.NODE : false;
    }
    if ((targetComponent != null) && (targetComponent instanceof PhysicalComponent)) {
      targetValid = (targetValid) ? ((PhysicalComponent) targetComponent).getNature() != PhysicalComponentNature.NODE : false;
    }
    return sourceValid && targetValid;

  }

  /**
   * Returns a unique name for a part If there is no part with its type name, use the type name otherwise, add to type name a final sequence with number
   */
  public static String getPartUniqueName(Partition part_p) {
    int i = 0;
    boolean isCorrectlyNamed = false;
    AbstractType type = part_p.getAbstractType();
    String racine = type.getName();
    String name = racine;
    while (!isCorrectlyNamed) {
      boolean nameExist = false;
      for (EObject object : part_p.eContainer().eContents()) {
        if (object instanceof Part) {
          if (((Part) object).getName().equals(name)) {
            i++;
            name = racine + ICommonConstants.WHITE_SPACE_CHARACTER + i;
            nameExist = true;
            break;
          }
        }
      }
      if (!nameExist) {
        isCorrectlyNamed = true;
      }
    }
    return name;
  }

  public boolean isValidCreationPABComponentExchange(EObject container_p, DSemanticDecorator sourceView_p) {

    if ((sourceView_p == null) || (sourceView_p.getTarget() == null)) {
      return false;
    }

    if (sourceView_p.getTarget() instanceof PhysicalPort) {
      return false;
    }

    EObject source = getComponentType(sourceView_p);
    return isAbstractActorOrNotNodeComponent(source);
  }

  /**
   * Returns whether the source and target elements are both source/target for a component exchange in the PAB diagram
   */
  public boolean isValidCreationPABComponentExchange(EObject container_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {

    if (!isValidCreationABComponentExchange(container_p, sourceView_p, targetView_p)) {
      return false;
    }

    EObject source = getComponentType(sourceView_p);
    EObject target = getComponentType(targetView_p);

    return (source != target) && isAbstractActorOrNotNodeComponent(source) && isAbstractActorOrNotNodeComponent(target);
  }

  public boolean isValidCreationPABDelegationExchange(EObject container_p, DSemanticDecorator sourceView_p) {

    if ((sourceView_p == null) || (sourceView_p.getTarget() == null)) {
      return false;
    }

    if (sourceView_p.getTarget() instanceof PhysicalPort) {
      return false;
    }

    EObject source = getComponentType(sourceView_p);
    return isAbstractActorOrNotNodeComponent(source);
  }

  /**
   * Returns whether the source and target elements are both source/target for a component exchange in the PAB diagram
   */
  public boolean isValidCreationPABDelegationExchange(EObject container_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {

    if (!isValidCreationABDelegationExchange(container_p, sourceView_p, targetView_p)) {
      return false;
    }

    EObject source = getComponentType(sourceView_p);
    EObject target = getComponentType(targetView_p);

    return (source != target) && isAbstractActorOrNotNodeComponent(source) && isAbstractActorOrNotNodeComponent(target);
  }

  /**
   * Returns whether the source and target elements are both source/target for a physical link in the PAB diagram
   */
  public boolean isValidCreationABPhysicalLink(EObject container_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {
    boolean sourceValid = false;
    boolean targetValid = false;

    DSemanticDecorator sourceElement = sourceView_p;
    DSemanticDecorator targetElement = targetView_p;

    EObject preSource = sourceElement.getTarget();
    EObject preTarget = targetElement.getTarget();

    if (TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(preSource))) {
      if (preSource == preTarget) {
        return false;
      }

    } else {
      if (preSource == preTarget) {
        return true;
      }
    }

    if (preSource instanceof PhysicalPort) {
      sourceValid = true;
      sourceElement = (DSemanticDecorator) sourceElement.eContainer();

    } else if (preSource instanceof Part) {
      Type type = ((Part) preSource).getType();
      if ((type != null) && (type instanceof Component)) {
        sourceValid = true;
      }
    } else if (preSource instanceof Component) {
      sourceValid = true;
    }

    if (preTarget instanceof PhysicalPort) {
      targetValid = true;
      targetElement = (DSemanticDecorator) targetElement.eContainer();

    } else if (preTarget instanceof Part) {
      Type type = ((Part) preTarget).getType();
      if ((type != null) && (type instanceof Component)) {
        targetValid = true;
      }
    } else if (preTarget instanceof Component) {
      targetValid = true;
    }

    sourceElement = (DSemanticDecorator) sourceElement.eContainer();
    targetElement = (DSemanticDecorator) targetElement.eContainer();

    if (!(sourceValid && targetValid)) {
      return false;
    }

    EObject source = getComponentType(sourceView_p);
    EObject target = getComponentType(targetView_p);

    if ((source instanceof PhysicalPort) && (target instanceof PhysicalPort)) {
      if (TriStateBoolean.False.equals(CapellaProjectHelper.isReusableComponentsDriven(source))) {
        return source.eContainer() != target.eContainer();
      }
      return source.eContainer() == target.eContainer();

    } else if (source instanceof PhysicalPort) {
      if (TriStateBoolean.False.equals(CapellaProjectHelper.isReusableComponentsDriven(source)) && (source.eContainer() == target)) {
        return false;
      }
      return isAbstractActorOrNodeComponent(source.eContainer()) && isAbstractActorOrNodeComponent(target);

    } else if (target instanceof PhysicalPort) {
      if (TriStateBoolean.False.equals(CapellaProjectHelper.isReusableComponentsDriven(source)) && (source == target.eContainer())) {
        return false;
      }
      return isAbstractActorOrNodeComponent(source) && isAbstractActorOrNodeComponent(target.eContainer());

    } else {
      if (TriStateBoolean.False.equals(CapellaProjectHelper.isReusableComponentsDriven(source)) && (source == target)) {
        return false;
      }
      return isAbstractActorOrNodeComponent(source) && isAbstractActorOrNodeComponent(target);
    }
  }

  /**
   * Returns whether given element is an abstract actor or a not-node physical component
   */
  protected boolean isAbstractActorOrNotNodeComponent(EObject source_p) {
    return ((source_p instanceof AbstractActor) || ((source_p instanceof PhysicalComponent) && (((PhysicalComponent) source_p).getNature() != PhysicalComponentNature.NODE)));
  }

  /**
   * Returns whether given element is an abstract actor or a node physical component
   */
  protected boolean isAbstractActorOrNodeComponent(EObject source_p) {
    return ((source_p instanceof AbstractActor) || ((source_p instanceof PhysicalComponent) && (((PhysicalComponent) source_p).getNature() == PhysicalComponentNature.NODE)));
  }

  /**
   * Returns whether the edge between preSourceView and preTargetView is valid to create a Delegation
   */
  public boolean isValidCreationABDelegationExchange(EObject root_p, DSemanticDecorator preSourceView_p, DSemanticDecorator preTargetView_p) {

    EObject preSource = preSourceView_p.getTarget();
    EObject preTarget = preTargetView_p.getTarget();

    if (preSource == preTarget) {
      return false;
    }

    // If connected to two ports, they should have same kind / orientation
    if (preSource instanceof Port) {
      if (!(preSource instanceof ComponentPort)) {
        return false;
      }
    }
    if (preTarget instanceof Port) {
      if (!(preTarget instanceof ComponentPort)) {
        return false;
      }
    }

    DSemanticDecorator sourcePart = preSourceView_p;
    DSemanticDecorator targetPart = preTargetView_p;

    if ((preSource instanceof Port) && (sourcePart.eContainer() != null) && (sourcePart.eContainer() instanceof AbstractDNode)) {
      sourcePart = (DSemanticDecorator) sourcePart.eContainer();
    }
    if ((preTarget instanceof Port) && (targetPart.eContainer() != null) && (targetPart.eContainer() instanceof AbstractDNode)) {
      targetPart = (DSemanticDecorator) targetPart.eContainer();
    }

    // If source NodeContainer contains target NodeContainer
    if ((sourcePart != null) && (sourcePart instanceof AbstractDNode)
        && ((sourcePart.getTarget() instanceof Part) || (sourcePart.getTarget() instanceof Component))) {
      if ((targetPart != null) && (targetPart instanceof AbstractDNode)
          && ((targetPart.getTarget() instanceof Part) || (targetPart.getTarget() instanceof Component))) {
        if (sourcePart.eContents().contains(targetPart)) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * Returns whether the edge between preSourceView and preTargetView is valid to create a Component Exchange
   */
  public boolean isValidCreationABComponentExchangeWithoutPorts(EObject root_p, DSemanticDecorator preSourceView_p, DSemanticDecorator preTargetView_p) {

    // allow only connection with parts

    boolean sourceValid = false;
    boolean targetValid = false;

    EObject preSource = preSourceView_p.getTarget();
    EObject preTarget = preTargetView_p.getTarget();

    EObject sourceComponent = getComponentType(preSourceView_p);
    EObject targetComponent = getComponentType(preTargetView_p);

    if (preSource == preTarget) {
      return false;
    }

    if (preSource instanceof ComponentPort) {
      sourceValid = false;
    } else if (sourceComponent instanceof Component) {
      sourceValid = true;
    }

    if (preTarget instanceof ComponentPort) {
      targetValid = false;
    } else if (targetComponent instanceof Component) {
      targetValid = true;
    }

    return sourceValid && targetValid;

  }

  public boolean isValidCreationPABComponentExchangeWithoutPorts(EObject container_p, DSemanticDecorator sourceView_p) {

    if ((sourceView_p == null) || (sourceView_p.getTarget() == null)) {
      return false;
    }

    if (sourceView_p.getTarget() instanceof PhysicalPort) {
      return false;
    }

    EObject source = getComponentType(sourceView_p);
    return isAbstractActorOrNotNodeComponent(source);
  }

  /**
   * Returns whether the edge between preSourceView and preTargetView is valid to create a Component Exchange
   */
  public boolean isValidCreationPABComponentExchangeWithoutPorts(EObject root_p, DSemanticDecorator preSourceView_p, DSemanticDecorator preTargetView_p) {
    if (!isValidCreationABComponentExchangeWithoutPorts(root_p, preSourceView_p, preTargetView_p)) {
      return false;
    }

    EObject source = getComponentType(preSourceView_p);
    EObject target = getComponentType(preTargetView_p);

    if ((source == null) || (target == null)) {
      return false;

    } else if ((source instanceof AbstractPhysicalComponent) && (target instanceof AbstractPhysicalComponent)) {
      return (isAbstractActorOrNotNodeComponent(source) && isAbstractActorOrNotNodeComponent(target));
    }
    return false;
  }

  public Component getFirstComponent(ModellingArchitecture architecture_p) {
    return BlockArchitectureExt.getFirstComponent(architecture_p);
  }

  public List<Component> getFirstComponents(ModellingArchitecture architecture_p) {
    return BlockArchitectureExt.getFirstComponents(architecture_p);
  }

  public List<Component> getFirstComponents(ModellingBlock block_p) {
    ArrayList<Component> result = new ArrayList<Component>();
    result.add((Component) block_p);
    return result;
  }

  /**
   * Returns the component related to the element
   */
  public EObject getComponentType(Component element_p) {
    return element_p;
  }

  /**
   * Returns the component related to the element
   */
  public EObject getComponentType(Port element_p) {
    return element_p.eContainer();
  }

  /**
   * Returns the component related to the view
   */
  public EObject getComponentType(DSemanticDecorator targetView_p) {
    if (targetView_p.getTarget() != null) {
      if (targetView_p.getTarget() instanceof Component) {
        return getComponentType((Component) targetView_p.getTarget());
      }
      if (targetView_p.getTarget() instanceof Port) {
        return getComponentType((Port) targetView_p.getTarget());
      }
      if (targetView_p.getTarget() instanceof Part) {
        return getComponentType((Part) targetView_p.getTarget());
      }
      if (targetView_p.getTarget() instanceof AbstractPropertyValue) {
        return getComponentType((DSemanticDecorator) targetView_p.eContainer());
      }
    }
    return null;
  }

  @Deprecated
  // Replaced by getComponentType
  public EObject getRelatedComponent(Part element_p) {
    return element_p.getAbstractType();
  }

  @Deprecated
  // Replaced by getComponentType
  public EObject getRelatedComponent(Port element_p) {
    return element_p.eContainer();
  }

  /**
   * Returns the component related to the element
   */
  public AbstractType getComponentType(Part element_p) {
    return element_p.getAbstractType();
  }

  /**
   * Create a component into the container
   * @param nameVariable_p interpreter-variable which will be store the new created component
   */
  @SuppressWarnings("unchecked")
  public void createComponent(EObject container_p, String nameVariable_p) {
    Component element = null;
    EStructuralFeature containerFeature = null;
    EObject containerObject = null;
    String namePrefix = ""; //$NON-NLS-1$

    if ((container_p instanceof LogicalComponent) || (container_p instanceof LogicalArchitecture)) {

      element = LaFactory.eINSTANCE.createLogicalComponent();
      namePrefix = "LC "; //$NON-NLS-1$

      if (container_p instanceof LogicalComponent) {
        containerFeature = LaPackage.Literals.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENTS;
        containerObject = container_p;

      } else if (container_p instanceof LogicalArchitecture) {
        LogicalComponentPkg pkg = ((LogicalArchitecture) container_p).getOwnedLogicalComponentPkg();
        if (pkg == null) {
          pkg = LaFactory.eINSTANCE.createLogicalComponentPkg();
          ((LogicalArchitecture) container_p).setOwnedLogicalComponentPkg(pkg);
        }

        containerFeature = LaPackage.Literals.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENTS;
        containerObject = pkg;

      }

    } else if ((container_p instanceof PhysicalComponent) || (container_p instanceof PhysicalArchitecture)) {

      element = PaFactory.eINSTANCE.createPhysicalComponent();
      namePrefix = "PC "; //$NON-NLS-1$

      if (container_p instanceof PhysicalComponent) {
        containerFeature = PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS;
        containerObject = container_p;

      } else if (container_p instanceof PhysicalArchitecture) {
        PhysicalComponentPkg pkg = ((PhysicalArchitecture) container_p).getOwnedPhysicalComponentPkg();
        if (pkg == null) {
          pkg = PaFactory.eINSTANCE.createPhysicalComponentPkg();
          ((PhysicalArchitecture) container_p).setOwnedPhysicalComponentPkg(pkg);
        }

        containerFeature = PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_COMPONENTS;
        containerObject = pkg;

      }
    } else if ((container_p instanceof EntityPkg) || (container_p instanceof OperationalAnalysis)) {

      element = OaFactory.eINSTANCE.createEntity();
      namePrefix = "Entity "; //$NON-NLS-1$

      if (container_p instanceof EntityPkg) {
        containerFeature = OaPackage.Literals.ENTITY_PKG__OWNED_ENTITIES;
        containerObject = container_p;

      } else if (container_p instanceof OperationalAnalysis) {
        EntityPkg pkg = ((OperationalAnalysis) container_p).getOwnedEntityPkg();
        if (pkg == null) {
          pkg = OaFactory.eINSTANCE.createEntityPkg();
          ((OperationalAnalysis) container_p).setOwnedEntityPkg(pkg);
        }

        containerFeature = OaPackage.Literals.ENTITY_PKG__OWNED_ENTITIES;
        containerObject = pkg;

      }
    }

    if ((element != null) && (containerObject != null) && (containerFeature != null)) {
      ((EList) containerObject.eGet(containerFeature)).add(element);
      element.setName(CapellaServices.getService().getUniqueName(element, namePrefix));
    }

    if (nameVariable_p != null) {
      InterpreterUtil.getInterpreter(container_p).setVariable(nameVariable_p, element);
    }
  }

  /**
   * Create a logical actor into the container
   * @param nameVariable_p interpreter-variable which will be store the new created actor (create service is not called.)
   */
  public Component createActor(ModellingArchitecture container_p, String nameVariable_p) {
    return createActor(container_p, false, nameVariable_p);
  }

  public Component createActor(ModellingArchitecture container_p) {
    return createActor(container_p, true, null);
  }

  /**
   * Create a logical actor into the container
   * @param nameVariable_p interpreter-variable which will be store the new created actor (create service is not called.)
   */
  @SuppressWarnings("unchecked")
  public Component createActor(ModellingArchitecture container_p, boolean creationService, String nameVariable_p) {
    Component element = null;
    EStructuralFeature containerFeature = null;
    EObject containerObject = null;
    String namePrefix = ""; //$NON-NLS-1$

    if (container_p instanceof LogicalArchitecture) {

      element = LaFactory.eINSTANCE.createLogicalActor();
      namePrefix = "LA "; //$NON-NLS-1$

      LogicalActorPkg pkg = ((LogicalArchitecture) container_p).getOwnedLogicalActorPkg();
      if (pkg == null) {
        pkg = LaFactory.eINSTANCE.createLogicalActorPkg();
        ((LogicalArchitecture) container_p).setOwnedLogicalActorPkg(pkg);
      }

      containerFeature = LaPackage.Literals.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTORS;
      containerObject = pkg;

    } else if (container_p instanceof PhysicalArchitecture) {

      element = PaFactory.eINSTANCE.createPhysicalActor();
      namePrefix = "PA "; //$NON-NLS-1$

      PhysicalActorPkg pkg = ((PhysicalArchitecture) container_p).getOwnedPhysicalActorPkg();
      if (pkg == null) {
        pkg = PaFactory.eINSTANCE.createPhysicalActorPkg();
        ((PhysicalArchitecture) container_p).setOwnedPhysicalActorPkg(pkg);
      }

      containerFeature = PaPackage.Literals.PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTORS;
      containerObject = pkg;

    } else if (container_p instanceof SystemAnalysis) {

      element = CtxFactory.eINSTANCE.createActor();
      namePrefix = "A "; //$NON-NLS-1$

      ActorPkg pkg = ((SystemAnalysis) container_p).getOwnedActorPkg();
      if (pkg == null) {
        pkg = CtxFactory.eINSTANCE.createActorPkg();
        ((SystemAnalysis) container_p).setOwnedActorPkg(pkg);
      }

      containerFeature = CtxPackage.Literals.ACTOR_PKG__OWNED_ACTORS;
      containerObject = pkg;

    } else if (container_p instanceof OperationalAnalysis) {

      element = OaFactory.eINSTANCE.createOperationalActor();
      namePrefix = "OperationalActor "; //$NON-NLS-1$

      EntityPkg pkg = ((OperationalAnalysis) container_p).getOwnedEntityPkg();
      if (pkg == null) {
        pkg = OaFactory.eINSTANCE.createEntityPkg();
        ((OperationalAnalysis) container_p).setOwnedEntityPkg(pkg);
      }
      Entity root = null;
      if (pkg.getOwnedEntities().isEmpty()) {
        root = OaFactory.eINSTANCE.createEntity();
        pkg.getOwnedEntities().add(root);
      } else {
        root = pkg.getOwnedEntities().get(0);
      }
      containerFeature = OaPackage.Literals.ENTITY_PKG__OWNED_ENTITIES;
      containerObject = pkg;

    }

    if ((element != null) && (containerObject != null) && (containerFeature != null)) {
      ((EList) containerObject.eGet(containerFeature)).add(element);

      if (creationService) {
        CapellaServices.getService().creationService(element);
      }

      element.setName(CapellaServices.getService().getUniqueName(element, namePrefix));
    }

    if (nameVariable_p != null) {
      InterpreterUtil.getInterpreter(container_p).setVariable(nameVariable_p, element);
    }
    return element;
  }

  /**
   * Return Customized label for component
   * @param property_p : component
   * @return : customized lable for component
   */
  public String computeComponentLabel(EObject component_p) {
    return EObjectLabelProviderHelper.getText(component_p);
  }

  public String computePartLabelMultiPartOnly(Part part_p) {
    if (isMultipartMode(part_p)) {
      return computePartLabel(part_p);
    }
    return ICommonConstants.EMPTY_STRING;
  }

  public String computePartLabel(Part part_p) {

    if (isMultipartMode(part_p)) {
      String mul = InformationServices.getService().multiplicityToStringDisplay(part_p);

      NumericValue ownedMaxCard = part_p.getOwnedMaxCard();
      NumericValue ownedMinCard = part_p.getOwnedMinCard();
      if ((ownedMinCard == null) && (ownedMaxCard == null)) {
        return getDefaultKindLabel(part_p);
      } else if (getCardValue(part_p, ownedMinCard).equalsIgnoreCase("1") //$NON-NLS-1$
                 && getCardValue(part_p, ownedMaxCard).equalsIgnoreCase("1")) {//$NON-NLS-1$
        return getDefaultKindLabel(part_p);
      } else if (getCardValue(part_p, ownedMinCard).equalsIgnoreCase(ICommonConstants.EMPTY_STRING)
                 && getCardValue(part_p, ownedMaxCard).equalsIgnoreCase(ICommonConstants.EMPTY_STRING)) {
        return getDefaultKindLabel(part_p);
      }

      return mul + ICommonConstants.WHITE_SPACE_CHARACTER + EObjectLabelProviderHelper.getText(part_p);
    }

    if (part_p.getName().length() == 0) {
      return "[" + part_p.eClass().getName() + "]"; //$NON-NLS-2$ //$NON-NLS-1$
    }

    return part_p.getName();
  }

  public String computePartLabelMultiPartMode(Part part_p) {

    if (isMultipartMode(part_p)) {
      String mul = InformationServices.getService().multiplicityToStringDisplay(part_p);

      NumericValue ownedMaxCard = part_p.getOwnedMaxCard();
      NumericValue ownedMinCard = part_p.getOwnedMinCard();
      if ((ownedMinCard == null) && (ownedMaxCard == null)) {
        return getDefaultKindLabel(part_p);
      } else if (getCardValue(part_p, ownedMinCard).equalsIgnoreCase("1") //$NON-NLS-1$
                 && getCardValue(part_p, ownedMaxCard).equalsIgnoreCase("1")) {//$NON-NLS-1$
        return getDefaultKindLabel(part_p);
      } else if (getCardValue(part_p, ownedMinCard).equalsIgnoreCase(ICommonConstants.EMPTY_STRING)
                 && getCardValue(part_p, ownedMaxCard).equalsIgnoreCase(ICommonConstants.EMPTY_STRING)) {
        return getDefaultKindLabel(part_p);
      }

      return mul + ICommonConstants.WHITE_SPACE_CHARACTER + EObjectLabelProviderHelper.getText(part_p);
    }

    String result = ICommonConstants.EMPTY_STRING;
    if ((part_p.getAbstractType() != null) && (part_p.getAbstractType() instanceof ConfigurationItem)) {
      ConfigurationItem type = (ConfigurationItem) part_p.getAbstractType();
      result += "[" + type.getKind().getName() + "] "; //$NON-NLS-1$ //$NON-NLS-2$
    }
    if (part_p.getName().length() == 0) {
      result += "[" + part_p.eClass().getName() + "]"; //$NON-NLS-2$ //$NON-NLS-1$
    } else {
      result += part_p.getName();
    }
    return result;
  }

  private String getDefaultKindLabel(Part part_p) {
    return ICommonConstants.WHITE_SPACE_CHARACTER + EObjectLabelProviderHelper.getText(part_p);
  }

  private String getCardValue(Part part_p, NumericValue card_p) {
    return InformationServices.getService().getCardValue(card_p);
  }

  /**
   * Returns targets for the LAB_ComponentExchangeByGroup mapping. (ie all connected parts)
   * @param source_p
   * @return
   */
  public Collection<EObject> getComponentExchangeByGroupTargets(EObject source_p) {
    Collection<EObject> semantics = new HashSet<EObject>();

    EObject related = source_p;
    if (related instanceof Part) {
      Part sourcePart = (Part) related;
      Component sourceComponent = (Component) ((Part) related).getAbstractType();

      Collection<ComponentExchange> relatedExchanges = new HashSet<ComponentExchange>();
      relatedExchanges.addAll(ComponentExt.getAllRelatedComponentExchange(sourcePart));
      relatedExchanges.addAll(ComponentExt.getAllRelatedComponentExchange(sourceComponent, false));

      for (ComponentExchange relatedExchange : relatedExchanges) {
        if (!ComponentExchangeKind.DELEGATION.equals(relatedExchange.getKind())) {
          EObject source = getSourcePart(relatedExchange);
          if (source == null) {
            semantics.addAll(ComponentExt.getRepresentingParts(ComponentExchangeExt.getSourceComponent(relatedExchange)));
          } else {
            semantics.add(source);
          }

          EObject target = getTargetPart(relatedExchange);
          if (target == null) {
            semantics.addAll(ComponentExt.getRepresentingParts(ComponentExchangeExt.getTargetComponent(relatedExchange)));
          } else {
            semantics.add(source);
          }
        }
      }
    }

    semantics.remove(related);
    return semantics;
  }

  /**
   * Returns all component exchange for mapping LAB_ComponentExchangeByGroup
   */
  public Collection<CapellaElement> getComponentExchangeByGroupSemantics(EObject source_p) {

    Object sourceView = getInterpreterVariable(source_p, IInterpreterSiriusVariables.SOURCE_VIEW);
    Object targetView = getInterpreterVariable(source_p, IInterpreterSiriusVariables.TARGET_VIEW);

    Part sourcePart = (Part) ((DSemanticDecorator) sourceView).getTarget();
    Part targetPart = (Part) ((DSemanticDecorator) targetView).getTarget();

    Component sourceComponent = (Component) sourcePart.getAbstractType();
    Component targetComponent = (Component) targetPart.getAbstractType();

    // Retrieve all related component exchange from source
    Collection<CapellaElement> sources = new ArrayList<CapellaElement>();
    for (CapellaElement element : ComponentExt.getAllRelatedComponentExchange(sourcePart)) {
      if (!sources.contains(element)) {
        sources.add(element);
      }
    }
    for (CapellaElement element : ComponentExt.getAllRelatedComponentExchange(sourceComponent, false)) {
      if (!sources.contains(element)) {
        sources.add(element);
      }
    }

    // Retrieve all related component exchange from target
    Collection<CapellaElement> targets = new ArrayList<CapellaElement>();
    for (CapellaElement element : ComponentExt.getAllRelatedComponentExchange(targetPart)) {
      if (!targets.contains(element)) {
        targets.add(element);
      }
    }
    for (CapellaElement element : ComponentExt.getAllRelatedComponentExchange(targetComponent, false)) {
      if (!targets.contains(element)) {
        targets.add(element);
      }
    }

    // compute intersection
    sources.retainAll(targets);

    // ordering is required since we use the crossReferencer to retrieve elements => hash dependent
    List<CapellaElement> target2 = new ArrayList<CapellaElement>(sources);
    Collections.sort(target2, getComparator());
    return target2;
  }

  /**
   * Returns all component exchange for mapping xAB_ComponentExchangeByGroup_Oriented associated semantic elements. Returns the outgoing componentExchanges
   */
  public Collection<CapellaElement> getComponentExchangeByGroupOrientedSemanticElts(final EObject source_p) {

    Collection<CapellaElement> componentExchanges = getComponentExchangeByGroupSemantics(source_p);
    Predicate<EObject> isPartSourceForCE = new Predicate<EObject>() {

      @Override
      public boolean apply(EObject eObj) {
        if (eObj instanceof ComponentExchange) {
          ComponentExchange ce = (ComponentExchange) eObj;
          // TODO: check this
          Part sourcePart = (Part) source_p;

          ComponentPort cpSource = ComponentExchangeExt.getAttachingPort(sourcePart, ce);
          ComponentPort cpTarget = (ComponentPort) ComponentExchangeExt.getOppositePort(ce, cpSource);

          if (!((cpTarget instanceof ComponentPort) && (cpSource instanceof ComponentPort))) {
            return false;
          }

          if (PortExt.isInStrict(cpSource)) {
            return false;
          }
          if (PortExt.isOutStrict(cpTarget)) {
            return false;
          }
        }
        return true;
      }
    };

    return Lists.newArrayList(Iterables.filter(componentExchanges, isPartSourceForCE));

  }

  /**
   * Returns target for mapping LAB_ComponentExchangeByGroup
   */
  public EObject getComponentExchangeByGroupTarget(EObject related_p) {
    return related_p;
  }

  public boolean isValidComponentExchangeByGroupOrientedEdge(EObject semantic, DSemanticDecorator source_p, DSemanticDecorator target_p) {

    // Retrieve the first correct semantic element between both elements
    Collection<CapellaElement> result = getComponentExchangeByGroupOrientedSemanticElts(source_p.getTarget());
    if (result.isEmpty()) {
      return false;
    }
    CapellaElement firstCE = result.iterator().next();

    if (firstCE instanceof ComponentExchange) {
      Part part = getSourcePart((ComponentExchange) firstCE);
      Part viewPart = ((Part) source_p.getTarget());
      if (part != null) {
        if (!part.equals(viewPart)) {
          return false;
        }
      }
    }

    if (source_p != null) {
      DDiagram diagram = CapellaServices.getService().getDiagramContainer(source_p);
      // check the activation of the filters
      if (diagram != null) {
        for (FilterDescription filter : diagram.getActivatedFilters()) {
          if (IMappingNameConstants.HIDE_CE_BY_GROUP_ORIENTED.equals(filter.getName())) {
            if (isFirstFilterActive(filter, diagram)) {
              return false;
            }
          }
        }
      }
    }

    return true;
  }

  /**
   * Returns whether the mapping LAB_ComponentExchangeByGroup should be displayed between both views. (avoid any double links, source>target and target>source)
   * @param communication_p
   * @param source_p
   * @param target_p
   * @return
   */
  public boolean isValidComponentExchangeByGroupEdge(EObject communication_p, DSemanticDecorator source_p, DSemanticDecorator target_p) {

    EObject semantic = communication_p;
    // Retrieve the first correct semantic element between both elements
    Collection<CapellaElement> result = getComponentExchangeByGroupSemantics(source_p.getTarget());
    semantic = result.iterator().next();

    if (semantic instanceof ComponentExchange) {
      Part part = getSourcePart((ComponentExchange) semantic);
      Part viewPart = ((Part) source_p.getTarget());
      if (part != null) {
        if (!part.equals(viewPart)) {
          return false;
        }
      } else {
        Component cps = ComponentExchangeExt.getSourceComponent((ComponentExchange) semantic);
        EObject type = viewPart.getAbstractType();
        if ((cps != null) && !cps.equals(type)) {
          return false;
        }
      }

    }

    if (source_p != null) {
      DDiagram diagram = CapellaServices.getService().getDiagramContainer(source_p);
      // check the activation of the filters
      if (diagram != null) {
        for (FilterDescription filter : diagram.getActivatedFilters()) {
          if (IMappingNameConstants.HIDE_CE_BY_GROUP.equals(filter.getName())) {
            if (isFirstFilterActive(filter, diagram)) {
              return false;
            }
          }
        }
      }
    }

    return true;
  }

  public boolean isFirstFilterActive(FilterDescription filter, DDiagram diagram) {
    if (filter instanceof CompositeFilterDescription) {
      CompositeFilterDescription cF = (CompositeFilterDescription) filter;
      if (cF.getFilters() != null) {
        for (Filter internalFilter : cF.getFilters()) {
          if ((internalFilter instanceof MappingFilter) && (internalFilter.getFilterKind() == FilterKind.HIDE_LITERAL)) {
            MappingFilter hF = (MappingFilter) internalFilter;
            if (hF.getMappings() != null) {
              for (DiagramElementMapping mapping : hF.getMappings()) {
                for (DEdge edge : diagram.getEdges()) {
                  if (mapping.equals(edge.getActualMapping())) {
                    return false;
                  }
                }
              }
            }
          }
        }
      }
    }
    return true;
  }

  /**
   * Returns whether the edge is the edge which should be displayed for a Connection.
   */
  public boolean isValidComponentExchangeByDelegationEdge(EObject communication_p, DSemanticDecorator source_p, DSemanticDecorator target_p) {

    if (!(communication_p instanceof ComponentPort)) {
      return false;
    }

    if (source_p == target_p) {
      return false;
    }

    if ((source_p.eContainer() == null) || (target_p.eContainer() == null)) {
      return false;
    }

    Collection<? extends EObject> semantics = getComponentExchangeByDelegationSemantics(communication_p);

    // We needs to recompute this, sirius make supposition, if no semanticElements, semanticElements = target...
    if (semantics.size() == 0) {
      return false;
    }

    if (source_p != null) {
      DDiagram diagram = CapellaServices.getService().getDiagramContainer(source_p);
      // check the activation of the filters
      if (diagram != null) {
        for (FilterDescription filter : diagram.getActivatedFilters()) {
          if (IMappingNameConstants.HIDE_CE_BY_DELEGATION.equals(filter.getName())) {
            if (isFirstFilterActive(filter, diagram)) {
              return false;
            }
          }
        }
      }
    }

    if (source_p instanceof EdgeTarget) {
      EdgeTarget view = (EdgeTarget) source_p;
      EObject viewTarget = source_p.getTarget();
      if (viewTarget instanceof ComponentPort) {
        ComponentPort sourcePort = (ComponentPort) viewTarget;

        // Model based (for further)
        boolean isValid = true;

        Collection<ComponentPort> delegating = PortExt.getAllDelegatingComponentPorts(sourcePort);
        EObject parent = view.eContainer().eContainer();
        while (isValid && (parent != null) && (parent instanceof DNodeContainer)) {
          DNodeContainer pNode = (DNodeContainer) parent;
          for (DNode border : pNode.getOwnedBorderedNodes()) {
            if (border == target_p) {
              return true;
            }
            if (delegating.contains(border.getTarget())) {
              isValid = false;
            }
          }
          parent = parent.eContainer();
        }

        if (!isValid) {
          return false;
        }
      }
    }

    if (target_p instanceof EdgeTarget) {
      EdgeTarget view = (EdgeTarget) target_p;
      EObject viewTarget = target_p.getTarget();
      if (viewTarget instanceof ComponentPort) {
        ComponentPort sourcePort = (ComponentPort) viewTarget;

        // Model based (for further)

        boolean isValid = true;

        Collection<ComponentPort> delegating = PortExt.getAllDelegatingComponentPorts(sourcePort);
        EObject parent = view.eContainer().eContainer();
        while (isValid && (parent != null) && (parent instanceof DNodeContainer)) {
          DNodeContainer pNode = (DNodeContainer) parent;
          for (DNode border : pNode.getOwnedBorderedNodes()) {
            if (border == source_p) {
              return true;
            }
            if (delegating.contains(border.getTarget())) {
              isValid = false;
            }
          }
          parent = parent.eContainer();
        }

        if (!isValid) {
          return false;
        }
      }
    }

    return isUndoublonLink(source_p, target_p);// isValidLinkEdge(link, source_p, target_p, true);
  }

  /**
   * Returns whether the edge is the edge which should be displayed for a ComponentExchange.
   * @deprecated Should be replaced by isValidComponentExchangeEdge
   */
  @Deprecated
  public boolean isValidConnectionEdge(ComponentExchange communication_p, DSemanticDecorator source_p, DSemanticDecorator target_p) {
    return isValidComponentExchangeEdge(communication_p, source_p, target_p);
  }

  /**
   * Returns whether the edge is the edge which should be displayed for a Connection.
   */
  public boolean isValidComponentExchangeEdge(EObject communication_p, DSemanticDecorator source_p, DSemanticDecorator target_p) {
    if (communication_p instanceof ComponentExchange) {
      return isValidLinkEdge(getComponentExchangeWrapper((ComponentExchange) communication_p), source_p, target_p, true);
    }
    return false;
  }

  /**
   * Returns whether the edge is the edge which should be displayed for a PortAllocation.
   */
  public boolean isValidPortAllocationEdge(PortAllocation communication_p, DSemanticDecorator source_p, DSemanticDecorator target_p) {
    return isValidLinkEdge(getPortAllocationWrapper(communication_p), source_p, target_p, true);
  }

  /**
   * Returns whether the edge is the edge which should be displayed for a PortAllocation.
   */
  public boolean isValidComponentPortAllocationEdge(ComponentPortAllocation communication_p, DSemanticDecorator source_p, DSemanticDecorator target_p) {
    return isValidLinkEdge(getComponentPortAllocationWrapper(communication_p), source_p, target_p, true);
  }

  /**
   * Returns whether the edge is the edge which should be displayed for an Interaction.
   */
  public boolean isValidInteractionEdge(FunctionalExchange communication_p, DSemanticDecorator source_p, DSemanticDecorator target_p) {
    return isValidLinkEdge(getFunctionalExchangeWrapper(communication_p), source_p, target_p, true);
  }

  /**
   * Returns whether the edge is the edge which should be displayed for a Functional Exchange.
   */
  public boolean isValidFunctionalExchangeEdge(FunctionalExchange communication_p, DSemanticDecorator source_p, DSemanticDecorator target_p) {
    return isValidLinkEdge(getFunctionalExchangeWrapper(communication_p), source_p, target_p, true);
  }

  /**
   * Returns whether the edge is the edge which should be displayed for a Connection.
   */
  public boolean isValidPhysicalLinkEdge(PhysicalLink link_p, DSemanticDecorator source_p, DSemanticDecorator target_p) {
    return isValidLinkEdge(getPhysicalLinkWrapper(link_p), source_p, target_p, true);
  }

  /**
   * Adds some common getters to Physical Link or a Connection
   */
  interface AbstractLink {

    public EObject getSource();

    public EObject getTarget();

    public Port getSourcePort();

    public Port getTargetPort();

    public Collection<? extends Port> getSourcePorts();

    public Collection<? extends Port> getTargetPorts();

    public Part getSourcePart();

    public Part getTargetPart();

    public ComponentExchangeKind getKind();

    public ModelElement getData();

    public void setData(EObject object_p);

  }

  /**
   * Describes an accessor for PhysicalLink
   */
  private class PhysicalLinkWrapper implements AbstractLink {

    private PhysicalLink link;

    PhysicalLinkWrapper() {
      super();
    }

    @Override
    public EObject getSource() {
      if (link.getLinkEnds().size() > 0) {
        return link.getLinkEnds().get(0);
      }
      return null;
    }

    @Override
    public EObject getTarget() {
      if (link.getLinkEnds().size() > 1) {
        return link.getLinkEnds().get(1);
      }
      return null;
    }

    @Override
    public Port getSourcePort() {
      return PhysicalLinkExt.getSourcePort(link);
    }

    @Override
    public Port getTargetPort() {
      return PhysicalLinkExt.getTargetPort(link);
    }

    @Override
    public Collection<? extends Port> getSourcePorts() {
      return Collections.singletonList(getSourcePort());
    }

    @Override
    public Collection<? extends Port> getTargetPorts() {
      return Collections.singletonList(getTargetPort());
    }

    @Override
    public Part getSourcePart() {
      return PhysicalLinkExt.getSourcePart(link);
    }

    @Override
    public Part getTargetPart() {
      return PhysicalLinkExt.getTargetPart(link);
    }

    @Override
    public ComponentExchangeKind getKind() {
      return null;
    }

    @Override
    public ModelElement getData() {
      return link;
    }

    @Override
    public void setData(EObject obj_p) {
      if (obj_p instanceof PhysicalLink) {
        link = (PhysicalLink) obj_p;
      }
    }
  }

  /**
   * Describes an accessor for ComponentExchange
   */
  private class ComponentExchangeWrapper implements AbstractLink {

    private ComponentExchange componentExchange;

    ComponentExchangeWrapper() {
      super();
    }

    @Override
    public EObject getSource() {
      return componentExchange.getSource();
    }

    @Override
    public EObject getTarget() {
      return componentExchange.getTarget();
    }

    @Override
    public Port getSourcePort() {
      return ComponentExchangeExt.getSourcePort(componentExchange);
    }

    @Override
    public Port getTargetPort() {
      return ComponentExchangeExt.getTargetPort(componentExchange);
    }

    @Override
    public Collection<? extends Port> getSourcePorts() {
      Port source = getSourcePort();
      if ((source != null) && (source instanceof ComponentPort)) {
        return PortExt.getAllLinkedDelegatedComponentPorts((ComponentPort) source);
      }
      return Collections.singletonList(getSourcePort());
    }

    @Override
    public Collection<? extends Port> getTargetPorts() {
      Port source = getTargetPort();
      if ((source != null) && (source instanceof ComponentPort)) {
        return PortExt.getAllLinkedDelegatedComponentPorts((ComponentPort) source);
      }
      return Collections.singletonList(getTargetPort());
    }

    @Override
    public Part getSourcePart() {
      return ComponentExchangeExt.getSourcePart(componentExchange);
    }

    @Override
    public Part getTargetPart() {
      return ComponentExchangeExt.getTargetPart(componentExchange);
    }

    @Override
    public ComponentExchangeKind getKind() {
      return componentExchange.getKind();
    }

    @Override
    public ModelElement getData() {
      return componentExchange;
    }

    @Override
    public void setData(EObject obj_p) {
      if (obj_p instanceof ComponentExchange) {
        componentExchange = (ComponentExchange) obj_p;
      }
    }

  }

  /**
   * Describes an accessor for Connection
   */
  private class PortAllocationWrapper implements AbstractLink {

    private Allocation exchange;

    PortAllocationWrapper() {
      super();
    }

    @Override
    public EObject getSource() {
      return exchange.getSourceElement();
    }

    @Override
    public EObject getTarget() {
      return exchange.getTargetElement();
    }

    @Override
    public Port getSourcePort() {
      if (exchange.getSourceElement() instanceof Port) {
        return (Port) exchange.getSourceElement();
      }
      return null;
    }

    @Override
    public Port getTargetPort() {
      if (exchange.getTargetElement() instanceof Port) {
        return (Port) exchange.getTargetElement();
      }
      return null;
    }

    @Override
    public Collection<? extends Port> getSourcePorts() {
      return Collections.singletonList(getSourcePort());
    }

    @Override
    public Collection<? extends Port> getTargetPorts() {
      return Collections.singletonList(getTargetPort());
    }

    @Override
    public Part getSourcePart() {
      return null;
    }

    @Override
    public Part getTargetPart() {
      return null;
    }

    @Override
    public ModelElement getData() {
      return exchange;
    }

    @Override
    public void setData(EObject obj_p) {
      if (obj_p instanceof Allocation) {
        exchange = (Allocation) obj_p;
      }
    }

    /**
     * @see org.polarsys.capella.core.sirius.analysis.CsServices.AbstractLink#getKind()
     */
    @Override
    public ComponentExchangeKind getKind() {
      return null;
    }

  }

  /**
   * Describes an accessor for Connection
   */
  private class ComponentPortAllocationWrapper implements AbstractLink {

    private Allocation exchange;

    ComponentPortAllocationWrapper() {
      super();
    }

    @Override
    public EObject getSource() {
      return exchange.getSourceElement();
    }

    @Override
    public EObject getTarget() {
      return exchange.getTargetElement();
    }

    @Override
    public Port getSourcePort() {
      if (exchange.getSourceElement() instanceof Port) {
        return (Port) exchange.getSourceElement();

      } else if (exchange.getSourceElement() instanceof ComponentPortAllocationEnd) {
        return ((ComponentPortAllocationEnd) exchange.getSourceElement()).getPort();
      }
      return null;
    }

    @Override
    public Port getTargetPort() {
      if (exchange.getTargetElement() instanceof Port) {
        return (Port) exchange.getTargetElement();
      } else if (exchange.getTargetElement() instanceof ComponentPortAllocationEnd) {
        return ((ComponentPortAllocationEnd) exchange.getTargetElement()).getPort();
      }

      return null;
    }

    @Override
    public Collection<? extends Port> getSourcePorts() {
      return Collections.singletonList(getSourcePort());
    }

    @Override
    public Collection<? extends Port> getTargetPorts() {
      return Collections.singletonList(getTargetPort());
    }

    @Override
    public Part getSourcePart() {
      return null;
    }

    @Override
    public Part getTargetPart() {
      return null;
    }

    @Override
    public ModelElement getData() {
      return exchange;
    }

    @Override
    public void setData(EObject obj_p) {
      if (obj_p instanceof Allocation) {
        exchange = (Allocation) obj_p;
      }
    }

    /**
     * @see org.polarsys.capella.core.sirius.analysis.CsServices.AbstractLink#getKind()
     */
    @Override
    public ComponentExchangeKind getKind() {
      return null;
    }

  }

  /**
   * Describes an accessor for Connection
   */
  private class FunctionalExchangeWrapper implements AbstractLink {

    private FunctionalExchange exchange;

    FunctionalExchangeWrapper() {
      super();
    }

    @Override
    public EObject getSource() {
      return exchange.getSource();
    }

    @Override
    public EObject getTarget() {
      return exchange.getTarget();
    }

    @Override
    public Port getSourcePort() {
      if (exchange.getSource() instanceof Port) {
        return (Port) exchange.getSource();
      }
      return null;
    }

    @Override
    public Port getTargetPort() {
      if (exchange.getTarget() instanceof Port) {
        return (Port) exchange.getTarget();
      }
      return null;
    }

    @Override
    public Collection<Port> getSourcePorts() {
      return Collections.singletonList(getSourcePort());
    }

    @Override
    public Collection<Port> getTargetPorts() {
      return Collections.singletonList(getTargetPort());
    }

    @Override
    public Part getSourcePart() {
      return null;
    }

    @Override
    public Part getTargetPart() {
      return null;
    }

    @Override
    public ModelElement getData() {
      return exchange;
    }

    @Override
    public void setData(EObject obj_p) {
      if (obj_p instanceof FunctionalExchange) {
        exchange = (FunctionalExchange) obj_p;
      }
    }

    /**
     * @see org.polarsys.capella.core.sirius.analysis.CsServices.AbstractLink#getKind()
     */
    @Override
    public ComponentExchangeKind getKind() {
      return null;
    }

  }

  private ComponentExchangeWrapper connectionWrapper = null;

  private FunctionalExchangeWrapper functionalExchangeWrapper = null;

  private PortAllocationWrapper portAllocationWrapper = null;

  private ComponentPortAllocationWrapper componentPortAllocationWrapper = null;

  AbstractLink getPortAllocationWrapper(Allocation object_p) {
    if (portAllocationWrapper == null) {
      portAllocationWrapper = new PortAllocationWrapper();
    }
    portAllocationWrapper.setData(object_p);
    return portAllocationWrapper;
  }

  AbstractLink getComponentPortAllocationWrapper(Allocation object_p) {
    if (componentPortAllocationWrapper == null) {
      componentPortAllocationWrapper = new ComponentPortAllocationWrapper();
    }
    componentPortAllocationWrapper.setData(object_p);
    return componentPortAllocationWrapper;
  }

  private PhysicalLinkWrapper linkWrapper = null;

  AbstractLink getComponentExchangeWrapper(ComponentExchange object_p) {
    if (connectionWrapper == null) {
      connectionWrapper = new ComponentExchangeWrapper();
    }
    connectionWrapper.setData(object_p);
    return connectionWrapper;
  }

  AbstractLink getPhysicalLinkWrapper(PhysicalLink object_p) {
    if (linkWrapper == null) {
      linkWrapper = new PhysicalLinkWrapper();
    }
    linkWrapper.setData(object_p);
    return linkWrapper;
  }

  AbstractLink getFunctionalExchangeWrapper(FunctionalExchange object_p) {
    if (functionalExchangeWrapper == null) {
      functionalExchangeWrapper = new FunctionalExchangeWrapper();
    }
    functionalExchangeWrapper.setData(object_p);
    return functionalExchangeWrapper;
  }

  public boolean isOrientationAllowed(EObject context_p) {
    Port sourcePort = getSourcePort(context_p);
    Port targetPort = getTargetPort(context_p);
    if ((null != sourcePort) && (null != targetPort)) {
      if ((sourcePort instanceof ComponentPort) && (targetPort instanceof ComponentPort)) {
        if ((((ComponentPort) sourcePort).getKind() == ComponentPortKind.STANDARD) && (((ComponentPort) targetPort).getKind() == ComponentPortKind.STANDARD)) {
          return true;
        }
      }
    }
    return false;
  }

  public Port getSourcePort(EObject context_p) {
    if ((context_p != null) && (context_p instanceof ComponentExchange)) {
      AbstractLink linkConnection = new ComponentExchangeWrapper();
      linkConnection.setData(context_p);
      return linkConnection.getSourcePort();
    } else if ((context_p != null) && (context_p instanceof PhysicalLink)) {
      AbstractLink linkConnection = new PhysicalLinkWrapper();
      linkConnection.setData(context_p);
      return linkConnection.getSourcePort();
    }
    return null;
  }

  public Port getTargetPort(EObject context_p) {
    if ((context_p != null) && (context_p instanceof ComponentExchange)) {
      AbstractLink linkConnection = new ComponentExchangeWrapper();
      linkConnection.setData(context_p);
      return linkConnection.getTargetPort();
    } else if ((context_p != null) && (context_p instanceof PhysicalLink)) {
      AbstractLink linkConnection = new PhysicalLinkWrapper();
      linkConnection.setData(context_p);
      return linkConnection.getTargetPort();
    }
    return null;
  }

  /**
   * Returns whether the edge is the edge which should be displayed for a Connection. The strict parameter check if the source and the target view is strictly
   * source and target bound of the edge or if they are containers of bound of the edge. (check if the edge can be valid if the bounds (contained in source or
   * target view) were correctly settled)
   */
  public boolean isValidLinkEdge(AbstractLink link_p, DSemanticDecorator source_p, DSemanticDecorator target_p, boolean strict_p) {

    boolean valid = false;
    DSemanticDecorator sourceElement = source_p;
    DSemanticDecorator targetElement = target_p;

    if (link_p.getData() == null) {
      return false;
    }

    if (link_p.getData().eContainer() == null) {
      return false;
    }

    if (strict_p) {

      // Remove edge between part whereas connection is between port
      if ((link_p.getSource() instanceof Port) && (sourceElement.getTarget() instanceof Part)) {
        return false;
      }
      if ((link_p.getTarget() instanceof Port) && (targetElement.getTarget() instanceof Part)) {
        return false;
      }

      // Remove edge between part or port if not the good port
      if (link_p.getSourcePort() != null) {
        if (link_p.getSourcePort() != sourceElement.getTarget()) {
          return false;
        }
      }
      if (link_p.getTargetPort() != null) {
        if (link_p.getTargetPort() != targetElement.getTarget()) {
          return false;
        }
      }
    }

    if (sourceElement.getTarget() instanceof Port) {
      sourceElement = (DSemanticDecorator) sourceElement.eContainer();
    }
    if (targetElement.getTarget() instanceof Port) {
      targetElement = (DSemanticDecorator) targetElement.eContainer();
    }

    // Remove edge between port if port is on another part
    if ((link_p.getSourcePart() != null) && (link_p.getSourcePart() != sourceElement.getTarget())) {
      return false;
    }
    if ((link_p.getTargetPart() != null) && (link_p.getTargetPart() != targetElement.getTarget())) {
      return false;
    }

    // hide the edge if the communication is not contained in on of the containerView.ancestor of the source
    valid = true;

    // Check delegation edges
    if (link_p.getKind() == ComponentExchangeKind.DELEGATION) {
      return targetElement.eContainer() == sourceElement;
    }

    if (!isMultipartMode(link_p.getData()) && !(BlockArchitectureExt.getRootBlockArchitecture(link_p.getData()) instanceof OperationalAnalysis)) {
      return valid;
    }

    return isUndoublonLink(source_p, target_p);

  }

  /**
   * @param sourceElement_p
   * @param targetElement_p
   * @return
   */
  private boolean isUndoublonLink(DSemanticDecorator source_p, DSemanticDecorator target_p) {
    DSemanticDecorator sourceElement = source_p;
    DSemanticDecorator targetElement = target_p;

    boolean valid = true;
    if (sourceElement.getTarget() instanceof Port) {
      sourceElement = (DSemanticDecorator) sourceElement.eContainer();
    }
    if (targetElement.getTarget() instanceof Port) {
      targetElement = (DSemanticDecorator) targetElement.eContainer();
    }

    // hide the edge if there is not the same node in the view of the part of the same type of the source
    LinkedList<EObject> sourceParents = getParents(source_p, DiagramPackage.Literals.DDIAGRAM);
    LinkedList<EObject> targetParents = getParents(target_p, DiagramPackage.Literals.DDIAGRAM);

    sourceParents.remove(sourceElement);
    targetParents.remove(targetElement);

    // Retrieve the first parent of source which is not common with the target
    HashSet<EObject> targetParentSet = new HashSet<EObject>(targetParents);

    EObject current = null;
    if (!sourceParents.isEmpty()) {
      current = sourceParents.removeLast();
      while ((current != null) && targetParentSet.contains(current)) {
        if (sourceParents.size() > 0) {
          current = sourceParents.removeLast();
        } else {
          current = null;
        }
      }
    }
    if (current != null) {
      if (current instanceof DDiagramElement) {
        for (DSemanticDecorator child : DiagramServices.getDiagramServices().getDiagramElements((DDiagramElement) current)) {
          if ((child.getTarget() != null) && child.getTarget().equals(targetElement.getTarget())) {
            valid = false;
          }
        }
      }
    } else {
      valid = true;
    }

    return valid;
  }

  /**
   * Returns semantics candidate in a LCB diagram
   */
  public Collection<Component> getLCBSemanticCandidates(Component element_p) {
    return getSubDefinedByUsedComponents(element_p);
  }

  public EObject getLCBPartSource(EObject element) {
    if (element instanceof Part) {
      return ((Part) element).getAbstractType();
    }
    return null;
  }

  public List<EObject> getLCBPartTarget(EObject element) {
    List<EObject> result = new ArrayList<EObject>();

    if ((element instanceof Part) && (((Part) element).getAbstractType() != null)) {
      for (Partition part : ((PartitionableElement) (((Part) element).getAbstractType())).getRepresentingPartitions()) {
        if (part instanceof Part) {
          result.add(getParentContainer(part));
        }
      }
    }

    return result;
  }

  public List<EObject> getLCBPartSemanticElements(EObject element_p) {
    List<EObject> result = new ArrayList<EObject>();
    result.add(element_p);
    return result;
  }

  public List<EObject> getLCBPartSemanticCandidates(EObject element_p) {
    List<EObject> result = new ArrayList<EObject>();
    if (element_p instanceof Component) {
      result.addAll(ComponentExt.getSubParts((Component) element_p));
    }
    return result;
  }

  /**
   * Returns the sub-defined components which are defined in sub-used components
   */
  public Collection<Component> getSubDefinedByUsedComponents(Component component_p) {
    LinkedList<Component> toVisit = new LinkedList<Component>();
    Collection<Component> components = new HashSet<Component>();
    toVisit.add(component_p);

    while (toVisit.size() > 0) {
      Component visited = toVisit.removeFirst();
      components.add(visited);
      for (Component c : ComponentExt.getSubDefinedComponents(visited)) {
        toVisit.addLast(c);
      }
      for (Part part : getParts(visited, CsPackage.Literals.COMPONENT, null)) {
        EObject type = getComponentType(part);
        if (type instanceof Component) {
          toVisit.addLast((Component) type);
        }
      }
    }

    components.remove(component_p);
    return components;
  }

  /**
   * Returns a linkedList of parents with the bottom-to-top order
   * @param clazz_p the eClass which will stop the browsing. (the eClass of the parent of the object which will not be included in the list)
   */
  protected LinkedList<EObject> getParents(EObject object_p, EClass clazz_p) {
    LinkedList<EObject> parents = new LinkedList<EObject>();
    EObject current = object_p;

    if (current == null) {
      return parents;
    }

    current = current.eContainer();

    while ((current != null) && !clazz_p.isInstance(current)) {
      parents.addLast(current);
      current = current.eContainer();
    }
    return parents;
  }

  /**
   * Returns the sources of a ComponentExchange (the component, the part, or the port according to the mode)
   */
  public Collection<? extends EObject> getComponentExchangeSources(ComponentExchange connection_p) {
    Collection<? extends EObject> source = getComponentExchangeWrapper(connection_p).getSourcePorts();
    if (source == null) {
      source = Collections.singletonList(getComponentExchangeWrapper(connection_p).getSourcePart());
    }
    return source;
  }

  public String getComponentExchangeLabelByDelegation(DSemanticDecorator view_p) {

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(view_p);
    Collection<EObject> sems = ((DRepresentationElement) view_p).getSemanticElements();
    Collection<ComponentExchange> exchanges = new ArrayList<ComponentExchange>();

    for (EObject semantic : sems) {
      if (semantic instanceof ComponentExchange) {
        if (((ComponentExchange) semantic).getKind() == ComponentExchangeKind.DELEGATION) {
          return " "; //$NON-NLS-1$;
        }
        exchanges.add((ComponentExchange) semantic);
      }
    }
    StringBuilder result = new StringBuilder();

    int index = 0;
    for (ComponentExchange exchange : exchanges) {
      index++;
      result.append(FaServices.getFaServices().getComponentExchangeLabel(exchange, diagram));
      if (index < exchanges.size()) {
        result.append(", "); //$NON-NLS-1$
      }
    }
    return result.toString();
  }

  public String getComponentExchangeLabelByDelegationOriented(DSemanticDecorator view_p) {

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(view_p);
    Collection<EObject> sems = ((DRepresentationElement) view_p).getSemanticElements();
    Collection<ComponentExchange> exchanges = Lists.newArrayList();

    for (EObject semantic : sems) {
      if (semantic instanceof ComponentExchange) {
        if (((ComponentExchange) semantic).getKind() == ComponentExchangeKind.DELEGATION) {
          return " "; //$NON-NLS-1$;
        }
        exchanges.add((ComponentExchange) semantic);

      }

    }
    StringBuilder result = new StringBuilder();

    int index = 0;
    for (ComponentExchange exchange : exchanges) {
      index++;
      result.append(FaServices.getFaServices().getComponentExchangeLabel(exchange, diagram));
      if (index < exchanges.size()) {
        result.append(", "); //$NON-NLS-1$
      }
    }
    return result.toString();
  }

  public String getComponentExchangeLabelByDelegationReversed(DSemanticDecorator view_p) {

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(view_p);
    Collection<EObject> sems = ((DRepresentationElement) view_p).getSemanticElements();
    Collection<ComponentExchange> exchanges = Lists.newArrayList();

    Part part = (Part) view_p.getTarget();
    PhysicalComponent physicalComponent = (PhysicalComponent) part.getAbstractType();

    for (EObject semantic : sems) {

      if (semantic instanceof ComponentExchange) {
        if (((ComponentExchange) semantic).getKind() == ComponentExchangeKind.DELEGATION) {
          return " "; //$NON-NLS-1$;
        }
        InformationsExchanger port = ((ComponentExchange) semantic).getSource();
        EObject ownerComponent = port.eContainer();

        if (!physicalComponent.equals(ownerComponent)) {
          exchanges.add((ComponentExchange) semantic);
        }
      }
    }
    StringBuilder result = new StringBuilder();

    int index = 0;
    for (ComponentExchange exchange : exchanges) {
      index++;
      result.append(FaServices.getFaServices().getComponentExchangeLabel(exchange, diagram));
      if (index < exchanges.size()) {
        result.append(", "); //$NON-NLS-1$
      }
    }
    return result.toString();
  }

  public Collection<ComponentPort> getAllLinkedDelegatedComponentPorts(ComponentPort port_p) {
    return PortExt.getAllLinkedDelegatedComponentPorts(port_p);
  }

  public List<ComponentExchange> getAssemblyComponentExchanges(ComponentPort port_p) {
    List<ComponentExchange> res = PortExt.getAssemblyComponentExchanges(port_p);

    return res;
  }

  public List<ComponentExchange> getFlowComponentExchanges(ComponentPort port_p) {
    List<ComponentExchange> res = PortExt.getFlowComponentExchanges(port_p);

    return res;
  }

  public Collection<CapellaElement> getAllRelatedLinks(Port port_p) {
    Collection<CapellaElement> target = new ArrayList<CapellaElement>();

    if (port_p instanceof ComponentPort) {

      for (Port relatedPort : getAllLinkedDelegatedComponentPorts((ComponentPort) port_p)) {
        if (relatedPort instanceof ComponentPort) {
          for (ComponentExchange exchange : getAssemblyComponentExchanges((ComponentPort) relatedPort)) {
            if (!target.contains(exchange)) {
              target.add(exchange);
            }
          }
          for (ComponentExchange exchange : getFlowComponentExchanges((ComponentPort) relatedPort)) {
            if (!target.contains(exchange)) {
              target.add(exchange);
            }
          }
        }
      }
    }
    return target;
  }

  public Collection<? extends EObject> getComponentExchangeByDelegationSemantics(EObject related_p) {

    Object sourceView = getInterpreterVariable(related_p, IInterpreterSiriusVariables.SOURCE_VIEW);
    Object targetView = getInterpreterVariable(related_p, IInterpreterSiriusVariables.TARGET_VIEW);

    return getComponentExchangeByDelegationSemantics(related_p, sourceView, targetView);

  }

  public Collection<? extends EObject> getComponentExchangeByDelegationSemantics(EObject related_p, Object sourceView, Object targetView) {

    Collection<CapellaElement> target = new ArrayList<CapellaElement>();

    if ((sourceView != null) && (sourceView instanceof EdgeTarget) && (targetView != null) && (targetView instanceof EdgeTarget)) {

      EdgeTarget sourceNode = (EdgeTarget) sourceView;
      EdgeTarget targetNode = (EdgeTarget) targetView;

      if ((sourceNode instanceof DSemanticDecorator) && (targetNode instanceof DSemanticDecorator)) {
        EObject sourcePort = ((DSemanticDecorator) sourceNode).getTarget();
        EObject targetPort = ((DSemanticDecorator) targetNode).getTarget();

        if ((sourcePort != null) && (targetPort != null) && (sourcePort instanceof Port) && (targetPort instanceof Port)) {
          Collection<CapellaElement> sourceLinks = getAllRelatedLinks((Port) sourcePort);

          Collection<CapellaElement> targetLinks = getAllRelatedLinks((Port) targetPort);
          sourceLinks.retainAll(targetLinks);

          target = sourceLinks;

          // retrains with related parts (for multipart mode)
          Collection<EObject> a = new ArrayList<EObject>();

          Iterator<? extends EObject> it = target.iterator();
          while (it.hasNext()) {
            EObject ce = it.next();
            if (ce instanceof ComponentExchange) {
              ComponentExchange cee = (ComponentExchange) ce;
              Part sourcePart = getSourcePart(cee);
              Part targetPart = getTargetPart(cee);
              if (sourcePart != null) {
                if (getRelatedPart((DSemanticDecorator) sourceNode) != sourcePart) {
                  a.add(ce);
                }
              }
              if (targetPart != null) {
                if (getRelatedPart((DSemanticDecorator) targetNode) != targetPart) {
                  a.add(ce);
                }
              }
            }
          }

          target.removeAll(a);
        }
      }
    }

    // ordering is required since we use the crossReferencer to retrieve elements => hash dependent
    List<CapellaElement> target2 = new ArrayList<CapellaElement>(target);
    Collections.sort(target2, getComparator());
    return target2;

  }

  public Part getSourcePart(ComponentExchange connection_p) {
    return ComponentExchangeExt.getSourcePart(connection_p);
  }

  public Part getTargetPart(ComponentExchange connection_p) {
    return ComponentExchangeExt.getTargetPart(connection_p);
  }

  public Port getSourcePort(ComponentExchange connection_p) {
    return ComponentExchangeExt.getSourcePort(connection_p);
  }

  public Port getTargetPort(ComponentExchange connection_p) {
    return ComponentExchangeExt.getTargetPort(connection_p);
  }

  public EObject getComponentExchangeByDelegationTarget(EObject related_p) {

    if (related_p instanceof ComponentPort) {
      return related_p;
    } else if (related_p instanceof ComponentExchange) {
      return getComponentExchangeByDelegationTarget(((ComponentExchange) related_p).getSource());
    }

    return related_p;
  }

  /**
   * Returns the targets of a ComponentExchange (the component, the part, or the port according to the mode)
   */
  public Collection<? extends EObject> getComponentExchangeLowestTargets(EObject related_p) {
    Collection<ComponentPort> target = new ArrayList<ComponentPort>();
    EObject related = related_p;
    if (related instanceof ComponentPort) {
      ComponentPort port = (ComponentPort) related;

      // We retrieve all ports connected to the current port
      for (ComponentPort connectedPort : PortExt.getConnectedComponentPortsWithoutDelegation(port)) {
        if (!target.contains(connectedPort)) {
          target.add(connectedPort);
        }
      }

      // We retrieve all lowest-delegated ports connected to a component exchange of any CE linked to delegated-ing ports.
      Collection<ComponentPort> delegateds = PortExt.getAllLinkedDelegatedComponentPorts(port);
      delegateds.remove(port);
      for (Port relatedPort : delegateds) {
        if (relatedPort instanceof ComponentPort) {
          for (Port connectedPort : PortExt.getConnectedComponentPortsWithoutDelegation((ComponentPort) relatedPort)) {
            Collection<ComponentPort> ports = PortExt.getAllDelegatedComponentPorts((ComponentPort) connectedPort);
            ports.add((ComponentPort) connectedPort);
            for (ComponentPort delegatedPort : ports) {
              if (PortExt.getDelegatedComponentPorts(delegatedPort).size() == 0) {
                if (!target.contains(delegatedPort)) {
                  target.add(delegatedPort);
                }
              }
            }
          }
        }
      }

      // We remove all ports which are already delegated by a result port.
      Iterator<ComponentPort> objects = target.iterator();
      while (objects.hasNext()) {
        ComponentPort result = objects.next();
        Collection<ComponentPort> resultPort = PortExt.getDelegatingComponentPorts(result);
        resultPort.retainAll(target);
        if (resultPort.size() != 0) {
          objects.remove();
        }
      }
    }
    return target;

  }

  /**
   * Returns the targets of a ComponentExchange (the component, the part, or the port according to the mode)
   */
  public Collection<? extends EObject> getComponentExchangeByDelegationTargets(EObject related_p) {
    Collection<CapellaElement> target = new ArrayList<CapellaElement>();
    EObject related = related_p;

    if (related instanceof ComponentPort) {
      ComponentPort port = (ComponentPort) related;
      for (Port relatedPort : PortExt.getAllLinkedDelegatedComponentPorts(port)) {
        if (relatedPort instanceof ComponentPort) {
          for (ComponentExchange exchange : PortExt.getAssemblyComponentExchanges((ComponentPort) relatedPort)) {
            Collection<? extends Port> a = getComponentExchangeWrapper(exchange).getTargetPorts();
            if (!a.contains(related)) {
              for (Port aPort : a) {
                if (!target.contains(aPort)) {
                  target.add(aPort);
                }
              }
            }
          }
          for (ComponentExchange exchange : PortExt.getFlowComponentExchanges((ComponentPort) relatedPort)) {
            Collection<? extends Port> a = getComponentExchangeWrapper(exchange).getTargetPorts();
            if (!a.contains(related)) {
              for (Port aPort : a) {
                if (!target.contains(aPort)) {
                  target.add(aPort);
                }
              }
            }
          }
        }
      }

    } else if (related instanceof ComponentExchange) {
      // Should be called only with a sirius regression
      return getComponentExchangeByDelegationTargets(((ComponentExchange) related).getSource());
    }

    return target;
  }

  /**
   * Returns the source of a ComponentExchange (the component, the part, or the port according to the mode)
   */
  public EObject getComponentExchangeSource(ComponentExchange connection_p) {
    EObject source = getComponentExchangeWrapper(connection_p).getSourcePort();
    if (source == null) {
      source = getComponentExchangeWrapper(connection_p).getSourcePart();
    }
    return source;
  }

  /**
   * Returns the source of a ComponentExchange (the component, the part, or the port according to the mode)
   */
  public EObject getComponentPortAllocationSource(ComponentPortAllocation connection_p) {
    EObject source = getComponentPortAllocationWrapper(connection_p).getSourcePort();
    if (source == null) {
      source = getComponentPortAllocationWrapper(connection_p).getSourcePart();
    }
    return source;
  }

  /**
   * Returns the source of a ComponentExchange (the component, the part, or the port according to the mode)
   */
  public EObject getComponentPortAllocationTarget(ComponentPortAllocation connection_p) {
    EObject source = getComponentPortAllocationWrapper(connection_p).getTargetPort();
    if (source == null) {
      source = getComponentPortAllocationWrapper(connection_p).getTargetPart();
    }
    return source;
  }

  /**
   * Returns the target of a ComponentExchange (the component, the part, or the port according to the mode)
   */
  public EObject getComponentExchangeTarget(ComponentExchange connection_p) {
    EObject target = getComponentExchangeWrapper(connection_p).getTargetPort();
    if (target == null) {
      target = getComponentExchangeWrapper(connection_p).getTargetPart();
    }
    return target;
  }

  /**
   * Returns the source of a PortCommunication (the component, the part, or the port according to the mode)
   */
  public EObject getPhysicalLinkSource(PhysicalLink connection_p) {
    return getPhysicalLinkWrapper(connection_p).getSourcePort();
  }

  /**
   * Returns the target of a PortCommunication (the component, the part, or the port according to the mode)
   */
  public EObject getPhysicalLinkTarget(PhysicalLink connection_p) {
    return getPhysicalLinkWrapper(connection_p).getTargetPort();
  }

  public Comparator<CapellaElement> getComparator() {
    return new Comparator<CapellaElement>() {
      @Override
      public int compare(CapellaElement o1_p, CapellaElement o2_p) {
        if ((o1_p == null) && (o2_p == null)) {
          return 0;
        } else if ((o1_p == null) || (o1_p.getId() == null)) {
          return -1;
        } else if ((o2_p == null) || (o2_p.getId() == null)) {
          return 1;
        }
        if ((o1_p instanceof AbstractNamedElement) && (o2_p instanceof AbstractNamedElement)) {
          AbstractNamedElement a1 = (AbstractNamedElement) o1_p;
          AbstractNamedElement a2 = (AbstractNamedElement) o2_p;
          if ((a1.getName() != null) && (a2.getName() != null)) {
            return a1.getName().compareTo(a2.getName());
          }
        }
        return o1_p.getId().compareTo(o2_p.getId());
      }
    };
  }

  /**
   * Returns all semantics candidates for a ComponentExchange link
   */
  public Collection<CapellaElement> getComponentExchangeSemantics(EObject element_p) {
    Collection<CapellaElement> list = new ArrayList<CapellaElement>();

    if (element_p instanceof Component) {
      list.addAll(ComponentExt.getAllOwnedComponentExchanges((Component) element_p));

    } else if (element_p instanceof BlockArchitecture) {
      list.addAll(BlockArchitectureExt.getAllComponentExchanges((BlockArchitecture) element_p));
    }

    return list;
  }

  /**
   * Returns semantic elements which can be related of a namedElement
   */
  public List<EObject> getPartSemanticElements(NamedElement element_p) {
    List<EObject> elements = new ArrayList<EObject>();

    if (element_p instanceof Part) {
      elements.add(element_p);
      elements.add(((Part) element_p).getAbstractType());

    } else if (element_p instanceof Component) {
      elements.add(element_p);
      elements.addAll(((Component) element_p).getRepresentingPartitions());
    }
    return elements;
  }

  /**
   * Returns allocated logical functions for the given part
   */
  public List<AbstractFunction> getAllocatedFunctions(Part part_p) {
    if (part_p.getAbstractType() instanceof Component) {
      return getAllocatedFunctions(((Component) part_p.getAbstractType()));
    }
    return null;
  }

  /**
   * Returns allocated logical functions for the given component
   */
  public List<AbstractFunction> getAllocatedFunctions(Component component_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>();

    for (ComponentFunctionalAllocation alloc : component_p.getFunctionalAllocations()) {
      if (alloc.getFunction() != null) {
        list.add(alloc.getFunction());
      }
      if ((alloc.getFunction() != null) && (alloc.getFunction().eContainer() != null) && (alloc.getFunction().eContainer() instanceof AbstractFunction)) {
        list.add((AbstractFunction) alloc.getFunction().eContainer());
      }
    }
    return list;
  }

  /**
   * Returns actors which should be inserted into the architecture of the component in the LAB diagram
   */
  public Collection<? extends Component> getABInsertActor(Component component_p) {
    Collection<? extends Component> components = new HashSet<Component>();

    // OLD CODE
    BlockArchitecture architecture = getArchitecture(component_p);

    if (architecture instanceof SystemAnalysis) {
      ActorPkg pkg = ((SystemAnalysis) architecture).getOwnedActorPkg();
      if (pkg != null) {
        components = ActorPkgExt.getAllActors(pkg);
      }
    } else if (architecture instanceof LogicalArchitecture) {
      LogicalActorPkg pkg = ((LogicalArchitecture) architecture).getOwnedLogicalActorPkg();
      if (pkg != null) {
        components = ActorPkgExt.getAllActors(pkg);
      }
    } else if (architecture instanceof PhysicalArchitecture) {
      PhysicalActorPkg pkg = ((PhysicalArchitecture) architecture).getOwnedPhysicalActorPkg();
      if (pkg != null) {
        components = ActorPkgExt.getAllActors(pkg);
      }
    }

    if (!isMultipartMode(architecture)) {
      Component context = getContext(architecture);

      if ((context != null) && (components != null)) {
        // Remove component from existing part
        components.removeAll(getSubUsedComponents(getContext(architecture)));
      }
    }

    // NEW CODE
    components = (List) QueryDebugger.executeQueryWithInclusionDebug(QueryIdentifierConstants.GET_AB_INSERT_ACTOR_FOR_LIB, component_p, components);
    // END CODE REFACTOR

    return components;
  }

  /**
   * Returns components which can be inserted into the given component in the LAB Diagram
   */
  public Collection<? extends Component> getABInsertComponent(Component component_p) {
    // OLD CODE
    Collection<Component> components = new java.util.HashSet<Component>();

    // Add components accessible by namespace
    components.addAll(ComponentExt.getAvailableComponentsByNamespace(component_p));

    if (!isMultipartMode(component_p)) {
      // Remove component from existing part
      components.removeAll(getSubUsedComponents(component_p));
    }

    // Remove current component and remove all containers of current component
    components.remove(component_p);
    components.removeAll(getParentContainers(component_p));

    if (component_p instanceof PhysicalComponent) {
      components = filterPhysicalComponentsByNature((PhysicalComponent) component_p, components);
    }

    // NEW CODE
    components = (List) QueryDebugger.executeQueryWithInclusionDebug(QueryIdentifierConstants.GET_CCII_INSERT_COMPONENTS_FOR_LIB, component_p, components);
    // END CODE REFACTOR
    return components;
  }

  public Collection<? extends NamedElement> getABShowHideActor(DSemanticDecorator view_p) {
    Component component = (Component) getComponentType(view_p);
    BlockArchitecture architecture = getArchitecture(component);
    List<Part> parts = getParts(getContext(architecture), CsPackage.Literals.ABSTRACT_ACTOR, null);
    return parts;
  }

  /**
   * Returns owned parts with the given eclass type
   */
  protected List<Part> getParts(PartitionableElement element_p, EClass eClass_p, EClass excludeEClass_p) {
    List<Part> parts = new ArrayList<Part>();
    for (Partition part : ComponentExt.getSubParts(element_p)) {
      if ((part instanceof Part) && eClass_p.isInstance(part.getAbstractType())
          && (!((excludeEClass_p != null) && excludeEClass_p.isInstance(part.getAbstractType())))) {
        parts.add((Part) part);
      }
    }
    return parts;
  }

  Couple<DNode, Boolean> createViewOrGetPort(DNodeContainer parent_p, Port target_p) {
    for (DNode node : parent_p.getOwnedBorderedNodes()) {
      if ((node.getTarget() != null) && node.getTarget().equals(target_p)) {
        return new Couple<DNode, Boolean>(node, Boolean.FALSE);
      }
    }

    DNode created = FaServices.getFaServices().createViewComponentPort(target_p, parent_p, parent_p.getParentDiagram());
    return new Couple<DNode, Boolean>(created, Boolean.TRUE);
  }

  Couple<DNode, Boolean> createViewOrGetPhysicalPort(DNodeContainer parent_p, Port target_p) {
    for (DNode node : parent_p.getOwnedBorderedNodes()) {
      if ((node.getTarget() != null) && node.getTarget().equals(target_p)) {
        return new Couple<DNode, Boolean>(node, Boolean.FALSE);
      }
    }

    DNode created = FaServices.getFaServices().createViewPhysicalPort(target_p, parent_p, parent_p.getParentDiagram());
    return new Couple<DNode, Boolean>(created, Boolean.TRUE);
  }

  /**
   * @param aNode_p
   * @param component_p
   * @return
   */
  Couple<DNodeContainer, Boolean> createViewOrGetPart(DragAndDropTarget parent_p, EObject target_p) {
    List<DDiagramElement> elements = null;

    if (parent_p instanceof DDiagram) {
      elements = ((DDiagram) parent_p).getOwnedDiagramElements();
    } else if (parent_p instanceof DNodeContainer) {
      elements = ((DNodeContainer) parent_p).getOwnedDiagramElements();
    }
    if (elements != null) {
      for (DDiagramElement node : elements) {
        if ((node instanceof DNodeContainer) && (node.getTarget() != null) && node.getTarget().equals(target_p)) {
          return new Couple<DNodeContainer, Boolean>((DNodeContainer) node, Boolean.FALSE);
        }
      }
    }

    DNodeContainer created = FaServices.getFaServices().createViewPart(target_p, parent_p, CapellaServices.getService().getDiagramContainer(parent_p));
    return new Couple<DNodeContainer, Boolean>(created, Boolean.TRUE);
  }

  /**
   * @param aNode_p
   * @param component_p
   * @return
   */
  Couple<DNodeContainer, Boolean> createViewOrGetDeployedPart(DragAndDropTarget parent_p, EObject target_p) {
    List<DDiagramElement> elements = null;

    if (parent_p instanceof DDiagram) {
      elements = ((DDiagram) parent_p).getOwnedDiagramElements();
    } else if (parent_p instanceof DNodeContainer) {
      elements = ((DNodeContainer) parent_p).getOwnedDiagramElements();
    }
    if (elements != null) {
      for (DDiagramElement node : elements) {
        if ((node instanceof DNodeContainer) && (node.getTarget() != null) && node.getTarget().equals(target_p)) {
          return new Couple<DNodeContainer, Boolean>((DNodeContainer) node, Boolean.FALSE);
        }
      }
    }

    DNodeContainer created = FaServices.getFaServices().createViewDeployedPart(target_p, parent_p, CapellaServices.getService().getDiagramContainer(parent_p));
    return new Couple<DNodeContainer, Boolean>(created, Boolean.TRUE);
  }

  public boolean isDeployed(DNodeContainer view_p) {
    return view_p.getMapping().getName().equals(IMappingNameConstants.PAB_PHYSICAL_COMPONENT_DEPLOYMENT_MAPPING_NAME);
  }

  @Deprecated
  /**
   * Such classes should not be used, and should be replaced with org.polarsys.capella.core.sirius.analysis.showhide mechanism.
   * Only problem is on multipart, when a fonction is allocated to a component with multiple part, all parts are revealed even if one of them is already displayed.
   * Same problem when a component exchange is linked to a port of a component with multiple part, all parts are revealed even if one of them is already displayed.
   * It is due to "getRelatedObjects" returning getRepresentingParts.
   */
  private class ShowABComponent extends ShowAbstractABComponent {

    private boolean isDeployed = false;
    Collection<EObject> parts;

    @Deprecated
    public ShowABComponent(Collection<EObject> parts_p, DSemanticDecorator currentElementView_p) {
      super(currentElementView_p);
      this.parts = parts_p;
    }

    @Override
    void showComponents() {
      showParts(parts, componentViews);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean isSourceDeployed() {
      if (!isDeployed) {
        for (DDiagramElement element : DiagramServices.getDiagramServices().getAllContainers(currentDiagram)) {
          if ((element instanceof DNodeContainer) && isDeployed((DNodeContainer) element)) {
            isDeployed = true;
            break;
          }
        }
      }
      return isDeployed;
    }

  }

  @Deprecated
  private abstract class ShowAbstractABComponent {

    DDiagram currentDiagram;
    boolean isPABDiagram = false;
    List<DNodeContainer> newCreated = new ArrayList<DNodeContainer>();
    DSemanticDecorator currentElementView;
    HashMapSet<EObject, DNodeContainer> componentViews = new HashMapSet<EObject, DNodeContainer>(); // entity or part

    @Deprecated
    public ShowAbstractABComponent(DSemanticDecorator currentElementView_p) {
      currentElementView = currentElementView_p;
      currentDiagram = CapellaServices.getService().getDiagramContainer(currentElementView_p);
      isPABDiagram = currentDiagram.getDescription().getName().equals(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME);
    }

    abstract boolean isSourceDeployed();

    void initialization() {

      // find elements in diagram (a part can be displayed more than one time)
      for (DDiagramElement aNode : DiagramServices.getDiagramServices().getDiagramElements(currentDiagram)) {

        if (aNode instanceof DNodeContainer) {
          componentViews.put(aNode.getTarget(), (DNodeContainer) aNode);
        }
      }

    }

    void showComponents() {
      // Nothing here
    }

    /**
     * Display deployed parts of the target bound on available containers
     */
    int displayDeployedBounds(Collection<EObject> components, boolean notify_p) {
      int nbViewsDeployed = 0;

      // for each other parts, if deployed, show the part deployed
      for (DDiagramElement aNode : DiagramServices.getDiagramServices().getDiagramElements(currentDiagram)) {

        if (aNode instanceof DNodeContainer) {
          for (EObject component : components) {
            if ((aNode.getTarget() instanceof Part) && (component instanceof DeployableElement)) {
              Part part = (Part) aNode.getTarget();
              for (AbstractDeploymentLink linka : ((DeployableElement) component).getDeployingLinks()) {
                if (part.equals(linka.getLocation())) {
                  Couple<DNodeContainer, Boolean> couple = createViewOrGetDeployedPart((DNodeContainer) aNode, component);
                  if (notify_p) {
                    notifyCreateFinalView(component, couple.getKey());
                  }
                  componentViews.put(component, couple.getKey());
                  nbViewsDeployed++;
                  if (couple.getValue().booleanValue()) {
                    newCreated.add(couple.getKey());
                  }
                }
              }
            }
          }
        }
      }

      return nbViewsDeployed;
    }

    void showParts(Collection<EObject> components_p, HashMapSet<EObject, DNodeContainer> componentViews_p) {
      // if there is no part displayed (deployed or not) for the other bound of the link,
      // if the bound is deployed but not shown, try to display hierarchically deployed parts.
      // if the bound isn't deployed, try to display hierarchically parts.
      //

      for (EObject component : components_p) {

        int nbViews = 0;
        int nbViewsDeployed = 0;

        for (DNodeContainer view : componentViews_p.get(component)) {
          if (isDeployed(view)) {
            nbViewsDeployed++;
          } else {
            nbViews++;
          }
        }

        if (isPABDiagram && isSourceDeployed() && (nbViewsDeployed == 0) && (nbViews == 0)) {
          List<EObject> locations = new ArrayList<EObject>();
          // display hierarchically containers of deployed parts which are linked to the edge
          locations.addAll(getDeployableLocations((DeployableElement) component));

          displayHierarchical(locations, false);
          // display deployed part linked to the edge on existing containers
          nbViewsDeployed += displayDeployedBounds(Collections.singletonList(component), true);
        }

        if ((nbViews == 0) && (nbViewsDeployed == 0)) {
          // display hierarchically part linked to the edge
          // We were displaying all component_p at the first loop, even if it was already displayed.
          displayHierarchical(Collections.singletonList(component), true);
        }

      }
    }

    void perform() {
      initialization();
      showComponents();
    }

    /**
     * Display the given elements in the diagram, adding hierarchy if necessary. the parameter notify is used to call callback notification
     * notifyCreateFinalView when a creation of a view is performed on one of the element of the given list. (used to know which view will be linked with an
     * edge)
     */
    @SuppressWarnings("unchecked")
    void displayHierarchical(Collection<EObject> elements_p, boolean notify_p) {
      // A ordered set by breakdown
      TreeMapSet<Integer, EObject> parentsResults = new TreeMapSet<Integer, EObject>(new Comparator<Integer>() {

        /**
         * A set ordered by breakdown position. Top level elements first, others on the next
         */
        @Override
        public int compare(Integer arg0_p, Integer arg1_p) {
          return arg1_p.intValue() - arg0_p.intValue();
        }
      });

      // We display any deployed element if possible. In one part mode, we display all the hierarchy of deployable element
      // in multi, only the part which deploy the target.
      LinkedList<Couple<Integer, EObject>> parents = new LinkedList<Couple<Integer, EObject>>();

      for (EObject component : elements_p) {
        parents.addLast(new Couple<Integer, EObject>(Integer.valueOf(0), component));
      }

      if (parents.size() > 0) {

        // disabled to fix bug.
        // This part was retrieving recursively all parents which contains given elements_p. It was adding to much functionality, unwanted by users.
        while (parents.size() > 0) {
          Couple<Integer, EObject> current = parents.removeFirst();
          Integer currentIndex = current.getKey();
          EObject currentElement = current.getValue();

          if (currentElement instanceof Entity) {
            parentsResults.put(currentIndex, currentElement);

          } else if (currentElement instanceof Part) {
            Part part = (Part) currentElement;
            parentsResults.put(currentIndex, part);

            if (isSourceDeployed()) {
              for (AbstractDeploymentLink link : ((DeployableElement) part).getDeployingLinks()) {
                parents.addLast(new Couple<Integer, EObject>(Integer.valueOf(currentIndex.intValue() + 1), link.getLocation()));
              }
            }

          }
        }

        // for each other parts, if deployed, show the part deployed
        for (EObject component : parentsResults.values()) {
          EObject parentComponent = getParentContainer(component);

          // Little test to avoid the case "nothing happens"
          if (parentComponent == null) {
            continue;
          }

          List<EObject> parentDeployings = new ArrayList<EObject>();
          if (component instanceof DeployableElement) {
            // Retrieve all deploying parts only for parent parts
            if (isSourceDeployed() || !elements_p.contains(component)) {
              for (AbstractDeploymentLink loc : ((DeployableElement) component).getDeployingLinks()) {
                parentDeployings.add(loc.getLocation());
              }
            }
          }

          if ((componentViews.get(component).size() == 0) || elements_p.contains(component)) {
            int nbAdded = 0;
            // We create views for deploying parts
            for (EObject partition : parentDeployings) {
              if (componentViews.get(partition).size() == 0) {
                Couple<DNodeContainer, Boolean> couple = createViewOrGetDeployedPart(currentDiagram, component);
                if (notify_p && elements_p.contains(component)) {
                  notifyCreateFinalView(component, couple.getKey());
                }

                componentViews.put(component, couple.getKey());
                nbAdded++;
                if (couple.getValue().booleanValue()) {
                  newCreated.add(couple.getKey());
                }
              }
              for (DNodeContainer aNode : componentViews.get(partition)) {
                Couple<DNodeContainer, Boolean> couple = createViewOrGetDeployedPart(aNode, component);
                componentViews.put(component, couple.getKey());
                if (notify_p && elements_p.contains(component)) {
                  notifyCreateFinalView(component, couple.getKey());
                }
                nbAdded++;
                if (couple.getValue().booleanValue()) {
                  newCreated.add(couple.getKey());
                }
              }
            }

            if (nbAdded == 0) {
              List<? extends EObject> parentParts = Collections.EMPTY_LIST;
              if (parentComponent instanceof Component) {
                parentParts = ((Component) parentComponent).getRepresentingPartitions();
              }
              if (parentParts.size() == 0) {
                Couple<DNodeContainer, Boolean> couple = createViewOrGetPart(currentDiagram, component);
                if (notify_p && elements_p.contains(component)) {
                  notifyCreateFinalView(component, couple.getKey());
                }
                componentViews.put(component, couple.getKey());
                nbAdded++;
                if (couple.getValue().booleanValue()) {
                  newCreated.add(couple.getKey());
                }
              }
              for (EObject partition : parentParts) {
                if (componentViews.get(partition).size() == 0) {
                  Couple<DNodeContainer, Boolean> couple = createViewOrGetPart(currentDiagram, component);
                  if (notify_p && elements_p.contains(component)) {
                    notifyCreateFinalView(component, couple.getKey());
                  }
                  componentViews.put(component, couple.getKey());
                  if (couple.getValue().booleanValue()) {
                    newCreated.add(couple.getKey());
                  }
                }
                for (DNodeContainer aNode : componentViews.get(partition)) {
                  Couple<DNodeContainer, Boolean> couple = createViewOrGetPart(aNode, component);
                  if (notify_p && elements_p.contains(component)) {
                    notifyCreateFinalView(component, couple.getKey());
                  }
                  componentViews.put(component, couple.getKey());
                  if (couple.getValue().booleanValue()) {
                    newCreated.add(couple.getKey());
                  }
                }
              }
            }
          }
        }
      }
    }

    /**
     * Called when the displayed hierarchy create a new view from the list
     */
    protected void notifyCreateFinalView(EObject component_p, DNodeContainer key_p) {
      // Nothing
    }

  }

  @Deprecated
  private abstract class ShowABExchange extends ShowAbstractABComponent {
    @Deprecated
    public ShowABExchange(DSemanticDecorator currentElementView_p) {
      super(currentElementView_p);
    }

    /**
     * Create edges on necessary elements
     */
    void createEdges() {
      //
    }

    @Override
    void perform() {
      super.perform();
      createEdges();
    }

    @Override
    void showParts(Collection<EObject> components_p, HashMapSet<EObject, DNodeContainer> componentViews_p) {
      // if there is no part displayed (deployed or not) for the other bound of the link,
      // if the bound is deployed but not shown, try to display hierarchically deployed parts.
      // if the bound isn't deployed, try to display hierarchically parts.
      //

      int nbViews = 0;
      int nbViewsDeployed = 0;
      for (EObject component : components_p) {
        for (DNodeContainer view : componentViews_p.get(component)) {
          if (isDeployed(view)) {
            nbViewsDeployed++;
          } else {
            nbViews++;
          }
        }
      }

      if (isPABDiagram && isSourceDeployed() && (nbViewsDeployed == 0) && (nbViews == 0)) {
        List<EObject> locations = new ArrayList<EObject>();
        // display hierarchically containers of deployed parts which are linked to the edge
        for (EObject component : components_p) {
          locations.addAll(getDeployableLocations((DeployableElement) component));
        }

        displayHierarchical(locations, false);
        // display deployed part linked to the edge on existing containers
        nbViewsDeployed += displayDeployedBounds(components_p, true);
      }

      if ((nbViews == 0) && (nbViewsDeployed == 0)) {
        // display hierarchically part linked to the edge
        displayHierarchical(components_p, true);

      }

    }

  }

  /**
   * Show an AbtractLink in Architecture Blank
   */
  @Deprecated
  private class ShowABConnection extends ShowABExchange {
    // Principle
    // We Display the connection between the source and the potential targets.
    // The targets are either the visible parts
    // or
    // if they are not present, all the parts that are the target of the connection
    // and which can be displayed
    // We display the "parts/ports/edges" only if the edge of the connection is valid
    // in order to avoid the display of the new parts with edges that will be removed after the refresh.

    AbstractLink link = null;

    Port sourcePort = null;
    Port targetPort = null;
    boolean isSource = false;

    List<EObject> sourceComponents = new ArrayList<EObject>(); // entity or part
    List<EObject> targetComponents = new ArrayList<EObject>(); // entity or part
    HashMapSet<EObject, DNodeContainer> sourceComponentViews = new HashMapSet<EObject, DNodeContainer>(); // entity or part
    HashMapSet<EObject, DNodeContainer> targetComponentViews = new HashMapSet<EObject, DNodeContainer>(); // entity or part

    List<EObject> firstComponents = null; // entity or part
    HashMapSet<EObject, DNodeContainer> firstComponentViews = null;
    List<EObject> othersComponents = null; // entity or part
    HashMapSet<EObject, DNodeContainer> othersComponentViews = null;

    DSemanticDecorator currentContainerView;

    boolean showIfExist;

    @Deprecated
    ShowABConnection(EObject exchangeToShow_p, DSemanticDecorator currentElementView_p) {
      this(exchangeToShow_p, currentElementView_p, true);
    }

    @Deprecated
    ShowABConnection(EObject exchangeToShow_p, DSemanticDecorator currentElementView_p, boolean showIfExist_p) {
      super(currentElementView_p);
      showIfExist = showIfExist_p;
      if (exchangeToShow_p instanceof ComponentExchange) {
        link = getComponentExchangeWrapper((ComponentExchange) exchangeToShow_p);
      } else if (exchangeToShow_p instanceof PhysicalLink) {
        link = getPhysicalLinkWrapper((PhysicalLink) exchangeToShow_p);
      }

      currentContainerView = currentElementView_p;
    }

    @Override
    void initialization() {

      if (link.getSourcePart() != null) {
        sourceComponents.add(link.getSourcePart());
      }
      if (link.getSourcePort() != null) {
        sourcePort = link.getSourcePort();
      }
      if (link.getSource() instanceof Port) {
        Component container = (Component) getParentContainer(sourcePort);
        EList<Partition> representingPartitions = container.getRepresentingPartitions();
        sourceComponents.addAll(representingPartitions);
      }
      if (link.getSource() instanceof Entity) {
        sourceComponents.add(link.getSource());
      }

      if (link.getTargetPart() != null) {
        targetComponents.add(link.getTargetPart());
      }
      if (link.getTargetPort() != null) {
        targetPort = link.getTargetPort();
      }
      if (link.getTarget() instanceof Port) {
        Component container = (Component) getParentContainer(targetPort);
        EList<Partition> representingPartitions = container.getRepresentingPartitions();
        targetComponents.addAll(representingPartitions);
      }
      if (link.getTarget() instanceof Entity) {
        targetComponents.add(link.getTarget());
      }

      // find elements in diagram (a part can be displayed more than one time)
      for (DDiagramElement aNode : DiagramServices.getDiagramServices().getDiagramElements(currentDiagram)) {

        if (aNode instanceof DNodeContainer) {
          componentViews.put(aNode.getTarget(), (DNodeContainer) aNode);

          for (EObject component : sourceComponents) {
            if (component.equals(aNode.getTarget())) {
              sourceComponentViews.put(component, (DNodeContainer) aNode);
            }
          }

          for (EObject component : targetComponents) {
            if (component.equals(aNode.getTarget())) {
              targetComponentViews.put(component, (DNodeContainer) aNode);
            }
          }
        }
      }

      // If the part selected is displayed more than one time, we need to remove
      // others view of the part for the bound where the part is linked.
      // This avoid to show connections starting from others view of the source part.

      if (currentContainerView instanceof DNodeContainer) {

        if (sourceComponentViews.get(currentContainerView.getTarget()).contains(currentContainerView)) {
          sourceComponentViews.remove(currentContainerView.getTarget());
          sourceComponentViews.put(currentContainerView.getTarget(), (DNodeContainer) currentContainerView);
          isSource = true;
        }
        if (!isSource && targetComponentViews.get(currentContainerView.getTarget()).contains(currentContainerView)) {
          targetComponentViews.remove(currentContainerView.getTarget());
          targetComponentViews.put(currentContainerView.getTarget(), (DNodeContainer) currentContainerView);
        }
      }

      if (isSource) {
        firstComponents = sourceComponents;
        firstComponentViews = sourceComponentViews;
        othersComponents = targetComponents;
        othersComponentViews = targetComponentViews;

      } else {
        firstComponents = targetComponents;
        firstComponentViews = targetComponentViews;
        othersComponents = sourceComponents;
        othersComponentViews = sourceComponentViews;
      }

      // Remove all invalid views
      for (EObject target : othersComponentViews.keySet()) {
        for (DNodeContainer targetView : new ArrayList<DNodeContainer>(othersComponentViews.get(target))) {
          boolean isValid = true;
          for (EObject source : firstComponentViews.keySet()) {
            for (DNodeContainer sourceView : new ArrayList<DNodeContainer>(firstComponentViews.get(source))) {
              if (isSource && !isValidLinkEdge(link, sourceView, targetView, false)) {
                isValid = false;
              } else if (!isSource && !isValidLinkEdge(link, targetView, sourceView, false)) {
                isValid = false;
              }
            }
          }
          if (!isValid) {
            othersComponentViews.get(target).remove(targetView);
          }
        }
      }
    }

    boolean showOthers = false;

    @Override
    void showComponents() {
      if (currentContainerView instanceof DDiagram) {
        showOthers = false;
        showParts(firstComponents, firstComponentViews);
      }
      showOthers = true;
      showParts(othersComponents, othersComponentViews);
    }

    @Override
    void createEdges() {

      // Remove all invalid edges and delete all invalid previously created views
      for (EObject target : othersComponentViews.keySet()) {
        for (DNodeContainer targetView : new ArrayList<DNodeContainer>(othersComponentViews.get(target))) {
          boolean isValid = true;
          for (EObject source : firstComponentViews.keySet()) {
            for (DNodeContainer sourceView : new ArrayList<DNodeContainer>(firstComponentViews.get(source))) {
              if (isSource && !isValidLinkEdge(link, sourceView, targetView, false)) {
                isValid = false;
              } else if (!isSource && !isValidLinkEdge(link, targetView, sourceView, false)) {
                isValid = false;
              }
            }
          }
          if (!isValid) {
            othersComponentViews.get(target).remove(targetView);
            if (newCreated.contains(targetView)) {
              DiagramServices.getDiagramServices().removeContainerView(targetView);
            }
          }
        }
      }

      // Create an edge for all parts from source and part from targets
      for (EObject source : sourceComponentViews.keySet()) {
        for (DNodeContainer sourceView : sourceComponentViews.get(source)) {

          Boolean isSourceCreatedAndNotUsed = Boolean.FALSE;
          EdgeTarget sourceEdge = sourceView;
          if (sourcePort != null) {
            Couple<DNode, Boolean> couple = createViewOrGetPort(sourceView, sourcePort);
            sourceEdge = couple.getKey();

            CapellaServices.getService().show((DNode) sourceEdge);
            isSourceCreatedAndNotUsed = couple.getValue();
          }

          for (EObject target : targetComponentViews.keySet()) {
            for (DNodeContainer targetView : targetComponentViews.get(target)) {

              Boolean isTargetCreatedAndNotUsed = Boolean.FALSE;
              EdgeTarget targetEdge = targetView;
              if (targetPort != null) {
                Couple<DNode, Boolean> couple = createViewOrGetPort(targetView, targetPort);
                targetEdge = couple.getKey();
                if (showIfExist) {
                  CapellaServices.getService().show((DNode) targetEdge);
                }
                isTargetCreatedAndNotUsed = couple.getValue();
              }

              if (isValidLinkEdge(link, (DSemanticDecorator) sourceEdge, (DSemanticDecorator) targetEdge, true)) {
                DEdge edge = DiagramServices.getDiagramServices().getEdgeOnDiagram(sourceEdge, targetEdge, link.getData());
                if (edge == null) {
                  if (link.getData() instanceof PhysicalLink) {
                    EdgeMapping edgeMapping = FaServices.getFaServices().getMappingABPhysicalLink(currentDiagram);
                    DiagramServices.getDiagramServices().createEdge(edgeMapping, sourceEdge, targetEdge, link.getData());
                    isSourceCreatedAndNotUsed = Boolean.FALSE;
                  } else {
                    EdgeMapping edgeMapping = FaServices.getFaServices().getMappingABConnection(currentDiagram);
                    DiagramServices.getDiagramServices().createEdge(edgeMapping, sourceEdge, targetEdge, link.getData());
                    isSourceCreatedAndNotUsed = Boolean.FALSE;
                  }
                } else if (showIfExist) {
                  CapellaServices.getService().show(edge);
                }

              }
            }
          }
        }
      }

    }

    @Override
    boolean isSourceDeployed() {
      return (currentContainerView instanceof DDiagram)
             || ((currentContainerView instanceof DNodeContainer) && isDeployed((DNodeContainer) currentContainerView));
    }

    @Override
    protected void notifyCreateFinalView(EObject component_p, DNodeContainer key_p) {
      super.notifyCreateFinalView(component_p, key_p);
      if (showOthers) {
        othersComponentViews.put(component_p, key_p);
      } else {
        firstComponentViews.put(component_p, key_p);
      }
    }

  }

  @Deprecated
  private class ShowABFunctionalExchange extends ShowABExchange {

    DSemanticDecorator currentContainerView;
    FunctionalExchange exchangeToShow;

    Pin sourcePort = null;
    Pin targetPort = null;

    HashMapSet<EObject, EObject> sourceComponents = new HashMapSet<EObject, EObject>(); // component by functions
    HashMapSet<EObject, EObject> targetComponents = new HashMapSet<EObject, EObject>(); // component by functions
    List<EObject> sourceFunctions = new ArrayList<EObject>(); // functions
    List<EObject> targetFunctions = new ArrayList<EObject>(); // functions

    HashMapSet<EObject, DNodeContainer> sourceComponentViews = new HashMapSet<EObject, DNodeContainer>(); // entity or part
    HashMapSet<EObject, DNodeContainer> targetComponentViews = new HashMapSet<EObject, DNodeContainer>(); // entity or part
    HashMapSet<EObject, DNode> sourceFunctionViews = new HashMapSet<EObject, DNode>(); // function
    HashMapSet<EObject, DNode> targetFunctionViews = new HashMapSet<EObject, DNode>(); // function

    List<DNode> newCreatedFunctions = new ArrayList<DNode>();

    // Source and target component and views with the selected part in the first list.
    HashMapSet<EObject, EObject> firstComponents = null; // entity or part
    List<EObject> firstFunctions = null; // functions
    HashMapSet<EObject, DNodeContainer> firstComponentViews = null;
    HashMapSet<EObject, DNode> firstFunctionViews = null;
    HashMapSet<EObject, EObject> othersComponents = null; // entity or part
    List<EObject> othersFunctions = null; // functions
    HashMapSet<EObject, DNodeContainer> othersComponentViews = null;
    HashMapSet<EObject, DNode> othersFunctionViews = null;
    boolean isSource = true;

    @Deprecated
    ShowABFunctionalExchange(FunctionalExchange exchangeToShow_p, DSemanticDecorator currentElementView_p) {
      super(currentElementView_p);

      exchangeToShow = exchangeToShow_p;
      currentContainerView = currentElementView_p;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    void initialization() {
      if (exchangeToShow.getSource() instanceof OperationalActivity) {
        sourceFunctions.add(exchangeToShow.getSource());

      } else {
        sourcePort = (Pin) exchangeToShow.getSource();
        sourceFunctions.add(exchangeToShow.getSource().eContainer());
      }

      if (exchangeToShow.getTarget() instanceof OperationalActivity) {
        targetFunctions.add(exchangeToShow.getTarget());
      } else {
        targetPort = (Pin) exchangeToShow.getTarget();
        targetFunctions.add(exchangeToShow.getTarget().eContainer());
      }

      // Retrieve all parts or entity allocating the source and target functions
      for (EObject function : sourceFunctions) {
        for (AbstractFunctionalBlock block : ((AbstractFunction) function).getAllocationBlocks()) {
          if (block instanceof Entity) {
            sourceComponents.put(function, block);
          } else if (block instanceof PartitionableElement) {
            sourceComponents.putAll(function, (List) ((PartitionableElement) block).getRepresentingPartitions());
          }
        }
      }
      for (EObject function : targetFunctions) {
        for (AbstractFunctionalBlock block : ((AbstractFunction) function).getAllocationBlocks()) {
          if (block instanceof Entity) {
            targetComponents.put(function, block);
          } else if (block instanceof PartitionableElement) {
            targetComponents.putAll(function, (List) ((PartitionableElement) block).getRepresentingPartitions());
          }
        }
      }

      // find elements in diagram (a part can be displayed more than one time)
      for (DDiagramElement aNode : DiagramServices.getDiagramServices().getDiagramElements(currentDiagram)) {

        if (aNode instanceof DNodeContainer) {
          componentViews.put(aNode.getTarget(), (DNodeContainer) aNode);
          for (EObject function : sourceFunctions) {
            for (EObject component : sourceComponents.get(function)) {
              if (component.equals(aNode.getTarget())) {
                sourceComponentViews.put(component, (DNodeContainer) aNode);
              }
            }
          }

          for (EObject function : targetFunctions) {
            for (EObject component : targetComponents.get(function)) {
              if (component.equals(aNode.getTarget())) {
                targetComponentViews.put(component, (DNodeContainer) aNode);
              }
            }
          }
        } else if (aNode instanceof DNode) {
          for (EObject function : sourceFunctions) {
            if (function.equals(aNode.getTarget())) {
              sourceFunctionViews.put(function, (DNode) aNode);
            }
          }

          for (EObject function : targetFunctions) {
            if (function.equals(aNode.getTarget())) {
              targetFunctionViews.put(function, (DNode) aNode);
            }
          }
        }
      }

      if (currentContainerView instanceof DNode) {
        isSource = false;
        // If the function selected is displayed more than one time, we need to remove
        // others view of the function for the bound where the function is linked.
        // This avoid to show connections starting from others view of the source function.

        if (sourceFunctionViews.get(currentContainerView.getTarget()).contains(currentContainerView)) {
          sourceFunctionViews.remove(currentContainerView.getTarget());
          sourceFunctionViews.put(currentContainerView.getTarget(), (DNode) currentContainerView);
          sourceComponentViews.remove(((DSemanticDecorator) currentContainerView.eContainer()).getTarget());
          sourceComponentViews.put(((DSemanticDecorator) currentContainerView.eContainer()).getTarget(), (DNodeContainer) currentContainerView.eContainer());
          isSource = true;
        }
        if (!isSource && targetFunctionViews.get(currentContainerView.getTarget()).contains(currentContainerView)) {
          targetFunctionViews.remove(currentContainerView.getTarget());
          targetFunctionViews.put(currentContainerView.getTarget(), (DNode) currentContainerView);
          targetComponentViews.remove(((DSemanticDecorator) currentContainerView.eContainer()).getTarget());
          targetComponentViews.put(((DSemanticDecorator) currentContainerView.eContainer()).getTarget(), (DNodeContainer) currentContainerView.eContainer());
        }
      }

      if (isSource) {
        firstComponents = sourceComponents;
        firstFunctions = sourceFunctions;
        firstComponentViews = sourceComponentViews;
        firstFunctionViews = sourceFunctionViews;
        othersComponents = targetComponents;
        othersFunctions = targetFunctions;
        othersComponentViews = targetComponentViews;
        othersFunctionViews = targetFunctionViews;

      } else {
        firstComponents = targetComponents;
        firstFunctions = targetFunctions;
        firstComponentViews = targetComponentViews;
        firstFunctionViews = targetFunctionViews;
        othersComponents = sourceComponents;
        othersFunctions = sourceFunctions;
        othersComponentViews = sourceComponentViews;
        othersFunctionViews = sourceFunctionViews;
      }

      // Remove all invalid views
      // Nothing here..

    }

    boolean showOthers = false;

    @Override
    void showComponents() {
      if (currentContainerView instanceof DDiagram) {
        showOthers = false;
        showParts(firstFunctions, firstFunctionViews, firstComponents, firstComponentViews);
      }
      showOthers = true;
      showParts(othersFunctions, othersFunctionViews, othersComponents, othersComponentViews);
    }

    /**
     * Display deployed parts of the target bound on available containers
     */
    int displayDeployedBounds(boolean notify_p, List<EObject> functions_p, HashMapSet<EObject, EObject> components_p) {
      int nbViewsDeployed = 0;

      // for each other parts, if deployed, show the part deployed
      for (DDiagramElement aNode : DiagramServices.getDiagramServices().getDiagramElements(currentDiagram)) {

        if (aNode instanceof DNodeContainer) {
          for (EObject function : functions_p) {
            for (EObject component : components_p.get(function)) {
              if ((aNode.getTarget() instanceof Part) && (component instanceof DeployableElement)) {
                Part part = (Part) aNode.getTarget();
                for (AbstractDeploymentLink link : ((DeployableElement) component).getDeployingLinks()) {
                  if (part.equals(link.getLocation())) {
                    Couple<DNodeContainer, Boolean> couple = createViewOrGetDeployedPart((DNodeContainer) aNode, component);
                    if (notify_p) {
                      notifyCreateFinalView(component, couple.getKey());
                    }
                    componentViews.put(component, couple.getKey());
                    nbViewsDeployed++;
                    if (couple.getValue().booleanValue()) {
                      newCreated.add(couple.getKey());
                    }
                  }
                }
              }
            }
          }
        }
      }

      return nbViewsDeployed;
    }

    @Override
    boolean isSourceDeployed() {
      return (currentContainerView instanceof DDiagram)
             || ((currentContainerView.eContainer() != null) && (currentContainerView.eContainer() instanceof DNodeContainer) && isDeployed((DNodeContainer) currentContainerView
                 .eContainer()));
    }

    /**
     * @param functions_p
     * @param functionViews_p
     * @param components_p
     * @param componentViews_p
     */
    protected void showParts(List<EObject> functions_p, HashMapSet<EObject, DNode> functionViews_p, HashMapSet<EObject, EObject> components_p,
        HashMapSet<EObject, DNodeContainer> componentViews_p) {

      // if there is no function displayed for the other bound of the link, display all available functions
      int nbViewsFunctions = 0;
      int nbViewsComponents = 0;
      int nbViewsDeployed = 0;

      // Counts
      for (EObject function : functions_p) {
        nbViewsFunctions += functionViews_p.get(function).size();

        for (EObject component : components_p.get(function)) {
          for (DNodeContainer view : componentViews_p.get(component)) {
            if (isDeployed(view)) {
              nbViewsDeployed++;
            } else {
              nbViewsComponents++;
            }
          }
        }
      }

      if (nbViewsFunctions == 0) {

        if (isPABDiagram && (nbViewsDeployed == 0) && (nbViewsComponents == 0) && isSourceDeployed()) {

          // display hierarchically containers of deployed parts which allocate functions
          for (EObject function : functions_p) {
            List<EObject> locations = new ArrayList<EObject>();
            for (EObject component : components_p.get(function)) {
              locations.addAll(getDeployableLocations((DeployableElement) component));
            }
            displayHierarchical(locations, false);
          }
          // display all deployed part allocating functions on existing containers
          nbViewsDeployed += displayDeployedBounds(true, functions_p, components_p);
        }

        if (((nbViewsComponents == 0) && (nbViewsDeployed == 0))) {
          // display hierarchically parts allocating functions
          for (EObject function : functions_p) {
            displayHierarchical(components_p.get(function), true);
          }

        }

        // Create a function in all component views
        for (EObject component : componentViews_p.keySet()) {
          for (DNodeContainer componentView : componentViews_p.get(component)) {
            for (EObject function : functions_p) {
              if (components_p.get(function).contains(component)) {
                Couple<DNode, Boolean> couple = createViewOrGetFunction(componentView, (AbstractFunction) function);
                functionViews_p.put(function, couple.getKey());
                if (couple.getValue().booleanValue()) {
                  newCreatedFunctions.add(couple.getKey());
                }
              }
            }
          }
        }
      }

      // Remove all invalid edges and delete all invalid previously created views
      // Nothing here..
    }

    @Override
    protected void notifyCreateFinalView(EObject component_p, DNodeContainer key_p) {
      super.notifyCreateFinalView(component_p, key_p);
      if (showOthers) {
        othersComponentViews.put(component_p, key_p);
      } else {
        firstComponentViews.put(component_p, key_p);
      }
    }

    @Override
    void createEdges() {
      // Create an edge for all functions from source and functions from targets
      for (EObject source : sourceFunctionViews.keySet()) {
        for (DNode sourceView : sourceFunctionViews.get(source)) {

          EdgeTarget sourceEdge = sourceView;
          if (sourcePort != null) {
            Couple<DNode, Boolean> couple = createViewOrGetFunctionPort(sourceView, sourcePort);
            sourceEdge = couple.getKey();
          }

          for (EObject target : targetFunctionViews.keySet()) {
            for (DNode targetView : targetFunctionViews.get(target)) {

              EdgeTarget targetEdge = targetView;
              if (targetPort != null) {
                Couple<DNode, Boolean> couple = createViewOrGetFunctionPort(targetView, targetPort);
                targetEdge = couple.getKey();
              }

              // TODO we must check if there is an edge between both source and target Edges
              if (!DiagramServices.getDiagramServices().isEdgeOnDiagram(sourceEdge, targetEdge, exchangeToShow)) {
                EdgeMapping edgeMapping = FaServices.getFaServices().getMappingABFunctionalExchange(currentDiagram);
                DiagramServices.getDiagramServices().createEdge(edgeMapping, sourceEdge, targetEdge, exchangeToShow);
              }
            }
          }
        }
      }

    }

  }

  public List<EObject> getDeployableLocations(DeployableElement element_p) {

    ArrayList<EObject> parents = new ArrayList<EObject>();

    for (AbstractDeploymentLink linka : element_p.getDeployingLinks()) {
      parents.add(linka.getLocation());
    }
    return parents;
  }

  /**
   * Show related contextual elements into the diagram
   * @param contextualElements_p
   */
  public void showABContextualElements(DDiagram diagram_p) {
    DDiagramContents context = FaServices.getFaServices().getDDiagramContents(diagram_p);
    showABContextualElements(context, ContextualDiagramHelper.getService().getContextualElements(diagram_p));
  }

  /**
   * Show given contextual elements into the diagram A contextual element is an element which will be displayed in the diagram with a lot of related elements
   * such as part linked with a ComponentExchange, PhysicalLink For a contextual functional chain, display all elements of the chain
   * @param contextualElements_p
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void showABContextualElements(DDiagramContents context_p, Collection<EObject> contextualElements_p) {

    DDiagram diagram = context_p.getDDiagram();
    Collection<EObject> contextualParts = new HashSet<EObject>();
    Collection<AbstractFunction> contextualFunctions = new HashSet<AbstractFunction>();
    Collection<FunctionalExchange> contextualFunctionExchanges = new HashSet<FunctionalExchange>();
    Collection<EObject> contextualFunctionalChains = new HashSet<EObject>();
    Collection<EObject> contextualConnections = new HashSet<EObject>();
    Collection<EObject> contextualModes = new HashSet<EObject>();
    Collection<EObject> contextualScenarios = new HashSet<EObject>();

    for (EObject contextualElement : contextualElements_p) {

      if (contextualElement instanceof Entity) {
        contextualParts.add(contextualElement);

      } else if (contextualElement instanceof Component) {
        Collection<Part> parts = ComponentExt.getRepresentingParts((Component) contextualElement);
        contextualParts.addAll(parts);
        for (Part part : parts) {
          contextualConnections.addAll(org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getAllRelatedPhysicalLinks(part));
        }

      } else if (contextualElement instanceof Part) {
        contextualParts.add(contextualElement);
        contextualConnections.addAll(org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getAllRelatedPhysicalLinks((Part) contextualElement));

      } else if (contextualElement instanceof AbstractFunction) {
        contextualFunctions.add((AbstractFunction) contextualElement);

      } else if (contextualElement instanceof FunctionalChain) {

        for (FunctionalChainInvolvement involvement : FunctionalChainExt.getFlatInvolvementsOf((FunctionalChain) contextualElement,
            FaPackage.Literals.ABSTRACT_FUNCTION)) {
          contextualFunctions.add((AbstractFunction) involvement.getInvolved());
        }
        for (FunctionalChainInvolvement involvement : FunctionalChainExt.getFlatInvolvementsOf((FunctionalChain) contextualElement,
            FaPackage.Literals.FUNCTIONAL_EXCHANGE)) {
          contextualFunctionExchanges.add((FunctionalExchange) involvement.getInvolved());
        }
        contextualFunctionalChains.add(contextualElement);

      } else if (contextualElement instanceof Scenario) {
        contextualScenarios.add(contextualElement);

      } else if (contextualElement instanceof State) {
        contextualModes.add(contextualElement);

      }

    }

    // Retrieve all related parts connected by a component exchange
    for (EObject contextualPart : contextualParts) {
      for (ComponentExchange flow : ABServices.getService().getRelatedComponentExchanges(contextualPart)) {
        if (flow.getKind() != ComponentExchangeKind.DELEGATION) {
          contextualConnections.add(flow);
        }
      }
    }

    // Show a lot of things
    CsServices.getService().showABComponent((Collection) contextualParts, (DSemanticDecorator) diagram);
    CsServices.getService().showABFunctionalExchange((Collection) contextualFunctionExchanges, (DSemanticDecorator) diagram);
    CsServices.getService().showABComponentExchange(contextualConnections, (DSemanticDecorator) diagram);
    FaServices.getFaServices().showABFunctionalChains(diagram, contextualFunctionalChains, context_p);
    ABServices.getService().showABScenarios((DSemanticDecorator) diagram, contextualScenarios);
    ABServices.getService().showABStateModes((DSemanticDecorator) diagram, contextualModes);
  }

  /**
   * used in context, logical, oa, physical
   */
  @Deprecated
  public EObject showABComponentExchange(Collection<EObject> exchanges_p, DSemanticDecorator currentElementView_p) {
    for (EObject exchange : exchanges_p) {
      new ShowABConnection(exchange, currentElementView_p, false).perform();
    }
    return currentElementView_p;
  }

  /**
   */
  public EObject showABComponentExchange(EObject exchange_p, DSemanticDecorator currentElementView_p) {
    new ShowABConnection(exchange_p, currentElementView_p).perform();
    return currentElementView_p;
  }

  /**
   * used in context, logical, oa, physical
   */
  public EObject showConnectionInArchitectureBlank(EObject exchangeToShow_p, DSemanticDecorator currentElementView_p) {
    return showABComponentExchange(exchangeToShow_p, currentElementView_p);
  }

  /**
   * Display a component in the best container (display deployment in their deploying component) used in context, logical, oa, physical
   */
  @Deprecated
  public EObject showABComponent(Collection<EObject> components_p, DSemanticDecorator diagram_p) {
    new ShowABComponent(components_p, diagram_p).perform();
    return diagram_p;
  }

  /**
   * Display a functional exchange and bounds if necessary used in context, logical, oa, physical
   */
  @Deprecated
  public EObject showABFunctionalExchange(Collection<FunctionalExchange> exchanges_p, DSemanticDecorator currentElementView_p) {
    for (FunctionalExchange exchange : exchanges_p) {
      new ShowABFunctionalExchange(exchange, currentElementView_p).perform();
    }
    return currentElementView_p;
  }

  /**
   * Display a functional exchange and bounds if necessary used in context, logical, oa, physical
   */
  @Deprecated
  public EObject showABFunctionalExchange(FunctionalExchange exchangeToShow_p, DSemanticDecorator currentElementView_p) {
    new ShowABFunctionalExchange(exchangeToShow_p, currentElementView_p).perform();
    return currentElementView_p;
  }

  /**
   * Display a functional exchange and bounds if necessary used in context, logical, oa, physical
   */
  public EObject showFunctionalExchangeInArchitectureBlank2(FunctionalExchange exchangeToShow_p, DSemanticDecorator currentElementView_p) {
    return showABFunctionalExchange(exchangeToShow_p, currentElementView_p);
  }

  /**
   * @param componentView_p
   * @param function_p
   * @return
   */
  Couple<DNode, Boolean> createViewOrGetFunction(DNodeContainer parent_p, AbstractFunction target_p) {
    for (DDiagramElement node : parent_p.getOwnedDiagramElements()) {
      if ((node instanceof DNode) && (node.getTarget() != null) && node.getTarget().equals(target_p)) {
        return new Couple<DNode, Boolean>((DNode) node, Boolean.FALSE);
      }
    }

    DNode created = FaServices.getFaServices().createViewABAbstractFunction(target_p, parent_p, parent_p.getParentDiagram());
    return new Couple<DNode, Boolean>(created, Boolean.TRUE);
  }

  /**
   * @param sourceView_p
   * @param sourcePort_p
   * @return
   */
  Couple<DNode, Boolean> createViewOrGetFunctionPort(DNode parent_p, Pin target_p) {
    for (DNode node : parent_p.getOwnedBorderedNodes()) {
      if ((node.getTarget() != null) && node.getTarget().equals(target_p)) {
        return new Couple<DNode, Boolean>(node, Boolean.FALSE);
      }
    }

    DNode created = FaServices.getFaServices().createViewFunctionPort(target_p, parent_p, parent_p.getParentDiagram());
    return new Couple<DNode, Boolean>(created, Boolean.TRUE);
  }

  /**
   * Create a representing part if no representing partition exists
   * @param rootComponent_p
   */
  public void createRepresentingPartIfNone(Component component_p) {
    if (component_p.getRepresentingPartitions().size() == 0) {
      Part part = CsFactory.eINSTANCE.createPart();
      EObject parentContainer = getParentContainer(component_p);
      if (parentContainer instanceof BlockArchitecture) {
        Component context = getContext((BlockArchitecture) parentContainer);
        context.getOwnedFeatures().add(part);
      } else if (parentContainer instanceof Component) {
        ((Component) parentContainer).getOwnedFeatures().add(part);
      }

      CapellaServices.getService().creationService(part);
      part.setAbstractType(component_p);
    }
  }

  /**
   * Returns the target of LCB_LogicalComponent_subComponents mapping
   * @param component_p
   * @return
   */
  public List<Component> getTargetsOfComponent_subComponents(Component component_p) {
    List<Component> returnedList = new ArrayList<Component>();
    if (isMultipartMode(component_p)) {
      return returnedList;
    }
    for (Component component : ComponentExt.getDirectParents(component_p)) {
      if (!returnedList.contains(component)) {
        returnedList.add(component);
      }
    }

    return returnedList;
  }

  public List<EObject> getPCBPartSemanticCandidates(ModelElement element_p) {
    List<EObject> result = new ArrayList<EObject>();
    if (!(element_p instanceof Component) || !isMultipartMode(element_p)) {
      return result;
    }
    for (Component aComponent : getLCBSemanticCandidates((Component) element_p)) {
      for (Part part : ComponentExt.getSubParts(aComponent)) {
        if (!result.contains(part)) {
          result.add(part);
        }
      }
    }

    return result;
  }

  /**
   * return integer value according the link to be created 0 = non 1 = provided 2 = required 3 = provided + required
   * @param port_p
   * @param interface_p
   * @return
   */
  public int getTypeOfTheLinkToCreate(ComponentPort port_p, Interface interface_p) {
    int value = 0;
    boolean flag = false;

    if ((null != port_p) && (interface_p != null)) {
      EList<Interface> proviededInterfaces = port_p.getProvidedInterfaces();
      if (!proviededInterfaces.isEmpty()) {
        if (proviededInterfaces.contains(interface_p)) {
          flag = true;
          value = 1;

        }
      }
      EList<Interface> requiredInterfaces = port_p.getRequiredInterfaces();
      if (!requiredInterfaces.isEmpty()) {
        if (requiredInterfaces.contains(interface_p)) {
          if (flag) {
            value = 3;
          } else {
            value = 2;
          }
        }
      }

    }

    return value;
  }

  /**
   * Assuming the 'context' as Component with use link and sending kind of communication link --> Get all the component implementing and receiving kind of
   * communication link toward the same element as 'context' (using and sending links).
   * @param context_p : Component
   * @return list of component
   */
  public List<Component> getOppositeCompsOfUseAndSendingCommLink(EObject context_p) {
    List<Component> result = new ArrayList<Component>(1);

    if (context_p instanceof Component) {
      Component comp = (Component) context_p;

      List<Interface> allUsedInterfaces = ComponentExt.getUsedAndRequiredInterfaces(comp);
      for (Interface interface1 : allUsedInterfaces) {
        // get implementer component
        List<Component> implementerComponents = interface1.getImplementorComponents();
        if (!implementerComponents.isEmpty()) {
          result.addAll(implementerComponents);
        }
        List<Component> providerComponent = InterfaceExt.getProviderComponent(interface1);
        if (!providerComponent.isEmpty()) {
          result.addAll(providerComponent);
        }
      }

      // sending communication link to receiving communicationLink
      // component
      //
      Collection<AbstractExchangeItem> exchangeItemsByKinds = getExchangeItemsByTransmitkinds(comp);
      for (AbstractExchangeItem abstractExchangeItem : exchangeItemsByKinds) {
        if (abstractExchangeItem instanceof ExchangeItem) {
          // get all the component of the receiving communication link
          // of the current exchange
          List<Component> oppositeComponentUsingCrossref = getCompOfReceivingCommLinkUsingCrossRef(abstractExchangeItem);
          if (!oppositeComponentUsingCrossref.isEmpty()) {
            result.addAll(oppositeComponentUsingCrossref);
          }
        }
      }

    }

    return result;
  }

  /**
   * return custom label {interfaces, exchangeItems}
   * @param context_p : ?
   * @param sourceView_p : Component
   * @param targetView_p : Component
   * @return String
   */
  public String getInterfaceExchangeItemLabel(EObject context_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {

    String result = ICommonConstants.EMPTY_STRING;
    if ((null == sourceView_p) || (null == targetView_p)) {
      return result;
    }
    EObject src = sourceView_p.getTarget();
    EObject tar = targetView_p.getTarget();

    if ((null != src) && (null != tar) && (src instanceof Component) && (tar instanceof Component)) {
      Component source = (Component) src;
      Component target = (Component) tar;
      // used links to implementer component
      List<Interface> allUsedInterfaces = ComponentExt.getUsedAndRequiredInterfaces(source);
      for (Interface interface1 : allUsedInterfaces) {
        List<Component> components = new ArrayList<Component>();

        components.addAll(interface1.getImplementorComponents());
        List<Component> providerComponent = InterfaceExt.getProviderComponent(interface1);
        if (!providerComponent.isEmpty()) {
          components.addAll(providerComponent);
        }
        for (Component component : components) {
          if (target.equals(component)) {
            if (result.equalsIgnoreCase(ICommonConstants.EMPTY_STRING)) {
              result = result + interface1.getName();
            } else {
              result = result + ", " + interface1.getName(); //$NON-NLS-1$
            }
          }
        }
      }

      // sending communication link --> receiving communication link
      Collection<AbstractExchangeItem> exchangeItemsByKinds = getExchangeItemsByTransmitkinds(source);
      for (AbstractExchangeItem abstractExchangeItem : exchangeItemsByKinds) {
        if (abstractExchangeItem instanceof ExchangeItem) {
          List<Component> oppositeComponentUsingCrossref = getCompOfReceivingCommLinkUsingCrossRef(abstractExchangeItem);
          for (Component component : oppositeComponentUsingCrossref) {
            if (target.equals(component)) {
              if (result.equalsIgnoreCase(ICommonConstants.EMPTY_STRING)) {
                result = result + abstractExchangeItem.getName();
              } else {
                result = result + ", " + abstractExchangeItem.getName(); //$NON-NLS-1$
              }
            }
          }
        }
      }

    }

    return result;
  }

  /**
   * return custom label {interfaces, exchangeItems} diagram based
   * @param context_p : ?
   * @param sourceView_p : Component
   * @param targetView_p : Component
   * @return String
   */
  public String getInterfaceExchangeItemLabelDiagramBased(EObject context_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {

    String result = ICommonConstants.EMPTY_STRING;
    if ((null == sourceView_p) || (null == targetView_p)) {
      return result;
    }
    EObject src = sourceView_p.getTarget();
    EObject tar = targetView_p.getTarget();

    if ((null != src) && (null != tar) && (src instanceof Component) && (tar instanceof Component)) {
      Component source = (Component) src;
      Component target = (Component) tar;

      DiagramServices diagramService = DiagramServices.getDiagramServices();
      DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView_p);
      if (null == diagram) {
        return result;
      }

      // used links to implementer component
      List<Interface> allUsedInterfaces = ComponentExt.getUsedAndRequiredInterfaces(source);
      for (Interface interface1 : allUsedInterfaces) {
        List<Component> components = new ArrayList<Component>();
        components.addAll(interface1.getImplementorComponents());
        List<Component> providerComponent = InterfaceExt.getProviderComponent(interface1);
        if (!providerComponent.isEmpty()) {
          components.addAll(providerComponent);
        }
        for (Component component : components) {
          if (target.equals(component)) {
            // add to result only if interface1 is found in diagram
            if (diagramService.isOnDiagram(diagram, interface1)) {
              if (result.equalsIgnoreCase(ICommonConstants.EMPTY_STRING)) {
                result = result + interface1.getName();
              } else {
                result = result + ", " + interface1.getName(); //$NON-NLS-1$
              }
            }
          }
        }
      }

      // sending communication link --> receiving communication link
      Collection<AbstractExchangeItem> exchangeItemsByKinds = getExchangeItemsByTransmitkinds(source);
      for (AbstractExchangeItem abstractExchangeItem : exchangeItemsByKinds) {
        if (abstractExchangeItem instanceof ExchangeItem) {
          List<Component> oppositeComponentUsingCrossref = getCompOfReceivingCommLinkUsingCrossRef(abstractExchangeItem);
          for (Component component : oppositeComponentUsingCrossref) {
            if (target.equals(component)) {
              // add to result only if interface1 is found in diagram
              if (diagramService.isOnDiagram(diagram, abstractExchangeItem)) {
                if (result.equalsIgnoreCase(ICommonConstants.EMPTY_STRING)) {
                  result = result + abstractExchangeItem.getName();
                } else {
                  result = result + ", " + abstractExchangeItem.getName(); //$NON-NLS-1$
                }
              }
            }
          }
        }
      }

    }

    return result;
  }

  /**
   * return custom label {interfaces, exchangeItems} diagram based
   * @param context_p : ?
   * @param sourceView_p : Component
   * @param targetView_p : Component
   * @return String
   */
  public boolean isInterfaceExchangeItemLabelDiagramBasedEmpty(EObject context_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {

    if ((null == sourceView_p) || (null == targetView_p)) {
      return false;
    }
    EObject src = sourceView_p.getTarget();
    EObject tar = targetView_p.getTarget();

    if ((null != src) && (null != tar) && (src instanceof Component) && (tar instanceof Component)) {
      Component source = (Component) src;
      Component target = (Component) tar;

      DiagramServices diagramService = DiagramServices.getDiagramServices();
      DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView_p);
      if (null == diagram) {
        return false;
      }

      // used links to implementer component
      List<Interface> allUsedInterfaces = ComponentExt.getUsedAndRequiredInterfaces(source);
      for (Interface interface1 : allUsedInterfaces) {
        List<Component> components = new ArrayList<Component>();
        components.addAll(interface1.getImplementorComponents());
        List<Component> providerComponent = InterfaceExt.getProviderComponent(interface1);
        if (!providerComponent.isEmpty()) {
          components.addAll(providerComponent);
        }
        for (Component component : components) {
          if (target.equals(component)) {
            // add to result only if interface1 is found in diagram
            if (diagramService.isOnDiagram(diagram, interface1)) {
              return true;
            }
          }
        }
      }

      // sending communication link --> receiving communication link
      Collection<AbstractExchangeItem> exchangeItemsByKinds = getExchangeItemsByTransmitkinds(source);
      for (AbstractExchangeItem abstractExchangeItem : exchangeItemsByKinds) {
        if (abstractExchangeItem instanceof ExchangeItem) {
          List<Component> oppositeComponentUsingCrossref = getCompOfReceivingCommLinkUsingCrossRef(abstractExchangeItem);
          for (Component component : oppositeComponentUsingCrossref) {
            if (target.equals(component)) {
              // add to result only if interface1 is found in diagram
              if (diagramService.isOnDiagram(diagram, abstractExchangeItem)) {
                return true;
              }
            }
          }
        }
      }

    }

    return false;
  }

  /**
   * Get Component of the receiving communication link of current element
   * @param eObj_p : ExchangeItem
   * @return List<Component>
   */
  public List<Component> getCompOfReceivingCommLinkUsingCrossRef(EObject eObj_p) {
    List<Component> result = new ArrayList<Component>();

    Collection<Setting> inverseReferences = CapellaElementExt.getInverseReferencesOfEObject(eObj_p);

    for (Setting setting : inverseReferences) {
      EObject eObject = setting.getEObject();
      if (eObject != null) {
        // filter CommunicationLink
        if (eObject instanceof CommunicationLink) {
          CommunicationLink link = (CommunicationLink) eObject;
          if (CommunicationLinkExt.isReceiver(link)) {
            EObject container = getParentContainer(link);
            if ((container != null) && (container instanceof Component)) {
              result.add((Component) container);
            }
          }
        }
      }
    }

    return result;
  }

  /**
   * get ExchangeItem by kinds
   * @param comp_p : Component
   * @return : Collection<AbstractExchangeItem>
   */
  public Collection<AbstractExchangeItem> getExchangeItemsByTransmitkinds(Component comp_p) {
    // get owned communication link
    EList<CommunicationLink> ownedCommunicationLinks = comp_p.getOwnedCommunicationLinks();
    // prepare list of sending communication link kind
    CommunicationLinkKind[] sending =
        new CommunicationLinkKind[] { CommunicationLinkKind.SEND, CommunicationLinkKind.CALL, CommunicationLinkKind.WRITE, CommunicationLinkKind.PRODUCE,
                                     CommunicationLinkKind.TRANSMIT };
    // get exchange : filter by list of prepared kind
    Collection<AbstractExchangeItem> exchangeItemsByKinds = CommunicationLinkExt.getExchangeItemsByKinds(ownedCommunicationLinks, sending);
    return exchangeItemsByKinds;
  }

  /**
   * @used in common.odesign Return diagram label for state transition : mode and state diagram syntax : <Trigger> [<Guard>] / <Effect> (Note : if no <Trigger>
   *       <TriggerDesecription> is displayed)
   * @param context_p : StateTransition
   * @return : String
   */
  public String getStateTransitionLabel(EObject context_p) {
    String result = ICommonConstants.EMPTY_STRING;

    if ((null != context_p) && (context_p instanceof StateTransition)) {
      StateTransition transition = (StateTransition) context_p;

      // Trigger
      EList<AbstractEvent> triggers = transition.getTriggers();
      for (AbstractEvent trigger : triggers) {
        if (trigger != null) {
          String name = trigger.getName();
          if (trigger instanceof ChangeEvent) {
            ChangeEvent changeEvent = (ChangeEvent) trigger;
            name = "(" + changeEvent.getKind() + ") "; //$NON-NLS-1$ //$NON-NLS-2$
          }
          if (trigger instanceof TimeEvent) {
            TimeEvent timeEvent = (TimeEvent) trigger;
            name = "(" + timeEvent.getKind() + ") "; //$NON-NLS-1$ //$NON-NLS-2$
          }
          result += name;
          if (trigger instanceof StateEvent) {
            Constraint triggerCondition = ((StateEvent) trigger).getCondition();
            if (triggerCondition != null) {
              result += CapellaServices.getService().getConstraintLabel(triggerCondition);
            } else {
              result += trigger.getName();
            }
          }
          if (trigger != triggers.get(triggers.size() - 1)) {
            result += ","; //$NON-NLS-1$
          } else {
            result += " "; //$NON-NLS-1$
          }
        }
      }

      if (triggers.isEmpty()) {
        String triggerDescription = transition.getTriggerDescription();
        if ((null != triggerDescription) && !triggerDescription.equalsIgnoreCase(ICommonConstants.EMPTY_STRING)) {
          result += triggerDescription;
        }
      }

      if (transition.getGuard() != null) {
        String constraintLabel = CapellaServices.getService().getConstraintLabel(transition.getGuard());
        if ((constraintLabel != null) && !constraintLabel.isEmpty()) {
          result += " [" + constraintLabel + "] ";
        }
      }

      AbstractEvent effect = transition.getEffect();
      // Effect
      if (effect != null) {
        result += " / " + effect.getName(); //$NON-NLS-1$
      }
    }

    return result;
  }

  /**
   * @param diagram_p
   */
  public void refreschEntitiesArchitecture(ContainerMapping mapping_p, DDiagram diagram_p) {
    if (null == mapping_p) {
      return;
    }

    // store all displayed elements of the diagram
    Hashtable<Component, DNodeContainer> componentsInDiagram = new Hashtable<Component, DNodeContainer>();
    Set<DNodeContainer> toBeMoved = new HashSet<DNodeContainer>(); // diagram elements to be moved

    // get all displayed components in the diagram
    for (AbstractDNode aContainer : diagram_p.getContainers()) {
      if ((aContainer != null) && (aContainer.getTarget() != null) && CapellaServices.getService().isVisibleInDiagram(diagram_p, aContainer)
          && (aContainer.getTarget() instanceof Component) && aContainer.getDiagramElementMapping().equals(mapping_p)) {
        componentsInDiagram.put((Component) aContainer.getTarget(), (DNodeContainer) aContainer);
      }
    }

    // first iteration (to avoid null container)
    // the elements to be moved are temporarily placed in the diagram
    for (DNodeContainer anElement : componentsInDiagram.values()) {

      Component currentComponent = (Component) anElement.getTarget();

      // case if the actual container is not available any more :
      if (anElement.eContainer() instanceof DNodeContainer) {
        PartitionableElement actualParentContainer = (PartitionableElement) ((DNodeContainer) anElement.eContainer()).getTarget();
        if (!ComponentExt.getDirectParents(currentComponent).contains(actualParentContainer)) {
          diagram_p.getOwnedDiagramElements().add(anElement);
          toBeMoved.add(anElement);
          continue;
        }
      }

      // case if the actual container is available for the current Component
      Component parent = ComponentExt.getParent(currentComponent);
      while (parent != null) {
        // test if a parent of the component appears in the diagram
        DNodeContainer parentGraphicalElement = componentsInDiagram.get(parent);
        if ((null != parentGraphicalElement) && CapellaServices.getService().isVisibleInDiagram(diagram_p, parentGraphicalElement)
            && !parentGraphicalElement.getOwnedDiagramElements().contains(anElement)) {

          // if the parent (diagramElement) does not contain the current component (diagramElement)
          // the current component (diagramElement) must be moved
          diagram_p.getOwnedDiagramElements().add(anElement);
          toBeMoved.add(anElement);
          break;
        }
        parent = ComponentExt.getParent(parent);
      }
    }

    // second iteration
    // the elements are correctly moved
    for (DNodeContainer aContainer : toBeMoved) {
      if (aContainer.getTarget() == null) {
        continue;
      }
      // for each parent of the component to be moved, we tests if a diagramElement representing the parent appears in the diagram
      // When a parent is found in the diagram, we moved the component and stop.
      Component parent = ComponentExt.getParent((Component) aContainer.getTarget());
      while (parent != null) {
        DNodeContainer parentGraphicalElement = componentsInDiagram.get(parent);
        if ((null != parentGraphicalElement) && !parentGraphicalElement.getOwnedDiagramElements().contains(aContainer)) {
          parentGraphicalElement.getOwnedDiagramElements().add(aContainer);
          break;
        }
        parent = ComponentExt.getParent(parent);
      }
    }
  }

  /**
   * get all the {@link Component} except {@link ComponentContext} from block architecture
   * @param context_p
   * @param arch_p
   * @return : list of components
   */
  public List<CapellaElement> getAllComponentFromBlockArchitecture(EObject context_p, BlockArchitecture arch_p) {
    List<CapellaElement> tempResult = new ArrayList<CapellaElement>();
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    BlockArchitectureExt.getAllComponentsFromBlockArchitecture(arch_p, tempResult);
    for (CapellaElement capellaElement : tempResult) {
      if (!(capellaElement instanceof ComponentContext)) {
        result.add(capellaElement);
      }
    }
    return result;
  }

  /**
   * @used in common.odesign Remove views and Create views depending on the selected element list
   * @param context_p : diagram context
   * @param selectedInterfaces_p : list of element to be show in diagram
   * @param diagram_p current diagram
   * @return
   */
  public EObject showHideInterfaces(EObject context_p, List<CapellaElement> selectedInterfaces_p, List<CapellaElement> scope_p, DDiagram diagram_p) {
    Map<CapellaElement, AbstractDNode> visibleElements = new HashMap<CapellaElement, AbstractDNode>();
    for (DDiagramElement aNode : DiagramServices.getDiagramServices().getDiagramElements(diagram_p)) {
      EObject target = aNode.getTarget();
      if ((aNode instanceof AbstractDNode) && (target != null) && (target instanceof Interface)) {
        visibleElements.put((CapellaElement) target, (AbstractDNode) aNode);
      }
    }

    // remove elements : if view is of type DContainer or DNode
    // any new type should be taken into consideration
    for (Entry<CapellaElement, AbstractDNode> me : visibleElements.entrySet()) {
      if (!selectedInterfaces_p.contains(me.getKey()) && scope_p.contains(me.getKey())) {
        AbstractDNode node = me.getValue();
        if (node.isVisible()) {
          if (node instanceof DContainer) {
            DiagramServices.getDiagramServices().removeContainerView((DContainer) me.getValue());
          } else if (node instanceof DNode) {
            DiagramServices.getDiagramServices().removeNodeView((DNode) me.getValue());
          }
        }
      }
    }
    // create elements
    for (CapellaElement aOperation : selectedInterfaces_p) {
      EObject container = CapellaServices.getService().getBestGraphicalContainer(aOperation, diagram_p, CsPackage.Literals.INTERFACE);
      if (!visibleElements.containsKey(aOperation)) {
        // create node or container mapping for interface
        createInterfaceView(container, aOperation, diagram_p);
      }
    }
    return context_p;
  }

  /**
   * Crate interface view with proper mapping
   * @param context_p
   * @param aOperation_p
   * @param diagram_p
   */
  public EObject createInterfaceView(EObject context_p, CapellaElement aOperation_p, DDiagram diagram_p) {
    String mappingName = ICommonConstants.EMPTY_STRING;
    // by default consider Container mapping
    boolean isContainerMapping = true;

    // assign proper mapping
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      mappingName = IMappingNameConstants.IDB_INTERFACE_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCDI_INTERFACE;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCEI_INTERFACE;
      // false : because of DNode mapping
      isContainerMapping = false;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCII_INTERFACE;
      // false : because of DNode mapping
      isContainerMapping = false;
    }
    // create node or container
    return createViews(context_p, aOperation_p, diagram_p, mappingName, isContainerMapping);
  }

  /**
   * @used in common.odesign Remove views and Create views depending on the selected element list
   * @param context_p : diagram context
   * @param selectedInterfaces_p : list of element to be show in diagram
   * @param diagram_p current diagram
   * @return
   */

  public EObject showHideActors(EObject context_p, List<CapellaElement> selectedOperations_p, DDiagram diagram_p) {
    Map<CapellaElement, AbstractDNode> visibleElements = new HashMap<CapellaElement, AbstractDNode>();
    // collect all the visible abstractActor element from the diagram
    for (DDiagramElement aNode : DiagramServices.getDiagramServices().getDiagramElements(diagram_p)) {
      EObject target = aNode.getTarget();
      if ((aNode instanceof AbstractDNode) && (target != null) && (target instanceof AbstractActor)) {
        visibleElements.put((CapellaElement) target, (AbstractDNode) aNode);
      }
    }

    removeAndCreateAppropriateViews(selectedOperations_p, diagram_p, visibleElements);

    return context_p;
  }

  /**
   * @used in common.odesign Remove views and Create views depending on the selected element list
   * @param context_p : diagram context
   * @param selectedInterfaces_p : list of element to be show in diagram
   * @param diagram_p current diagram
   * @return
   */

  public EObject showHideCapabilityRealizations(EObject context_p, List<CapellaElement> selectedOperations_p, DDiagram diagram_p) {
    Map<CapellaElement, AbstractDNode> visibleElements = new HashMap<CapellaElement, AbstractDNode>();
    // collect all the visible abstractActor element from the diagram
    for (DDiagramElement aNode : DiagramServices.getDiagramServices().getDiagramElements(diagram_p)) {
      EObject target = aNode.getTarget();
      if ((aNode instanceof AbstractDNode) && (target != null) && (target instanceof CapabilityRealization)) {
        visibleElements.put((CapellaElement) target, (AbstractDNode) aNode);
      }
    }

    removeAndCreateAppropriateViews(selectedOperations_p, diagram_p, visibleElements);

    return context_p;
  }

  private void removeAndCreateAppropriateViews(List<CapellaElement> selectedOperations_p, DDiagram diagram_p, Map<CapellaElement, AbstractDNode> visibleElements) {
    // remove elements : if view is of type DContainer or DNode
    // any new type should be taken into consideration
    for (Entry<CapellaElement, AbstractDNode> me : visibleElements.entrySet()) {
      if (!selectedOperations_p.contains(me.getKey())) {
        if (me.getValue() instanceof DContainer) {
          DiagramServices.getDiagramServices().removeContainerView((DContainer) me.getValue());
        } else if (me.getValue() instanceof DNode) {
          DiagramServices.getDiagramServices().removeNodeView((DNode) me.getValue());
        }
      }
    }
    // create elements
    for (CapellaElement anElement : selectedOperations_p) {
      EObject container = CapellaServices.getService().getBestGraphicalContainer(anElement, diagram_p, anElement.eClass());
      if (!visibleElements.containsKey(anElement)) {
        // create node or container view for an actor
        createAppropriateView(container, anElement, diagram_p);
      }
    }
  }

  /**
   * Crate interface view with proper mapping
   * @param context_p
   * @param element_p
   * @param diagram_p
   * @return
   */
  private EObject createAppropriateView(EObject context_p, CapellaElement element_p, DDiagram diagram_p) {
    String mappingName = ICommonConstants.EMPTY_STRING;
    // by default consider Container mapping
    boolean isContainerMapping = true;

    // find proper mapping
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      mappingName = IMappingNameConstants.IDB_COMPONENT_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCDI_COMPONENT_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCEI_COMPONENT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCII_COMPONENT;
    } else if (IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK.equals(diagram_p.getDescription().getName())) {
      if (element_p instanceof AbstractActor) {
        mappingName = IMappingNameConstants.CRB_COMPONENT_MAPPING;
      } else if (element_p instanceof CapabilityRealization) {
        mappingName = IMappingNameConstants.CRB_CAPABILITY_REALIZATION_MAPPING;
        isContainerMapping = false;
      }
    }

    // crate node or container view
    if (!mappingName.equals(ICommonConstants.EMPTY_STRING)) {
      return createViews(context_p, element_p, diagram_p, mappingName, isContainerMapping);
    }

    return null;
  }

  /**
   * @param context_p diagram context
   * @param element_p capella element
   * @param diagram_p current diagram
   * @param mappingName element mapping name that need to created in current diagram
   * @param isContainerMapping : weather to create container or node kind of view in the current diagram
   * @return
   */
  private EObject createViews(EObject context_p, CapellaElement element_p, DDiagram diagram_p, String mappingName, boolean isContainerMapping) {
    if (isContainerMapping) {
      // create container
      ContainerMapping mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram_p, mappingName);
      return DiagramServices.getDiagramServices().createAbstractDNode(mapping, element_p, (DragAndDropTarget) context_p, diagram_p);
    }
    // crate node
    NodeMapping mapping = DiagramServices.getDiagramServices().getNodeMapping(diagram_p, mappingName);
    return DiagramServices.getDiagramServices().createNode(mapping, element_p, (DragAndDropTarget) context_p, diagram_p);
  }

  /**
   * Return first abstract capability package
   * @param context_p : a capella element
   * @return value could be null if no abstract capability package not found
   */
  public EObject getFirstAbstractCapPkg(EObject context_p) {
    BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture((ModelElement) context_p);
    if (null != arch) {
      return arch.getOwnedAbstractCapabilityPkg();
    }

    return null;
  }

  public void removeCapellaElement(EObject capellaElement) {
    // create empty list
    if ((null != capellaElement) && (capellaElement instanceof CapellaElement)) {
      CapellaServices.getService().removeElement((CapellaElement) capellaElement);
    }
  }

  public boolean isAbstract(EObject context_p) {
    // DataType, Class
    if (context_p instanceof GeneralizableElement) {
      GeneralizableElement genEle = (GeneralizableElement) context_p;
      return genEle.isAbstract();
    }
    // DataValue
    else if (context_p instanceof DataValue) {
      DataValue value = (DataValue) context_p;
      return value.isAbstract();
    }
    // Property
    else if (context_p instanceof Feature) {
      Feature feature = (Feature) context_p;
      return feature.isIsAbstract();
    }

    return false;
  }

  public boolean isPartOfKey(EObject context_p) {
    // DataType, Class
    // Property
    if (context_p instanceof Property) {
      Property property = (Property) context_p;
      return property.isIsPartOfKey();
    }

    return false;
  }

  public boolean isComponentTargetAvailableForCapInvolvement(EObject context_p, EObject preSource_p, EObject preTarget_p) {
    return CapabilityRealizationExt.isComponentTargetAvailableForCapInvolvement(context_p, preSource_p, preTarget_p);
  }

  public boolean isARootComponent(EObject context_p) {
    if (context_p instanceof CapellaElement) {
      BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(context_p);
      if (null != arch) {
        Component firstComponent = getFirstComponent(arch);
        if ((null != firstComponent) && firstComponent.equals(context_p)) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * Returns available components which are accessible CRB-Show-Hide-Component.
   */
  public Collection<Component> getCRBShowHideComponent(DSemanticDecorator decorator_p) {
    Collection<Component> components = new ArrayList<Component>();

    if ((decorator_p.getTarget() instanceof Component)) {
      return getCCIIShowHideComponent(decorator_p);
    }

    EObject parentContainer = getParentContainer(decorator_p.getTarget());
    if (null == parentContainer) {
      return components;
    }

    if (parentContainer instanceof Component) {
      return getSubComponents(parentContainer);
    } else if (parentContainer instanceof BlockArchitecture) {
      Component firstComponent = BlockArchitectureExt.getFirstComponent((ModellingArchitecture) parentContainer);
      if (null != firstComponent) {
        return getSubComponents(firstComponent);
      }
    }

    return components;
  }

  /**
   * Returns available components which are accessible by brothers-part CCEI-Show-Hide-Component.
   */
  public Collection<Component> getCRBShowHideActors(EObject context_p) {

    Collection<Component> components = new ArrayList<Component>();

    // Add actors
    components.addAll(ComponentExt.getSubDefinedActors(getArchitecture(context_p)));

    return filterActors(components);
  }

  /**
   * Returns available Capability Realization which are accessible by brothers-part CRA-Show-Hide-Component.
   */
  public Collection<CapabilityRealization> getCRBShowHideCapabilityRealizations(EObject context_p) {
    Collection<CapabilityRealization> elements = new ArrayList<CapabilityRealization>();
    if (context_p instanceof CapellaElement) {
      elements.addAll(CapabilityRealizationExt.getAllCapabilityRealizationOfOneLayer((CapellaElement) context_p));
    }
    return elements;
  }

  /**
   * Used in common.odesign CRB Diagram Used to filter the drag and drop from project Explorer
   * @param object_p
   * @param diagram_p
   * @return
   */
  public boolean isDiagramAndElementFromSameLayer(EObject context_p, EObject diagram_p) {
    if ((null == context_p) || (null == diagram_p) || !((context_p instanceof ModelElement) || (diagram_p instanceof DSemanticDiagram))) {
      return false;
    }

    boolean laLayer = CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) context_p);
    boolean paLayer = CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement) context_p);
    boolean epbsLayer = CapellaLayerCheckingExt.isInEPBSLayer((CapellaElement) context_p);

    EObject parentContainer = getParentContainer(((DSemanticDiagram) diagram_p).getTarget());
    if (null != parentContainer) {
      if (laLayer && CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) parentContainer)) {
        return true;
      } else if (paLayer && CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement) parentContainer)) {
        return true;
      } else if (epbsLayer && CapellaLayerCheckingExt.isInEPBSLayer((CapellaElement) parentContainer)) {
        return true;
      }
    }

    return false;
  }

  public List<EObject> getConstraintToInsertInDiagram(EObject context_p) {
    List<EObject> result = new ArrayList<EObject>(0);

    if (context_p instanceof DDiagram) {
      // return all the constraints of current level
      DSemanticDecorator diagram = (DSemanticDecorator) context_p;
      EObject target = diagram.getTarget();
      if ((null != target) && (target instanceof ModelElement)) {
        BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(target);
        return BlockArchitectureExt.getAllConstraints(arch);
      }
    } else if (context_p instanceof DDiagramElement) {
      DDiagramElement element = (DDiagramElement) context_p;
      EObject target = element.getTarget();
      if ((null != target) && (target instanceof ModelElement)) {
        ModelElement capellaElement = (ModelElement) target;
        result.addAll(capellaElement.getConstraints());
      }
    }
    return result;
  }

  /**
   * Create Constraint, if not already in diagram Create ConstaintElement(in unsynchronized mode ) if not already in diagram
   * @param context_p
   * @param constriant_p
   * @param dDiagram_p
   * @param constraintsInDiagram_p
   * @param kindDiagram_p = true if (Diagram), false if (Scenario)
   */
  public void createConstraintWithConstaintedElementInDiagram(EObject context_p, EObject constriant_p, DDiagram dDiagram_p,
      List<Constraint> constraintsInDiagram_p, boolean kindDiagram_p) {

    if ((null == context_p) || (null == constriant_p) || (null == dDiagram_p)) {
      return;
    }

    // diagramServices instance
    DiagramServices diagramServices = DiagramServices.getDiagramServices();
    NodeMapping constraintNodeMapping = null;
    EdgeMapping constaintEdgeMapping = null;
    if (kindDiagram_p) {
      // get constraint node mapping
      constraintNodeMapping = diagramServices.getNodeMapping(dDiagram_p, IMappingNameConstants.CDB_CONSTRAINT_MAPPING_NAME);
      // edge mapping to be created in (unSynchronized mode)
      constaintEdgeMapping = diagramServices.getEdgeMapping(dDiagram_p, IMappingNameConstants.CDB_CONSTRAINT_ELEMENT_MAPPING_NAME);
    } else {
      // its scenario kind
      // get constraint node mapping
      constraintNodeMapping = diagramServices.getNodeMapping(dDiagram_p, IMappingNameConstants.IS_CONSTRAINT_MAPPING);
      // edge mapping to be created in (unSynchronized mode)
      constaintEdgeMapping = diagramServices.getEdgeMapping(dDiagram_p, IMappingNameConstants.IS_CONSTRAINT_ELEMENT_MAPPING);
    }

    // constraint node
    DNode constraintNode = null;

    // create or retrieve node for 'constraint_p'
    if (constraintsInDiagram_p.contains(constriant_p) && (null != constraintNodeMapping)) {
      // retrieve constraint node
      Iterable<DDiagramElement> diagramElements = diagramServices.getDiagramElements(dDiagram_p);
      for (DDiagramElement dDiagramElement : diagramElements) {
        EObject target = dDiagramElement.getTarget();
        if ((target != null) && target.equals(constriant_p) && (dDiagramElement instanceof DNode)) {
          RepresentationElementMapping mapping = dDiagramElement.getMapping();
          if ((null != mapping) && mapping.equals(constraintNodeMapping)) {
            constraintNode = (DNode) dDiagramElement;
          }
        }
      }
    } else {
      // create constraint node
      constraintNode = diagramServices.createNode(constraintNodeMapping, constriant_p, dDiagram_p, dDiagram_p);
    }

    if (!dDiagram_p.isSynchronized() && (null != constaintEdgeMapping)) {
      // create constriantElementLink does not exist in diagram
      if (null != constraintNode) {
        EObject target = constraintNode.getTarget();
        if ((null != target) && (target instanceof Constraint)) {
          Constraint constraint = (Constraint) target;
          EList<ModelElement> constrainedElements = constraint.getConstrainedElements();
          for (ModelElement modelElement : constrainedElements) {
            // for all the constraintElements of the constraint in the diagram
            // check if there is any edge, if not create one
            if (diagramServices.isOnDiagram(dDiagram_p, modelElement)) {
              EList<DEdge> outgoingEdges = constraintNode.getOutgoingEdges();
              boolean edgeExist = false;
              for (DEdge dEdge : outgoingEdges) {
                EdgeTarget edgeTargetNode = dEdge.getTargetNode();
                if (null != edgeTargetNode) {
                  EObject edgeTargetNode_target = ((DDiagramElement) edgeTargetNode).getTarget();
                  // check if edge exist between 'modelElement' and given Constraint
                  if ((null != edgeTargetNode_target) && edgeTargetNode_target.equals(modelElement)) {
                    // no need to create an edge
                    edgeExist = true;
                    break;
                  }
                }
              }
              if (!edgeExist) {
                // get TargetView for the edge
                EObject diagramElement = diagramServices.getDiagramElement(dDiagram_p, modelElement);
                if (null != diagramElement) {
                  // create an edge
                  diagramServices.createEdge(constaintEdgeMapping, constraintNode, (EdgeTarget) diagramElement, target);
                }
              }
            }
          }
        }
      }
    }
  }

  /**
   * @used in physical.odesign for the moment (should be common for all) get proper target element from given target to be added in constraint, also move the
   *       constraint in proper target if needed
   * @param context_p
   * @param target_p
   * @param targetDiagramEle_p
   */
  public void createConstraintElement(EObject context_p, EObject target_p, DDiagramElement targetDiagramEle_p) {
    if ((null == context_p) || (null == target_p) || (null == targetDiagramEle_p)) {
      return;
    }
    if ((context_p instanceof Constraint) && (target_p instanceof ModelElement)) {
      Constraint constraint = (Constraint) context_p;
      EList<ModelElement> constrainedElements = constraint.getConstrainedElements();
      if (null != constrainedElements) {
        if (constrainedElements.isEmpty()) {
          constrainedElements.addAll(getTargetToAddAsConstraintedElement((ModelElement) target_p, targetDiagramEle_p));
          // move the constraint
          ModelElement properTargetToMoveConstraint = getProperTargetToMoveConstraint((ModelElement) target_p, targetDiagramEle_p);
          if (null != properTargetToMoveConstraint) {
            properTargetToMoveConstraint.getConstraints().add(constraint);
          }
        } else {
          constrainedElements.addAll(getTargetToAddAsConstraintedElement((ModelElement) target_p, targetDiagramEle_p));
        }
      }
    }
  }

  /**
   * Return Target element in which the constraint will be moved <b> null in case if diagram element with mapping "PAB_Deployment" <b>
   * @param target_p
   * @param targetDiagramEle_p
   * @return
   */
  private ModelElement getProperTargetToMoveConstraint(ModelElement target_p, DDiagramElement targetDiagramEle_p) {
    DiagramElementMapping diagramElementMapping = targetDiagramEle_p.getDiagramElementMapping();
    if (null != diagramElementMapping) {
      if (diagramElementMapping.getName().equals(IMappingNameConstants.PAB_PHYSICAL_COMPONENT_DEPLOYMENT_MAPPING_NAME)) {
        return null;
      }
    }
    return target_p;
  }

  /**
   * Return the default target <b> Exception for diagram element with mapping "PAB_Deployment" <b>
   * @param target_p
   * @return
   */
  private List<ModelElement> getTargetToAddAsConstraintedElement(ModelElement target_p, DDiagramElement targetDiagramEle_p) {
    List<ModelElement> result = new ArrayList<ModelElement>();
    // if targetDiagramElemnet deployed part
    // return the deployedElemnet link as target
    boolean flag = false;
    DiagramElementMapping diagramElementMapping = targetDiagramEle_p.getDiagramElementMapping();
    if (null != diagramElementMapping) {
      if (diagramElementMapping.getName().equals(IMappingNameConstants.PAB_PHYSICAL_COMPONENT_DEPLOYMENT_MAPPING_NAME)) {
        if (target_p instanceof Part) {
          Part part = (Part) target_p;
          EList<AbstractDeploymentLink> deployingLinks = part.getDeployingLinks();
          if ((null != deployingLinks) && !deployingLinks.isEmpty()) {
            result.addAll(deployingLinks);
            flag = true;
          }
        }
      }
    }
    if (!flag) {
      result.add(target_p);
    }
    return result;
  }

  /**
   * Return all constriantedElement for given Constraint <b> Exception for PartDeploymentLink (return its DeployedElement) <b>
   * @param context_p
   * @return
   */
  public List<EObject> targeFinderExpressionForConstraint(Constraint context_p) {
    List<EObject> result = new ArrayList<EObject>();
    EList<ModelElement> constrainedElements = context_p.getConstrainedElements();
    for (ModelElement modelElement : constrainedElements) {
      // get deployed element for each partDeploymentLink
      if (modelElement instanceof PartDeploymentLink) {
        PartDeploymentLink link = (PartDeploymentLink) modelElement;
        DeployableElement deployedElement = link.getDeployedElement();
        if (null != deployedElement) {
          result.add(deployedElement);
        }
      } else {
        result.add(modelElement);
      }
    }

    return result;
  }

  /**
   * @param semantic_p
   * @param sourceView_p
   * @param targetView_p
   * @return
   */
  public boolean isValidConstrainedElementsEdge(EObject semantic_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {
    if (semantic_p instanceof Constraint) {
      Constraint constraint = (Constraint) semantic_p;
      EList<ModelElement> elements = constraint.getConstrainedElements();
      return (elements != null) && !elements.isEmpty();
    }
    return false;
  }

  /**
   * Return true if the property or AssociationEnds are derived.
   * @param capellaElement_p : Capella element
   * @return boolean
   */
  public boolean isPropertyDerived(EObject capellaElement_p) {
    if (capellaElement_p instanceof Property) {
      Property property = (Property) capellaElement_p;
      if (property.isIsDerived()) {
        return true;
      }
    } else if (capellaElement_p instanceof Association) {
      Association ass = (Association) capellaElement_p;
      Collection<Property> properties = AssociationExt.getProperties(ass);
      for (Property property : properties) {
        if (property.isIsDerived()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Check if a CommunicationLink (graphically represented by the given object) between a component c1 and an interface is not defined in the children of c1.
   * Return true if the edge does not represents a CommunicationLink as previously defined or if the link is not graphically represented in one of the component
   * children.
   */
  public boolean doesCommunicationLinkEdgeIsNotRepresentedInComponentChildren(EObject object) {
    if (object instanceof DEdge) {
      DEdge currentEdge = (DEdge) object;
      EObject target = currentEdge.getTarget();
      if (target instanceof CommunicationLink) {
        CommunicationLink link = (CommunicationLink) target;
        EdgeTarget sourceNode = currentEdge.getSourceNode();
        if (sourceNode instanceof DNodeContainer) {
          for (DDiagramElement child : ((DNodeContainer) sourceNode).getElements()) {
            if ((child.getTarget() instanceof Component) && (child instanceof DNodeContainer)) {
              for (DEdge edge : ((DNodeContainer) child).getOutgoingEdges()) {
                target = edge.getTarget();
                if (target instanceof CommunicationLink) {
                  CommunicationLink childLink = (CommunicationLink) target;
                  if (CommunicationLinkExt.isSameCommunication(childLink, link)) {
                    return false;
                  }
                }
              }
            }
          }
        }
      }
    }
    return true;
  }

  public boolean doesUseOrImplementOrRequireOrProvideLinkEdgeIsNotRepresentedInComponentChildren(EObject object) {
    if (object instanceof DEdge) {
      DEdge currentEdge = (DEdge) object;
      EObject target = currentEdge.getTarget();

      if (((target instanceof ComponentPort) && isProvidedEdge(object)) || (target instanceof InterfaceImplementation)) {
        return isImplementedOrProvidedLinkEdgeNotRepresentedInChildrenComponent(object);
      }
      if (((target instanceof ComponentPort) && (isRequiredEdge(object))) || (target instanceof InterfaceUse)) {
        return isUsedOrRequiredLinkEdgeNotRepresentedInChildrenComponent(object);
      }
    }
    return true;
  }

  /**
   * Check if a provides/implements link (graphically represented by the given object) between a component c1 and an interface is not defined in the children of
   * c1. Return true if the edge does not represents a use/implements link as previously defined or if the link is not graphically represented in one of the
   * component children.
   */
  public boolean isImplementedOrProvidedLinkEdgeNotRepresentedInChildrenComponent(EObject object) {
    if (object instanceof DEdge) {
      DEdge currentEdge = (DEdge) object;
      EObject target = currentEdge.getTarget();
      if (target instanceof InterfaceImplementation) {
        Relationship link = (Relationship) target;
        EdgeTarget sourceNode = currentEdge.getSourceNode();
        if (sourceNode instanceof DNodeContainer) {
          for (DDiagramElement child : ((DNodeContainer) sourceNode).getElements()) {
            if ((child.getTarget() instanceof Component) && (child instanceof DNodeContainer)) {
              for (DEdge edge : ((DNodeContainer) child).getOutgoingEdges()) {
                target = edge.getTarget();
                if (target instanceof InterfaceImplementation) {
                  Relationship childLink = (Relationship) target;
                  if (((link instanceof InterfaceImplementation) && (childLink instanceof InterfaceImplementation) && (((InterfaceImplementation) link)
                      .getImplementedInterface() == ((InterfaceImplementation) childLink).getImplementedInterface()))) {
                    return false;
                  }
                }
              }
              for (DNode borderedNode : ((DNodeContainer) child).getOwnedBorderedNodes()) {
                if (borderedNode.getTarget() instanceof ComponentPort) {
                  for (DEdge outgoingEdge : borderedNode.getOutgoingEdges()) {
                    if (isProvidedEdge(outgoingEdge) && (currentEdge.getTargetNode() == outgoingEdge.getTargetNode())) {
                      return false;
                    }
                  }
                }
              }
            }
          }
        }
      } else if ((target instanceof ComponentPort) && isProvidedEdge(currentEdge)) {
        EObject sourceNode = currentEdge.getSourceNode().eContainer();
        if (sourceNode instanceof DNodeContainer) {
          for (DDiagramElement child : ((DNodeContainer) sourceNode).getElements()) {
            if ((child.getTarget() instanceof Component) && (child instanceof DNodeContainer)) {
              for (DNode borderedNode : ((DNodeContainer) child).getOwnedBorderedNodes()) {
                if (borderedNode.getTarget() instanceof ComponentPort) {
                  for (DEdge outgoingEdge : borderedNode.getOutgoingEdges()) {
                    if (isProvidedEdge(outgoingEdge) && (currentEdge.getTargetNode() == outgoingEdge.getTargetNode())) {
                      return false;
                    }
                  }
                }
              }
              if (!((DNodeContainer) child).getOutgoingEdges().isEmpty()) {
                for (DEdge edge : ((DNodeContainer) child).getOutgoingEdges()) {
                  target = edge.getTarget();
                  if ((target instanceof InterfaceImplementation) && (currentEdge.getTargetNode() == edge.getTargetNode())) {
                    return false;
                  }
                }
              }
            }
          }
        }
      }
    }
    return true;
  }

  /**
   * Check if a provides/implements link (graphically represented by the given object) between a component c1 and an interface is not defined in the children of
   * c1. Return true if the edge does not represents a use/implements link as previously defined or if the link is not graphically represented in one of the
   * component children.
   */
  public boolean isUsedOrRequiredLinkEdgeNotRepresentedInChildrenComponent(EObject object) {
    if (object instanceof DEdge) {
      DEdge currentEdge = (DEdge) object;
      EObject target = currentEdge.getTarget();
      if (target instanceof InterfaceUse) {
        Relationship link = (Relationship) target;
        EdgeTarget sourceNode = currentEdge.getSourceNode();
        if (sourceNode instanceof DNodeContainer) {
          for (DDiagramElement child : ((DNodeContainer) sourceNode).getElements()) {
            if ((child.getTarget() instanceof Component) && (child instanceof DNodeContainer)) {
              for (DEdge edge : ((DNodeContainer) child).getOutgoingEdges()) {
                target = edge.getTarget();
                if (target instanceof InterfaceUse) {
                  Relationship childLink = (Relationship) target;
                  if (((link instanceof InterfaceUse) && (childLink instanceof InterfaceUse) && (((InterfaceUse) link).getUsedInterface() == ((InterfaceUse) childLink)
                      .getUsedInterface()))) {
                    return false;
                  }
                }
              }
              for (DNode borderedNode : ((DNodeContainer) child).getOwnedBorderedNodes()) {
                if (borderedNode.getTarget() instanceof ComponentPort) {
                  for (DEdge outgoingEdge : borderedNode.getOutgoingEdges()) {
                    if (isRequiredEdge(outgoingEdge) && (currentEdge.getTargetNode() == outgoingEdge.getTargetNode())) {
                      return false;
                    }
                  }
                }
              }
            }
          }
        }
      } else if ((target instanceof ComponentPort) && isRequiredEdge(currentEdge)) {
        EObject sourceNode = currentEdge.getSourceNode().eContainer();
        if (sourceNode instanceof DNodeContainer) {
          for (DDiagramElement child : ((DNodeContainer) sourceNode).getElements()) {
            if ((child.getTarget() instanceof Component) && (child instanceof DNodeContainer)) {
              for (DNode borderedNode : ((DNodeContainer) child).getOwnedBorderedNodes()) {
                if (borderedNode.getTarget() instanceof ComponentPort) {
                  for (DEdge outgoingEdge : borderedNode.getOutgoingEdges()) {
                    if (isRequiredEdge(outgoingEdge) && (currentEdge.getTargetNode() == outgoingEdge.getTargetNode())) {
                      return false;
                    }
                  }
                }
              }
              if (!((DNodeContainer) child).getOutgoingEdges().isEmpty()) {
                for (DEdge edge : ((DNodeContainer) child).getOutgoingEdges()) {
                  target = edge.getTarget();
                  if ((target instanceof InterfaceUse) && (currentEdge.getTargetNode() == edge.getTargetNode())) {
                    return false;
                  }
                }
              }
            }
          }
        }
      }
    }
    return true;
  }

  /**
   * Check if an edge represents a provides link. Be careful (for developers only), this code uses the targetFinderExpression of mappings of type
   * EdgeMappingSpec to check if edges represent provides link. Since the targetFinderExpression property is a string built with class property name of the
   * metamodel, we use literals of the CsPackage for features 'requiredInterfaces' and 'providedInterfaces' so that a change concerning them in the metamodel
   * will make this code not compiling as a side effect.
   * @param object
   * @return
   */
  @SuppressWarnings("restriction")
  public boolean isProvidedEdge(EObject object) {
    if (object instanceof DEdge) {
      DEdge currentEdge = (DEdge) object;
      EObject target = currentEdge.getTarget();
      if (target instanceof ComponentPort) {
        IEdgeMapping mapping = currentEdge.getActualMapping();
        if (mapping instanceof EdgeMappingSpec) {
          String featureDef = ((EdgeMappingSpec) mapping).getTargetFinderExpression();
          if (featureDef.equals("feature:" + CsPackage.Literals.COMPONENT__PROVIDED_INTERFACES.getName())) { //$NON-NLS-1$
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Check if an edge represents a requires link. Be careful (for developers only), this code uses the targetFinderExpression of mappings of type
   * EdgeMappingSpec to check if edges represent requires link. Since the targetFinderExpression property is a string build with class property name of the
   * metamodel, we use literals of the CsPackage for features 'requiredInterfaces' and 'providedInterfaces' so that a change concerning them in the metamodel
   * will make this code not compiling as a side effect.
   * @param object
   * @return
   */
  @SuppressWarnings("restriction")
  public boolean isRequiredEdge(EObject object) {
    if (object instanceof DEdge) {
      DEdge currentEdge = (DEdge) object;
      EObject target = currentEdge.getTarget();
      if (target instanceof ComponentPort) {
        IEdgeMapping mapping = currentEdge.getActualMapping();
        if (mapping instanceof EdgeMappingSpec) {
          String featureDef = ((EdgeMappingSpec) mapping).getTargetFinderExpression();
          if (featureDef.equals("feature:" + CsPackage.Literals.COMPONENT__REQUIRED_INTERFACES.getName())) { //$NON-NLS-1$
            return true;
          }
        }
      }
    }
    return false;
  }

}
