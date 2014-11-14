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
package org.polarsys.capella.core.data.helpers.cs;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.cs.ArchitectureAllocation;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentAllocation;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceAllocation;
import org.polarsys.capella.core.data.cs.InterfaceAllocator;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalLinkRealization;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.cs.PhysicalPathRealization;
import org.polarsys.capella.core.data.cs.PhysicalPathReference;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.cs.PhysicalPortRealization;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.helpers.cs.delegates.AbstractActorHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.ActorCapabilityRealizationInvolvementHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.ArchitectureAllocationHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.BlockArchitectureHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.ComponentAllocationHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.ComponentHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.ExchangeItemAllocationHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.InterfaceAllocationHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.InterfaceAllocatorHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.InterfaceHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.InterfaceImplementationHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.InterfaceUseHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.PartHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.PhysicalLinkEndHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.PhysicalLinkHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.PhysicalLinkRealizationHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.PhysicalPathHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.PhysicalPathInvolvementHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.PhysicalPathRealizationHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.PhysicalPathReferenceHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.PhysicalPortHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.PhysicalPortRealizationHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.SystemComponentCapabilityRealizationInvolvementHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.SystemComponentHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.common.tig.model.HelperNotFoundException;
import org.polarsys.capella.common.tig.model.IHelper;

public class CompositeStructureHelper implements IHelper {

  /**
   * Default constructor
   */
  public CompositeStructureHelper() {
    //do nothing
  }

  /**
   * @see org.polarsys.capella.common.tig.model.IHelper#getValue(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature,
   *      org.eclipse.emf.ecore.EAnnotation)
   */
  public Object getValue(EObject object_p, EStructuralFeature feature_p, EAnnotation annotation_p) {
    Object ret = null;

    if (object_p instanceof Part) {
      ret = PartHelper.getInstance().doSwitch((Part) object_p, feature_p);
    } else if (object_p instanceof SystemComponent) {
      ret = SystemComponentHelper.getInstance().doSwitch((SystemComponent) object_p, feature_p);
    } else if (object_p instanceof Component) {
      ret = ComponentHelper.getInstance().doSwitch((Component) object_p, feature_p);
    } else if (object_p instanceof BlockArchitecture) {
      ret = BlockArchitectureHelper.getInstance().doSwitch((BlockArchitecture) object_p, feature_p);
    } else if (object_p instanceof Interface) {
      ret = InterfaceHelper.getInstance().doSwitch((Interface) object_p, feature_p);
    } else if (object_p instanceof InterfaceAllocator) {
      ret = InterfaceAllocatorHelper.getInstance().doSwitch((InterfaceAllocator) object_p, feature_p);
    } else if (object_p instanceof InterfaceAllocation) {
      ret = InterfaceAllocationHelper.getInstance().doSwitch((InterfaceAllocation) object_p, feature_p);
    } else if (object_p instanceof ComponentAllocation) {
      ret = ComponentAllocationHelper.getInstance().doSwitch((ComponentAllocation) object_p, feature_p);
    } else if (object_p instanceof ArchitectureAllocation) {
      ret = ArchitectureAllocationHelper.getInstance().doSwitch((ArchitectureAllocation) object_p, feature_p);
    } else if (object_p instanceof AbstractActor) {
      ret = AbstractActorHelper.getInstance().doSwitch((AbstractActor) object_p, feature_p);
    } else if (object_p instanceof InterfaceImplementation) {
      ret = InterfaceImplementationHelper.getInstance().doSwitch((InterfaceImplementation) object_p, feature_p);
    } else if (object_p instanceof InterfaceUse) {
      ret = InterfaceUseHelper.getInstance().doSwitch((InterfaceUse) object_p, feature_p);
    } else if (object_p instanceof InterfacePkg) {
      ret = StructureHelper.getInstance().doSwitch((InterfacePkg) object_p, feature_p);
    } else if (object_p instanceof ActorCapabilityRealizationInvolvement) {
      ret = ActorCapabilityRealizationInvolvementHelper.getInstance().doSwitch((ActorCapabilityRealizationInvolvement) object_p, feature_p);
    } else if (object_p instanceof SystemComponentCapabilityRealizationInvolvement) {
      ret = SystemComponentCapabilityRealizationInvolvementHelper.getInstance().doSwitch((SystemComponentCapabilityRealizationInvolvement) object_p, feature_p);
    } else if (object_p instanceof ExchangeItemAllocation) {
      ret = ExchangeItemAllocationHelper.getInstance().doSwitch((ExchangeItemAllocation) object_p, feature_p);
    } else if (object_p instanceof PhysicalLink) {
      ret = PhysicalLinkHelper.getInstance().doSwitch((PhysicalLink) object_p, feature_p);
    } else if (object_p instanceof PhysicalPath) {
      ret = PhysicalPathHelper.getInstance().doSwitch((PhysicalPath) object_p, feature_p);
    } else if (object_p instanceof PhysicalPort) {
      ret = PhysicalPortHelper.getInstance().doSwitch((PhysicalPort) object_p, feature_p);
    } else if (object_p instanceof PhysicalLinkEnd) {
      ret = PhysicalLinkEndHelper.getInstance().doSwitch((PhysicalLinkEnd) object_p, feature_p);
    } else if (object_p instanceof PhysicalPathReference) {
      ret = PhysicalPathReferenceHelper.getInstance().doSwitch((PhysicalPathReference) object_p, feature_p);
    } else if (object_p instanceof PhysicalPathInvolvement) {
      ret = PhysicalPathInvolvementHelper.getInstance().doSwitch((PhysicalPathInvolvement) object_p, feature_p);
    } else if (object_p instanceof PhysicalLinkCategory) {
      ret = NamedElementHelper.getInstance().doSwitch((PhysicalLinkCategory) object_p, feature_p);
    } else if (object_p instanceof PhysicalLinkRealization) {
      ret = PhysicalLinkRealizationHelper.getInstance().doSwitch((PhysicalLinkRealization) object_p, feature_p);
    } else if (object_p instanceof PhysicalPathRealization) {
      ret = PhysicalPathRealizationHelper.getInstance().doSwitch((PhysicalPathRealization) object_p, feature_p);
    } else if (object_p instanceof PhysicalPortRealization) {
      ret = PhysicalPortRealizationHelper.getInstance().doSwitch((PhysicalPortRealization) object_p, feature_p);
    }

    if (null != ret || feature_p.getUpperBound() == 1)
      return ret;

    throw new HelperNotFoundException();
  }
}
