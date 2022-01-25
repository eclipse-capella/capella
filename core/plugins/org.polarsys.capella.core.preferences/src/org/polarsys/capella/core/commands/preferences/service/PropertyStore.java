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
package org.polarsys.capella.core.commands.preferences.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.preferences.Activator;

/**
 * Its overall complicated but mainly:
 * 
 * Unlike the default PreferenceStore, it stores all values even default ones. Indeed, Configuration projects and
 * Capella projects can be shared between users and all preferences shall be shared between users even default ones.
 * 
 * It stores each preferences in the ProjectScope and as Properties using IProject.getPersistentProperty, which is idk
 * why, used in ScopedCapellaPreferencesStore.getProjectValue(IProject, String)
 */
public class PropertyStore extends PreferenceStore
    implements IPropertyPersistentPreferenceStore, IPreferenceChangeListener {

  public static final String USEPROJECTSETTINGS = "useProjectSettings"; //$NON-NLS-1$

  private static final Logger logger = ReportManagerRegistry.getInstance()
      .subscribe(IReportManagerDefaultComponents.UI);

  private static Set<IPropertyChangeListener> guestListener = new HashSet<IPropertyChangeListener>();

  private IResource resource;

  private IPreferenceStore workbenchStore;

  private boolean inserting = false;

  private boolean isCanceled;

  private ProjectScope scope;

  public IResource getResource() {
    return resource;
  }

  /**
   * @param _resource
   * @param _workbenchStore
   * @param id
   */
  public PropertyStore(IResource _resource, IPreferenceStore _workbenchStore) {
    this.resource = _resource;

    this.workbenchStore = _workbenchStore;
    Activator.getDefault().setPropertyStore((IResource) _resource, this);

    scope = new ProjectScope((IProject) resource);
    scope.getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName()).addPreferenceChangeListener(this);
    initilizeGuestListeners();
  }

  public void dispose() {
    scope.getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName()).removePreferenceChangeListener(this);
  }

  /**
   * 
   */
  @Override
  public void initilizeGuestListeners() {
    addPropertyChangeListener(new IPropertyChangeListener() {

      @Override
      public void propertyChange(PropertyChangeEvent event) {
        String name = event.getProperty();
        try {
          setProperty(name, getString(name));
        } catch (IOException exception) {
          logger.warn("PropertyStore : ", exception);
        }
      }
    });
  }

  @Override
  public void preferenceChange(PreferenceChangeEvent event) {
    for (IPropertyChangeListener iPropertyChangeListener : guestListener) {
      iPropertyChangeListener.propertyChange(
          new PropertyChangeEvent(event.getSource(), event.getKey(), event.getOldValue(), event.getNewValue()));
    }
  }

  /*** Write modified values back to properties ***/

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.IPersistentPreferenceStore#save()
   */
  @Override
  public void save() throws IOException {
    try {
      // to bypass other capella modeller preference page
      if ((resource instanceof IProject) && !isCanceled) {
        resource.setPersistentProperty(new QualifiedName(FrameworkUtil.getBundle(Activator.class).getSymbolicName(), USEPROJECTSETTINGS), TRUE); // idk if its useful
        writeProperties();
        scope.getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName()).flush();
      }

    } catch (Exception exception) {
      logger.warn("PropertyStore : ", exception);
    }
  }

  /**
   * Writes modified preferences into resource properties.
   */
  private void writeProperties() throws IOException {
    for (String name : preferenceNames()) {
      scope.getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName()).put(name, getString(name));
      setProperty(name, getString(name));
    }
  }

  /**
   * Convenience method to set a property
   * 
   * @param name
   *          - the preference name
   * @param value
   *          - the property value or null to delete the property
   * @throws CoreException
   */
  private void setProperty(String name, String value) throws IOException {
    try {
      scope.getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName()).put(name, value);
      resource.setPersistentProperty(new QualifiedName(FrameworkUtil.getBundle(Activator.class).getSymbolicName(), name), value);
    } catch (CoreException e) {
      throw new IOException("PropertyStore.Cannot_write_resource_property" + name, e); //$NON-NLS-1$
    }
  }

  /*** Get default values (Delegate to workbench store) ***/

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultBoolean(java.lang.String)
   */
  @Override
  public boolean getDefaultBoolean(String name) {
    return workbenchStore.getDefaultBoolean(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultDouble(java.lang.String)
   */
  @Override
  public double getDefaultDouble(String name) {
    return workbenchStore.getDefaultDouble(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultFloat(java.lang.String)
   */
  @Override
  public float getDefaultFloat(String name) {
    return workbenchStore.getDefaultFloat(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultInt(java.lang.String)
   */
  @Override
  public int getDefaultInt(String name) {
    return workbenchStore.getDefaultInt(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultLong(java.lang.String)
   */
  @Override
  public long getDefaultLong(String name) {
    return workbenchStore.getDefaultLong(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultString(java.lang.String)
   */
  @Override
  public String getDefaultString(String name) {
    return workbenchStore.getDefaultString(name);
  }

  /*** Get property values ***/

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.IPreferenceStore#getBoolean(java.lang.String)
   */
  @Override
  public boolean getBoolean(String name) {
    insertValue(name);
    return super.getBoolean(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.IPreferenceStore#getDouble(java.lang.String)
   */
  @Override
  public double getDouble(String name) {
    insertValue(name);
    return super.getDouble(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.IPreferenceStore#getFloat(java.lang.String)
   */
  @Override
  public float getFloat(String name) {
    insertValue(name);
    return super.getFloat(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.IPreferenceStore#getInt(java.lang.String)
   */
  @Override
  public int getInt(String name) {
    insertValue(name);
    return super.getInt(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.IPreferenceStore#getLong(java.lang.String)
   */
  @Override
  public long getLong(String name) {
    insertValue(name);
    return super.getLong(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.IPreferenceStore#getString(java.lang.String)
   */
  @Override
  public String getString(String name) {
    insertValue(name);
    return super.getString(name);
  }

  /**
   * @param name
   */
  private synchronized void insertValue(String name) {
    if (inserting) {
      return;
    }
    if (super.contains(name)) {
      return;
    }
    inserting = true;
    String prop = null;
    try {
      prop = getProperty(name);
    } catch (CoreException e) {
    }
    if (prop == null) {
      prop = workbenchStore.getString(name);
    }
    if (prop != null) {
      setValue(name, prop);
    }
    inserting = false;
  }

  /**
   * Convenience method to fetch a property
   * 
   * @param name
   *          - the preference name
   * @return - the property value
   * @throws CoreException
   */
  private String getProperty(String name) throws CoreException {
    return resource.getPersistentProperty(new QualifiedName(FrameworkUtil.getBundle(Activator.class).getSymbolicName(), name));
  }

  /*** Misc ***/

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.IPreferenceStore#contains(java.lang.String)
   */
  @Override
  public boolean contains(String name) {
    return workbenchStore.contains(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.IPreferenceStore#setToDefault(java.lang.String)
   */
  @Override
  public void setToDefault(String name) {
    setValue(name, getDefaultString(name));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.IPreferenceStore#isDefault(java.lang.String)
   */
  @Override
  public boolean isDefault(String name) {
    String defaultValue = getDefaultString(name);
    if (defaultValue == null) {
      return false;
    }
    return defaultValue.equals(getString(name));
  }

  /**
   * @param listener
   */
  public static void addToGuestListener(IPropertyChangeListener listener) {
    guestListener.add(listener);
  }

  /**
   * @param b
   */
  public void setCanceled(boolean b) {
    isCanceled = true;
  }

}
