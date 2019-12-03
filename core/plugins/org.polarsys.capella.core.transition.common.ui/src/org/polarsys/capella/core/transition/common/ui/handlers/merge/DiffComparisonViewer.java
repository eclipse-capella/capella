/*******************************************************************************
 * Copyright (c) 2016, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.common.ui.handlers.merge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.compare.CompareEditorInput;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection;
import org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.HeaderViewer;
import org.eclipse.emf.diffmerge.ui.viewers.MergeChoiceData;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.common.ui.toolkit.viewers.menu.ModalContextMenuExtender;
import org.polarsys.capella.core.transition.common.ui.Activator;

public class DiffComparisonViewer extends ComparisonViewer {

  /** The name of the "merge all" image */
  private static final String CHECKIN_ACTION_ALL = "checkin_action_all.gif";

  private static final String CHECKOUT_ACTION_ALL = "checkout_action_all.gif";

  private static final String DIFFMERGE_VIEWER_MENU = "org.polarsys.capella.core.transition.common.ui.diffmerge";

  private static boolean mergeAllInProgress = false;

  private static boolean mergeAllSucceed = false;

  public DiffComparisonViewer(Composite parent) {
    super(parent);
    _isExternallySynced = false;
  }

  /**
   * The viewer is launched inside a modal window, using a site is unrelevant. It also lead to selection
   * inconsistencies. (the Diffmerge viewer manipulating selection of the active part, when closing the modal window,
   * the selection of the activePart will be empty)
   */
  @Override
  protected IWorkbenchPartSite getSite() {
    return null;
  }

  /**
   * The viewer is launched inside a modal window, sync with external views is impossible. It can only be done with
   * contextual menus.
   */
  protected ActionContributionItem createItemSyncExternal(IContributionManager context) {
    return null;
  }

  /**
   * Register the context menu on a modal window
   */
  @Override
  protected MenuManager createViewerContextMenus(HeaderViewer<?> viewer, boolean useLocalSelectionProvider) {
    MenuManager manager = super.createViewerContextMenus(viewer, useLocalSelectionProvider);

    ISelectionProvider selectionProvider = useLocalSelectionProvider ? viewer.getInnerViewer()
        : getMultiViewerSelectionProvider();
    ModalContextMenuExtender.registerContextMenu(manager, DIFFMERGE_VIEWER_MENU, selectionProvider);

    return manager;
  }

  public DiffComparisonViewer(Composite parent, IActionBars actionBars) {
    super(parent, actionBars);
  }

  protected MenuItem createMenuSupportUndoRedo(Menu menu) {
    // We don't want to create an Undo/Redo menu, we are in a global transaction while the whole process
    return null;
  }

  @Override
  protected void inputChanged(final Object input, Object oldInput) {
    super.inputChanged(input, oldInput);

    // We expand all expected matches
    if (input instanceof EMFDiffNode) {
      BusyIndicator.showWhile(getShell().getDisplay(), new Runnable() {

        public void run() {
          for (IMatch o : getExpandedElements((EMFDiffNode) input)) {
            _viewerSynthesisMain.getInnerViewer().expandToLevel(o, 0);
          }
        }
      });
    }
  }

  /**
   * Return the first matches
   */
  protected Collection<IMatch> getExpandedElements(EMFDiffNode node) {
    LinkedList<IMatch> matches = new LinkedList<IMatch>();
    Collection<IMatch> result = new ArrayList<IMatch>();
    EComparison comparison = node.getActualComparison();
    matches.addAll(comparison.getContents());

    while (!matches.isEmpty()) {
      IMatch match = matches.removeFirst();
      if (match != null) {
        if (node.getCategoryManager().representAsUserDifference(match)) {
          result.add(match);
        } else {
          matches.addAll(comparison.getContentsOf(match));
        }
      }
    }
    return result;
  }

  protected ImageDescriptor getImageDescriptor(String key) {
    ImageDescriptor desc = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/ctool16/" + key);
    return desc;
  }

  /**
   * Create the "merge all" tool to the given side in the given tool bar and return it
   * 
   * @param manager
   *          a non-null tool bar
   * @param onLeft
   *          whether the side is left
   * @return a potentially null tool item
   */
  protected ActionContributionItem createToolMergeAll(IContributionManager manager, final boolean onLeft) {

    final IAction action = new Action() {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        mergeAll();
      }
    };
    // Image
    String imageKey = onLeft ? CHECKIN_ACTION_ALL : CHECKOUT_ACTION_ALL;
    action.setImageDescriptor(getImageDescriptor(imageKey));

    // Tool tip
    action.setToolTipText(
        onLeft ? Messages.ComparisonViewer_MergeAllOnLeftTooltip : Messages.ComparisonViewer_MergeAllOnRightTooltip);
    action.setEnabled(false);

    ActionContributionItem result = new ActionContributionItem(action);
    manager.add(result);

    // Activation
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event) {
        if (PROPERTY_CURRENT_INPUT.equals(event.getProperty())
            || PROPERTY_ACTIVATION_MERGE_TO_LEFT.equals(event.getProperty())
            || PROPERTY_ACTIVATION_MERGE_TO_RIGHT.equals(event.getProperty())) {
          EMFDiffNode input = getInput();

          if (input instanceof MergeEMFDiffNode) {
            MergeEMFDiffNode mergeInput = (MergeEMFDiffNode) input;
            // enable merge all button if the other model is editable and there are differences to merge
            action.setEnabled(mergeInput.isMergeAllEnabled(onLeft));
          }
        } else if (CompareEditorInput.DIRTY_STATE.equals(event.getProperty())) {
          if (Boolean.TRUE.equals(event.getNewValue())) {
            mergeAllSucceed = true;
          }
        }
      }
    });

    return result;
  }

  @Override
  protected boolean interactionsRequiredForMerge(MergeChoiceData choices, EMFDiffNode input,
      List<EMatch> selectedMatches) {
    if (mergeAllInProgress) {
      return false;
    }
    return super.interactionsRequiredForMerge(choices, input, selectedMatches);
  }

  protected void setupToolsDetailsSide(ToolBar toolbar, boolean onLeft) {
    ToolBarManager toolbarManager = new ToolBarManager(toolbar);
    createToolMergeAll(toolbarManager, onLeft);
    createItemMerge(toolbarManager, !onLeft);
    createItemIgnore(toolbarManager, onLeft);
    createItemDelete(toolbarManager, onLeft);
    toolbarManager.update(true);
  }
  
  boolean mergeAll() {
    IEditableModelScope scope = getComparison().getScope(getInput().getRoleForSide(true));
    List<EObject> root = scope.getContents();
    ComparisonSelection selection = asComparisonSelection(new StructuredSelection(root));
    mergeAllInProgress = true;
    mergeAllSucceed = false;
    merge(false, true, selection);
    mergeAllInProgress = false;
    return mergeAllSucceed;
  }
}
