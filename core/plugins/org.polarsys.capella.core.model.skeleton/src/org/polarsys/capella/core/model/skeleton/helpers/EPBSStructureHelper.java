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
import org.polarsys.capella.core.data.epbs.ConfigurationItemKind;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsFactory;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class EPBSStructureHelper {

  /**
   * @param systempEngineering_p
   * @return
   */
  public static PhysicalArchitecture getPhysicalArchitecture(SystemEngineering systemEngineering_p) {
    if (systemEngineering_p != null) {
      for (ModellingArchitecture arch : systemEngineering_p.getOwnedArchitectures()) {
        if (arch instanceof PhysicalArchitecture) {
          return (PhysicalArchitecture) arch;
        }
      }
    }
    return null;
  }

  /**
   * @param systemEngineering_p
   * @return
   */
  public static PhysicalFunction getRootPhysicalFunction(SystemEngineering systemEngineering_p) {
    PhysicalArchitecture physicalArchitecture = getPhysicalArchitecture(systemEngineering_p);
    if (physicalArchitecture != null) {
      PhysicalFunctionPkg functionPkg = (PhysicalFunctionPkg) physicalArchitecture.getOwnedFunctionPkg();
      if (functionPkg != null) {
        if (!functionPkg.getOwnedPhysicalFunctions().isEmpty()) {
          return functionPkg.getOwnedPhysicalFunctions().get(0);
        }
      }
    }
    return null;
  }

  /**
   * @param systemEngineering_p
   * @return
   */
  public static PhysicalComponent getRootPhysicalComponent(SystemEngineering systemEngineering_p) {
    PhysicalArchitecture physicalArchitecture = getPhysicalArchitecture(systemEngineering_p);
    if (physicalArchitecture != null) {
      return physicalArchitecture.getOwnedPhysicalComponent();
    }
    return null;
  }

  /**
   * @param editingDomain_p
   * @param systemEngineering_p
   * @return
   */
  public static Command getEPBSArchitectureCreationCmd(final EditingDomain editingDomain_p, final SystemEngineering systemEngineering_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the EPBSArchitecture.
    final Command createEPBSArchitectureCmd = CreateChildCommand.create(editingDomain_p, systemEngineering_p, new CommandParameter(systemEngineering_p,
      CapellacorePackage.Literals.ABSTRACT_MODELLING_STRUCTURE__OWNED_ARCHITECTURES, EpbsFactory.eINSTANCE.createEPBSArchitecture(NamingConstants.CreateEPBSArchCmd_name)), Collections.EMPTY_LIST);
    cmd.append(createEPBSArchitectureCmd);

    Command createEPBSArchitectureContentCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createEPBSArchitectureCmd.getResult();
        if (res.size() == 1) {
          Object createdEPBSArchitecture = res.iterator().next();
          if (createdEPBSArchitecture instanceof EPBSArchitecture) {
            return getEPBSArchitectureCreationCmd(editingDomain_p, systemEngineering_p, (EPBSArchitecture) createdEPBSArchitecture);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createEPBSArchitectureContentCmd);

    return cmd;
  }

  /**
   * @param editingDomain_p
   * @param systemEngineering_p
   * @param physicalArchitecture_p
   * @return
   */
  public static Command getEPBSArchitectureCreationCmd(final EditingDomain editingDomain_p, SystemEngineering systemEngineering_p, EPBSArchitecture epbsArchitecture_p) {
    CompoundCommand cmd = new CompoundCommand();

    PhysicalArchitecture physicalArchitecture = getPhysicalArchitecture(systemEngineering_p);
    cmd.append(CommonStructureHelper.getLinkCreationCmd(editingDomain_p,
      EpbsPackage.Literals.PHYSICAL_ARCHITECTURE_REALIZATION,
      EpbsPackage.Literals.EPBS_ARCHITECTURE__OWNED_PHYSICAL_ARCHITECTURE_REALIZATIONS,
      ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT, epbsArchitecture_p,
      ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT, physicalArchitecture));

    cmd.append(CommonStructureHelper.getRequirementPkgCreationCmd(editingDomain_p, epbsArchitecture_p));
    cmd.append(CommonStructureHelper.getCapabilityRealizationPkgCreationCmd(editingDomain_p, epbsArchitecture_p));
    cmd.append(getRootConfigurationItemCreationCmd(editingDomain_p, systemEngineering_p, epbsArchitecture_p));

    return cmd;
  }

  /**
   * @param editingDomain_p
   * @param epbsArchitecture_p
   * @return
   */
  public static Command getRootConfigurationItemCreationCmd(final EditingDomain editingDomain_p, final SystemEngineering systemEngineering_p, EPBSArchitecture epbsArchitecture_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the EPBSContext.
    final Command createEPBSContextCmd = CreateChildCommand.create(editingDomain_p, epbsArchitecture_p, new CommandParameter(epbsArchitecture_p,
      EpbsPackage.Literals.EPBS_ARCHITECTURE__OWNED_EPBS_CONTEXT, EpbsFactory.eINSTANCE.createEPBSContext(NamingConstants.CreateEPBSArchCmd_epbsContext_name)), Collections.EMPTY_LIST);
    cmd.append(createEPBSContextCmd);

    // Creates the Root ConfigurationItem.
    final Command createConfigurationItemCmd = CreateChildCommand.create(editingDomain_p, epbsArchitecture_p, new CommandParameter(epbsArchitecture_p,
      EpbsPackage.Literals.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM, EpbsFactory.eINSTANCE.createConfigurationItem(NamingConstants.CreateEPBSArchCmd_configurationItem_name)), Collections.EMPTY_LIST);
    cmd.append(createConfigurationItemCmd);

    // Sets the kind.
    final Command setKindCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createConfigurationItemCmd.getResult();
        if (res.size() == 1) {
          Object createdConfigurationItem = res.iterator().next();
          if (createdConfigurationItem instanceof EObject) {
            return new SetCommand(editingDomain_p, (EObject) createdConfigurationItem, EpbsPackage.Literals.CONFIGURATION_ITEM__KIND, ConfigurationItemKind.SYSTEM_CI);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(setKindCmd);

    // Creates the PhysicalComponentRealization.
    Command createPhysicalComponentRealizationCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createConfigurationItemCmd.getResult();
        if (res.size() == 1) {
          Object createdConfigurationItem = res.iterator().next();
          if (createdConfigurationItem instanceof EObject) {
            PhysicalComponent rootPhysicalComponent = getRootPhysicalComponent(systemEngineering_p);
            return CommonStructureHelper.getLinkCreationCmd(editingDomain_p,
              EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION,
              EpbsPackage.Literals.CONFIGURATION_ITEM__OWNED_PHYSICAL_ARTIFACT_REALIZATIONS,
              ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT, (EObject) createdConfigurationItem,
              ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT, rootPhysicalComponent);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createPhysicalComponentRealizationCmd);

    // Creates a Part.
    final Command createPartCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createEPBSContextCmd.getResult();
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
        Collection<?> res2 = createConfigurationItemCmd.getResult();
        if ((res1.size() == 1) && (res2.size() == 1)) {
          Object createdPart = res1.iterator().next();
          Object createdConfigurationItem = res2.iterator().next();
          if ((createdPart instanceof EObject) && (createdConfigurationItem instanceof EObject)) {
            return new SetCommand(editingDomain_p, (EObject) createdPart, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, ((AbstractNamedElement) createdConfigurationItem).getName());
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
        Collection<?> res2 = createConfigurationItemCmd.getResult();
        if ((res1.size() == 1) && (res2.size() == 1)) {
          Object createdPart = res1.iterator().next();
          Object createdConfigurationItem = res2.iterator().next();
          if ((createdPart instanceof EObject) && (createdConfigurationItem instanceof EObject)) {
            return new SetCommand(editingDomain_p, (EObject) createdPart, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, createdConfigurationItem);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(setPartTypeCmd);

    return cmd;
  }
}
