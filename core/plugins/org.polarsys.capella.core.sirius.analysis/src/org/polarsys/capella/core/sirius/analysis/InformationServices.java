/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.StringHelper;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.ui.toolkit.dialogs.TransferTreeListDialog;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.AbstractExchangeItemPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemPkg;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.helpers.information.services.CommunicationLinkExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.AssociationPkg;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.ElementKind;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.information.ParameterDirection;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.UnionProperty;
import org.polarsys.capella.core.data.information.Unit;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.PhysicalQuantity;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.information.util.PropertyNamingHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;
import org.polarsys.capella.core.libraries.queries.QueryIdentifierConstants;
import org.polarsys.capella.core.model.helpers.AbstractDependenciesPkgExt;
import org.polarsys.capella.core.model.helpers.AbstractExchangeItemPkgExt;
import org.polarsys.capella.core.model.helpers.AssociationExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.DataTypeExt;
import org.polarsys.capella.core.model.helpers.ExchangeItemExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.providers.DependencyLabelProvider;

/**
 * Services for Information.
 */
public class InformationServices {

  /**
   * 
   */
  private static final String THROWS_WITH_WHITE_SPACE_CHAR = ICommonConstants.WHITE_SPACE_CHARACTER + "throws" //$NON-NLS-1$
      + ICommonConstants.WHITE_SPACE_CHARACTER;
  /**
   * 
   */
  private static final String RETURN_WITH_WHITE_SPACE_CHAR = ICommonConstants.WHITE_SPACE_CHARACTER + "returns" //$NON-NLS-1$
      + ICommonConstants.WHITE_SPACE_CHARACTER;
  private static InformationServices service = null;
  public String COMMA_WITH_SPACE = ", "; //$NON-NLS-1$

  public static InformationServices getService() {
    if (service == null) {
      service = new InformationServices();
    }
    return service;
  }

  /**
   * Gets types which can be set by the parameters. used in common.odesign
   * 
   * @param parameter
   *          the given parameter
   * @return the parameter types
   */
  public List<EObject> getExchangeItemElementTypes(ExchangeItemElement parameter) {
    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(
        InformationPackage.Literals.EXCHANGE_ITEM_ELEMENT, CapellacorePackage.Literals.TYPED_ELEMENT__TYPE);
    if (query != null) {
      return query.getAvailableElements(parameter);
    }
    return Collections.emptyList();
  }

  /**
   * Gets types which can be set by the parameters.
   * 
   * @param parameter
   *          the given parameter
   * @return the parameter types
   */
  public List<EObject> getParameterTypes(Parameter parameter) {
    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(InformationPackage.Literals.PARAMETER,
        ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);
    if (query != null) {
      return query.getAvailableElements(parameter);
    }
    return Collections.emptyList();
  }

  /**
   * Gets types which can be set by the property.
   * 
   * @param property
   *          the given property
   * @return the property types
   */
  public List<EObject> getPropertyTypes(Property property) {
    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(InformationPackage.Literals.PROPERTY,
        ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);
    if (query != null) {
      return query.getAvailableElements(property);
    }
    return Collections.emptyList();
  }

  /**
   * Convert the parameters to a String.
   * 
   * @param eventOperation
   *          the current operation
   * @return a string representing a list of parameter : param1:type1[0..*], param2:type2,
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  private String parametersToString(final AbstractEventOperation eventOperation) {
    StringBuilder sb = new StringBuilder();
    StringBuilder sbReturns = new StringBuilder();
    StringBuilder sbExceptions = new StringBuilder();
    StringBuilder sbExceptionsOfOperation = new StringBuilder();

    boolean first = true;
    boolean displayParenthesisIfEmpty = false;

    List<MultiplicityElement> parameters = null;
    List<MultiplicityElement> parameterReturns = new ArrayList<>();
    List<MultiplicityElement> parameterExceptions = new ArrayList<>();
    EStructuralFeature directionFeature = null;
    EStructuralFeature typeFeature = null;
    EStructuralFeature nameFeature = ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME;

    if (eventOperation instanceof ExchangeItemAllocation) {
      ExchangeItemAllocation itemAllocation = (ExchangeItemAllocation) eventOperation;
      AbstractExchangeItem allocatedItem = itemAllocation.getAllocatedItem();
      if (allocatedItem instanceof ExchangeItem) {
        parameters = (List) ((ExchangeItem) allocatedItem).getOwnedElements();
        directionFeature = InformationPackage.Literals.EXCHANGE_ITEM_ELEMENT__DIRECTION;
        typeFeature = CapellacorePackage.Literals.TYPED_ELEMENT__TYPE;
      }
    } else if (eventOperation instanceof Operation) {
      displayParenthesisIfEmpty = true;
      Operation operation = (Operation) eventOperation;
      parameters = (List) operation.getOwnedParameters();
      directionFeature = InformationPackage.Literals.PARAMETER__DIRECTION;
      typeFeature = CapellacorePackage.Literals.TYPED_ELEMENT__TYPE;
    }

    if (parameters != null) {
      if ((!parameters.isEmpty()) || (parameters.isEmpty() && displayParenthesisIfEmpty)) {
        sb.append(ICommonConstants.PARENTHESIS_OPEN_CHARACTER);
      }

      for (MultiplicityElement currentParameter : parameters) {
        Object directionObj = currentParameter.eGet(directionFeature);
        if (directionObj instanceof ParameterDirection) {
          ParameterDirection direction = (ParameterDirection) directionObj;
          if ((direction == ParameterDirection.IN) || (direction == ParameterDirection.OUT)
              || (direction == ParameterDirection.INOUT) || (direction == ParameterDirection.UNSET)) {
            if (first) {
              first = false;
            } else {
              sb.append(COMMA_WITH_SPACE);
            }
            // In, Out, InOut, and UnSet related string
            sb.append(parameterToString(currentParameter, (AbstractType) currentParameter.eGet(typeFeature),
                (String) currentParameter.eGet(nameFeature), direction, true, true, true));
          } else if (direction == ParameterDirection.RETURN) {
            // return sting
            parameterReturns.add(currentParameter);
          } else if (direction == ParameterDirection.EXCEPTION) {
            // exception string
            parameterExceptions.add(currentParameter);
          }
        }
      }

      if ((!parameters.isEmpty()) || (parameters.isEmpty() && displayParenthesisIfEmpty)) {
        sb.append(ICommonConstants.PARENTHESIS_CLOSE_CHARACTER);
      }

    }

    // Return
    if (!parameterReturns.isEmpty()) {
      parametersToStringReturnAndException(sbReturns, typeFeature, nameFeature, parameterReturns,
          RETURN_WITH_WHITE_SPACE_CHAR);
    }
    // Exception
    if (!parameterExceptions.isEmpty()) {
      parametersToStringReturnAndException(sbExceptions, typeFeature, nameFeature, parameterExceptions,
          THROWS_WITH_WHITE_SPACE_CHAR);
    }
    // Exception of Service
    // retrieve exception if any
    boolean firstServiceExeption = true;
    boolean atLeastOneExcepIsNamed = false;
    if (eventOperation instanceof Service) {
      Service serviceOp = (Service) eventOperation;
      List<org.polarsys.capella.core.data.information.communication.Exception> thrownExceptions = serviceOp
          .getThrownExceptions();
      for (org.polarsys.capella.core.data.information.communication.Exception exceptionOp : thrownExceptions) {
        String excEleName = (String) exceptionOp.eGet(nameFeature);
        if (!excEleName.equals(ICommonConstants.EMPTY_STRING)) {
          atLeastOneExcepIsNamed = true;
        }
        if (atLeastOneExcepIsNamed && !excEleName.equals(ICommonConstants.EMPTY_STRING)) {
          // qualifier
          if (parameterExceptions.isEmpty() && firstServiceExeption) {
            sbExceptionsOfOperation.append(THROWS_WITH_WHITE_SPACE_CHAR);
            firstServiceExeption = false;
          } else {
            sbExceptionsOfOperation.append(COMMA_WITH_SPACE);
          }
          // name and type
          parameterToStringReturnAndException(sbExceptionsOfOperation, null, exceptionOp.eClass().getName(),
              exceptionOp.getName(), false, true, true, false);
        }
      }
      // since only one exception is selected from may be multiple unNamed
      if (!thrownExceptions.isEmpty() && !atLeastOneExcepIsNamed) {
        // qualifier
        if (parameterExceptions.isEmpty() && firstServiceExeption) {
          sbExceptionsOfOperation.append(THROWS_WITH_WHITE_SPACE_CHAR);
        } else {
          sbExceptionsOfOperation.append(COMMA_WITH_SPACE);
        }
        // name and type
        parameterToStringReturnAndException(sbExceptionsOfOperation, null,
            CommunicationPackage.Literals.EXCEPTION.getName(), ICommonConstants.EMPTY_STRING, false, true, true, false);
      }

    }

    // Merge all sub strings
    if (sbReturns.length() > 0) {
      sb.append(ICommonConstants.WHITE_SPACE_CHARACTER);
      sb.append(ICommonConstants.COLON_CHARACTER);
      sb.append(ICommonConstants.WHITE_SPACE_CHARACTER);
      sb.append(sbReturns);
    }
    if (sbExceptions.length() > 0) {
      sb.append(sbExceptions);
      sb.append(sbExceptionsOfOperation);
    } else if (sbExceptionsOfOperation.length() > 0) {
      sb.append(sbExceptionsOfOperation);
    }

    return sb.toString();
  }

  /**
   * Convert the given parameter to a String.
   * 
   * @param parameter
   *          : MultiplicityElement
   * @param type
   *          : Type
   * @param name
   *          : ElementName
   * @param showName
   *          : decide weather to add name in return string
   * @param showCardinality
   *          : decide weather to show cardinality in return string
   * @return : String
   */
  private String parameterToString(MultiplicityElement parameter, AbstractType type, String name,
      ParameterDirection direction, boolean showDirection, boolean showName, boolean showCardinality) {
    StringBuilder sb = new StringBuilder();
    if (showName && (null != parameter)) {
      if (parameter instanceof ExchangeItemElement) {
        ExchangeItemElement exchangeItemElement = (ExchangeItemElement) parameter;
        if (ElementKind.MEMBER.equals(exchangeItemElement.getKind())) {
          parameterToStringAppendDirection(direction, showDirection, sb);
        }
      } else if (parameter instanceof Parameter) {
        parameterToStringAppendDirection(direction, showDirection, sb);
      }

      sb.append(name);
      sb.append(ICommonConstants.COLON_CHARACTER);

    }

    if (type != null) {
      sb.append(EObjectExt.getText(type));
    } else {
      sb.append(Messages.InformationServices_Undefined);
    }

    if (showCardinality) {
      getCardinalityAsString(parameter, sb);
    }

    return sb.toString();
  }

  private void parameterToStringAppendDirection(ParameterDirection direction, boolean showDirection, StringBuilder sb) {
    if ((null != direction) && showDirection) {
      sb.append(direction.getName());
      sb.append(ICommonConstants.WHITE_SPACE_CHARACTER);
    }
  }

  /**
   * Convert the given return and exception kind parameter to string Case 1 : If one of parameter is named, the return
   * sting will avoid unNamed Elements of same kind Case 2 : If all the parameters if same type are unNamed, return the
   * type only once
   * 
   * @param sb
   *          : return string to be stored
   * @param typeFeature
   *          : to retrieve type of an element
   * @param nameFeature
   *          : to retrieve name of an element
   * @param parameters
   *          : multiplicity elements
   * @param qualifier
   *          : return or exception kind qualifier
   */
  private void parametersToStringReturnAndException(StringBuilder sb, EStructuralFeature typeFeature,
      EStructuralFeature nameFeature, List<MultiplicityElement> parameters, String qualifier) {
    // Collection multiplicityElement of same Type
    Map<String, List<MultiplicityElement>> typeToMulElesMap = new HashMap<>();
    for (MultiplicityElement currentParameter : parameters) {
      AbstractType type = (AbstractType) currentParameter.eGet(typeFeature);
      if (null != type) {
        String typeName = type.getName();
        if (typeToMulElesMap.containsKey(typeName)) {
          List<MultiplicityElement> existingMulEles = typeToMulElesMap.get(typeName);
          existingMulEles.add(currentParameter);
        } else {
          List<MultiplicityElement> newMulEles = new ArrayList<>();
          newMulEles.add(currentParameter);
          typeToMulElesMap.put(typeName, newMulEles);
        }
      }
    }

    boolean first = true;
    for (Entry<String, List<MultiplicityElement>> typeToMulEles : typeToMulElesMap.entrySet()) {
      // direction, name, Type and cardinality
      boolean atLeastOneMulEleIsNamed = false;
      List<MultiplicityElement> retrievedMulEles = typeToMulEles.getValue();
      // If one of the multiplicityElement is named, avoid unNamed Element
      // if non is named, return the Type once (instead of multiple
      // unNamed types)
      for (MultiplicityElement retrievedMulEle : retrievedMulEles) {
        String mulEleName = (String) retrievedMulEle.eGet(nameFeature);
        if (!mulEleName.equals(ICommonConstants.EMPTY_STRING)) {
          atLeastOneMulEleIsNamed = true;
        }
        if (atLeastOneMulEleIsNamed && !mulEleName.equals(ICommonConstants.EMPTY_STRING)) {
          first = setQualifier(sb, qualifier, first);
          // direction, name, type and Cardinality
          parameterToStringReturnAndException(sb, retrievedMulEle, typeToMulEles.getKey(), mulEleName, false, true,
              true, false);
        }
      }
      // since only one type is selected from may be multiple unNamed
      // Types, there is no need of cardinality
      if (!atLeastOneMulEleIsNamed) {
        first = setQualifier(sb, qualifier, first);
        parameterToStringReturnAndException(sb, null, typeToMulEles.getKey(), ICommonConstants.EMPTY_STRING, false,
            true, true, false);
      }
    }
  }

  /**
   * add the qualifier string to stringBuffer if first time else add comma
   * 
   * @param sb
   * @param qualifier
   * @param first
   * @return
   */
  private boolean setQualifier(StringBuilder sb, String qualifier, boolean first) {
    boolean flag = first;
    // qualifier
    if (flag) {
      sb.append(qualifier);
      flag = false;
    } else {
      sb.append(COMMA_WITH_SPACE);
    }
    return flag;
  }

  private String parameterToStringReturnAndException(StringBuilder sb, MultiplicityElement parameter, String type,
      String name, boolean showDirection, boolean showName, boolean showType, boolean showCardinality) {
    if (showName) {
      // Direction
      if (showDirection && parameter instanceof ExchangeItemElement) {
        ExchangeItemElement exchangeItemElement = (ExchangeItemElement) parameter;
        ParameterDirection direction = exchangeItemElement.getDirection();
        if (ElementKind.MEMBER.equals(exchangeItemElement.getKind()) && null != direction) {
          sb.append(direction.getName());
          sb.append(ICommonConstants.WHITE_SPACE_CHARACTER);
        }
      }
      sb.append(name);
      sb.append(ICommonConstants.COLON_CHARACTER);

    }
    // Type
    if (!type.equals(ICommonConstants.EMPTY_STRING)) {
      sb.append(type);
    } else {
      sb.append(Messages.InformationServices_Undefined);
    }

    if (showCardinality) {
      getCardinalityAsString(parameter, sb);
    }

    return sb.toString();
  }

  /**
   * @param parameter
   * @param sb
   */
  private void getCardinalityAsString(MultiplicityElement parameter, StringBuilder sb) {
    if (null == parameter) {
      return;
    }

    String resultMin = PropertyNamingHelper.getCardValue(parameter.getOwnedMinCard());
    String resultMax = PropertyNamingHelper.getCardValue(parameter.getOwnedMaxCard());

    if (!((parameter.getOwnedMaxCard() instanceof LiteralNumericValue)
        && (parameter.getOwnedMinCard() instanceof LiteralNumericValue) && "1".equals(resultMin) //$NON-NLS-1$
        && "1".equals(resultMax))) { //$NON-NLS-1$
      sb.append("["); //$NON-NLS-1$
      sb.append(resultMin);
      sb.append(".."); //$NON-NLS-1$
      sb.append(resultMax);
      sb.append("]"); //$NON-NLS-1$
    }
  }

