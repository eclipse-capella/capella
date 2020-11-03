/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.testcases.datalisteners;

import org.eclipse.sirius.business.api.session.Session;
import org.junit.Assert;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeMechanismExt;
import org.polarsys.capella.core.data.information.ElementKind;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.SkeletonHelper;
import org.polarsys.capella.test.framework.model.GenericModel;
import org.polarsys.capella.test.model.ju.model.MiscModel;

public class ExchangeItemsCommunicationLinks extends MiscModel {

  public static final String RANDOM_NAME_1 = "AAA";
  public static final String RANDOM_NAME_2 = "BBB";
  public static final String RANDOM_NAME_3 = "CCC";

  @Override
  public void test() {

    Session session = getSession(getRequiredTestModels().get(0));
    SessionContext context = new SessionContext(session);
    SkeletonHelper.createActor(SA__ACTORS, GenericModel.ACTOR_1, GenericModel.PART_1, context);
    SkeletonHelper.createActor(SA__ACTORS, GenericModel.ACTOR_2, GenericModel.PART_2, context);

    SkeletonHelper.createInterface(SA__INTERFACES, GenericModel.INTERFACE_1, context);
    SkeletonHelper.createExchangeItem(SA__INTERFACES, GenericModel.EXCHANGE_ITEM_1, context);

    SkeletonHelper.createExchangeItemAllocation(GenericModel.OBJECT_1, GenericModel.INTERFACE_1, GenericModel.EXCHANGE_ITEM_1, context);
    SkeletonHelper.createExchangeItemElement(GenericModel.EXCHANGE_ITEM_1, GenericModel.EXCHANGE_ITEM_ELEMENT_1, context);

    SkeletonHelper.createCommunicationLinkSend(GenericModel.CL_1, GenericModel.ACTOR_1, GenericModel.EXCHANGE_ITEM_1, context);
    SkeletonHelper.createCommunicationLinkReceive(GenericModel.CL_2, GenericModel.ACTOR_2, GenericModel.EXCHANGE_ITEM_1, context);

    // Ensure that all connected elements have correct kind based on the ExchangeItem mechanism
    setMechanism(GenericModel.EXCHANGE_ITEM_1, ExchangeMechanism.EVENT, context);
    ensureSynchronizedElements(context, ElementKind.TYPE);

    setMechanism(GenericModel.EXCHANGE_ITEM_1, ExchangeMechanism.SHARED_DATA, context);
    ensureSynchronizedElements(context, ElementKind.TYPE);

    setMechanism(GenericModel.EXCHANGE_ITEM_1, ExchangeMechanism.OPERATION, context);
    ensureSynchronizedElements(context, ElementKind.MEMBER);

    setMechanism(GenericModel.EXCHANGE_ITEM_1, ExchangeMechanism.FLOW, context);
    ensureSynchronizedElements(context, ElementKind.TYPE);

  }

  private void ensureSynchronizedElements(SessionContext context, ElementKind kind) {

    //new and existing elements
    SkeletonHelper.createExchangeItemElement(GenericModel.EXCHANGE_ITEM_1, GenericModel.EXCHANGE_ITEM_ELEMENT_2, context);
    ensureElement(context, GenericModel.EXCHANGE_ITEM_ELEMENT_1, kind);
    ensureElement(context, GenericModel.EXCHANGE_ITEM_ELEMENT_2, kind);
    
    //communication link
    ensureLink(context, GenericModel.CL_1, GenericModel.EXCHANGE_ITEM_1, true);
    ensureLink(context, GenericModel.CL_2, GenericModel.EXCHANGE_ITEM_1, false);
    
    //exchangeitem allocation
    ensureAllocation(context, GenericModel.OBJECT_1, GenericModel.EXCHANGE_ITEM_1);
  }

  private void ensureAllocation(SessionContext context, String linkId, String itemId) {
    ExchangeItemAllocation link = context.getSemanticElement(linkId);
    ExchangeItem item = context.getSemanticElement(itemId);
    Assert.assertEquals(link.getSendProtocol(), ExchangeMechanismExt.getProtocol(item.getExchangeMechanism(), true));
    Assert.assertEquals(link.getReceiveProtocol(),
        ExchangeMechanismExt.getProtocol(item.getExchangeMechanism(), false));
  }

  private void ensureLink(SessionContext context, String linkId, String itemId, boolean isSender) {
    CommunicationLink link = context.getSemanticElement(linkId);
    ExchangeItem item = context.getSemanticElement(itemId);
    Assert.assertEquals(link.getProtocol(), ExchangeMechanismExt.getProtocol(item.getExchangeMechanism(), isSender));
    Assert.assertEquals(link.getKind(), ExchangeMechanismExt.getKind(item.getExchangeMechanism(), isSender));
  }

  private void ensureElement(final SessionContext context, final String elementId, ElementKind member) {
    ExchangeItemElement element = context.getSemanticElement(elementId);
    Assert.assertTrue(member.equals(element.getKind()));
  }

  private void setMechanism(String exchangeItemId, ExchangeMechanism mechanism, SessionContext context) {
    SkeletonHelper.setAttribute(context.getSemanticElement(exchangeItemId), mechanism,
        InformationPackage.Literals.EXCHANGE_ITEM__EXCHANGE_MECHANISM, context);
  }

}
