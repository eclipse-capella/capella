/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.validation.rules.ju.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.emf.validation.service.IConstraintFilter;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.re.ReAbstractElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.validation.CapellaValidationActivator;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.api.OracleDefinition;

/**
 * This class is a generic test case for validation rule test.<br>
 * To use it, create a test case that inherits from this class and implement abstract methods, that are<br>
 * (see method documentation for more details) :<br>
 * <br>
 * - getTestProjectName()<br>
 * - getTargetedEClass()<br>
 * - getRuleID()<br>
 * - getOracleDefinitions()<br>
 * 
 * @author Erwan Brottier
 * @contributor Joao Barata
 */
public abstract class ValidationRuleTestCase extends BasicTestCase {

  // these values are obtained by using methods defined in concrete test cases
  protected String ruleID = getRuleID();
  protected EClass targetedEClass = getTargetedEClass();

  // internal variables
  protected IConstraintDescriptor ruleDescriptor;
  protected IConstraintFilter filter;
  protected IBatchValidator validator;
  protected Hashtable<String, OracleDefinition> objectID2OracleDefinition = new Hashtable<String, OracleDefinition>();
  
  // stores the status of the rule before to launch the test
  private boolean _ruleWasDisabled;

  // these methods must be overridden by concrete test cases
  /** returns the name of the test project folder (by default in the folder "model") */
  protected abstract String getRequiredTestModel();

  /** returns the class targeted by the tested rule (should be automatically found from the rule but not accessible) */
  protected abstract EClass getTargetedEClass();

  /** returns tested rule ID */
  protected abstract String getRuleID();

  /** returns the oracle definition to be checked */
  protected abstract List<OracleDefinition> getOracleDefinitions();

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    // init validator
    validator = CapellaValidationActivator.getDefault().getCapellaValidatorAdapter().getValidator();
    ModelValidationService.getInstance().loadXmlConstraintDeclarations();// load the xml definition of constraints
    // get the descriptor of the rule to test
    ConstraintRegistry registry = ConstraintRegistry.getInstance();

    if (ruleID != null) {
      ruleDescriptor = registry.getDescriptor(ruleID);
      if (ruleDescriptor == null) {
        throw new InternalError("rule for ID " + ruleID + " does not exist. Test can not be performed"); //$NON-NLS-1$//$NON-NLS-2$
      }
      
      if (!ruleDescriptor.isEnabled()) {
        ruleDescriptor.setEnabled(true);
        _ruleWasDisabled = true;
      }
      
      // create the filter and add it to the validator
      filter = new IConstraintFilter() {
        @SuppressWarnings("synthetic-access")
        public boolean accept(IConstraintDescriptor constraint_p, EObject target_p) {
          return ruleDescriptor == constraint_p;
        }
      };
      validator.addConstraintFilter(filter);
    }
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getRequiredTestModel());
  }

  protected List<EObject> getTestScope(ICapellaModel model) {
    List<EObject> scope = new ArrayList<EObject>();
    Project project = model.getProject(getSessionForTestModel(getRequiredTestModel()).getTransactionalEditingDomain());
    if (project != null) {
      for (EObject object : EObjectExt.getAll(project, targetedEClass)) {
        if (object instanceof CapellaElement || object instanceof ReAbstractElement) {
          scope.add(object);
        }
      }
    }
    return scope;
  }
  
  public void test() throws Exception {
    // get the objects to validate (only CapellaElement because the oracle is based on object ID)
    ICapellaModel model = getTestModel(getRequiredTestModel());
    List<EObject> objectsToValidate = getTestScope(model);
    List<OracleDefinition> oracleDefinitions = getOracleDefinitions();
    if (oracleDefinitions != null) {
      // prepare oracle table
      for (OracleDefinition definition : oracleDefinitions) {
        objectID2OracleDefinition.put(definition.getObjectID(), definition);
      }
      // check the validation result for each objects
      List<EObject> failedObjects = new ArrayList<EObject>();
      Diagnostician diagnostician = new Diagnostician();
      for (EObject object : objectsToValidate) {
        String objectID = getId(object);
        OracleDefinition oracleDef = objectID2OracleDefinition.get(objectID);
        Diagnostic diagnostic = diagnostician.validate(object);
        if ((diagnostic.getSeverity() == Diagnostic.OK) && (oracleDef != null) && oracleDef.getNbExpectedErrors() > 0) {
          assertTrue("Validation rule " + ruleID + " has not detected an error on object " + objectID + " while it must be the case", false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        } else {
          if (diagnostic.getSeverity() != Diagnostic.OK) {
            if (oracleDef != null) {
              oracleDef.countOneError();
            } else {
              failedObjects.add(object);
            }
          }
        }
      }
      // check all objects to be validated before throwing a message
      if (!failedObjects.isEmpty()) {
        String message = "Validation rule " + ruleID + " has detected an error on object(s): \n"; //$NON-NLS-1$ //$NON-NLS-2$
        for (EObject elt : failedObjects) {
          message += " - " + getId(elt) + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
        }
        message += "while it must not be the case.";
        assertTrue(message, false);
      }
      // check that some errors has not been detected according to the number of error occurences for each object in error
      for (OracleDefinition oracleDef : oracleDefinitions) {
        int nbExpectedErrors = oracleDef.getNbExpectedErrors();
        int nbFoundErrors = oracleDef.getNbFoundErrors();
        if (nbFoundErrors < nbExpectedErrors) {
          assertTrue(
              "Validation rule " + ruleID + " has only detected " + nbFoundErrors + " of " + nbExpectedErrors + " expected error(s) on object " + oracleDef.getObjectID(), false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        } else if (nbFoundErrors > nbExpectedErrors) {
          assertTrue(
              "Validation rule " + ruleID + " has detected " + nbFoundErrors + " error(s) instead of " + nbExpectedErrors + " error(s) on object " + oracleDef.getObjectID(), false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        }
      }
    }
  }

  protected String getId(EObject object) {
    if(object instanceof CapellaElement){
      return ((CapellaElement)object).getId();
    }
    if(object instanceof ReAbstractElement){
      return ((ReAbstractElement)object).getId();
    }
    throw new IllegalArgumentException(object.eClass().getName() +"is not supported as a validation targeted EClass");
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();

    if (_ruleWasDisabled && ruleDescriptor != null) {
      ruleDescriptor.setEnabled(false);
    }

    if (validator != null) {
      // remove the filter from the validator
      validator.removeConstraintFilter(filter);
    }
    ruleID = null;
    targetedEClass = null;
    ruleDescriptor = null;
    filter = null;
    validator = null;
    objectID2OracleDefinition = null;    
  }

}
