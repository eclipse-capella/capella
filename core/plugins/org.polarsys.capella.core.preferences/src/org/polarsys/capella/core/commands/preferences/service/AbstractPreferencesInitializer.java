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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

public abstract class AbstractPreferencesInitializer extends AbstractPreferenceInitializer {

  public static ScopedCapellaPreferencesStore preferencesManager;

  /*
   * 
   */
  IProject project;

  /**
   * 
   */
  public AbstractPreferencesInitializer(String pluginID) {
    super();
    this.project =
        PreferencesHelper.getSelectedEclipseProject() != null ? PreferencesHelper.getSelectedEclipseProject() : PreferencesHelper.getSelectedCapellaProject();
    preferencesManager = ScopedCapellaPreferencesStore.getInstance(pluginID);
    initializeDefaultPreferences();
    try {
      //force start of EMF Validation plugin before initializing their default preferences scope
      ModelValidationService.getInstance().loadXmlConstraintDeclarations();
      PreferencesHelper.initializeCapellaPreferencesFromEPFFile();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public abstract void initializeDefaultPreferences();

  /**
   * @param key
   * @param value
   */
  public void putBoolean(String key, boolean value, Class scopeClass) {
	  if (scopeClass.equals(ProjectScope.class)) {
		  project = PreferencesHelper.getSelectedCapellaProject();
		  ScopedCapellaPreferencesStore.getOptions().put(key, String.valueOf(value));
		  if (project != null) {
			  ScopedCapellaPreferencesStore.putBoolean(project, key, value);
		  } else {
			  ScopedCapellaPreferencesStore.putBoolean(null, key, value);
		  }
	  }
    initializeAllOpenedProjects(key, value);
//    new InstanceScope().getNode(Activator.PLUGIN_ID).putBoolean(key, value);
//    new DefaultScope().getNode(Activator.PLUGIN_ID).putBoolean(key, value);
  }

  /**
   * @param key
   * @param value
   */
  public void putString(String key, String value, Class scopeClass) {
    if (scopeClass.equals(ProjectScope.class)) {
      project = PreferencesHelper.getSelectedCapellaProject();
      ScopedCapellaPreferencesStore.getOptions().put(key, String.valueOf(value));
      if (project != null) {
        ScopedCapellaPreferencesStore.putString(project, key, String.valueOf(value));
      }
    } else {
      ScopedCapellaPreferencesStore.putString(null, key, String.valueOf(value));
    }
    
    initializeAllOpenedProjects(key, value);

//    new InstanceScope().getNode(Activator.PLUGIN_ID).put(key, String.valueOf(value));
//    new DefaultScope().getNode(Activator.PLUGIN_ID).put(key, String.valueOf(value));
  }

  /**
   * @param key
   * @param value
   */
  public void putInt(String key, int value, Class scopeClass) {
    if (scopeClass.equals(ProjectScope.class)) {
      project = PreferencesHelper.getSelectedCapellaProject();
      ScopedCapellaPreferencesStore.getOptions().put(key, String.valueOf(value));
      if (project != null) {
        ScopedCapellaPreferencesStore.putInt(project, key, value);
      }
    } else {
      ScopedCapellaPreferencesStore.putInt(null, key, value);
    }

    initializeAllOpenedProjects(key, value);
//    new InstanceScope().getNode(Activator.PLUGIN_ID).putInt(key, value);
//    new DefaultScope().getNode(Activator.PLUGIN_ID).putInt(key, value);
  }

  /**
   * @param value
   * @param key
   */
  private void initializeAllOpenedProjects(String key, Object value) {
    IProject[] iProjects = ResourcesPlugin.getWorkspace().getRoot().getProjects();

    // initialize default scope
    if (value instanceof Boolean) {
      ScopedCapellaPreferencesStore.putBoolean(null, key, ((Boolean) value).booleanValue());
    } else if (value instanceof String) {
      ScopedCapellaPreferencesStore.putString(null, key, ((String) value));
    }

    // initialize project scopes
    for (IProject iProject : iProjects) {
      if (iProject.isOpen() && iProject.isAccessible() && (CapellaResourceHelper.isCapellaProject(iProject))) {
        if (value instanceof Boolean) {
          ScopedCapellaPreferencesStore.putBoolean(iProject, key, ((Boolean) value).booleanValue());
        } else if (value instanceof String) {
          ScopedCapellaPreferencesStore.putString(iProject, key, (String) value);
        }
      }
    }

  }

  /**
   * @return
   */
  public static String getString(String key, boolean inProject) {
    return ScopedCapellaPreferencesStore.getString(true, key);

  }

  /**
   * @param key
   * @param value
   */
  public static boolean getBoolean(String key, boolean inProjectScope) {
    return ScopedCapellaPreferencesStore.getBoolean(true, key);
  }

  /**
   * @param key
   * @param value
   */
  public static int getInt(String key, boolean inProjectScope) {
    return ScopedCapellaPreferencesStore.getInt(true, key);
  }

  /**
   * @param preferenceShowCapellaProjectConcept_p
   * @param contentChild_p
   * @return
   */
  public static boolean getBoolean(String preferenceShowCapellaProjectConcept_p, Object contentChild_p) {
    return ScopedCapellaPreferencesStore.getBoolean(preferenceShowCapellaProjectConcept_p, contentChild_p);
  }

}
