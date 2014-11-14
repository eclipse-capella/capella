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
package org.polarsys.capella.core.transition.system.topdown.handlers.filter;

import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class AppliedPropertyValuesFilterItem extends AbstractFilterItem {

  @Override
  public String getDescription(IDifference difference_p) {
    return "Application of property values is disabled by preference";
  }

  @Override
  public boolean isMergeable(EStructuralFeature feature_p, IContext context_p) {
    if (isAppliedPropertyValueReference(feature_p, context_p)) {
      return (OptionsHandlerHelper.getInstance(context_p).getBooleanValue(context_p, ITopDownConstants.OPTIONS_SCOPE,
          ITopDownConstants.OPTIONS__PROPERTY_VALUE__APPLIED_PROPERTY_VALUES,
          ITopDownConstants.OPTIONS__PROPERTY_VALUE__APPLIED_PROPERTY_VALUES__DEFAULT.booleanValue()));
    }
    return super.isMergeable(feature_p, context_p);
  }

  /**
   * @param reference_p
   * @param context_p
   * @return
   */
  protected boolean isAppliedPropertyValueReference(EStructuralFeature reference_p, IContext context_p) {
    return CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES.equals(reference_p)
           || CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS.equals(reference_p);
  }
}
