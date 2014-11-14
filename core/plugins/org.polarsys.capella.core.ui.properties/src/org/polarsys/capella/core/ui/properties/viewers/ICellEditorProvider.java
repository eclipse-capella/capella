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
package org.polarsys.capella.core.ui.properties.viewers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

/**
 */
public interface ICellEditorProvider {
  /**
   * @param element_p
   * @param column_p
   * @return
   */
  public Object getElementValue(EObject element_p, int column_p);

  /**
   * @param element_p
   * @param column_p
   * @param value_p
   */
  public void modifyElement(EObject element_p, int column_p, Object value_p);

  /**
   * @param composite_p
   * @param column_p
   * @param element_p
   * @return
   */
  public CellEditor getCellEditor(Composite composite_p, int column_p, Object element_p);
}