  /**
   * Reconnect an association. used in common.odesign
   * 
   * @param element
   *          the given Association
   * @param source
   *          the given Classifier
   * @param target
   *          the given Classifier
   * @param property
   *          the given Property
   * @return element
   */
  public EObject reconnectAssociation(EObject element, EObject source, EObject target, EObject property) {
    // Move property according to navigability and create union property
    // instead of property if target is an union

    Association association = (Association) element;
    Classifier sourceClassifier = (Classifier) source;
    Classifier targetClassifier = (Classifier) target;
    Property sourceProperty = (Property) property;
    Property targetProperty = getOthers(sourceProperty, getAssociationProperties(association));

    sourceProperty.setAbstractType(targetClassifier);

    Property createdProperty = null;

    // If an unionproperty was present or unionproperty is required, make a
    // copy of the current property
    if ((targetClassifier instanceof Union) && !(targetProperty instanceof UnionProperty)
        && association.getNavigableMembers().contains(targetProperty)) {
      createdProperty = InformationFactory.eINSTANCE.createUnionProperty();
    } else if (!(targetClassifier instanceof Union) && (targetProperty instanceof UnionProperty)) {
      createdProperty = InformationFactory.eINSTANCE.createProperty();
    }

    if (createdProperty != null && targetProperty != null) {

      createdProperty.setName(targetProperty.getName());
      createdProperty.setAbstractType(targetProperty.getAbstractType());
      createdProperty.setAggregationKind(targetProperty.getAggregationKind());
      createdProperty.setOwnedMaxCard(targetProperty.getOwnedMaxCard());
      createdProperty.setOwnedMinCard(targetProperty.getOwnedMinCard());

      association.getOwnedMembers().add(createdProperty);

      if (association.getNavigableMembers().contains(targetProperty)) {
        targetClassifier.getOwnedFeatures().add(createdProperty);
        association.getNavigableMembers().add(createdProperty);
        association.getNavigableMembers().remove(targetProperty);
        CapellaServices.getService().removeElement(targetProperty);
      } else {
        CapellaServices.getService().removeElement(targetProperty);
      }
    }

    // Update container of navigable property
    for (Property prop : getAssociationProperties(association)) {
      if (prop.eContainer().equals(sourceClassifier)) {
        targetClassifier.getOwnedFeatures().add(prop);
      }
    }

    // move association to its correct container if necessary
    AssociationExt.moveToCorrectContainer(association);

    return association;
  }

  /**
   * convert a multiplicity to a string FIXME duplicate used in common.odesign and context.odesign
   * 
   * @param element
   * @return
   */
  public String multiplicityToString(MultiplicityElement element) {
    return PropertyNamingHelper.multiplicityToStringDisplay(element);
  }

  /**
   * used in common.odesign
   */
  public String computeLabel(Operation operation) {
    String result = EObjectExt.getText(operation);
    result = result + parametersToString(operation);
    return result;
  }

  public String computeLabel(ExchangeCategory category) {
    return EObjectExt.getText(category);
  }

  public String computeLabel(Port port) {
    return EObjectExt.getText(port);
  }

  /**
   * compute the ExchangeItemAllocation label.
   * 
   * @param item
   *          : ExchangeItemAllocation
   * @return String
   */
  public String computeLabel(ExchangeItemAllocation item) {
    if (item == null) {
      return ICommonConstants.EMPTY_STRING;
    }
    String result = getAllocatedElementName(item);
    result = result + parametersToString(item);
    return result;
  }

  public String computeLabel(Property property) {
    if (property instanceof Port) {
      // Acceleo bug: it doesn't retrieve the best method according inheritance, it
      // return the first method
      // compatible with the parameter. (so if the method computeLabel(Port) was
      // defined before this method,
      // it will return it !!
      return computeLabel((Port) property);
    }
    return EObjectExt.getText(property);
  }

  /**
   * Compute label.
   * 
   * @param link
   *          the given communicationLink
   * @return the String
   */
  public String computeLabel(CommunicationLink link) {
    return Messages.InformationServices_to + link.getExchangeItem().getName();
  }

  /**
   * Compute label.
   * 
   * @param element
   *          the given exchangeItemElement
   * @return the String
   */
  public String computeLabel(ExchangeItemElement element) {
    String result = computeLabelWithoutType(element);
    if (element.getType() != null) {
      result += " : " + (element.getType()).getName(); //$NON-NLS-1$
    }
    return result;
  }

  /**
   * Compute label without type. used in common.odesign
   * 
   * @param element
   *          the given exchangeItemElement
   * @return the String
   */
  public String computeLabelWithoutType(ExchangeItemElement element) {
    StringBuilder result = new StringBuilder(
        PropertyNamingHelper.multiplicityToStringDisplay(element) + " " + element.getName()); //$NON-NLS-1$
    EList<Property> referencedProperties = element.getReferencedProperties();
    if (!referencedProperties.isEmpty()) {
      result.append(" {"); //$NON-NLS-1$
      Iterator<Property> iterator = referencedProperties.iterator();
      while (iterator.hasNext()) {
        Property property = iterator.next();
        result.append(property.getName());
        if (iterator.hasNext()) {
          result.append(COMMA_WITH_SPACE);
        }
      }
      result.append("}"); //$NON-NLS-1$
    }
    return result.toString();
  }

  private Property getOthers(Property property, Collection<Property> properties) {
    for (Property other : properties) {
      if (other != property) {
        return other;
      }
    }
    return null;
  }

  /**
   * used in common.odesign
   * 
   * @param association
   * @return
   */
  public Property getAssociationSource(Association association) {
    int navigableMembersSize = association.getNavigableMembers().size();
    if (1 == navigableMembersSize) {
      return getOthers(association.getNavigableMembers().get(0), association.getOwnedMembers());
    } else if (2 == navigableMembersSize) {
      return association.getNavigableMembers().get(1);
    } else if (!association.getOwnedMembers().isEmpty()) {
      return association.getOwnedMembers().get(0);
    } else {
      return null;
    }
  }

  /**
   * used in common.odesign
   * 
   * @param association
   * @return
   */
  public Collection<Property> getAssociationProperties(Association association) {
    HashSet<Property> properties = new HashSet<>();
    properties.addAll(association.getOwnedMembers());
    properties.addAll(association.getNavigableMembers());
    return properties;
  }

  /**
   * used in common.odesign
   * 
   * @param association
   * @return
   */
  public Property getAssociationTarget(Association association) {
    int navigableMembersSize = association.getNavigableMembers().size();
    if ((1 == navigableMembersSize) || (2 == navigableMembersSize)) {
      return association.getNavigableMembers().get(0);
    } else if (association.getOwnedMembers().size() > 1) {
      return association.getOwnedMembers().get(1);
    } else {
      return null;
    }
  }

  public AssociationPkg getSourceClassPkg(Class sourceClass) {
    EObject sourceClassPkg = sourceClass.eContainer();
    if (sourceClassPkg instanceof AssociationPkg) {
      return (AssociationPkg) sourceClassPkg;
    }
    return null;
  }

  public AssociationPkg getAssociationPkgCandidate(Class sourceClass, Association association, Class targeClass) {
    EObject associationPkgCandidate = null;
    // if association is unidirectional return the datapkg containing the
    // association source class
    if (AssociationExt.isUnidirectional(association)) {
      associationPkgCandidate = sourceClass.eContainer();

    } else { // the association is bidirectional or has no direction => return the common
      // ancestor of the source and
      // target classes
      EObject commonAncestor = CapellaServices.getService().getCommonAncestor(sourceClass, targeClass);
      associationPkgCandidate = EcoreUtil2.getFirstContainer(commonAncestor,
          InformationPackage.Literals.ASSOCIATION_PKG);
    }
    if (associationPkgCandidate instanceof AssociationPkg) {
      return (AssociationPkg) associationPkgCandidate;
    }
    return null;
  }

  public String computeLabel(UnionProperty property) {
    String result = computeLabel((Property) property);
    EObject container = property.eContainer();
    if (container instanceof Union) {
      Union union = (Union) container;
      if (union.getDiscriminant() != null) {
        result = result + computeUnionPropertyLabelWithQualifier(property);
      }
    }

    return result;
  }

  /**
   * Return Customized label for UnioinProperty
   * 
   * @param property
   *          : UnionProperty
   * @return : customized label for unionProperty
   */
  private String computeUnionPropertyLabelWithQualifier(UnionProperty property) {
    StringBuilder result = new StringBuilder();
    EList<DataValue> qualifier = property.getQualifier();
    if ((qualifier != null) && (!qualifier.isEmpty())) {
      result.append(" { "); //$NON-NLS-1$
      for (int i = 0; i < (qualifier.size() - 1); i++) {
        result.append(EObjectExt.getText(qualifier.get(i)) + COMMA_WITH_SPACE);
      }
      result.append(EObjectExt.getText(qualifier.get(qualifier.size() - 1)));
      result.append(" }"); //$NON-NLS-1$
    }
    return result.toString();
  }

  /**
   * used in common.odesign
   * 
   * @param association
   * @return
   */
  public EObject showHideProperties(EObject context, List<Property> selectedProperties, DDiagram diagram) {

    Map<Property, AbstractDNode> visibleElements = new HashMap<>();
    for (AbstractDNode aNode : ((DNodeList) context).getOwnedElements()) {
      if (aNode.getTarget() instanceof Property) {
        visibleElements.put((Property) aNode.getTarget(), aNode);
      }
    }
    for (Entry<Property, AbstractDNode> me : visibleElements.entrySet()) {
      if (!selectedProperties.contains(me.getKey())) {
        DiagramServices.getDiagramServices().removeNodeListElementView(me.getValue());
      }
    }
    for (Property aProperty : selectedProperties) {
      if (!visibleElements.containsKey(aProperty)) {
        createPropertyView(context, aProperty, diagram);
      }
    }
    return context;
  }

  private AbstractDNode createPropertyView(EObject context, Property property, DDiagram diagram) {
    String mappingName = IMappingNameConstants.CDB_PROPERTY_MAPPING_NAME;
    NodeMapping mapping = DiagramServices.getDiagramServices().getNodeMapping(diagram, mappingName);
    return DiagramServices.getDiagramServices().createDNodeListElement(mapping, property, (DragAndDropTarget) context,
        diagram);

  }

  @Deprecated
  public EObject showHideExchangeItems(EObject context, List<CapellaElement> selectedOperations, DDiagram diagram) {
    return IBServices.getService().showHideIDExchangeItems(context, selectedOperations, diagram);
  }

  /**
   * @param pkg
   * @param context
   * @param variableName
   */
  public ExchangeItem createExchangeItem(InterfacePkg pkg, Interface context, String variableName) {
    ExchangeItem item = InformationFactory.eINSTANCE.createExchangeItem();
    pkg.getOwnedExchangeItems().add(item);

    CapellaServices.getService().creationService(item);
    CsServices.getService().setInterpreterVariable(context, variableName, item);
    return item;
  }

  /**
   * @param link
   * @param item
   */
  public CommunicationLink changeExchangeItem(CommunicationLink link, ExchangeItem item) {
    CommunicationLinkExt.changeExchangeItem(link, item);
    return link;
  }

  /**
   * Show/Hide of AbstractEventOperation in classes and interfaces used in common.odesign
   */
  public EObject showHideOperations(EObject context, List<CapellaElement> selectedOperations, DDiagram diagram) {

    Map<CapellaElement, AbstractDNode> visibleElements = new HashMap<>();
    for (AbstractDNode aNode : ((DNodeList) context).getOwnedElements()) {
      if ((aNode.getTarget() != null)
          && ((aNode.getTarget() instanceof Operation) || (aNode.getTarget() instanceof ExchangeItemElement)
              || (aNode.getTarget() instanceof ExchangeItemAllocation))) {
        visibleElements.put((CapellaElement) aNode.getTarget(), aNode);
      }
    }
    for (Entry<CapellaElement, AbstractDNode> me : visibleElements.entrySet()) {
      if (!selectedOperations.contains(me.getKey())) {
        DiagramServices.getDiagramServices().removeNodeListElementView(me.getValue());
      }
    }
    for (CapellaElement aOperation : selectedOperations) {
      if (!visibleElements.containsKey(aOperation)) {
        createOperationView(context, aOperation, diagram);
      }
    }
    return context;
  }

  @Deprecated
  public EObject showHideDataValues(EObject context, List<CapellaElement> selectedDataValues, DDiagram diagram) {
    return showHideCDBDataValues(context, selectedDataValues, diagram);
  }

  /**
   * Show/Hide of AbstractEventOperation in classes and interfaces used in common.odesign
   */
  public EObject showHideCDBDataValues(EObject context, List<CapellaElement> selectedDataValues, DDiagram diagram) {
    // Initialization
    Map<CapellaElement, AbstractDNode> visibleElements = new HashMap<>();
    // Browse the nodes content in the current context
    List<EObject> allNodes = new ArrayList<>();
    if ((null != context) && ((context instanceof DDiagram) || (context instanceof DNodeContainer))) {
      // all Nodes contained in Diagram and DNodeContainer
      allNodes.addAll(DiagramServices.getDiagramServices().getAllNodes(context));
    } else if (context instanceof DNodeList) {
      // all DNodeListElement contained in DNodeList
      EList<DDiagramElement> elements = ((DNodeList) context).getElements();
      if (null != elements) {
        allNodes.addAll(elements);
      }
    }

    for (EObject aObject : allNodes) {
      // Check if the node is a Diagram element
      if (aObject instanceof DDiagramElement) {
        // Typing the element
        DDiagramElement aNode = ((DDiagramElement) aObject);

        // Get the target of the node
        EObject nodeTarget = aNode.getTarget();

        // Check if the current node is an Abstract Node and if the
        // target is a DataValue not null
        if (nodeTarget instanceof DataValue && aNode instanceof AbstractDNode) {
          // Add DataValue to the visible element list
          visibleElements.put((CapellaElement) nodeTarget, (AbstractDNode) aNode);
        }

      }
    }
    // Browse visible element list
    for (Entry<CapellaElement, AbstractDNode> me : visibleElements.entrySet()) {
      // Check if the selected value contain the current visible element
      if (!selectedDataValues.contains(me.getKey())) {
        // Get the container of the element
        EObject container = me.getValue().eContainer();

        // Check if the container is a NodeList
        if (container instanceof DNodeList) {
          // Remove the element of the view
          DiagramServices.getDiagramServices().removeNodeListElementView(me.getValue());
        }
        // Check if the container is the Diagram or a Container Node
        else if ((container instanceof DSemanticDiagram) || (container instanceof DNodeContainer)) {
          // Remove the element of the view
          DiagramServices.getDiagramServices().removeNodeView((DNode) me.getValue());
        }
      }
    }
    // Browse the selected values
    for (CapellaElement aDataValue : selectedDataValues) {
      // Check if the visible element contain the current selected value
      if (!visibleElements.containsKey(aDataValue)) {
        // Create the view
        createDataValueView(context, aDataValue, diagram);
      }
    }
    return context;
  }

