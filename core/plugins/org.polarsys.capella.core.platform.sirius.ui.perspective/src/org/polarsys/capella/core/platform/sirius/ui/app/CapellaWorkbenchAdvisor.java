/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.activities.IActivityManager;
import org.eclipse.ui.activities.ICategory;
import org.eclipse.ui.activities.IWorkbenchActivitySupport;
import org.eclipse.ui.activities.WorkbenchActivityHelper;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.ide.undo.WorkspaceUndoUtil;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchErrorHandler;
import org.eclipse.ui.internal.ide.application.IDEWorkbenchAdvisor;
import org.eclipse.ui.statushandlers.AbstractStatusHandler;
import org.eclipse.ui.statushandlers.StatusAdapter;
import org.eclipse.ui.statushandlers.WorkbenchStatusDialogManager;
import org.osgi.service.prefs.BackingStoreException;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;
import org.polarsys.capella.core.model.handler.advisor.DelegateWorkbenchAdvisor;
import org.polarsys.capella.core.platform.sirius.ui.PerspectivePlugin;
import org.polarsys.capella.core.platform.sirius.ui.perspective.CapellaPerspective;

/**
 * Extends the {@link IDEWorkbenchAdvisor} to enable Capella features.
 */
public class CapellaWorkbenchAdvisor extends IDEWorkbenchAdvisor {
  /**
   * Configuration folder short name.
   */
  private static final String CONFIGURATION_FOLDER_SHORT_NAME = "configuration"; //$NON-NLS-1$
  /**
   * Config.ini file short name.
   */
  private static final String CONFIG_INI_FILE_SHORT_NAME = "config.ini"; //$NON-NLS-1$
  /**
   * Development category capability.
   */
  private static final String CATEGORY_DEVELOPMENT = "org.eclipse.categories.developmentCategory"; //$NON-NLS-1$
  /**
   * Team category capability.
   */
  private static final String CATEGORY_TEAM = "org.eclipse.categories.teamCategory"; //$NON-NLS-1$
  /**
   * CVS capability.
   */
  private static final String CATEGORY_TEAM_CVS = "org.eclipse.team.cvs"; //$NON-NLS-1$
  /**
   * CapellaVersion Tag used in about.mappings.
   */
  private static final String CAPELLA_VERSION_TAG = "CapellaVersion"; //$NON-NLS-1$
  /**
   * CapellaVersion Tag used in about.mappings.
   */
  private static final String BUILD_ID_TAG = "BuildId"; //$NON-NLS-1$

  private AbstractStatusHandler ideWorkbenchErrorHandler;

