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
package org.polarsys.capella.core.sirius.ui.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.common.mdsofa.common.helper.ProjectHelper;
import org.polarsys.capella.common.mdsofa.common.helper.StringHelper;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.Messages;

/**
 * Dialog to handle Capella model fragment creation.
 */
public class ControlResourceDialog extends ResourceDialog {
  protected EditingDomain _domain;
  protected Resource _resource;
  protected Resource _currentResource;
  protected EObject _controledObject;

  /**
   * Constructor.
   * 
   * @param parent_p
   * @param domain_p
   * @param currentResource_p
   */
  public ControlResourceDialog(Shell parent_p, EditingDomain domain_p, Resource currentResource_p,
      final EObject controledObject) {
    super(parent_p, Messages.ControlAction_Window_Title, SWT.SAVE);
    _domain = domain_p;
    _currentResource = currentResource_p;
    _controledObject = controledObject;
  }

  /**
   * Create the content of this dialog.<br>
   * The code is copied from {@link #createDialogArea(Composite)} super method.
   * 
   * @param parent_p
   * @return
   */
  protected Control doCreateDialogArea(Composite parent_p) {
    // Code from Dialog.createDialogArea.
    // Create a composite with standard margins and spacing
    Composite composite = new Composite(parent_p, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
    layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
    layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
    layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
    composite.setLayout(layout);
    composite.setLayoutData(new GridData(GridData.FILL_BOTH));
    applyDialogFont(composite);

    // Label.
    Label resourceURILabel = new Label(composite, SWT.NULL);
    resourceURILabel.setText(Messages.ControlAction_Label_Title);
    resourceURILabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
    // Text field.
    uriField = new Text(composite, SWT.SINGLE | SWT.BORDER);
    uriField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    return composite;
  }

  /**
   * @see org.eclipse.emf.common.ui.dialogs.ResourceDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createDialogArea(Composite parent) {
    final Control control = doCreateDialogArea(parent);
    String defaultUri = computeFragmentUri();
    this.uriField.setText(URI.encodeFragment(defaultUri, true));
    return control;
  }

  /**
   * Compute default fragment URI.<br>
   * Default implementation computes a fragment URI located in the project under
   * {@link CapellaResourceHelper#FRAGMENTS_DEFAULT_FOLDER} folder.
   * 
   * @return must be not <code>null</code>.
   */
  protected String computeFragmentUri() {
    StringBuilder defaultURI = new StringBuilder();
    URI fragmentFolderUri = _controledObject.eResource().getURI().trimSegments(1);
    // Add fragments folder if necessary.
    if (!CapellaResourceHelper.FRAGMENTS_DEFAULT_FOLDER.equals(fragmentFolderUri.lastSegment())) {
      fragmentFolderUri = fragmentFolderUri.appendSegment(CapellaResourceHelper.FRAGMENTS_DEFAULT_FOLDER);
    }
    defaultURI.append(URI.decode(fragmentFolderUri.toString()));
    defaultURI.append(ICommonConstants.SLASH_CHARACTER);

    // Format name = full label path, with Camel Case to shorten the path.
    ModelElement controlledModelElement = (ModelElement) _controledObject;

    // Special characters need a specific encoding
    IPath fullObjectPath = new Path(getFullLabel(controlledModelElement));
    // Remove Project name and SystemEngineering name
    fullObjectPath = fullObjectPath.removeFirstSegments(2);
    // Make analysis level name shortest as possible (camel case based).
    String analyisName = fullObjectPath.segment(0);
    if (null != analyisName) {
      // Remove analysis long name.
      fullObjectPath = fullObjectPath.removeFirstSegments(1);
      IPath fragmentRelativePath = new Path(getCamelCaseRepresentation(analyisName));
      for (String segment : fullObjectPath.segments()) {
        String appendedSegment = segment;
        // Replacing "Root XXXX Function" expression by its camel case representation.
        if (segment.matches("(Root\\s).*(\\sFunction)")) { //$NON-NLS-1$
          appendedSegment = getCamelCaseRepresentation(segment);
        }
        fragmentRelativePath = fragmentRelativePath.append(appendedSegment);
      }
      // Append the new name starting with came case analysis level.
      String fragmentRelative = fragmentRelativePath.toString();
      // Replace '/' character to avoid sub folders.
      fragmentRelative = fragmentRelative.replace(ICommonConstants.SLASH_CHARACTER, '-');
      defaultURI.append(fragmentRelative);
      defaultURI.append(ICommonConstants.POINT_CHARACTER);
      defaultURI.append(CapellaResourceHelper.CAPELLA_FRAGMENT_FILE_EXTENSION);
    }
    return defaultURI.toString();
  }

  private String getFullLabel(ModelElement object_p) {
    String result;
    result = getFullPath(object_p) + getLabel(object_p);
    return result;
  }

  public static String getFullPath(EObject object_p) {
    String result;
    EObject eContainer = object_p.eContainer();
    if (eContainer == object_p) {
      return ICommonConstants.EMPTY_STRING;
    }
    if (eContainer != null) {
      result = getFullPath(eContainer) + getLabel(eContainer) + ICommonConstants.SLASH_CHARACTER;
    } else {
      result = Character.toString(ICommonConstants.SLASH_CHARACTER);
    }
    return result;
  }

  public static String getLabel(EObject object_p) {
    StringBuilder result = new StringBuilder();
    for (char car : org.polarsys.capella.common.model.label.LabelRetriever.getLabel(object_p).toCharArray()) {
      if (Character.isJavaIdentifierPart(car) || Character.isWhitespace(car)) {
        result.append(car);
      }
    }

    return result.toString();
  }

  /**
   * Get Camel case representation for specified word.
   * 
   * @param word_p
   */
  private String getCamelCaseRepresentation(String word_p) {
    StringBuilder camelCaseAnalysisLevel = new StringBuilder();
    for (char currentCharacter : word_p.toCharArray()) {
      if (Character.isUpperCase(currentCharacter)) {
        camelCaseAnalysisLevel.append(currentCharacter);
      }
    }
    return camelCaseAnalysisLevel.length() == 0 ? word_p : camelCaseAnalysisLevel.toString();
  }

  /**
   *
   */
  private String[] XMLEscapeCharacters = { "\"", //$NON-NLS-1$
      "'", //$NON-NLS-1$
      "<", //$NON-NLS-1$
      ">", //$NON-NLS-1$
      "&" //$NON-NLS-1$
  };

  /**
   *
   */
  private List<String> getInvalidCharacters() {
    List<String> invalidCharacters = new ArrayList<String>();

    String OSName = System.getProperty("os.name"); //$NON-NLS-1$
    if (OSName.toLowerCase().startsWith("win")) { //$NON-NLS-1$
      invalidCharacters.addAll(Arrays.asList(new String[] { "\\", "/", ":", "*", "?", "\"", "<", ">", "|" })); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
    } else if (OSName.toLowerCase().startsWith("mac")) { //$NON-NLS-1$
      invalidCharacters.addAll(Arrays.asList(new String[] { "/", ":" })); //$NON-NLS-1$ //$NON-NLS-2$
    } else { // assume Unix/Linux
      invalidCharacters.addAll(Arrays.asList(new String[] { "/" })); //$NON-NLS-1$
    }

    for (String character : XMLEscapeCharacters) {
      if (!invalidCharacters.contains(character)) {
        invalidCharacters.add(character);
      }
    }

    return invalidCharacters;
  }

  /**
   * Creates and, if it already exists, loads the specified resource. This implementation verifies that a resource can
   * be opened for that URI, that the resource is not the object's current container, and that it is not read-only in
   * the editing domain. If there is an existing resource with that URI, it prompts before overriding or adding to it.
   */
  @Override
  protected boolean processResources() {
    List<URI> uris = getURIs();
    if (uris.isEmpty()) {
      return false;
    }
    URI uri = uris.get(0);
    // Preconditions:

    // 0) URI must not contain an invalid character (from XML or OS point of view).
    String file = uri.lastSegment();
    for (String character : getInvalidCharacters()) {
      if (file.contains(character)) {
        // Warn the end-user about the problem.
        String formattedMessage = StringHelper.formatMessage(Messages.ControlAction_Fragmentation_Error3_Message,
            new String[] { !character.equals("&") ? character : "&&" }); //$NON-NLS-1$ //$NON-NLS-2$
        MessageDialog.openError(getShell(), Messages.ControlAction_Window_Title, formattedMessage);
        return false;
      }
    }

    // URI extension must be CAPELLA_FRAGMENT_EXTENSION_FILE value.
    // Check also that they do not introduce CapellaResourceHelper.CAPELLA_FRAGMENT_FILE_EXTENSION as last segment
    if (!CapellaResourceHelper.isCapellaFragment(uri) || uri.lastSegment()
        .equals(ICommonConstants.POINT_CHARACTER + CapellaResourceHelper.CAPELLA_FRAGMENT_FILE_EXTENSION)) {
      // Warn the end-user about the problem.
      String formattedMessage = StringHelper.formatMessage(Messages.ControlAction_Fragmentation_Error_Message,
          new String[] { ICommonConstants.POINT_CHARACTER + CapellaResourceHelper.CAPELLA_FRAGMENT_FILE_EXTENSION });
      MessageDialog.openError(getShell(), Messages.ControlAction_Window_Title, formattedMessage);
      return false;
    }

    // Check if it is platform resource uri
    if (!uri.isPlatformResource()) {
      String formattedMessage = StringHelper.formatMessage(Messages.ControlAction_Fragmentation_Error4_Message,
          new String[] { uri.toString() });
      MessageDialog.openError(getShell(), Messages.ControlAction_Window_Title, formattedMessage);
      return false;
    }

    // Check if project exists
    // So far first segment must be 'resource'. Then second segment must be project name
    if (!projectExists(uri)) {
      String formattedMessage = StringHelper.formatMessage(Messages.ControlAction_Fragmentation_Error5_Message,
          new String[] { uri.toString() });
      MessageDialog.openError(getShell(), Messages.ControlAction_Window_Title, formattedMessage);
      return false;
    }

    // URI must not override an existing file.
    if (FileHelper.getPlatformFile(uri.toPlatformString(true)).exists()) {
      // Warn the end-user about the problem.
      String formattedMessage = StringHelper.formatMessage(Messages.ControlAction_Fragmentation_Error2_Message,
          new String[] { uri.toPlatformString(true) });
      MessageDialog.openError(getShell(), Messages.ControlAction_Window_Title, formattedMessage);
      return false;
    }

    ResourceSet resourceSet = _domain.getResourceSet();
    Resource theResource = resourceSet.getResource(uri, false);
    boolean resourceInSet = theResource != null;

    if (theResource == _currentResource) {
      MessageDialog.openError(getShell(), EMFEditUIPlugin.INSTANCE.getString("_UI_InvalidURI_label"), //$NON-NLS-1$
          EMFEditUIPlugin.INSTANCE.getString("_WARN_AlreadyInResource")); //$NON-NLS-1$
      return false;
    }
    if (_domain.isReadOnly(theResource)) {
      MessageDialog.openError(getShell(), EMFEditUIPlugin.INSTANCE.getString("_UI_InvalidURI_label"), //$NON-NLS-1$
          EMFEditUIPlugin.INSTANCE.getString("_WARN_ReadOnlyResource")); //$NON-NLS-1$
      return false;
    }

    boolean resourceExists = false;
    try {
      InputStream stream = resourceSet.getURIConverter().createInputStream(uri);
      if (stream != null) {
        resourceExists = true;
        stream.close();
      }
    } catch (IOException exception) {
      // Ignore
    }

    boolean resourceBad = false;
    if (!resourceInSet) {
      theResource = resourceSet.createResource(uri);
      if (null == theResource) {
        MessageDialog.openError(getShell(), EMFEditUIPlugin.INSTANCE.getString("_UI_InvalidURI_label"), //$NON-NLS-1$
            EMFEditUIPlugin.INSTANCE.getString("_WARN_CannotCreateResource")); //$NON-NLS-1$
        return false;
      }

      if (resourceExists) {
        try {
          theResource = resourceSet.getResource(uri, true);
        } catch (RuntimeException exception) {
          EMFEditUIPlugin.INSTANCE.log(exception);
          resourceBad = theResource.getContents().isEmpty();
        }
      }
    }

    boolean result = true;
    if (resourceBad) {
      result = MessageDialog.openQuestion(getShell(), EMFEditUIPlugin.INSTANCE.getString("_UI_ExistingResource_label"), //$NON-NLS-1$
          EMFEditUIPlugin.INSTANCE.getString("_WARN_ReplaceResource")); //$NON-NLS-1$
    } else if (resourceExists) {
      result = MessageDialog.openQuestion(getShell(), EMFEditUIPlugin.INSTANCE.getString("_UI_ExistingResource_label"), //$NON-NLS-1$
          EMFEditUIPlugin.INSTANCE.getString("_WARN_AddToResource")); //$NON-NLS-1$
    }

    if (!result && !resourceInSet && (theResource != null)) {
      theResource.unload();
      resourceSet.getResources().remove(theResource);
    } else {
      _resource = theResource;
    }
    return result;
  }

  /**
   * @param platformResourceUri
   * @return true if project exists
   */
  private boolean projectExists(URI platformResourceUri) {
    if (!platformResourceUri.isPlatformResource() || (platformResourceUri.segmentCount() <= 1)) {
      return false;
    }
    String projectName = platformResourceUri.segment(1);
    // Decode to replace %20 white-spaces etc.
    projectName = URI.decode(projectName);
    IProject project = ProjectHelper.getProject(projectName);
    if ((project == null) || !project.exists()) {
      return false;
    }
    return true;
  }

  /**
   * @return The resource
   */
  public Resource getResource() {
    return _resource;
  }
}
