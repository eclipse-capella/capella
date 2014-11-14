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
package org.polarsys.capella.core.platform.sirius.ui.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaActionsActivator;

/**
 * Delete properties page.
 */
public class DeletePropertiesPage extends DeletePreferencePage implements IWorkbenchPropertyPage {
  /**
   * Storage for preferences.
   */
  private ScopedPreferenceStore preferenceStore;

  private IProject project;

  /**
   * @see org.eclipse.jface.preference.PreferencePage#doGetPreferenceStore()
   */
  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    if (preferenceStore == null) {
      preferenceStore = new ScopedPreferenceStore(new ProjectScope(project), CapellaActionsActivator.getDefault().getBundle().getSymbolicName());
    }
    return preferenceStore;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IAdaptable getElement() {
    return project;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setElement(IAdaptable element_p) {
    if (element_p instanceof IProject) {
      project = (IProject) element_p;
    }
  }
}
