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
package org.polarsys.capella.common.command.recorder.core.writer;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple tree representation
 */
public class DummyTreeData {

  protected int _depth;
  
  protected DummyTreeData _parent;
  
  protected List<DummyTreeData> _children;
  
  protected Object _data;
  protected Object _subData;
  
  public DummyTreeData(DummyTreeData parent, int depth) {
    
    _depth = depth;
    _parent = parent; 
    _children = new ArrayList<DummyTreeData>();
    
  }
  
  public int getDepth() {
	  return _depth;
  }
  
  public Object getData() {
    return _data;
  }
  
  public void setData(Object data_p) {
    _data = data_p;
    return;
  }
  
  public void setSubData(Object subData_p) {
	_subData = subData_p;
	return;
  }
  
  public Object getSubData() {
    return _subData;
  }
  
  public boolean hasSubData(){ return null!=_subData;}
  
  public List<DummyTreeData> getChildren() {
    return _children;
  }
  
  public DummyTreeData getParent() {
    return _parent;
  }
  
  public boolean isRoot() {
    return ( null == _parent );
  }
  
  public boolean hasChildren() {
    return (null != _children && !_children.isEmpty());
  }
  
  public void addChild(DummyTreeData child_p) {

    if (null == _children) {
      _children = new ArrayList<DummyTreeData>();
    }
    _children.add(child_p);

    return;

  }
  
}
