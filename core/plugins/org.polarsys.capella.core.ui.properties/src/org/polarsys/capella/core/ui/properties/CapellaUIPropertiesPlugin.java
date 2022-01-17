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
package org.polarsys.capella.core.ui.properties;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.osgi.framework.BundleContext;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.services.AbstractUIActivator;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.ui.properties.preferences.CapellaUIPropertiesPreferencesInitializer;
import org.polarsys.capella.core.ui.properties.preferences.ITransfertViewerPreferences;
import org.polarsys.capella.core.ui.properties.wizards.CustomWizardHandler;
import org.polarsys.capella.core.ui.properties.wizards.ICustomWizardHandler;
import org.polarsys.capella.core.ui.properties.wizards.OpenCustomWizardCommand;

/**
 * The activator class controls the plug-in life cycle
 */
public class CapellaUIPropertiesPlugin extends AbstractUIActivator {
  /**
   * Properties contributor ID.
   */
  public static final String PROPERTIES_CONTRIBUTOR = "org.polarsys.capella.core.data.capellamodeller.properties"; //$NON-NLS-1$
  public static final String PROPERTIES_SHEET_VIEW_ID = "org.eclipse.ui.views.PropertySheet"; //$NON-NLS-1$

  public static final String CAPELLA_EXPERT_SECTION = "org.polarsys.capella.common.data.modellingcore.section.advanced.ModelElement"; //$NON-NLS-1$
  
  
  /**
   * The shared instance
   */
  private static CapellaUIPropertiesPlugin __plugin;

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    __plugin = this;
    new CapellaUIPropertiesPreferencesInitializer();
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    __plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   * 
   * @return the shared instance
   */
  public static CapellaUIPropertiesPlugin getDefault() {
    return __plugin;
  }

  /**
   * Get the custom wizard singleton instance.
   * 
   * @return a not <code>null</code> instance.
   */
  public ICustomWizardHandler getCustomWizardHandler() {
    return new CustomWizardHandler();
  }

  /**
   * Open the related wizard for specified model element. The entire operation is wrapped in a command and executed on
   * the command stack. If you're already running inside a command, do not use this method. You should then instantiate
   * and execute a CustomWizardHandler directly.
   * 
   * @param modelElement
   *          the model element to be edited
   * @param event
   *          not used
   * @return true if the wizard was successfully finished (not canceled)
   */
  public boolean openWizard(DoubleClickEvent event, EObject object) {
    OpenCustomWizardCommand command = new OpenCustomWizardCommand(object);
    if (command.canExecute()) {
      TransactionHelper.getExecutionManager(object).execute(command);
      return !command.isCanceled();
    }
    return false;
  }

  /**
   * @see openWizard(DoubleClickEvent, ModelElement)
   */
  public boolean openWizard(EObject object) {
    return openWizard(null, object);
  }

  /**
   * Get the Allow Expand of the content of the left viewer preference value. <br>
   * <br>
   * 
   * @link {@link ITransfertViewerPreferences#PREFS_EXPAND_LEFT_VIEWER_CONTENT} value <code>true or false</code>
   * @return boolean value
   */
  public boolean isAllowedExpandLeftViewerContent() {
    return AbstractPreferencesInitializer.getBoolean(ITransfertViewerPreferences.PREFS_EXPAND_LEFT_VIEWER_CONTENT,
        ITransfertViewerPreferences.PREFS_EXPAND_LEFT_VIEWER_CONTENT_DEFAULT.booleanValue());
  }

  /**
   * Get the Allow Expand of the content of the right viewer preference value. <br>
   * <br>
   * 
   * @link {@link ITransfertViewerPreferences#PREFS_EXPAND_RIGHT_VIEWER_CONTENT} value <code>true or false</code>
   * @return boolean value
   */
  public boolean isAllowedExpandRightViewerContent() {
    return AbstractPreferencesInitializer.getBoolean(ITransfertViewerPreferences.PREFS_EXPAND_RIGHT_VIEWER_CONTENT,
        ITransfertViewerPreferences.PREFS_EXPAND_RIGHT_VIEWER_CONTENT_DEFAULT.booleanValue());
  }

  /**
   * Get the Allow Expand of the content of the single viewer preference value. <br>
   * <br>
   * 
   * @link {@link ITransfertViewerPreferences#PREFS_EXPAND_SINGLE_VIEWER_CONTENT} value <code>true or false</code>
   * @return boolean value
   */
  public boolean isAllowedExpandSingleViewerContent() {
    return AbstractPreferencesInitializer.getBoolean(ITransfertViewerPreferences.PREFS_EXPAND_SINGLE_VIEWER_CONTENT,
        ITransfertViewerPreferences.PREFS_EXPAND_SINGLE_VIEWER_CONTENT_DEFAULT.booleanValue());
  }

  /**
   * Returns a boolean for either to calculate a custom label for the items in the viewer or to use element name as
   * label.
   * 
   * @link {@link ITransfertViewerPreferences#PREFS_DISABLE_LABEL_COMPUTATION} value <code>true or false</code>
   * @return boolean value
   */
  public boolean isDisableLabelComputation() {
    return AbstractPreferencesInitializer.getBoolean(ITransfertViewerPreferences.PREFS_DISABLE_LABEL_COMPUTATION,
        ITransfertViewerPreferences.PREFS_DISABLE_LABEL_COMPUTATION_DEFAULT.booleanValue());
  }
}
