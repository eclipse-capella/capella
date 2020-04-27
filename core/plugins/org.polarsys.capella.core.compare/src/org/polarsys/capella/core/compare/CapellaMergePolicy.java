/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.compare;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.sirius.SiriusMergePolicy;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;

/**
 * A merge policy for Capella models.
 */
public class CapellaMergePolicy extends SiriusMergePolicy {
  
  /** The set of references which must be considered mandatory */
  protected static final Collection<EReference> MANDATORY_REFERENCES = Arrays.asList(
      // Abstract Trace
      ModellingcorePackage.eINSTANCE.getAbstractTrace_SourceElement(),
      ModellingcorePackage.eINSTANCE.getAbstractTrace_TargetElement(),
      // Involvement
      CapellacorePackage.eINSTANCE.getInvolvement_Involved(),
      CapellacorePackage.eINSTANCE.getInvolvement_Involver(),
      // Communication Link
      CommunicationPackage.eINSTANCE.getCommunicationLink_ExchangeItem(),
      // Sequence Message
      InteractionPackage.eINSTANCE.getSequenceMessage_SendingEnd(),
      InteractionPackage.eINSTANCE.getSequenceMessage_ReceivingEnd(),
      InteractionPackage.eINSTANCE.getSequenceMessage_ExchangedItems(),
      // Operation Events
      InteractionPackage.eINSTANCE.getEventReceiptOperation_Operation(),
      InteractionPackage.eINSTANCE.getEventSentOperation_Operation(),
      // Component Exchange
      FaPackage.eINSTANCE.getComponentExchange_SourcePart(),
      FaPackage.eINSTANCE.getComponentExchange_SourcePort(),
      FaPackage.eINSTANCE.getComponentExchange_TargetPart(),
      FaPackage.eINSTANCE.getComponentExchange_TargetPort(),
      // Exchange Item Allocation
      CsPackage.eINSTANCE.getExchangeItemAllocation_AllocatedItem(),
      CsPackage.eINSTANCE.getExchangeItemAllocation_AllocatingInterface(),
      // Physical Link End
      CsPackage.eINSTANCE.getPhysicalLinkEnd_Part(),
      CsPackage.eINSTANCE.getPhysicalLinkEnd_Port(),
      // Physical Link
      CsPackage.eINSTANCE.getPhysicalLink_LinkEnds()
      );
  
  
  /**
   * Extend the given addition group for the given element within the given scope
   * to take into account the Association/Property coupling
   * @param group a non-null, modifiable collection
   * @param element a non-null element
   * @param scope a non-null scope
   */
  protected void extendCapellaAssociationPropertyAdditionGroup(
      Set<EObject> group, EObject element, IFeaturedModelScope scope) {
    if (element instanceof Association) {
      Association association = (Association)element;
      group.addAll((association.getOwnedMembers()));
      group.addAll((association.getNavigableMembers()));
    } else if (element instanceof Property) {
      Property property = (Property)element;
      Association association = property.getAssociation();
      if (association != null)
        group.add(association);
      EObject minCard = property.getOwnedMinCard();
      if (minCard != null)
        group.add(minCard);
      EObject maxCard = property.getOwnedMaxCard();
      if (maxCard != null)
        group.add(maxCard);
    }
  }
  
  /**
   * Extend the given addition group for the given element within the given scope
   * to take into account graphical containment conditions
   * @param group a non-null, modifiable collection
   * @param element a non-null element
   * @param scope a non-null scope
   */
  protected void extendCapellaGraphicalContainmentAdditionGroup(
      Set<EObject> group, EObject element, IFeaturedModelScope scope) {
    if (element instanceof AbstractDNode) {
      EObject container = element.eContainer();
      if (container instanceof AbstractDNode) {
        // Node in node
        AbstractDNode node = (AbstractDNode)element;
        AbstractDNode containerNode = (AbstractDNode)container;
        EObject nodeTarget = node.getTarget();
        EObject containerTarget = containerNode.getTarget();
        // Semantic relation affecting the graphical presence of the node
        EObject relation = getCapellaRelationBetween(containerTarget, nodeTarget);
        if (relation != null)
          group.add(relation);
      }
    }
  }
  
