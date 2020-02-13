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
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JTable;
import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ComboContentAdapter;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.jface.text.FindReplaceDocumentAdapterContentProposalProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.search.ui.IReplacePage;
import org.eclipse.search.ui.ISearchPage;
import org.eclipse.search.ui.ISearchPageContainer;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.fieldassist.ContentAssistCommandAdapter;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.core.ui.search.searchfor.CapellaLeftSearchForContainerArea;
import org.polarsys.capella.core.ui.search.searchfor.CapellaRightSearchForContainerArea;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.core.ui.search.searchfor.AbstractCapellaSearchForContainerArea;

public class CapellaSearchPage extends DialogPage implements ISearchPage, IReplacePage {

  private List<CapellaSearchSettings> previousSearchSettings = new ArrayList<>();

  private Combo comboSearchPattern;
  private Label labelForComboSearchPattern;

  private Map<CapellaSearchField, Button> mapSearchFieldToCheckbox = new EnumMap<>(CapellaSearchField.class);

  private Label labelRegex;
  private CLabel labelValidationStatus;
  private ContentAssistCommandAdapter comboSearchPatternRegexContentAssist;

  private Button checkboxCaseSensitive;
  private Button checkboxRegex;
  private Button checkboxWholeWord;

  private CheckboxTableViewer checkboxProjectsSelection;

  private static final Predicate<IProject> IS_VALID_PROJECT = project -> {
    if (!CapellaResourceHelper.isCapellaProject(project)) {
      return false;
    }
    if (!project.isOpen()) {
      return false;
    }
    return !SessionHelper.getExistingSessions(project).isEmpty();
  };

  protected Map<EClass, Set<Object>> displayedElements = new HashMap<EClass, Set<Object>>();
  private ISearchPageContainer searchPageContainer;

  private final CapellaSearchSettings capellaSearchSettings = new CapellaSearchSettings();

  @Override
  public void createControl(Composite parent) {
    initializeDialogUnits(parent);
    // init history searches
    previousSearchSettings.addAll(CapellaSearchSettingsHistory.getAllSearchSettings());
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
    labelForComboSearchPattern.setText(Messages.CapellaSearchContainingText);
    GridDataFactory.swtDefaults()
        .align(SWT.FILL, SWT.CENTER)
        .span(2, 1)
        .grab(true, true).applyTo(labelForComboSearchPattern);
    labelForComboSearchPattern.setFont(group.getFont());
  }

  private void createComboSearchPattern(Composite group) {
    comboSearchPattern = new Combo(group, SWT.SINGLE | SWT.BORDER);
    comboSearchPattern.setFont(group.getFont());
    GridDataFactory.fillDefaults()
        .grab(true, false)
        .hint(convertWidthInCharsToPixels(50), SWT.DEFAULT)
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
    comboSearchPattern.setToolTipText(Messages.CapellaSearchPage_Combo_Pattern_Label_Regex_Enabled);

    ComboContentAdapter contentAdapter = new ComboContentAdapter();
    FindReplaceDocumentAdapterContentProposalProvider findProposer = new FindReplaceDocumentAdapterContentProposalProvider(
        true);
    comboSearchPatternRegexContentAssist = new ContentAssistCommandAdapter(comboSearchPattern, contentAdapter,
        findProposer, ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS, new char[0], true);
    comboSearchPatternRegexContentAssist.setEnabled(false);
  }

