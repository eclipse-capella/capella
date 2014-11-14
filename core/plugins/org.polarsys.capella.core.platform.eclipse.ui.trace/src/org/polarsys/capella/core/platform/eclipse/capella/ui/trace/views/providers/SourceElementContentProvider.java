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

  protected CapellaElement _currentElement;
  protected Map<TraceableElement, AbstractTrace> _parentLinkMap=new HashMap<TraceableElement, AbstractTrace>();
  protected List<Class<? extends AbstractTrace>> _traceType=new ArrayList<Class<? extends AbstractTrace>>();
  
  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
   */
  public Object[] getChildren(Object parentElement_p) {
    
    //It's a class we get all instances of trace of this type
    if(parentElement_p instanceof Class){
      // Retrieve all source traces from current element 
      List<AbstractTrace> srcTraceList = _currentElement.getIncomingTraces();
      List<AbstractTrace> result=new ArrayList<AbstractTrace>();
      for (AbstractTrace trace : srcTraceList) {
        if(trace.getClass().equals(parentElement_p)){
          result.add(trace);
        }
      }
      return result.toArray();
    }
    //It's a instance of trace we get all related NamedElement
    else if(parentElement_p instanceof Trace){
      TraceableElement result=((Trace)parentElement_p).getSourceElement();
      if (result!=null){
        //To have the parent link
          _parentLinkMap.put(result, (AbstractTrace)parentElement_p);
        return Collections.singleton(result).toArray();
      }
    }
    
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
   */
  public Object getParent(Object element_p) {
    if(element_p instanceof AbstractNamedElement){
      return _parentLinkMap.get(element_p);
    }
    else if(element_p instanceof Trace){
      return ((Trace)element_p).getClass();
    }
    //type class return root
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
   */
  public boolean hasChildren(Object element_p) {
    if(element_p instanceof AbstractNamedElement){
      return false;
    }
    else if((element_p instanceof Trace) || (element_p instanceof Class)){
      return true;
    }
    return false;
  }

  /**
   * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
   */
  public Object[] getElements(Object inputElement_p) {
    
    if(inputElement_p instanceof CapellaElement){
      _currentElement=(CapellaElement)inputElement_p;
      // Retrieve all source traces from current element 
      List<AbstractTrace> srcTraceList = ((CapellaElement)inputElement_p).getIncomingTraces();
      _traceType.clear();
      // Build the list of type trace
      for (AbstractTrace currentTrace : srcTraceList) {
        if(!_traceType.contains(currentTrace.getClass()))
          _traceType.add(currentTrace.getClass());
      }
      return _traceType.toArray();
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
  public void inputChanged(Viewer viewer_p, Object oldInput_p, Object newInput_p) {
    _currentElement=(CapellaElement)newInput_p;
    _parentLinkMap.clear();
  }
  
  /**
   * @return the traceType
   */
  public List<Class<? extends AbstractTrace>> getTraceType() {
    return _traceType;
  }
}
