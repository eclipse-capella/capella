/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.task.DeleteEObjectTask;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.business.internal.sync.DDiagramElementSynchronizer;
import org.eclipse.sirius.diagram.business.internal.sync.DDiagramSynchronizer;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.ui.business.api.helper.graphicalfilters.CompositeFilterApplicationBuilder;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.Pin;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManager;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.commands.preferences.initializers.CapellaDiagramPreferencesInitializer;
import org.polarsys.capella.core.commands.preferences.preferences.CapellaDiagramPreferences;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionInvolvement;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPkgExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalExt;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.AggregationKind;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.PhysicalQuantity;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.RefinementLink;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.libraries.queries.QueryIdentifierConstants;
import org.polarsys.capella.core.linkedtext.ui.CapellaEmbeddedLinkedTextEditorInput;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.sirius.analysis.tool.StringUtil;

/**
 * Basic Services For Capella models.
 */
public class CapellaServices {

  /**
   * A specific prefix to add for message of {@link OperationCanceledException} that must be rethrown to rollback all
   * the corresponding command.
   */
  public static final String RE_THROW_OCE_PREFIX = "-RT-"; //$NON-NLS-1$

  public EObject getRootContainer(EObject eObject) {
    return EcoreUtil.getRootContainer(eObject);
  }

  /** used by aql queries */
  public List<DDiagramElementContainer> getAllContainersNew(EObject container) {
    return getAllContainers(container);
  }

  // equivalent de <%
  // (((current+current.~).ancestor[eClass.name=="DAnalysis"].nMinimize().put("aird")
  // + (get("aird")+get("aird").~+get("aird").~.~+get("aird").~.~.~).put("airds")
  // +
  // get("airds")+get("airds").referencedAnalysis+get("airds").referencedAnalysis.referencedAnalysis+get("airds").referencedAnalysis.referencedAnalysis.referencedAnalysis).nMinimize()[eClass.name=="DAnalysis"])
  // %>
  public static Collection<EObject> getAllDAnalysis(EObject eObject) {
    Collection<EObject> result = new ArrayList<>();
    EObject source = eObject;
    if (source instanceof DSemanticDecorator) {
      source = ((DSemanticDecorator) source).getTarget();
    }
    if (source instanceof DRepresentationDescriptor) {
      source = ((DRepresentationDescriptor) source).getTarget();
    }
    Session session = SessionManager.INSTANCE.getSession(source);
    if (session instanceof DAnalysisSession) {
      result.addAll(((DAnalysisSession) session).allAnalyses());
    }
    return result;
  }

