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
package org.polarsys.capella.core.commands.preferences.ui.sirius;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.sirius.diagram.ui.internal.preferences.DiagramAppearancePreferencePage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.polarsys.capella.core.commands.preferences.service.IAbstractDefaultPreferencePage;

public class DiagramAppearancePropertyPreferencePage extends DiagramAppearancePreferencePage implements IAbstractDefaultPreferencePage, IWorkbenchPropertyPage {

  public DiagramAppearancePropertyPreferencePage() {
  }

  @Override
  protected Control createContents(Composite parent_p) {
    Control page = super.createContents(parent_p);
    return page;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IAdaptable getElement() {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setElement(IAdaptable element_p) {

  }

}
