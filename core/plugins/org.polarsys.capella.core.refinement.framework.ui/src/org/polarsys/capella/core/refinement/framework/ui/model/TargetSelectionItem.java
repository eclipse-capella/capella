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
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecturePkg;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;

public class TargetSelectionItem extends SelectionItemNode {

	/**
	 * The NamedElement attached to this item.
	 */
    private NamedElement _src = null;
	private NamedElement _data = null;

	/**
	 * Constructor
	 *
   * @param src_p
	 * @param data_p
	 */
	public TargetSelectionItem(NamedElement src_p, NamedElement data_p) {
		_src = src_p;
		_data = data_p;
	}

	/**
	 * Constructor
	 *
   * @param src_p
	 * @param data_p
	 */
	public TargetSelectionItem(NamedElement src_p, List<NamedElement> data_p) {
		for (NamedElement msg : data_p) {
			TargetSelectionItem child = new TargetSelectionItem(src_p, msg);
			addChild(child);
		}
	}

  /**
   * Constructor
   *
   * @param data_p
   */
  public TargetSelectionItem(Map<NamedElement, List<NamedElement>> data_p) {
    Set<NamedElement> sources = data_p.keySet();
    for (NamedElement src : sources) {
      List<NamedElement> datas = data_p.get(src);
      TargetSelectionItem child = new TargetSelectionItem(src, datas);
      child.setSrc(src);
      addChild(child);
    }
  }

	/**
	 * @see SelectionItemNode#getName()
	 *
	 * @return the name of the NamedElement.
	 */
	@Override
	public String getName() {
		if (_data != null) {
			if (_data instanceof LogicalArchitecture) {
			  EObject container = _data.eContainer();
			  if (!(container instanceof System) && !(container instanceof LogicalArchitecturePkg)) {
			    return ((NamedElement) container).getName() + "." + _data.getName(); //$NON-NLS-1$
			  }
			}
			else if (_data instanceof AbstractTypedElement) {
        AbstractType type = ((AbstractTypedElement) _data).getAbstractType();
        if (type != null) {
          return _data.getName() + ": " + type.getName(); //$NON-NLS-1$
        }
        return _data.getName() + ": <undefined>"; //$NON-NLS-1$
      }
      return _data.getName();
		}
		else if (_src != null) {
      if (_src instanceof LogicalArchitecture) {
        EObject container = _src.eContainer();
        if (!(container instanceof System) && !(container instanceof LogicalArchitecturePkg)) {
          return ((NamedElement) container).getName() + "." + _src.getName(); //$NON-NLS-1$
        }
      }
      else if (_src instanceof AbstractTypedElement) {
        AbstractType type = ((AbstractTypedElement) _src).getAbstractType();
        if (type != null) {
          return _src.getName() + ": " + type.getName(); //$NON-NLS-1$
        }
        return _src.getName() + ": <undefined>"; //$NON-NLS-1$
      }
      return _src.getName();
		}

		return "<unamed>"; //$NON-NLS-1$
	}

  /**
   * @see SelectionItemNode#getFullName()
   *
   * @return the complete name of the NamedElement.
   */
  @Override
  public String getFullName() {
    if (_data != null)
      return CapellaElementExt.getFullPathFromModel(_data);
    else if (_src != null)
      return CapellaElementExt.getFullPathFromModel(_src);
    return "<unamed>"; //$NON-NLS-1$
  }

	/**
	 * @see SelectionItemNode#getData()
	 *
	 * @return the NamedElement attached to this item.
	 */
	@Override
	public Object getData() {
		return _data;
	}

  /**
   * @see SelectionItemNode#getSrc()
   *
   * @return the NamedElement attached to this item.
   */
  @Override
  public Object getSrc() {
    return _src;
  }

  /**
   * 
   */
  public void setSrc(NamedElement src_p) {
    _src = src_p;
  }
}
