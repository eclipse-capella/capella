/*******************************************************************************
 * Copyright (c) 2002, 2020 Corporation and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    IBM - Initial API and implementation 
 *    Jakub Jurkiewicz - Fix for Bug 174737
 *    Oakland Software Incorporated - Bug 224997 [Workbench]
 *    Thales - Contributor
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.project.internal;

import java.net.URI;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.ide.IIDEHelpContextIds;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.ProjectContentsLocationArea.IErrorMessageReporter;

/**
 * This class is copied from org.eclipse.ui.dialogs because we need to override private methods. See TODO Standard main page for a wizard that is creates a
 * project resource.
 * <p>
 * This page may be used by clients as-is; it may be also be subclassed to suit.
 * </p>
 * <p>
 * Example usage:
 * 
 * <pre>
 * mainPage = new WizardNewProjectCreationPage(&quot;basicNewProjectPage&quot;);
 * mainPage.setTitle(&quot;Project&quot;);
 * mainPage.setDescription(&quot;Create a new project resource.&quot;);
 * </pre>
 * 
 * </p>
 */
public class WizardNewProjectCreationPage extends WizardPage {

  // initial value stores
  private String initialProjectFieldValue;

  // widgets
  Text projectNameField;

  private Listener nameModifyListener = new Listener() {
    public void handleEvent(Event e) {
      setLocationForSelection();
      boolean valid = validatePage();
      setPageComplete(valid);

    }
  };

  private ProjectContentsLocationArea locationArea;

  // constants
  private static final int SIZING_TEXT_FIELD_WIDTH = 250;

  /**
   * Project approach selection.
   */
  private CapellaProjectHelper.ProjectApproach projectApproachSelection;

  /**
   * Get the project approach selection.
   * @return the projectApproachSelection
   */
  public CapellaProjectHelper.ProjectApproach getProjectApproachSelection() {
    return projectApproachSelection;
  }

  /**
   * Creates a new project creation wizard page.
   * @param pageName the name of this page
   */
  public WizardNewProjectCreationPage(String pageName) {
    super(pageName);
    setPageComplete(false);
  }

  /**
   * Handle default project location.
   * @param defaultProjectLocation
   */
  protected ProjectContentsLocationArea handleDefaultProjectLocation(Composite composite) {
    return new ProjectContentsLocationArea(getErrorReporter(), composite);
  }

  /**
   * (non-Javadoc) Method declared on IDialogPage.
   */
  public void createControl(Composite parent) {
    GridLayout layout = new GridLayout();
    Composite dialogAreaComposite = new Composite(parent, SWT.NULL);
    dialogAreaComposite.setFont(parent.getFont());

    initializeDialogUnits(dialogAreaComposite);

    PlatformUI.getWorkbench().getHelpSystem().setHelp(dialogAreaComposite, IIDEHelpContextIds.NEW_PROJECT_WIZARD_PAGE);

    dialogAreaComposite.setLayout(layout);
    dialogAreaComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

    ScrolledComposite scrolled = new ScrolledComposite(dialogAreaComposite, SWT.H_SCROLL | SWT.V_SCROLL | SWT.RESIZE);

    Composite composite = new Composite(scrolled, SWT.NONE | SWT.RESIZE);
    // GridLayout layout = new GridLayout();
    composite.setLayout(layout);
    composite.setLayoutData(new GridData(GridData.FILL_BOTH));
    composite.setFont(dialogAreaComposite.getFont());

    createProjectNameGroup(composite);

    locationArea = handleDefaultProjectLocation(composite);

    if (initialProjectFieldValue != null) {
      locationArea.updateProjectName(initialProjectFieldValue);
    }
    // Scale the button based on the rest of the dialog
    setButtonLayoutData(locationArea.getBrowseButton());

    // Create a radio group to select the project approach.
    createProjectApproachGroup(composite);

    setPageComplete(validatePage());
    // Show description on opening
    setErrorMessage(null);
    setMessage(null);

    scrolled.setContent(composite);

    scrolled.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    scrolled.setLayout(new GridLayout());
    scrolled.setExpandVertical(true);
    scrolled.setExpandHorizontal(true);
    scrolled.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
    scrolled.setVisible(true);

    setControl(dialogAreaComposite);

  }

