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
package org.polarsys.capella.core.transition.system.topdown.handlers.merge;

import org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * This category enables or disables the differences for appliedPropertyValues according to the preference in the Window
 * / Preferences / Transitions
 */
public class AppliedPropertyValuesCategoryFilter extends CategoryFilter {

  public AppliedPropertyValuesCategoryFilter(IContext context) {
    super(context, Messages.AppliedPropertyValuesCategoryFilter, Messages.AppliedPropertyValuesCategoryFilter_Description);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
    setInFocusMode(false); 
    setVisible(true);

    boolean active = (OptionsHandlerHelper.getInstance(context).getBooleanValue(context,
        ITopDownConstants.OPTIONS_SCOPE, ITopDownConstants.OPTIONS__PROPERTY_VALUE__APPLIED_PROPERTY_VALUES,
        ITopDownConstants.OPTIONS__PROPERTY_VALUE__APPLIED_PROPERTY_VALUES__DEFAULT.booleanValue()));
    setActive(!active);
  }

  @Override
  public boolean covers(EStructuralFeature feature) {
    if ((CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES.equals(feature)
        || CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS.equals(feature))) {
      return true;
    }
    return super.covers(feature);
  }

  @Override
  public boolean covers(IDifference<EObject> difference) {
    if (difference instanceof EReferenceValuePresence) {
      EReference feature = ((EReferenceValuePresence) difference).getFeature();
      return covers(feature);
    }
    return false;

  }

}
