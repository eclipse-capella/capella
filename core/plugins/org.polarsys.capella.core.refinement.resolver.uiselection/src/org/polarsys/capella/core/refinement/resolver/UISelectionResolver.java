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
package org.polarsys.capella.core.refinement.resolver;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.helpers.interaction.services.ExecutionEndExt;
import org.polarsys.capella.core.data.helpers.interaction.services.MessageEndExt.COMPONENT_TYPE;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.refinement.framework.ui.SelectionWizard;
import org.polarsys.capella.core.refinement.framework.ui.model.ComponentSelectionItem;
import org.polarsys.capella.core.refinement.framework.ui.model.SelectionItemNode;
import org.polarsys.capella.core.refinement.scenarios.core.datastructures.ScenarioRepresentation;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ResolverException;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IResolver;

/**
 *
 */
public class UISelectionResolver implements IResolver {

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IRefinementPlug#getName()
   */
  public Object getName() {
    return "UI Selection Resolver"; //$NON-NLS-1$
  }

  /**
   * @return returns a {@link String} representation of the {@link MessageKind} parameter
   */
  private String getString(MessageKind kind) {
    if (kind == MessageKind.ASYNCHRONOUS_CALL) {
      return "asynchronous"; //$NON-NLS-1$
    }
    else if (kind == MessageKind.SYNCHRONOUS_CALL) {
      return "synchronous"; //$NON-NLS-1$
    }
    else if (kind == MessageKind.CREATE) {
      return "create"; //$NON-NLS-1$
    }
    else if (kind == MessageKind.DELETE) {
      return "delete"; //$NON-NLS-1$
    }
    else if (kind == MessageKind.REPLY) {
      return "reply"; //$NON-NLS-1$
    }
    return ""; //$NON-NLS-1$
  }

  /**
   * @return returns a {@link String} representation of the {@link COMPONENT_TYPE} parameter
   */
  private String getString(COMPONENT_TYPE type) {
    if (type == COMPONENT_TYPE.SENDER) {
      return "SENDER"; //$NON-NLS-1$
    }
    else if (type == COMPONENT_TYPE.RECEIVER) {
      return "RECEIVER"; //$NON-NLS-1$
    }
    return ""; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IResolver#resolving(List, ScenarioRepresentation, ScenarioRepresentation, AbstractEnd, COMPONENT_TYPE)
   *
   * @param candidateAbstractInstances_p
   * @param srcMsg_p
   * @param type_p
   */
  public List<AbstractInstance> resolving(List<AbstractInstance> candidateAbstractInstances_p, ScenarioRepresentation srcTree_p, ScenarioRepresentation tgtTree_p, AbstractEnd srcMsg_p, COMPONENT_TYPE type_p) throws ResolverException
  {
    List<AbstractInstance> selectedAbstractInstances = new ArrayList<AbstractInstance>();

    if (candidateAbstractInstances_p.size() == 1) {
      selectedAbstractInstances.add(candidateAbstractInstances_p.get(0));
    }
    else if (candidateAbstractInstances_p.size() > 1) {
      String message = "An ambiguity has been detected during the refinement process.\n"; //$NON-NLS-1$
      message += "Select the "; //$NON-NLS-1$

      if (srcMsg_p instanceof MessageEnd) {
        message += getString(type_p);

        SequenceMessage msg = ((MessageEnd) srcMsg_p).getMessage();
        if (msg != null) {
          message += " of the " + getString(msg.getKind()) + " message '" + msg.getName() + "'."; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
      }
      else if (srcMsg_p instanceof ExecutionEnd) {
        SequenceMessage msg = ExecutionEndExt.getMessage((ExecutionEnd) srcMsg_p);
        if (msg != null) {
          message += "sender of the reply message '" + msg.getName() + "'."; //$NON-NLS-1$ //$NON-NLS-2$
        }
      }
      
      ComponentSelectionItem rootItem = new ComponentSelectionItem(candidateAbstractInstances_p);
      SelectionWizard wizard = new SelectionWizard(rootItem, "Capella wizard", "Refinement ambiguity resolution", message, false); //$NON-NLS-1$ //$NON-NLS-2$
      if (wizard.open() == 0) {
        SelectionItemNode selection = wizard.getSelection();
        if (selection != null) {
          selectedAbstractInstances.add((Part) selection.getData());
        }
      }
    }

    return selectedAbstractInstances;
  }
}
