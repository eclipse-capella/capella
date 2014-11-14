/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.model.skeleton.impl.cmd;

import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.LogicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.LogicalComponentRealization;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalContext;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.model.skeleton.Messages;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 * The command allowing to create the physical architecture structure skeleton.
 */
public class CreatePhysicalArchiCmd extends AbstractReadWriteCommand {
  // The architecture name.
  private String _architectureName;
  // The physical architecture.
  private PhysicalArchitecture _physicalArchitecture;
  // The logical function.
  private LogicalFunction _logicalFunction;
  // The logical component.
  private LogicalComponent _logicalComponent;
  // The root PhysicalComponent
  private PhysicalComponent _physicalComponent;

  // The logical architecture.
  private LogicalArchitecture _logicalArchitecture;
  // The system engineering.
  private SystemEngineering _systemEng;

  /**
   * Constructs the command allowing to create the physical architecture structure skeleton.
   * @param systemEng_p The parent system engineering.
   * @param architectureName_p The architecture name.
   * @param logicalArchitecture_p The logical architecture.
   * @param logicalComponent_p The logical component.
   * @param logicalFunction_p The logical function.
   */
  public CreatePhysicalArchiCmd(SystemEngineering systemEng_p, String architectureName_p, LogicalArchitecture logicalArchitecture_p,
      LogicalComponent logicalComponent_p, LogicalFunction logicalFunction_p) {
    _architectureName = architectureName_p;
    _logicalFunction = logicalFunction_p;
    _logicalComponent = logicalComponent_p;
    _logicalArchitecture = logicalArchitecture_p;
    _systemEng = systemEng_p;
  }

  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {
    // Builds the physical architecture root element.
    _physicalArchitecture = PaFactory.eINSTANCE.createPhysicalArchitecture(_architectureName);

    // Builds the physical functions structure skeleton.
    PhysicalFunctionPkg physicalFunctionPkg =
        PaFactory.eINSTANCE.createPhysicalFunctionPkg(NamingConstants.CreatePhysicalArchCmd_physicalFunctions_pkg_name);
    _physicalArchitecture.setOwnedFunctionPkg(physicalFunctionPkg);

    PhysicalFunction physicalFunction = PaFactory.eINSTANCE.createPhysicalFunction(NamingConstants.CreatePhysicalArchCmd_physicalFunction_root_name);
    physicalFunctionPkg.getOwnedPhysicalFunctions().add(physicalFunction);

    FunctionRealization functionRealisation = FaFactory.eINSTANCE.createFunctionRealization();
    physicalFunction.getOwnedFunctionRealizations().add(functionRealisation);
    functionRealisation.setSourceElement(physicalFunction);
    if (null != _logicalFunction) {
      functionRealisation.setTargetElement(_logicalFunction);
    }

    // Builds the interfaces structure skeleton.
    InterfacePkg interfacesPkg = CsFactory.eINSTANCE.createInterfacePkg(NamingConstants.CreateCommonCmd_interfaces_pkg_name);
    _physicalArchitecture.setOwnedInterfacePkg(interfacesPkg);


    // Builds the data structure skeleton.
    DataPkg dataPkg = InformationFactory.eINSTANCE.createDataPkg(NamingConstants.CreateCommonCmd_data_pkg_name);
    _physicalArchitecture.setOwnedDataPkg(dataPkg);

    // Builds the physical actors structure skeleton.
    PhysicalActorPkg actorsPkg = PaFactory.eINSTANCE.createPhysicalActorPkg(NamingConstants.CreatePhysicalArchCmd_actors_pkg_name);
    _physicalArchitecture.setOwnedPhysicalActorPkg(actorsPkg);

    // Builds the physical components structure skeleton.
    _physicalComponent = PaFactory.eINSTANCE.createPhysicalComponent(NamingConstants.CreatePhysicalArchCmd_physicalComponent_name);
    _physicalArchitecture.setOwnedPhysicalComponent(_physicalComponent);

    // Builds the logical context structure skeleton.
    PhysicalContext physicalContext = PaFactory.eINSTANCE.createPhysicalContext(NamingConstants.CreatePhysicalArchCmd_physicalContext_name);
    _physicalArchitecture.setOwnedPhysicalContext(physicalContext);

    Part physicalRootPart = CsFactory.eINSTANCE.createPart(_physicalComponent.getName());
    physicalContext.getOwnedFeatures().add(physicalRootPart);
    physicalRootPart.setAbstractType(_physicalComponent);

    // Build the logical component realization.
    LogicalComponentRealization logicalComponentRealisation = PaFactory.eINSTANCE.createLogicalComponentRealization();
    _physicalComponent.getOwnedLogicalComponentRealizations().add(logicalComponentRealisation);
    logicalComponentRealisation.setSourceElement(_physicalComponent);
    if (null != _logicalComponent) {
      logicalComponentRealisation.setTargetElement(_logicalComponent);
    }

    // Build the logical architecture realization.
    LogicalArchitectureRealization logicalArchiRealisation = PaFactory.eINSTANCE.createLogicalArchitectureRealization();
    _physicalArchitecture.getOwnedLogicalArchitectureRealizations().add(logicalArchiRealisation);
    logicalArchiRealisation.setSourceElement(_physicalArchitecture);
    if (null != _logicalArchitecture) {
      logicalArchiRealisation.setTargetElement(_logicalArchitecture);
    }

    // Builds the capabilities realizations structure skeleton.
    CapabilityRealizationPkg capaRealisationPkg =
        LaFactory.eINSTANCE.createCapabilityRealizationPkg(NamingConstants.CreateCommonCmd_capability_realisation_pkg_name);
    _physicalArchitecture.setOwnedAbstractCapabilityPkg(capaRealisationPkg);

    // Attaches the physical architecture to its parent system engineering.
    _systemEng.getOwnedArchitectures().add(_physicalArchitecture);
  }

  /**
   * Gets the physical architecture.
   * @return The physical architecture.
   */
  public PhysicalArchitecture getPhysicalArchitecture() {
    return _physicalArchitecture;
  }

  /**
   * @return the logicalComponent
   */
  public PhysicalComponent getPhysicalComponent() {
    return _physicalComponent;
  }
  
  /**
   * @see org.polarsys.capella.common.ef.command.AbstractCommand#getName()
   */
  @Override
  public String getName() {
    return Messages.getString("capella.physical_archi.create.cmd"); //$NON-NLS-1$
  }

}
