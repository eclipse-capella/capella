/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Composite;

/**
 */
public interface IDelegatedViewer extends ISelectionProvider {
  /**
   * @param parent
   */
  public void createContainer(Composite parent);

  /**
   * @param enabled
   */
  public void setEnabled(boolean enabled);

  /**
   * @return
   */
  public ColumnViewer getColumnViewer();

  /**
   * @param parent
   * @return
   */
  public Composite getViewerGroup(Composite parent);

  /**
   * @param input
   */
  public void setInput(List<EObject> input);
}
