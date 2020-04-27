/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.dcon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.diffmerge.patterns.capella.CapellaPatternsPlugin;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase;

import junit.framework.Test;

public class Rule_DCON_03 extends ValidationRulePartialTestCase {

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    //Activate Pattern package registration with Validation framework
    CapellaPatternsPlugin.getDefault();
  }

  protected EClass getTargetedEClass() {
    return EcorePackage.Literals.ECLASS;
  }

  protected String getRuleID() {
    return "org.polarsys.capella.patterns.DCON_03";
  }

  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { "28187235-3a82-4fc5-b5e7-3d4a2139b37a" });
  }

  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition("28187235-3a82-4fc5-b5e7-3d4a2139b37a", 1) });
  }

  @Override
  protected String getRequiredTestModel() {
    return "DCON_03";
  }
  
  @Override
  //Override this method not to filter pattern meta-classes
  protected List<EObject> getTestScope(ICapellaModel model) {
    List<EObject> scope = new ArrayList<EObject>();
    IScope resourceScope = new IScope() {
      public List<Resource> getResources() {
        return new ArrayList<Resource>(getSessionForTestModel(getRequiredTestModel()).getTransactionalEditingDomain().getResourceSet().getResources());
      }
    };
    for (String id : getScopeDefinition()) {
      EObject obj = IdManager.getInstance().getEObject(id, resourceScope);
        scope.add(obj);
    }
    return scope;
  }
  
  @Override
  //Override this method to provide id for pattern meta-classes
  protected String getId(EObject object) {
    return IdManager.getInstance().getId(object);
  }

  public static Test suite() {
    return new Rule_DCON_03();
  }
}
