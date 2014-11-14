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
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerFactory;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;

/**
 */
public class SharedStructureHelper {

  /**
   * @param editingDomain_p
   * @param systemEngineering_p
   * @return
   */
  public static Command getSharedCreationCmd(final EditingDomain editingDomain_p, final SystemEngineering systemEngineering_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the SharedPkg.
    final Command createSharedPkgCmd = CreateChildCommand.create(editingDomain_p, systemEngineering_p, new CommandParameter(systemEngineering_p,
      CapellamodellerPackage.Literals.PROJECT__OWNED_MODEL_ROOTS, CapellamodellerFactory.eINSTANCE.createProject("Shared Pkg")), Collections.EMPTY_LIST); //$NON-NLS-1$
    cmd.append(createSharedPkgCmd);

    Command createSharedPkgContentCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createSharedPkgCmd.getResult();
        if (res.size() == 1) {
          Object createdSharedPkg = res.iterator().next();
          if (createdSharedPkg instanceof SharedPkg) {
            return getSharedPkgCreationCmd(editingDomain_p, systemEngineering_p, (SharedPkg) createdSharedPkg);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createSharedPkgContentCmd);

    return cmd;
  }

  /**
   * @param editingDomain_p
   * @param systemEngineering_p
   * @param systemAnalysis_p
   * @return
   */
  public static Command getSharedPkgCreationCmd(final EditingDomain editingDomain_p, SystemEngineering systemEngineering_p, SharedPkg sharedPkg_p) {
    CompoundCommand cmd = new CompoundCommand();

    cmd.append(CommonStructureHelper.getLinkCreationCmd(editingDomain_p,
      CapellacorePackage.Literals.REUSE_LINK,
      CapellacorePackage.Literals.REUSER_STRUCTURE__OWNED_REUSE_LINKS,
      CapellacorePackage.Literals.REUSE_LINK__REUSER, systemEngineering_p,
      CapellacorePackage.Literals.REUSE_LINK__REUSED, sharedPkg_p));

    cmd.append(CommonStructureHelper.getDataPkgCreationCmd(editingDomain_p, sharedPkg_p));

    return cmd;
  }
}
