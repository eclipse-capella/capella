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
package org.polarsys.capella.core.transition.system.handlers.merge;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IValuePresence;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class RootCategoryFilter extends CategoryFilter {

  public RootCategoryFilter(IContext context) {
    super(context, Messages.RootCategoryFilter, null);
    setInFocusMode(false);
    setActive(true);
    setVisible(false);
  }

  @Override
  public boolean covers(IDifference difference) {
    // Filter SystemEngineering differences
    if (difference instanceof IValuePresence) {
      IValuePresence presence = (IValuePresence) difference;
      if (presence.getElementMatch().get(Role.REFERENCE) instanceof SystemEngineering) {
        return true;
      }
    }

    return super.covers(difference);
  }
}
