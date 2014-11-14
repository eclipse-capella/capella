/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.common.ui.handlers.filter;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.jface.window.Window;

import org.polarsys.capella.core.transition.common.handlers.filter.DefaultFilteringDifferencesHandler;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelViewer;
import org.polarsys.capella.core.transition.common.ui.dialog.DifferencesDisplayDialog;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class FilteringUIDifferencesHandler extends DefaultFilteringDifferencesHandler {

  @Override
  public IStatus processDifferences(IContext context_p, Collection<IDifference> diffSource_p, Collection<IDifference> diffTarget_p) {
    super.processDifferences(context_p, diffSource_p, diffTarget_p);
    return displayDifferences(context_p, diffSource_p, diffTarget_p);
  }

  /**
   * @param context_p
   * @param diffSource_p
   * @param diffTarget_p
   * @return
   */
  protected IStatus displayDifferences(IContext context_p, Collection<IDifference> diffSource_p, Collection<IDifference> diffTarget_p) {
    DifferencesDisplayDialog diffviewer =
        new DifferencesDisplayDialog(context_p, getViewsToMerge(context_p), (List<IDiffModelViewer>) getViewsToDisplay(context_p));

    if (diffviewer.open() == Window.OK) {
      return Status.OK_STATUS;
    }

    return Status.CANCEL_STATUS;
  }

}
