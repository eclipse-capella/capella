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
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeDialog;
import org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.core.commands.preferences.service.ScopedCapellaPreferencesStore;
import org.polarsys.capella.core.preferences.Activator;
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
    final EMFDiffNode diffNode = createDiffNode(context);
    initializeCategories(context, diffNode);
    return displayDifferences(context, diffNode);
  }

  protected IStatus displayDifferences(final IContext context, final EMFDiffNode diffNode) {
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

    MergeEMFDiffNode diffNode = new MergeEMFDiffNode(context, domain);
    diffNode.setDefaultCoverChildren(true);
    diffNode.setDefaultShowImpact(true);
    diffNode.setUndoRedoSupported(false);
    diffNode.setLeftRole(Role.REFERENCE);
    diffNode.setMergeAllOnLeft(true);
    diffNode.setMergeAllOnRight(false);
    return diffNode;
  }
  
  protected void initializeCategories(IContext context, EMFDiffNode diffNode) {
    Set<IDifferenceCategory> category = diffNode.getCategoryManager().getCategories();
    
    Object purposeValue = context.get(ITransitionConstants.TRANSPOSER_PURPOSE);
    if (purposeValue instanceof String) {
      String purpose = (String) purposeValue;
      ScopedCapellaPreferencesStore scps = ScopedCapellaPreferencesStore.getInstance(Activator.PLUGIN_ID);

      for (ICategoryItem item : categories) {
        category.add(new DiffCategoryProxy(context, item));
      }
      
      for (IDifferenceCategory item : category) {
        String isActiveKey = MergeCategoryManager.getIsActiveKey(purpose, item, diffNode);
        scps.setDefault(isActiveKey, item.isActive());
        if (scps.containsKey(isActiveKey)) {
          boolean active = scps.getBoolean(isActiveKey);
          item.setActive(active);
        }
        
        String inFocusModeKey = MergeCategoryManager.getIsInFocusModeKey(purpose, item, diffNode);
        scps.setDefault(inFocusModeKey, item.isInFocusMode());
        if (scps.containsKey(inFocusModeKey)) {
          boolean inFocusMode = scps.getBoolean(inFocusModeKey);
          item.setInFocusMode(inFocusMode);
        }
        
      }
    }
  }

}
