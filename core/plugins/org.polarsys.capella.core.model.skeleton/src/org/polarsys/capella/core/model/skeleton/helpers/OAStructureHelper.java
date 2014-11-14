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
package org.polarsys.capella.core.model.skeleton.helpers;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;

/**
 */
public class OAStructureHelper {

  /**
   * @param editingDomain_p
   * @param systempEngineering_p
   * @return
   */
  public static Command getOperationalAnalysisCreationCmd(final EditingDomain editingDomain_p, SystemEngineering systempEngineering_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the OperationalAnalysis.
    final Command createOperationalAnalysisCmd = CreateChildCommand.create(editingDomain_p, systempEngineering_p, new CommandParameter(systempEngineering_p,
      CapellacorePackage.Literals.ABSTRACT_MODELLING_STRUCTURE__OWNED_ARCHITECTURES, OaFactory.eINSTANCE.createOperationalAnalysis(NamingConstants.CreateOpAnalysisCmd_name)), Collections.EMPTY_LIST);
    cmd.append(createOperationalAnalysisCmd);

    Command createOperationalAnalysisContentCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createOperationalAnalysisCmd.getResult();
        if (res.size() == 1) {
          Object createdOperationalAnalysis = res.iterator().next();
          if (createdOperationalAnalysis instanceof OperationalAnalysis) {
            return getOperationalAnalysisCreationCmd(editingDomain_p, (OperationalAnalysis) createdOperationalAnalysis);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createOperationalAnalysisContentCmd);

    return cmd;
  }

  /**
   * @param editingDomain_p
   * @param operationalAnalysis_p
   * @return
   */
  public static Command getOperationalAnalysisCreationCmd(final EditingDomain editingDomain_p, OperationalAnalysis operationalAnalysis_p) {
    CompoundCommand cmd = new CompoundCommand();

    cmd.append(CommonStructureHelper.getRequirementPkgCreationCmd(editingDomain_p, operationalAnalysis_p));
    cmd.append(getOperationalActivityPkgCreationCmd(editingDomain_p, operationalAnalysis_p));
    cmd.append(CommonStructureHelper.getDataPkgCreationCmd(editingDomain_p, operationalAnalysis_p));
    cmd.append(getOperationalCapabilityPkgCreationCmd(editingDomain_p, operationalAnalysis_p));
    cmd.append(CommonStructureHelper.getInterfacePkgCreationCmd(editingDomain_p, operationalAnalysis_p));
    cmd.append(getEntityPkgCreationCmd(editingDomain_p, operationalAnalysis_p));
    cmd.append(getRolePkgCreationCmd(editingDomain_p, operationalAnalysis_p));

    return cmd;
  }

  /**
   * @param editingDomain_p
   * @param operationalAnalysis_p
   * @return
   */
  public static Command getEntityPkgCreationCmd(final EditingDomain editingDomain_p, OperationalAnalysis operationalAnalysis_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the OperationalContext.
    final Command createOperationalContextCmd = CreateChildCommand.create(editingDomain_p, operationalAnalysis_p, new CommandParameter(operationalAnalysis_p,
      OaPackage.Literals.OPERATIONAL_ANALYSIS__OWNED_OPERATIONAL_CONTEXT, OaFactory.eINSTANCE.createOperationalContext(NamingConstants.CreateOpAnalysisCmd_operational_context_name)), Collections.EMPTY_LIST);
    cmd.append(createOperationalContextCmd);

    // Creates the EntityPkg.
    final Command createEntityPkgCmd = CreateChildCommand.create(editingDomain_p, operationalAnalysis_p, new CommandParameter(operationalAnalysis_p,
      OaPackage.Literals.OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG, OaFactory.eINSTANCE.createEntityPkg(NamingConstants.CreateOpAnalysisCmd_operationalEntities_pkg_name)), Collections.EMPTY_LIST);
    cmd.append(createEntityPkgCmd);
    
    return cmd;
  }

  /**
   * Creates an OperationalCapabilityPkg.
   * 
   * @param editingDomain_p
   * @param operationalAnalysis_p
   * @return
   */
  public static Command getOperationalCapabilityPkgCreationCmd(EditingDomain editingDomain_p, OperationalAnalysis operationalAnalysis_p) {
    return CreateChildCommand.create(editingDomain_p, operationalAnalysis_p, new CommandParameter(operationalAnalysis_p,
      CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG, OaFactory.eINSTANCE.createOperationalCapabilityPkg(NamingConstants.CreateOpAnalysisCmd_operationalCapabilities_pkg_name)), Collections.EMPTY_LIST);
  }

  /**
   * Creates a RolePkg.
   * 
   * @param editingDomain_p
   * @param operationalAnalysis_p
   * @return
   */
  public static Command getRolePkgCreationCmd(EditingDomain editingDomain_p, OperationalAnalysis operationalAnalysis_p) {
    return CreateChildCommand.create(editingDomain_p, operationalAnalysis_p, new CommandParameter(operationalAnalysis_p,
      OaPackage.Literals.OPERATIONAL_ANALYSIS__OWNED_ROLE_PKG, OaFactory.eINSTANCE.createRolePkg(NamingConstants.CreateOpAnalysisCmd_roles_pkg_name)), Collections.EMPTY_LIST);
  }

  /**
   * @param editingDomain_p
   * @param operationalAnalysis_p
   * @return
   */
  public static Command getOperationalActivityPkgCreationCmd(final EditingDomain editingDomain_p, OperationalAnalysis operationalAnalysis_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the OperationalActivityPkg.
    final Command createOperationalActivityPkgCmd = CreateChildCommand.create(editingDomain_p, operationalAnalysis_p, new CommandParameter(operationalAnalysis_p,
      FaPackage.Literals.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTION_PKG, OaFactory.eINSTANCE.createOperationalActivityPkg(NamingConstants.CreateOpAnalysisCmd_operationalActivities_pkg_name)), Collections.EMPTY_LIST);
    cmd.append(createOperationalActivityPkgCmd);

    // Creates the OperationalActivity root.
    Command createOperationalActivityCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createOperationalActivityPkgCmd.getResult();
        if (res.size() == 1) {
          Object createdOperationalActivityPkg = res.iterator().next();
          if (createdOperationalActivityPkg instanceof EObject) {
            return CreateChildCommand.create(editingDomain_p, createdOperationalActivityPkg, new CommandParameter(createdOperationalActivityPkg,
              OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITIES, OaFactory.eINSTANCE.createOperationalActivity(NamingConstants.CreateOpAnalysisCmd_operationalActivity_root_name)), Collections.EMPTY_LIST);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createOperationalActivityCmd);

    return cmd;
  }
}
