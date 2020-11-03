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
   * @param msg
   * @return the operation
   */
  static public AbstractEventOperation getOperation(AbstractEnd msg) {
    if (msg instanceof ExecutionEnd) {
      return ExecutionEndExt.getOperation((ExecutionEnd) msg);
    } else if (msg instanceof MessageEnd) {
      return MessageEndExt.getOperation((MessageEnd) msg);
    }
    return null;
  }

  /**
   * Gets the component or signal from abstract end
   * @param abstractEnd the abstract end
   * @return the component or signal associated with the abstract end
   */
  public static NamedElement getComponent(AbstractEnd abstractEnd) {
    if (null != abstractEnd) {
      InstanceRole instanceRole = abstractEnd.getCovered();
      if (null != instanceRole) {
        AbstractInstance componentInstance = instanceRole.getRepresentedInstance();
        if (null != componentInstance && null != componentInstance.getAbstractType()) {
          return (NamedElement) componentInstance.getAbstractType();
        }
      }
    }
    return null;
  }

  /**
   * Compare two {@link AbstractEnd}s in function of the {@link AbstractType} of theirs {@link InstanceRole} and related {@link Event}.
   * @param left first {@link AbstractEnd} to compare
   * @param right second {@link AbstractEnd} to compare
   * @return <code>true</code> is they are considered as similar, <code>false</code> otherwise.
   * @throws Merge2ToolException
   */
  public static boolean compareAbstractEnd(AbstractEnd left, AbstractEnd right) {

    boolean test = true;

    EClass eClass = left.eClass();

    //
    // Let's test the type of AbstractEnd
    //
    if (eClass != right.eClass()) {
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
      Event evt_left = left.getEvent();
      Event evt_right = right.getEvent();

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
   * @param left the first {@link InstanceRole} to compare
   * @param right the second {@link InstanceRole} to compare
   * @return <code>true</code> if yes, <code>false</code> otherwise
   */
  public static boolean compareInstanceRole(InstanceRole left, InstanceRole right) {

    boolean result = true;

    AbstractInstance ai_left = left.getRepresentedInstance();
    AbstractInstance ai_right = right.getRepresentedInstance();

    result = ai_left.equals(ai_right);

    return result;
  }

  /**
   * Gets the InstanceRole associated to the given AbstractEnd.
   * @param abstractEnd
   * @return an InstanceRole or <code>null</code> if the given AbstractEnd is <code>null</code> or has no InstanceRole.
   */
  public static InstanceRole getInstanceRole(AbstractEnd abstractEnd) {
    InstanceRole instanceRole = null;
    if (null != abstractEnd) {
      instanceRole = abstractEnd.getCovered();
    }
    return instanceRole;
  }
}
