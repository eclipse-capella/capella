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
package org.polarsys.capella.core.ui.toolkit.decomposition;

public class DecompositionItemService {

	private String _name;
	private Object _value;
	private DecompositionItem _parentDecompositionItem;
	private boolean _used;
  private String _path;

	
	public DecompositionItemService(String name_p, Object value_p, boolean used_p, String path_p) {
		setName(name_p);
		setValue(value_p);
		setUsed(used_p);
    setPath(path_p);
	}
	
	public String getName() {
		return _name;
	}

	public void setName(String name_p) {
		this._name = name_p;
	}

	public Object getValue() {
		return _value;
	}

	public void setValue(Object value_p) {
		this._value = value_p;
	}

	public DecompositionItem getParentDecompositionItem() {
		return _parentDecompositionItem;
	}

	public void setParentDecompositionItem(DecompositionItem decompositionItem_p) {
		_parentDecompositionItem = decompositionItem_p;
	}

	@Override
	public String toString() {
		return getName();
	}

	/**
	 * Clones the current {@link DecompositionItemService} 
	 */
	public DecompositionItemService getCopy() {
		DecompositionItemService copy = new DecompositionItemService(this.getName(), this.getValue(), this.isUsed(), this.getPath());
		return copy;
	}

	public boolean isUsed() {
		return _used;
	}

	public void setUsed(boolean used_p) {
		this._used = used_p;
	}

  /**
   * @return the path
   */
  public String getPath() {
    return _path;
  }

  /**
   * @param path_p the path to set
   */
  public void setPath(String path_p) {
    _path = path_p;
  }

}
