/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.re.handlers.merge;

import org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class AvoidReAttributeCategoryFilter extends CategoryFilter {

  public AvoidReAttributeCategoryFilter(IContext context) {
    super(context, Messages.AvoidReAttributeCategoryFilter, Messages.AvoidReAttributeCategoryFilter_Description);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
    setInFocusMode(false);
    setActive(true);
    setVisible(false);
  }

  @Override
  public boolean covers(IDifference<EObject> difference) {
    if (difference instanceof EAttributeValuePresence) {
      if (RePackage.Literals.CATALOG_ELEMENT__KIND.equals(((EAttributeValuePresence) difference).getFeature())) {
        return true;
      }
      if (RePackage.Literals.RE_NAMED_ELEMENT__NAME.equals(((EAttributeValuePresence) difference).getFeature())) {
        return true;
      }
      if (RePackage.Literals.CATALOG_ELEMENT__SUFFIX.equals(((EAttributeValuePresence) difference).getFeature())) {
        return true;
      }
    }
    return false;
  }
}
