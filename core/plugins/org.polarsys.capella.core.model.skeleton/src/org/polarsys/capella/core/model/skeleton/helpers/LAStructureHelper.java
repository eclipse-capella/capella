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
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class LAStructureHelper {

  /**
   * @param systempEngineering_p
   * @return
   */
  public static SystemAnalysis getSystemAnalysis(SystemEngineering systemEngineering_p) {
    if (systemEngineering_p != null) {
      for (ModellingArchitecture arch : systemEngineering_p.getOwnedArchitectures()) {
        if (arch instanceof SystemAnalysis) {
          return (SystemAnalysis) arch;
        }
      }
    }
    return null;
  }

  /**
   * @param systemEngineering_p
   * @return
   */
  public static SystemFunction getRootSystemFunction(SystemEngineering systemEngineering_p) {
    SystemAnalysis systemAnalysis = getSystemAnalysis(systemEngineering_p);
    if (systemAnalysis != null) {
      SystemFunctionPkg functionPkg = (SystemFunctionPkg) systemAnalysis.getOwnedFunctionPkg();
      if (functionPkg != null) {
        if (!functionPkg.getOwnedSystemFunctions().isEmpty()) {
          return functionPkg.getOwnedSystemFunctions().get(0);
        }
      }
    }
    return null;
  }

  /**
   * @param systemEngineering_p
   * @return
   */
  public static System getRootSystem(SystemEngineering systemEngineering_p) {
    SystemAnalysis systemAnalysis = getSystemAnalysis(systemEngineering_p);
    if (systemAnalysis != null) {
      return systemAnalysis.getOwnedSystem();
    }
    return null;
  }

  /**
   * @param editingDomain_p
   * @param systemEngineering_p
   * @return
   */
  public static Command getLogicalArchitectureCreationCmd(final EditingDomain editingDomain_p, final SystemEngineering systemEngineering_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the LogicalArchitecture.
    final Command createLogicalArchitectureCmd = CreateChildCommand.create(editingDomain_p, systemEngineering_p, new CommandParameter(systemEngineering_p,
      CapellacorePackage.Literals.ABSTRACT_MODELLING_STRUCTURE__OWNED_ARCHITECTURES, LaFactory.eINSTANCE.createLogicalArchitecture(NamingConstants.CreateLogicalArchCmd_name)), Collections.EMPTY_LIST);
    cmd.append(createLogicalArchitectureCmd);

    Command createLogicalArchitectureContentCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createLogicalArchitectureCmd.getResult();
        if (res.size() == 1) {
          Object createdLogicalArchitecture = res.iterator().next();
          if (createdLogicalArchitecture instanceof LogicalArchitecture) {
            return getLogicalArchitectureCreationCmd(editingDomain_p, systemEngineering_p, (LogicalArchitecture) createdLogicalArchitecture);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createLogicalArchitectureContentCmd);

    return cmd;
  }

  /**
   * @param editingDomain_p
   * @param systemEngineering_p
   * @param logicalArchitecture_p
   * @return
   */
  public static Command getLogicalArchitectureCreationCmd(final EditingDomain editingDomain_p, SystemEngineering systemEngineering_p, LogicalArchitecture logicalArchitecture_p) {
    CompoundCommand cmd = new CompoundCommand();

    SystemAnalysis systemAnalysis = getSystemAnalysis(systemEngineering_p);
    cmd.append(CommonStructureHelper.getLinkCreationCmd(editingDomain_p,
      LaPackage.Literals.SYSTEM_ANALYSIS_REALIZATION,
      LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_SYSTEM_ANALYSIS_REALIZATIONS,
      ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT, logicalArchitecture_p,
      ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT, systemAnalysis));

    cmd.append(CommonStructureHelper.getRequirementPkgCreationCmd(editingDomain_p, logicalArchitecture_p));
    cmd.append(getLogicalFunctionPkgCreationCmd(editingDomain_p, systemEngineering_p, logicalArchitecture_p));
    cmd.append(CommonStructureHelper.getCapabilityRealizationPkgCreationCmd(editingDomain_p, logicalArchitecture_p));
    cmd.append(CommonStructureHelper.getInterfacePkgCreationCmd(editingDomain_p, logicalArchitecture_p));
    cmd.append(CommonStructureHelper.getDataPkgCreationCmd(editingDomain_p, logicalArchitecture_p));
    cmd.append(getLogicalActorPkgCreationCmd(editingDomain_p, logicalArchitecture_p));
    cmd.append(getRootLogicalComponentCreationCmd(editingDomain_p, systemEngineering_p, logicalArchitecture_p));

    return cmd;
  }

  /**
   * @param editingDomain_p
   * @param systemEngineering_p
   * @param logicalArchitecture_p
   * @return
   */
  public static Command getRootLogicalComponentCreationCmd(final EditingDomain editingDomain_p, final SystemEngineering systemEngineering_p, LogicalArchitecture logicalArchitecture_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the LogicalContext.
    final Command createLogicalContextCmd = CreateChildCommand.create(editingDomain_p, logicalArchitecture_p, new CommandParameter(logicalArchitecture_p,
      LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_CONTEXT, LaFactory.eINSTANCE.createLogicalContext(NamingConstants.CreateLogicalArchCmd_logicalContext_name)), Collections.EMPTY_LIST);
    cmd.append(createLogicalContextCmd);

    // Creates the Root LogicalComponent.
    final Command createLogicalComponentCmd = CreateChildCommand.create(editingDomain_p, logicalArchitecture_p, new CommandParameter(logicalArchitecture_p,
      LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT, LaFactory.eINSTANCE.createLogicalComponent(NamingConstants.CreateLogicalArchCmd_logicalComponent_name)), Collections.EMPTY_LIST);
    cmd.append(createLogicalComponentCmd);

    // Creates the SystemRealization.
    Command createSystemRealizationCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createLogicalComponentCmd.getResult();
        if (res.size() == 1) {
          Object createdLogicalComponent = res.iterator().next();
          if (createdLogicalComponent instanceof EObject) {
            System rootSystem = getRootSystem(systemEngineering_p);
            return CommonStructureHelper.getLinkCreationCmd(editingDomain_p,
              LaPackage.Literals.SYSTEM_REALIZATION,
              LaPackage.Literals.LOGICAL_COMPONENT__OWNED_SYSTEM_REALIZATIONS,
              ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT, (EObject) createdLogicalComponent,
              ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT, rootSystem);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createSystemRealizationCmd);

    // Creates a Part.
    final Command createPartCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createLogicalContextCmd.getResult();
        if (res.size() == 1) {
          Object createdContext = res.iterator().next();
          if (createdContext instanceof EObject) {
            return CreateChildCommand.create(editingDomain_p, createdContext, new CommandParameter(createdContext,
              CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES, CsFactory.eINSTANCE.createPart()), Collections.EMPTY_LIST);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createPartCmd);

    // Sets the part name.
    final Command setPartNameCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res1 = createPartCmd.getResult();
        Collection<?> res2 = createLogicalComponentCmd.getResult();
        if ((res1.size() == 1) && (res2.size() == 1)) {
          Object createdPart = res1.iterator().next();
          Object createdLogicalComponent = res2.iterator().next();
          if ((createdPart instanceof EObject) && (createdLogicalComponent instanceof EObject)) {
            return new SetCommand(editingDomain_p, (EObject) createdPart, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, ((AbstractNamedElement) createdLogicalComponent).getName());
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(setPartNameCmd);

    // Sets the part type.
    Command setPartTypeCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res1 = createPartCmd.getResult();
        Collection<?> res2 = createLogicalComponentCmd.getResult();
        if ((res1.size() == 1) && (res2.size() == 1)) {
          Object createdPart = res1.iterator().next();
          Object createdLogicalComponent = res2.iterator().next();
          if ((createdPart instanceof EObject) && (createdLogicalComponent instanceof EObject)) {
            return new SetCommand(editingDomain_p, (EObject) createdPart, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, createdLogicalComponent);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(setPartTypeCmd);

   
    return cmd;
  }

  /**
   * Creates an LogicalActorPkg.
   * 
   * @param editingDomain_p
   * @param logicalArchitecture_p
   * @return
   */
  public static Command getLogicalActorPkgCreationCmd(EditingDomain editingDomain_p, LogicalArchitecture logicalArchitecture_p) {
    return CreateChildCommand.create(editingDomain_p, logicalArchitecture_p, new CommandParameter(logicalArchitecture_p,
      LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_ACTOR_PKG, LaFactory.eINSTANCE.createLogicalActorPkg(NamingConstants.CreateLogicalArchCmd_actors_pkg_name)), Collections.EMPTY_LIST);
  }

  /**
   * @param editingDomain_p
   * @param systemEngineering_p
   * @param logicalArchitecture_p
   * @return
   */
  public static Command getLogicalFunctionPkgCreationCmd(final EditingDomain editingDomain_p, final SystemEngineering systemEngineering_p, final LogicalArchitecture logicalArchitecture_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the LogicalFunctionPkg.
    final Command createLogicalFunctionPkgCmd = CreateChildCommand.create(editingDomain_p, logicalArchitecture_p, new CommandParameter(logicalArchitecture_p,
      FaPackage.Literals.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTION_PKG, LaFactory.eINSTANCE.createLogicalFunctionPkg(NamingConstants.CreateLogicalArchCmd_logicalFunctions_pkg_name)), Collections.EMPTY_LIST);
    cmd.append(createLogicalFunctionPkgCmd);

    // Creates the LogicalFunction root.
    final Command createLogicalFunctionCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createLogicalFunctionPkgCmd.getResult();
        if (res.size() == 1) {
          Object createdLogicalFunctionPkg = res.iterator().next();
          if (createdLogicalFunctionPkg instanceof EObject) {
            return CreateChildCommand.create(editingDomain_p, createdLogicalFunctionPkg, new CommandParameter(createdLogicalFunctionPkg,
              LaPackage.Literals.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTIONS, LaFactory.eINSTANCE.createLogicalFunction(NamingConstants.CreateLogicalArchCmd_logicalFunction_root_name)), Collections.EMPTY_LIST);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createLogicalFunctionCmd);

    // Creates the SystemFunctionRealization.
    Command createSystemFunctionRealizationCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createLogicalFunctionCmd.getResult();
        if (res.size() == 1) {
          Object createdLogicalFunction = res.iterator().next();
          if (createdLogicalFunction instanceof EObject) {
            SystemFunction rootSystemFunction = getRootSystemFunction(systemEngineering_p);
            return CommonStructureHelper.getLinkCreationCmd(editingDomain_p,
              FaPackage.Literals.FUNCTION_REALIZATION,
              FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS,
              ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT, (EObject) createdLogicalFunction,
              ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT, rootSystemFunction);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createSystemFunctionRealizationCmd);

    return cmd;
  }
}
