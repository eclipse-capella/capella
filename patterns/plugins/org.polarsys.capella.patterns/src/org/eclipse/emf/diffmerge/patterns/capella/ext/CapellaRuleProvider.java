/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.emf.diffmerge.patterns.capella.ext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.impl.scopes.FilteredModelScope;
import org.eclipse.emf.diffmerge.patterns.core.api.IPatternApplication;
import org.eclipse.emf.diffmerge.patterns.core.api.locations.IReferenceLocation;
import org.eclipse.emf.diffmerge.patterns.core.util.LocationsUtil;
import org.eclipse.emf.diffmerge.patterns.core.util.locations.BasicReferenceLocation;
import org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ModellerSemanticRuleProvider;
import org.eclipse.emf.diffmerge.patterns.ui.dialogs.MultiStorageChoiceDialog;
import org.eclipse.emf.diffmerge.patterns.ui.dialogs.MultiStorageChoiceDialog.MultiStorageKind;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.diffmerge.structures.common.FOrderedSet;
import org.eclipse.emf.diffmerge.util.ModelsUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacore.Allocation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.AssociationPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;
import org.polarsys.capella.core.model.helpers.ComponentExt;

/**
 * An implementation of a semantic rule provider for Capella models.
 */
public class CapellaRuleProvider extends ModellerSemanticRuleProvider {

  /** Saves storage by prompt locations when "Apply for all Similar Elements" is selected */
  private final Map<EClass, IReferenceLocation> perTypeLocations;

  /** Saves storage by prompt locations when "Apply for all Compatible Elements" is selected */
  private final Collection<IReferenceLocation> predefinedLocations;
  
  /**
   * Constructor
   */
  public CapellaRuleProvider(){
    perTypeLocations = new HashMap<EClass, IReferenceLocation>();
    predefinedLocations = new HashSet<IReferenceLocation>(); 
  }
  
  /** The set of references that must not be considered for dependencies */
  protected static final List<EReference> NON_DEPENDENCY_REFERENCES = Arrays.asList(
      ModellingcorePackage.eINSTANCE.getAbstractType_AbstractTypedElements(),
      CapellacorePackage.eINSTANCE.getType_TypedElements());
  
  /**
   * 
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#adjustScope(org.eclipse.emf.diffmerge.impl.scopes.FilteredModelScope, boolean)
   */
  public void adjustScope(FilteredModelScope scope, boolean extend) {
    boolean changed;
    do {
      changed = adjustScopeNonRec(scope, extend);
    } while (changed); // Reach fix point
  }

  /**
   * Non-recursive core behavior of adjustScope (may not reach a fix point)
   * @param scope a non-null scope
   * @return whether the scope was modified
   */
  private boolean adjustScopeNonRec(FilteredModelScope scope, boolean extend) {
    return extend? extendScopeNonRec(scope): reduceScopeNonRec(scope);
  }

  /**
   * Return whether the given object is concerned by the receiver
   * @param context a non-null object, typically resource or model element
   */
  public boolean appliesTo(Object context) {
    boolean result = context instanceof CapellaElement;
    if (!result) {
      Resource resource = null;
      if (context instanceof Resource) {
        resource = (Resource)context;
      } else if (context instanceof EObject) {
        resource = ((EObject)context).eResource();
      }
      result = CapellaResourceHelper.isCapellaResource(resource);
    }
    return result;
  }

