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
package org.polarsys.capella.common.re.handlers.merge;

import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class AvoidReAttributeCategoryFilter extends CategoryFilter {

  public AvoidReAttributeCategoryFilter(IContext context) {
    super(context, Messages.AvoidReAttributeCategoryFilter, Messages.AvoidReAttributeCategoryFilter_Description);
    setInFocusMode(false);
    setActive(true);
    setVisible(false);
  }

  @Override
  public boolean covers(IDifference difference) {
    if (difference instanceof IElementRelativeDifference) {
      IElementRelativeDifference diff = (IElementRelativeDifference) difference;
      if (diff instanceof IAttributeValuePresence) {
        if (RePackage.Literals.CATALOG_ELEMENT__KIND.equals(((IAttributeValuePresence) diff).getFeature())) {
          return true;
        }
        if (RePackage.Literals.RE_NAMED_ELEMENT__NAME.equals(((IAttributeValuePresence) diff).getFeature())) {
          return true;
        }
        if (RePackage.Literals.CATALOG_ELEMENT__SUFFIX.equals(((IAttributeValuePresence) diff).getFeature())) {
          return true;
        }
      }
    }

    return false;
  }
}
