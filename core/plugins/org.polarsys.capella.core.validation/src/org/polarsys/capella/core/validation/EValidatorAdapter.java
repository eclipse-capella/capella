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
package org.polarsys.capella.core.validation;

import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
    // First, do whatever the basic EcoreValidator does
    super.validate(eClass_p, eObject_p, diagnostics_p, context_p);

    IStatus status = Status.OK_STATUS;
    // No point in validating if we can't report results
    if (null != diagnostics_p) {
      // if EMF Mode Validation Service already covered the sub-tree,
      // which it does for efficient computation and error reporting,
      // then don't repeat (the Diagnostician does the recursion
      // externally). If there is no context map, then we can't
      // help it
      if (!hasProcessed(eObject_p, context_p)) {
        status = getValidator().validate(eObject_p, new NullProgressMonitor());
        processed(eObject_p, context_p, status);
        appendDiagnostics(status, diagnostics_p);
      }
    }
    return status.isOK();
  }

  /**
   * Direct validation of {@link EDataType}s is not supported by the EMF validation framework; they are validated indirectly via the {@link EObject}s that
   * hold their values.
   */
  @Override
  public boolean validate(EDataType eDataType_p, Object value_p, DiagnosticChain diagnostics_p, Map<Object, Object> context_p) {
    return super.validate(eDataType_p, value_p, diagnostics_p, context_p);
  }

  /**
   * If we have a context map, record this object's <code>status</code> in it so that we will know later that we have processed it and its sub-tree.
   * @param eObject_p an element that we have validated
   * @param context_p the context (may be <code>null</code>)
   * @param status_p the element's validation status
   */
  protected void processed(EObject eObject_p, Map<Object, Object> context_p, IStatus status_p) {
    if (null != context_p) {
      context_p.put(eObject_p, status_p);
    }
  }

  /**
   * Determines whether we have processed this <code>eObject</code> before, by automatic recursion of the EMF Model Validation Service. This is only possible
   * if we do, indeed, have a context.
   * @param eObject_p an element to be validated (we hope not)
   * @param context_p the context (may be <code>null</code>)
   * @return <code>true</code> if the context is not <code>null</code> and the <code>eObject</code> or one of its containers has already been validated;
   *         <code>false</code>, otherwise
   */
  protected boolean hasProcessed(EObject eObject_p, Map<Object, Object> context_p) {
    boolean result = false;
    if (null != context_p) {
      EObject eObject = eObject_p;
      // This is O(NlogN) but there's no helping it
      while ((null != eObject) && !result) {
        if (context_p.containsKey(eObject)) {
          result = true;
        } else {
          eObject = eObject.eContainer();
        }
      }
    }
    return result;
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