  /**
   * Create DataValue view in respected diagram
   * 
   * @param context
   * @param dataValue
   * @param diagram
   * @return Created View
   */
  private AbstractDNode createDataValueView(EObject context, CapellaElement dataValue, DDiagram diagram) {

    String mappingName = null;
    String dataTypeContentMappingName = IMappingNameConstants.CDB_DATA_TYPE_CONTENTS_MAPPING_NAME;
    String dataTypeUnitMappingName = IMappingNameConstants.CDB_DATA_TYPE_UNITS_MAPPING_NAME;
    String booleanTypeContentsMappingName = IMappingNameConstants.CDB_BOOLEAN_TYPE_BOOLEAN_CONTENTS_MAPPING_NAME;
    String booleanTypeLiteralsMappingName = IMappingNameConstants.CDB_BOOLEAN_TYPE_BOOLEAN_LITERAL_MAPPING_NAME;
    String enumerationContentsMappingName = IMappingNameConstants.CDB_ENUMERATION_ENUMERATION_CONTENTS_MAPPING_NAME;
    String enumerationLiteralsMappingName = IMappingNameConstants.CDB_ENUMERATION_ENUMERATION_LITERAL_MAPPING_NAME;
    String dataDataValueMappingName = IMappingNameConstants.CDB_DATAVALUE_MAPPING_NAME;
    String dataSubDataValueMappingName = IMappingNameConstants.CDB_SUBDATAVALUE_MAPPING_NAME;

    if (DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if ((((DSemanticDecorator) context).getTarget() instanceof NumericType)
          || (((DSemanticDecorator) context).getTarget() instanceof StringType)
          || (((DSemanticDecorator) context).getTarget() instanceof PhysicalQuantity)) {
        if (dataValue instanceof DataValue) {
          mappingName = dataTypeContentMappingName;
        } else if (dataValue instanceof Unit) {
          mappingName = dataTypeUnitMappingName;
        }
      } else if (((DSemanticDecorator) context).getTarget() instanceof BooleanType) {
        BooleanType type = (BooleanType) ((DSemanticDecorator) context).getTarget();
        EList<LiteralBooleanValue> ownedLiterals = type.getOwnedLiterals();
        if (!ownedLiterals.isEmpty() && ownedLiterals.contains(dataValue)) {
          mappingName = booleanTypeLiteralsMappingName;
        } else {
          mappingName = booleanTypeContentsMappingName;
        }
      } else if (((DSemanticDecorator) context).getTarget() instanceof Enumeration) {
        Enumeration type = (Enumeration) ((DSemanticDecorator) context).getTarget();
        EList<EnumerationLiteral> ownedLiterals = type.getOwnedLiterals();
        if (!ownedLiterals.isEmpty() && ownedLiterals.contains(dataValue)) {
          mappingName = enumerationLiteralsMappingName;
        } else {
          mappingName = enumerationContentsMappingName;
        }
      } else if (((DSemanticDecorator) context).getTarget() instanceof Class) {
        mappingName = dataSubDataValueMappingName;
      } else if (((DSemanticDecorator) context).getTarget() instanceof DataPkg) {
        mappingName = dataDataValueMappingName;
      } else if (((DSemanticDecorator) context)
          .getTarget() instanceof org.polarsys.capella.core.data.information.Collection) {
        mappingName = dataSubDataValueMappingName;
      }
    }
    NodeMapping mapping = DiagramServices.getDiagramServices().getNodeMapping(diagram, mappingName);
    if (context instanceof DNodeList) {
      return DiagramServices.getDiagramServices().createDNodeListElement(mapping, dataValue,
          (DragAndDropTarget) context, diagram);
    }

    return DiagramServices.getDiagramServices().createNode(mapping, dataValue, (DragAndDropTarget) context, diagram);
  }

  AbstractDNode createOperationView(EObject context, CapellaElement Operation, DDiagram diagram) {
    String mappingName = IMappingNameConstants.CDB_OPERATION_MAPPING_NAME;

    if (DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (((DSemanticDecorator) context).getTarget() instanceof Interface) {
        mappingName = IMappingNameConstants.CCDI_OPERATION_MAPPING_NAME;
      }
    } else if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (((DSemanticDecorator) context).getTarget() instanceof Interface) {
        mappingName = IMappingNameConstants.IDB_OPERATION_MAPPING_NAME;
      } else if (((DSemanticDecorator) context).getTarget() instanceof ExchangeItem) {
        mappingName = IMappingNameConstants.IDB_EXCHANGE_ITEM_ELEMENT_MAPPING_NAME;
      }
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      if (((DSemanticDecorator) context).getTarget() instanceof Interface) {
        mappingName = IMappingNameConstants.CCDI_OPERATION_MAPPING_NAME;
      } else if (((DSemanticDecorator) context).getTarget() instanceof ExchangeItem) {
        mappingName = IMappingNameConstants.CCDI_EXCHANGE_ITEM_ELEMENT_MAPPING_NAME;
      }
    } else if (((DSemanticDecorator) context).getTarget() instanceof Interface
        && IDiagramNameConstants.INTERFACE_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.ID_EXCHANGE_ITEM_MAPPING_NAME;
    }

