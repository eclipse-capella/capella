/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ComboContentAdapter;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.jface.text.FindReplaceDocumentAdapterContentProposalProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.search.internal.ui.SearchDialog;
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
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.fieldassist.ContentAssistCommandAdapter;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.ui.search.match.SearchMatch;
import org.polarsys.capella.core.ui.search.result.CapellaSearchResult;
import org.polarsys.capella.core.ui.search.searchfor.CapellaLeftSearchForContainerArea;
import org.polarsys.capella.core.ui.search.searchfor.CapellaRightSearchForContainerArea;

public class CapellaSearchPage extends DialogPage implements ISearchPage, IReplacePage {
  /**
   * Workspace scope (value <code>0</code>).
   */
  public static final int WORKSPACE_SCOPE = 0;
  /**
   * Selected element scope (value <code>1</code>).
   */
  public static final int SELECTED_ELEMENT_SCOPE = 1;
  /**
   * Project scope (value <code>2</code>).
   */
  public static final int PROJECT_SCOPE = 2;
  private int selectedScope;

  private List<CapellaSearchSettings> previousSearchSettings = new ArrayList<>();

  private Combo comboSearchPattern;
  private Label labelForComboSearchPattern;

  private CLabel labelValidationStatus;
  private ContentAssistCommandAdapter comboSearchPatternRegexContentAssist;

  private Button checkboxCaseSensitive;
  private Button checkboxRegex;
  private Button checkboxWholeWord;

  private Button workspaceBtn;
  private Button selectedElementBtn;
  private Button projectBtn;

  private ISearchPageContainer searchPageContainer;

  // keep the current search settings (searched text, searched meta-classes, attributes etc)
  private CapellaSearchSettings capellaSearchSettings;

  // leftCont, rightCont are the containers displaying the metaclasses and attributes
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

    // create the text field search area and checkboxes (case sensitive etc)
    createSearchPatternControls(composite);