  /**
   * @see org.eclipse.ui.internal.ide.application.IDEWorkbenchAdvisor#createWorkbenchWindowAdvisor(org.eclipse.ui.application.IWorkbenchWindowConfigurer)
   */
  @Override
  public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
    return new CapellaWorkbenchWindowAdvisor(this, configurer);
  }

  /**
   * Disable non Capella activities : Java , Plugin, development, etc...<br>
   * End-user can reactivate these disabled activities from preferences.
   */
  @SuppressWarnings("unchecked")
  private void disableNonCapellaActivities() {
    // Disable development activities (i.e capabilities)
    IWorkbenchActivitySupport activitySupport = getWorkbenchConfigurer().getWorkbench().getActivitySupport();
    IActivityManager activityManager = activitySupport.getActivityManager();
    // Get all enabled activities.
    Set<String> allEnabledActivities = new HashSet<String>(activityManager.getEnabledActivityIds());
    Iterator<String> enabledCategories = WorkbenchActivityHelper.getEnabledCategories(activityManager).iterator();
    // finished is 'true' when equals to 2.
    int finished = 0;
    while (enabledCategories.hasNext() && (finished != 2)) {
      String categoryId = enabledCategories.next();
      if (CATEGORY_DEVELOPMENT.equals(categoryId)) {
        ICategory category = activityManager.getCategory(CATEGORY_DEVELOPMENT);
        Set<Object> developmentActivities = WorkbenchActivityHelper.getActivityIdsForCategory(category);
        // Remove development activities : Java & PDE.
        allEnabledActivities.removeAll(developmentActivities);
        finished++;
      } else if (CATEGORY_TEAM.equals(categoryId)) {
        ICategory category = activityManager.getCategory(CATEGORY_TEAM);
        Set<String> teamActivities = WorkbenchActivityHelper.getActivityIdsForCategory(category);
        for (String activityId : teamActivities) {
          // Remove CVS activity.
          if (CATEGORY_TEAM_CVS.equals(activityId)) {
            allEnabledActivities.remove(activityId);
            break;
          }
        }
        finished++;
      }
    }
    // Set Capella allowed activities.
    activitySupport.setEnabledActivityIds(allEnabledActivities);
  }

  /**
   * @see org.eclipse.ui.application.WorkbenchAdvisor#getInitialWindowPerspectiveId()
   */
  @Override
  public String getInitialWindowPerspectiveId() {
    return CapellaPerspective.PERSPECTIVE_ID;
  }

  /**
   * @see org.eclipse.ui.internal.ide.application.IDEWorkbenchAdvisor#preStartup()
   */
  @Override
  public void preStartup() {
    super.preStartup();

    // Disable capabilities during the first start of capella
    // See https://bugs.polarsys.org/show_bug.cgi?id=155
    IEclipsePreferences prefs = ConfigurationScope.INSTANCE.getNode(PerspectivePlugin.PLUGIN_ID);
    String key = "disableCapabilitiesOnStartUp";
    if (prefs.getBoolean(key, true)) {
      disableNonCapellaActivities();
      prefs.putBoolean(key, false);
      try {
        prefs.flush();
      } catch (BackingStoreException e) {
        PerspectivePlugin.getDefault().getLog().log(new Status(IStatus.ERROR, PerspectivePlugin.PLUGIN_ID, e.getMessage(), e));
      }
    }

    // Load SiriusEdit
    SiriusEditPlugin.getPlugin().getPreferenceStore().setValue(SiriusPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name(), true);
    // Disable Sirius Pre-commit listener behavior since Capella has the same one.
    SiriusTransPlugin.getPlugin().getPreferenceStore().setValue(CommonPreferencesConstants.PREF_DEFENSIVE_EDIT_VALIDATION, false);
    // force all workspace operations to be undoable
    // That's the easiest way to avoid undo operation on a capella project creation from clipboard (copy/paste).
    IUndoContext workspaceUndoContext = WorkspaceUndoUtil.getWorkspaceUndoContext();
    OperationHistoryFactory.getOperationHistory().setLimit(workspaceUndoContext, 0);
    // Set Capella version env property based on the version of the plug-in that provides the Capella product i.e this current plug-in.
    // Don't use Capella Feature version as it is also used in persistence of semantic models to get something working for both 1.x & 2.x releases.
    String bundleVersion = ((String) Platform.getProduct().getDefiningBundle().getHeaders().get("Bundle-version")); //$NON-NLS-1$
    System.setProperty(CAPELLA_VERSION_TAG, bundleVersion.substring(0, 5));
    System.setProperty(BUILD_ID_TAG, bundleVersion.substring(6));

    DelegateWorkbenchAdvisor.INSTANCE.callPreStartup();

    // MSidati : remove all acceleo UI elements from capella( remove all extensions from extensionRegistry).
    removeAllAcceleoIntroExtensionPoints();

  }

  /**
   * @see org.eclipse.ui.internal.ide.application.IDEWorkbenchAdvisor#postShutdown()
   */
  @Override
  public void postShutdown() {
    super.postShutdown();
    // Get the config.ini file used by runtime.
    IPath configFileUsedByRuntime = new Path(Platform.getConfigurationLocation().getURL().getFile()).append(CONFIG_INI_FILE_SHORT_NAME);
    // Get the config.ini declared in installation.
    IPath configFileFromInstallation =
        new Path(Platform.getInstallLocation().getURL().getFile()).append(CONFIGURATION_FOLDER_SHORT_NAME + ICommonConstants.SLASH_CHARACTER
                                                                          + CONFIG_INI_FILE_SHORT_NAME);
    // config.ini files are different ones.
    File installationConfigFile = configFileFromInstallation.toFile();
    if (installationConfigFile.exists() && !configFileUsedByRuntime.equals(configFileFromInstallation)) {
      Properties configFileAsProperties = new Properties();
      try {
        // Create a stream to load config.ini from installation.
        InputStream openStream = new FileInputStream(installationConfigFile);
        configFileAsProperties.load(openStream);
        // Close the stream.
        openStream.close();
        // Create a stream to save loaded config.ini as runtime one : to replace the one tweaked by p2 where keys are replaced with bad values e.g product,
        // application,...
        OutputStream out = new FileOutputStream(configFileUsedByRuntime.toFile());
        configFileAsProperties.store(out, "This configuration file was written by Capella"); //$NON-NLS-1$
        // Close the stream.
        out.close();
      } catch (Exception exception_p) {
        exception_p.printStackTrace();
      }
    }

    DelegateWorkbenchAdvisor.INSTANCE.callPostShutdown();
  }

  @Override
  public void postStartup() {
    DelegateWorkbenchAdvisor.INSTANCE.callPostStartup();
    super.postStartup();
    PreferencesHelper.removeEclipseProjectReferences(CapellaPerspective.PERSPECTIVE_ID);
    try {
      // force start of EMF Validation plugin before initializing the default preferences scope
      ModelValidationService.getInstance().loadXmlConstraintDeclarations();
      PreferencesHelper.initializeCapellaPreferencesFromEPFFile();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean preShutdown() {
    DelegateWorkbenchAdvisor.INSTANCE.callPreShutdown();
    return super.preShutdown();
  }

  /**
   * remove all acceleo UI elements from capella <br>
   * remove all acceleo extensions from Capella extensionRegistry</br>.
   */
  private void removeAllAcceleoIntroExtensionPoints() {
    Field privateStringField;
    try {
      privateStringField = ExtensionRegistry.class.getDeclaredField("masterToken"); //$NON-NLS-1$
      privateStringField.setAccessible(true);
      Object masterToken = privateStringField.get(Platform.getExtensionRegistry());
      // Reads the External Datatype Providers
      IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint("org.eclipse.ui.intro.configExtension"); //$NON-NLS-1$
      IConfigurationElement[] extensionPointArray = extensionPoint.getConfigurationElements();
      for (IConfigurationElement element : extensionPointArray) {
        if ((element.getNamespaceIdentifier() != null) && element.getNamespaceIdentifier().equals("org.eclipse.acceleo.ide.ui")) { //$NON-NLS-1$
          if (element.getAttribute("configId").equals("org.eclipse.ui.intro.universalConfig")) { //$NON-NLS-1$ //$NON-NLS-2$
            Platform.getExtensionRegistry().removeExtension(element.getDeclaringExtension(), masterToken);
            break;
          }
        }
      }
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  /**
   * Override the default error message to avoid exception message in the main message. (it is already displayed in Details part)
   */
  @Override
  public synchronized AbstractStatusHandler getWorkbenchErrorHandler() {
    if (ideWorkbenchErrorHandler == null) {
      ideWorkbenchErrorHandler = new IDEWorkbenchErrorHandler(getWorkbenchConfigurer()) {
        @Override
        protected void configureStatusDialog(WorkbenchStatusDialogManager statusDialog) {
          statusDialog.setMessageDecorator(new ILabelDecorator() {

            @Override
            public String decorateText(String text, Object element) {
              // We want to always display WorkbenchMessages.WorkbenchStatusDialog_SeeDetails, not the exception message
              if (element instanceof StatusAdapter) {
                if (text != null) {
                  IStatus status = ((StatusAdapter) element).getStatus();
                  if (status != null) {
                    Throwable exception = status.getException();
                    if ((exception != null) && (exception.getMessage() != null) && (status.getMessage() != null)
                        && !status.getMessage().equals(exception.getMessage())) {
                      if (text.equals(status.getException().getMessage())) {
                        return WorkbenchMessages.WorkbenchStatusDialog_SeeDetails;
                      }
                    }
                  }
                }
              }
              return text;
            }

            @Override
            public void removeListener(ILabelProviderListener listener) {
              // Nothing here
            }

            @Override
            public boolean isLabelProperty(Object element, String property) {
              return false;
            }

            @Override
            public void dispose() {
              // Nothing here
            }

            @Override
            public void addListener(ILabelProviderListener listener) {
              // Nothing here
            }

            @Override
            public Image decorateImage(Image image, Object element) {
              return image;
            }
          });

        }
      };

    }
    return ideWorkbenchErrorHandler;
  }
}
