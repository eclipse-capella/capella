/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.table.ju.requirements;

import org.eclipse.sirius.table.metamodel.table.DTable;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;

public class RequirementsTableTestCase extends RequirementsTableTestFramework {

  @Override
  public void test() throws Exception {
    init();
    DTable table = createTable(context, rootReqPkgId, IToolNameConstants.REQUIREMENTS_DIAGRAM_NAME);

    /**
     * Create a new System User Requirement SUR1 in Root Req Pkg
     * <P>
     * New System User Requirement is created and added under Root Req Pkg
     */
    _systemUserReq = createRequirementSUR(table, _rootReqPkg);

    /**
     * Create a new System Functional Requirement SFR in Root Req Pkg
     * <P>
     * New System Functional Requirement is created and added under Root Req Pkg
     */
    _systemFuncReq = createRequirementSFR(table, _rootReqPkg);

    /**
     * Create a new System Functional Interface Requirement SFIR in Root Req Pkg
     * <P>
     * New System User Requirement is created and added under Root Req Pkg
     */
    createRequirementSFIR(table, _rootReqPkg);

    /**
     * Create a new System Non Functional Requirement SNFR in Root Req Pkg
     * <P>
     * New System Non Functional Requirement is created and added under Root Req Pkg
     */
    createRequirementSNFR(table, _rootReqPkg);

    /**
     * Create a new System Non Functional Interface Requirement SNFIR in Root Req Pkg
     * <P>
     * New System Non Functional Interface Requirement is created and added under Root Req Pkg
     */
    createRequirementSNFIR(table, _rootReqPkg);

    /**
     * Create a new Sub Req Pkg in Root Req Pkg
     * <P>
     * New Sub Req Pkg is created and added under Root Req Pkg
     */
    createRequirementREQPKG(table, _rootReqPkg);

    /**
     * Delete the System User Requirement SUR in Root Req Pkg
     * <P>
     * System User Requirement is deleted from Root Req Pkg
     */
    deleteRequirement(table, _systemUserReq);

    /**
     * Delete the System Functional Requirement SFR in Root Req Pkg
     * <P>
     * System Functional Requirement SFRt is deleted from Root Req Pkg
     */
    deleteRequirement(table, _systemFuncReq);
  }
}
