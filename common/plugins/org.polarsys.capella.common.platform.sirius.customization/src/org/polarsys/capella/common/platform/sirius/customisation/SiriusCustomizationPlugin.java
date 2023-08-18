/*******************************************************************************
 * Copyright (c) 2006, 2023 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *  Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.platform.sirius.customisation;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.internal.preferences.DefaultPreferences;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.gmf.runtime.notation.JumpLinkStatus;
import org.eclipse.gmf.runtime.notation.JumpLinkType;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramCorePreferences;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.polarsys.capella.common.platform.sirius.customisation.uicallback.SiriusUiCallBack;

/**
 * The activator class controls the plug-in life cycle.
 * 
 */
public class SiriusCustomizationPlugin extends AbstractUIPlugin {

  /** The plug-in ID. */
  public static final String PLUGIN_ID = "org.polarsys.capella.common.platform.sirius.customisation"; //$NON-NLS-1$

  // The shared instance
  private static SiriusCustomizationPlugin plugin;

  /**
   * The default constructor.
   */
  public SiriusCustomizationPlugin() {
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
    // Do all initialization inside a try-catch block to ensure plug-in start normally
    try {
      // Initialize the preferences for Sirius
      PlatformUI.getPreferenceStore().setDefault(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN, false);

      // Change the UICallBack to have a specific Session displayed name
      SiriusEditPlugin.getPlugin().setUiCallback(new SiriusUiCallBack(SiriusEditPlugin.getPlugin().getUiCallback()));

      customizeSiriusDefaultPreferences();
    } catch (Exception e) {
      getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, e.getMessage(), e));
    }
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance.
   * 
   * @return the shared instance
   */
  public static SiriusCustomizationPlugin getDefault() {
    return plugin;
  }

  private void customizeSiriusDefaultPreferences() {
    // ----------------
    
    // Preference customization for plugin "org.eclipse.sirius"
    IEclipsePreferences siriusPreferences = DefaultScope.INSTANCE.getNode(org.eclipse.sirius.tools.api.SiriusPlugin.ID);
    
    // Allow by default aird fragment with no representation creation
    siriusPreferences.putBoolean(SiriusPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name(), true);

    // Re-apply command line customizations
    applyCommandLineCustomizations(siriusPreferences);

    // ----------------
    
    // Preference customization for plugin "org.eclipse.sirius.common.ui"
    IEclipsePreferences transPreferences = DefaultScope.INSTANCE.getNode(SiriusTransPlugin.PLUGIN_ID);
    
    // Disable Sirius Pre-commit listener behavior since Capella has the same one.
    transPreferences.putBoolean(CommonPreferencesConstants.PREF_DEFENSIVE_EDIT_VALIDATION, false);

    // Re-apply command line customizations
    applyCommandLineCustomizations(transPreferences);

    // ----------------
    
    // Preference customization for plugin "org.eclipse.sirius.ui"
    IEclipsePreferences editPreferences = DefaultScope.INSTANCE.getNode(SiriusEditPlugin.ID);
    // Required since Sirius 5.1 since default behavior is not welcome
    editPreferences.putBoolean(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
    editPreferences.putBoolean(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);

    // Set the default Sirius scale option (20%).
    editPreferences.putInt(SiriusUIPreferencesKeys.PREF_SCALE_LEVEL_DIAGRAMS_ON_EXPORT.name(), 2);

    // Don't use colors from odesign in diagram palettes
    editPreferences.putBoolean(SiriusUIPreferencesKeys.PREF_DISPLAY_VSM_USER_FIXED_COLOR_IN_PALETTE.name(), false);

    // Activate the SVG Traceability (the target semantic id is exported in the SVG file)
    editPreferences.putBoolean(SiriusUIPreferencesKeys.PREF_EXPORT_SEMANTIC_TRACEABILITY.name(), true);

    // Re-apply command line customizations
    applyCommandLineCustomizations(editPreferences);

    // ----------------

    // Preference customization for plugin "org.eclipse.sirius.diagram"
    IEclipsePreferences diagramPreferences = DefaultScope.INSTANCE.getNode(DiagramPlugin.ID);

    // By default override the Jump Link properties default values
    diagramPreferences.putBoolean(SiriusDiagramCorePreferences.PREF_JUMP_LINK_ENABLE_OVERRIDE, true);
    
    // Set the jump link status as "Above"
    diagramPreferences.putInt(SiriusDiagramCorePreferences.PREF_JUMP_LINK_STATUS, JumpLinkStatus.ABOVE);
    
    // Set the jump link type as "Tunnel"
    diagramPreferences.putInt(SiriusDiagramCorePreferences.PREF_JUMP_LINK_TYPE, JumpLinkType.TUNNEL);
    
    IEclipsePreferences diagramUiPreferences = DefaultScope.INSTANCE.getNode(DiagramUIPlugin.ID);
    diagramUiPreferences.putInt(SiriusDiagramUiPreferencesKeys.PREF_NEWLY_CREATED_ELEMENTS_LAYOUT.name(), SiriusLayoutDataManager.VERTICAL_ARRANGEMENT);
    
    // Re-apply command line customizations
    applyCommandLineCustomizations(diagramPreferences);
    
  }

  /**
   * Method to re-apply the command line customizations done during {@link DefaultPreferences#load} after modification
   * of default values done from an other plugin.
   * 
   * @param preferencesNode
   *      the default preference node to customize.
   */
  private void applyCommandLineCustomizations(IEclipsePreferences preferencesNode) {
    // Re-apply command line customizations.
    if (preferencesNode instanceof DefaultPreferences) {
      try {
        ReflectionHelper.invokeMethod(preferencesNode, DefaultPreferences.class, "applyCommandLineDefaults", new Class[] {}, new Object[] {}, true); //$NON-NLS-1$
      } catch (SecurityException | NoSuchMethodException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
        getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.ERROR, "Failed to apply plugin customization from command line arguments for plugin " + PLUGIN_ID, e)); //$NON-NLS-1$
      }
    }
  }
}
