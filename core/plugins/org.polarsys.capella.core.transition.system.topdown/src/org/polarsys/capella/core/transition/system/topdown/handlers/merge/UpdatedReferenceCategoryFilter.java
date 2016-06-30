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
package org.polarsys.capella.core.transition.system.topdown.handlers.merge;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.capella.core.transition.common.rules.IRuleUpdateReference;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingPossibility;

public class UpdatedReferenceCategoryFilter extends CategoryFilter {

  public UpdatedReferenceCategoryFilter(IContext context) {
    super(context, Messages.UpdatedReferenceCategoryFilter, null);
    setInFocusMode(false);
    setVisible(true);
    setActive(true);
  }

  @Override
  public boolean covers(IDifference difference) {

    if (difference instanceof IReferenceValuePresence) {
      IRulesHandler ruleHandler = (IRulesHandler) context.get(ITransitionConstants.RULES_HANDLER);
      EObject source = ((IReferenceValuePresence) difference).getElementMatch().get(Role.REFERENCE);
      if (source != null) {
        try {
          MappingPossibility mapping = ruleHandler.getApplicablePossibility(source);
          if (mapping != null) {
            IRule<?> rule = mapping.getCompleteRule();
            if ((rule != null) && (rule instanceof IRuleUpdateReference)) {
              IRuleUpdateReference deeperRule = (IRuleUpdateReference) rule;
              return !deeperRule.isUpdatedReference(((IReferenceValuePresence) difference).getFeature());
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
