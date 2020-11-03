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

import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemKind;
import org.polarsys.capella.core.data.epbs.ConfigurationItemPkg;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsFactory;
import org.polarsys.capella.core.data.epbs.PhysicalArchitectureRealization;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.model.skeleton.Messages;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 * The command allowing to create the EPBS architecture structure skeleton.
 */
public class CreateEPBSArchiCmd extends AbstractReadWriteCommand {
  // The architecture name.
  private String architectureName;
  // The EPBS architecture.
  private EPBSArchitecture epbsArchitecture;
  // The physical architecture.
  private PhysicalArchitecture physicalArchitecture;
  // The system engineering.
  private SystemEngineering systemEng;
  private PhysicalComponent pcRoot;

  /**
   * Constructs the command allowing to create the EPBS architecture structure skeleton.
   * 
   * @param systemEng
   *          The system engineering.
   * @param architectureName
   *          The architecture name.
   * @param physicalArchitecture
   *          The physical architecture.
   */
  public CreateEPBSArchiCmd(SystemEngineering systemEng, String architectureName,
      PhysicalArchitecture physicalArchitecture, PhysicalComponent physicalComp) {
    this.architectureName = architectureName;
    this.physicalArchitecture = physicalArchitecture;
    this.systemEng = systemEng;
    this.pcRoot = physicalComp;
  }

  public CreateEPBSArchiCmd(SystemEngineering systemEng, String architectureName,
      PhysicalArchitecture physicalArchitecture, PhysicalComponent physicalComp, EPBSArchitecture createdElement) {
    this(systemEng, architectureName, physicalArchitecture, physicalComp);
    epbsArchitecture = createdElement;
  }

  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {
    if (epbsArchitecture == null) {
      // Builds the root element of the EPBS architecture.
      epbsArchitecture = EpbsFactory.eINSTANCE.createEPBSArchitecture();
      systemEng.getOwnedArchitectures().add(epbsArchitecture);
    }
    epbsArchitecture.setName(architectureName);

    ConfigurationItemPkg cipkg = EpbsFactory.eINSTANCE
        .createConfigurationItemPkg(NamingConstants.CreateEPBSArchCmd_configurationItemPkg_name);
    epbsArchitecture.setOwnedConfigurationItemPkg(cipkg);

    ConfigurationItem ci = EpbsFactory.eINSTANCE
        .createConfigurationItem(NamingConstants.CreateEPBSArchCmd_configurationItem_name);
    ci.setKind(ConfigurationItemKind.SYSTEM_CI);
    cipkg.getOwnedConfigurationItems().add(ci);

    Part epbsRootPart = CsFactory.eINSTANCE.createPart(ci.getName());
    cipkg.getOwnedParts().add(epbsRootPart);
    epbsRootPart.setAbstractType(ci);

    if (null != pcRoot) {
      // Build the physical component realization.
      PhysicalArtifactRealization physicalComponentRealisation = EpbsFactory.eINSTANCE
          .createPhysicalArtifactRealization();
      ci.getOwnedPhysicalArtifactRealizations().add(physicalComponentRealisation);
      physicalComponentRealisation.setSourceElement(ci);
      physicalComponentRealisation.setTargetElement(pcRoot);
    }

    if (null != physicalArchitecture) {
      PhysicalArchitectureRealization physicalArchitectureAlloc = EpbsFactory.eINSTANCE
          .createPhysicalArchitectureRealization();
      epbsArchitecture.getOwnedPhysicalArchitectureRealizations().add(physicalArchitectureAlloc);
      physicalArchitectureAlloc.setSourceElement(epbsArchitecture);
      physicalArchitectureAlloc.setTargetElement(physicalArchitecture);
    }

    // Builds the capabilities realizations structure skeleton.
    CapabilityRealizationPkg capaRealisationPkg = LaFactory.eINSTANCE
        .createCapabilityRealizationPkg(NamingConstants.CreateCommonCmd_capability_realisation_pkg_name);
    epbsArchitecture.setOwnedAbstractCapabilityPkg(capaRealisationPkg);

  }

  /**
   * Gets the EPBS architecture.
   * 
   * @return The EPBS architecture.
   */
  public EPBSArchitecture getEPBSArchitecture() {
    return epbsArchitecture;
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
   * @see org.polarsys.capella.common.ef.command.AbstractCommand#getName()
   */
  @Override
  public String getName() {
    return Messages.getString("capella.epbs_archi.create.cmd"); //$NON-NLS-1$
  }
}
