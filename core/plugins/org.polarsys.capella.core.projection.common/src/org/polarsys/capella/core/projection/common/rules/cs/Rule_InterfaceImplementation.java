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
package org.polarsys.capella.core.projection.common.rules.cs;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.Query;

/**
 */
public class Rule_InterfaceImplementation extends Rule_CapellaElement {

  public Rule_InterfaceImplementation() {
    super(CsPackage.Literals.INTERFACE_IMPLEMENTATION, CsPackage.Literals.INTERFACE_IMPLEMENTATION);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    InterfaceImplementation sourceElement = (InterfaceImplementation) element_p;
    Component srcComponent = sourceElement.getInterfaceImplementor();
    Component tgtComponent = (Component) Query.retrieveFirstTransformedElement(srcComponent, context_p.getTransfo(), CsPackage.Literals.COMPONENT);

    Interface sourceItf = sourceElement.getImplementedInterface();

    if (tgtComponent != null) {
      for (InterfaceImplementation implementation : tgtComponent.getOwnedInterfaceImplementations()) {
        Interface itf = implementation.getImplementedInterface();
        if (itf != null && (RefinementLinkExt.isLinkedTo(itf, sourceItf) || sourceItf.equals(itf))) {
          return implementation;
        }
      }
    }

    return super.transformDirectElement(element_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getSourceContainer(EObject element_p, EObject result_p, IContext context_p) {
    InterfaceImplementation sourceElement = (InterfaceImplementation) element_p;
    return sourceElement.getInterfaceImplementor();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return CsPackage.Literals.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    AttachmentHelper.getInstance(context_p).attachToBestElement(element_p, result_p, CsPackage.Literals.INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE,
        context_p);
  }

}
