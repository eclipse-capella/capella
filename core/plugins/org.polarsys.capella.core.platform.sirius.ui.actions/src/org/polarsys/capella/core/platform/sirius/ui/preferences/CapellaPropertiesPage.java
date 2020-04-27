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

package org.polarsys.capella.core.platform.sirius.ui.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaActionsActivator;

/**
 * Main Capella properties page.
 */
public class CapellaPropertiesPage extends CapellaPreferencePage implements IWorkbenchPropertyPage {
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
  public void setElement(IAdaptable element) {
    if (element instanceof IProject) {
      project = (IProject) element;
    }
  }
}
