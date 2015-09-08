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
package org.polarsys.capella.test.transition.ju.testcases;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.helpers.information.services.CommunicationLinkExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.model.ModelReconciliationCommunicationlinks;

/**
 * This test ensure that CommunicationLinks are correctly reconciliated if there is no TransfoLink between them.
 */
public class ReconciliationCommunicationLinks extends ModelReconciliationCommunicationlinks {

  @Override
  public void performTest() throws Exception {
    LCPCTransition1();
    LCPCTransition2();
    LCPCTransition3();
    SystemActorTransition();
    SystemActorTransition(); // retrigger a second time the transition to check same behavior with TransfoLinks between CL

  }

  private void SystemActorTransition() {

    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM, Boolean.FALSE);

    performActorTransition(getObjects(SA__ACTORS__A1));

    Component actor = shouldExist(SA__ACTORS__A1);
    Component logicalActor = shouldExist(LA__LOGICAL_ACTORS__LA1);

    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "actor.communicationLinks", "logicalActor.communicationLinks"),
        (actor.getOwnedCommunicationLinks().size() + 1) == logicalActor.getOwnedCommunicationLinks().size());

    for (CommunicationLink link : logicalActor.getOwnedCommunicationLinks()) {

      if (link != getObjects(LA__LOGICAL_ACTORS__LA1__LINK_TO_EI4_2).iterator().next()) {

        assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "communicationLink.outgoingTraces", "1"), link.getOutgoingTraces().size() == 1);
        assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "communicationLink.outgoingTraces", "1"), link.getOutgoingTraces().get(0) instanceof TransfoLink);

        TransfoLink stLink = (TransfoLink) link.getOutgoingTraces().get(0);
        assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "communicationLink.outgoingTraces", "link"), stLink.getTargetElement() instanceof CommunicationLink);
        CommunicationLink sLink = (CommunicationLink) stLink.getTargetElement();
        assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "communicationLink.kind", "communicationLink.kind"), link.getKind() == sLink.getKind());
        assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "communicationLink.protocol", "communicationLink.protocol"), link.getProtocol() == sLink.getProtocol());
      }

    }
  }

  protected void LCPCTransition1() {

    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM, Boolean.FALSE);

    performLCtoPCTransition(getObjects(LA__LC1));

    Component lc1 = shouldExist(LA__LC1);
    Component pc1 = shouldExist(PA__PC1);

    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "pc1.senderItems", "3"),
        CommunicationLinkExt.getExchangeItems(CommunicationLinkExt.getSenderCommunicationLink(pc1)).size() == 3);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "pc1.receiverItems", "3"),
        CommunicationLinkExt.getExchangeItems(CommunicationLinkExt.getReceiverCommunicationLink(pc1)).size() == 3);

    ExchangeItem i11 = shouldExist(LA__IP1__EI11);
    ExchangeItem i12 = shouldExist(LA__IP1__EI12);
    ExchangeItem i13 = shouldExist(LA__IP1__EI13);
    ExchangeItem i14 = shouldExist(LA__IP1__EI14);
    ExchangeItem i15 = shouldExist(LA__IP1__EI15);
    ExchangeItem i16 = shouldExist(LA__IP1__EI16);
    ExchangeItem pi11 = shouldExist(PA__IP1__EI11);
    ExchangeItem pi12 = shouldExist(PA__IP1__EI12);

    EObject res = null;

    res = mustBeTransitioned(LA__LC1__LINK_TO_EI11);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "LA__LC1__LINK_TO_EI11", "PA__PC1__LINK_TO_EI11"), res == getObject(PA__PC1__LINK_TO_EI11));
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "PEI1"), pi11.equals(((CommunicationLink) res).getExchangeItem()));

    res = mustBeTransitioned(LA__LC1__LINK_TO_EI12);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "LA__LC1__LINK_TO_EI12", "PA__PC1__LINK_TO_EI12"), res == getObject(PA__PC1__LINK_TO_EI12));
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "PEI2"), pi12.equals(((CommunicationLink) res).getExchangeItem()));

    res = mustBeTransitioned(LA__LC1__LINK_TO_EI13);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "LA__LC1__LINK_TO_EI13", "PA__PC1__LINK_TO_EI13"), res == getObject(PA__PC1__LINK_TO_EI13));
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "EI3"), i13.equals(((CommunicationLink) res).getExchangeItem()));

    res = mustBeTransitioned(LA__LC1__LINK_TO_EI14);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "LA__LC1__LINK_TO_EI14", "PA__PC1__LINK_TO_EI14"), res == getObject(PA__PC1__LINK_TO_EI14));
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "EI4"), i14.equals(((CommunicationLink) res).getExchangeItem()));

    res = mustBeTransitioned(LA__LC1__LINK_TO_EI15);
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "EI5"), i15.equals(((CommunicationLink) res).getExchangeItem()));

    res = mustBeTransitioned(LA__LC1__LINK_TO_EI16);
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "EI6"), i16.equals(((CommunicationLink) res).getExchangeItem()));

  }

  protected void LCPCTransition2() {

    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM, Boolean.TRUE);

    performLCtoPCTransition(getObjects(LA__LC1));

    Component lc1 = shouldExist(LA__LC1);
    Component pc1 = shouldExist(PA__PC1);

    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "pc1.senderItems", "3"),
        CommunicationLinkExt.getExchangeItems(CommunicationLinkExt.getSenderCommunicationLink(pc1)).size() == 3);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "pc1.receiverItems", "3"),
        CommunicationLinkExt.getExchangeItems(CommunicationLinkExt.getReceiverCommunicationLink(pc1)).size() == 3);

    ExchangeItem i11 = shouldExist(LA__IP1__EI11);
    ExchangeItem i12 = shouldExist(LA__IP1__EI12);
    ExchangeItem i13 = shouldExist(LA__IP1__EI13);
    ExchangeItem i14 = shouldExist(LA__IP1__EI14);
    ExchangeItem i15 = shouldExist(LA__IP1__EI15);
    ExchangeItem i16 = shouldExist(LA__IP1__EI16);

    ExchangeItem pi11 = shouldExist(PA__IP1__EI11);
    ExchangeItem pi12 = shouldExist(PA__IP1__EI12);

    ExchangeItem pi13 = mustBeTransitioned(LA__IP1__EI13);
    ExchangeItem pi14 = mustBeTransitioned(LA__IP1__EI14);
    ExchangeItem pi15 = mustBeTransitioned(LA__IP1__EI15);
    ExchangeItem pi16 = mustBeTransitioned(LA__IP1__EI16);

    EObject res = null;

    res = mustBeTransitioned(LA__LC1__LINK_TO_EI11);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "LA__LC1__LINK_TO_EI11", "PA__PC1__LINK_TO_EI11"), res == getObject(PA__PC1__LINK_TO_EI11));
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "PEI1"), pi11.equals(((CommunicationLink) res).getExchangeItem()));

    res = mustBeTransitioned(LA__LC1__LINK_TO_EI12);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "LA__LC1__LINK_TO_EI12", "PA__PC1__LINK_TO_EI12"), res == getObject(PA__PC1__LINK_TO_EI12));
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "PEI2"), pi12.equals(((CommunicationLink) res).getExchangeItem()));

    res = mustBeTransitioned(LA__LC1__LINK_TO_EI13);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "LA__LC1__LINK_TO_EI13", "PA__PC1__LINK_TO_EI13"), res == getObject(PA__PC1__LINK_TO_EI13));
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "PEI3"), pi13.equals(((CommunicationLink) res).getExchangeItem()));

    res = mustBeTransitioned(LA__LC1__LINK_TO_EI14);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "LA__LC1__LINK_TO_EI14", "PA__PC1__LINK_TO_EI14"), res == getObject(PA__PC1__LINK_TO_EI14));
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "PEI4"), pi14.equals(((CommunicationLink) res).getExchangeItem()));

    res = mustBeTransitioned(LA__LC1__LINK_TO_EI15);
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "PEI5"), pi15.equals(((CommunicationLink) res).getExchangeItem()));

    res = mustBeTransitioned(LA__LC1__LINK_TO_EI16);
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "PEI6"), pi16.equals(((CommunicationLink) res).getExchangeItem()));

  }

  protected void LCPCTransition3() {

    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM, Boolean.FALSE);

    performLCtoPCTransition(getObjects(LA__LC2));

    Component pc1 = mustBeTransitioned(LA__LC1);
    Component pc2 = mustBeTransitioned(LA__LC2);

    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "pc1.implementedInterfaces", "3"),
        CommunicationLinkExt.getExchangeItems(CommunicationLinkExt.getSenderCommunicationLink(pc1)).size() == 3);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "pc2.implementedInterfaces", "1"),
        CommunicationLinkExt.getExchangeItems(CommunicationLinkExt.getSenderCommunicationLink(pc2)).size() == 1);

    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "pc1.usedInterfaces", "3"),
        CommunicationLinkExt.getExchangeItems(CommunicationLinkExt.getReceiverCommunicationLink(pc1)).size() == 3);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "pc2.usedInterfaces", "1"),
        CommunicationLinkExt.getExchangeItems(CommunicationLinkExt.getReceiverCommunicationLink(pc2)).size() == 1);

  }
}
