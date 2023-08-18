/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.table.ju.interfaces;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.table.ju.utils.TableTestFramework;

public abstract class InterfaceTableTestFramework extends TableTestFramework {
  protected String modelName = "InterfaceTable";
  protected Session session;
  protected SessionContext context;

  protected String systemAnalysisId = "7cc43d36-8a50-47cd-b29a-ca8f7b97b773";

  protected Interface _interface1;
  protected Interface _interface2;
  protected Interface _interface3;

  protected Capability _capability1;
  protected Capability _capability2;
  protected Capability _capability3;
  protected Capability _subCapability1;

  protected CapabilityPkg _capabilityPkg;
  protected CapabilityPkg _subCapabilityPkg;

  protected ExchangeItem _flow1;
  protected ExchangeItem _operation1;
  protected ExchangeItem _operation2;
  protected ExchangeItem _operation3;

  protected ExchangeItemAllocation _flow1ExItem1;
  protected ExchangeItemAllocation _oper1ExItem1;
  protected ExchangeItemAllocation _oper2ExItem1;
  protected ExchangeItemAllocation _oper3ExItem1;

  protected SequenceMessage _oper1SeqMsgCall;
  protected SequenceMessage _oper1SeqMsgReply;

  protected SequenceMessage _oper2SeqMsgCall;
  protected SequenceMessage _oper2SeqMsgReply;

  protected Scenario _scenario1;
  protected Scenario _scenario2;

  protected String interface1Id = "312039c6-2d59-4421-8417-520482a3ee6e";
  protected String interface2Id = "68eec963-b0d3-4dc1-a007-e9c98fd16cbf";
  protected String interface3Id = "81d8a076-184c-4def-9350-ea4edd48ace4";
  protected String capability1Id = "7217cf06-840b-4f32-8f98-62c882c46cb0";
  protected String capability2Id = "848b1ed2-9417-4cec-bf93-0595eb9be8df";
  protected String capability3Id = "61cea9b0-3d05-444b-9964-b6c65549d2ea";

  protected String capabilityPkgId = "f832a320-700b-4893-bd93-d45f39a59c74";
  protected String subCapabilityPkgId = "43e633ba-1696-453f-9828-286eda8ef4f7";
  protected String subcapability1Id = "0a2876c1-5742-4804-8646-2ba58b0091ed";

  protected String flow1Id = "213ebbac-12a5-441c-8a73-2c352d1a2224";
  protected String operation1Id = "57ff7d22-2ce7-449a-9799-d57cb90d526d";
  protected String operation2Id = "e041f199-b247-48c0-9868-85ede5c9520a";
  protected String operation3Id = "36189653-6f81-4235-8d4f-7731b9e1ca5b";

  protected String flow1ExItem1Id = "28bc519a-2bc7-4222-98d8-2334c1aa1ec6";
  protected String oper1ExItem1Id = "d3c58667-1803-46bc-9d77-ba0fdb019271";
  protected String oper2ExItem1Id = "d3888319-c5d1-4d2e-9f2a-70c23700cd1d";
  protected String oper3ExItem1Id = "e1e600c2-16cd-4458-8c10-a0133d5b7e13";

  protected String seqMsgOper1Id = "4c9fb9dd-2944-4234-af92-1f6b8cc35298";
  protected String seqMsgOper1ReplyId = "b4aaafe4-8f71-42a3-97ed-8901c9bcbde0";
  protected String oper2SeqMsgCallId = "7c23b7bd-afd2-4ede-91b5-7a7cc5dd2854";
  protected String oper2SeqMsgReplyId = "a0aac67f-a304-42f2-ad42-ca85a28a1527";

  protected String actor1Id = "ae11b25f-4c7e-4ac6-aa48-6670e228e6b1";
  protected String actor2Id = "92d86842-7d4d-4a9d-8354-7c5c8b6bee39";

  protected String scenario1Id = "17d20d45-e318-4b48-ac61-541ea8fdc2b7";
  protected String scenario2Id = "b02f425f-c3c5-4178-8fdf-01868b8a79da";

  protected void init() {
    session = getSession(modelName);
    context = new SessionContext(session);

    _interface1 = (Interface) context.getSemanticElement(interface1Id);
    _interface2 = (Interface) context.getSemanticElement(interface2Id);
    _interface3 = (Interface) context.getSemanticElement(interface3Id);

    _capability1 = (Capability) context.getSemanticElement(capability1Id);
    _capability2 = (Capability) context.getSemanticElement(capability2Id);
    _capability3 = (Capability) context.getSemanticElement(capability3Id);

    _subCapability1 = (Capability) context.getSemanticElement(subcapability1Id);
    _capabilityPkg = (CapabilityPkg) context.getSemanticElement(capabilityPkgId);
    _subCapabilityPkg = (CapabilityPkg) context.getSemanticElement(subCapabilityPkgId);

    _flow1 = (ExchangeItem) context.getSemanticElement(flow1Id);
    _operation1 = (ExchangeItem) context.getSemanticElement(operation1Id);
    _operation2 = (ExchangeItem) context.getSemanticElement(operation2Id);
    _operation3 = (ExchangeItem) context.getSemanticElement(operation3Id);

    _flow1ExItem1 = (ExchangeItemAllocation) context.getSemanticElement(flow1ExItem1Id);
    _oper1ExItem1 = (ExchangeItemAllocation) context.getSemanticElement(oper1ExItem1Id);
    _oper2ExItem1 = (ExchangeItemAllocation) context.getSemanticElement(oper2ExItem1Id);
    _oper3ExItem1 = (ExchangeItemAllocation) context.getSemanticElement(oper3ExItem1Id);

    _oper1SeqMsgCall = (SequenceMessage) context.getSemanticElement(seqMsgOper1Id);
    _oper1SeqMsgReply = (SequenceMessage) context.getSemanticElement(seqMsgOper1ReplyId);

    _oper2SeqMsgCall = (SequenceMessage) context.getSemanticElement(oper2SeqMsgCallId);
    _oper2SeqMsgReply = (SequenceMessage) context.getSemanticElement(oper2SeqMsgReplyId);

    _scenario1 = (Scenario) context.getSemanticElement(scenario1Id);
    _scenario2 = (Scenario) context.getSemanticElement(scenario2Id);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(modelName);
  }
}
