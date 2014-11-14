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
package org.polarsys.capella.core.projection.common.rules.information;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.rules.core.Rule_FinalizableElement;

/**
 */
public class Rule_ExchangeItem extends Rule_FinalizableElement {

  /**
   * @param sourceType_p
   * @param targetType_p
   */
  public Rule_ExchangeItem() {
    super(InformationPackage.Literals.EXCHANGE_ITEM, InformationPackage.Literals.EXCHANGE_ITEM, InformationPackage.Literals.INFORMATION_REALIZATION);
    registerAttributeUpdate(InformationPackage.Literals.EXCHANGE_ITEM__EXCHANGE_MECHANISM);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    ExchangeItem src = (ExchangeItem) source_p;
    if (transformRequired(source_p, context_p).isOK()) {
      result_p.addAll(src.getOwnedElements());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return CapellacorePackage.Literals.ABSTRACT_EXCHANGE_ITEM_PKG__OWNED_EXCHANGE_ITEMS;
  }

}
