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
package org.polarsys.capella.core.transition.common.handlers.merge;

import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class DefaultFocusCategoryFilter extends CategoryFilter {

  public DefaultFocusCategoryFilter(IContext context) {
    super(context, DefaultFocusCategoryFilter.class.getSimpleName(), null);
    setInFocusMode(true);
    setActive(true);
    setVisible(false);
  }

  @Override
  public boolean covers(IDifference difference) {
    return true;
  }
}
