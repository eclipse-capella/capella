/*******************************************************************************
 * Copyright (c) 2013, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.compare;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.diffmerge.sirius.SiriusMatchPolicy;
import org.eclipse.emf.diffmerge.structures.common.comparable.ComparableTreeMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.libraries.LibrariesPackage;
import org.polarsys.capella.common.libraries.LibraryReference;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.KeyValue;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.Library;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentRealization;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datavalue.BinaryExpression;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.information.datavalue.ValuePart;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.kitalpha.ad.metadata.metadata.ViewpointReference;

/**
 * A multi-criteria match policy for Capella.
 * @author Olivier Constant
 */
public class CapellaMatchPolicy extends SiriusMatchPolicy {
  
  /** A criterion for semantic matching of technical elements */
  public static final FineGrainedMatchCriterion CRITERION_INTRINSIC_ID_SID =
      new FineGrainedMatchCriterion(MatchCriterionKind.INTRINSIC_ID,
          Messages.CapellaMatchPolicy_Criterion_SIDs,
          Messages.CapellaMatchPolicy_Criterion_SIDs_Tooltip);
  
  /** A criterion for semantic matching of technical elements */
  public static final FineGrainedMatchCriterion CRITERION_SEMANTICS_TECHNICALELEMENTS =
      new FineGrainedMatchCriterion(MatchCriterionKind.SEMANTICS,
          Messages.CapellaMatchPolicy_Criterion_Technical,
          Messages.CapellaMatchPolicy_Criterion_Technical_Tooltip);
  
  /** A criterion for semantic matching of technical elements */
  public static final FineGrainedMatchCriterion CRITERION_SEMANTICS_P2L =
      new FineGrainedMatchCriterion(MatchCriterionKind.SEMANTICS,
          Messages.CapellaMatchPolicy_Criterion_P2L,
          Messages.CapellaMatchPolicy_Criterion_P2L_Tooltip);
  
  /** A criterion for name-based matching of exchanges and links */
  public static final FineGrainedMatchCriterion CRITERION_QNAMES_EXCHANGES =
      new FineGrainedMatchCriterion(MatchCriterionKind.NAME,
          Messages.CapellaMatchPolicy_Criterion_ExchangeEnds,
          Messages.CapellaMatchPolicy_Criterion_ExchangeEnds_Tooltip);
  
  /** The string identifying the Capella project approach configuration */
  protected static final String CAPELLA_PROJECT_APPROACH = "projectApproach"; //$NON-NLS-1$
  
