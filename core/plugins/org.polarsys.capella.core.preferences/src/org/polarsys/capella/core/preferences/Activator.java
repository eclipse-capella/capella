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
package org.polarsys.capella.core.preferences;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.wizards.preferences.PreferencesExportWizard;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.Event;
import org.polarsys.capella.core.commands.preferences.initializers.CapellaDiagramPreferencesInitializer;
import org.polarsys.capella.core.commands.preferences.internalization.l10n.CustomPreferencesMessages;
import org.polarsys.capella.core.commands.preferences.model.CategoryPreferences;
import org.polarsys.capella.core.commands.preferences.model.CategoryPreferencesManager;
import org.polarsys.capella.core.commands.preferences.properties.PreferencesHandler;
import org.polarsys.capella.core.commands.preferences.service.PreferencesItemsRegistry;
import org.polarsys.capella.core.commands.preferences.service.PropertyStore;
import org.polarsys.capella.core.commands.preferences.service.ScopedCapellaPreferencesStore;
import org.polarsys.capella.core.commands.preferences.util.PreferencesExtensionHandler;
import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;
import org.polarsys.capella.core.commands.preferences.util.XmlPreferencesConfig;

/**
 * The Preferences activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

  /*
   * Track extensions for extension points defined in this bundle.
   */
  private ExtensionTracker extensionTracker = new ExtensionTracker();

  /*
   * Key for list separator.
   */
  private static final String LIST_SEPARATOR = CustomPreferencesMessages.list_separator;

  /** Key for list prefix. */
  private static final String LIST_PREFIX = CustomPreferencesMessages.list_prefix;

  /** Key for list suffix. */
  private static final String LIST_SUFFIX = CustomPreferencesMessages.list_suffix;

  /** Key for default list separator. */
  private static final String DEFAULT_LIST_SEPARATOR = ", "; //$NON-NLS-1$

  /** Key for default list prefix. */
  private static final String DEFAULT_LIST_PREFIX = ""; //$NON-NLS-1$

  /** Key for default list suffix. */
  private static final String DEFAULT_LIST_SUFFIX = ""; //$NON-NLS-1$

  /**
   * Extension point name for the constraint providers extension point.
   */
  public static final String PREFERENCES_PROVIDERS_EXT_P_NAME = "capellaPreferences"; //$NON-NLS-1$

  // The plug-in ID
  public static final String PLUGIN_ID = "org.polarsys.capella.core.preferences"; //$NON-NLS-1$

  // The shared instance
  private static Activator plugin;

  private static final String ICONS = "icons/"; //$NON-NLS-1$

  private PreferencesExtensionHandler extensionHandler;

  /**
   * The constructor
   */
  public Activator() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    extensionTracker = new ExtensionTracker();
    extensionHandler = new PreferencesExtensionHandler();
    plugin = this;
    PreferencesItemsRegistry.getInstance().getAllDescriptors();
    initializeExtensionsPointProvider();

    CategoryPreferencesManager.getInstance().loadUserProfile();
    PreferencesHandler.initializePreferenceCommands();
    
    new CapellaDiagramPreferencesInitializer(PLUGIN_ID);

    IEventBroker eventBroker = PlatformUI.getWorkbench().getService(IEventBroker.class);
    if (eventBroker != null) {
      eventBroker.subscribe(PreferencesExportWizard.EVENT_EXPORT_BEGIN, new org.osgi.service.event.EventHandler() {
        
        @Override
        public void handleEvent(Event event) {
          ScopedCapellaPreferencesStore.getInstance(Activator.PLUGIN_ID).saveForExport();

          IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
          for (IProject project : projects) {
            if (PreferencesHelper.hasConfigurationProject(project)) {
              try {
                IProject configProject = PreferencesHelper.getReferencedProjectConfiguration(project);
                new ProjectScope(configProject).getNode(Activator.PLUGIN_ID).flush();
                configProject.refreshLocal(IResource.DEPTH_INFINITE, null);
                project.refreshLocal(IResource.DEPTH_INFINITE, null);
              } catch (Exception exception) {
                getLog().error(exception.getMessage(), exception);
              }
            }
          }
        }
      });
    }
  }

  // Overlay preference store for property pages
  public static Map<IResource, IPreferenceStore> propertiesStore = new HashMap<IResource, IPreferenceStore>();

  IPreferenceStore preferenceStore = null;

  @Override
  public IPreferenceStore getPreferenceStore() {
    // Create the preference store lazily.
    if (preferenceStore == null) {
      preferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, Activator.PLUGIN_ID);
    }
    return preferenceStore;
  }

  public IPreferenceStore getPropertyPreferenceStore(IResource project) {
    return propertiesStore.get(project);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
    extensionTracker.close();
    extensionTracker = null;
  }

  /**
   * 
   */
  private void initializeExtensionsPointProvider() {
    final CategoryPreferences globalCategory = CategoryPreferences.GLOBAL_NAMESPACE;
    final IExtensionChangeHandler extensionHandler = new IExtensionChangeHandler() {
      public void addExtension(IExtensionTracker tracker, IExtension extension) {
        for (IConfigurationElement next : extension.getConfigurationElements()) {
          if (next.getName().equals(XmlPreferencesConfig.ELEMENT_CATEGORY)) {
            CategoryPreferencesManager.getInstance().loadCategories(globalCategory, next);
          } else if (next.getName().equals(XmlPreferencesConfig.ELEMENT_PROJECT_NATURE)) {
            CategoryPreferencesManager.getInstance().loadProjectsNature(globalCategory, next);
          }
        }
      }

      public void removeExtension(IExtension extension, Object[] objects) {
        // category definitions cannot be removed
      }
    };

    extensionTracker.registerHandler(extensionHandler, null);

    IExtensionPoint extPoint = Platform.getExtensionRegistry().getExtensionPoint(Activator.PLUGIN_ID,
        Activator.PREFERENCES_PROVIDERS_EXT_P_NAME);
    IExtensionTracker extTracker = Activator.getExtensionTracker();
    if (extTracker != null) {
      extTracker.registerHandler(extensionHandler, ExtensionTracker.createExtensionPointFilter(extPoint));
      for (IExtension extension : extPoint.getExtensions()) {
        extensionHandler.addExtension(extTracker, extension);
      }
    }
  }

  /**
   * Returns the shared instance
   * 
   * @return the shared instance
   */
  public static Activator getDefault() {
    return plugin;
  }

  /**
   * @return utility to track extensions managed by this bundle
   */
  public static IExtensionTracker getExtensionTracker() {
    return (plugin == null) ? null : plugin.extensionTracker;
  }

  /**
   * Loads the category definitions from my constraintCategories extension point.
   */
  public void loadCategories() {
    if (Platform.isRunning()) {

      IExtensionPoint extPoint = Platform.getExtensionRegistry().getExtensionPoint(Activator.PLUGIN_ID,
          Activator.PREFERENCES_PROVIDERS_EXT_P_NAME);

      IExtensionTracker extTracker = Activator.getExtensionTracker();
      if (extTracker != null) {
        extTracker.registerHandler(extensionHandler, ExtensionTracker.createExtensionPointFilter(extPoint));

        for (IExtension extension : extPoint.getExtensions()) {
          extensionHandler.addExtension(extTracker, extension);
        }
      }
    }
  }

  /**
   * <p>
   * Returns an {@link ImageDescriptor}whose path, relative to the plugin directory's <tt>icons/</tt> directory, is
   * <code>imageFile</code>. If the image descriptor cannot be created, either because the file does not exist or
   * because of an internal error, then the result is the Eclipse default "missing image" descriptor.
   * </p>
   * <p>
   * <b>Note </b> that the file specified must not have any leading "." or path separators "/" or "\". It is strictly
   * relative to the <tt>icons/</tt> directory.
   * 
   * @param imageFile
   *          the name of the image file to retrieve
   * @return the corresponding image descriptor
   */
  public static ImageDescriptor getImageDescriptor(String imageFile) {
    URL fullPath = FileLocator.find(getDefault().getBundle(), new Path(ICONS + imageFile), null);
    if (fullPath != null) {
      return ImageDescriptor.createFromURL(fullPath);
    }
    return ImageDescriptor.getMissingImageDescriptor();
  }

  /**
   * Creates a localized, parameterized message from the specified pattern and argument keys in the resource bundle.
   * 
   * @param messagePattern
   *          resource bundle key of the message pattern
   * @param args
   *          literal values as arguments to the pattern
   * @return the formatted message
   * @see org.eclipse.osgi.util.NLS
   */
  public static String getMessage(String messagePattern, Object... args) {
    return formatMessage(messagePattern, args);
  }

  /**
   * Creates a localized, parameterized message from the specified pattern in the resource bundle.
   * 
   * @param messagePattern
   *          the message pattern
   * @param args
   *          objects to substitute into the <tt>{0}</tt>, <tt>{1}</tt>, etc. parameters in the message pattern
   * @return the formatted message
   * @see org.eclipse.osgi.util.NLS
   */
  private static String formatMessage(String messagePattern, Object... args) {
    try {
      return NLS.bind(messagePattern, args);
    } catch (Exception e) {
      e.printStackTrace();

      return messagePattern; // better than nothing?
    }
  }

  /**
   * <p>
   * Formats a collection of objects according to the conventions of the locale. For example, in English locales, the
   * result is a comma-separated list with "and" preceding the last item (no commas if there are only two items).
   * </p>
   * <p>
   * The individual elements of the collection are converted to strings using the
   * {@link String#valueOf(java.lang.Object)} method.
   * </p>
   * 
   * @param items
   *          an array of objects to format into a list
   * @return the list, <code>strings[0]</code> if there is only one element, or <code>""</code> if the array has no
   *         elements
   */
  public static String formatList(Collection<?> items) {
    switch (items.size()) {
    case 0:
      return ""; //$NON-NLS-1$
    case 1:
      return String.valueOf(items.iterator().next());
    case 2:
      return formatPair(items);
    default:
      return formatList2(items);
    }
  }

  /**
   * Helper method to format a list of more than two items.
   * 
   * @param mgr
   *          the common core plug-in's resource manager, which is used to retrieve the localized components of a list
   * @param items
   *          the list of items (must be more than two)
   * @return the list as a string
   * @see #formatList(Collection)
   */
  private static String formatList2(Collection<?> items) {
    Iterator<?> iter = items.iterator();
    int max = items.size() - 1;

    final String sep = getString(LIST_SEPARATOR, DEFAULT_LIST_SEPARATOR);

    StringBuffer result = new StringBuffer(32);

    result.append(getString(LIST_PREFIX, DEFAULT_LIST_PREFIX));

    for (int i = 0; i <= max; i++) {
      if (i == 1) {
        result.append(getString(LIST_SEPARATOR, sep));
      } else if (i == max) {
        result.append(getString(LIST_SEPARATOR, sep));
      } else if (i > 1) {
        result.append(sep);
      }

      result.append(iter.next());
    }

    result.append(getString(LIST_SUFFIX, DEFAULT_LIST_SUFFIX));

    return result.toString();
  }

  private static String getString(String message, String defaultResult) {
    return message != null ? message : defaultResult;
  }

  /**
   * Helper method to format a two-item list (which in some locales looks different from a list of more than two items).
   * 
   * @param mgr
   *          the common core plug-in's resource manager, which is used to retrieve the localized components of a list
   * @param items
   *          the pair of items (must be exactly two)
   * @return the pair as a string
   * @see #formatList(Collection)
   */
  private static String formatPair(Collection<?> items) {
    Iterator<?> iter = items.iterator();

    StringBuffer result = new StringBuffer(32);

    result.append(iter.next());

    result.append(getString(LIST_SEPARATOR, DEFAULT_LIST_SEPARATOR));

    result.append(iter.next());

    return result.toString();
  }

  /**
   * @param iResource
   * @param propertiesStore
   */
  public void setPropertyStore(IResource iResource, IPreferenceStore propertiesStore) {
    Activator.propertiesStore.put(iResource, propertiesStore);
  }

  /**
   * @param capellaCommonNavigator
   */
  public void addProjectsPropertyChangeListener(IPropertyChangeListener capellaCommonNavigator) {
    PropertyStore.addToGuestListener(capellaCommonNavigator);
  }

}
