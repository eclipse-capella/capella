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

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class Rule_ExchangeItemElement extends Rule_CapellaElement {

  public Rule_ExchangeItemElement() {
    super(InformationPackage.Literals.EXCHANGE_ITEM_ELEMENT, InformationPackage.Literals.EXCHANGE_ITEM_ELEMENT);
  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    //Nothing to do
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    ExchangeItemElement eie = (ExchangeItemElement) source_p;

    if (eie.getAbstractType() != null) {
      
      PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
      if (preferenceHelper.transitionDatatypeWhileExchangeItemTransition()) {
        result_p.add(eie.getAbstractType());
      }
    }

    if (eie.getOwnedMinCard() != null)
      result_p.add(eie.getOwnedMinCard());
    if (eie.getOwnedMaxCard() != null)
      result_p.add(eie.getOwnedMaxCard());
    if (eie.getOwnedDefaultValue() != null)
      result_p.add(eie.getOwnedDefaultValue());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    AttachmentHelper.getInstance(context_p).attachToBestElement(element_p, result_p, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE,
        context_p);

    if (isFirstAttach(element_p, result_p, context_p)) {
      AttachmentHelper.getInstance(context_p).attachElementByRel(result_p, result_p.eContainer(),
          CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATING_INTERFACE);
    }
  }

}
