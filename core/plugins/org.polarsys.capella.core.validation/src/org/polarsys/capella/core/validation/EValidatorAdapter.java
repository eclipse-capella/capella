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
package org.polarsys.capella.core.validation;

import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;

/**
 * An adapter that plugs the EMF Model Validation Service API into the {@link org.eclipse.emf.ecore.EValidator} API.
 */
public class EValidatorAdapter extends EObjectValidator {
  /**
   * Model Validation Service interface for batch validation of EMF elements.
   */
  private IBatchValidator _validator;

  /**
   * Constructor.<br>
   * Default constructor includes live constraints, does not report successes.
   */
  public EValidatorAdapter() {
    this(true, false);
  }

  /**
   * Get the underlying batch validator.
   * @return the validator
   */
  protected IBatchValidator getValidator() {
    return _validator;
  }

  /**
   * Constructor.
   * @param includeLiveConstraints_p
   * @param reportSuccesses_p
   */
  public EValidatorAdapter(boolean includeLiveConstraints_p, boolean reportSuccesses_p) {
    super();
    _validator = (IBatchValidator) ModelValidationService.getInstance().newValidator(EvaluationMode.BATCH);
    _validator.setIncludeLiveConstraints(includeLiveConstraints_p);
    _validator.setReportSuccesses(reportSuccesses_p);
  }

  /**
   * @see org.eclipse.emf.ecore.util.EObjectValidator#validate(org.eclipse.emf.ecore.EObject, org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
   */
  @Override
  public boolean validate(EObject eObject_p, DiagnosticChain diagnostics_p, Map<Object, Object> context_p) {
    return validate(eObject_p.eClass(), eObject_p, diagnostics_p, context_p);
  }

  /**
   * Implements validation by delegation to the EMF validation framework.
   */
  @Override
  public boolean validate(EClass eClass_p, EObject eObject_p, DiagnosticChain diagnostics_p, Map<Object, Object> context_p) {
    IStatus status = Status.OK_STATUS;
    if (context_p.get(ROOT_OBJECT) == null) {
      status = getValidator().validate(eObject_p, new NullProgressMonitor());
      appendDiagnostics(status, diagnostics_p);
    }
    return super.validate(eClass_p, eObject_p, diagnostics_p, context_p) && status.isOK();
  }


  /**
   * Converts a status result from the EMF validation service to diagnostics.
   * @param status_p the EMF validation service's status result
   * @param diagnostics_p a diagnostic chain to accumulate results on
   */
  protected void appendDiagnostics(IStatus status_p, DiagnosticChain diagnostics_p) {
    if (status_p.isMultiStatus()) {
      IStatus[] children = status_p.getChildren();
      for (int i = 0; i < children.length; i++) {
        appendDiagnostics(children[i], diagnostics_p);
      }
    } else if (status_p instanceof IConstraintStatus) {
      diagnostics_p.add(new BasicDiagnostic(status_p.getSeverity(), status_p.getPlugin(), status_p.getCode(), status_p.getMessage(),
                                            ((IConstraintStatus) status_p).getResultLocus().toArray()));
    }
  }
}
