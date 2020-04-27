/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
   * @param editingDomain
   * @param containerElement
   * @param createdElement
   * @param feature
   */
  public abstract Command executionContribution(EditingDomain editingDomain, ModelElement containerElement, ModelElement createdElement, EStructuralFeature feature);

  /**
   * @param modelElement
   * @param cls
   * @param feature
   */
  public abstract boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature);
}
