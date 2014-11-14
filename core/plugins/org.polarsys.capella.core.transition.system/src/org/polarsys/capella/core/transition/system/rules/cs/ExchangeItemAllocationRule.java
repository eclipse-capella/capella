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
package org.polarsys.capella.core.transition.system.rules.cs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class ExchangeItemAllocationRule extends AbstractCapellaElementRule {

  public ExchangeItemAllocationRule() {
    super();
    registerUpdatedAttribute(CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__RECEIVE_PROTOCOL);
    registerUpdatedAttribute(CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__SEND_PROTOCOL);

    registerUpdatedAttribute(ModellingcorePackage.Literals.FINALIZABLE_ELEMENT__FINAL);
  }

  @Override
  protected EClass getSourceType() {
    return CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION;
  }

  @Override
  public IStatus transformRequired(EObject source_p, IContext context_p) {
    return super.transformRequired(source_p, context_p);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    ExchangeItemAllocation element = (ExchangeItemAllocation) source_p;
    result_p.add(element.getAllocatedItem());
    result_p.add(element.getAllocatingInterface());
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM, context_p);
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    needed_p.addAll(createDefaultPrecedencePremices(element_p, CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM));
  }

}
