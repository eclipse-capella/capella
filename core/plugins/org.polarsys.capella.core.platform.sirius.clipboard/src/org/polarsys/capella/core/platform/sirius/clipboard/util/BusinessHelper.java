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
package org.polarsys.capella.core.platform.sirius.clipboard.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.deployment.DeploymentFactory;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.preferences.helpers.PreferencesHelper;

/**
 * This class defines business-specific rules for managing Capella models
 */
public class BusinessHelper {

  // Singleton design pattern
  private static final BusinessHelper INSTANCE = new BusinessHelper();

  public static BusinessHelper getInstance() {
    return INSTANCE;
  }

  private BusinessHelper() {
    // Forbids instantiation
  }

  /**
   * Create semantic elements which are implicitly coupled with the given semantic element in the context of its given
   * semantic context and representation context
   */
  public Collection<EObject> addImplicitElements(EObject element, Object context, DSemanticDecorator graphicalContext) {
    Collection<EObject> result = new HashSet<>();
    EObject container = element.eContainer();
    EObject graphicalTarget = null;
    if (graphicalContext != null) {
      graphicalTarget = graphicalContext.getTarget();
    }
    if ((container instanceof Component) && (element instanceof Component)) {
      // Whatever the diagram, the addition of a Component within another one
      // also triggers the creation of a Part if not present
      Component ownerC = (Component) container;
      Component eltC = (Component) element;
      if (eltC.getAbstractTypedElements().isEmpty()) {
        Part addition = createPartFor(eltC);
        ownerC.getOwnedFeatures().add(addition);
        result.add(addition);
        // Special case: in PABs, behavioral component within a node component
        // also requires a deployment link
        if ((graphicalTarget instanceof Part) && (container instanceof PhysicalComponent)
            && (container.eContainer() instanceof PhysicalComponent) && // container is not a root
            (element instanceof PhysicalComponent)
            && (((PhysicalComponent) container).getNature() == PhysicalComponentNature.NODE)
            && (((PhysicalComponent) element).getNature() == PhysicalComponentNature.BEHAVIOR)) {
          deployPhysicalPartOn(addition, (Part) graphicalTarget);
        }
      }
    } else if ((element instanceof Part) && (graphicalTarget instanceof Part)
        && (((Part) element).getAbstractType() instanceof PhysicalComponent)
        && (((Part) graphicalTarget).getAbstractType() instanceof PhysicalComponent)
        && (((PhysicalComponent) ((Part) element).getAbstractType()).getNature() == PhysicalComponentNature.BEHAVIOR)
        && (((PhysicalComponent) ((Part) graphicalTarget).getAbstractType())
            .getNature() == PhysicalComponentNature.NODE)) {
      EList<Part> deployingParts = ((Part) element).getDeployingParts();
      boolean isAllocated = !deployingParts.isEmpty();
      if (!isAllocated)
        deployPhysicalPartOn((Part) element, (Part) graphicalTarget);
    } else if ((element instanceof AbstractFunction)
        && ((context instanceof Component) || (graphicalTarget instanceof Part))) {
      // A function within a component triggers the creation of an allocation
      // if there exists none
      AbstractFunction function = (AbstractFunction) element;
      Component component = null;
      if (context instanceof Component) {
        component = (Component) context;
      } else {
        Part part = (Part) graphicalTarget;
        if (part != null && part.getAbstractType() instanceof Component) {
          component = (Component) part.getAbstractType();
        }
      }
      boolean mustCreate = component != null;
      if (mustCreate) {
        BlockArchitecture functionArchi = getBlockArchitecture(function);
        BlockArchitecture componentArchi = getBlockArchitecture(component);
        if ((functionArchi != null) && (componentArchi != null)) {
          mustCreate = functionArchi.eClass() == componentArchi.eClass();
        }
      }
      if (mustCreate && (component instanceof PhysicalComponent)) {
        PhysicalComponent pc = (PhysicalComponent) component;
        mustCreate = pc.getNature() != PhysicalComponentNature.NODE;
      }
      if (mustCreate) {
        for (ComponentFunctionalAllocation existingAlloc : function.getComponentFunctionalAllocations()) {
          if (existingAlloc.getBlock() == component) {
            mustCreate = false;
            break;
          }
        }
        EList<AbstractFunctionalBlock> allocationBlocks = function.getAllocationBlocks();
        boolean isAllocated = !allocationBlocks.isEmpty();
        if (mustCreate && !isAllocated) {
          ComponentFunctionalAllocation allocation = FaFactory.eINSTANCE.createComponentFunctionalAllocation();
          MiscUtil.setNewId(allocation);
          allocation.setSourceElement(component);
          allocation.setTargetElement(function);
          component.getOwnedFunctionalAllocation().add(allocation);
          result.add(allocation);
        }
      }
    } else if ((element instanceof EnumerationLiteral) && (context instanceof Enumeration)) {
      ((EnumerationLiteral) element).setAbstractType((AbstractType) context);
    }
    return result;
  }

