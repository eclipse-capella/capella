/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.skeleton.impl;

import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.model.skeleton.EngineeringDomain;
import org.polarsys.capella.core.model.skeleton.ISkeletonServices;
import org.polarsys.capella.core.model.skeleton.Messages;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateCtxArchiCmd;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateEPBSArchiCmd;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateEngSystemCmd;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateLogicalArchiCmd;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateOpAnalysisCmd;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreatePhysicalArchiCmd;

/**
 * The skeleton services implementation.
 */
public class SkeletonServicesImpl implements ISkeletonServices {

  private CreateOpAnalysisCmd _currentOpAnalysisCmd;
  private CreateCtxArchiCmd _currentCtxArchitectureCmd;
  private CreateLogicalArchiCmd _currentLogicalArchitectureCmd;
  private CreatePhysicalArchiCmd _currentPhysicalArchitectureCmd;
  private CreateEPBSArchiCmd _currentEpbsArchitectureCmd;

  /**
   * Constructs the skeleton services implementation.
   */
  public SkeletonServicesImpl() {
    // Do nothing.
  }

  /**
   * @see org.polarsys.capella.core.model.skeleton.ISkeletonServices#doSystemEngineering(org.polarsys.capella.core.data.capellamodeller.Project,
   *      java.lang.String, org.polarsys.capella.core.model.skeleton.EngineeringDomain, boolean)
   */
  public SystemEngineering doSystemEngineering(final Project project_p, final String systemName_p, final EngineeringDomain engDomain_p,
      final boolean isOpAnalysisRequired_p) {
    final SystemEngineering[] systemEng = { null };
    // Create a single command that creates the entire skeleton.
    AbstractReadWriteCommand createSystemEngineeringCommand = new AbstractReadWriteCommand() {
      @SuppressWarnings("synthetic-access")
      public void run() {
        // 1 - Builds the system engineering root element.
        CreateEngSystemCmd engSystemCmd = new CreateEngSystemCmd(project_p, systemName_p);
        engSystemCmd.run();
        systemEng[0] = engSystemCmd.getSystemEngineering();

        // 2 - Builds the operational analysis structure skeleton.
        OperationalAnalysis opAnalysis = null;
        if (isOpAnalysisRequired_p) {
          // Call the command allowing to create the operational analysis structure skeleton.
          _currentOpAnalysisCmd = new CreateOpAnalysisCmd(systemEng[0], NamingConstants.SkeletonServicesImpl_package_name_operationalAnalysis);
          _currentOpAnalysisCmd.run();
          opAnalysis = _currentOpAnalysisCmd.getOperationalAnalysis();
        }

        // 3 - Builds the context architecture structure skeleton.
        OperationalActivity opActivity = null;
        if (null != _currentOpAnalysisCmd) {
          opActivity = _currentOpAnalysisCmd.getRootOperationalActivity();
        }
        // Call the command allowing to create the context architecture structure skeleton.
        _currentCtxArchitectureCmd =
            new CreateCtxArchiCmd(systemEng[0], NamingConstants.SkeletonServicesImpl_package_name_systemAnalysis, opAnalysis, opActivity);
        _currentCtxArchitectureCmd.run();
        SystemAnalysis contextArchitecture = _currentCtxArchitectureCmd.getSystemAnalysis();

        // 4 - Builds the logical architecture structure skeleton.
        SystemFunction systemFunction = _currentCtxArchitectureCmd.getSystemFunction();
        SystemComponent system = _currentCtxArchitectureCmd.getSystem();

        // Call the command allowing to create the logical architecture structure skeleton.
        _currentLogicalArchitectureCmd =
            new CreateLogicalArchiCmd(systemEng[0], NamingConstants.SkeletonServicesImpl_package_name_logicalArchitecture, contextArchitecture,
                systemFunction, system);
        _currentLogicalArchitectureCmd.run();
        LogicalArchitecture logicalArchitecture = _currentLogicalArchitectureCmd.getLogicalArchitecture();

        // 5 - Builds the physical architecture.
        LogicalComponent logicalComponent = _currentLogicalArchitectureCmd.getLogicalComponent();
        LogicalFunction logicalFunction = _currentLogicalArchitectureCmd.getLogicalFunction();

        // Call the command allowing to create the physical architecture structure skeleton.
        _currentPhysicalArchitectureCmd =
            new CreatePhysicalArchiCmd(systemEng[0], NamingConstants.SkeletonServicesImpl_package_name_physicalArchitecture, logicalArchitecture,
                logicalComponent, logicalFunction);
        _currentPhysicalArchitectureCmd.run();
        PhysicalArchitecture physicalArchitecture = _currentPhysicalArchitectureCmd.getPhysicalArchitecture();

        // 6 - Builds the EPBS architecture according to the engineering domain value.
        switch (engDomain_p) {
          case System: {
            PhysicalComponent pc = _currentPhysicalArchitectureCmd.getPhysicalComponent();
            // Call the command allowing to create the EPBS architecture structure skeleton.
            _currentEpbsArchitectureCmd =
                new CreateEPBSArchiCmd(systemEng[0], NamingConstants.SkeletonServicesImpl_package_name_epbsArchitecture, physicalArchitecture, pc);
            _currentEpbsArchitectureCmd.run();
            break;
          }
          case Software:
          default: {
            // Do nothing.
          }
        }
      }

      /**
       * @see org.polarsys.capella.common.ef.command.AbstractCommand#getName()
       */
      @Override
      public String getName() {
        return Messages.getString("capella.sys_eng.create.cmd"); //$NON-NLS-1$
      }
    };
    TransactionHelper.getExecutionManager(project_p).execute(createSystemEngineeringCommand);
    return systemEng[0];
  }

