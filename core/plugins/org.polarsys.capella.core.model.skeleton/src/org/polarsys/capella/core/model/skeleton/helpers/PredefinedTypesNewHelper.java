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
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datatype.DatatypeFactory;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.NumericTypeKind;
import org.polarsys.capella.core.data.information.datavalue.BinaryOperator;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.capellacore.VisibilityKind;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class PredefinedTypesNewHelper {

  protected EditingDomain __editingDomain;
  private static PredefinedTypesNewHelper __instance;

  protected static final String __ZERO_VALUE = "0"; //$NON-NLS-1$
  protected static final String __ONE_VALUE = "1"; //$NON-NLS-1$
  protected static final String __8BITS_MAX_VALUE = "255"; //$NON-NLS-1$

  // Predefined string constants for role-dependent value names
  protected static final String __MIN_NAME = ""; //$NON-NLS-1$
  protected static final String __MAX_NAME = "";//$NON-NLS-1$
  protected static final String __MIN_LENGTH_NAME = ""; //$NON-NLS-1$
  protected static final String __MAX_LENGTH_NAME = ""; //$NON-NLS-1$

  // Visibility for all predefined types
  protected static final VisibilityKind __PREDEFINED_TYPE_VISIBILITY = VisibilityKind.PUBLIC;

  /**
   * Private constructor
   * @param editingDomain_p
   */
  private PredefinedTypesNewHelper(EditingDomain editingDomain_p) {
    __editingDomain = editingDomain_p;
  }

  /**
   * @param editingDomain_p
   */
  public static PredefinedTypesNewHelper getInstance(EditingDomain editingDomain_p) {
    if (__instance == null)
      __instance = new PredefinedTypesNewHelper(editingDomain_p);
    return __instance;
  }

  /**
   * Fills the DataPkg with basic instances of subclasses of DataType
   * @param owner_p
   */
  public Command createPredefinedDataTypes(DataPkg owner_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the DataTypePkg.
    final Command createDataTypePkgCmd = CreateChildCommand.create(__editingDomain, owner_p, new CommandParameter(owner_p,
      InformationPackage.Literals.DATA_PKG__OWNED_DATA_PKGS, InformationFactory.eINSTANCE.createDataPkg(NamingConstants.PredefinedTypesCmd_predefinedDataTypePkg_name)), Collections.EMPTY_LIST);
    cmd.append(createDataTypePkgCmd);

    // Creates the Predefined Types.
    Command createPredefinedTypesCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createDataTypePkgCmd.getResult();
        if (res.size() == 1) {
          Object createdDataTypePkg = res.iterator().next();
          if (createdDataTypePkg instanceof EObject) {
            EObject owner = (EObject) createdDataTypePkg;
            CompoundCommand predefinedTypesCmd = new CompoundCommand();

            predefinedTypesCmd.append(newBooleanType(owner, NamingConstants.PredefinedTypesCmd_boolean_name));
            predefinedTypesCmd.append(newNumericType(owner, NamingConstants.PredefinedTypesCmd_byte_name, Boolean.TRUE, NumericTypeKind.INTEGER, Boolean.TRUE, __ZERO_VALUE, Boolean.TRUE, __8BITS_MAX_VALUE));
            predefinedTypesCmd.append(newNumericType(owner, NamingConstants.PredefinedTypesCmd_double_name, Boolean.FALSE, NumericTypeKind.FLOAT));
            predefinedTypesCmd.append(newNumericType(owner, NamingConstants.PredefinedTypesCmd_float_name, Boolean.FALSE, NumericTypeKind.FLOAT));
            predefinedTypesCmd.append(newHexadecimalType(owner, NamingConstants.PredefinedTypesCmd_hexadecimal_name, Boolean.TRUE, Boolean.TRUE, __ZERO_VALUE, Boolean.TRUE));
            predefinedTypesCmd.append(newNumericType(owner, NamingConstants.PredefinedTypesCmd_integer_name, Boolean.TRUE, NumericTypeKind.INTEGER));
            predefinedTypesCmd.append(newNumericType(owner, NamingConstants.PredefinedTypesCmd_long_name, Boolean.TRUE, NumericTypeKind.INTEGER));
            predefinedTypesCmd.append(newNumericType(owner, NamingConstants.PredefinedTypesCmd_longLong_name, Boolean.TRUE, NumericTypeKind.INTEGER));
            predefinedTypesCmd.append(newNumericType(owner, NamingConstants.PredefinedTypesCmd_short_name, Boolean.TRUE, NumericTypeKind.INTEGER));
            predefinedTypesCmd.append(newNumericType(owner, NamingConstants.PredefinedTypesCmd_unsignedInteger_name, Boolean.TRUE, NumericTypeKind.INTEGER, Boolean.TRUE, __ZERO_VALUE, Boolean.FALSE, null));
            Command unsignedShortCmd = newNumericType(owner, NamingConstants.PredefinedTypesCmd_unsignedShort_name, Boolean.TRUE, NumericTypeKind.INTEGER, Boolean.TRUE, __ZERO_VALUE, Boolean.FALSE, null);
            predefinedTypesCmd.append(unsignedShortCmd);
            predefinedTypesCmd.append(newNumericType(owner, NamingConstants.PredefinedTypesCmd_unsignedLong_name, Boolean.TRUE, NumericTypeKind.INTEGER, Boolean.TRUE, __ZERO_VALUE, Boolean.FALSE, null));
            predefinedTypesCmd.append(newNumericType(owner, NamingConstants.PredefinedTypesCmd_unsignedLongLong_name, Boolean.TRUE, NumericTypeKind.INTEGER, Boolean.TRUE, __ZERO_VALUE, Boolean.FALSE, null));
            predefinedTypesCmd.append(newStringType(owner, NamingConstants.PredefinedTypesCmd_string_name));
            predefinedTypesCmd.append(newStringType(owner, NamingConstants.PredefinedTypesCmd_char_name, unsignedShortCmd));

            return predefinedTypesCmd;
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createPredefinedTypesCmd);

    return cmd;
  }

  /**
   * @param owner_p
   * @param name_p
   * @param cmd_p
   */
  protected Command newStringType(EObject owner_p, String name_p, Command cmd_p) {
    final CompoundCommand cmd = newStringType(owner_p, name_p);

    Command createMinLengthValueCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = cmd.getResult();
        if (!res.isEmpty()) {
          Object createdStringType = res.iterator().next();
          if (createdStringType instanceof EObject) {
            return newLiteralNumericValue(DatatypePackage.Literals.STRING_TYPE__OWNED_MIN_LENGTH, (EObject) createdStringType, __MIN_LENGTH_NAME, __ONE_VALUE);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createMinLengthValueCmd);
    cmd.append(setWrappedCommand(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, createMinLengthValueCmd, cmd_p));

    Command createMaxLengthValueCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = cmd.getResult();
        if (!res.isEmpty()) {
          Object createdStringType = res.iterator().next();
          if (createdStringType instanceof EObject) {
            return newLiteralNumericValue(DatatypePackage.Literals.STRING_TYPE__OWNED_MAX_LENGTH, (EObject) createdStringType, __MAX_LENGTH_NAME, __ONE_VALUE);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createMaxLengthValueCmd);
    cmd.append(setWrappedCommand(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, createMaxLengthValueCmd, cmd_p));

    return cmd;
  }

  /**
   * @param owner_p
   * @param name_p
   */
  protected CompoundCommand newStringType(EObject owner_p, String name_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the StringType.
    final Command createStringTypeCmd = CreateChildCommand.create(__editingDomain, owner_p, new CommandParameter(owner_p,
      InformationPackage.Literals.DATA_PKG__OWNED_DATA_TYPES, DatatypeFactory.eINSTANCE.createStringType(name_p)), Collections.EMPTY_LIST);
    cmd.append(createStringTypeCmd);

    // Sets the discrete value.
    cmd.append(setWrappedCommand(DatatypePackage.Literals.DATA_TYPE__DISCRETE, createStringTypeCmd, Boolean.TRUE));

    // Sets the visibility value.
    cmd.append(setWrappedCommand(DatatypePackage.Literals.DATA_TYPE__VISIBILITY, createStringTypeCmd, __PREDEFINED_TYPE_VISIBILITY));

    return cmd;
  }

  /**
   * @param owner_p
   * @param name_p
   */
  protected Command newBooleanType(EObject owner_p, String name_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the BooleanType.
    final Command createBooleanTypeCmd = CreateChildCommand.create(__editingDomain, owner_p, new CommandParameter(owner_p,
      InformationPackage.Literals.DATA_PKG__OWNED_DATA_TYPES, DatatypeFactory.eINSTANCE.createBooleanType(name_p)), Collections.EMPTY_LIST);
    cmd.append(createBooleanTypeCmd);

    // Sets the discrete value.
    cmd.append(setWrappedCommand(DatatypePackage.Literals.DATA_TYPE__DISCRETE, createBooleanTypeCmd, Boolean.TRUE));

    // Sets the visibility value.
    cmd.append(setWrappedCommand(DatatypePackage.Literals.DATA_TYPE__VISIBILITY, createBooleanTypeCmd, __PREDEFINED_TYPE_VISIBILITY));

    Command createTrueBooleanValueCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createBooleanTypeCmd.getResult();
        if (!res.isEmpty()) {
          Object createdBooleanType = res.iterator().next();
          if (createdBooleanType instanceof EObject) {
            return newLiteralBooleanValue(DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES,
              (EObject) createdBooleanType, NamingConstants.PredefinedTypesCmd_trueValue_name, Boolean.TRUE);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createTrueBooleanValueCmd);
    cmd.append(setWrappedCommand(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, createTrueBooleanValueCmd, createBooleanTypeCmd));

    Command createFalseBooleanValueCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createBooleanTypeCmd.getResult();
        if (!res.isEmpty()) {
          Object createdBooleanType = res.iterator().next();
          if (createdBooleanType instanceof EObject) {
            return newLiteralBooleanValue(DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES,
              (EObject) createdBooleanType, NamingConstants.PredefinedTypesCmd_falseValue_name, Boolean.FALSE);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createFalseBooleanValueCmd);
    cmd.append(setWrappedCommand(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, createFalseBooleanValueCmd, createBooleanTypeCmd));

    return cmd;
  }

  /**
   * @param owner_p
   * @param name_p
   * @param discrete_p
   * @param kind_p
   * @param minInclusive_p
   * @param minValue_p
   * @param maxInclusive_p
   * @param maxValue_p
   */
  protected Command newNumericType(EObject owner_p, String name_p, Boolean discrete_p, NumericTypeKind kind_p, final Boolean minInclusive_p, final String minValue_p, final Boolean maxInclusive_p, final String maxValue_p) {
    final CompoundCommand cmd = newNumericType(owner_p, name_p, discrete_p, kind_p);

    if (null != minValue_p) {
      // Sets the minValue value.
      final Command createLiteralNumericValueCmd = new CommandWrapper() {
        @Override
        public Command createCommand() {
          Collection<?> res = cmd.getResult();
          if (!res.isEmpty()) {
            Object createdNumericType = res.iterator().next();
            if (createdNumericType instanceof EObject) {
              return newLiteralNumericValue(DatatypePackage.Literals.NUMERIC_TYPE__OWNED_MIN_VALUE, (EObject) createdNumericType, __MIN_NAME, minValue_p);
            }
          }
          return new IdentityCommand();
        }
      };
      cmd.append(createLiteralNumericValueCmd);

      // Sets type value.
      cmd.append(setWrappedCommand(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, createLiteralNumericValueCmd, cmd));
    }

    if (null != maxValue_p) {
      // Creates the maxValue value.
      final Command createLiteralNumericValueCmd = new CommandWrapper() {
        @Override
        public Command createCommand() {
          Collection<?> res = cmd.getResult();
          if (!res.isEmpty()) {
            Object createdNumericType = res.iterator().next();
            if (createdNumericType instanceof EObject) {
              return newLiteralNumericValue(DatatypePackage.Literals.NUMERIC_TYPE__OWNED_MAX_VALUE, (EObject) createdNumericType, __MAX_NAME, maxValue_p);
            }
          }
          return new IdentityCommand();
        }
      };
      cmd.append(createLiteralNumericValueCmd);

      // Sets type value.
      cmd.append(setWrappedCommand(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, createLiteralNumericValueCmd, cmd));
    }

    // Sets the minInclusive value.
    cmd.append(setWrappedCommand(DatatypePackage.Literals.DATA_TYPE__MIN_INCLUSIVE, cmd, minInclusive_p));

    // Sets the maxInclusive value.
    cmd.append(setWrappedCommand(DatatypePackage.Literals.DATA_TYPE__MAX_INCLUSIVE, cmd, maxInclusive_p));

    return cmd;
  }

  /**
   * @param owner_p
   * @param name_p
   * @param discrete_p
   * @param kind_p
   */
  protected CompoundCommand newNumericType(EObject owner_p, String name_p, Boolean discrete_p, NumericTypeKind kind_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the NumericType.
    final Command createNumericTypeCmd = CreateChildCommand.create(__editingDomain, owner_p, new CommandParameter(owner_p,
      InformationPackage.Literals.DATA_PKG__OWNED_DATA_TYPES, DatatypeFactory.eINSTANCE.createNumericType(name_p)), Collections.EMPTY_LIST);
    cmd.append(createNumericTypeCmd);

    // Sets the discrete value.
    cmd.append(setWrappedCommand(DatatypePackage.Literals.DATA_TYPE__DISCRETE, createNumericTypeCmd, discrete_p));

    // Sets the visibility value.
    cmd.append(setWrappedCommand(DatatypePackage.Literals.DATA_TYPE__VISIBILITY, createNumericTypeCmd, __PREDEFINED_TYPE_VISIBILITY));

    // Sets the kind value.
    cmd.append(setWrappedCommand(DatatypePackage.Literals.NUMERIC_TYPE__KIND, createNumericTypeCmd, kind_p));

    return cmd;
  }

  /**
   * Helper method that creates a Numeric Value with the given name and value.
   * @param containmentFeature_p
   * @param owner_p
   * @param name_p
   * @param value_p
   * @param type_p
   */
  protected Command newLiteralNumericValue(EReference containmentFeature_p, EObject owner_p, String name_p, String value_p, final NumericType type_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the LiteralNumericValue and sets the value.
    final Command createLiteralNumericValueCmd = newLiteralNumericValue(containmentFeature_p, owner_p, name_p, value_p);
    cmd.append(createLiteralNumericValueCmd);

    // Sets the type.
    cmd.append(setWrappedCommand(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, createLiteralNumericValueCmd, type_p));

    return cmd;
  }

  /**
   * @param containmentFeature_p
   * @param owner_p
   * @param name_p
   * @param value_p
   */
  protected Command newLiteralNumericValue(EReference containmentFeature_p, EObject owner_p, String name_p, final String value_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the LiteralNumericValue.
    final Command createLiteralNumericValueCmd = CreateChildCommand.create(__editingDomain, owner_p, new CommandParameter(owner_p,
      containmentFeature_p, DatavalueFactory.eINSTANCE.createLiteralNumericValue(name_p)), Collections.EMPTY_LIST);
    cmd.append(createLiteralNumericValueCmd);

    // Sets the value.
    cmd.append(setWrappedCommand(DatavaluePackage.Literals.LITERAL_NUMERIC_VALUE__VALUE, createLiteralNumericValueCmd, value_p));

    return cmd;
  }

  /**
   * @param containmentFeature_p
   * @param owner_p
   * @param name_p
   * @param value_p
   */
  protected Command newLiteralBooleanValue(EReference containmentFeature_p, EObject owner_p, String name_p, final Boolean value_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the LiteralBooleanValue.
    final Command createLiteralNumericValueCmd = CreateChildCommand.create(__editingDomain, owner_p, new CommandParameter(owner_p,
      containmentFeature_p, DatavalueFactory.eINSTANCE.createLiteralBooleanValue(name_p)), Collections.EMPTY_LIST);
    cmd.append(createLiteralNumericValueCmd);

    // Sets the value.
    cmd.append(setWrappedCommand(DatavaluePackage.Literals.LITERAL_BOOLEAN_VALUE__VALUE, createLiteralNumericValueCmd, value_p));

    return cmd;
  }

  /**
   * Sets value.
   * @param feature_p
   * @param object_p
   * @param value_p
   * @return
   */
  protected Command setWrappedCommand(final EStructuralFeature feature_p, final Command object_p, final Command value_p) {
    Command setTypeCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res1 = object_p.getResult();
        Collection<?> res2 = value_p.getResult();
        if (!res1.isEmpty() && !res2.isEmpty()) {
          Object obj = res1.iterator().next();
          Object value = res2.iterator().next();
          if ((obj instanceof EObject) && (value instanceof EObject)) {
            return new SetCommand(__editingDomain, (EObject) obj, feature_p, value);
          }
        }
        return new IdentityCommand();
      }
    };
    return setTypeCmd;
  }

  /**
   * Sets value.
   * @param feature_p
   * @param object_p
   * @param value_p
   * @return
   */
  protected Command setWrappedCommand(final EStructuralFeature feature_p, final Command object_p, final Object value_p) {
    Command setCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = object_p.getResult();
        if (!res.isEmpty()) {
          Object obj = res.iterator().next();
          if (obj instanceof EObject) {
            return new SetCommand(__editingDomain, (EObject) obj, feature_p, value_p);
          }
        }
        return new IdentityCommand();
      }
    };
    return setCmd;
  }

  /**
   * @param owner_p
   * @param name_p
   * @param discrete_p
   * @param minInclusive_p
   * @param minValue_p
   * @param maxInclusive_p
   * @param maxValue_p
   */
  protected Command newHexadecimalType(EObject owner_p, String name_p, Boolean discrete_p, final Boolean minInclusive_p, final String minValue_p, final Boolean maxInclusive_p) {
    final CompoundCommand cmd = newNumericType(owner_p, name_p, discrete_p, NumericTypeKind.INTEGER);

    if (null != minValue_p) {
      // Sets the minValue value.
      final Command createLiteralNumericValueCmd = new CommandWrapper() {
        @Override
        public Command createCommand() {
          Collection<?> res = cmd.getResult();
          if (!res.isEmpty()) {
            Object createdNumericType = res.iterator().next();
            if (createdNumericType instanceof EObject) {
              return newLiteralNumericValue(DatatypePackage.Literals.NUMERIC_TYPE__OWNED_MIN_VALUE, (EObject) createdNumericType, __MIN_NAME, minValue_p);
            }
          }
          return new IdentityCommand();
        }
      };
      cmd.append(createLiteralNumericValueCmd);

      // Sets type value.
      cmd.append(setWrappedCommand(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, createLiteralNumericValueCmd, cmd));
    }

    // Creates the maxValue value.
    final Command createLiteralNumericValueCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = cmd.getResult();
        if (!res.isEmpty()) {
          Object createdNumericType = res.iterator().next();
          if (createdNumericType instanceof EObject) {
            return newBinaryExpressionValue(DatatypePackage.Literals.NUMERIC_TYPE__OWNED_MAX_VALUE, (EObject) createdNumericType);
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(createLiteralNumericValueCmd);

    // Sets type value.
    cmd.append(setWrappedCommand(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, createLiteralNumericValueCmd, cmd));

    // Sets the minInclusive value.
    cmd.append(setWrappedCommand(DatatypePackage.Literals.DATA_TYPE__MIN_INCLUSIVE, cmd, minInclusive_p));

    // Sets the maxInclusive value.
    cmd.append(setWrappedCommand(DatatypePackage.Literals.DATA_TYPE__MAX_INCLUSIVE, cmd, maxInclusive_p));

    return cmd;
  }

  /**
   * @param containmentFeature_p
   * @param owner_p
   */
  protected Command newBinaryExpressionValue(EReference containmentFeature_p, EObject owner_p) {
    CompoundCommand cmd = new CompoundCommand();

    final Command createSubBinaryExpressionCmd = CreateChildCommand.create(__editingDomain, owner_p, new CommandParameter(owner_p,
      containmentFeature_p, DatavalueFactory.eINSTANCE.createBinaryExpression()), Collections.EMPTY_LIST);
    cmd.append(createSubBinaryExpressionCmd);
    cmd.append(setWrappedCommand(DatavaluePackage.Literals.BINARY_EXPRESSION__OPERATOR, createSubBinaryExpressionCmd, BinaryOperator.SUB));
    cmd.append(new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res1 = createSubBinaryExpressionCmd.getResult();
        if (!res1.isEmpty()) {
          Object createdExpression = res1.iterator().next();
          if (createdExpression instanceof EObject) {
            CompoundCommand subCmd = new CompoundCommand();
            final Command createBinaryExpressionCmd = CreateChildCommand.create(__editingDomain, createdExpression, new CommandParameter(createdExpression,
                DatavaluePackage.Literals.BINARY_EXPRESSION__OWNED_LEFT_OPERAND , DatavalueFactory.eINSTANCE.createBinaryExpression()), Collections.EMPTY_LIST);
            subCmd.append(createBinaryExpressionCmd);
            subCmd.append(setWrappedCommand(DatavaluePackage.Literals.BINARY_EXPRESSION__OPERATOR, createBinaryExpressionCmd, BinaryOperator.POW));
            subCmd.append(new CommandWrapper() {
              @Override
              public Command createCommand() {
                Collection<?> res2 = createBinaryExpressionCmd.getResult();
                if (!res2.isEmpty()) {
                  Object createdPowExpression = res2.iterator().next();
                  if (createdPowExpression instanceof EObject) {
                    CompoundCommand subsubCmd = new CompoundCommand();
                    subsubCmd.append(newLiteralNumericValue(DatavaluePackage.Literals.BINARY_EXPRESSION__OWNED_LEFT_OPERAND, (EObject) createdPowExpression, "", "2")); //$NON-NLS-1$ //$NON-NLS-2$
                    subsubCmd.append(newLiteralNumericValue(DatavaluePackage.Literals.BINARY_EXPRESSION__OWNED_RIGHT_OPERAND, (EObject) createdPowExpression, "", "64")); //$NON-NLS-1$ //$NON-NLS-2$
                    return subsubCmd;
                  }
                }
                return new IdentityCommand();
              }
            });
            subCmd.append(newLiteralNumericValue(DatavaluePackage.Literals.BINARY_EXPRESSION__OWNED_RIGHT_OPERAND, (EObject) createdExpression, "", "1")); //$NON-NLS-1$ //$NON-NLS-2$
            return subCmd;
          }
        }
        return new IdentityCommand();
      }
    });

    return cmd;
  }
}
