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
package org.polarsys.capella.test.validation.rules.ju.testcases.misc;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.model.handler.validation.CapellaDiagnostician;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase;

/**
 * Checks that there's no stackoverflow error when using a CapellaDiagnostician against a model that contains part-component cycles, e.g.: LC1 LC1 (Part) : LC1
 */
public class NoStackoverflowErrorOnValidation extends ValidationRuleTestCase {

  @Override
  protected String getRequiredTestModel() {
    return "NoStackOverflowModel"; //$NON-NLS-1$
  }

  @Override
  protected EClass getTargetedEClass() {
    return null;
  }

  @Override
  protected String getRuleID() {
    return null;
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return null;
  }
  
  public void test() throws Exception {
    ICapellaModel model = getTestModel(getRequiredTestModel());
    Project project = model.getProject(getSessionForTestModel(getRequiredTestModel()).getTransactionalEditingDomain());
    if (project != null) {
      // we only care about this call to complete normally.
      CapellaDiagnostician md = new CapellaDiagnostician(CapellaAdapterFactoryProvider.getInstance().getAdapterFactory(), new NullProgressMonitor());
      md.validate(project);
    }
  }

}
