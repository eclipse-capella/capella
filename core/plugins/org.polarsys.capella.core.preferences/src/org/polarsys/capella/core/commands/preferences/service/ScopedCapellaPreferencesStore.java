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
package org.polarsys.capella.core.commands.preferences.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.service.prefs.BackingStoreException;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.preferences.Activator;

/**
 */
public class ScopedCapellaPreferencesStore extends ScopedPreferenceStore {

  /*
   * 
   */
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);

  /*
   * 
   */
  private static ScopedCapellaPreferencesStore instance;

  /*
	 * 
	 */
  public static Map<String, String> DEFAULT_OPTIONS_MAP;

  /*
	 * 
	 */
  private static Map<String, String> options;

  /*
   * 
   */
  public static Map<FieldEditor, String> fields;

  /*
   * 
   */
  private static List<IScopeContext> projectContexts = new ArrayList<IScopeContext>(3);

  /*
	 * 
	 */
  private static final DefaultScope defaultScopPref = new DefaultScope();

  /*
	 * 
	 */
  private static final InstanceScope instanceScopPrefs = new InstanceScope();

  /*
	 * 
	 */
  private static final Map<IProject, IScopeContext> projectScopPrefs = new HashMap<IProject, IScopeContext>(0);

  /*
   * 
   */
  private static final String PREFERENCE_SEPARATOR = "."; //$NON-NLS-1$

  /**
	 * 
	 */
  static {
    options = new HashMap<String, String>();
    DEFAULT_OPTIONS_MAP = Collections.unmodifiableMap(options);
    fields = new HashMap<FieldEditor, String>(0);
  }

  /**
	 * 
	 */
  private ScopedCapellaPreferencesStore(String pluginId) {
    super(instanceScopPrefs, pluginId);
  }

  /**
   * @param pluginID_p
   * @return the instance
   */
  public static ScopedCapellaPreferencesStore getInstance(String pluginID_p) {
    if (instance == null) {
      instance = new ScopedCapellaPreferencesStore(pluginID_p);
    }
    return instance;
  }

  @Override
  public void addPropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener listener) {
    super.addPropertyChangeListener(listener);
  }

  @Override
  public void firePropertyChangeEvent(String name, Object oldValue, Object newValue) {
    if (Activator.getDefault().getPropertyPreferenceStore(PreferencesHelper.getSelectedCapellaProject()) == null) {
      super.firePropertyChangeEvent(name, oldValue, newValue);
    }
  }

  /**
   * @param project
   * @param optionName
   * @param value
   */
  protected static void putInt(IProject project, String optionName, int value) {
    IScopeContext context = (null != project) ? getProjectScope(project) : null;
    String key = project != null ? project.getName() + PREFERENCE_SEPARATOR + optionName : optionName;

    IEclipsePreferences projectNode = context != null ? context.getNode(Activator.PLUGIN_ID) : null;
    IEclipsePreferences instanceNode = instanceScopPrefs.getNode(Activator.PLUGIN_ID);
    IEclipsePreferences defaultNode = defaultScopPref.getNode(Activator.PLUGIN_ID);

    String projectValue =
        projectNode != null ? Platform.getPreferencesService().get(key, null, new IEclipsePreferences[] { projectNode }) : ICommonConstants.EMPTY_STRING;
    String instanceValue = Platform.getPreferencesService().get(optionName, null, new IEclipsePreferences[] { instanceNode });
    String defaultValue = Platform.getPreferencesService().get(optionName, null, new IEclipsePreferences[] { defaultNode });

    if (instanceValue == null) {
      instanceNode.putInt(key, instanceNode.getInt(key, value));
      flushPreference(instanceNode);
    }

    if (defaultValue == null) {
      defaultNode.putInt(key, instanceNode.getInt(key, value));
      flushPreference(defaultNode);
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getInt(String name) {
    return getInt(true, name);
  }

  /**
   * @param inProjectScope_p
   * @param key_p
   * @return
   */
  public static int getInt(boolean inProjectScope, String optionName) {
    IProject selectedCapellaProject = PreferencesHelper.getSelectedCapellaProject();
    if (inProjectScope && (selectedCapellaProject != null)) {
      if (PreferencesHelper.hasConfigurationProject(selectedCapellaProject)) {
        final IProject refProjectConfiguration = PreferencesHelper.getReferencedProjectConfiguration(selectedCapellaProject);
        if (Activator.getDefault().getPropertyPreferenceStore(refProjectConfiguration) != null) {
          return Activator.getDefault().getPropertyPreferenceStore(refProjectConfiguration).getInt(optionName);
        } else if ((getValueFromPresistentPropertyStore(refProjectConfiguration, optionName) != null)
                   && (getValueFromPresistentPropertyStore(refProjectConfiguration, optionName) instanceof String)) {
          return Integer.valueOf((String) getValueFromPresistentPropertyStore(refProjectConfiguration, optionName)).intValue();
        }
      } else if (Activator.getDefault().getPropertyPreferenceStore(selectedCapellaProject) != null) {
        return Activator.getDefault().getPropertyPreferenceStore(selectedCapellaProject).getInt(optionName);
      }
    }
    return Activator.getDefault().getPreferenceStore().getInt(optionName);
  }

  /**
   * @param project
   * @param optionName
   * @param value
   */
  protected static void putBoolean(IProject project, String optionName, boolean value) {
    IScopeContext context = (null != project) ? getProjectScope(project) : null;
    String key = project != null ? project.getName() + PREFERENCE_SEPARATOR + optionName : optionName;

    IEclipsePreferences projectNode = context != null ? context.getNode(Activator.PLUGIN_ID) : null;
    IEclipsePreferences instanceNode = instanceScopPrefs.getNode(Activator.PLUGIN_ID);
    IEclipsePreferences defaultNode = defaultScopPref.getNode(Activator.PLUGIN_ID);

    String projectValue =
        projectNode != null ? Platform.getPreferencesService().get(key, null, new IEclipsePreferences[] { projectNode }) : ICommonConstants.EMPTY_STRING;
    String instanceValue = Platform.getPreferencesService().get(optionName, null, new IEclipsePreferences[] { instanceNode });
    String defaultValue = Platform.getPreferencesService().get(optionName, null, new IEclipsePreferences[] { defaultNode });


    if (instanceValue == null) {
      instanceNode.put(key, instanceNode.get(key, String.valueOf(value)));
      flushPreference(instanceNode);
    }

    if (defaultValue == null) {
      defaultNode.put(key, defaultNode.get(key, String.valueOf(value)));
      flushPreference(defaultNode);
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean getBoolean(String name) {
    return getBoolean(true, name);
  }

  /**
   * @param project
   * @param optionName
   * @return
   */
  public static boolean getBoolean(boolean inProjectScope, String optionName) {
    IProject selectedCapellaProject = PreferencesHelper.getSelectedCapellaProject();
    return getPreferenceFromCustomScopes(inProjectScope, optionName, selectedCapellaProject);
  }

  /**
   * @param project
   * @param optionName
   * @return
   */
  public static boolean getBoolean(boolean inProjectScope, String optionName, IProject projectParam) {
    return getPreferenceFromCustomScopes(inProjectScope, optionName, projectParam);
  }

  private static boolean getPreferenceFromCustomScopes(boolean inProjectScope, String optionName, IProject selectedCapellaProject) {
    if (inProjectScope && (selectedCapellaProject != null)) {
      if (PreferencesHelper.hasConfigurationProject(selectedCapellaProject)) {
        final IProject refProjectConfiguration = PreferencesHelper.getReferencedProjectConfiguration(selectedCapellaProject);
       	if ((getValueFromPresistentPropertyStore(refProjectConfiguration, optionName) != null)
                   && (getValueFromPresistentPropertyStore(refProjectConfiguration, optionName) instanceof String)) {
          return Boolean.valueOf((String) getValueFromPresistentPropertyStore(refProjectConfiguration, optionName)).booleanValue();
        }
      } else if (getValueFromPresistentPropertyStore(selectedCapellaProject, optionName) != null ) {
        return Boolean.valueOf((String) getValueFromPresistentPropertyStore(selectedCapellaProject, optionName)).booleanValue();
      }
    }
    return Activator.getDefault().getPreferenceStore().getBoolean(optionName);
  }

  /**
   * @param preferenceShowCapellaProjectConcept_p
   * @param contentChild_p
   * @return
   */
  public static boolean getBoolean(String preferenceName, Object contentChild_p) {
    if ((contentChild_p instanceof Project) && (PreferencesHelper.getProject((Project) contentChild_p) != null)) {
      return getBoolean(true, preferenceName, PreferencesHelper.getProject((Project) contentChild_p));
    }
    return getBoolean(true, preferenceName);

  }

  @Override
  public void setValue(String name, boolean value) {
    IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
    for (IProject project : projects) {
      if (PreferencesHelper.hasConfigurationProject(project)) {
        IProject configProject = PreferencesHelper.getReferencedProjectConfiguration(project);
        if (getDefaultBoolean(name) == value) {
        	IEclipsePreferences pref = 
        	          new ProjectScope(configProject).getNode(Activator.PLUGIN_ID);
        	pref.remove(name);
        } else {
        	IEclipsePreferences pref = 
      	          new ProjectScope(configProject).getNode(Activator.PLUGIN_ID);
        	pref.putBoolean(name, value);
        	}
      	}
    }
    	if (getDefaultBoolean(name) == value) {
    		IEclipsePreferences pref = new InstanceScope().getNode(Activator.PLUGIN_ID);
    		pref.remove(name);
    	} else {
    		IEclipsePreferences pref = new InstanceScope().getNode(Activator.PLUGIN_ID);
    		pref.putBoolean(name, value);
    	}
  }

  /**
   * @param resource
   * @param key
   * @return
   */
  public static Object getValueFromPresistentPropertyStore(IProject resource, String key) {
    try {

      if (resource.isAccessible() && resource.isOpen()) {
        Map<QualifiedName, String> properties =
            resource.getPersistentProperties() != null ? resource.getPersistentProperties() : new HashMap<QualifiedName, String>(0);
        for (QualifiedName qualifiedName : properties.keySet()) {
          QualifiedName qualified = qualifiedName;
          if (key.equals(qualified.getLocalName())) {
            return properties.get(qualifiedName);
          }
        }
      }
    } catch (CoreException exception_p1) {
      StringBuilder loggerMessage = new StringBuilder("Activator.initializePropertyStore(..) _ "); //$NON-NLS-1$
      __logger.warn(loggerMessage.toString(), exception_p1);
    }

    return null;
  }

  /**
   * @param project
   * @param optionName
   * @param defaultValue
   */
  protected static void putString(IProject project, String optionName, String value) {

    IScopeContext context = (null != project) ? getProjectScope(project) : null;
    String key = project != null ? project.getName() + PREFERENCE_SEPARATOR + optionName : optionName;

    IEclipsePreferences projectNode = context != null ? context.getNode(Activator.PLUGIN_ID) : null;
    IEclipsePreferences instanceNode = instanceScopPrefs.getNode(Activator.PLUGIN_ID);
    IEclipsePreferences defaultNode = defaultScopPref.getNode(Activator.PLUGIN_ID);

    String projectValue =
        projectNode != null ? Platform.getPreferencesService().get(key, null, new IEclipsePreferences[] { projectNode }) : ICommonConstants.EMPTY_STRING;
    String instanceValue = Platform.getPreferencesService().get(optionName, null, new IEclipsePreferences[] { instanceNode });
    String defaultValue = Platform.getPreferencesService().get(optionName, null, new IEclipsePreferences[] { defaultNode });


    if (instanceValue == null) {
      instanceNode.put(key, instanceNode.get(key, String.valueOf(value)));
      flushPreference(instanceNode);
    }

    if (defaultValue == null) {
      defaultNode.put(key, instanceNode.get(key, String.valueOf(value)));
      flushPreference(defaultNode);
    }

  }

  @Override
  public String getString(String name) {
    return getString(true, name);
  }

  /**
   * Helper method to get a single preference setting, e.g., APT_GENSRCDIR. This is a different level of abstraction than the processor -A settings! The -A
   * settings are all contained under one single preference node, APT_PROCESSOROPTIONS. Use @see #getProcessorOptions(IJavaProject) to get the -A settings; use @see
   * #getOptions(IJavaProject) to get all the preference settings as a map; and use this helper method to get a single preference setting.
   * @param jproj the project, or null for workspace.
   * @param optionName a preference constant from @see AptPreferenceConstants.
   * @return the string value of the setting.
   */
  public static String getString(boolean inProjectScope, String optionName) {
    IProject selectedCapellaProject = PreferencesHelper.getSelectedCapellaProject();
    if (inProjectScope && (selectedCapellaProject != null)) {
      if (PreferencesHelper.hasConfigurationProject(selectedCapellaProject)) {
        final IProject refProjectConfiguration = PreferencesHelper.getReferencedProjectConfiguration(selectedCapellaProject);
        if (Activator.getDefault().getPropertyPreferenceStore(refProjectConfiguration) != null) {
          return Activator.getDefault().getPropertyPreferenceStore(refProjectConfiguration).getString(optionName);
        } else if ((getValueFromPresistentPropertyStore(refProjectConfiguration, optionName) != null)
                   && (getValueFromPresistentPropertyStore(refProjectConfiguration, optionName) instanceof String)) {
          return (String) getValueFromPresistentPropertyStore(refProjectConfiguration, optionName);
        }
      } else if (Activator.getDefault().getPropertyPreferenceStore(selectedCapellaProject) != null) {
        return Activator.getDefault().getPropertyPreferenceStore(selectedCapellaProject).getString(optionName);
      }
    }
    return Activator.getDefault().getPreferenceStore().getString(optionName);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(String name, String value) {
    IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
    for (IProject project : projects) {
      if (PreferencesHelper.hasConfigurationProject(project)) {
        IProject configProject = PreferencesHelper.getReferencedProjectConfiguration(project);

        if (getDefaultString(name) == value) {
          new ProjectScope(configProject).getNode(Activator.PLUGIN_ID).remove(name);
        } else {
          new ProjectScope(configProject).getNode(Activator.PLUGIN_ID).put(name, value);
        }
      }
    }

    if (getDefaultString(name) == value) {
      new InstanceScope().getNode(Activator.PLUGIN_ID).remove(name);
    } else {
      new InstanceScope().getNode(Activator.PLUGIN_ID).put(name, value);
    }

  }

  /**
   * @throws BackingStoreException
   */
  @Override
  public void save() {
    try {
      Platform.getPreferencesService().getRootNode().flush();

    } catch (BackingStoreException exception_p) {
      StringBuilder loggerMessage = new StringBuilder("ScopedPreferenceManager.save(..) _ "); //$NON-NLS-1$
      __logger.warn(loggerMessage.toString(), exception_p);
    }
  }

  /**
   * @param optionName
   * @param node
   */
  private static void flushPreference(IEclipsePreferences node) {
    try {
      node.flush();
    } catch (BackingStoreException exception_p) {
      StringBuilder loggerMessage = new StringBuilder("ScopedPreferenceManager.flushPreference(..) _ "); //$NON-NLS-1$
      __logger.warn(loggerMessage.toString(), exception_p);

    }

  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.preference.IPreferenceStore#contains(java.lang.String)
   */
  @Override
  public boolean contains(String name) {
    boolean contains = super.contains(name);
    IProject selectedCapellaProject = PreferencesHelper.getSelectedCapellaProject();
    IPreferencesService service = Platform.getPreferencesService();
    IScopeContext[] contexts;
    String key =
        (selectedCapellaProject != null) && !name.contains(selectedCapellaProject.getName()) ? selectedCapellaProject.getName() + PREFERENCE_SEPARATOR + name
                                                                                          : name;
    if ((selectedCapellaProject != null)) {
      contexts = new IScopeContext[] { getProjectScope(selectedCapellaProject), instanceScopPrefs, defaultScopPref };
    } else {
      contexts = new IScopeContext[] { instanceScopPrefs, defaultScopPref };
    }

    contains = service.getString(Activator.PLUGIN_ID, key, null, contexts) != null;

    return contains;
  }

  /**
   * @param project
   * @return
   */
  public static IScopeContext getProjectScope(IProject project) {
    final IProject realProject = project.getProject();
    if (projectScopPrefs.get(realProject) != null) {
      return projectScopPrefs.get(realProject);
    }
    return new ProjectScope(realProject);
  }

  /**
   * @return
   */
  public static Map<String, String> getOptions() {
    return options;
  }

  /**
   * @return
   */
  public static Map<IProject, IScopeContext> getProjectContexts() {

    return projectScopPrefs;
  }

}
