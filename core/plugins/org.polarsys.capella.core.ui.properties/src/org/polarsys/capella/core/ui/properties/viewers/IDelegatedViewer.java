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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Composite;

/**
 */
public interface IDelegatedViewer extends ISelectionProvider {
  /**
   * @param parent_p
   */
  public void createContainer(Composite parent_p);

  /**
   * @param enabled_p
   */
  public void setEnabled(boolean enabled_p);

  /**
   * @return
   */
  public ColumnViewer getColumnViewer();

  /**
   * @param parent_p
   * @return
   */
  public Composite getViewerGroup(Composite parent_p);

  /**
   * @param input_p
   */
  public void setInput(List<EObject> input_p);
}
