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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
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
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.prefs.BackingStoreException;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.preferences.Activator;

/**
 */
public class ScopedCapellaPreferencesStore extends ScopedPreferenceStore {

  private static ScopedCapellaPreferencesStore instance;

  public static Map<String, String> DEFAULT_OPTIONS_MAP;

  private static Map<String, String> options;

  private static final Map<IProject, IScopeContext> projectScopes = new HashMap<IProject, IScopeContext>(0);

  private static final String PREFERENCE_SEPARATOR = "."; //$NON-NLS-1$

  static {
    options = new HashMap<String, String>();
    DEFAULT_OPTIONS_MAP = Collections.unmodifiableMap(options);
  }

  private ScopedCapellaPreferencesStore(String pluginId) {
    super(InstanceScope.INSTANCE, pluginId);
  }

  /**
   * @param pluginID_p
   * @return the instance
   */
  public static ScopedCapellaPreferencesStore getInstance(String pluginID_p) {
    if (instance == null) {
      // 1953: We share the same preference store. we don't want use the first one loaded.
      instance = new ScopedCapellaPreferencesStore(FrameworkUtil.getBundle(Activator.class).getSymbolicName());
    }
    return instance;
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
  protected static void putBoolean(IProject project, String optionName, boolean value) {
    String key = project != null ? project.getName() + PREFERENCE_SEPARATOR + optionName : optionName;

    IEclipsePreferences instanceNode = InstanceScope.INSTANCE.getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName());
    IEclipsePreferences defaultNode = DefaultScope.INSTANCE.getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName());

