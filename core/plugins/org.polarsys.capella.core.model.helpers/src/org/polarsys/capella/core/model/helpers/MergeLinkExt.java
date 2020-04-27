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

package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.interaction.MergeLink;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 *
 */
public class MergeLinkExt {

  /**
   * Finds the elements linked to the source element by a refinement relationship
   * @param sourceElement The source element
   * @return The list of refinement elements
   */
  public static List<ModelElement> findMergeElements(
  	  CapellaElement sourceElement) {
    List<ModelElement> elementList = new ArrayList<ModelElement>();
    
    EList<AbstractTrace> sourceLinkList = 
      sourceElement.getOutgoingTraces();
    
    for (AbstractTrace mergeLink : sourceLinkList) {
      if (mergeLink instanceof MergeLink) {
        TraceableElement elements = 
          mergeLink.getTargetElement();
        elementList.add(elements); 
      }
    }
    
    return elementList;    
  }

  /**
   * Retrieves the merged link of an element which are source links.
   * @param element The element to be tested
   * @return The list of {@link MergeLink} to be searched
   */
  public static MergeLink findSourceMergedLink(CapellaElement element) {
    //List<AbstractTrace> traces = element.getOutgoingTraces();
    List<AbstractTrace> traces = element.getIncomingTraces();
    AbstractTrace link = null;
    int i = 0;
    boolean found = false;
    while(i<traces.size() && !found) {
      found = traces.get(i) instanceof MergeLink;
      if(found) {
        link = traces.get(i);        
        return (MergeLink) link;
      }
      i++;
    }
    
    return null;
  }

  /**
   * Retrieves the merged link of an element which are target links.
   * @param element The element to be tested
   * @return The list of {@link MergeLink} to be searched
   */
  public static MergeLink findTargetMergedLink(CapellaElement element) {
    EList<AbstractTrace> traces = element.getOutgoingTraces();
    AbstractTrace link = null;
    int i = 0;
    boolean found = false;
    while(i<traces.size() && !found) {
      found = traces.get(i) instanceof MergeLink;
      if(found) {
        link = traces.get(i);        
        return (MergeLink) link;
      }
      i++;
    }
    
    return null;
  }

}
