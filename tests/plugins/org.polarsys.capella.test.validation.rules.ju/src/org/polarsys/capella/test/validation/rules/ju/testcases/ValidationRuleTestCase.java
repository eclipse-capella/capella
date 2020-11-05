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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.emf.validation.service.IConstraintFilter;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.validation.ConstraintStatusDiagnostic;
import org.polarsys.capella.common.re.ReAbstractElement;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.model.handler.markers.ICapellaValidationConstants;
import org.polarsys.capella.core.validation.CapellaValidationActivator;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.api.ComposedOracleDefinition;
import org.polarsys.capella.test.framework.api.IOracleDefinition;
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
 * @contributor Sandu Postaru
 */
public abstract class ValidationRuleTestCase extends BasicTestCase {

  private static final String RULE_DOES_NOT_EXIST_PATTERN = "Rule {0} does not exist. Test can not be performed"; // $NON-NLS-N$
  private static final String MISSING_ERROR_ON_OBJECT_PATTERN = "Validation rule {0} has not detected an error on object {1} while it must be the case"; // $NON-NLS-N$
  private static final String UNEXPECTED_ERROR_ON_OBJECT_PATTERN = "Validation rule {0} has detected an error on object(s): \n{1}\nwhile it must not be the case."; // $NON-NLS-N$
  private static final String LESS_ERRORS_THAN_EXPECTED_PATTERN = "Validation rule {0} has only detected {1} of {2} expected error(s) on object {3}"; // $NON-NLS-N$
  private static final String MORE_ERRORS_THAT_EXPECTED_PATTER = "Validation rule {0} has detected {1} error(s) instead of {2} error(s) on object {3}"; // $NON-NLS-N$
  private static final String UNEXPECTED_EMF_ERROR_KIND_PATTERN = "Validation rule {0} has detected another kind of error, please check EMF integrity of the model: {1}"; // $NON-NLS-N$
  private static final String UNEXPECTED_ERROR_KIND = "Validation rule {0} has detected an error but {1} expected"; // $NON-NLS-N$

  // these values are obtained by using methods defined in concrete test cases
  protected String ruleID = getRuleID();
  protected EClass targetedEClass = getTargetedEClass();
  protected boolean quickFix = getCheckQuickFix();

  // internal variables
  protected IConstraintDescriptor ruleDescriptor;
  protected IConstraintFilter filter;
  protected IBatchValidator validator;

  // stores the status of the rule before to launch the test
  private boolean ruleWasDisabled;

  // these methods must be overridden by concrete test cases
  /** returns the name of the test project folder (by default in the folder "model") */
  protected abstract String getRequiredTestModel();

  /** returns the class targeted by the tested rule (should be automatically found from the rule but not accessible) */
  protected abstract EClass getTargetedEClass();

  /** returns tested rule ID */
  protected abstract String getRuleID();

  /** returns the oracle definition to be checked */
  protected abstract List<OracleDefinition> getOracleDefinitions();

  protected boolean getCheckQuickFix() {
    return false;
  }

  protected IStatus testCheckQuickFix(List<IMarker> markers) {
    return Status.OK_STATUS;
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    validator = CapellaValidationActivator.getDefault().getCapellaValidatorAdapter().getValidator();
    ModelValidationService.getInstance().loadXmlConstraintDeclarations();// load the xml definition of constraints

    ConstraintRegistry registry = ConstraintRegistry.getInstance();

    if (ruleID != null) {
      ruleDescriptor = registry.getDescriptor(ruleID);
      if (ruleDescriptor == null) {
        throw new InternalError(MessageFormat.format(RULE_DOES_NOT_EXIST_PATTERN, ruleID)); // $NON-NLS-1$ //$NON-NLS-2$
      }

      if (!ruleDescriptor.isEnabled()) {
        ruleDescriptor.setEnabled(true);
        ruleWasDisabled = true;
      }

      filter = new IConstraintFilter() {
        @Override
        @SuppressWarnings("synthetic-access")
        public boolean accept(IConstraintDescriptor constraint_p, EObject target_p) {
          return ruleDescriptor == constraint_p;
        }
      };
      validator.addConstraintFilter(filter);
    }
  }

