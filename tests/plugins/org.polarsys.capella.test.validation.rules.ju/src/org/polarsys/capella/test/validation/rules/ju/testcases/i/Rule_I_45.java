/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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

public class Rule_I_45 extends ValidationRulePartialTestCase {

  public static final String VALID_EXCHANGE_ITEM_1 = "4f7a6106-ee35-4dc0-87e2-74b67f2b5aae"; //$NON-NLS-1$
  public static final String VALID_EXCHANGE_ITEM_INSTANCE_1 = "a31d4905-1834-4546-95a0-25ea02f4319d"; //$NON-NLS-1$
  public static final String VALID_EXCHANGE_ITEM_INSTANCE_2 = "d6647703-10c2-4abf-9e69-ce45fa12b7f3"; //$NON-NLS-1$
  public static final String INVALID_EXCHANGE_ITEM = "d6c9f791-0287-4be8-ae7a-31c4af18de84"; //$NON-NLS-1$

  public static final String VALID_LC1_PART = "7ba0c519-1570-4d6a-b38a-e04afc30a622"; //$NON-NLS-1$
  public static final String INVALID_LC2_PART = "44394260-1494-487b-94f1-659ace72ab84"; //$NON-NLS-1$

  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(VALID_EXCHANGE_ITEM_1, VALID_EXCHANGE_ITEM_INSTANCE_1, VALID_EXCHANGE_ITEM_INSTANCE_2,
        VALID_EXCHANGE_ITEM_INSTANCE_2, INVALID_EXCHANGE_ITEM, VALID_LC1_PART, INVALID_LC2_PART);
  }

  @Override
  protected EClass getTargetedEClass() {
    return ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.core.validation.I_45";
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition(VALID_EXCHANGE_ITEM_1, 0),
        new OracleDefinition(VALID_EXCHANGE_ITEM_INSTANCE_1, 0),
        new OracleDefinition(VALID_EXCHANGE_ITEM_INSTANCE_2, 0), new OracleDefinition(INVALID_EXCHANGE_ITEM, 1),
        new OracleDefinition(VALID_LC1_PART, 0), new OracleDefinition(INVALID_LC2_PART, 1));
  }

  @Override
  public void test() throws Exception {
    prepareI45();
    super.test();
  }

  /**
   * To check that I45 is raising an error, we modify the model. Indeed, there is a DataNotifier while migration
   * preventing to have an "invalid" model after model being migrated.
   */
  private void prepareI45() {
    CapellaModel model = getTestModel(getRequiredTestModel());
    IScope scope = new ScopeModelWrapper(model);
    Part part = (Part) IdManager.getInstance().getEObject(INVALID_LC2_PART, scope);
    part.eResource().eAdapters().removeIf(DataNotifier.class::isInstance);
    ExecutionManager manager = ExecutionManagerRegistry.getInstance().getExecutionManager(model.getEditingDomain());
    manager.execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        part.setName("differentName");
      }
    });
  }

  @Override
  protected String getRequiredTestModel() {
    return "exchange-item-instance-and-part-model";
  }

}
