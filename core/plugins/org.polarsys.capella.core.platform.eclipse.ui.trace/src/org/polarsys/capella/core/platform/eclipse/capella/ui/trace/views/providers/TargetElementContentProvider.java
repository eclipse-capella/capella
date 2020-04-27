/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * <code>SourceElementContentProvider</code> provides the source elements trace for
 * a NamedElement.
 * 
 */
public class TargetElementContentProvider extends SourceElementContentProvider {

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
   */
  @Override
  public Object[] getChildren(Object parentElement) {
    //It's a class we get all instances of trace of this type
    if (parentElement instanceof Class){
      // Retrieve all source traces from current element 
      List<AbstractTrace> targetTraceList = currentElement.getOutgoingTraces();
      List<AbstractTrace> result=new ArrayList<AbstractTrace>();
      for (AbstractTrace trace : targetTraceList) {
        if(trace.getClass().equals(parentElement)){
          result.add(trace);
        }
      }
      return result.toArray();
    }
    //It's a instance of trace we get all related NamedElement
    else if (parentElement instanceof Trace){
      TraceableElement result=((AbstractTrace) parentElement).getTargetElement();
      if (result!=null){
        //To have the parent link
          parentLinkMap.put(result, (Trace)parentElement);
        return Collections.singletonList(result).toArray();
      }
    }
    
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
   */
  @Override
  public Object[] getElements(Object inputElement) {
    
    if (inputElement instanceof CapellaElement){
      currentElement=(CapellaElement) inputElement;
      // Retrieve all source traces from current element 
      List<AbstractTrace> targetTraceList = ((CapellaElement) inputElement).getOutgoingTraces();
      traceType=new ArrayList<Class<? extends AbstractTrace>>();
      // Build the list of type trace
      for (AbstractTrace currentTrace : targetTraceList) {
        if(!traceType.contains(currentTrace.getClass()))
          traceType.add(currentTrace.getClass());
      }
      return traceType.toArray();
    }
    return null;
  }
}