    String instanceValue = Platform.getPreferencesService().get(optionName, null,
        new IEclipsePreferences[] { instanceNode });
    String defaultValue = Platform.getPreferencesService().get(optionName, null,
        new IEclipsePreferences[] { defaultNode });

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
   * @param project
   * @param optionName
   * @param value
   */
  protected static void putInt(IProject project, String optionName, int value) {
    String key = project != null ? project.getName() + PREFERENCE_SEPARATOR + optionName : optionName;

    IEclipsePreferences instanceNode = InstanceScope.INSTANCE.getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName());
    IEclipsePreferences defaultNode = DefaultScope.INSTANCE.getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName());

    String instanceValue = Platform.getPreferencesService().get(optionName, null,
        new IEclipsePreferences[] { instanceNode });
    String defaultValue = Platform.getPreferencesService().get(optionName, null,
        new IEclipsePreferences[] { defaultNode });

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
   * @param project
   * @param optionName
   * @param defaultValue
   */
  protected static void putString(IProject project, String optionName, String value) {

    String key = project != null ? project.getName() + PREFERENCE_SEPARATOR + optionName : optionName;

    IEclipsePreferences instanceNode = InstanceScope.INSTANCE.getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName());
    IEclipsePreferences defaultNode = DefaultScope.INSTANCE.getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName());

    String instanceValue = Platform.getPreferencesService().get(optionName, null,
        new IEclipsePreferences[] { instanceNode });
    String defaultValue = Platform.getPreferencesService().get(optionName, null,
        new IEclipsePreferences[] { defaultNode });

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
  public boolean getBoolean(String name) {
    return getBoolean(true, name);
  }

  @Override
  public String getString(String name) {
    return getString(true, name);
  }

  @Override
  public int getInt(String name) {
    return getInt(true, name);
  }

  /**
   * @param project
   * @param optionName
   * @return
   */
  @Deprecated
  public static boolean getBoolean(boolean inProjectScope, String optionName) {
    IProject selectedCapellaProject = PreferencesHelper.getSelectedCapellaProject();
    return getBoolean(inProjectScope, optionName, selectedCapellaProject);
  }

  /**
   * @param preferenceShowCapellaProjectConcept_p
   * @param contentChild_p
   * @return
   */
  @Deprecated
  public static boolean getBoolean(String preferenceName, Object contentChild_p) {
    if ((contentChild_p instanceof Project) && (PreferencesHelper.getProject((Project) contentChild_p) != null)) {
      return getBoolean(true, preferenceName, PreferencesHelper.getProject((Project) contentChild_p));
    }
    return getBoolean(true, preferenceName);
  }

  private static IProject adapt(IProject selectedCapellaProject) {
    if (PreferencesHelper.hasConfigurationProject(selectedCapellaProject)) {
      return PreferencesHelper.getReferencedProjectConfiguration(selectedCapellaProject);
    }
    return selectedCapellaProject;
  }
  
  @Deprecated
  public static boolean getBoolean(boolean inProjectScope, String optionName, IProject selectedCapellaProject) {
    if (inProjectScope && (selectedCapellaProject != null)) {
      IProject project = adapt(selectedCapellaProject);
      if ((getProjectValue(project, optionName) != null) && (getProjectValue(project, optionName) instanceof String)) {
        return Boolean.valueOf((String) getProjectValue(project, optionName)).booleanValue();
      }
    }
    return Activator.getDefault().getPreferenceStore().getBoolean(optionName);
  }

  @Deprecated
  public static int getInt(boolean inProjectScope, String optionName) {
    IProject selectedCapellaProject = PreferencesHelper.getSelectedCapellaProject();
    if (inProjectScope && (selectedCapellaProject != null)) {
      IProject project = adapt(selectedCapellaProject);
      if ((getProjectValue(project, optionName) != null) && (getProjectValue(project, optionName) instanceof String)) {
        return Integer.valueOf((String) getProjectValue(project, optionName)).intValue();
      }
    }
    return Activator.getDefault().getPreferenceStore().getInt(optionName);
  }

  @Deprecated
  public static String getString(boolean inProjectScope, String optionName) {
    IProject selectedCapellaProject = PreferencesHelper.getSelectedCapellaProject();
    if (inProjectScope && (selectedCapellaProject != null)) {
      IProject project = adapt(selectedCapellaProject);
      if ((getProjectValue(project, optionName) != null) && (getProjectValue(project, optionName) instanceof String)) {
        return (String) getProjectValue(project, optionName);
      }
    }
    return Activator.getDefault().getPreferenceStore().getString(optionName);
  }

  /**
   * 
   * @param prefName name of the preference
   * @param capellaProject the project to look for preference value
   * @return value of the preference at the project scope if it is set. Otherwise return the value at the workspace scope.
   */
  public static boolean getBoolean(String prefName, IProject capellaProject) {
    if (capellaProject != null) {
      IProject project = adapt(capellaProject);
      if (getProjectValue(project, prefName) instanceof String) {
        return Boolean.parseBoolean(((String) getProjectValue(project, prefName)));
      }
    }
    return Activator.getDefault().getPreferenceStore().getBoolean(prefName);
  }

  /**
   * 
   * @param prefName name of the preference
   * @param capellaProject the project to look for preference value
   * @return value of the preference at the project scope if it is set. Otherwise return the value at the workspace scope.
   */
  public static int getInt(String prefName, IProject capellaProject) {
    if (capellaProject != null) {
      IProject project = adapt(capellaProject);
      if (getProjectValue(project, prefName) instanceof String) {
        return Integer.parseInt(((String) getProjectValue(project, prefName)));
      }
    }
    return Activator.getDefault().getPreferenceStore().getInt(prefName);
  }

  /**
   * 
   * @param prefName name of the preference
   * @param capellaProject the project to look for preference value
   * @return value of the preference at the project scope if it is set. Otherwise return the value at the workspace scope.
   */
  public static String getString(String prefName, IProject capellaProject) {
    if (capellaProject != null) {
      IProject project = adapt(capellaProject);
      if (getProjectValue(project, prefName) instanceof String) {
        return (String) getProjectValue(project, prefName);
      }
    }
    return Activator.getDefault().getPreferenceStore().getString(prefName);
  }
  /**
   * @param project
   * @param optionName
   * @return
   */
  public boolean containsKey(String optionName) {
    IProject selectedCapellaProject = PreferencesHelper.getSelectedCapellaProject();
    return containsKey(true, optionName, selectedCapellaProject);
  }

  @Deprecated
  public static boolean containsKey(boolean inProjectScope, String optionName, IProject selectedCapellaProject) {
    if (inProjectScope && (selectedCapellaProject != null)) {

      if (PreferencesHelper.hasConfigurationProject(selectedCapellaProject)) {
        final IProject configuration = PreferencesHelper.getReferencedProjectConfiguration(selectedCapellaProject);
        if (getProjectValue(configuration, optionName) != null) {
          return true;
        }
      }

      if (getProjectValue(selectedCapellaProject, optionName) != null) {
        return true;
      }
    }
    return Activator.getDefault().getPreferenceStore().contains(optionName);
  }

  /**
   * @param resource
   * @param key
   * @return
   */
  @Deprecated
  public static Object getValueFromPresistentPropertyStore(IProject resource, String key) {
    return getProjectValue(resource, key);
  }
  
  private static Logger getLogger() {
    return ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);
  }

  public static Object getProjectValue(IProject resource, String key) {
    
    try {
      if (resource.isAccessible() && resource.isOpen()) {
        Map<QualifiedName, String> properties = resource.getPersistentProperties() != null
            ? resource.getPersistentProperties() : new HashMap<QualifiedName, String>(0);
        for (QualifiedName qualifiedName : properties.keySet()) {
          QualifiedName qualified = qualifiedName;
          if (key.equals(qualified.getLocalName())) {
            return properties.get(qualifiedName);
          }
        }
      }
    } catch (CoreException exception_p1) {
      StringBuilder loggerMessage = new StringBuilder("Activator.initializePropertyStore(..) _ "); //$NON-NLS-1$
      getLogger().warn(loggerMessage.toString(), exception_p1);
    }

    return null;
  }

  @Override
  public void setValue(String name, boolean value) {
    IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
    for (IProject project : projects) {
      if (PreferencesHelper.hasConfigurationProject(project)) {
        IProject configuration = PreferencesHelper.getReferencedProjectConfiguration(project);
        if (getDefaultBoolean(name) == value) {
          new ProjectScope(configuration).getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName()).remove(name);
        } else {
          new ProjectScope(configuration).getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName()).putBoolean(name, value);
        }
      }
    }
    if (getDefaultBoolean(name) == value) {
      InstanceScope.INSTANCE.getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName()).remove(name);
    } else {
      InstanceScope.INSTANCE.getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName()).putBoolean(name, value);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(String name, String value) {
    IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
    for (IProject project : projects) {
      if (PreferencesHelper.hasConfigurationProject(project)) {
        
        IProject configuration = PreferencesHelper.getReferencedProjectConfiguration(project);
        if (getDefaultString(name) == value) {
          new ProjectScope(configuration).getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName()).remove(name);
        } else {
          new ProjectScope(configuration).getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName()).put(name, value);
        }
      }
    }
    
    if (getDefaultString(name) == value) {
      InstanceScope.INSTANCE.getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName()).remove(name);
    } else {
      InstanceScope.INSTANCE.getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName()).put(name, value);
    }
    
  }

  public void saveForExport() {
    try {
      IEclipsePreferences defaultPreferences = DefaultScope.INSTANCE.getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName());
      IEclipsePreferences instancePreferences = InstanceScope.INSTANCE.getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName());
      Collection<String> instanceKeys = Arrays.asList(instancePreferences.keys());
      for (String name: defaultPreferences.keys()) {
        if (!instanceKeys.contains(name)) {
          instancePreferences.put(name, defaultPreferences.get(name, ""));
        }
      }
    } catch (BackingStoreException e) {
      getLogger().warn(e.getMessage(), e);
    }
    save();
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
      getLogger().warn(loggerMessage.toString(), exception_p);
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
      getLogger().warn(loggerMessage.toString(), exception_p);
    }
  }

  /**
   * Deprecated: unexpected behavior, should use containsKey(String) [Bug 1127]
   */
  @Override
  @Deprecated
  public boolean contains(String name) {
    boolean contains = super.contains(name);
    IProject selectedCapellaProject = PreferencesHelper.getSelectedCapellaProject();
    IPreferencesService service = Platform.getPreferencesService();
    IScopeContext[] contexts;
    String key = (selectedCapellaProject != null) && !name.contains(selectedCapellaProject.getName())
        ? selectedCapellaProject.getName() + PREFERENCE_SEPARATOR + name : name;
    if ((selectedCapellaProject != null)) {
      contexts = new IScopeContext[] { getProjectScope(selectedCapellaProject), InstanceScope.INSTANCE,
          DefaultScope.INSTANCE };
    } else {
      contexts = new IScopeContext[] { InstanceScope.INSTANCE, DefaultScope.INSTANCE };
    }

    contains = service.getString(FrameworkUtil.getBundle(Activator.class).getSymbolicName(), key, null, contexts) != null;

    return contains;
  }

  /**
   * @param project
   */
  @Deprecated
  public static IScopeContext getProjectScope(IProject project) {
    final IProject realProject = project.getProject();
    if (projectScopes.get(realProject) != null) {
      return projectScopes.get(realProject);
    }
    return new ProjectScope(realProject);
  }

  @Deprecated
  public static Map<String, String> getOptions() {
    return options;
  }

  @Deprecated
  public static Map<IProject, IScopeContext> getProjectContexts() {

    return projectScopes;
  }

}
