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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.requirement.RequirementFactory;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;

/**
 */
public class CommonStructureHelper {

  private static final String _DEFAULT_CARDINALITY = "1"; //$NON-NLS-1$

  /**
   * @param editingDomain_p
   * @param eClass_p
   * @param containmentReference_p
   * @param sourceReference_p
   * @param sourceValue_p
   * @param targetReference_p
   * @param targetValue_p
   * @return
   */
  public static Command getLinkCreationCmd(final EditingDomain editingDomain_p, EClass eClass_p, EReference containmentReference_p, final EReference sourceReference_p, final EObject sourceValue_p, final EReference targetReference_p, final EObject targetValue_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Create a link.
    final Command createTraceCmd = CreateChildCommand.create(editingDomain_p, sourceValue_p,
        new CommandParameter(sourceValue_p, containmentReference_p, eClass_p.getEPackage().getEFactoryInstance().create(eClass_p)), Collections.EMPTY_LIST);
    cmd.append(createTraceCmd);

    // Sets the source value.
    Command setSourceCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createTraceCmd.getResult();
        if (res.size() == 1) {
          Object createdLink = res.iterator().next();
          if (createdLink instanceof EObject) {
            return new SetCommand(editingDomain_p, (EObject) createdLink, sourceReference_p, sourceValue_p);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(setSourceCmd);

    // Sets the target value.
    Command setTargetCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createTraceCmd.getResult();
        if (res.size() == 1) {
          Object createdLink = res.iterator().next();
          if (createdLink instanceof EObject) {
            return new SetCommand(editingDomain_p, (EObject) createdLink, targetReference_p, targetValue_p);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(setTargetCmd);

    return cmd;
  }

  /**
   * Creates an InterfacePkg.
   * 
   * @param editingDomain_p
   * @param blockArchitecture_p
   * @return the creation command
   */
  public static Command getInterfacePkgCreationCmd(EditingDomain editingDomain_p, BlockArchitecture blockArchitecture_p) {
    return CreateChildCommand.create(editingDomain_p, blockArchitecture_p, new CommandParameter(blockArchitecture_p,
        CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG, CsFactory.eINSTANCE.createInterfacePkg(NamingConstants.CreateCommonCmd_interfaces_pkg_name)), Collections.EMPTY_LIST);
  }

  /**
   * Creates a DataPkg, a DataTypePkg and a ClassPkg.<br>
   * (a predefined types package is also created)
   * @param editingDomain_p
   * @param sharedPkg_p
   * @return the creation command
   */
  public static Command getDataPkgCreationCmd(final EditingDomain editingDomain_p, SharedPkg sharedPkg_p) {
    CompoundCommand cmd = new CompoundCommand();

    final Command createDataPkgCmd = CreateChildCommand.create(editingDomain_p, sharedPkg_p, new CommandParameter(sharedPkg_p,
        SharedmodelPackage.Literals.SHARED_PKG__OWNED_DATA_PKG, InformationFactory.eINSTANCE.createDataPkg(NamingConstants.CreateCommonCmd_data_pkg_name)), Collections.EMPTY_LIST);
    cmd.append(createDataPkgCmd);
    cmd.append(getDataPkgContentCmd(editingDomain_p, createDataPkgCmd, true));

    return cmd;
  }

  /**
   * Creates a DataPkg, a DataTypePkg and a ClassPkg.
   *
   * @param editingDomain_p
   * @param blockArchitecture_p
   * @return the creation command
   */
  public static Command getDataPkgCreationCmd(final EditingDomain editingDomain_p, BlockArchitecture blockArchitecture_p) {
    return getDataPkgCreationCmd(editingDomain_p, blockArchitecture_p, false);
  }

  /**
   * Creates a DataPkg, a DataTypePkg and a ClassPkg.<br>
   * (a predefined types package is also created when <b>'createPredefinedTypes_p'</b> is <b>'TRUE'</b>)
   * @param editingDomain_p
   * @param blockArchitecture_p
   * @param createPredefinedTypes_p
   * @return the creation command
   */
  public static Command getDataPkgCreationCmd(final EditingDomain editingDomain_p, BlockArchitecture blockArchitecture_p, boolean createPredefinedTypes_p) {
    CompoundCommand cmd = new CompoundCommand();

    final Command createDataPkgCmd = CreateChildCommand.create(editingDomain_p, blockArchitecture_p, new CommandParameter(blockArchitecture_p,
        CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_DATA_PKG, InformationFactory.eINSTANCE.createDataPkg(NamingConstants.CreateCommonCmd_data_pkg_name)), Collections.EMPTY_LIST);
    cmd.append(createDataPkgCmd);
    cmd.append(getDataPkgContentCmd(editingDomain_p, createDataPkgCmd, createPredefinedTypes_p));

    return cmd;
  }

  /**
   * Creates a DataPkg, a DataTypePkg and a ClassPkg.<br>
   * (a predefined types package is also created when <b>'createPredefinedTypes_p'</b> is <b>'TRUE'</b>)
   * @param editingDomain_p
   * @param dataPkgCreationCmd_p
   * @param createPredefinedTypes_p
   * @return the creation command
   */
  public static Command getDataPkgContentCmd(final EditingDomain editingDomain_p, final Command dataPkgCreationCmd_p, final boolean createPredefinedTypes_p) {
    return new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = dataPkgCreationCmd_p.getResult();
        if (!res.isEmpty()) {
          final Object createdDataPkg = res.iterator().next();
          if (createdDataPkg instanceof EObject) {
            CompoundCommand cmd = new CompoundCommand();

            // Creates the DataTypePkg.
            final Command createDataTypePkgCmd = CreateChildCommand.create(editingDomain_p, createdDataPkg, new CommandParameter(createdDataPkg,
                InformationPackage.Literals.DATA_PKG__OWNED_DATA_PKGS, InformationFactory.eINSTANCE.createDataPkg(NamingConstants.CreateCommonCmd_types_pkg_name)), Collections.EMPTY_LIST);

            if (createPredefinedTypes_p) {
              // Creates the Predefined Types.
              Command createPredefinedTypesCmd = new CommandWrapper() {
                @Override
                public Command createCommand() {
                  Collection<?> result = createDataTypePkgCmd.getResult();
                  if (!result.isEmpty()) {
                    Object createdDataTypePkg = result.iterator().next();
                    if (createdDataTypePkg instanceof EObject) {
                      return PredefinedTypesNewHelper.getInstance(editingDomain_p).createPredefinedDataTypes((DataPkg) createdDataPkg);
                    }
                  }
                  return new IdentityCommand();
                }
              };
              cmd.append(createPredefinedTypesCmd);
              return cmd;
            }
          }
        }
        return new IdentityCommand();
      }
    };
  }

  /**
   * Creates a RequirementPkg.
   * 
   * @param editingDomain_p
   * @param operationalAnalysis_p
   * @return the creation command
   */
  public static Command getRequirementPkgCreationCmd(EditingDomain editingDomain_p, BlockArchitecture blockArchitecture_p) {
    return CreateChildCommand.create(editingDomain_p, blockArchitecture_p, new CommandParameter(blockArchitecture_p,
        CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_REQUIREMENT_PKGS, RequirementFactory.eINSTANCE.createRequirementsPkg(NamingConstants.CreateCommonCmd_requirements_pkg_name)), Collections.EMPTY_LIST);
  }

  /**
   * Creates a CapabilityRealizationPkg.
   * 
   * @param editingDomain_p
   * @param blockArchitecture_p
   * @return
   */
  public static Command getCapabilityRealizationPkgCreationCmd(EditingDomain editingDomain_p, BlockArchitecture blockArchitecture_p) {
    return CreateChildCommand.create(editingDomain_p, blockArchitecture_p, new CommandParameter(blockArchitecture_p,
        CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG, LaFactory.eINSTANCE.createCapabilityRealizationPkg(NamingConstants.CreateCommonCmd_capability_realisation_pkg_name)), Collections.EMPTY_LIST);
  }

  public static Command getCardinalityCmd(final EditingDomain editingDomain_p, final Command element) {
    return new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = element.getResult();
        if (!res.isEmpty()) {
          final Object createdElement = res.iterator().next();
          if (createdElement!=null && createdElement instanceof MultiplicityElement) {
            CompoundCommand cmd = new CompoundCommand();

            // Creates the min cardinality.
            final Command createMinCardCmd = new CommandWrapper() {
              @Override
              public Command createCommand() {
                return CreateChildCommand.create(editingDomain_p, createdElement, new CommandParameter(createdElement,
                    InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD, DatavalueFactory.eINSTANCE.createLiteralNumericValue("minCard")), //$NON-NLS-1$
                    Collections.EMPTY_LIST);

              }
            };
            cmd.append(createMinCardCmd);

            // Sets the min cardinality value.
            Command setMinCardValueCmd = new CommandWrapper() {
              @Override
              public Command createCommand() {
                Collection<?> res1 = createMinCardCmd.getResult();
                if (res1.size() == 1) {
                  Object createdObj = res1.iterator().next();
                  if (createdObj instanceof EObject) {
                    return new SetCommand(editingDomain_p, (EObject) createdObj, DatavaluePackage.Literals.LITERAL_NUMERIC_VALUE__VALUE, _DEFAULT_CARDINALITY);
                  }
                }
                return null;
              }
            };
            cmd.append(setMinCardValueCmd);

            // Creates the max cardinality.
            final Command createMaxCardCmd = new CommandWrapper() {
              @Override
              public Command createCommand() {
                return CreateChildCommand.create(editingDomain_p, createdElement, new CommandParameter(createdElement,
                    InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD, DatavalueFactory.eINSTANCE.createLiteralNumericValue("maxCard")), //$NON-NLS-1$
                    Collections.EMPTY_LIST);

              }
            };
            cmd.append(createMaxCardCmd);

            // Sets the max cardinality value.
            Command setMaxCardValueCmd = new CommandWrapper() {
              @Override
              public Command createCommand() {
                Collection<?> res1 = createMinCardCmd.getResult();
                if (res1.size() == 1) {
                  Object createdObj = res1.iterator().next();
                  if (createdObj instanceof EObject) {
                    return new SetCommand(editingDomain_p, (EObject) createdObj, DatavaluePackage.Literals.LITERAL_NUMERIC_VALUE__VALUE, _DEFAULT_CARDINALITY);
                  }
                }
                return null;
              }
            };
            cmd.append(setMaxCardValueCmd);
            return cmd;
          }
        }
        return new IdentityCommand();
      }
    };
  }

}
