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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * <code>SourceElementContentProvider</code> provides the source elements trace for
 * an Element.
 * 
 */
public class SourceElementContentProvider implements ITreeContentProvider {

  protected CapellaElement currentElement;
  protected Map<TraceableElement, AbstractTrace> parentLinkMap = new HashMap<TraceableElement, AbstractTrace>();
  protected List<Class<? extends AbstractTrace>> traceType = new ArrayList<Class<? extends AbstractTrace>>();
  
  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
   */
  public Object[] getChildren(Object parentElement) {
    
    //It's a class we get all instances of trace of this type
    if (parentElement instanceof Class){
      // Retrieve all source traces from current element 
      List<AbstractTrace> srcTraceList = currentElement.getIncomingTraces();
      List<AbstractTrace> result=new ArrayList<AbstractTrace>();
      for (AbstractTrace trace : srcTraceList) {
        if(trace.getClass().equals(parentElement)){
          result.add(trace);
        }
      }
      return result.toArray();
    }
    //It's a instance of trace we get all related NamedElement
    else if (parentElement instanceof Trace){
      TraceableElement result=((Trace)parentElement).getSourceElement();
      if (result!=null){
        //To have the parent link
          parentLinkMap.put(result, (AbstractTrace)parentElement);
        return Collections.singleton(result).toArray();
      }
    }
    
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
   */
  public Object getParent(Object element) {
    if (element instanceof AbstractNamedElement){
      return parentLinkMap.get(element);
    }
    else if (element instanceof Trace){
      return ((Trace)element).getClass();
    }
    //type class return root
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
   */
  public boolean hasChildren(Object element) {
    if (element instanceof AbstractNamedElement){
      return false;
    }
    else if ((element instanceof Trace) || (element instanceof Class)){
      return true;
    }
    return false;
  }

  /**
   * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
   */
  public Object[] getElements(Object inputElement) {
    
    if (inputElement instanceof CapellaElement){
      currentElement=(CapellaElement)inputElement;
      // Retrieve all source traces from current element 
      List<AbstractTrace> srcTraceList = ((CapellaElement)inputElement).getIncomingTraces();
      traceType.clear();
      // Build the list of type trace
      for (AbstractTrace currentTrace : srcTraceList) {
        if(!traceType.contains(currentTrace.getClass()))
          traceType.add(currentTrace.getClass());
      }
      return traceType.toArray();
    }
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.IContentProvider#dispose()
   */
  public void dispose() {
    // do nothing
  }

  /**
   * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
   */
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    currentElement=(CapellaElement)newInput;
    parentLinkMap.clear();
  }
  
  /**
   * @return the traceType
   */
  public List<Class<? extends AbstractTrace>> getTraceType() {
    return traceType;
  }
}
