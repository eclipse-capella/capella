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
package org.polarsys.capella.common.menu.dynamic.contributions;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 *
 */
public interface IMDEMenuItemContribution {
  /**
   * 
   */
  public abstract EClass getMetaclass();

  /**
   * @param editingDomain_p
   * @param containerElement_p
   * @param createdElement_p
   * @param feature_p
   */
  public abstract Command executionContribution(EditingDomain editingDomain_p, ModelElement containerElement_p, ModelElement createdElement_p, EStructuralFeature feature_p);

  /**
   * @param modelElement_p
   * @param cls_p
   * @param feature_p
   */
  public abstract boolean selectionContribution(ModelElement modelElement_p, EClass cls_p, EStructuralFeature feature_p);
}
