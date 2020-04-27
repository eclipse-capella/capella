/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.crossreferencer;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerFactory;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateCtxArchiCmd;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateEngSystemCmd;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateLogicalArchiCmd;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateOpAnalysisCmd;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Test to check CrossReferencer behavior when moving elements from a resource to a new one at Capella project creation
 * time.
 */
public class MoveElementToNewResourceTest extends AbstractReflectiveCrossReferencerTest {
  private Project _capellaProject;
  private Resource _resource;
  private ExecutionManager _executionManager;

  /**
   * Test implementation that check cross referencer are registered on all model elements.
   */
  public void testMoveElementToNewResource() {
    final LogicalArchitecture[] logicalArchitecture = new LogicalArchitecture[] { null };
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        // Create the system engineering.
        CreateEngSystemCmd createEngSystemCmd = new CreateEngSystemCmd(_capellaProject, "MoveElementTestProject");
        createEngSystemCmd.run();
        SystemEngineering systemEngineering = createEngSystemCmd.getSystemEngineering();
        // Create the Operational Analysis.
        CreateOpAnalysisCmd createOpAnalysisCmd = new CreateOpAnalysisCmd(systemEngineering,
            NamingConstants.SkeletonServicesImpl_package_name_operationalAnalysis);
        createOpAnalysisCmd.run();
        OperationalAnalysis operationalAnalysis = createOpAnalysisCmd.getOperationalAnalysis();
        OperationalActivity rootOperationalActivity = createOpAnalysisCmd.getRootOperationalActivity();
        // Create the System Analysis.
        CreateCtxArchiCmd createCtxArchiCmd = new CreateCtxArchiCmd(systemEngineering,
            NamingConstants.SkeletonServicesImpl_package_name_systemAnalysis, operationalAnalysis,
            rootOperationalActivity);
        createCtxArchiCmd.run();
        SystemAnalysis contextArchitecture = createCtxArchiCmd.getSystemAnalysis();
        //
        SystemFunction systemFunction = createCtxArchiCmd.getSystemFunction();
        SystemComponent system = createCtxArchiCmd.getSystem();
        // Create the logical Architecture.
        CreateLogicalArchiCmd createLogicalArchiCmd = new CreateLogicalArchiCmd(systemEngineering,
            NamingConstants.SkeletonServicesImpl_package_name_logicalArchitecture, contextArchitecture, systemFunction,
            system);
        createLogicalArchiCmd.run();
        logicalArchitecture[0] = createLogicalArchiCmd.getLogicalArchitecture();
      }
    };
    getExecutionManager().execute(command);

    SemanticEditingDomain editingDomain = (SemanticEditingDomain) getExecutionManager().getEditingDomain();
    ECrossReferenceAdapter crossReferencer = editingDomain.getCrossReferencer();

    LogicalComponent logicalComponent = (LogicalComponent)logicalArchitecture[0].getSystem();
    // Check the Logical System has cross referencers registered as eAdapters().
    assertTrue(logicalComponent.eAdapters().contains(crossReferencer));
  }

  @Override
  protected void postRunTest() {
    super.postRunTest();
    ResourceSet resourceSet = _resource.getResourceSet();
    _resource.unload();
    resourceSet.getResources().remove(_resource);
    _capellaProject = null;
    ExecutionManagerRegistry.getInstance().removeManager(_executionManager);
    _executionManager = null;
  }

  @Override
  protected void preRunTest() {
    super.preRunTest();
    IProject project = TestHelper.createCapellaProject("MoveElementTestProject");
    String fullPath = project.getFullPath().toString() + "/moveElementTestProject.melodymodeller";
    URI capellaModelURI = URI.createPlatformResourceURI(fullPath, true);
    _resource = getExecutionManager().getEditingDomain().getResourceSet().createResource(capellaModelURI);
    _capellaProject = CapellamodellerFactory.eINSTANCE.createProject("moveElementTestProject");
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        _resource.getContents().add(_capellaProject);
      }
    };
    getExecutionManager().execute(command);
  }

  @Override
  protected ExecutionManager getExecutionManager() {
    if (null == _executionManager) {
      _executionManager = ExecutionManagerRegistry.getInstance().addNewManager();
    }
    return _executionManager;
  }

  @Override
  protected List<EClass> getRootTypes() {
    return null;
  }

  @Override
  public void test() throws Exception {
    testMoveElementToNewResource();
  }
}
