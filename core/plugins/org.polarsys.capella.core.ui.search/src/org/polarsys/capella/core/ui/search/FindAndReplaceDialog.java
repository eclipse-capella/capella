/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.misc.StringMatcher;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.ef.command.AbstractCompoundCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.re.ReNamedElement;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerView;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewPlugin;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.utils.saxparser.WriteCapellaElementDescriptionSAXParser;
import org.polarsys.capella.core.ui.toolkit.dialogs.ImpactAnalysisDialog;
import org.polarsys.kitalpha.emde.model.Element;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

public class FindAndReplaceDialog extends SelectElementsDialog {

  private static final String SPACE = " "; //$NON-NLS-1$

  private Pattern pattern;

  private static final int REPLACE_ALL_BUTTON = IDialogConstants.CLIENT_ID + 1;
  private static final int COMPUTE_IMPACT_BUTTON = IDialogConstants.CLIENT_ID + 2;
  private FindAndReplaceHeader header;
  private ISelection currentSelection;

  private Predicate<Element> isNameMatching;
  private Predicate<Element> isSummaryMatching;
  private Predicate<Element> isDescriptionMatching;

  boolean ignoreWildCards;
  private boolean wholeExpression;
  private boolean wholeModelSearch;
  private boolean nameSearch;
  private boolean summarySearch;
  private boolean descriptionSearch;
  private SystemEngineering systemEngineering;

  private Set<Element> scope;
  // TODO use a matchingMap:HashMap
  private Set<Element> matchingElementsForName;

  private Set<Element> matchingElementsForSummary;

  private Set<Element> matchingElementsForDescription;

  private boolean updateHyperlinks;

  private boolean ignoreCase;

  // TODO: this is not used anywhere? To remove?
  private Predicate<CapellaElement> isDescriptionMatchingByWords;

  /**
   * Create the dialog.
   * @param parentShell
   * @param elements
   * @param treeViewerExpandLevel
   * @wbp.parser.constructor
   */
  protected FindAndReplaceDialog(Shell parentShell, Collection<? extends EObject> elements, int treeViewerExpandLevel) {
    super(parentShell, org.polarsys.capella.core.ui.search.Messages.FindAndReplaceDialog_title,
          org.polarsys.capella.core.ui.search.Messages.FindAndReplaceDialog_dialogMessage, elements);

  }

  /**
   * @param shell
   * @param root
   * @param modelElementContent
   * @param selection
   * @param treeViewerExpandLevel
   */
  public FindAndReplaceDialog(Shell shell, SystemEngineering root, Set<EObject> modelElementContent, ISelection selection,
      int treeViewerExpandLevel) {
    this(shell, modelElementContent, treeViewerExpandLevel);
    this.currentSelection = selection;
    this.systemEngineering = root;
  }

  /**
   * Overridden to add accelerators.
   * {@inheritDoc}
   */
  @Override
  protected void createButtonsForButtonBar(Composite parent) {
    // Create buttons.
    Button reportImpactButton = createButton(parent, COMPUTE_IMPACT_BUTTON, Messages.FindAndReplaceDialog_compute_impact, false);
    Button replaceAllButton = createButton(parent, REPLACE_ALL_BUTTON, Messages.FindAndReplaceDialog_replace_all, false);
    createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, false);

    reportImpactButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        handlePreviewImpact();
      }

    });
    replaceAllButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        handleReplaceAll(e.getSource());
      }
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doCreateDialogArea(Composite parent) {
    // add the header
    header = new FindAndReplaceHeader(parent, SWT.NO_BACKGROUND);
    super.doCreateDialogArea(parent);
    getViewer().getClientViewer().setSelection(currentSelection, true);
    getViewer().getClientViewer().getTree().showSelection();

    isNameMatching = new Predicate<Element>() {
      @Override
      public boolean apply(Element elt) {
        if (elt instanceof AbstractNamedElement) {
          return match(((AbstractNamedElement) elt).getName());
        } else if (elt instanceof ReNamedElement) {
          return match(((ReNamedElement) elt).getName());

        }
        return false;
      }
    };

    isSummaryMatching = new Predicate<Element>() {
      @Override
      public boolean apply(Element elt) {
        return elt instanceof CapellaElement ? match(((CapellaElement) elt).getSummary()) : false;
      }
    };
    isDescriptionMatching = new Predicate<Element>() {
      @Override
      public boolean apply(Element elt) {
        return elt instanceof CapellaElement ? match(((CapellaElement) elt).getDescription()) : false;
      }
    };

    // other version that matches description words
    isDescriptionMatchingByWords = new Predicate<CapellaElement>() {
      @Override
      public boolean apply(CapellaElement elt) {
        String description = elt.getDescription();
        if (null == description) {
          return false;
        }
        List<String> words = Arrays.asList(description.split(SPACE));

        for (String word : words) {
          if (match(word)) {
            return true;
          }
        }
        return false;
      }
    };

    matchingElementsForSummary = Sets.newHashSet();
    matchingElementsForName = Sets.newHashSet();
    matchingElementsForDescription = Sets.newHashSet();

    header.getWholeModelRadioButton().addSelectionListener(new SelectionAdapter() {
      @SuppressWarnings("synthetic-access")
      @Override
      public void widgetSelected(SelectionEvent e) {
        getViewer().setEnabled(false);
      }
    });
    header.getSelectedElementsRadioBtn().addSelectionListener(new SelectionAdapter() {
      @SuppressWarnings("synthetic-access")
      @Override
      public void widgetSelected(SelectionEvent e) {
        getViewer().setEnabled(true);
      }
    });
  }

  /**
   * 
   */
  private void getUserChoices() {
    ignoreWildCards = !header.isWildCardsChecked();
    ignoreCase = !header.isCaseSensitiveChecked();
    wholeModelSearch = header.isWholeModelChecked();
    wholeExpression = header.isWholeExpression();
    nameSearch = header.isNameChecked();
    summarySearch = header.isSummaryChecked();
    descriptionSearch = header.isDescriptionChecked();
    updateHyperlinks = header.isUpdateHyperlinks();
  }

  void handlePreviewImpact() {
    getUserChoices();
    updateScope();
    updatePattern();
    updateMatchingElements();

    header.getImpactedElementsLabel().setText(matchingElementsForName.size() + SPACE);
    header.getNbImpactedSummaries().setText(matchingElementsForSummary.size() + SPACE);
    header.getNbImpactedDescs().setText(matchingElementsForDescription.size() + SPACE);

    SetView<Element> impactedElts = Sets.union(Sets.union(matchingElementsForName, matchingElementsForSummary), matchingElementsForDescription);

    // no matching elements
    if (impactedElts.isEmpty()) {
      MessageDialog.openInformation(getParentShell(), Messages.FindAndReplaceDialog_title, Messages.FindAndReplaceDialog_no_matching);
    }
    // Some elements matches => opens a preview dialog
    if (!impactedElts.isEmpty()) {
      ImpactAnalysisDialog impactDialog =
          new ImpactAnalysisDialog(new ArrayList<EObject>(impactedElts), Messages.FindAndReplaceDialog_preview, NLS.bind(
              Messages.FindAndReplaceDialog_impacted_find_string, getFindString()));

      //AbstractContextMenuFiller contextMenuFiller = new PreviewDialogContextMenuFiller();
     // impactDialog.setContextMenuManagerFiller(contextMenuFiller);
      impactDialog.open();
    }
  }

  void handleReplaceAll(Object source) {

    getUserChoices();
    updateScope();
    updatePattern();
    updateMatchingElements();

    ICommand replaceAllInNameCmd =
        CommandFactory.createReplaceAllInNameCommand(matchingElementsForName, getFindString(), getReplaceString(), false, ignoreWildCards, false, false);
    ICommand replaceAllInSummaryCmd =
        CommandFactory.createReplaceAllInSummary(matchingElementsForSummary, getFindString(), getReplaceString(), false, ignoreWildCards, false, false);
    ICommand replaceAllInDescCmd =
        CommandFactory.createReplaceAllInDescription(matchingElementsForDescription, getFindString(), getReplaceString(), false, ignoreWildCards, false,
            false);

    // execute the compoundcommand to replace names, summaries and descriptions
    AbstractCompoundCommand replaceAllCommand = new CompoundCommand();
    replaceAllCommand.append(replaceAllInNameCmd);
    replaceAllCommand.append(replaceAllInSummaryCmd);
    replaceAllCommand.append(replaceAllInDescCmd);

    if (replaceAllCommand.getContainedCommandsSize() > 0) {
      TransactionHelper.getExecutionManager(systemEngineering).execute(replaceAllCommand);
    }

    // update description hyperlinks to impacted elements
    if (updateHyperlinks) {
      updateHyperlinksInDescriptions();
    }

    // report results to user
    reportResults(Messages.FindAndReplaceDialog_impacted_names, matchingElementsForName);
    reportResults(Messages.FindAndReplaceDialog_impacted_summaries, matchingElementsForSummary);
    reportResults(Messages.FindAndReplaceDialog_impacted_descriptions, matchingElementsForDescription);

  }

  /**
   * 
   */
  @SuppressWarnings("unchecked")
  private void updateHyperlinksInDescriptions() {
    // warn: not optimized implementation, scans the whole system engineering content
    // TODO reduce the scope update to referencing descriptions
    final Set<EObject> SysEngAllContents = (Set<EObject>) EcoreUtil2.getAllContents(Collections.singleton(systemEngineering));
    final WriteCapellaElementDescriptionSAXParser writeDescription = new WriteCapellaElementDescriptionSAXParser() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected String getName(EObject object) {
        String result = super.getName(object);
        if ((null == result || result.isEmpty()) && object instanceof DRepresentationDescriptor) {
          DRepresentationDescriptor res = (DRepresentationDescriptor) object;
          String repName = res.getName();
          if (null != repName) {
            result = repName;
          }
        }
        return result;
      }

      /**
       * {@inheritDoc}
       */
      @Override
      protected boolean managedObject(EObject object) {
        return super.managedObject(object) || (object instanceof DRepresentationDescriptor);
      }
    };
    ICommand updateHyperlinksCommand = new AbstractReadWriteCommand() {

      @Override
      public void run() {
        writeDescription.updateDescription(new ArrayList<EObject>(SysEngAllContents));

      }

    };
    TransactionHelper.getExecutionManager(SysEngAllContents).execute(updateHyperlinksCommand);
  }

  /**
   * 
   */
  private void updateMatchingElements() {
    if (nameSearch) {
      matchingElementsForName = ImmutableSet.copyOf(Sets.filter(scope, isNameMatching));
    } else {
      matchingElementsForName = Collections.emptySet();
    }
    if (summarySearch) {
      matchingElementsForSummary = ImmutableSet.copyOf(Sets.filter(scope, isSummaryMatching));
    } else {
      matchingElementsForSummary = Collections.emptySet();
    }
    if (descriptionSearch) {
      matchingElementsForDescription = ImmutableSet.copyOf(Sets.filter(scope, isDescriptionMatching));
    } else {
      matchingElementsForDescription = Collections.emptySet();
    }
  }

  /**
   * 
   */
  private void updatePattern() {
    if (!ignoreWildCards) {
      return;
    }
    pattern = Pattern.compile(getFindString(), (ignoreCase ? Pattern.CASE_INSENSITIVE : 0) | Pattern.LITERAL);
  }

  /**
   * @return
   */
  @SuppressWarnings("unchecked")
  private void updateScope() {
    if (wholeModelSearch) {
      scope = (Set<Element>) EcoreUtil2.getAllContents(Collections.singleton(systemEngineering));
    } else { // scope is the selected elements in the tree viewer
      List<? extends EObject> selected = handleResult();
      // append contents of selected elements to the list
      // TODO transform
      scope = (Set<Element>) EcoreUtil2.getAllContents(selected);
    }

  }

  /**
   * @return
   */
  public String getFindString() {
    return header.getFindCombo().getText();
  }

  /**
   * @return
   */
  public String getReplaceString() {
    return header.getReplaceCombo().getText();
  }

  /**
   * Answers whether the given String matches the pattern.
   * @param string the String to test
   * @return whether the string matches the pattern
   */
  boolean match(String string) {
    if (null == string) {
      return false;
    }
    if (!ignoreWildCards) {// if wildcards are used

      return matchWithWildCard(string);
    }
    if (wholeExpression) {
      List<String> findExprList = Arrays.asList(getFindString().split(SPACE));
      List<String> stringPList = Arrays.asList(string.split(SPACE));

      return matchExpressionList(stringPList, findExprList, ignoreCase);
    }
    return pattern.matcher(string).find();
  }

  /**
   * @param string
   * @return
   */
  @SuppressWarnings("restriction")
  private boolean matchWithWildCard(String string) {
    StringMatcher wildCardMatcher = new StringMatcher(getFindString(), ignoreCase, ignoreWildCards);
    return wildCardMatcher.match(string);
  }

  public static boolean matchExpressionList(List<String> text, List<String> findExpr, boolean ignoreCase) {
    if ((null == text) || (null == findExpr)) {
      return false;
    }
    int textSize = text.size();
    int findExprSize = findExpr.size();
    if (findExprSize > textSize) {
      return false;
    }
    for (int i = 0; i < textSize; i++) {
      if (areEquals(findExpr.get(0), text.get(i), ignoreCase)) {
        List<String> textSubList = text.subList(i + 1, i + findExprSize);
        List<String> fExprSublist = findExpr.subList(1, findExprSize);
        if (areEquals(fExprSublist, textSubList, ignoreCase)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * @param l1
   * @param l2
   * @param ignoreCase
   * @return
   */
  private static boolean areEquals(List<String> l1, List<String> l2, boolean ignoreCase) {
    if (l2.size() != l1.size()) {
      return false;
    }
    if (ignoreCase) {
      for (int i = 0; i < l2.size(); i++) {
        if (!l1.get(i).equalsIgnoreCase(l2.get(i))) {
          return false;
        }
      }
    } else {
      return l1.equals(l2);
    }
    return true;
  }

  /**
   * @param string
   * @param string2
   * @param ignoreCase
   * @return
   */
  private static boolean areEquals(String string, String string2, boolean ignoreCase) {
    if (ignoreCase) {
      return string2.equalsIgnoreCase(string);
    }
    return string2.equals(string);
  }

  /**
   * reports impacted elements count to the InfoView
   * @param message
   * @param impactedElements
   */
  private void reportResults(String message, final Set<Element> impactedElements) {
    final int impactedCount = impactedElements.size();
    final String informationMessage =  NLS.bind(message, Integer.valueOf(impactedCount));
    LightMarkerRegistry.getInstance().createMarker(ResourcesPlugin.getWorkspace().getRoot(), new BasicDiagnostic(Messages.FindAndReplaceDialog_1, 0, informationMessage, impactedElements.toArray()));
    try {
      // Show the Information view
      PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(MarkerView.VIEW_ID, null, IWorkbenchPage.VIEW_VISIBLE);
    } catch (PartInitException exception) {
      MarkerViewPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, MarkerViewPlugin.PLUGIN_ID, exception.getLocalizedMessage(), exception));
    }
  }


  class CompoundCommand extends AbstractCompoundCommand {
    /**
     * {@inheritDoc}
     */
    @Override
    public void append(ICommand command) {
      if (null != command) {
        super.append(command);
      }
    }

  }

}
