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
package org.polarsys.capella.core.model.skeleton.impl.cmd;

import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.ComponentRealization;
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
import org.polarsys.capella.core.data.pa.LogicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.model.skeleton.Messages;

/**
 * The command allowing to create the physical architecture structure skeleton.
 */
public class CreatePhysicalArchiCmd extends AbstractReadWriteCommand {
  // The architecture name.
  private String architectureName;
  // The physical architecture.
  private PhysicalArchitecture physicalArchitecture;
  // The logical function.
  private LogicalFunction logicalFunction;
  // The logical component.
  private LogicalComponent logicalComponent;
  // The root PhysicalComponent
  private PhysicalComponent physicalComponent;

  // The logical architecture.
  private LogicalArchitecture logicalArchitecture;
  // The system engineering.
  private SystemEngineering systemEng;

  /**
   * Constructs the command allowing to create the physical architecture structure skeleton.
   * 
   * @param systemEng
   *          The parent system engineering.
   * @param architectureName
   *          The architecture name.
   * @param logicalArchitecture
   *          The logical architecture.
   * @param logicalComponent
   *          The logical component.
   * @param logicalFunction
   *          The logical function.
   */
  public CreatePhysicalArchiCmd(SystemEngineering systemEng, String architectureName,
      LogicalArchitecture logicalArchitecture, LogicalComponent logicalComponent, LogicalFunction logicalFunction) {
    this.architectureName = architectureName;
    this.logicalFunction = logicalFunction;
    this.logicalComponent = logicalComponent;
    this.logicalArchitecture = logicalArchitecture;
    this.systemEng = systemEng;
  }

  public CreatePhysicalArchiCmd(SystemEngineering systemEng, String architectureName,
      LogicalArchitecture logicalArchitecture, LogicalComponent logicalComponent, LogicalFunction logicalFunction,
      PhysicalArchitecture createdElement) {
    this(systemEng, architectureName, logicalArchitecture, logicalComponent, logicalFunction);
    this.physicalArchitecture = createdElement;
  }

  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {
    if (physicalArchitecture == null) {
      // Builds the physical architecture root element.
      physicalArchitecture = PaFactory.eINSTANCE.createPhysicalArchitecture();
      systemEng.getOwnedArchitectures().add(physicalArchitecture);
    }
    physicalArchitecture.setName(architectureName);

    // Builds the physical functions structure skeleton.
    PhysicalFunctionPkg physicalFunctionPkg = PaFactory.eINSTANCE
        .createPhysicalFunctionPkg(NamingConstants.CreatePhysicalArchCmd_physicalFunctions_pkg_name);
    physicalArchitecture.setOwnedFunctionPkg(physicalFunctionPkg);

    PhysicalFunction physicalFunction = PaFactory.eINSTANCE
        .createPhysicalFunction(NamingConstants.CreatePhysicalArchCmd_physicalFunction_root_name);
    physicalFunctionPkg.getOwnedPhysicalFunctions().add(physicalFunction);

    if (null != logicalFunction) {
      FunctionRealization functionRealisation = FaFactory.eINSTANCE.createFunctionRealization();
      physicalFunction.getOwnedFunctionRealizations().add(functionRealisation);
      functionRealisation.setSourceElement(physicalFunction);
      functionRealisation.setTargetElement(logicalFunction);
    }

    // Builds the interfaces structure skeleton.
    InterfacePkg interfacesPkg = CsFactory.eINSTANCE
        .createInterfacePkg(NamingConstants.CreateCommonCmd_interfaces_pkg_name);
    physicalArchitecture.setOwnedInterfacePkg(interfacesPkg);

    // Builds the data structure skeleton.
    DataPkg dataPkg = InformationFactory.eINSTANCE.createDataPkg(NamingConstants.CreateCommonCmd_data_pkg_name);
    physicalArchitecture.setOwnedDataPkg(dataPkg);

    // Builds the physical actors structure skeleton.
    PhysicalComponentPkg componentPkg = PaFactory.eINSTANCE
        .createPhysicalComponentPkg(NamingConstants.CreatePhysicalArchCmd_actors_pkg_name);
    physicalArchitecture.setOwnedPhysicalComponentPkg(componentPkg);

    // Builds the physical components structure skeleton.
    physicalComponent = PaFactory.eINSTANCE
        .createPhysicalComponent(NamingConstants.CreatePhysicalArchCmd_physicalComponent_name);
    componentPkg.getOwnedPhysicalComponents().add(physicalComponent);

    Part physicalRootPart = CsFactory.eINSTANCE.createPart(physicalComponent.getName());
    componentPkg.getOwnedParts().add(physicalRootPart);
    physicalRootPart.setAbstractType(physicalComponent);

    // Build the logical component realization.
    if (null != logicalComponent) {
      ComponentRealization logicalComponentRealisation = CsFactory.eINSTANCE.createComponentRealization();
      physicalComponent.getOwnedComponentRealizations().add(logicalComponentRealisation);
      logicalComponentRealisation.setSourceElement(physicalComponent);
      logicalComponentRealisation.setTargetElement(logicalComponent);
    }

    // Build the logical architecture realization.
    if (null != logicalArchitecture) {
      LogicalArchitectureRealization logicalArchiRealisation = PaFactory.eINSTANCE
          .createLogicalArchitectureRealization();
      physicalArchitecture.getOwnedLogicalArchitectureRealizations().add(logicalArchiRealisation);
      logicalArchiRealisation.setSourceElement(physicalArchitecture);
      logicalArchiRealisation.setTargetElement(logicalArchitecture);
    }

    // Builds the capabilities realizations structure skeleton.
    CapabilityRealizationPkg capaRealisationPkg = LaFactory.eINSTANCE
        .createCapabilityRealizationPkg(NamingConstants.CreateCommonCmd_capability_realisation_pkg_name);
    physicalArchitecture.setOwnedAbstractCapabilityPkg(capaRealisationPkg);

  }

  /**
   * Gets the physical architecture.
   * 
   * @return The physical architecture.
   */
  public PhysicalArchitecture getPhysicalArchitecture() {
    return physicalArchitecture;
  }

  /**
   * @return the logicalComponent
   */
  public PhysicalComponent getPhysicalComponent() {
    return physicalComponent;
  }

  /**
   * @see org.polarsys.capella.common.ef.command.AbstractCommand#getName()
   */
  @Override
  public String getName() {
    return Messages.getString("capella.physical_archi.create.cmd"); //$NON-NLS-1$
  }

}
