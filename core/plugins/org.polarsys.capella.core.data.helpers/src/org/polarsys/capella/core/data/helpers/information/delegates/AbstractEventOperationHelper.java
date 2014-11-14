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
package org.polarsys.capella.core.data.helpers.information.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

public class AbstractEventOperationHelper {
	private static AbstractEventOperationHelper instance;

	private AbstractEventOperationHelper() {
    // do nothing
	}

	public static AbstractEventOperationHelper getInstance(){
		if(instance == null)
			instance = new AbstractEventOperationHelper();
		return instance;
	}

	public Object doSwitch(AbstractEventOperation element_p, EStructuralFeature feature_p) {
		Object ret = null;

		if (feature_p.equals(InformationPackage.Literals.ABSTRACT_EVENT_OPERATION__INVOKING_SEQUENCE_MESSAGES)) {
			ret = getInvokingSequenceMessages(element_p);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element_p, feature_p);
		}		

		return ret;
	}

	/**
	 * 
	 */
	protected List<SequenceMessage> getInvokingSequenceMessages(AbstractEventOperation element_p) {
		List <SequenceMessage> ret = new ArrayList <SequenceMessage>();
		for (EObject evt : EObjectExt.getReferencers(element_p, InteractionPackage.Literals.EVENT_RECEIPT_OPERATION__OPERATION)) {
	    for (EObject end : EObjectExt.getReferencers(evt, InteractionPackage.Literals.ABSTRACT_END__EVENT)) {
  	    for (EObject msg : EObjectExt.getReferencers(end, InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_END)) {
    		  if (msg instanceof SequenceMessage) {
    				if (!ret.contains(msg)) {
    				  ret.add((SequenceMessage) msg);
    				}
    			}
  	    }
	    }
		}
    for (EObject evt : EObjectExt.getReferencers(element_p, InteractionPackage.Literals.EVENT_SENT_OPERATION__OPERATION)) {
      for (EObject end : EObjectExt.getReferencers(evt, InteractionPackage.Literals.ABSTRACT_END__EVENT)) {
        for (EObject msg : EObjectExt.getReferencers(end, InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_END)) {
          if (msg instanceof SequenceMessage) {
            if (!ret.contains(msg)) {
              ret.add((SequenceMessage) msg);
            }
          }
        }
      }
    }
		return ret;
	}
}