  /**
   * Helper method for deriveOwnership: case where container is derived from the ends
   * of a link/exchange
   * @param source a non-null element which is the end of a link/exchange
   * @param target a non-null element which is the end of a link/exchange
   * @return the computed container, if any
   */
  private EObject deriveLinkContainer(EObject source, EObject target) {
    EObject result = null;
    EObject sourceElement = getLinkDerivationReferenceElement(source);
    EObject targetElement = getLinkDerivationReferenceElement(target);
    if (sourceElement != null && targetElement != null) {
      result = ModelsUtil.getCommonAncestor(sourceElement, targetElement);
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ModellerSemanticRuleProvider#deriveOwnership(org.eclipse.emf.ecore.EObject, java.lang.Object)
   */
  @Override
  public boolean deriveOwnership(EObject element, Object context) {
    boolean result = super.deriveOwnership(element, context);
    if (!result) {
      EObject container = null;
      EReference containment = null;
      // ActivityEdge
      if (element instanceof ActivityEdge) {
        ActivityEdge casted = (ActivityEdge)element;
        if (casted.getSource() != null && casted.getTarget() != null) {
          container = deriveLinkContainer(casted.getSource(), casted.getTarget());
          if (container instanceof AbstractFunction) {
            containment = FaPackage.eINSTANCE.getAbstractFunction_OwnedFunctionalExchanges();
          }
        }
      }
      // Connections
      else if (element instanceof ComponentExchange) {
        ComponentExchange casted = (ComponentExchange)element;
        container = deriveLinkContainer(casted.getSource(), casted.getTarget());
        if (container instanceof Component) {
          containment = FaPackage.eINSTANCE.getAbstractFunctionalBlock_OwnedComponentExchanges();
        }
      }
      // PhysicalLinks
      else if (element instanceof PhysicalLink) {
        PhysicalLink exchange = (PhysicalLink)element;
        List<? extends EObject> ends = exchange.getLinkEnds();
        if (2 == ends.size()) {
          container = deriveLinkContainer(ends.get(0), ends.get(1));
          if (container instanceof Component) {
            containment = CsPackage.eINSTANCE.getComponent_OwnedPhysicalLinks();
          }
        }
      }
      // Association
      else if (element instanceof Association) {
        Association casted = (Association)element;
        List<Property> allEndProperties = new FOrderedSet<Property>();
        allEndProperties.addAll(casted.getOwnedMembers());
        allEndProperties.addAll(casted.getNavigableMembers());
        if (!allEndProperties.isEmpty()) {
          AbstractType type = allEndProperties.get(0).getAbstractType();
          if (type != null) {
            EObject typeContainer = type.eContainer();
            if (typeContainer instanceof AssociationPkg) {
              container = typeContainer;
              containment =
                  InformationPackage.eINSTANCE.getAssociationPkg_OwnedAssociations();
            }
          }
        }
      }
      // AbstractType
      else if (element instanceof AbstractType) {
        AbstractType casted = (AbstractType)element;
        List<AbstractTypedElement> typedElements = Collections.emptyList();
        try {
          TransactionalEditingDomain domain = TransactionHelper.getEditingDomain((EObject)context);
          Resource holdingResource = HoldingResourceHelper.getHoldingResource(domain);
          HoldingResourceHelper.attachToHoldingResource(casted, holdingResource);
          typedElements = casted.getAbstractTypedElements();
          holdingResource.getContents().remove(casted);
        } catch (Exception e) {
          // Cannot navigate derived reference: proceed
        }
        if (typedElements.size() == 1) {
          AbstractTypedElement typedElement = typedElements.get(0);
          container = typedElement.eContainer();
          List<EReference> containments =
              getReferencesForAddition(container, element.eClass(), true, true);
          if (containments.size() == 1) {
            containment = containments.get(0);
          }
        }
      }
      if (null != container && null != containment) {
        IReferenceLocation location =
            new BasicReferenceLocation(container, containment);
        try {
          LocationsUtil.add(location, element);
          result = true;
        } catch (RuntimeException e) {
          // Failure: will return false
        }
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#enforceOwnership(java.util.Collection, java.lang.Object)
   */
  public Boolean enforceOwnership(Collection<? extends EObject> roots, Object context) {
    EList<EObject> derivables = new FOrderedSet<EObject>();
    // Prompt for ownership of non-derivables, remember derivables
    for (EObject root : roots) {
      if (root.eContainer() == null) {
        if (getOwnershipDerivationLevel(root) > 0) {
          // Assume derivables are already ordered by derivation level
          derivables.add(root);
        } else {
          Boolean success = enforceOwnershipByPrompt(
              root, context, true, perTypeLocations, predefinedLocations);
          if (!Boolean.TRUE.equals(success)) {
            return success;
          }
        }
      }
    }
    // Try and derive ownerships
    for (EObject derivable : derivables) {
      boolean derived = deriveOwnership(derivable, context);
      if (!derived) {
        Boolean success = enforceOwnershipByPrompt(
            derivable, context, true, perTypeLocations, predefinedLocations);
        if (!Boolean.TRUE.equals(success)) {
          return success;
        }
      }
    }
    return Boolean.TRUE;
  }
  
  /**
   * Enforce ownership of the given element by prompting the user or using former prompts
   * @param element a non-null element
   * @param context a non-null object, typically resource or model element
   * @param allowCancel whether cancellation by the user is allowed
   * @param perTypeLocations a non-null, modifiable map that registers reference locations for given types
   * @param predefinedLocations a non-null, modifiable collection of reference locations to be tried
   * @return whether the operation succeeded, or null for canceled
   */
  @SuppressWarnings("hiding")
  private Boolean enforceOwnershipByPrompt(EObject element, Object context, boolean allowCancel,
      Map<EClass, IReferenceLocation> perTypeLocations,
      Collection<IReferenceLocation> predefinedLocations) {
    Boolean result = Boolean.TRUE;
    boolean done = false;
    // Trying predefined per-type locations
    IReferenceLocation registeredLocation = perTypeLocations.get(element.eClass());
    if (registeredLocation != null && supportsAdditionOf(registeredLocation, element)) {
      LocationsUtil.add(registeredLocation, element);
      done = true;
    } else {
      // Trying predefined general locations
      Iterator<IReferenceLocation> it = predefinedLocations.iterator();
      while (it.hasNext() && !done) {
        IReferenceLocation predefinedLocation = it.next();
        if (supportsAdditionOf(predefinedLocation, element)) {
          LocationsUtil.add(predefinedLocation, element);
          done = true;
        }
      }
    }
    // Trying by prompt
    while (!done) {
      IReferenceLocation location = null;
      boolean proceed = true;
      MultiStorageKind storageKind = MultiStorageKind.CURRENT_ONLY;
      List<EObject> candidateContainers = getPossibleContainersInContext(
          context, Collections.singleton(element));
      if (candidateContainers.size() == 1) {
        EObject container = candidateContainers.get(0);
        List<EReference> containments =
            getReferencesForElementAddition(container, element, true, true);
        if (containments.size() == 1) {
          EReference containment = containments.get(0);
          location = new BasicReferenceLocation(container, containment);
        }
      }
      if (location == null) {
        MultiStorageChoiceDialog dialog = new MultiStorageChoiceDialog(
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
            null, candidateContainers, element);
        int answer = dialog.open();
        proceed = Window.OK == answer;
        if (proceed) {
          location = dialog.getChoice();
          storageKind = dialog.getStorageKind();
        }
      }
      if (proceed) {
        if (location != null) {
          LocationsUtil.add(location, element);
          done = true;
          switch (storageKind) {
          case ALL_SIMILAR:
            perTypeLocations.put(element.eClass(), location); break;
          case ALL_COMPATIBLE:
            predefinedLocations.add(location); break;
          default: // Nothing
          }
        }
      } else {
        // Canceled
        if (allowCancel) {
          return null;
        }
      }
    }
    return result;
  }
  
  /**
   * Return the references which support the addition of the given value
   * on the given element
   * @param element a non-null element
   * @param value a non-null element
   * @param nonErasing whether the removal of existing values is allowed
   * @param containmentOnly whether only containment references must be considered
   * @return a non-null, potentially empty, unmodifiable list
   */
  private List<EReference> getReferencesForElementAddition(EObject element,
      EObject value, boolean nonErasing, boolean containmentOnly) {
    List<EReference> result = new ArrayList<EReference>();
    List<EReference> candidates;
    if (containmentOnly) {
      candidates = element.eClass().getEAllContainments();
    } else {
      candidates = element.eClass().getEAllReferences();
    }
    for (EReference candidate : candidates) {
      if (supportsAdditionOf(element, candidate, value, nonErasing)) {
        result.add(candidate);
      }
    }
    return Collections.unmodifiableList(result);
  }
  
  /**
   * Behavior of adjustScopeNonRec in the extend case
   * @param scope a non-null scope
   * @return whether the scope was modified
   */
  private boolean extendScopeNonRec(FilteredModelScope scope) {
    List<EObject> toAdd = new FOrderedSet<EObject>();
    for (EObject root : scope.getContents()) {
      toAdd.addAll(getAdditionalRelevantElements(root, scope));
      Iterator<EObject> it = scope.getAllContents(root);
      while (it.hasNext()) {
        EObject current = it.next();
        toAdd.addAll(getAdditionalRelevantElements(current, scope));
      }
    }
    boolean result = false;
    for (EObject rootToAdd : ModelsUtil.getRoots(toAdd)) {
      if (!scope.covers(rootToAdd)) {
        scope.add(rootToAdd, true);
        result = true;
      }
    }
    return result;
  }
  
  /**
   * Return the set of elements which should be present in the given scope for the
   * given element to make sense 
   * @param element a non-null element
   * @param scope a non-null scope
   * @return a non-null, potentially empty, unmodifiable collection
   */
  private Collection<? extends EObject> getAdditionalRelevantElements(EObject element,
      IModelScope scope) {
    List<EObject> result = new FOrderedSet<EObject>();
    // AbstractTypedElement: type
    if (element instanceof AbstractTypedElement) {
      AbstractTypedElement casted = (AbstractTypedElement)element;
      if (casted.getAbstractType() != null) {
        result.add(casted.getAbstractType());
      }
    }
    // Allocation: target element
    if (element instanceof Allocation) {
      Allocation casted = (Allocation)element;
      if (casted.getTargetElement() != null) {
        result.add(casted.getTargetElement());
      }
    }
    // Association: navigable members
    if (element instanceof Association) {
      Association casted = (Association)element;
      result.addAll(casted.getNavigableMembers());
    }
    // ActivityNode: outgoing/incoming within the scope
    if (element instanceof ActivityNode) {
      ActivityNode casted = (ActivityNode)element;
      // Incoming
      for (ActivityEdge incoming : casted.getIncoming()) {
        if (scope.covers(incoming.getSource())) {
          result.add(incoming);
        }
      }
      // Outgoing
      for (ActivityEdge outgoing : casted.getOutgoing()) {
        if (scope.covers(outgoing.getTarget())) {
          result.add(outgoing);
        }
      }
    }
    // ComponentPort: connections within the scope
    if (element instanceof ComponentPort) {
      ComponentPort casted = (ComponentPort)element;
      for (AbstractInformationFlow flow : casted.getInformationFlows()) {
        if (scope.covers(flow.getSource()) && scope.covers(flow.getTarget())) {
          result.add(flow);
        }
      }
    }
    // PhysicalPort: physical links within the scope
    if (element instanceof PhysicalPort) {
      PhysicalPort casted = (PhysicalPort)element;
      for (PhysicalLink link : casted.getInvolvedLinks()) {
        List<AbstractPhysicalLinkEnd> remaining =
            new FOrderedSet<AbstractPhysicalLinkEnd>(link.getLinkEnds(), null);
        remaining.remove(casted);
        if (!remaining.isEmpty() && scope.covers(remaining.get(0))) {
          result.add(link);
        }
      }
    }
    // SequenceMessage: ends
    if (element instanceof SequenceMessage) {
      SequenceMessage casted = (SequenceMessage)element;
      result.add(casted.getSendingEnd());
      result.add(casted.getSendingEnd().getEvent());
      result.add(casted.getReceivingEnd());
      result.add(casted.getReceivingEnd().getEvent());
    }
    // InstanceRole: represented instance
    if (element instanceof InstanceRole) {
      InstanceRole casted = (InstanceRole)element;
      if (casted.getRepresentedInstance() != null) {
        result.add(casted.getRepresentedInstance());
      }
    }
    return Collections.unmodifiableList(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#getDefaultOptionalMergeFeatures()
   */
  public List<EStructuralFeature> getDefaultOptionalMergeFeatures() {
    return Collections.<EStructuralFeature>singletonList(
        ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#getElementsToRename(java.util.Collection)
   */
  public Collection<EObject> getElementsToRename(Collection<? extends EObject> elements) {
    Collection<EObject> candidates =Collections.unmodifiableCollection(elements);
    List<EObject> result = new FOrderedSet<EObject>(candidates, null);
    // If components and parts are synchronized, only rename components
    if (!candidates.isEmpty() && isSingletonComponentDriven(candidates.iterator().next())) {
      for (EObject candidate : candidates) {
        if (candidate instanceof Part) {
          Part part = (Part)candidate;
          AbstractType type = part.getAbstractType();
          if (type instanceof Component && result.contains(type)) {
            result.remove(candidate);
          }
        }
      }
    }
    return Collections.unmodifiableCollection(result);
  }
  
  /**
   * From the given non-null end of a link, return the associated element which
   * must be used for deriving a storage for the link
   * @param element the non-null end of a link/exchange
   * @return a non-null element
   */
  private EObject getLinkDerivationReferenceElement(EObject element) {
    EObject result;
    if (element instanceof ComponentExchangeEnd) {
      ComponentExchangeEnd end = (ComponentExchangeEnd)element;
      if (end.getPart() != null) {
        result = end.getPart();
      } else {
        result = end.getPort();
      }
    } else if (element instanceof PhysicalLinkEnd) {
      PhysicalLinkEnd end = (PhysicalLinkEnd)element;
      if (end.getPart() != null) {
        result = end.getPart();
      } else {
        result = end.getPort();
      }
    } else {
      result = element;
    }
    return result.eContainer();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ModellerSemanticRuleProvider#getNameAttribute(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public EAttribute getNameAttribute(EObject element) {
    EAttribute result;
    if (element instanceof AbstractNamedElement) {
      result = ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name();
    } else {
      result = super.getNameAttribute(element);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#getOptionalMergeFeatures()
   */
  public List<EStructuralFeature> getOptionalMergeFeatures() {
    return Arrays.<EStructuralFeature>asList(
        ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(),
        CapellacorePackage.eINSTANCE.getCapellaElement_Description(),
        CapellacorePackage.eINSTANCE.getCapellaElement_Summary());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#getRootsForPatternInclusion(org.eclipse.emf.ecore.EObject)
   */
  public Collection<EObject> getRootsForPatternInclusion(EObject context) {
    List<EObject> result = new FArrayList<EObject>();
    EObject root = EcoreUtil.getRootContainer(context);
    if (root instanceof Project) {
      result.addAll(((Project)root).getOwnedModelRoots());
    } else {
      result.add(root);
    }
    return Collections.unmodifiableCollection(result);
  }
  
  /**
   * Return whether the given element is meaningful when considered within
   * the given scope only, i.e., when separated from anything outside the scope
   * @param element a non-null element
   * @param scope a non-null scope
   */
  private boolean isMeaningfulWithin(EObject element, IModelScope scope) {
    if (element instanceof EnumerationPropertyValue) {
      return false;
    }
    // Other cases
    Collection<EObject> mustBeIncluded = new FOrderedSet<EObject>();
    // AbstractTrace: requires source element, target element
    if (element instanceof AbstractTrace) {
      AbstractTrace casted = (AbstractTrace)element;
      mustBeIncluded.add(casted.getSourceElement());
      mustBeIncluded.add(casted.getTargetElement());
    }
    // FunctionalExchange: source, target
    else if (element instanceof FunctionalExchange) {
      FunctionalExchange casted = (FunctionalExchange)element;
      mustBeIncluded.add(casted.getSource());
      mustBeIncluded.add(casted.getTarget());
    }
    // Connection: contents of the ends
    else if (element instanceof ComponentExchange) {
      ComponentExchange connection = (ComponentExchange)element;
      InformationsExchanger source = connection.getSource();
      if (source instanceof ComponentExchangeEnd) {
        mustBeIncluded.add(((ComponentExchangeEnd)source).getPart());
        mustBeIncluded.add(((ComponentExchangeEnd)source).getPort());
      } else {
        mustBeIncluded.add(source);
      }
      InformationsExchanger target = connection.getTarget();
      if (target instanceof ComponentExchangeEnd) {
        mustBeIncluded.add(((ComponentExchangeEnd)target).getPart());
        mustBeIncluded.add(((ComponentExchangeEnd)target).getPort());
      } else {
        mustBeIncluded.add(target);
      }
    }
    // PhysicalLink: contents of the ends
    else if (element instanceof PhysicalLink) {
      PhysicalLink physicalLink = (PhysicalLink)element;
      for (AbstractPhysicalLinkEnd end : physicalLink.getLinkEnds()) {
        if (end instanceof PhysicalLinkEnd) {
          mustBeIncluded.add(((PhysicalLinkEnd)end).getPart());
          mustBeIncluded.add(((PhysicalLinkEnd)end).getPort());
        } else {
          mustBeIncluded.add(end);
        }
      }
    }
    for (EObject current : mustBeIncluded) {
      if (current == null || !scope.covers(current)) {
        return false;
      }
    }
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#isMergeDependency(org.eclipse.emf.ecore.EObject)
   */
  public boolean isMergeDependency(EObject element) {
    return element instanceof DataType;
  }
  
  /**
   * Return whether components are singletons in the project of the given element
   * @param element a non-null EObject
   */
  private boolean isSingletonComponentDriven(EObject element) {
    boolean result = false;
    if (element instanceof ModelElement) {
      result = TriStateBoolean.True.equals(
          CapellaProjectHelper.isSingletonComponentsDriven(element));
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ModellerSemanticRuleProvider#getOwnershipDerivationLevel(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public int getOwnershipDerivationLevel(EObject element) {
    int result;
    if (element instanceof PhysicalLink) {
      result = 2; // Binds Ports, hence Components
    } else if (element instanceof ActivityEdge ||
        element instanceof ComponentExchange || // Binds Parts
        element instanceof Association ||
        element instanceof Component) { // Bound to a Part
      result = 1;
    } else {
      result = super.getOwnershipDerivationLevel(element);
    }
    return result;
  }
  
  /**
   * Behavior of adjustScopeNonRec in the reduction case
   * @param elements a non-null, potentially empty, unmodifiable set of roots
   *        of disjoint containment trees
   * @return whether the scope was modified
   */
  private boolean reduceScopeNonRec(FilteredModelScope scope) {
    List<EObject> toDelete = new FOrderedSet<EObject>();
    for (EObject root : scope.getContents()) {
      // Always keep roots
      TreeIterator<EObject> it = scope.getAllContents(root);
      while (it.hasNext()) {
        EObject current = it.next();
        if (!isMeaningfulWithin(current, scope)) {
          toDelete.add(current);
          it.prune();
        }
      }
    }
    for (EObject rootToDelete : ModelsUtil.getRoots(toDelete)) {
      scope.removeFromScope(rootToDelete, true);
    }
    return !toDelete.isEmpty();
  }
  
  /**
   * Return whether the given element supports the addition of the given value via
   * the given reference
   * @param element a non-null element
   * @param reference a non-null reference
   * @param value a non-null element
   * @param nonErasing whether the removal of existing values is allowed
   */
  @Override
  public boolean supportsAdditionOf(EObject element, EReference reference,
      EObject value, boolean nonErasing) {
    boolean result = super.supportsAdditionOf(element, reference, value, nonErasing);
    if (result && value instanceof Part) {
      // Parts
      Part part = (Part)value;
      Type type = part.getType();
      if (type instanceof SystemComponent) {
        result = element instanceof SystemComponent || element instanceof SystemComponentPkg;
      } else if (type instanceof LogicalComponent) {
        result = element instanceof LogicalComponent || element instanceof LogicalComponentPkg;
      } else if (type instanceof PhysicalComponent) {
        result = element instanceof PhysicalComponent || element instanceof PhysicalComponentPkg;
      }
      if (result && ComponentExt.isActor(type)) {
        result = element instanceof ComponentPkg || ComponentExt.isActor(element);
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ModellerSemanticRuleProvider#supportsAdditionOf(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EClass, boolean)
   */
  @Override
  protected boolean supportsAdditionOf(EObject element, EReference reference,
      EClass valueType, boolean nonErasing) {
    boolean result = !(reference == // Ignore ownedMigratedElements completely
        ModellingcorePackage.eINSTANCE.getModelElement_OwnedMigratedElements()) &&
        super.supportsAdditionOf(element, reference, valueType, nonErasing);
    if (result) {
      EClass elementClass = element.eClass();
      if (FaPackage.eINSTANCE.getAbstractFunction().isSuperTypeOf(valueType)) {
        // Function
        if (PaPackage.eINSTANCE.getPhysicalFunction().isSuperTypeOf(valueType)) {
          // Physical functions
          result = PaPackage.eINSTANCE.getPhysicalFunction().isSuperTypeOf(elementClass) ||
              PaPackage.eINSTANCE.getPhysicalFunctionPkg().isSuperTypeOf(elementClass);
        } else if (LaPackage.eINSTANCE.getLogicalFunction().isSuperTypeOf(valueType)) {
          // Logical functions
          result = LaPackage.eINSTANCE.getLogicalFunction().isSuperTypeOf(elementClass) ||
              LaPackage.eINSTANCE.getLogicalFunctionPkg().isSuperTypeOf(elementClass);
        } else if (CtxPackage.eINSTANCE.getSystemFunction().isSuperTypeOf(valueType)) {
          // System functions
          result = CtxPackage.eINSTANCE.getSystemFunction().isSuperTypeOf(elementClass) ||
              CtxPackage.eINSTANCE.getSystemFunctionPkg().isSuperTypeOf(elementClass);
        } else if (OaPackage.eINSTANCE.getOperationalActivity().isSuperTypeOf(valueType)) {
          // Operational activity
          result = OaPackage.eINSTANCE.getOperationalActivity().isSuperTypeOf(elementClass) ||
              OaPackage.eINSTANCE.getOperationalActivityPkg().isSuperTypeOf(elementClass);
        }
      }
    }
    return result;
  }
  
  /**
   * Return whether the given reference location supports the addition of the given value
   * @param location a non-null, well-formed reference location
   * @param value a non-null element
   */
  private boolean supportsAdditionOf(IReferenceLocation location, EObject value) {
    return supportsAdditionOf(location.getElement(), location.getReference(), value, true);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ModellerSemanticRuleProvider#isDependency(org.eclipse.emf.ecore.EReference)
   */
  @Override
  public boolean isDependency(EReference reference) {
    return !reference.isDerived() && !NON_DEPENDENCY_REFERENCES.contains(reference);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ModellerSemanticRuleProvider#isApplicableTo(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean isApplicableTo(EObject obj){
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#canBeAutomaticallyMerged(org.eclipse.emf.ecore.EObject)
   */
  public boolean canBeAutomaticallyMerged(EObject element) {
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#getAutomaticMergeTarget(org.eclipse.emf.ecore.EObject, java.lang.Object)
   */
  public EObject getAutomaticMergeTarget(EObject element, Object targetScope) {
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#getPrefixText(org.eclipse.emf.ecore.EObject)
   */
  public String getPrefixText(EObject element) {
    return ""; //$NON-NLS-1$
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#initializeTargetScope(org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope, org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope)
   */
  public void initializeTargetScope(IFeaturedModelScope referenceScope,
      IFeaturedModelScope targetScope){
    // Nothing

  }
  
  /**
   * Only a Capella Project can be a root element in his resource.
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#isAllowedToBeRoot(org.eclipse.emf.ecore.EObject)
   */
  public boolean isAllowedToBeRoot(EObject obj){
    if (obj instanceof Project) {
      return true;
    }
    return false;
  }
  
  /**
   * Says if the given diagram is of a type that is automatically redrawn. 
   * When true is returned the layout reuse is not executed on the given diagram after pattern application.
   * See PatternApplicationWizard#doPerformFinish
   */
  public boolean isAutomaticallyUpdatedDiagram(Object diagram) {
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#postPatternApplication(org.eclipse.emf.diffmerge.patterns.core.api.IPatternApplication, java.util.Collection, java.util.Collection)
   */
  public void postPatternApplication(IPatternApplication application,
      Collection<EObject> additions, Collection<IDifference> merges) {
    if (!additions.isEmpty()) {
      TransactionalEditingDomain domain = TransactionHelper.getEditingDomain(
          additions.iterator().next());
      HoldingResourceHelper.flushHoldingResource(domain);
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#reset()
   */
  public void reset() {
    perTypeLocations.clear();
    predefinedLocations.clear(); 
  }
  
}