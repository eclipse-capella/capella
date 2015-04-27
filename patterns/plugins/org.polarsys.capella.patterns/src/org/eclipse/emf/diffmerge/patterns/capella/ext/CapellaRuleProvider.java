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
import org.eclipse.emf.diffmerge.util.ModelsUtil;
import org.eclipse.emf.diffmerge.util.structures.FArrayList;
import org.eclipse.emf.diffmerge.util.structures.FOrderedSet;
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
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemContext;
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
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalContext;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalContext;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;


/**
 * An implementation of a semantic rule provider for Capella models.
 */
public class CapellaRuleProvider extends ModellerSemanticRuleProvider {

  /** Saves storage by prompt locations when "Apply for all Similar Elements" is selected */
  private final Map<EClass, IReferenceLocation> _perTypeLocations;

  /** Saves storage by prompt locations when "Apply for all Compatible Elements" is selected */
  private final Collection<IReferenceLocation> _predefinedLocations;
  
  
  /**
   * Constructor
   */
  public CapellaRuleProvider(){
    _perTypeLocations = new HashMap<EClass, IReferenceLocation>();
    _predefinedLocations = new HashSet<IReferenceLocation>(); 
  }
  
  /** The set of references that must not be considered for dependencies */
  protected static final List<EReference> NON_DEPENDENCY_REFERENCES = Arrays.asList(
      ModellingcorePackage.eINSTANCE.getAbstractType_AbstractTypedElements(),
      CapellacorePackage.eINSTANCE.getType_TypedElements());
  
  /**
   * 
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#adjustScope(org.eclipse.emf.diffmerge.impl.scopes.FilteredModelScope, boolean)
   */
  public void adjustScope(FilteredModelScope scope_p, boolean extend_p) {
    boolean changed;
    do {
      changed = adjustScopeNonRec(scope_p, extend_p);
    } while (changed); // Reach fix point
  }

  /**
   * Non-recursive core behavior of adjustScope (may not reach a fix point)
   * @param scope_p a non-null scope
   * @return whether the scope was modified
   */
  private boolean adjustScopeNonRec(FilteredModelScope scope_p,
      boolean extend_p) {
    return extend_p? extendScopeNonRec(scope_p): reduceScopeNonRec(scope_p);
  }

  /**
   * Return whether the given object is concerned by the receiver
   * @param context_p a non-null object, typically resource or model element
   */
  public boolean appliesTo(Object context_p) {
    boolean result = context_p instanceof CapellaElement;
    if (!result) {
      Resource resource = null;
      if (context_p instanceof Resource)
        resource = (Resource)context_p;
      else if (context_p instanceof EObject)
        resource = ((EObject)context_p).eResource();
      result = CapellaResourceHelper.isCapellaResource(resource);
    }
    return result;
  }

