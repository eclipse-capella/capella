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
package org.polarsys.capella.core.model.preferences;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.framework.BundleContext;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;

/**
 * The activator class controls the plug-in life cycle
 */
public class CapellaModelPreferencesPlugin extends Plugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.polarsys.capella.core.model.preferences"; //$NON-NLS-1$

  // The shared instance
  private static CapellaModelPreferencesPlugin plugin;

  // The data listeners
  private CapellaModelDataListenerForDatas _dataListenerForDatas;
  private CapellaModelDataListenerForSequenceMessages _dataListenerForSequenceMessages;
  private CapellaModelDataListenerForPartsAndComponents _dataListenerForPartsAndComponents;
  private CapellaModelDataListenerForExchangeItemsAndCommunicationLinks _dataListenerForExchangeItemsAndCommunicationLinks;

  /**
   * The constructor
   */
  public CapellaModelPreferencesPlugin() {
    super();
  }

  /**
   * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
    checkPreferences();
    new CapellaModelPreferencesInitializer();

  }

  /**
   * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   * @return the shared instance
   */
  public static CapellaModelPreferencesPlugin getDefault() {
    return plugin;
  }

  /**
   * Get the Confirm Delete current preference value. <br>
   * <br>
   * @deprecated use IDeletePreferences.INSTANCE.isConfirmationRequired()
   * @link {@link IDeletePreferences#PREFERENCE_CONFIRM_DELETE} value <code>true or false</code>
   * @return boolean value
   */
  @Deprecated
  public boolean isConfirmDeleteAllowed() {
    return AbstractPreferencesInitializer.getBoolean(IDeletePreferences.PREFERENCE_CONFIRM_DELETE,
        IDeletePreferences.PREFERENCE_CONFIRM_DELETE_DEFAULT.booleanValue());
  }

  /**
   * Get the Delete Parts current preference value. <br>
   * <br>
   * @deprecated use IDeletePreferences.INSTANCE.isDeletePartsAllowed()
   * @link {@link IDeletePreferences#PREFERENCE_DELETE_PARTS} value <code>true or false</code>
   * @return boolean value
   */
  @Deprecated
  public boolean isDeletePartsAllowed() {
    return AbstractPreferencesInitializer.getBoolean(IDeletePreferences.PREFERENCE_DELETE_PARTS,
        IDeletePreferences.PREFERENCE_DELETE_PARTS_DEFAULT.booleanValue());
  }

  /**
   * Get the Allow Primitive Synchronization Strategy current preference value. <br>
   * <br>
   * @link {@link IDataPreferences#PREFS_ALLOW_PRIMITIVE_SYNCHRONIZATION} value <code>true or false</code>
   * @return boolean value
   */
  public boolean isPrimitiveSynchroAllowed() {
    return AbstractPreferencesInitializer.getBoolean(IDataPreferences.PREFS_ALLOW_PRIMITIVE_SYNCHRONIZATION, true);

  }

  /**
   * Get the interface projection mode. <br>
   * <br>
   * @link {@link IProjectionPreferences#PREFS_INTERFACE_PROJECTION} value <code>true or false</code>
   * @return boolean value
   */
  public boolean isInterfaceProjectionHandle() {
    IEclipsePreferences preferences = new InstanceScope().getNode(IProjectionPreferences.PREFS_PROJECTION_ID);
    return preferences.getBoolean(IProjectionPreferences.PREFS_INTERFACE_PROJECTION,
        IProjectionPreferences.DEFAULT_INTERFACE_PROJECTION.booleanValue());
  }

  /**
   * Get the Allow Reuse of components Strategy current preference value. <br>
   * <br>
   * @link {@link IReuseComponentsPreferences#PREFS_ALLOW_REUSE_COMPONENTS} value <code>true or false</code>
   * @return boolean value
   */
  public boolean isReuseOfComponentsAllowed() {
    return AbstractPreferencesInitializer.getBoolean(IReuseComponentsPreferences.PREFS_ALLOW_REUSE_COMPONENTS, true);

  }

  /**
   * Get the Allow Multiple Inheritance Strategy current preference value. <br>
   * <br>
   * @link {@link IInheritancePreferences#PREFS_ALLOW_MULTIPLE_INHERITANCE} value <code>true or false</code>
   * @return boolean value
   */
  public boolean isMultipleInheritanceAllowed() {
    return AbstractPreferencesInitializer.getBoolean(IInheritancePreferences.PREFS_ALLOW_MULTIPLE_INHERITANCE, true);

  }

  /**
   * Get the Allow Multiple Deployment Strategy current preference value. <br>
   * <br>
   * @link {@link IDeploymentPreferences#PREFS_ALLOW_MULTIPLE_DEPLOYMENT} value <code>true or false</code>
   * @return boolean value
   */
  public boolean isMultipleDeploymentAllowed() {
    return AbstractPreferencesInitializer.getBoolean(IDeploymentPreferences.PREFS_ALLOW_MULTIPLE_DEPLOYMENT, true);
  }

  /**
   * Get the Allow Synchronization of ComponentPort to FunctionPort current preference value. <br>
   * <br>
   * @link {@link ISynchronizationPreferences#PREFS_ALLOW_SYNC_COMPONENTPORT_TO_FUNCTIONPORT} value <code>true or false</code>
   * @return boolean value
   */
  public boolean isSynchronizationOfComponentPortToFunctionPortAllowed() {
    return AbstractPreferencesInitializer.getBoolean(
        ISynchronizationPreferences.PREFS_ALLOW_SYNC_COMPONENTPORT_TO_FUNCTIONPORT, true);
  }

  /**
   * Get the Allow Synchronization of PhysicalPort to ComponentPort on Physical Link current preference value. <br>
   * <br>
   * @link {@link ISynchronizationPreferences#PREFS_ALLOW_SYNC_PHYSICALPORT_TO_COMPONENTPORT_ON_PHYSICALLINK} value <code>true or false</code>
   * @return boolean value
   */
  public boolean isSynchronizationOfPhysicalPortToComponentPortOnPhysicalLinkAllowed() {
    return AbstractPreferencesInitializer.getBoolean(
        ISynchronizationPreferences.PREFS_ALLOW_SYNC_PHYSICALPORT_TO_COMPONENTPORT_ON_PHYSICALLINK, true);
  }

  /**
   * Get the Allow Synchronization of PhysicalPort to ComponentPort on Physical Path current preference value. <br>
   * <br>
   * @link {@link ISynchronizationPreferences#PREFS_ALLOW_SYNC_PHYSICALPORT_TO_COMPONENTPORT_ON_PHYSICALPATH} value <code>true or false</code>
   * @return boolean value
   */
  public boolean isSynchronizationOfPhysicalPortToComponentPortOnPhysicalPathAllowed() {
    return AbstractPreferencesInitializer.getBoolean(
        ISynchronizationPreferences.PREFS_ALLOW_SYNC_PHYSICALPORT_TO_COMPONENTPORT_ON_PHYSICALPATH, true);
  }

  /**
   * load/unload the data listener according to the corresponding preference
   */
  private void checkPreferences() {
    loadDataListenerForDatas();
    loadDataListenerForSequenceMessages();
    loadDataListenerForExchangeItemsAndCommunicationLinks();
    loadDataListenerForPartsAndComponents();

  }

  /**
   * loads the data listener
   */
  private void loadDataListenerForDatas() {
    if (_dataListenerForDatas == null) {
      _dataListenerForDatas = new CapellaModelDataListenerForDatas();
      MDEAdapterFactory.getDataNotifier().addAdapter(AbstractNamedElement.class, _dataListenerForDatas);
    }
  }

  /**
   * loads the data listener
   */
  private void loadDataListenerForSequenceMessages() {
    if (_dataListenerForSequenceMessages == null) {
      _dataListenerForSequenceMessages = new CapellaModelDataListenerForSequenceMessages();
      MDEAdapterFactory.getDataNotifier().addAdapter(AbstractNamedElement.class, _dataListenerForSequenceMessages);
    }
  }

  /**
   * loads the data listener
   */
  private void loadDataListenerForPartsAndComponents() {
    if (_dataListenerForPartsAndComponents == null) {
      _dataListenerForPartsAndComponents = new CapellaModelDataListenerForPartsAndComponents();
      MDEAdapterFactory.getDataNotifier().addAdapter(AbstractNamedElement.class, _dataListenerForPartsAndComponents);
    }
  }

  /**
   * loads the data listener
   */
  private void loadDataListenerForExchangeItemsAndCommunicationLinks() {
    if (_dataListenerForExchangeItemsAndCommunicationLinks == null) {
      _dataListenerForExchangeItemsAndCommunicationLinks =
          new CapellaModelDataListenerForExchangeItemsAndCommunicationLinks();
      MDEAdapterFactory.getDataNotifier().addAdapter(AbstractNamedElement.class,
          _dataListenerForExchangeItemsAndCommunicationLinks);
    }
  }
}
