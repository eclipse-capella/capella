/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.emf.validation.service.IConstraintFilter;
import org.eclipse.emf.validation.service.IValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.polarsys.capella.test.framework.api.OracleDefinition;

/**
 * 
 */
public abstract class AbstractSimpleValidationTest extends ValidationRuleTestCase {

  @Override
  protected String getRequiredTestModel() {
    return null;
  }

  @Override
  protected String getRuleID() {
    return null;
  }

  @Override
  protected EClass getTargetedEClass() {
    return null;
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return null;
  }

  @Override
  protected void setUp() throws Exception {
    ModelValidationService.getInstance().loadXmlConstraintDeclarations();
  }
  
  /**
   * Validate the given Object with the EMF Validation service.
   * Triggers a failure unless the result status 'isOK()'. 
   * 
   * @param e
   */
  protected void ok(EObject e) {
    IStatus status = validate(e);
    assertTrue(status.getMessage(), status.isOK());
  }

  /**
   * Validate the given Object with the EMF Validation service.
   * Triggers a failure if the result status 'isOK()'.
   * @param e
   */
  protected void ko(EObject e) {
    IStatus status = validate(e);
    assertFalse("Validator returned unexpected OK Status", status.isOK()); //$NON-NLS-1$
  }

  protected abstract List<String> getRuleIDs();

  /**
   * Runs model validation on the given EObejct. Before running the validation,
   * we install a filter on the validator to only validate the constraint
   * returned by getConstraintId()
   * 
   * @param e
   * @return the status we receive from the model validation service after validation.
   */
  protected IStatus validate(EObject e) {
    // force activation of constraints under test
    ConstraintRegistry registry = ConstraintRegistry.getInstance();
    for (String ruleID : getRuleIDs()) {
      IConstraintDescriptor descriptor = registry.getDescriptor(ruleID);
      assertNotNull("Did not find constraint with id " + ruleID //$NON-NLS-1$ 
          + "in the constraint registry", descriptor); //$NON-NLS-1$
      
      descriptor.setEnabled(true);
    }
    IValidator<EObject> validator = getValidator();
    validator.addConstraintFilter(new IConstraintFilter() {
      public boolean accept(IConstraintDescriptor constraint_p, EObject target_p) {
        return getRuleIDs().contains(constraint_p.getId());
      }
    });
    return validator.validate(e);
  }

  /**
   * Create the validator that's used by validate()
   * @return
   */
  protected IValidator<EObject> getValidator() {
    return ModelValidationService.getInstance().newValidator(EvaluationMode.BATCH);
  }
}