    NodeMapping mapping = DiagramServices.getDiagramServices().getNodeMapping(diagram, mappingName);
    if (mapping != null) {
      return DiagramServices.getDiagramServices().createDNodeListElement(mapping, Operation,
          (DragAndDropTarget) context, diagram);
    }
    return null;
  }

  public void showCDBRelationship(EObject semantic, DDiagramContents content) {

    // Initialization
    AbstractType source = null;
    AbstractType target = null;
    EObject sourceView = null;
    EObject targetView = null;
    Property associationSource = null;
    Property associationTarget = null;
    EdgeMapping mapping = null;

    // assign source and target related to [Association]
    if (semantic instanceof Association) {
      mapping = getMappingCDBAssociation((CapellaElement) semantic, content.getDDiagram());
      associationSource = getAssociationSource((Association) semantic);
      if (associationSource != null) {
        source = associationSource.getAbstractType();
      }
      associationTarget = getAssociationTarget((Association) semantic);
      if (associationTarget != null) {
        target = associationTarget.getAbstractType();
      }
    }

    // assign source and target related to [Generalization]
    else if (semantic instanceof Generalization) {
      mapping = getMappingCDBGeneralization((CapellaElement) semantic, content.getDDiagram());
      source = ((Generalization) semantic).getSub();
      target = ((Generalization) semantic).getSuper();
    } // assign source and target related to [ExchangeItemElement]
    else if (semantic instanceof ExchangeItemElement) {
      mapping = getMappingCDBExchangeItemElement((CapellaElement) semantic, content.getDDiagram());
      source = (AbstractType) ((ExchangeItemElement) semantic).eContainer();
      target = ((ExchangeItemElement) semantic).getType();
    }

    // If invalid mapping or existing edge
    if ((mapping == null) || (!content.getDiagramElements(semantic, mapping).isEmpty())) {
      return;
    }

    // Create or retrieve the sourceView
    if (source != null) {
      AbstractNodeMapping sourceMapping = getMappingCDBCapellaElement((CapellaElement) source, content.getDDiagram());
      Collection<DDiagramElement> views = content.getDiagramElements(source, sourceMapping);
      if (!views.isEmpty()) {
        sourceView = views.iterator().next();
      } else {
        sourceView = DiagramServices.getDiagramServices().createAbstractDNodeContainer(sourceMapping, source,
            content.getBestContainer(source), content.getDDiagram());
        content.addView((AbstractDNode) sourceView);
      }
    }

    // Create or retrieve the targetView
    if (target != null) {
      AbstractNodeMapping targetMapping = getMappingCDBCapellaElement((CapellaElement) target, content.getDDiagram());
      Collection<DDiagramElement> views = content.getDiagramElements(target, targetMapping);
      if (!views.isEmpty()) {
        targetView = views.iterator().next();
      } else {
        targetView = DiagramServices.getDiagramServices().createAbstractDNodeContainer(targetMapping, target,
            content.getBestContainer(target), content.getDDiagram());
        content.addView((AbstractDNode) targetView);
      }
    }

    // Create the view between both bounds
    if ((sourceView != null) && (targetView != null)) {
      content.addView(DiagramServices.getDiagramServices().createEdge(mapping, (EdgeTarget) sourceView,
          (EdgeTarget) targetView, semantic));
    }
  }

  @Deprecated
  public EObject showHideRelationshipInClassDiagramBlank(CapellaElement context, EObject context_view,
      List<CapellaElement> allSelectedLinksFromWizard) {
    return showHideCDBRelationships(context, context_view, allSelectedLinksFromWizard);
  }

  /**
   * Show/Hide Relationship in Class Diagram Blank used in common.odesign
   * 
   * @param context
   * @param context_view
   * @param allSelectedRelationshipFromWizard
   * @return current view
   */
  public EObject showHideCDBRelationships(CapellaElement context, EObject contextView,
      List<CapellaElement> allSelectedLinksFromWizard) {

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(contextView);
    DDiagramContents content = new DDiagramContents(diagram);

    // remove association from diagram [if unSynchronized]
    for (DEdge existingRelationship : diagram.getEdges()) {
      EObject target = existingRelationship.getTarget();
      if ((target != null) && (contextView.equals(existingRelationship.getSourceNode())
          || contextView.equals(existingRelationship.getTargetNode()))) {
        if (!diagram.isSynchronized() || (target instanceof Generalization)) {
          // filter selected relationship in wizard
          if (!allSelectedLinksFromWizard.contains(target) && isValideRelationShip(target, context, content)) {
            // remove relationship from the diagram
            DiagramServices.getDiagramServices().removeEdgeView(existingRelationship);
            content.removeView(existingRelationship);
          }
        }
      }
    }

    // display association and related missing source and target element in
    // diagram
    for (CapellaElement selectedRelationship : allSelectedLinksFromWizard) {
      showCDBRelationship(selectedRelationship, content);
    }

    return contextView;
  }

  /**
   * This method is used to exclude a non valid relation ship (like : ExchangeItem,Collection,..)
   * 
   * @param context
   * @param content
   * @param target
   * @return
   */
  private boolean isValideRelationShip(EObject target, EObject context, DDiagramContents content) {
    boolean isValid = true;
    Collection<DDiagramElement> diagramElements = content.getDiagramElements(target);

    // for remove exchange item link
    if ((diagramElements != null) && !diagramElements.isEmpty() && (context instanceof ExchangeItem)
        && !(target instanceof Constraint)) {
      return true;
    }

    if (target instanceof org.polarsys.capella.core.data.information.Collection || target instanceof ExchangeItemElement
        || target instanceof Constraint) {
      isValid = false;
    }
    return isValid;
  }

  /**
   * @param source
   * @param diagram
   * @return
   */
  private EdgeMapping getMappingCDBExchangeItemElement(CapellaElement source, DDiagram diagram) {
    String mappingName = null;
    if (diagram.getDescription().getName().equalsIgnoreCase(DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CDB_EXCHANGE_ITEM_ELEMENT_MAPPING_NAME;
    }

    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  /**
   * get the meldoyElement container mapping from class blank diagram
   */
  private AbstractNodeMapping getMappingCDBCapellaElement(CapellaElement element, DDiagram diagram) {
    String mappingName = null;
    if (diagram.getDescription().getName().equalsIgnoreCase(DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME)) {
      if (element instanceof Class) {
        mappingName = IMappingNameConstants.CDB_CLASS_MAPPING_NAME;
        return DiagramServices.getDiagramServices().getContainerMapping(diagram, mappingName);
      } else if (element instanceof org.polarsys.capella.core.data.information.Collection) {
        mappingName = IMappingNameConstants.CDB_COLLECTION_MAPPING_NAME;
        return DiagramServices.getDiagramServices().getContainerMapping(diagram, mappingName);
      } else if (element instanceof Interface) {
        mappingName = IMappingNameConstants.CDB_INTERFACE_MAPPING_NAME;
        return DiagramServices.getDiagramServices().getContainerMapping(diagram, mappingName);
      } else if ((element instanceof DataType) && !(element instanceof Enumeration)
          && !(element instanceof BooleanType)) {
        mappingName = IMappingNameConstants.CDB_DATA_TYPE_MAPPING_NAME;
        return DiagramServices.getDiagramServices().getContainerMapping(diagram, mappingName);
      } else if (element instanceof Enumeration) {
        mappingName = IMappingNameConstants.CDB_ENUMERATION_MAPPING_NAME;
        return DiagramServices.getDiagramServices().getContainerMapping(diagram, mappingName);
      } else if (element instanceof BooleanType) {
        mappingName = IMappingNameConstants.CDB_BOOLEAN_TYPE_MAPPING_NAME;
        return DiagramServices.getDiagramServices().getContainerMapping(diagram, mappingName);
      } else if (element instanceof ExchangeItem) {
        mappingName = IMappingNameConstants.CDB_EXCHANGE_ITEM_MAPPING_NAME;
        return DiagramServices.getDiagramServices().getAbstractNodeMapping(diagram, mappingName);
      }
    }

    return null;
  }

  /**
   * get the Association edge mapping from class blank diagram
   */
  private ContainerMapping getMappingCDBType(EObject element, DDiagram diagram) {
    ContainerMapping mapping = null;
    if (element instanceof Class) {
      mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram,
          IMappingNameConstants.CDB_CLASS_MAPPING_NAME);
    } else if (element instanceof org.polarsys.capella.core.data.information.Collection) {
      mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram,
          IMappingNameConstants.CDB_COLLECTION_MAPPING_NAME);
    } else if (element instanceof Enumeration) {
      mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram,
          IMappingNameConstants.CDB_ENUMERATION_MAPPING_NAME);
    } else if (element instanceof BooleanType) {
      mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram,
          IMappingNameConstants.CDB_BOOLEAN_TYPE_MAPPING_NAME);
    } else {
      mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram,
          IMappingNameConstants.CDB_DATA_TYPE_MAPPING_NAME);
    }
    return mapping;
  }

  /**
   * get the Association edge mapping from class blank diagram
   */
  private EdgeMapping getMappingCDBAssociation(CapellaElement element, DDiagram diagram) {
    String mappingName = null;
    if (diagram.getDescription().getName().equalsIgnoreCase(DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CDB_ASSOCIATION_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  /**
   * get the Generalization edge mapping from class blank diagram
   */
  private EdgeMapping getMappingCDBGeneralization(CapellaElement element, DDiagram diagram) {
    String mappingName = null;
    if (diagram.getDescription().getName().equalsIgnoreCase(DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CDB_GENERALIZATION_MAPPING_NAME;
    }

    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  /**
   * used in common.odesign
   * 
   * @param context
   * @param containerView
   * @return
   */
  public List<DataPkg> getCDBShowHideDataPkgsScope(final EObject elementView) {
    List<DataPkg> returnedPackages = null;
    DSemanticDiagram diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(elementView);
    if (elementView.equals(diagram)) {
      returnedPackages = QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_DATA_PCK_FOR_LIB,
          diagram.getTarget());
    } else if (elementView instanceof DNodeContainer) {
      DataPkg currentPkg = (DataPkg) ((DNodeContainer) elementView).getTarget();
      returnedPackages = DataPkgExt.getRecursiveSubDataPkgs(currentPkg);
    }

    if (returnedPackages != null) {
      for (AbstractDNode aContainer : diagram.getContainers()) {
        if (aContainer.getTarget() instanceof DataPkg) {
          returnedPackages.remove(aContainer.getTarget());
        }
      }
    }
    return returnedPackages;
  }

  @Deprecated
  public List<DataPkg> getAvailableDataPkgsToInsert(final EObject elementView) {
    return getCDBShowHideDataPkgsScope(elementView);
  }

  /**
   * used in common.odesign
   * 
   * @param context
   * @param containerView
   * @return
   */
  public List<InterfacePkg> getAvailableInterfacePkgsToInsert(final EObject elementView) {
    List<InterfacePkg> returnedPackages = null;
    DSemanticDiagram diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(elementView);
    if (elementView.equals(diagram)) {
      returnedPackages = QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_INTERFACE_PCKS_FOR_LIB,
          diagram.getTarget());
    } else if (elementView instanceof DNodeContainer) {
      InterfacePkg currentPkg = (InterfacePkg) ((DNodeContainer) elementView).getTarget();
      returnedPackages = InterfacePkgExt.getRecursiveSubInterfacePkgs(currentPkg);
    }
    if (returnedPackages != null) {
      for (AbstractDNode aContainer : diagram.getContainers()) {
        if (aContainer.getTarget() instanceof InterfacePkg) {
          returnedPackages.remove(aContainer.getTarget());
        }
      }
    }
    return returnedPackages;
  }

  /**
   * show the given dataPkg
   * 
   * @param datapkg
   * @param context
   */
  public AbstractDNode showCDBDataPkg(EObject semantic, DDiagramContents context) {
    ContainerMapping mapping = DiagramServices.getDiagramServices().getContainerMapping(context.getDDiagram(),
        IMappingNameConstants.CDB_DATA_PKG_MAPPING_NAME);
    DragAndDropTarget container = context.getBestContainer(semantic);
    Collection<DDiagramElement> views = context.getDiagramElements(semantic, mapping, (DSemanticDecorator) container);
    if (!views.isEmpty()) {
      return (AbstractDNode) views.iterator().next();
    }
    AbstractDNode result = DiagramServices.getDiagramServices().createContainer(mapping, semantic, container,
        context.getDDiagram());
    context.addView(result);
    return result;
  }

  /**
   * @param elementView
   * @param selectedPkgs
   * @return
   */
  public EObject showHideCDBDataPkgs(final EObject elementView, List<DataPkg> selectedPkgs) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(elementView);
    DDiagramContents content = new DDiagramContents(diagram);
    ContainerMapping mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram,
        IMappingNameConstants.CDB_DATA_PKG_MAPPING_NAME);

    Map<DataPkg, DNodeContainer> existingPkgs = new HashMap<>();
    for (DDiagramElement aContainer : content.getDiagramElements((DSemanticDecorator) elementView, mapping)) {
      if ((aContainer instanceof DNodeContainer) && (aContainer.getTarget() instanceof DataPkg)) {
        existingPkgs.put((DataPkg) aContainer.getTarget(), (DNodeContainer) aContainer);
      }
    }

    Set<DNodeContainer> toBeRemoved = new HashSet<>();
    for (Entry<DataPkg, DNodeContainer> me : existingPkgs.entrySet()) {
      if (!selectedPkgs.contains(me.getKey())) {
        toBeRemoved.add(me.getValue());
      }
    }
    // move packages whose parent must be deleted
    for (DNodeContainer aContainer : existingPkgs.values()) {
      if (!toBeRemoved.contains(aContainer) && toBeRemoved.contains(aContainer.eContainer())) {
        diagram.getOwnedDiagramElements().add(aContainer);
      }
    }
    for (DNodeContainer aContainer : toBeRemoved) {
      DiagramServices.getDiagramServices().removeContainerView(aContainer);
    }

    for (DataPkg aPkg : selectedPkgs) {
      if (!existingPkgs.containsKey(aPkg)) {
        showCDBDataPkg(aPkg, content);
      }
    }
    return elementView;
  }

  /**
   * used in common (Package dependencies & CDB)
   * 
   * @param diagram
   * @param selectedPkgs
   * @return
   */
  @Deprecated
  public EObject showHideDataPkgs(final EObject elementView, List<DataPkg> selectedPkgs) {
    return showHideCDBDataPkgs(elementView, selectedPkgs);
  }

  /**
   * show the given dataPkg
   * 
   * @param datapkg
   * @param context
   */
  public AbstractDNode showCDBInterfacePkg(EObject semantic, DDiagramContents context) {
    ContainerMapping mapping = DiagramServices.getDiagramServices().getContainerMapping(context.getDDiagram(),
        IMappingNameConstants.CDB_INTERFACE_PKG_MAPPING_NAME);
    DragAndDropTarget container = context.getBestContainer(semantic);
    Collection<DDiagramElement> views = context.getDiagramElements(semantic, mapping, (DSemanticDecorator) container);
    if (!views.isEmpty()) {
      return (AbstractDNode) views.iterator().next();
    }
    AbstractDNode result = DiagramServices.getDiagramServices().createContainer(mapping, semantic, container,
        context.getDDiagram());
    context.addView(result);
    return result;
  }

  public EObject showHideCDBInterfacePkgs(final EObject elementView, List<InterfacePkg> selectedPkgs) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(elementView);
    DDiagramContents content = new DDiagramContents(diagram);
    ContainerMapping mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram,
        IMappingNameConstants.CDB_INTERFACE_PKG_MAPPING_NAME);

    Map<InterfacePkg, DNodeContainer> existingPkgs = new HashMap<>();
    for (DDiagramElement aContainer : content.getDiagramElements((DSemanticDecorator) elementView, mapping)) {
      if ((aContainer instanceof DNodeContainer) && (aContainer.getTarget() instanceof InterfacePkg)) {
        existingPkgs.put((InterfacePkg) aContainer.getTarget(), (DNodeContainer) aContainer);
      }
    }

    Set<DNodeContainer> toBeRemoved = new HashSet<>();
    for (Entry<InterfacePkg, DNodeContainer> me : existingPkgs.entrySet()) {
      if (!selectedPkgs.contains(me.getKey())) {
        toBeRemoved.add(me.getValue());
      }
    }
    // move packages whose parent must be deleted
    for (DNodeContainer aContainer : existingPkgs.values()) {
      if (!toBeRemoved.contains(aContainer) && toBeRemoved.contains(aContainer.eContainer())) {
        diagram.getOwnedDiagramElements().add(aContainer);
      }
    }
    for (DNodeContainer aContainer : toBeRemoved) {
      DiagramServices.getDiagramServices().removeContainerView(aContainer);
    }
    for (InterfacePkg aPkg : selectedPkgs) {
      if (!existingPkgs.containsKey(aPkg)) {
        showCDBInterfacePkg(aPkg, content);
      }
    }
    return elementView;
  }

  /**
   * used in common (Package dependencies & CDB)
   * 
   * @param diagram
   * @param selectedPkgs
   * @return
   */
  @Deprecated
  public EObject showHideInterfacePkgs(final EObject elementView, List<InterfacePkg> selectedPkgs) {
    Map<InterfacePkg, DDiagramElement> existingPkgs = new HashMap<>();
    for (DDiagramElement aContainer : DiagramServices.getDiagramServices().getAllNodeContainers(elementView)) {
      if (aContainer.getTarget() instanceof InterfacePkg) {
        existingPkgs.put((InterfacePkg) aContainer.getTarget(), aContainer);
      }
    }
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(elementView);
    Set<DDiagramElement> toBeRemoved = new HashSet<>();

    for (Entry<InterfacePkg, DDiagramElement> me : existingPkgs.entrySet()) {
      if (!selectedPkgs.contains(me.getKey())) {
        toBeRemoved.add(me.getValue());
      }
    }
    // move packages whose parent must be deleted
    for (DDiagramElement aContainer : existingPkgs.values()) {
      if (!toBeRemoved.contains(aContainer) && toBeRemoved.contains(aContainer.eContainer())) {
        diagram.getOwnedDiagramElements().add(aContainer);
      }
    }
    for (DDiagramElement aContainer : toBeRemoved) {
      DiagramServices.getDiagramServices().removeContainerView(aContainer);
    }
    for (InterfacePkg aPkg : selectedPkgs) {
      if (!existingPkgs.containsKey(aPkg)) {
        ContainerMapping mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram,
            IMappingNameConstants.CDB_INTERFACE_PKG_MAPPING_NAME);
        DiagramServices.getDiagramServices().createContainer(mapping, aPkg, diagram, diagram);
      }
    }
    return elementView;
  }

  @Deprecated
  public EObject showHideDependentPkgs(final EObject elementView, List<AbstractDependenciesPkg> selectedPkgs,
      List<AbstractDependenciesPkg> existingPackages) {
    return showHidePDDependentPkgs(elementView, selectedPkgs, existingPackages);
  }

  /**
   * used in common (Package dependencies)
   * 
   * @param diagram
   * @param selectedPkgs
   * @param existingPackages
   * @return
   */
  public EObject showHidePDDependentPkgs(final EObject elementView, List<AbstractDependenciesPkg> selectedPkgs,
      List<AbstractDependenciesPkg> existingPackages) {
    Map<AbstractDependenciesPkg, DDiagramElement> existingPkgs = new HashMap<>();
    for (DDiagramElement aContainer : DiagramServices.getDiagramServices().getAllNodeContainers(elementView)) {
      if (existingPackages.contains(aContainer.getTarget())) {
        existingPkgs.put((AbstractDependenciesPkg) aContainer.getTarget(), aContainer);
      }
    }
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(elementView);
    Set<DDiagramElement> toBeRemoved = new HashSet<>();

    for (Entry<AbstractDependenciesPkg, DDiagramElement> me : existingPkgs.entrySet()) {
      if (!selectedPkgs.contains(me.getKey())) {
        toBeRemoved.add(me.getValue());
      }
    }

    // move packages whose parent must be deleted
    for (DDiagramElement aContainer : DiagramServices.getDiagramServices().getAllNodeContainers(diagram)) {
      if (!toBeRemoved.contains(aContainer) && toBeRemoved.contains(aContainer.eContainer())) {
        diagram.getOwnedDiagramElements().add(aContainer);
      }
    }
    for (DDiagramElement aContainer : toBeRemoved) {
      DiagramServices.getDiagramServices().removeContainerView(aContainer);
    }
    for (AbstractDependenciesPkg aPkg : selectedPkgs) {
      if (!existingPkgs.containsKey(aPkg)) {
        String mappingName = null;
        if (aPkg instanceof DataPkg) {
          mappingName = IMappingNameConstants.CDB_DATA_PKG_MAPPING_NAME;
        } else {
          mappingName = IMappingNameConstants.CDB_INTERFACE_PKG_MAPPING_NAME;
        }
        ContainerMapping mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram, mappingName);
        DiagramServices.getDiagramServices().createContainer(mapping, aPkg, diagram, diagram);
      }
    }
    return elementView;
  }

  @Deprecated
  public EObject showHidePackageDependencies(final AbstractDependenciesPkg subject, DDiagram diagram) {
    return showHidePDPackageDependencies(subject, diagram);
  }

  public EObject showHidePDPackageDependencies(final AbstractDependenciesPkg subject, DDiagram diagram) {

    Collection<AbstractDependenciesPkg> deps = AbstractDependenciesPkgExt.getDependencies(subject);
    Collection<AbstractDependenciesPkg> inverseDeps = AbstractDependenciesPkgExt.getInverseDependencies(subject);

    List<AbstractDependenciesPkg> all = new ArrayList<>(deps);
    all.addAll(inverseDeps);

    boolean expandLeftViewer = CapellaUIPropertiesPlugin.getDefault().isAllowedExpandLeftViewerContent();
    boolean expandRightViewer = CapellaUIPropertiesPlugin.getDefault().isAllowedExpandRightViewerContent();
    int leftViewerExpandLevel = expandLeftViewer ? AbstractTreeViewer.ALL_LEVELS : 0;
    int rightViewerExpandLevel = expandRightViewer ? AbstractTreeViewer.ALL_LEVELS : 0;

    TransferTreeListDialog dialog = new TransferTreeListDialog(
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
        Messages.InformationServices_PackageDependency_Title,
        NLS.bind(Messages.InformationServices_PackageDependency_Message, subject.getName()),
        new DependencyLabelProvider(deps, inverseDeps, subject), // two
        // instances
        // required
        new DependencyLabelProvider(deps, inverseDeps, subject), leftViewerExpandLevel, rightViewerExpandLevel); // or
    // enabled/disabled
    // fonts
    // will
    // mess
    // up

    List<AbstractDependenciesPkg> left = new ArrayList<>(); // unselected
    List<AbstractDependenciesPkg> right = new ArrayList<>(); // selected

    for (AbstractDependenciesPkg pkg : all) {
      if (DiagramServices.getDiagramServices().getDiagramElement(diagram, pkg) != null) {
        right.add(pkg);
      } else {
        left.add(pkg);
      }
    }

    dialog.setLeftInput(left, subject);
    dialog.setRightInput(right, subject);

    if (dialog.open() == Window.OK) {
      Object[] result = dialog.getResult();
      right.clear();
      for (Object o : result) {
        if (o instanceof AbstractDependenciesPkg) {
          right.add((AbstractDependenciesPkg) o);
        }
      }
      showHidePDDependentPkgs(diagram, right, all);
    }
    return subject;
  }

  public Collection<AbstractDependenciesPkg> getDependentPackages(final AbstractDependenciesPkg pkg) {
    return AbstractDependenciesPkgExt.getDependencies(pkg);
  }

  /**
   * Searches the direct dependencies and inverse dependencies of an AbstractDependenciesPkg.
   * 
   * @param pkg
   * @return The direct dependencies and inverse dependencies of the argument
   */
  public Collection<AbstractDependenciesPkg> getDependenciesAndInverseDependencies(final AbstractDependenciesPkg pkg) {
    Set<AbstractDependenciesPkg> result = new HashSet<>();
    result.addAll(AbstractDependenciesPkgExt.getDependencies(pkg));
    result.addAll(AbstractDependenciesPkgExt.getInverseDependencies(pkg));
    return result;
  }

  /**
   * used in common.odesign
   * 
   * @param sourcePackage
   * @param targetPackage
   * @return test if we add a dependency between sourcePackage and targetPackage it does not create any cycle
   */
  public boolean isADependencyAvailable(final EObject context, AbstractDependenciesPkg sourcePackage,
      AbstractDependenciesPkg targetPackage) {
    return AbstractDependenciesPkgExt.isADependencyAvailable(sourcePackage, targetPackage);
  }

  /**
   * used in common.odesign
   * 
   * @param sourcePackage
   * @param targetPackage
   * @return test if we add a dependency between sourcePackage and targetPackage it does not create any cycle
   */
  public boolean isADependencyAvailable2(final EObject context, EObject sourceObj, EObject targetObj) {
    EObject ancestor1 = CapellaServices.getService().getAncestor(sourceObj,
        CapellacorePackage.Literals.ABSTRACT_DEPENDENCIES_PKG);
    EObject ancestor2 = CapellaServices.getService().getAncestor(targetObj,
        CapellacorePackage.Literals.ABSTRACT_DEPENDENCIES_PKG);
    return AbstractDependenciesPkgExt.isADependencyAvailable((AbstractDependenciesPkg) ancestor1,
        (AbstractDependenciesPkg) ancestor2);
  }

  /**
   * used in common.odesign
   * 
   * @param elementView
   * @return all the available dataTypes we can insert in the elementView
   */
  public Collection<DataType> getAvailableDataTypesToInsert(final EObject elementView) {
    if (elementView instanceof DDiagram) {
      return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_DATA_TYPES_FOR_LIB,
          ((DSemanticDiagram) elementView).getTarget());
    }
    if (elementView instanceof DNodeContainer) {
      DNodeContainer currentContainer = (DNodeContainer) elementView;
      if (currentContainer.getTarget() instanceof DataPkg) {
        return DataPkgExt.getAllDataTypes((DataPkg) currentContainer.getTarget());
      }
    }
    return Collections.emptyList();
  }

  /**
   * used in common.odesign (CDB)
   * 
   * @param elementView
   * @return all the available classes we can insert in the elementView
   */
  public Collection<Class> getAvailableClassesToInsert(final EObject elementView) {
    if (elementView instanceof DDiagram) {
      return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_CLASSES_FOR_LIB,
          ((DSemanticDiagram) elementView).getTarget());
    }
    if (elementView instanceof DNodeContainer) {
      DNodeContainer currentContainer = (DNodeContainer) elementView;
      if (currentContainer.getTarget() instanceof DataPkg) {
        return DataPkgExt.getAllClasses((DataPkg) currentContainer.getTarget());
      }
    }
    return Collections.emptyList();
  }

  /**
   * used in common.odesign (CDB)
   * 
   * @param elementView
   * @return all the available interfaces we can insert in the containerView
   */
  public Collection<Interface> getAvailableInterfacesToInsert(final EObject elementView) {
    if (elementView instanceof DDiagram) {
      return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_INTERFACES_FOR_LIB,
          ((DSemanticDiagram) elementView).getTarget());
    }
    if (elementView instanceof DNodeContainer) {
      DNodeContainer currentContainer = (DNodeContainer) elementView;
      if (currentContainer.getTarget() instanceof InterfacePkg) {
        return InterfacePkgExt.getAllInterfaces((InterfacePkg) currentContainer.getTarget());
      }
    }
    return Collections.emptyList();
  }

  /**
   * used in common.odesign (CDB)
   * 
   * @param context
   * @param containerView
   * @return all the available interfaces we can insert in the containerView
   */
  public Collection<Interface> getAvailableInterfacesToInsert(final EObject context, EObject containerView) {
    if (containerView instanceof DDiagram) {
      return InterfaceExt.getAllInterfaces(context);
    }
    if (context instanceof InterfacePkg) {
      return InterfacePkgExt.getAllInterfaces((InterfacePkg) context);
    }
    return Collections.emptyList();
  }

  /**
   * used in common (CDB)
   * 
   * @param elementView
   * @return all the available exchange items we can insert in the elementView
   */
  public Collection<AbstractExchangeItem> getAvailableExchangeItemsToInsert(final EObject elementView) {
    if (elementView instanceof DDiagram) {
      return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_EXCHANGE_ITEMS_FOR_LIB,
          ((DSemanticDiagram) elementView).getTarget());
    }
    if (elementView instanceof DNodeContainer) {
      DNodeContainer currentContainer = (DNodeContainer) elementView;
      if (currentContainer.getTarget() instanceof AbstractExchangeItemPkg) {
        return AbstractExchangeItemPkgExt.getAllAbstractExchangeItems((DataPkg) currentContainer.getTarget());
      }
    }
    return Collections.emptyList();
  }

  /**
   * used in common (CDB)
   * 
   * @param elementView
   * @return all the available collections we can insert in the elementView
   */
  public Collection<org.polarsys.capella.core.data.information.Collection> getAvailableCollectionsToInsert(
      final EObject elementView) {
    if (elementView instanceof DDiagram) {
      return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_COLLECTIONS_FOR_LIB,
          ((DSemanticDiagram) elementView).getTarget());
    }
    if (elementView instanceof DNodeContainer) {
      DNodeContainer currentContainer = (DNodeContainer) elementView;
      if (currentContainer.getTarget() instanceof DataPkg) {
        return DataPkgExt.getAllCollections((DataPkg) currentContainer.getTarget());
      }
    }
    return Collections.emptyList();
  }

  @Deprecated
  public EObject showHideTypes(final EObject elementView, List<EObject> selectedTypes) {
    return showHideCDBTypes(elementView, selectedTypes);
  }

  /**
   * used in common (CDB)
   * 
   * @param diagram
   * @param selectedTypes
   * @return
   */
  public EObject showHideCDBTypes(final EObject elementView, List<EObject> selectedTypes) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(elementView);
    DDiagramContents content = new DDiagramContents(diagram);

    Map<EObject, AbstractDNode> existingTypes = new HashMap<>();
    for (EObject currentContainer : DiagramServices.getDiagramServices().getAllContainersAndNodeLists(elementView)) {
      AbstractDNode aContainer = (AbstractDNode) currentContainer;
      if ((aContainer.getTarget() instanceof Class)
          || (aContainer.getTarget() instanceof org.polarsys.capella.core.data.information.Collection)
          || (aContainer.getTarget() instanceof DataType)) {
        existingTypes.put(aContainer.getTarget(), aContainer);
      }
    }

    for (Entry<EObject, AbstractDNode> me : existingTypes.entrySet()) {
      if (!selectedTypes.contains(me.getKey())) {
        DiagramServices.getDiagramServices().removeContainerView(me.getValue());
      }
    }
    for (EObject aType : selectedTypes) {
      if (!existingTypes.containsKey(aType)) {
        showCDBType(aType, content);
      }
    }
    return elementView;
  }

  /**
   * show the given dataPkg
   * 
   * @param datapkg
   * @param context
   */
  public AbstractDNode showCDBType(EObject semantic, DDiagramContents context) {
    ContainerMapping mapping = getMappingCDBType(semantic, context.getDDiagram());
    DragAndDropTarget container = context.getBestContainer(semantic);
    Collection<DDiagramElement> views = context.getDiagramElements(semantic, mapping, (DSemanticDecorator) container);
    if (!views.isEmpty()) {
      return (AbstractDNode) views.iterator().next();
    }
    AbstractDNode result = DiagramServices.getDiagramServices().createAbstractDNodeContainer(mapping, semantic,
        container, context.getDDiagram());
    context.addView(result);
    return result;
  }

  /**
   * show the given dataPkg
   * 
   * @param datapkg
   * @param context
   */
  public AbstractDNode showCDBInterface(EObject semantic, DDiagramContents context) {
    ContainerMapping mapping = getMappingCDBInterface(semantic, context.getDDiagram());
    DragAndDropTarget container = context.getBestContainer(semantic);
    Collection<DDiagramElement> views = context.getDiagramElements(semantic, mapping, (DSemanticDecorator) container);
    if (!views.isEmpty()) {
      return (AbstractDNode) views.iterator().next();
    }
    AbstractDNode result = DiagramServices.getDiagramServices().createAbstractDNodeContainer(mapping, semantic,
        container, context.getDDiagram());
    context.addView(result);
    return result;
  }

  /**
   * @param semantic
   * @param dDiagram
   * @return
   */
  private ContainerMapping getMappingCDBInterface(EObject semantic, DDiagram dDiagram) {
    return DiagramServices.getDiagramServices().getContainerMapping(dDiagram,
        IMappingNameConstants.CDB_INTERFACE_MAPPING_NAME);
  }

  /**
   * show the given dataPkg
   * 
   * @param datapkg
   * @param context
   */
  public AbstractDNode showCDBExchangeItem(EObject semantic, DDiagramContents context) {
    NodeMapping mapping = getMappingCDBExchangeItem(semantic, context.getDDiagram());
    DragAndDropTarget container = context.getBestContainer(semantic);
    Collection<DDiagramElement> views = context.getDiagramElements(semantic, mapping, (DSemanticDecorator) container);
    if (!views.isEmpty()) {
      return (AbstractDNode) views.iterator().next();
    }
    AbstractDNode result = DiagramServices.getDiagramServices().createNode(mapping, semantic, container,
        context.getDDiagram());
    context.addView(result);
    return result;
  }

  /**
   * @param semantic
   * @param dDiagram
   * @return
   */
  private NodeMapping getMappingCDBExchangeItem(EObject semantic, DDiagram dDiagram) {
    return DiagramServices.getDiagramServices().getNodeMapping(dDiagram,
        IMappingNameConstants.CDB_INTERFACE_MAPPING_NAME);
  }

  /**
   * used in common (CDB)
   * 
   * @param diagram
   * @param selectedInterfaces
   * @return
   */
  public EObject showHideCDBInterfaces(final EObject elementView, List<EObject> selectedInterfaces) {
    Map<EObject, AbstractDNode> existingInterfaces = new HashMap<>();
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(elementView);
    DDiagramContents content = new DDiagramContents(diagram);

    for (EObject currentContainer : DiagramServices.getDiagramServices().getAllContainersAndNodeLists(elementView)) {
      AbstractDNode aContainer = (AbstractDNode) currentContainer;
      if (aContainer.getTarget() instanceof Interface) {
        existingInterfaces.put(aContainer.getTarget(), aContainer);
      }
    }
    for (Entry<EObject, AbstractDNode> me : existingInterfaces.entrySet()) {
      if (!selectedInterfaces.contains(me.getKey())) {
        DiagramServices.getDiagramServices().removeContainerView(me.getValue());
      }
    }
    for (EObject aType : selectedInterfaces) {
      if (!existingInterfaces.containsKey(aType)) {
        showCDBInterface(aType, content);
      }
    }
    return elementView;
  }

  /**
   * Semantic Candidates Expression: resolve a list of candidate Associations that could be displayed in the given
   * diagram.
   * 
   * @param diagram
   * @return
   */
  public Collection<EObject> getCDBAssociationSemanticCandidates(DDiagram diagram) {
    // Use a HashSet to avoid to have twice the same Association. This occurs when
    // an Association has the same class as source and target.
    Collection<EObject> candidateAssociations = new HashSet<>();
    for (DDiagramElement dNode : diagram.getDiagramElements()) {
      if (dNode instanceof AbstractDNode) {
        EObject target = dNode.getTarget();
        if (target instanceof AbstractType) {
          // Get Properties referencing this AbstractType.
          List<EObject> properties = EObjectExt.getReferencers(target, InformationPackage.Literals.PROPERTY,
              ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);
          for (EObject property : properties) {
            // Get Associations referencing this Property.
            candidateAssociations.addAll(EObjectExt.getReferencers(property, InformationPackage.Literals.ASSOCIATION,
                InformationPackage.Literals.ASSOCIATION__NAVIGABLE_MEMBERS));
            candidateAssociations.addAll(EObjectExt.getReferencers(property, InformationPackage.Literals.ASSOCIATION,
                InformationPackage.Literals.ASSOCIATION__OWNED_MEMBERS));
          }
        }
      }
    }
    return candidateAssociations;
  }

  /**
   * Semantic Candidates Expression: resolve a list of candidate Generalizations that could be displayed in the given
   * diagram.
   * 
   * @param diagram
   * @return
   */
  public Collection<EObject> getCDBGeneralizationSemanticCandidates(DDiagram diagram) {
    Collection<EObject> candidateGeneralizations = new ArrayList<>();
    for (DDiagramElement dNode : diagram.getDiagramElements()) {
      if (dNode instanceof AbstractDNode) {
        EObject target = dNode.getTarget();
        // Get AbstractNodes with a GeneralizableElement as semantic target.
        if (target instanceof GeneralizableElement) {
          // Get all Generalizations in the Generalizable element.
          candidateGeneralizations.addAll(((GeneralizableElement) target).getOwnedGeneralizations());
        }
      }
    }
    return candidateGeneralizations;
  }

  /**
   * Semantic Candidates Expression: resolve a list of candidate ExchangeItemElements that could be displayed in the
   * given diagram.
   * 
   * @param diagram
   * @return
   */
  public Collection<EObject> getCDBExchangeItemElementSemanticCandidates(DDiagram diagram) {
    Collection<EObject> candidateExchangeItemElements = new ArrayList<>();
    for (DDiagramElement dNode : diagram.getDiagramElements()) {
      if (dNode instanceof AbstractDNode) {
        EObject target = dNode.getTarget();
        // Get AbstractNodes with a ExchangeItem as semantic target.
        if (target instanceof ExchangeItem) {
          // Get all ExchangeItemElement in the ExchangeItem element.
          candidateExchangeItemElements.addAll(((ExchangeItem) target).getOwnedElements());
        }
      }
    }
    return candidateExchangeItemElements;
  }

  /**
   * used in common (CDB)
   * 
   * @param diagram
   * @param selectedExchangeItems
   * @return
   */
  public EObject showHideCDBExchangeItems(final EObject elementView, List<EObject> selectedExchangeItems) {
    Map<ExchangeItem, AbstractDNode> existingExchangeItems = new HashMap<>();
    for (AbstractDNode aNode : DiagramServices.getDiagramServices().getAllNodesAndNodeListElements(elementView)) {
      if (aNode.getTarget() instanceof ExchangeItem) {
        existingExchangeItems.put((ExchangeItem) aNode.getTarget(), aNode);
      }
    }
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(elementView);

    for (Entry<ExchangeItem, AbstractDNode> me : existingExchangeItems.entrySet()) {
      if (!selectedExchangeItems.contains(me.getKey())) {
        // remove abstract dNode view
        DiagramServices.getDiagramServices().removeAbstractDNodeView(me.getValue());
      }
    }
    for (EObject aType : selectedExchangeItems) {
      if (!existingExchangeItems.containsKey(aType)) {
        NodeMapping mapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
            IMappingNameConstants.CDB_EXCHANGE_ITEM_MAPPING_NAME);
        DiagramServices.getDiagramServices().createNode(mapping, aType, diagram, diagram);
      }
    }
    return elementView;
  }

  public String getCommunicationLinkLabel(EObject context) {
    String result = ICommonConstants.EMPTY_STRING;
    if (null != context) {
      if (context instanceof CommunicationLink) {
        CommunicationLink cl = (CommunicationLink) context;
        CommunicationLinkKind kind = cl.getKind();
        CommunicationLinkProtocol protocol = cl.getProtocol();
        if (null != kind) {
          result = kind.getName();
        } else {
          result = "<undefined>"; //$NON-NLS-1$
        }
        if ((null != protocol) && (protocol != CommunicationLinkProtocol.UNSET)
            && (protocol != CommunicationLinkProtocol.UNICAST) && (protocol != CommunicationLinkProtocol.SYNCHRONOUS)
            && (protocol != CommunicationLinkProtocol.READ)) {
          result = result + " / " + protocol.getName(); //$NON-NLS-1$
        }
      }
    }

    return result;
  }

  /**
   * compute the Interface service label from ExchangeItemAllocation
   * 
   * @param exchangeItemAllocation
   * @param view
   * @return
   */
  public String getOperationLabel(EObject exchangeItemAllocation, EObject view) {
    String result = ICommonConstants.EMPTY_STRING;

    if (exchangeItemAllocation instanceof ExchangeItemAllocation && view instanceof DSemanticDecorator) {
      // Type Exchange Item Allocation
      ExchangeItemAllocation allocation = (ExchangeItemAllocation) exchangeItemAllocation;
      // get Diagram
      DDiagram diagram = CapellaServices.getService().getDiagramContainer(view);

      if (diagram != null) {
        EList<FilterDescription> activatedFilters = diagram.getActivatedFilters();
        boolean allParameterHide = false;
        boolean onlyOperationParameterHide = false;
        for (FilterDescription filterDescription : activatedFilters) {
          if ((null != filterDescription)
              && filterDescription.getName().equalsIgnoreCase(IMappingNameConstants.HIDE_ALL_PARAMETER)) {
            allParameterHide = true;
          }
          if ((null != filterDescription)
              && filterDescription.getName().equalsIgnoreCase(IMappingNameConstants.HIDE_OPERATION_PARAMETER)) {
            onlyOperationParameterHide = true;
          }
        }

        // Case 1: hide all parameter and also operation parameter
        if (allParameterHide && onlyOperationParameterHide) {
          result = getAllocatedElementName(allocation);
        }
        // Case 2: hide nothing
        else if (!onlyOperationParameterHide && !allParameterHide) {
          result = computeLabel(allocation);
        }
        // Case 3: hide all parameter
        else if (allParameterHide) {
          result = getAllocatedElementName(allocation);
        }
        // Case 4: hide only operation parameter
        else {
          AbstractExchangeItem allocatedItem = allocation.getAllocatedItem();
          if (allocatedItem instanceof ExchangeItem) {
            ExchangeItem item = (ExchangeItem) allocatedItem;
            ExchangeMechanism exchangeMechanism = item.getExchangeMechanism();
            if ((exchangeMechanism != null) && exchangeMechanism.equals(ExchangeMechanism.OPERATION)) {
              result = getAllocatedElementName(allocation);
            } else {
              result = computeLabel(allocation);
            }
          } else {
            result = computeLabel(allocation);
          }
        }
      }
    }

    return result;
  }

  /**
   * @param operation
   * @param result
   * @return
   */
  private String getAllocatedElementName(ExchangeItemAllocation operation) {
    String result = EObjectExt.getText(operation.getAllocatedItem());
    return result == null ? ICommonConstants.EMPTY_STRING : result;
  }

  public boolean hasExchangeItemAllocationLink(DDiagramElement context) {
    if (null != context) {
      EObject eiTarget = context.getTarget();
      if ((eiTarget instanceof ExchangeItem) && (context instanceof DNode)) {
        DNode node = (DNode) context;
        EList<DEdge> incomingEdges = node.getIncomingEdges();
        for (DEdge edge : incomingEdges) {
          EObject target = edge.getTarget();
          if (target instanceof ExchangeItemAllocation) {
            ExchangeItemAllocation eia = (ExchangeItemAllocation) target;
            AbstractExchangeItem allocatedItem = eia.getAllocatedItem();
            if (allocatedItem != null && allocatedItem.equals(eiTarget)) {
              return false;
            }
          }

        }
      } else if (eiTarget instanceof ExchangeItemAllocation) {
        return false;
      }
    }

    return true;
  }

  /**
   * Convert first letter of the string to Capital letter
   * 
   * @param context
   * @param toConvert
   * @return converted String
   */
  public String convertToUpperFirst(EObject context, String toConvert) {
    return StringHelper.toLowerFirst(toConvert);
  }

  /**
   * [Method used in common.odesign] Returns true if any of the association end is of type primitive
   * 
   * @param context
   *          current Association
   * @return boolean value
   */
  public boolean hasNonPrimitiveEnds(EObject context) {
    if (context instanceof Association) {
      Association association = (Association) context;
      Collection<Property> associationProperties = getAssociationProperties(association);
      for (Property property : associationProperties) {
        AbstractType abstractType = property.getAbstractType();
        if (abstractType instanceof Class) {
          Class cls = (Class) abstractType;
          if (cls.isIsPrimitive()) {
            return true;
          } else if (abstractType instanceof org.polarsys.capella.core.data.information.Collection) {
            org.polarsys.capella.core.data.information.Collection collection = (org.polarsys.capella.core.data.information.Collection) abstractType;
            if (collection.isIsPrimitive()) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  /**
   * used in common.odesign : ClassDiagram Return customized label for UnionProperty
   * 
   * @param context
   *          : an EObject
   * @param property
   *          : an UnionProperty
   * @return : the customized label for UnionProperty
   */
  public String suffixLabelForUnionProperty(EObject context, EObject property) {
    String result = ICommonConstants.EMPTY_STRING;
    // filter UnionProperty
    if (property instanceof UnionProperty) {
      UnionProperty unionPro = (UnionProperty) property;
      EObject container = unionPro.eContainer();
      if (container instanceof Union) {
        Union union = (Union) container;
        if (union.getDiscriminant() != null) {
          result = computeUnionPropertyLabelWithQualifier(unionPro);
        }
      }
    }

    return result;
  }

  /**
   * @param context
   * @param ele
   *          a capella element
   * @param displayedExchangeItem
   *          : exchangeItem displayed in diagram
   * @return list of exchange item
   */
  public List<EObject> getDisplayedAndLinkedExchangeItems(EObject context, EObject ele,
      List<ExchangeItem> displayedExchangeItem) {
    // list of exchange Item
    List<EObject> result = new ArrayList<>(1);
    // all the exchanges linked via communication link owned by given
    // component
    List<EObject> linkedExchagneItem = new ArrayList<>(1);

    if (ele instanceof Component) {
      // Component
      Component comp = (Component) ele;

      // collect linked Exchange Item of given component
      EList<CommunicationLink> ownedCommunicationLinks = comp.getOwnedCommunicationLinks();
      for (CommunicationLink communicationLink : ownedCommunicationLinks) {
        AbstractExchangeItem exchangeItem = communicationLink.getExchangeItem();
        if (null != exchangeItem) {
          linkedExchagneItem.add(exchangeItem);
        }
      }

      // filter the unlinked Exchange item from displayed Exchange Items
      for (ExchangeItem displayedExhagneItem : displayedExchangeItem) {
        if (!linkedExchagneItem.contains(displayedExhagneItem)) {
          result.add(displayedExhagneItem);
        }
      }
    }

    return result;
  }

  public String getDataTypeLabel(DataType dataType) {
    return dataType.getName();
  }

  /**
   * @used in common.odesign Return true if "Hide Association Label is true"
   * @param assocation
   * @param view
   * @return
   */
  public boolean isHideAssociationLabelEnable(EObject assocation, EObject view) {
    return isDiagramFilterEnable(assocation, view, IMappingNameConstants.HIDE_ASSOCIATION_LABELS);
  }

  /**
   * @used in common.odesign Return true if "Hide Role Label is true"
   * @param assocation
   * @param view
   * @return
   */
  public boolean isHideRoleLabelEnable(EObject assocation, EObject view) {
    return isDiagramFilterEnable(assocation, view, IMappingNameConstants.HIDE_ROLE_LABELS);
  }

  /**
   * @used in common.odesign Return true if "Hide Role Label is true"
   * @param assocation
   * @param view
   * @return
   */
  public boolean isHideRoleNameEnable(EObject assocation, EObject view) {
    return isDiagramFilterEnable(assocation, view, IMappingNameConstants.HIDE_ROLE_NAMES);
  }

  /**
   * @used in common.odesign Return true if "Show Modifiers is true"
   * @param assocation
   * @param view
   * @return
   */
  public boolean isShowModifiersEnable(EObject obj, EObject view) {
    return isDiagramFilterEnable(obj, view, IMappingNameConstants.SHOW_MODIFIERS);
  }

  /**
   * @used in common.odesign Return true if given filter is true
   * @param assocation
   * @param view
   * @return
   */
  public boolean isDiagramFilterEnable(EObject assocation, EObject view, String filterName) {
    if (null != view) {
      // get Diagram
      DDiagram diagram = CapellaServices.getService().getDiagramContainer(view);

      if (diagram != null) {
        EList<FilterDescription> activatedFilters = diagram.getActivatedFilters();
        for (FilterDescription filterDescription : activatedFilters) {
          // if given filter is enable return true
          if ((null != filterDescription) && filterDescription.getName().equalsIgnoreCase(filterName)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  /**
   * Return association begin label
   * 
   * @param context
   *          : context
   * @param property
   *          : Property
   * @param view
   *          : current diagram element view
   * @param showRoleName
   *          : decide weather to display the role name
   * @return
   */
  public String getAssociationBeginRoleLabel(EObject association, EObject context, EObject property, EObject view) {
    StringBuilder beginLabel = new StringBuilder();
    if (association instanceof Association && property instanceof Property) {
      Property pro = (Property) property;

      Association asso = (Association) association;
      boolean hideRoleLabelEnable = isHideRoleLabelEnable(context, view);
      boolean hideRoleNameEnable = isHideRoleNameEnable(context, view);

      // multiplicity
      String multiplicityToString = multiplicityToString(pro);
      beginLabel.append(multiplicityToString);
      if (!multiplicityToString.equals(ICommonConstants.EMPTY_STRING)) {
        beginLabel.append(ICommonConstants.WHITE_SPACE_CHARACTER);
      }
      // prefix
      if (!hideRoleLabelEnable) {
        beginLabel.append(PropertyNamingHelper.prefixPropertyLabel(pro));
        // isDerived
        if (pro.isIsDerived()) {
          beginLabel.append(ICommonConstants.SLASH_CHARACTER);
        }
        // role name (consider only if filter is disable)
        if (!hideRoleNameEnable
            && (asso.getNavigableMembers().contains(pro) || asso.getNavigableMembers().size() != 1)) {
          beginLabel.append(pro.getName());
        }
        // Show Modifiers suffix
        if (isShowModifiersEnable(context, view)) {
          if (pro.isOrdered()) {
            beginLabel.append(" {ordered}");
          }
          if (!pro.isUnique()) {
            beginLabel.append(" {nonUnique}");
          }
        }
      }
    }
    return beginLabel.toString();
  }

  /**
   * Return Association Center label
   * 
   * @param association
   *          : an Association
   * @param view
   *          : an Association view
   * @return : association center label
   */
  public String getAssociationCenterLabel(EObject association, EObject view) {
    // why white space char
    // The manual refresh of the diagram does not take into account the EmptySting
    String centerLabel = Character.toString(ICommonConstants.WHITE_SPACE_CHARACTER);
    if (association instanceof Association) {
      Association ass = (Association) association;
      if (!isHideAssociationLabelEnable(association, view)) {
        return ass.getName();
      }
    }
    return centerLabel;
  }

  /**
   * Return association end label
   * 
   * @param context
   *          : context
   * @param property
   *          : Property
   * @param view
   *          : current diagram element view
   * @return
   */
  public String getAssociationEndRoleLabel(EObject association, EObject context, EObject property, EObject view) {
    StringBuilder endLabel = new StringBuilder();
    if (association instanceof Association && property instanceof Property) {

      Property pro = (Property) property;
      Association asso = (Association) association;
      boolean hideRoleLabelEnable = isHideRoleLabelEnable(context, view);
      boolean hideRoleNameEnable = isHideRoleNameEnable(context, view);

      // multiplicity
      String multiplicityToString = multiplicityToString(pro);
      endLabel.append(multiplicityToString);
      if (!multiplicityToString.equals(ICommonConstants.EMPTY_STRING)) {
        endLabel.append(ICommonConstants.WHITE_SPACE_CHARACTER);
      }
      // prefix
      if (!hideRoleLabelEnable) {
        endLabel.append(PropertyNamingHelper.prefixPropertyLabel(pro));
        // isDerived
        if (pro.isIsDerived()) {
          endLabel.append(ICommonConstants.SLASH_CHARACTER);
        }
        // role name (consider only if filter is disable)
        if (!hideRoleNameEnable
            && (asso.getNavigableMembers().contains(pro) || asso.getNavigableMembers().size() != 1)) {
          endLabel.append(pro.getName());
        }
        // Show Modifiers
        if (isShowModifiersEnable(context, view)) {
          if (pro.isOrdered()) {
            endLabel.append(" {ordered}");
          }
          if (!pro.isUnique()) {
            endLabel.append(" {nonUnique}");
          }
        }
      }
    }
    return endLabel.toString();
  }

  /**
   * Return project explorer label of given element (if element is type NumericType, take into consideration the unit
   * name)
   */
  public String getDatavalueLabel(EObject context) {
    return CapellaServices.getService().getEObjectLabelProviderHelper(context);
  }

  public List<Interface> getAvailableInterfacesFromComponentToInsert(EObject context) {
    List<Interface> result = new ArrayList<>();
    Component component = (Component) ((DSemanticDecorator) context).getTarget();
    result.addAll(ComponentExt.getAllImplementedAndProvidedInterfaces(component));
    result.addAll(ComponentExt.getAllUsedAndRequiredInterfaces(component));
    return result;
  }

  /**
   * Create Edge view and target view if needed in the diagram
   * 
   * @param sourceView
   * @param diagram
   * @param link
   */
  private void createEdgeViewWithTargetViewIfNeeded(EObject sourceView, DDiagram diagram, EObject link) {
    DiagramServices diagramServices = DiagramServices.getDiagramServices();

    if (!diagramServices.isOnDiagram(diagram, link)) {
      EObject targetElement = InterfaceExt.getTargetElementFromLink(link);
      AbstractDNode targetView = null;
      // create or retrieve targetView
      if ((null != targetElement) && diagramServices.isOnDiagram(diagram, targetElement)) {
        // retrieve target view
        EObject diagramElement = diagramServices.getDiagramElement(diagram, targetElement);
        if (diagramElement instanceof AbstractDNode) {
          targetView = (AbstractDNode) diagramElement;
        }
      } else {
        // create target view
        // get targetElement mapping from given diagram
        AbstractNodeMapping mapping = getTargetMappingOfGivenEdgeFromGivenDiagram(diagram, targetElement);
        if (null != mapping) {
          targetView = diagramServices.createAbstractDNodeContainer(mapping, targetElement, diagram, diagram);
        }
      }
      // create Edge
      if ((null != targetView) && (sourceView instanceof EdgeTarget) && (targetView instanceof EdgeTarget)) {
        // get edge mapping to be created for given link and diagram
        List<EdgeMapping> edgeMappings = getEdgeMappingFromGivenDiagram(link, diagram, false, false);
        for (EdgeMapping edgeMapping : edgeMappings) {
          diagramServices.createEdge(edgeMapping, (EdgeTarget) sourceView, (EdgeTarget) targetView, link);
        }
      }
    }
  }

  /**
   * return true if actual mapping found for current representation
   * 
   * @param representation
   *          : an edge
   * @param mappingToCheck
   *          : list of valid mapping
   * @return
   */
  private boolean isValidActualMapping(DEdge representation, List<String> mappingToCheck) {
    IEdgeMapping actualMapping = representation.getActualMapping();
    if (actualMapping instanceof EdgeMapping) {
      String mappingName = ((EdgeMapping) actualMapping).getName();
      if (mappingToCheck.contains(mappingName)) {
        return true;
      }
    }
    return false;
  }

  /**
   * return existing interface from diagram
   * 
   * @param context
   * @return
   */
  public List<Interface> getExistingInterfacesFromDiagram(EObject context) {
    List<Interface> available = getAvailableInterfacesFromComponentToInsert(context);
    List<Interface> result = new ArrayList<>();
    // filter 'context' as 'DDiagramElementContainer'
    if (context instanceof AbstractDNode) {
      AbstractDNode currentContainer = (AbstractDNode) context;
      // retrieve Diagram
      DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(currentContainer);
      // add existing association in Diagram to the result list.
      for (DRepresentationElement aNode : currentDiagram.getOwnedRepresentationElements()) {
        if ((aNode.getTarget() instanceof Interface) && available.contains(aNode.getTarget())) {
          result.add((Interface) aNode.getTarget());
        }
      }
    }
    return result;
  }

  /**
   * Return all the available links from CRB Diagram (i.e involvement, extends, includes, CapReal Generalization, Actor
   * Generalisation)
   * 
   * @param context
   * @return
   */
  public List<CapellaElement> getAvailableLinksForCRBDiagram(EObject context) {
    List<CapellaElement> result = new ArrayList<>();
    EObject target = ((DSemanticDecorator) context).getTarget();
    if (target instanceof CapabilityRealization) {
      CapabilityRealization element = (CapabilityRealization) target;
      EList<CapabilityRealizationInvolvement> capRealInvols = element.getOwnedCapabilityRealizationInvolvements();
      if (!capRealInvols.isEmpty()) {
        result.addAll(capRealInvols);
      }

      // extends
      EList<AbstractCapability> extendedCapabilities = element.getExtendedAbstractCapabilities();
      if (!extendedCapabilities.isEmpty()) {
        for (AbstractCapability abstractCapability : extendedCapabilities) {
          EList<AbstractCapabilityExtend> extending = abstractCapability.getExtending();
          if (!extending.isEmpty()) {
            result.addAll(extending);
          }
        }
      }
      // includes
      EList<AbstractCapability> includesCapabilities = element.getIncludedAbstractCapabilities();
      if (!includesCapabilities.isEmpty()) {
        for (AbstractCapability abstractCapability : includesCapabilities) {
          EList<AbstractCapabilityInclude> including = abstractCapability.getIncluding();
          if (!including.isEmpty()) {
            result.addAll(including);
          }
        }
      }
      // generalization
      EList<AbstractCapability> superAbstractCapabilities = element.getSuper();
      if (!superAbstractCapabilities.isEmpty()) {
        for (AbstractCapability abstractCapability : superAbstractCapabilities) {
          EList<AbstractCapabilityGeneralization> subGeneralizations = abstractCapability.getSubGeneralizations();
          if (!subGeneralizations.isEmpty()) {
            result.addAll(subGeneralizations);
          }
        }
      }
    } else if (target instanceof Component) {
      Component element = (Component) target;
      EList<Generalization> ownedGeneralizations = element.getOwnedGeneralizations();
      if (!ownedGeneralizations.isEmpty()) {
        result.addAll(ownedGeneralizations);
      }
    }
    return result;
  }

  /**
   * return existing links from CRB Diagram
   */
  public List<CapellaElement> getExistingLinksFromCRBDiagram(EObject sourceView) {
    List<CapellaElement> result = new ArrayList<>();
    DDiagram diagramContainer = CapellaServices.getService().getDiagramContainer(sourceView);
    EObject sourceTarget = ((DSemanticDecorator) sourceView).getTarget();
    if ((null != sourceTarget) && (null != diagramContainer)) {
      List<CapellaElement> availableLinksForCRBDiagram = getAvailableLinksForCRBDiagram(sourceView);
      if ((sourceTarget instanceof CapabilityRealization) || (sourceTarget instanceof Component)) {
        // add edges from diagram
        for (CapellaElement capellaElement : availableLinksForCRBDiagram) {
          if (DiagramServices.getDiagramServices().isOnDiagram(diagramContainer, capellaElement)) {
            result.add(capellaElement);
          }
        }
      }
    }
    return result;
  }

  @Deprecated
  public void showHideRelationShipFromCRB(EObject sourceView, List<EObject> selectedElements) {
    showHideCRBRelationships(sourceView, selectedElements);
  }

  /**
   * @common.odesign show/hide links from CRB Diagram
   */
  public void showHideCRBRelationships(EObject sourceView, List<EObject> selectedElements) {

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView);
    DiagramServices diagramServices = DiagramServices.getDiagramServices();
    EObject sourceTarget = ((DSemanticDecorator) sourceView).getTarget();
    if (null == diagram) {
      return;
    }

    // Remove Elements From Diagram (only if diagram is synchronized)
    //
    if (!diagram.isSynchronized()) {
      List<CapellaElement> existingElements = getExistingLinksFromCRBDiagram(sourceView);
      // existing elements to be removed
      if (null != selectedElements) {
        existingElements.removeAll(selectedElements);
      }
      List<DEdge> edges = new ArrayList<>();
      if ((sourceTarget instanceof CapabilityRealization) || (sourceTarget instanceof Component)) {
        // remove use and implementation links
        for (DEdge representation : diagram.getEdges()) {
          EObject target = representation.getTarget();
          // sourceNode check
          EdgeTarget sourceNode = representation.getSourceNode();
          // mapping check (use and implementation)
          List<String> mappingCheckList = new ArrayList<>();
          mappingCheckList.add(IMappingNameConstants.CRB_INVOLVEMENT_MAPPING);
          mappingCheckList.add(IMappingNameConstants.CRB_EXTENDS_MAPPING);
          mappingCheckList.add(IMappingNameConstants.CRB_INCLIDE_MAPPING);
          mappingCheckList.add(IMappingNameConstants.CRB_CAP_GENERALIZATION_MAPPING);
          mappingCheckList.add(IMappingNameConstants.CRB_ACTOR_GENERALIZATION_MAPPING);
          if ((null != sourceNode) && sourceNode.equals(sourceView) && (null != target)
              && existingElements.contains(target) && isValidActualMapping(representation, mappingCheckList)) {
            // collect the representation to remove
            edges.add(representation);
          }
        }
      }
      // remove the representation via iterator
      Iterator<DEdge> iterator = edges.iterator();
      while (iterator.hasNext()) {
        DEdge dEdge = iterator.next();
        // remove edgeView action
        diagramServices.removeEdgeView(dEdge);
      }
    }

    // Create Edge if needed
    //
    if (selectedElements != null) {
      for (EObject selectedElement : selectedElements) {
        // when sourceView is Component
        if ((selectedElement instanceof CapabilityRealizationInvolvement)
            || (selectedElement instanceof AbstractCapabilityExtend)
            || (selectedElement instanceof AbstractCapabilityInclude)
            || (selectedElement instanceof AbstractCapabilityGeneralization)
            || (selectedElement instanceof Generalization)) {
          // create edge view and target if needed
          createEdgeViewWithTargetViewIfNeeded(sourceView, diagram, selectedElement);
        }
      }
    }
  }

  /**
   * Return all the available links from CRI Diagram (i.e involvement)
   * 
   * @param context
   * @return
   */
  public List<CapellaElement> getAvailableLinksForCRIDiagram(EObject context) {
    List<CapellaElement> result = new ArrayList<>();
    EObject target = ((DSemanticDecorator) context).getTarget();
    if (target instanceof CapabilityRealization) {
      CapabilityRealization element = (CapabilityRealization) target;
      EList<CapabilityRealizationInvolvement> capRealInvols = element
          .getOwnedCapabilityRealizationInvolvements();
      if (!capRealInvols.isEmpty()) {
        result.addAll(capRealInvols);
      }
    }
    return result;
  }

  /**
   * return existing links from CRI Diagram
   */
  public Set<CapellaElement> getExistingLinksFromCRIDiagram(EObject sourceView) {

    Set<CapellaElement> result = new HashSet<>();
    DDiagram diagramContainer = CapellaServices.getService().getDiagramContainer(sourceView);
    EObject sourceTarget = ((DSemanticDecorator) sourceView).getTarget();
    if ((null != sourceTarget) && (null != diagramContainer)) {
      List<CapellaElement> availableLinksForCRIDiagram = getAvailableLinksForCRIDiagram(sourceView);
      if ((sourceTarget instanceof CapabilityRealization) || (sourceTarget instanceof Component)) {
        // add edges from diagram
        for (CapellaElement capellaElement : availableLinksForCRIDiagram) {
          if (DiagramServices.getDiagramServices().isOnDiagram(diagramContainer, capellaElement)) {
            result.add(capellaElement);
          }
        }
      }
    }
    return result;
  }

  /**
   * @common.odesign show/hide links from CRI Diagram
   */
  public void showHideCRIRelationships(EObject sourceView, List<EObject> selectedElements) {

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView);
    DiagramServices diagramServices = DiagramServices.getDiagramServices();
    EObject sourceTarget = ((DSemanticDecorator) sourceView).getTarget();
    if (null == diagram) {
      return;
    }

    // Remove Elements From Diagram (only if diagram is synchronized)
    if (!diagram.isSynchronized()) {
      Set<CapellaElement> existingElements = getExistingLinksFromCRIDiagram(sourceView);
      // existing elements to be removed
      if (null != selectedElements) {
        existingElements.removeAll(selectedElements);
      }
      List<DEdge> edges = new ArrayList<>();
      if ((sourceTarget instanceof CapabilityRealization) || (sourceTarget instanceof Component)) {
        // remove use and implementation links
        for (DEdge representation : diagram.getEdges()) {
          EObject target = representation.getTarget();
          // sourceNode check
          EdgeTarget sourceNode = representation.getSourceNode();
          // mapping check (use and implementation)
          List<String> mappingCheckList = new ArrayList<>();
          mappingCheckList.add(IMappingNameConstants.CCRI_CAPABILITY_REALIZATION_INVOLVEMENT);
          if ((null != sourceNode) && sourceNode.equals(sourceView) && (null != target)
              && existingElements.contains(target) && isValidActualMapping(representation, mappingCheckList)) {
            // collect the representation to remove
            edges.add(representation);
          }
        }
      }

      for (DEdge edge : edges) {

        diagramServices.removeEdgeView(edge);
      }
    }

    // Create Edge if needed
    //
    if (selectedElements != null) {
      for (EObject selectedElement : selectedElements) {
        // when sourceView is Component
        if (selectedElement instanceof CapabilityRealizationInvolvement) {
          // create edge view and target if needed
          createEdgeViewWithTargetViewIfNeeded(sourceView, diagram, selectedElement);
        }
      }
    }
  }

  /**
   * Return actual mapping based on targetElement(a capella Element) from a Diagram
   * 
   * @param diagram
   * @param targetElement
   * @return
   */
  private AbstractNodeMapping getTargetMappingOfGivenEdgeFromGivenDiagram(DDiagram diagram, EObject targetElement) {
    AbstractNodeMapping actualMapping = null;
    if ((null == diagram) || (targetElement == null)) {
      return actualMapping;
    }
    DiagramServices diagramServices = DiagramServices.getDiagramServices();
    if (diagram.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      if (targetElement instanceof Component) {
        actualMapping = diagramServices.getContainerMapping(diagram, IMappingNameConstants.CRB_COMPONENT_MAPPING);
      } else if (targetElement instanceof CapabilityRealization) {
        actualMapping = diagramServices.getNodeMapping(diagram,
            IMappingNameConstants.CRB_CAPABILITY_REALIZATION_MAPPING);
      }
    } else if (targetElement instanceof Interface
        && diagram.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME)) {
      actualMapping = diagramServices.getContainerMapping(diagram, IMappingNameConstants.IDB_INTERFACE_MAPPING_NAME);
    } else if (diagram.getDescription().getName()
        .equalsIgnoreCase(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_REALIZATION_INVOLVEMENT)) {
      if (targetElement instanceof Component) {
        actualMapping = diagramServices.getNodeMapping(diagram, IMappingNameConstants.CCRI_COMPONENT);
      }
    }

    return actualMapping;
  }

  /**
   * get the Generalisation edge mapping from class blank diagram
   */
  private List<EdgeMapping> getEdgeMappingFromGivenDiagram(EObject element, DDiagram diagram, boolean providedEdge,
      boolean requiredEdge) {
    List<EdgeMapping> result = new ArrayList<>();

    if ((null == element) || (null == diagram)) {
      return result;
    }
    // CAPABILITY REALIZATION BLANK Diagram
    if (diagram.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      if (element instanceof CapabilityRealizationInvolvement) {
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram,
            IMappingNameConstants.CRB_INVOLVEMENT_MAPPING));
      } else if (element instanceof AbstractCapabilityExtend) {
        result.add(
            DiagramServices.getDiagramServices().getEdgeMapping(diagram, IMappingNameConstants.CRB_EXTENDS_MAPPING));
      } else if (element instanceof AbstractCapabilityInclude) {
        result.add(
            DiagramServices.getDiagramServices().getEdgeMapping(diagram, IMappingNameConstants.CRB_INCLIDE_MAPPING));
      } else if (element instanceof AbstractCapabilityGeneralization) {
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram,
            IMappingNameConstants.CRB_CAP_GENERALIZATION_MAPPING));
      } else if (element instanceof Generalization) {
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram,
            IMappingNameConstants.CRB_ACTOR_GENERALIZATION_MAPPING));
      }
    } else
    // INTERFACES DIAGRAM BLANK
    if (diagram.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME)) {
      if (element instanceof InterfaceImplementation) {
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram,
            IMappingNameConstants.IDB_IMPLEMENTATION_INTERFACE_MAPPING_NAME));
      } else if (element instanceof InterfaceUse) {
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram,
            IMappingNameConstants.IDB_USE_INTERFACE_MAPPING_NAME));
      } else if ((element instanceof ComponentPort) && providedEdge && !requiredEdge) {
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram,
            IMappingNameConstants.IDB_PROVIDED_INTERFACE_MAPPING_NAME));
      } else if ((element instanceof ComponentPort) && requiredEdge && !providedEdge) {
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram,
            IMappingNameConstants.IDB_REQUIRED_INTERFACE_MAPPING_NAME));
      } else if ((element instanceof ComponentPort) && requiredEdge && providedEdge) {
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram,
            IMappingNameConstants.IDB_PROVIDED_INTERFACE_MAPPING_NAME));
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram,
            IMappingNameConstants.IDB_REQUIRED_INTERFACE_MAPPING_NAME));
      }
    } else
    // CONTEXTUAL CAPABILITY REALIZATION INVOLVEMENT DIAGRAM
    if (diagram.getDescription().getName()
        .equalsIgnoreCase(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_REALIZATION_INVOLVEMENT)) {

      if (element instanceof CapabilityRealizationInvolvement) {
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram,
            IMappingNameConstants.CCRI_CAPABILITY_REALIZATION_INVOLVEMENT));
      }
    }

    return result;
  }

  /**
   * @param diagram
   * @param contextualElements
   */
  public void showCDBContextualElements(DDiagram diagram, Collection<EObject> contextualElements) {
    Collection<EObject> contextualTypes = new HashSet<>();
    Collection<EObject> contextualInterfaces = new HashSet<>();
    Collection<EObject> contextualExchangeItems = new HashSet<>();
    Collection<EObject> contextualRelationships = new HashSet<>();

    DDiagramContents content = new DDiagramContents(diagram);
    for (EObject contextualElement : contextualElements) {
      if (contextualElement instanceof org.polarsys.capella.core.data.information.Collection) {
        contextualTypes.add(contextualElement);

      } else if (contextualElement instanceof Class) {
        contextualTypes.add(contextualElement);

      } else if (contextualElement instanceof DataType) {
        contextualTypes.add(contextualElement);

      } else if (contextualElement instanceof Interface) {
        contextualInterfaces.add(contextualElement);

      } else if (contextualElement instanceof ExchangeItem) {
        contextualExchangeItems.add(contextualElement);
        contextualRelationships.addAll(((ExchangeItem) contextualElement).getOwnedElements());
      }

      if (contextualElement instanceof Classifier) {
        contextualRelationships
            .addAll(CapellaServices.getService().getRelatedAssociations((Classifier) contextualElement));
      }

      if (contextualElement instanceof GeneralizableElement) {
        contextualRelationships.addAll(((GeneralizableElement) contextualElement).getSuperGeneralizations());
      }

    }

    for (EObject object : contextualTypes) {
      showCDBType(object, content);
    }
    for (EObject object : contextualInterfaces) {
      showCDBInterface(object, content);
    }
    for (EObject object : contextualRelationships) {
      showCDBRelationship(object, content);
    }

  }

  public String getFullPathLableConsiderViewContentment(AbstractNamedElement context, EObject view) {
    List<String> resultList = new ArrayList<>(0);
    // make sure filter is enable an element in question is capella named element
    if (isDiagramFilterEnable(context, view, IMappingNameConstants.CDB_SHOW_FULL_PATH)) {
      if (view instanceof DDiagramElement) {
        DDiagramElement dElement = (DDiagramElement) view;
        EObject dElementContainer = dElement.eContainer();
        if (null != dElementContainer) {
          // container is a diagram
          if (dElementContainer instanceof DDiagram) {
            resultList = getFullNameUntilGivenElement(context, null, false);
          }
          // container is a dataPkg
          else if (dElementContainer instanceof DDiagramElement) {
            DDiagramElement dElelementContinerTyped = (DDiagramElement) dElementContainer;
            EObject dElelementContinerTarget = dElelementContinerTyped.getTarget();
            // continue if its named named element
            if (dElelementContinerTarget instanceof AbstractNamedElement) {
              resultList = getFullNameUntilGivenElement(context, dElelementContinerTarget, true);
            }
          }
        }
      }
    } else {
      return CapellaServices.getService().getEObjectLabelProviderHelper(context);
    }

    // reverse the list to get the right order to display
    Collections.reverse(resultList);
    // insert special character "::"
    StringBuilder result = new StringBuilder();
    Iterator<String> itResultList = resultList.iterator();
    while (itResultList.hasNext()) {
      result.append(itResultList.next());
      if (itResultList.hasNext()) {
        result.append("::");
      }
    }
    return result.toString();
  }

  /**
   * Return full path in a list for give capella element If @param checkUntileGivenElement : is true calculate full path
   * until @param dElelementContinerTarget
   * 
   * @param context
   * @param dElelementContinerTarget
   * @param checkUntileGivenElement
   */
  private List<String> getFullNameUntilGivenElement(AbstractNamedElement context, EObject dElelementContinerTarget,
      boolean checkUntileGivenElement) {
    List<String> resultList = new ArrayList<>(0);

    // check if full name need to be calculated until some element
    if (checkUntileGivenElement && ((null == dElelementContinerTarget) || context.equals(dElelementContinerTarget))) {
      return resultList;
    }

    // if root add symbol
    // else simple name
    if (isRoot(context)) {
      resultList.add(getSymbol(context));
    } else {
      resultList.add(CapellaServices.getService().getEObjectLabelProviderHelper(context));
    }

    // if any architecture later encountered
    // return the result
    if ((context instanceof ComponentArchitecture) || (context instanceof OperationalAnalysis)) {
      return resultList;
    }

    // retrieve container of the currentElement
    // apply recursive call
    EObject eContainer = context.eContainer();
    if (eContainer instanceof AbstractNamedElement) {
      resultList.addAll(getFullNameUntilGivenElement((AbstractNamedElement) eContainer, dElelementContinerTarget,
          checkUntileGivenElement));
    }

    return resultList;
  }

  /**
   * return Symbol
   * 
   * @param context
   * @return
   */
  private String getSymbol(AbstractNamedElement context) {
    if (context instanceof Entity) {
      return "[E]"; //$NON-NLS-1$
    } else if (context instanceof SystemComponent) {
      return "[S]";//$NON-NLS-1$
    } else if (context instanceof LogicalComponent) {
      return "[LS]";//$NON-NLS-1$
    } else if (context instanceof PhysicalComponent) {
      return "[PS]";//$NON-NLS-1$
    } else if (context instanceof ConfigurationItem) {
      return "[CI]";//$NON-NLS-1$
    } else if (context instanceof OperationalAnalysis) {
      return "[OA]";//$NON-NLS-1$
    } else if (context instanceof SystemAnalysis) {
      return "[SA]";//$NON-NLS-1$
    } else if (context instanceof LogicalArchitecture) {
      return "[LA]";//$NON-NLS-1$
    } else if (context instanceof PhysicalArchitecture) {
      return "[PA]";//$NON-NLS-1$
    } else if (context instanceof EPBSArchitecture) {
      return "[EPBSA]";//$NON-NLS-1$
    } else if (context instanceof EntityPkg) {
      return "[EPkg]";//$NON-NLS-1$
    } else if (context instanceof LogicalComponentPkg) {
      return "[PAPkg]";//$NON-NLS-1$
    } else if (context instanceof PhysicalComponentPkg) {
      return "[PAPkg]";//$NON-NLS-1$
    } else if (context instanceof ConfigurationItemPkg) {
      return "[PAPkg]";//$NON-NLS-1$
    }
    return null;
  }

  /**
   * check if element is root element in the model
   * 
   * @param context
   * @return
   */
  private boolean isRoot(AbstractNamedElement context) {
    if (context instanceof Component && !ComponentExt.isActor(context)) {
      return ComponentExt.isComponentRoot(context);
    } else if ((context instanceof ComponentArchitecture)
        || (context instanceof OperationalAnalysis)) {
      return true;
    } else if (context instanceof ComponentPkg) {
      EObject eContainer = context.eContainer();
      if ((null != eContainer)
          && ((eContainer instanceof ComponentArchitecture) || (eContainer instanceof OperationalAnalysis))) {
        return true;
      }
    }

    return false;
  }

  /**
   * @used in common.odesign Return true if "Show Full Path"
   * @param assocation
   * @param view
   * @return
   */
  public boolean isShowFullPathInClassDiagramEnable(EObject assocation, EObject view) {
    return isDiagramFilterEnable(assocation, view, IMappingNameConstants.CDB_SHOW_FULL_PATH);
  }

  /**
   * <b>Get all DataValues to insert</b>
   * <p>
   * Get all datavalue content in the selected element used in common.odesign
   * 
   * @param context
   * @param containerView
   * @return
   */
  public List<DataValue> getAvailableDataValuesToInsert(final EObject elementView) {

    List<DataValue> result = new ArrayList<>(1);

    // Get diagram
    DSemanticDiagram diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(elementView);

    /*
     * If Diagram
     */
    if (elementView.equals(diagram)) {
      // Get all dataPkg (consider layers)
      EObject diagramTarget = diagram.getTarget();
      // Get all dataVales and add to result list
      result.addAll(QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_DATA_VALUES_FOR_LIB, diagramTarget));
      return filterDataValues(result);
    }
    /*
     * If DNode Container (assume target as DataPkg)
     */
    else if (elementView instanceof DNodeContainer) {
      List<DataPkg> listPackages = new ArrayList<>(1);
      // Get target
      EObject containerTarget = ((DNodeContainer) elementView).getTarget();
      // If DataPkg
      if (containerTarget instanceof DataPkg) {
        DataPkg currentPkg = (DataPkg) containerTarget;
        // Get all dataPkg (recursively from current dataPkg)
        listPackages.add(currentPkg);
        listPackages.addAll(DataPkgExt.getRecursiveSubDataPkgs(currentPkg));

        // Get all dataVales and add to result
        result.addAll(DataPkgExt.getDataValues(listPackages));
      }
      return filterDataValues(result);
    }
    /*
     * If DNodeList (Class, DataType, etc)
     */
    else if (elementView instanceof DNodeList) {
      // Get target
      EObject nodeListTarget = ((DNodeList) elementView).getTarget();
      // If Class
      if (nodeListTarget instanceof Class) {
        Class currentClass = (Class) nodeListTarget;
        // Add the DataValues from Class to the result list
        Iterator<DataValue> itDataValues = currentClass.getOwnedDataValues().iterator();
        while (itDataValues.hasNext()) {
          result.add(itDataValues.next());
        }
        return filterDataValues(result);
      }
      // If DataType
      else if (nodeListTarget instanceof DataType) {
        DataType currentDataType = (DataType) nodeListTarget;
        // Add the DataValues from DataType to the result list
        result.addAll(DataTypeExt.getAllDataValuesFromDataType(currentDataType));
        return (currentDataType instanceof Enumeration || currentDataType instanceof BooleanType) ? result
            : filterDataValues(result);
      } else if (nodeListTarget instanceof org.polarsys.capella.core.data.information.Collection) {
        org.polarsys.capella.core.data.information.Collection currentClass = (org.polarsys.capella.core.data.information.Collection) nodeListTarget;
        // Add the DataValues from Class to the result list
        Iterator<DataValue> itDataValues = currentClass.getOwnedDataValues().iterator();
        while (itDataValues.hasNext()) {
          result.add(itDataValues.next());
        }
        return filterDataValues(result);
      }
    }
    return result;
  }

  /**
   * Removes the EnumrationLiterals which are contained in Enumeration and LiteralBooleanValue which are contained in
   * BooleanType.
   * 
   * @param values
   * @return
   */
  private List<DataValue> filterDataValues(List<DataValue> values) {
    Iterator<DataValue> iterator = new ArrayList<>(values).iterator();
    while (iterator.hasNext()) {
      DataValue next = iterator.next();
      if ((next instanceof EnumerationLiteral && next.eContainer() instanceof Enumeration)
          || (next instanceof LiteralBooleanValue && next.eContainer() instanceof BooleanType)) {
        values.remove(next);
      }
    }
    return values;
  }

  /**
   * <b>Return initial selection of the data value for transfer wizard</b>
   * <p>
   * Used in common.odesign
   * 
   * @param elementView
   * @return return list of dataValues
   */
  public List<DataValue> getInitialSelectionOfShowHideDataValues(final EObject elementView) {
    // Initialization
    List<DataValue> result = new ArrayList<>(1);

    // get diagram
    DSemanticDiagram diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(elementView);
    /*
     * If Diagram or DNodeContainer
     */
    if (elementView.equals(diagram)) {
      // get all nodes from the diagram
      EList<DNode> diagramElements = diagram.getNodes();
      for (DNode dDiagramElement : diagramElements) {
        EObject target = dDiagramElement.getTarget();
        // consider only dataValues
        if (target instanceof DataValue) {
          result.add((DataValue) target);
        }
      }
    }
    /*
     * If DNodeContainer
     */
    if (elementView instanceof DNodeContainer) {
      // get all nodes from the diagram
      EList<DNode> diagramElements = ((DNodeContainer) elementView).getNodes();
      for (DNode dDiagramElement : diagramElements) {
        EObject target = dDiagramElement.getTarget();
        // consider only dataValues
        if (target instanceof DataValue) {
          result.add((DataValue) target);
        }
      }
    }
    /*
     * If DNodeList (Class, DataType, etc)
     */
    else if (elementView instanceof DNodeList) {
      // get target
      DNodeList nodeList = (DNodeList) elementView;
      EList<DNodeListElement> ownedElements = nodeList.getOwnedElements();
      for (DNodeListElement dDiagramElement : ownedElements) {
        EObject target = dDiagramElement.getTarget();
        if (target instanceof DataValue) {
          result.add((DataValue) target);
        }
      }
    }

    return result;
  }

  public static Object getEILabel(AbstractExchangeItem ei, boolean showExchangeItemsParameters) {
    return ExchangeItemExt.getEILabel(ei, showExchangeItemsParameters);
  }

  class DependencyPair {
    final AbstractDependenciesPkg src;
    final AbstractDependenciesPkg tar;

    public DependencyPair(AbstractDependenciesPkg src, AbstractDependenciesPkg tar) {
      this.src = src;
      this.tar = tar;
    }

    @Override
    public boolean equals(Object obj) {
      return (obj instanceof DependencyPair)
          && ((src == ((DependencyPair) obj).src) && (tar == ((DependencyPair) obj).tar));
    }

    @Override
    public int hashCode() {
      int result = 17;
      result = (src.hashCode() * result) + tar.hashCode();
      return result;
    }
  }

  /**
   * Check if the dependency between two packages is a primitive one (cannot be deduced from sub-packages' dependencies)
   * 
   * @param src
   *          The depending package
   * @param tar
   *          The dependent package
   * @return
   */
  boolean isPrimitiveDependency(AbstractDependenciesPkg src, AbstractDependenciesPkg tar) {
    if (src instanceof DataPkg) {
      return DataPkgExt.isPrimitiveDependency((DataPkg) src, tar);
    }
    // InterfacePkg
    return InterfacePkgExt.isPrimitiveDependency((InterfacePkg) src, tar);
  }

  /**
   * Get the dependent packages of a package with the condition that if all child dependencies are displayed already,
   * there's no need to display the parent dependency
   * 
   * @param pkg
   * @param diagram
   * @return
   */
  public Collection<AbstractDependenciesPkg> getDependentPackages2(final AbstractDependenciesPkg pkg,
      final DDiagram diagram) {
    Collection<AbstractDependenciesPkg> depPkgs = new HashSet<>();
    for (AbstractDependenciesPkg depPkg : AbstractDependenciesPkgExt.getDependencies2(pkg)) {
      if (shouldDisplayed(pkg, depPkg, diagram)) {
        depPkgs.add(depPkg);
      }
    }

    return depPkgs;
  }

  /**
   * Check if a dependency between two packages should be displayed
   * 
   * @param src
   * @param tar
   * @param diagram
   * @return
   */
  public boolean shouldDisplayed(AbstractDependenciesPkg src, AbstractDependenciesPkg tar, DDiagram diagram) {
    // If the dependency between two packages is primitive then it should be
    // displayed
    if (isPrimitiveDependency(src, tar)) {
      return true;
    }

    List<AbstractDependenciesPkg> srcSubPkgs = new ArrayList<>();
    List<AbstractDependenciesPkg> tarSubPkgs = new ArrayList<>();
    if (src instanceof DataPkg) {
      srcSubPkgs.addAll(((DataPkg) src).getOwnedDataPkgs());
    }
    // InterfacePkg
    else {
      srcSubPkgs.addAll(((InterfacePkg) src).getOwnedInterfacePkgs());
    }

    if (tar instanceof DataPkg) {
      tarSubPkgs.addAll(((DataPkg) tar).getOwnedDataPkgs());
    }
    // InterfacePkg
    else {
      tarSubPkgs.addAll(((InterfacePkg) tar).getOwnedInterfacePkgs());
    }

    // If the source package of the dependency is a leaf package (no child)
    if (srcSubPkgs.isEmpty()) {
      List<DependencyPair> depPairs = new ArrayList<>();
      Collection<AbstractDependenciesPkg> depPkgs = AbstractDependenciesPkgExt.getDependencies2(src);
      for (AbstractDependenciesPkg depPkg : depPkgs) {
        if (tarSubPkgs.contains(depPkg)) {
          depPairs.add(new DependencyPair(src, depPkg));
        }
      }

      for (DependencyPair dep : depPairs) {
        // For each dependency, if there's a child dependency that should be displayed
        // but not, display the dependency
        // in question
        if (shouldDisplayed(dep.src, dep.tar, diagram) && !isDisplayed(dep.src, dep.tar, diagram)) {
          return true;
        }
      }
    }
    // If the target package of the dependency is a leaf package (no child)
    else if (tarSubPkgs.isEmpty()) {
      List<DependencyPair> depPairs = new ArrayList<>();

      for (AbstractDependenciesPkg pkg : srcSubPkgs) {
        Collection<AbstractDependenciesPkg> depPkgs = AbstractDependenciesPkgExt.getDependencies2(pkg);
        for (AbstractDependenciesPkg depPkg : depPkgs) {
          if (depPkg == tar) {
            depPairs.add(new DependencyPair(pkg, tar));
          }
        }
      }

      for (DependencyPair dep : depPairs) {
        // For each dependency, if there's a child dependency that should be displayed
        // but not, display the dependency
        // in question
        if (shouldDisplayed(dep.src, dep.tar, diagram) && !isDisplayed(dep.src, dep.tar, diagram)) {
          return true;
        }
      }
    }
    // Dependency between parent packages
    else {
      List<DependencyPair> depPairs = new ArrayList<>();
      for (AbstractDependenciesPkg pkg : srcSubPkgs) {
        Collection<AbstractDependenciesPkg> depPkgs = AbstractDependenciesPkgExt.getDependencies2(pkg);
        for (AbstractDependenciesPkg depPkg : depPkgs) {
          if (tarSubPkgs.contains(depPkg)) {
            depPairs.add(new DependencyPair(pkg, depPkg));
          }
        }
      }

      for (DependencyPair dep : depPairs) {
        // For each dependency, if there's a child dependency that should be displayed
        // but not, display the dependency
        // in question
        if (shouldDisplayed(dep.src, tar, diagram) && !isDisplayed(dep.src, tar, diagram)
            && shouldDisplayed(src, dep.tar, diagram) && !isDisplayed(src, dep.tar, diagram)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Check if the dependency between two packages is displayed. If one of the package is invisible, the dependency is
   * not displayed
   * 
   * @param src
   *          the source package
   * @param tar
   *          the target package
   * @param diagram
   * @return
   */
  public boolean isDisplayed(AbstractDependenciesPkg src, AbstractDependenciesPkg tar, DDiagram diagram) {
    EObject srcPkg = DiagramServices.getDiagramServices().getDiagramElement(diagram, src);
    EObject tarPkg = DiagramServices.getDiagramServices().getDiagramElement(diagram, tar);
    return ((srcPkg != null) && (tarPkg != null));
  }

  /**
   * 
   * @param context
   * @param view
   * @return
   */
  public String modifiersSuffix(EObject context, EObject view) {
    String str = "";
    if (isShowModifiersEnable(context, view)) {
      if (context instanceof Property) {
        Property prop = (Property) context;
        if (prop.isOrdered()) {
          str += " {ordered}";
        }
        if (!prop.isUnique()) {
          str += " {nonUnique}";
        }
      }
      if (context instanceof ExchangeItemElement) {
        ExchangeItemElement eie = (ExchangeItemElement) context;
        if (eie.isOrdered()) {
          str += " {ordered}";
        }
        if (!eie.isUnique()) {
          str += " {nonUnique}";
        }
        if (!eie.isComposite()) {
          str += " {nonComposite}";
        }
      }
    }
    return str;
  }
}
