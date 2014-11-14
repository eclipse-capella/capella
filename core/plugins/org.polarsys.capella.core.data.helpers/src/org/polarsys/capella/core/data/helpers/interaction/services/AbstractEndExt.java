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
package org.polarsys.capella.core.data.helpers.interaction.services;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 */
public class AbstractEndExt {

  /**
   * @param msg_p
   * @return the operation
   */
  static public AbstractEventOperation getOperation(AbstractEnd msg_p) {
    if (msg_p instanceof ExecutionEnd) {
      return ExecutionEndExt.getOperation((ExecutionEnd) msg_p);
    } else if (msg_p instanceof MessageEnd) {
      return MessageEndExt.getOperation((MessageEnd) msg_p);
    }
    return null;
  }

  /**
   * Gets the component or signal from abstract end
   * @param abstractEnd_p the abstract end
   * @return the component or signal associated with the abstract end
   */
  static public NamedElement getComponent(AbstractEnd abstractEnd_p) {
    if (null != abstractEnd_p) {
      InstanceRole instanceRole = abstractEnd_p.getCovered();
      if (null != instanceRole) {
        AbstractInstance componentInstance = instanceRole.getRepresentedInstance();
        if (null != componentInstance) {
          if (null != componentInstance.getAbstractType()) {
            return (NamedElement) componentInstance.getAbstractType();
          }
        }
      }
    }
    return null;
  }

  /**
   * Compare two {@link AbstractEnd}s in function of the {@link AbstractType} of theirs {@link InstanceRole} and related {@link Event}.
   * @param left_p first {@link AbstractEnd} to compare
   * @param right_p second {@link AbstractEnd} to compare
   * @return <code>true</code> is they are considered as similar, <code>false</code> otherwise.
   * @throws Merge2ToolException
   */
  public static boolean compareAbstractEnd(AbstractEnd left_p, AbstractEnd right_p) {

    boolean test = true;

    EClass eClass = left_p.eClass();

    //
    // Let's test the type of AbstractEnd
    //
    if (eClass != right_p.eClass()) {
      return false;
    }

    //
    // We need to differentiate AbstractEnd
    //

    if (eClass == InteractionPackage.Literals.EXECUTION_END) {
      //
      // ExecutionEnd case
      //

      // can not say anything more...
      test = true;

    } else if (eClass == InteractionPackage.Literals.MESSAGE_END) {
      //
      // MessageEnd case
      //

      //
      // type of event
      //
      Event evt_left = left_p.getEvent();
      Event evt_right = right_p.getEvent();

      // We do not allow null events
      if ((null == evt_left) || (null == evt_right)) {
        return false;
      }

      if (evt_left.eClass() != evt_right.eClass()) {
        return false;
      }

      //
      // At last, Services...
      //
      if (evt_left.eClass() == InteractionPackage.Literals.EVENT_SENT_OPERATION) {
        AbstractEventOperation s_left = ((EventSentOperation) evt_left).getOperation();
        AbstractEventOperation s_right = ((EventSentOperation) evt_right).getOperation();
        test = s_left.equals(s_right);
      } else if (evt_left.eClass() == InteractionPackage.Literals.EVENT_RECEIPT_OPERATION) {
        AbstractEventOperation s_left = ((EventReceiptOperation) evt_left).getOperation();
        AbstractEventOperation s_right = ((EventReceiptOperation) evt_right).getOperation();
        test = s_left.equals(s_right);
      } else { // could such case happened??
        return false;
      }

      if (!test) {
        return false;
      }

    } else { // could such case happened??
      return false;
    }

    return true;
  }

  /**
   * Compare if two {@link InstanceRole}s have the same {@link AbstractInstance}.
   * @param left_p the first {@link InstanceRole} to compare
   * @param right_p the second {@link InstanceRole} to compare
   * @return <code>true</code> if yes, <code>false</code> otherwise
   */
  public static boolean compareInstanceRole(InstanceRole left_p, InstanceRole right_p) {

    boolean result = true;

    AbstractInstance ai_left = left_p.getRepresentedInstance();
    AbstractInstance ai_right = right_p.getRepresentedInstance();

    result = ai_left.equals(ai_right);

    return result;
  }

  /**
   * Gets the InstanceRole associated to the given AbstractEnd.
   * @param abstractEnd_p
   * @return an InstanceRole or <code>null</code> if the given AbstractEnd is <code>null</code> or has no InstanceRole.
   */
  public static InstanceRole getInstanceRole(AbstractEnd abstractEnd_p) {
    InstanceRole instanceRole = null;
    if (null != abstractEnd_p) {
      instanceRole = abstractEnd_p.getCovered();
    }
    return instanceRole;
  }
}
