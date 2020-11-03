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
package org.polarsys.capella.core.commands.preferences.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.eclipse.core.internal.preferences.EclipsePreferences;
import org.eclipse.core.internal.preferences.PrefsMessages;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.validation.preferences.EMFModelValidationPreferences;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.preferences.Activator;

public class CapellaPreferencesService {

  private static final char EXPORT_ROOT_PREFIX = '!';
  private static final char BUNDLE_VERSION_PREFIX = '@';
  private static final String VERSION_KEY = "file_export_version"; //$NON-NLS-1$
  private static final String EMPTY_STRING = ""; //$NON-NLS-1$
  private static final String EPF_PATH_ARGUMENT_ID = "-EPF"; //$NON-NLS-1$
  
  /**
   * Category path for exportedCommon EMF Constraint .
   */
  public final static String KEY_PREFIX = "org.eclipse.emf.validation"; //$NON-NLS-1$

  /*
   * @see org.eclipse.core.runtime.preferences.IPreferencesService#importPreferences(java.io.InputStream)
   */

  public void initializePreferences(final InputStream input) {
    Display.getDefault().syncExec(new Runnable() {

      @Override
      public void run() {
        try {
          applyPreferences(readEPFPreferencesFile(input));
        } catch (CoreException exception_p) {
          getLogger().error(exception_p.getMessage());
        }
      }
    });
  }

  /**
   * return the EPF file path from Capella application arguments
   * @return
   */
  public static String getEPFPathFromApplicationArguments() {
	
    if (Platform.inDebugMode()) {
      getLogger().debug("Start Capella Preferences initialization From EPF File"); //$NON-NLS-1$
    }
    String epfPath = ""; //$NON-NLS-1$
    String[] args = Platform.getApplicationArgs();

    for (int i = 0; i < args.length; i++) {
      String currentArgument = args[i].toLowerCase();
      if (EPF_PATH_ARGUMENT_ID.equalsIgnoreCase(currentArgument)) {
        epfPath = args[++i];
      }
    }

    if (Platform.inDebugMode()) {
      getLogger().debug("funded EPF File : " + epfPath); //$NON-NLS-1$
    }

    return epfPath;
  }

  /**
   * @param readEPFPreferencesFile_p
   * @return
   */
  private void applyPreferences(IEclipsePreferences readEPFPreferencesFile_p) {
    try {
      flushPreferences(readEPFPreferencesFile_p);
    } catch (CoreException exception_p) {
      createStatusError("Fail to initialize Capella Preferences From File : ", exception_p); //$NON-NLS-1$
    }
  }

  /*
   * @see org.eclipse.core.runtime.preferences.IPreferencesService#readPreferences(java.io.InputStream)
   */
  public IEclipsePreferences readEPFPreferencesFile(InputStream input) throws CoreException {
    if (input == null) {
      throw new IllegalArgumentException();
    }

    // read the file into a properties object
    Properties properties = new Properties();
    try {
      properties.load(input);
    } catch (IOException e) {
      throw new CoreException(createStatusError(PrefsMessages.preferences_importProblems, e));
    } finally {
      try {
        input.close();
      } catch (IOException e) {
        // ignore
      }
    }

    // an empty file is an invalid file format
    if (properties.isEmpty()) {
      throw new CoreException(createStatusError(PrefsMessages.preferences_invalidFileFormat, null));
    }

    // manipulate the file if it from a legacy preference export
    if (isLegacy(properties)) {
      if (EclipsePreferences.DEBUG_PREFERENCE_GENERAL) {
        PrefsMessages.message("Read legacy preferences file, converting to 3.0 format..."); //$NON-NLS-1$
      }
      properties = convertFromLegacy(properties);
    } else {
      if (EclipsePreferences.DEBUG_PREFERENCE_GENERAL) {
        PrefsMessages.message("Read preferences file."); //$NON-NLS-1$
      }
      properties.remove(VERSION_KEY);
    }

    // convert the Properties object into an object to return
    return convertFromProperties(properties);
  }

  /*
   * Convert the given properties file into a node hierarchy suitable for importing.
   */
  private IEclipsePreferences convertFromProperties(Properties properties) {
    IEclipsePreferences root = Platform.getPreferencesService().getRootNode();

    for (Object element : properties.keySet()) {
      String path = (String) element;
      String value = properties.getProperty(path);

      String[] decoded = EclipsePreferences.decodePath(path);
      path = decoded[0] == null ? EMPTY_STRING : decoded[0];
      IEclipsePreferences current = (IEclipsePreferences) root.node(path);
     
      String key = decoded[1];
      if ((key != null) && (path != null) && isEMFValidationNode(path)) {
        key = key.replaceFirst("con.disabled/", ICommonConstants.EMPTY_STRING);
        boolean bool = Boolean.parseBoolean(value);
        EMFModelValidationPreferences.setConstraintDisabled(key, Boolean.parseBoolean(value));

        EMFModelValidationPreferences.save();

      }
      current.put(key, value);
      InstanceScope.INSTANCE.getNode(Activator.PLUGIN_ID).put(key, value);
      // new DefaultScope().getNode(Activator.PLUGIN_ID).put(key, value);

    }

    return root;
  }

  /**
   * @param path
   * @return
   */
  private boolean isEMFValidationNode(String path) {
    return (path != null) && path.contains(KEY_PREFIX);
  }

  /*
   * Create and return an IStatus object with ERROR severity and the given message and exception.
   */
  private static IStatus createStatusError(String message, Exception e) {
    return new Status(IStatus.ERROR, PrefsMessages.OWNER_NAME, IStatus.ERROR, message, e);
  }

  /*
   * Returns a boolean value indicating whether or not the given Properties object is the result of a preference export previous to Eclipse 3.0. Check the
   * contents of the file. In Eclipse 3.0 we printed out a file version key.
   */
  private boolean isLegacy(Properties properties) {
    return properties.getProperty(VERSION_KEY) == null;
  }

  /*
   * Convert the given properties file from legacy format to one which is Eclipse 3.0 compliant. Convert the plug-in version indicator entries to export roots.
   */
  private Properties convertFromLegacy(Properties properties) {
    Properties result = new Properties();
    String prefix = IPath.SEPARATOR + InstanceScope.SCOPE + IPath.SEPARATOR;
    for (Object element : properties.keySet()) {
      String key = (String) element;
      String value = properties.getProperty(key);
      if (value != null) {
        int index = key.indexOf(IPath.SEPARATOR);
        if (index == -1) {
          result.put(BUNDLE_VERSION_PREFIX + key, value);
          result.put(EXPORT_ROOT_PREFIX + prefix + key, EMPTY_STRING);
        } else {
          String path = key.substring(0, index);
          key = key.substring(index + 1);
          result.put(EclipsePreferences.encodePath(prefix + path, key), value);
        }
      }
    }
    return result;
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.core.runtime.preferences.IPreferencesService#applyPreferences(org.eclipse.core.runtime.preferences.IEclipsePreferences,
   * org.eclipse.core.runtime.preferences.IPreferenceFilter[])
   */

  public void flushPreferences(IEclipsePreferences root) throws CoreException {

    try {

      root.flush();
    } catch (Exception e) {
      throw new CoreException(createStatusError(PrefsMessages.preferences_applyProblems, e));
    }
  }
  
  private static Logger getLogger() {
    return ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);
  }
}