  /**
   * Deploy the given part on the given location part
   * 
   * @param element
   *          a non-null part typed by a behavior physical component
   * @param location
   *          a non-null part typed by a node physical component
   */
  private void deployPhysicalPartOn(Part element, Part location) {
    PartDeploymentLink link = DeploymentFactory.eINSTANCE.createPartDeploymentLink();
    MiscUtil.setNewId(link);
    link.setLocation(location);
    link.setDeployedElement(element);
    location.getOwnedDeploymentLinks().add(link);
  }

  /**
   * Create and return a Part typed by the given non-null type
   */
  private Part createPartFor(AbstractType type) {
    Part result = CsFactory.eINSTANCE.createPart();
    MiscUtil.setNewId(result);
    result.setName(type.getName());
    LiteralNumericValue minCard = DatavalueFactory.eINSTANCE.createLiteralNumericValue("minCard"); //$NON-NLS-1$
    minCard.setValue("1"); //$NON-NLS-1$
    result.setOwnedMinCard(minCard);
    LiteralNumericValue maxCard = DatavalueFactory.eINSTANCE.createLiteralNumericValue("maxCard"); //$NON-NLS-1$
    maxCard.setValue("1"); //$NON-NLS-1$
    result.setOwnedMaxCard(maxCard);
    result.setAbstractType(type);
    return result;
  }

  /**
   * Return semantic elements which are implicitly coupled with the given semantic element in the context of their given
   * representation context
   */
  public Set<EObject> getImplicitElements(EObject element, Object context) {
    Set<EObject> result = new HashSet<>();
    if ((element instanceof Part) && !isMultipartAllowed(element)) {
      // Whatever the diagram, parts are always coupled with their
      // type if there is a unique part per type
      result.add(((Part) element).getAbstractType());
    } else if ((element instanceof Component) && !isMultipartAllowed(element)) {
      List<AbstractTypedElement> typedElements = ((Component) element).getAbstractTypedElements();
      if ((typedElements.size() == 1) && (typedElements.get(0) instanceof Part)) {
        result.add(typedElements.get(0));
      }
    } else if (element instanceof Association) {
      // Whatever the diagram, Associations are coupled with their
      // navigable members, which they may not own
      result.addAll(((Association) element).getNavigableMembers());
    } else if (element instanceof SequenceMessage) {
      // Whatever the diagram, SequenceMessages are coupled with their ends
      SequenceMessage msg = (SequenceMessage) element;
      result.add(msg.getSendingEnd());
      result.add(msg.getSendingEnd().getEvent());
      result.add(msg.getReceivingEnd());
      result.add(msg.getReceivingEnd().getEvent());
    } else if (element instanceof Execution) {
      // Whatever the diagram, Executions are coupled with their ends
      Execution msg = (Execution) element;
      result.add(msg.getStart());
      if (msg.getStart() instanceof AbstractEnd) {
        result.add(((AbstractEnd) msg.getStart()).getEvent());
      }
      result.add(msg.getFinish());
      if (msg.getFinish() instanceof AbstractEnd) {
        result.add(((AbstractEnd) msg.getFinish()).getEvent());
      }
    } else if (element instanceof InstanceRole) {
      // Whatever the diagram, InstanceRoles are coupled with their
      // represented instance
      result.add(((InstanceRole) element).getRepresentedInstance());
    }
    return result;
  }

  /**
   * Generalization of getImplicitElements to collections
   */
  public final Set<EObject> getImplicitElements(Collection<? extends EObject> elements, Object context) {
    Set<EObject> result = new HashSet<>();
    for (EObject element : elements) {
      result.addAll(getImplicitElements(element, context));
    }
    return result;
  }

