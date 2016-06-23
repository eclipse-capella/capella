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

package org.polarsys.capella.core.platform.sirius.ui.actions;

import org.eclipse.emf.ecore.EClass;
import org.osgi.framework.BundleContext;

import org.polarsys.capella.common.ui.services.AbstractUIActivator;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.platform.sirius.ui.preferences.ActionsPreferenceInitializer;
import org.polarsys.capella.core.platform.sirius.ui.preferences.DeletePreferences;
import org.polarsys.capella.core.platform.sirius.ui.preferences.CapellaValidationPreferencesInitializer;
import org.polarsys.capella.core.platform.sirius.ui.preferences.ProtectedElementsPreferences;

/**
 * Nothing specific to implement here. There is just to take advantage of AbstractUIActivator services.
 */
public class CapellaActionsActivator extends AbstractUIActivator {

  /*
   * 
   */
  public static final String PLUGIN_ID = "org.polarsys.capella.core.platform.sirius.ui.actions"; //$NON-NLS-1$

  /**
   * Shared instance.
   */
  private static CapellaActionsActivator _plugin;

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    _plugin = this;
    new DeletePreferences();
    new CapellaValidationPreferencesInitializer();
    new ActionsPreferenceInitializer();

  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    _plugin = null;
    super.stop(context);
  }

  /**
   * Get shared instance.
   * @return
   */
  public static CapellaActionsActivator getDefault() {
    return _plugin;
  }

  /**
   * Is the given metaclass protected ?
   * @param class must be not <code>null</code>.
   * @return <code>true</code> or <code>false</code> if one of them is a protected element.
   */
  public boolean isMetaclassProtected(EClass cls) {

    boolean result = false;

    // Get the string representation from given metaclass.
    String eClassRepresentation = getPreferenceValue(cls);

    // Preference index.
    int index = 0;
    // Get a preference title according an index.
    String preferenceId = null;
    String preferenceValue = null;

    do {
      preferenceId = getPreferenceId(index);
      // Get its value from the preference store.
      preferenceValue = AbstractPreferencesInitializer.getString(preferenceId, true);
      // Is retrieved preference value equals to given metaclass representation ?
      if (eClassRepresentation.equals(preferenceValue)) {
        // Found, let's get if the preference is checked or not (checked means not deletable).
        result = AbstractPreferencesInitializer.getBoolean(getPreference(index), true);
        break;
      }
      // Increment and recompute value.
      index++;
    } while ((preferenceValue != null) && (preferenceValue != ICommonConstants.EMPTY_STRING));

    return result;
  }

  /**
   * Get protected element preference with given index.
   * @param index
   * @return a not <code>null</code> string.
   */
  public String getPreference(int index) {
    return new StringBuilder(ProtectedElementsPreferences.PREFERENCE_DELETE_RESTRICTION).append(index).toString();
  }

  /**
   * Get protected element title preference with given index.
   * @param index
   * @return a not <code>null</code> string.
   */
  public String getPreferenceId(int index) {
    return new StringBuilder(ProtectedElementsPreferences.PREFERENCE_DELETE_RESTRICTION).append(index).append("_Id").toString(); //$NON-NLS-1$
  }

  /**
   * Get preference representation value for given {@link EClass}.
   * @param class
   * @return a not <code>null</code> string.
   */
  public String getPreferenceValue(EClass cls) {
    return cls.getName();
  }

  /**
   * Get protected element title preference with given index.
   * @param index
   * @return a not <code>null</code> string.
   */
  public String getPreferenceTitle(int index) {
    return new StringBuilder(ProtectedElementsPreferences.PREFERENCE_DELETE_RESTRICTION).append(index).append("_Title").toString(); //$NON-NLS-1$
  }

}
