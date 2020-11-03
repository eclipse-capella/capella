/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.interaction.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

public class SequenceMessageHelper {
	private static SequenceMessageHelper instance;

	private SequenceMessageHelper() {
    // do nothing
	}

	public static SequenceMessageHelper getInstance() {
		if (instance == null)
			instance = new SequenceMessageHelper();
		return instance;
	}

	public Object doSwitch(SequenceMessage element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(InteractionPackage.Literals.SEQUENCE_MESSAGE__INVOKED_OPERATION)) {
      ret = getInvokedOperation(element);
    } else if (feature.equals(InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_PART)) {
      ret = getSendingPart(element);
    } else if (feature.equals(InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_PART)) {
      ret = getReceivingPart(element);
    } else if (feature.equals(InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_FUNCTION)) {
      ret = getSendingFunction(element);
    } else if (feature.equals(InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_FUNCTION)) {
      ret = getReceivingFunction(element);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected AbstractEventOperation getInvokedOperation(SequenceMessage element) {
    if (element != null) {
      MessageEnd receiver = element.getReceivingEnd();
      if (receiver != null) {
        Event evt = receiver.getEvent();
        if (evt instanceof EventReceiptOperation) {
          return ((EventReceiptOperation) evt).getOperation();
        }
      }
      MessageEnd sender = element.getSendingEnd();
      if (sender != null) {
        Event evt = sender.getEvent();
        if (evt instanceof EventSentOperation) {
          return ((EventSentOperation) evt).getOperation();
        }
      }
    }
    return null;
  }

  protected Part getSendingPart(SequenceMessage element) {
    MessageEnd sender = element.getSendingEnd();
    if (sender != null) {
      InstanceRole role = sender.getCovered();
      if (null != role) {
        AbstractInstance inst = role.getRepresentedInstance();
        if (inst instanceof Part) {
          return (Part) inst;
        }
      }
    }
    return null;
  }

  protected Part getReceivingPart(SequenceMessage element) {
    MessageEnd sender = element.getReceivingEnd();
    if (sender != null) {
      InstanceRole role = sender.getCovered();
      if (null != role) {
        AbstractInstance inst = role.getRepresentedInstance();
        if (inst instanceof Part) {
          return (Part) inst;
        }
      }
    }
    return null;
  }

  protected AbstractFunction getSendingFunction(SequenceMessage element) {
    MessageEnd sender = element.getSendingEnd();
    if (sender != null) {
      InstanceRole role = sender.getCovered();
      if (null != role) {
        AbstractInstance inst = role.getRepresentedInstance();
        if (inst instanceof AbstractFunction) {
          return (AbstractFunction) inst;
        }
      }
    }
    return null;
  }

  protected AbstractFunction getReceivingFunction(SequenceMessage element) {
    MessageEnd sender = element.getReceivingEnd();
    if (sender != null) {
      InstanceRole role = sender.getCovered();
      if (null != role) {
        AbstractInstance inst = role.getRepresentedInstance();
        if (inst instanceof AbstractFunction) {
          return (AbstractFunction) inst;
        }
      }
    }
    return null;
  }
}