  /**
   * Return whether the given reference with isMany==true has a fixed multiplicity and should therefore not be used for
   * adding elements
   */
  private boolean hasFixedCardinality(EReference reference) {
    return (CsPackage.eINSTANCE.getPhysicalLink_LinkEnds() == reference)
        || (InformationPackage.eINSTANCE.getAssociation_NavigableMembers() == reference);
  }

  /**
   * Return whether the given element is meaningful when considered within the given set of elements or their children
   * only, i.e., when separated from anything outside those elements.
   */
  public boolean isMeaningfulWithin(EObject element, Collection<? extends EObject> contexts) {
    // EnumerationPropertyValues never meaningful for the moment
    if (element instanceof EnumerationPropertyValue) {
      return false;
    }
    // Other cases
    Collection<EObject> mustBeIncluded = new ArrayList<>();
    if (element instanceof AbstractTrace) {
      // Traces and allocations require their source and target to be present
      AbstractTrace abstractTrace = (AbstractTrace) element;
      mustBeIncluded.add(abstractTrace.getSourceElement());
      mustBeIncluded.add(abstractTrace.getTargetElement());
    } else if (element instanceof FunctionalExchange) {
      // Same for FunctionalExchange
      FunctionalExchange exchange = (FunctionalExchange) element;
      mustBeIncluded.add(exchange.getSource());
      mustBeIncluded.add(exchange.getTarget());
    } else if (element instanceof ComponentExchange) {
      // For connections, check for the contents of the ends
      ComponentExchange connection = (ComponentExchange) element;
      InformationsExchanger source = connection.getSource();
      if (source instanceof ComponentExchangeEnd) {
        mustBeIncluded.add(((ComponentExchangeEnd) source).getPart());
        mustBeIncluded.add(((ComponentExchangeEnd) source).getPort());
      } else {
        mustBeIncluded.add(source);
      }
      InformationsExchanger target = connection.getTarget();
      if (target instanceof ComponentExchangeEnd) {
        mustBeIncluded.add(((ComponentExchangeEnd) target).getPart());
        mustBeIncluded.add(((ComponentExchangeEnd) target).getPort());
      } else {
        mustBeIncluded.add(target);
      }
    } else if (element instanceof PhysicalLink) {
      // Same for physical links
      PhysicalLink physicalLink = (PhysicalLink) element;
      for (AbstractPhysicalLinkEnd end : physicalLink.getLinkEnds()) {
        if (end instanceof PhysicalLinkEnd) {
          mustBeIncluded.add(((PhysicalLinkEnd) end).getPart());
          mustBeIncluded.add(((PhysicalLinkEnd) end).getPort());
        } else {
          mustBeIncluded.add(end);
        }
      }
    } else if ((element instanceof AbstractDeploymentLink) && !isMultipleDeploymentAllowed(element)) {
      // Deployment links require their target to be present if multiple
      // deployments are not allowed
      AbstractDeploymentLink link = (AbstractDeploymentLink) element;
      DeployableElement deployed = link.getDeployedElement();
      mustBeIncluded.add(deployed);
    } else if (element instanceof Property) {
      // For properties typed by non-primitive classes...
      Property property = (Property) element;
      AbstractType propertyType = property.getAbstractType();
      if (propertyType instanceof org.polarsys.capella.core.data.information.Class) {
        org.polarsys.capella.core.data.information.Class propertyClass = (org.polarsys.capella.core.data.information.Class) propertyType;
        EObject propertyContainer = property.eContainer();
        if (!propertyClass.isIsPrimitive() && contexts.contains(propertyContainer)
            && (propertyContainer instanceof org.polarsys.capella.core.data.information.Class)) {
          // ... check if an association is present
          boolean found = false;
          // We can't use the cross-referencer here because the elements may not be
          // attached to the Capella model yet
          Iterator<? extends EObject> it = contexts.iterator();
          while (!found && it.hasNext()) {
            EObject context = it.next();
            if (context instanceof Association) {
              Association association = (Association) context;
              if (association.getNavigableMembers().contains(property)) {
                found = true;
              }
            }
          }
          if (!found) {
            return false;
          }
        }
      }
    }
    for (EObject current : mustBeIncluded) {
      if ((current == null) || !EcoreUtil.isAncestor(contexts, current)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Return whether it makes sense (according to business criteria) to store the given element at the given location
   */
  public boolean isMeaningfulStorageFor(StorageLocation loc, EObject toStore) {
    // *** True by default
    boolean result = true;
    EObject container = loc.getContainer();
    // *** Functions
    if (toStore instanceof AbstractFunction) {
      // Functions go in similar functions (system, logical, physical)
      result = ((container instanceof FunctionPkg) || (container instanceof AbstractFunction));
      if (result) {
        BlockArchitecture archi = getBlockArchitecture(container);
        if (toStore instanceof SystemFunction) {
          result = archi instanceof SystemAnalysis;
        } else if (toStore instanceof LogicalFunction) {
          result = archi instanceof LogicalArchitecture;
        } else if (toStore instanceof PhysicalFunction) {
          result = archi instanceof PhysicalArchitecture;
        } else if (toStore instanceof OperationalActivity) {
          result = archi instanceof OperationalAnalysis;
        }
      }
    }
    // *** FunctionalExchanges
    else if (toStore instanceof FunctionalExchange) {
      // FunctionalExchanges go in functions which are similar to the
      // source/target's owner
      FunctionalExchange exchange = (FunctionalExchange) toStore;
      ActivityNode pin = exchange.getSource();
      if (null == pin) {
        pin = exchange.getTarget();
      }
      if (null != pin) {
        EObject pinOwner = pin.eContainer();
        if (pinOwner != null) {
          result = container.eClass().isSuperTypeOf(pinOwner.eClass());
        }
      }
    }
    // *** Parts of type Component
    else if ((toStore instanceof Part) && (((Part) toStore).getAbstractType() instanceof Component)
        && ((container instanceof Part) || (container instanceof Component))) {
      Component toStoreType = (Component) ((Part) toStore).getAbstractType();
      Component containerType = container instanceof Component ? (Component) container
          : (Component) ((Part) container).getAbstractType();
      result = componentsMatchForStorage(containerType, toStoreType);
    }
    return result;
  }

  /**
   * Return whether the given non-null components match for storage
   */
  private boolean componentsMatchForStorage(Component container, Component toStore) {
    BlockArchitecture architecture = getBlockArchitecture(container);
    if (architecture instanceof LogicalArchitecture) {
      return (toStore instanceof LogicalComponent);
    } else if (architecture instanceof PhysicalArchitecture) {
      return (toStore instanceof PhysicalComponent);
    } else if (architecture instanceof SystemAnalysis) {
      return (toStore instanceof SystemComponent);
    } else if (architecture instanceof OperationalAnalysis) {
      return toStore instanceof Entity;
    } else if (architecture instanceof EPBSArchitecture) {
      return toStore instanceof ConfigurationItem;
    }
    return false;
  }

  /**
   * Return the ComponentArchitecture to which the given potentially null element belongs, if any
   */
  private BlockArchitecture getBlockArchitecture(EObject element) {
    if (element == null) {
      return null;
    }
    if (element instanceof BlockArchitecture) {
      return (BlockArchitecture) element;
    }
    return getBlockArchitecture(element.eContainer());
  }

  /**
   * Return whether multiparts are allowed (reuse of types)
   * 
   * @param element
   *          an element that defines a project context
   */
  private boolean isMultipartAllowed(EObject element) {
    return TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven((ModelElement) element));
  }

  /**
   * Return whether multiple deployments are allowed
   * 
   * @param element
   *          an element that defines a project context
   */
  private boolean isMultipleDeploymentAllowed(EObject element) {
    return PreferencesHelper.isMultipleDeploymentAllowed();
  }

  /**
   * Return whether the storage location of the given element can be derived from other elements it depends upon
   */
  public boolean storageCanBeDerived(EObject element) {
    if (element instanceof FunctionalExchange || element instanceof ComponentExchange
        || element instanceof PhysicalLink) {
      return true;
    }
    if (element instanceof Part) {
      AbstractType type = ((Part) element).getType();
      return (type instanceof Component && ((Component) type).isActor());
    }
    return false;
  }

  /**
   * Derive the storage location of the given element based on the already defined storage locations for the given
   * additional elements
   */
  public StorageLocation deriveStorage(EObject element, List<? extends StorageLocation> locations,
      List<? extends EObject> peers) {
    assert locations.size() == peers.size();
    StorageLocation result = null;
    ModelElement container = null;
    EReference containment = null;

    // FunctionalExchanges
    if (element instanceof FunctionalExchange) {
      FunctionalExchange exchange = (FunctionalExchange) element;
      container = deriveLinkStorage(exchange.getSource(), exchange.getTarget(), locations, peers);
      if (container instanceof AbstractFunction) {
        containment = FaPackage.eINSTANCE.getAbstractFunction_OwnedFunctionalExchanges();
      }
    }
    // Connections
    else if (element instanceof ComponentExchange) {
      ComponentExchange exchange = (ComponentExchange) element;
      container = deriveLinkStorage(exchange.getSource(), exchange.getTarget(), locations, peers);
      if (container instanceof Component) {
        containment = FaPackage.eINSTANCE.getAbstractFunctionalBlock_OwnedComponentExchanges();
      }
    }
    // PhysicalLinks
    else if (element instanceof PhysicalLink) {
      PhysicalLink exchange = (PhysicalLink) element;
      List<? extends EObject> ends = exchange.getLinkEnds();
      if (2 == ends.size()) {
        container = deriveLinkStorage(ends.get(0), ends.get(1), locations, peers);
        if (container instanceof Component) {
          containment = CsPackage.eINSTANCE.getComponent_OwnedPhysicalLinks();
        }
      }
    }
    if ((null != container) && (null != containment)) {
      result = new StorageLocation(container, containment);
    }
    return result;
  }

  /**
   * Helper method for deriveStorage: case where storage is derived from the ends of a link/exchange
   * 
   * @return the computed storage element, if any
   */
  private ModelElement deriveLinkStorage(EObject source, EObject target, List<? extends StorageLocation> locations,
      List<? extends EObject> peers) {
    ModelElement result = null;
    EObject sourceElement = getLinkDerivationReferenceElement(source);
    EObject targetElement = getLinkDerivationReferenceElement(target);
    try {
      int sourceIndex = peers.indexOf(sourceElement);
      int targetIndex = peers.indexOf(targetElement);
      StorageLocation sourceLoc = locations.get(sourceIndex);
      StorageLocation targetLoc = locations.get(targetIndex);
      EObject ancestor = MiscUtil.getCommonAncestor(sourceLoc.getContainer(), targetLoc.getContainer());
      if (ancestor instanceof ModelElement) {
        result = (ModelElement) ancestor;
      }
    } catch (RuntimeException e) {
      // Failure: return null
    }
    return result;
  }

  /**
   * From the given non-null end of a link, return the associated element which must be used for deriving a storage for
   * the link
   * 
   * @return a non-null element
   */
  private EObject getLinkDerivationReferenceElement(EObject element) {
    EObject result;
    if (element instanceof ComponentExchangeEnd) {
      ComponentExchangeEnd end = (ComponentExchangeEnd) element;
      if (end.getPart() != null) {
        result = end.getPart();
      } else {
        result = end.getPort();
      }
    } else if (element instanceof PhysicalLinkEnd) {
      PhysicalLinkEnd end = (PhysicalLinkEnd) element;
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
   * Return whether the given reference must be updated when its values are duplicated, i.e., whether the duplicates
   * must be referenced too
   * 
   * @param reference
   *          a reference such that QueryUtil.supportsAddition(reference)
   */
  public boolean updateWithDuplicatedValues(EReference reference) {
    return !hasFixedCardinality(reference) && !NON_UPDATING_FEATURES.contains(reference);
  }

  /**
   * The set of references which should not be updated when their values are duplicated
   */
  private static final Collection<EReference> NON_UPDATING_FEATURES = Arrays.<EReference> asList(
      InformationPackage.eINSTANCE.getPort_ProvidedInterfaces(),
      InformationPackage.eINSTANCE.getPort_RequiredInterfaces(),
      CapellacorePackage.eINSTANCE.getAbstractPropertyValue_ValuedElements(),
      CapellacorePackage.eINSTANCE.getPropertyValueGroup_ValuedElements(),
      CapellacorePackage.eINSTANCE.getCapellaElement_AppliedPropertyValues(),
      CapellacorePackage.eINSTANCE.getCapellaElement_AppliedPropertyValueGroups());

}
