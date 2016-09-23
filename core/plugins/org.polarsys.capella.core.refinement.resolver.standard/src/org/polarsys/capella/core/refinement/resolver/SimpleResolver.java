/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.resolver;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.helpers.interaction.services.ExecutionEndExt;
import org.polarsys.capella.core.data.helpers.interaction.services.MessageEndExt.COMPONENT_TYPE;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.model.helpers.SequenceMessageExt;
import org.polarsys.capella.core.refinement.scenarios.core.datastructures.Node;
import org.polarsys.capella.core.refinement.scenarios.core.datastructures.ScenarioRepresentation;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ResolverException;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IResolver;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * 
 */
public class SimpleResolver implements IResolver {

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IRefinementPlug#getName()
   */
  public Object getName() {
    return "Simple Resolver"; //$NON-NLS-1$
  }

  /**
   * The strategy implemented to try to resolve some of the remaining ambiguities is as follows:<BR> 
   * We consider that for a given MessageEnd, if it is of type 'RECEIVER',<BR>
   * the MessageEnd 'SENDER has already been refined in the preceding iteration. <BR> 
   * So we recover the source MessageEnd and we map the Message.<BR>
   *
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IResolver#resolving(java.util.List,
   *      org.polarsys.capella.core.common.model.MessageEnd, org.polarsys.capella.core.data.helpers.interaction.services.model.helpers.MessageEndExt.COMPONENT_TYPE)
   */
  public List<AbstractInstance> resolving(List<AbstractInstance> candidateAbstractInstances, ScenarioRepresentation srcTree, ScenarioRepresentation tgtTree, AbstractEnd srcMsg, COMPONENT_TYPE cpntType)
      throws ResolverException {
    AbstractInstance retrievedAbstractInstance = null;
    List<AbstractInstance> selectedAbstractInstances = new ArrayList<AbstractInstance>();

    if (candidateAbstractInstances != null) {
      if (srcMsg instanceof MessageEnd) {
        SequenceMessage msg = ((MessageEnd) srcMsg).getMessage();
        if (msg != null) {
          if (msg.getKind() == MessageKind.REPLY) {
            SequenceMessage oppositeMsg = SequenceMessageExt.getOppositeSequenceMessage(msg);
            if (oppositeMsg != null) {
              MessageEnd srcOppositeMsgEnd = null;
              if (cpntType == COMPONENT_TYPE.SENDER) {
                srcOppositeMsgEnd = oppositeMsg.getSendingEnd();
              }
              else if (cpntType == COMPONENT_TYPE.RECEIVER) {
                srcOppositeMsgEnd = oppositeMsg.getReceivingEnd();
              }

              if (srcOppositeMsgEnd != null) {
                Node<InteractionFragment> srcOppositeMsgEndNode = srcTree.getNodeByData(srcOppositeMsgEnd);
                Node<InteractionFragment> tgtOppositeMsgEndNode = srcOppositeMsgEndNode.relatedNode;

                InteractionFragment tgtOppositeMsgEnd = tgtOppositeMsgEndNode.getData();
                if (tgtOppositeMsgEnd instanceof AbstractEnd) {
                  AbstractInstance inst = ((AbstractEnd) tgtOppositeMsgEnd).getCovered().getRepresentedInstance();
                  if (inst != null) {
                	AbstractType type = ((Part) inst).getAbstractType();
                    if ((type != null) && (type instanceof Component)) {
                      retrievedAbstractInstance = inst;
                    }
                  }
                }
              }
            }
          }
        }
      }
      else if (srcMsg instanceof ExecutionEnd) {
        SequenceMessage msg = ExecutionEndExt.getMessage((ExecutionEnd) srcMsg);
        if (msg != null) {
          MessageEnd srcMsgEnd = msg.getReceivingEnd();
          if (srcMsgEnd != null) {
            Node<InteractionFragment> srcMsgEndNode = srcTree.getNodeByData(srcMsgEnd);
            Node<InteractionFragment> tgtMsgEndNode = srcMsgEndNode.relatedNode;

            InteractionFragment tgtMsgEnd = tgtMsgEndNode.getData();
            if (tgtMsgEnd instanceof AbstractEnd) {
              AbstractInstance inst = ((AbstractEnd) tgtMsgEnd).getCovered().getRepresentedInstance();
              if (inst != null) {
            	AbstractType type = ((Part) inst).getAbstractType();
                if ((type != null) && (type instanceof Component)) {
                  retrievedAbstractInstance = inst;
                }
              }
            }
          }
        }
      }

      if (retrievedAbstractInstance != null) {
        selectedAbstractInstances.add(retrievedAbstractInstance);
      }
      else {
        selectedAbstractInstances.addAll(candidateAbstractInstances);
      }
    }

    return selectedAbstractInstances;
  }
}
