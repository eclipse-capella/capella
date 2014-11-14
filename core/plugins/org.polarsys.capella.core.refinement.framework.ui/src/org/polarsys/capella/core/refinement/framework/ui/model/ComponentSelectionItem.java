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
package org.polarsys.capella.core.refinement.framework.ui.model;

import java.util.List;

import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;

public class ComponentSelectionItem extends SelectionItemNode {

	/**
	 * The Component attached to this item.
	 */
	private NamedElement _data = null;

	/**
	 * Constructor
	 *
	 * @param data
	 */
	public ComponentSelectionItem(NamedElement data) {
		_data = data;
	}

	/**
	 * Constructor
	 *
	 * @param data
	 */
	public ComponentSelectionItem(List<AbstractInstance> data) {
		for (NamedElement msg : data) {
			ComponentSelectionItem child = new ComponentSelectionItem(msg);
			addChild(child);
		}
	}

	/**
	 * @see SelectionItemNode#getName()
	 *
	 * @return the name of the Component.
	 */
	@Override
	public String getName() {
		if (_data != null) {
		  if (_data instanceof AbstractTypedElement) {
		    AbstractType type = ((AbstractTypedElement) _data).getAbstractType();
		    if (type != null) {
		      return _data.getName() + ": " + type.getName(); //$NON-NLS-1$
		    }
		    return _data.getName() + ": <undefined>"; //$NON-NLS-1$
		  }
			return _data.getName();
		}
		return "<unamed>"; //$NON-NLS-1$
	}

  /**
   * @see SelectionItemNode#getFullName()
   *
   * @return the complete name of the Component.
   */
  @Override
  public String getFullName() {
    if (_data != null)
      return CapellaElementExt.getFullPath(_data);
    return "<unamed>"; //$NON-NLS-1$
  }

	/**
	 * @see SelectionItemNode#getData()
	 *
	 * @return the Component attached to this item.
	 */
	@Override
	public Object getData() {
		return _data;
	}

  /**
   * @see SelectionItemNode#getSrc()
   *
   * @return the Component attached to this item.
   */
  @Override
  public Object getSrc() {
    return null;
  }
}
