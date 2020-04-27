/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.osgi.service.prefs.BackingStoreException;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.preferences.Activator;

/**
 */
public class PropertyStore extends PreferenceStore implements IPropertyPersistentPreferenceStore {

  /*
   * 
   */
  private static final Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);

  /*
   * 
   */
  private static Set<IPropertyChangeListener> guestListener = new HashSet<IPropertyChangeListener>();

  /*
   * 
   */
  private IResource resource;

  public IResource getResource() {
    return resource;
  }

  public void setResource(IResource resource) {
    this.resource = resource;
  }

  /*
   * 
   */
  private IPreferenceStore workbenchStore;

  /*
   * 
   */
  private String pageId;

  /*
   * 
   */
  private boolean inserting = false;

  private boolean isCanceled;

  /**
   * @param _resource
   * @param _workbenchStore
   * @param id
   */
  public PropertyStore(IResource _resource, IPreferenceStore _workbenchStore, String id) {
    this.resource = _resource;
    this.workbenchStore = _workbenchStore;
    this.pageId = id;

  }

  /**
   * 
   */
  @Override
  public void initilizeGuestListeners() {
    for (IPropertyChangeListener iPropertyChangeListener : guestListener) {
      this.addPropertyChangeListener(iPropertyChangeListener);
    }
  }

  @Override
  public void addPropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener listener) {
    super.addPropertyChangeListener(listener);
  }

  @Override
  public void firePropertyChangeEvent(String name, Object oldValue, Object newValue) {
    super.firePropertyChangeEvent(name, oldValue, newValue);
  }

  /*** Write modified values back to properties ***/

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.preference.IPersistentPreferenceStore#save()
   */
  @Override
  public void save() throws IOException {

    try {

      // to bypass other capella modeller preference page
      if ((resource instanceof IProject) && !isCanceled) {
        final ProjectScope project = new ProjectScope((IProject) resource);
        writeProperties(project);
        project.getNode(Activator.PLUGIN_ID).flush();
      }

    } catch (BackingStoreException exception) {
      StringBuilder loggerMessage = new StringBuilder("PropertyStore : "); //$NON-NLS-1$
      logger.warn(loggerMessage.toString(), exception);
    }
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.preference.PreferenceStore#save(java.io.OutputStream, java.lang.String)
   */
  @Override
  public void save(OutputStream out, String header) throws IOException {
    writeProperties();
  }

  /**
   * Writes modified preferences into resource properties.
   */
  private void writeProperties() throws IOException {
    String[] preferences = super.preferenceNames();
    for (String name : preferences) {
      try {
        setProperty(name, getString(name));
      } catch (CoreException e) {
        throw new IOException("PropertyStore.Cannot_write_resource_property" + name); //$NON-NLS-1$
      }
    }
  }

  /**
   * Writes modified preferences into resource properties.
   */
  private void writeProperties(ProjectScope project) throws IOException {
    String[] preferences = super.preferenceNames();
    for (String name : preferences) {
      try {
        setProperty(name, getString(name));
        project.getNode(Activator.PLUGIN_ID).put(name, getString(name));
      } catch (CoreException e) {
        throw new IOException("PropertyStore.Cannot_write_resource_property" + name); //$NON-NLS-1$
      }
    }
  }

  /**
   * Convenience method to set a property
   * @param name - the preference name
   * @param value - the property value or null to delete the property
   * @throws CoreException
   */
  private void setProperty(String name, String value) throws CoreException {
    resource.setPersistentProperty(new QualifiedName(pageId, name), value);
  }

  /*** Get default values (Delegate to workbench store) ***/

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultBoolean(java.lang.String)
   */
  @Override
  public boolean getDefaultBoolean(String name) {
    return workbenchStore.getDefaultBoolean(name);
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultDouble(java.lang.String)
   */
  @Override
  public double getDefaultDouble(String name) {
    return workbenchStore.getDefaultDouble(name);
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultFloat(java.lang.String)
   */
  @Override
  public float getDefaultFloat(String name) {
    return workbenchStore.getDefaultFloat(name);
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultInt(java.lang.String)
   */
  @Override
  public int getDefaultInt(String name) {
    return workbenchStore.getDefaultInt(name);
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultLong(java.lang.String)
   */
  @Override
  public long getDefaultLong(String name) {
    return workbenchStore.getDefaultLong(name);
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultString(java.lang.String)
   */
  @Override
  public String getDefaultString(String name) {
    return workbenchStore.getDefaultString(name);
  }

  /*** Get property values ***/

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.preference.IPreferenceStore#getBoolean(java.lang.String)
   */
  @Override
  public boolean getBoolean(String name) {
    insertValue(name);
    return super.getBoolean(name);
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.preference.IPreferenceStore#getDouble(java.lang.String)
   */
  @Override
  public double getDouble(String name) {
    insertValue(name);
    return super.getDouble(name);
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.preference.IPreferenceStore#getFloat(java.lang.String)
   */
  @Override
  public float getFloat(String name) {
    insertValue(name);
    return super.getFloat(name);
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.preference.IPreferenceStore#getInt(java.lang.String)
   */
  @Override
  public int getInt(String name) {
    insertValue(name);
    return super.getInt(name);
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.preference.IPreferenceStore#getLong(java.lang.String)
   */
  @Override
  public long getLong(String name) {
    insertValue(name);
    return super.getLong(name);
  }

  /*
   * (non-Javadoc)
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
   * @param name - the preference name
   * @return - the property value
   * @throws CoreException
   */
  private String getProperty(String name) throws CoreException {
    return resource.getPersistentProperty(new QualifiedName(pageId, name));
  }

  /*** Misc ***/

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.preference.IPreferenceStore#contains(java.lang.String)
   */
  @Override
  public boolean contains(String name) {
    return workbenchStore.contains(name);
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.preference.IPreferenceStore#setToDefault(java.lang.String)
   */
  @Override
  public void setToDefault(String name) {
    setValue(name, getDefaultString(name));
  }

  /*
   * (non-Javadoc)
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
