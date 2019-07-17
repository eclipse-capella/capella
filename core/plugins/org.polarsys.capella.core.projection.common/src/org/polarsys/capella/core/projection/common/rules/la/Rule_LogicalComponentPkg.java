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
package org.polarsys.capella.core.projection.common.rules.la;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.projection.common.CapellaEngine;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.Query;

/**
 */
public class Rule_LogicalComponentPkg extends Rule_CapellaElement {

  public Rule_LogicalComponentPkg() {
    super(LaPackage.Literals.LOGICAL_COMPONENT_PKG, PaPackage.Literals.PHYSICAL_COMPONENT_PKG);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject sourceContainer = element_p.eContainer();
    if (sourceContainer instanceof BlockArchitecture) {
      BlockArchitecture architecture = (BlockArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
      if (((PhysicalArchitecture) architecture).getOwnedPhysicalComponentPkg() == null) {
        return architecture;
      }
      return ((PhysicalArchitecture) architecture).getOwnedPhysicalComponentPkg();
    }

    EObject parent = sourceContainer;
    while (parent != null) {
      EObject targetContainer = Query.retrieveFirstTransformedElement(parent, context_p.getTransfo(), PaPackage.Literals.PHYSICAL_COMPONENT_PKG);
      if (targetContainer != null) {
        return targetContainer;
      }
      parent = parent.eContainer();
    }

    return super.getBestContainer(element_p, result_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    LogicalComponentPkg sourceElement = (LogicalComponentPkg) source_p;

    if (isRelatedToSource(sourceElement, context_p)) {
      // Retrieve sub LogicalComponentPkg from the current LC
      for (LogicalComponentPkg pkg : sourceElement.getOwnedLogicalComponentPkgs()) {
        result_p.add(pkg);
      }

      // Retrieve sub LogicalComponent from the current LC
      for (LogicalComponent subLc : sourceElement.getOwnedLogicalComponents()) {
        result_p.add(subLc);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    BlockArchitecture architecture = (BlockArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
    if (architecture != null) {
      return BlockArchitectureExt.getOrCreateSystem(architecture);
    }
    return super.getDefaultContainer(element_p, result_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    if (!(element_p.eContainer() instanceof Component) && !(element_p.eContainer() instanceof BlockArchitecture)) {
      super.retrieveContainer(element_p, result_p, context_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    if (container_p instanceof PhysicalArchitecture) {
      return PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG;

    } else if (container_p instanceof PhysicalComponent) {
      return PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS;

    } else if (container_p instanceof PhysicalComponentPkg) {
      return PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENT_PKGS;
    }

    return super.getTargetContainementFeature(element_p, result_p, container_p, context_p);
  }

}
