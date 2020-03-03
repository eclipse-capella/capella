/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ComboContentAdapter;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.jface.text.FindReplaceDocumentAdapterContentProposalProvider;
import org.eclipse.search.ui.IReplacePage;
import org.eclipse.search.ui.ISearchPage;
import org.eclipse.search.ui.ISearchPageContainer;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.fieldassist.ContentAssistCommandAdapter;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.polarsys.capella.core.ui.search.searchfor.CapellaLeftSearchForContainerArea;
import org.polarsys.capella.core.ui.search.searchfor.CapellaRightSearchForContainerArea;
import org.polarsys.capella.core.ui.search.CapellaReplaceRunnableWrapper;
import org.polarsys.capella.core.ui.search.CapellaReplaceRunnable;
import org.polarsys.capella.core.ui.search.CapellaSearchQuery;
import org.polarsys.capella.core.ui.search.CapellaSearchResult;

public class CapellaSearchPage extends DialogPage implements ISearchPage, IReplacePage {

  private List<CapellaSearchSettings> previousSearchSettings = new ArrayList<>();

  private Combo comboSearchPattern;
  private Label labelForComboSearchPattern;

  private Label labelRegex;
  private CLabel labelValidationStatus;
  private ContentAssistCommandAdapter comboSearchPatternRegexContentAssist;

  private Button checkboxCaseSensitive;
  private Button checkboxRegex;
  private Button checkboxWholeWord;

  private ISearchPageContainer searchPageContainer;

  private CapellaSearchSettings capellaSearchSettings;// = new CapellaSearchSettings();
  private CapellaLeftSearchForContainerArea leftCont;
  private CapellaRightSearchForContainerArea rightCont;

