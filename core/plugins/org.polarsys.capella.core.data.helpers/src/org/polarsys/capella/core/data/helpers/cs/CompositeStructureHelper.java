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

package org.polarsys.capella.core.data.helpers.cs;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.cs.ArchitectureAllocation;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentRealization;
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
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.ArchitectureAllocationHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.BlockArchitectureHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.ComponentHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.ComponentRealizationHelper;
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
import org.polarsys.capella.core.data.helpers.cs.delegates.SystemComponentHelper;

public class CompositeStructureHelper implements IHelper {

  /**
   * Default constructor
   */
  public CompositeStructureHelper() {
    //do nothing
  }

  /**
   * @see org.polarsys.capella.common.model.helpers.IHelper#getValue(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature,
   *      org.eclipse.emf.ecore.EAnnotation)
   */
  public Object getValue(EObject object, EStructuralFeature feature, EAnnotation annotation) {
    Object ret = null;

    if (object instanceof Part) {
      ret = PartHelper.getInstance().doSwitch((Part) object, feature);
    } else if (object instanceof SystemComponent) {
      ret = SystemComponentHelper.getInstance().doSwitch((SystemComponent) object, feature);
    } else if (object instanceof Component) {
      ret = ComponentHelper.getInstance().doSwitch((Component) object, feature);
    } else if (object instanceof BlockArchitecture) {
      ret = BlockArchitectureHelper.getInstance().doSwitch((BlockArchitecture) object, feature);
    } else if (object instanceof Interface) {
      ret = InterfaceHelper.getInstance().doSwitch((Interface) object, feature);
    } else if (object instanceof InterfaceAllocator) {
      ret = InterfaceAllocatorHelper.getInstance().doSwitch((InterfaceAllocator) object, feature);
    } else if (object instanceof InterfaceAllocation) {
      ret = InterfaceAllocationHelper.getInstance().doSwitch((InterfaceAllocation) object, feature);
    } else if (object instanceof ArchitectureAllocation) {
      ret = ArchitectureAllocationHelper.getInstance().doSwitch((ArchitectureAllocation) object, feature);
    } else if (object instanceof InterfaceImplementation) {
      ret = InterfaceImplementationHelper.getInstance().doSwitch((InterfaceImplementation) object, feature);
    } else if (object instanceof InterfaceUse) {
      ret = InterfaceUseHelper.getInstance().doSwitch((InterfaceUse) object, feature);
    } else if (object instanceof InterfacePkg) {
      ret = StructureHelper.getInstance().doSwitch((InterfacePkg) object, feature);
    } else if (object instanceof ExchangeItemAllocation) {
      ret = ExchangeItemAllocationHelper.getInstance().doSwitch((ExchangeItemAllocation) object, feature);
    } else if (object instanceof PhysicalLink) {
      ret = PhysicalLinkHelper.getInstance().doSwitch((PhysicalLink) object, feature);
    } else if (object instanceof PhysicalPath) {
      ret = PhysicalPathHelper.getInstance().doSwitch((PhysicalPath) object, feature);
    } else if (object instanceof PhysicalPort) {
      ret = PhysicalPortHelper.getInstance().doSwitch((PhysicalPort) object, feature);
    } else if (object instanceof PhysicalLinkEnd) {
      ret = PhysicalLinkEndHelper.getInstance().doSwitch((PhysicalLinkEnd) object, feature);
    } else if (object instanceof PhysicalPathReference) {
      ret = PhysicalPathReferenceHelper.getInstance().doSwitch((PhysicalPathReference) object, feature);
    } else if (object instanceof PhysicalPathInvolvement) {
      ret = PhysicalPathInvolvementHelper.getInstance().doSwitch((PhysicalPathInvolvement) object, feature);
    } else if (object instanceof PhysicalLinkCategory) {
      ret = NamedElementHelper.getInstance().doSwitch((PhysicalLinkCategory) object, feature);
    } else if (object instanceof PhysicalLinkRealization) {
      ret = PhysicalLinkRealizationHelper.getInstance().doSwitch((PhysicalLinkRealization) object, feature);
    } else if (object instanceof PhysicalPathRealization) {
      ret = PhysicalPathRealizationHelper.getInstance().doSwitch((PhysicalPathRealization) object, feature);
    } else if (object instanceof PhysicalPortRealization) {
      ret = PhysicalPortRealizationHelper.getInstance().doSwitch((PhysicalPortRealization) object, feature);
    } else if (object instanceof ComponentRealization) {
      ret = ComponentRealizationHelper.getInstance().doSwitch((ComponentRealization) object, feature);
    }

    if (null != ret || feature.getUpperBound() == 1)
      return ret;

    throw new HelperNotFoundException();
  }
}
