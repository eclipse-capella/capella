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
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemKind;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSContext;
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
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 * The command allowing to create the EPBS architecture structure skeleton.
 */
public class CreateEPBSArchiCmd extends AbstractReadWriteCommand {
  // The architecture name.
  private String _architectureName;
  // The EPBS architecture.
  private EPBSArchitecture _epbsArchitecture;
  // The physical architecture.
  private PhysicalArchitecture _physicalArchitecture;
  // The system engineering.
  private SystemEngineering _systemEng;
  private PhysicalComponent _pcRoot;

  /**
   * Constructs the command allowing to create the EPBS architecture structure skeleton.
   * @param systemEng_p The system engineering.
   * @param architectureName_p The architecture name.
   * @param physicalArchitecture_p The physical architecture.
   */
  public CreateEPBSArchiCmd(SystemEngineering systemEng_p, String architectureName_p, PhysicalArchitecture physicalArchitecture_p, PhysicalComponent physicalComp_p) {
    _architectureName = architectureName_p;
    _physicalArchitecture = physicalArchitecture_p;
    _systemEng = systemEng_p;
    _pcRoot = physicalComp_p;
  }

  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {
    // Builds the root element of the EPBS architecture.
    _epbsArchitecture = EpbsFactory.eINSTANCE.createEPBSArchitecture(_architectureName);

    ConfigurationItem ci = EpbsFactory.eINSTANCE.createConfigurationItem(NamingConstants.CreateEPBSArchCmd_configurationItem_name);
    ci.setKind(ConfigurationItemKind.SYSTEM_CI);
    _epbsArchitecture.setOwnedConfigurationItem(ci);

    // Builds the epbs context structure skeleton.
    EPBSContext epbsContext = EpbsFactory.eINSTANCE.createEPBSContext(NamingConstants.CreateEPBSArchCmd_epbsContext_name);
    _epbsArchitecture.setOwnedEPBSContext(epbsContext);

    Part epbsRootPart = CsFactory.eINSTANCE.createPart(ci.getName());
    epbsContext.getOwnedFeatures().add(epbsRootPart);
    epbsRootPart.setAbstractType(ci);
    
    // Build the physical component realization.
    PhysicalArtifactRealization physicalComponentRealisation = EpbsFactory.eINSTANCE.createPhysicalArtifactRealization();
    ci.getOwnedPhysicalArtifactRealizations().add(physicalComponentRealisation);
    physicalComponentRealisation.setSourceElement(ci);
    if (null != _pcRoot) {
      physicalComponentRealisation.setTargetElement(_pcRoot);
    }

    PhysicalArchitectureRealization physicalArchitectureAlloc = EpbsFactory.eINSTANCE.createPhysicalArchitectureRealization();
    _epbsArchitecture.getOwnedPhysicalArchitectureRealizations().add(physicalArchitectureAlloc);
    physicalArchitectureAlloc.setSourceElement(_epbsArchitecture);
    if (null != _physicalArchitecture) {
      physicalArchitectureAlloc.setTargetElement(_physicalArchitecture);
    }

    // Builds the capabilities realizations structure skeleton.
    CapabilityRealizationPkg capaRealisationPkg =
                                                  LaFactory.eINSTANCE.createCapabilityRealizationPkg(NamingConstants.CreateCommonCmd_capability_realisation_pkg_name);
    _epbsArchitecture.setOwnedAbstractCapabilityPkg(capaRealisationPkg);
    
    // Attaches the EPBS architecture to its parent system engineering.
    _systemEng.getOwnedArchitectures().add(_epbsArchitecture);
  }

  /**
   * Gets the EPBS architecture.
   * @return The EPBS architecture.
   */
  public EPBSArchitecture getEPBSArchitecture() {
    return _epbsArchitecture;
  }

  /**
   * Gets the physical architecture.
   * @return The physical architecture.
   */
  public PhysicalArchitecture getPhysicalArchitecture() {
    return _physicalArchitecture;
  }
  
  /**
   * @see org.polarsys.capella.common.tig.ef.command.AbstractCommand#getName()
   */
  @Override
  public String getName() {
    return Messages.getString("capella.epbs_archi.create.cmd"); //$NON-NLS-1$
  }
}
