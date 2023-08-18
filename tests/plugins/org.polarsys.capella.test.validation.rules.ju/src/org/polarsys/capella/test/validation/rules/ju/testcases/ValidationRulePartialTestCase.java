/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.re.ReAbstractElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;

/**
 * This class is a generic test case for validation rule test.<br>
 * It overrides the class {@link ValidationRuleTestCase}, allowing the test to be applied on a limited scope.<br>
 * This scope is defined through the implementation of the method {@link ValidationRulePartialTestCase#getScopeDefinition()}.
 * 
 * @author Joao Barata
 */
public abstract class ValidationRulePartialTestCase extends ValidationRuleTestCase {

  /**
   * This method returns the ID of the model elements on which this rule shall be applied.
   * @return a list of model element IDs
   */
  protected abstract List<String> getScopeDefinition();

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTestScope(ICapellaModel)
   */
  @Override
  protected List<EObject> getTestScope(ICapellaModel model) {
    List<EObject> scope = new ArrayList<EObject>();
    IScope resourceScope = new IScope() {
      public List<Resource> getResources() {
        return new ArrayList<Resource>(getSessionForTestModel(getRequiredTestModel()).getTransactionalEditingDomain().getResourceSet().getResources());
      }
    };
    for (String id : getScopeDefinition()) {
      EObject obj = IdManager.getInstance().getEObject(id, resourceScope);
      if (obj instanceof CapellaElement || obj instanceof ReAbstractElement) {
        scope.add(obj);
      }
    }
    return scope;
  }
}
