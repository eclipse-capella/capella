/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.recrpl.ju.testcases.compliance;

import java.util.Arrays;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.emf.validation.service.IConstraintFilter;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.re.validation.design.consistency.BlackBoxComplianceWithRealizationLinks;
import org.polarsys.capella.test.recrpl.ju.model.Compliance;

public class BatchBlackboxComplianceTest1 extends Compliance {

  public static final String RPL_1 = "932d44e4-19b1-4ae3-ad46-3520abe78963"; //$NON-NLS-1$
  public static final String LC_1 = "d4f1132a-cea8-41ed-8f60-d30e3f5f8116"; //$NON-NLS-1$

  private CatalogElement rpl1;
  private LogicalComponent lc1;

  @Override
  public void test() throws Exception {

    rpl1 = (CatalogElement) getObject(RPL_1);
    lc1 = (LogicalComponent) getObject(LC_1);

    assertNotNull(rpl1);
    assertNotNull(lc1);

    IBatchValidator validator = ModelValidationService.getInstance().newValidator(EvaluationMode.BATCH);
    validator.setIncludeLiveConstraints(true);

    validator.addConstraintFilter(new IConstraintFilter() {
      @Override
      public boolean accept(IConstraintDescriptor constraint, EObject target) {
        return BlackBoxComplianceWithRealizationLinks.CONSTRAINT_ID.equals(constraint.getId());
      }
    });

    IStatus status = validator.validate(rpl1);

    assertTrue(status.getSeverity() == IStatus.ERROR);
    ConstraintStatus cs = (ConstraintStatus) status;

    Set<EObject> locus = cs.getResultLocus();
    assertSame(2, locus.size());
    assertTrue(locus.containsAll(Arrays.asList(rpl1, lc1)));

  }


}