    // create search for area
    createSearchForGroup(composite);
    // Create Scope area
    createScopeGroup(composite);
    Dialog.applyDialogFont(composite);
    setControl(composite);

  }

  // create the text search box and the check-boxes for the search preferences
  private void createSearchPatternControls(Composite group) {
    createLabelForComboSearchPattern(group);
    Composite column1 = new Composite(group, SWT.NONE);
    column1.setFont(group.getFont());
    GridLayoutFactory.fillDefaults().applyTo(column1);
    GridDataFactory.fillDefaults().grab(true, false).applyTo(column1);
    createComboSearchPattern(column1);
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
    labelForComboSearchPattern.setText(CapellaSearchConstants.CapellaSearchPage_Combo_Pattern_Label_Regex_Disabled);
    GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).span(2, 1).grab(true, true)
        .applyTo(labelForComboSearchPattern);
    labelForComboSearchPattern.setFont(group.getFont());
  }

  private void createComboSearchPattern(Composite group) {
    // the combo search will be populated with the previous search characteristics (attributes)
    comboSearchPattern = new Combo(group, SWT.SINGLE | SWT.BORDER);
    comboSearchPattern.setFont(group.getFont());
    GridDataFactory.fillDefaults().grab(true, false).hint(convertWidthInCharsToPixels(50), SWT.DEFAULT)
        .applyTo(comboSearchPattern);

    // whenever we change the selection, we apply the search settings stored in history
    comboSearchPattern.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        int selectionIndex = comboSearchPattern.getSelectionIndex();
        if (-1 < selectionIndex && selectionIndex < previousSearchSettings.size()) {
          CapellaSearchSettings previous = previousSearchSettings.get(selectionIndex);
          if (previous != null) {
            // apply the settings from history
            applySearchSettings(previous);
          }
        }
      }
    });
    comboSearchPattern.setToolTipText(CapellaSearchConstants.CapellaSearchPage_Combo_Pattern_Label_Regex_Enabled);

    ComboContentAdapter contentAdapter = new ComboContentAdapter();
    FindReplaceDocumentAdapterContentProposalProvider findProposer = new FindReplaceDocumentAdapterContentProposalProvider(
        true);
    comboSearchPatternRegexContentAssist = new ContentAssistCommandAdapter(comboSearchPattern, contentAdapter,
        findProposer, ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS, new char[0], true);
    comboSearchPatternRegexContentAssist.setEnabled(false);
  }

  // Create a label to display a message if the search pattern is correctly formatted
  private void createLabelValidationStatus(Composite group) {
    labelValidationStatus = new CLabel(group, SWT.LEAD);
    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).grab(true, false).applyTo(labelValidationStatus);
    labelValidationStatus.setFont(group.getFont());
    labelValidationStatus.setAlignment(SWT.LEFT);
    labelValidationStatus.setForeground(JFaceColors.getErrorText(labelValidationStatus.getDisplay()));
  }

  // create a checkbox which will enable case sensitive search
  private void createCheckboxCaseSensitive(Composite group) {
    checkboxCaseSensitive = new Button(group, SWT.CHECK);
    checkboxCaseSensitive.setText(CapellaSearchConstants.CapellaSearchPage_Checkbox_CaseSensitive_Label);
    GridDataFactory.fillDefaults().applyTo(checkboxCaseSensitive);
    checkboxCaseSensitive.setFont(group.getFont());
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
          labelForComboSearchPattern
              .setText(CapellaSearchConstants.CapellaSearchPage_Combo_Pattern_Label_Regex_Enabled);
          checkboxWholeWord.setEnabled(false);
        } else {
          labelForComboSearchPattern
              .setText(CapellaSearchConstants.CapellaSearchPage_Combo_Pattern_Label_Regex_Disabled);
          checkboxWholeWord.setEnabled(true);
        }
      }
    });
  }

  // create a checkbox which will select whole word search
  private void createCheckboxWholeWord(Composite group) {
    checkboxWholeWord = new Button(group, SWT.CHECK);
    checkboxWholeWord.setText(CapellaSearchConstants.CapellaSearchPage_Checkbox_WholeWord_Label);
    GridDataFactory.fillDefaults().applyTo(checkboxWholeWord);
    checkboxWholeWord.setFont(group.getFont());
  }

  private void createSearchForGroup(Composite parent) {
    Group qGrp = new Group(parent, SWT.NONE);
    qGrp.setLayout(new GridLayout(4, false));

    GridData gdGrp = new GridData(GridData.FILL_BOTH);
    gdGrp.horizontalSpan = 2;
    gdGrp.heightHint = 250;

    qGrp.setLayoutData(gdGrp);
    qGrp.setText(CapellaSearchConstants.SearchFor_Label);

    // leftCont will display the list of meta-classes available for search
    leftCont = new CapellaLeftSearchForContainerArea(qGrp, this);

    // rightCont will display the list of attributes available for search
    // the rightCont attributes list is updated based on the selected data in leftCont
    rightCont = new CapellaRightSearchForContainerArea(qGrp, leftCont, this);
    leftCont.setOtherSideArea(rightCont);
    leftCont.createFiltercontainer(qGrp);
  }

  private void createScopeGroup(Composite parent) {
    Group scopeGroup = new Group(parent, SWT.NONE);
    scopeGroup.setLayout(new GridLayout(4, false));

    GridData gdGrp = new GridData(GridData.FILL_BOTH);
    gdGrp.horizontalSpan = 2;
    scopeGroup.setLayoutData(gdGrp);
    scopeGroup.setText(CapellaSearchConstants.ScopeGroup_text);

    workspaceBtn = new Button(scopeGroup, SWT.RADIO);
    workspaceBtn.setData(Integer.valueOf(WORKSPACE_SCOPE));
    workspaceBtn.setText(CapellaSearchConstants.WorkspaceScope_text);

    selectedElementBtn = new Button(scopeGroup, SWT.RADIO);
    selectedElementBtn.setData(Integer.valueOf(SELECTED_ELEMENT_SCOPE));
    selectedElementBtn.setText(CapellaSearchConstants.SelectedElementScope_text);

    projectBtn = new Button(scopeGroup, SWT.RADIO);
    projectBtn.setData(Integer.valueOf(PROJECT_SCOPE));
    projectBtn.setText(CapellaSearchConstants.ProjectScope_text);

    // Add scope change listeners
    SelectionAdapter scopeChangedLister = new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        handleScopeChanged(e);
      }
    };
    workspaceBtn.addSelectionListener(scopeChangedLister);
    selectedElementBtn.addSelectionListener(scopeChangedLister);
    projectBtn.addSelectionListener(scopeChangedLister);

    // Set initial scope
    setScopeAndUpdateUI(WORKSPACE_SCOPE);
  }

  private void handleScopeChanged(SelectionEvent e) {
    Object source = e.getSource();
    if (source instanceof Button) {
      Button button = (Button) source;
      if (button.getSelection())
        this.selectedScope = ((Integer) button.getData()).intValue();
    }
  }

  public void setScopeAndUpdateUI(int scope) {
    this.selectedScope = scope;
    workspaceBtn.setSelection(scope == WORKSPACE_SCOPE);
    selectedElementBtn.setSelection(scope == SELECTED_ELEMENT_SCOPE);
    projectBtn.setSelection(scope == PROJECT_SCOPE);
  }

  // this function is used to apply the settings stored in history, from a previous search
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
      // update the meta-classes panel to display the selected elements stored from a previous search
      leftCont.applySearchSettings(settings);
    }

    if (rightCont != null) {
      // update the attributes panel to display the selected elements stored from a previous search
      rightCont.applySearchSettings(settings);
    }

    int scope = settings.getScope();
    setScopeAndUpdateUI(scope);

    validate();
  }

  /**
   * 
   * Apply the default settings
   */
  protected void applyDefaultSearchSettings() {
    checkboxCaseSensitive.setSelection(false);
    comboSearchPattern.setText("");
    checkboxWholeWord.setSelection(false);
    checkboxRegex.setSelection(false);

    if (leftCont != null) {
      leftCont.applyDefaultSearchSettings();
    }

    if (rightCont != null) {
      rightCont.applyDefaultSearchSettings();
    }

    setScopeAndUpdateUI(WORKSPACE_SCOPE);
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
        String[] previousSearchPatterns = previousSearchSettings.stream().map(CapellaSearchSettings::getTextPattern)
            .toArray(String[]::new);
        comboSearchPattern.setItems(previousSearchPatterns);
        comboSearchPattern.select(0);
        applySearchSettings(previousSearchSettings.get(0));
      } else {
        applyDefaultSearchSettings();
      }
      comboSearchPattern.setFocus();
    }
    super.setVisible(visible);
  }

  /*
   * Validate the current search settings
   */
  private IStatus validate() {
    // Update the search settings from UI
    capellaSearchSettings = getCapellaSearchSettings();
    capellaSearchSettings.setTextPattern(comboSearchPattern.getText());
    capellaSearchSettings.setCaseSensitive(checkboxCaseSensitive.getSelection());
    capellaSearchSettings.setRegExSearch(checkboxRegex.getSelection());
    capellaSearchSettings.setWholeWord(checkboxWholeWord.getSelection());
    capellaSearchSettings.setScope(selectedScope);

    capellaSearchSettings.setAbstractChecked(leftCont.isAbstractChecked());
    capellaSearchSettings.setNonSemanticChecked(leftCont.isNonSemanticChecked());

    capellaSearchSettings.clearProjects();

    if (selectedScope == WORKSPACE_SCOPE) {
      for (IProject project : getProjectsFromWorkspace()) {
        capellaSearchSettings.addObjectToSearch(project);
      }
    } else if (selectedScope == PROJECT_SCOPE) {
      Set<IProject> selectedProjects = new HashSet<>();
      ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
      // EObject is selected
      if (selection instanceof IStructuredSelection) {
        for (Iterator<?> iter = ((IStructuredSelection) selection).iterator(); iter.hasNext();) {
          Object sel = iter.next();
          if (sel instanceof EObject) {
            selectedProjects.add(PreferencesHelper.getProject((EObject) sel));
          }
        }
      }
      // Resource/editor is selected
      String[] evaluatedProjects = SearchDialog.evaluateEnclosingProject(selection, getActiveEditor());
      for (IProject project : getProjectsFromWorkspace()) {
        if (Arrays.asList(evaluatedProjects).contains(project.getName())) {
          selectedProjects.add(project);
        }
      }
      selectedProjects.stream().forEach(project -> capellaSearchSettings.addObjectToSearch(project));
    } else if (selectedScope == SELECTED_ELEMENT_SCOPE) {
      Set<EObject> selectedElements = new HashSet<>();
      ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
      // EObject is selected
      if (selection instanceof IStructuredSelection) {
        for (Iterator<?> iter = ((IStructuredSelection) selection).iterator(); iter.hasNext();) {
          Object sel = iter.next();
          EObject semanticObj = CapellaAdapterHelper.resolveSemanticObject(sel);
          selectedElements.add(semanticObj);
        }
      }
      selectedElements.stream().forEach(project -> capellaSearchSettings.addObjectToSearch(project));
    }

    // Validate and update status to be displayed
    IStatus validateStatus = capellaSearchSettings.validate();
    updateValidationStatus(validateStatus);
    return validateStatus;
  }

  private IEditorPart getActiveEditor() {
    IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    if (activePage == null)
      return null;

    IWorkbenchPart activePart = activePage.getActivePart();
    if (activePart == null)
      return null;

    IEditorPart activeEditor = activePage.getActiveEditor();
    if (activeEditor == activePart)
      return activeEditor;

    return null;
  }

  // this function will display an error message if the search parameters are not ok
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
    // The action is enabled if only the form is validated (when the replace button from the search form is pressed).
    IStatus validateStatus = validate();
    if (validateStatus.isOK()) {
      CapellaSearchQuery searchQuery = new CapellaSearchQuery(getCapellaSearchSettings());
      IStatus searchStatus = NewSearchUI.runQueryInForeground(searchPageContainer.getRunnableContext(), searchQuery);

      if (searchStatus.isOK()) {
        CapellaSearchResult searchResult = searchQuery.getSearchResult();
        if (searchResult.getMatchCount() > 0) {
          Set<SearchMatch> allMatches = searchResult.getDisplayedMatches();
          CapellaReplaceRunnable capellaReplaceRunnable = new CapellaReplaceRunnable(searchQuery, allMatches, true);
          new CapellaReplaceRunnableWrapper(capellaReplaceRunnable).run();
          if (capellaSearchSettings.getReplaceTextPattern() != null) {
            // If the action is enabled, that means the form is validated.
            // We can save it in search history to propose it for next usages in future.
            CapellaSearchSettingsHistory.getInstance().appendSearchSettings(capellaSearchSettings);
            CapellaReplaceHistory.getInstance().appendSearchSettings(capellaSearchSettings);
          }
        } else {
          MessageDialog.openInformation(getShell(), CapellaSearchConstants.ReplaceDialog_Title,
              String.format(CapellaSearchConstants.ReplaceDialog_No_Match_Found_Message,
                  searchQuery.getCapellaSearchSettings().getTextPattern()));
        }
      }
      return true;
    }
    return false;
  }

  @Override
  public boolean performAction() {
    // The action is enabled if only the form is validated.
    IStatus validateStatus = validate();
    if (validateStatus.isOK()) {
      CapellaSearchQuery searchQuery = new CapellaSearchQuery(getCapellaSearchSettings());
      NewSearchUI.runQueryInBackground(searchQuery);
      CapellaSearchSettingsHistory.getInstance().appendSearchSettings(capellaSearchSettings);
      return true;
    }
    return false;
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