  /**
   * Extend the given addition group for the given element within the given scope
   * to take into account the MultiplicityElement/owned properties coupling
   * @param group a non-null, modifiable collection
   * @param element a non-null element
   * @param scope a non-null scope
   */
  protected void extendCapellaMultiplicityAdditionGroup(
      Set<EObject> group, EObject element, IFeaturedModelScope scope) {
    if (element instanceof MultiplicityElement) {
      MultiplicityElement multi = (MultiplicityElement)element;
      EObject minCard = multi.getOwnedMinCard();
      if (minCard != null)
        group.add(minCard);
      EObject maxCard = multi.getOwnedMaxCard();
      if (maxCard != null)
        group.add(maxCard);
    }
  }
  
  /**
   * Extend the given addition group for the given element within the given scope
   * to take into account the Part/Component coupling
   * @param group a non-null, modifiable collection
   * @param element a non-null element
   * @param scope a non-null scope
   */
  protected void extendCapellaPartComponentAdditionGroup(
      Set<EObject> group, EObject element, IFeaturedModelScope scope) {
    if (element instanceof Part) {
      Part part = (Part)element;
      AbstractType type = part.getAbstractType();
      if (type instanceof Component)
        group.add(type);
    } else if (element instanceof Component) {
      Component component = (Component)element;
      List<AbstractTypedElement> typed = component.getAbstractTypedElements();
      if (typed.size() == 1 && typed.get(0) instanceof Part)
        group.addAll(typed);
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.sirius.SiriusMergePolicy#getAdditionGroup(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope)
   */
  @Override
  public Set<EObject> getAdditionGroup(EObject element,
      IFeaturedModelScope scope) {
    Set<EObject> result = super.getAdditionGroup(element, scope);
    extendCapellaPartComponentAdditionGroup(result, element, scope);
    extendCapellaAssociationPropertyAdditionGroup(result, element, scope);
    extendCapellaGraphicalContainmentAdditionGroup(result, element, scope);
    extendCapellaMultiplicityAdditionGroup(result, element, scope);
    return result;
  }
  
  /**
   * Return the relationship, if any, from the given element to the given other element
   * that may alter the ability to show nodes in a diagram
   * @param source a non-null element
   * @param target a non-null element
   * @return a potentially null element
   */
  protected EObject getCapellaRelationBetween(EObject source, EObject target) {
    EObject result = null;
    if (source instanceof Part && target instanceof Part) {
      // Looking for DeploymentLink (Part -> Part)
      for (AbstractDeploymentLink link : ((Part)target).getDeployingLinks()) {
        if (link.getLocation() == source) {
          result = link;
          break;
        }
      } 
    } else if (source instanceof Role && target instanceof OperationalActivity) {
      // Looking for ActivityAllocation (Role -> Operational Activity)
      OperationalActivity targetActivity = (OperationalActivity)target;
      for (ActivityAllocation link : targetActivity.getActivityAllocations()) {
        if (link.getRole() == source) {
          result = link;
          break;
        }
      }
    } else if (target instanceof AbstractFunction) {
      // Looking for ComponentFunctionalAllocation (AbstractFunctionalBlock -> AbstractFunction)
      AbstractFunction targetFunction = (AbstractFunction)target;
      AbstractFunctionalBlock sourceType = null;
      if (source instanceof AbstractFunctionalBlock) {
        sourceType = (AbstractFunctionalBlock)source;
      } else if (source instanceof AbstractTypedElement) { // Role or Part
        AbstractType rawType = ((AbstractTypedElement)source).getAbstractType();
        if (rawType instanceof AbstractFunctionalBlock) {
          sourceType = (AbstractFunctionalBlock)rawType;
        }
      }
      if (sourceType != null) {
        for (ComponentFunctionalAllocation link :
          targetFunction.getComponentFunctionalAllocations()) {
          if (link.getBlock() == sourceType) {
            result = link;
            break;
          }
        }
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy#getNewIntrinsicID(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope)
   */
  @Override
  public String getNewIntrinsicID(EObject element, IFeaturedModelScope scope) {
    String result;
    if (element.eClass().getEIDAttribute() != null)
      result = IdGenerator.createId();
    else
      result = super.getNewIntrinsicID(element, scope);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy#isSingleMandatory(org.eclipse.emf.ecore.EReference)
   */
  @Override
  protected boolean isSingleMandatory(EReference reference) {
    return super.isSingleMandatory(reference) ||
        MANDATORY_REFERENCES.contains(reference);
  }
  
}
