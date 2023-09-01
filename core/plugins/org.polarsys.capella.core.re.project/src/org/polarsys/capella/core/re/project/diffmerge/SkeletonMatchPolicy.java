/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.project.diffmerge;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.libraries.LibrariesPackage;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.KeyValue;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.data.information.datavalue.BinaryExpression;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralStringValue;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;

/**
 * A match policy that generates a unique predictable match ID for every element of the Capella Project Skeleton, and
 * uses the element ID for every other element, so it has these properties:
 *
 * - Comparing a new Capella Project A with a new Capella Project B yields no difference - Comparing a Project X with a
 * new Capella Project Y yields the elements that were added to X after the project was created.
 *
 * FIXME this match policy exists also in the SSS Multiphase Transition Addon, as MultiphaseMatchpolicy. It's a pretty
 * useful match policy, so this should be unified and added to capella core.
 */
public class SkeletonMatchPolicy implements IMatchPolicy<EObject> {

  private final Map<Object, String> matchIDs = new HashMap<>();

  private enum PredefinedType {

    PREDEFINED_DATA_PKG(NamingConstants.PredefinedTypesCmd_predefinedDataTypePkg_name,
        InformationPackage.Literals.DATA_PKG), PREDEFINED_BOOLEAN(NamingConstants.PredefinedTypesCmd_boolean_name,
            DatatypePackage.Literals.BOOLEAN_TYPE), PREDEFINED_BYTE(NamingConstants.PredefinedTypesCmd_byte_name,
                DatatypePackage.Literals.NUMERIC_TYPE), PREDEFINED_CHAR(NamingConstants.PredefinedTypesCmd_char_name,
                    DatatypePackage.Literals.STRING_TYPE), PREDEFINED_DOUBLE(
                        NamingConstants.PredefinedTypesCmd_double_name,
                        DatatypePackage.Literals.NUMERIC_TYPE), PREDEFINED_FLOAT(
                            NamingConstants.PredefinedTypesCmd_float_name,
                            DatatypePackage.Literals.NUMERIC_TYPE), PREDEFINED_HEXADECIMAL(
                                NamingConstants.PredefinedTypesCmd_hexadecimal_name,
                                DatatypePackage.Literals.NUMERIC_TYPE), PREDEFINED_INTEGER(
                                    NamingConstants.PredefinedTypesCmd_integer_name,
                                    DatatypePackage.Literals.NUMERIC_TYPE), PREDEFINED_LONG(
                                        NamingConstants.PredefinedTypesCmd_long_name,
                                        DatatypePackage.Literals.NUMERIC_TYPE), PREDEFINED_LONGLONG(
                                            NamingConstants.PredefinedTypesCmd_longLong_name,
                                            DatatypePackage.Literals.NUMERIC_TYPE), PREDEFINED_SHORT(
                                                NamingConstants.PredefinedTypesCmd_short_name,
                                                DatatypePackage.Literals.NUMERIC_TYPE), PREDEFINED_STRING(
                                                    NamingConstants.PredefinedTypesCmd_string_name,
                                                    DatatypePackage.Literals.STRING_TYPE), PREDEFINED_UNSIGNED_INTEGER(
                                                        NamingConstants.PredefinedTypesCmd_unsignedInteger_name,
                                                        DatatypePackage.Literals.NUMERIC_TYPE), PREDEFINED_UNSIGNED_SHORT(
                                                            NamingConstants.PredefinedTypesCmd_unsignedShort_name,
                                                            DatatypePackage.Literals.NUMERIC_TYPE), PREDEFINED_UNSIGNED_LONG_LONG(
                                                                NamingConstants.PredefinedTypesCmd_unsignedLongLong_name,
                                                                DatatypePackage.Literals.NUMERIC_TYPE), PREDEFINED_UNSIGNED_LONG(
                                                                    NamingConstants.PredefinedTypesCmd_unsignedLong_name,
                                                                    DatatypePackage.Literals.NUMERIC_TYPE);

    private final String name;
    private final EClass type;

    PredefinedType(String name, EClass type) {
      this.name = name;
      this.type = type;
    }

    static PredefinedType getPredefinedType(EObject e) {
      PredefinedType result = null;
      if ((e instanceof DataPkg) && PREDEFINED_DATA_PKG.name.equals(((DataPkg) e).getName())
          && (EcoreUtil2.getFirstContainer(e, CtxPackage.Literals.SYSTEM_ANALYSIS) != null)) {
        result = PREDEFINED_DATA_PKG;
      }

      if (result == null && ((e instanceof NumericType) || (e instanceof StringType) || (e instanceof BooleanType))) {
        for (PredefinedType key : values()) {
          if (key.name.equals(((NamedElement) e).getName()) && key.type.isInstance(e)
              && (getPredefinedType(e.eContainer()) == PREDEFINED_DATA_PKG)) {
            result = key;
          }
        }
      }

      return result;
    }

  }

  private enum RootFunction {