  /**
   * Comment on, not used at the moment. Create project workflow radio group.
   * @param composite
   */
  protected void createProjectApproachGroup(Composite parent) {
    Group radioBox = new Group(parent, SWT.NONE);
    radioBox.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    radioBox.setLayout(new GridLayout());
    radioBox.setText(Messages.WizardNewProjectCreationPage_CapellaProjectApproach_Title);
    Button radioRC = createRadioButton(radioBox, IDialogConstants.YES_LABEL, CapellaProjectHelper.ProjectApproach.ReusableComponents);
    Button radioSC =
        createRadioButton(radioBox, Messages.WizardNewProjectCreationPage_NO_LABEL /* use our own label to avoid conflicting accelerator */,
            CapellaProjectHelper.ProjectApproach.SingletonComponents);
    // Get default project approach.
    projectApproachSelection = getRelatedPreferenceProjectApproach();
    // Select the radio button according to the default preference.
    Button selectedButton = null;
    if (CapellaProjectHelper.ProjectApproach.SingletonComponents.equals(projectApproachSelection)) {
      selectedButton = radioSC;
    } else {
      selectedButton = radioRC;
    }
    selectedButton.setSelection(true);

    boolean isReuseOfComponentsAllowed = CapellaModelPreferencesPlugin.getDefault().isReuseOfComponentsAllowed();
    radioRC.setEnabled(isReuseOfComponentsAllowed);
    radioSC.setEnabled(isReuseOfComponentsAllowed);
    radioBox.setEnabled(isReuseOfComponentsAllowed);
  }

  /**
   * @return
   */
  protected ProjectApproach getRelatedPreferenceProjectApproach() {
    return ProjectApproach.SingletonComponents;
  }