  @Override
  public void createControl(Composite parent) {
    initializeDialogUnits(parent);
    // init history searches
    previousSearchSettings.addAll(CapellaSearchSettingsHistory.getInstance().getAllSearchSettings());
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setFont(parent.getFont());
    GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);
    createSearchPatternControls(composite);
    setControl(composite);
    createSearchForGroup(composite);
    Dialog.applyDialogFont(composite);
  }

  /*
   * Create the text search box and the check-boxes for the search preferences
   */
  private void createSearchPatternControls(Composite group) {
    createLabelForComboSearchPattern(group);
    Composite column1 = new Composite(group, SWT.NONE);
    column1.setFont(group.getFont());
    GridLayoutFactory.fillDefaults().applyTo(column1);
    GridDataFactory.fillDefaults().grab(true, false).applyTo(column1);
    createComboSearchPattern(column1);
    createLabelRegex(column1);
    createLabelValidationStatus(column1);

    Composite column2 = new Composite(group, SWT.NONE);
    column2.setFont(group.getFont());
    GridLayoutFactory.fillDefaults().applyTo(column2);
    GridDataFactory.fillDefaults().grab(false, false).applyTo(column2);
    createCheckboxCaseSensitive(column2);
    createCheckboxRegex(column2);
    createCheckboxWholeWord(column2);
  }

  private void createLabelForComboSearchPattern(Composite group) {
    labelForComboSearchPattern = new Label(group, SWT.LEAD);
    labelForComboSearchPattern.setText(CapellaSearchConstants.CapellaSearchContainingText);
    GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).span(2, 1).grab(true, true)
        .applyTo(labelForComboSearchPattern);
    labelForComboSearchPattern.setFont(group.getFont());
  }

  private void createComboSearchPattern(Composite group) {
    comboSearchPattern = new Combo(group, SWT.SINGLE | SWT.BORDER);
    comboSearchPattern.setFont(group.getFont());
    GridDataFactory.fillDefaults().grab(true, false).hint(convertWidthInCharsToPixels(50), SWT.DEFAULT)
        .applyTo(comboSearchPattern);

    comboSearchPattern.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        int selectionIndex = comboSearchPattern.getSelectionIndex();
        if (-1 < selectionIndex && selectionIndex < previousSearchSettings.size()) {
          CapellaSearchSettings previous = previousSearchSettings.get(selectionIndex);
          if (previous != null) {
            applySearchSettings(previous);
          }
        }
      }
    });
    comboSearchPattern.addModifyListener(e -> validate());
    comboSearchPattern.setToolTipText(CapellaSearchConstants.CapellaSearchPage_Combo_Pattern_Label_Regex_Enabled);

    ComboContentAdapter contentAdapter = new ComboContentAdapter();
    FindReplaceDocumentAdapterContentProposalProvider findProposer = new FindReplaceDocumentAdapterContentProposalProvider(
        true);
    comboSearchPatternRegexContentAssist = new ContentAssistCommandAdapter(comboSearchPattern, contentAdapter,
        findProposer, ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS, new char[0], true);
    comboSearchPatternRegexContentAssist.setEnabled(false);
  }

  private void createLabelRegex(Composite group) {
    labelRegex = new Label(group, SWT.LEAD);
    labelRegex.setText(CapellaSearchConstants.CapellaSearchRegexExplanation);
    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).applyTo(labelRegex);
    labelRegex.setFont(group.getFont());
    labelRegex.setAlignment(SWT.LEFT);
  }

  /*
   * Create a label to display a message if the search pattern is correctly formatted
   */
  private void createLabelValidationStatus(Composite group) {
    labelValidationStatus = new CLabel(group, SWT.LEAD);
    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).grab(true, false).applyTo(labelValidationStatus);
    labelValidationStatus.setFont(group.getFont());
    labelValidationStatus.setAlignment(SWT.LEFT);
    labelValidationStatus.setForeground(JFaceColors.getErrorText(labelValidationStatus.getDisplay()));
  }

  /*
   * create a checkbox which will enable case sensitive search
   */
  private void createCheckboxCaseSensitive(Composite group) {
    checkboxCaseSensitive = new Button(group, SWT.CHECK);
    checkboxCaseSensitive.setText(CapellaSearchConstants.CapellaSearchPage_Checkbox_CaseSensitive_Label);
    GridDataFactory.fillDefaults().applyTo(checkboxCaseSensitive);
    checkboxCaseSensitive.setFont(group.getFont());
    checkboxCaseSensitive.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        validate();
      }
    });
  }

  private void createCheckboxRegex(Composite group) {
    checkboxRegex = new Button(group, SWT.CHECK);
    checkboxRegex.setText(CapellaSearchConstants.CapellaSearchPage_Checkbox_Regex_Label);
    GridDataFactory.fillDefaults().applyTo(checkboxRegex);
    checkboxRegex.setFont(group.getFont());
    checkboxRegex.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        boolean regexEnabled = checkboxRegex.getSelection();
        comboSearchPatternRegexContentAssist.setEnabled(regexEnabled);
        if (regexEnabled) {
          labelRegex.setText(CapellaSearchConstants.CapellaSearchEmptyString);
          checkboxWholeWord.setEnabled(false);
        } else {
          labelRegex.setText(CapellaSearchConstants.CapellaSearchRegexExplanation);
          checkboxWholeWord.setEnabled(true);
        }
        validate();
      }
    });
  }

  private void createCheckboxWholeWord(Composite group) {
    checkboxWholeWord = new Button(group, SWT.CHECK);
    checkboxWholeWord.setText(CapellaSearchConstants.CapellaSearchPage_Checkbox_WholeWord_Label);
    GridDataFactory.fillDefaults().applyTo(checkboxWholeWord);
    checkboxWholeWord.setFont(group.getFont());
    checkboxWholeWord.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        validate();
      }
    });
  }

  private void createSearchForGroup(Composite parent) {
    Group qGrp = new Group(parent, SWT.NONE);
    qGrp.setLayout(new GridLayout(4, false));

    GridData gdGrp = new GridData(GridData.FILL_BOTH);
    gdGrp.heightHint = 250;

    qGrp.setLayoutData(gdGrp);
    qGrp.setText(CapellaSearchConstants.SearchFor_Label);

    leftCont = new CapellaLeftSearchForContainerArea(qGrp, this);
    rightCont = new CapellaRightSearchForContainerArea(qGrp, leftCont, this);
    leftCont.setOtherSideArea(rightCont);
    leftCont.createFiltercontainer(qGrp);
  }

  protected void applySearchSettings(CapellaSearchSettings settings) {
    capellaSearchSettings = settings;
    checkboxCaseSensitive.setSelection(settings.isCaseSensitive());
    comboSearchPattern.setText(settings.getTextPattern());
    checkboxWholeWord.setSelection(settings.isWholeWord());

    boolean regexEnabled = settings.isRegExSearch();
    checkboxRegex.setSelection(regexEnabled);
    comboSearchPatternRegexContentAssist.setEnabled(regexEnabled);
    if (regexEnabled) {
      labelForComboSearchPattern.setText(CapellaSearchConstants.CapellaSearchPage_Combo_Pattern_Label_Regex_Enabled);
    } else {
      labelForComboSearchPattern.setText(CapellaSearchConstants.CapellaSearchPage_Combo_Pattern_Label_Regex_Disabled);
    }

    if (leftCont != null) {
      leftCont.applySearchSettings(settings.getSearchMetaClasses());
    }

    if (rightCont != null) {
      rightCont.applySearchSettings(settings.getSearchAttributes());
    }

    validate();
  }

  public CapellaSearchSettings getCapellaSearchSettings() {
    if (capellaSearchSettings == null) {
      capellaSearchSettings = new CapellaSearchSettings();
    }
    return capellaSearchSettings;
  }

  @Override
  public void setVisible(boolean visible) {
    if (visible && comboSearchPattern != null) {
      if (!previousSearchSettings.isEmpty()) {
        String[] previousSearchPatterns = previousSearchSettings.stream() //
            .map(CapellaSearchSettings::getTextPattern) //
            .toArray(String[]::new);
        comboSearchPattern.setItems(previousSearchPatterns);
        comboSearchPattern.select(0);
        applySearchSettings(previousSearchSettings.get(0));
      }
      comboSearchPattern.setFocus();
    }
    super.setVisible(visible);
  }

  /*
   * Validate the current search settings
   */
  private void validate() {
    // Update the search settings from UI
    capellaSearchSettings = getCapellaSearchSettings();
    capellaSearchSettings.setTextPattern(comboSearchPattern.getText());
    capellaSearchSettings.setCaseSensitive(checkboxCaseSensitive.getSelection());
    capellaSearchSettings.setRegExSearch(checkboxRegex.getSelection());
    capellaSearchSettings.setWholeWord(checkboxWholeWord.getSelection());

    capellaSearchSettings.clearProjects();
    for (Object checkedElement : getProjectsFromWorkspace()) {
      if (checkedElement instanceof IProject) {
        capellaSearchSettings.addProject(((IProject) checkedElement).getName());
      }
    }

    // Validate and update status to be displayed
    updateValidationStatus(capellaSearchSettings.validate());
  }

  public void updateValidationStatus(IStatus validateStatus) {
    if (validateStatus.isOK()) {
      searchPageContainer.setPerformActionEnabled(true);
      labelValidationStatus.setText(CapellaSearchConstants.CapellaSearchPage_Validation_Message_OK);
    } else {
      labelValidationStatus.setText(validateStatus.getMessage());
      searchPageContainer.setPerformActionEnabled(true);
    }
  }

  @Override
  public boolean performReplace() {
    // The action is enabled if only the form is validated.
    CapellaSearchQuery searchQuery = new CapellaSearchQuery(getCapellaSearchSettings());
    IStatus searchStatus = NewSearchUI.runQueryInForeground(searchPageContainer.getRunnableContext(), searchQuery);

    if (searchStatus.isOK()) {
      CapellaSearchResult searchResult = searchQuery.getSearchResult();
      if (searchResult.getMatchCount() > 0) {
        Set<CapellaSearchMatchEntry> allMatches = searchResult.getDisplayedMatches();
        CapellaReplaceRunnable capellaReplaceRunnable = new CapellaReplaceRunnable(searchQuery, allMatches, true);
        new CapellaReplaceRunnableWrapper(capellaReplaceRunnable).run();
        if (capellaSearchSettings.getReplaceTextPattern() != null) {
          CapellaSearchSettingsHistory.getInstance().appendSearchSettings(capellaSearchSettings);
          CapellaReplaceHistory.getInstance().appendSearchSettings(capellaSearchSettings);
        }
      } else {
        MessageDialog.openInformation(getShell(), CapellaSearchConstants.ReplaceDialog_Title,
            String.format(CapellaSearchConstants.ReplaceDialog_No_Match_Found_Message,
                searchQuery.getCapellaSearchSettings().getTextPattern()));
      }
    }

    // If the action is enabled, that means the form is validated. We can save it in search history to propose it for
    // next usages in future.
    CapellaSearchSettingsHistory.getInstance().appendSearchSettings(capellaSearchSettings);
    return true;
  }

  @Override
  public boolean performAction() {
    // The action is enabled if only the form is validated.
    CapellaSearchQuery searchQuery = new CapellaSearchQuery(getCapellaSearchSettings());
    NewSearchUI.runQueryInBackground(searchQuery);
    CapellaSearchSettingsHistory.getInstance().appendSearchSettings(capellaSearchSettings);
    return true;
  }

  private IProject[] getProjectsFromWorkspace() {
    IProject[] projectsToCheck = ResourcesPlugin.getWorkspace().getRoot().getProjects();
    return projectsToCheck;
  }

  @Override
  public void setContainer(ISearchPageContainer container) {
    this.searchPageContainer = container;
  }

}
