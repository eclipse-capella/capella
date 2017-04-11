/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

/**
 * A merge policy for Capella models.
 */
public class CapellaMergePolicy extends SiriusMergePolicy {
  
  /** The set of references which must be considered mandatory */
  protected static final Collection<EReference> __MANDATORY_REFERENCES = Arrays.asList(
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
   * @see org.polarsys.capella.common.consonance.ui.sirius.SiriusMergePolicy#getAdditionGroup(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope)
   */
  @Override
  public Set<EObject> getAdditionGroup(EObject element,
      IFeaturedModelScope scope) {
    Set<EObject> result = super.getAdditionGroup(element, scope);
    if (element instanceof Part) {
      Part part = (Part)element;
      AbstractType type = part.getAbstractType();
      if (type instanceof Component)
        result.add(type);
    } else if (element instanceof Component) {
      Component component = (Component)element;
      List<AbstractTypedElement> typed = component.getAbstractTypedElements();
      if (typed.size() == 1 && typed.get(0) instanceof Part)
        result.addAll(typed);
    } else if (element instanceof Association) {
      Association association = (Association)element;
      result.addAll((association.getOwnedMembers()));
      result.addAll((association.getNavigableMembers()));
    } else if (element instanceof Property) {
      Property property = (Property)element;
      Association association = property.getAssociation();
      if (association != null)
        result.add(association);
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
        __MANDATORY_REFERENCES.contains(reference);
  }
  
}