  /**
   * Create a radio button for specified parameters.
   * @param parent
   * @param label
   * @param approach
   * @return
   */
  private Button createRadioButton(Group parent, String label, CapellaProjectHelper.ProjectApproach approach) {
    Button radioButton = new Button(parent, SWT.RADIO | SWT.LEFT);
    radioButton.setText(label);
    radioButton.setData(approach);
    radioButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        handleProjectApproachSelection(event);
      }
    });
    return radioButton;
  }

  /**
   * Handle project approach selection.
   * @param event
   */
  protected void handleProjectApproachSelection(SelectionEvent event) {
    projectApproachSelection = (ProjectApproach) event.widget.getData();
  }

  /**
   * TODO change for Get an error reporter for the receiver.
   * @return IErrorMessageReporter
   */
  protected IErrorMessageReporter getErrorReporter() {
    return new IErrorMessageReporter() {
      /*
       * (non-Javadoc)
       * @see org.eclipse.ui.internal.ide.dialogs.ProjectContentsLocationArea.IErrorMessageReporter#reportError(java.lang.String)
       */
      public void reportError(String errorMessage) {
        setErrorMessage(errorMessage);
        boolean valid = errorMessage == null;
        if (valid) {
          valid = validatePage();
        }

        setPageComplete(valid);
      }
    };
  }

  /**
   * Creates the project name specification controls.
   * @param parent the parent composite
   */
  private final void createProjectNameGroup(Composite parent) {
    // project specification group
    Composite projectGroup = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    projectGroup.setLayout(layout);
    projectGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // new project label
    Label projectLabel = new Label(projectGroup, SWT.NONE);
    projectLabel.setText(""); //$NON-NLS-1$
    projectLabel.setFont(parent.getFont());

    // new project name entry field
    projectNameField = new Text(projectGroup, SWT.BORDER);
    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    data.widthHint = SIZING_TEXT_FIELD_WIDTH;
    projectNameField.setLayoutData(data);
    projectNameField.setFont(parent.getFont());

    // Set the initial value first before listener
    // to avoid handling an event during the creation.
    if (initialProjectFieldValue != null) {
      projectNameField.setText(initialProjectFieldValue);
    }
    projectNameField.addListener(SWT.Modify, nameModifyListener);
  }

  /**
   * Returns the current project location path as entered by the user, or its anticipated initial value. Note that if the default has been returned the path in
   * a project description used to create a project should not be set.
   * @return the project location path or its anticipated initial value.
   */
  public IPath getLocationPath() {
    return new Path(locationArea.getProjectLocation());
  }

  /**
   * /** Returns the current project location URI as entered by the user, or <code>null</code> if a valid project location has not been entered.
   * @return the project location URI, or <code>null</code>
   * @since 3.2
   */
  public URI getLocationURI() {
    return locationArea.getProjectLocationURI();
  }

  /**
   * Creates a project resource handle for the current project name field value. The project handle is created relative to the workspace root.
   * <p>
   * This method does not create the project resource; this is the responsibility of <code>IProject::create</code> invoked by the new project resource wizard.
   * </p>
   * @return the new project resource handle
   */
  public IProject getProjectHandle() {
    return ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
  }

  /**
   * Returns the current project name as entered by the user, or its anticipated initial value.
   * @return the project name, its anticipated initial value, or <code>null</code> if no project name is known
   */
  public String getProjectName() {
    if (projectNameField == null) {
      return initialProjectFieldValue;
    }

    return getProjectNameFieldValue();
  }

  /**
   * Returns the value of the project name field with leading and trailing spaces removed.
   * @return the project name in the field
   */
  private String getProjectNameFieldValue() {
    if (projectNameField == null) {
      return ""; //$NON-NLS-1$
    }

    return projectNameField.getText().trim();
  }

  /**
   * Sets the initial project name that this page will use when created. The name is ignored if the createControl(Composite) method has already been called.
   * Leading and trailing spaces in the name are ignored. Providing the name of an existing project will not necessarily cause the wizard to warn the user.
   * Callers of this method should first check if the project name passed already exists in the workspace.
   * @param name initial project name for this page
   * @see IWorkspace#validateName(String, int)
   */
  public void setInitialProjectName(String name) {
    if (name == null) {
      initialProjectFieldValue = null;
    } else {
      initialProjectFieldValue = name.trim();
      if (locationArea != null) {
        locationArea.updateProjectName(name.trim());
      }
    }
  }

  /**
   * Set the location to the default location if we are set to useDefaults.
   */
  void setLocationForSelection() {
    locationArea.updateProjectName(getProjectNameFieldValue());
  }

  /**
   * Returns whether this page's controls currently all contain valid values.
   * @return <code>true</code> if all controls are valid, and <code>false</code> if at least one is invalid
   */
  protected boolean validatePage() {
    IWorkspace workspace = IDEWorkbenchPlugin.getPluginWorkspace();

    String projectFieldContents = getProjectNameFieldValue();
    if (ICommonConstants.EMPTY_STRING.equals(projectFieldContents)) {
      setErrorMessage(null);
      setMessage(ICommonConstants.EMPTY_STRING);
      return false;
    }

    IStatus nameStatus = workspace.validateName(projectFieldContents, IResource.PROJECT);
    if (!nameStatus.isOK()) {
      setErrorMessage(nameStatus.getMessage());
      return false;
    }

    IProject handle = getProjectHandle();
    if (handle.exists()) {
      setErrorMessage(IDEWorkbenchMessages.WizardNewProjectCreationPage_projectExistsMessage);
      return false;
    }

    String validLocationMessage = locationArea.checkValidLocation();
    if (validLocationMessage != null) { // there is no destination location given
      setErrorMessage(validLocationMessage);
      return false;
    }

    setErrorMessage(null);
    setMessage(null);
    return true;
  }

  /*
   * see @DialogPage.setVisible(boolean)
   */
  @Override
  public void setVisible(boolean visible) {
    super.setVisible(visible);
    if (visible) {
      projectNameField.setFocus();
    }
  }

  /**
   * Returns the useDefaults.
   * @return boolean
   */
  public boolean useDefaults() {
    return locationArea.isDefault();
  }

}
