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
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class SAStructureHelper {

  /**
   * @param systempEngineering_p
   * @return
   */
  public static OperationalAnalysis getOperationalAnalysis(SystemEngineering systemEngineering_p) {
    if (systemEngineering_p != null) {
      for (ModellingArchitecture arch : systemEngineering_p.getOwnedArchitectures()) {
        if (arch instanceof OperationalAnalysis) {
          return (OperationalAnalysis) arch;
        }
      }
    }
    return null;
  }

  /**
   * @param systemEngineering_p
   * @return
   */
  public static OperationalActivity getRootOperationalActivity(SystemEngineering systemEngineering_p) {
    OperationalAnalysis operationalAnalysis = getOperationalAnalysis(systemEngineering_p);
    if (operationalAnalysis != null) {
      OperationalActivityPkg activityPkg = (OperationalActivityPkg) operationalAnalysis.getOwnedFunctionPkg();
      if (activityPkg != null) {
        if (!activityPkg.getOwnedOperationalActivities().isEmpty()) {
          return activityPkg.getOwnedOperationalActivities().get(0);
        }
      }
    }
    return null;
  }

  /**
   * @param editingDomain_p
   * @param systemEngineering_p
   * @return
   */
  public static Command getSystemAnalysisCreationCmd(final EditingDomain editingDomain_p, final SystemEngineering systemEngineering_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the SystemAnalysis.
    final Command createSystemAnalysisCmd =
        CreateChildCommand.create(
            editingDomain_p,
            systemEngineering_p,
            new CommandParameter(systemEngineering_p, CapellacorePackage.Literals.ABSTRACT_MODELLING_STRUCTURE__OWNED_ARCHITECTURES, CtxFactory.eINSTANCE
                .createSystemAnalysis(NamingConstants.CreateSysAnalysisCmd_name)), Collections.EMPTY_LIST);
    cmd.append(createSystemAnalysisCmd);

    Command createSystemAnalysisContentCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createSystemAnalysisCmd.getResult();
        if (res.size() == 1) {
          Object createdSystemAnalysis = res.iterator().next();
          if (createdSystemAnalysis instanceof SystemAnalysis) {
            return getSystemAnalysisCreationCmd(editingDomain_p, systemEngineering_p, (SystemAnalysis) createdSystemAnalysis);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createSystemAnalysisContentCmd);

    return cmd;
  }

  /**
   * @param editingDomain_p
   * @param systemEngineering_p
   * @param systemAnalysis_p
   * @return
   */
  public static Command getSystemAnalysisCreationCmd(final EditingDomain editingDomain_p, SystemEngineering systemEngineering_p, SystemAnalysis systemAnalysis_p) {
    CompoundCommand cmd = new CompoundCommand();

    OperationalAnalysis operationalAnalysis = getOperationalAnalysis(systemEngineering_p);
    cmd.append(CommonStructureHelper.getLinkCreationCmd(editingDomain_p, CtxPackage.Literals.OPERATIONAL_ANALYSIS_REALIZATION,
        CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_OPERATIONAL_ANALYSIS_REALIZATIONS, ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT,
        systemAnalysis_p, ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT, operationalAnalysis));

    cmd.append(CommonStructureHelper.getRequirementPkgCreationCmd(editingDomain_p, systemAnalysis_p));
    cmd.append(getSystemFunctionPkgCreationCmd(editingDomain_p, systemEngineering_p, systemAnalysis_p));
    cmd.append(getCapabilityPkgCreationCmd(editingDomain_p, systemAnalysis_p));
    cmd.append(getMissionPkgCreationCmd(editingDomain_p, systemAnalysis_p));
    cmd.append(CommonStructureHelper.getInterfacePkgCreationCmd(editingDomain_p, systemAnalysis_p));
    cmd.append(CommonStructureHelper.getDataPkgCreationCmd(editingDomain_p, systemAnalysis_p, true));
    cmd.append(getActorPkgCreationCmd(editingDomain_p, systemAnalysis_p));
    cmd.append(getSystemCreationCmd(editingDomain_p, systemAnalysis_p));

    return cmd;
  }

  /**
   * @param editingDomain_p
   * @param systemAnalysis_p
   * @return
   */
  public static Command getSystemCreationCmd(final EditingDomain editingDomain_p, SystemAnalysis systemAnalysis_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the SystemContext.
    final Command createSystemContextCmd =
        CreateChildCommand.create(
            editingDomain_p,
            systemAnalysis_p,
            new CommandParameter(systemAnalysis_p, CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_SYSTEM_CONTEXT, CtxFactory.eINSTANCE
                .createSystemContext(NamingConstants.CreateSysAnalysisCmd_system_context_name)), Collections.EMPTY_LIST);
    cmd.append(createSystemContextCmd);

    // Creates the System.
    final Command createSystemCmd =
        CreateChildCommand.create(editingDomain_p, systemAnalysis_p, new CommandParameter(systemAnalysis_p, CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_SYSTEM,
            CtxFactory.eINSTANCE.createSystem(NamingConstants.CreateSysAnalysisCmd_system_name)), Collections.EMPTY_LIST);
    cmd.append(createSystemCmd);

    // Creates a Part.
    final Command createPartCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createSystemContextCmd.getResult();
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
        Collection<?> res2 = createSystemCmd.getResult();
        if ((res1.size() == 1) && (res2.size() == 1)) {
          Object createdPart = res1.iterator().next();
          Object createdSystem = res2.iterator().next();
          if ((createdPart instanceof EObject) && (createdSystem instanceof EObject)) {
            return new SetCommand(editingDomain_p, (EObject) createdPart, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME,
                ((AbstractNamedElement) createdSystem).getName());
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
        Collection<?> res2 = createSystemCmd.getResult();
        if ((res1.size() == 1) && (res2.size() == 1)) {
          Object createdPart = res1.iterator().next();
          Object createdSystem = res2.iterator().next();
          if ((createdPart instanceof EObject) && (createdSystem instanceof EObject)) {
            return new SetCommand(editingDomain_p, (EObject) createdPart, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, createdSystem);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(setPartTypeCmd);

    // Creates a StateMachine.
    final Command createStateMachineCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createSystemCmd.getResult();
        if (res.size() == 1) {
          Object createdSystem = res.iterator().next();
          if (createdSystem instanceof EObject) {
            return CreateChildCommand.create(
                editingDomain_p,
                createdSystem,
                new CommandParameter(createdSystem, CsPackage.Literals.BLOCK__OWNED_STATE_MACHINES, CapellacommonFactory.eINSTANCE
                    .createStateMachine(NamingConstants.CreateSysAnalysisCmd_system_statemachine_name)), Collections.EMPTY_LIST);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createStateMachineCmd);

    // Creates a Region.
    Command createRegionCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createStateMachineCmd.getResult();
        if (res.size() == 1) {
          Object createdStateMachine = res.iterator().next();
          if (createdStateMachine instanceof EObject) {
            return CreateChildCommand.create(
                editingDomain_p,
                createdStateMachine,
                new CommandParameter(createdStateMachine, CapellacommonPackage.Literals.STATE_MACHINE__OWNED_REGIONS, CapellacommonFactory.eINSTANCE
                    .createRegion(NamingConstants.Region_DefaultRegion)), Collections.EMPTY_LIST);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createRegionCmd);

    return cmd;
  }

  /**
   * Creates an ActorPkg.
   * 
   * @param editingDomain_p
   * @param systemAnalysis_p
   * @return
   */
  public static Command getActorPkgCreationCmd(EditingDomain editingDomain_p, SystemAnalysis systemAnalysis_p) {
    return CreateChildCommand.create(editingDomain_p, systemAnalysis_p, new CommandParameter(systemAnalysis_p,
        CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_ACTOR_PKG, CtxFactory.eINSTANCE.createActorPkg(NamingConstants.CreateSysAnalysisCmd_actors_pkg_name)),
        Collections.EMPTY_LIST);
  }

  /**
   * Creates a CapabilityPkg.
   * 
   * @param editingDomain_p
   * @param systemAnalysis_p
   * @return
   */
  public static Command getCapabilityPkgCreationCmd(EditingDomain editingDomain_p, SystemAnalysis systemAnalysis_p) {
    return CreateChildCommand.create(
        editingDomain_p,
        systemAnalysis_p,
        new CommandParameter(systemAnalysis_p, CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG, CtxFactory.eINSTANCE
            .createCapabilityPkg(NamingConstants.CreateSysAnalysisCmd_capabilities_pkg_name)), Collections.EMPTY_LIST);
  }

  /**
   * Creates a MissionPkg.
   * 
   * @param editingDomain_p
   * @param systemAnalysis_p
   * @return
   */
  public static Command getMissionPkgCreationCmd(EditingDomain editingDomain_p, SystemAnalysis systemAnalysis_p) {
    return CreateChildCommand.create(editingDomain_p, systemAnalysis_p, new CommandParameter(systemAnalysis_p,
        CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_MISSION_PKG, CtxFactory.eINSTANCE.createMissionPkg(NamingConstants.CreateSysAnalysisCmd_missions_pkg_name)),
        Collections.EMPTY_LIST);
  }

  /**
   * @param editingDomain_p
   * @param systemEngineering_p
   * @param systemAnalysis_p
   * @return
   */
  public static Command getSystemFunctionPkgCreationCmd(final EditingDomain editingDomain_p, final SystemEngineering systemEngineering_p,
      final SystemAnalysis systemAnalysis_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the SystemFunctionPkg.
    final Command createSystemFunctionPkgCmd =
        CreateChildCommand.create(
            editingDomain_p,
            systemAnalysis_p,
            new CommandParameter(systemAnalysis_p, FaPackage.Literals.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTION_PKG, CtxFactory.eINSTANCE
                .createSystemFunctionPkg(NamingConstants.CreateSysAnalysisCmd_system_functions_pkg_name)), Collections.EMPTY_LIST);
    cmd.append(createSystemFunctionPkgCmd);

    // Creates the SystemFunction root.
    final Command createSystemFunctionCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createSystemFunctionPkgCmd.getResult();
        if (res.size() == 1) {
          Object createdSystemFunctionPkg = res.iterator().next();
          if (createdSystemFunctionPkg instanceof EObject) {
            return CreateChildCommand.create(
                editingDomain_p,
                createdSystemFunctionPkg,
                new CommandParameter(createdSystemFunctionPkg, CtxPackage.Literals.SYSTEM_FUNCTION_PKG__OWNED_SYSTEM_FUNCTIONS, CtxFactory.eINSTANCE
                    .createSystemFunction(NamingConstants.CreateSysAnalysisCmd_system_function_root_name)), Collections.EMPTY_LIST);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createSystemFunctionCmd);

    // Creates the OperationalActivityRealization.
    Command createOperationalActivityRealizationCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createSystemFunctionCmd.getResult();
        if (res.size() == 1) {
          Object createdSystemFunction = res.iterator().next();
          if (createdSystemFunction instanceof EObject) {
            OperationalActivity rootOperationalActivity = getRootOperationalActivity(systemEngineering_p);
            return CommonStructureHelper.getLinkCreationCmd(editingDomain_p, FaPackage.Literals.FUNCTION_REALIZATION,
                FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS, ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT,
                (EObject) createdSystemFunction, ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT, rootOperationalActivity);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createOperationalActivityRealizationCmd);

    return cmd;
  }
}
