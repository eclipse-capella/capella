/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.system.rules.cs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class ExchangeItemAllocationRule extends AbstractCapellaElementRule {

  public ExchangeItemAllocationRule() {
    super();
    registerUpdatedAttribute(CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__RECEIVE_PROTOCOL);
    registerUpdatedAttribute(CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__SEND_PROTOCOL);

    registerUpdatedAttribute(ModellingcorePackage.Literals.FINALIZABLE_ELEMENT__FINAL);

    registerUpdatedReference(CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM);
  }

  @Override
  protected EClass getSourceType() {
    return CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION;
  }

  @Override
  public IStatus transformRequired(EObject source, IContext context) {
    return super.transformRequired(source, context);
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    ExchangeItemAllocation element = (ExchangeItemAllocation) source;
    result.add(element.getAllocatedItem());
    result.add(element.getAllocatingInterface());
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM, context);
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    needed.addAll(createDefaultPrecedencePremices(element, CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM));
  }

}
