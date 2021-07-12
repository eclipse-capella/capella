/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.testcases;

import java.util.Optional;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.model.skeleton.CapellaModelSkeleton;
import org.polarsys.capella.core.transition.common.context.TransitionContext;
import org.polarsys.capella.core.transition.common.handlers.traceability.LevelBasedTraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.MappingTraceability;
import org.polarsys.capella.core.transition.common.handlers.traceability.LevelBasedTraceabilityHandler.LevelMappingTraceability;
import org.polarsys.capella.core.transition.system.handlers.traceability.ReconciliationTraceabilityHandler;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class DataPkgReconciliation extends BasicTestCase {
  
  CapellaModelSkeleton project1;
  ExecutionManager manager1;
  DataPkg nonPredefinedDataPkg1;
  LiteralNumericValue nonPredefinedValue1;
  
  CapellaModelSkeleton project2;
  ExecutionManager manager2;
  DataPkg nonPredefinedDataPkg2;
  LiteralNumericValue nonPredefinedValue2;
  
  LevelMappingTraceability mappingTraceability;
  
  @Override
  public void setUp() throws Exception {
    super.setUp();
    manager1 = ExecutionManagerRegistry.getInstance().addNewManager();
    project1 = new CapellaModelSkeleton.Builder(manager1).build();
    nonPredefinedDataPkg1 = InformationFactory.eINSTANCE.createDataPkg("A non-predefined package");
    nonPredefinedValue1 = DatavalueFactory.eINSTANCE.createLiteralNumericValue();
    nonPredefinedValue1.setName("One");
    nonPredefinedValue1.setValue("1");
    nonPredefinedDataPkg1.getOwnedDataValues().add(nonPredefinedValue1);
    manager1.execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        project1.getSystemAnalysis().getOwnedDataPkg().getOwnedDataPkgs().add(nonPredefinedDataPkg1);
      }
    });
    
    manager2 = ExecutionManagerRegistry.getInstance().addNewManager();
    project2 = new CapellaModelSkeleton.Builder(manager2).build();
    nonPredefinedDataPkg2 = InformationFactory.eINSTANCE.createDataPkg("A non-predefined package");
    nonPredefinedValue2 = DatavalueFactory.eINSTANCE.createLiteralNumericValue();
    nonPredefinedValue2.setName("One");
    nonPredefinedValue2.setValue("1");
    nonPredefinedDataPkg2.getOwnedDataValues().add(nonPredefinedValue2);
    manager2.execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        project2.getSystemAnalysis().getOwnedDataPkg().getOwnedDataPkgs().add(nonPredefinedDataPkg2);
      }
    });
    
    DataPkg dataPkg1 = project1.getSystemAnalysis().getOwnedDataPkg();
    DataPkg dataPkg2 = project2.getSystemAnalysis().getOwnedDataPkg();
    MockLevelBasedTraceabilityHandler levelBasedTraceabilityHandler = new MockLevelBasedTraceabilityHandler("Mock LevelBasedTraceabilityHandler");
    mappingTraceability = (LevelMappingTraceability) levelBasedTraceabilityHandler.createMappingTraceability(dataPkg1, null, "Mock MappingTraceability");
    MockReconciliationTraceabilityHandler handler = new MockReconciliationTraceabilityHandler("Mock Traceability Handler");
    IContext context = new TransitionContext();
    handler.initializeDataPkg(dataPkg1, dataPkg2, context, mappingTraceability);
  }
  
  @Override
  public void test() throws Exception {
    nonPredefinedTypesAreNotMapped(mappingTraceability);
    predefinedTypesAreMapped(mappingTraceability);
  }

  protected void predefinedTypesAreMapped(LevelMappingTraceability mappingTraceability) {
    DataPkg dataPkg2 = project2.getSystemAnalysis().getOwnedDataPkg();
    Optional<DataPkg> predefinedTypesPkgOpt = dataPkg2.getOwnedDataPkgs().stream()
        .filter(pkg -> pkg.getName().equals(NamingConstants.PredefinedTypesCmd_predefinedDataTypePkg_name))
        .findFirst();
    assertTrue(predefinedTypesPkgOpt.isPresent());
    
    TreeIterator<EObject> eAllContents =  predefinedTypesPkgOpt.get().eAllContents();
    while (eAllContents.hasNext()) {
      EObject next = eAllContents.next();
      assertTrue(mappingTraceability.containsKey(next));
    }
  }
  
  protected void nonPredefinedTypesAreNotMapped(LevelMappingTraceability mappingTraceability) {
    assertTrue(!mappingTraceability.containsKey(nonPredefinedDataPkg2));
    TreeIterator<EObject> eAllContents = nonPredefinedDataPkg2.eAllContents();
    while (eAllContents.hasNext()) {
      EObject next = eAllContents.next();
      assertTrue(!mappingTraceability.containsKey(next));
    }
  }
  
  @Override
  public void tearDown() throws Exception {
    ExecutionManagerRegistry.getInstance().removeManager(manager1);
    ExecutionManagerRegistry.getInstance().removeManager(manager2);
    super.tearDown();
  }

  class MockLevelBasedTraceabilityHandler extends LevelBasedTraceabilityHandler {
    
    public MockLevelBasedTraceabilityHandler(String identifier) {
      super(identifier);
    }
    
    @Override
    public MappingTraceability createMappingTraceability(EObject source, IContext context, String key) {
      return super.createMappingTraceability(source, context, key);
    }
  }
  
  class MockReconciliationTraceabilityHandler extends ReconciliationTraceabilityHandler {
    
    public MockReconciliationTraceabilityHandler(String identifier) {
      super(identifier);
    }
    
    public void initializeDataPkg(DataPkg source, DataPkg target, IContext context, LevelMappingTraceability map) {
      super.initializeDataPkg(source, target, context, map);
    }
  }
}
