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

	public Object doSwitch(SequenceMessage element_p, EStructuralFeature feature_p) {
		Object ret = null;

    if (feature_p.equals(InteractionPackage.Literals.SEQUENCE_MESSAGE__INVOKED_OPERATION)) {
      ret = getInvokedOperation(element_p);
    } else if (feature_p.equals(InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_PART)) {
      ret = getSendingPart(element_p);
    } else if (feature_p.equals(InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_PART)) {
      ret = getReceivingPart(element_p);
    } else if (feature_p.equals(InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_FUNCTION)) {
      ret = getSendingFunction(element_p);
    } else if (feature_p.equals(InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_FUNCTION)) {
      ret = getReceivingFunction(element_p);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

  protected AbstractEventOperation getInvokedOperation(SequenceMessage element_p) {
    if (element_p != null) {
      MessageEnd receiver = element_p.getReceivingEnd();
      if (receiver != null) {
        Event evt = receiver.getEvent();
        if (evt instanceof EventReceiptOperation) {
          return ((EventReceiptOperation) evt).getOperation();
        }
      }
      MessageEnd sender = element_p.getSendingEnd();
      if (sender != null) {
        Event evt = sender.getEvent();
        if (evt instanceof EventSentOperation) {
          return ((EventSentOperation) evt).getOperation();
        }
      }
    }
    return null;
  }

  protected Part getSendingPart(SequenceMessage element_p) {
    MessageEnd sender = element_p.getSendingEnd();
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

  protected Part getReceivingPart(SequenceMessage element_p) {
    MessageEnd sender = element_p.getReceivingEnd();
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

  protected AbstractFunction getSendingFunction(SequenceMessage element_p) {
    MessageEnd sender = element_p.getSendingEnd();
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

  protected AbstractFunction getReceivingFunction(SequenceMessage element_p) {
    MessageEnd sender = element_p.getReceivingEnd();
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
