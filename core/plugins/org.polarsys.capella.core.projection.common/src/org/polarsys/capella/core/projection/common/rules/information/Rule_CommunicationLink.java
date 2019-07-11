/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;

/**
 */
public class Rule_CommunicationLink extends Rule_CapellaElement {

  public Rule_CommunicationLink() {
    super(CommunicationPackage.Literals.COMMUNICATION_LINK, CommunicationPackage.Literals.COMMUNICATION_LINK);
    registerAttributeUpdate(CommunicationPackage.Literals.COMMUNICATION_LINK__KIND);
    registerAttributeUpdate(CommunicationPackage.Literals.COMMUNICATION_LINK__PROTOCOL);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    CommunicationLink sourceElement = (CommunicationLink) source_p;
    if (sourceElement.getExchangeItem() != null) {
      
      PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
      if (preferenceHelper.transitionExchangeItemWhileComponentTransition()) {
        result_p.add(sourceElement);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__OWNED_COMMUNICATION_LINKS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p)
        .attachToBestElement(element_p, result_p, CommunicationPackage.Literals.COMMUNICATION_LINK__EXCHANGE_ITEM, context_p);
  }

}
