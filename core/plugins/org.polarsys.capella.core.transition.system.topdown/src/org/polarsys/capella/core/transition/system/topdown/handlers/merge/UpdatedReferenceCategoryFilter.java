/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.capella.core.transition.common.rules.IRuleUpdateReference;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingPossibility;

/**
 * This category hides differences of non-automatically updated attributes (which is defined on rules)
 * 
 * @see org.polarsys.capella.core.transition.common.rules.IRuleUpdateReference
 */
public class UpdatedReferenceCategoryFilter extends CategoryFilter {

  public UpdatedReferenceCategoryFilter(IContext context) {
    super(context, Messages.UpdatedReferenceCategoryFilter, Messages.UpdatedReferenceCategoryFilter_Description);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
    setInFocusMode(false);
    setVisible(true);
    setActive(true);
  }

  @Override
  public boolean covers(IDifference<EObject> difference) {

    if (difference instanceof EReferenceValuePresence) {
      IRulesHandler ruleHandler = (IRulesHandler) context.get(ITransitionConstants.RULES_HANDLER);
      EObject source = ((EReferenceValuePresence) difference).getElementMatch().get(Role.REFERENCE);
      if (source != null) {
        try {
          MappingPossibility mapping = ruleHandler.getApplicablePossibility(source);
          if (mapping != null) {
            IRule<?> rule = mapping.getCompleteRule();
            if ((rule != null) && (rule instanceof IRuleUpdateReference)) {
              IRuleUpdateReference deeperRule = (IRuleUpdateReference) rule;
              return !deeperRule.isUpdatedReference(((EReferenceValuePresence) difference).getFeature());
            }
          }
        } catch (Exception exception) {
          // Nothing to report
        }
      }
    }

    return false;
  }

}