  /**
   * Helper method for deriveOwnership: case where container is derived from the ends
   * of a link/exchange
   * @param source_p a non-null element which is the end of a link/exchange
   * @param target_p a non-null element which is the end of a link/exchange
   * @return the computed container, if any
   */
  private EObject deriveLinkContainer(EObject source_p, EObject target_p) {
    EObject result = null;
    EObject sourceElement = getLinkDerivationReferenceElement(source_p);
    EObject targetElement = getLinkDerivationReferenceElement(target_p);
    if (sourceElement != null && targetElement != null)
      result = ModelsUtil.getCommonAncestor(sourceElement, targetElement);
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ModellerSemanticRuleProvider#deriveOwnership(org.eclipse.emf.ecore.EObject, java.lang.Object)
   */
  @Override
  public boolean deriveOwnership(EObject element_p, Object context_p) {
    boolean result = super.deriveOwnership(element_p, context_p);
    if (!result) {
      EObject container = null;
      EReference containment = null;
      // ActivityEdge
      if (element_p instanceof ActivityEdge) {
        ActivityEdge casted = (ActivityEdge)element_p;
        if (casted.getSource() != null && casted.getTarget() != null) {
          container = deriveLinkContainer(casted.getSource(), casted.getTarget());
          if (container instanceof AbstractFunction)
            containment = FaPackage.eINSTANCE.getAbstractFunction_OwnedFunctionalExchanges();
        }
      }
      // Connections
      else if (element_p instanceof ComponentExchange) {
        ComponentExchange casted = (ComponentExchange)element_p;
        container = deriveLinkContainer(casted.getSource(), casted.getTarget());
        if (container instanceof Component)
          containment = FaPackage.eINSTANCE.getAbstractFunctionalBlock_OwnedComponentExchanges();
      }
      // PhysicalLinks
      else if (element_p instanceof PhysicalLink) {
        PhysicalLink exchange = (PhysicalLink)element_p;
        List<? extends EObject> ends = exchange.getLinkEnds();
        if (2 == ends.size()) {
          container = deriveLinkContainer(ends.get(0), ends.get(1));
          if (container instanceof Component)
            containment = CsPackage.eINSTANCE.getComponent_OwnedPhysicalLinks();
        }
      }
      // Association
      else if (element_p instanceof Association) {
        Association casted = (Association)element_p;
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
      else if (element_p instanceof AbstractType) {
        AbstractType casted = (AbstractType)element_p;
        List<AbstractTypedElement> typedElements = Collections.emptyList();
        try {
          TransactionalEditingDomain domain = TransactionHelper.getEditingDomain((EObject)context_p);
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
              getReferencesForAddition(container, element_p.eClass(), true, true);
          if (containments.size() == 1)
            containment = containments.get(0);
        }
      }
      if (null != container && null != containment) {
        IReferenceLocation location =
            new BasicReferenceLocation(container, containment);
        try {
          LocationsUtil.add(location, element_p);
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
  public Boolean enforceOwnership(Collection<? extends EObject> roots_p, Object context_p) {
    EList<EObject> derivables = new FOrderedSet<EObject>();
    // Prompt for ownership of non-derivables, remember derivables
    for (EObject root : roots_p) {
      if (root.eContainer() == null) {
        if (ownershipMightBeDerived(root))
          derivables.add(root);
        else {
          Boolean success = enforceOwnershipByPrompt(
              root, context_p, true, _perTypeLocations, _predefinedLocations);
          if (!Boolean.TRUE.equals(success)) return success;
        }
      }
    }
    // Put physical links last because they depend on types which
    // may themselves be derived
    int lastPos = derivables.size() - 1;
    for (EObject derivable : new FArrayList<EObject>(derivables, null)) {
      if (derivable instanceof PhysicalLink)
        derivables.move(lastPos, derivable);
    }
    // Try and derive ownerships
    for (EObject derivable : derivables) {
      boolean derived = deriveOwnership(derivable, context_p);
      if (!derived) {
        Boolean success = enforceOwnershipByPrompt(
            derivable, context_p, true, _perTypeLocations, _predefinedLocations);
        if (!Boolean.TRUE.equals(success)) return success;
      }
    }
    return Boolean.TRUE;
  }
  
  /**
   * Enforce ownership of the given element by prompting the user or using former prompts
   * @param element_p a non-null element
   * @param context_p a non-null object, typically resource or model element
   * @param allowCancel_p whether cancellation by the user is allowed
   * @param perTypeLocations_p a non-null, modifiable map that registers reference locations for given types
   * @param predefinedLocations_p a non-null, modifiable collection of reference locations to be tried
   * @return whether the operation succeeded, or null for canceled
   */
  private Boolean enforceOwnershipByPrompt(EObject element_p, Object context_p, boolean allowCancel_p,
      Map<EClass, IReferenceLocation> perTypeLocations_p,
      Collection<IReferenceLocation> predefinedLocations_p) {
    Boolean result = Boolean.TRUE;
    boolean done = false;
    // Trying predefined per-type locations
    IReferenceLocation registeredLocation = perTypeLocations_p.get(element_p.eClass());
    if (registeredLocation != null && supportsAdditionOf(registeredLocation, element_p)) {
      LocationsUtil.add(registeredLocation, element_p);
      done = true;
    } else {
      // Trying predefined general locations
      Iterator<IReferenceLocation> it = predefinedLocations_p.iterator();
      while (it.hasNext() && !done) {
        IReferenceLocation predefinedLocation = it.next();
        if (supportsAdditionOf(predefinedLocation, element_p)) {
          LocationsUtil.add(predefinedLocation, element_p);
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
          context_p, Collections.singleton(element_p));
      if (candidateContainers.size() == 1) {
        EObject container = candidateContainers.get(0);
        List<EReference> containments =
            getReferencesForElementAddition(container, element_p, true, true);
        if (containments.size() == 1) {
          EReference containment = containments.get(0);
          location = new BasicReferenceLocation(container, containment);
        }
      }
      if (location == null) {
        MultiStorageChoiceDialog dialog = new MultiStorageChoiceDialog(
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
            null, candidateContainers, element_p);
        int answer = dialog.open();
        proceed = Window.OK == answer;
        if (proceed) {
          location = dialog.getChoice();
          storageKind = dialog.getStorageKind();
        }
      }
      if (proceed) {
        if (location != null) {
          LocationsUtil.add(location, element_p);
          done = true;
          switch (storageKind) {
          case ALL_SIMILAR:
            perTypeLocations_p.put(element_p.eClass(), location); break;
          case ALL_COMPATIBLE:
            predefinedLocations_p.add(location); break;
          default: // Nothing
          }
        }
      } else {
        // Canceled
        if (allowCancel_p)
          return null;
      }
    }
    return result;
  }
  
  /**
   * Return the references which support the addition of the given value
   * on the given element
   * @param element_p a non-null element
   * @param value_p a non-null element
   * @param nonErasing_p whether the removal of existing values is allowed
   * @param containmentOnly_p whether only containment references must be considered
   * @return a non-null, potentially empty, unmodifiable list
   */
  private List<EReference> getReferencesForElementAddition(EObject element_p,
      EObject value_p, boolean nonErasing_p, boolean containmentOnly_p) {
    List<EReference> result = new ArrayList<EReference>();
    List<EReference> candidates;
    if (containmentOnly_p)
      candidates = element_p.eClass().getEAllContainments();
    else
      candidates = element_p.eClass().getEAllReferences();
    for (EReference candidate : candidates) {
      if (supportsAdditionOf(element_p, candidate, value_p, nonErasing_p))
        result.add(candidate);
    }
    return Collections.unmodifiableList(result);
  }
  
  /**
   * Behavior of adjustScopeNonRec in the extend case
   * @param scope_p a non-null scope
   * @return whether the scope was modified
   */
  private boolean extendScopeNonRec(FilteredModelScope scope_p) {
    List<EObject> toAdd = new FOrderedSet<EObject>();
    for (EObject root : scope_p.getContents()) {
      toAdd.addAll(getAdditionalRelevantElements(root, scope_p));
      Iterator<EObject> it = scope_p.getAllContents(root);
      while (it.hasNext()) {
        EObject current = it.next();
        toAdd.addAll(getAdditionalRelevantElements(current, scope_p));
      }
    }
    boolean result = false;
    for (EObject rootToAdd : ModelsUtil.getRoots(toAdd)) {
      if (!scope_p.covers(rootToAdd)) {
        scope_p.add(rootToAdd, true);
        result = true;
      }
    }
    return result;
  }
  
  /**
   * Return the set of elements which should be present in the given scope for the
   * given element to make sense 
   * @param element_p a non-null element
   * @param scope_p a non-null scope
   * @return a non-null, potentially empty, unmodifiable collection
   */
  private Collection<? extends EObject> getAdditionalRelevantElements(EObject element_p,
      IModelScope scope_p) {
    List<EObject> result = new FOrderedSet<EObject>();
    // AbstractTypedElement: type
    if (element_p instanceof AbstractTypedElement) {
      AbstractTypedElement casted = (AbstractTypedElement)element_p;
      if (casted.getAbstractType() != null)
        result.add(casted.getAbstractType());
    }
    // Allocation: target element
    if (element_p instanceof Allocation) {
      Allocation casted = (Allocation)element_p;
      if (casted.getTargetElement() != null)
        result.add(casted.getTargetElement());
    }
    // Association: navigable members
    if (element_p instanceof Association) {
      Association casted = (Association)element_p;
      result.addAll(casted.getNavigableMembers());
    }
    // ActivityNode: outgoing/incoming within the scope
    if (element_p instanceof ActivityNode) {
      ActivityNode casted = (ActivityNode)element_p;
      // Incoming
      for (ActivityEdge incoming : casted.getIncoming()) {
        if (scope_p.covers(incoming.getSource()))
          result.add(incoming);
      }
      // Outgoing
      for (ActivityEdge outgoing : casted.getOutgoing()) {
        if (scope_p.covers(outgoing.getTarget()))
          result.add(outgoing);
      }
    }
    // ComponentPort: connections within the scope
    if (element_p instanceof ComponentPort) {
      ComponentPort casted = (ComponentPort)element_p;
      for (AbstractInformationFlow flow : casted.getInformationFlows()) {
        if (scope_p.covers(flow.getSource()) && scope_p.covers(flow.getTarget()))
          result.add(flow);
      }
    }
    // PhysicalPort: physical links within the scope
    if (element_p instanceof PhysicalPort) {
      PhysicalPort casted = (PhysicalPort)element_p;
      for (PhysicalLink link : casted.getInvolvedLinks()) {
        List<AbstractPhysicalLinkEnd> remaining =
            new FOrderedSet<AbstractPhysicalLinkEnd>(link.getLinkEnds(), null);
        remaining.remove(casted);
        if (!remaining.isEmpty() && scope_p.covers(remaining.get(0)))
          result.add(link);
      }
    }
    // SequenceMessage: ends
    if (element_p instanceof SequenceMessage) {
      SequenceMessage casted = (SequenceMessage)element_p;
      result.add(casted.getSendingEnd());
      result.add(casted.getSendingEnd().getEvent());
      result.add(casted.getReceivingEnd());
      result.add(casted.getReceivingEnd().getEvent());
    }
    // InstanceRole: represented instance
    if (element_p instanceof InstanceRole) {
      InstanceRole casted = (InstanceRole)element_p;
      if (casted.getRepresentedInstance() != null)
        result.add(casted.getRepresentedInstance());
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
  public Collection<EObject> getElementsToRename(Collection<? extends EObject> elements_p) {
    Collection<EObject> candidates =Collections.unmodifiableCollection(elements_p);
    List<EObject> result = new FOrderedSet<EObject>(candidates, null);
    // If components and parts are synchronized, only rename components
    if (!candidates.isEmpty() && isSingletonComponentDriven(candidates.iterator().next())) {
      for (EObject candidate : candidates) {
        if (candidate instanceof Part) {
          Part part = (Part)candidate;
          AbstractType type = part.getAbstractType();
          if (type instanceof Component && result.contains(type))
            result.remove(candidate);
        }
      }
    }
    return Collections.unmodifiableCollection(result);
  }
  
  /**
   * From the given non-null end of a link, return the associated element which
   * must be used for deriving a storage for the link
   * @param element_p the non-null end of a link/exchange
   * @return a non-null element
   */
  private EObject getLinkDerivationReferenceElement(EObject element_p) {
    EObject result;
    if (element_p instanceof ComponentExchangeEnd) {
      ComponentExchangeEnd end = (ComponentExchangeEnd)element_p;
      if (end.getPart() != null)
        result = end.getPart();
      else
        result = end.getPort();
    } else if (element_p instanceof PhysicalLinkEnd) {
      PhysicalLinkEnd end = (PhysicalLinkEnd)element_p;
      if (end.getPart() != null)
        result = end.getPart();
      else
        result = end.getPort();
    } else {
      result = element_p;
    }
    return result.eContainer();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ModellerSemanticRuleProvider#getNameAttribute(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public EAttribute getNameAttribute(EObject element_p) {
    EAttribute result;
    if (element_p instanceof AbstractNamedElement)
      result = ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name();
    else
      result = super.getNameAttribute(element_p);
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
  public Collection<EObject> getRootsForPatternInclusion(EObject context_p) {
    List<EObject> result = new FArrayList<EObject>();
    EObject root = EcoreUtil.getRootContainer(context_p);
    if (root instanceof Project)
      result.addAll(((Project)root).getOwnedModelRoots());
    else
      result.add(root);
    return Collections.unmodifiableCollection(result);
  }
  
  /**
   * Return whether the given element is meaningful when considered within
   * the given scope only, i.e., when separated from anything outside the scope
   * @param element_p a non-null element
   * @param scope_p a non-null scope
   */
  private boolean isMeaningfulWithin(EObject element_p, IModelScope scope_p) {
    if (element_p instanceof EnumerationPropertyValue)
      return false;
    // Other cases
    Collection<EObject> mustBeIncluded = new FOrderedSet<EObject>();
    // AbstractTrace: requires source element, target element
    if (element_p instanceof AbstractTrace) {
      AbstractTrace casted = (AbstractTrace)element_p;
      mustBeIncluded.add(casted.getSourceElement());
      mustBeIncluded.add(casted.getTargetElement());
    }
    // FunctionalExchange: source, target
    else if (element_p instanceof FunctionalExchange) {
      FunctionalExchange casted = (FunctionalExchange)element_p;
      mustBeIncluded.add(casted.getSource());
      mustBeIncluded.add(casted.getTarget());
    }
    // Connection: contents of the ends
    else if (element_p instanceof ComponentExchange) {
      ComponentExchange connection = (ComponentExchange)element_p;
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
    else if (element_p instanceof PhysicalLink) {
      PhysicalLink physicalLink = (PhysicalLink)element_p;
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
      if (current == null || !scope_p.covers(current))
        return false;
    }
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#isMergeDependency(org.eclipse.emf.ecore.EObject)
   */
  public boolean isMergeDependency(EObject element_p) {
    return element_p instanceof DataType;
  }
  
  /**
   * Return whether components are singletons in the project of the given element
   * @param element_p a non-null EObject
   */
  private boolean isSingletonComponentDriven(EObject element_p) {
    boolean result = false;
    if (element_p instanceof ModelElement)
      result = TriStateBoolean.True.equals(
          CapellaProjectHelper.isSingletonComponentsDriven(element_p));
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.SemanticRuleProvider#ownershipMightBeDerived(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean ownershipMightBeDerived(EObject element_p) {
    return super.ownershipMightBeDerived(element_p) ||
        element_p instanceof ActivityEdge ||
        element_p instanceof ComponentExchange ||
        element_p instanceof PhysicalLink ||
        element_p instanceof Association ||
        element_p instanceof Component;
  }
  
  /**
   * Behavior of adjustScopeNonRec in the reduction case
   * @param elements_p a non-null, potentially empty, unmodifiable set of roots
   *        of disjoint containment trees
   * @return whether the scope was modified
   */
  private boolean reduceScopeNonRec(FilteredModelScope scope_p) {
    List<EObject> toDelete = new FOrderedSet<EObject>();
    for (EObject root : scope_p.getContents()) {
      // Always keep roots
      TreeIterator<EObject> it = scope_p.getAllContents(root);
      while (it.hasNext()) {
        EObject current = it.next();
        if (!isMeaningfulWithin(current, scope_p)) {
          toDelete.add(current);
          it.prune();
        }
      }
    }
    for (EObject rootToDelete : ModelsUtil.getRoots(toDelete)) {
      scope_p.removeFromScope(rootToDelete, true);
    }
    return !toDelete.isEmpty();
  }
  
  /**
   * Return whether the given element supports the addition of the given value via
   * the given reference
   * @param element_p a non-null element
   * @param reference_p a non-null reference
   * @param value_p a non-null element
   * @param nonErasing_p whether the removal of existing values is allowed
   */
  @Override
  public boolean supportsAdditionOf(EObject element_p, EReference reference_p,
      EObject value_p, boolean nonErasing_p) {
    boolean result = super.supportsAdditionOf(element_p, reference_p, value_p, nonErasing_p);
    if (result && value_p instanceof Part) {
      // Parts
      Part part = (Part)value_p;
      Type type = part.getType();
      if (type instanceof AbstractActor) {
        if (type instanceof Actor)
          result = element_p instanceof SystemContext;
        else if (type instanceof LogicalActor)
          result = element_p instanceof LogicalContext;
        else
          result = element_p instanceof PhysicalContext;
      } else if (type instanceof LogicalComponent) {
        result = element_p instanceof LogicalContext ||
            element_p instanceof LogicalComponent;
      } else if (type instanceof PhysicalComponent) {
        result = element_p instanceof PhysicalContext ||
            element_p instanceof PhysicalComponent;
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ModellerSemanticRuleProvider#supportsAdditionOf(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EClass, boolean)
   */
  @Override
  protected boolean supportsAdditionOf(EObject element_p, EReference reference_p,
      EClass valueType_p, boolean nonErasing_p) {
    boolean result = super.supportsAdditionOf(element_p, reference_p, valueType_p, nonErasing_p);
    if (result) {
      EClass elementClass = element_p.eClass();
      if (PaPackage.eINSTANCE.getPhysicalFunction().isSuperTypeOf(valueType_p))
        result = PaPackage.eINSTANCE.getPhysicalFunction().isSuperTypeOf(elementClass) ||
        PaPackage.eINSTANCE.getPhysicalFunctionPkg().isSuperTypeOf(elementClass);
      else if (LaPackage.eINSTANCE.getLogicalFunction().isSuperTypeOf(valueType_p))
        result = LaPackage.eINSTANCE.getLogicalFunction().isSuperTypeOf(elementClass) ||
        LaPackage.eINSTANCE.getLogicalFunctionPkg().isSuperTypeOf(elementClass);
      else if (CtxPackage.eINSTANCE.getSystemFunction().isSuperTypeOf(valueType_p))
        result = CtxPackage.eINSTANCE.getSystemFunction().isSuperTypeOf(elementClass) ||
        CtxPackage.eINSTANCE.getSystemFunctionPkg().isSuperTypeOf(elementClass);
    }
    return result;
  }
  
  /**
   * Return whether the given reference location supports the addition of the given value
   * @param location_p a non-null, well-formed reference location
   * @param value_p a non-null element
   */
  private boolean supportsAdditionOf(IReferenceLocation location_p, EObject value_p) {
    return supportsAdditionOf(location_p.getElement(), location_p.getReference(), value_p, true);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ModellerSemanticRuleProvider#isDependency(org.eclipse.emf.ecore.EReference)
   */
  @Override
  public boolean isDependency(EReference reference_p) {
    return !reference_p.isDerived() && !NON_DEPENDENCY_REFERENCES.contains(reference_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.SemanticRuleProvider#isApplicableTo(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean isApplicableTo(EObject obj_p){
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#canBeAutomaticallyMerged(org.eclipse.emf.ecore.EObject)
   */
  public boolean canBeAutomaticallyMerged(EObject element_p) {
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#getAutomaticMergeTarget(org.eclipse.emf.ecore.EObject, java.lang.Object)
   */
  public EObject getAutomaticMergeTarget(EObject element_p, Object targetScope_p) {
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#getPrefixText(org.eclipse.emf.ecore.EObject)
   */
  public String getPrefixText(EObject element_p) {
    return ""; //$NON-NLS-1$
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#initializeTargetScope(org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope, org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope)
   */
  public void initializeTargetScope(IFeaturedModelScope referenceScope_p,
      IFeaturedModelScope targetScope_p){
    // Nothing

  }
  
  /**
   * Only a Capella Project can be a root element in his resource.
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#isAllowedToBeRoot(org.eclipse.emf.ecore.EObject)
   */
  public boolean isAllowedToBeRoot(EObject obj_p){
    if(obj_p instanceof Project)
      return true;
    return false;
  }
  
  /**
   * Says if the given diagram is of a type that is automatically redrawn. 
   * When true is returned the layout reuse is not executed on the given diagram after pattern application.
   * See PatternApplicationWizard#doPerformFinish
   */
  public boolean isAutomaticallyUpdatedDiagram(Object diagram_p) {
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#postPatternApplication(org.eclipse.emf.diffmerge.patterns.core.api.IPatternApplication, java.util.Collection, java.util.Collection)
   */
  public void postPatternApplication(IPatternApplication application_p,
      Collection<EObject> additions_p, Collection<IDifference> merges_p) {
    if (!additions_p.isEmpty()) {
      TransactionalEditingDomain domain = TransactionHelper.getEditingDomain(
          additions_p.iterator().next());
      HoldingResourceHelper.flushHoldingResource(domain);
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider#reset()
   */
  public void reset() {
    _perTypeLocations.clear();
    _predefinedLocations.clear(); 
  }
  
}