/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
  private NamedElement src = null;
	private NamedElement data = null;

	/**
	 * Constructor
	 *
   * @param src
	 * @param data
	 */
	public TargetSelectionItem(NamedElement src, NamedElement data) {
		this.src = src;
		this.data = data;
	}

	/**
	 * Constructor
	 *
   * @param src
	 * @param data
	 */
	public TargetSelectionItem(NamedElement src, List<NamedElement> data) {
		for (NamedElement msg : data) {
			TargetSelectionItem child = new TargetSelectionItem(src, msg);
			addChild(child);
		}
	}

  /**
   * Constructor
   *
   * @param data
   */
  public TargetSelectionItem(Map<NamedElement, List<NamedElement>> data) {
    Set<NamedElement> sources = data.keySet();
    for (NamedElement src : sources) {
      List<NamedElement> datas = data.get(src);
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
		if (data != null) {
			if (data instanceof LogicalArchitecture) {
			  EObject container = data.eContainer();
			  if (!(container instanceof System) && !(container instanceof LogicalArchitecturePkg)) {
			    return ((NamedElement) container).getName() + "." + data.getName(); //$NON-NLS-1$
			  }
			}
			else if (data instanceof AbstractTypedElement) {
        AbstractType type = ((AbstractTypedElement) data).getAbstractType();
        if (type != null) {
          return data.getName() + ": " + type.getName(); //$NON-NLS-1$
        }
        return data.getName() + ": <undefined>"; //$NON-NLS-1$
      }
      return data.getName();
		}
		else if (src != null) {
      if (src instanceof LogicalArchitecture) {
        EObject container = src.eContainer();
        if (!(container instanceof System) && !(container instanceof LogicalArchitecturePkg)) {
          return ((NamedElement) container).getName() + "." + src.getName(); //$NON-NLS-1$
        }
      }
      else if (src instanceof AbstractTypedElement) {
        AbstractType type = ((AbstractTypedElement) src).getAbstractType();
        if (type != null) {
          return src.getName() + ": " + type.getName(); //$NON-NLS-1$
        }
        return src.getName() + ": <undefined>"; //$NON-NLS-1$
      }
      return src.getName();
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
    if (data != null)
      return CapellaElementExt.getFullPathFromModel(data);
    else if (src != null)
      return CapellaElementExt.getFullPathFromModel(src);
    return "<unamed>"; //$NON-NLS-1$
  }

	/**
	 * @see SelectionItemNode#getData()
	 *
	 * @return the NamedElement attached to this item.
	 */
	@Override
	public Object getData() {
		return data;
	}

  /**
   * @see SelectionItemNode#getSrc()
   *
   * @return the NamedElement attached to this item.
   */
  @Override
  public Object getSrc() {
    return src;
  }

  /**
   * 
   */
  public void setSrc(NamedElement src) {
    this.src = src;
  }
}
