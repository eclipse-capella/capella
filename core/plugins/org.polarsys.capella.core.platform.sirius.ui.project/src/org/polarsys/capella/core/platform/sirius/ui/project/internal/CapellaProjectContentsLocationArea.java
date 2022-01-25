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
package org.polarsys.capella.core.platform.sirius.ui.project.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.osgi.util.NLS;
import org.eclipse.osgi.util.TextProcessor;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.core.platform.sirius.ui.project.CapellaProjectActivator;
import org.polarsys.capella.core.platform.sirius.ui.project.provider.IDefaultProjectLocationProvider;

/**
 */
public class CapellaProjectContentsLocationArea extends ProjectContentsLocationArea {
  /**
   * Extension-point short id for default project location provider.
   */
  private static final String PROJECT_LOCATION_PROVIDER_EXTENSION_POINT_ID = "defaultProjectLocationProvider"; //$NON-NLS-1$
  /**
   * Default project location provider.
   */
  private IDefaultProjectLocationProvider _defaultProjectLocationProvider;

  /**
   * Constructor.
   * @param reporter_p
   * @param composite_p
   * @param startProject_p
   */
  public CapellaProjectContentsLocationArea(IErrorMessageReporter reporter_p, Composite composite_p, IProject startProject_p) {
    super(reporter_p, composite_p, startProject_p);
  }

  /**
   * Constructor.
   * @param reporter_p
   * @param composite_p
   */
  public CapellaProjectContentsLocationArea(IErrorMessageReporter reporter_p, Composite composite_p) {
    super(reporter_p, composite_p);
  }

  /**
   * @see org.polarsys.capella.core.platform.sirius.ui.project.internal.ProjectContentsLocationArea#createContents(org.eclipse.swt.widgets.Composite, boolean)
   */
  @Override
  protected void createContents(Composite composite_p, boolean defaultEnabled_p) {
    super.createContents(composite_p, defaultEnabled_p);
    // Force to set internal field to the appropriate location. Fake the "browse..." button click.
    updateLocationField(getDefaultPathDisplayString());
  }

  /**
   * Get default project location contribution if any.
   * @return <code>null</code> if no contribution found.
   */
  protected IDefaultProjectLocationProvider getDefaultProjectLocationContribution() {
    IDefaultProjectLocationProvider contribution = null;
    IConfigurationElement[] configurationElements =
        ExtensionPointHelper.getConfigurationElements(FrameworkUtil.getBundle(CapellaProjectActivator.class).getSymbolicName(), PROJECT_LOCATION_PROVIDER_EXTENSION_POINT_ID);
    if (configurationElements.length > 0) {
      // There should be one implementation only !
      // So take the first one, as expected.
      Object instantiatedClass = ExtensionPointHelper.createInstance(configurationElements[0], ExtensionPointHelper.ATT_CLASS);
      // Make sure this is the correct resulting type.
      if (instantiatedClass instanceof IDefaultProjectLocationProvider) {
        contribution = (IDefaultProjectLocationProvider) instantiatedClass;
      }
    }
    return contribution;
  }

  /**
   * @see org.polarsys.capella.core.platform.sirius.ui.project.internal.ProjectContentsLocationArea#getDefaultPathDisplayString()
   */
  @Override
  protected String getDefaultPathDisplayString() {
    // Indeed, every character entered in the project name text field calls this method.
    if (null == _defaultProjectLocationProvider) {
      _defaultProjectLocationProvider = getDefaultProjectLocationContribution();
    }
    if (null == _defaultProjectLocationProvider) {
      return super.getDefaultPathDisplayString();
    }
    // A contribution was found, get the default project location from it.
    String defaultProjectLocation = _defaultProjectLocationProvider.getDefaultProjectLocation();
    // Contribution could return null if an error occurred.
    return (null == defaultProjectLocation) ? super.getDefaultPathDisplayString() : new Path(defaultProjectLocation).append(getProjectName()).toString();
  }

  /**
   * @see org.polarsys.capella.core.platform.sirius.ui.project.internal.ProjectContentsLocationArea#updateProjectName(java.lang.String)
   */
  @Override
  public void updateProjectName(String newName_p) {
    super.updateProjectName(newName_p);
    // If a contribution is provided, update the project location text field accordingly.
    if ((null != _defaultProjectLocationProvider) && isUseDefaultButtonChecked()) {
      updateLocationField(TextProcessor.process(getDefaultPathDisplayString()));
    }
  }

  /**
   * @see org.polarsys.capella.core.platform.sirius.ui.project.internal.ProjectContentsLocationArea#checkValidLocation()
   */
  @Override
  public String checkValidLocation() {
    String isValid = super.checkValidLocation();
    if (null == isValid) {
      String projectName = getProjectName();
      if (projectName.contains(ICommonConstants.EMPTY_STRING + '&')) {
        isValid =
            NLS.bind(Messages.CapellaProjectContentsLocationArea_CapellaProjectContentsLocationArea_ErrorMessage_ForbiddenCharacter, Character.valueOf('&'));
        return isValid;
      }
      // Check a project folder doesn't already exist.
      IPath projectFolder = null;
      String defaultPathDisplayString = getDefaultPathDisplayString();
      if ((null != _defaultProjectLocationProvider) && isUseDefaultButtonChecked()) {
        if (!ICommonConstants.EMPTY_STRING.equals(defaultPathDisplayString)) {
          // Project location is provided by a contributor and the use default location is checked.
          // Default Extension location path.
          projectFolder = new Path(defaultPathDisplayString);
        } else {
          isValid = _defaultProjectLocationProvider.getErrorMessage();
        }
      } else if (isDefault()) {
        // Default path without contribution.
        projectFolder = new Path(defaultPathDisplayString);
      } else {
        // User-defined path : whatever the location is contributed or not.
        projectFolder = new Path(getProjectLocation()).append(projectName);
      }
      if ((null != projectFolder) && projectFolder.toFile().exists()) {
        isValid = Messages.CapellaProjectContentsLocationArea_ErrorMessage_FolderAlreadyExists;
      }
    }
    return isValid;
  }

  /**
   * @see org.polarsys.capella.core.platform.sirius.ui.project.internal.ProjectContentsLocationArea#getProjectLocation()
   */
  @Override
  public String getProjectLocation() {
    String projectLocation = super.getProjectLocation();
    if ((null != _defaultProjectLocationProvider) && isUseDefaultButtonChecked()) {
      // If project location is provided by a contributor we must remove the last segment to return a project location that can be used by other API
      // as a not default project location. That's weird but that's life.
      if (null != _defaultProjectLocationProvider.getDefaultProjectLocation()) {
        projectLocation = new Path(projectLocation).removeLastSegments(1).toString();
      }
    }
    return projectLocation;
  }

  /**
   * Return whether or not we are currently showing the default location for the project.
   * @return <code>true</code> means the 'Use default location' check button is selected.
   */
  protected boolean isUseDefaultButtonChecked() {
    return useDefaultsButton.getSelection();
  }

  /**
   * @see org.polarsys.capella.core.platform.sirius.ui.project.internal.ProjectContentsLocationArea#isDefault()
   */
  @Override
  public boolean isDefault() {
    if ((null == _defaultProjectLocationProvider) || (null == _defaultProjectLocationProvider.getDefaultProjectLocation())) {
      return super.isDefault();
    }
    // It is not the default location even if "use defaults" check box was selected.
    return false;
  }
}
