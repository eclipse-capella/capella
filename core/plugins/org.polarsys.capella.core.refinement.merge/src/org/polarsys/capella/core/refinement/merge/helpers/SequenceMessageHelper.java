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
package org.polarsys.capella.core.refinement.merge.helpers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.refinement.merge.exception.MergeToolException;
import org.polarsys.capella.core.refinement.merge.messages.MergeMessages;

/**
 * Utility class for SequenceMessage
 *
 */
public class SequenceMessageHelper {

  /** SEND and RECEIVE feature on SequenceMessage */
  static private List<EStructuralFeature> BOUNDS = null;
  
  /**
   * Check the position of a given {@link InteractionFragment} e.g. is sending or receiving on a {@link SequenceMessage}
   * @param sm_p the target {@link SequenceMessage}
   * @param ifrag_p the {@link InteractionFragment}
   * @return The feature whether found, <code>null</code> otherwise.
   * @throws MergeToolException
   */
  public static EStructuralFeature returnPositionOn(SequenceMessage sm_p, InteractionFragment ifrag_p) throws MergeToolException {
    
    EStructuralFeature result = null;
    
    if (null == sm_p || null == ifrag_p) {
      //TODO more explicit message
      throw new MergeToolException(MergeMessages.genericToolError);
    }
    
    for (EStructuralFeature feature: boundFeaturesList()) {
      Object target = sm_p.eGet(feature);
      if ( null != target && target.equals(ifrag_p) ) {
        result = feature;
        break;
      }
    }
    
    return result;
    
  }
  
  /** internal initialization */
  private static List<EStructuralFeature> boundFeaturesList() {
 
    if (null == BOUNDS) {
      BOUNDS = new ArrayList<EStructuralFeature>();
      BOUNDS.add(InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_END);
      BOUNDS.add(InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_END);
    }
    
    return BOUNDS;
  }
  
}
