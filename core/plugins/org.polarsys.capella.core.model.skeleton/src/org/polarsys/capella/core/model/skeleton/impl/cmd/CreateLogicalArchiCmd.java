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

package org.polarsys.capella.core.model.skeleton.impl.cmd;

import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.ComponentRealization;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.la.SystemAnalysisRealization;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.model.skeleton.Messages;

/**
 * The command allowing to create the logical architecture structure skeleton.
 */
public class CreateLogicalArchiCmd extends AbstractReadWriteCommand {
  // The architecture name.
  private String _architectureName;

  // The logical architecture structure skeleton.
  private LogicalArchitecture _logicalArchitecture;
  // The system function.
  private SystemFunction _systemFunction;
  // The system.
  private SystemComponent _system;

  // The System Analysis.
  private SystemAnalysis _ctxArchitecture;
  // The logical function.
  private LogicalFunction _logicalFunction;
  // The logical component.
  private LogicalComponent _rootComponent;
  // The parent system engineering.
  private SystemEngineering _systemEng;

  /**
   * Constructs the command allowing to create the logical architecture structure skeleton.
   * 
   * @param systemEng_p
   *          The parent system engineering.
   * @param architectureName_p
   *          The architecture name.
   * @param ctxArchitecture_p
   *          The System Analysis.
   * @param systemFunction_p
   *          The system function.
   * @param system_p
   *          The system.
   */
  public CreateLogicalArchiCmd(SystemEngineering systemEng_p, String architectureName_p,
      SystemAnalysis ctxArchitecture_p, SystemFunction systemFunction_p, SystemComponent system_p) {
    _architectureName = architectureName_p;

    _ctxArchitecture = ctxArchitecture_p;
    _systemFunction = systemFunction_p;
    _system = system_p;
    _systemEng = systemEng_p;
  }

  public CreateLogicalArchiCmd(SystemEngineering systemEng_p, String architectureName_p,
      SystemAnalysis ctxArchitecture_p, SystemFunction systemFunction_p, SystemComponent system_p,
      LogicalArchitecture createdElement) {
    this(systemEng_p, architectureName_p, ctxArchitecture_p, systemFunction_p, system_p);
    _logicalArchitecture = createdElement;
  }

  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {
    if (_logicalArchitecture == null) {
      // Builds the logical architecture root element.
      _logicalArchitecture = LaFactory.eINSTANCE.createLogicalArchitecture();
      _systemEng.getOwnedArchitectures().add(_logicalArchitecture);
    }
    _logicalArchitecture.setName(_architectureName);

    // Builds the logical functions structure skeleton.
    LogicalFunctionPkg logicalFunctionPkg = LaFactory.eINSTANCE
        .createLogicalFunctionPkg(NamingConstants.CreateLogicalArchCmd_logicalFunctions_pkg_name);
    _logicalArchitecture.setOwnedFunctionPkg(logicalFunctionPkg);

    _logicalFunction = LaFactory.eINSTANCE
        .createLogicalFunction(NamingConstants.CreateLogicalArchCmd_logicalFunction_root_name);
    logicalFunctionPkg.getOwnedLogicalFunctions().add(_logicalFunction);

    if (null != _systemFunction) {
      FunctionRealization functionRealisation = FaFactory.eINSTANCE.createFunctionRealization();
      _logicalFunction.getOwnedFunctionRealizations().add(functionRealisation);
      functionRealisation.setSourceElement(_logicalFunction);
      functionRealisation.setTargetElement(_systemFunction);
    }

    // Builds the interfaces structure skeleton.
    InterfacePkg interfacesPkg = CsFactory.eINSTANCE
        .createInterfacePkg(NamingConstants.CreateCommonCmd_interfaces_pkg_name);
    _logicalArchitecture.setOwnedInterfacePkg(interfacesPkg);

    // Builds the data structure skeleton.
    DataPkg dataPkg = InformationFactory.eINSTANCE.createDataPkg(NamingConstants.CreateCommonCmd_data_pkg_name);
    _logicalArchitecture.setOwnedDataPkg(dataPkg);

    // Builds the logical actors structure skeleton.
    LogicalComponentPkg logicalComponentsPkg = LaFactory.eINSTANCE
        .createLogicalComponentPkg(NamingConstants.CreateLogicalArchCmd_actors_pkg_name);
    _logicalArchitecture.setOwnedLogicalComponentPkg(logicalComponentsPkg);

    // Builds the logical components structure skeleton.
    _rootComponent = LaFactory.eINSTANCE
        .createLogicalComponent(NamingConstants.CreateLogicalArchCmd_logicalComponent_name);
    logicalComponentsPkg.getOwnedLogicalComponents().add(_rootComponent);

    Part logicalRootPart = CsFactory.eINSTANCE.createPart(_rootComponent.getName());
    logicalComponentsPkg.getOwnedParts().add(logicalRootPart);
    logicalRootPart.setAbstractType(_rootComponent);

    if (null != _system) {
      ComponentRealization systemRealisation = CsFactory.eINSTANCE.createComponentRealization();
      _rootComponent.getOwnedComponentRealizations().add(systemRealisation);
      systemRealisation.setSourceElement(_rootComponent);
      systemRealisation.setTargetElement(_system);
    }

    // Links the System Analysis to the current logical architecture.
    if (null != _ctxArchitecture) {
      SystemAnalysisRealization ctxArchitectureRealisation = LaFactory.eINSTANCE.createSystemAnalysisRealization();
      _logicalArchitecture.getOwnedSystemAnalysisRealizations().add(ctxArchitectureRealisation);
      ctxArchitectureRealisation.setSourceElement(_logicalArchitecture);
      ctxArchitectureRealisation.setTargetElement(_ctxArchitecture);
    }

    // Builds the capabilities realizations structure skeleton.
    CapabilityRealizationPkg capaRealisationPkg = LaFactory.eINSTANCE
        .createCapabilityRealizationPkg(NamingConstants.CreateCommonCmd_capability_realisation_pkg_name);
    _logicalArchitecture.setOwnedAbstractCapabilityPkg(capaRealisationPkg);

  }

  /**
   * Gets the logical architecture.
   * 
   * @return The logical architecture.
   */
  public LogicalArchitecture getLogicalArchitecture() {
    return _logicalArchitecture;
  }

  /**
   * Gets the logical function.
   * 
   * @return The logical function.
   */
  public LogicalFunction getLogicalFunction() {
    return _logicalFunction;
  }

  /**
   * Gets the logical component.
   * 
   * @return The logical component.
   */
  public LogicalComponent getLogicalComponent() {
    return _rootComponent;
  }

  /**
   * @see org.polarsys.capella.common.ef.command.AbstractCommand#getName()
   */
  @Override
  public String getName() {
    return Messages.getString("capella.logical_archi.create.cmd"); //$NON-NLS-1$
  }
}
