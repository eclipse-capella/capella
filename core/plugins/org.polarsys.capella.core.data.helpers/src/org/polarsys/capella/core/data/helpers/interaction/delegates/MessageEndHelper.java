/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

public class MessageEndHelper {
	private static MessageEndHelper instance;

	private MessageEndHelper() {
    // do nothing
	}

	public static MessageEndHelper getInstance() {
		if (instance == null)
			instance = new MessageEndHelper();
		return instance;
	}

	public Object doSwitch(MessageEnd element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(InteractionPackage.Literals.MESSAGE_END__MESSAGE)) {
      ret = getMessage(element);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = AbstractEndHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected SequenceMessage getMessage(MessageEnd element) {
    if (element != null) {
      EObject owner = element.eContainer();
      if (owner instanceof InteractionOperand) {
        owner = owner.eContainer();
      }
      if (owner instanceof CombinedFragment) {
        owner = owner.eContainer();
      }
      if (owner instanceof Scenario) {
        for (SequenceMessage message : ((Scenario) owner).getOwnedMessages()) {
          if (element.equals(message.getSendingEnd())
           || element.equals(message.getReceivingEnd()))
          {
            return message;
          }
        }
      }
    }
    return null;
  }
}
