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

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;

/**
 */
public class Rule_ExchangeItemAllocation extends Rule_CapellaElement {

  public Rule_ExchangeItemAllocation() {
    super(CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION, CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION);
  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    EObject transfoSource = (EObject) getTransfo().get(TransfoEngine.TRANSFO_SOURCE);
    if (transfoSource == element_p) {
      super.retrieveContainer(element_p, result_p, context_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    //Performs transition of exchange items if included into an InterfacePkg
    ExchangeItemAllocation sourceElement = (ExchangeItemAllocation) source_p;
    AbstractExchangeItem item = sourceElement.getAllocatedItem();
    if (item != null) {
      
      PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
      if (preferenceHelper.transitionExchangeItemWhileInterfaceTransition()) {
        result_p.add(sourceElement.getAllocatedItem());
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return CsPackage.Literals.INTERFACE__OWNED_EXCHANGE_ITEM_ALLOCATIONS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    AttachmentHelper.getInstance(context_p).attachToBestElement(element_p, result_p, CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM, context_p);

    if (isFirstAttach(element_p, result_p, context_p)) {
      AttachmentHelper.getInstance(context_p).attachElementByRel(result_p, result_p.eContainer(),
          CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATING_INTERFACE);
    }
  }

}