  /** used by aql queries */
  public Object void2Null(EObject eObject, Object object) {
    if (object instanceof Collection && ((Collection<?>) object).isEmpty()) {
      return null;
    }
    return object;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  /** used by aql queries */
  public Object makeIntersection(EObject eObject, Object obj1, Object obj2) {
    try {
      List<Object> result = new ArrayList<>();
      if (obj1 instanceof Collection) {
        result.addAll((Collection) obj1);
      } else if (obj1 != null) {
        result.add(obj1);
      }
      if (obj2 instanceof Collection) {
        result.retainAll((Collection) obj2);
      } else if (obj2 != null && !result.contains(obj2)) {
        result.remove(obj2);
      }
      return result;
    } catch (Exception e) {
      throw new UnsupportedOperationException();
    }
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  /** used by aql queries */
  public Object makeDiff(EObject eObject, Object obj1, Object obj2) {
    try {
      List<Object> result = new ArrayList<Object>();
      if (obj1 instanceof Collection) {
        result.addAll((Collection) obj1);
      } else if (obj1 != null) {
        result.add(obj1);
      }

      if (obj2 instanceof Collection) {
        result.removeAll((Collection) obj2);
      } else if (obj2 instanceof Object[]) {
        for (Object o : (Object[]) obj2) {
          result.remove(o);
        }
      } else if (obj2 != null) {
        result.remove(obj2);
      }
      // if (result.size() == 1)
      // return result.get(0);
      return result;
    } catch (Exception e) {
      throw new UnsupportedOperationException();
    }
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  /** used by aql queries */
  public Object makeUnion(EObject eObject, Object obj1, Object obj2) {
    try {
      List<Object> result = new ArrayList<>();
      if (obj1 instanceof Collection) {
        result.addAll((Collection) obj1);
      } else if (obj1 != null) {
        result.add(obj1);
      }
      if (obj2 instanceof Collection) {
        result.addAll((Collection) obj2);
      } else if (obj2 != null) {
        result.add(obj2);
      }
      return result;
    } catch (Exception e) {
      throw new UnsupportedOperationException();
    }
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  /** used by aql queries */
  public Object makeUnion(EObject eObject, Object obj1, Object obj2, Object obj3) {
    try {
      List<Object> result = new ArrayList<>();
      if (obj1 instanceof Collection) {
        result.addAll((Collection) obj1);
      } else if (obj1 != null) {
        result.add(obj1);
      }
      if (obj2 instanceof Collection) {
        result.addAll((Collection) obj2);
      } else if (obj2 != null) {
        result.add(obj2);
      }
      if (obj3 instanceof Collection) {
        result.addAll((Collection) obj3);
      } else if (obj3 != null) {
        result.add(obj3);
      }
      return result;
    } catch (Exception e) {
      throw new UnsupportedOperationException();
    }
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  /** used by aql queries */
  public Object makeUnion(EObject eObject, Object obj1, Object obj2, Object obj3, Object obj4) {
    try {
      List<Object> result = new ArrayList<>();
      if (obj1 instanceof Collection) {
        result.addAll((Collection) obj1);
      } else if (obj1 != null) {
        result.add(obj1);
      }
      if (obj2 instanceof Collection) {
        result.addAll((Collection) obj2);
      } else if (obj2 != null) {
        result.add(obj2);
      }
      if (obj3 instanceof Collection) {
        result.addAll((Collection) obj3);
      } else if (obj3 != null) {
        result.add(obj3);
      }
      if (obj4 instanceof Collection) {
        result.addAll((Collection) obj4);
      } else if (obj4 != null) {
        result.add(obj4);
      }
      return result;
    } catch (Exception e) {
      throw new UnsupportedOperationException();
    }
  }

  public List<EObject> selectOnlyCreatedView(EObject eObject) {
    return Collections.singletonList((EObject) InterpreterUtil.getInterpreter(eObject).getVariable("view"));
  }

  public boolean isInLib(EObject context) {
    Session session = SessionManager.INSTANCE.getSession(context);
    IModel sessionModel = ILibraryManager.INSTANCE.getModel(TransactionHelper.getEditingDomain(session));
    IModel currentElementModel = ILibraryManager.INSTANCE.getModel(context);
    return sessionModel.equals(currentElementModel); // forbidden if the element's IModel is not the session's one
    // (ie
    // is a Library)
  }

  public List<EObject> ancestor(EObject object) {
    List<EObject> result = new ArrayList<>();
    if (object != null) {
      for (object = object.eContainer(); object != null; object = object.eContainer()) {
        result.add(object);
      }
    }

    return result;
  }

  /** A shared instance. */
  private static CapellaServices service;

  protected void addInAvailableFunctionList(AbstractFunction function, AbstractFunction functionToAdd,
      List<AbstractFunction> functionInDiagram) {
    if (EcoreUtil.isAncestor(functionToAdd, function) || EcoreUtil.isAncestor(function, functionToAdd)
        || functionInDiagram.contains(functionToAdd)) {
      return;
    }
    AbstractFunction toRemove = null;
    boolean toAdd = true;
    for (AbstractFunction aFunction : functionInDiagram) {
      // if a descendant of the actual function is in diagram
      // we must remove the descendant and add the actual function
      if (EcoreUtil.isAncestor(functionToAdd, aFunction)) {
        toRemove = aFunction;
        toAdd = true;
        break;
      }
      // if a parent of the actual function is in diagram
      // do nothing
      if (EcoreUtil.isAncestor(aFunction, functionToAdd)) {
        toAdd = false;
      }
    }
    if ((toRemove != null) && (functionInDiagram.contains(toRemove))) {
      functionInDiagram.remove(toRemove);
    }
    if (toAdd && !functionInDiagram.contains(toRemove)) {
      functionInDiagram.add(functionToAdd);
    }
  }

  public EObject creationService(EObject context) {
    return CapellaElementExt.creationService(context);

  }

  public EObject creationService(EObject context, String namingPrefix) {
    return CapellaElementExt.creationService(context, namingPrefix);
  }

  /**
   * @param element
   */
  public void deleteView(DDiagramElement element) {
    DeleteEObjectTask task = new DeleteEObjectTask(element,
        SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(element));

    try {
      task.execute();
    } catch (FeatureNotFoundException | MetaClassNotFoundException ex) {
      // Do nothing
    }
  }

  public void filter(Collection<? extends EObject> collection, EClass type) {
    Iterator<? extends EObject> iterContents = collection.iterator();
    while (iterContents.hasNext()) {
      final EObject next = iterContents.next();
      if (!type.isSuperTypeOf(next.eClass())) {
        iterContents.remove();
      }
    }
  }

  public EObject forceRefresh(DDiagram diagram) {
    if (null != diagram && !new DRepresentationQuery(diagram).isAutoRefresh()) {

      if (!diagram.getActivatedFilters().isEmpty()) {
        CompositeFilterApplicationBuilder builder = new CompositeFilterApplicationBuilder(diagram);
        builder.computeCompositeFilterApplications();
      }

      // Refreshes the diagram
      DialectManager.INSTANCE.refresh(diagram, new NullProgressMonitor());
    }

    return diagram;
  }

  /**
   * Retrieve name of navigation menu from the given containerView to the named diagramName
   * 
   * @param containerView
   * @param diagramName
   * @return
   */
  public String getNavigationName(DSemanticDecorator containerView, String diagramName) {
    // low-reference to private method
    // org.eclipse.sirius.diagram.tools.internal.menu.NavigateToMenuContribution.buildOpenRepresentationAction
    String result = diagramName;
    if (StringUtil.isEmpty(result)) {
      result = Messages.CapellaServices_NavigateUnnamed;
    }
    return Messages.CapellaServices_NavigateOpen + result;
  }

  /**
   * Get scope for others navigable elements
   * 
   * @param current
   * @return
   */
  public List<EObject> getNavigationScope(EObject current) {
    ArrayList<EObject> scope = new ArrayList<>();

    if (current instanceof AbstractTypedElement) {
      scope.add(((AbstractTypedElement) current).getAbstractType());
    } else if (current instanceof AbstractType) {
      scope.addAll(((AbstractType) current).getAbstractTypedElements());
    }

    return scope;
  }

  public List<Component> getAllActors(ComponentPkg actorPkg) {
    return ScenarioExt.getAllActors(actorPkg);
  }

  /**
   * TODO check duplicate used everywhere
   * 
   * @param eObject
   * @return
   */
  public List<EObject> getAllAncestor(EObject eObject) {
    EObject obj = eObject;
    List<EObject> list = new ArrayList<>();
    if (obj == null) {
      return list;
    }
    while (obj.eContainer() != null) {
      EObject container = obj.eContainer();
      list.add(container);
      obj = container;
    }
    return list;
  }

  /**
   * used
   * 
   * @param current
   * @return
   */
  public List<Component> getAllAncestors(final EObject context, Component current) {
    return getAllAncestors(current);
  }

  List<Component> getAllAncestors(Component current) {
    return ComponentExt.getAllPartitionableElementAncestors(current);
  }

  /**
   * used in context
   * 
   * @param context
   * @return
   */
  public EList<CapabilityExploitation> getAllCapabilityExploitation(final EObject context) {
    // Missions Capabilities Blank : CapExploitation4
    // Contextual Capability: CapExploitation3
    EList<CapabilityExploitation> list = new BasicEList<>();

    SystemAnalysis ca = (SystemAnalysis) getAncestor(context, CtxPackage.Literals.SYSTEM_ANALYSIS);
    // get all recursive missionpkgs : private service
    EList<MissionPkg> allMissionPkg = getAllRecursiveMissionPkg(ca);
    EList<Mission> ownedMissions = new BasicEList<>();
    for (MissionPkg missionPkg : allMissionPkg) {
      // get all missions
      ownedMissions.addAll(missionPkg.getOwnedMissions());
    }
    for (Mission mission : ownedMissions) {
      // add all capability exploitation
      list.addAll(mission.getOwnedCapabilityExploitations());
    }

    return list;
  }

  private Collection<Constraint> getAllConstraints(final EObject context) {
    Collection<Constraint> returnedClasses = new ArrayList<>();
    for (BlockArchitecture aBlockArchitecture : getAvailableArchitectures(context)) {
      TreeIterator<EObject> it = aBlockArchitecture.eAllContents();
      while (it.hasNext()) {
        EObject currentObject = it.next();
        if (currentObject instanceof Constraint) {
          returnedClasses.add((Constraint) currentObject);
        }
      }
    }
    return returnedClasses;
  }

  /**
   * Recursively get all ports on current function and its children functions.
   */
  private List<Pin> getAllContainedPins(AbstractFunction function) {
    List<Pin> result = new LinkedList<>();
    for (EObject child : FaServices.getFaServices().getAllAbstractFunctions(function)) {
      if (child instanceof AbstractFunction) {
        result.addAll(getContainedPins((AbstractFunction) child));
      }
    }
    return result;
  }

  /**
   * used in context, logical, physical
   * 
   * @param container
   * @return container + recursively all the containers of container
   */
  public List<DDiagramElementContainer> getAllContainers(EObject container) {
    List<DDiagramElementContainer> returnedList = new ArrayList<>();
    if (container instanceof DDiagram) {
      returnedList = ((DDiagram) container).getContainers();
    } else if (container instanceof DDiagramElementContainer) {
      returnedList.add((DDiagramElementContainer) container);
      for (DDiagramElementContainer aContainer : ((DDiagramElementContainer) container).getContainers()) {
        returnedList.addAll(getAllContainers(aContainer));
      }
    }
    return returnedList;
  }

  /**
   * used everywhere
   * 
   * @param current
   * @return
   */
  public List<Component> getAllDescendants(Component current) {
    List<Component> result = new ArrayList<>();
    List<Part> ownedPartitions = ComponentExt.getSubParts(current);
    List<Component> children = new ArrayList<>();

    for (Part partition : ownedPartitions) {
      if (partition.getAbstractType() instanceof Component) {
        Component pa = (Component) partition.getAbstractType();
        children.add(pa);
      }
    }
    result.addAll(children);

    for (Component partitionableElement : children) {
      result.addAll(getAllDescendants(partitionableElement));
    }

    return result;
  }

  /**
   * used in context, logical, physical
   * 
   * @param context
   * @return
   */
  public EList<ExchangeCategory> getAllExchangeCategory(final EObject context) {
    EList<ExchangeCategory> result = new BasicEList<>();
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(context);
    result.addAll(FunctionPkgExt.getAllExchangeCategories(architecture.getOwnedFunctionPkg()));
    return result;
  }

  public Collection<AbstractExchangeItem> getAllExchangeItems(final EObject context) {
    Collection<AbstractExchangeItem> returnedInformationItems = new ArrayList<>();
    List<BlockArchitecture> blocks = QueryInterpretor
        .executeQuery(QueryIdentifierConstants.GET_AVAILABLE_ARCHITECTURES_FOR_LIB, context);
    for (BlockArchitecture aBlockArchitecture : blocks) {
      TreeIterator<EObject> it = aBlockArchitecture.eAllContents();
      while (it.hasNext()) {
        EObject currentObject = it.next();
        if (currentObject instanceof AbstractExchangeItem) {
          returnedInformationItems.add((AbstractExchangeItem) currentObject);
        }
        if (!((currentObject instanceof Block) || (currentObject instanceof Structure))) {
          it.prune();
        }
      }
    }

    return returnedInformationItems;
  }

  /**
   * used partout
   * 
   * @param context
   * @return
   */
  public List<FunctionalChain> getAllFunctionalChain(final ModelElement context) {
    BlockArchitecture architecture = ComponentExt.getRootBlockArchitecture(context);
    return FunctionalChainExt.getAllFunctionalChains(architecture);
  }

  /**
   * Recursively get all graphical container nodes which are contained within a diagram or a container node.
   */
  private List<DNodeContainer> getAllGraphicalContainers(DDiagram diagram) {
    List<DNodeContainer> returnedList = new ArrayList<>();
    for (AbstractDNode aContainer : diagram.getContainers()) {
      if (aContainer instanceof DNodeContainer) {
        returnedList.add((DNodeContainer) aContainer);
      }
    }
    return returnedList;
  }

  private List<DNodeContainer> getAllGraphicalContainers(DNodeContainer container) {
    List<DNodeContainer> result = new LinkedList<>();
    result.add(container);
    for (AbstractDNode aContainer : container.getContainers()) {
      if (aContainer instanceof DNodeContainer) {
        result.add((DNodeContainer) aContainer);
      }
    }
    return result;
  }

  public List<LogicalComponent> getAllLogicalActors(LogicalComponentPkg logicalActorPkg) {
    return (List) ScenarioExt.getAllActors(logicalActorPkg);
  }

  public List<PhysicalComponent> getAllphysicalActors(PhysicalComponentPkg physicalActorPkg) {
    return (List) ScenarioExt.getAllActors(physicalActorPkg);
  }

  /**
   * used in common logical physical
   * 
   * @param context
   * @return
   */
  @Deprecated
  public EList<PortAllocation> getAllPortAllocation(final EObject context) {
    EList<PortAllocation> result = new BasicEList<>();
    Collection<Component> subLCsFromRoot = BlockArchitectureExt
        .getAllComponents(BlockArchitectureExt.getRootBlockArchitecture(context));
    for (Component partitionableElement : subLCsFromRoot) {
      EList<Feature> ownedFeatures = partitionableElement.getOwnedFeatures();
      for (Feature feature : ownedFeatures) {
        if (feature instanceof Port) {
          Port fp = (Port) feature;
          result.addAll(fp.getOwnedPortAllocations());
        }
      }
    }

    return result;
  }

  /**
   * used in context, logical, physical
   * 
   * @param context
   * @return
   */
  @Deprecated
  public EList<PortRealization> getAllPortRealisation(final EObject context) {
    // //System Architecture Blank : CA PortRealization Flow Port to
    // Standard Port + CA PortRealization FlowPort to FlowPort
    EList<PortRealization> result = new BasicEList<>();

    Collection<Component> enclosedComponents = BlockArchitectureExt
        .getAllComponents(BlockArchitectureExt.getRootBlockArchitecture(context));
    for (Component partitionableElement : enclosedComponents) {
      EList<Feature> ownedFeatures = partitionableElement.getOwnedFeatures();
      for (Feature feature : ownedFeatures) {
        if (feature instanceof Port) {
          Port fp = (Port) feature;
          result.addAll(fp.getOwnedPortRealizations());
        }
      }
    }

    return result;
  }

  /**
   * used by refreshExtension
   * 
   * @param context
   * @return
   */
  public EList<Mission> getAllPurposeMission(final Capability context) {
    // Contextual Capability : MissionNode3
    EList<Mission> list = new BasicEList<>();
    list.addAll(context.getPurposeMissions());

    /*
     * EList<CapabilityExploitation> purposes = context.getPurposes(); for (CapabilityExploitation
     * capabilityExploitation : purposes) { Mission mission = capabilityExploitation.getMission(); list.add(mission); }
     */

    SystemAnalysis ca = (SystemAnalysis) getAncestor(context, CtxPackage.Literals.SYSTEM_ANALYSIS);
    // get all missionpkgs : private service
    EList<MissionPkg> allMissionPkg = getAllRecursiveMissionPkg(ca);
    EList<Mission> ownedMissions = new BasicEList<>();
    for (MissionPkg missionPkg : allMissionPkg) {
      // get all missions
      ownedMissions.addAll(missionPkg.getOwnedMissions());
    }

    for (Mission mission : ownedMissions) {
      EList<Capability> exploitedCapabilities = mission.getExploitedCapabilities();
      if (exploitedCapabilities.contains(context)) {
        list.add(mission);
      }
    }

    return list;
  }

  private EList<MissionPkg> getAllRecursiveMissionPkg(MissionPkg missionPkg) {
    EList<MissionPkg> list = new BasicEList<>();

    EList<MissionPkg> subMissionPkgs = missionPkg.getOwnedMissionPkgs();
    list.addAll(subMissionPkgs);

    for (MissionPkg pkg : subMissionPkgs) {
      list.addAll(getAllRecursiveMissionPkg(pkg));
    }

    return list;
  }

  private EList<MissionPkg> getAllRecursiveMissionPkg(SystemAnalysis contextArchi) {
    EList<MissionPkg> list = new BasicEList<>();

    MissionPkg ownedMissionPkg = contextArchi.getOwnedMissionPkg();
    // add the root missionpkg
    list.add(ownedMissionPkg);
    // add all the owned missionpkgs
    list.addAll(getAllRecursiveMissionPkg(ownedMissionPkg));

    return list;
  }

  /**
   * used in context.odesign
   * 
   * @param context
   * @param target
   * @return
   */
  public List<AbstractCapability> getAllSuperCapabilities(final EObject context, AbstractCapability target) {
    List<AbstractCapability> result = new LinkedList<>();
    result.add(target);
    if (target.getSuper() == null) {
      return result;
    }
    for (AbstractCapability current : target.getSuper()) {
      result.addAll(getAllSuperCapabilities(context, current));
    }
    return result;
  }

  /**
   * Finds an unique name for the given capella element. Suffixes the <code>prefix</code> string with a number to get an
   * unique name for the namespace of <code>namedElement</code>.
   * 
   * @param namedElement
   *          the named element.
   * @param prefix
   *          the prefix of the name.
   * @return an unique name for the given capella element. Suffixes the <code>prefix</code> string with a number to get
   *         an unique name for the namespace of <code>namedElement</code>.
   */
  public String getAllUniqueName(AbstractNamedElement namedElement, String prefix) {
    return EcoreUtil2.getUniqueName(namedElement, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, prefix,
        false, true);
  }

  /**
   * Gets the first ancestor of the given type.
   * 
   * @param context
   *          the context.
   * @param type
   *          the type.
   * @return the first ancestor of the given type.
   */
  public EObject getAncestor(final EObject context, EClass eclass) {
    EObject current = context.eContainer();
    while ((current != null) && !eclass.isInstance(current)) {
      current = current.eContainer();
    }
    return current;
  }

  public List<BlockArchitecture> getAvailableArchitectures(final EObject context) {
    return QueryInterpretor.executeQuery(
        org.polarsys.capella.core.sirius.analysis.queries.QueryIdentifierConstants.GET_AVAILABLE_ARCHITECTURES,
        context);
  }

  public Collection<EObject> getRelatedAssociations(Classifier clazz) {
    Collection<EObject> result = new ArrayList<>();
    // retrieve all the association of the target
    for (TypedElement typedElement : clazz.getTypedElements()) {

      if ((typedElement instanceof Property)
          && (((Property) typedElement).getAggregationKind() != AggregationKind.UNSET)) {
        Property property = (Property) typedElement;

        SemanticEditingDomain semEditDomain = (SemanticEditingDomain) TransactionHelper.getEditingDomain(property);
        if (semEditDomain != null) {
          ECrossReferenceAdapter crossReferencer = semEditDomain.getCrossReferencer();
          if (crossReferencer != null) {
            for (Setting setting : crossReferencer.getInverseReferences(property)) {
              EObject eObject = setting.getEObject();
              if (eObject instanceof Association) {
                result.add(eObject);
              }
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * used in common.odesign
   * 
   * @param context
   *          : '$containerView' - veiw of Element in Diagram
   * @return return the all the association not in Diagram of the target value of '$containerVeiw'.
   */
  public List<EObject> getAvailableAssociationToInsert(EObject context) {
    // collect all association of the context target not in Diagram
    List<EObject> result = new ArrayList<>();
    // filter 'context' as 'DDiagramElementContainer'
    if (context instanceof AbstractDNode) {
      AbstractDNode currentContainer = (AbstractDNode) context;
      // retrieve Diagram
      DDiagram currentDiagram = getDiagramContainer(currentContainer);
      // collect all the elements from the Diagram
      List<EObject> elementsIndiagram = new ArrayList<>();
      for (AbstractDNode aContainer : currentDiagram.getContainers()) {
        elementsIndiagram.add(aContainer.getTarget());
      }
      // filter 'context' target element as 'Classifier'
      if (currentContainer.getTarget() instanceof Classifier) {
        Classifier target = (Classifier) currentContainer.getTarget();
        result.addAll(getRelatedAssociations(target));
      }

      // filter existing Association from the result list.
      for (DEdge anEdge : currentDiagram.getEdges()) {
        if (result.contains(anEdge.getTarget())) {
          result.remove(anEdge.getTarget());
        }
      }
    }

    return result;
  }

  /**
   * Returns the semantic context for the given context. If the given context is a DSemanticDecorator, retrieve the
   * target of this DSemanticDecorator. (it is used if sirius change its default variables from semantic to view)
   */
  public EObject getSemanticContext(EObject context) {
    EObject semanticContext = context;
    if (semanticContext instanceof DSemanticDecorator) {
      semanticContext = ((DSemanticDecorator) semanticContext).getTarget();
    }
    return semanticContext;
  }

  public Collection<Constraint> getAvailableConstraintToInsert(final EObject context, EObject containerView) {
    EObject semanticContext = getSemanticContext(context);
    if (containerView instanceof DDiagram) {
      return getAllConstraints(semanticContext);
    }

    if (semanticContext instanceof Structure) {
      List<Constraint> list = new ArrayList<>();
      EList<AbstractConstraint> constraints = ((Structure) semanticContext).getOwnedConstraints();
      for (AbstractConstraint abstractConstraint : constraints) {
        if (abstractConstraint instanceof Constraint) {
          list.add((Constraint) abstractConstraint);
        }
      }
      return list;
    }
    return new ArrayList<>();
  }

  public List<AbstractFunction> getCDFSemanticAbstractFunctions(AbstractFunction function) {
    // previously named getAvailableContextualFunctions
    List<AbstractFunction> returnedFunctions = new ArrayList<>();

    ActivityNode node = function;

    if (node.getOutgoing() != null) {
      for (ActivityEdge anActivityEdge : node.getOutgoing()) {
        if (anActivityEdge instanceof FunctionalExchange) {
          AbstractFunction linkedFunction = FunctionExt
              .getOutGoingAbstractFunction((FunctionalExchange) anActivityEdge);
          addInAvailableFunctionList(function, linkedFunction, returnedFunctions);
        }
      }
    }
    if (node.getIncoming() != null) {
      for (ActivityEdge anActivityEdge : node.getIncoming()) {
        if (anActivityEdge instanceof FunctionalExchange) {
          AbstractFunction linkedFunction = FunctionExt
              .getIncomingAbstractFunction((FunctionalExchange) anActivityEdge);
          addInAvailableFunctionList(function, linkedFunction, returnedFunctions);
        }
      }
    }

    for (Pin aPin : getAllContainedPins(function)) {
      if (aPin.getIncoming() != null) {
        for (ActivityEdge anActivityEdge : aPin.getIncoming()) {
          if (anActivityEdge instanceof FunctionalExchange) {
            AbstractFunction linkedFunction = FunctionExt
                .getIncomingAbstractFunction((FunctionalExchange) anActivityEdge);
            addInAvailableFunctionList(function, linkedFunction, returnedFunctions);
          }
        }
      }
      if (aPin.getOutgoing() != null) {
        for (ActivityEdge anActivityEdge : aPin.getOutgoing()) {
          if (anActivityEdge instanceof FunctionalExchange) {
            AbstractFunction linkedFunction = FunctionExt
                .getOutGoingAbstractFunction((FunctionalExchange) anActivityEdge);
            addInAvailableFunctionList(function, linkedFunction, returnedFunctions);
          }
        }
      }
    }
    returnedFunctions.add(function);
    return returnedFunctions;
  }

  /**
   * used in common.odesign
   * 
   * @param context
   *          : '$containerView' - view of Element in Diagram
   * @return return the all the incoming and outgoing Generalization not in Diagram of the target value of
   *         '$containerVeiw'.
   */
  public List<EObject> getAvailableExchangeItemElementToInsert(EObject context) {
    // collect all super and sub Generalization of the context target not in Diagram
    List<EObject> result = new ArrayList<>();
    // filter AbstractDNode
    if (context instanceof AbstractDNode) {
      AbstractDNode currentContainer = (AbstractDNode) context;
      // retrieve Diagram
      DDiagram currentDiagram = getDiagramContainer(currentContainer);
      // collect all the containers from the Diagram
      List<EObject> elementsIndiagram = new ArrayList<>();
      for (AbstractDNode aContainer : currentDiagram.getContainers()) {
        elementsIndiagram.add(aContainer.getTarget());
      }
      // collect all the nodes from the Diagram
      for (DNode aContainer : currentDiagram.getNodes()) {
        elementsIndiagram.add(aContainer.getTarget());
      }

      // filter ExchangeItem elements
      //
      if (currentContainer.getTarget() instanceof ExchangeItem) {
        ExchangeItem target = (ExchangeItem) currentContainer.getTarget();
        // retrieve all exchange item elements
        result.addAll(target.getOwnedElements());
      }

      // filter existing Generalization from the result list.
      for (DEdge anEdge : currentDiagram.getEdges()) {
        if (result.contains(anEdge.getTarget())) {
          result.remove(anEdge.getTarget());
        }
      }
    }

    return result;
  }

  public List<EObject> getAvailableFunctionalAllocation(CapellaElement capellaElement) {
    List<EObject> returnedList = new ArrayList<>();
    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(capellaElement.eClass(),
        FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS);
    if (query != null) {
      returnedList.addAll(query.getAvailableElements(capellaElement));
    }
    return returnedList;
  }

  List<AbstractFunction> getAvailableFunctionsInDataFlowBlank(AbstractFunction context) {
    FunctionPkg pkgOwner = (FunctionPkg) getAncestor(context, FaPackage.Literals.FUNCTION_PKG);
    return FunctionPkgExt.getAllAbstractFunctions(pkgOwner);
  }

  /**
   * used in context, oa, logical, physical
   */
  public List<AbstractFunction> getAvailableFunctionsToInsert(EObject current) {
    List<AbstractFunction> returnedFunctions = new ArrayList<>();
    List<DNodeContainer> allGraphicalContainers = null;
    List<DNode> ownedNodes = new ArrayList<>();
    AbstractFunction rootFunction = null;

    if (current instanceof DSemanticDecorator) {
      EObject target = ((DSemanticDiagram) current).getTarget();

      if (current instanceof DSemanticDiagram) {
        rootFunction = BlockArchitectureExt.getRootFunction(BlockArchitectureExt.getRootBlockArchitecture(target));
        allGraphicalContainers = getAllGraphicalContainers((DDiagram) current);
      }
      if (current instanceof DNodeContainer) {
        rootFunction = (AbstractFunction) ((DNodeContainer) current).getTarget();
        allGraphicalContainers = getAllGraphicalContainers((DNodeContainer) current);
        ownedNodes.addAll(((DNodeContainer) current).getNodes());
        ownedNodes.removeAll(((DNodeContainer) current).getOwnedBorderedNodes());
      }

    }
    returnedFunctions.addAll(getCache(FunctionExt::getAllAbstractFunctions, rootFunction));
    returnedFunctions.removeAll(ownedNodes);

    if (allGraphicalContainers != null) {
      for (DDiagramElementContainer aNodeContainer : allGraphicalContainers) {
        List<AbstractFunction> toBeRemoved = new ArrayList<>();
        if (aNodeContainer.getTarget() instanceof AbstractFunction) {
          returnedFunctions.remove(aNodeContainer.getTarget());
          for (AbstractFunction anAbstractFunction : returnedFunctions) {
            if (EcoreUtil.isAncestor(anAbstractFunction, aNodeContainer.getTarget())) {
              toBeRemoved.add(anAbstractFunction);
            }
          }
        }
        List<DNode> ownedBorderedNodes = aNodeContainer.getOwnedBorderedNodes();
        for (DNode aNode : aNodeContainer.getNodes()) {
          if ((aNode.getTarget() instanceof AbstractFunction) && !ownedBorderedNodes.contains(aNode)) {
            returnedFunctions.remove(aNode.getTarget());
            for (AbstractFunction anAbstractFunction : returnedFunctions) {
              if (EcoreUtil.isAncestor(anAbstractFunction, aNode.getTarget())) {
                toBeRemoved.add(anAbstractFunction);
              }
            }
          }
        }
        returnedFunctions.removeAll(toBeRemoved);
        toBeRemoved = null;
      }
    }
    return returnedFunctions;
  }

  /**
   * used in common.odesign
   * 
   * @param context
   *          : '$containerView' - veiw of Element in Diagram
   * @return return the all the incoming and outgoing Generalization not in Diagram of the target value of
   *         '$containerVeiw'.
   */
  public List<EObject> getAvailableGeneralizationToInsert(EObject context) {
    // collect all super and sub Generalization of the context target not in Diagram
    List<EObject> result = new ArrayList<>();
    // filter AbstractDNode
    if (context instanceof AbstractDNode) {
      AbstractDNode currentContainer = (AbstractDNode) context;
      // retrieve Diagram
      DDiagram currentDiagram = getDiagramContainer(currentContainer);
      // collect all the containers from the Diagram
      List<EObject> elementsIndiagram = new ArrayList<>();
      for (AbstractDNode aContainer : currentDiagram.getContainers()) {
        elementsIndiagram.add(aContainer.getTarget());
      }
      // collect all the nodes from the Diagram
      for (DNode aContainer : currentDiagram.getNodes()) {
        elementsIndiagram.add(aContainer.getTarget());
      }

      // filter GeneralizableElement elements
      //
      if (currentContainer.getTarget() instanceof GeneralizableElement) {
        GeneralizableElement target = (GeneralizableElement) currentContainer.getTarget();
        // Retrieve all the super generalization of the target
        for (Generalization aGeneralization : target.getSuperGeneralizations()) {
          result.add(aGeneralization);
        }
        // Retrieve all the sub generalization of the target
        for (Generalization aGeneralization : target.getSubGeneralizations()) {
          result.add(aGeneralization);
        }
      }

      // filter existing Generalization from the result list.
      for (DEdge anEdge : currentDiagram.getEdges()) {
        if (result.contains(anEdge.getTarget())) {
          result.remove(anEdge.getTarget());
        }
      }
    }

    return result;
  }

  public List<AbstractFunction> getAvailableOperationalActivities(OperationalActivity context) {
    return getAvailableFunctionsInDataFlowBlank(context);
  }

  /*
   * used in context, logical, physical
   */
  public List<ActivityNode> getAvailablePins(AbstractFunction context, DDiagram viewPoint,
      AbstractDNode containerView) {
    List<ActivityNode> returnedList = new ArrayList<>();
    returnedList.addAll(getAllContainedPins(context));

    // Remove all pins already displayed in inner function
    if (containerView instanceof DNodeContainer) {
      for (DNodeContainer aContainer : FaServices.getFaServices()
          .getOwnedVisibleFunctionContainersInDataFlowBlank((DNodeContainer) containerView, viewPoint)) {
        Iterator<ActivityNode> returnedIterator = returnedList.iterator();
        if (aContainer.getTarget() != null) {
          while (returnedIterator.hasNext()) {
            ActivityNode node = returnedIterator.next();
            if (EcoreUtil.isAncestor(aContainer.getTarget(), node)) {
              returnedIterator.remove();
            }
          }
        }
      }
    }

    // remove pins with internal exchanges
    Iterator<ActivityNode> returnedIterator = returnedList.iterator();
    while (returnedIterator.hasNext()) {
      ActivityNode pin = returnedIterator.next();
      boolean toRemove = true;

      if (pin instanceof FunctionInputPort) {
        EList<FunctionalExchange> incoming = ((FunctionInputPort) pin).getIncomingFunctionalExchanges();
        if (incoming != null) {
          if (incoming.isEmpty()) {
            toRemove = false;
          }
          for (ActivityEdge anActivityEdge : incoming) {
            if ((anActivityEdge instanceof FunctionalExchange)
                && (anActivityEdge.getSource() instanceof FunctionOutputPort)
                && (!EcoreUtil.isAncestor(context, anActivityEdge.getSource().eContainer()))) {
              // remove internal exchanges
              toRemove = false;
              break;
            }
          }
        }

      } else if (pin instanceof FunctionOutputPort) {
        EList<FunctionalExchange> outgoing = ((FunctionOutputPort) pin).getOutgoingFunctionalExchanges();
        if (outgoing != null) {
          if (outgoing.isEmpty()) {
            toRemove = false;
          }
          for (ActivityEdge anActivityEdge : outgoing) {
            if ((anActivityEdge instanceof FunctionalExchange)
                && (anActivityEdge.getTarget() instanceof FunctionInputPort)
                && (!EcoreUtil.isAncestor(context, anActivityEdge.getTarget().eContainer()))) {
              // remove internal exchanges
              toRemove = false;
              break;
            }
          }
        }
      }

      if (toRemove) {
        returnedIterator.remove();
      }
    }

    return returnedList;
  }

  /**
   * Check if given elements are contained in containers which are in the same containment tree (the method is used to
   * know if an edge is internal).
   * 
   * @param sourcePort
   * @param targetPort
   * @return
   */
  public boolean areInternalEdgePorts(DSemanticDecorator sourcePort, DSemanticDecorator targetPort) {
    return (EcoreUtil.isAncestor(sourcePort.eContainer(), targetPort.eContainer()))
        || (EcoreUtil.isAncestor(targetPort.eContainer(), sourcePort.eContainer()));
  }

  /**
   * used in oa.odesign
   * 
   * @param eObject
   * @param first
   * @param second
   * @return
   */
  public EObject getBestCandidate(EObject eObject, List<?> first, List<?> second) {
    List<Object> first2 = new LinkedList<>(first);
    first2.retainAll(second);
    if (first2.isEmpty() && (eObject instanceof DSemanticDecorator)) {
      return ((DSemanticDecorator) eObject).getTarget();
    }
    return first2.isEmpty() ? eObject : (EObject) first2.get(0);
  }

  /**
   * Returns the best container for given eObject which semantic element is of the given eclass type. used in
   * common.odesign
   */
  public EObject getBestGraphicalContainer(EObject eObject, DDiagram diagram, EClass eclass) {
    Hashtable<EObject, DDiagramElement> elementsInDiagram = new Hashtable<>(); // all displayed
    // elements in
    // the diagram

    // get all displayed functions in the diagram
    for (DDiagramElement aContainer : diagram.getContainers()) {
      if ((aContainer != null) && (aContainer.getTarget() != null) && (aContainer.isVisible())) {
        elementsInDiagram.put(aContainer.getTarget(), aContainer);
      }
    }

    DDiagramElement container;
    EObject anObject = eObject;
    while (anObject != null) {
      anObject = getAncestor(anObject, eclass);
      if ((anObject != null) && ((container = elementsInDiagram.get(anObject)) != null)) {
        return container;
      }
    }
    return diagram;
  }

  /**
   * Returns the first ancestor of <code>e1</code> AND <code>e2</code>.
   * 
   * @param e1
   *          the first object.
   * @param e2
   *          the second object.
   * @return the first ancestor of <code>e1</code> AND <code>e2</code>.
   */
  public EObject getCommonAncestor(EObject e1, EObject e2) {
    EObject contextContainer = e1.eContainer();
    if (EcoreUtil.isAncestor(e1, e2)) {
      return e1;
    } else if (EcoreUtil.isAncestor(e2, e1)) {
      return e2;
    } else {
      while (!EcoreUtil.isAncestor(contextContainer, e2) && (contextContainer != null)) {
        contextContainer = contextContainer.eContainer();
      }
    }
    return contextContainer;
  }

  public AbstractFunction getCommonFunctionAncestor(EObject e1, EObject e2) {
    EObject contextContainer = getCommonAncestor(e1, e2);
    if (contextContainer == null) {
      contextContainer = e1;
    }
    return FaServices.getFaServices().getFunctionContainer(contextContainer);
  }

  public Component getCommonComponentAncestor(EObject e1, Component c1, Component c2) {
    return ComponentExt.getCommonComponentAncestor(c1, c2);
  }

  /**
   * Used to find the container of a newly created Logical/Physical component in a scenario
   * 
   * @param object
   *          the context scenario
   * @return the parent logical component
   */
  public Component getComponentContainer(EObject object) {
    // Go back in "eContainer" until find a logical component or architecture.
    // If an architecture, search the root logical component architecture,
    // otherwise, it is the current logical component.
    EObject container = object;

    while (container != null && !(container instanceof Component) && !(container instanceof ModellingArchitecture)) {
      container = container.eContainer();
    }
    if (container instanceof Component) {
      return (Component) container;
    } else if (container instanceof SystemAnalysis || container instanceof LogicalArchitecture
        || container instanceof PhysicalArchitecture || container instanceof EPBSArchitecture) {
      return ((BlockArchitecture) container).getSystem();
    }
    return null;
  }

  private List<Pin> getContainedPins(AbstractFunction function) {
    List<Pin> result = new LinkedList<>();
    result.addAll(function.getInputs());
    result.addAll(function.getOutputs());
    return result;
  }

  public EList<EObject> getDepleyedElements(final PhysicalComponent context, final DDiagram viewPoint,
      final DNodeContainer containerView) {
    EList<Layer> activatedLayers = viewPoint.getActivatedLayers();
    boolean hasActivatedLayer = false;
    for (Layer layer : activatedLayers) {
      if (layer.getName().equalsIgnoreCase("Sub Components")) { //$NON-NLS-1$
        hasActivatedLayer = true;
      }
    }

    EList<EObject> deployedElements = new BasicEList<>();
    EList<EObject> containerViewdeployedElements = new BasicEList<>();
    if (!hasActivatedLayer) {
      // retrieve all the DeployedElement in depth
      getInDepthDeployedElements(context, deployedElements);
    } else {
      // retrieve all the DeployedElement in depth
      getInDepthDeployedElements(context, deployedElements);

      if (null != containerView) {
        EList<DDiagramElement> ownedDiagramElements = containerView.getOwnedDiagramElements();
        for (DDiagramElement diagramElement : ownedDiagramElements) {
          if (diagramElement instanceof AbstractDNode) {
            AbstractDNode abstractNode = (AbstractDNode) diagramElement;
            EObject target = abstractNode.getTarget();
            if (target instanceof PhysicalComponent) {
              // retrieve all the DeployedElement in depth
              getInDepthDeployedElements((PhysicalComponent) target, containerViewdeployedElements);
            }
          }
        }
      }
    }

    deployedElements.removeAll(containerViewdeployedElements);

    return deployedElements;
  }

  /**
   * used everywhere
   * 
   * @param current
   *          a diagram element
   * @return current if it is a diagram or the diagram that contains current if it is a DDiagramElement.
   * 
   *         May return null: If used in a style computation or a decoration, the expression will be called twice, the
   *         first time, the view doesn't have yet a container nor a semantic element.
   */
  public DDiagram getDiagramContainer(EObject current) {
    DDiagram parent = DiagramHelper.getService().getDiagramContainer(current);

    if (parent == null) {
      EObject target = current;
      if ((current instanceof DSemanticDecorator) && (((DSemanticDecorator) current).getTarget() != null)) {
        target = ((DSemanticDecorator) current).getTarget();
        Object oDiagram = CsServices.getService().getInterpreterVariable(target, IInterpreterSiriusVariables.DIAGRAM);
        if (oDiagram instanceof DDiagram) {
          return (DDiagram) oDiagram;
        }
      }

    }

    return parent;
  }
  
  public boolean isOrIsChildFunction(AbstractFunction child, EObject parent) {
    EObject childContainer = child;
    while (childContainer instanceof AbstractFunction) {
      if (childContainer.equals(parent)) {
        return true;
      }
      childContainer = childContainer.eContainer();
    }
    return false;
  }
  
  public Set<EObject> getEdgeExchangeCategorySemanticElements(ExchangeCategory context, DEdge view) {
    Set<EObject> returnedList = new LinkedHashSet<>();
    returnedList.add(context);
    EObject targetViewFunction = ((DSemanticDecorator) view.getTargetNode().eContainer()).getTarget();
    EObject sourceViewFunction = ((DSemanticDecorator) view.getSourceNode().eContainer()).getTarget();
    
    for (FunctionalExchange anExchange : context.getExchanges()) {
      AbstractFunction sourceContainer = FunctionalExchangeExt.getSourceFunction(anExchange);
      AbstractFunction targetContainer = FunctionalExchangeExt.getTargetFunction(anExchange);
      
      if (isOrIsChildFunction(sourceContainer, sourceViewFunction) && 
          isOrIsChildFunction(targetContainer, targetViewFunction)) {
        returnedList.add(anExchange);
      }
    }
    return returnedList;
  }

  public Set<EObject> getEdgeExchangeCategorySemanticElements(ComponentExchangeCategory context, DEdge view) {
    Set<EObject> returnedList = new LinkedHashSet<>();
    returnedList.add(context);
    EObject sourceViewComponent = CsServices.getService().getComponentType((DSemanticDecorator) view.getSourceNode().eContainer());
    EObject targetViewComponent = CsServices.getService().getComponentType((DSemanticDecorator) view.getTargetNode().eContainer());
    
    for (ComponentExchange exchange : context.getExchanges()) {
      EObject sourceComponent = ComponentExchangeExt.getSourceComponent(exchange);
      EObject targetComponent = ComponentExchangeExt.getTargetComponent(exchange);
      
      if (sourceComponent.equals(sourceViewComponent) && targetComponent.equals(targetViewComponent)) {
        returnedList.add(exchange);
        
      } else if (targetComponent.equals(sourceViewComponent) && sourceComponent.equals(targetViewComponent)) {
        // A Component Category is merging both ways of links
        returnedList.add(exchange);
      }
    }
    return returnedList;
  }

  public Set<EObject> getEdgeExchangeCategorySemanticElements(PhysicalLinkCategory context, DEdge view) {
    Set<EObject> returnedList = new LinkedHashSet<>();
    returnedList.add(context);
    EObject sourceViewComponent = CsServices.getService().getComponentType((DSemanticDecorator) view.getSourceNode().eContainer());
    EObject targetViewComponent = CsServices.getService().getComponentType((DSemanticDecorator) view.getTargetNode().eContainer());
    
    for (PhysicalLink link : context.getLinks()) {
      EObject sourceComponent = PhysicalLinkExt.getSourceComponent(link);
      EObject targetComponent = PhysicalLinkExt.getTargetComponent(link);
      
      if (sourceComponent.equals(sourceViewComponent) && targetComponent.equals(targetViewComponent)) {
        returnedList.add(link);
        
      } else if (targetComponent.equals(sourceViewComponent) && sourceComponent.equals(targetViewComponent)) {
        // A Physical Category is merging both ways of links
        returnedList.add(link);
      }
    }
    return returnedList;
  }

  public List<EObject> getEmptyList(EObject context) {
    return new ArrayList<>();
  }

  public String getEObjectLabelProviderHelper(EObject context) {
    return EObjectExt.getText(context);
  }

  /**
   * used in common.odesign
   * 
   * @param context
   *          : '$containerView' - veiw of Element in Diagram
   * @return return the all the association existing in Diagram of the target value of '$containerVeiw'.
   */
  public List<EObject> getExistingAssociationFromDiagram(EObject context) {
    // collect all association of the context target existing in Diagram
    List<EObject> result = new ArrayList<>();
    // filter 'context' as 'DDiagramElementContainer'
    if (context instanceof AbstractDNode) {
      AbstractDNode currentContainer = (AbstractDNode) context;
      // retrieve Diagram
      DDiagram currentDiagram = getDiagramContainer(currentContainer);
      // add existing association in Diagram to the result list.
      for (DEdge anEdge : currentDiagram.getEdges()) {
        if ((anEdge.getTarget() instanceof Association)
            && (anEdge.getSourceNode().equals(context) || anEdge.getTargetNode().equals(context))) {
          result.add(anEdge.getTarget());
        }
      }
    }
    return result;
  }

  /**
   * used everywhere
   * 
   * @param context
   *          : '$container' - view of Element in Diagram
   * @return return the all the association existing in Diagram of the target value of '$container'.
   */
  public List<EObject> getExistingConstraintsFromDiagram(EObject context, DDiagram diagram) {
    // collect all association of the context target existing in Diagram
    List<EObject> result = new ArrayList<>();
    if ((null == diagram) || (null == context)) {
      return result;
    }
    // add existing association in Diagram to the result list.
    for (DNode aNode : DiagramServices.getDiagramServices().getAllNodes(diagram)) {
      EObject target = aNode.getTarget();
      if ((target instanceof Constraint)) {
        if (context instanceof DDiagramElement) {
          EObject target2 = ((DSemanticDecorator) context).getTarget();
          if (null != target2) {
            if (((Constraint) target).getConstrainedElements().contains(target2)) {
              result.add(target);
            }
          }
        } else if (context instanceof DDiagram) {
          result.add(target);
        }
      }
    }
    return result;
  }

  /**
   * Returns the existing cross referencer adapter or <code>null</code> if no adapter is installed.
   * 
   * @param notifier
   *          a notifier.
   * @return the existing cross referencer adapter or <code>null</code> if no adapter is installed.
   */
  public ECrossReferenceAdapter getExistingCrossReferenceAdapter(final Notifier notifier) {
    return ECrossReferenceAdapter.getCrossReferenceAdapter(notifier);
  }

  /**
   * used in common.odesign
   * 
   * @param context
   *          : '$containerView' - view of Element in Diagram
   * @return return the all the incoming and outgoing Generalization existing in Diagram of the target value of
   *         '$containerVeiw'.
   */
  public List<EObject> getExistingExchangeItemElementFromDiagram(EObject context) {
    // collect all super and sub Generalization of the context target existing in
    // Diagram
    List<EObject> result = new ArrayList<>();
    // filter 'context' as 'DDiagramElementContainer'
    if (context instanceof AbstractDNode) {
      AbstractDNode currentContainer = (AbstractDNode) context;
      // retrieve Diagram
      DDiagram currentDiagram = getDiagramContainer(currentContainer);
      // add existing Generalization in Diagram to the result list.
      for (DEdge anEdge : currentDiagram.getEdges()) {
        if ((anEdge.getTarget() instanceof ExchangeItemElement)
            && (anEdge.getSourceNode().equals(context) || anEdge.getTargetNode().equals(context))) {
          result.add(anEdge.getTarget());
        }
      }
    }
    return result;
  }

  /**
   * used in common.odesign
   * 
   * @param context
   *          : '$containerView' - view of Element in Diagram
   * @return return the all the incoming and outgoing Generalization existing in Diagram of the target value of
   *         '$containerVeiw'.
   */
  public List<EObject> getExistingGeneralizationFromDiagram(EObject context) {
    // collect all super and sub Generalization of the context target existing in
    // Diagram
    List<EObject> result = new ArrayList<>();
    // filter 'context' as 'DDiagramElementContainer'
    if (context instanceof AbstractDNode) {
      AbstractDNode currentContainer = (AbstractDNode) context;
      // retrieve Diagram
      DDiagram currentDiagram = getDiagramContainer(currentContainer);
      // add existing Generalization in Diagram to the result list.
      for (DEdge anEdge : currentDiagram.getEdges()) {
        if ((anEdge.getTarget() instanceof Generalization)
            && (anEdge.getSourceNode().equals(context) || anEdge.getTargetNode().equals(context))) {
          result.add(anEdge.getTarget());
        }
      }
    }
    return result;
  }

  public Collection<FunctionalChain> getFunctionalChain(final AbstractFunction context) {
    Collection<FunctionalChain> result = new ArrayList<>();
    result.addAll(context.getOwnedFunctionalChains());
    TreeIterator<EObject> allContents = context.eAllContents();
    while (allContents.hasNext()) {
      EObject next = allContents.next();
      if (next instanceof AbstractFunction) {
        AbstractFunction function = (AbstractFunction) next;
        result.addAll(function.getOwnedFunctionalChains());
      }
    }
    return result;
  }

  public List<DEdge> getIncomingEdges(EdgeTarget containerView) {
    List<DEdge> returnedList = new ArrayList<>();
    DDiagram currentDiagram = getDiagramContainer(containerView);
    List<DEdge> allEgdesInDiagram = currentDiagram.getEdges();
    for (DEdge anEdge : containerView.getIncomingEdges()) {
      if (allEgdesInDiagram.contains(anEdge)) {
        returnedList.add(anEdge);
      }
    }
    return returnedList;
  }

  private void getInDepthDeployedElements(final PhysicalComponent context, EList<EObject> deployedElements) {
    EList<AbstractDeploymentLink> ownedDeployments = context.getOwnedDeploymentLinks();
    for (AbstractDeploymentLink abstractDeployment : ownedDeployments) {
      DeployableElement deployedElement = abstractDeployment.getDeployedElement();
      if (null != deployedElement) {
        deployedElements.add(deployedElement);
      }
    }
    List<Component> allDescendants = getAllDescendants(context);
    for (Component partitionableElement : allDescendants) {
      if (partitionableElement instanceof PhysicalComponent) {
        for (AbstractDeploymentLink abstractDeployment : ((PhysicalComponent) partitionableElement)
            .getOwnedDeploymentLinks()) {
          DeployableElement deployedElement = abstractDeployment.getDeployedElement();
          if (null != deployedElement) {
            deployedElements.add(deployedElement);
          }

        }
      }
    }
  }

  private void getInDepthTargetElements(final PhysicalComponent context, EList<EObject> functionalAllocations) {
    EList<ComponentFunctionalAllocation> functionAllocations = context.getOwnedFunctionalAllocation();
    for (ComponentFunctionalAllocation functionAllocation : functionAllocations) {
      TraceableElement traceableElement = functionAllocation.getTargetElement();
      if (null != traceableElement) {
        functionalAllocations.add(traceableElement);
      }
    }
    List<Component> allDescendants = getAllDescendants(context);
    for (Component partitionableElement : allDescendants) {
      if (partitionableElement instanceof PhysicalComponent) {
        for (ComponentFunctionalAllocation abstractDeployment : ((PhysicalComponent) partitionableElement)
            .getOwnedFunctionalAllocation()) {
          TraceableElement traceableElement = abstractDeployment.getTargetElement();
          if (null != traceableElement) {
            functionalAllocations.add(traceableElement);
          }
        }
      }
    }
  }

  /**
   * used in context, logical, physical
   * 
   * @param context
   * @param view
   * @return
   */
  public List<EObject> getInputPinCategorySemanticElements(EObject context, DSemanticDecorator view) {
    List<EObject> returnedList = new ArrayList<>();
    if (context instanceof ExchangeCategory) {
      returnedList.add(context);
      ExchangeCategory currentCategory = (ExchangeCategory) context;
      EObject container = ((DSemanticDecorator) view.eContainer()).getTarget();
      for (FunctionalExchange anExchange : currentCategory.getExchanges()) {
        if (isOrIsChildFunction(FunctionalExchangeExt.getTargetFunction(anExchange), container) && !returnedList.contains(anExchange)) {
          returnedList.add(anExchange);
        }
      }
    }
    return returnedList;
  }

  /**
   * used in common and physical
   * 
   * @param eObject
   * @param first
   * @param second
   * @return
   */
  public Collection<?> getIntersection(EObject eObject, Collection<?> first, Collection<?> second) {
    if (first == null) { // for acceleo2aql
      first = Collections.emptyList();
    }

    List<Object> firstCopy = new LinkedList<>(first);
    firstCopy.retainAll(second);
    return firstCopy;
  }

  /**
   * Returns the objects that reference the given object by the given reference.
   * 
   * @param object
   *          the referenced object.
   * @param reference
   *          the reference.
   * @return the objects that reference the given object by the given reference.
   */
  public Collection<EObject> getInverseReferences(EObject object, EReference reference) {
    final ECrossReferenceAdapter crossReferenceAdapter = getExistingCrossReferenceAdapter(object);
    if (crossReferenceAdapter != null) {
      Collection<EStructuralFeature.Setting> allInverseReferences = crossReferenceAdapter.getInverseReferences(object,
          true);
      Collection<EObject> inverseReferences = new HashSet<>();
      for (EStructuralFeature.Setting setting : allInverseReferences) {
        if (setting.getEStructuralFeature() == reference) {
          inverseReferences.add(setting.getEObject());
        }
      }
      return inverseReferences;
    }
    return Collections.<EObject> emptyList();

  }

  static LinkedList<AbstractFunction> getLeaves(AbstractFunction function) {
    LinkedList<AbstractFunction> subFunctions = new LinkedList<>();
    for (AbstractFunction func : function.getSubFunctions()) {
      if (AbstractFunctionExt.isLeaf(func)) {
        subFunctions.add(func);
      } else {
        subFunctions.addAll(getLeaves(func));
      }
    }
    return subFunctions;
  }

  public List<Component> getLogicalParent(EObject context, Component aComponent) {
    return ComponentExt.getDirectParents(aComponent);
  }

  public List<Component> getLogicalParent(Component aComponent) {
    return ComponentExt.getDirectParents(aComponent);
  }

  public List<DEdge> getOutgoingEdges(EdgeTarget containerView) {
    List<DEdge> returnedList = new ArrayList<>();
    DDiagram currentDiagram = getDiagramContainer(containerView);
    List<DEdge> allEgdesInDiagram = currentDiagram.getEdges();
    for (DEdge anEdge : containerView.getOutgoingEdges()) {
      if (allEgdesInDiagram.contains(anEdge)) {
        returnedList.add(anEdge);
      }
    }
    return returnedList;
  }

  /**
   * used in context, logical, physical
   * 
   * @param context
   * @param view
   * @return
   */
  public List<EObject> getOutputPinCategorySemanticElements(EObject context, DSemanticDecorator view) {
    List<EObject> returnedList = new ArrayList<>();
    if (context instanceof ExchangeCategory) {
      returnedList.add(context);
      ExchangeCategory currentCategory = (ExchangeCategory) context;
      EObject container = ((DSemanticDecorator) view.eContainer()).getTarget();
      for (FunctionalExchange anExchange : currentCategory.getExchanges()) {
        if (isOrIsChildFunction(FunctionalExchangeExt.getSourceFunction(anExchange), container) && !returnedList.contains(anExchange)) {
          returnedList.add(anExchange);
        }
      }
    }
    return returnedList;
  }

  /**
   * @param object
   * @param clazz
   * @return the parent of object that is an instance of given class
   */
  EObject getParent(EObject object, EClass clazz) {
    EObject currentObject = object;
    while (currentObject != null) {
      if (currentObject.eClass().equals(clazz) || currentObject.eClass().getEAllSuperTypes().contains(clazz)) {
        return currentObject;
      }
      currentObject = currentObject.eContainer();
    }
    return null;
  }

  public Set<DDiagramElement> getSetOfDiagramElements(DDiagram diagram) {
    return DiagramServices.getDiagramServices().getSetOfDiagramElements(diagram);
  }

  public Set<EObject> getPinCategorySemanticElements(ComponentExchangeCategory context, DSemanticDecorator view) {
    Set<EObject> returnedList = new HashSet<>();
    returnedList.add(context);
    EObject viewComponent = CsServices.getService().getComponentType((DSemanticDecorator) view.eContainer());
    for (ComponentExchange exchange : context.getExchanges()) {
      EObject sourceComponent = ComponentExchangeExt.getSourceComponent(exchange);
      EObject targetComponent = ComponentExchangeExt.getTargetComponent(exchange);
      if (sourceComponent.equals(viewComponent) || targetComponent.equals(viewComponent)) {
        returnedList.add(exchange);
      }
    }
    return returnedList;
  }

  public Set<EObject> getPinCategorySemanticElements(PhysicalLinkCategory context, DSemanticDecorator view) {
    Set<EObject> returnedList = new HashSet<>();
    returnedList.add(context);
    EObject viewComponent = CsServices.getService().getComponentType((DSemanticDecorator) view.eContainer());
    for (PhysicalLink link : context.getLinks()) {
      EObject sourceComponent = PhysicalLinkExt.getSourceComponent(link);
      EObject targetComponent = PhysicalLinkExt.getTargetComponent(link);
      if (sourceComponent.equals(viewComponent) || targetComponent.equals(viewComponent)) {
        returnedList.add(link);
      }
    }
    return returnedList;
  }

  /**
   * used in context, logical, physical
   * 
   * @param eObject
   * @param categories
   *          : list of existing categories.
   * @param exchangesView
   *          : list of DEdge represented.
   * @return every displayable categories : a category is displayable if one of its exchanges are represented in the
   *         diagram
   */
  public Set<ExchangeCategory> getShowableCategories(EObject eObject, final List<ExchangeCategory> categories,
      final List<DEdge> exchangesView) {

    Set<ExchangeCategory> showableCategory = new HashSet<>();

    for (DEdge edge : exchangesView) {
      if (edge.getTarget() instanceof FunctionalExchange) {
        FunctionalExchange target = (FunctionalExchange) edge.getTarget();
        showableCategory.addAll(target.getCategories());
      } else if (edge.getTarget() instanceof ExchangeCategory) {
        ExchangeCategory target = (ExchangeCategory) edge.getTarget();
        for (FunctionalExchange exchange : target.getExchanges()) {
          showableCategory.addAll(exchange.getCategories());
        }
      }
    }
    return showableCategory;
  }

  /**
   * used in common context
   * 
   * @param aClassifier
   * @return
   */
  public Collection<GeneralizableElement> getSuperClassifiers(GeneralizableElement aClassifier) {
    Collection<GeneralizableElement> superClassifiers = new ArrayList<>();
    superClassifiers.add(aClassifier);
    for (Generalization aGeneralization : aClassifier.getSuperGeneralizations()) {
      superClassifiers.addAll(getSuperClassifiers(aGeneralization.getSuper()));
    }
    return superClassifiers;
  }

  /**
   * used in common context
   * 
   * @param aClassifier
   * @return
   */
  public Collection<GeneralizableElement> getSuperClassifiers(GeneralizableElement aClassifier,
      Generalization generalization) {
    Collection<GeneralizableElement> superClassifiers = new ArrayList<>();
    superClassifiers.add(aClassifier);
    for (Generalization aGeneralization : aClassifier.getSuperGeneralizations()) {
      if (!aGeneralization.equals(generalization)) {
        superClassifiers.addAll(getSuperClassifiers(aGeneralization.getSuper()));
      }

    }
    return superClassifiers;
  }

  /**
   * used in common context
   * 
   * @param aClassifier
   * @return
   */
  public Collection<GeneralizableElement> getSubClassifiers(GeneralizableElement aClassifier) {
    Collection<GeneralizableElement> superClassifiers = new ArrayList<>();
    superClassifiers.add(aClassifier);
    for (Generalization aGeneralization : aClassifier.getSubGeneralizations()) {
      superClassifiers.addAll(getSubClassifiers(aGeneralization.getSub()));
    }
    return superClassifiers;
  }

  /**
   * used in common context
   * 
   * @param aClassifier
   * @return
   */
  public Collection<GeneralizableElement> getSubClassifiers(GeneralizableElement aClassifier,
      Generalization generalization) {
    Collection<GeneralizableElement> superClassifiers = new ArrayList<>();
    superClassifiers.add(aClassifier);
    for (Generalization aGeneralization : aClassifier.getSubGeneralizations()) {
      if (!aGeneralization.equals(generalization)) {
        superClassifiers.addAll(getSubClassifiers(aGeneralization.getSub()));
      }

    }
    return superClassifiers;
  }

  public EList<EObject> getTargetElements(final PhysicalComponent context, final DDiagram viewPoint,
      final DNodeContainer containerView) {
    EList<Layer> activatedLayers = viewPoint.getActivatedLayers();
    boolean hasActivatedLayer = false;
    for (Layer layer : activatedLayers) {
      if (layer.getName().equalsIgnoreCase("Sub Components")) { //$NON-NLS-1$
        hasActivatedLayer = true;
      }
    }

    EList<EObject> functionalAllocations = new BasicEList<>();
    EList<EObject> containerfunctionalAllocations = new BasicEList<>();
    if (!hasActivatedLayer) {
      // retrieve all the DeployedElement in depth
      getInDepthTargetElements(context, functionalAllocations);
    } else {
      // retrieve all the DeployedElement in depth
      getInDepthTargetElements(context, functionalAllocations);

      if (null != containerView) {
        EList<DDiagramElement> ownedDiagramElements = containerView.getOwnedDiagramElements();
        for (DDiagramElement diagramElement : ownedDiagramElements) {
          if (diagramElement instanceof AbstractDNode) {
            AbstractDNode abstractNode = (AbstractDNode) diagramElement;
            EObject target = abstractNode.getTarget();
            if (target instanceof PhysicalComponent) {
              // retrieve all the DeployedElement in depth
              getInDepthTargetElements((PhysicalComponent) target, containerfunctionalAllocations);
            }
          }
        }
      }
    }

    functionalAllocations.removeAll(containerfunctionalAllocations);

    return functionalAllocations;
  }

  /**
   * Finds an unique name for the given capella element. used everywhere
   * 
   * @param namedElement
   *          the element.
   * @return an unique name for the given capella element.
   */
  public String getUniqueName(AbstractNamedElement namedElement) {
    return EcoreUtil2.getUniqueName(namedElement, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME,
        namedElement.eClass().getName(), false, false);
  }

  /**
   * Finds an unique name for the given capella element. Suffixes the <code>prefix</code> string with a number to get an
   * unique name for the namespace of <code>namedElement</code>.
   * 
   * @param namedElement
   *          the named element.
   * @param space
   *          {value can be either 'true' or 'false'} if true : leave space between namedElement EClass and Integer
   *          Index
   * @return an unique name for the given capella element. Suffixes the <code>prefix</code> string with a number to get
   *         an unique name for the namespace of <code>namedElement</code>.
   */
  public String getUniqueName(AbstractNamedElement namedElement, boolean space) {
    return EcoreUtil2.getUniqueName(namedElement, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME,
        namedElement.eClass().getName(), space, false);
  }

  /**
   * Finds an unique name for the given capella element. Suffixes the <code>prefix</code> string with a number to get an
   * unique name for the namespace of <code>namedElement</code>.
   * 
   * @param namedElement
   *          the named element.
   * @param prefix
   *          the prefix of the name.
   * @return an unique name for the given capella element. Suffixes the <code>prefix</code> string with a number to get
   *         an unique name for the namespace of <code>namedElement</code>.
   */
  public String getUniqueName(AbstractNamedElement namedElement, String prefix) {
    return EcoreUtil2.getUniqueName(namedElement, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, prefix,
        true, false);
  }

  /**
   * Return the ddiagram of the element replaced by getDiagramContainer
   */
  @Deprecated
  public DDiagram getViewDiagram(EObject object) {
    if (object instanceof DDiagramElement) {
      return ((DDiagramElement) object).getParentDiagram();

    } else if (object instanceof DDiagram) {
      return (DDiagram) object;
    }

    return null;
  }

  /**
   * used in oa
   * 
   * @param decorators
   * @return
   */
  public List<DSemanticDecorator> getVisibles(List<DSemanticDecorator> decorators) {
    List<DSemanticDecorator> visibles = new LinkedList<>(decorators);
    for (DSemanticDecorator dec : decorators) {
      if (dec instanceof DDiagramElement) {
        if (!isVisibleInDiagram(dec)) {
          visibles.remove(dec);
        }
      }
    }

    return visibles;
  }

  public boolean isAllocatedInActor(final AbstractFunction context) {
    EList<AbstractFunctionalBlock> allocationBlocks = context.getAllocationBlocks();
    for (AbstractFunctionalBlock abstractFunctionalBlock : allocationBlocks) {
      if (abstractFunctionalBlock instanceof Component) {
        Component component = (Component) abstractFunctionalBlock;
        if (component.isActor()) {
          return true;
        }
      }
    }
    return false;
  }

  public EObject hide(DDiagramElement context) {
    HideFilterHelper.INSTANCE.hide(context);
    return context;
  }

  /**
   * used everywhere
   * 
   * @param node
   *          : a DNode in a diagram
   * @return if the current node is a borderedNode or not
   */
  public boolean isABorderedNode(AbstractDNode node) {
    return DiagramServices.getDiagramServices().isABorderedNode(node);
  }

  /**
   * a function is allocated to a component/role if it is effectively allocated or if all its leaves are allocated to
   * considered component/role. used in Architecture Blank Diagrams
   * 
   * @param eObject
   * @param function
   * @param component
   *          or role
   * @return is function allocated to component/role
   */
  public boolean isAllocatedFunction(EObject eObject, AbstractFunction function, EObject container) {
    LinkedList<AbstractFunction> allocatedFunctions = new LinkedList<>();

    if (container instanceof Component) {
      Component component = (Component) container;
      allocatedFunctions.addAll(component.getAllocatedFunctions());
      for (Component subComponent : getCache(ComponentExt::getAllSubUsedAndDeployedComponents, component)) {
        allocatedFunctions.addAll(subComponent.getAllocatedFunctions());
      }
    }

    return isAllocatedFunctionCommon(function, container, allocatedFunctions);
  }

  /**
   * Returns true if the function can be displayed in the container which has the specified target, false otherwise.
   * 
   * @param function
   *          the function
   * @param container
   *          the container target
   * @param containerView
   *          the container view
   * @return true if the function can be displayed in the container which has the specified target, false otherwise.
   * 
   *         TODO This function should have a more meaningfull name such as shouldFunctionBeDisplayed, the current
   *         isAllocatedFunction name is confusing. This should be changed in a non patch version.
   */
  public boolean isAllocatedFunction(AbstractFunction function, EObject container, DSemanticDecorator containerView) {
    EObject containerTarget;

    if (!(containerView instanceof DNodeContainer)) {
      // As PAB_Function mapping is defined under layer and not under component mapping, the precondition may be called
      // with diagram as container. Making container parameter as DNodeContainer only raised an EvaluationException,
      // which returns true on AbstractNodeMappingQuery.evaluationPrecondition.
      // We need to return false if used outside a container.
      return false;
    }

    if (function instanceof OperationalActivity) {
      containerTarget = container;
    } else {
      containerTarget = CsServices.getService().getComponentType(container);
    }

    // contains allocation blocks and allocation roles
    Set<EObject> allocationObjects = AbstractFunctionExt.getAllocationBlocks(function).stream()
        .filter(EObject.class::isInstance).map(EObject.class::cast).collect(Collectors.toSet());

    // function directly contained in the containerTarget
    if (allocationObjects.contains(containerTarget)) {
      return true;
    }

    // none of the allocation blocks must be in this container
    // otherwise we could just display the function in the visible container
    for (EObject allocationObject : allocationObjects) {
      if (DiagramServices.getDiagramServices().isIndirectlyOnDiagram((DNodeContainer) containerView,
          allocationObject)) {
        return false;
      }
    }

    // all the allocation blocks are not in the container, analyze the subcomponents
    Set<EObject> subComponents = new HashSet<>();
    if (container instanceof Component) {
      subComponents.addAll(ComponentExt.getSubUsedAndDeployedComponents((Component) container));

      if (container instanceof Entity) {
        subComponents.addAll(((Entity) container).getAllocatedRoles());
      }
    } else if (container instanceof Part) {
      subComponents.addAll(PartExt.getSubUsedAndDeployedComponentsOfPart((Part) container));
    }

    // not a single allocation block is visible in the container, and at least one is a direct subcomponent of target
    if (!Collections.disjoint(subComponents, allocationObjects)) {
      return true;
    }

    // not a direct subcomponent -> compute the subproblem on hidden subcomponents and stop at first match
    for (EObject subComponent : subComponents) {
      boolean isOnDiagram = DiagramServices.getDiagramServices().isIndirectlyOnDiagram((DNodeContainer) containerView,
          subComponent);

      if (!isOnDiagram && isAllocatedFunction(function, subComponent, containerView)) {
        return true;
      }
    }

    // none of the hidden subcomponents is a valid target -> analyze children functions
    EList<AbstractFunction> subFunctions = function.getSubFunctions();

    if (subFunctions.isEmpty()) {
      return false;
    }

    // an element can be displayed in a container, if all of its children can be displayed in that container
    // compute the subproblem on all children
    for (AbstractFunction subFunction : subFunctions) {
      if (!isAllocatedFunction(subFunction, container, containerView)) {
        return false;
      }
    }

    return true;
  }

  protected boolean isAllocatedFunctionCommon(AbstractFunction function, EObject container,
      LinkedList<AbstractFunction> allocatedFunctions) {
    boolean result = false;

    // can be added after
    if (container instanceof Role) {
      Role role = (Role) container;
      for (ActivityAllocation alloc : role.getOwnedActivityAllocations()) {
        if (alloc.getTargetElement() instanceof AbstractFunction) {
          AbstractFunction alfunc = (AbstractFunction) alloc.getTargetElement();
          allocatedFunctions.add(alfunc);
        }
      }
    }

    if (allocatedFunctions.contains(function)) {
      result = true;
    } else if (!FunctionExt.isLeaf(function)) {
      LinkedList<AbstractFunction> leaves = getLeaves(function);
      LinkedList<AbstractFunction> allocatedLeaves = new LinkedList<>(leaves);
      allocatedLeaves.retainAll(allocatedFunctions);
      if (allocatedLeaves.size() == leaves.size()) {
        result = true;
      }
    }

    return result;
  }

  /**
   * Is given AbtractFunction directly allocated (or considered as allocated) to given Component.<br>
   * To be considered as allocated, all leaf of a non leaf AbstractFunction must be allocated to given Component.
   * 
   * @param function
   * @param container
   * @return
   */
  public boolean isAllocatedInThisComponent(AbstractFunction function, EObject container) {
    if (AbstractFunctionExt.isLeaf(function)) {
      List<Component> allocatingComponent = getCache(AbstractFunctionExt::getAllocatingComponents, function);
      if (allocatingComponent.size() != 1 || allocatingComponent.get(0) != container) {
        // Function is a leaf but is not allocated to given Component
        return false;
      }
    } else {
      List<AbstractFunction> allLeaves = getCache(FunctionExt::getAllLeafAbstractFunctions, function);
      for (AbstractFunction leaf : allLeaves) {
        List<Component> allocatingComponent = getCache(AbstractFunctionExt::getAllocatingComponents, leaf);
        if (allocatingComponent.size() != 1 || allocatingComponent.get(0) != container) {
          // Function is not a leaf and at least one of its leaf is not allocated to given
          // Component
          return false;
        }
      }
    }
    return true;
  }

  /**
   * 
   * Function border should be dashed only in the specified use cases : - ALL its leaf sub-functions are allocated to
   * sub-components of the input component - Function is not allocated directly to the displayed component
   * 
   * @param function
   * @param container
   * @return Return whether the border of a function should be dashed or not.
   */
  public boolean isDashedFunction(AbstractFunction function, Component container) {
    if (AbstractFunctionExt.isLeaf(function)) {
      List<Component> allocatingComponents = getCache(AbstractFunctionExt::getAllocatingComponents, function);
      if (!allocatingComponents.isEmpty()) {
        return allocatingComponents.stream().allMatch(comp -> comp != container);
      }
    } else {
      List<AbstractFunction> allLeaves = getCache(FunctionExt::getAllLeafAbstractFunctions, function);
      List<Component> subComponents = getCache(ComponentExt::getAllSubUsedAndDeployedComponents, container);
      for (AbstractFunction leaf : allLeaves) {
        List<Component> allocatingComponents = getCache(AbstractFunctionExt::getAllocatingComponents, leaf);
        if (allocatingComponents.stream().noneMatch(comp -> comp != container && subComponents.contains(comp))) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  /**
   * used in physical
   * 
   * @param context
   * @return
   */
  public boolean isAllocatedFunctionOnParentContainers(final EObject context) {
    // Physical Architecture Blank : Hide Allocated Function On Parent
    // Containers {Mapping filter Hide}
    boolean flag = true;
    EObject context2 = context;
    if (context2 instanceof AbstractDNode) {
      EObject container = context2.eContainer();

      if (container != null) {
        TreeIterator<EObject> allContents = container.eAllContents();

        while (allContents.hasNext()) {
          EObject next = allContents.next();
          if (next instanceof DNodeContainer) {
            DNodeContainer dnc = (DNodeContainer) next;
            EList<DDiagramElement> ownedDiagramElements = dnc.getOwnedDiagramElements();
            for (DDiagramElement diagramElement : ownedDiagramElements) {
              if (diagramElement instanceof DNode
                  && ((DNode) diagramElement).getTarget().equals(((AbstractDNode) context2).getTarget())) {
                flag = false;
                break;
              }
            }
          }
        }
      } else {
        return false;
      }
      if (flag) {
        return true;
      }

      DDiagram dDiagram = getDiagramContainer(context2);
      if (null != dDiagram) {
        EList<Layer> activatedLayers = dDiagram.getActivatedLayers();
        for (Layer layer : activatedLayers) {
          if (layer.getName().equalsIgnoreCase("Sub Components")) { //$NON-NLS-1$
            return false;
          }
        }
      }
    }

    return false;
  }

  /**
   * Return <code>true</code> if <code>mayBeChild</code> is a child of <code>current</code>. used everywhere
   * 
   * @param current
   *          the current object.
   * @param mayBeChild
   *          the object that may be the child.
   * @return <code>true</code> if <code>mayBeChild</code> is a child of <code>current</code>.
   */
  public boolean isChild(EObject current, EObject mayBeChild) {
    EObject child = mayBeChild;
    if (child == null) {
      return false;
    }
    while (child.eContainer() != null) {
      if (child.eContainer().equals(current)) {
        return true;
      }
      child = child.eContainer();
    }
    return false;
  }

  public boolean isEPBSContext(ModelElement scenario) {
    ModellingArchitecture archi = (ModellingArchitecture) getAncestor(scenario,
        CapellacorePackage.Literals.MODELLING_ARCHITECTURE);
    if (archi instanceof EPBSArchitecture) {
      return true;
    }
    return false;
  }

  /**
   * used in common, context
   */
  public boolean isGeneralizable(final EObject context, EObject source, EObject target) {
    // return false if source(Class) is not primitive and target(Class) is primitive
    if ((source instanceof Class) && (target instanceof Class) && ((Class) source).isIsPrimitive()
        && !((Class) target).isIsPrimitive()) {
      return false;
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

    if (((source instanceof PhysicalQuantity) && (target instanceof NumericType))
        || ((source instanceof Component) && (target instanceof Component)) || ((source instanceof GeneralizableElement)
            && (target instanceof GeneralizableElement) && source.eClass().equals(target.eClass()))) {

      GeneralizableElement targetClass = (GeneralizableElement) target;
      GeneralizableElement sourceClass = (GeneralizableElement) source;

      // check if multiple inheritance is allowed
      // check if target is not already inherited
      // check to avoid inheritance cycle
      if (!isMultipleInheritanceAllowed()) {
        if (!getSuperClassifiers(sourceClass).contains(targetClass)
            && !getSuperClassifiers(targetClass).contains(sourceClass)) {
          if (getSuperClassifiers(sourceClass).size() == 1) {
            return true;
          }
          // context is Generalization used when reconnect Link is
          // used
          if (context instanceof Generalization) {
            return true;
          }

        }
      } else {
        if (source instanceof Component) {
          Component sourceComp = (Component) source;
          if (!sourceComp.isActor()
              && !CapellaModelPreferencesPlugin.getDefault().isComponentNonActorInheritanceAllowed()) {
            return false;
          }
          if (target instanceof Component) {
            Component targetComp = (Component) target;
            return targetComp.isActor() == sourceComp.isActor();
          }
        }
        return (!getSuperClassifiers(sourceClass).contains(targetClass)
            && !getSuperClassifiers(targetClass).contains(sourceClass));
      }
    }

    return false;
  }

  /**
   * used in common oa
   * 
   * @param execution
   * @return
   */
  public boolean isInternalLCForExecution(Execution execution) {
    InstanceRole role = execution.getCovered();
    return isInternalLCForInstanceRole(role);
  }

  /**
   * return true if the instanceRole correspond to a part which is inside the logical component containing the scenario,
   * false otherwise used in common oa
   * 
   * @param instanceRole
   * @return
   */
  public boolean isInternalLCForInstanceRole(InstanceRole instanceRole) {
    Scenario scenario = (Scenario) instanceRole.eContainer();
    EObject scenarioContainer = EcoreUtil2.getFirstContainer(scenario, CsPackage.Literals.BLOCK_ARCHITECTURE);
    AbstractInstance instance = instanceRole.getRepresentedInstance();
    EObject container = instance.eContainer();
    while (container != null) {
      if (container == scenarioContainer) {
        return true;
      }
      container = container.eContainer();
    }
    return false;
  }

  /**
   * used in common.odesign
   */
  public boolean isBehaviorComponent(final InstanceRole instanceRole) {
    return isComponentNature(instanceRole, PhysicalComponentNature.BEHAVIOR);
  }

  /**
   * used in common.odesign
   */
  public boolean isNodeComponent(final InstanceRole instanceRole) {
    return isComponentNature(instanceRole, PhysicalComponentNature.NODE);
  }

  private boolean isComponentNature(final InstanceRole instanceRole, PhysicalComponentNature nature) {
    BlockArchitecture ownerBlockArchitecture = (BlockArchitecture) getAncestor(instanceRole,
        CsPackage.Literals.BLOCK_ARCHITECTURE);
    boolean isNodeComponent = (ownerBlockArchitecture instanceof PhysicalArchitecture);
    Component instanceRoleComponent = instanceRole == null ? null
        : (Component) instanceRole.getRepresentedInstance().getAbstractType();
    if (instanceRoleComponent instanceof PhysicalComponent) {
      PhysicalComponent sourcePhysicalComponent = (PhysicalComponent) instanceRoleComponent;
      isNodeComponent = isNodeComponent && sourcePhysicalComponent.getNature().equals(nature);
    }

    return isNodeComponent;
  }

  /**
   * used in context, logical, physical
   * 
   * @param pin
   * @return if a pin is linked to an Internal ControlNode by a functional exchange
   */
  public boolean isLinkedWithInternalControlNode(Pin pin) {
    AbstractFunction container = (AbstractFunction) pin.eContainer();

    if (pin instanceof FunctionInputPort) {
      for (FunctionalExchange exchange : ((FunctionInputPort) pin).getIncomingFunctionalExchanges()) {
        if (FaServices.getFaServices().isControlNode(exchange.getSource())) {
          if (isChild(container, exchange.getSource())) {
            return true;
          }
        }
      }
    }
    if (pin instanceof FunctionOutputPort) {
      for (FunctionalExchange exchange : ((FunctionOutputPort) pin).getOutgoingFunctionalExchanges()) {
        if (FaServices.getFaServices().isControlNode(exchange.getTarget())) {
          if (isChild(container, exchange.getSource())) {
            return true;
          }
        }
      }
    }
    return false;

  }

  /*
   * return the 'Allow Multiple Inheritance' preference value
   */
  private boolean isMultipleInheritanceAllowed() {
    return CapellaModelPreferencesPlugin.getDefault().isMultipleInheritanceAllowed();
  }

  public boolean isNull(EObject current) {
    return current == null;
  }

  /**
   * used
   */
  public boolean isOperationalContext(Scenario scenario) {
    return (scenario.eContainer() instanceof AbstractCapability)
        && BlockArchitectureExt.getRootBlockArchitecture(scenario) instanceof OperationalAnalysis;
  }

  public boolean isOperationalContext(AbstractCapability capability) {
    return BlockArchitectureExt.getRootBlockArchitecture(capability) instanceof OperationalAnalysis;
  }

  public boolean isPrimitiveTypeForAttribute(EObject object) {
    if (!(object instanceof Property)) {
      return false;
    }
    AbstractType type = ((Property) object).getAbstractType();

    if (type == null) {
      return true;
    }

    if (type instanceof Class) {
      Class cl = (Class) type;
      return cl.isIsPrimitive();
    }
    if (type instanceof org.polarsys.capella.core.data.information.Collection) {
      org.polarsys.capella.core.data.information.Collection collection = (org.polarsys.capella.core.data.information.Collection) type;
      return collection.isIsPrimitive();
    }
    if (type instanceof DataType) {
      return true;

    }

    return false;
  }

  public boolean isSynchronized(DDiagram diagram) {
    return diagram.isSynchronized();
  }

  /**
   * return true if the message is a source of a RefinementLink, false otherwise used in common and oa
   * 
   * @param message
   * @return
   */
  public boolean isTransformedExecution(Execution message) {
    for (AbstractTrace trace : message.getOutgoingTraces()) {
      if (trace instanceof RefinementLink) {
        return true;
      }
    }
    return false;
  }

  /**
   * used in context
   * 
   * @param context
   * @param preSource
   * @param preTarget
   * @return
   */
  public boolean isValidActorTarget(EObject context, EObject preSource, EObject preTarget) {
    if ((preSource == null) || (preTarget == null)) {
      return false;
    }

    if (preSource instanceof Capability) {
      Capability cap = (Capability) preSource;
      EList<CapabilityInvolvement> ownedActInvol = cap.getOwnedCapabilityInvolvements();
      if (!ownedActInvol.isEmpty()) {
        if (ownedActInvol.contains(preTarget)) {
          return true;
        }
      }
    } else if (preSource instanceof Mission) {
      Mission mis = (Mission) preSource;
      EList<MissionInvolvement> ownedActInvol = mis.getOwnedMissionInvolvements();
      if (!ownedActInvol.isEmpty()) {
        if (ownedActInvol.contains(preTarget)) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * used in common.odesign
   * 
   * @param context
   * @param sourceCapability
   *          - The source Capability of the involvement link
   * @param involvementTarget
   *          - The Actor target of the involvement link
   * @return
   */
  public boolean isActorInvolvedWithCapability(EObject context, EObject sourceCapability, EObject involvementTarget) {

    if ((sourceCapability == null) || (involvementTarget == null)) {
      return false;
    }

    EList<Component> involvedActors = new BasicEList<>();

    // Logical and Physical Levels
    if (sourceCapability instanceof CapabilityRealization) {

      for (CapabilityRealizationInvolvedElement element : ((CapabilityRealization) sourceCapability)
          .getInvolvedComponents()) {
        if (element instanceof Component) {
          Component component = (Component) element;
          if (ComponentExt.isActor(component)) {
            involvedActors.add(component);
          }
        }
      }
    }

    // System Analysis level
    else if (sourceCapability instanceof Capability) {

      for (SystemComponent component : ((Capability) sourceCapability).getInvolvedSystemComponents()) {
        if (ComponentExt.isActor(component)) {
          involvedActors.add(component);
        }
      }
    }

    if (!involvedActors.isEmpty()) {
      if (involvedActors.contains(involvementTarget)) {
        return true;
      }
    }

    return false;
  }

  /**
   * used in common.odesign
   * 
   * @param context
   * @param sourceCapability
   *          - The source Capability of the involvement link
   * @param involvementTarget
   *          - The SystemComponent target of the involvement link
   * @return
   */
  public boolean isComponentInvolvedWithCapability(EObject context, EObject sourceCapability,
      EObject involvementTarget) {

    if ((sourceCapability == null) || (involvementTarget == null)) {
      return false;
    }

    if (sourceCapability instanceof CapabilityRealization) {
      return ((CapabilityRealization) sourceCapability).getInvolvedComponents().contains(involvementTarget);
    }

    return false;
  }

  /**
   * used by refresh extensions
   * 
   * @param diagram
   * @param anElement
   * @return true if the element is visible in the diagram
   */
  public boolean isVisibleInDiagram(DDiagram diagram, DDiagramElement anElement) {
    return DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, anElement);
  }

  public boolean isVisibleInDiagram(EObject current) {
    if (!(current instanceof DDiagramElement)) {
      return false;
    }
    DDiagramElement currentElement = (DDiagramElement) current;
    DDiagram currentDiagram = currentElement.getParentDiagram();
    return DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(currentDiagram, currentElement);
  }

  /**
   * refresh the current element
   */
  public void refreshElement(DRepresentationElement element) {
    if (element != null) {
      DDiagram diagram = getDiagramContainer(element);
      ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry()
          .getModelAccessor(element.getTarget());
      IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(element.getTarget());
      DDiagramSynchronizer diagramSync = new DDiagramSynchronizer(interpreter, diagram.getDescription(), accessor);
      diagramSync.setDiagram((DSemanticDiagram) diagram);
      DDiagramElementSynchronizer elementSync = diagramSync.getElementSynchronizer();
      if (element instanceof DEdge) {
        elementSync.refresh((DEdge) element);
      } else if (element instanceof AbstractDNode) {
        elementSync.refresh((AbstractDNode) element);
      }
    }
  }

  /**
   * Get all the ancestor elements of DNodeContainer from Diagram.
   * 
   * @param current
   * @return List of DiagramElements
   */
  public void removeElement(CapellaElement element) {
    if (element != null) {
      List<EObject> list = new ArrayList<>();
      list.add(element);
      removeElements(list);
    }
  }

  public void removeElements(Collection<? extends EObject> elements) {
    if ((elements != null) && (!elements.isEmpty())) {
      CapellaDeleteCommand command = new CapellaDeleteCommand(TransactionHelper.getExecutionManager(elements), elements,
          false, false, true);
      if (command.canExecute()) {
        try {
          command.execute();
        } catch (OperationCanceledException oce) {
          throw new OperationCanceledException(RE_THROW_OCE_PREFIX);
        }
      }
    }
  }

  public EObject show(DDiagramElement context) {
    HideFilterHelper.INSTANCE.reveal(context);
    return context;
  }

  public EObject show(Collection<DDiagramElement> contexts) {
    EObject result = null;
    for (DDiagramElement element : contexts) {
      result = element;
      HideFilterHelper.INSTANCE.reveal(element);
    }
    return result;
  }

  /**
   * returns a shared instance of this services.
   * 
   * @return a shared instance of this services.
   */
  public static CapellaServices getService() {
    if (service == null) {
      service = new CapellaServices();
    }
    return service;
  }

  /**
   * Display the contents of a constraint. common.odesing : CDB (referenced everywhere)
   * 
   * @param constraint
   * @return a non-null constraint label
   */
  public String getConstraintLabel(Constraint constraint) {
    return CapellaEmbeddedLinkedTextEditorInput.getDefaultText(constraint, constraint.getName());
  }

  /**
   * Returns the EClass of the given domain.
   * 
   * @param domain
   *          : Sirius Domain Class which can be EClassName or prefix:EClassName
   */
  public EClass getEClass(EObject context, String domain) {
    EClass clazz = null;
    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(context);
    // We would call accessor.getEClass(domain) but it doesn't exist and
    // EcoreIntrinsicExtender.getEClassesFromName is private
    try {
      clazz = accessor.createInstance(domain).eClass();
    } catch (Exception e) {
      // Nothing here
    }
    return clazz;
  }

  public EClass getDomainClass(EObject context, DiagramElementMapping mapping) {
    DiagramElementMappingQuery query = new DiagramElementMappingQuery(mapping);
    Option<String> domainClass = query.getDomainClass();
    if (domainClass.some()) {
      return getEClass(context, domainClass.get());
    }
    return null;
  }

  public String capellaLabelService(EObject e, DDiagramElement view, DDiagram diagram) {
    return EObjectExt.getText(e);
  }

  /**
   * A service provides associated semantic elements for a given target.
   * 
   * @param target
   * @return A collection of associated semantic elements (including the target itself) based on the type of given
   *         target.
   */
  public Collection<EObject> getAssociatedSemanticElements(EObject target) {
    List<EObject> associatedSemanticElements = new ArrayList<>();
    if (target == null) {
      return associatedSemanticElements;
    }

    if (target instanceof AbstractTypedElement) {
      Collection<EObject> elements = getAssociatedSemanticElementsForAbstractTypedElement(
          (AbstractTypedElement) target);
      associatedSemanticElements.addAll(elements);
    }
    if (target instanceof EventReceiptOperation) {
      Collection<EObject> elements = getAssociatedSemanticElementsForEventReceiptOperation(
          (EventReceiptOperation) target);
      associatedSemanticElements.addAll(elements);
    }
    if (target instanceof AbstractEnd) {
      Collection<EObject> elements = getAssociatedSemanticElementsForAbstractEnd((AbstractEnd) target);
      associatedSemanticElements.addAll(elements);
    }
    if (target instanceof EventSentOperation) {
      Collection<EObject> elements = getAssociatedSemanticElementsForEventSentOperation((EventSentOperation) target);
      associatedSemanticElements.addAll(elements);
    }
    if (target instanceof SequenceMessage) {
      Collection<EObject> elements = getAssociatedSemanticElementsForSequenceMessage((SequenceMessage) target);
      associatedSemanticElements.addAll(elements);
    }
    if (target instanceof InstanceRole) {
      Collection<EObject> elements = getAssociatedSemanticElementsForInstanceRole((InstanceRole) target);
      associatedSemanticElements.addAll(elements);
    }
    if (target instanceof StateFragment) {
      Collection<EObject> elements = getAssociatedSemanticElementsForStateFragment((StateFragment) target);
      associatedSemanticElements.addAll(elements);
    }
    if (target instanceof Involvement) {
      Collection<EObject> elements = getAssociatedSemanticElementsForInvolvement((Involvement) target);
      associatedSemanticElements.addAll(elements);
    }

    List<EObject> associatedSemanticElementsWithoutNulls = associatedSemanticElements.stream().filter(Objects::nonNull)
        .map(x -> x).collect(Collectors.toList());

    if (!associatedSemanticElementsWithoutNulls.isEmpty()) {
      return associatedSemanticElementsWithoutNulls;
    }
    return Arrays.asList(target);
  }

  private Collection<EObject> getAssociatedSemanticElementsForAbstractTypedElement(AbstractTypedElement element) {
    List<EObject> semanticElements = new ArrayList<>();
    semanticElements.add(element);
    semanticElements.add(element.getAbstractType());
    return semanticElements;
  }

  private Collection<EObject> getAssociatedSemanticElementsForEventReceiptOperation(EventReceiptOperation element) {
    List<EObject> semanticElements = new ArrayList<>();
    semanticElements.add(element);
    semanticElements.add(element.getOperation());
    return semanticElements;
  }

  private Collection<EObject> getAssociatedSemanticElementsForAbstractEnd(AbstractEnd element) {
    List<EObject> semanticElements = new ArrayList<>();
    semanticElements.add(element);
    semanticElements.add(element.getEvent());
    return semanticElements;
  }

  private Collection<EObject> getAssociatedSemanticElementsForEventSentOperation(EventSentOperation element) {
    List<EObject> semanticElements = new ArrayList<>();
    semanticElements.add(element);
    semanticElements.add(element.getOperation());
    return semanticElements;
  }

  private Collection<EObject> getAssociatedSemanticElementsForSequenceMessage(SequenceMessage element) {
    List<EObject> semanticElements = new ArrayList<>();
    semanticElements.add(element);
    semanticElements.add(element.getReceivingEnd());
    semanticElements.add(element.getSendingEnd());
    semanticElements.add(element.getInvokedOperation());
    return semanticElements;
  }

  private Collection<EObject> getAssociatedSemanticElementsForInstanceRole(InstanceRole element) {
    List<EObject> semanticElements = new ArrayList<>();
    semanticElements.add(element);
    semanticElements.add(element.getRepresentedInstance());
    if (element.getRepresentedInstance() instanceof Part) {
      semanticElements.add(element.getRepresentedInstance().getAbstractType());
    }
    return semanticElements;
  }

  private Collection<EObject> getAssociatedSemanticElementsForStateFragment(StateFragment element) {
    List<EObject> semanticElements = new ArrayList<>();
    semanticElements.add(element);
    semanticElements.add(element.getRelatedAbstractState());
    semanticElements.add(element.getRelatedAbstractFunction());
    return semanticElements;
  }

  private Collection<EObject> getAssociatedSemanticElementsForInvolvement(Involvement element) {
    List<EObject> semanticElements = new ArrayList<>();
    semanticElements.add(element);
    semanticElements.add(element.getInvolved());
    return semanticElements;
  }

  /**
   * Return the formatted date of the last representation change.
   */
  public String getLastModificationDate(EObject representationDescriptor) {
    Long timeStamp = System.currentTimeMillis();
    if (representationDescriptor instanceof DRepresentationDescriptor) {
      String changeId = ((DRepresentationDescriptor) representationDescriptor).getChangeId();
      try {
        timeStamp = Long.parseLong(changeId);
      } catch (NumberFormatException e) {
      }
    }

    Date date = new Date(timeStamp);
    SimpleDateFormat sdf = new SimpleDateFormat(CapellaDiagramPreferencesInitializer.getFormatDate());
    String timeZoneForDateFormatting = CapellaDiagramPreferencesInitializer.getTimeZoneForDateFormatting();
    if (CapellaDiagramPreferences.PREF_DATE_TIMEZONE_SYSTEM.equals(timeZoneForDateFormatting)) {
      timeZoneForDateFormatting = TimeZone.getDefault().getID();
    }
    sdf.setTimeZone(TimeZone.getTimeZone(timeZoneForDateFormatting));
    return sdf.format(date);
  }

  public boolean isLibraryElementOf(EObject source, EObject target) {
    if (source == null || target == null) {
      return false;
    }
    IModel srcModel = LibraryManager.INSTANCE.getModel(source);
    IModel targetModel = LibraryManager.INSTANCE.getModel(target);
    if (srcModel == null || targetModel == null) {
      return false;
    }
    return !targetModel.equals(srcModel);
  }
}