  private void createLabelRegex(Composite group) {
    labelRegex = new Label(group, SWT.LEAD);
    labelRegex.setText(Messages.CapellaSearchRegexExplanation);
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
    checkboxCaseSensitive.setText(Messages.CapellaSearchPage_Checkbox_CaseSensitive_Label);
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
    checkboxRegex.setText(Messages.CapellaSearchPage_Checkbox_Regex_Label);
    GridDataFactory.fillDefaults().applyTo(checkboxRegex);
    checkboxRegex.setFont(group.getFont());
    checkboxRegex.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        boolean regexEnabled = checkboxRegex.getSelection();
        comboSearchPatternRegexContentAssist.setEnabled(regexEnabled);
        if (regexEnabled) {
          labelRegex.setText(Messages.CapellaSearchEmptyString);
          checkboxWholeWord.setEnabled(false);
        } else {
          labelRegex.setText(Messages.CapellaSearchRegexExplanation);
          checkboxWholeWord.setEnabled(true);
        }
        validate();
      }
    });
  }

  private void createCheckboxWholeWord(Composite group) {
    checkboxWholeWord = new Button(group, SWT.CHECK);
    checkboxWholeWord.setText(Messages.CapellaSearchPage_Checkbox_WholeWord_Label);
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
    qGrp.setText(Messages.SearchFor_Label);
    CapellaLeftSearchForContainerArea leftCont = new CapellaLeftSearchForContainerArea(qGrp);
    CapellaRightSearchForContainerArea rightCont = new CapellaRightSearchForContainerArea(qGrp, leftCont);
    leftCont.setOtherSideArea(rightCont);
    createFiltercontainer(qGrp);
  }
  
  protected void createFiltercontainer(Group parentGroup) {
    Group searchForSelectionGroup = new Group(parentGroup, SWT.NONE);
    GridLayoutFactory.swtDefaults().numColumns(2).applyTo(searchForSelectionGroup);
    
    GridData gdGrp = new GridData(GridData.FILL_BOTH);
    gdGrp.widthHint = 50;
    searchForSelectionGroup.setLayoutData(gdGrp);
    
    searchForSelectionGroup.setText(Messages.Filters_Label);
    createCheckboxRegex(searchForSelectionGroup, Messages.Abstract_Label);
    createCheckboxRegex(searchForSelectionGroup, Messages.Semantic_Label);
  }
  
  private void createCheckboxRegex(Composite group, String text) {
    Button checkboxRegex = new Button(group, SWT.CHECK);
    checkboxRegex.setText(text);
    GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).applyTo(checkboxRegex);
    checkboxRegex.setFont(group.getFont());
  }

  protected void applySearchSettings(CapellaSearchSettings settings) {
    checkboxCaseSensitive.setSelection(settings.isCaseSensitive());
    comboSearchPattern.setText(settings.getTextPattern());
    checkboxWholeWord.setSelection(settings.isWholeWord());

    boolean regexEnabled = settings.isRegExSearch();
    checkboxRegex.setSelection(regexEnabled);
    comboSearchPatternRegexContentAssist.setEnabled(regexEnabled);
    if (regexEnabled) {
      labelForComboSearchPattern.setText(Messages.CapellaSearchPage_Combo_Pattern_Label_Regex_Enabled);
    } else {
      labelForComboSearchPattern.setText(Messages.CapellaSearchPage_Combo_Pattern_Label_Regex_Disabled);
    }

    validate();
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
    capellaSearchSettings.setTextPattern(comboSearchPattern.getText());
    capellaSearchSettings.setCaseSensitive(checkboxCaseSensitive.getSelection());
    capellaSearchSettings.setRegExSearch(checkboxRegex.getSelection());
    capellaSearchSettings.setWholeWord(checkboxWholeWord.getSelection());

    capellaSearchSettings.clearSearchFields();
    for (Entry<CapellaSearchField, Button> entry : mapSearchFieldToCheckbox.entrySet()) {
      CapellaSearchField capellaSearchField = entry.getKey();
      Button searchFieldCheckbox = entry.getValue();
      if (searchFieldCheckbox.getSelection()) {
        capellaSearchSettings.addSearchField(capellaSearchField);
      }
    }
    capellaSearchSettings.addSearchField(CapellaSearchField.NAME);
    capellaSearchSettings.addSearchField(CapellaSearchField.DESCRIPTION);
    capellaSearchSettings.addSearchField(CapellaSearchField.SUMMARY);

    capellaSearchSettings.clearProjects();
    for (Object checkedElement : getProjectsFromWorkspace()) {
      if (checkedElement instanceof IProject) {
        capellaSearchSettings.addProject(((IProject) checkedElement).getName());
      }
    }

    // Validate
    IStatus validateStatus = capellaSearchSettings.validate();
    if (validateStatus.isOK()) {
      searchPageContainer.setPerformActionEnabled(true);
      labelValidationStatus.setText(Messages.CapellaSearchPage_Validation_Message_OK);
    } else {
      labelValidationStatus.setText(validateStatus.getMessage());
      searchPageContainer.setPerformActionEnabled(true);
    }
  }

  @Override
  public boolean performReplace() {
    return true;
  }

  @Override
  public boolean performAction() {
    // The action is enabled if only the form is validated.
    CapellaSearchQuery searchQuery = new CapellaSearchQuery(capellaSearchSettings);
    NewSearchUI.runQueryInBackground(searchQuery);
    CapellaSearchSettingsHistory.appendSearchSettings(capellaSearchSettings);
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
