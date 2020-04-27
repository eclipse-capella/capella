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

package org.polarsys.capella.common.helpers.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.IConstraintStatus;

/**
 * A Diagnostic that's backed by an IConstraintStatus
 */
public class ConstraintStatusDiagnostic implements Diagnostic {

  private IConstraintStatus status;

  public ConstraintStatusDiagnostic(IConstraintStatus status) {
    this.status = status;
  }

  /**
   * @return the IConstraintStatus that is backing this Diagnostic
   */
  public IConstraintStatus getConstraintStatus() {
    return status;
  }

  /**
   * {@inheritDoc}
   */
  public int getSeverity() {
    return status.getSeverity();
  }

  /**
   * {@inheritDoc}
   */
  public String getMessage() {
    return status.getMessage();
  }

  /**
   * {@inheritDoc}
   */
  public String getSource() {
    return status.getPlugin();
  }

  /**
   * {@inheritDoc}
   */
  public int getCode() {
    return status.getCode();
  }

  /**
   * {@inheritDoc}
   */
  public Throwable getException() {
    return status.getException();
  }

  /**
   * {@inheritDoc}
   */
  public List<?> getData() {
    List<Object> result = new ArrayList<Object>();

    EObject target = status.getTarget();
    Set<EObject> resultLocus = status.getResultLocus();

    // the target shall be always the first element
    if (resultLocus.contains(target)) {
      result.add(target);
    }
    // then we add the other elements
    for (EObject obj : resultLocus) {
      if (!result.contains(obj)) {
        result.add(obj);
      }
    }

    return result;
  }

  /**
   * {@inheritDoc}
   */
  public List<Diagnostic> getChildren() {
    return Collections.emptyList();
  }
}
