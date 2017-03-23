/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

  protected int depth;
  
  protected DummyTreeData parent;
  
  protected List<DummyTreeData> children;
  
  protected Object data;
  protected Object subData;
  
  public DummyTreeData(DummyTreeData parent, int depth) {
    this.depth = depth;
    this.parent = parent; 
    this.children = new ArrayList<DummyTreeData>();
  }
  
  public int getDepth() {
	  return depth;
  }
  
  public Object getData() {
    return data;
  }
  
  public void setData(Object data) {
    this.data = data;
  }
  
  public void setSubData(Object subData) {
    this.subData = subData;
  }
  
  public Object getSubData() {
    return subData;
  }
  
  public boolean hasSubData(){ return null!=subData;}
  
  public List<DummyTreeData> getChildren() {
    return children;
  }
  
  public DummyTreeData getParent() {
    return parent;
  }
  
  public boolean isRoot() {
    return ( null == parent );
  }
  
  public boolean hasChildren() {
    return (null != children && !children.isEmpty());
  }
  
  public void addChild(DummyTreeData child) {
    if (null == children) {
      children = new ArrayList<DummyTreeData>();
    }
    children.add(child);
  }
  
}
