/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.commands.preferences.ui;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.commands.preferences.internalization.l10n.PreferencesUIMessages;
import org.polarsys.capella.core.commands.preferences.preferences.ConfigurabilityPreferences;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.PreferencesItemsRegistry;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
import org.polarsys.capella.core.commands.preferences.util.IUserProfileDescriptor;
import org.polarsys.capella.core.commands.preferences.util.XmlPreferencesConfig;
import org.polarsys.capella.core.preferences.Activator;

/**
 * The preference page for Modeler validation controls.
 */
public class UserProfilePreferencePage extends AbstractDefaultPreferencePage {

  /*
   * 
   */
  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.commands.preferences.ui.validationPreferencePage"; //$NON-NLS-1$

  /*
   * 
   */
  private Composite expertUserComposite;

  /*
   * 
   */
  private Composite userCompsite;

  /*
   * 
   */
  private Button expertUserField;

  /*
   * 
   */
  private Button userField;
  /*
   * 
   */
  private IUserProfileDescriptor userProfileDescriptor;

  /**
   * Initializes me.
   */
  public UserProfilePreferencePage() {
    super(PROPERTY_PAGE_ID);
  }

  /*
   * (non-Javadoc) Method declared on PreferencePage.
   */
  @Override
  protected Control createContents(Composite parent) {
    Composite fieldEditorParent = new Composite(parent, SWT.NULL);
    GridLayout layout = new GridLayout();
    layout.numColumns = 1;
    layout.marginHeight = 0;
    layout.marginWidth = 0;
    fieldEditorParent.setLayout(layout);
    fieldEditorParent.setFont(parent.getFont());

    createFieldEditors(fieldEditorParent);

    return fieldEditorParent;
  }

  /*
   * (non-Javadoc) Implements the inherited method.
   */
  public void applyToPreferences() {
    // ensure that I get my saved preference state
    ConfigurabilityPreferences.setInstanceScopePreferenceItem(userProfileDescriptor.getId(), expertUserField.getSelection());

  }

  @Override
  protected void performApply() {
    applyToPreferences();
    enablePreferencesPage(ConfigurabilityPreferences.isInstanceScopePreferenceItemEnabled(XmlPreferencesConfig.USER_PROFILE_MODE_ID));

  }

  @Override
  protected void createFieldEditors() {

  }

  /**
   * The field editors for this preference page are inserted in this method.
   * @param parent
   */

  protected void createFieldEditors(final Composite panel) {
    GridLayout panelLayout = new GridLayout();
    panelLayout.numColumns = 1;
    panel.setLayout(panelLayout);

    GridData blockData = new GridData();
    blockData.grabExcessHorizontalSpace = true;
    blockData.horizontalAlignment = GridData.FILL;
    blockData.horizontalSpan = 1;
    panel.setLayoutData(blockData);

    // create groups
    createUserProfileGroup(panel);

    applyDialogFont(panel);
  }

  /**
   * Extends the inherited method to set up the enablement of the checkboxes according to the current preference settings.
   */
  @Override
  protected void initialize() {
    super.initialize();
  }

  /**
   * Create the "Live validation problems" group of the preference page.
   * @param parent the parent composite
   */
  private void createUserProfileGroup(Composite parent) {

    Group group = new Group(parent, SWT.NONE);
    group.setText(PreferencesUIMessages.Validation_liveValidationGroupLabel);
    GridLayout layout = new GridLayout();
    layout.numColumns = 1;
    group.setLayout(layout);

    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    data.grabExcessHorizontalSpace = true;
    group.setLayoutData(data);

    GridLayout subLayout = new GridLayout();
    subLayout.numColumns = 2;

    expertUserComposite = new Composite(group, SWT.NONE);

    expertUserComposite.setLayout(subLayout);

    expertUserField = new Button(expertUserComposite, SWT.RADIO | SWT.LEFT);
    expertUserField.setSelection(false);

    new Label(expertUserComposite, SWT.BEGINNING).setText("Expert");

    expertUserField.addSelectionListener(new SelectionListener() {

      @Override
      public void widgetSelected(SelectionEvent e_p) {
        setCheckboxesEnablement(UserProfileModeEnum.Expert);
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e_p) {

      }
    });

    userCompsite = new Composite(group, SWT.NONE);
    userCompsite.setLayout(subLayout);

    userField = new Button(userCompsite, SWT.RADIO | SWT.LEFT);
    userField.setSelection(true);

    new Label(userCompsite, SWT.BEGINNING).setText("Standard");

    userField.addSelectionListener(new SelectionListener() {

      @Override
      public void widgetSelected(SelectionEvent e_p) {
        setCheckboxesEnablement(UserProfileModeEnum.User);
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e_p) {

      }
    });

    initializeUserModeProfile();

  }

  /**
	 * 
	 */
  private void initializeUserModeProfile() {
    userProfileDescriptor = (IUserProfileDescriptor) PreferencesItemsRegistry.getProfileDescriptor();
    boolean isExpertMode = ConfigurabilityPreferences.isInstanceScopePreferenceItemEnabled(userProfileDescriptor.getId());

    expertUserComposite.setEnabled(!isExpertMode);
    userCompsite.setEnabled(isExpertMode);

    expertUserField.setSelection(isExpertMode);
    userField.setSelection(!isExpertMode);

  }

  /**
   * Sets the enablement of the "Show warnings in dialog ..." and "Show Output view ..." checkboxes according to whether the Output view is the destination of
   * live validation problems.
   */
  void setCheckboxesEnablement(UserProfileModeEnum newUserProfileMode) {
    if (newUserProfileMode != null) {
      expertUserComposite.setEnabled(!UserProfileModeEnum.Expert.equals(newUserProfileMode));

      userCompsite.setEnabled(!UserProfileModeEnum.User.equals(newUserProfileMode));

      expertUserField.setSelection(UserProfileModeEnum.Expert.equals(newUserProfileMode));
      userField.setSelection(UserProfileModeEnum.User.equals(newUserProfileMode));

    } else {
      expertUserComposite.setEnabled(true);
      userCompsite.setEnabled(true);
      userField.setSelection(true);
      expertUserField.setSelection(false);
    }
  }

  /**
   * This method must be implemented to obtain the correct location of the preference store, as it is called by getPreferenceStore().
   * @return IPreferenceStore the returned preference store
   */
  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return Activator.getDefault().getPreferenceStore();
  }

  /**
   * When the user clicks OK, save the values in the field editors by calling storeValues() and also in the preference store. This is the same as what is done
   * in apply.
   * @return true since the ok function completed successfully. If the saving does not complete successfully, for this preference page, it was because of
   *         something the user cannot fix, so return true anyway otherwise the page will not close and becomes annoying.
   *         <P>
   *         Looking at the samples, I don't think they expect this to be false.
   */
  @Override
  public boolean performOk() {
    applyToPreferences();
    ConfigurabilityPreferences.save();
    return super.performOk();
  }
  
  @Override
  protected void performDefaults() {
    this.expertUserField.setSelection(true);
    this.expertUserComposite.setEnabled(true);
    this.userField.setSelection(false);
    this.userCompsite.setEnabled(true);
    super.performDefaults();
  }

  /**
   * Redefines/Implements/Extends the inherited method.
   */
  @Override
  public void init(IWorkbench workbench) {
    // Nothing to do in this implementation
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getPageTitle() {
    return PreferencesUIMessages.Validation_liveValidationGroupLabel;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getPageDescription() {
    return ICommonConstants.EMPTY_STRING;
  }

  @Override
  protected String getPageId() {
    return PROPERTY_PAGE_ID;
  }
}