  @Override
  public void test() throws Exception {
    List<EObject> elementsToValidate = getElementsToValidate();
    List<OracleDefinition> oracleDefinitions = getOracleDefinitions();
    Map<String, IOracleDefinition> elementIdToOracleMap = computeElementIdToOracleMap(oracleDefinitions);

    List<IMarker> markers = new ArrayList<IMarker>();
    List<EObject> unexpectedFailingElements = new ArrayList<>();

    Diagnostician diagnostician = new Diagnostician();

    for (EObject element : elementsToValidate) {
      String elementId = getId(element);
      IOracleDefinition rootOracle = elementIdToOracleMap.get(elementId);
      Diagnostic diagnostic = diagnostician.validate(element);

      if (missingValidationError(rootOracle, diagnostic)) {
        fail(MessageFormat.format(MISSING_ERROR_ON_OBJECT_PATTERN, ruleID, elementId));
      } else {

        if (diagnostic.getSeverity() != Diagnostic.OK) {
          for (Diagnostic childDiagnostic : diagnostic.getChildren()) {

            if (rootOracle instanceof ComposedOracleDefinition) {
              List<CapellaElement> childFailedElements = getElements(childDiagnostic);

              for (CapellaElement childFailedElement : childFailedElements) {
                String childId = getId(childFailedElement);
                IOracleDefinition childOracle = elementIdToOracleMap.get(childId);

                if (childOracle == null || childOracle.getNbExpectedErrors() == 0) {
                  unexpectedFailingElements.add(childFailedElement);
                } else {
                  childOracle.countOneError();
                }
              }
            }

            IFile resourceFile = EcoreUtil2.getFile(element.eResource());
            IMarker marker = LightMarkerRegistry.getInstance().createMarker(resourceFile, childDiagnostic,
                ICapellaValidationConstants.CAPELLA_MARKER_ID);

            if (marker != null) {
              markers.add(marker);
            }
          }

          assertExpectedRuleHasBeenThrown(diagnostic, element);

          if (rootOracle != null) {
            rootOracle.countOneError();
          } else {
            unexpectedFailingElements.add(element);
          }
        }
      }
    }

    if (!unexpectedFailingElements.isEmpty()) {
      String objects = unexpectedFailingElements.stream().map(elt -> " - " + getId(elt))
          .collect(Collectors.joining("\n"));
      fail(MessageFormat.format(UNEXPECTED_ERROR_ON_OBJECT_PATTERN, ruleID, objects));
    }

    for (IOracleDefinition oracleDef : elementIdToOracleMap.values()) {
      int nbExpectedErrors = oracleDef.getNbExpectedErrors();
      int nbFoundErrors = oracleDef.getNbFoundErrors();

      if (nbFoundErrors < nbExpectedErrors) {
        fail(MessageFormat.format(LESS_ERRORS_THAN_EXPECTED_PATTERN, ruleID, nbFoundErrors, nbExpectedErrors,
            oracleDef.getObjectID()));
      } else if (nbFoundErrors > nbExpectedErrors) {
        fail(MessageFormat.format(MORE_ERRORS_THAT_EXPECTED_PATTER, ruleID, nbFoundErrors, nbExpectedErrors,
            oracleDef.getObjectID()));
      }
    }

    if (getCheckQuickFix()) {
      IStatus status = testCheckQuickFix(markers);
      if (status != null && !status.isOK()) {
        fail(status.getMessage());
      }
    }
  }

  private List<CapellaElement> getElements(Diagnostic diagnostic) {
    return diagnostic.getData().stream() //
        .filter(CapellaElement.class::isInstance)//
        .map(CapellaElement.class::cast) //
        .collect(Collectors.toList());
  }

  private boolean missingValidationError(IOracleDefinition rootOracle, Diagnostic diagnostic) {
    return diagnostic.getSeverity() == Diagnostic.OK && rootOracle != null && rootOracle.getNbExpectedErrors() > 0;
  }

  @Override
  public List<String> getRequiredTestModels() {
    String requiredTestModel = getRequiredTestModel();
    return requiredTestModel != null ? Collections.singletonList(requiredTestModel) : Collections.emptyList();
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

  private List<EObject> getElementsToValidate() {
    ICapellaModel model = getTestModel(getRequiredTestModel());
    return getTestScope(model);
  }

  protected Map<String, IOracleDefinition> computeElementIdToOracleMap(List<OracleDefinition> oracleDefinitions) {
    Map<String, IOracleDefinition> elementIdToOracleMap = new HashMap<>();

    if (oracleDefinitions != null) {
      for (OracleDefinition oracle : oracleDefinitions) {
        elementIdToOracleMap.put(oracle.getObjectID(), oracle);

        if (oracle instanceof ComposedOracleDefinition) {
          ComposedOracleDefinition composedOracle = (ComposedOracleDefinition) oracle;
          Collection<IOracleDefinition> childOracleDefinitions = composedOracle.getChildren();
          for (IOracleDefinition childOracle : childOracleDefinitions) {
            elementIdToOracleMap.put(childOracle.getObjectID(), childOracle);
          }
        }

      }
    }
    return elementIdToOracleMap;
  }

  protected void assertExpectedRuleHasBeenThrown(Diagnostic diagnostic, EObject object) {
    assertFalse(diagnostic.getChildren().isEmpty());
    Diagnostic nestedDiag = diagnostic.getChildren().get(0);

    assertTrue(MessageFormat.format(UNEXPECTED_EMF_ERROR_KIND_PATTERN, ruleID, getAirdURI(object)),
        nestedDiag instanceof ConstraintStatusDiagnostic);

    String ruleIdThrown = ((ConstraintStatusDiagnostic) nestedDiag).getConstraintStatus().getConstraint()
        .getDescriptor().getId();
    assertEquals(MessageFormat.format(UNEXPECTED_ERROR_KIND, ruleIdThrown, ruleID), ruleIdThrown, ruleID);
  }

  protected String getAirdURI(EObject object) {
    return SessionManager.INSTANCE.getSession(object).getSessionResource().getURI().toPlatformString(true);
  }

  protected String getId(EObject object) {
    if (object instanceof CapellaElement) {
      return ((CapellaElement) object).getId();
    }
    if (object instanceof ReAbstractElement) {
      return ((ReAbstractElement) object).getId();
    }
    throw new IllegalArgumentException(object.eClass().getName() + "is not supported as a validation targeted EClass");
  }

  @Override
  protected void tearDown() throws Exception {
    if (ruleWasDisabled && ruleDescriptor != null) {
      ruleDescriptor.setEnabled(false);
    }

    if (validator != null) {
      validator.removeConstraintFilter(filter);
    }

    super.tearDown();
  }

}
