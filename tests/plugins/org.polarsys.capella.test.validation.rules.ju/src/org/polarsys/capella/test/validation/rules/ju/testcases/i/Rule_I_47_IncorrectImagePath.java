/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.i;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.platform.sirius.ted.DataNotifier;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase;

public class Rule_I_47_IncorrectImagePath extends ValidationRulePartialTestCase {

  /**
   * Could be any other element of the model. It just have to have the feature description.
   */
  public static final String INVALID_LC2_PART = "44394260-1494-487b-94f1-659ace72ab84"; //$NON-NLS-1$

  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(INVALID_LC2_PART);
  }

  @Override
  protected EClass getTargetedEClass() {
    return ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.core.validation.I_47";
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition(INVALID_LC2_PART, 1));
  }

  @Override
  public void test() throws Exception {
    prepareI47();
    super.test();
  }

  /**
   * To check that I47 is raising an error, we modify the model to associate a documentation that contains an invalid
   * absolute path to an image.<br/>
   */
  private void prepareI47() {
    CapellaModel model = getTestModel(getRequiredTestModel());
    IScope scope = new ScopeModelWrapper(model);
    Part part = (Part) IdManager.getInstance().getEObject(INVALID_LC2_PART, scope);
    part.eResource().eAdapters().removeIf(DataNotifier.class::isInstance);
    ExecutionManager manager = ExecutionManagerRegistry.getInstance().getExecutionManager(model.getEditingDomain());
    manager.execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        part.setDescription(
            "&lt;p>&lt;img src=&quot;file:/C:/Users/T0108576/Documents/Docs/Docs/11_Images/circle_ws2.png&quot; />&lt;/p>&#xA;");
      }
    });
  }

  @Override
  protected String getRequiredTestModel() {
    return "exchange-item-instance-and-part-model";
  }

}
