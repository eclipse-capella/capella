/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.polarsys.capella.common.data.activity.Pin;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.common.menu.dynamic.util.INamePrefixService;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
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
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemPkg;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd;
import org.polarsys.capella.core.data.fa.ComponentPortKind;
import org.polarsys.capella.core.data.fa.ControlNode;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.data.fa.SequenceLinkEnd;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.helpers.information.services.CommunicationLinkExt;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeItemExt;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.ExchangeItem;
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
import org.polarsys.capella.core.data.information.util.PropertyNamingHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.diagram.helpers.traceability.DiagramTraceabilityHelper;
import org.polarsys.capella.core.diagram.helpers.traceability.IDiagramTraceability;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.helpers.AbstractDependenciesPkgExt;
import org.polarsys.capella.core.model.helpers.AssociationExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapabilityRealizationExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeCategoryExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ComponentPkgExt;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.model.helpers.PhysicalLinkCategoryExt;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.helpers.FilterHelper;
import org.polarsys.capella.core.sirius.analysis.queries.QueryIdentifierConstants;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Streams;

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

  public boolean isValidInitializeDiagramFromExistingDiagram(DSemanticDecorator diagram) {
    if (!(diagram instanceof DRepresentation)) {
      return false;
    }

    return true;
  }

  /**
   * Returns whether the Initialization tool must be show in palette
   * 
   * @param diagram
   * @return true if hidden
   */
  public boolean isFilterInitializeDiagramFromExistingDiagram(DSemanticDecorator diagram) {
    if (!(diagram instanceof DRepresentation)) {
      return true;
    }

    IDiagramTraceability handler = DiagramTraceabilityHelper.getService()
        .getTraceabilityHandler((DRepresentation) diagram, TRANSITION_TRACEABILITY);
    if (handler == null) {
      return true;
    }

    DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor((DRepresentation) diagram);
    if (handler.isRealizingable(descriptor)) {
      handler.dispose();

      if (diagram instanceof DDiagram) {
        // Disable initialization on non-empty diagram

        if (DiagramHelper.getService().isA((DDiagram) diagram,
            IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
          if (((DDiagram) diagram).getOwnedDiagramElements().size() > 1) {
            return true;
          }
        } else if (DiagramHelper.getService().isA((DDiagram) diagram,
            IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
          if (((DDiagram) diagram).getOwnedDiagramElements().size() > 1) {
            return true;
          }
        } else {
          // Disable initialization on non-empty diagram
          if (!((DDiagram) diagram).getOwnedDiagramElements().isEmpty()) {
            return true;
          }
        }
      }
      return false;
    }
    return true;
  }

  public EObject initializeDiagramFromExistingDiagram(DSemanticDecorator diagram, DSemanticDecorator sourceDiagram) {

    Logger logger = Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM);

    IHandlerService handlerService = PlatformUI.getWorkbench().getService(IHandlerService.class);
    ICommandService commandService = PlatformUI.getWorkbench().getService(ICommandService.class);
    if (handlerService == null || commandService == null) {
      logger.error("Cannot access to diagram initialization tool");
      return diagram;
    }

    if (sourceDiagram == null) {
      logger.error("Cannot retrieve source diagram for diagram initialization");
      return diagram;
    }

    Command command = commandService.getCommand(TRANSITION_TRACEABILITY_COMMAND);
    // Create a ParameterizedCommand with no parameter
    ParameterizedCommand parameterizedCommand = new ParameterizedCommand(command, null);

    EvaluationContext context = new EvaluationContext(null, Collections.singleton(sourceDiagram));
    context.addVariable("TARGET_DIAGRAM", diagram);

    try {
      handlerService.executeCommandInContext(parameterizedCommand, null, context);
    } catch (ExecutionException | NotDefinedException | NotEnabledException | NotHandledException exception) {
      logger.error("Errors occured while diagram initialization", exception);

    }
    return diagram;
  }

  /**
   * @param diagram
   * @return
   */
  public List<DRepresentation> getScopeInitializeDiagramFromExistingDiagram(DRepresentation diagram) {
    List<DRepresentation> scope = new ArrayList<>();
    IDiagramTraceability handler = DiagramTraceabilityHelper.getService().getTraceabilityHandler(diagram,
        TRANSITION_TRACEABILITY);

    Session session = DiagramHelper.getService().getSession(diagram);

    DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);

    for (DRepresentationDescriptor representationDescriptor : DialectManager.INSTANCE
        .getAllRepresentationDescriptors(session)) {
      if (handler.isRealizable(representationDescriptor, descriptor)
          && !scope.contains(representationDescriptor.getRepresentation())) {
        scope.add(representationDescriptor.getRepresentation());
      }
    }

    handler.dispose();

    return scope;
  }

  /**
   * Returns whether object is contextual element and filter is active
   * 
   * @param object
   * @param diagram
   * @return
   */
  public boolean isFilterContextualElement(EObject object, DDiagram diagram) {
    DRepresentationDescriptor descriptor = new DRepresentationQuery(diagram).getRepresentationDescriptor();
    if (ContextualDiagramHelper.getService().hasContextualElements(descriptor)) {
      for (FilterDescription filter : diagram.getActivatedFilters()) {
        if (IMappingNameConstants.SHOW_CONTEXTUAL_ELEMENTS.equals(filter.getName())
            && ContextualDiagramHelper.getService().getContextualElements(descriptor).contains(object)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Allows to set variable on the interpreter Should be replaced odesign by
   * org.polarsys.capella.core.sirius.analysis.actions.extensions.SetVariable
   * 
   * @param context
   *          the context
   * @param name
   *          the name
   * @param value
   *          the value
   * @return the EObject
   */
  public EObject setInterpreterVariable(EObject context, String name, EObject value) {
    InterpreterUtil.getInterpreter(context).setVariable(name, value);
    return context;
  }

  public EObject setInterpreterVariable(EObject context, String name, Collection<EObject> values) {
    InterpreterUtil.getInterpreter(context).setVariable(name, values);
    return context;
  }

  /**
   * Allows to retrieve variable on the interpreter
   * 
   * @param context
   *          the context
   * @param name
   *          the name
   * @param value
   *          the value
   * @return the EObject
   */
  public Object getInterpreterVariable(EObject context, String name) {
    return InterpreterUtil.getInterpreter(context).getVariable(name);
  }

  /**
   * Support for old odesign definition
   */
  @Deprecated
  public EObject setVariableA(EObject context, String name, EObject value) {
    return setInterpreterVariable(context, name, value);
  }

  /**
   * Checks if user has enabled the preference.
   * 
   * @param object
   *          useful only in sirius
   * @return whether if is preference is enabled
   */
  @Deprecated
  public boolean isPreferenceEnabled(EObject object, String preference) {
    if (object instanceof DSemanticDecorator) {
      DSemanticDecorator decorator = (DSemanticDecorator) object;
      if (decorator.getTarget() instanceof ModelElement) {
        return isMultipartMode((ModelElement) decorator.getTarget());
      }
    }
    return true;
  }

  /**
   * Used in sirius to check if multipart mode is enabled
   * 
   * @param object
   *          , any eobject
   * @return whether if is preference is enabled
   */
  public boolean isMultipartMode(ModelElement object) {
    return TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(object));
  }

  @Deprecated
  public static final String PREFERENCE_LISTENER_ID = "org.polarsys.capella.core.preferences.Listener"; //$NON-NLS-1$

  @Deprecated
  public static final String PREFERENCE_LISTENER_ID_PREFERENCE_MULTIPART = "multipart.allowed"; //$NON-NLS-1$

  @Deprecated
  public static final String[] PREFERENCE_LISTENER_ID_PREFERENCES = new String[] {
      PREFERENCE_LISTENER_ID_PREFERENCE_MULTIPART, };

  @Deprecated
  public EObject preferenceListener = null;

  /**
   * Create a wrapper EObject which will be send a notification when the preference change
   * 
   * @param object
   * @return
   */
  @Deprecated
  public EObject getPreferenceListener(final EObject object) {
    return object;
  }

  /**
   * Debug purpose only. display a popup message with informations about the given object
   * 
   * @param object
   *          the object
   * @return true, if successful
   */
  public boolean myDebug(EObject object) {
    MessageDialog.openInformation(null, object.getClass().getName(), object.toString());
    return true;
  }

  /**
   * Returns all related capability which are associated to the interface.
   * 
   * @param itf
   *          the given interface
   * @return the related components
   */
  public Collection<Component> getRelatedComponents(Interface itf) {
    return InterfaceExt.getRelatedComponents(itf);
  }

  public DSemanticDecorator getRelatedFunctionView(DSemanticDecorator element) {
    if (element.getTarget() instanceof AbstractFunction) {
      return element;
    }
    if (element.eContainer() instanceof DSemanticDecorator) {
      return getRelatedFunctionView((DSemanticDecorator) element.eContainer());
    }
    return null;
  }

  public DSemanticDecorator getRelatedPartView(DSemanticDecorator element) {
    if ((element.getTarget() != null)
        && ((element.getTarget() instanceof Part) || (element.getTarget() instanceof Entity))) {
      return element;
    }
    if (element.eContainer() instanceof DSemanticDecorator) {
      return getRelatedPartView((DSemanticDecorator) element.eContainer());
    }
    return null;
  }

  public InformationsExchanger getRelatedPart(DSemanticDecorator element) {
    if ((element.getTarget() != null)
        && ((element.getTarget() instanceof Part) || (element.getTarget() instanceof Entity))) {
      return (InformationsExchanger) element.getTarget();
    }
    if (element.eContainer() instanceof DSemanticDecorator) {
      return getRelatedPart((DSemanticDecorator) element.eContainer());
    }
    return null;
  }

  /**
   * Returns all related capability which are associated to the interface.
   * 
   * @param itf
   *          the given interface
   * @return the related components
   */
  public Collection<CommunicationLinkExchanger> getRelatedExchangers(AbstractExchangeItem item) {
    return ExchangeItemExt.getRelatedExchangers(item);
  }

  /**
   * Returns all interfaces related to the component.
   * 
   * @param root
   *          the component of the TID table
   * @return interfaces related to the given component for the TID table
   */
  public Collection<Interface> getTIDInterfaces(Component root) {
    return getAllRelatedInterfaces(root);
  }

  /**
   * Returns label displayed instead of interface name.
   * 
   * @param itf
   *          the given interface
   * @return label of the given interface for the TID table
   */
  public String getTIDInterfaceLabel(Interface itf) {
    return itf.getName();
  }

  /**
   * Returns label displayed in a cell of the TID.
   * 
   * @param itf
   *          the interface
   * @param line
   *          the line of the cell
   * @param column
   *          the column of the cell
   * @return the label which will be display in the cell
   */
  public String getTIDLabel(EObject itf, EObject line, EObject column) {
    Component component = (Component) ((DSemanticDecorator) column).getTarget();
    Interface relatedItf = (Interface) ((DSemanticDecorator) line).getTarget();

    StringBuilder res = new StringBuilder();

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
   * 
   * @param current
   *          the current EObject
   * @param parent
   *          the parent EObject
   * @return true, if parent is an ancestor of the current element
   */
  public boolean isAnAncestor(EObject current, EObject parent) {
    EObject object = current;
    for (object = object.eContainer(); object != null; object = object.eContainer()) {
      if (object == parent) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns true if e1 is an ancestor of e2 and vice versa
   * 
   * @param e1
   * @param e2
   * @return
   */
  public boolean oneIsAncestorAnother(EObject current, EObject e1, EObject e2) {
    return EcoreUtil.isAncestor(e1, e2) || EcoreUtil.isAncestor(e1, e2);
  }

  /**
   * Returns the parent component of the element or the blockarchitecture based on what's displayed on the diagram.
   * 
   * @param current
   *          the current element
   * @return the parent component or block architecture
   */
  public EObject getParentContainer(EObject current) {
    EObject object = current;
    for (object = current.eContainer(); object != null; object = object.eContainer()) {
      if ((object instanceof Component) || (object instanceof BlockArchitecture)) {
        return object;
      }
    }
    return null;
  }

  /**
   * Returns the nearest semantic parent component of the element
   * 
   * @param current
   *          the current element isActorContext whether we are looking the parent of an actor
   * @return the parent component or block architecture
   */
  public EObject getSemanticParentContainer(EObject current, boolean isActorContext) {
    EObject object = current;
    for (object = current.eContainer(); object != null; object = object.eContainer()) {
      if (object instanceof BlockArchitecture) {
        return object;
      }
      if (isActorContext) {
        if (ComponentExt.canCreateABActor(object)) {
          return object;
        }
      } else {
        if (ComponentExt.canCreateABComponent(object)) {
          return object;
        }
      }
    }
    return null;
  }

  /**
   * Returns the list of parent component or block architecture of the element.
   * 
   * @param current
   *          the current element
   * @return the parent component or block architecture
   */
  public Collection<EObject> getParentContainers(EObject current) {
    LinkedList<EObject> elements = new LinkedList<>();
    elements.add(current);
    return getParentContainers(elements);
  }

  /**
   * Returns all parents of currents elements
   * 
   * @param currents
   *          the list of elements
   * @return parents component or block architecture
   */
  protected Collection<EObject> getParentContainers(Collection<EObject> currents) {
    LinkedList<EObject> toVisits = new LinkedList<>(currents);
    Collection<EObject> parents = new java.util.HashSet<>();
    // Access all hierarchy of components and blockarchitectures
    while (!toVisits.isEmpty()) {
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
   * Returns the parent component of the element or the blockarchitecture. If current is a component or a
   * blockarchitecture, returns the given element itself
   * 
   * @param current
   *          the given EObject
   * @return the first parent component or blockarchitecture
   */
  public EObject getFirstParentContainer(EObject current) {
    EObject object = current;
    for (; object != null; object = object.eContainer()) {
      if ((object instanceof Component) || (object instanceof BlockArchitecture)) {
        return current;
      }
    }
    return null;
  }

  /**
   * Returns all components which contains parts of the given component.
   * 
   * @param component
   *          the component
   * @return all components which contains a part of the given component.
   */
  public Collection<Component> getContainersOfParts(Component component) {
    Collection<Component> containers = new java.util.HashSet<>();

    // Access to all container of parts
    for (Part part : component.getRepresentingParts()) {
      EObject parent = getParentContainer(part);
      if (parent instanceof Component) {
        containers.add((Component) parent);
      }

      for (DeploymentTarget deploment : getCache(PartExt::getDeployingElements, part)) {
        if (deploment instanceof Part) {
          AbstractType type = (((Part) deploment)).getAbstractType();
          if (type instanceof Component) {
            containers.add((Component) type);
          }
        }
      }
    }
    return containers;
  }

  /**
   * Returns all components which contains parts of the given component.
   * 
   * @param component
   *          the component
   * @return all components which contains a part of the given component.
   */
  public Collection<Component> getContainersOfPart(Part part) {
    Collection<Component> containers = new java.util.HashSet<>();

    // Access to all container of parts
    EObject parent = getParentContainer(part);
    if (parent instanceof Component) {
      containers.add((Component) parent);
    }

    for (DeploymentTarget deploment : getCache(PartExt::getDeployingElements, part)) {
      if (deploment instanceof Part) {
        AbstractType type = (((Part) deploment)).getAbstractType();
        if (type instanceof Component) {
          containers.add((Component) type);
        }
      }
    }

    return containers;
  }

  /**
   * Returns whether the given interface can be linked to the current component (interfaces from parents) (requires,
   * implements, uses or provides).
   * 
   * @param component
   *          the given component
   * @param inter
   *          the given interface
   * @return true, if the interface can be linked to the current component
   */
  public boolean isAvailableInterface(Component component, Interface inter) {
    return getCCEIInsertInterface(component).contains(inter);
  }

  /**
   * Returns parents components of parts of the given component.
   * 
   * @param component
   *          the given component
   * @return the parent components by parts of the given component
   */
  public Collection<EObject> getParentContainersByParts(Component component) {
    LinkedList<Component> elements = new LinkedList<>();
    elements.add(component);
    return getParentContainersByParts(elements);
  }

  /**
   * Returns parents components of parts of given objects.
   * 
   * @param currents
   *          the collection of EObject
   * @return the parent components by parts of the given objects.
   */
  public Collection<EObject> getParentContainersByParts(Collection<Component> currents) {
    LinkedList<EObject> toVisits = new LinkedList<>(currents);
    Collection<EObject> parents = new java.util.HashSet<>();
    // Access all hierarchy of components and blockarchitectures
    while (!toVisits.isEmpty()) {
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
  public Collection<EObject> getAvailableComponentsByNamespaceOfParts(Component component) {
    LinkedList<EObject> ownerParts = new LinkedList<>();
    ownerParts.addAll(getContainersOfParts(component));
    return ComponentExt.getAvailableComponentsByNamespaceOfParts(ownerParts);
  }

  /**
   * Returns interfaces available on the Show/Hide interface of the CCII diagram.
   */
  public Collection<Interface> getCCIIInsertInterface(EObject object) {
    Collection<Interface> res = getCCEIInsertInterface(object);

    if (object instanceof Component) {
      res.addAll(getSubDefinedInterfaces((Component) object));
      res.addAll(getRelatedInterfaces((Component) object));
    } else if (object instanceof BlockArchitecture) {
      res.addAll(getSubDefinedInterfaces((BlockArchitecture) object));
    }
    return res;
  }

  /**
   * Returns interfaces available on the Show/Hide interface of the IB diagram.
   */
  public Collection<Interface> getIBInsertInterface(EObject object) {
    return getCCIIInsertInterface(object);
  }

  /**
   * Returns available interfaces which can be added into an external diagram (all interfaces from parents-hierarchy of
   * all part of a component) CCEI-Insert-Interface CCII-Insert-Interface with getSubInterfaces added For an EPBS,
   * returns interfaces related of parents and sub-components and their allocated components For others, returns
   * interfaces of parents components and their allocated components (recursively).
   */
  public Collection<Interface> getCCEIInsertInterface(EObject context) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_CCE_INSERT_INTERFACE_FOR_LIB, context);
  }

  /**
   * Returns related interfaces of components (implements, uses, provides, requires).
   */
  public Collection<CommunicationLink> getRelatedCommunicationLinks(Component component) {
    return CommunicationLinkExt.getAllCommunicationLinks(component);
  }

  /**
   * Returns related interfaces of components (implements, uses, provides, requires).
   */
  public Collection<Interface> getRelatedInterfaces(Component component) {
    Collection<Interface> interfaces = new ArrayList<>();

    if (component != null) {
      interfaces.addAll(ComponentExt.getImplementedAndProvidedInterfaces(component));
      interfaces.addAll(ComponentExt.getUsedAndRequiredInterfaces(component));
    }

    return interfaces;
  }

  /**
   * Returns related interfaces of components (implements, uses, provides, requires).
   */
  public Collection<Interface> getAllRelatedInterfaces(Component component) {
    Collection<Interface> interfaces = new ArrayList<>();

    if (component != null) {
      interfaces.addAll(ComponentExt.getAllImplementedAndProvidedInterfaces(component));
      interfaces.addAll(ComponentExt.getAllUsedAndRequiredInterfaces(component));
    }

    return interfaces;
  }

  /**
   * Remove into the given collection all allocated interfaces.
   */
  public void removeAllAllocatedInterfaces(Collection<Interface> interfaces) {
    // Remove all allocated interfaces
    Collection<Interface> toRemove = new java.util.HashSet<>();
    for (Interface inter : interfaces) {
      if (inter.getAllocatedInterfaces() != null) {
        for (Interface allocated : inter.getAllocatedInterfaces()) {
          toRemove.add(allocated);
        }
      }
    }
    interfaces.removeAll(toRemove);
  }

  /**
   * Returns the list with all interfaces which are in allocated components.
   */
  public Collection<Interface> getInterfacesFromAllocatedComponent(Component subComponent) {
    Collection<Interface> interfaces = new ArrayList<>();

    // Add interfaces from allocated components
    if (subComponent.getRealizedComponents() != null) {
      LinkedList<Component> allocateds = new LinkedList<>();
      for (Component allocated : subComponent.getRealizedComponents()) {
        allocateds.add(allocated); // addAll can throw an exception
      }

      while (!allocateds.isEmpty()) {
        Component allocated = allocateds.removeFirst();
        interfaces.addAll(getSubDefinedInterfaces(allocated));
        if (allocated.getRealizedComponents() != null) {
          for (Component suballocated : allocated.getRealizedComponents()) {
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
  public Collection<Interface> getRelatedInterfacesFromAllocatedComponent(Component subComponent) {
    Collection<Interface> interfaces = new ArrayList<>();

    // Add interfaces from allocated components
    if (subComponent.getRealizedComponents() != null) {
      LinkedList<Component> allocateds = new LinkedList<>();
      for (Component allocated : subComponent.getRealizedComponents()) {
        allocateds.add(allocated); // addAll can throw an exception
      }

      while (!allocateds.isEmpty()) {
        Component allocated = allocateds.removeFirst();
        interfaces.addAll(getRelatedInterfaces(allocated));
      }
    }

    return interfaces;
  }

  /**
   * Fills the list with all interfaces which are in allocated architectures.
   */
  public Collection<Interface> getInterfacesFromAllocatedArchitecture(BlockArchitecture architecture) {
    Collection<Interface> interfaces = new ArrayList<>();

    // Add interfaces from allocated components
    if (architecture.getAllocatedArchitectures() != null) {
      LinkedList<BlockArchitecture> allocateds = new LinkedList<>();
      for (BlockArchitecture allocated : architecture.getAllocatedArchitectures()) {
        allocateds.add(allocated); // addAll can throw an exception
      }

      while (!allocateds.isEmpty()) {
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
  public boolean isSameArchitecture(NamedElement source, NamedElement target) {
    BlockArchitecture architectureSource = ComponentExt.getRootBlockArchitecture(source);
    BlockArchitecture architectureTarget = ComponentExt.getRootBlockArchitecture(target);

    return ((architectureSource != null) && architectureSource.equals(architectureTarget))
        || ((architectureSource == null) && (architectureTarget == null));

  }

  /**
   * Gets the architecture.
   */
  public BlockArchitecture getArchitecture(EObject current) {
    return BlockArchitectureExt.getRootBlockArchitecture(current);
  }

  /**
   * Returns the current context of a component.
   */
  public CapellaElement getContext(Component component) {
    EObject parent = getParentContainer(component);

    if (parent instanceof BlockArchitecture) {
      return getContext((BlockArchitecture) parent);
    }

    return (Component) parent;
  }

  /**
   * Returns the current context of a component.
   */
  public CapellaElement getContext(Part part) {
    EObject parent = getParentContainer(part);

    if (parent instanceof BlockArchitecture) {
      return getContext((BlockArchitecture) parent);
    }

    return (Component) parent;
  }

  /**
   * Returns the current context of an architecture.
   */
  public ComponentPkg getContext(BlockArchitecture architecture) {
    return BlockArchitectureExt.getContext(architecture);
  }

  /**
   * Returns available components which can be added into an internal diagram (all components visible in the current
   * namespace which haven't part) CCII-Insert-Component.
   */
  public Collection<Component> getCCIIInsertComponent(Component component) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_CCII_SHOW_HIDE_COMPONENTS_FOR_LIB, component)
        .stream().filter(comp -> comp instanceof Component && !ComponentExt.isActor((Component) comp))
        .map(Component.class::cast).collect(Collectors.toList());
  }

  public Collection<Component> getCCIIInsertActor(Component component) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_CCII_SHOW_HIDE_COMPONENTS_FOR_LIB, component)
        .stream().filter(comp -> comp instanceof Component && ComponentExt.isActor((Component) comp))
        .map(Component.class::cast).collect(Collectors.toList());
  }

  /**
   * Returns a sub-list of the given components which can be inserted into the given component.
   */
  public Collection<Component> filterPhysicalComponentsByNature(PhysicalComponent component,
      Collection<Component> components) {
    Collection<Component> result = new java.util.ArrayList<>();
    PhysicalComponentNature natureComponent = component.getNature();
    for (Component sub : components) {
      if (sub instanceof PhysicalComponent) {
        PhysicalComponentNature nature = ((PhysicalComponent) sub).getNature();
        if ((nature == PhysicalComponentNature.UNSET) || (natureComponent == PhysicalComponentNature.UNSET)
            || (nature == natureComponent)) {
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
  public Collection<Component> filterNotActors(Collection<? extends EObject> components) {
    Collection<Component> result = new java.util.ArrayList<>();
    for (EObject sub : components) {
      if (sub instanceof Component) {
        Component component = (Component) sub;
        if (!component.isActor() && !result.contains(sub)) {
          result.add(component);
        }
      }
    }
    return result;
  }

  /**
   * Returns a sub-list of the given components which are only actors.
   */
  // replaced by IQueryFilter KeepRealActorsFilter
  public Collection<Component> filterActors(Collection<? extends EObject> components) {
    Collection<Component> result = new java.util.ArrayList<>();
    for (EObject sub : components) {
      if ((sub instanceof Component)) {
        Component component = (Component) sub;
        if (component.isActor()) {
          result.add(component);
        }
      }
    }
    return result;
  }

  /**
   * Returns available components which can be added into an external diagram (all components visible in the current
   * namespace which haven't part) CCEI-Insert-Component.
   */
  public Collection<Component> getCCEIInsertComponent(Component component) {
    Collection<Component> result = null;
    EObject element = getParentContainer(component);
    if (element instanceof Component) {
      result = getCCIIInsertComponent((Component) element);
    } else {
      result = getCCIIInsertComponent(component);
    }
    result.remove(component);
    return result;
  }

  /**
   * Returns available components which can be added into an external diagram (all components visible in the current
   * namespace which haven't part) CCEI-Insert-Actor.
   */
  public Collection<Component> getCCEIInsertActor(Component component) {
    Collection<Component> components = new java.util.HashSet<>();

    // Add components accessible by namespace
    components.addAll(ComponentExt.getSubDefinedActors(getArchitecture(component)));

    // Remove component from existing part
    components.removeAll(PartExt.getComponentsOfParts(getContext(getArchitecture(component)).getOwnedParts()));

    // Remove current component and remove all containers of current component
    components.remove(component);
    components.removeAll(getParentContainersByParts(component));

    return components;
  }

  public Collection<AbstractExchangeItem> getIBShowHideExchangeItems(Component component) {
    return CapellaServices.getService().getAllExchangeItems(component);
  }

  public Collection<AbstractExchangeItem> getIBReuseExchangeItems(Interface itf) {
    return CapellaServices.getService().getAllExchangeItems(itf);
  }

  /**
   * Returns available components which can be added into an external diagram (all components visible in the namespace
   * of parts without parents).
   */
  public Collection<? extends EObject> getFilterHideChildComponents(Component component) {

    // Get components by namespace (return also a set so it's great for checking a contains(component))
    Collection<EObject> list = getAvailableComponentsByNamespaceOfParts(component);

    // Add components of brothers-parts
    // The filter was too restrictive since it was hiding also inner-children of parent of component
    list.addAll(getBrothersComponents(component));

    // Remove all hierarchy-container of parts
    list.removeAll(getParentContainersByParts(component));

    // Add current element
    list.add(component);
    return list;
  }

  /**
   * Gets the brothers components.
   */
  public Collection<Component> getBrothersComponents(Component component) {
    Collection<Component> components = new java.util.HashSet<>();

    // Add components which are brothers of component-parts
    for (Part part : component.getRepresentingParts()) {
      Component container = ComponentExt.getDirectParent(part);
      if (container != null) {
        Component ownerPart = container;
        for (Part siblingPart : ownerPart.getContainedParts()) {
          components.add((Component) siblingPart.getType());
        }
      } else if (part.eContainer() instanceof ComponentPkg) {
        for (Part siblingPart : ((ComponentPkg) part.eContainer()).getOwnedParts()) {
          components.add((Component) siblingPart.getType());
        }
      }
    }
    return components;
  }

  /**
   * Returns available components which are accessible by brothers-part CCEI-Show-Hide-Component.
   */
  public Collection<Component> getCCEIShowHideComponent(Component component) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_CCEI_SHOW_HIDE_COMPONENTS_FOR_LIB, component);
  }

  /**
   * Returns available components which are accessible by brothers-part CCEI-Show-Hide-Component.
   */
  public Collection<Component> getCCEIShowHideActors(Component component) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_CCEI_SHOW_HIDE_ACTORS_FOR_LIB, component);
  }

  public Collection<Component> getCCEIShowHideActors(DSemanticDecorator decorator) {
    if (!(decorator.getTarget() instanceof Component)) {
      return Collections.emptyList();
    }
    Component target = (Component) decorator.getTarget();
    Collection<Component> siblingActors = getCCEIShowHideActors(target);
    // Search for first level root actors since Show/Hide tool is always applied on the diagram background
    EObject rootActorContainer = getIBTarget(decorator, true);
    List<Component> firstLevelRootActors = QueryInterpretor
        .executeQuery(QueryIdentifierConstants.GET_CCII_SHOW_HIDE_ACTORS, rootActorContainer);
    return Streams.concat(siblingActors.stream(), firstLevelRootActors.stream()).collect(Collectors.toSet());
  }

  public Collection<Component> getIBShowHideActor(DSemanticDecorator decorator) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_IB_SHOW_HIDE_ACTORS_FOR_LIB, decorator);
  }

  /**
   * Returns available components which are accessible CCII-Show-Hide-Component.
   */
  public Collection<Component> getCCIIShowHideComponent(DSemanticDecorator decorator) {
    if (!(decorator.getTarget() instanceof Component)) {
      return Collections.emptyList();
    }
    EObject target = getCCIITarget(decorator);
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_CCII_SHOW_HIDE_COMPONENTS_FOR_LIB, target);
  }

  /**
   * Returns available actors which are accessible CCII-Show-Hide-Actor.
   */
  public Collection<Component> getCCIIShowHideActor(DSemanticDecorator decorator) {
    if (!(decorator.getTarget() instanceof Component)) {
      return Collections.emptyList();
    }
    EObject directContainer = getCCIITarget(decorator);
    EObject rootActorContainer = getIBTarget(decorator, true);
    List<Component> siblingActors = QueryInterpretor
        .executeQuery(QueryIdentifierConstants.GET_CCII_SHOW_HIDE_ACTORS_FOR_LIB, directContainer);
    // Show/Hide tool is applied on a Component
    if (directContainer == rootActorContainer) {
      return siblingActors;
    }
    // Show/Hide tool is applied on the diagram background
    List<Component> firstLevelRootActors = QueryInterpretor
        .executeQuery(QueryIdentifierConstants.GET_CCII_SHOW_HIDE_ACTORS, rootActorContainer);
    return Streams.concat(siblingActors.stream(), firstLevelRootActors.stream()).collect(Collectors.toSet());
  }

  public Collection<Component> getSubComponents(EObject target) {
    Collection<Component> components = new ArrayList<>();
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

  public Collection<Component> getSubActors(EObject target) {
    Collection<Component> components = new ArrayList<>();
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
    return filterActors(components);
  }

  public Collection<? extends CapellaElement> getABShowHidePureComponent(DSemanticDecorator decorator) {
    return getABShowHideComponent(decorator).stream().filter(part -> !ComponentExt.isActor(part))
        .collect(Collectors.toList());
  }

  /**
   * Returns available components which are accessible AB-Show-Hide-Component.
   */
  public Collection<? extends CapellaElement> getABShowHideComponent(DSemanticDecorator decorator) {
    EObject target = getABTarget(decorator);
    return getABShowHideComponent(decorator, target);
  }

  /**
   * Returns available components which are accessible AB-Show-Hide-Actor.
   */
  public Collection<? extends CapellaElement> getABShowHideComponentForActor(DSemanticDecorator decorator) {
    EObject target = getABActorTarget(decorator);
    return getABShowHideComponent(decorator, target);
  }

  /**
   * Returns available Components/Actors which are accessible AB-Show-Hide-Component/Actor.
   */
  public Collection<? extends CapellaElement> getABShowHideComponent(DSemanticDecorator decorator, EObject target) {

    // In multipart mode or in EPBS Architecture Blank, we display parts
    if (isMultipartMode((ModelElement) target) || getComponentType(decorator) instanceof ConfigurationItem
        || getComponentType(decorator) instanceof ConfigurationItemPkg) {

      Collection<Part> parts = new ArrayList<>();

      Component targetComponent = null;

      if (target instanceof Component) {
        targetComponent = (Component) target;
      }
      if (target instanceof Part) {
        targetComponent = (Component) ((Part) target).getAbstractType();
      }

      if (decorator instanceof DDiagram) {
        if (targetComponent != null) {
          parts.addAll(getParts(targetComponent, CsPackage.Literals.COMPONENT, null));
          parts.addAll(targetComponent.getRepresentingParts());
        } else if (target instanceof BlockArchitecture) {
          parts.addAll(getParts(getContext((BlockArchitecture) target), CsPackage.Literals.COMPONENT, null));
        } else if (target instanceof ComponentPkg) {
          parts.addAll(getParts((ComponentPkg) target, CsPackage.Literals.COMPONENT, null));
        }
      } else if (targetComponent != null) {
        parts.addAll(getParts(targetComponent, CsPackage.Literals.COMPONENT, null));
      }

      return parts;
    }

    // Mono part, we return all components contained in the part.
    ModelElement root = (ModelElement) getComponentType(decorator);
    Collection<Component> components = new ArrayList<>();

    if (decorator instanceof DDiagram) {
      BlockArchitecture architecture = ComponentExt.getRootBlockArchitecture(root);
      components = CsServices.getService().getAllSubDefinedComponents(architecture);

    } else if (root instanceof Component) {
      Part part = (Part) decorator.getTarget();
      components = PartExt.getComponentsOfParts(ComponentExt.getAllSubUsedParts((Component) root, false));

      // Add all children of deployed components
      for (DeployableElement deployed : PartExt.getDeployedElements(part)) {
        if (deployed instanceof Part && ((Part) deployed).getAbstractType() instanceof Component) {
          Part deployedPart = (Part) deployed;
          Component abstractType = (Component) deployedPart.getAbstractType();
          Collection<Part> allSubUsedParts = ComponentExt.getAllSubUsedParts(abstractType, false);

          components.addAll(PartExt.getComponentsOfParts(allSubUsedParts));
        }
      }
    }
    return components.stream().filter(c -> !c.getRepresentingParts().isEmpty()).collect(Collectors.toList());
  }

  /**
   * Returns available components which are accessible CCII-Show-Hide-Component.
   */
  public Collection<Component> getIBShowHideComponent(DSemanticDecorator decorator) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_IB_SHOW_HIDE_COMPONENTS_FOR_LIB, decorator);
  }

  /**
   * Gets the cCII target.
   */
  public EObject getCCIITarget(DSemanticDecorator decorator) {
    if (decorator instanceof DDiagram) {
      return getParentContainer(decorator.getTarget());
    }
    return decorator.getTarget();
  }

  /**
   * Gets the AB target to create
   */
  public EObject getABTarget(DSemanticDecorator decorator) {
    if (decorator instanceof DDiagram) {
      ContainerMapping cMapping = FaServices.getFaServices().getMappingABComponent(decorator.getTarget(),
          (DDiagram) decorator);
      for (DDiagramElement element : ((DDiagram) decorator).getOwnedDiagramElements()) {
        if (DiagramServices.getDiagramServices().isMapping(element, cMapping)) {
          // If the target of the diagram is present in the diagram, we create new elements in the target's container
          if (element.getTarget() == decorator.getTarget()) {
            return getSemanticParentContainer(decorator.getTarget(), false);
          }
          if ((element.getTarget() instanceof Part)
              && (((Part) element.getTarget()).getAbstractType() == decorator.getTarget())) {
            return getSemanticParentContainer(decorator.getTarget(), false);
          }
        }
      }

      if (ABServices.getService().isValidCreationABComponent(decorator)) {
        return decorator.getTarget();
      }

      // We find the nearest container to store the element
      EObject parent = getSemanticParentContainer(decorator.getTarget(), false);
      if (parent instanceof BlockArchitecture
          && ABServices.getService().isValidCreationABComponent(((BlockArchitecture) parent).getSystem())) {
        return ((BlockArchitecture) parent).getSystem();
      }

      return parent;
    }
    return decorator.getTarget();
  }

  /**
   * Gets the target to create an actor in a Architecture Blank diagram
   */
  public EObject getABActorTarget(DSemanticDecorator decorator) {
    EObject target = decorator.getTarget();

    if (decorator instanceof DDiagram) {
      BlockArchitecture rootBlockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(target);
      return BlockArchitectureExt.getComponentPkg(rootBlockArchitecture);
    }
    return target;
  }

  public EObject getCBDActorTarget(DSemanticDecorator decorator) {
    if (decorator instanceof DDiagram) {
      ContainerMapping cMapping = FaServices.getFaServices().getMappingABComponent(decorator.getTarget(),
          (DDiagram) decorator);
      for (DDiagramElement element : ((DDiagram) decorator).getOwnedDiagramElements()) {
        if (DiagramServices.getDiagramServices().isMapping(element, cMapping)) {
          // If the target of the diagram is present in the diagram, we create new elements in the target's container
          if (element.getTarget() == decorator.getTarget()) {
            return getSemanticParentContainer(decorator.getTarget(), true);
          }
          if ((element.getTarget() instanceof Part)
              && (((Part) element.getTarget()).getAbstractType() == decorator.getTarget())) {
            return getSemanticParentContainer(decorator.getTarget(), true);
          }
        }
      }
      if (ABServices.getService().isValidCreationABActor((decorator))) {
        return decorator.getTarget();
      }

      // We find the nearest container to store the element
      EObject parent = getSemanticParentContainer(decorator.getTarget(), true);
      if (parent instanceof BlockArchitecture
          && ABServices.getService().isValidCreationABComponent(((BlockArchitecture) parent).getSystem())) {
        return ((BlockArchitecture) parent).getSystem();
      }

      return parent;
    }
    return decorator.getTarget();
  }

  /**
   * 
   * @param decorator
   * @return whether the target of the diagram is present in the diagram
   */
  public boolean isABTargetPresent(DSemanticDecorator decorator) {
    if (decorator instanceof DDiagram) {
      ContainerMapping cMapping = FaServices.getFaServices().getMappingABComponent(decorator.getTarget(),
          (DDiagram) decorator);
      for (DDiagramElement element : ((DDiagram) decorator).getOwnedDiagramElements()) {
        if (DiagramServices.getDiagramServices().isMapping(element, cMapping)) {
          if (element.getTarget() == decorator.getTarget()) {
            return true;
          }
          if ((element.getTarget() instanceof Part)
              && (((Part) element.getTarget()).getAbstractType() == decorator.getTarget())) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Gets the iB target (used for Interfaces, ExchangeItems, etc.).
   */
  public EObject getIBTarget(DSemanticDecorator decorator) {
    if (decorator instanceof DDiagram) {
      for (DDiagramElement element : ((DDiagram) decorator).getOwnedDiagramElements()) {
        if (element.getTarget() == decorator.getTarget()) {
          return getParentContainer(decorator.getTarget());
        }
      }
      if (decorator.getTarget() instanceof ComponentPkg)
        return getParentContainer(decorator.getTarget());
      return decorator.getTarget();
    }
    return decorator.getTarget();
  }

  /**
   * Gets the iB target (used for Components, Actors).
   * 
   * @param decorator
   *          the graphical element isActorContext whether we are looking the target for an actor
   */
  public EObject getIBTarget(DSemanticDecorator decorator, boolean isActorContext) {
    if (decorator instanceof DDiagram) {

      // actors are always created in root structure
      if (isActorContext) {
        EObject target = decorator.getTarget();
        BlockArchitecture rootBlockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(target);
        return BlockArchitectureExt.getComponentPkg(rootBlockArchitecture);
      }

      // components are always created in the best container
      for (DDiagramElement element : ((DDiagram) decorator).getOwnedDiagramElements()) {
        if (element.getTarget() == decorator.getTarget()) {
          return getSemanticParentContainer(decorator.getTarget(), false);
        }
      }

      if (ABServices.getService().isValidCreationABComponent(decorator)) {
        return decorator.getTarget();
      }

      // We find the nearest container to store the element
      EObject parent = getSemanticParentContainer(decorator.getTarget(), false);
      if (parent instanceof BlockArchitecture
          && ABServices.getService().isValidCreationABComponent(((BlockArchitecture) parent).getSystem())) {
        return ((BlockArchitecture) parent).getSystem();
      }

      return parent;
    }
    return decorator.getTarget();
  }

  /**
   * @param context
   * @param newSource
   * @param newTarget
   * @return
   */

  public boolean isGeneralizableForReConnect(final EObject context, EObject newSource, EObject newTarget) {

    // return false if source(Class) is not primitive and target(Class) is primitive
    if (newSource instanceof Class && newTarget instanceof Class && ((Class) newSource).isIsPrimitive()
        && !((Class) newTarget).isIsPrimitive()) {
      return false;
    }
    // return false if target(Class) is not primitive and source(Class) is primitive
    if (newSource instanceof Class && newTarget instanceof Class && !((Class) newSource).isIsPrimitive()
        && ((Class) newTarget).isIsPrimitive()) {
      return false;
    }

    if ((newSource instanceof PhysicalQuantity) && (newTarget instanceof NumericType)) {
      return true;
    }

    if ((context instanceof Generalization) && (newSource instanceof GeneralizableElement)
        && (newTarget instanceof GeneralizableElement) && (newTarget instanceof Class)
        && !((Class) newTarget).isIsPrimitive() && newSource.eClass().equals(newTarget.eClass())) {

      Generalization generalization = (Generalization) context;

      GeneralizableElement newSourceClazz = (GeneralizableElement) newSource;
      GeneralizableElement newTargetClazz = (GeneralizableElement) newTarget;

      boolean notExistCycle = true;
      boolean superSourcesContainsTarget = CapellaServices.getService()
          .getSuperClassifiers(newSourceClazz, generalization).contains(newTargetClazz);
      boolean subTargetConatinsSource = CapellaServices.getService().getSubClassifiers(newTargetClazz, generalization)
          .contains(newSourceClazz);
      boolean superTargetsContainsSource = CapellaServices.getService()
          .getSuperClassifiers(newTargetClazz, generalization).contains(newSourceClazz);
      boolean subSourcesConatinsTarget = CapellaServices.getService().getSubClassifiers(newSourceClazz, generalization)
          .contains(newTargetClazz);

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
        || ((newSource instanceof GeneralizableElement) && (newTarget instanceof GeneralizableElement)
            && newSource.eClass().equals(newTarget.eClass()))) {

      GeneralizableElement targetClass = (GeneralizableElement) newTarget;
      GeneralizableElement sourceClass = (GeneralizableElement) newSource;

      // check if multiple inheritance is allowed
      // check if target is not already inherited
      // check to avoid inheritance cycle
      boolean existCycle = !CapellaServices.getService().getSuperClassifiers(sourceClass).contains(targetClass)
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

    if (((newSource instanceof AbstractCapability) && (newTarget instanceof AbstractCapability))
        && newSource.eClass().equals(newTarget.eClass())) {
      boolean cond = !CapellaServices.getService().getAllSuperCapabilities(context, (AbstractCapability) newTarget)
          .contains(newSource)
          && !CapellaServices.getService().getAllSuperCapabilities(context, (AbstractCapability) newSource)
              .contains(newTarget);
      if (cond) {
        return true;
      }

    }

    return false;
  }

  /**
   * @param newSourceClazz
   * @param generalisation
   *          FIXME the code should be replaced by a simple 'return !newSourceClazz.getOwnedGeneralizations().isEmpty()'
   */
  private boolean containMultipleIheritence(GeneralizableElement newSourceClazz, Generalization generalisation) {
    EList<Generalization> generalisations = newSourceClazz.getOwnedGeneralizations();
    for (Generalization generalization : generalisations) {
      if (generalization.getSub().equals(newSourceClazz)) {
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

    if ((context instanceof Generalization) && (source instanceof GeneralizableElement)
        && (target instanceof GeneralizableElement) && !((Class) target).isIsPrimitive()
        && source.eClass().equals(target.eClass())) {

      GeneralizableElement sourceClazz = (GeneralizableElement) source;
      GeneralizableElement targetClazz = (GeneralizableElement) target;

      boolean notExistCycle = true;
      boolean superSourcesContainsTarget = CapellaServices.getService().getSuperClassifiers(sourceClazz)
          .contains(targetClazz);
      boolean subTargetConatinsSource = CapellaServices.getService().getSubClassifiers(targetClazz)
          .contains(sourceClazz);
      boolean superTargetsContainsSource = CapellaServices.getService().getSuperClassifiers(targetClazz)
          .contains(sourceClazz);
      boolean subSourcesConatinsTarget = CapellaServices.getService().getSubClassifiers(sourceClazz)
          .contains(targetClazz);

      if (superSourcesContainsTarget) {
        return !subTargetConatinsSource;
      }

      if (subSourcesConatinsTarget) {
        return CapellaModelPreferencesPlugin.getDefault().isMultipleInheritanceAllowed() && superTargetsContainsSource;
      }

      return notExistCycle;

    }

    if (((source instanceof PhysicalQuantity) && (target instanceof NumericType))
        || ((source instanceof Component) && (target instanceof Component)) || ((source instanceof GeneralizableElement)
            && (target instanceof GeneralizableElement) && source.eClass().equals(target.eClass()))) {

      GeneralizableElement targetClass = (GeneralizableElement) target;
      GeneralizableElement sourceClass = (GeneralizableElement) source;

      // check if multiple inheritance is allowed
      // check if target is not already inherited
      // check to avoid inheritance cycle
      boolean existCycle = !CapellaServices.getService().getSuperClassifiers(sourceClass).contains(targetClass)
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
   * used in Common (tool create and reconnect generalization in CDB and Interface diagrams)
   * 
   * @param generalization
   *          current generalization
   * @param newSource
   *          the new source of the generalization
   * @return true if newSource can be the source of generalization
   */
  public boolean preGeneralization(EObject generalization, EObject preSource) {
    if (preSource instanceof Component && !((Component) preSource).isActor()) {
      return CapellaModelPreferencesPlugin.getDefault().isComponentNonActorInheritanceAllowed();
    }
    return true;
  }

  /**
   * used in Common (tool reconnect generalization in CDB and Interface diagrams)
   * 
   * @param generalization
   *          current generalization
   * @param newSource
   *          the new source of the generalization
   * @return true if newSource can be the source of generalization
   */
  public boolean canReconnectGeneralization(EObject generalization, EObject subObject, EObject targetObject) {
    EObject source = subObject;
    EObject target = targetObject;

    if (!isGeneralizableForReConnect(generalization, source, target)) {
      return false;
    }

    if ((source instanceof FinalizableElement) && (target instanceof FinalizableElement)) {
      return !((FinalizableElement) target).isFinal();
    }

    if (!((source instanceof Component) && (target instanceof Component))) {

      source = getParentContainer(source);
      target = getParentContainer(target);

      if ((source instanceof ModellingArchitecture) && (target instanceof ModellingArchitecture)) {

        AbstractDependenciesPkg sourcePkg = (AbstractDependenciesPkg) CapellaServices.getService().getParent(subObject,
            CapellacorePackage.Literals.ABSTRACT_DEPENDENCIES_PKG);
        AbstractDependenciesPkg targetPkg = (AbstractDependenciesPkg) CapellaServices.getService()
            .getParent(targetObject, CapellacorePackage.Literals.ABSTRACT_DEPENDENCIES_PKG);

        if (AbstractDependenciesPkgExt.isADependencyAvailable(sourcePkg, targetPkg)) {
          return true;
        }

      } else if ((source instanceof ModellingArchitecture) || (target instanceof ModellingArchitecture)) {
        return true;
      }
    }

    if ((source instanceof Component) && (target instanceof Component)) {
      if (!(((Component) source).isActor() && ((Component) target).isActor())
          && !CapellaModelPreferencesPlugin.getDefault().isComponentNonActorInheritanceAllowed()) {
        return false;
      }

      if ((((Component) source).isActor()) && (((Component) target).isActor())) {
        return source.getClass().equals(target.getClass());
      } else if (!(((Component) source).isActor()) && !(((Component) target).isActor())) {
        if (!getCache(ComponentExt::getAllSubUsedAndDeployedComponents, (Component) source).contains(target)
            && !getCache(ComponentExt::getAllSubUsedAndDeployedComponents, (Component) target).contains(source)) {
          /*
           * if (source instanceof PhysicalComponent && target instanceof PhysicalComponent) { return
           * ((PhysicalComponent) source).getNature().equals(((PhysicalComponent) target).getNature()); }
           */
          return (source.getClass().equals(target.getClass()));
        }
      }
    }
    // if (((Component) source).getTypedElements().size() == 0) {
    // return false;
    // }
    //
    // for (TypedElement element : ((Component) source).getTypedElements()) {
    // if (element instanceof Part) {
    // if (ComponentExt.getAvailableComponentsByNamespaceOfParts((Part) element).contains(target)) {
    // return true;
    // }
    // }
    // }
    // }
    return false;
  }

  /**
   * Returns whether the reconnect-source performed between source and target is available (if given target is a brother
   * or an ancestor of given source).
   */
  public boolean linkReconnectSource(EObject current, EObject sourceObject, EObject targetObject) {
    EObject source = sourceObject;
    EObject target = targetObject;

    if (!((source instanceof Component) || (source instanceof ComponentPort))
        || (source instanceof ConfigurationItem)) {
      return false;
    }
    if (!((target instanceof Component) || (target instanceof ComponentPort))
        || (target instanceof ConfigurationItem)) {
      return false;
    }
    if ((source instanceof Component) && (((Component) source).isActor()) && (target instanceof Component)) {
      return true;
    }
    if ((target instanceof Component) && (((Component) target).isActor()) && (source instanceof Component)) {
      return true;
    }
    if (source instanceof ComponentPort) {
      source = source.eContainer();
    }
    if (target instanceof ComponentPort) {
      target = target.eContainer();
    }
    if ((source instanceof Component) && (((Component) source).isActor()) && (target instanceof Component)) {
      return true;
    }
    if ((target instanceof Component) && (((Component) target).isActor()) && (source instanceof Component)) {
      return true;
    }

    EObject sAncestor = CapellaServices.getService().getAncestor(source, CsPackage.Literals.COMPONENT);
    EObject tAncestor = CapellaServices.getService().getAncestor(target, CsPackage.Literals.COMPONENT);

    if (sAncestor == tAncestor) {
      return true;
    }

    return isAnAncestor(source, target);
  }

  /**
   * Returns the list of interfaces defined into a component.
   */
  public Collection<Interface> getSubDefinedInterfaces(Component component) {
    return InterfacePkgExt.getAllInterfaces(component.getOwnedInterfacePkg());
  }

  /**
   * Returns the list of interfaces defined into the architecture.
   */
  public Collection<Interface> getSubDefinedInterfaces(BlockArchitecture block) {
    return InterfacePkgExt.getAllInterfaces(block.getOwnedInterfacePkg());
  }

  /**
   * Returns true if the edge represents a sub-link of provided interface.
   */
  public boolean isASubProvidedLink(DEdge edge) {

    if ((edge.getSourceNode() instanceof DSemanticDecorator) && (edge.getTargetNode() instanceof DSemanticDecorator)) {
      ComponentPort port = (ComponentPort) ((DSemanticDecorator) edge.getSourceNode()).getTarget();
      Interface inter = (Interface) ((DSemanticDecorator) edge.getTargetNode()).getTarget();

      List<ComponentPort> ports = new ArrayList<>();
      List<ComponentPort> portsVisited = new ArrayList<>();
      portsVisited.add(port);
      for (PortAllocation allocation : port.getOutgoingPortAllocations()) {
        if ((allocation.getAllocatedPort() instanceof ComponentPort)
            && !portsVisited.contains(allocation.getAllocatedPort())) {
          ports.add((ComponentPort) allocation.getAllocatedPort());
        }
      }

      while (!ports.isEmpty()) {
        port = ports.remove(0);
        if (port.getProvidedInterfaces().contains(inter)
            && DiagramServices.getDiagramServices().isOnDiagram(edge.getParentDiagram(), port)) {
          return false;
        }
        portsVisited.add(port);
        for (PortAllocation allocation : port.getOutgoingPortAllocations()) {
          if ((allocation.getAllocatedPort() instanceof ComponentPort)
              && !portsVisited.contains(allocation.getAllocatedPort())) {
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
  public boolean isASuperProvidedLink(DEdge edge) {

    if ((edge.getSourceNode() instanceof DSemanticDecorator) && (edge.getTargetNode() instanceof DSemanticDecorator)) {
      ComponentPort port = (ComponentPort) ((DSemanticDecorator) edge.getSourceNode()).getTarget();
      Interface inter = (Interface) ((DSemanticDecorator) edge.getTargetNode()).getTarget();

      List<ComponentPort> ports = new ArrayList<>();
      List<ComponentPort> portsVisited = new ArrayList<>();
      portsVisited.add(port);
      for (PortAllocation allocation : port.getIncomingPortAllocations()) {
        if ((allocation.getAllocatingPort() instanceof ComponentPort)
            && !portsVisited.contains(allocation.getAllocatingPort())) {
          ports.add((ComponentPort) allocation.getAllocatingPort());
        }
      }

      while (!ports.isEmpty()) {
        port = ports.remove(0);
        if (port.getProvidedInterfaces().contains(inter)
            && DiagramServices.getDiagramServices().isOnDiagram(edge.getParentDiagram(), port)) {
          return false;
        }
        portsVisited.add(port);
        for (PortAllocation allocation : port.getOutgoingPortAllocations()) {
          if ((allocation.getAllocatedPort() instanceof ComponentPort)
              && !portsVisited.contains(allocation.getAllocatedPort())) {
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
  public boolean isASubRequiredLink(DEdge edge) {

    if ((edge.getSourceNode() instanceof DSemanticDecorator) && (edge.getTargetNode() instanceof DSemanticDecorator)) {
      ComponentPort port = (ComponentPort) ((DSemanticDecorator) edge.getSourceNode()).getTarget();
      Interface inter = (Interface) ((DSemanticDecorator) edge.getTargetNode()).getTarget();

      List<ComponentPort> ports = new ArrayList<>();
      List<ComponentPort> portsVisited = new ArrayList<>();
      portsVisited.add(port);
      for (PortAllocation allocation : port.getOutgoingPortAllocations()) {
        if ((allocation.getAllocatedPort() instanceof ComponentPort)
            && !portsVisited.contains(allocation.getAllocatedPort())) {
          ports.add((ComponentPort) allocation.getAllocatedPort());
        }
      }

      while (!ports.isEmpty()) {
        port = ports.remove(0);
        if (port.getRequiredInterfaces().contains(inter)
            && DiagramServices.getDiagramServices().isOnDiagram(edge.getParentDiagram(), port)) {
          return false;
        }
        portsVisited.add(port);
        for (PortAllocation allocation : port.getOutgoingPortAllocations()) {
          if ((allocation.getAllocatedPort() instanceof ComponentPort)
              && !portsVisited.contains(allocation.getAllocatedPort())) {
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
  public boolean isASuperRequiredLink(DEdge edge) {

    if ((edge.getSourceNode() instanceof DSemanticDecorator) && (edge.getTargetNode() instanceof DSemanticDecorator)) {
      ComponentPort port = (ComponentPort) ((DSemanticDecorator) edge.getSourceNode()).getTarget();
      Interface inter = (Interface) ((DSemanticDecorator) edge.getTargetNode()).getTarget();

      List<ComponentPort> ports = new ArrayList<>();
      List<ComponentPort> portsVisited = new ArrayList<>();
      portsVisited.add(port);
      for (PortAllocation allocation : port.getIncomingPortAllocations()) {
        if ((allocation.getAllocatingPort() instanceof ComponentPort)
            && !portsVisited.contains(allocation.getAllocatingPort())) {
          ports.add((ComponentPort) allocation.getAllocatingPort());
        }
      }

      while (!ports.isEmpty()) {
        port = ports.remove(0);
        if (port.getRequiredInterfaces().contains(inter)
            && DiagramServices.getDiagramServices().isOnDiagram(edge.getParentDiagram(), port)) {
          return false;
        }
        portsVisited.add(port);
        for (PortAllocation allocation : port.getOutgoingPortAllocations()) {
          if ((allocation.getAllocatedPort() instanceof ComponentPort)
              && !portsVisited.contains(allocation.getAllocatedPort())) {
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
  protected List<Component> getSuperComponents(Component component) {
    List<Component> comps = new ArrayList<>();
    EObject item = component;
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
  public List<Component> getSubUsedComponents(CapellaElement capellaElement) {
    if (capellaElement instanceof Component) {
      return ComponentExt.getSubUsedComponents((Component) capellaElement);
    } else if (capellaElement instanceof ComponentPkg) {
      return ComponentPkgExt.getSubUsedComponents((ComponentPkg) capellaElement);
    }
    return Collections.emptyList();
  }

  /**
   * Returns sub components of the component.
   */
  public Collection<Component> getAllSubUsedComponents(Component component) {
    return ComponentExt.getAllSubUsedComponents(component);
  }

  /**
   * Returns sub components of the component.
   */
  public List<Component> getAllSubDefinedComponents(Component component) {
    return ComponentExt.getAllSubDefinedComponents(component);
  }

  /**
   * Returns sub components of the component.
   */
  public List<Component> getAllSubDefinedComponents(BlockArchitecture architecture) {
    List<Component> comps = new ArrayList<>();
    LinkedList<Component> subs = new LinkedList<>();

    subs.addAll(ComponentExt.getSubDefinedComponents(architecture));
    while (!subs.isEmpty()) {
      Component sub = subs.removeFirst();
      comps.add(sub);
      List<Component> internal = ComponentExt.getSubDefinedComponents(sub);
      comps.addAll(internal);
      subs.addAll(internal);
    }
    return comps;
  }

  /**
   * Returns true if the edge represents a sub-link of implemented interface.
   */
  public boolean isASubImplementedLink(DEdge edge) {

    if ((edge.getSourceNode() instanceof DSemanticDecorator) && (edge.getTargetNode() instanceof DSemanticDecorator)) {
      Component comp = (Component) ((DSemanticDecorator) (edge.getSourceNode())).getTarget();
      Interface inter = (Interface) ((DSemanticDecorator) (edge.getTargetNode())).getTarget();

      List<Component> comps = new ArrayList<>();
      List<Component> compsVisited = new ArrayList<>();
      comps.addAll(getSuperComponents(comp));

      while (!comps.isEmpty()) {
        comp = comps.remove(0);
        if (comp.getImplementedInterfaces().contains(inter)
            && DiagramServices.getDiagramServices().isOnDiagram(edge.getParentDiagram(), comp)) {
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
  public boolean isASuperImplementedLink(DEdge edge) {

    if ((edge.getSourceNode() instanceof DSemanticDecorator) && (edge.getTargetNode() instanceof DSemanticDecorator)) {
      Component comp = (Component) ((DSemanticDecorator) (edge.getSourceNode())).getTarget();
      Interface inter = (Interface) ((DSemanticDecorator) (edge.getTargetNode())).getTarget();

      List<Component> comps = new ArrayList<>();
      List<Component> compsVisited = new ArrayList<>();
      comps.addAll(ComponentExt.getSubUsedComponents(comp));

      while (!comps.isEmpty()) {
        comp = comps.remove(0);
        if (comp.getImplementedInterfaces().contains(inter)
            && DiagramServices.getDiagramServices().isOnDiagram(edge.getParentDiagram(), comp)) {
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
  public boolean isASubUsedLink(DEdge edge) {

    if ((edge.getSourceNode() instanceof DSemanticDecorator) && (edge.getTargetNode() instanceof DSemanticDecorator)) {
      Component comp = (Component) ((DSemanticDecorator) (edge.getSourceNode())).getTarget();
      Interface inter = (Interface) ((DSemanticDecorator) (edge.getTargetNode())).getTarget();

      List<Component> comps = new ArrayList<>();
      List<Component> compsVisited = new ArrayList<>();
      comps.addAll(getSuperComponents(comp));

      while (!comps.isEmpty()) {
        comp = comps.remove(0);
        if (comp.getUsedInterfaces().contains(inter)
            && DiagramServices.getDiagramServices().isOnDiagram(edge.getParentDiagram(), comp)) {
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
  public boolean isASuperUsedLink(DEdge edge) {

    if ((edge.getSourceNode() instanceof DSemanticDecorator) && (edge.getTargetNode() instanceof DSemanticDecorator)) {
      Component comp = (Component) ((DSemanticDecorator) (edge.getSourceNode())).getTarget();
      Interface inter = (Interface) ((DSemanticDecorator) (edge.getTargetNode())).getTarget();

      List<Component> comps = new ArrayList<>();
      List<Component> compsVisited = new ArrayList<>();
      comps.addAll(ComponentExt.getSubUsedComponents(comp));

      while (!comps.isEmpty()) {
        comp = comps.remove(0);
        if (comp.getUsedInterfaces().contains(inter)
            && DiagramServices.getDiagramServices().isOnDiagram(edge.getParentDiagram(), comp)) {
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
   * 
   * @param port
   *          the given componentPort
   * @return true, if is standard port
   */
  public boolean isStandardPort(EObject port) {
    return PortExt.isStandardPort(port);
  }

  /**
   * Checks if port is a flow port.
   * 
   * @param port
   *          the given componentPort
   * @return true, if is a flow port
   */
  public boolean isFlowPort(EObject port) {
    return PortExt.isFlowPort(port);
  }

  /**
   * Checks if port is an in flow port.
   * 
   * @param port
   *          the given componentPort
   * @return true, if is in flow port
   */
  public boolean isInFlowPort(EObject port) {
    return PortExt.isInFlowPort(port);
  }

  /**
   * Checks if port is an (strictly) in flow port.
   * 
   * @param port
   *          the given componentPort
   * @return true, if is in flow port
   */
  public boolean isInStrictFlowPort(EObject port) {
    return PortExt.isInStrictFlowPort(port);
  }

  /**
   * Checks if port is an out flow port.
   * 
   * @param port
   *          the given componentPort
   * @return true, if is out flow port
   */
  public boolean isOutFlowPort(EObject port) {
    return PortExt.isOutFlowPort(port);
  }

  /**
   * Checks if port is an (strictly) out flow port.
   * 
   * @param port
   *          the given componentPort
   * @return true, if is out flow port
   */
  public boolean isOutStrictFlowPort(EObject port) {
    return PortExt.isOutStrictFlowPort(port);
  }

  /**
   * Checks if port is an (strictly) in-out flow port.
   * 
   * @param port
   *          the given componentPort
   * @return true, if is out flow port
   */
  public boolean isInoutStrictFlowPort(EObject port) {
    return PortExt.isInoutStrictFlowPort(port);
  }

  /**
   * Returns whether the edge between preSourceView and preTargetView is valid to create a ComponentExchange Should be
   * named isValidCreationConnectionWithPort
   */
  public boolean isValidCreationABComponentExchange(EObject root, DSemanticDecorator preSourceView,
      DSemanticDecorator preTargetView) {

    boolean sourceValid = true;
    boolean targetValid = true;

    // diagram target
    EObject preSource = preSourceView.getTarget();
    EObject preTarget = preTargetView.getTarget();

    // component
    EObject sourceComponent = getComponentType(preSourceView);
    EObject targetComponent = getComponentType(preTargetView);

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
    if (sourceComponent instanceof PhysicalComponent) {
      sourceValid = (sourceValid)
          ? (((PhysicalComponent) sourceComponent).getNature() != PhysicalComponentNature.NODE
              || ComponentExt.isActor(sourceComponent))
          : false;
    }
    if (targetComponent instanceof PhysicalComponent) {
      targetValid = (targetValid)
          ? ((PhysicalComponent) targetComponent).getNature() != PhysicalComponentNature.NODE
              || ComponentExt.isActor(targetComponent)
          : false;
    }
    return sourceValid && targetValid;

  }

  /**
   * Returns a unique name for a part If there is no part with its type name, use the type name otherwise, add to type
   * name a final sequence with number
   */
  public static String getPartUniqueName(Part part) {
    int i = 0;
    boolean isCorrectlyNamed = false;
    AbstractType type = part.getAbstractType();
    String racine = type.getName();
    String name = racine;
    while (!isCorrectlyNamed) {
      boolean nameExist = false;
      for (EObject object : part.eContainer().eContents()) {
        if (object instanceof Part && ((Part) object).getName().equals(name)) {
          i++;
          name = racine + ICommonConstants.WHITE_SPACE_CHARACTER + i;
          nameExist = true;
          break;
        }
      }
      if (!nameExist) {
        isCorrectlyNamed = true;
      }
    }
    return name;
  }

  public boolean isValidCreationPABComponentExchange(EObject container, DSemanticDecorator sourceView) {

    if ((sourceView == null) || (sourceView.getTarget() == null)) {
      return false;
    }

    if (sourceView.getTarget() instanceof PhysicalPort) {
      return false;
    }

    EObject source = getComponentType(sourceView);
    return isAbstractActorOrNotNodeComponent(source);
  }

  /**
   * Returns whether the source and target elements are both source/target for a component exchange in the PAB diagram
   */
  public boolean isValidCreationPABComponentExchange(EObject container, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {

    if (!isValidCreationABComponentExchange(container, sourceView, targetView)) {
      return false;
    }

    EObject source = getComponentType(sourceView);
    EObject target = getComponentType(targetView);

    return isAbstractActorOrNotNodeComponent(source) && isAbstractActorOrNotNodeComponent(target);
  }

  public boolean isValidCreationPABDelegationExchange(EObject container, DSemanticDecorator sourceView) {

    if ((sourceView == null) || (sourceView.getTarget() == null)) {
      return false;
    }

    if (sourceView.getTarget() instanceof PhysicalPort) {
      return false;
    }

    EObject source = getComponentType(sourceView);
    return isAbstractActorOrNotNodeComponent(source);
  }

  /**
   * Returns whether the source and target elements are both source/target for a component exchange in the PAB diagram
   */
  public boolean isValidCreationPABDelegationExchange(EObject container, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {

    if (!isValidCreationABDelegationExchange(container, sourceView, targetView)) {
      return false;
    }

    EObject source = getComponentType(sourceView);
    EObject target = getComponentType(targetView);

    return (source != target) && isAbstractActorOrNotNodeComponent(source) && isAbstractActorOrNotNodeComponent(target);
  }

  /**
   * Returns whether the source is valid for the creation of a physical link in the PAB diagram
   */
  public boolean isValidCreationPhysicalLink(EObject container) {
    EObject source = getComponentType(container);
    return isNodeComponent(source);
  }

  /**
   * Returns whether the source and target elements are both source/target for a physical link in the PAB diagram
   */
  public boolean isValidCreationABPhysicalLink(EObject container, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {
    boolean sourceValid = false;
    boolean targetValid = false;

    DSemanticDecorator sourceElement = sourceView;
    DSemanticDecorator targetElement = targetView;

    EObject preSource = sourceElement.getTarget();
    EObject preTarget = targetElement.getTarget();

    if (TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(preSource))) {
      if (preSource == preTarget) {
        return false;
      }

    } else {
      if (preSource == preTarget) {
        if (preSource instanceof PhysicalPort) {
          return false;
        }
        return true;
      }
    }

    if (preSource instanceof PhysicalPort) {
      sourceValid = true;
    } else if (preSource instanceof Part) {
      Type type = ((Part) preSource).getType();
      if (type instanceof Component) {
        sourceValid = true;
      }
    } else if (preSource instanceof Component) {
      sourceValid = true;
    }

    if (preTarget instanceof PhysicalPort) {
      targetValid = true;
    } else if (preTarget instanceof Part) {
      Type type = ((Part) preTarget).getType();
      if (type instanceof Component) {
        targetValid = true;
      }
    } else if (preTarget instanceof Component) {
      targetValid = true;
    }

    if (!(sourceValid && targetValid)) {
      return false;
    }

    EObject source = getComponentType(sourceView);
    EObject target = getComponentType(targetView);

    if ((source instanceof PhysicalPort) && (target instanceof PhysicalPort)) {
      if (TriStateBoolean.False.equals(CapellaProjectHelper.isReusableComponentsDriven(source))) {
        return source.eContainer() != target.eContainer();
      }
      return source.eContainer() == target.eContainer();

    } else if (source instanceof PhysicalPort) {
      if (TriStateBoolean.False.equals(CapellaProjectHelper.isReusableComponentsDriven(source))
          && (source.eContainer() == target)) {
        return false;
      }
      return isNodeComponent(source.eContainer()) && isNodeComponent(target);

    } else if (target instanceof PhysicalPort) {
      if (TriStateBoolean.False.equals(CapellaProjectHelper.isReusableComponentsDriven(source))
          && (source == target.eContainer())) {
        return false;
      }
      return isNodeComponent(source) && isNodeComponent(target.eContainer());

    } else {
      return isNodeComponent(source) && isNodeComponent(target);
    }
  }

  /**
   * Returns whether given element is an abstract actor or a not-node physical component
   */
  protected boolean isAbstractActorOrNotNodeComponent(EObject source) {
    return ((source instanceof Component && ((Component) source).isActor()) || ((source instanceof PhysicalComponent)
        && (((PhysicalComponent) source).getNature() != PhysicalComponentNature.NODE)));
  }

  /**
   * Returns whether given element is an abstract actor or a node physical component
   */
  protected boolean isNodeComponent(EObject source) {
    return source instanceof PhysicalComponent
        && ((PhysicalComponent) source).getNature() == PhysicalComponentNature.NODE;
  }

  /**
   * Returns whether the edge between preSourceView and preTargetView is valid to create a Delegation
   */
  public boolean isValidCreationABDelegationExchange(EObject root, DSemanticDecorator preSourceView,
      DSemanticDecorator preTargetView) {

    EObject preSource = preSourceView.getTarget();
    EObject preTarget = preTargetView.getTarget();

    if (preSource == preTarget) {
      return false;
    }

    // If connected to two ports, they should have same kind / orientation
    if (preSource instanceof Port && !(preSource instanceof ComponentPort)) {
      return false;
    }
    if (preTarget instanceof Port && !(preTarget instanceof ComponentPort)) {
      return false;
    }

    DSemanticDecorator sourcePart = preSourceView;
    DSemanticDecorator targetPart = preTargetView;

    if ((preSource instanceof Port) && (sourcePart.eContainer() != null)
        && (sourcePart.eContainer() instanceof AbstractDNode)) {
      sourcePart = (DSemanticDecorator) sourcePart.eContainer();
    }
    if ((preTarget instanceof Port) && (targetPart.eContainer() != null)
        && (targetPart.eContainer() instanceof AbstractDNode)) {
      targetPart = (DSemanticDecorator) targetPart.eContainer();
    }

    // If source NodeContainer contains target NodeContainer
    if ((sourcePart instanceof AbstractDNode)
        && ((sourcePart.getTarget() instanceof Part) || (sourcePart.getTarget() instanceof Component))) {
      if ((targetPart instanceof AbstractDNode)
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
  public boolean isValidCreationABComponentExchangeWithoutPorts(EObject root, DSemanticDecorator preSourceView,
      DSemanticDecorator preTargetView) {

    // allow only connection with parts

    boolean sourceValid = false;
    boolean targetValid = false;

    EObject preSource = preSourceView.getTarget();
    EObject preTarget = preTargetView.getTarget();

    EObject sourceComponent = getComponentType(preSourceView);
    EObject targetComponent = getComponentType(preTargetView);

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

  public boolean isValidCreationPABComponentExchangeWithoutPorts(EObject container, DSemanticDecorator sourceView) {

    if ((sourceView == null) || (sourceView.getTarget() == null)) {
      return false;
    }

    if (sourceView.getTarget() instanceof PhysicalPort) {
      return false;
    }

    EObject source = getComponentType(sourceView);
    return isAbstractActorOrNotNodeComponent(source);
  }

  /**
   * Returns whether the edge between preSourceView and preTargetView is valid to create a Component Exchange
   */
  public boolean isValidCreationPABComponentExchangeWithoutPorts(EObject root, DSemanticDecorator preSourceView,
      DSemanticDecorator preTargetView) {
    if (!isValidCreationABComponentExchangeWithoutPorts(root, preSourceView, preTargetView)) {
      return false;
    }

    EObject source = getComponentType(preSourceView);
    EObject target = getComponentType(preTargetView);

    if ((source == null) || (target == null)) {
      return false;

    } else if ((source instanceof PhysicalComponent) && (target instanceof PhysicalComponent)) {
      return (isAbstractActorOrNotNodeComponent(source) && isAbstractActorOrNotNodeComponent(target));
    }
    return false;
  }

  public Component getFirstComponent(BlockArchitecture architecture) {
    return BlockArchitectureExt.getOrCreateSystem(architecture);
  }

  public List<Component> getFirstComponents(BlockArchitecture architecture) {
    return BlockArchitectureExt.getFirstComponents(architecture);
  }

  public List<Component> getFirstComponents(ModellingBlock block) {
    ArrayList<Component> result = new ArrayList<>();
    result.add((Component) block);
    return result;
  }

  /**
   * Returns the component related to the element
   */
  public EObject getComponentType(Component element) {
    return element;
  }

  /**
   * Returns the component related to the element
   */
  public EObject getComponentType(Port element) {
    return element.eContainer();
  }

  /**
   * Returns the component related to the view
   */
  public EObject getComponentType(DSemanticDecorator targetView) {
    if (targetView.getTarget() != null) {
      if (targetView.getTarget() instanceof Component) {
        return getComponentType((Component) targetView.getTarget());
      }
      if (targetView.getTarget() instanceof Port) {
        return getComponentType((Port) targetView.getTarget());
      }
      if (targetView.getTarget() instanceof Part) {
        return getComponentType((Part) targetView.getTarget());
      }
      if (targetView.getTarget() instanceof AbstractPropertyValue) {
        return getComponentType((DSemanticDecorator) targetView.eContainer());
      }
      if (targetView.getTarget() instanceof ComponentPkg) {
        return targetView.getTarget();
      }
    }
    return null;
  }

  /**
   * Returns the component type for the target.
   * 
   * @param target
   *          the target.
   * @return the component type for the target.
   */
  public EObject getComponentType(EObject target) {

    if (target instanceof Component) {
      return getComponentType((Component) target);
    } else if (target instanceof Port) {
      return getComponentType((Port) target);
    } else if (target instanceof Part) {
      return getComponentType((Part) target);
    } else if (target instanceof ComponentPkg) {
      return target;
    }

    return null;
  }

  @Deprecated
  // Replaced by getComponentType
  public EObject getRelatedComponent(Part element) {
    return element.getAbstractType();
  }

  @Deprecated
  // Replaced by getComponentType
  public EObject getRelatedComponent(Port element) {
    return element.eContainer();
  }

  /**
   * Returns the component related to the element
   */
  public AbstractType getComponentType(Part element) {
    return element.getAbstractType();
  }

  /**
   * Create a component into the container
   * 
   * @param nameVariable
   *          interpreter-variable which will be store the new created component
   */
  public Component createComponent(CapellaElement container, String nameVariable) {
    Component component = createComponent(container, false, nameVariable);
    INamePrefixService prefixService = PlatformUI.getWorkbench().getService(INamePrefixService.class);
    String createdElementPrefix = prefixService.getPrefix(component);
    component.setName(CapellaServices.getService().getUniqueName(component, createdElementPrefix));
    return component;
  }

  private Component createComponentInternal(CapellaElement container, String nameVariable) {
    Component element = null;
    EStructuralFeature containerFeature = null;
    EObject containerObject = null;
    if ((container instanceof LogicalComponent) || (container instanceof LogicalComponentPkg)
        || (container instanceof LogicalArchitecture)) {
      element = LaFactory.eINSTANCE.createLogicalComponent();
      if (container instanceof LogicalComponent) {
        containerFeature = LaPackage.Literals.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENTS;
        containerObject = container;
      } else if (container instanceof LogicalComponentPkg) {
        containerFeature = LaPackage.Literals.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENTS;
        containerObject = container;
      } else if (container instanceof LogicalArchitecture) {
        LogicalComponentPkg pkg = ((LogicalArchitecture) container).getOwnedLogicalComponentPkg();
        if (pkg == null) {
          pkg = LaFactory.eINSTANCE.createLogicalComponentPkg();
          ((LogicalArchitecture) container).setOwnedLogicalComponentPkg(pkg);
        }
        containerFeature = LaPackage.Literals.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENTS;
        containerObject = pkg;
      }
    } else if ((container instanceof PhysicalComponent) || (container instanceof PhysicalComponentPkg)
        || (container instanceof PhysicalArchitecture)) {
      element = PaFactory.eINSTANCE.createPhysicalComponent();
      if (container instanceof PhysicalComponent) {
        containerFeature = PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS;
        containerObject = container;
      } else if (container instanceof PhysicalComponentPkg) {
        containerFeature = PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS;
        containerObject = container;
      } else if (container instanceof PhysicalArchitecture) {
        PhysicalComponentPkg pkg = ((PhysicalArchitecture) container).getOwnedPhysicalComponentPkg();
        if (pkg == null) {
          pkg = PaFactory.eINSTANCE.createPhysicalComponentPkg();
          ((PhysicalArchitecture) container).setOwnedPhysicalComponentPkg(pkg);
        }
        containerFeature = PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS;
        containerObject = pkg;
      }
    } else if ((container instanceof Entity) || (container instanceof EntityPkg)
        || (container instanceof OperationalAnalysis)) {
      element = OaFactory.eINSTANCE.createEntity();
      if (container instanceof Entity) {
        containerFeature = OaPackage.Literals.ENTITY__OWNED_ENTITIES;
        containerObject = container;
      } else if (container instanceof EntityPkg) {
        containerFeature = OaPackage.Literals.ENTITY_PKG__OWNED_ENTITIES;
        containerObject = container;
      } else if (container instanceof OperationalAnalysis) {
        EntityPkg pkg = ((OperationalAnalysis) container).getOwnedEntityPkg();
        if (pkg == null) {
          pkg = OaFactory.eINSTANCE.createEntityPkg();
          ((OperationalAnalysis) container).setOwnedEntityPkg(pkg);
        }
        containerFeature = OaPackage.Literals.ENTITY_PKG__OWNED_ENTITIES;
        containerObject = pkg;
      }
    } else if (container instanceof SystemComponent || container instanceof SystemComponentPkg
        || container instanceof SystemAnalysis) {
      element = CtxFactory.eINSTANCE.createSystemComponent();
      if (container instanceof SystemComponent) {
        containerFeature = CtxPackage.Literals.SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENTS;
        containerObject = container;
      } else if (container instanceof SystemComponentPkg) {
        containerFeature = CtxPackage.Literals.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENTS;
        containerObject = container;
      } else if (container instanceof SystemAnalysis) {
        SystemComponentPkg pkg = ((SystemAnalysis) container).getOwnedSystemComponentPkg();
        if (pkg == null) {
          pkg = CtxFactory.eINSTANCE.createSystemComponentPkg();
          ((SystemAnalysis) container).setOwnedSystemComponentPkg(pkg);
        }
        containerFeature = CtxPackage.Literals.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENTS;
        containerObject = pkg;
      }
    }

    if ((element != null) && (containerObject != null) && (containerFeature != null)) {
      ((EList) containerObject.eGet(containerFeature)).add(element);
    }

    if (nameVariable != null) {
      InterpreterUtil.getInterpreter(container).setVariable(nameVariable, element);
    }
    return element;
  }

  public Component createComponent(CapellaElement container, boolean creationService, String nameVariable) {
    Component component = createComponentInternal(container, nameVariable);
    if (creationService) {
      CapellaServices.getService().creationService(component);
    }
    return component;
  }

  public Component createComponent(CapellaElement container) {
    return createComponent(container, true, null);
  }

  /**
   * Create a logical actor into the container
   * 
   * @param nameVariable
   *          interpreter-variable which will be store the new created actor (create service is not called.)
   */
  public Component createActor(CapellaElement container, String nameVariable) {
    return createActor(container, false, nameVariable);
  }

  public Component createActor(CapellaElement container) {
    return createActor(container, true, null);
  }

  /**
   * Create a logical actor into the container
   * 
   * @param nameVariable
   *          interpreter-variable which will be store the new created actor (create service is not called.)
   */
  public Component createActor(CapellaElement container, boolean creationService, String nameVariable) {
    Component component = createComponent(container, nameVariable);

    if (component instanceof Entity) {
      component.setHuman(true);
    } else if (component instanceof PhysicalComponent) {
      PhysicalComponent physicalComponent = (PhysicalComponent) component;
      PhysicalComponentNature nature = computePhysicalActorNature(container);
      physicalComponent.setNature(nature);
    }

    component.setActor(true);

    if (creationService) {
      CapellaServices.getService().creationService(component);
    } else {
      INamePrefixService prefixService = PlatformUI.getWorkbench().getService(INamePrefixService.class);
      String createdElementPrefix = prefixService.getPrefix(component);
      component.setName(CapellaServices.getService().getUniqueName(component, createdElementPrefix));
    }
    return component;
  }

  public PhysicalComponentNature computePhysicalActorNature(CapellaElement container) {

    PhysicalComponentNature nature = PhysicalComponentNature.NODE;
    PhysicalComponent parentComponent = null;

    if (container instanceof PhysicalComponent) {
      parentComponent = (PhysicalComponent) container;
    } else if (container instanceof PhysicalComponentPkg) {
      PhysicalComponentPkg pkg = (PhysicalComponentPkg) container;
      parentComponent = (PhysicalComponent) ComponentPkgExt.getParentComponent(pkg);
    }

    if (parentComponent != null && parentComponent.getNature() != PhysicalComponentNature.UNSET) {
      nature = parentComponent.getNature();
    }

    return nature;
  }

  /**
   * Used to show the link between a PV/PVG and its containing PVG
   */
  public Collection<CapellaElement> PVinPVG(CapellaElement elem) {
    Collection<CapellaElement> result = new ArrayList<>();
    for (EObject content : elem.eContents()) {
      if (content instanceof AbstractPropertyValue || content instanceof PropertyValueGroup) {
        result.add((CapellaElement) content);
      }
    }
    return result;
  }

  /**
   * Used to show the link between a PV/PVG and and a component (while the PV/PVG may be applied to a component, only
   * the part is represented in the diagram!)
   */
  public Collection<CapellaElement> computeValuedElements(CapellaElement elem) {
    Collection<CapellaElement> result = new ArrayList<>();
    if (elem instanceof AbstractPropertyValue) {
      AbstractPropertyValue abstractPropertyValue = (AbstractPropertyValue) elem;
      for (CapellaElement element : abstractPropertyValue.getValuedElements()) {
        result.add(element);
        if (element instanceof Component) {
          Component comp = (Component) element;
          for (Part part : comp.getRepresentingParts()) {
            result.add(part);
          }
        }
      }
    } else if (elem instanceof PropertyValueGroup) {
      PropertyValueGroup propertyValueGroup = (PropertyValueGroup) elem;
      for (CapellaElement element : propertyValueGroup.getValuedElements()) {
        result.add(element);
        if (element instanceof Component) {
          Component comp = (Component) element;
          for (Part part : comp.getRepresentingParts()) {
            result.add(part);
          }
        }
      }
    }
    return result;
  }

  /**
   * Used to show the applied PV/PVG (while the PV/PVG may be applied to a component, only the part is represented in
   * the diagram!)
   */
  public Collection<CapellaElement> computeAppliedPV(CapellaElement elem) {
    Collection<CapellaElement> result = new ArrayList<>();
    result.addAll(elem.getAppliedPropertyValueGroups());
    result.addAll(elem.getAppliedPropertyValues());
    // if we have a part, we also add the PV and PVG applied to the type
    if (elem instanceof Part) {
      Part part = (Part) elem;
      result.addAll(part.getType().getAppliedPropertyValueGroups());
      result.addAll(part.getType().getAppliedPropertyValues());
    }
    return result;
  }

  /**
   * Return the label to be displayed for PV / PVG in diagrams
   */
  public String computePVLabel(EObject PV) {
    return EObjectExt.getText(PV);
  }

  /**
   * Return Customized label for component
   * 
   * @param property
   *          : component
   * @return : customized lable for component
   */
  public String computeComponentLabel(EObject component) {
    return EObjectExt.getText(component);
  }

  public String computePartLabelMultiPartOnly(Part part) {
    if (isMultipartMode(part)) {
      return computePartLabel(part);
    }
    return ICommonConstants.EMPTY_STRING;
  }

  public String computePartLabel(Part part) {

    if (isMultipartMode(part)) {
      String mul = PropertyNamingHelper.multiplicityToStringDisplay(part);

      NumericValue ownedMaxCard = part.getOwnedMaxCard();
      NumericValue ownedMinCard = part.getOwnedMinCard();
      if ((ownedMinCard == null) && (ownedMaxCard == null)) {
        return getDefaultKindLabel(part);
      } else if (getCardValue(part, ownedMinCard).equalsIgnoreCase("1") //$NON-NLS-1$
          && getCardValue(part, ownedMaxCard).equalsIgnoreCase("1")) {//$NON-NLS-1$
        return getDefaultKindLabel(part);
      } else if (getCardValue(part, ownedMinCard).equalsIgnoreCase(ICommonConstants.EMPTY_STRING)
          && getCardValue(part, ownedMaxCard).equalsIgnoreCase(ICommonConstants.EMPTY_STRING)) {
        return getDefaultKindLabel(part);
      }

      return mul + ICommonConstants.WHITE_SPACE_CHARACTER + EObjectExt.getText(part);
    }

    if (part.getName().length() == 0) {
      return "[" + part.eClass().getName() + "]"; //$NON-NLS-2$ //$NON-NLS-1$
    }

    // if we are in mono part mode, we display the name of the type
    return EObjectExt.getText(part.getType());
  }

  public String computePartLabelMultiPartMode(Part part) {

    if (isMultipartMode(part)) {
      String mul = PropertyNamingHelper.multiplicityToStringDisplay(part);

      NumericValue ownedMaxCard = part.getOwnedMaxCard();
      NumericValue ownedMinCard = part.getOwnedMinCard();
      if ((ownedMinCard == null) && (ownedMaxCard == null)) {
        return getDefaultKindLabel(part);
      } else if (getCardValue(part, ownedMinCard).equalsIgnoreCase("1") //$NON-NLS-1$
          && getCardValue(part, ownedMaxCard).equalsIgnoreCase("1")) {//$NON-NLS-1$
        return getDefaultKindLabel(part);
      } else if (getCardValue(part, ownedMinCard).equalsIgnoreCase(ICommonConstants.EMPTY_STRING)
          && getCardValue(part, ownedMaxCard).equalsIgnoreCase(ICommonConstants.EMPTY_STRING)) {
        return getDefaultKindLabel(part);
      }

      return mul + ICommonConstants.WHITE_SPACE_CHARACTER + EObjectExt.getText(part);
    }

    String result = ICommonConstants.EMPTY_STRING;
    if (part.getAbstractType() instanceof ConfigurationItem) {
      ConfigurationItem type = (ConfigurationItem) part.getAbstractType();
      result += "[" + type.getKind().getName() + "] "; //$NON-NLS-1$ //$NON-NLS-2$
    }
    if (part.getName().length() == 0) {
      result += "[" + part.eClass().getName() + "]"; //$NON-NLS-2$ //$NON-NLS-1$
    } else {
      result += part.getName();
    }
    return result;
  }

  private String getDefaultKindLabel(Part part) {
    return ICommonConstants.WHITE_SPACE_CHARACTER + EObjectExt.getText(part);
  }

  private String getCardValue(Part part, NumericValue card) {
    return PropertyNamingHelper.getCardValue(card);
  }

  /**
   * Returns targets for the LAB_ComponentExchangeByGroup mapping. (ie all connected parts)
   * 
   * @param source
   * @return
   */
  public Collection<EObject> getComponentExchangeByGroupTargets(EObject source) {
    Collection<EObject> semantics = new HashSet<>();

    EObject related = source;
    if (related instanceof Part) {
      Part sourcePart = (Part) related;
      Component sourceComponent = (Component) ((Part) related).getAbstractType();

      Collection<ComponentExchange> relatedExchanges = new HashSet<>();
      relatedExchanges.addAll(getCache(ComponentExt::getAllRelatedComponentExchange, sourcePart));
      relatedExchanges.addAll(ComponentExt.getAllRelatedComponentExchange(sourceComponent, false));

      for (ComponentExchange relatedExchange : relatedExchanges) {
        if (!ComponentExchangeKind.DELEGATION.equals(relatedExchange.getKind())) {
          EObject src = getSourcePart(relatedExchange);
          if (src == null) {
            semantics.addAll(
                getCache(ComponentExt::getRepresentingParts, ComponentExchangeExt.getSourceComponent(relatedExchange)));
          } else {
            semantics.add(src);
          }

          EObject target = getTargetPart(relatedExchange);
          if (target == null) {
            semantics.addAll(
                getCache(ComponentExt::getRepresentingParts, ComponentExchangeExt.getTargetComponent(relatedExchange)));
          } else {
            semantics.add(src);
          }
        }
      }
    }

    semantics.remove(related);
    return semantics;
  }

  public Collection<CapellaElement> getComponentExchangeByGroupSemantics(EObject source) {

    Object sourceView = getInterpreterVariable(source, IInterpreterSiriusVariables.SOURCE_VIEW);
    Object targetView = getInterpreterVariable(source, IInterpreterSiriusVariables.TARGET_VIEW);

    return getComponentExchangeByGroupSemantics(source, (DSemanticDecorator) sourceView,
        (DSemanticDecorator) targetView);
  }

  /**
   * Returns all component exchange for mapping LAB_ComponentExchangeByGroup
   * 
   * @param target
   * @param source2
   */
  public Collection<CapellaElement> getComponentExchangeByGroupSemantics(EObject source, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {

    Part sourcePart = (Part) sourceView.getTarget();
    Part targetPart = (Part) targetView.getTarget();

    Component sourceComponent = (Component) sourcePart.getAbstractType();
    Component targetComponent = (Component) targetPart.getAbstractType();

    // Retrieve all related component exchange from source
    Collection<CapellaElement> sources = new ArrayList<>();
    for (CapellaElement element : getCache(ComponentExt::getAllRelatedComponentExchange, sourcePart)) {
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
    Collection<CapellaElement> targets = new ArrayList<>();
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
    List<CapellaElement> target2 = new ArrayList<>(sources);
    Collections.sort(target2, getComparator());
    return target2;
  }

  public Collection<CapellaElement> getComponentExchangeByGroupOrientedSemanticElts(final EObject source) {

    Object sourceView = getInterpreterVariable(source, IInterpreterSiriusVariables.SOURCE_VIEW);
    Object targetView = getInterpreterVariable(source, IInterpreterSiriusVariables.TARGET_VIEW);

    return getComponentExchangeByGroupOrientedSemanticElts(source, (DSemanticDecorator) sourceView,
        (DSemanticDecorator) targetView);
  }

  /**
   * Returns all component exchange for mapping xAB_ComponentExchangeByGroup_Oriented associated semantic elements.
   * Returns the outgoing componentExchanges
   */
  public Collection<CapellaElement> getComponentExchangeByGroupOrientedSemanticElts(final EObject source,
      DSemanticDecorator sourceView, DSemanticDecorator targetView) {

    Collection<CapellaElement> componentExchanges = getComponentExchangeByGroupSemantics(source, sourceView,
        targetView);
    Predicate<EObject> isPartSourceForCE = eObj -> {
      if (eObj instanceof ComponentExchange) {
        ComponentExchange ce = (ComponentExchange) eObj;
        // TODO: check this
        Part sourcePart = (Part) source;

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
    };

    return Lists.newArrayList(Iterables.filter(componentExchanges, isPartSourceForCE));

  }

  /**
   * Returns target for mapping LAB_ComponentExchangeByGroup
   */
  public EObject getComponentExchangeByGroupTarget(EObject related) {
    return related;
  }

  public boolean isValidComponentExchangeByGroupOrientedEdge(EObject semantic, DSemanticDecorator source,
      DSemanticDecorator target) {

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(source);
    DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);
    // check the activation of the filters
    if (!FilterHelper.isDesactivatedOnce(IMappingNameConstants.HIDE_CE_BY_GROUP_ORIENTED, descriptor)) {
      return false;
    }

    // Retrieve the first correct semantic element between both elements
    Collection<CapellaElement> result = getComponentExchangeByGroupOrientedSemanticElts(source.getTarget(), source,
        target);
    if (result.isEmpty()) {
      return false;
    }
    CapellaElement firstCE = result.iterator().next();

    if (firstCE instanceof ComponentExchange) {
      Part part = getSourcePart((ComponentExchange) firstCE);
      Part viewPart = ((Part) source.getTarget());
      if (part != null && !part.equals(viewPart)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Returns whether the mapping LAB_ComponentExchangeByGroup should be displayed between both views. (avoid any double
   * links, source>target and target>source)
   * 
   * @param communication
   * @param source
   * @param target
   * @return
   */
  public boolean isValidComponentExchangeByGroupEdge(EObject communication, DSemanticDecorator source,
      DSemanticDecorator target) {

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(source);
    DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);
    if (!FilterHelper.isDesactivatedOnce(IMappingNameConstants.HIDE_CE_BY_GROUP, descriptor)) {
      return false;
    }

    EObject semantic = communication;
    // Retrieve the first correct semantic element between both elements
    Collection<CapellaElement> result = getComponentExchangeByGroupSemantics(source.getTarget(), source, target);
    semantic = result.iterator().next();

    if (semantic instanceof ComponentExchange) {
      Part part = getSourcePart((ComponentExchange) semantic);
      Part viewPart = ((Part) source.getTarget());
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

    return true;
  }

  /**
   * Returns true whether the filter has been activated at least one time. Until the filter has not yet been activated,
   * edges are not created
   * 
   * @deprecated the description is not clear. Use FilterHelper.isDesactivatedOnce
   */
  @Deprecated
  public boolean isFirstFilterActive(FilterDescription filter, DDiagram diagram) {
    DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);
    return FilterHelper.isDesactivatedOnce(filter.getName(), descriptor);
  }

  /**
   * Returns whether the edge is the edge which should be displayed for a Connection.
   */
  public boolean isValidComponentExchangeByDelegationEdge(EObject communication, DSemanticDecorator source,
      DSemanticDecorator target) {

    if (!(communication instanceof ComponentPort)) {
      return false;
    }

    if (source == null || target == null || source == target) {
      return false;
    }

    if ((source.eContainer() == null) || (target.eContainer() == null)) {
      return false;
    }

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(source);
    DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);
    if (!FilterHelper.isDesactivatedOnce(IMappingNameConstants.HIDE_CE_BY_DELEGATION, descriptor)) {
      return false;
    }

    Collection<? extends EObject> semantics = getComponentExchangeByDelegationSemantics(communication, source, target);

    // We needs to recompute this, sirius make supposition, if no semanticElements, semanticElements = target...
    if (semantics.isEmpty()) {
      return false;
    }

    if (source instanceof EdgeTarget) {
      EdgeTarget view = (EdgeTarget) source;
      EObject viewTarget = source.getTarget();
      if (viewTarget instanceof ComponentPort) {
        ComponentPort sourcePort = (ComponentPort) viewTarget;

        // Model based (for further)
        boolean isValid = true;

        Collection<ComponentPort> delegating = PortExt.getAllDelegatingComponentPorts(sourcePort);
        EObject parent = view.eContainer().eContainer();
        while (isValid && (parent != null) && (parent instanceof DNodeContainer)) {
          DNodeContainer pNode = (DNodeContainer) parent;
          for (DNode border : pNode.getOwnedBorderedNodes()) {
            if (border == target) {
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

    if (target instanceof EdgeTarget) {
      EdgeTarget view = (EdgeTarget) target;
      EObject viewTarget = target.getTarget();
      if (viewTarget instanceof ComponentPort) {
        ComponentPort sourcePort = (ComponentPort) viewTarget;

        // Model based (for further)

        boolean isValid = true;

        Collection<ComponentPort> delegating = PortExt.getAllDelegatingComponentPorts(sourcePort);
        EObject parent = view.eContainer().eContainer();
        while (isValid && (parent != null) && (parent instanceof DNodeContainer)) {
          DNodeContainer pNode = (DNodeContainer) parent;
          for (DNode border : pNode.getOwnedBorderedNodes()) {
            if (border == source) {
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

    return isUndoublonLink(source, target);
  }

  /**
   * Returns whether the edge is the edge which should be displayed for a ComponentExchange.
   * 
   * @deprecated Should be replaced by isValidComponentExchangeEdge
   */
  @Deprecated
  public boolean isValidConnectionEdge(ComponentExchange communication, DSemanticDecorator source,
      DSemanticDecorator target) {
    return isValidComponentExchangeEdge(communication, source, target);
  }

  /**
   * Returns whether the edge is the edge which should be displayed for a Connection.
   */
  public boolean isValidComponentExchangeEdge(EObject communication, DSemanticDecorator source,
      DSemanticDecorator target) {
    if (communication instanceof ComponentExchange) {
      return isValidLinkEdge(getComponentExchangeWrapper((ComponentExchange) communication), source, target, true)
          && !isComponentExchangeCategoryEdgeDisplayed((ComponentExchange) communication, source, target);
    }
    return false;
  }

  public boolean isComponentExchangeCategoryEdgeDisplayed(ComponentExchange exchange, DSemanticDecorator source,
      DSemanticDecorator target) {
    Set<DEdge> edgesOfSource = getEdgesOnOwnedBoderedNodesOfContainer(source);
    Set<DEdge> edgesOfTarget = getEdgesOnOwnedBoderedNodesOfContainer(target);

    if (edgesOfTarget.isEmpty() || edgesOfSource.isEmpty()) {
      return false;
    }

    edgesOfTarget.retainAll(edgesOfSource);
    Set<DEdge> commonEdges = edgesOfTarget;

    for (DEdge edge : commonEdges) {
      EObject edgeTarget = edge.getTarget();
      if (edgeTarget instanceof ComponentExchangeCategory) {
        ComponentExchangeCategory category = (ComponentExchangeCategory) edgeTarget;
        if (ComponentExchangeCategoryExt.getCoveredComponentExchanges(category).contains(exchange)) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * Returns whether the edge is the edge which should be displayed for a PortAllocation.
   */
  public boolean isValidPortAllocationEdge(PortAllocation communication, DSemanticDecorator source,
      DSemanticDecorator target) {
    return isValidLinkEdge(getPortAllocationWrapper(communication), source, target, true);
  }

  /**
   * Returns whether the edge is the edge which should be displayed for a PortAllocation.
   */
  public boolean isValidComponentPortAllocationEdge(ComponentPortAllocation communication, DSemanticDecorator source,
      DSemanticDecorator target) {
    return isValidLinkEdge(getComponentPortAllocationWrapper(communication), source, target, true)
        && !isPhysicalCategoryEdgeDisplayed(communication, source, target);
  }

  /**
   * Returns whether the edge is the edge which should be displayed for an Interaction.
   */
  public boolean isValidInteractionEdge(FunctionalExchange communication, DSemanticDecorator source,
      DSemanticDecorator target) {
    return isValidLinkEdge(getFunctionalExchangeWrapper(communication), source, target, true);
  }

  /**
   * Returns whether the edge is the edge which should be displayed for a Functional Exchange.
   */
  public boolean isValidFunctionalExchangeEdge(FunctionalExchange communication, DSemanticDecorator source,
      DSemanticDecorator target) {
    return isValidLinkEdge(getFunctionalExchangeWrapper(communication), source, target, true);
  }

  /**
   * Returns whether the edge is the edge which should be displayed for a Connection.
   */
  public boolean isValidPhysicalLinkEdge(PhysicalLink link, DSemanticDecorator source, DSemanticDecorator target) {
    return isValidLinkEdge(getPhysicalLinkWrapper(link), source, target, true)
        && !isPhysicalCategoryEdgeDisplayed(link, source, target);
  }

  public boolean isPhysicalCategoryEdgeDisplayed(PhysicalLink link, DSemanticDecorator source,
      DSemanticDecorator target) {
    Set<DEdge> edgesOfSource = getEdgesOnOwnedBoderedNodesOfContainer(source);
    Set<DEdge> edgesOfTarget = getEdgesOnOwnedBoderedNodesOfContainer(target);

    if (edgesOfTarget.isEmpty() || edgesOfSource.isEmpty()) {
      return false;
    }

    edgesOfTarget.retainAll(edgesOfSource);
    Set<DEdge> commonEdges = edgesOfTarget;

    for (DEdge edge : commonEdges) {
      EObject edgeTarget = edge.getTarget();
      if (edgeTarget instanceof PhysicalLinkCategory) {
        PhysicalLinkCategory category = (PhysicalLinkCategory) edgeTarget;
        if (PhysicalLinkCategoryExt.getCoveredPhysicalLinks(category).contains(link)) {
          return true;
        }
      }
    }

    return false;
  }

  public boolean isPhysicalCategoryEdgeDisplayed(ComponentPortAllocation cpa, DSemanticDecorator source,
      DSemanticDecorator target) {
    Set<DEdge> edgesOfSource = getEdgesOnOwnedBoderedNodesOfContainer(source);
    Set<DEdge> edgesOfTarget = getEdgesOnOwnedBoderedNodesOfContainer(target);

    if (edgesOfTarget.isEmpty() || edgesOfSource.isEmpty()) {
      return false;
    }

    edgesOfTarget.retainAll(edgesOfSource);
    Set<DEdge> commonEdges = edgesOfTarget;

    for (DEdge edge : commonEdges) {
      EObject edgeTarget = edge.getTarget();
      if (edgeTarget instanceof PhysicalLinkCategory) {
        PhysicalLinkCategory category = (PhysicalLinkCategory) edgeTarget;
        if (PhysicalLinkCategoryExt.getCoveredComponentPortAllocations(category).contains(cpa)) {
          return true;
        }
      }
    }

    return false;
  }

  private Set<DEdge> getEdgesOnOwnedBoderedNodesOfContainer(DSemanticDecorator source) {
    Set<DEdge> edges = new HashSet<>();
    if (source instanceof DNode) {
      AbstractDNode portContainer = (AbstractDNode) source.eContainer();
      for (DNode borderedNode : portContainer.getOwnedBorderedNodes()) {
        edges.addAll(borderedNode.getOutgoingEdges());
        edges.addAll(borderedNode.getIncomingEdges());
      }
    }
    return edges;
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

    public void setData(EObject object);

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
      if (!link.getLinkEnds().isEmpty()) {
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
    public void setData(EObject obj) {
      if (obj instanceof PhysicalLink) {
        link = (PhysicalLink) obj;
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
      if (source instanceof ComponentPort) {
        return PortExt.getAllLinkedDelegatedComponentPorts((ComponentPort) source);
      }
      return Collections.singletonList(getSourcePort());
    }

    @Override
    public Collection<? extends Port> getTargetPorts() {
      Port source = getTargetPort();
      if (source instanceof ComponentPort) {
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
    public void setData(EObject obj) {
      if (obj instanceof ComponentExchange) {
        componentExchange = (ComponentExchange) obj;
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
    public void setData(EObject obj) {
      if (obj instanceof Allocation) {
        exchange = (Allocation) obj;
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
    public void setData(EObject obj) {
      if (obj instanceof Allocation) {
        exchange = (Allocation) obj;
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
    public void setData(EObject obj) {
      if (obj instanceof FunctionalExchange) {
        exchange = (FunctionalExchange) obj;
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

  AbstractLink getPortAllocationWrapper(Allocation object) {
    if (portAllocationWrapper == null) {
      portAllocationWrapper = new PortAllocationWrapper();
    }
    portAllocationWrapper.setData(object);
    return portAllocationWrapper;
  }

  AbstractLink getComponentPortAllocationWrapper(Allocation object) {
    if (componentPortAllocationWrapper == null) {
      componentPortAllocationWrapper = new ComponentPortAllocationWrapper();
    }
    componentPortAllocationWrapper.setData(object);
    return componentPortAllocationWrapper;
  }

  private PhysicalLinkWrapper linkWrapper = null;

  AbstractLink getComponentExchangeWrapper(ComponentExchange object) {
    if (connectionWrapper == null) {
      connectionWrapper = new ComponentExchangeWrapper();
    }
    connectionWrapper.setData(object);
    return connectionWrapper;
  }

  AbstractLink getPhysicalLinkWrapper(PhysicalLink object) {
    if (linkWrapper == null) {
      linkWrapper = new PhysicalLinkWrapper();
    }
    linkWrapper.setData(object);
    return linkWrapper;
  }

  AbstractLink getFunctionalExchangeWrapper(FunctionalExchange object) {
    if (functionalExchangeWrapper == null) {
      functionalExchangeWrapper = new FunctionalExchangeWrapper();
    }
    functionalExchangeWrapper.setData(object);
    return functionalExchangeWrapper;
  }

  public boolean isOrientationAllowed(EObject context) {
    Port sourcePort = getSourcePort(context);
    Port targetPort = getTargetPort(context);
    return sourcePort instanceof ComponentPort && targetPort instanceof ComponentPort
        && ((ComponentPort) sourcePort).getKind() == ComponentPortKind.STANDARD
        && ((ComponentPort) targetPort).getKind() == ComponentPortKind.STANDARD;
  }

  public Port getSourcePort(EObject context) {
    if (context instanceof ComponentExchange) {
      AbstractLink linkConnection = new ComponentExchangeWrapper();
      linkConnection.setData(context);
      return linkConnection.getSourcePort();
    } else if (context instanceof PhysicalLink) {
      AbstractLink linkConnection = new PhysicalLinkWrapper();
      linkConnection.setData(context);
      return linkConnection.getSourcePort();
    }
    return null;
  }

  public Port getTargetPort(EObject context) {
    if (context instanceof ComponentExchange) {
      AbstractLink linkConnection = new ComponentExchangeWrapper();
      linkConnection.setData(context);
      return linkConnection.getTargetPort();
    } else if (context instanceof PhysicalLink) {
      AbstractLink linkConnection = new PhysicalLinkWrapper();
      linkConnection.setData(context);
      return linkConnection.getTargetPort();
    }
    return null;
  }

  /**
   * Returns whether the edge is the edge which should be displayed for a Connection. The strict parameter check if the
   * source and the target view is strictly source and target bound of the edge or if they are containers of bound of
   * the edge. (check if the edge can be valid if the bounds (contained in source or target view) were correctly
   * settled)
   */
  public boolean isValidLinkEdge(AbstractLink link, DSemanticDecorator source, DSemanticDecorator target,
      boolean strict) {

    boolean valid = false;
    DSemanticDecorator sourceElement = source;
    DSemanticDecorator targetElement = target;

    if (link.getData() == null) {
      return false;
    }

    if (link.getData().eContainer() == null) {
      return false;
    }

    if (strict) {

      // Remove edge between part whereas connection is between port
      if ((link.getSource() instanceof Port) && (sourceElement.getTarget() instanceof Part)) {
        return false;
      }
      if ((link.getTarget() instanceof Port) && (targetElement.getTarget() instanceof Part)) {
        return false;
      }

      // Remove edge between part or port if not the good port
      if (link.getSourcePort() != null && link.getSourcePort() != sourceElement.getTarget()) {
        return false;
      }
      if (link.getTargetPort() != null && link.getTargetPort() != targetElement.getTarget()) {
        return false;
      }
    }

    if (sourceElement.getTarget() instanceof Port) {
      sourceElement = (DSemanticDecorator) sourceElement.eContainer();
    }
    if (targetElement.getTarget() instanceof Port) {
      targetElement = (DSemanticDecorator) targetElement.eContainer();
    }

    // Remove edge between port if port is on another part
    if ((link.getSourcePart() != null) && (link.getSourcePart() != sourceElement.getTarget())) {
      return false;
    }
    if ((link.getTargetPart() != null) && (link.getTargetPart() != targetElement.getTarget())) {
      return false;
    }

    // hide the edge if the communication is not contained in on of the containerView.ancestor of the source
    valid = true;

    // Check delegation edges
    if (link.getKind() == ComponentExchangeKind.DELEGATION) {
      return targetElement.eContainer() == sourceElement;
    }

    if (!isMultipartMode(link.getData())
        && !(BlockArchitectureExt.getRootBlockArchitecture(link.getData()) instanceof OperationalAnalysis)) {
      return valid;
    }

    return isUndoublonLink(source, target);

  }

  /**
   * @param source
   * @param target
   * @return
   */
  private boolean isUndoublonLink(DSemanticDecorator source, DSemanticDecorator target) {
    DSemanticDecorator sourceElement = source;
    DSemanticDecorator targetElement = target;

    boolean valid = true;
    if (sourceElement.getTarget() instanceof Port) {
      sourceElement = (DSemanticDecorator) sourceElement.eContainer();
    }
    if (targetElement.getTarget() instanceof Port) {
      targetElement = (DSemanticDecorator) targetElement.eContainer();
    }

    // hide the edge if there is not the same node in the view of the part of the same type of the source
    LinkedList<EObject> sourceParents = getParents(source, DiagramPackage.Literals.DDIAGRAM);
    LinkedList<EObject> targetParents = getParents(target, DiagramPackage.Literals.DDIAGRAM);

    sourceParents.remove(sourceElement);
    targetParents.remove(targetElement);

    // Retrieve the first parent of source which is not common with the target
    HashSet<EObject> targetParentSet = new HashSet<>(targetParents);

    EObject current = null;
    if (!sourceParents.isEmpty()) {
      current = sourceParents.removeLast();
      while ((current != null) && targetParentSet.contains(current)) {
        if (!sourceParents.isEmpty()) {
          current = sourceParents.removeLast();
        } else {
          current = null;
        }
      }
    }
    if (current != null) {
      if (current instanceof DDiagramElement) {
        for (DSemanticDecorator child : DiagramServices.getDiagramServices().getDiagramElements(current)) {
          if ((child.getTarget() != null) && child.getTarget().equals(targetElement.getTarget())) {
            valid = false;
          }
        }
      }
    }

    return valid;
  }

  /**
   * Returns the sub-defined components which are defined in sub-used components
   */
  public Collection<Component> getSubDefinedByUsedComponents(Component component) {
    LinkedList<Component> toVisit = new LinkedList<>();
    Collection<Component> components = new HashSet<>();
    toVisit.add(component);

    while (!toVisit.isEmpty()) {
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

    components.remove(component);
    return components;
  }

  /**
   * Returns a linkedList of parents with the bottom-to-top order
   * 
   * @param clazz
   *          the eClass which will stop the browsing. (the eClass of the parent of the object which will not be
   *          included in the list)
   */
  protected LinkedList<EObject> getParents(EObject object, EClass clazz) {
    LinkedList<EObject> parents = new LinkedList<>();
    EObject current = object;

    if (current == null) {
      return parents;
    }

    current = current.eContainer();

    while ((current != null) && !clazz.isInstance(current)) {
      parents.addLast(current);
      current = current.eContainer();
    }
    return parents;
  }

  /**
   * Returns the sources of a ComponentExchange (the component, the part, or the port according to the mode)
   */
  public Collection<? extends EObject> getComponentExchangeSources(ComponentExchange connection) {
    Collection<? extends EObject> source = getComponentExchangeWrapper(connection).getSourcePorts();
    if (source == null) {
      source = Collections.singletonList(getComponentExchangeWrapper(connection).getSourcePart());
    }
    return source;
  }

  public String getComponentExchangeLabelByDelegation(DSemanticDecorator view) {

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(view);
    Collection<EObject> sems = ((DRepresentationElement) view).getSemanticElements();
    Collection<ComponentExchange> exchanges = new ArrayList<>();

    for (EObject semantic : sems) {
      if (semantic instanceof ComponentExchange) {
        if (((ComponentExchange) semantic).getKind() == ComponentExchangeKind.DELEGATION) {
          return " "; //$NON-NLS-1$
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

  public String getComponentExchangeLabelByDelegationOriented(DSemanticDecorator view) {

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(view);
    Collection<EObject> sems = ((DRepresentationElement) view).getSemanticElements();
    Collection<ComponentExchange> exchanges = Lists.newArrayList();

    for (EObject semantic : sems) {
      if (semantic instanceof ComponentExchange) {
        if (((ComponentExchange) semantic).getKind() == ComponentExchangeKind.DELEGATION) {
          return " "; //$NON-NLS-1$
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

  public String getComponentExchangeLabelByDelegationReversed(DSemanticDecorator view) {

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(view);
    Collection<EObject> sems = ((DRepresentationElement) view).getSemanticElements();
    Collection<ComponentExchange> exchanges = Lists.newArrayList();

    Part part = (Part) view.getTarget();
    PhysicalComponent physicalComponent = (PhysicalComponent) part.getAbstractType();

    for (EObject semantic : sems) {

      if (semantic instanceof ComponentExchange) {
        if (((ComponentExchange) semantic).getKind() == ComponentExchangeKind.DELEGATION) {
          return " "; //$NON-NLS-1$
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

  public Collection<ComponentPort> getAllLinkedDelegatedComponentPorts(ComponentPort port) {
    return PortExt.getAllLinkedDelegatedComponentPorts(port);
  }

  public List<ComponentExchange> getAssemblyComponentExchanges(ComponentPort port) {
    return PortExt.getAssemblyComponentExchanges(port);
  }

  public List<ComponentExchange> getFlowComponentExchanges(ComponentPort port) {
    return PortExt.getFlowComponentExchanges(port);
  }

  public Collection<CapellaElement> getAllRelatedLinks(Port port) {
    Collection<CapellaElement> target = new ArrayList<>();

    if (port instanceof ComponentPort) {

      for (Port relatedPort : getAllLinkedDelegatedComponentPorts((ComponentPort) port)) {
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

  public Collection<? extends EObject> getComponentExchangeByDelegationSemantics(EObject related) {

    Object sourceView = getInterpreterVariable(related, IInterpreterSiriusVariables.SOURCE_VIEW);
    Object targetView = getInterpreterVariable(related, IInterpreterSiriusVariables.TARGET_VIEW);

    return getComponentExchangeByDelegationSemantics(related, sourceView, targetView);

  }

  public Collection<? extends EObject> getComponentExchangeByDelegationSemantics(EObject related, Object sourceView,
      Object targetView) {

    Collection<CapellaElement> target = new ArrayList<>();

    if (sourceView instanceof EdgeTarget && targetView instanceof EdgeTarget) {

      EdgeTarget sourceNode = (EdgeTarget) sourceView;
      EdgeTarget targetNode = (EdgeTarget) targetView;

      if ((sourceNode instanceof DSemanticDecorator) && (targetNode instanceof DSemanticDecorator)) {
        EObject sourcePort = ((DSemanticDecorator) sourceNode).getTarget();
        EObject targetPort = ((DSemanticDecorator) targetNode).getTarget();

        if ((sourcePort != null) && (targetPort != null) && (sourcePort instanceof Port)
            && (targetPort instanceof Port)) {
          Collection<CapellaElement> sourceLinks = getAllRelatedLinks((Port) sourcePort);

          Collection<CapellaElement> targetLinks = getAllRelatedLinks((Port) targetPort);
          sourceLinks.retainAll(targetLinks);

          target = sourceLinks;

          // retrains with related parts (for multipart mode)
          Collection<EObject> a = new ArrayList<>();

          for (EObject ce : target) {
            if (ce instanceof ComponentExchange) {
              ComponentExchange cee = (ComponentExchange) ce;
              Part sourcePart = getSourcePart(cee);
              Part targetPart = getTargetPart(cee);
              if (sourcePart != null && getRelatedPart((DSemanticDecorator) sourceNode) != sourcePart) {
                a.add(ce);
              }
              if (targetPart != null && getRelatedPart((DSemanticDecorator) targetNode) != targetPart) {
                a.add(ce);
              }
            }
          }
          target.removeAll(a);
        }
      }
    }

    // ordering is required since we use the crossReferencer to retrieve elements => hash dependent
    List<CapellaElement> target2 = new ArrayList<>(target);
    Collections.sort(target2, getComparator());
    return target2;

  }

  public Part getSourcePart(ComponentExchange connection) {
    return ComponentExchangeExt.getSourcePart(connection);
  }

  public Part getTargetPart(ComponentExchange connection) {
    return ComponentExchangeExt.getTargetPart(connection);
  }

  public Port getSourcePort(ComponentExchange connection) {
    return ComponentExchangeExt.getSourcePort(connection);
  }

  public Port getTargetPort(ComponentExchange connection) {
    return ComponentExchangeExt.getTargetPort(connection);
  }

  public EObject getComponentExchangeByDelegationTarget(EObject related) {

    if (related instanceof ComponentPort) {
      return related;
    } else if (related instanceof ComponentExchange) {
      return getComponentExchangeByDelegationTarget(((ComponentExchange) related).getSource());
    }

    return related;
  }

  /**
   * Returns the targets of a ComponentExchange (the component, the part, or the port according to the mode)
   */
  public Collection<? extends EObject> getComponentExchangeLowestTargets(EObject related) {
    Collection<ComponentPort> target = new ArrayList<>();
    if (related instanceof ComponentPort) {
      ComponentPort port = (ComponentPort) related;

      // We retrieve all ports connected to the current port
      for (ComponentPort connectedPort : PortExt.getConnectedComponentPortsWithoutDelegation(port)) {
        if (!target.contains(connectedPort)) {
          target.add(connectedPort);
        }
      }

      // We retrieve all lowest-delegated ports connected to a component exchange of any CE linked to delegated-ing
      // ports.
      Collection<ComponentPort> delegateds = PortExt.getAllLinkedDelegatedComponentPorts(port);
      delegateds.remove(port);
      for (Port relatedPort : delegateds) {
        if (relatedPort instanceof ComponentPort) {
          for (Port connectedPort : PortExt.getConnectedComponentPortsWithoutDelegation((ComponentPort) relatedPort)) {
            Collection<ComponentPort> ports = PortExt.getAllDelegatedComponentPorts((ComponentPort) connectedPort);
            ports.add((ComponentPort) connectedPort);
            for (ComponentPort delegatedPort : ports) {
              if (PortExt.getDelegatedComponentPorts(delegatedPort).isEmpty() && !target.contains(delegatedPort)) {
                target.add(delegatedPort);
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
        if (!resultPort.isEmpty()) {
          objects.remove();
        }
      }
    }
    return target;

  }

  /**
   * Returns the targets of a ComponentExchange (the component, the part, or the port according to the mode)
   */
  public Collection<? extends EObject> getComponentExchangeByDelegationTargets(EObject related) {
    Collection<CapellaElement> target = new ArrayList<>();
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
  public EObject getComponentExchangeSource(ComponentExchange connection) {
    EObject source = getComponentExchangeWrapper(connection).getSourcePort();
    if (source == null) {
      source = getComponentExchangeWrapper(connection).getSourcePart();
    }
    return source;
  }

  /**
   * Returns the source of a ComponentExchange (the component, the part, or the port according to the mode)
   */
  public EObject getComponentPortAllocationSource(ComponentPortAllocation connection) {
    EObject source = getComponentPortAllocationWrapper(connection).getSourcePort();
    if (source == null) {
      source = getComponentPortAllocationWrapper(connection).getSourcePart();
    }
    return source;
  }

  /**
   * Returns the source of a ComponentExchange (the component, the part, or the port according to the mode)
   */
  public EObject getComponentPortAllocationTarget(ComponentPortAllocation connection) {
    EObject source = getComponentPortAllocationWrapper(connection).getTargetPort();
    if (source == null) {
      source = getComponentPortAllocationWrapper(connection).getTargetPart();
    }
    return source;
  }

  /**
   * Returns the target of a ComponentExchange (the component, the part, or the port according to the mode)
   */
  public EObject getComponentExchangeTarget(ComponentExchange connection) {
    EObject target = getComponentExchangeWrapper(connection).getTargetPort();
    if (target == null) {
      target = getComponentExchangeWrapper(connection).getTargetPart();
    }
    return target;
  }

  /**
   * Returns the source of a PortCommunication (the component, the part, or the port according to the mode)
   */
  public EObject getPhysicalLinkSource(PhysicalLink connection) {
    return getPhysicalLinkWrapper(connection).getSourcePort();
  }

  /**
   * Returns the target of a PortCommunication (the component, the part, or the port according to the mode)
   */
  public EObject getPhysicalLinkTarget(PhysicalLink connection) {
    return getPhysicalLinkWrapper(connection).getTargetPort();
  }

  public Comparator<CapellaElement> getComparator() {
    return (o1, o2) -> {
      if ((o1 == null) && (o2 == null)) {
        return 0;
      } else if ((o1 == null) || (o1.getId() == null)) {
        return -1;
      } else if ((o2 == null) || (o2.getId() == null)) {
        return 1;
      }
      if ((o1 instanceof AbstractNamedElement) && (o2 instanceof AbstractNamedElement)) {
        AbstractNamedElement a1 = (AbstractNamedElement) o1;
        AbstractNamedElement a2 = (AbstractNamedElement) o2;
        if ((a1.getName() != null) && (a2.getName() != null)) {
          return a1.getName().compareTo(a2.getName());
        }
      }
      return o1.getId().compareTo(o2.getId());
    };
  }

  /**
   * Returns all semantics candidates for a ComponentExchange link
   */
  public Collection<CapellaElement> getComponentExchangeSemantics(EObject element) {
    Collection<CapellaElement> list = new ArrayList<>();

    if (element instanceof Component) {
      list.addAll(ComponentExt.getAllOwnedComponentExchanges((Component) element));

    } else if (element instanceof BlockArchitecture) {
      list.addAll(BlockArchitectureExt.getAllComponentExchanges((BlockArchitecture) element));
    }

    return list;
  }

  /**
   * Returns semantic elements which can be related of a namedElement
   */
  public List<EObject> getPartSemanticElements(NamedElement element) {
    List<EObject> elements = new ArrayList<>();

    if (element instanceof Part) {
      elements.add(element);
      elements.add(((Part) element).getAbstractType());

    } else if (element instanceof Component) {
      elements.add(element);
      elements.addAll(((Component) element).getRepresentingParts());
    }
    return elements;
  }

  /**
   * Returns allocated logical functions for the given part
   */
  public List<AbstractFunction> getAllocatedFunctions(Part part) {
    if (part.getAbstractType() instanceof Component) {
      return getAllocatedFunctions(((Component) part.getAbstractType()));
    }
    return Collections.emptyList();
  }

  /**
   * Returns allocated logical functions for the given component
   */
  public List<AbstractFunction> getAllocatedFunctions(Component component) {
    List<AbstractFunction> list = new ArrayList<>();

    for (ComponentFunctionalAllocation alloc : component.getFunctionalAllocations()) {
      if (alloc.getFunction() != null) {
        list.add(alloc.getFunction());
      }
      if ((alloc.getFunction() != null) && (alloc.getFunction().eContainer() != null)
          && (alloc.getFunction().eContainer() instanceof AbstractFunction)) {
        list.add((AbstractFunction) alloc.getFunction().eContainer());
      }
    }
    return list;
  }

  /**
   * Returns actors which should be inserted into the architecture of the component in the LAB diagram
   */
  public Collection<? extends Component> getABInsertActor(CapellaElement capellaElement) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_AB_INSERT_ACTOR_FOR_LIB, capellaElement);
  }

  /**
   * Returns components which can be inserted into the given component in the LAB Diagram
   */
  public Collection<? extends Component> getABInsertComponent(CapellaElement capellaElement) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_AB_INSERT_COMPONENT_FOR_LIB, capellaElement);
  }

  /**
   * Returns node components which can be inserted into the given component in the PAB Diagram
   */
  public Collection<PhysicalComponent> getPABInsertNodeComponent(CapellaElement capellaElement) {
    Collection<PhysicalComponent> initialCollection = QueryInterpretor
        .executeQuery(QueryIdentifierConstants.GET_AB_INSERT_COMPONENT_FOR_LIB, capellaElement);
    return initialCollection.stream().filter(PhysicalComponentExt::isNode).collect(Collectors.toList());
  }

  /**
   * Returns behaviour components which can be inserted into the given component in the PAB Diagram
   */
  public Collection<PhysicalComponent> getPABInsertBehaviourComponent(CapellaElement capellaElement) {
    Collection<PhysicalComponent> initialCollection = QueryInterpretor
        .executeQuery(QueryIdentifierConstants.GET_AB_INSERT_COMPONENT_FOR_LIB, capellaElement);
    return initialCollection.stream().filter(PhysicalComponentExt::isBehaviour).collect(Collectors.toList());
  }

  public Collection<? extends CapellaElement> getABShowHideActor(DSemanticDecorator view) {
    return getABShowHideComponentForActor(view).stream().filter(ComponentExt::isActor).collect(Collectors.toList());
  }

  /**
   * Returns owned parts with the given eclass type
   */
  protected List<Part> getParts(CapellaElement element, EClass eClass, EClass excludeEClass) {
    List<Part> parts = new ArrayList<>();
    List<Part> subParts = new ArrayList<>();
    if (element instanceof Component) {
      subParts = ComponentExt.getSubParts((Component) element);
    } else if (element instanceof ComponentPkg) {
      subParts = ((ComponentPkg) element).getOwnedParts();
    }
    for (Part part : subParts) {
      if ((part instanceof Part) && eClass.isInstance(part.getAbstractType())
          && (!((excludeEClass != null) && excludeEClass.isInstance(part.getAbstractType())))) {
        parts.add(part);
      }
    }
    return parts;
  }

  Couple<DNode, Boolean> createViewOrGetPort(DNodeContainer parent, Port target) {
    for (DNode node : parent.getOwnedBorderedNodes()) {
      if ((node.getTarget() != null) && node.getTarget().equals(target)) {
        return new Couple<>(node, Boolean.FALSE);
      }
    }

    DNode created = FaServices.getFaServices().createViewComponentPort(target, parent, parent.getParentDiagram());
    return new Couple<>(created, Boolean.TRUE);
  }

  Couple<DNode, Boolean> createViewOrGetPhysicalPort(DNodeContainer parent, Port target) {
    for (DNode node : parent.getOwnedBorderedNodes()) {
      if ((node.getTarget() != null) && node.getTarget().equals(target)) {
        return new Couple<>(node, Boolean.FALSE);
      }
    }

    DNode created = FaServices.getFaServices().createViewPhysicalPort(target, parent, parent.getParentDiagram());
    return new Couple<>(created, Boolean.TRUE);
  }

  /**
   * Unused
   */
  @Deprecated
  Couple<DNodeContainer, Boolean> createViewOrGetPart(DragAndDropTarget parent, EObject target) {
    List<DDiagramElement> elements = null;

    if (parent instanceof DDiagram) {
      elements = ((DDiagram) parent).getOwnedDiagramElements();
    } else if (parent instanceof DNodeContainer) {
      elements = ((DNodeContainer) parent).getOwnedDiagramElements();
    }
    if (elements != null) {
      for (DDiagramElement node : elements) {
        if ((node instanceof DNodeContainer) && (node.getTarget() != null) && node.getTarget().equals(target)) {
          return new Couple<>((DNodeContainer) node, Boolean.FALSE);
        }
      }
    }

    DNodeContainer created = FaServices.getFaServices().createViewPart(target, parent,
        CapellaServices.getService().getDiagramContainer(parent));
    return new Couple<>(created, Boolean.TRUE);
  }

  /**
   * Unused
   */
  @Deprecated
  Couple<DNodeContainer, Boolean> createViewOrGetDeployedPart(DragAndDropTarget parent, EObject target) {
    List<DDiagramElement> elements = null;

    if (parent instanceof DDiagram) {
      elements = ((DDiagram) parent).getOwnedDiagramElements();
    } else if (parent instanceof DNodeContainer) {
      elements = ((DNodeContainer) parent).getOwnedDiagramElements();
    }
    if (elements != null) {
      for (DDiagramElement node : elements) {
        if ((node instanceof DNodeContainer) && (node.getTarget() != null) && node.getTarget().equals(target)) {
          return new Couple<>((DNodeContainer) node, Boolean.FALSE);
        }
      }
    }

    DNodeContainer created = FaServices.getFaServices().createViewDeployedPart(target, parent,
        CapellaServices.getService().getDiagramContainer(parent));
    return new Couple<>(created, Boolean.TRUE);
  }

  @Deprecated
  public boolean isDeployed(DNodeContainer view) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(view);
    String mappingName = MappingConstantsHelper.getMappingABDeployedElement(diagram);
    return view.getMapping().getName().equals(mappingName);
  }

  public List<EObject> getDeployableLocations(DeployableElement element) {

    ArrayList<EObject> parents = new ArrayList<>();

    for (AbstractDeploymentLink linka : element.getDeployingLinks()) {
      parents.add(linka.getLocation());
    }
    return parents;
  }

  /**
   * Show related contextual elements into the diagram
   * 
   * @param contextualElements
   */
  public void showABContextualElements(DDiagram diagram) {
    DDiagramContents context = FaServices.getFaServices().getDDiagramContents(diagram);
    DRepresentationDescriptor descriptor = new DRepresentationQuery(diagram).getRepresentationDescriptor();
    showABContextualElements(context, ContextualDiagramHelper.getService().getContextualElements(descriptor));
  }

  /**
   * Show given contextual elements into the diagram A contextual element is an element which will be displayed in the
   * diagram with a lot of related elements such as part linked with a ComponentExchange, PhysicalLink For a contextual
   * functional chain, display all elements of the chain
   * 
   * @param contextualElements
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void showABContextualElements(DDiagramContents context, Collection<EObject> contextualElements) {
    if (!contextualElements.isEmpty()) {
      DDiagram diagram = context.getDDiagram();
      Collection<EObject> contextualParts = new HashSet<>();
      Collection<AbstractFunction> contextualFunctions = new HashSet<>();
      Collection<FunctionalExchange> contextualFunctionExchanges = new HashSet<>();
      Collection<EObject> contextualFunctionalChains = new HashSet<>();
      Collection<EObject> contextualConnections = new HashSet<>();
      Collection<EObject> contextualPhysicalLinks = new HashSet<>();
      Collection<EObject> contextualModes = new HashSet<>();
      Collection<EObject> contextualScenarios = new HashSet<>();

      for (EObject contextualElement : contextualElements) {

        if (contextualElement instanceof Entity) {
          contextualParts.add(contextualElement);

        } else if (contextualElement instanceof Component) {
          Collection<Part> parts = getCache(ComponentExt::getRepresentingParts, (Component) contextualElement);
          contextualParts.addAll(parts);

        } else if (contextualElement instanceof Part) {
          contextualParts.add(contextualElement);

        } else if (contextualElement instanceof AbstractFunction) {
          contextualFunctions.add((AbstractFunction) contextualElement);

        } else if (contextualElement instanceof FunctionalChain) {

          for (FunctionalChainInvolvement involvement : FunctionalChainExt
              .getFlatInvolvementsOf((FunctionalChain) contextualElement, FaPackage.Literals.ABSTRACT_FUNCTION)) {
            contextualFunctions.add((AbstractFunction) involvement.getInvolved());
          }
          for (FunctionalChainInvolvement involvement : FunctionalChainExt
              .getFlatInvolvementsOf((FunctionalChain) contextualElement, FaPackage.Literals.FUNCTIONAL_EXCHANGE)) {
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
        for (ComponentExchange flow : getCache(ABServices::getRelatedComponentExchanges2, contextualPart)) {
          if (flow.getKind() != ComponentExchangeKind.DELEGATION) {
            contextualConnections.add(flow);
          }
        }

        Collection<PhysicalLink> delagatesPhysicalLink = getAllDelegatesPhysicalLink(contextualPart);
        for (PhysicalLink physicalLink : ABServices.getService().getRelatedPhysicalLink(contextualPart)) {
          if (!delagatesPhysicalLink.contains(physicalLink)) {
            contextualPhysicalLinks.add(physicalLink);
          }
        }
      }

      // Show a lot of things
      ABServices.getService().showABComponent(contextualParts, context);
      CsServices.getService().showABFunctionalExchange((Collection) contextualFunctionExchanges,
          (DSemanticDecorator) diagram);
      CsServices.getService().showABComponentExchange(contextualConnections, (DSemanticDecorator) diagram);
      CsServices.getService().showABPhysicalLink(contextualPhysicalLinks, (DSemanticDecorator) diagram);
      FaServices.getFaServices().showABFunctionalChains(diagram, contextualFunctionalChains, context);
      ABServices.getService().showABScenarios((DSemanticDecorator) diagram, contextualScenarios);
      ABServices.getService().showABStateModes((DSemanticDecorator) diagram, contextualModes);
    }
  }

  public Collection<PhysicalLink> getAllDelegatesPhysicalLink(EObject contextualPart) {
    Collection<PhysicalLink> result = new ArrayList<>();
    for (PhysicalPort port : getContainedPhysicalPorts(contextualPart)) {
      result.addAll(PortExt.getDelegatedPhysicalLinks(port));
      result.addAll(PortExt.getDelegatingPhysicalLinks(port));
    }
    return result;
  }

  /**
   * Get contained physical ports for a Part or Component
   * 
   * @param contextualPart
   * @return
   */
  public List<PhysicalPort> getContainedPhysicalPorts(EObject contextualPart) {
    if (contextualPart instanceof Component) {
      return ((Component) contextualPart).getContainedPhysicalPorts();
    } else if (contextualPart instanceof Part) {
      Part part = (Part) contextualPart;
      if (part.getAbstractType() instanceof Component) {
        Component component = ((Component) part.getAbstractType());
        return component.getContainedPhysicalPorts();
      }
    }
    return Collections.emptyList();
  }

  public EObject showABPhysicalLink(Collection<EObject> contextualPhysicalLinks,
      DSemanticDecorator currentElementView) {
    ABServices.getService().showABPhysicalLink(contextualPhysicalLinks,
        new DDiagramContents(CapellaServices.getService().getDiagramContainer(currentElementView)));
    return currentElementView;
  }

  public EObject showABComponentPortAllocations(Collection<EObject> contextualComponentPortAllocations,
      DSemanticDecorator currentElementView) {
    ABServices.getService().showABComponentPortAllocations(contextualComponentPortAllocations,
        new DDiagramContents(CapellaServices.getService().getDiagramContainer(currentElementView)));
    return currentElementView;
  }

  public EObject hideABComponentPortAllocations(Collection<EObject> contextualComponentPortAllocations,
      DSemanticDecorator currentElementView) {
    ABServices.getService().hideABComponentPortAllocations(contextualComponentPortAllocations,
        new DDiagramContents(CapellaServices.getService().getDiagramContainer(currentElementView)));
    return currentElementView;
  }

  public EObject showABPortAllocations(Collection<EObject> contextualPortAllocations,
      DSemanticDecorator currentElementView) {
    ABServices.getService().showABPortAllocations(contextualPortAllocations,
        new DDiagramContents(CapellaServices.getService().getDiagramContainer(currentElementView)));
    return currentElementView;
  }

  public EObject hideABPortAllocations(Collection<EObject> contextualPortAllocations,
      DSemanticDecorator currentElementView) {
    ABServices.getService().hideABPortAllocations(contextualPortAllocations,
        new DDiagramContents(CapellaServices.getService().getDiagramContainer(currentElementView)));
    return currentElementView;
  }

  /**
   * used in context, logical, oa, physical
   */
  public EObject showABComponentExchange(Collection<EObject> exchanges, DSemanticDecorator currentElementView) {
    ABServices.getService().showABComponentExchange(exchanges,
        new DDiagramContents(CapellaServices.getService().getDiagramContainer(currentElementView)));
    return currentElementView;
  }

  /**
   */
  public EObject showABComponentExchange(EObject exchange, DSemanticDecorator currentElementView) {
    return showABComponentExchange(Collections.singletonList(exchange), currentElementView);
  }

  /**
   * used in context, logical, oa, physical
   */
  public EObject showABPhysicalLink(EObject physicalLinkToShow, DSemanticDecorator currentElementView) {
    return showABPhysicalLink(Collections.singleton(physicalLinkToShow), currentElementView);
  }

  public EObject showABComponentPortAllocations(EObject componentPortAllocationToShow,
      DSemanticDecorator currentElementView) {
    return showABComponentPortAllocations(Collections.singleton(componentPortAllocationToShow), currentElementView);
  }

  public EObject hideABComponentPortAllocations(EObject portAllocationToHide, DSemanticDecorator currentElementView) {
    return hideABComponentPortAllocations(Collections.singleton(portAllocationToHide), currentElementView);
  }

  public EObject showABPortAllocations(EObject portAllocationToShow, DSemanticDecorator currentElementView) {
    return showABPortAllocations(Collections.singleton(portAllocationToShow), currentElementView);
  }

  public EObject hideABPortAllocations(EObject portAllocationToHide, DSemanticDecorator currentElementView) {
    return hideABPortAllocations(Collections.singleton(portAllocationToHide), currentElementView);
  }

  /**
   * Display a component in the best container (display deployment in their deploying component) used in context,
   * logical, oa, physical
   */
  @Deprecated
  public EObject showABComponent(Collection<EObject> components, DSemanticDecorator diagram) {
    ABServices.getService().showABComponent(components,
        new DDiagramContents(CapellaServices.getService().getDiagramContainer(diagram)));
    return diagram;
  }

  /**
   * Display a functional exchange and bounds if necessary used in context, logical, oa, physical
   */
  public EObject showABFunctionalExchange(Collection<FunctionalExchange> exchanges,
      DSemanticDecorator currentElementView) {
    ABServices.getService().showABFunctionalExchange((Collection) exchanges,
        new DDiagramContents(CapellaServices.getService().getDiagramContainer(currentElementView)));
    return currentElementView;
  }

  /**
   * Display a functional exchange and bounds if necessary used in context, logical, oa, physical
   */
  public EObject showABFunctionalExchange(FunctionalExchange exchangeToShow, DSemanticDecorator currentElementView) {
    return showABFunctionalExchange(Collections.singletonList(exchangeToShow), currentElementView);
  }

  /**
   * Display a functional exchange and bounds if necessary used in context, logical, oa, physical
   */
  public EObject showFunctionalExchangeInArchitectureBlank2(FunctionalExchange exchangeToShow,
      DSemanticDecorator currentElementView) {
    return showABFunctionalExchange(exchangeToShow, currentElementView);
  }

  /**
   * @param componentView
   * @param function
   * @return
   */
  Couple<DNode, Boolean> createViewOrGetFunction(DNodeContainer parent, AbstractFunction target) {
    for (DDiagramElement node : parent.getOwnedDiagramElements()) {
      if ((node instanceof DNode) && (node.getTarget() != null) && node.getTarget().equals(target)) {
        return new Couple<>((DNode) node, Boolean.FALSE);
      }
    }

    DNode created = FaServices.getFaServices().createViewABAbstractFunction(target, parent, parent.getParentDiagram());
    return new Couple<>(created, Boolean.TRUE);
  }

  /**
   * @param sourceView
   * @param sourcePort
   * @return
   */
  @Deprecated
  Couple<DNode, Boolean> createViewOrGetFunctionPort(DNode parent, Pin target) {
    for (DNode node : parent.getOwnedBorderedNodes()) {
      if ((node.getTarget() != null) && node.getTarget().equals(target)) {
        return new Couple<>(node, Boolean.FALSE);
      }
    }

    DNode created = FaServices.getFaServices().createViewFunctionPort(target, parent, parent.getParentDiagram());
    return new Couple<>(created, Boolean.TRUE);
  }

  /**
   * Create a representing part if no representing partition exists
   * 
   * @param rootComponent
   */
  public void createRepresentingPartIfNone(Component component) {
    if (component.getRepresentingParts().isEmpty()) {
      Part part = CsFactory.eINSTANCE.createPart();
      EObject parentContainer = getParentContainer(component);
      if (parentContainer instanceof BlockArchitecture) {
        ComponentPkg context = getContext((BlockArchitecture) parentContainer);
        context.getOwnedParts().add(part);
      } else if (parentContainer instanceof Component) {
        ((Component) parentContainer).getOwnedFeatures().add(part);
      }

      CapellaServices.getService().creationService(part);
      part.setAbstractType(component);
    }
  }

  /**
   * return integer value according the link to be created 0 = non 1 = provided 2 = required 3 = provided + required
   * 
   * @param port
   * @param interf
   * @return
   */
  public int getTypeOfTheLinkToCreate(ComponentPort port, Interface itf) {
    int value = 0;
    boolean flag = false;

    if ((null != port) && (itf != null)) {
      EList<Interface> proviededInterfaces = port.getProvidedInterfaces();
      if (!proviededInterfaces.isEmpty() && proviededInterfaces.contains(itf)) {
        flag = true;
        value = 1;
      }
      EList<Interface> requiredInterfaces = port.getRequiredInterfaces();
      if (!requiredInterfaces.isEmpty() && requiredInterfaces.contains(itf)) {
        if (flag) {
          value = 3;
        } else {
          value = 2;
        }
      }
    }

    return value;
  }

  /**
   * Assuming the 'context' as Component with use link and sending kind of communication link --> Get all the component
   * implementing and receiving kind of communication link toward the same element as 'context' (using and sending
   * links).
   * 
   * @param context
   *          : Component
   * @return list of component
   */
  public List<Component> getOppositeCompsOfUseAndSendingCommLink(EObject context) {
    List<Component> result = new ArrayList<>(1);

    if (context instanceof Component) {
      Component comp = (Component) context;

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
          List<Component> oppositeComponentUsingCrossref = getCompOfReceivingCommLinkUsingCrossRef(
              abstractExchangeItem);
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
   * 
   * @param context
   *          : ?
   * @param sourceView
   *          : Component
   * @param targetView
   *          : Component
   * @return String
   */
  public String getInterfaceExchangeItemLabel(EObject context, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {

    StringBuilder result = new StringBuilder();
    if ((null == sourceView) || (null == targetView)) {
      return result.toString();
    }
    EObject src = sourceView.getTarget();
    EObject tar = targetView.getTarget();

    if ((null != src) && (null != tar) && (src instanceof Component) && (tar instanceof Component)) {
      Component source = (Component) src;
      Component target = (Component) tar;
      // used links to implementer component
      List<Interface> allUsedInterfaces = ComponentExt.getUsedAndRequiredInterfaces(source);
      for (Interface interface1 : allUsedInterfaces) {
        List<Component> components = new ArrayList<>();

        components.addAll(interface1.getImplementorComponents());
        List<Component> providerComponent = InterfaceExt.getProviderComponent(interface1);
        if (!providerComponent.isEmpty()) {
          components.addAll(providerComponent);
        }
        for (Component component : components) {
          if (target.equals(component)) {
            if (result.toString().isEmpty()) {
              result.append(interface1.getName());
            } else {
              result.append(", "); //$NON-NLS-1$
              result.append(interface1.getName());
            }
          }
        }
      }

      // sending communication link --> receiving communication link
      Collection<AbstractExchangeItem> exchangeItemsByKinds = getExchangeItemsByTransmitkinds(source);
      for (AbstractExchangeItem abstractExchangeItem : exchangeItemsByKinds) {
        if (abstractExchangeItem instanceof ExchangeItem) {
          List<Component> oppositeComponentUsingCrossref = getCompOfReceivingCommLinkUsingCrossRef(
              abstractExchangeItem);
          for (Component component : oppositeComponentUsingCrossref) {
            if (target.equals(component)) {
              if (result.toString().isEmpty()) {
                result.append(abstractExchangeItem.getName());
              } else {
                result.append(", "); //$NON-NLS-1$
                result.append(abstractExchangeItem.getName());
              }
            }
          }
        }
      }
    }
    return result.toString();
  }

  /**
   * return custom label {interfaces, exchangeItems} diagram based
   * 
   * @param context
   *          : ?
   * @param sourceView
   *          : Component
   * @param targetView
   *          : Component
   * @return String
   */
  public String getInterfaceExchangeItemLabelDiagramBased(EObject context, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {

    StringBuilder result = new StringBuilder();
    if ((null == sourceView) || (null == targetView)) {
      return result.toString();
    }
    EObject src = sourceView.getTarget();
    EObject tar = targetView.getTarget();

    if ((null != src) && (null != tar) && (src instanceof Component) && (tar instanceof Component)) {
      Component source = (Component) src;
      Component target = (Component) tar;

      DiagramServices diagramService = DiagramServices.getDiagramServices();
      DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView);
      if (null == diagram) {
        return result.toString();
      }

      // used links to implementer component
      List<Interface> allUsedInterfaces = ComponentExt.getUsedAndRequiredInterfaces(source);
      for (Interface interface1 : allUsedInterfaces) {
        List<Component> components = new ArrayList<>();
        components.addAll(interface1.getImplementorComponents());
        List<Component> providerComponent = InterfaceExt.getProviderComponent(interface1);
        if (!providerComponent.isEmpty()) {
          components.addAll(providerComponent);
        }
        for (Component component : components) {
          if (target.equals(component) && diagramService.isOnDiagram(diagram, interface1)) {
            // add to result only if interface1 is found in diagram
            if (result.toString().isEmpty()) {
              result.append(interface1.getName());
            } else {
              result.append(", "); //$NON-NLS-1$
              result.append(interface1.getName());
            }
          }
        }
      }

      // sending communication link --> receiving communication link
      Collection<AbstractExchangeItem> exchangeItemsByKinds = getExchangeItemsByTransmitkinds(source);
      for (AbstractExchangeItem abstractExchangeItem : exchangeItemsByKinds) {
        if (abstractExchangeItem instanceof ExchangeItem) {
          List<Component> oppositeComponentUsingCrossref = getCompOfReceivingCommLinkUsingCrossRef(
              abstractExchangeItem);
          for (Component component : oppositeComponentUsingCrossref) {
            if (target.equals(component) && diagramService.isOnDiagram(diagram, abstractExchangeItem)) {
              // add to result only if interface1 is found in diagram
              if (result.toString().isEmpty()) {
                result.append(abstractExchangeItem.getName());
              } else {
                result.append(", "); //$NON-NLS-1$
                result.append(abstractExchangeItem.getName());
              }
            }
          }
        }
      }
    }
    return result.toString();
  }

  /**
   * return custom label {interfaces, exchangeItems} diagram based
   * 
   * @param context
   *          : ?
   * @param sourceView
   *          : Component
   * @param targetView
   *          : Component
   * @return String
   */
  public boolean isInterfaceExchangeItemLabelDiagramBasedEmpty(EObject context, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {

    if ((null == sourceView) || (null == targetView)) {
      return false;
    }
    EObject src = sourceView.getTarget();
    EObject tar = targetView.getTarget();

    if ((null != src) && (null != tar) && (src instanceof Component) && (tar instanceof Component)) {
      Component source = (Component) src;
      Component target = (Component) tar;

      DiagramServices diagramService = DiagramServices.getDiagramServices();
      DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView);
      if (null == diagram) {
        return false;
      }

      // used links to implementer component
      List<Interface> allUsedInterfaces = ComponentExt.getUsedAndRequiredInterfaces(source);
      for (Interface interface1 : allUsedInterfaces) {
        List<Component> components = new ArrayList<>();
        components.addAll(interface1.getImplementorComponents());
        List<Component> providerComponent = InterfaceExt.getProviderComponent(interface1);
        if (!providerComponent.isEmpty()) {
          components.addAll(providerComponent);
        }
        for (Component component : components) {
          if (target.equals(component) && diagramService.isOnDiagram(diagram, interface1)) {
            // add to result only if interface1 is found in diagram
            return true;
          }
        }
      }

      // sending communication link --> receiving communication link
      Collection<AbstractExchangeItem> exchangeItemsByKinds = getExchangeItemsByTransmitkinds(source);
      for (AbstractExchangeItem abstractExchangeItem : exchangeItemsByKinds) {
        if (abstractExchangeItem instanceof ExchangeItem) {
          List<Component> oppositeComponentUsingCrossref = getCompOfReceivingCommLinkUsingCrossRef(
              abstractExchangeItem);
          for (Component component : oppositeComponentUsingCrossref) {
            if (target.equals(component) && diagramService.isOnDiagram(diagram, abstractExchangeItem)) {
              // add to result only if interface1 is found in diagram
              return true;
            }
          }
        }
      }
    }

    return false;
  }

  /**
   * Get Component of the receiving communication link of current element
   * 
   * @param eObj
   *          : ExchangeItem
   * @return List<Component>
   */
  public List<Component> getCompOfReceivingCommLinkUsingCrossRef(EObject eObj) {
    List<Component> result = new ArrayList<>();

    Collection<Setting> inverseReferences = CapellaElementExt.getInverseReferencesOfEObject(eObj);

    for (Setting setting : inverseReferences) {
      EObject eObject = setting.getEObject();
      // filter CommunicationLink
      if (eObject instanceof CommunicationLink) {
        CommunicationLink link = (CommunicationLink) eObject;
        if (CommunicationLinkExt.isReceiver(link)) {
          EObject container = getParentContainer(link);
          if (container instanceof Component) {
            result.add((Component) container);
          }
        }
      }
    }

    return result;
  }

  /**
   * get ExchangeItem by kinds
   * 
   * @param comp
   *          : Component
   * @return : Collection<AbstractExchangeItem>
   */
  public Collection<AbstractExchangeItem> getExchangeItemsByTransmitkinds(Component comp) {
    // get owned communication link
    EList<CommunicationLink> ownedCommunicationLinks = comp.getOwnedCommunicationLinks();
    // prepare list of sending communication link kind
    CommunicationLinkKind[] sending = new CommunicationLinkKind[] { CommunicationLinkKind.SEND,
        CommunicationLinkKind.CALL, CommunicationLinkKind.WRITE, CommunicationLinkKind.PRODUCE,
        CommunicationLinkKind.TRANSMIT };
    // get exchange : filter by list of prepared kind
    return CommunicationLinkExt.getExchangeItemsByKinds(ownedCommunicationLinks, sending);
  }

  /**
   * @used in common.odesign Return true if ShowTriggerFESource filter is on
   * @param transition
   * @param view
   * @return
   */
  public boolean isShowTriggerSourceFunctionEnable(EObject transition, EObject view) {
    return InformationServices.getService().isDiagramFilterEnable(transition, view,
        IMappingNameConstants.SHOW_TRIGGER_SOURCE_FUNCTION);
  }

  /**
   * @used in common.odesign Return diagram label for state transition : mode and state diagram syntax : <Trigger> [
   *       <Guard>] / <Effect> (Note : if no <Trigger> <TriggerDesecription> is displayed)
   * @param context
   *          : StateTransition
   * @return : String
   */
  public String getStateTransitionLabel(EObject context, EObject view) {
    StringBuilder result = new StringBuilder();

    if (context instanceof StateTransition) {
      StateTransition transition = (StateTransition) context;

      // Trigger
      EList<AbstractEvent> triggers = transition.getTriggers();
      boolean firstTriggerToDisplay = true;
      for (AbstractEvent trigger : triggers) {
        if (trigger != null) {
          String name = "";
          if (trigger instanceof FunctionalExchange && isShowTriggerSourceFunctionEnable(context, view)) {
            name = ModeStateMachineServices.getService()
                .getIncomingFunctionalExchangeLabel((FunctionalExchange) trigger);
          } else
            name = EObjectExt.getText(trigger);
          if (trigger instanceof ChangeEvent) {
            ChangeEvent changeEvent = (ChangeEvent) trigger;
            name = "(" + changeEvent.getKind() + ") "; //$NON-NLS-1$ //$NON-NLS-2$
          }
          if (trigger instanceof TimeEvent) {
            TimeEvent timeEvent = (TimeEvent) trigger;
            name = "(" + timeEvent.getKind() + ") "; //$NON-NLS-1$ //$NON-NLS-2$
          }
          if (firstTriggerToDisplay) {
            firstTriggerToDisplay = false;
          } else {
            result.append(", "); //$NON-NLS-1$
          }
          result.append(name);
          if (trigger instanceof StateEvent) {
            Constraint triggerCondition = ((StateEvent) trigger).getExpression();
            if (triggerCondition != null) {
              result.append(CapellaServices.getService().getConstraintLabel(triggerCondition));
            } else {
              result.append(trigger.getName());
            }
          }
        }
      }

      if (triggers.isEmpty()) {
        String triggerDescription = transition.getTriggerDescription();
        if ((null != triggerDescription) && !triggerDescription.equalsIgnoreCase(ICommonConstants.EMPTY_STRING)) {
          result.append(triggerDescription);
        }
      }

      if (transition.getGuard() != null) {
        String constraintLabel = CapellaServices.getService().getConstraintLabel(transition.getGuard());
        if ((constraintLabel != null) && !constraintLabel.isEmpty()) {
          result.append(" [" + constraintLabel + "] ");
        }
      }

      if (!transition.getEffect().isEmpty()) {
        result.append(" / "); //$NON-NLS-1$

        boolean firstEffectToDisplay = true;
        for (AbstractEvent effect : transition.getEffect()) {
          if (effect != null) {
            if (firstEffectToDisplay) {
              firstEffectToDisplay = false;
            } else {
              result.append(", "); //$NON-NLS-1$
            }
            if (effect instanceof FunctionalExchange) {
              result.append(ModeStateMachineServices.getService()
                  .getOutgoingFunctionalExchangeLabel((FunctionalExchange) effect));
            } else
              result.append(EObjectExt.getText(effect));
          }
        }
      }
    }
    return result.toString();
  }

  public void refreshEntitiesArchitecture(ContainerMapping mapping, DDiagram diagram) {
    if (null == mapping) {
      return;
    }

    // local cache for fast access to components and their containers
    HashMap<Component, DNodeContainer> componentContainerMap = new HashMap<>();

    // containers that should be analyzed in order to determine if they should be moved
    Set<DNodeContainer> containersToAnalyze = new HashSet<>();

    // construct the local cache for components and containers
    for (DDiagramElementContainer container : diagram.getContainers()) {
      boolean shouldAnalyze = container instanceof DNodeContainer && container.getTarget() instanceof Component
          && container.getDiagramElementMapping().equals(mapping);

      if (shouldAnalyze) {
        Component component = (Component) container.getTarget();
        DNodeContainer nodeContainer = (DNodeContainer) container;

        componentContainerMap.put(component, nodeContainer);

        // only analyze visible containers
        if (CapellaServices.getService().isVisibleInDiagram(diagram, container)) {
          containersToAnalyze.add(nodeContainer);
        }
      }
    }

    // containers to move
    Set<DNodeContainer> containersToMove = new HashSet<>();

    // extract the components that should be moved
    for (DNodeContainer container : containersToAnalyze) {
      Component component = (Component) container.getTarget();
      Component parentComponent = ComponentExt.getParent(component);
      DNodeContainer parentContainer = componentContainerMap.get(parentComponent);

      // parent is deleted || parent does not contain the child
      boolean shouldMove = parentContainer == null || !parentContainer.getOwnedDiagramElements().contains(container);

      if (shouldMove) {
        diagram.getOwnedDiagramElements().add(container);
        containersToMove.add(container);
      }
    }

    // move the dirty components to their new parent
    for (DNodeContainer container : containersToMove) {
      Component component = (Component) container.getTarget();
      Component parentComponent = ComponentExt.getParent(component);

      while (parentComponent != null) {
        DNodeContainer parentContainer = componentContainerMap.get(parentComponent);

        boolean parentIsValid = parentContainer != null
            && !parentContainer.getOwnedDiagramElements().contains(container)
            && CapellaServices.getService().isVisibleInDiagram(diagram, parentContainer);

        if (parentIsValid) {
          parentContainer.getOwnedDiagramElements().add(container);
          break;
        }

        parentComponent = ComponentExt.getParent(parentComponent);
      }
    }
  }

  /**
   * get all the {@link Component} except {@link ComponentContext} from block architecture
   * 
   * @param context
   * @param arch
   * @return : list of components
   */
  public List<CapellaElement> getAllComponentFromBlockArchitecture(EObject context, BlockArchitecture arch) {
    return BlockArchitectureExt.getAllComponents(arch).stream().collect(Collectors.toList());
  }

  /**
   * @used in common.odesign Remove views and Create views depending on the selected element list
   * @param context
   *          : diagram context
   * @param selectedInterfaces
   *          : list of element to be show in diagram
   * @param diagram
   *          current diagram
   * @return
   */

  public EObject showHideActors(EObject context, List<CapellaElement> selectedOperations, DDiagram diagram) {
    // This method is used in IDB and capability diagrams
    if (selectedOperations == null) // for acceleo2aql
      selectedOperations = new ArrayList<>();
    Map<CapellaElement, AbstractDNode> visibleElements = new HashMap<>();
    // collect all the visible abstractActor element from the diagram
    for (DDiagramElement aNode : DiagramServices.getDiagramServices().getDiagramElements(diagram)) {
      EObject target = aNode.getTarget();
      if ((aNode instanceof AbstractDNode) && (target != null)
          && (target instanceof Component && ((Component) target).isActor())) {
        visibleElements.put((CapellaElement) target, (AbstractDNode) aNode);
      }
    }
    removeAndCreateActorsAndCapabilities(selectedOperations, diagram, visibleElements);
    return context;
  }

  /**
   * @used in common.odesign Remove views and Create views depending on the selected element list
   * @param context
   *          : diagram context
   * @param selectedInterfaces
   *          : list of element to be show in diagram
   * @param diagram
   *          current diagram
   * @return
   */

  public EObject showHideCapabilityRealizations(EObject context, List<CapellaElement> selectedOperations,
      DDiagram diagram) {
    Map<CapellaElement, AbstractDNode> visibleElements = new HashMap<>();
    // collect all the visible abstractActor element from the diagram
    for (DDiagramElement aNode : DiagramServices.getDiagramServices().getDiagramElements(diagram)) {
      EObject target = aNode.getTarget();
      if ((aNode instanceof AbstractDNode) && (target != null) && (target instanceof CapabilityRealization)) {
        visibleElements.put((CapellaElement) target, (AbstractDNode) aNode);
      }
    }

    removeAndCreateActorsAndCapabilities(selectedOperations, diagram, visibleElements);
    return context;
  }

  private void removeAndCreateActorsAndCapabilities(List<CapellaElement> selectedOperations, DDiagram diagram,
      Map<CapellaElement, AbstractDNode> visibleElements) {
    // remove elements : if view is of type DContainer or DNode
    // any new type should be taken into consideration
    for (Entry<CapellaElement, AbstractDNode> me : visibleElements.entrySet()) {
      if (!selectedOperations.contains(me.getKey())) {
        if ((me.getValue() instanceof DDiagramElementContainer) || (me.getValue() instanceof DDiagram)) {
          DiagramServices.getDiagramServices().removeContainerView(me.getValue());
        } else if (me.getValue() instanceof DNode) {
          DiagramServices.getDiagramServices().removeNodeView((DNode) me.getValue());
        }
      }
    }

    // create elements
    for (CapellaElement anElement : selectedOperations) {
      EObject container = CapellaServices.getService().getBestGraphicalContainer(anElement, diagram,
          anElement.eClass());
      if (!visibleElements.containsKey(anElement)) {

        if (anElement instanceof Component) {
          // create node or container view for an actor
          AbstractNodeMapping mapping = IBServices.getService().getMappingIDComponent(anElement, diagram);
          if (mapping == null) {
            mapping = getMappingCRBActor(anElement, diagram);
          }

          DiagramServices.getDiagramServices().createAbstractDNode(mapping, anElement, (DragAndDropTarget) container,
              diagram);

        } else if (anElement instanceof AbstractCapability) {
          // create node or container view for a capability
          AbstractNodeMapping mapping = getMappingCRBCapability(anElement, diagram);
          DiagramServices.getDiagramServices().createAbstractDNode(mapping, anElement, (DragAndDropTarget) container,
              diagram);

        }

      }
    }
  }

  public AbstractNodeMapping getMappingCRBActor(EObject element, DDiagram diagram) {

    String mappingName = ""; //$NON-NLS-1$
    if (IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK.equals(diagram.getDescription().getName())
        && element instanceof Component && ((Component) element).isActor()) {
      mappingName = IMappingNameConstants.CRB_COMPONENT_MAPPING;
    }
    return DiagramServices.getDiagramServices().getContainerMapping(diagram, mappingName);
  }

  public AbstractNodeMapping getMappingCRBCapability(EObject element, DDiagram diagram) {
    boolean isContainerMapping = true;

    String mappingName = ""; //$NON-NLS-1$
    if (IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK.equals(diagram.getDescription().getName())
        && element instanceof CapabilityRealization) {
      mappingName = IMappingNameConstants.CRB_CAPABILITY_REALIZATION_MAPPING;
      isContainerMapping = false;
    }

    if (isContainerMapping) {
      return DiagramServices.getDiagramServices().getContainerMapping(diagram, mappingName);
    }

    return DiagramServices.getDiagramServices().getNodeMapping(diagram, mappingName);
  }

  /**
   * Return first abstract capability package
   * 
   * @param context
   *          : a capella element
   * @return value could be null if no abstract capability package not found
   */
  public EObject getFirstAbstractCapPkg(EObject context) {
    BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture((ModelElement) context);
    if (null != arch) {
      return arch.getOwnedAbstractCapabilityPkg();
    }

    return null;
  }

  public void removeCapellaElement(EObject capellaElement) {
    // create empty list
    if (capellaElement instanceof CapellaElement) {
      CapellaServices.getService().removeElement((CapellaElement) capellaElement);
    }
  }

  public boolean isPrimitive(EObject context) {
    if (context instanceof Class) {
      return ((Class) context).isIsPrimitive();
    }
    return false;
  }

  public boolean isAbstract(EObject context) {
    // DataType, Class
    if (context instanceof GeneralizableElement) {
      GeneralizableElement genEle = (GeneralizableElement) context;
      return genEle.isAbstract();
    }
    // DataValue
    else if (context instanceof DataValue) {
      DataValue value = (DataValue) context;
      return value.isAbstract();
    }
    // Property
    else if (context instanceof Feature) {
      Feature feature = (Feature) context;
      return feature.isIsAbstract();
    }

    return false;
  }

  public boolean isPartOfKey(EObject context) {
    // DataType, Class
    // Property
    if (context instanceof Property) {
      Property property = (Property) context;
      return property.isIsPartOfKey();
    }

    return false;
  }

  public boolean isComponentTargetAvailableForCapInvolvement(EObject context, EObject preSource, EObject preTarget) {
    return CapabilityRealizationExt.isComponentTargetAvailableForCapInvolvement(context, preSource, preTarget);
  }

  public boolean isARootComponent(EObject context) {
    if (context instanceof CapellaElement) {
      BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(context);
      if (null != arch) {
        Component firstComponent = getFirstComponent(arch);
        if ((null != firstComponent) && firstComponent.equals(context)) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * Returns available components which are accessible CRB-Show-Hide-Component.
   */
  public Collection<Component> getCRBShowHideComponent(DSemanticDecorator decorator) {
    Collection<Component> components = new ArrayList<>();

    if ((decorator.getTarget() instanceof Component)) {
      return getCCIIShowHideComponent(decorator);
    }

    EObject parentContainer = getParentContainer(decorator.getTarget());
    if (null == parentContainer) {
      return components;
    }

    if (parentContainer instanceof Component) {
      return getSubComponents(parentContainer);
    } else if (parentContainer instanceof BlockArchitecture) {
      Component firstComponent = ((BlockArchitecture) parentContainer).getSystem();
      if (null != firstComponent) {
        return getSubComponents(firstComponent);
      }
    }

    return components;
  }

  /**
   * Returns available components which are accessible by brothers-part CCEI-Show-Hide-Component.
   */
  public Collection<Component> getCRBShowHideActors(EObject context) {

    Collection<Component> components = new ArrayList<>();

    // Add actors
    components.addAll(ComponentExt.getSubDefinedActors(getArchitecture(context)));

    return filterActors(components);
  }

  /**
   * Returns available Capability Realization which are accessible by brothers-part CRA-Show-Hide-Component.
   */
  public Collection<CapabilityRealization> getCRBShowHideCapabilityRealizations(EObject context) {
    Collection<CapabilityRealization> elements = new ArrayList<>();
    if (context instanceof CapellaElement) {
      BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(context);
      elements.addAll(CapabilityRealizationExt.getAllCapabilityRealizations(arch));
    }
    return elements;
  }

  /**
   * Used in common.odesign CRB Diagram Used to filter the drag and drop from project Explorer
   * 
   * @param object
   * @param diagram
   * @return
   */
  public boolean isDiagramAndElementFromSameLayer(EObject context, EObject diagram) {
    if ((null == context) || (null == diagram)
        || !((context instanceof ModelElement) || (diagram instanceof DSemanticDiagram))) {
      return false;
    }

    boolean laLayer = CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) context);
    boolean paLayer = CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement) context);
    boolean epbsLayer = CapellaLayerCheckingExt.isInEPBSLayer((CapellaElement) context);

    EObject parentContainer = getParentContainer(((DSemanticDiagram) diagram).getTarget());
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

  public List<? extends EObject> getConstraintToInsertInDiagram(EObject context) {
    List<EObject> result = new ArrayList<>(0);

    if (context instanceof DDiagram) {
      // return all the constraints of current level
      DSemanticDecorator diagram = (DSemanticDecorator) context;
      EObject target = diagram.getTarget();
      if (target instanceof ModelElement) {
        BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(target);
        return BlockArchitectureExt.getAllConstraints(arch);
      }
    } else if (context instanceof DDiagramElement) {
      DDiagramElement element = (DDiagramElement) context;
      EObject target = element.getTarget();
      if (target instanceof ModelElement) {
        ModelElement capellaElement = (ModelElement) target;
        result.addAll(capellaElement.getConstraints());
      }
    }
    return result;
  }

  /**
   * Create Constraint, if not already in diagram Create ConstaintElement(in unsynchronized mode ) if not already in
   * diagram
   * 
   * @param context
   * @param constraint
   * @param dDiagram
   * @param constraintsInDiagram
   * @param kindDiagram
   *          = true if (Diagram), false if (Scenario)
   */
  public void createConstraintWithConstaintedElementInDiagram(EObject context, EObject constraint, DDiagram dDiagram,
      List<Constraint> constraintsInDiagram, boolean kindDiagram) {
    if (constraintsInDiagram == null) // for acceleo2aql
      constraintsInDiagram = new ArrayList<>();
    if ((null == context) || (null == constraint) || (null == dDiagram)) {
      return;
    }

    // diagramServices instance
    DiagramServices diagramServices = DiagramServices.getDiagramServices();
    NodeMapping constraintNodeMapping = null;
    EdgeMapping constaintEdgeMapping = null;
    if (kindDiagram) {
      // get constraint node mapping
      constraintNodeMapping = diagramServices.getNodeMapping(dDiagram,
          IMappingNameConstants.CDB_CONSTRAINT_MAPPING_NAME);
      // edge mapping to be created in (unSynchronized mode)
      constaintEdgeMapping = diagramServices.getEdgeMapping(dDiagram,
          IMappingNameConstants.CDB_CONSTRAINT_ELEMENT_MAPPING_NAME);
    } else {
      // its scenario kind
      // get constraint node mapping
      constraintNodeMapping = diagramServices.getNodeMapping(dDiagram, IMappingNameConstants.IS_CONSTRAINT_MAPPING);
      // edge mapping to be created in (unSynchronized mode)
      constaintEdgeMapping = diagramServices.getEdgeMapping(dDiagram,
          IMappingNameConstants.IS_CONSTRAINT_ELEMENT_MAPPING);
    }

    // constraint node
    DNode constraintNode = null;

    // create or retrieve node for 'constraint'
    if (constraintsInDiagram.contains(constraint) && (null != constraintNodeMapping)) {
      // retrieve constraint node
      Iterable<DDiagramElement> diagramElements = diagramServices.getDiagramElements(dDiagram);
      for (DDiagramElement dDiagramElement : diagramElements) {
        EObject target = dDiagramElement.getTarget();
        if ((target != null) && target.equals(constraint) && (dDiagramElement instanceof DNode)) {
          RepresentationElementMapping mapping = dDiagramElement.getMapping();
          if ((null != mapping) && mapping.equals(constraintNodeMapping)) {
            constraintNode = (DNode) dDiagramElement;
          }
        }
      }
    } else {
      // create constraint node
      constraintNode = diagramServices.createNode(constraintNodeMapping, constraint, dDiagram, dDiagram);
    }

    if (!dDiagram.isSynchronized() && null != constaintEdgeMapping && null != constraintNode) {
      // create constriantElementLink does not exist in diagram
      EObject target = constraintNode.getTarget();
      if (target instanceof Constraint) {
        Constraint cst = (Constraint) target;
        EList<ModelElement> constrainedElements = cst.getConstrainedElements();
        for (ModelElement modelElement : constrainedElements) {
          // for all the constraintElements of the constraint in the diagram
          // check if there is any edge, if not create one
          if (diagramServices.isOnDiagram(dDiagram, modelElement)) {
            EList<DEdge> outgoingEdges = constraintNode.getOutgoingEdges();
            boolean edgeExist = false;
            for (DEdge dEdge : outgoingEdges) {
              EdgeTarget edgeTargetNode = dEdge.getTargetNode();
              if (null != edgeTargetNode) {
                EObject edgeTargetNodeTarget = ((DDiagramElement) edgeTargetNode).getTarget();
                // check if edge exist between 'modelElement' and given Constraint
                if ((null != edgeTargetNodeTarget) && edgeTargetNodeTarget.equals(modelElement)) {
                  // no need to create an edge
                  edgeExist = true;
                  break;
                }
              }
            }
            if (!edgeExist) {
              // get TargetView for the edge
              EObject diagramElement = diagramServices.getDiagramElement(dDiagram, modelElement);
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

  /**
   * @used in physical.odesign for the moment (should be common for all) get proper target element from given target to
   *       be added in constraint, also move the constraint in proper target if needed
   * @param context
   * @param target
   * @param targetDiagramEle
   */
  public void createConstraintElement(EObject context, EObject target, DDiagramElement targetDiagramEle) {
    if ((null == context) || (null == target) || (null == targetDiagramEle)) {
      return;
    }
    if ((context instanceof Constraint) && (target instanceof ModelElement)) {
      Constraint constraint = (Constraint) context;
      EList<ModelElement> constrainedElements = constraint.getConstrainedElements();
      if (null != constrainedElements) {
        if (constrainedElements.isEmpty()) {
          constrainedElements.addAll(getTargetToAddAsConstraintedElement((ModelElement) target, targetDiagramEle));
          // move the constraint
          ModelElement properTargetToMoveConstraint = getProperTargetToMoveConstraint((ModelElement) target,
              targetDiagramEle);
          if (null != properTargetToMoveConstraint) {
            properTargetToMoveConstraint.getConstraints().add(constraint);
          }
        } else {
          constrainedElements.addAll(getTargetToAddAsConstraintedElement((ModelElement) target, targetDiagramEle));
        }
      }
    }
  }

  /**
   * Return Target element in which the constraint will be moved <b> null in case if diagram element with mapping
   * "PAB_Deployment" <b>
   * 
   * @param target
   * @param targetDiagramEle
   * @return
   */
  private ModelElement getProperTargetToMoveConstraint(ModelElement target, DDiagramElement targetDiagramEle) {
    DiagramElementMapping diagramElementMapping = targetDiagramEle.getDiagramElementMapping();
    if (null != diagramElementMapping && diagramElementMapping.getName()
        .equals(IMappingNameConstants.PAB_PHYSICAL_COMPONENT_DEPLOYMENT_MAPPING_NAME)) {
      return null;
    }
    return target;
  }

  /**
   * Return the default target <b> Exception for diagram element with mapping "PAB_Deployment" <b>
   * 
   * @param target
   * @return
   */
  private List<ModelElement> getTargetToAddAsConstraintedElement(ModelElement target,
      DDiagramElement targetDiagramEle) {
    List<ModelElement> result = new ArrayList<>();
    // if targetDiagramElemnet deployed part
    // return the deployedElemnet link as target
    boolean flag = false;
    DiagramElementMapping diagramElementMapping = targetDiagramEle.getDiagramElementMapping();
    if (null != diagramElementMapping
        && diagramElementMapping.getName().equals(IMappingNameConstants.PAB_PHYSICAL_COMPONENT_DEPLOYMENT_MAPPING_NAME)
        && target instanceof Part) {
      Part part = (Part) target;
      EList<AbstractDeploymentLink> deployingLinks = part.getDeployingLinks();
      if ((null != deployingLinks) && !deployingLinks.isEmpty()) {
        result.addAll(deployingLinks);
        flag = true;
      }
    }
    if (!flag) {
      result.add(target);
    }
    return result;
  }

  /**
   * Return all constriantedElement for given Constraint <b> Exception for PartDeploymentLink (return its
   * DeployedElement) <b>
   * 
   * @param context
   * @return
   */
  public List<EObject> targeFinderExpressionForConstraint(Constraint context) {
    List<EObject> result = new ArrayList<>();
    // Retrieve all contrainedElements for a given constraint
    // If applied on a deployment, retrieve the part and its component
    // If applied on a part, then retrieve also its component
    // If applied on a component, then retrieve all its parts

    LinkedList<EObject> toVisit = new LinkedList<>();
    toVisit.addAll(context.getConstrainedElements());
    while (!toVisit.isEmpty()) {
      EObject object = toVisit.removeFirst();
      if (result.contains(object)) {
        continue;
      }
      result.add(object);

      if (object instanceof PartDeploymentLink) {
        PartDeploymentLink link = (PartDeploymentLink) object;
        DeployableElement deployedElement = link.getDeployedElement();
        if (null != deployedElement) {
          toVisit.add(deployedElement);
        }
      } else if (object instanceof Component) {
        toVisit.addAll(((Component) object).getRepresentingParts());
      } else if (object instanceof Part) {
        result.add(((Part) object).getAbstractType());
      }
    }
    return result;
  }

  /**
   * @param semantic
   * @param sourceView
   * @param targetView
   * @return
   */
  public boolean isValidConstrainedElementsEdge(EObject semantic, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {
    if (semantic instanceof Constraint) {
      Constraint constraint = (Constraint) semantic;
      EList<ModelElement> elements = constraint.getConstrainedElements();
      return (elements != null) && !elements.isEmpty();
    }
    return false;
  }

  /**
   * Return true if the property or AssociationEnds are derived.
   * 
   * @param capellaElement
   *          : Capella element
   * @return boolean
   */
  public boolean isPropertyDerived(EObject capellaElement) {
    if (capellaElement instanceof Property) {
      Property property = (Property) capellaElement;
      if (property.isIsDerived()) {
        return true;
      }
    } else if (capellaElement instanceof Association) {
      Association ass = (Association) capellaElement;
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
   * Check if a CommunicationLink (graphically represented by the given object) between a component c1 and an interface
   * is not defined in the children of c1. Return true if the edge does not represents a CommunicationLink as previously
   * defined or if the link is not graphically represented in one of the component children.
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

      if (((target instanceof ComponentPort) && isProvidedEdge(object))
          || (target instanceof InterfaceImplementation)) {
        return isImplementedOrProvidedLinkEdgeNotRepresentedInChildrenComponent(object);
      }
      if (((target instanceof ComponentPort) && (isRequiredEdge(object))) || (target instanceof InterfaceUse)) {
        return isUsedOrRequiredLinkEdgeNotRepresentedInChildrenComponent(object);
      }
    }
    return true;
  }

  /**
   * Check if a provides/implements link (graphically represented by the given object) between a component c1 and an
   * interface is not defined in the children of c1. Return true if the edge does not represents a use/implements link
   * as previously defined or if the link is not graphically represented in one of the component children.
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
                  if (((link instanceof InterfaceImplementation) && (childLink instanceof InterfaceImplementation)
                      && (((InterfaceImplementation) link)
                          .getImplementedInterface() == ((InterfaceImplementation) childLink)
                              .getImplementedInterface()))) {
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
                  if ((target instanceof InterfaceImplementation)
                      && (currentEdge.getTargetNode() == edge.getTargetNode())) {
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
   * Check if a provides/implements link (graphically represented by the given object) between a component c1 and an
   * interface is not defined in the children of c1. Return true if the edge does not represents a use/implements link
   * as previously defined or if the link is not graphically represented in one of the component children.
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
                  if (((link instanceof InterfaceUse) && (childLink instanceof InterfaceUse)
                      && (((InterfaceUse) link).getUsedInterface() == ((InterfaceUse) childLink).getUsedInterface()))) {
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
   * Check if an edge represents a provides link. Be careful (for developers only), this code uses the
   * targetFinderExpression of mappings of type EdgeMapping to check if edges represent provides link. Since the
   * targetFinderExpression property is a string built with class property name of the metamodel, we use literals of the
   * CsPackage for features 'requiredInterfaces' and 'providedInterfaces' so that a change concerning them in the
   * metamodel will make this code not compiling as a side effect.
   * 
   * @param object
   * @return
   */
  public boolean isProvidedEdge(EObject object) {
    if (object instanceof DEdge) {
      DEdge currentEdge = (DEdge) object;
      EObject target = currentEdge.getTarget();
      if (target instanceof ComponentPort) {
        IEdgeMapping mapping = currentEdge.getActualMapping();
        if (mapping instanceof EdgeMapping) {
          String featureDef = ((EdgeMapping) mapping).getTargetFinderExpression();
          if (featureDef.equals("feature:" + CsPackage.Literals.COMPONENT__PROVIDED_INTERFACES.getName())) { //$NON-NLS-1$
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Check if an edge represents a requires link. Be careful (for developers only), this code uses the
   * targetFinderExpression of mappings of type EdgeMapping to check if edges represent requires link. Since the
   * targetFinderExpression property is a string build with class property name of the metamodel, we use literals of the
   * CsPackage for features 'requiredInterfaces' and 'providedInterfaces' so that a change concerning them in the
   * metamodel will make this code not compiling as a side effect.
   * 
   * @param object
   * @return
   */
  public boolean isRequiredEdge(EObject object) {
    if (object instanceof DEdge) {
      DEdge currentEdge = (DEdge) object;
      EObject target = currentEdge.getTarget();
      if (target instanceof ComponentPort) {
        IEdgeMapping mapping = currentEdge.getActualMapping();
        if (mapping instanceof EdgeMapping) {
          String featureDef = ((EdgeMapping) mapping).getTargetFinderExpression();
          if (featureDef.equals("feature:" + CsPackage.Literals.COMPONENT__REQUIRED_INTERFACES.getName())) { //$NON-NLS-1$
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Check if the input is a PhysicalPort
   * 
   * @param port
   * @return
   */
  public boolean isAPhysicalPort(EObject port) {
    return port instanceof PhysicalPort;
  }

  public Collection<EObject> getComputedComponentExchangeSource(EObject related, DDiagram diagram) {
    if (related instanceof ComponentExchange) {
      ComponentExchange connection = (ComponentExchange) related;
      EObject componentExchangeSource = getComponentExchangeSource(connection);
      return getVisibleEdgeEnds(diagram, componentExchangeSource);
    }
    return Collections.singletonList(related);
  }

  /**
   * Returns target for mapping LAB_ComputedComponentExchange
   */
  public Collection<EObject> getComputedComponentExchangeTarget(EObject related, DDiagram diagram) {
    if (related instanceof ComponentExchange) {
      ComponentExchange connection = (ComponentExchange) related;
      EObject componentExchangeTarget = getComponentExchangeTarget(connection);
      return getVisibleEdgeEnds(diagram, componentExchangeTarget);
    }
    return Collections.singletonList(related);
  }

  public Object getComputedComponentExchangeSemanticCandidates(final DDiagram diagram) {
    Collection<ComponentExchange> result = new HashSet<>();
    final DDiagramContents context = new DDiagramContents(diagram);
    for (DDiagramElement dNode : context.getDiagramElements()) {
      EObject target = dNode.getTarget();
      if (target instanceof Part) {
        result.addAll(ComponentExt.getAllRelatedComponentExchange((Part) target, true));
        Collection<Part> allSubUsedParts = ComponentExt.getAllSubUsedParts((Part) target, true);
        for (Part part : allSubUsedParts) {
          result.addAll(ComponentExt.getAllRelatedComponentExchange(part, true));
        }
      } else if (target instanceof Component) {
        result.addAll(getCache(ComponentExt::getAllRelatedComponentExchange, (Component) target));
        Collection<Component> allSubUsedComponents = ComponentExt.getAllSubUsedComponents((Component) target);
        for (Component component : allSubUsedComponents) {
          result.addAll(getCache(ComponentExt::getAllRelatedComponentExchange, component));
        }
      }
    }
    return result;
  }

  public Collection<EObject> getComputedPhysicalLinkSource(EObject related, DDiagram diagram) {
    if (related instanceof PhysicalLink) {
      EObject physicalLinkSource = getPhysicalLinkSource((PhysicalLink) related);
      return getVisibleEdgeEnds(diagram, physicalLinkSource);
    }
    return Collections.singletonList(related);
  }

  public Collection<EObject> getComputedPhysicalLinkTarget(EObject related, DDiagram diagram) {
    if (related instanceof PhysicalLink) {
      EObject physicalLinkTarget = getPhysicalLinkTarget((PhysicalLink) related);
      return getVisibleEdgeEnds(diagram, physicalLinkTarget);
    }
    return Collections.singletonList(related);
  }

  public Object getComputedPhysicalLinkSemanticCandidates(final DDiagram diagram) {
    Collection<PhysicalLink> result = new ArrayList<>();
    final DDiagramContents context = new DDiagramContents(diagram);
    for (DDiagramElement dNode : context.getDiagramElements()) {
      EObject target = dNode.getTarget();
      if (target instanceof Part) {
        result.addAll(getCache(PhysicalLinkExt::getAllRelatedPhysicalLinks, (Part) target));
        Collection<Part> allSubUsedParts = ComponentExt.getAllSubUsedParts((Part) target, true);
        for (Part part : allSubUsedParts) {
          result.addAll(getCache(PhysicalLinkExt::getAllRelatedPhysicalLinks, part));
        }
      } else if (target instanceof Component) {
        result.addAll(getCache(PhysicalLinkExt::getAllRelatedPhysicalLinks, (Component) target));
        Collection<Component> allSubUsedComponents = ComponentExt.getAllSubUsedComponents((Component) target);
        for (Component component : allSubUsedComponents) {
          result.addAll(PhysicalLinkExt.getAllRelatedPhysicalLinks(component));
        }
      }
    }
    return result;
  }

  public boolean isValidComputedPhysicalLinkEdge(EObject communication, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {

    // Case 1
    if (!(communication instanceof PhysicalLink)) {
      return false;
    }

    PhysicalLink pl = (PhysicalLink) communication;

    // Case 2
    return isValidComputedLink(communication, pl.getSourcePhysicalPort(), pl.getTargetPhysicalPort(), sourceView,
        targetView, IMappingNameConstants.LAB_COMPUTED_PHYSICAL_LINK, IMappingNameConstants.PAB_COMPUTED_PHYSICAL_LINK,
        IFilterNameConstants.FILTER_XAB_HIDE_COMPUTED_PL);
  }

  public boolean isValidComputedComponentExchangeEdge(EObject communication, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {

    // Case 1
    if (!(communication instanceof ComponentExchange)) {
      return false;
    }

    ComponentExchange ce = (ComponentExchange) communication;

    // Case 2
    if (ce.getKind() == ComponentExchangeKind.DELEGATION) {
      return false;
    }

    // Case 3
    // Check that source component is not a Node
    EObject source = sourceView.getTarget();
    if (source instanceof Part) {
      AbstractType sourceType = ((Part) source).getAbstractType();
      if (sourceType instanceof PhysicalComponent
          && ((PhysicalComponent) sourceType).getNature() == PhysicalComponentNature.NODE) {
        return false;
      }
    }

    // Case 4
    // Check that target component is not a Node
    EObject target = targetView.getTarget();
    if (target instanceof Part) {
      AbstractType targetType = ((Part) target).getAbstractType();
      if (targetType instanceof PhysicalComponent
          && ((PhysicalComponent) targetType).getNature() == PhysicalComponentNature.NODE) {
        return false;
      }
    }

    // Case 5
    return isValidComputedLink(communication, ce.getSourcePort(), ce.getTargetPort(), sourceView, targetView,
        IMappingNameConstants.LAB_COMPUTED_COMPONENT_EXCHANGE, IMappingNameConstants.PAB_COMPUTED_COMPONENT_EXCHANGE,
        IFilterNameConstants.FILTER_XAB_HIDE_COMPUTED_CE);
  }

  /**
   * Common cases for {@link #isValidComputedComponentExchangeEdge(EObject, DSemanticDecorator, DSemanticDecorator)} and
   * {@link #isValidComputedPhysicalLinkEdge(EObject, DSemanticDecorator, DSemanticDecorator)}
   */
  private boolean isValidComputedLink(EObject communication, Port sourcePort, Port targetPort,
      DSemanticDecorator sourceView, DSemanticDecorator targetView, String labMappingName, String pabMappingName,
      String filterName) {

    EObject source = sourceView.getTarget();
    EObject target = targetView.getTarget();

    // Case 1
    if (sourceView == targetView) {
      return false;
    }

    // Case 2
    if (source instanceof Port && target instanceof Port) {
      return false;
    }

    // Case 3
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView);
    DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);
    if (!FilterHelper.isDesactivatedOnce(filterName, descriptor)) {
      return false;
    }

    // Case 4
    if (source instanceof Port && target instanceof Part) {
      Part targetPart = (Part) target;
      AbstractType sourceComponent = (AbstractType) source.eContainer();
      AbstractType targetComponent = targetPart.getAbstractType();
      if (sourceComponent == targetComponent) {
        return false;
      }
      if (isAnAncestor(sourceComponent, targetComponent)) {
        return false;
      }
      // Check that the part of the Port source is not deployed by the target Part
      DNodeContainer portParentNode = (DNodeContainer) sourceView.eContainer();
      EObject portParentNodeTarget = portParentNode.getTarget();
      if (portParentNodeTarget instanceof Part && PartExt.isDeploying(targetPart, (Part) portParentNodeTarget)) {
        return false;
      }
    }

    // Case 5
    if (source instanceof Part && target instanceof Port) {
      Part sourcePart = (Part) source;
      AbstractType sourceComponent = sourcePart.getAbstractType();
      AbstractType targetComponent = (AbstractType) target.eContainer();
      if (targetComponent == sourceComponent) {
        return false;
      }
      if (isAnAncestor(targetComponent, sourceComponent)) {
        return false;
      }

      // Check that the part of the Port target is not deployed by the source Part
      DNodeContainer portParentNode = (DNodeContainer) targetView.eContainer();
      EObject portParentNodeTarget = portParentNode.getTarget();
      if (portParentNodeTarget instanceof Part && PartExt.isDeploying(sourcePart, (Part) portParentNodeTarget)) {
        return false;
      }
    }

    // Case 6
    if (source instanceof Part && target instanceof Part) {
      Part sourcePart = (Part) source;
      Part targetPart = (Part) target;
      AbstractType sourceComponent = sourcePart.getAbstractType();
      AbstractType targetComponent = targetPart.getAbstractType();

      if (PartExt.isDeploying(sourcePart, targetPart) || PartExt.isDeploying(targetPart, sourcePart)) {
        return false;
      }

      if (isAnAncestor(targetComponent, sourceComponent) || isAnAncestor(sourceComponent, targetComponent)) {
        return false;
      }
      // Check source
      if (isInnerPort((AbstractDNode) sourceView, sourcePort)) {
        return false;
      }

      // Check target
      if (isInnerPort((AbstractDNode) targetView, targetPort)) {
        return false;
      }
    }

    if (diagram != null) {
      DSemanticDecorator sourceElement = sourceView;
      DSemanticDecorator targetElement = targetView;
      if (sourceElement.getTarget() instanceof Port) {
        sourceElement = (DSemanticDecorator) sourceElement.eContainer();
      }
      if (targetElement.getTarget() instanceof Port) {
        targetElement = (DSemanticDecorator) targetElement.eContainer();
      }
      // Case 7
      Collection<DSemanticDecorator> elements = DiagramServices.getDiagramServices().getDiagramElements(diagram,
          communication);
      for (DSemanticDecorator view : elements) {
        if (view instanceof DEdge) {
          DEdge edge = (DEdge) view;
          if (edge.isVisible() && !(edge.getMapping().getName().equals(labMappingName)
              || edge.getMapping().getName().equals(pabMappingName))) {
            boolean hasSrc = false;
            DSemanticDecorator sourceNode = (DSemanticDecorator) edge.getSourceNode();
            if (sourceNode.getTarget() instanceof Port) {
              sourceNode = (DSemanticDecorator) sourceNode.eContainer();
            }
            List<DDiagramElement> toCheck = new ArrayList<>();
            toCheck.add((DDiagramElement) sourceElement);
            toCheck.addAll(((DNodeContainer) sourceElement).getOwnedDiagramElements());
            Iterator<DDiagramElement> sourceElementContents = toCheck.iterator();
            while (!hasSrc && sourceElementContents.hasNext()) {
              EObject next = sourceElementContents.next();
              if (next == sourceNode) {
                hasSrc = true;
              }
            }
            toCheck.clear();
            boolean hasTrgt = false;
            DSemanticDecorator targetNode = (DSemanticDecorator) edge.getTargetNode();
            if (targetNode.getTarget() instanceof Port) {
              targetNode = (DSemanticDecorator) targetNode.eContainer();
            }
            toCheck.add((DDiagramElement) targetElement);
            toCheck.addAll(((DNodeContainer) targetElement).getOwnedDiagramElements());
            Iterator<DDiagramElement> targetElementContents = toCheck.iterator();
            while (!hasTrgt && targetElementContents.hasNext()) {
              EObject next = targetElementContents.next();
              if (next == targetNode) {
                hasTrgt = true;
              }
            }
            if (hasSrc && hasTrgt) {
              return false;
            }
          }
        }
      }
    }

    return true;
  }

  private boolean isInnerPort(AbstractDNode node, Port port) {
    for (DDiagramElement diagramElement : node.getOwnedBorderedNodes()) {
      if (diagramElement.getTarget() == port && diagramElement.isVisible()) {
        return true;
      }
    }
    if (node instanceof DNodeContainer) {
      for (DDiagramElement next : ((DNodeContainer) node).getOwnedDiagramElements()) {
        if (next instanceof AbstractDNode && isInnerPort((AbstractDNode) next, port)) {
          return true;
        }
      }
    }
    return false;
  }

  private Collection<EObject> getVisibleEdgeEnds(DDiagram diagram, EObject source) {
    // When it is a port, try to find the diagram element
    // corresponding to this port and return it
    if (source instanceof Port) {
      DDiagramElement diagramElement = DiagramServices.getDiagramServices().getDiagramElement(diagram, source);
      // Port is on t
      if (diagramElement != null) {
        List<EObject> result = new ArrayList<>();
        if (diagramElement.isVisible()) {
          result.add(source);
        }
        if (source.eContainer() instanceof Component) {
          Collection<Part> representingParts = getCache(ComponentExt::getRepresentingParts,
              (Component) source.eContainer());
          if (!representingParts.isEmpty()) {
            Part portParent = representingParts.iterator().next();
            result.addAll(getVisibleEdgeEnds(diagram, portParent, false));
          }
        }
        return result;

      } else if (source.eContainer() instanceof Component) {
        Collection<Part> representingParts = ComponentExt.getRepresentingParts((Component) source.eContainer());
        if (!representingParts.isEmpty()) {
          Part portParent = representingParts.iterator().next();
          return getVisibleEdgeEnds(diagram, portParent, true);
        }
      }

    } else if (source instanceof Part) {
      return getVisibleEdgeEnds(diagram, (Part) source, true);
    }
    return Collections.emptyList();
  }

  private Collection<EObject> getVisibleEdgeEnds(DDiagram diagram, Part mainPart, boolean handleMainPart) {
    List<EObject> result = new ArrayList<>();
    List<DeploymentTarget> toHandle = new ArrayList<>();
    if (handleMainPart) {
      toHandle.add(mainPart);
    }
    addRelevantParts(diagram, mainPart, toHandle);
    for (DeploymentTarget element : getCache(PartExt::getDeployingElements, mainPart)) {
      if (!isChildView(diagram, mainPart, element)) {
        toHandle.add(element);
      }
    }
    for (DeploymentTarget deploymentTarget : toHandle) {
      Collection<Part> relevantParts = new ArrayList<>();
      relevantParts.add((Part) deploymentTarget);
      relevantParts.addAll(ComponentExt.getPartAncestors((Part) deploymentTarget));
      EObject firstVisibleAncestor = getFirstVisibleAncestor(diagram, relevantParts);
      if (firstVisibleAncestor instanceof Part) {
        result.add(firstVisibleAncestor);
      }
    }
    return result;
  }

  private boolean isChildView(DDiagram diagram, Part mainPart, DeploymentTarget element) {
    for (DSemanticDecorator view : DiagramServices.getDiagramServices().getDiagramElements(diagram, element)) {
      if (view instanceof DNodeContainer) {
        DNodeContainer node = (DNodeContainer) view;
        for (DDiagramElement child : node.getOwnedDiagramElements()) {
          if (mainPart.equals(child.getTarget()) && child.isVisible()) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private void addRelevantParts(DDiagram diagram, Part mainPart, List<DeploymentTarget> toHandle) {
    if (mainPart.eContainer() instanceof Component) {
      for (Part parentPart : getCache(ComponentExt::getRepresentingParts, (Component) mainPart.eContainer())) {
        Collection<DSemanticDecorator> diagramElements = DiagramServices.getDiagramServices()
            .getDiagramElements(diagram, parentPart);
        int foundCount = 0;
        for (DSemanticDecorator diagElt : diagramElements) {
          if (diagElt instanceof DNodeContainer) {
            DNodeContainer node = (DNodeContainer) diagElt;
            for (DDiagramElement elt : node.getOwnedDiagramElements()) {
              if (mainPart.equals(elt.getTarget()) && elt.isVisible()) {
                foundCount++;
              }
            }
          }
        }
        if (diagramElements.size() != foundCount) {
          toHandle.add(parentPart);
        }
      }
    }
  }

  private EObject getFirstVisibleAncestor(DDiagram diagram, Collection<Part> relevantParts) {
    for (Part part : relevantParts) {
      DDiagramElement diagramElement = DiagramServices.getDiagramServices().getDiagramElement(diagram, part);
      if (diagramElement != null && diagramElement.isVisible()) {
        return part;
      }
    }
    return null;
  }

  public Collection<ControlNode> getAvailableControlNodes(DDiagram diagram) {
    Set<FunctionalChain> displayedFunctionalChains = FunctionalChainServices.getFunctionalChainServices()
        .getDisplayedFunctionalChainsOnDiagram(diagram);
    return displayedFunctionalChains.stream().flatMap(fc -> FunctionalChainExt.getFlatControlNodes(fc).stream())
        .collect(Collectors.toList());
  }

  public Collection<SequenceLink> getAvailableSequenceLinks(DDiagram diagram) {
    Set<FunctionalChain> displayedFunctionalChains = FunctionalChainServices.getFunctionalChainServices()
        .getDisplayedFunctionalChainsOnDiagram(diagram);
    return displayedFunctionalChains.stream().flatMap(fc -> FunctionalChainExt.getFlatSequenceLinks(fc).stream())
        .collect(Collectors.toList());
  }

  public EObject getSequenceLinkSource(SequenceLink sequenceLink) {
    SequenceLinkEnd source = sequenceLink.getSource();
    if (source instanceof ControlNode)
      return source;
    else if (source instanceof FunctionalChainInvolvementFunction) {
      return ((FunctionalChainInvolvementFunction) source).getInvolved();
    }
    return null;
  }

  public EObject getSequenceLinkTarget(SequenceLink sequenceLink) {
    SequenceLinkEnd target = sequenceLink.getTarget();
    if (target instanceof ControlNode)
      return target;
    else if (target instanceof FunctionalChainInvolvementFunction) {
      return ((FunctionalChainInvolvementFunction) target).getInvolved();
    }
    return null;
  }

  public boolean isActor(EObject context) {
    return ComponentExt.isActor(context);
  }

  public Object getConstraintLocation(EObject context, EObject target) {
    if (null == context) {
      return null;
    }
    if (isMultipartMode((ModelElement) target))
      return target;
    if (target instanceof Part) {
      return ((Part) target).getAbstractType();
    }
    return target;
  }
}