    ROOT_OPERATIONAL_ACTIVITY, ROOT_SYSTEM_FUNCTION, ROOT_LOGICAL_FUNCTION, ROOT_PHYSICAL_FUNCTION;

    static RootFunction getRootFunctionKey(EObject e) {
      if (isRootFunction(e)) {
        if (e instanceof OperationalActivity) {
          return ROOT_OPERATIONAL_ACTIVITY;
        }
        if (e instanceof SystemFunction) {
          return ROOT_SYSTEM_FUNCTION;
        }
        if (e instanceof LogicalFunction) {
          return ROOT_LOGICAL_FUNCTION;
        }
        if (e instanceof PhysicalFunction) {
          return ROOT_PHYSICAL_FUNCTION;
        }
      }
      return null;
    }

    private static boolean isRootFunction(EObject eObject) {
      return (eObject instanceof AbstractFunction)
          && (EcoreUtil2.getFirstContainer(eObject, FaPackage.Literals.ABSTRACT_FUNCTION) == null);
    }
  }

  public SkeletonMatchPolicy() {

    Collection<ENamedElement> uniqueKeys = Arrays.asList(CapellamodellerPackage.Literals.PROJECT,
        CapellamodellerPackage.Literals.LIBRARY, OaPackage.Literals.OPERATIONAL_ANALYSIS,
        OaPackage.Literals.OPERATIONAL_ANALYSIS__OWNED_CONCEPT_PKG,
        OaPackage.Literals.OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG,
        OaPackage.Literals.OPERATIONAL_ANALYSIS__OWNED_ROLE_PKG, CtxPackage.Literals.SYSTEM_ANALYSIS,
        CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_MISSION_PKG,
        CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_SYSTEM_COMPONENT_PKG,
        CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_OPERATIONAL_ANALYSIS_REALIZATIONS,
        LaPackage.Literals.LOGICAL_ARCHITECTURE, LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG,
        LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_SYSTEM_ANALYSIS_REALIZATIONS,
        PaPackage.Literals.PHYSICAL_ARCHITECTURE,
        PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_LOGICAL_ARCHITECTURE_REALIZATIONS,
        PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG, EpbsPackage.Literals.EPBS_ARCHITECTURE,
        EpbsPackage.Literals.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM_PKG,
        EpbsPackage.Literals.EPBS_ARCHITECTURE__OWNED_PHYSICAL_ARCHITECTURE_REALIZATIONS,
        CapellamodellerPackage.Literals.PROJECT__OWNED_MODEL_ROOTS, LibrariesPackage.Literals.MODEL_INFORMATION);

    for (ENamedElement o : uniqueKeys) {
      matchIDs.put(o, EcoreUtil.getURI(o).toString());
    }

    Collection<EClass> allBlockArchitectures = Arrays.asList(OaPackage.Literals.OPERATIONAL_ANALYSIS,
        CtxPackage.Literals.SYSTEM_ANALYSIS, LaPackage.Literals.LOGICAL_ARCHITECTURE,
        PaPackage.Literals.PHYSICAL_ARCHITECTURE, EpbsPackage.Literals.EPBS_ARCHITECTURE);

    for (EClass c : allBlockArchitectures) {

      Key key = new Key(c, CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG);
      matchIDs.put(key, key.toString());

      key = new Key(c, CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_DATA_PKG);
      matchIDs.put(key, key.toString());

      key = new Key(c, CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG);
      matchIDs.put(key, key.toString());

      key = new Key(c, FaPackage.Literals.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTION_PKG);
      matchIDs.put(key, key.toString());

      key = new Key(c, CsPackage.Literals.BLOCK_ARCHITECTURE__SYSTEM);
      matchIDs.put(key, key.toString());
    }

    for (RootFunction k : RootFunction.values()) {
      matchIDs.put(k, k.toString());
    }

    for (PredefinedType k : PredefinedType.values()) {
      matchIDs.put(k, k.toString());
    }

  }

  private static final class Key {

    private final EClass eContainerClass;
    private final EStructuralFeature eContainingFeature;

    private Key(EClass eContainerClass, EStructuralFeature eContainingFeature) {
      this.eContainerClass = eContainerClass;
      this.eContainingFeature = eContainingFeature;
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(new Object[] { eContainerClass, eContainingFeature });
    }

    @Override
    public boolean equals(Object obj) {
      boolean result = false;
      if (obj != null && obj.getClass() == Key.class) {
        result = (eContainerClass == ((Key) obj).eContainerClass)
            && (eContainingFeature == ((Key) obj).eContainingFeature);
      }
      return result;
    }

    @Override
    public String toString() {
      return EcoreUtil.getURI(eContainerClass) + ";" + EcoreUtil.getURI(eContainingFeature); //$NON-NLS-1$
    }
  }

