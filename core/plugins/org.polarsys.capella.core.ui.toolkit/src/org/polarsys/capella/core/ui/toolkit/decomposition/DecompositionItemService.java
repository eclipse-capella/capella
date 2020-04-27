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
package org.polarsys.capella.core.ui.toolkit.decomposition;

public class DecompositionItemService {

	private String name;
	private Object value;
	private DecompositionItem parentDecompositionItem;
	private boolean used;
  private String path;

	
	public DecompositionItemService(String name, Object value, boolean used, String path) {
		setName(name);
		setValue(value);
		setUsed(used);
    setPath(path);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public DecompositionItem getParentDecompositionItem() {
		return parentDecompositionItem;
	}

	public void setParentDecompositionItem(DecompositionItem decompositionItem) {
		parentDecompositionItem = decompositionItem;
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
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

  /**
   * @return the path
   */
  public String getPath() {
    return path;
  }

  /**
   * @param path the path to set
   */
  public void setPath(String path) {
    this.path = path;
  }

}
