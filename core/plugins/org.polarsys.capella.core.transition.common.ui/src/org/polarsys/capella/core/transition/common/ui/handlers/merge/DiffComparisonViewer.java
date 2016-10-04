/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection;
import org.eclipse.emf.diffmerge.ui.viewers.DirectedComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.MergeChoiceData;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.core.transition.common.ui.Activator;

public class DiffComparisonViewer extends DirectedComparisonViewer {

  /** The name of the "merge all" image */
  private static final String CHECKIN_ACTION_ALL = "checkin_action_all.gif";
  
  private static final String CHECKOUT_ACTION_ALL = "checkout_action_all.gif";

  private static boolean mergeAllInProgress = false;

  public DiffComparisonViewer(Composite parent) {
    super(parent);
  }

  public DiffComparisonViewer(Composite parent, IActionBars actionBars) {
    super(parent, actionBars);
  }

  protected MenuItem createMenuSupportUndoRedo(Menu menu) {
    //We don't want to create an Undo/Redo menu, we are in a global transaction while the whole process
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
  
  protected Image getImage(String key) {
    ImageRegistry reg = Activator.getDefault().getImageRegistry();
    Image image = reg.get(key);
    if (image == null) {
      ImageDescriptor desc = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/ctool16/"+key);
      image = desc.createImage();
      reg.put(key, image);
    }
    return reg.get(key);
  }
  
  /**
   * Create the "merge all" tool to the given side in the given tool bar and return it
   * @param toolbar a non-null tool bar
   * @param onLeft whether the side is left
   * @return a potentially null tool item
   */
  protected ToolItem createToolMergeAll(ToolBar toolbar, final boolean onLeft) {
    final ToolItem result = new ToolItem(toolbar, SWT.PUSH);
    // Image
    String imageKey = onLeft ? CHECKIN_ACTION_ALL : CHECKOUT_ACTION_ALL;
    result.setImage(getImage(imageKey));
    // Tool tip
    result.setToolTipText(onLeft ? Messages.ComparisonViewer_MergeAllOnLeftTooltip : Messages.ComparisonViewer_MergeAllOnRightTooltip);

    // Selection
    result.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        mergeAll();
      }
    });
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
          if (input != null) {
            if (input instanceof MergeEMFDiffNode) {
              MergeEMFDiffNode mergeInput = (MergeEMFDiffNode) input;
              
              // enable merge all button if the other model is editable and there are differences to merge
              result.setEnabled(mergeInput.isMergeAllEnabled(onLeft));
            }
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

  @Override
  protected void setupToolsDetailsSide(ToolBar toolbar, boolean onLeft) {
    createToolMergeAll(toolbar, onLeft);
    super.setupToolsDetailsSide(toolbar, onLeft);
  }

  void mergeAll(){
    IEditableModelScope  scope = getComparison().getScope(getInput().getRoleForSide(true));
    List<EObject> root = scope.getContents();
    ComparisonSelection selection = asComparisonSelection(new StructuredSelection(root));
    mergeAllInProgress = true;
    merge(false, true, selection);
    mergeAllInProgress = false;
  }
}
