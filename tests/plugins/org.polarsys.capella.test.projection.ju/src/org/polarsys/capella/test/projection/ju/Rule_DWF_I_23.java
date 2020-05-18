/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.projection.ju;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.emf.validation.service.IConstraintFilter;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.projection.interfaces.validation.DWF_I_23_GenerateInterfacesValidator;
import org.polarsys.capella.core.validation.CapellaValidationActivator;
import org.polarsys.capella.test.framework.api.BasicCommandTestCase;

/**
 * Runs model validation for components, and compares actual and expected validation results for related
 * interfaces/exchange items
 */
// FIXME cannot use ValidationRuleTest for this because of https://bugs.polarsys.org/show_bug.cgi?id=1342
public class Rule_DWF_I_23 extends BasicCommandTestCase {

  IBatchValidator validator;
  IConstraintFilter filter;

  protected void setUp() throws Exception {
    super.setUp();

    // init validator
    validator = CapellaValidationActivator.getDefault().getCapellaValidatorAdapter().getValidator();
    ModelValidationService.getInstance().loadXmlConstraintDeclarations();// load the xml definition of constraints
    // get the descriptor of the rule to test
    ConstraintRegistry registry = ConstraintRegistry.getInstance();
    final IConstraintDescriptor ruleDescriptor = registry.getDescriptor(DWF_I_23_GenerateInterfacesValidator.RULE_ID);
    assertNotNull(ruleDescriptor);

    filter = new IConstraintFilter() {
      public boolean accept(IConstraintDescriptor constraint, EObject target) {
        return ruleDescriptor == constraint;
      }
    };
    validator.addConstraintFilter(filter);
  }

  @Override
  protected void tearDown() throws Exception {
    validator.removeConstraintFilter(filter);
    super.tearDown();
  }


  @Override
  public List<String> getRequiredTestModels() {
    return Collections.singletonList("testInterfacesFromAllocatedFunctions"); //$NON-NLS-1$
  }

  @Override
  public void performTest() throws Exception {

    Component component = (Component) getObject("0dbb64b4-bf00-4ad2-89be-d6d013bb81c0"); //$NON-NLS-1$
    IStatus status = validator.validate(component);

    assertSame(4, status.getChildren().length);

    IStatus[] children = status.getChildren();
    find(children, "d22ab4e8-b5b4-44ce-9789-c4e0b5a36152", //$NON-NLS-1$
        DWF_I_23_GenerateInterfacesValidator.UNKNOWN_EI_ON_INTERFACE);
    find(children, "f77ec71e-f927-4f4c-b4c9-5dc2cfa806cf", //$NON-NLS-1$
        DWF_I_23_GenerateInterfacesValidator.UNKNOWN_EI_ON_INTERFACE);
    find(children, "9466ef4d-d58f-43ff-8e91-12b846a16dce", //$NON-NLS-1$
        DWF_I_23_GenerateInterfacesValidator.MISSING_EI_ON_INTERFACE);
    find(children, "b1a442a6-b9cc-4be9-8abe-bf265046aa31", //$NON-NLS-1$
        DWF_I_23_GenerateInterfacesValidator.MISSING_EI_ON_INTERFACE);
  }

  private void find(IStatus[] s, String id, int code) {
    for (IStatus status : s) {
      if (id.equals(EcoreUtil.getID(((IConstraintStatus) status).getTarget())) && status.getCode() == code) {
        return;
      }
    }
    fail(String.format("Missing status for %s with code %d", id, code)); //$NON-NLS-1$
  }

}
