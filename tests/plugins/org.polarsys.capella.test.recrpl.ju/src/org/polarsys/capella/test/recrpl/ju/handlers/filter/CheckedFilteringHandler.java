/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.recrpl.ju.handlers.filter;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.polarsys.capella.common.re.handlers.filter.FilterFromTargetItem;
import org.polarsys.capella.core.transition.common.handlers.filter.DefaultFilteringDifferencesHandler;
import org.polarsys.capella.core.transition.common.handlers.filter.IFilterItem;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * This class simulates a diffmerge with everything checked in the wizards
 */
public class CheckedFilteringHandler extends DefaultFilteringDifferencesHandler {

  @Override
  public IStatus processDifferences(IContext context_p, Collection<IDifference> diffSource_p, Collection<IDifference> diffTarget_p) {
    if (filterItems != null) {
      for (IFilterItem item : new ArrayList<IFilterItem>(filterItems)) {
        if (item instanceof FilterFromTargetItem) {
          filterItems.remove(item);
        }
      }
    }
    return super.processDifferences(context_p, diffSource_p, diffTarget_p);
  }

}