  @Override
  public String getMatchID(final EObject element_p, ITreeDataScope<EObject> scope_p) {

    String result = null;

    if ((element_p instanceof AbstractTrace) && (((AbstractTrace) element_p).getSourceElement() != null)
        && (((AbstractTrace) element_p).getTargetElement() != null)) {
      result = String.format("t[(%s)%s=>%s]", element_p.eClass().getName(), //$NON-NLS-1$
          getMatchID(((AbstractTrace) element_p).getSourceElement(), scope_p), getMatchID(((AbstractTrace) element_p).getTargetElement(), scope_p));
    }

    // otherwise we test for some unique characteristics
    if (result == null) {
      result = matchIDs.get(element_p.eClass());
    }

    if (result == null) {
      result = matchIDs.get(element_p.eContainingFeature());
    }

    if ((result == null) && (element_p.eContainer() != null)) {
      result = matchIDs.get(new Key(element_p.eContainer().eClass(), element_p.eContainingFeature()));
    }

    // otherwise, we test if the element is one of the root functions
    if (result == null) {
      result = matchIDs.get(RootFunction.getRootFunctionKey(element_p));
    }

    // or one of the predefined types
    if (result == null) {
      result = matchIDs.get(PredefinedType.getPredefinedType(element_p));
    }

    // or the root system
    if (element_p instanceof Component
        && element_p.equals(BlockArchitectureExt.getRootBlockArchitecture(element_p).getSystem())) {
      result = matchIDs.get(new Key(BlockArchitectureExt.getRootBlockArchitecture(element_p).eClass(),
          CsPackage.Literals.BLOCK_ARCHITECTURE__SYSTEM));
    }

    if ((result == null) && (element_p.eContainer() != null)) {

      if (element_p instanceof LiteralBooleanValue) {
        String parentMatch = getMatchID(element_p.eContainer(), scope_p);
        if (parentMatch != null) {
          result = parentMatch + ";" + element_p.eContainingFeature().getName() + "@boolean:" //$NON-NLS-1$ //$NON-NLS-2$
              + ((LiteralBooleanValue) element_p).getName();
        }
      }
      if (element_p instanceof LiteralStringValue) {
        String parentMatch = getMatchID(element_p.eContainer(), scope_p);
        if (parentMatch != null) {
          result = parentMatch + ";" + element_p.eContainingFeature().getName() + ";string:" //$NON-NLS-1$ //$NON-NLS-2$
              + ((LiteralStringValue) element_p).getName();
        }
      }
      if (element_p instanceof LiteralNumericValue) {
        String parentMatch = getMatchID(element_p.eContainer(), scope_p);
        if (parentMatch != null) {
          result = parentMatch + ";" + element_p.eContainingFeature().getName() + ";numeric: " //$NON-NLS-1$ //$NON-NLS-2$
              + ((LiteralNumericValue) element_p).getValue();
        }
      }
      if (element_p instanceof BinaryExpression) {
        String parentMatch = getMatchID(element_p.eContainer(), scope_p);
        if (parentMatch != null) {
          result = parentMatch + ";" + (((InternalEObject) element_p.eContainer()) //$NON-NLS-1$
              .eURIFragmentSegment(element_p.eContainingFeature(), element_p));
        }
      }
      if (element_p instanceof EnumerationPropertyLiteral) {
        String parentMatch = getMatchID(element_p.eContainer(), scope_p);
        if (parentMatch != null) {
          result = parentMatch + ";" + ((EnumerationPropertyLiteral) element_p).getName(); //$NON-NLS-1$
        }
      }
      if (element_p instanceof EnumerationPropertyType) {
        String parentMatch = getMatchID(element_p.eContainer(), scope_p);
        if (parentMatch != null) {
          result = parentMatch + ";" + ((EnumerationPropertyType) element_p).getName(); //$NON-NLS-1$
        }
      }
      if (element_p instanceof KeyValue) {
        String parentMatch = getMatchID(element_p.eContainer(), scope_p);
        if (parentMatch != null) {
          result = parentMatch + ";" + element_p.eContainingFeature().getName() + ";" + ((KeyValue) element_p).getKey(); //$NON-NLS-1$ //$NON-NLS-2$
        }
      }

      // root component parts
      if (element_p instanceof Part) {
        Type type = ((Part) element_p).getType();
        if ((type != null) && (type.getTypedElements().size() == 1)) {
          String typeMatchId = matchIDs.get(type.eContainingFeature());
          if (typeMatchId != null) {
            result = typeMatchId + "Part"; //$NON-NLS-1$
          }
        }
      }
      if (element_p instanceof StateMachine) {
        String parentMatch = getMatchID(element_p.eContainer(), scope_p);
        if (parentMatch != null) {
          result = parentMatch + ";stateMachine:" + ((StateMachine) element_p).getName(); //$NON-NLS-1$
        }
      }
      if (element_p instanceof Region) {
        String parentMatch = getMatchID(element_p.eContainer(), scope_p);
        if (parentMatch != null) {
          result = parentMatch + ";region:" + ((Region) element_p).getName(); //$NON-NLS-1$
        }
      }

    }

    if (result == null) {
      result = "UNMATCHABLE-ELEMENT-" + EcoreUtil.getID(element_p); //$NON-NLS-1$
    }

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Comparator<?> getMatchIDComparator() {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean keepMatchIDs() {
    return true;
  }

}
