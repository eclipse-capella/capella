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
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class PAStructureHelper {

  /**
   * @param systempEngineering_p
   * @return
   */
  public static LogicalArchitecture getLogicalArchitecture(SystemEngineering systemEngineering_p) {
    if (systemEngineering_p != null) {
      for (ModellingArchitecture arch : systemEngineering_p.getOwnedArchitectures()) {
        if (arch instanceof LogicalArchitecture) {
          return (LogicalArchitecture) arch;
        }
      }
    }
    return null;
  }

  /**
   * @param systemEngineering_p
   * @return
   */
  public static LogicalFunction getRootLogicalFunction(SystemEngineering systemEngineering_p) {
    LogicalArchitecture logicalArchitecture = getLogicalArchitecture(systemEngineering_p);
    if (logicalArchitecture != null) {
      LogicalFunctionPkg functionPkg = (LogicalFunctionPkg) logicalArchitecture.getOwnedFunctionPkg();
      if (functionPkg != null) {
        if (!functionPkg.getOwnedLogicalFunctions().isEmpty()) {
          return functionPkg.getOwnedLogicalFunctions().get(0);
        }
      }
    }
    return null;
  }

  /**
   * @param systemEngineering_p
   * @return
   */
  public static LogicalComponent getRootLogicalComponent(SystemEngineering systemEngineering_p) {
    LogicalArchitecture logicalArchitecture = getLogicalArchitecture(systemEngineering_p);
    if (logicalArchitecture != null) {
      return logicalArchitecture.getOwnedLogicalComponent();
    }
    return null;
  }

  /**
   * @param editingDomain_p
   * @param systemEngineering_p
   * @return
   */
  public static Command getPhysicalArchitectureCreationCmd(final EditingDomain editingDomain_p, final SystemEngineering systemEngineering_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the PhysicalArchitecture.
    final Command createPhysicalArchitectureCmd = CreateChildCommand.create(editingDomain_p, systemEngineering_p, new CommandParameter(systemEngineering_p,
      CapellacorePackage.Literals.ABSTRACT_MODELLING_STRUCTURE__OWNED_ARCHITECTURES, PaFactory.eINSTANCE.createPhysicalArchitecture(NamingConstants.CreatePhysicalArchCmd_name)), Collections.EMPTY_LIST);
    cmd.append(createPhysicalArchitectureCmd);

    Command createPhysicalArchitectureContentCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createPhysicalArchitectureCmd.getResult();
        if (res.size() == 1) {
          Object createdPhysicalArchitecture = res.iterator().next();
          if (createdPhysicalArchitecture instanceof PhysicalArchitecture) {
            return getPhysicalArchitectureCreationCmd(editingDomain_p, systemEngineering_p, (PhysicalArchitecture) createdPhysicalArchitecture);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createPhysicalArchitectureContentCmd);

    return cmd;
  }

  /**
   * @param editingDomain_p
   * @param systemEngineering_p
   * @param physicalArchitecture_p
   * @return
   */
  public static Command getPhysicalArchitectureCreationCmd(final EditingDomain editingDomain_p, SystemEngineering systemEngineering_p, PhysicalArchitecture physicalArchitecture_p) {
    CompoundCommand cmd = new CompoundCommand();

    LogicalArchitecture logicalArchitecture = getLogicalArchitecture(systemEngineering_p);
    cmd.append(CommonStructureHelper.getLinkCreationCmd(editingDomain_p,
      PaPackage.Literals.LOGICAL_ARCHITECTURE_REALIZATION,
      PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_LOGICAL_ARCHITECTURE_REALIZATIONS,
      ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT, physicalArchitecture_p,
      ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT, logicalArchitecture));

    cmd.append(CommonStructureHelper.getRequirementPkgCreationCmd(editingDomain_p, physicalArchitecture_p));
    cmd.append(getPhysicalFunctionPkgCreationCmd(editingDomain_p, systemEngineering_p, physicalArchitecture_p));
    cmd.append(CommonStructureHelper.getCapabilityRealizationPkgCreationCmd(editingDomain_p, physicalArchitecture_p));
    cmd.append(CommonStructureHelper.getInterfacePkgCreationCmd(editingDomain_p, physicalArchitecture_p));
    cmd.append(CommonStructureHelper.getDataPkgCreationCmd(editingDomain_p, physicalArchitecture_p));
    cmd.append(getPhysicalActorPkgCreationCmd(editingDomain_p, physicalArchitecture_p));
    cmd.append(getRootPhysicalComponentCreationCmd(editingDomain_p, systemEngineering_p, physicalArchitecture_p));

    return cmd;
  }

  /**
   * @param editingDomain_p
   * @param physicalArchitecture_p
   * @return
   */
  public static Command getRootPhysicalComponentCreationCmd(final EditingDomain editingDomain_p, final SystemEngineering systemEngineering_p, PhysicalArchitecture physicalArchitecture_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the PhysicalContext.
    final Command createPhysicalContextCmd = CreateChildCommand.create(editingDomain_p, physicalArchitecture_p, new CommandParameter(physicalArchitecture_p,
      PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_CONTEXT, PaFactory.eINSTANCE.createPhysicalContext(NamingConstants.CreatePhysicalArchCmd_physicalContext_name)), Collections.EMPTY_LIST);
    cmd.append(createPhysicalContextCmd);

    // Creates the Root PhysicalComponent.
    final Command createPhysicalComponentCmd = CreateChildCommand.create(editingDomain_p, physicalArchitecture_p, new CommandParameter(physicalArchitecture_p,
      PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT, PaFactory.eINSTANCE.createPhysicalComponent(NamingConstants.CreatePhysicalArchCmd_physicalComponent_name)), Collections.EMPTY_LIST);
    cmd.append(createPhysicalComponentCmd);

    // Creates the LogicalComponentRealization.
    Command createLogicalComponentRealizationCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createPhysicalComponentCmd.getResult();
        if (res.size() == 1) {
          Object createdPhysicalComponent = res.iterator().next();
          if (createdPhysicalComponent instanceof EObject) {
            LogicalComponent rootLogicalComponent = getRootLogicalComponent(systemEngineering_p);
            return CommonStructureHelper.getLinkCreationCmd(editingDomain_p,
              PaPackage.Literals.LOGICAL_COMPONENT_REALIZATION,
              PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_REALIZATIONS,
              ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT, (EObject) createdPhysicalComponent,
              ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT, rootLogicalComponent);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createLogicalComponentRealizationCmd);

    // Creates a Part.
    final Command createPartCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createPhysicalContextCmd.getResult();
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
        Collection<?> res2 = createPhysicalComponentCmd.getResult();
        if ((res1.size() == 1) && (res2.size() == 1)) {
          Object createdPart = res1.iterator().next();
          Object createdPhysicalComponent = res2.iterator().next();
          if ((createdPart instanceof EObject) && (createdPhysicalComponent instanceof EObject)) {
            return new SetCommand(editingDomain_p, (EObject) createdPart, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, ((AbstractNamedElement) createdPhysicalComponent).getName());
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
        Collection<?> res2 = createPhysicalComponentCmd.getResult();
        if ((res1.size() == 1) && (res2.size() == 1)) {
          Object createdPart = res1.iterator().next();
          Object createdPhysicalComponent = res2.iterator().next();
          if ((createdPart instanceof EObject) && (createdPhysicalComponent instanceof EObject)) {
            return new SetCommand(editingDomain_p, (EObject) createdPart, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, createdPhysicalComponent);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(setPartTypeCmd);

    return cmd;
  }

  /**
   * Creates an PhysicalActorPkg.
   * 
   * @param editingDomain_p
   * @param physicalArchitecture_p
   * @return
   */
  public static Command getPhysicalActorPkgCreationCmd(EditingDomain editingDomain_p, PhysicalArchitecture physicalArchitecture_p) {
    return CreateChildCommand.create(editingDomain_p, physicalArchitecture_p, new CommandParameter(physicalArchitecture_p,
      PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG, PaFactory.eINSTANCE.createPhysicalActorPkg(NamingConstants.CreatePhysicalArchCmd_actors_pkg_name)), Collections.EMPTY_LIST);
  }

  /**
   * @param editingDomain_p
   * @param systemEngineering_p
   * @param physicalArchitecture_p
   * @return
   */
  public static Command getPhysicalFunctionPkgCreationCmd(final EditingDomain editingDomain_p, final SystemEngineering systemEngineering_p, final PhysicalArchitecture physicalArchitecture_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the PhysicalFunctionPkg.
    final Command createPhysicalFunctionPkgCmd = CreateChildCommand.create(editingDomain_p, physicalArchitecture_p, new CommandParameter(physicalArchitecture_p,
      FaPackage.Literals.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTION_PKG, PaFactory.eINSTANCE.createPhysicalFunctionPkg(NamingConstants.CreatePhysicalArchCmd_physicalFunctions_pkg_name)), Collections.EMPTY_LIST);
    cmd.append(createPhysicalFunctionPkgCmd);

    // Creates the PhysicalFunction root.
    final Command createPhysicalFunctionCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createPhysicalFunctionPkgCmd.getResult();
        if (res.size() == 1) {
          Object createdPhysicalFunctionPkg = res.iterator().next();
          if (createdPhysicalFunctionPkg instanceof EObject) {
            return CreateChildCommand.create(editingDomain_p, createdPhysicalFunctionPkg, new CommandParameter(createdPhysicalFunctionPkg,
              PaPackage.Literals.PHYSICAL_FUNCTION_PKG__OWNED_PHYSICAL_FUNCTIONS, PaFactory.eINSTANCE.createPhysicalFunction(NamingConstants.CreatePhysicalArchCmd_physicalFunction_root_name)), Collections.EMPTY_LIST);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createPhysicalFunctionCmd);

    // Creates the LogicalFunctionRealization.
    Command createLogicalFunctionRealizationCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createPhysicalFunctionCmd.getResult();
        if (res.size() == 1) {
          Object createdPhysicalFunction = res.iterator().next();
          if (createdPhysicalFunction instanceof EObject) {
            LogicalFunction rootLogicalFunction = getRootLogicalFunction(systemEngineering_p);
            return CommonStructureHelper.getLinkCreationCmd(editingDomain_p,
              FaPackage.Literals.FUNCTION_REALIZATION,
              FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS,
              ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT, (EObject) createdPhysicalFunction,
              ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT, rootLogicalFunction);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createLogicalFunctionRealizationCmd);

    return cmd;
  }
}