  /**
   * @see org.polarsys.capella.core.model.skeleton.ISkeletonServices#doOperationalAnalysis(SystemEngineering)
   */
  public OperationalAnalysis doOperationalAnalysis(SystemEngineering systemEng_p) {
    // Call the command allowing to create the operational analysis structure skeleton.
    String architectureName = NamingConstants.SkeletonServicesImpl_package_name_operationalAnalysis;
    _currentOpAnalysisCmd = new CreateOpAnalysisCmd(systemEng_p, architectureName);
    TransactionHelper.getExecutionManager(systemEng_p).execute(_currentOpAnalysisCmd);

    return _currentOpAnalysisCmd.getOperationalAnalysis();
  }

  /**
   * @see org.polarsys.capella.core.model.skeleton.ISkeletonServices#doSystemAnalysis(org.polarsys.capella.core.data.capellamodeller.SystemEngineering,
   *      org.polarsys.capella.core.data.oa.OperationalAnalysis, org.polarsys.capella.core.data.oa.OperationalActivity)
   */
  public SystemAnalysis doSystemAnalysis(SystemEngineering systemEng_p, OperationalAnalysis opAnalysis_p, OperationalActivity opActivity_p) {
    // Call the command allowing to create the System Analysis structure skeleton.
    String packageName = NamingConstants.SkeletonServicesImpl_package_name_systemAnalysis;
    _currentCtxArchitectureCmd = new CreateCtxArchiCmd(systemEng_p, packageName, opAnalysis_p, opActivity_p);
    TransactionHelper.getExecutionManager(systemEng_p).execute(_currentCtxArchitectureCmd);

    return _currentCtxArchitectureCmd.getSystemAnalysis();
  }

  /**
   * @see org.polarsys.capella.core.model.skeleton.ISkeletonServices#doLogicalArchitecture(org.polarsys.capella.core.data.capellamodeller.SystemEngineering,
   *      org.polarsys.capella.core.data.ctx.SystemAnalysis, org.polarsys.capella.core.data.ctx.SystemFunction,
   *      org.polarsys.capella.core.data.ctx.SystemComponent)
   */
  public LogicalArchitecture doLogicalArchitecture(SystemEngineering systemEng_p, SystemAnalysis ctxArchitecture_p, SystemFunction systemFunction_p,
      SystemComponent system_p) {
    // Call the command allowing to create the logical architecture structure skeleton.
    String packageName = NamingConstants.SkeletonServicesImpl_package_name_logicalArchitecture;
    _currentLogicalArchitectureCmd = new CreateLogicalArchiCmd(systemEng_p, packageName, ctxArchitecture_p, systemFunction_p, system_p);
    TransactionHelper.getExecutionManager(systemEng_p).execute(_currentLogicalArchitectureCmd);

    return _currentLogicalArchitectureCmd.getLogicalArchitecture();
  }

  /**
   * @see org.polarsys.capella.core.model.skeleton.ISkeletonServices#doPhysicalArchitecture(org.polarsys.capella.core.data.capellamodeller.SystemEngineering,
   *      org.polarsys.capella.core.data.la.LogicalArchitecture, org.polarsys.capella.core.data.la.LogicalComponent,
   *      org.polarsys.capella.core.data.la.LogicalFunction)
   */
  public PhysicalArchitecture doPhysicalArchitecture(SystemEngineering systemEng_p, LogicalArchitecture logicalArchitecture_p,
      LogicalComponent logicalComponent_p, LogicalFunction logicalFunction_p) {
    // Call the command allowing to create the physical architecture structure skeleton.
    String packageName = NamingConstants.SkeletonServicesImpl_package_name_physicalArchitecture;
    _currentPhysicalArchitectureCmd = new CreatePhysicalArchiCmd(systemEng_p, packageName, logicalArchitecture_p, logicalComponent_p, logicalFunction_p);
    TransactionHelper.getExecutionManager(systemEng_p).execute(_currentPhysicalArchitectureCmd);

    return _currentPhysicalArchitectureCmd.getPhysicalArchitecture();
  }

  /**
   * @see org.polarsys.capella.core.model.skeleton.ISkeletonServices#doEPBSArchitecture(org.polarsys.capella.core.data.capellamodeller.SystemEngineering,
   *      org.polarsys.capella.core.data.pa.PhysicalArchitecture)
   */
  public EPBSArchitecture doEPBSArchitecture(SystemEngineering systemEng_p, PhysicalArchitecture physicalArchitecture_p, PhysicalComponent pc_p) {
    // Call the command allowing to create the EPBS architecture structure skeleton.
    String packageName = NamingConstants.SkeletonServicesImpl_package_name_epbsArchitecture;
    _currentEpbsArchitectureCmd = new CreateEPBSArchiCmd(systemEng_p, packageName, physicalArchitecture_p, pc_p);
    TransactionHelper.getExecutionManager(systemEng_p).execute(_currentEpbsArchitectureCmd);

    return _currentEpbsArchitectureCmd.getEPBSArchitecture();
  }
}
