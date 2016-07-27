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
package org.polarsys.capella.core.re.handlers.merge;

import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class PartOwnedTypeCategoryFilter extends CategoryFilter {

  public PartOwnedTypeCategoryFilter(IContext context) {
    super(context, PartOwnedTypeCategoryFilter.class.getSimpleName(), null);
    setInFocusMode(false);
    setActive(true);
    setVisible(false);
  }

  @Override
  public boolean covers(IDifference difference) {
    if (difference instanceof IElementRelativeDifference) {
      IElementRelativeDifference diff = (IElementRelativeDifference) difference;
      if (diff instanceof IReferenceValuePresence) {
        if (CsPackage.Literals.PART__OWNED_ABSTRACT_TYPE.equals(((IReferenceValuePresence) diff).getFeature())) {
          return true;
        }
      }
    }

    return false;
  }
}