  /** The strings identifying the Capella progress status literals */
  protected static final Collection<String> CAPELLA_PROGRESS_STATUS_LITERALS = Arrays.asList(
      "DRAFT", "TO_BE_REVIEWED", "TO_BE_DISCUSSED", "REWORK_NECESSARY", "UNDER_REWORK", "REVIEWED_OK"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
 
  /** The strings identifying the Capella predefined types */
  protected static final Collection<String> CAPELLA_PREDEFINED_TYPE_NAMES = Arrays.asList(
      NamingConstants.PredefinedTypesCmd_boolean_name,
      NamingConstants.PredefinedTypesCmd_byte_name,
      NamingConstants.PredefinedTypesCmd_char_name,
      NamingConstants.PredefinedTypesCmd_double_name,
      NamingConstants.PredefinedTypesCmd_float_name,
      NamingConstants.PredefinedTypesCmd_hexadecimal_name,
      NamingConstants.PredefinedTypesCmd_integer_name,
      NamingConstants.PredefinedTypesCmd_long_name,
      NamingConstants.PredefinedTypesCmd_longLong_name,
      NamingConstants.PredefinedTypesCmd_short_name,
      NamingConstants.PredefinedTypesCmd_string_name,
      NamingConstants.PredefinedTypesCmd_unsignedInteger_name,
      NamingConstants.PredefinedTypesCmd_unsignedLong_name,
      NamingConstants.PredefinedTypesCmd_unsignedLongLong_name,
      NamingConstants.PredefinedTypesCmd_unsignedShort_name
      );
  
  /** The strings identifying the Capella predefined boolean literals */
  protected static final Collection<String> CAPELLA_PREDEFINED_BOOLEAN_LITERALS = Arrays.asList(
      NamingConstants.PredefinedTypesCmd_trueValue_name,
      NamingConstants.PredefinedTypesCmd_falseValue_name);
  
  /** A representation of the corresponding semantic property */
  protected static final String SEMANTIC_ID_END1_PROPERTY = "~END1"; //$NON-NLS-1$
  /** A representation of the corresponding semantic property */
  protected static final String SEMANTIC_ID_END2_PROPERTY = "~END2"; //$NON-NLS-1$
  
  /**
   * The set of Capella containment references which are discriminating w.r.t. their children
   * even though they are "isMany"
   */
  private static Collection<EReference> DISCRIMINATING_CONTAINMENTS = null;
  
  /**
   * The set of Capella types which may normally have no more than one instance per model
   * or library.
   */
  private static Collection<EClass> UNIQUELY_OCCURRING_TYPES = null;
  
  /**
   * The set of Capella types which are NamedElements but whose name is not significant,
   * typically because instances have automatically-generated names.
   */
  private static Collection<EClass> UNSIGNIFICANT_NAMEDELEMENT_SUBTYPES = null;
  /**
   * Default constructor
   */
  public CapellaMatchPolicy() {
    super();
  }
  
  /**
   * Constructor
   * @param policy_p a non-null policy whose configuration to clone
   */
  public CapellaMatchPolicy(CapellaMatchPolicy policy_p) {
    this();
    update(policy_p);
  }
  
  /**
  /**
   * @see org.eclipse.emf.diffmerge.sirius.SiriusMatchPolicy#getAvailableFineGrainedCriteria()
   */
  @Override
  public List<FineGrainedMatchCriterion> getAvailableFineGrainedCriteria() {
    List<FineGrainedMatchCriterion> result = super.getAvailableFineGrainedCriteria();
    result.add(CRITERION_QNAMES_EXCHANGES);
    int pos = result.indexOf(CRITERION_SEMANTICS_DEFAULTCONTENTS);
    result.add(pos+1, CRITERION_SEMANTICS_P2L);
    result.add(pos+2, CRITERION_SEMANTICS_TECHNICALELEMENTS);
    result.add(CRITERION_INTRINSIC_ID_SID);
    return result;
  }
  
  /**
   * Return an ID for the Capella model root of the given element, if applicable.
   * If the element is a root, then null is returned because the "default content"
   * criterion is not applicable.
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   * @return a potentially null object
   */
  protected String getCapellaDefaultContentRootSemanticID(EObject element_p,
      ITreeDataScope<EObject> scope_p) {
    String result = null;
    EObject root = EcoreUtil.getRootContainer(element_p);
    if (root != null && root != element_p)
      result = getStructureBasedRootQualifier(root, scope_p);
    return result;
  }
  
  /**
   * Return an ID based on the semantics of the given element as part of the
   * Capella Project structure, if applicable
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   * @return a potentially null object
   */
  protected String getCapellaDefaultContentSemanticID(
      EObject element_p, ITreeDataScope<EObject> scope_p) {
    String result = null;
    final String rootPrefix =
        getCapellaDefaultContentRootSemanticID(element_p, scope_p);
    if (rootPrefix != null) {
      EClass type = element_p.eClass();
      if (getUniquelyOccurringTypes().contains(type)) {
        // Unique instance of its structure-related type in the model
        result = rootPrefix + getQualificationSeparatorDefault() + type.getName();
      } else {
        // Possibly multiple instances of the type
        if (isUniqueModelingArchitectureChild(element_p, scope_p) ||
            isRootComponent(element_p, scope_p) ||
            isSystemComponentAllocation(element_p, scope_p) ||
            isRootFunctionRealization(element_p, scope_p) ||
            isSystemStateMachine(element_p, scope_p) ||
            isSystemStateMachineMainRegion(element_p, scope_p)) {
          // Child of architecture, System component allocation,
          // Root function realization, System state machine & main region
          result = getContainerRelativeID(element_p, scope_p, type.getName(), null);
        } else if (isPredefinedTypePackage(element_p, scope_p) ||
            isPredefinedType(element_p, scope_p) ||
            isPredefinedBooleanLiteral(element_p, scope_p) ||
            isProgressStatusLiteral(element_p, scope_p)) {
          // Predefined Types package, Predefined types & literals,
          // Progress status enumeration literals
          result = getContainerRelativeID(
              element_p, scope_p, ((AbstractNamedElement)element_p).getName(), null);
        } else if (isSystemComponentPart(element_p, scope_p)) {
          // Part for System in ComponentContext in all architectures
          result = getContainerRelativeID(element_p, scope_p, "SystemPart", null); //$NON-NLS-1$
        } else if (isRootFunction(element_p, scope_p)) {
          // Root function
          result = getContainerRelativeID(element_p, scope_p, "Root" + type.getName(), null); //$NON-NLS-1$
        } else if (isPredefinedTypeProperty(element_p, scope_p)) {
          // Property of predefined type
          result = getContainerRelativeID(
              element_p, scope_p, getContainment(element_p, scope_p).getName(), null);
        } else if (isProjectApproach(element_p, scope_p)) {
          // Project approach key-value
          result = rootPrefix + getQualificationSeparatorSemantics() +
              ((KeyValue)element_p).getKey();
        } else if (isProgressStatus(element_p, scope_p)) {
          // Progress status enumeration type
          result = rootPrefix + getQualificationSeparatorSemantics() +
              CapellaProjectHelper.PROGRESS_STATUS_KEYWORD;
        }
      }
    }
    return result;
  }
  
  /**
   * Return the set of Capella containment references which are discriminating w.r.t. their children
   * even though they are "isMany"
   * @return a non-null collection
   */
  protected Collection<EReference> getDiscriminatingContainments() {
    if (DISCRIMINATING_CONTAINMENTS == null)
      DISCRIMINATING_CONTAINMENTS = Arrays.asList(
          // Architecture allocations
          CtxPackage.eINSTANCE.getSystemAnalysis_OwnedOperationalAnalysisRealizations(),
          LaPackage.eINSTANCE.getLogicalArchitecture_OwnedSystemAnalysisRealizations(),
          PaPackage.eINSTANCE.getPhysicalArchitecture_OwnedLogicalArchitectureRealizations(),
          EpbsPackage.eINSTANCE.getEPBSArchitecture_OwnedPhysicalArchitectureRealizations()
          );
    return DISCRIMINATING_CONTAINMENTS;
  }
  
  /**
   * Return a unique name for the given named element which has no naming requirement
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   * @return a potentially null string
   */
  protected String getFreelyNamedElementUniqueName(AbstractNamedElement element_p,
      ITreeDataScope<EObject> scope_p) {
    String result = null;
    if (useFineGrainedCriterion(CRITERION_QNAMES_EXCHANGES)) {
      if (element_p instanceof FunctionalExchange) {
        // Functional Exchange
        FunctionalExchange casted = (FunctionalExchange)element_p;
        result = getTwoEndedElementUniqueName(
            casted, casted.getSourceFunctionOutputPort(), casted.getTargetFunctionInputPort(),
            scope_p);
      } else if (element_p instanceof ComponentExchange) {
        // Component Exchange
        ComponentExchange casted = (ComponentExchange)element_p;
        result = getTwoEndedElementUniqueName(
            casted, casted.getSourcePort(), casted.getTargetPort(), scope_p);
      } else if (element_p instanceof PhysicalLink) {
        // Physical Link
        PhysicalLink casted = (PhysicalLink)element_p;
        result = getTwoEndedElementUniqueName(
            casted, casted.getSourcePhysicalPort(), casted.getTargetPhysicalPort(), scope_p);
      }
    }
    if (result == null)
      result = element_p.getName();
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy#getIntrinsicID(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  @Override
  protected String getIntrinsicID(EObject element_p, ITreeDataScope<EObject> scope_p) {
    String result = null;
    if (element_p instanceof ModelElement && useFineGrainedCriterion(CRITERION_INTRINSIC_ID_SID)) {
      result = ((ModelElement) element_p).getSid();
    }
    if (result == null) {
      result = super.getIntrinsicID(element_p, scope_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.sirius.SiriusMatchPolicy#getName(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  @Override
  protected String getName(EObject element_p, ITreeDataScope<EObject> scope_p) {
    String result = null;
    if (element_p instanceof AbstractNamedElement &&
        !isInstanceOf(element_p, getUnsignificantNamedElementSubtypes())) {
      AbstractNamedElement namedElement = (AbstractNamedElement)element_p;
      String rawName = namedElement.getName();
      UniqueNameRequirementKind req = getNamingRequirement(namedElement);
      if (req == UniqueNameRequirementKind.IN_CONTAINER) {
        // Name is unique: use it
        result = rawName;
      } else if (req == UniqueNameRequirementKind.IN_CONTAINMENT) {
        // Name is unique for its containment: use it and the containment
        EReference containment = getContainment(element_p, scope_p);
        if (containment != null)
          result = containment.getName() + getQualificationSeparatorDefault() + rawName;
      } else {
        // No name uniqueness requirement
        result = getFreelyNamedElementUniqueName(namedElement, scope_p);
      }
    }
    if (result == null)
      result = super.getName(element_p, scope_p);
    return result;
  }
  
  /**
   * Return whether the given element is required to have a unique name, and if so
   * whether it is within its container or solely among its containment siblings
   * @param element_p a non-null element
   * @return a non-null object
   */
  protected UniqueNameRequirementKind getNamingRequirement(AbstractNamedElement element_p) {
    UniqueNameRequirementKind result;
    if (element_p instanceof FunctionalExchange ||
        element_p instanceof ComponentExchange ||
        element_p instanceof PhysicalLink ||
        isTechnical(element_p)) {
      result = UniqueNameRequirementKind.NONE;
    } else if (element_p instanceof Part) {
      result = UniqueNameRequirementKind.IN_CONTAINMENT;
    } else {
      result = UniqueNameRequirementKind.IN_CONTAINER;
    }
    return result;
  }
  /**
   * Name uniqueness requirement levels.
   */
  protected static enum UniqueNameRequirementKind {
    /** No requirement */
    NONE,
    /** Among all siblings of the same container */
    IN_CONTAINER,
    /** Among all siblings of the same container and containment feature */
    IN_CONTAINMENT
  }
  
  /**
   * Return a semantic ID for the given element according to the additional two
   * given elements
   * @param element_p a non-null element
   * @param end1_p a potentially null element
   * @param end2_p a potentially null element
   * @param scope_p a non-null scope that covers element_p
   * @return a potentially null object
   */
  protected String getTwoEndedElementSemanticID(
      EObject element_p, EObject end1_p, EObject end2_p, ITreeDataScope<EObject> scope_p) {
    String result = null;
    if (end1_p != null && end2_p != null) {
      String id1 = getMatchID(end1_p, scope_p);
      if (id1 != null) {
        String id2 = getMatchID(end2_p, scope_p);
        if (id2 != null) {
          String typeID = element_p.eClass().getName();
          Map<String, String> map = new ComparableTreeMap<String, String>();
          map.put(SEMANTIC_ID_TYPE_PROPERTY, typeID);
          map.put(SEMANTIC_ID_END1_PROPERTY, id1);
          map.put(SEMANTIC_ID_END2_PROPERTY, id2);
          result = map.toString();
        }
      }
    }
    return result;
  }
  
  /**
   * Return a name for the given element which is characterized by the additional two
   * given elements
   * @param element_p a non-null element
   * @param end1_p a potentially null element
   * @param end2_p a potentially null element
   * @param scope_p a non-null scope that covers element_p
   * @return a potentially null string
   */
  protected String getTwoEndedElementUniqueName(AbstractNamedElement element_p,
      EObject end1_p, EObject end2_p, ITreeDataScope<EObject> scope_p) {
    String result = null;
    if (end1_p != null && end2_p != null) {
      String qname1 = getMatchID(end1_p, scope_p);
      if (qname1 != null) {
        String qname2 = getMatchID(end2_p, scope_p);
        if (qname2 != null) {
          StringBuilder builder = new StringBuilder();
          String mainName = element_p.getName();
          builder.append(mainName);
          builder.append(" ("); //$NON-NLS-1$
          builder.append(qname1);
          builder.append("->"); //$NON-NLS-1$
          builder.append(qname2);
          builder.append(')');
          result = builder.toString();
        }
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.sirius.SiriusMatchPolicy#getSemanticID(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  @Override
  protected String getSemanticID(EObject element_p, ITreeDataScope<EObject> scope_p) {
    String result = null;
    if (useFineGrainedCriterion(CRITERION_SEMANTICS_P2L) &&
        isMainCapellaRoot(element_p, scope_p)) {
      result = getQualificationSeparatorSemantics() + "Capella"; //$NON-NLS-1$
    } else if (useFineGrainedCriterion(CRITERION_SEMANTICS_TECHNICALELEMENTS) &&
        isTechnical(element_p)) {
      result = getTechnicalElementSemanticID(element_p, scope_p);
    } else if (element_p instanceof ViewpointReference) {
      result = getViewpointReferenceSemanticID((ViewpointReference)element_p, scope_p);
    } else if (useFineGrainedCriterion(CRITERION_SEMANTICS_DEFAULTCONTENTS)) {
      result = getCapellaDefaultContentSemanticID(element_p, scope_p);
    }
    if (result == null)
      result = super.getSemanticID(element_p, scope_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy#getStructureBasedRootQualifier(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  @Override
  protected String getStructureBasedRootQualifier(EObject element_p,
      ITreeDataScope<EObject> scope_p) {
    String result = null;
    if (useFineGrainedCriterion(CRITERION_SEMANTICS_P2L) &&
        isMainCapellaRoot(element_p, scope_p)) {
      result = getSemanticID(element_p, scope_p);
    } else {
      result = super.getStructureBasedRootQualifier(element_p, scope_p);
    }
    if (result == null && element_p instanceof Library) {
      StringBuilder builder = new StringBuilder();
      builder.append(getQualificationSeparatorStructure());
      builder.append("CapellaLibrary<"); //$NON-NLS-1$
      builder.append(((Library)element_p).getId());
      builder.append('>');
      result =  builder.toString();
    }
    return result;
  }
  
  /**
   * Return an ID based on the semantics of the given technical element
   * Precondition: isTechnical(element_p)
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   * @return a potentially null object
   */
  protected String getTechnicalElementSemanticID(EObject element_p, ITreeDataScope<EObject> scope_p) {
    String result = null;
    if (element_p instanceof AbstractTrace) {
      // Abstract Trace
      AbstractTrace casted = (AbstractTrace)element_p;
      result = getTwoEndedElementSemanticID(
          casted, casted.getSourceElement(), casted.getTargetElement(), scope_p);
    } else if (element_p instanceof AbstractCapabilityInclude) {
      // Abstract Capability Include
      AbstractCapabilityInclude casted = (AbstractCapabilityInclude)element_p;
      result = getTwoEndedElementSemanticID(
          casted, casted.getInclusion(), casted.getIncluded(), scope_p);
    } else if (element_p instanceof AbstractDeploymentLink) {
      // Abstract Deployment Link
      AbstractDeploymentLink casted = (AbstractDeploymentLink)element_p;
      result = getTwoEndedElementSemanticID(
          casted, casted.getDeployedElement(), casted.getLocation(), scope_p);
    } else if (element_p instanceof CapabilityExploitation) {
      // Capability Exploitation
      CapabilityExploitation casted = (CapabilityExploitation)element_p;
      result = getTwoEndedElementSemanticID(
          casted, casted.getCapability(), casted.getMission(), scope_p);
    } else if (element_p instanceof CommunicationLink) {
      // Communication Link
      CommunicationLink casted = (CommunicationLink)element_p;
      result = getTwoEndedElementSemanticID(
          casted, casted.eContainer(), casted.getExchangeItem(), scope_p);
    } else if (element_p instanceof ComponentExchangeEnd) {
      // Component Exchange End
      ComponentExchangeEnd casted = (ComponentExchangeEnd)element_p;
      result = getTwoEndedElementSemanticID(
          casted, casted.getPart(), casted.getPort(), scope_p);
    } else if (element_p instanceof ExchangeItemAllocation) {
      // Exchange Item Allocation
      ExchangeItemAllocation casted = (ExchangeItemAllocation)element_p;
      result = getTwoEndedElementSemanticID(
          casted, casted.getAllocatingInterface(), casted.getAllocatedItem(), scope_p);
    } else if (element_p instanceof Generalization) {
      // Generalization
      Generalization casted = (Generalization)element_p;
      result = getTwoEndedElementSemanticID(
          casted, casted.getSub(), casted.getSuper(), scope_p);
    } else if (element_p instanceof InterfaceImplementation) {
      // Interface Implementation
      InterfaceImplementation casted = (InterfaceImplementation)element_p;
      result = getTwoEndedElementSemanticID(
          casted, casted.getInterfaceImplementor(), casted.getImplementedInterface(), scope_p);
    } else if (element_p instanceof InterfaceUse) {
      // Interface Use
      InterfaceUse casted = (InterfaceUse)element_p;
      result = getTwoEndedElementSemanticID(
          casted, casted.getInterfaceUser(), casted.getUsedInterface(), scope_p);
    } else if (element_p instanceof Involvement &&
        !(element_p instanceof FunctionalChainInvolvement)) {
      // Involvement
      Involvement casted = (Involvement)element_p;
      result = getTwoEndedElementSemanticID(
          casted, casted.getInvolver(), casted.getInvolved(), scope_p);
    } else if (element_p instanceof ValuePart) {
      // Value Part
      Property property = ((ValuePart)element_p).getReferencedProperty();
      if (property != null)
        result = getContainerRelativeID(
            element_p, scope_p, property.getName(), null);
    } else if (element_p instanceof KeyValue) {
      // Key Value
      result = getContainerRelativeID(
          element_p, scope_p, ((KeyValue)element_p).getKey() + '|' + ((KeyValue)element_p).getValue(), null);
    } else if (element_p instanceof PhysicalLinkEnd) {
      // Physical Link End
      PhysicalLinkEnd casted = (PhysicalLinkEnd)element_p;
      result = getTwoEndedElementSemanticID(
          casted, casted.getPart(), casted.getPort(), scope_p);
    } else if (element_p instanceof LibraryReference) {
      // Library reference
      result = getContainerRelativeID(
          element_p, scope_p, ((LibraryReference)element_p).getLibrary().getId(), null);
    }
    return result;
  }
  
  /**
   * Return the set of Capella types which may normally have no more than one instance per model
   * or library
   * @return a non-null collection
   */
  protected Collection<EClass> getUniquelyOccurringTypes() {
    if (UNIQUELY_OCCURRING_TYPES == null)
      UNIQUELY_OCCURRING_TYPES = Arrays.asList(
          // Project roots
          CapellamodellerPackage.eINSTANCE.getProject(),
          CapellamodellerPackage.eINSTANCE.getSystemEngineering(),
          // Architectures
          OaPackage.eINSTANCE.getOperationalAnalysis(),
          CtxPackage.eINSTANCE.getSystemAnalysis(),
          LaPackage.eINSTANCE.getLogicalArchitecture(),
          PaPackage.eINSTANCE.getPhysicalArchitecture(),
          EpbsPackage.eINSTANCE.getEPBSArchitecture(),
          // Libraries
          LibrariesPackage.eINSTANCE.getModelInformation()
          );
    return UNIQUELY_OCCURRING_TYPES;
  }
  
  /**
   * Return the set of Capella types which are NamedElements but whose name is not significant,
   * typically because instances have automatically-generated names
   * @return a non-null collection
   */
  protected Collection<EClass> getUnsignificantNamedElementSubtypes() {
    if (UNSIGNIFICANT_NAMEDELEMENT_SUBTYPES == null)
      UNSIGNIFICANT_NAMEDELEMENT_SUBTYPES = Arrays.asList(
          // Scenarios
          InteractionPackage.eINSTANCE.getCombinedFragment(),
          InteractionPackage.eINSTANCE.getEvent(),
          InteractionPackage.eINSTANCE.getExecution(),
          InteractionPackage.eINSTANCE.getInteractionFragment(),
          InteractionPackage.eINSTANCE.getInteractionUse(),
          InteractionPackage.eINSTANCE.getSequenceMessage(),
          InteractionPackage.eINSTANCE.getStateFragment()
          );
    return UNSIGNIFICANT_NAMEDELEMENT_SUBTYPES;
  }
  
  /**
   * Return a semantic ID for the given ViewpointReference
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   * @return a potentially null object
   */
  protected String getViewpointReferenceSemanticID(ViewpointReference element_p,
      ITreeDataScope<EObject> scope_p) {
    String result = null;
    String vpId = element_p.getVpId();
    if (vpId != null)
      result = getContainerRelativeID(element_p, scope_p, vpId, null);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy#getVisibleCriteria()
   */
  @Override
  public Collection<MatchCriterionKind> getVisibleCriteria() {
    return Arrays.asList(MatchCriterionKind.values());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.sirius.SiriusMatchPolicy#isDiscriminatingContainment(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference)
   */
  @Override
  protected boolean isDiscriminatingContainment(EObject element_p, EReference containment_p) {
    return super.isDiscriminatingContainment(element_p, containment_p) ||
        getDiscriminatingContainments().contains(containment_p);
  }
  
  /**
   * Return whether the given element can be considered as the main root of the given scope
   * @param element_p a non-null element
   * @param scope_p a non-null scope to which the element belongs
   */
  protected boolean isMainCapellaRoot(EObject element_p, ITreeDataScope<EObject> scope_p) {
    boolean result = false;
    if (element_p instanceof Project || element_p instanceof Library) {
      result = isUniqueSiblingOfItsType(element_p, scope_p);
      if (result && element_p instanceof Library) {
        // For libraries, additional constraint that there is no project
        for (EObject root : scope_p.getRoots()) {
          if (root instanceof Project && !(root instanceof Library)) {
            result = false;
            break;
          }
        }
      }
    }
    return result;
  }
  
  /**
   * Return whether the given element represents a Capella predefined type
   * @param element_p a potentially null element
   * @param scope_p a non-null scope that covers element_p
   */
  protected boolean isPredefinedBooleanLiteral(EObject element_p,
      ITreeDataScope<EObject> scope_p) {
    boolean result = false;
    if (element_p instanceof LiteralBooleanValue) {
      EObject container = getContainer(element_p, scope_p);
      if (container instanceof BooleanType && isPredefinedType(container, scope_p))
        result = CAPELLA_PREDEFINED_BOOLEAN_LITERALS.contains(
            ((LiteralBooleanValue)element_p).getName());
    }
    return result;
  }
  
  /**
   * Return whether the given element represents a Capella predefined type
   * @param element_p a potentially null element
   * @param scope_p a non-null scope that covers element_p
   */
  protected boolean isPredefinedType(EObject element_p,
      ITreeDataScope<EObject> scope_p) {
    boolean result = false;
    if (element_p instanceof DataType) {
      EObject container = getContainer(element_p, scope_p);
      if (isPredefinedTypePackage(container, scope_p))
        result = CAPELLA_PREDEFINED_TYPE_NAMES.contains(
            ((DataType)element_p).getName());
    }
    return result;
  }
  
  /**
   * Return whether the given element represents the package of predefined types
   * @param element_p a potentially null element
   * @param scope_p a non-null scope that covers element_p
   */
  protected boolean isPredefinedTypePackage(EObject element_p,
      ITreeDataScope<EObject> scope_p) {
    boolean result = false;
    if (element_p instanceof DataPkg) {
      EObject container = getContainer(element_p, scope_p);
      if (container instanceof DataPkg) {
        EObject superContainer = getContainer(container, scope_p);
        if (superContainer instanceof SystemAnalysis)
          result = NamingConstants.PredefinedTypesCmd_predefinedDataTypePkg_name.equals(
              ((DataPkg)element_p).getName());
      }
    }
    return result;
  }
  
  /**
   * Return whether the given element is a property of a Capella predefined type
   * @param element_p a potentially null element
   * @param scope_p a non-null scope that covers element_p
   */
  protected boolean isPredefinedTypeProperty(EObject element_p,
      ITreeDataScope<EObject> scope_p) {
    boolean result = false;
    if (element_p instanceof NumericValue) {
      EObject container = getContainer(element_p, scope_p);
      if (isPredefinedType(container, scope_p)) {
        // Numeric property directly owned by predefined type
        result = isInDiscriminatingContainment(element_p, scope_p);
      } else if (container instanceof BinaryExpression &&
          isInDiscriminatingContainment(container, scope_p)) {
        // Cases such as Hexadecimal::ownedMaxValue as a decomposed expression
        EObject typeCandidate = getContainer(container, scope_p);
        if (typeCandidate instanceof BinaryExpression &&
            isInDiscriminatingContainment(typeCandidate, scope_p))
          typeCandidate = getContainer(typeCandidate, scope_p); // Up to 3rd container
        result = isPredefinedType(typeCandidate, scope_p);
      }
    }
    return result;
  }
  
  /**
   * Return whether the given element represents the Capella progress status type
   * @param element_p a potentially null element
   * @param scope_p a non-null scope that covers element_p
   */
  protected boolean isProgressStatus(EObject element_p,
      ITreeDataScope<EObject> scope_p) {
    return element_p instanceof EnumerationPropertyType &&
        getContainer(element_p, scope_p) instanceof Project &&
        CapellaProjectHelper.PROGRESS_STATUS_KEYWORD.equals(
            ((EnumerationPropertyType)element_p).getName());
  }
  
  /**
   * Return whether the given element represents a Capella progress status literal
   * @param element_p a potentially null element
   * @param scope_p a non-null scope that covers element_p
   */
  protected boolean isProgressStatusLiteral(EObject element_p,
      ITreeDataScope<EObject> scope_p) {
    return element_p instanceof EnumerationPropertyLiteral &&
        isProgressStatus(getContainer(element_p, scope_p), scope_p) &&
        CAPELLA_PROGRESS_STATUS_LITERALS.contains(
            ((EnumerationPropertyLiteral)element_p).getName());
  }
  
  /**
   * Return whether the given element represents the Capella project approach
   * @param element_p a potentially null element
   * @param scope_p a non-null scope that covers element_p
   */
  protected boolean isProjectApproach(EObject element_p, ITreeDataScope<EObject> scope_p) {
    return element_p instanceof KeyValue &&
        getContainer(element_p, scope_p) instanceof Project &&
        CAPELLA_PROJECT_APPROACH.equals(((KeyValue)element_p).getKey());
  }
  
  /**
   * Return whether the given element represents the unique root function
   * @param element_p a potentially null element
   * @param scope_p a non-null scope that covers element_p
   */
  protected boolean isRootFunction(EObject element_p, ITreeDataScope<EObject> scope_p) {
    boolean result = false;
    if (element_p instanceof AbstractFunction) {
      EObject container = getContainer(element_p, scope_p);
      if (container instanceof FunctionPkg)
        result = getContainer(container, scope_p) instanceof ModellingArchitecture &&
          isUniqueSiblingOfItsType(element_p, scope_p);
    }
    return result;
  }
  
  /**
   * Return whether the given element represents a root function realization
   * @param element_p a potentially null element
   * @param scope_p a non-null scope that covers element_p
   */
  protected boolean isRootFunctionRealization(EObject element_p,
      ITreeDataScope<EObject> scope_p) {
    boolean result = false;
    if (element_p instanceof FunctionRealization) {
      FunctionRealization realization = (FunctionRealization)element_p;
      result = isRootFunction(realization.getAllocatedFunction(), scope_p) &&
          isRootFunction(realization.getAllocatingFunction(), scope_p);
    }
    return result;
  }
  
  /**
   * Return whether the given element represents the system
   * @param element_p a potentially null element
   * @param scope_p a non-null scope that covers element_p
   */
  protected boolean isRootComponent(EObject element_p, ITreeDataScope<EObject> scope_p) {
    return element_p instanceof Component &&
        BlockArchitectureExt.isRootComponent((Component)element_p);
  }
  
  /**
   * Return whether the given element represents an allocation between systems
   * @param element_p a potentially null element
   * @param scope_p a non-null scope that covers element_p
   */
  protected boolean isSystemComponentAllocation(EObject element_p,
      ITreeDataScope<EObject> scope_p) {
    boolean result = false;
    if (element_p instanceof ComponentRealization) {
      ComponentRealization allocation = (ComponentRealization)element_p;
      result = isRootComponent(allocation.getRealizedComponent(), scope_p) &&
          isRootComponent(allocation.getRealizingComponent(), scope_p);
    } else if (element_p instanceof PhysicalArtifactRealization) {
      PhysicalArtifactRealization allocation = (PhysicalArtifactRealization)element_p;
      result = isRootComponent(allocation.getRealizedPhysicalArtifact(), scope_p) &&
          isRootComponent(allocation.getRealizingConfigurationItem(), scope_p);
    }
    return result;
  }

  /**
   * Return whether the given element is a part that represents the system
   * @param element_p a potentially null element
   * @param scope_p a non-null scope that covers element_p
   */
  protected boolean isSystemComponentPart(EObject element_p, ITreeDataScope<EObject> scope_p) {
    boolean result = false;
    if (element_p instanceof Part) {
      Part part = (Part) element_p;
      result = isRootComponent(part.getType(), scope_p);
    }
    return result;
  }
  
  /**
   * Return whether the given element represents the unique state machine of the system
   * @param element_p a potentially null element
   * @param scope_p a non-null scope that covers element_p
   */
  protected boolean isSystemStateMachine(EObject element_p,
      ITreeDataScope<EObject> scope_p) {
    boolean result = false;
    if (element_p instanceof StateMachine) {
      EObject container = getContainer(element_p, scope_p);
      result = container instanceof SystemComponent &&
          isRootComponent(container, scope_p) && isUniqueSiblingOfItsType(element_p, scope_p);
    }
    return result;
  }
  
  /**
   * Return whether the given element represents the main region of the state machine of the system
   * @param element_p a potentially null element
   * @param scope_p a non-null scope that covers element_p
   */
  protected boolean isSystemStateMachineMainRegion(EObject element_p,
      ITreeDataScope<EObject> scope_p) {
    return element_p instanceof Region &&
        isSystemStateMachine(getContainer(element_p, scope_p), scope_p) &&
        isUniqueSiblingOfItsType(element_p, scope_p);
  }
  
  /**
   * Return whether the given element is considered technical
   * @param element_p a non-null element
   */
  protected boolean isTechnical(EObject element_p) {
    return
        element_p instanceof AbstractTrace ||
        element_p instanceof AbstractCapabilityInclude ||
        element_p instanceof AbstractDeploymentLink ||
        element_p instanceof CapabilityExploitation ||
        element_p instanceof CommunicationLink ||
        element_p instanceof ComponentExchangeEnd ||
        element_p instanceof ExchangeItemAllocation ||
        element_p instanceof Generalization ||
        element_p instanceof InterfaceImplementation ||
        element_p instanceof InterfaceUse ||
        (element_p instanceof Involvement && !(element_p instanceof FunctionalChainInvolvement)) ||
        element_p instanceof ValuePart ||
        element_p instanceof KeyValue ||
        element_p instanceof LibraryReference ||
        element_p instanceof PhysicalLinkEnd;
  }
  
  /**
   * Return whether the given element is directly owned by a modeling architecture
   * in such a way that it can be uniquely identified
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   */
  protected boolean isUniqueModelingArchitectureChild(EObject element_p,
      ITreeDataScope<EObject> scope_p) {
    return getContainer(element_p, scope_p) instanceof ModellingArchitecture &&
        isInDiscriminatingContainment(element_p, scope_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.config.IConfigurablePolicy#copy()
   */
  @Override
  public CapellaMatchPolicy copy() {
    return new CapellaMatchPolicy(this);
  }
}
