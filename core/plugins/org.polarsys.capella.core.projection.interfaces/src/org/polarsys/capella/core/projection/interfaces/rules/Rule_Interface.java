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
package org.polarsys.capella.core.projection.interfaces.rules;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.rules.core.Rule_FinalizableElement;
import org.polarsys.capella.core.tiger.ITransfo;

/**
 */
public class Rule_Interface extends Rule_FinalizableElement {

  /**
   * @param sourceType_p
   * @param targetType_p
   */
  public Rule_Interface() {
    this(LaPackage.Literals.CONTEXT_INTERFACE_REALIZATION);
  }

  /**
   * @param sourceType_p
   * @param targetType_p
   */
  public Rule_Interface(EClass realizationLink) {
    super(CsPackage.Literals.INTERFACE, CsPackage.Literals.INTERFACE, realizationLink);
    registerAttributeUpdate(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__ABSTRACT);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#getDescription()
   */
  @Override
  public String getDescription() {
    return super.getDescription() + __br + " - This rule manages interfaces which are not nested." + __br //$NON-NLS-1$
           + " - The container must be an " + CsPackage.Literals.INTERFACE_PKG.getName() + __br; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#when(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.impl.Transfo)
   */
  @Override
  public boolean when(EObject element_p, ITransfo transfo_p) {
    if (!super.when(element_p, transfo_p))
      return false;

    return element_p.eContainer().eClass().equals(CsPackage.Literals.INTERFACE_PKG)
           && !EcoreUtil2.isContainedBy(element_p, LaPackage.Literals.LOGICAL_ARCHITECTURE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    Interface sourceElement = (Interface) source_p;

    if (transformRequired(source_p, context_p).isOK()) {
      result_p.addAll(sourceElement.getSuperGeneralizations());
      result_p.addAll(sourceElement.getOwnedExchangeItemAllocations());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return CsPackage.Literals.INTERFACE_PKG__OWNED_INTERFACES;
  }
}
