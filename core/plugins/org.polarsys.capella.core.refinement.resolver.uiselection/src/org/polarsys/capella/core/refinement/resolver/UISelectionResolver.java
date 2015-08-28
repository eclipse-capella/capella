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
package org.polarsys.capella.core.refinement.resolver;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.helpers.interaction.services.ExecutionEndExt;
import org.polarsys.capella.core.data.helpers.interaction.services.MessageEndExt.COMPONENT_TYPE;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecturePkg;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
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
    } else if (kind == MessageKind.SYNCHRONOUS_CALL) {
      return "synchronous"; //$NON-NLS-1$
    } else if (kind == MessageKind.CREATE) {
      return "create"; //$NON-NLS-1$
    } else if (kind == MessageKind.DELETE) {
      return "delete"; //$NON-NLS-1$
    } else if (kind == MessageKind.REPLY) {
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
    } else if (type == COMPONENT_TYPE.RECEIVER) {
      return "RECEIVER"; //$NON-NLS-1$
    }
    return ""; //$NON-NLS-1$
  }

  private String getCustomizeStartMessage(COMPONENT_TYPE type) {
    if (type == COMPONENT_TYPE.SENDER) {
      return "received by"; //$NON-NLS-1$
    } else if (type == COMPONENT_TYPE.RECEIVER) {
      return "sent by"; //$NON-NLS-1$
    }
    return ""; //$NON-NLS-1$
  }

  private String getCustomizeEndMessage(COMPONENT_TYPE type) {
    if (type == COMPONENT_TYPE.SENDER) {
      return "and sent by"; //$NON-NLS-1$
    } else if (type == COMPONENT_TYPE.RECEIVER) {
      return "and received by"; //$NON-NLS-1$
    }
    return ""; //$NON-NLS-1$
  }

  private String getMessageStartName(SequenceMessage sm, COMPONENT_TYPE type) {
    if (type == COMPONENT_TYPE.SENDER) {
      return getPartName(sm.getReceivingPart()); //$NON-NLS-1$
    } else if (type == COMPONENT_TYPE.RECEIVER) {
      return getPartName(sm.getSendingPart()); //$NON-NLS-1$
    }
    return ""; //$NON-NLS-1$
  }

  private String getMessageName(SequenceMessage sm) {
    if (sm.getName().equals("")) {
      if (sm.getInvokedOperation() == null)
        return "";
      return sm.getInvokedOperation().getName() == null ? "" : sm.getInvokedOperation().getName();
    }
    return sm.getName();
  }

  private String getMessageEndName(SequenceMessage sm, COMPONENT_TYPE type) {
    if (type == COMPONENT_TYPE.SENDER) {
      return getPartName(sm.getSendingPart()); //$NON-NLS-1$
    } else if (type == COMPONENT_TYPE.RECEIVER) {
      return getPartName(sm.getReceivingPart()); //$NON-NLS-1$
    }
    return ""; //$NON-NLS-1$
  }

  public String getPartName(NamedElement _data) {
    if (_data != null) {
      if (_data instanceof LogicalArchitecture) {
        EObject container = _data.eContainer();
        if (!(container instanceof System) && !(container instanceof LogicalArchitecturePkg)) {
          return ((NamedElement) container).getName() + "." + _data.getName(); //$NON-NLS-1$
        }
      } else if (_data instanceof AbstractTypedElement) {
        AbstractType type = ((AbstractTypedElement) _data).getAbstractType();
        if (type != null) {
          return _data.getName() + ": " + type.getName(); //$NON-NLS-1$
        }
        return _data.getName() + ": <undefined>"; //$NON-NLS-1$
      }
      return _data.getName();
    }
    return "<unamed>"; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IResolver#resolving(List, ScenarioRepresentation,
   *      ScenarioRepresentation, AbstractEnd, COMPONENT_TYPE)
   *
   * @param candidateAbstractInstances
   * @param srcMsg
   * @param type
   */
  public List<AbstractInstance> resolving(List<AbstractInstance> candidateAbstractInstances,
      ScenarioRepresentation srcTree, ScenarioRepresentation tgtTree, AbstractEnd srcMsg, COMPONENT_TYPE type)
      throws ResolverException {
    List<AbstractInstance> selectedAbstractInstances = new ArrayList<AbstractInstance>();

    if (candidateAbstractInstances.size() == 1) {
      selectedAbstractInstances.add(candidateAbstractInstances.get(0));
    } else if (candidateAbstractInstances.size() > 1) {
      String message = "An ambiguity has been detected during the refinement process.\n"; //$NON-NLS-1$
      message += "Select the "; //$NON-NLS-1$

      if (srcMsg instanceof MessageEnd) {
        message += getString(type);

        SequenceMessage msg = ((MessageEnd) srcMsg).getMessage();
        if (msg != null) {
          message += " of the " + getString(msg.getKind()) + " message \"" + getMessageName(msg) + "\" "
              + getCustomizeStartMessage(type) + " \"" + getMessageStartName(msg, type) + "\" "
              + getCustomizeEndMessage(type) + " \"" + getMessageEndName(msg, type) + "\" from \""
              + CapellaElementExt.getFullPath((Scenario) msg.eContainer()) + "\".";
        }
      } else if (srcMsg instanceof ExecutionEnd) {
        SequenceMessage msg = ExecutionEndExt.getMessage((ExecutionEnd) srcMsg);
        if (msg != null) {
          message += "sender of the reply message '" + getMessageName(msg) + "\" " + getCustomizeStartMessage(type)
              + " \"" + getMessageStartName(msg, type) + "\" " + getCustomizeEndMessage(type) + " \""
              + getMessageEndName(msg, type) + "\" from \""
              + CapellaElementExt.getFullPath((Scenario) msg.eContainer()) + "\".";
        }
      }

      ComponentSelectionItem rootItem = new ComponentSelectionItem(candidateAbstractInstances);
      SelectionWizard wizard = new SelectionWizard(rootItem,
          "Capella wizard", "Refinement ambiguity resolution", message, false); //$NON-NLS-1$ //$NON-NLS-2$
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
