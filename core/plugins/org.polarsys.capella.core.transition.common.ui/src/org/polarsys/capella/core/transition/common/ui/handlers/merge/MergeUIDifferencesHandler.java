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

import java.util.Collection;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeDialog;
import org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.DefaultMergeHandler;
import org.polarsys.capella.core.transition.common.handlers.merge.ICategoryItem;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class MergeUIDifferencesHandler extends DefaultMergeHandler {

  @Override
  public IStatus processDifferences(IContext context, Collection<IDifference> diffSource,
      Collection<IDifference> diffTarget) {
    super.processDifferences(context, diffSource, diffTarget);
    return displayDifferences(context, diffSource, diffTarget);
  }

  protected IStatus displayDifferences(final IContext context, Collection<IDifference> diffSource,
      Collection<IDifference> diffTarget) {

    final EMFDiffNode diffNode = createDiffNode(context);
    initializeCategories(context, diffNode);

    final Integer[] result = new Integer[] { IDialogConstants.OK_ID };
    final Display display = Display.getDefault();
    display.syncExec(new Runnable() {
      public void run() {
        DiffMergeDialog dialog = createDiffDialog(context, display, diffNode);
        result[0] = dialog.open();
      }
    });

    if (result[0] == IDialogConstants.CANCEL_ID) {
      return Status.CANCEL_STATUS;
    }
    return Status.OK_STATUS;
  }

  protected DiffMergeDialog createDiffDialog(IContext context, Display display, EMFDiffNode diffNode) {
    DiffMergeDialog dialog = new DiffMergeDialog(display.getActiveShell(),
        (String) context.get(ITransitionConstants.COMMAND_NAME), diffNode) {
      protected AbstractComparisonViewer createComparisonViewer(Composite parent) {
        return new DiffComparisonViewer(parent);
      }
    };
    return dialog;
  }

  protected EMFDiffNode createDiffNode(IContext context) {
    TransactionalEditingDomain domain = (TransactionalEditingDomain) context
        .get(ITransitionConstants.TRANSITION_TARGET_EDITING_DOMAIN);

    MergeEMFDiffNode diffNode = new MergeEMFDiffNode((EComparison) context.get(ITransitionConstants.MERGE_COMPARISON),
        domain);
    diffNode.setDefaultIncrementalMode(true);
    diffNode.setDefaultCoverChildren(true);
    diffNode.setDefaultShowImpact(true);
    return diffNode;
  }

  protected void initializeCategories(IContext context, EMFDiffNode diffNode) {
    Set<IDifferenceCategory> category = diffNode.getCategoryManager().getCategories();

    for (ICategoryItem item : categories) {
      category.add(new DiffCategoryProxy(context, item));
    }
  }

}
