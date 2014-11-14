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
package org.polarsys.capella.core.platform.sirius.clipboard.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import org.polarsys.capella.core.data.cs.AbstractActor;
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
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
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
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.deployment.DeploymentFactory;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;

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
   * Create semantic elements which are implicitly coupled with the given semantic element in the context of its given semantic context and representation
   * context
   */
  public Collection<EObject> addImplicitElements(EObject element_p, Object context_p, DSemanticDecorator graphicalContext_p) {
    Collection<EObject> result = new HashSet<EObject>();
    EObject container = element_p.eContainer();
    EObject graphicalTarget = null;
    if (graphicalContext_p != null) {
      graphicalTarget = graphicalContext_p.getTarget();
    }
    if ((container instanceof Component) && (element_p instanceof Component)) {
      // Whatever the diagram, the addition of a Component within another one
      // also triggers the creation of a Part if not present
      Component ownerC = (Component) container;
      Component eltC = (Component) element_p;
      if (eltC.getAbstractTypedElements().isEmpty()) {
        Part addition = createPartFor(eltC);
        ownerC.getOwnedFeatures().add(addition);
        result.add(addition);
        // Special case: in PABs, behavioral component within a node component
        // also requires a deployment link
        if ((graphicalTarget instanceof Part) && (container instanceof PhysicalComponent) && (container.eContainer() instanceof PhysicalComponent)
            && // container is not a root
            (element_p instanceof PhysicalComponent) && (((PhysicalComponent) container).getNature() == PhysicalComponentNature.NODE)
            && (((PhysicalComponent) element_p).getNature() == PhysicalComponentNature.BEHAVIOR)) {
          deployPhysicalPartOn(addition, (Part) graphicalTarget);
        }
      }
    } else if ((element_p instanceof Part) && (graphicalTarget instanceof Part) && (((Part) element_p).getAbstractType() instanceof PhysicalComponent)
               && (((Part) graphicalTarget).getAbstractType() instanceof PhysicalComponent)
               && (((PhysicalComponent) ((Part) element_p).getAbstractType()).getNature() == PhysicalComponentNature.BEHAVIOR)
               && (((PhysicalComponent) ((Part) graphicalTarget).getAbstractType()).getNature() == PhysicalComponentNature.NODE)) {
      deployPhysicalPartOn((Part) element_p, (Part) graphicalTarget);
    } else if ((element_p instanceof AbstractFunction) && ((context_p instanceof Component) || (graphicalTarget instanceof Part))) {
      // A function within a component triggers the creation of an allocation
      // if there exists none
      AbstractFunction function = (AbstractFunction) element_p;
      Component component = null;
      if (context_p instanceof Component) {
        component = (Component) context_p;
      } else {
        Part part = (Part) graphicalTarget;
        if (part.getAbstractType() instanceof Component) {
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
        if (mustCreate) {
          ComponentFunctionalAllocation allocation = FaFactory.eINSTANCE.createComponentFunctionalAllocation();
          MiscUtil.setNewId(allocation);
          allocation.setSourceElement(component);
          allocation.setTargetElement(function);
          component.getOwnedFunctionalAllocation().add(allocation);
          result.add(allocation);
        }
      }
    } else if ((element_p instanceof EnumerationLiteral) && (context_p instanceof Enumeration)) {
      ((EnumerationLiteral) element_p).setAbstractType((AbstractType) context_p);
    } else if ((element_p instanceof FunctionalChainInvolvement) && (context_p instanceof FunctionalChain)) {
      ((FunctionalChainInvolvement) element_p).setInvolver((FunctionalChain) context_p);
    }
    return result;
  }

  /**
   * Deploy the given part on the given location part
   * @param element_p a non-null part typed by a behavior physical component
   * @param location_p a non-null part typed by a node physical component
   */
  private void deployPhysicalPartOn(Part element_p, Part location_p) {
    PartDeploymentLink link = DeploymentFactory.eINSTANCE.createPartDeploymentLink();
    MiscUtil.setNewId(link);
    link.setLocation(location_p);
    link.setDeployedElement(element_p);
    location_p.getOwnedDeploymentLinks().add(link);
  }

  /**
   * Create and return a Part typed by the given non-null type
   */
  private Part createPartFor(AbstractType type_p) {
    Part result = CsFactory.eINSTANCE.createPart();
    MiscUtil.setNewId(result);
    result.setName(type_p.getName());
    LiteralNumericValue minCard = DatavalueFactory.eINSTANCE.createLiteralNumericValue("minCard"); //$NON-NLS-1$
    minCard.setValue("1"); //$NON-NLS-1$
    result.setOwnedMinCard(minCard);
    LiteralNumericValue maxCard = DatavalueFactory.eINSTANCE.createLiteralNumericValue("maxCard"); //$NON-NLS-1$
    maxCard.setValue("1"); //$NON-NLS-1$
    result.setOwnedMaxCard(maxCard);
    result.setAbstractType(type_p);
    return result;
  }

  /**
   * Return semantic elements which are implicitly coupled with the given semantic element in the context of their given representation context
   */
  public Set<EObject> getImplicitElements(EObject element_p, Object context_p) {
    Set<EObject> result = new HashSet<EObject>();
    if ((element_p instanceof Part) && !isMultipartAllowed(element_p)) {
      // Whatever the diagram, parts are always coupled with their
      // type if there is a unique part per type
      result.add(((Part) element_p).getAbstractType());
    } else if ((element_p instanceof Component) && !isMultipartAllowed(element_p)) {
      List<AbstractTypedElement> typedElements = ((Component) element_p).getAbstractTypedElements();
      if ((typedElements.size() == 1) && (typedElements.get(0) instanceof Part)) {
        result.add(typedElements.get(0));
      }
    } else if (element_p instanceof Association) {
      // Whatever the diagram, Associations are coupled with their
      // navigable members, which they may not own
      result.addAll(((Association) element_p).getNavigableMembers());
    } else if (element_p instanceof SequenceMessage) {
      // Whatever the diagram, SequenceMessages are coupled with their ends
      SequenceMessage msg = (SequenceMessage) element_p;
      result.add(msg.getSendingEnd());
      result.add(msg.getSendingEnd().getEvent());
      result.add(msg.getReceivingEnd());
      result.add(msg.getReceivingEnd().getEvent());
    } else if (element_p instanceof Execution) {
      // Whatever the diagram, Executions are coupled with their ends
      Execution msg = (Execution) element_p;
      result.add(msg.getStart());
      if (msg.getStart() instanceof AbstractEnd) {
        result.add(((AbstractEnd) msg.getStart()).getEvent());
      }
      result.add(msg.getFinish());
      if (msg.getFinish() instanceof AbstractEnd) {
        result.add(((AbstractEnd) msg.getFinish()).getEvent());
      }
    } else if (element_p instanceof InstanceRole) {
      // Whatever the diagram, InstanceRoles are coupled with their
      // represented instance
      result.add(((InstanceRole) element_p).getRepresentedInstance());
    }
    return result;
  }

  /**
   * Generalization of getImplicitElements to collections
   */
  public final Set<EObject> getImplicitElements(Collection<? extends EObject> elements_p, Object context_p) {
    Set<EObject> result = new HashSet<EObject>();
    for (EObject element : elements_p) {
      result.addAll(getImplicitElements(element, context_p));
    }
    return result;
  }

  /**
   * Return whether the given reference with isMany==true has a fixed multiplicity and should therefore not be used for adding elements
   */
  private boolean hasFixedCardinality(EReference reference_p) {
    return (CsPackage.eINSTANCE.getPhysicalLink_LinkEnds() == reference_p) || (InformationPackage.eINSTANCE.getAssociation_NavigableMembers() == reference_p);
  }

  /**
   * Return whether the given element is meaningful when considered within the given set of elements or their children only, i.e., when separated from anything
   * outside those elements.
   */
  public boolean isMeaningfulWithin(EObject element_p, Collection<? extends EObject> contexts_p) {
    // EnumerationPropertyValues never meaningful for the moment
    if (element_p instanceof EnumerationPropertyValue) {
      return false;
    }
    // Other cases
    Collection<EObject> mustBeIncluded = new ArrayList<EObject>();
    if (element_p instanceof AbstractTrace) {
      // Traces and allocations require their source and target to be present
      AbstractTrace abstractTrace = (AbstractTrace) element_p;
      mustBeIncluded.add(abstractTrace.getSourceElement());
      mustBeIncluded.add(abstractTrace.getTargetElement());
    } else if (element_p instanceof FunctionalExchange) {
      // Same for FunctionalExchange
      FunctionalExchange exchange = (FunctionalExchange) element_p;
      mustBeIncluded.add(exchange.getSource());
      mustBeIncluded.add(exchange.getTarget());
    } else if (element_p instanceof ComponentExchange) {
      // For connections, check for the contents of the ends
      ComponentExchange connection = (ComponentExchange) element_p;
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
    } else if (element_p instanceof PhysicalLink) {
      // Same for physical links
      PhysicalLink physicalLink = (PhysicalLink) element_p;
      for (AbstractPhysicalLinkEnd end : physicalLink.getLinkEnds()) {
        if (end instanceof PhysicalLinkEnd) {
          mustBeIncluded.add(((PhysicalLinkEnd) end).getPart());
          mustBeIncluded.add(((PhysicalLinkEnd) end).getPort());
        } else {
          mustBeIncluded.add(end);
        }
      }
    } else if ((element_p instanceof AbstractDeploymentLink) && !isMultipleDeploymentAllowed(element_p)) {
      // Deployment links require their target to be present if multiple
      // deployments are not allowed
      AbstractDeploymentLink link = (AbstractDeploymentLink) element_p;
      DeployableElement deployed = link.getDeployedElement();
      mustBeIncluded.add(deployed);
    } else if (element_p instanceof Property) {
      // For properties typed by non-primitive classes...
      Property property = (Property) element_p;
      AbstractType propertyType = property.getAbstractType();
      if (propertyType instanceof org.polarsys.capella.core.data.information.Class) {
    	  org.polarsys.capella.core.data.information.Class propertyClass = (org.polarsys.capella.core.data.information.Class) propertyType;
        EObject propertyContainer = property.eContainer();
        if (!propertyClass.isIsPrimitive() && contexts_p.contains(propertyContainer)
            && (propertyContainer instanceof org.polarsys.capella.core.data.information.Class)) {
          // ... check if an association is present
          boolean found = false;
          // We can't use the cross-referencer here because the elements may not be
          // attached to the Capella model yet
          Iterator<? extends EObject> it = contexts_p.iterator();
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
      if ((current == null) || !EcoreUtil.isAncestor(contexts_p, current)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Return whether it makes sense (according to business criteria) to store the given element at the given location
   */
  public boolean isMeaningfulStorageFor(StorageLocation loc_p, EObject toStore_p) {
    // *** True by default
    boolean result = true;
    EObject container = loc_p.getContainer();
    // *** Functions
    if (toStore_p instanceof AbstractFunction) {
      // Functions go in similar functions (system, logical, physical)
      result = ((container instanceof FunctionPkg) || (container instanceof AbstractFunction));
      if (result) {
        BlockArchitecture archi = getBlockArchitecture(container);
        if (toStore_p instanceof SystemFunction) {
          result = archi instanceof SystemAnalysis;
        } else if (toStore_p instanceof LogicalFunction) {
          result = archi instanceof LogicalArchitecture;
        } else if (toStore_p instanceof PhysicalFunction) {
          result = archi instanceof PhysicalArchitecture;
        } else if (toStore_p instanceof OperationalActivity) {
          result = archi instanceof OperationalAnalysis;
        }
      }
    }
    // *** FunctionalExchanges
    else if (toStore_p instanceof FunctionalExchange) {
      // FunctionalExchanges go in functions which are similar to the
      // source/target's owner
      FunctionalExchange exchange = (FunctionalExchange) toStore_p;
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
    else if ((toStore_p instanceof Part) && (((Part) toStore_p).getAbstractType() instanceof Component)
             && ((container instanceof Part) || (container instanceof Component))) {
      Component toStoreType = (Component) ((Part) toStore_p).getAbstractType();
      Component containerType = container instanceof Component ? (Component) container : (Component) ((Part) container).getAbstractType();
      result = componentsMatchForStorage(containerType, toStoreType);
    }
    return result;
  }

  /**
   * Return whether the given non-null components match for storage
   */
  private boolean componentsMatchForStorage(Component container_p, Component toStore_p) {
    BlockArchitecture architecture = getBlockArchitecture(container_p);
    if (architecture instanceof LogicalArchitecture) {
      return (toStore_p instanceof LogicalComponent) || (toStore_p instanceof LogicalActor);
    } else if (architecture instanceof PhysicalArchitecture) {
      return (toStore_p instanceof PhysicalComponent) || (toStore_p instanceof PhysicalActor);
    } else if (architecture instanceof SystemAnalysis) {
      return (toStore_p instanceof SystemComponent) || (toStore_p instanceof Actor);
    } else if (architecture instanceof OperationalAnalysis) {
      return toStore_p instanceof Entity;
    } else if (architecture instanceof EPBSArchitecture) {
      return toStore_p instanceof ConfigurationItem;
    }
    return false;
  }

  /**
   * Return the ComponentArchitecture to which the given potentially null element belongs, if any
   */
  private BlockArchitecture getBlockArchitecture(EObject element_p) {
    if (element_p == null) {
      return null;
    }
    if (element_p instanceof BlockArchitecture) {
      return (BlockArchitecture) element_p;
    }
    return getBlockArchitecture(element_p.eContainer());
  }

  /**
   * Return whether multiparts are allowed (reuse of types)
   * @param element_p an element that defines a project context
   */
  private boolean isMultipartAllowed(EObject element_p) {
    return TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven((ModelElement) element_p));
  }

  /**
   * Return whether multiple deployments are allowed
   * @param element_p an element that defines a project context
   */
  private boolean isMultipleDeploymentAllowed(EObject element_p) {
    return CapellaModelPreferencesPlugin.getDefault().isMultipleDeploymentAllowed();
  }

  /**
   * Return whether the storage location of the given element can be derived from other elements it depends upon
   */
  public boolean storageCanBeDerived(EObject element_p) {
    return (element_p instanceof FunctionalExchange) || (element_p instanceof ComponentExchange) || (element_p instanceof PhysicalLink)
           || ((element_p instanceof Part) && (((Part) element_p).getType() instanceof AbstractActor));
  }

  /**
   * Derive the storage location of the given element based on the already defined storage locations for the given additional elements
   */
  public StorageLocation deriveStorage(EObject element_p, List<? extends StorageLocation> locations_p, List<? extends EObject> peers_p) {
    assert locations_p.size() == peers_p.size();
    StorageLocation result = null;
    ModelElement container = null;
    EReference containment = null;

    // FunctionalExchanges
    if (element_p instanceof FunctionalExchange) {
      FunctionalExchange exchange = (FunctionalExchange) element_p;
      container = deriveLinkStorage(exchange.getSource(), exchange.getTarget(), locations_p, peers_p);
      if (container instanceof AbstractFunction) {
        containment = FaPackage.eINSTANCE.getAbstractFunction_OwnedFunctionalExchanges();
      }
    }
    // Connections
    else if (element_p instanceof ComponentExchange) {
      ComponentExchange exchange = (ComponentExchange) element_p;
      container = deriveLinkStorage(exchange.getSource(), exchange.getTarget(), locations_p, peers_p);
      if (container instanceof Component) {
        containment = FaPackage.eINSTANCE.getAbstractFunctionalBlock_OwnedComponentExchanges();
      }
    }
    // PhysicalLinks
    else if (element_p instanceof PhysicalLink) {
      PhysicalLink exchange = (PhysicalLink) element_p;
      List<? extends EObject> ends = exchange.getLinkEnds();
      if (2 == ends.size()) {
        container = deriveLinkStorage(ends.get(0), ends.get(1), locations_p, peers_p);
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
   * @return the computed storage element, if any
   */
  private ModelElement deriveLinkStorage(EObject source_p, EObject target_p, List<? extends StorageLocation> locations_p, List<? extends EObject> peers_p) {
    ModelElement result = null;
    EObject sourceElement = getLinkDerivationReferenceElement(source_p);
    EObject targetElement = getLinkDerivationReferenceElement(target_p);
    try {
      int sourceIndex = peers_p.indexOf(sourceElement);
      int targetIndex = peers_p.indexOf(targetElement);
      StorageLocation sourceLoc = locations_p.get(sourceIndex);
      StorageLocation targetLoc = locations_p.get(targetIndex);
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
   * From the given non-null end of a link, return the associated element which must be used for deriving a storage for the link
   * @return a non-null element
   */
  private EObject getLinkDerivationReferenceElement(EObject element_p) {
    EObject result;
    if (element_p instanceof ComponentExchangeEnd) {
      ComponentExchangeEnd end = (ComponentExchangeEnd) element_p;
      if (end.getPart() != null) {
        result = end.getPart();
      } else {
        result = end.getPort();
      }
    } else if (element_p instanceof PhysicalLinkEnd) {
      PhysicalLinkEnd end = (PhysicalLinkEnd) element_p;
      if (end.getPart() != null) {
        result = end.getPart();
      } else {
        result = end.getPort();
      }
    } else {
      result = element_p;
    }
    return result.eContainer();
  }

  /**
   * Return whether the given reference must be updated when its values are duplicated, i.e., whether the duplicates must be referenced too
   * @param reference_p a reference such that QueryUtil.supportsAddition(reference_p)
   */
  public boolean updateWithDuplicatedValues(EReference reference_p) {
    return !hasFixedCardinality(reference_p) && !NON_UPDATING_FEATURES.contains(reference_p);
  }

  /**
   * The set of references which should not be updated when their values are duplicated
   */
  private static final Collection<EReference> NON_UPDATING_FEATURES = Arrays.<EReference> asList(InformationPackage.eINSTANCE.getPort_ProvidedInterfaces(),
      InformationPackage.eINSTANCE.getPort_RequiredInterfaces(), CapellacorePackage.eINSTANCE.getAbstractPropertyValue_ValuedElements(),
      CapellacorePackage.eINSTANCE.getPropertyValueGroup_ValuedElements(), CapellacorePackage.eINSTANCE.getCapellaElement_AppliedPropertyValues(),
      CapellacorePackage.eINSTANCE.getCapellaElement_AppliedPropertyValueGroups());

}
