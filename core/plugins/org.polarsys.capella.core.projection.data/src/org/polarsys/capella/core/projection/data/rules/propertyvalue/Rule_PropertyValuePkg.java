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
package org.polarsys.capella.core.projection.data.rules.propertyvalue;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;

public class Rule_PropertyValuePkg extends Rule_CapellaElement {

  public Rule_PropertyValuePkg() {
    super(CapellacorePackage.Literals.PROPERTY_VALUE_PKG, CapellacorePackage.Literals.PROPERTY_VALUE_PKG);
  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    EObject parent = getStructureContainer(element_p, context_p);
    if (parent != null) {
      result_p.add(parent);
    }
  }

  protected EObject getStructureContainer(EObject element_p, IContext context_p) {
    EObject parent = element_p.eContainer();
    if (EcoreUtil2.isContainedBy(element_p, CsPackage.Literals.BLOCK_ARCHITECTURE)) {
      while (parent != null) {
        if ((parent instanceof Structure) || (parent instanceof PropertyValueGroup) || (parent instanceof AbstractPropertyValue)) {
          if (TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(parent, context_p).isOK()) {
            if (!TransformationHandlerHelper.getInstance(context_p).getTargetType(parent, context_p).isAbstract()) {
              return parent;
            }
          }
        }
        parent = parent.eContainer();
      }
    }
    return getSourceContainer(element_p, null, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return CapellacorePackage.Literals.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;
  }

  @Override
  protected EObject getSourceContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject parent = element_p.eContainer();
    if (EcoreUtil2.isContainedBy(element_p, CsPackage.Literals.BLOCK_ARCHITECTURE)) {
      while (parent != null) {
        if (TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(parent, context_p, CapellacorePackage.Literals.STRUCTURE) != null) {
          return parent;
        }
        parent = parent.eContainer();
      }
    }
    return null;
  }

  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    BlockArchitecture architecture = (BlockArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
    return architecture;
  }
}
