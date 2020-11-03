/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.viewers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

/**
 */
public interface ICellEditorProvider {
  /**
   * @param element
   * @param column
   * @return
   */
  public Object getElementValue(EObject element, int column);

  /**
   * @param element
   * @param column
   * @param value
   */
  public void modifyElement(EObject element, int column, Object value);

  /**
   * @param composite
   * @param column
   * @param element
   * @return
   */
  public CellEditor getCellEditor(Composite composite, int column, Object element);
}
