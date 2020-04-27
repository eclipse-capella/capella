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
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.oa.RolePkg;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.model.skeleton.Messages;

/**
 * The command allowing to create the operational analysis structure skeleton.
 */
public class CreateOpAnalysisCmd extends AbstractReadWriteCommand {
  // The architecture name.
  private String _architectureName;
  // The operational analysis.
  private OperationalAnalysis _operationalAnalysis;
  // The root operational activity.
  private OperationalActivity _rootOperationalActivity;
  // The system engineering.
  private SystemEngineering _systemEng;

  /**
   * Constructs the command to create an operational analysis structure skeleton.
   * 
   * @param systemEng_p
   *          The parent system engineering.
   * @param architectureName_p
   *          The architecture name.
   */
  public CreateOpAnalysisCmd(SystemEngineering systemEng_p, String architectureName_p) {
    _systemEng = systemEng_p;
    _architectureName = architectureName_p;
  }

  public CreateOpAnalysisCmd(SystemEngineering systemEng_p, String architectureName_p, OperationalAnalysis createdElement) {
    this(systemEng_p, architectureName_p);
    _operationalAnalysis = createdElement;
  }
  
  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {
    if (_operationalAnalysis == null) {
      // 1 - Builds the operational analysis root element.
      _operationalAnalysis = OaFactory.eINSTANCE.createOperationalAnalysis();
      _systemEng.getOwnedArchitectures().add(_operationalAnalysis);
    }
    _operationalAnalysis.setName(_architectureName);

    // Builds the operational activities structure skeleton.
    OperationalActivityPkg activitiesPkg = OaFactory.eINSTANCE
        .createOperationalActivityPkg(NamingConstants.CreateOpAnalysisCmd_operationalActivities_pkg_name);
    _operationalAnalysis.setOwnedFunctionPkg(activitiesPkg);

    _rootOperationalActivity = OaFactory.eINSTANCE
        .createOperationalActivity(NamingConstants.CreateOpAnalysisCmd_operationalActivity_root_name);
    activitiesPkg.getOwnedOperationalActivities().add(_rootOperationalActivity);

    // Builds the operational capabilities structure skeleton.
    OperationalCapabilityPkg capabilitiesPkg = OaFactory.eINSTANCE
        .createOperationalCapabilityPkg(NamingConstants.CreateOpAnalysisCmd_operationalCapabilities_pkg_name);
    _operationalAnalysis.setOwnedAbstractCapabilityPkg(capabilitiesPkg);

    // Builds the interfaces structure skeleton.
    InterfacePkg interfacesPkg = CsFactory.eINSTANCE
        .createInterfacePkg(NamingConstants.CreateCommonCmd_interfaces_pkg_name);
    _operationalAnalysis.setOwnedInterfacePkg(interfacesPkg);

    // Builds the data structure skeleton.
    DataPkg dataPkg = InformationFactory.eINSTANCE.createDataPkg(NamingConstants.CreateCommonCmd_data_pkg_name);
    _operationalAnalysis.setOwnedDataPkg(dataPkg);

    // Builds the roles structure skeleton.
    RolePkg rolesPkg = OaFactory.eINSTANCE.createRolePkg(NamingConstants.CreateOpAnalysisCmd_roles_pkg_name);
    _operationalAnalysis.setOwnedRolePkg(rolesPkg);

    // Builds the entities structure skeleton.
    EntityPkg entitiesPkg = OaFactory.eINSTANCE
        .createEntityPkg(NamingConstants.CreateOpAnalysisCmd_operationalEntities_pkg_name);
    _operationalAnalysis.setOwnedEntityPkg(entitiesPkg);

  }

  /**
   * @see org.polarsys.capella.common.ef.command.AbstractCommand#getName()
   */
  @Override
  public String getName() {
    return Messages.getString("capella.op_analysis.create.cmd"); //$NON-NLS-1$
  }

  /**
   * Gets the operational analysis structure skeleton.
   * 
   * @return The operational analysis structure skeleton.
   */
  public OperationalAnalysis getOperationalAnalysis() {
    return _operationalAnalysis;
  }

  /**
   * Gets the root operational activity.
   * 
   * @return The root operational activity.
   */
  public OperationalActivity getRootOperationalActivity() {
    return _rootOperationalActivity;
  }
}
