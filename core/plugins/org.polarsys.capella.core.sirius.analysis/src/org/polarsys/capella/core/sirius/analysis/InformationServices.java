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
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.internal.metamodel.spec.DNodeContainerSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.DNodeListSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.DSemanticDiagramSpec;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.viewpoint.AbstractDNode;
import org.eclipse.sirius.viewpoint.DContainer;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DEdge;
import org.eclipse.sirius.viewpoint.DNode;
import org.eclipse.sirius.viewpoint.DNodeContainer;
import org.eclipse.sirius.viewpoint.DNodeList;
import org.eclipse.sirius.viewpoint.DNodeListElement;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DragAndDropTarget;
import org.eclipse.sirius.viewpoint.EdgeTarget;
import org.eclipse.sirius.viewpoint.description.AbstractNodeMapping;
import org.eclipse.sirius.viewpoint.description.ContainerMapping;
import org.eclipse.sirius.viewpoint.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.description.IEdgeMapping;
import org.eclipse.sirius.viewpoint.description.NodeMapping;
import org.eclipse.sirius.viewpoint.description.filter.FilterDescription;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.common.ui.toolkit.dialogs.TransferTreeListDialog;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.StringHelper;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.ComponentContext;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemContext;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemPkg;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSContext;
import org.polarsys.capella.core.data.fa.AbstractFunctionalStructure;
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
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.information.impl.DataPkgImpl;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalContext;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.AbstractExchangeItemPkg;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalContext;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalContext;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;
import org.polarsys.capella.core.libraries.extendedqueries.QueryIdentifierConstants;
import org.polarsys.capella.core.model.helpers.AbstractDependenciesPkgExt;
import org.polarsys.capella.core.model.helpers.AbstractExchangeItemPkgExt;
import org.polarsys.capella.core.model.helpers.AssociationExt;
import org.polarsys.capella.core.model.helpers.ClassExt;
import org.polarsys.capella.core.model.helpers.CollectionExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.DataTypeExt;
import org.polarsys.capella.core.model.helpers.DataValueExt;
import org.polarsys.capella.core.model.helpers.ExchangeItemExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.ui.properties.providers.DependencyLabelProvider;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.queries.debug.QueryDebugger;

/**
 * Services for Information.
 */
public class InformationServices {

  /**
   * 
   */
  private static final String THROWS_WITH_WHITE_SPACE_CHAR = ICommonConstants.WHITE_SPACE_CHARACTER + "throws" + ICommonConstants.WHITE_SPACE_CHARACTER; //$NON-NLS-1$
  /**
   * 
   */
  private static final String RETURN_WITH_WHITE_SPACE_CHAR = ICommonConstants.WHITE_SPACE_CHARACTER + "returns" + ICommonConstants.WHITE_SPACE_CHARACTER; //$NON-NLS-1$
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
   * @param parameter_p the given parameter
   * @return the parameter types
   */
  public List<CapellaElement> getExchangeItemElementTypes(ExchangeItemElement parameter_p) {
    IBusinessQuery query =
        BusinessQueriesProvider.getInstance()
            .getContribution(InformationPackage.Literals.EXCHANGE_ITEM_ELEMENT, CapellacorePackage.Literals.TYPED_ELEMENT__TYPE);
    if (query != null) {
      return query.getAvailableElements(parameter_p);
    }
    return new ArrayList<CapellaElement>(0);
  }

  /**
   * Gets types which can be set by the parameters.
   * @param parameter_p the given parameter
   * @return the parameter types
   */
  public List<CapellaElement> getParameterTypes(Parameter parameter_p) {
    IBusinessQuery query =
        BusinessQueriesProvider.getInstance().getContribution(InformationPackage.Literals.PARAMETER,
            ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);
    if (query != null) {
      return query.getAvailableElements(parameter_p);
    }
    return new ArrayList<CapellaElement>(0);
  }

  /**
   * Gets types which can be set by the property.
   * @param property_p the given property
   * @return the property types
   */
  public List<CapellaElement> getPropertyTypes(Property property_p) {
    IBusinessQuery query =
        BusinessQueriesProvider.getInstance().getContribution(InformationPackage.Literals.PROPERTY,
            ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);
    if (query != null) {
      return query.getAvailableElements(property_p);
    }
    return new ArrayList<CapellaElement>(0);
  }

  /**
   * Convert the parameters to a String.
   * @param eventOperation_p the current operation
   * @return a string representing a list of parameter : param1:type1[0..*], param2:type2,
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  private String parametersToString(final AbstractEventOperation eventOperation_p) {
    StringBuffer sb = new StringBuffer();
    StringBuffer sbReturns = new StringBuffer();
    StringBuffer sbExceptions = new StringBuffer();
    StringBuffer sbExceptionsOfOperation = new StringBuffer();

    boolean first = true;
    boolean displayParenthesisIfEmpty = false;

    List<MultiplicityElement> parameters = null;
    List<MultiplicityElement> parameterReturns = new ArrayList<MultiplicityElement>();
    List<MultiplicityElement> parameterExceptions = new ArrayList<MultiplicityElement>();
    EStructuralFeature directionFeature = null;
    EStructuralFeature typeFeature = null;
    EStructuralFeature nameFeature = ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME;

    if (eventOperation_p instanceof ExchangeItemAllocation) {
      ExchangeItemAllocation itemAllocation = (ExchangeItemAllocation) eventOperation_p;
      AbstractExchangeItem allocatedItem = itemAllocation.getAllocatedItem();
      if (allocatedItem instanceof ExchangeItem) {
        parameters = (List) ((ExchangeItem) allocatedItem).getOwnedElements();
        directionFeature = InformationPackage.Literals.EXCHANGE_ITEM_ELEMENT__DIRECTION;
        typeFeature = CapellacorePackage.Literals.TYPED_ELEMENT__TYPE;
      }
    } else if (eventOperation_p instanceof Operation) {
      displayParenthesisIfEmpty = true;
      Operation operation = (Operation) eventOperation_p;
      parameters = (List) operation.getOwnedParameters();
      directionFeature = InformationPackage.Literals.PARAMETER__DIRECTION;
      typeFeature = CapellacorePackage.Literals.TYPED_ELEMENT__TYPE;
    }

    if (parameters != null) {

      if ((parameters.size() > 0) || ((parameters.size() == 0) && displayParenthesisIfEmpty)) {
        sb.append(ICommonConstants.PARENTHESIS_OPEN_CHARACTER);
      }

      for (MultiplicityElement currentParameter : parameters) {
        Object directionObj = currentParameter.eGet(directionFeature);
        if (directionObj instanceof ParameterDirection) {
          ParameterDirection direction = (ParameterDirection) directionObj;
          if ((direction == ParameterDirection.IN) || (direction == ParameterDirection.OUT) || (direction == ParameterDirection.INOUT)
              || (direction == ParameterDirection.UNSET)) {
            if (first) {
              first = false;
            } else {
              sb.append(COMMA_WITH_SPACE);
            }
            // In, Out, InOut, and UnSet related string
            sb.append(parameterToString(currentParameter, (AbstractType) currentParameter.eGet(typeFeature), (String) currentParameter.eGet(nameFeature),
                direction, true, true, true));
          } else if (direction == ParameterDirection.RETURN) {
            // return sting
            parameterReturns.add(currentParameter);
          } else if (direction == ParameterDirection.EXCEPTION) {
            // exception string
            parameterExceptions.add(currentParameter);
          }
        }
      }

      if ((parameters.size() > 0) || ((parameters.size() == 0) && displayParenthesisIfEmpty)) {
        sb.append(ICommonConstants.PARENTHESIS_CLOSE_CHARACTER);
      }

    }

    // Return
    if (!parameterReturns.isEmpty()) {
      parametersToStringReturnAndException(sbReturns, typeFeature, nameFeature, parameterReturns, RETURN_WITH_WHITE_SPACE_CHAR);
    }
    // Exception
    if (!parameterExceptions.isEmpty()) {
      parametersToStringReturnAndException(sbExceptions, typeFeature, nameFeature, parameterExceptions, THROWS_WITH_WHITE_SPACE_CHAR);
    }
    // Exception of Service
    // retrieve exception if any
    boolean firstServiceExeption = true;
    boolean atLeastOneExcepIsNamed = false;
    if (eventOperation_p instanceof Service) {
      Service serviceOp = (Service) eventOperation_p;
      List<org.polarsys.capella.core.data.information.communication.Exception> thrownExceptions = serviceOp.getThrownExceptions();
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
          parameterToStringReturnAndException(sbExceptionsOfOperation, null, exceptionOp.eClass().getName(), exceptionOp.getName(), false, true, true, false);
        }
      }
      // since only one exception is selected from may be multiple unNamed
      if (!thrownExceptions.isEmpty() && !atLeastOneExcepIsNamed) {
        // qualifier
        if (parameterExceptions.isEmpty() && firstServiceExeption) {
          sbExceptionsOfOperation.append(THROWS_WITH_WHITE_SPACE_CHAR);
          firstServiceExeption = false;
        } else {
          sbExceptionsOfOperation.append(COMMA_WITH_SPACE);
        }
        // name and type
        parameterToStringReturnAndException(sbExceptionsOfOperation, null, CommunicationPackage.Literals.EXCEPTION.getName(), ICommonConstants.EMPTY_STRING,
            false, true, true, false);
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
   * @param parameter : MultiplicityElement
   * @param type : Type
   * @param name : ElementName
   * @param showName : decide weather to add name in return string
   * @param showCardinality : decide weather to show cardinality in return string
   * @return : String
   */
  private String parameterToString(MultiplicityElement parameter, AbstractType type, String name, ParameterDirection direction_p, boolean showDirection,
      boolean showName, boolean showCardinality) {
    StringBuffer sb = new StringBuffer();
    if (showName && (null != parameter)) {
      if (parameter instanceof ExchangeItemElement) {
        ExchangeItemElement exchangeItemElement = (ExchangeItemElement) parameter;
        if (ElementKind.PARAMETER.equals(exchangeItemElement.getKind())) {
          parameterToStringAppendDirection(direction_p, showDirection, sb);
        }
      } else if (parameter instanceof Parameter) {
        parameterToStringAppendDirection(direction_p, showDirection, sb);
      }

      sb.append(name);
      sb.append(ICommonConstants.COLON_CHARACTER);

    }

    if (type != null) {
      sb.append(type.getName());
    } else {
      sb.append(Messages.InformationServices_Undefined);
    }

    if (showCardinality) {
      getCardinalityAsString(parameter, sb);
    }

    return sb.toString();
  }

  private void parameterToStringAppendDirection(ParameterDirection direction_p, boolean showDirection, StringBuffer sb) {
    if ((null != direction_p) && showDirection) {
      sb.append(direction_p.getName());
      sb.append(ICommonConstants.WHITE_SPACE_CHARACTER);
    }
  }

  /**
   * Convert the given return and exception kind parameter to string Case 1 : If one of parameter is named, the return sting will avoid unNamed Elements of same
   * kind Case 2 : If all the parameters if same type are unNamed, return the type only once
   * @param sb_p : return string to be stored
   * @param typeFeature : to retrieve type of an element
   * @param nameFeature : to retrieve name of an element
   * @param parameters_p : multiplicity elements
   * @param qualifier_p : return or exception kind qualifier
   */
  private void parametersToStringReturnAndException(StringBuffer sb_p, EStructuralFeature typeFeature, EStructuralFeature nameFeature,
      List<MultiplicityElement> parameters_p, String qualifier_p) {
    // Collection multiplicityElement of same Type
    Map<String, List<MultiplicityElement>> typeToMulElesMap = new HashMap<String, List<MultiplicityElement>>();
    for (MultiplicityElement currentParameter : parameters_p) {
      AbstractType type = (AbstractType) currentParameter.eGet(typeFeature);
      if (null != type) {
        String typeName = type.getName();
        if (typeToMulElesMap.containsKey(typeName)) {
          List<MultiplicityElement> existingMulEles = typeToMulElesMap.get(typeName);
          existingMulEles.add(currentParameter);
        } else {
          List<MultiplicityElement> newMulEles = new ArrayList<MultiplicityElement>();
          newMulEles.add(currentParameter);
          typeToMulElesMap.put(typeName, newMulEles);
        }
      }
    }

    Set<String> types = typeToMulElesMap.keySet();
    boolean first = true;
    for (String type : types) {
      // direction, name, Type and cardinality
      boolean atLeastOneMulEleIsNamed = false;
      List<MultiplicityElement> retrievedMulEles = typeToMulElesMap.get(type);
      // If one of the multiplicityElement is named, avoid unNamed Element
      // if non is named, return the Type once (instead of multiple
      // unNamed types)
      for (MultiplicityElement retrievedMulEle : retrievedMulEles) {
        String mulEleName = (String) retrievedMulEle.eGet(nameFeature);
        if (!mulEleName.equals(ICommonConstants.EMPTY_STRING)) {
          atLeastOneMulEleIsNamed = true;
        }
        if (atLeastOneMulEleIsNamed && !mulEleName.equals(ICommonConstants.EMPTY_STRING)) {
          first = setQualifier(sb_p, qualifier_p, first);
          // direction, name, type and Cardinality
          parameterToStringReturnAndException(sb_p, retrievedMulEle, type, mulEleName, false, true, true, false);
        }
      }
      // since only one type is selected from may be multiple unNamed
      // Types, there is no need of cardinality
      if (!atLeastOneMulEleIsNamed) {
        first = setQualifier(sb_p, qualifier_p, first);
        parameterToStringReturnAndException(sb_p, null, type, ICommonConstants.EMPTY_STRING, false, true, true, false);
      }
    }
  }

  /**
   * add the qualifier string to stringBuffer if first time else add comma
   * @param sb_p
   * @param qualifier_p
   * @param first_p
   * @return
   */
  private boolean setQualifier(StringBuffer sb_p, String qualifier_p, boolean first_p) {
    boolean flag = first_p;
    // qualifier
    if (flag) {
      sb_p.append(qualifier_p);
      flag = false;
    } else {
      sb_p.append(COMMA_WITH_SPACE);
    }
    return flag;
  }

  private String parameterToStringReturnAndException(StringBuffer sb_p, MultiplicityElement parameter, String type, String name, boolean showDirection,
      boolean showName, boolean showType, boolean showCardinality) {
    if (showName) {
      // Direction
      if (showDirection) {
        if (parameter instanceof ExchangeItemElement) {
          ExchangeItemElement exchangeItemElement = (ExchangeItemElement) parameter;
          ParameterDirection direction = exchangeItemElement.getDirection();
          if (ElementKind.PARAMETER.equals(exchangeItemElement.getKind())) {
            if (null != direction) {
              sb_p.append(direction.getName());
              sb_p.append(ICommonConstants.WHITE_SPACE_CHARACTER);
            }
          }
        }
      }
      sb_p.append(name);
      sb_p.append(ICommonConstants.COLON_CHARACTER);

    }
    // Type
    if (!type.equals(ICommonConstants.EMPTY_STRING)) {
      sb_p.append(type);
    } else {
      sb_p.append(Messages.InformationServices_Undefined);
    }

    if (showCardinality) {
      getCardinalityAsString(parameter, sb_p);
    }

    return sb_p.toString();
  }

  /**
   * @param parameter
   * @param sb
   */
  private void getCardinalityAsString(MultiplicityElement parameter, StringBuffer sb) {
    if (null == parameter) {
      return;
    }

    String resultMin = getCardValue(parameter.getOwnedMinCard());
    String resultMax = getCardValue(parameter.getOwnedMaxCard());

    if (!((parameter.getOwnedMaxCard() instanceof LiteralNumericValue) && (parameter.getOwnedMinCard() instanceof LiteralNumericValue) && "1".equals(resultMin) && "1".equals(resultMax))) { //$NON-NLS-1$ //$NON-NLS-2$
      sb.append("["); //$NON-NLS-1$
      sb.append(resultMin);
      sb.append(".."); //$NON-NLS-1$
      sb.append(resultMax);
      sb.append("]"); //$NON-NLS-1$
    }
  }

  /**
   * Reconnect an association. used in common.odesign
   * @param element_p the given Association
   * @param source_p the given Classifier
   * @param target_p the given Classifier
   * @param property_p the given Property
   * @return element_p
   */
  public EObject reconnectAssociation(EObject element_p, EObject source_p, EObject target_p, EObject property_p) {
    // Move property according to navigability and create union property
    // instead of property if target is an union

    Association association = (Association) element_p;
    Classifier source = (Classifier) source_p;
    Classifier target = (Classifier) target_p;
    Property sourceProperty = (Property) property_p;

    Property targetProperty = getOthers(sourceProperty, getAssociationProperties(association));

    sourceProperty.setAbstractType(target);

    Property createdProperty = null;

    // If an unionproperty was present or unionproperty is required, make a
    // copy of the current property
    if ((target instanceof Union) && !(targetProperty instanceof UnionProperty) && association.getNavigableMembers().contains(targetProperty)) {
      createdProperty = InformationFactory.eINSTANCE.createUnionProperty();
    } else if (!(target instanceof Union) && (targetProperty instanceof UnionProperty)) {
      createdProperty = InformationFactory.eINSTANCE.createProperty();
    }

    if (createdProperty != null) {

      createdProperty.setName(targetProperty.getName());
      createdProperty.setAbstractType(targetProperty.getAbstractType());
      createdProperty.setAggregationKind(targetProperty.getAggregationKind());
      createdProperty.setOwnedMaxCard(targetProperty.getOwnedMaxCard());
      createdProperty.setOwnedMinCard(targetProperty.getOwnedMinCard());

      association.getOwnedMembers().add(createdProperty);

      if (association.getNavigableMembers().contains(targetProperty)) {
        target.getOwnedFeatures().add(createdProperty);
        association.getNavigableMembers().add(createdProperty);
        association.getNavigableMembers().remove(targetProperty);
        CapellaServices.getService().removeElement(targetProperty);
      } else {
        CapellaServices.getService().removeElement(targetProperty);
      }
    }

    // Update container of navigable property
    for (Property property : getAssociationProperties(association)) {
      if (property.eContainer().equals(source)) {
        target.getOwnedFeatures().add(property);
      }
    }

    // move association to its correct container if necessary
    AssociationExt.moveToCorrectContainer(association);

    return association;
  }

  /**
   * convert a multiplicity to a string
   * FIXME duplicate used in common.odesign and context.odesign
   * @param element
   * @return
   */
  public String multiplicityToString(MultiplicityElement element) {
    return multiplicityToStringDisplay(element);
  }

  /**
   * convert a multiplicity to a string used in common.odesign and context.odesign
   * @param element
   * @return
   */
  public String multiplicityToStringDisplay(MultiplicityElement element) {
    NumericValue ownedMinCard = element.getOwnedMinCard();
    NumericValue ownedMaxCard = element.getOwnedMaxCard();
    if ((ownedMinCard == null) && (ownedMaxCard == null)) {
      return "[undefined]"; //$NON-NLS-1$
    }

    String minCard = getCardValue(ownedMinCard);
    String maxCard = getCardValue(ownedMaxCard);

    // rule 1: if min = max display max only (except if min and max equals 1, display void)
    boolean displayNothing = false;
    boolean displayOnlyMax = false;
    if (minCard.equalsIgnoreCase(maxCard) && !minCard.equals(ICommonConstants.EMPTY_STRING) && !maxCard.equals(ICommonConstants.EMPTY_STRING)) {
      if (minCard.equalsIgnoreCase("1")) { //$NON-NLS-1$
        displayNothing = true;
      } else {
        displayOnlyMax = true;
      }
    }

    // rule 2: if min=0 and max=* display only max
    if (minCard.equalsIgnoreCase("0") && maxCard.equalsIgnoreCase("*")) { //$NON-NLS-1$ //$NON-NLS-2$
      displayOnlyMax = true;
    }

    // rule 3: if minCard or maxCard are named, display the names
    String ownedminCardName = ownedMinCard.getName();
    String ownedmaxCardName = ownedMaxCard.getName();
    if ((null != ownedminCardName) && !ownedminCardName.equals(ICommonConstants.EMPTY_STRING)) {
      minCard = ownedminCardName;
      displayNothing = false;
      displayOnlyMax = false;
    }
    if ((null != ownedmaxCardName) && !ownedmaxCardName.equals(ICommonConstants.EMPTY_STRING)) {
      maxCard = ownedmaxCardName;
      displayNothing = false;
      displayOnlyMax = false;
    }

    if (displayNothing) {
      return ICommonConstants.EMPTY_STRING;
    } else if (displayOnlyMax) {
      return "[" //$NON-NLS-1$
             + maxCard + "]"; //$NON-NLS-1$
    }

    return "[" //$NON-NLS-1$
           + minCard + ".." //$NON-NLS-1$
           + maxCard + "]"; //$NON-NLS-1$

  }

  /**
   * Return cardValue or cardName depending on the NumericValue if 'numericValue_p' Type is LiteralNumericValue - return its value if 'numericValue_p' Type is
   * AbstractExpression - return its Name if 'numericValue_p' Type is NumericReference - return its Name [if referencedProperty - calculate cardName as
   * (OwnerClass name :: referencedPropertyName)
   * @param numericValue_p
   * @return cardValue or cardName depending on the NumericValue
   */
  public String getCardValue(NumericValue numericValue_p) {
    return DataValueExt.getCardValue(numericValue_p);
  }

  /**
   * used in common.odesign
   */
  public String computeLabel(Operation operation_p) {
    String result = operation_p.getName();
    result = result + parametersToString(operation_p);
    return result;
  }

  public String computeLabel(ExchangeCategory category_p) {
    String result = category_p.getName();
    return result;
  }

  public String computeLabel(Port port_p) {
    String result = port_p.getName();
    return result;
  }

  /**
   * compute the ExchangeItemAllocation label.
   * @param item_p : ExchangeItemAllocation
   * @return String
   */
  public String computeLabel(ExchangeItemAllocation item_p) {
    if (item_p == null) {
      return ICommonConstants.EMPTY_STRING;
    }
    String result = getAllocatedElementName(item_p);
    result = result + parametersToString(item_p);
    return result;
  }

  public String computeLabel(Property property) {
    if (property instanceof Port) {
      // Acceleo bug: it doesn't retrieve the best method according inheritance, it return the first method
      // compatible with the parameter. (so if the method computeLabel(Port) was defined before this method,
      // it will return it !!
      return computeLabel((Port) property);
    }
    String result = multiplicityToStringDisplay(property) + " " + getSymbolIfPropertyIsDerived(property) + property.getName(); //$NON-NLS-1$
    if (property.getType() != null) {
      result += " : " + property.getType().getName(); //$NON-NLS-1$
    }
    return result;
  }

  public String getSymbolIfPropertyIsDerived(Property property_p) {
    if (null != property_p) {
      if (property_p.isIsDerived()) {
        return String.valueOf(ICommonConstants.SLASH_CHARACTER);
      }
    }

    return ICommonConstants.EMPTY_STRING;
  }

  /**
   * Compute label.
   * @param link the given communicationLink
   * @return the String
   */
  public String computeLabel(CommunicationLink link) {
    return Messages.InformationServices_to + link.getExchangeItem().getName();
  }

  /**
   * Compute label.
   * @param element the given exchangeItemElement
   * @return the String
   */
  public String computeLabel(ExchangeItemElement element) {
    String result = computeLabelWithoutType(element);
    if (element.getType() != null) {
      result += " : " + ((AbstractNamedElement) element.getType()).getName(); //$NON-NLS-1$
    }
    return result;
  }

  /**
   * Compute label without type. used in common.odesign
   * @param element the given exchangeItemElement
   * @return the String
   */
  public String computeLabelWithoutType(ExchangeItemElement element) {
    String result = multiplicityToStringDisplay(element) + " " + element.getName(); //$NON-NLS-1$
    EList<Property> referencedProperties = element.getReferencedProperties();
    if (!referencedProperties.isEmpty()) {
      result = result + " {"; //$NON-NLS-1$
      Iterator<Property> iterator = referencedProperties.iterator();
      while (iterator.hasNext()) {
        Property property = iterator.next();
        result = result + property.getName();
        if (iterator.hasNext()) {
          result = result + COMMA_WITH_SPACE;
        }
      }
      result = result + "}"; //$NON-NLS-1$
    }
    return result;
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
   * @param association
   * @return
   */
  public Property getAssociationSource(Association association) {

    if (association.getNavigableMembers().size() == 1) {
      return getOthers(association.getNavigableMembers().get(0), association.getOwnedMembers());

    } else if (association.getNavigableMembers().size() == 2) {
      return association.getNavigableMembers().get(1);

    } else {
      return association.getOwnedMembers().get(0);
    }
  }

  /**
   * used in common.odesign
   * @param association
   * @return
   */
  public Collection<Property> getAssociationProperties(Association association) {
    HashSet<Property> properties = new HashSet<Property>();
    properties.addAll(association.getOwnedMembers());
    properties.addAll(association.getNavigableMembers());
    return properties;
  }

  /**
   * used in common.odesign
   * @param association
   * @return
   */
  public Property getAssociationTarget(Association association) {

    if (association.getNavigableMembers().size() == 1) {
      return association.getNavigableMembers().get(0);

    } else if (association.getNavigableMembers().size() == 2) {
      return association.getNavigableMembers().get(0);

    } else {
      return association.getOwnedMembers().get(1);
    }
  }

  public AssociationPkg getSourceClassPkg(Class sourceClass_p) {
    EObject sourceClassPkg = sourceClass_p.eContainer();
    if ((null != sourceClassPkg) && (sourceClassPkg instanceof AssociationPkg)) {
      return (AssociationPkg) sourceClassPkg;
    }
    return null;
  }

  public AssociationPkg getAssociationPkgCandidate(Class sourceClass_p, Association association_p, Class targeClass_p) {
    EObject associationPkgCandidate = null;
    // if association is unidirectional return the datapkg containing the association source class
    if (AssociationExt.isUnidirectional(association_p)) {
      associationPkgCandidate = sourceClass_p.eContainer();

    } else { // the association is bidirectional or has no direction => return the common ancestor of the source and target classes
      EObject commonAncestor = CapellaServices.getService().getCommonAncestor(sourceClass_p, targeClass_p);
      associationPkgCandidate = EcoreUtil2.getFirstContainer(commonAncestor, InformationPackage.Literals.ASSOCIATION_PKG);
    }
    if (associationPkgCandidate instanceof AssociationPkg) {
      return (AssociationPkg) associationPkgCandidate;
    }
    return null;
  }

  public String computeLabel(UnionProperty property) {
    String result = computeLabel((Property) property);
    EObject container = property.eContainer();
    if ((container != null) && (container instanceof Union)) {
      Union union = (Union) container;
      if (union.getDiscriminant() != null) {
        result = result + computeUnionPropertyLabelWithQualifier(property);
      }
    }

    return result;
  }

  /**
   * Return Customized label for UnioinProperty
   * @param property_p : UnionProperty
   * @return : customized lable for unionProperty
   */
  private String computeUnionPropertyLabelWithQualifier(UnionProperty property_p) {
    String result = ICommonConstants.EMPTY_STRING;
    EList<DataValue> qualifier = property_p.getQualifier();
    if ((qualifier != null) && (qualifier.size() > 0)) {
      result += " { "; //$NON-NLS-1$
      for (int i = 0; i < (qualifier.size() - 1); i++) {
        result += EObjectLabelProviderHelper.getText(qualifier.get(i)) + COMMA_WITH_SPACE;
      }
      result += EObjectLabelProviderHelper.getText(qualifier.get(qualifier.size() - 1));
      result += " }"; //$NON-NLS-1$
    }
    return result;
  }

  /**
   * used in common.odesign
   * @param association
   * @return
   */
  public EObject showHideProperties(EObject context_p, List<Property> selectedProperties_p, DDiagram diagram_p) {

    Map<Property, AbstractDNode> visibleElements = new HashMap<Property, AbstractDNode>();
    for (AbstractDNode aNode : ((DNodeList) context_p).getOwnedElements()) {
      if ((aNode.getTarget() != null) && (aNode.getTarget() instanceof Property)) {
        visibleElements.put((Property) aNode.getTarget(), aNode);
      }
    }
    for (Entry<Property, AbstractDNode> me : visibleElements.entrySet()) {
      if (!selectedProperties_p.contains(me.getKey())) {
        DiagramServices.getDiagramServices().removeNodeListElementView(me.getValue());
      }
    }
    for (Property aProperty : selectedProperties_p) {
      if (!visibleElements.containsKey(aProperty)) {
        createPropertyView(context_p, aProperty, diagram_p);
      }
    }
    return context_p;
  }

  private AbstractDNode createPropertyView(EObject context_p, Property property_p, DDiagram diagram_p) {
    String mappingName = IMappingNameConstants.CDB_PROPERTY_MAPPING_NAME;
    NodeMapping mapping = DiagramServices.getDiagramServices().getNodeMapping(diagram_p, mappingName);
    return DiagramServices.getDiagramServices().createDNodeListElement(mapping, property_p, (DragAndDropTarget) context_p, diagram_p);

  }

  @Deprecated
  public EObject showHideExchangeItems(EObject context_p, List<CapellaElement> selectedOperations_p, DDiagram diagram_p) {
    return showHideIBExchangeItems(context_p, selectedOperations_p, diagram_p);
  }

  /**
   * Show/Hide of AbstractEventOperation in classes and interfaces used in common.odesign
   */
  public EObject showHideIBExchangeItems(EObject context_p, List<CapellaElement> selectedOperations_p, DDiagram diagram_p) {

    Map<CapellaElement, AbstractDNode> visibleElements = new HashMap<CapellaElement, AbstractDNode>();
    for (DDiagramElement aNode : DiagramServices.getDiagramServices().getDiagramElements(diagram_p)) {
      if ((aNode instanceof AbstractDNode) && (aNode.getTarget() != null) && (aNode.getTarget() instanceof ExchangeItem)) {
        visibleElements.put((CapellaElement) aNode.getTarget(), (AbstractDNode) aNode);
      }
    }

    // remove elements
    for (Entry<CapellaElement, AbstractDNode> me : visibleElements.entrySet()) {
      if (!selectedOperations_p.contains(me.getKey())) {
        if (me.getValue() instanceof DNodeList) {
          DiagramServices.getDiagramServices().removeContainerView((DNodeList) me.getValue());
        } else if (me.getValue() instanceof DNode) {
          DiagramServices.getDiagramServices().removeNodeView((DNode) me.getValue());
        }
      }
    }
    // create elements
    for (CapellaElement aOperation : selectedOperations_p) {
      EObject container = CapellaServices.getService().getBestGraphicalContainer(aOperation, diagram_p, CsPackage.Literals.COMPONENT);
      if (!visibleElements.containsKey(aOperation)) {
        createExchangeItemView(container, aOperation, diagram_p);
      }
    }
    return context_p;
  }

  /**
   * @param context_p
   * @param aOperation_p
   * @param diagram_p
   */
  private AbstractDNode createExchangeItemView(EObject context_p, CapellaElement aOperation_p, DDiagram diagram_p) {
    String mappingName = ""; //$NON-NLS-1$
    boolean isContainerMapping = true;

    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      mappingName = IMappingNameConstants.IDB_EXCHANGE_ITEM_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCDI_EXCHANGE_ITEM_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCEI_EXCHANGE_ITEM_MAPPING_NAME;
      isContainerMapping = false;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCII_EXCHANGE_ITEM_MAPPING_NAME;
      isContainerMapping = false;
    }

    if (isContainerMapping) {
      ContainerMapping mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram_p, mappingName);
      return DiagramServices.getDiagramServices().createAbstractDNodeContainer(mapping, aOperation_p, (DragAndDropTarget) context_p, diagram_p);
    }

    NodeMapping mapping = DiagramServices.getDiagramServices().getNodeMapping(diagram_p, mappingName);
    return DiagramServices.getDiagramServices().createNode(mapping, aOperation_p, (DragAndDropTarget) context_p, diagram_p);

  }

  /**
   * @param context_p
   * @param aOperation_p
   * @param diagram_p
   */
  public ExchangeItem createExchangeItem(InterfacePkg pkg_p, Interface context, String variableName) {
    ExchangeItem item = InformationFactory.eINSTANCE.createExchangeItem();
    pkg_p.getOwnedExchangeItems().add(item);

    CapellaServices.getService().creationService(item);
    CsServices.getService().setInterpreterVariable(context, variableName, item);
    return item;
  }

  /**
   * @param context_p
   * @param aOperation_p
   * @param diagram_p
   */
  public CommunicationLink changeExchangeItem(CommunicationLink link, ExchangeItem item) {
    CommunicationLinkExt.changeExchangeItem(link, item);
    return link;
  }

  /**
   * Show/Hide of AbstractEventOperation in classes and interfaces used in common.odesign
   */
  public EObject showHideOperations(EObject context_p, List<CapellaElement> selectedOperations_p, DDiagram diagram_p) {

    Map<CapellaElement, AbstractDNode> visibleElements = new HashMap<CapellaElement, AbstractDNode>();
    for (AbstractDNode aNode : ((DNodeList) context_p).getOwnedElements()) {
      if ((aNode.getTarget() != null)
          && ((aNode.getTarget() instanceof Operation) || (aNode.getTarget() instanceof ExchangeItemElement) || (aNode.getTarget() instanceof ExchangeItemAllocation))) {
        visibleElements.put((CapellaElement) aNode.getTarget(), aNode);
      }
    }
    for (Entry<CapellaElement, AbstractDNode> me : visibleElements.entrySet()) {
      if (!selectedOperations_p.contains(me.getKey())) {
        DiagramServices.getDiagramServices().removeNodeListElementView(me.getValue());
      }
    }
    for (CapellaElement aOperation : selectedOperations_p) {
      if (!visibleElements.containsKey(aOperation)) {
        createOperationView(context_p, aOperation, diagram_p);
      }
    }
    return context_p;
  }

  @Deprecated
  public EObject showHideDataValues(EObject context_p, List<CapellaElement> selectedDataValues_p, DDiagram diagram_p) {
    return showHideCDBDataValues(context_p, selectedDataValues_p, diagram_p);
  }

  /**
   * Show/Hide of AbstractEventOperation in classes and interfaces used in common.odesign
   */
  public EObject showHideCDBDataValues(EObject context_p, List<CapellaElement> selectedDataValues_p, DDiagram diagram_p) {
    // Initialization
    Map<CapellaElement, AbstractDNode> visibleElements = new HashMap<CapellaElement, AbstractDNode>();
    // Browse the nodes content in the current context
    List<EObject> allNodes = new ArrayList<EObject>();
    if ((null != context_p) && ((context_p instanceof DDiagram) || (context_p instanceof DNodeContainer))) {
      // all Nodes contained in Diagram and DNodeContainer
      allNodes.addAll(DiagramServices.getDiagramServices().getAllNodes(context_p));
    } else if (context_p instanceof DNodeList) {
      // all DNodeListElement contained in DNodeList
      EList<DDiagramElement> elements = ((DNodeList) context_p).getElements();
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
        if ((nodeTarget != null) && (nodeTarget instanceof DataValue)) {
          if ((aNode instanceof AbstractDNode)) {
            // Add DataValue to the visible element list
            visibleElements.put((CapellaElement) nodeTarget, (AbstractDNode) aNode);
          }
        }

      }
    }
    // Browse visible element list
    for (Entry<CapellaElement, AbstractDNode> me : visibleElements.entrySet()) {
      // Check if the selected value contain the current visible element
      if (!selectedDataValues_p.contains(me.getKey())) {
        // Get the container of the element
        EObject container = me.getValue().eContainer();

        // Check if the container is a NodeList
        if (container instanceof DNodeList) {
          // Remove the element of the view
          DiagramServices.getDiagramServices().removeNodeListElementView(me.getValue());
        }
        // Check if the container is the Diagram or a Container Node
        else if ((container instanceof DSemanticDiagramSpec) || (container instanceof DNodeContainerSpec)) {
          // Remove the element of the view
          DiagramServices.getDiagramServices().removeNodeView((DNode) me.getValue());
        }
      }
    }
    // Browse the selected values
    for (CapellaElement aDataValue : selectedDataValues_p) {
      // Check if the visible element contain the current selected value
      if (!visibleElements.containsKey(aDataValue)) {
        // Create the view
        createDataValueView(context_p, aDataValue, diagram_p);
      }
    }
    return context_p;
  }

  /**
   * Create DataValue view in respected diagram
   * @param context_p
   * @param dataValue_p
   * @param diagram_p
   * @return Created View
   */
  private AbstractDNode createDataValueView(EObject context_p, CapellaElement dataValue_p, DDiagram diagram_p) {

    String mappingName = null;
    String dataTypeContentMappingName = IMappingNameConstants.CDB_DATA_TYPE_CONTENTS_MAPPING_NAME;
    String dataTypeUnitMappingName = IMappingNameConstants.CDB_DATA_TYPE_UNITS_MAPPING_NAME;
    String booleanTypeContentsMappingName = IMappingNameConstants.CDB_BOOLEAN_TYPE_BOOLEAN_CONTENTS_MAPPING_NAME;
    String booleanTypeLiteralsMappingName = IMappingNameConstants.CDB_BOOLEAN_TYPE_BOOLEAN_LITERAL_MAPPING_NAME;
    String enumerationContentsMappingName = IMappingNameConstants.CDB_ENUMERATION_ENUMERATION_CONTENTS_MAPPING_NAME;
    String enumerationLiteralsMappingName = IMappingNameConstants.CDB_ENUMERATION_ENUMERATION_LITERAL_MAPPING_NAME;
    String dataDataValueMappingName = IMappingNameConstants.CDB_DATAVALUE_MAPPING_NAME;
    String dataSubDataValueMappingName = IMappingNameConstants.CDB_SUBDATAVALUE_MAPPING_NAME;

    if (DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      if ((((DSemanticDecorator) context_p).getTarget() instanceof NumericType) || (((DSemanticDecorator) context_p).getTarget() instanceof StringType)
          || (((DSemanticDecorator) context_p).getTarget() instanceof PhysicalQuantity)) {
        if (dataValue_p instanceof DataValue) {
          mappingName = dataTypeContentMappingName;
        } else if (dataValue_p instanceof Unit) {
          mappingName = dataTypeUnitMappingName;
        }
      } else if (((DSemanticDecorator) context_p).getTarget() instanceof BooleanType) {
        BooleanType type = (BooleanType) ((DSemanticDecorator) context_p).getTarget();
        EList<LiteralBooleanValue> ownedLiterals = type.getOwnedLiterals();
        if (!ownedLiterals.isEmpty() && ownedLiterals.contains(dataValue_p)) {
          mappingName = booleanTypeLiteralsMappingName;
        } else {
          mappingName = booleanTypeContentsMappingName;
        }
      } else if (((DSemanticDecorator) context_p).getTarget() instanceof Enumeration) {
        Enumeration type = (Enumeration) ((DSemanticDecorator) context_p).getTarget();
        EList<EnumerationLiteral> ownedLiterals = type.getOwnedLiterals();
        if (!ownedLiterals.isEmpty() && ownedLiterals.contains(dataValue_p)) {
          mappingName = enumerationLiteralsMappingName;
        } else {
          mappingName = enumerationContentsMappingName;
        }
      } else if (((DSemanticDecorator) context_p).getTarget() instanceof Class) {
        mappingName = dataSubDataValueMappingName;
      } else if (((DSemanticDecorator) context_p).getTarget() instanceof DataPkgImpl) {
        mappingName = dataDataValueMappingName;
      } else if (((DSemanticDecorator) context_p).getTarget() instanceof org.polarsys.capella.core.data.information.Collection) {
        mappingName = dataSubDataValueMappingName;
      }
    }
    NodeMapping mapping = DiagramServices.getDiagramServices().getNodeMapping(diagram_p, mappingName);
    if (context_p instanceof DNodeList) {
      return DiagramServices.getDiagramServices().createDNodeListElement(mapping, dataValue_p, (DragAndDropTarget) context_p, diagram_p);
    }

    return DiagramServices.getDiagramServices().createNode(mapping, dataValue_p, (DragAndDropTarget) context_p, diagram_p);
  }

  AbstractDNode createOperationView(EObject context_p, CapellaElement Operation_p, DDiagram diagram_p) {
    String mappingName = IMappingNameConstants.CDB_OPERATION_MAPPING_NAME;

    if (DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      if (((DSemanticDecorator) context_p).getTarget() instanceof Interface) {
        mappingName = IMappingNameConstants.CCDI_OPERATION_MAPPING_NAME;
      }
    } else if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      if (((DSemanticDecorator) context_p).getTarget() instanceof Interface) {
        mappingName = IMappingNameConstants.IDB_OPERATION_MAPPING_NAME;
      } else if (((DSemanticDecorator) context_p).getTarget() instanceof ExchangeItem) {
        mappingName = IMappingNameConstants.IDB_EXCHANGE_ITEM_ELEMENT_MAPPING_NAME;
      }
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      if (((DSemanticDecorator) context_p).getTarget() instanceof Interface) {
        mappingName = IMappingNameConstants.CCDI_OPERATION_MAPPING_NAME;
      } else if (((DSemanticDecorator) context_p).getTarget() instanceof ExchangeItem) {
        mappingName = IMappingNameConstants.CCDI_EXCHANGE_ITEM_ELEMENT_MAPPING_NAME;
      }
    } else if (IDiagramNameConstants.INTERFACE_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      if (((DSemanticDecorator) context_p).getTarget() instanceof Interface) {
        mappingName = IMappingNameConstants.ID_EXCHANGE_ITEM_MAPPING_NAME;
      }
    }

    NodeMapping mapping = DiagramServices.getDiagramServices().getNodeMapping(diagram_p, mappingName);
    if (mapping != null) {
      return DiagramServices.getDiagramServices().createDNodeListElement(mapping, Operation_p, (DragAndDropTarget) context_p, diagram_p);
    }
    return null;
  }

  public void showCDBRelationship(EObject semantic_p, DDiagramContents content_p) {

    // Initialization
    AbstractType source = null;
    AbstractType target = null;
    EObject sourceView = null;
    EObject targetView = null;
    Property associationSource = null;
    Property associationTarget = null;
    EdgeMapping mapping = null;

    // assign source and target related to [Association]
    if (semantic_p instanceof Association) {
      mapping = getMappingCDBAssociation((CapellaElement) semantic_p, content_p.getDDiagram());
      associationSource = getAssociationSource((Association) semantic_p);
      if (associationSource != null) {
        source = associationSource.getAbstractType();
      }
      associationTarget = getAssociationTarget((Association) semantic_p);
      if (associationTarget != null) {
        target = associationTarget.getAbstractType();
      }
    }

    // assign source and target related to [Generalization]
    else if (semantic_p instanceof Generalization) {
      mapping = getMappingCDBGeneralization((CapellaElement) semantic_p, content_p.getDDiagram());
      source = ((Generalization) semantic_p).getSub();
      target = ((Generalization) semantic_p).getSuper();
    } // assign source and target related to [ExchangeItemElement]
    else if (semantic_p instanceof ExchangeItemElement) {
      mapping = getMappingCDBExchangeItemElement((CapellaElement) semantic_p, content_p.getDDiagram());
      source = (AbstractType) ((ExchangeItemElement) semantic_p).eContainer();
      target = ((ExchangeItemElement) semantic_p).getType();
    }

    // If invalid mapping or existing edge
    if ((mapping == null) || (content_p.getDiagramElements(semantic_p, mapping).size() > 0)) {
      return;
    }

    // Create or retrieve the sourceView
    if (source != null) {
      AbstractNodeMapping sourceMapping = getMappingCDBCapellaElement((CapellaElement) source, content_p.getDDiagram());
      Collection<DDiagramElement> views = content_p.getDiagramElements(source, sourceMapping);
      if (views.size() > 0) {
        sourceView = views.iterator().next();
      } else {
        sourceView =
            DiagramServices.getDiagramServices().createAbstractDNodeContainer(sourceMapping, source, content_p.getBestContainer(source),
                content_p.getDDiagram());
        content_p.addView((AbstractDNode) sourceView);
      }
    }

    // Create or retrieve the targetView
    if (target != null) {
      AbstractNodeMapping targetMapping = getMappingCDBCapellaElement((CapellaElement) target, content_p.getDDiagram());
      Collection<DDiagramElement> views = content_p.getDiagramElements(target, targetMapping);
      if (views.size() > 0) {
        targetView = views.iterator().next();
      } else {
        targetView =
            DiagramServices.getDiagramServices().createAbstractDNodeContainer(targetMapping, target, content_p.getBestContainer(target),
                content_p.getDDiagram());
        content_p.addView((AbstractDNode) targetView);
      }
    }

    // Create the view between both bounds
    if ((sourceView != null) && (targetView != null)) {
      content_p.addView(DiagramServices.getDiagramServices().createEdge(mapping, (EdgeTarget) sourceView, (EdgeTarget) targetView, semantic_p));
    }
  }

  @Deprecated
  public EObject showHideRelationshipInClassDiagramBlank(CapellaElement context_p, EObject context_view, List<CapellaElement> allSelectedLinksFromWizard) {
    return showHideCDBRelationships(context_p, context_view, allSelectedLinksFromWizard);
  }

  /**
   * Show/Hide Relationship in Class Diagram Blank used in common.odesign
   * @param context_p
   * @param context_view
   * @param allSelectedRelationshipFromWizard
   * @return current view
   */
  public EObject showHideCDBRelationships(CapellaElement context_p, EObject context_view, List<CapellaElement> allSelectedLinksFromWizard) {

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(context_view);
    DDiagramContents content = new DDiagramContents(diagram);

    // remove association from diagram [if unSynchronized]
    for (DEdge existingRelationship : diagram.getEdges()) {
      EObject target = existingRelationship.getTarget();
      if ((target != null) && (context_view.equals(existingRelationship.getSourceNode()) || context_view.equals(existingRelationship.getTargetNode()))) {
        if (!diagram.isSynchronized() || (target instanceof Generalization)) {
          // filter selected relationship in wizard
          if (!allSelectedLinksFromWizard.contains(target) && isValideRelationShip(target, context_p, content)) {
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

    return context_view;
  }

  /**
   * This method is used to exclude a non valid relation ship (like : ExchangeItem,Collection,..)
   * @param context_p
   * @param content_p
   * @param target_p
   * @return
   */
  private boolean isValideRelationShip(EObject target, EObject context, DDiagramContents content_p) {
    boolean isValide = true;
    Collection<DDiagramElement> diagramElements = content_p.getDiagramElements(target);

    // for remove exchange item link
    if ((diagramElements != null) && !diagramElements.isEmpty() && (context instanceof ExchangeItem) && !(target instanceof Constraint)) {
      return true;
    }

    if (target instanceof org.polarsys.capella.core.data.information.Collection) {
      isValide = false;
    } else if (target instanceof ExchangeItemElement) {
      isValide = false;
    } else if (target instanceof Constraint) {
      isValide = false;
    }
    return isValide;
  }

  /**
   * @param source_p
   * @param diagram_p
   * @return
   */
  private EdgeMapping getMappingCDBExchangeItemElement(CapellaElement source_p, DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CDB_EXCHANGE_ITEM_ELEMENT_MAPPING_NAME;
    }

    return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, mappingName);
  }

  /**
   * get the meldoyElement container mapping from class blank diagram
   */
  private AbstractNodeMapping getMappingCDBCapellaElement(CapellaElement element_p, DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME)) {
      if (element_p instanceof Class) {
        mappingName = IMappingNameConstants.CDB_CLASS_MAPPING_NAME;
        return DiagramServices.getDiagramServices().getContainerMapping(diagram_p, mappingName);
      } else if (element_p instanceof org.polarsys.capella.core.data.information.Collection) {
        mappingName = IMappingNameConstants.CDB_COLLECTION_MAPPING_NAME;
        return DiagramServices.getDiagramServices().getContainerMapping(diagram_p, mappingName);
      } else if (element_p instanceof Interface) {
        mappingName = IMappingNameConstants.CDB_INTERFACE_MAPPING_NAME;
        return DiagramServices.getDiagramServices().getContainerMapping(diagram_p, mappingName);
      } else if ((element_p instanceof DataType) && !(element_p instanceof Enumeration) && !(element_p instanceof BooleanType)) {
        mappingName = IMappingNameConstants.CDB_DATA_TYPE_MAPPING_NAME;
        return DiagramServices.getDiagramServices().getContainerMapping(diagram_p, mappingName);
      } else if (element_p instanceof Enumeration) {
        mappingName = IMappingNameConstants.CDB_ENUMERATION_MAPPING_NAME;
        return DiagramServices.getDiagramServices().getContainerMapping(diagram_p, mappingName);
      } else if (element_p instanceof BooleanType) {
        mappingName = IMappingNameConstants.CDB_BOOLEAN_TYPE_MAPPING_NAME;
        return DiagramServices.getDiagramServices().getContainerMapping(diagram_p, mappingName);
      } else if (element_p instanceof ExchangeItem) {
        mappingName = IMappingNameConstants.CDB_EXCHANGE_ITEM_MAPPING_NAME;
        return DiagramServices.getDiagramServices().getAbstractNodeMapping(diagram_p, mappingName);
      }
    }

    return null;
  }

  /**
   * get the Association edge mapping from class blank diagram
   */
  private ContainerMapping getMappingCDBType(EObject element_p, DDiagram diagram_p) {
    ContainerMapping mapping = null;
    if (element_p instanceof Class) {
      mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram_p, IMappingNameConstants.CDB_CLASS_MAPPING_NAME);
    } else if (element_p instanceof org.polarsys.capella.core.data.information.Collection) {
      mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram_p, IMappingNameConstants.CDB_COLLECTION_MAPPING_NAME);
    } else if (element_p instanceof Enumeration) {
      mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram_p, IMappingNameConstants.CDB_ENUMERATION_MAPPING_NAME);
    } else if (element_p instanceof BooleanType) {
      mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram_p, IMappingNameConstants.CDB_BOOLEAN_TYPE_MAPPING_NAME);
    } else {
      mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram_p, IMappingNameConstants.CDB_DATA_TYPE_MAPPING_NAME);
    }
    return mapping;
  }

  /**
   * get the Association edge mapping from class blank diagram
   */
  private EdgeMapping getMappingCDBAssociation(CapellaElement element_p, DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CDB_ASSOCIATION_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, mappingName);
  }

  /**
   * get the Generalization edge mapping from class blank diagram
   */
  private EdgeMapping getMappingCDBGeneralization(CapellaElement element_p, DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CDB_GENERALIZATION_MAPPING_NAME;
    }

    return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, mappingName);
  }

  /**
   * used in common.odesign
   * @param context
   * @param containerView
   * @return
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public List<DataPkg> getCDBShowHideDataPkgsScope(final EObject elementView_p) {
    List<DataPkg> returnedPackages = new ArrayList<DataPkg>();
    DSemanticDiagram diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(elementView_p);
    if (elementView_p.equals(diagram)) {
      // OLD CODE
      returnedPackages = DataPkgExt.getAllDataPkgs(diagram.getTarget());
      // NEW CODE
      returnedPackages =
          (List) QueryDebugger.executeQueryWithInclusionDebug(QueryIdentifierConstants.GET_ALL_DATA_PCK_FOR_LIB, diagram.getTarget(), returnedPackages);
      // END CODE REFACTOR
    } else if (elementView_p instanceof DNodeContainer) {
      DataPkg currentPkg = (DataPkg) ((DNodeContainer) elementView_p).getTarget();
      returnedPackages = DataPkgExt.getRecursiveSubDataPkgs(currentPkg);
    }

    for (AbstractDNode aContainer : diagram.getContainers()) {
      if (aContainer.getTarget() instanceof DataPkg) {
        returnedPackages.remove(aContainer.getTarget());
      }
    }
    return returnedPackages;
  }

  @Deprecated
  public List<DataPkg> getAvailableDataPkgsToInsert(final EObject elementView_p) {
    return getCDBShowHideDataPkgsScope(elementView_p);
  }

  /**
   * used in common.odesign
   * @param context
   * @param containerView
   * @return
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public List<InterfacePkg> getAvailableInterfacePkgsToInsert(final EObject elementView_p) {
    List<InterfacePkg> returnedPackages = new ArrayList<InterfacePkg>();
    DSemanticDiagram diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(elementView_p);
    if (elementView_p.equals(diagram)) {
      // OLD CODE
      returnedPackages = InterfacePkgExt.getAllInterfacePkgs(diagram.getTarget());
      // NEW CODE
      returnedPackages =
          (List) QueryDebugger.executeQueryWithInclusionDebug(QueryIdentifierConstants.GET_ALL_INTERFACE_PCKS_FOR_LIB, diagram.getTarget(), returnedPackages);
      // END CODE REFACTOR
    } else if (elementView_p instanceof DNodeContainer) {
      InterfacePkg currentPkg = (InterfacePkg) ((DNodeContainer) elementView_p).getTarget();
      returnedPackages = InterfacePkgExt.getRecursiveSubInterfacePkgs(currentPkg);
    }
    for (AbstractDNode aContainer : diagram.getContainers()) {
      if (aContainer.getTarget() instanceof InterfacePkg) {
        returnedPackages.remove(aContainer.getTarget());
      }
    }
    return returnedPackages;
  }

  /**
   * show the given dataPkg_p
   * @param datapkg_p
   * @param context_p
   */
  public AbstractDNode showCDBDataPkg(EObject semantic_p, DDiagramContents context_p) {
    ContainerMapping mapping =
        DiagramServices.getDiagramServices().getContainerMapping(context_p.getDDiagram(), IMappingNameConstants.CDB_DATA_PKG_MAPPING_NAME);
    DragAndDropTarget container = context_p.getBestContainer(semantic_p);
    Collection<DDiagramElement> views = context_p.getDiagramElements(semantic_p, mapping, (DSemanticDecorator) container);
    if (views.size() > 0) {
      return (AbstractDNode) views.iterator().next();
    }
    AbstractDNode result = DiagramServices.getDiagramServices().createContainer(mapping, semantic_p, container, context_p.getDDiagram());
    context_p.addView(result);
    return result;
  }

  /**
   * @param elementView_p
   * @param selectedPkgs
   * @return
   */
  public EObject showHideCDBDataPkgs(final EObject elementView_p, List<DataPkg> selectedPkgs) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(elementView_p);
    DDiagramContents content = new DDiagramContents(diagram);
    ContainerMapping mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.CDB_DATA_PKG_MAPPING_NAME);

    Map<DataPkg, DNodeContainer> existingPkgs = new HashMap<DataPkg, DNodeContainer>();
    for (DDiagramElement aContainer : content.getDiagramElements((DSemanticDecorator) elementView_p, mapping)) {
      if ((aContainer instanceof DNodeContainer) && (aContainer.getTarget() instanceof DataPkg)) {
        existingPkgs.put((DataPkg) aContainer.getTarget(), (DNodeContainer) aContainer);
      }
    }

    Set<DNodeContainer> toBeRemoved = new HashSet<DNodeContainer>();
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
    return elementView_p;
  }

  /**
   * used in common (Package dependencies & CDB)
   * @param diagram_p
   * @param selectedPkgs
   * @return
   */
  @Deprecated
  public EObject showHideDataPkgs(final EObject elementView_p, List<DataPkg> selectedPkgs) {
    return showHideCDBDataPkgs(elementView_p, selectedPkgs);
  }

  /**
   * show the given dataPkg_p
   * @param datapkg_p
   * @param context_p
   */
  public AbstractDNode showCDBInterfacePkg(EObject semantic_p, DDiagramContents context_p) {
    ContainerMapping mapping =
        DiagramServices.getDiagramServices().getContainerMapping(context_p.getDDiagram(), IMappingNameConstants.CDB_INTERFACE_PKG_MAPPING_NAME);
    DragAndDropTarget container = context_p.getBestContainer(semantic_p);
    Collection<DDiagramElement> views = context_p.getDiagramElements(semantic_p, mapping, (DSemanticDecorator) container);
    if (views.size() > 0) {
      return (AbstractDNode) views.iterator().next();
    }
    AbstractDNode result = DiagramServices.getDiagramServices().createContainer(mapping, semantic_p, container, context_p.getDDiagram());
    context_p.addView(result);
    return result;
  }

  public EObject showHideCDBInterfacePkgs(final EObject elementView_p, List<InterfacePkg> selectedPkgs_p) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(elementView_p);
    DDiagramContents content = new DDiagramContents(diagram);
    ContainerMapping mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.CDB_INTERFACE_PKG_MAPPING_NAME);

    Map<InterfacePkg, DNodeContainer> existingPkgs = new HashMap<InterfacePkg, DNodeContainer>();
    for (DDiagramElement aContainer : content.getDiagramElements((DSemanticDecorator) elementView_p, mapping)) {
      if ((aContainer instanceof DNodeContainer) && (aContainer.getTarget() instanceof InterfacePkg)) {
        existingPkgs.put((InterfacePkg) aContainer.getTarget(), (DNodeContainer) aContainer);
      }
    }

    Set<DNodeContainer> toBeRemoved = new HashSet<DNodeContainer>();
    for (Entry<InterfacePkg, DNodeContainer> me : existingPkgs.entrySet()) {
      if (!selectedPkgs_p.contains(me.getKey())) {
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
    for (InterfacePkg aPkg : selectedPkgs_p) {
      if (!existingPkgs.containsKey(aPkg)) {
        showCDBInterfacePkg(aPkg, content);
      }
    }
    return elementView_p;
  }

  /**
   * used in common (Package dependencies & CDB)
   * @param diagram_p
   * @param selectedPkgs
   * @return
   */
  @Deprecated
  public EObject showHideInterfacePkgs(final EObject elementView_p, List<InterfacePkg> selectedPkgs_p) {
    Map<InterfacePkg, DNodeContainer> existingPkgs = new HashMap<InterfacePkg, DNodeContainer>();
    for (DNodeContainer aContainer : DiagramServices.getDiagramServices().getAllContainers(elementView_p)) {
      if (aContainer.getTarget() instanceof InterfacePkg) {
        existingPkgs.put((InterfacePkg) aContainer.getTarget(), aContainer);
      }
    }
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(elementView_p);
    Set<DNodeContainer> toBeRemoved = new HashSet<DNodeContainer>();

    for (Entry<InterfacePkg, DNodeContainer> me : existingPkgs.entrySet()) {
      if (!selectedPkgs_p.contains(me.getKey())) {
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
    for (InterfacePkg aPkg : selectedPkgs_p) {
      if (!existingPkgs.containsKey(aPkg)) {
        ContainerMapping mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.CDB_INTERFACE_PKG_MAPPING_NAME);
        DiagramServices.getDiagramServices().createContainer(mapping, aPkg, diagram, diagram);
      }
    }
    return elementView_p;
  }

  @Deprecated
  public EObject showHideDependentPkgs(final EObject elementView_p, List<AbstractDependenciesPkg> selectedPkgs_p,
      List<AbstractDependenciesPkg> existingPackages_p) {
    return showHidePDDependentPkgs(elementView_p, selectedPkgs_p, existingPackages_p);
  }

  /**
   * used in common (Package dependencies)
   * @param diagram_p
   * @param selectedPkgs
   * @param existingPackages_p
   * @return
   */
  public EObject showHidePDDependentPkgs(final EObject elementView_p, List<AbstractDependenciesPkg> selectedPkgs_p,
      List<AbstractDependenciesPkg> existingPackages_p) {
    Map<AbstractDependenciesPkg, DNodeContainer> existingPkgs = new HashMap<AbstractDependenciesPkg, DNodeContainer>();
    for (DNodeContainer aContainer : DiagramServices.getDiagramServices().getAllContainers(elementView_p)) {
      if (existingPackages_p.contains(aContainer.getTarget())) {
        existingPkgs.put((AbstractDependenciesPkg) aContainer.getTarget(), aContainer);
      }
    }
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(elementView_p);
    Set<DNodeContainer> toBeRemoved = new HashSet<DNodeContainer>();

    for (Entry<AbstractDependenciesPkg, DNodeContainer> me : existingPkgs.entrySet()) {
      if (!selectedPkgs_p.contains(me.getKey())) {
        toBeRemoved.add(me.getValue());
      }
    }

    // move packages whose parent must be deleted
    for (DNodeContainer aContainer : DiagramServices.getDiagramServices().getAllContainers(diagram)) {
      if (!toBeRemoved.contains(aContainer) && toBeRemoved.contains(aContainer.eContainer())) {
        diagram.getOwnedDiagramElements().add(aContainer);
      }
    }
    for (DNodeContainer aContainer : toBeRemoved) {
      DiagramServices.getDiagramServices().removeContainerView(aContainer);
    }
    for (AbstractDependenciesPkg aPkg : selectedPkgs_p) {
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
    return elementView_p;
  }

  @Deprecated
  public EObject showHidePackageDependencies(final AbstractDependenciesPkg subject_p, DDiagram diagram_p) {
    return showHidePDPackageDependencies(subject_p, diagram_p);
  }

  public EObject showHidePDPackageDependencies(final AbstractDependenciesPkg subject_p, DDiagram diagram_p) {

    Collection<AbstractDependenciesPkg> deps = AbstractDependenciesPkgExt.getDependencies(subject_p);
    Collection<AbstractDependenciesPkg> inverseDeps = AbstractDependenciesPkgExt.getInverseDependencies(subject_p);

    List<AbstractDependenciesPkg> all = new ArrayList<AbstractDependenciesPkg>(deps);
    all.addAll(inverseDeps);

    TransferTreeListDialog dialog =
        new TransferTreeListDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.InformationServices_PackageDependency_Title,
            NLS.bind(Messages.InformationServices_PackageDependency_Message, subject_p.getName()), new DependencyLabelProvider(deps, inverseDeps, subject_p), // two
                                                                                                                                                              // instances
                                                                                                                                                              // required
            new DependencyLabelProvider(deps, inverseDeps, subject_p)); // or enabled/disabled fonts will mess up

    List<AbstractDependenciesPkg> left = new ArrayList<AbstractDependenciesPkg>(); // unselected
    List<AbstractDependenciesPkg> right = new ArrayList<AbstractDependenciesPkg>(); // selected

    for (AbstractDependenciesPkg pkg : all) {
      if (DiagramServices.getDiagramServices().getDiagramElement(diagram_p, pkg) != null) {
        right.add(pkg);
      } else {
        left.add(pkg);
      }
    }

    dialog.setLeftInput(left, subject_p);
    dialog.setRightInput(right, subject_p);

    if (dialog.open() == Window.OK) {
      Object[] result = dialog.getResult();
      right.clear();
      for (Object o : result) {
        if (o instanceof AbstractDependenciesPkg) {
          right.add((AbstractDependenciesPkg) o);
        }
      }
      showHidePDDependentPkgs(diagram_p, right, all);
    }
    return subject_p;
  }

  public Collection<AbstractDependenciesPkg> getDependentPackages(final AbstractDependenciesPkg pkg_p) {
    return AbstractDependenciesPkgExt.getDependencies(pkg_p);
  }

  /**
   * Searches the direct dependencies and inverse dependencies of an AbstractDependenciesPkg.
   * @param pkg_p
   * @return The direct dependencies and inverse dependencies of the argument
   */
  public Collection<AbstractDependenciesPkg> getDependenciesAndInverseDependencies(final AbstractDependenciesPkg pkg_p) {
    Set<AbstractDependenciesPkg> result = new HashSet<AbstractDependenciesPkg>();
    result.addAll(AbstractDependenciesPkgExt.getDependencies(pkg_p));
    result.addAll(AbstractDependenciesPkgExt.getInverseDependencies(pkg_p));
    return result;
  }

  /**
   * used in common.odesign
   * @param sourcePackage
   * @param targetPackage
   * @return test if we add a dependency between sourcePackage and targetPackage it does not create any cycle
   */
  public boolean isADependencyAvailable(final EObject context, AbstractDependenciesPkg sourcePackage, AbstractDependenciesPkg targetPackage) {
    return AbstractDependenciesPkgExt.isADependencyAvailable(sourcePackage, targetPackage);
  }

  /**
   * used in common.odesign
   * @param sourcePackage
   * @param targetPackage
   * @return test if we add a dependency between sourcePackage and targetPackage it does not create any cycle
   */
  public boolean isADependencyAvailable2(final EObject context, EObject sourceObj, EObject targetObj) {
    EObject ancestor1 = CapellaServices.getService().getAncestor(sourceObj, CapellacorePackage.Literals.ABSTRACT_DEPENDENCIES_PKG);
    EObject ancestor2 = CapellaServices.getService().getAncestor(targetObj, CapellacorePackage.Literals.ABSTRACT_DEPENDENCIES_PKG);
    return AbstractDependenciesPkgExt.isADependencyAvailable((AbstractDependenciesPkg) ancestor1, (AbstractDependenciesPkg) ancestor2);
  }

  /**
   * used in common.odesign
   * @param elementView
   * @return all the available dataTypes we can insert in the elementView
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Collection<DataType> getAvailableDataTypesToInsert(final EObject elementView) {
    if (elementView instanceof DDiagram) {
      // OLD CODE
      Collection<DataType> result = DataTypeExt.getAllDataTypes(((DSemanticDiagram) elementView).getTarget());
      // NEW CODE
      result =
          (List) QueryDebugger.executeQueryWithInclusionDebug(QueryIdentifierConstants.GET_ALL_DATA_TYPES_FOR_LIB,
              ((DSemanticDiagram) elementView).getTarget(), result);
      // END CODE REFACTOR
      return result;
    }
    if (elementView instanceof DNodeContainer) {
      DNodeContainer currentContainer = (DNodeContainer) elementView;
      if (currentContainer.getTarget() instanceof DataPkg) {
        return DataPkgExt.getAllDataTypes((DataPkg) currentContainer.getTarget());
      }
    }
    return new ArrayList<DataType>();
  }

  /**
   * used in common.odesign (CDB)
   * @param elementView
   * @return all the available classes we can insert in the elementView
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Collection<Class> getAvailableClassesToInsert(final EObject elementView) {
    if (elementView instanceof DDiagram) {
      // OLD CODE
      Collection<Class> result = ClassExt.getAllClasses(((DSemanticDiagram) elementView).getTarget());
      // NEW CODE
      result =
          (List) QueryDebugger.executeQueryWithInclusionDebug(QueryIdentifierConstants.GET_ALL_CLASSES_FOR_LIB, ((DSemanticDiagram) elementView).getTarget(),
              result);
      // END CODE REFACTOR
      return result;
    }
    if (elementView instanceof DNodeContainer) {
      DNodeContainer currentContainer = (DNodeContainer) elementView;
      if (currentContainer.getTarget() instanceof DataPkg) {
        return DataPkgExt.getAllClasses((DataPkg) currentContainer.getTarget());
      }
    }
    return new ArrayList<Class>();
  }

  /**
   * used in common.odesign (CDB)
   * @param elementView
   * @return all the available interfaces we can insert in the containerView
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Collection<Interface> getAvailableInterfacesToInsert(final EObject elementView) {
    if (elementView instanceof DDiagram) {
      // OLD CODE
      Collection<Interface> result = InterfaceExt.getAllInterfaces(((DSemanticDiagram) elementView).getTarget());
      // NEW CODE
      result =
          (List) QueryDebugger.executeQueryWithInclusionDebug(QueryIdentifierConstants.GET_ALL_INTERFACES_FOR_LIB,
              ((DSemanticDiagram) elementView).getTarget(), result);
      // END CODE REFACTOR
      return result;
    }
    if (elementView instanceof DNodeContainer) {
      DNodeContainer currentContainer = (DNodeContainer) elementView;
      if (currentContainer.getTarget() instanceof InterfacePkg) {
        return InterfacePkgExt.getAllInterfaces((InterfacePkg) currentContainer.getTarget());
      }
    }
    return new ArrayList<Interface>();
  }

  /**
   * used in common.odesign (CDB)
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
    return new ArrayList<Interface>();
  }

  /**
   * used in common (CDB)
   * @param elementView
   * @return all the available exchange items we can insert in the elementView
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Collection<AbstractExchangeItem> getAvailableExchangeItemsToInsert(final EObject elementView) {
    if (elementView instanceof DDiagram) {
      // OLD CODE
      Collection<AbstractExchangeItem> result = ExchangeItemExt.getAllExchangeItems(((DSemanticDiagram) elementView).getTarget());
      // NEW CODE
      result =
          (List) QueryDebugger.executeQueryWithInclusionDebug(QueryIdentifierConstants.GET_ALL_EXCHANGE_ITEMS_FOR_LIB,
              ((DSemanticDiagram) elementView).getTarget(), result);
      // END CODE REFACTOR
      return result;
    }
    if (elementView instanceof DNodeContainer) {
      DNodeContainer currentContainer = (DNodeContainer) elementView;
      if (currentContainer.getTarget() instanceof AbstractExchangeItemPkg) {
        return AbstractExchangeItemPkgExt.getAllAbstractExchangeItems((DataPkg) currentContainer.getTarget());
      }
    }
    return new ArrayList<AbstractExchangeItem>();
  }

  /**
   * used in common (CDB)
   * @param elementView
   * @return all the available collections we can insert in the elementView
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Collection<org.polarsys.capella.core.data.information.Collection> getAvailableCollectionsToInsert(final EObject elementView) {
    if (elementView instanceof DDiagram) {
      // OLD CODE
      Collection<org.polarsys.capella.core.data.information.Collection> result = CollectionExt.getAllCollections(((DSemanticDiagram) elementView).getTarget());
      // NEW CODE
      result =
          (List) QueryDebugger.executeQueryWithInclusionDebug(QueryIdentifierConstants.GET_ALL_COLLECTIONS_FOR_LIB,
              ((DSemanticDiagram) elementView).getTarget(), result);
      // END CODE REFACTOR
      return result;
    }
    if (elementView instanceof DNodeContainer) {
      DNodeContainer currentContainer = (DNodeContainer) elementView;
      if (currentContainer.getTarget() instanceof DataPkg) {
        return DataPkgExt.getAllCollections((DataPkg) currentContainer.getTarget());
      }
    }
    return new ArrayList<org.polarsys.capella.core.data.information.Collection>();
  }

  @Deprecated
  public EObject showHideTypes(final EObject elementView_p, List<EObject> selectedTypes_p) {
    return showHideCDBTypes(elementView_p, selectedTypes_p);
  }

  /**
   * used in common (CDB)
   * @param diagram_p
   * @param selectedTypes_p
   * @return
   */
  public EObject showHideCDBTypes(final EObject elementView_p, List<EObject> selectedTypes_p) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(elementView_p);
    DDiagramContents content = new DDiagramContents(diagram);

    Map<EObject, AbstractDNode> existingTypes = new HashMap<EObject, AbstractDNode>();
    for (DContainer currentContainer : DiagramServices.getDiagramServices().getAllContainersAndNodeLists(elementView_p)) {
      AbstractDNode aContainer = (AbstractDNode) currentContainer;
      if ((aContainer.getTarget() instanceof Class) || (aContainer.getTarget() instanceof org.polarsys.capella.core.data.information.Collection)
          || (aContainer.getTarget() instanceof DataType)) {
        existingTypes.put(aContainer.getTarget(), aContainer);
      }
    }

    for (Entry<EObject, AbstractDNode> me : existingTypes.entrySet()) {
      if (!selectedTypes_p.contains(me.getKey())) {
        DiagramServices.getDiagramServices().removeContainerView((DContainer) me.getValue());
      }
    }
    for (EObject aType : selectedTypes_p) {
      if (!existingTypes.containsKey(aType)) {
        showCDBType(aType, content);
      }
    }
    return elementView_p;
  }

  /**
   * show the given dataPkg_p
   * @param datapkg_p
   * @param context_p
   */
  public AbstractDNode showCDBType(EObject semantic_p, DDiagramContents context_p) {
    ContainerMapping mapping = getMappingCDBType(semantic_p, context_p.getDDiagram());
    DragAndDropTarget container = context_p.getBestContainer(semantic_p);
    Collection<DDiagramElement> views = context_p.getDiagramElements(semantic_p, mapping, (DSemanticDecorator) container);
    if (views.size() > 0) {
      return (AbstractDNode) views.iterator().next();
    }
    AbstractDNode result = DiagramServices.getDiagramServices().createAbstractDNodeContainer(mapping, semantic_p, container, context_p.getDDiagram());
    context_p.addView(result);
    return result;
  }

  /**
   * show the given dataPkg_p
   * @param datapkg_p
   * @param context_p
   */
  public AbstractDNode showCDBInterface(EObject semantic_p, DDiagramContents context_p) {
    ContainerMapping mapping = getMappingCDBInterface(semantic_p, context_p.getDDiagram());
    DragAndDropTarget container = context_p.getBestContainer(semantic_p);
    Collection<DDiagramElement> views = context_p.getDiagramElements(semantic_p, mapping, (DSemanticDecorator) container);
    if (views.size() > 0) {
      return (AbstractDNode) views.iterator().next();
    }
    AbstractDNode result = DiagramServices.getDiagramServices().createAbstractDNodeContainer(mapping, semantic_p, container, context_p.getDDiagram());
    context_p.addView(result);
    return result;
  }

  /**
   * @param semantic_p
   * @param dDiagram_p
   * @return
   */
  private ContainerMapping getMappingCDBInterface(EObject semantic_p, DDiagram dDiagram_p) {
    return DiagramServices.getDiagramServices().getContainerMapping(dDiagram_p, IMappingNameConstants.CDB_INTERFACE_MAPPING_NAME);
  }

  /**
   * show the given dataPkg_p
   * @param datapkg_p
   * @param context_p
   */
  public AbstractDNode showCDBExchangeItem(EObject semantic_p, DDiagramContents context_p) {
    NodeMapping mapping = getMappingCDBExchangeItem(semantic_p, context_p.getDDiagram());
    DragAndDropTarget container = context_p.getBestContainer(semantic_p);
    Collection<DDiagramElement> views = context_p.getDiagramElements(semantic_p, mapping, (DSemanticDecorator) container);
    if (views.size() > 0) {
      return (AbstractDNode) views.iterator().next();
    }
    AbstractDNode result = DiagramServices.getDiagramServices().createNode(mapping, semantic_p, container, context_p.getDDiagram());
    context_p.addView(result);
    return result;
  }

  /**
   * @param semantic_p
   * @param dDiagram_p
   * @return
   */
  private NodeMapping getMappingCDBExchangeItem(EObject semantic_p, DDiagram dDiagram_p) {
    return DiagramServices.getDiagramServices().getNodeMapping(dDiagram_p, IMappingNameConstants.CDB_INTERFACE_MAPPING_NAME);
  }

  /**
   * used in common (CDB)
   * @param diagram_p
   * @param selectedInterfaces_p
   * @return
   */
  public EObject showHideCDBInterfaces(final EObject elementView_p, List<EObject> selectedInterfaces_p) {
    Map<EObject, AbstractDNode> existingInterfaces = new HashMap<EObject, AbstractDNode>();
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(elementView_p);
    DDiagramContents content = new DDiagramContents(diagram);

    for (DContainer currentContainer : DiagramServices.getDiagramServices().getAllContainersAndNodeLists(elementView_p)) {
      AbstractDNode aContainer = (AbstractDNode) currentContainer;
      if (aContainer.getTarget() instanceof Interface) {
        existingInterfaces.put(aContainer.getTarget(), aContainer);
      }
    }
    for (Entry<EObject, AbstractDNode> me : existingInterfaces.entrySet()) {
      if (!selectedInterfaces_p.contains(me.getKey())) {
        DiagramServices.getDiagramServices().removeContainerView((DContainer) me.getValue());
      }
    }
    for (EObject aType : selectedInterfaces_p) {
      if (!existingInterfaces.containsKey(aType)) {
        showCDBInterface(aType, content);
      }
    }
    return elementView_p;
  }

  /**
   * used in common (CDB)
   * @param diagram_p
   * @param selectedExchangeItems_p
   * @return
   */
  public EObject showHideCDBExchangeItems(final EObject elementView_p, List<EObject> selectedExchangeItems_p) {
    Map<ExchangeItem, AbstractDNode> existingExchangeItems = new HashMap<ExchangeItem, AbstractDNode>();
    for (AbstractDNode aNode : DiagramServices.getDiagramServices().getAllNodesAndNodeListElements(elementView_p)) {
      if (aNode.getTarget() instanceof ExchangeItem) {
        existingExchangeItems.put((ExchangeItem) aNode.getTarget(), aNode);
      }
    }
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(elementView_p);

    for (Entry<ExchangeItem, AbstractDNode> me : existingExchangeItems.entrySet()) {
      if (!selectedExchangeItems_p.contains(me.getKey())) {
        // remove abstract dNode view
        DiagramServices.getDiagramServices().removeAbstractDNodeView(me.getValue());
      }
    }
    for (EObject aType : selectedExchangeItems_p) {
      if (!existingExchangeItems.containsKey(aType)) {
        NodeMapping mapping = DiagramServices.getDiagramServices().getNodeMapping(diagram, IMappingNameConstants.CDB_EXCHANGE_ITEM_MAPPING_NAME);
        DiagramServices.getDiagramServices().createNode(mapping, aType, diagram, diagram);
      }
    }
    return elementView_p;
  }

  public String getCommunicationLinkLabel(EObject context_p) {
    String result = ICommonConstants.EMPTY_STRING;
    if (null != context_p) {
      if (context_p instanceof CommunicationLink) {
        CommunicationLink cl = (CommunicationLink) context_p;
        CommunicationLinkKind kind = cl.getKind();
        CommunicationLinkProtocol protocol = cl.getProtocol();
        if (null != kind) {
          result = kind.getName();
        } else {
          result = "<undefined>"; //$NON-NLS-1$
        }
        if ((null != protocol) && (protocol != CommunicationLinkProtocol.UNSET) && (protocol != CommunicationLinkProtocol.UNICAST)
            && (protocol != CommunicationLinkProtocol.SYNCHRONOUS) && (protocol != CommunicationLinkProtocol.READ)) {
          result = result + " / " + protocol.getName(); //$NON-NLS-1$
        }
      }
    }

    return result;
  }

  /**
   * compute the Interface service label from ExchangeItemAllocation
   * @param exchangeItemAllocation_p
   * @param view_p
   * @return
   */
  public String getOperationLabel(EObject exchangeItemAllocation_p, EObject view_p) {
    String result = ICommonConstants.EMPTY_STRING;

    if ((null != exchangeItemAllocation_p) && (exchangeItemAllocation_p instanceof ExchangeItemAllocation) && (null != view_p)
        && (view_p instanceof DSemanticDecorator)) {
      // Type Exchange Item Allocation
      ExchangeItemAllocation allocation = (ExchangeItemAllocation) exchangeItemAllocation_p;
      // get Diagram
      DDiagram diagram = CapellaServices.getService().getDiagramContainer(view_p);
      if (diagram == null) {
        Object oDiagram = CsServices.getService().getInterpreterVariable(exchangeItemAllocation_p, IInterpreterSiriusVariables.DIAGRAM);
        if ((oDiagram != null) && (oDiagram instanceof DDiagram)) {
          diagram = (DDiagram) oDiagram;
        }
      }

      if (diagram != null) {
        EList<FilterDescription> activatedFilters = diagram.getActivatedFilters();
        boolean allParameterHide = false;
        boolean onlyOperationParameterHide = false;
        for (FilterDescription filterDescription : activatedFilters) {
          if ((null != filterDescription) && filterDescription.getName().equalsIgnoreCase(IMappingNameConstants.HIDE_ALL_PARAMETER)) {
            allParameterHide = true;
          }
          if ((null != filterDescription) && filterDescription.getName().equalsIgnoreCase(IMappingNameConstants.HIDE_OPERATION_PARAMETER)) {
            onlyOperationParameterHide = true;
          }
        }

        // Case 1: hide all parameter
        if (allParameterHide) {
          result = getAllocatedElementName(allocation);
        }
        // Case 2: hide only operation parameter
        else if (!allParameterHide && onlyOperationParameterHide) {
          AbstractExchangeItem allocatedItem = allocation.getAllocatedItem();
          if (allocatedItem instanceof ExchangeItem) {
            ExchangeItem item = (ExchangeItem) allocatedItem;
            ExchangeMechanism exchangeMechanism = item.getExchangeMechanism();
            if ((exchangeMechanism != null) && exchangeMechanism.equals(ExchangeMechanism.OPERATION)) {
              // customize the label
              String itemName = item.getName();
              result = itemName;
            } else {
              result = computeLabel(allocation);
            }
          } else {
            result = computeLabel(allocation);
          }

        }

        // Case 3: hide all parameter and also operation parameter
        else if (allParameterHide && onlyOperationParameterHide) {
          result = getAllocatedElementName(allocation);
        }
        // Case 4 : hide nothing
        else if (!onlyOperationParameterHide && !allParameterHide) {
          result = computeLabel(allocation);
        }

      }
    }

    return result;
  }

  /**
   * @param operation_p
   * @param result
   * @return
   */
  private String getAllocatedElementName(ExchangeItemAllocation operation_p) {
    String result = ICommonConstants.EMPTY_STRING;
    AbstractExchangeItem allocatedItem = operation_p.getAllocatedItem();
    if (null != allocatedItem) {
      result = allocatedItem.getName();
    }
    return result;
  }

  public boolean hasExchangeItemAllocationLink(DDiagramElement context_p) {
    if (null != context_p) {
      EObject eiTarget = context_p.getTarget();
      if ((eiTarget instanceof ExchangeItem) && (context_p instanceof DNode)) {
        DNode node = (DNode) context_p;
        EList<DEdge> incomingEdges = node.getIncomingEdges();
        for (DEdge edge : incomingEdges) {
          EObject target = edge.getTarget();
          if (target instanceof ExchangeItemAllocation) {
            ExchangeItemAllocation eia = (ExchangeItemAllocation) target;
            AbstractExchangeItem allocatedItem = eia.getAllocatedItem();
            if (allocatedItem != null) {
              if (allocatedItem.equals(eiTarget)) {
                return false;
              }
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
   * convert first letter of the string to Capital letter
   * @param context_p
   * @param toConvert_p
   * @return converted String
   */
  public String convertToUpperFirst(EObject context_p, String toConvert_p) {
    String result = ICommonConstants.EMPTY_STRING;
    result = StringHelper.toLowerFirst(toConvert_p);
    return result;
  }

  /**
   * [Method used in common.odesign] Returns true if any of the association end is of type primitive
   * @param context current Association
   * @return boolean value
   */
  public boolean hasNonPrimitiveEnds(EObject context) {
    if ((context != null) && (context instanceof Association)) {
      Association association = (Association) context;
      Collection<Property> associationProperties = getAssociationProperties(association);
      for (Property property : associationProperties) {
        AbstractType abstractType = property.getAbstractType();
        if (null != abstractType) {
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
    }
    return false;
  }

  /**
   * [Method used in common.odesign] return prefix of the property label
   * @param context current Property
   * @return prefix as string
   */
  public String prefixPropertyLabel(EObject context) {
    String str = ICommonConstants.EMPTY_STRING;
    if ((null != context) && (context instanceof Property)) {
      Property pro = (Property) context;
      if (pro.isIsPartOfKey()) {
        str = str + "&" + ICommonConstants.WHITE_SPACE_CHARACTER; //$NON-NLS-1$
      }
      if (pro.isIsStatic()) {
        str = str + "%" + ICommonConstants.WHITE_SPACE_CHARACTER; //$NON-NLS-1$
      }
    }
    return str;
  }

  /**
   * used in common.odesign : ClassDiagram Return customized label for UnionProperty
   * @param context_p : an EObject
   * @param property_p : an UnionProperty
   * @return : the customized label for UnionProperty
   */
  public String suffixLabelForUnionProperty(EObject context_p, EObject property_p) {
    String result = ICommonConstants.EMPTY_STRING;
    // filter UnionProperty
    if ((null != property_p) && (property_p instanceof UnionProperty)) {
      UnionProperty unionPro = (UnionProperty) property_p;
      EObject container = unionPro.eContainer();
      if ((container != null) && (container instanceof Union)) {
        Union union = (Union) container;
        if (union.getDiscriminant() != null) {
          result = computeUnionPropertyLabelWithQualifier(unionPro);
        }
      }
    }

    return result;
  }

  /**
   * @param context_p
   * @param ele_p a capella element
   * @param displayedExchangeItem_p : exchangeItem displayed in diagram
   * @return list of exchange item
   */
  public List<EObject> getDisplayedAndLinkedExchangeItems(EObject context_p, EObject ele_p, List<ExchangeItem> displayedExchangeItem_p) {
    // list of exchange Item
    List<EObject> result = new ArrayList<EObject>(1);
    // all the exchanges linked via communication link owned by given
    // component
    List<EObject> linkedExchagneItem = new ArrayList<EObject>(1);

    if ((null != ele_p) && (ele_p instanceof Component)) {
      // Component
      Component comp = (Component) ele_p;

      // collect linked Exchange Item of given component
      EList<CommunicationLink> ownedCommunicationLinks = comp.getOwnedCommunicationLinks();
      for (CommunicationLink communicationLink : ownedCommunicationLinks) {
        AbstractExchangeItem exchangeItem = communicationLink.getExchangeItem();
        if (null != exchangeItem) {
          linkedExchagneItem.add(exchangeItem);
        }
      }

      // filter the unlinked Exchange item from displayed Exchange Items
      for (ExchangeItem displayedExhagneItem : displayedExchangeItem_p) {
        if (!linkedExchagneItem.contains(displayedExhagneItem)) {
          result.add(displayedExhagneItem);
        }
      }

    }

    return result;
  }

  public String getDataTypeLabel(DataType dataType_p) {
    String result = dataType_p.getName();
    return result;
  }

  /**
   * @used in common.odesign Return true if "Hide Association Label is true"
   * @param assocation_p
   * @param view_p
   * @return
   */
  public boolean isHideAssociationLabelEnable(EObject assocation_p, EObject view_p) {
    return isDiagramFilterEnable(assocation_p, view_p, IMappingNameConstants.HIDE_ASSOCIATION_LABLE);
  }

  /**
   * @used in common.odesign Return true if "Hide Role Label is true"
   * @param assocation_p
   * @param view_p
   * @return
   */
  public boolean isHideRoleLabelEnable(EObject assocation_p, EObject view_p) {
    return isDiagramFilterEnable(assocation_p, view_p, IMappingNameConstants.HIDE_ROLE_LABLE);
  }

  /**
   * @used in common.odesign Return true if given filter is true
   * @param assocation_p
   * @param view_p
   * @return
   */
  private boolean isDiagramFilterEnable(EObject assocation_p, EObject view_p, String filterName_p) {
    if (null != view_p) {
      // get Diagram
      DDiagram diagram = CapellaServices.getService().getDiagramContainer(view_p);
      if (diagram == null) {
        Object oDiagram = CsServices.getService().getInterpreterVariable(assocation_p, IInterpreterSiriusVariables.DIAGRAM);
        if ((oDiagram != null) && (oDiagram instanceof DDiagram)) {
          diagram = (DDiagram) oDiagram;
        }
      }

      if (diagram != null) {
        EList<FilterDescription> activatedFilters = diagram.getActivatedFilters();
        for (FilterDescription filterDescription : activatedFilters) {
          // if given filter is enable return true
          if ((null != filterDescription) && filterDescription.getName().equalsIgnoreCase(filterName_p)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  /**
   * Return association begin label
   * @param context_p : context
   * @param property_p : Property
   * @param view_p : current diagram element view
   * @param showRoleName : decide weather to display the role name
   * @return
   */
  public String getAssociationBeginRoleLabel(EObject context_p, EObject property_p, EObject view_p, boolean showRoleName) {
    StringBuffer beginLabel = new StringBuffer();
    if ((null != property_p) && (property_p instanceof Property)) {
      Property pro = (Property) property_p;
      boolean hideRoleLabelEnable = isHideRoleLabelEnable(context_p, view_p);
      // prefix
      if (!hideRoleLabelEnable) {
        beginLabel.append(prefixPropertyLabel(pro));
      }
      // multiplicity
      String multiplicityToString = multiplicityToString(pro);
      beginLabel.append(multiplicityToString);
      if (!multiplicityToString.equals(ICommonConstants.EMPTY_STRING)) {
        beginLabel.append(ICommonConstants.WHITE_SPACE_CHARACTER);
      }
      // isDerived
      if (pro.isIsDerived()) {
        beginLabel.append(ICommonConstants.SLASH_CHARACTER);
      }
      // role name (consider only if filter is disable)
      if (!hideRoleLabelEnable) {
        // check if role name is to be displayed
        if (showRoleName) {
          beginLabel.append(pro.getName());
        }
      }
    }
    return beginLabel.toString();
  }

  /**
   * Return Association Center label
   * @param association_p : an Association
   * @param view_p : an Association view
   * @return : association center label
   */
  public String getAssociationCenterLabel(EObject association_p, EObject view_p) {
    // why white space char
    // The manual refresh of the diagram does not take into account the EmptySting
    String centerLabel = Character.toString(ICommonConstants.WHITE_SPACE_CHARACTER);
    if ((null != association_p) && (association_p instanceof Association)) {
      Association association = (Association) association_p;
      if (!isHideAssociationLabelEnable(association_p, view_p)) {
        return association.getName();
      }
    }
    return centerLabel;
  }

  /**
   * Return association end label
   * @param context_p : context
   * @param property_p : Property
   * @param view_p : current diagram element view
   * @param showRoleName : decide weather to display the role name
   * @return
   */
  public String getAssociationEndRoleLabel(EObject context_p, EObject property_p, EObject view_p, boolean showRoleName) {
    StringBuffer endLabel = new StringBuffer();
    if ((null != property_p) && (property_p instanceof Property)) {
      Property pro = (Property) property_p;
      boolean hideRoleLabelEnable = isHideRoleLabelEnable(context_p, view_p);
      // prefix
      if (!hideRoleLabelEnable) {
        endLabel.append(prefixPropertyLabel(pro));
      }
      // multiplicity
      String multiplicityToString = multiplicityToString(pro);
      endLabel.append(multiplicityToString);
      if (!multiplicityToString.equals(ICommonConstants.EMPTY_STRING)) {
        endLabel.append(ICommonConstants.WHITE_SPACE_CHARACTER);
      }
      // suffix for unionProperty
      if (!hideRoleLabelEnable) {
        String suffixLabel = suffixLabelForUnionProperty(property_p, property_p);
        endLabel.append(suffixLabel);
        if (!suffixLabel.equals(ICommonConstants.EMPTY_STRING)) {
          endLabel.append(ICommonConstants.WHITE_SPACE_CHARACTER);
        }
      }
      // isDerived
      if (pro.isIsDerived()) {
        endLabel.append(ICommonConstants.SLASH_CHARACTER);
      }
      // role name (consider only if filter is disable)
      if (!hideRoleLabelEnable) {
        // check if role name is to be displayed
        if (showRoleName) {
          endLabel.append(pro.getName());
        }
      }
    }
    return endLabel.toString();
  }

  /**
   * Return project explorer label of given element (if element is type NumericType, take into consideration the unit name)
   */
  public String getDatavalueLabel(EObject context_p) {
    String result = ICommonConstants.EMPTY_STRING;
    result = CapellaServices.getService().getEObjectLabelProviderHelper(context_p);
    return result;
  }

  public List<Interface> getAvailableInterfacesFromComponentToInsert(EObject context_p) {
    List<Interface> result = new ArrayList<Interface>();
    Component component = (Component) ((DSemanticDecorator) context_p).getTarget();
    result.addAll(ComponentExt.getAllImplementedAndProvidedInterfaces(component));
    result.addAll(ComponentExt.getAllUsedAndRequiredInterfaces(component));
    return result;
  }

  public List<CapellaElement> getAvailableInterfacesAndLinksFromComponentToInsert(EObject context_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    EObject target = ((DSemanticDecorator) context_p).getTarget();
    if (target instanceof Component) {
      Component component = (Component) target;
      EList<InterfaceImplementation> implementedInterfaceLinks = component.getImplementedInterfaceLinks();
      if (null != implementedInterfaceLinks) {
        result.addAll(implementedInterfaceLinks);
      }
      EList<InterfaceUse> usedInterfaceLinks = component.getUsedInterfaceLinks();
      if (null != usedInterfaceLinks) {
        result.addAll(usedInterfaceLinks);
      }
    } else if (target instanceof ComponentPort) {
      ComponentPort port = (ComponentPort) target;
      EList<Interface> providedInterfaces = port.getProvidedInterfaces();
      if (null != providedInterfaces) {
        result.addAll(providedInterfaces);
      }
      EList<Interface> requitedInterfaces = port.getRequiredInterfaces();
      if (null != requitedInterfaces) {
        result.addAll(requitedInterfaces);
      }
    }
    return result;
  }

  /*
   * return existing use link, implementation link, provided interface and required interface from diagram
   */
  public List<CapellaElement> getExistingInterfacesAndLinksFromDiagram(EObject sourceView_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    DDiagram diagramContainer = CapellaServices.getService().getDiagramContainer(sourceView_p);
    EObject sourceTarget = ((DSemanticDecorator) sourceView_p).getTarget();
    if ((null != sourceTarget) && (null != diagramContainer)) {
      List<CapellaElement> availableInterfacesAndLinksFromComponentToInsert = getAvailableInterfacesAndLinksFromComponentToInsert(sourceView_p);
      if (sourceTarget instanceof Component) {
        // add only implementation and use edges from diagram
        for (CapellaElement capellaElement : availableInterfacesAndLinksFromComponentToInsert) {
          if (DiagramServices.getDiagramServices().isOnDiagram(diagramContainer, capellaElement)) {
            result.add(capellaElement);
          }
        }
      } else if (sourceTarget instanceof ComponentPort) {
        // add only provided and required edges from diagram
        for (CapellaElement capellaElement : availableInterfacesAndLinksFromComponentToInsert) {
          if (DiagramServices.getDiagramServices().isOnDiagram(diagramContainer, capellaElement)) {
            // check if there is provided or required link between sourceView_p and capellaElement
            EList<DEdge> outgoingEdges = ((DNode) sourceView_p).getOutgoingEdges();
            for (DEdge dEdge : outgoingEdges) {
              EdgeTarget edgeSNode = dEdge.getSourceNode();
              EdgeTarget edgeTNode = dEdge.getTargetNode();
              if ((null != edgeSNode) && (null != edgeTNode)) {
                EObject edgeSTarget = ((DSemanticDecorator) edgeSNode).getTarget();
                EObject edgeTTarget = ((DSemanticDecorator) edgeTNode).getTarget();
                if ((null != edgeSTarget) && (null != edgeTTarget)) {
                  if (edgeSTarget.equals(sourceTarget) && edgeTTarget.equals(capellaElement)) {
                    result.add(capellaElement);
                  }
                }
              }
            }

          }
        }
      }
    }
    return result;
  }

  @Deprecated
  public void showHideRelationShipFromIDB(EObject sourceView_p, List<EObject> selectedElements) {
    showHideIDBRelationships(sourceView_p, selectedElements);
  }

  /**
   * @common.odesign show/hide use, impl, required and provided links
   */
  public void showHideIDBRelationships(EObject sourceView_p, List<EObject> selectedElements) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView_p);
    DiagramServices diagramServices = DiagramServices.getDiagramServices();
    EObject sourceTarget = ((DSemanticDecorator) sourceView_p).getTarget();
    if (null == diagram) {
      return;
    }

    // Remove Elements From Diagram (only if diagram is synchronized)
    //
    if (!diagram.isSynchronized()) {
      List<CapellaElement> existingElements = getExistingInterfacesAndLinksFromDiagram(sourceView_p);
      // existing elements to be removed
      if (null != selectedElements) {
        existingElements.removeAll(selectedElements);
      }
      List<DEdge> edges = new ArrayList<DEdge>();
      if (sourceTarget instanceof Component) {
        // remove use and implementation links
        for (DEdge representation : diagram.getEdges()) {
          EObject target = representation.getTarget();
          // sourceNode check
          EdgeTarget sourceNode = representation.getSourceNode();
          // mapping check (use and implementation)
          List<String> mappingCheckList = new ArrayList<String>();
          mappingCheckList.add(IMappingNameConstants.IDB_IMPLEMENTATION_INTERFACE_MAPPING_NAME);
          mappingCheckList.add(IMappingNameConstants.IDB_USE_INTERFACE_MAPPING_NAME);
          if ((null != sourceNode) && sourceNode.equals(sourceView_p) && (null != target) && existingElements.contains(target)
              && isValidActualMapping(representation, mappingCheckList)) {
            // collect the representation to remove
            edges.add(representation);
          }
        }
      } else if (sourceTarget instanceof ComponentPort) {
        // remove provided and required links
        for (DEdge representation : diagram.getEdges()) {
          EObject edgeTarget = representation.getTarget();
          // sourceNode check
          EdgeTarget sourceNode = representation.getSourceNode();
          EdgeTarget targetNode = representation.getTargetNode();
          if ((null != edgeTarget) && (null != sourceNode) && (null != targetNode) && sourceNode.equals(sourceView_p)) {
            EObject targetTarget = ((DSemanticDecorator) targetNode).getTarget();
            if ((null != targetTarget) && existingElements.contains(targetTarget)) {
              List<String> mappingCheckList = new ArrayList<String>();
              mappingCheckList.add(IMappingNameConstants.IDB_PROVIDED_INTERFACE_MAPPING_NAME);
              mappingCheckList.add(IMappingNameConstants.IDB_REQUIRED_INTERFACE_MAPPING_NAME);
              if (isValidActualMapping(representation, mappingCheckList)) {
                edges.add(representation);
              }
            }
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
    for (EObject selectedElement : selectedElements) {
      // when sourceView_p is Component
      if ((selectedElement instanceof InterfaceImplementation) || (selectedElement instanceof InterfaceUse)) {
        createEdgeViewWithTargetViewIfNeeded(sourceView_p, diagram, selectedElement);
      } else if ((selectedElement instanceof Interface) && (sourceTarget instanceof ComponentPort)) {
        // when sourceView_p is ComponentPort
        Interface targetElement = (Interface) selectedElement;
        AbstractDNode targetVeiw = null;
        boolean providedLink = ComponentExt.isProvidingInterface((ComponentPort) sourceTarget, targetElement);
        boolean requiredLink = ComponentExt.isRequiringInterface((ComponentPort) sourceTarget, targetElement);
        if (diagramServices.isOnDiagram(diagram, targetElement)) {
          // retrieve InterfaceView
          EObject diagramElement = diagramServices.getDiagramElement(diagram, targetElement);
          if ((null != diagramElement) && (diagramElement instanceof AbstractDNode)) {
            targetVeiw = (AbstractDNode) diagramElement;
          }
        } else {
          // create interfaceView
          ContainerMapping containerMapping = diagramServices.getContainerMapping(diagram, IMappingNameConstants.IDB_INTERFACE_MAPPING_NAME);
          if (null != containerMapping) {
            // before refresh will rearrange the targetElement created
            targetVeiw = diagramServices.createAbstractDNodeContainer(containerMapping, targetElement, diagram, diagram);
          }
        }
        // create Edge
        if ((null != targetVeiw) && (sourceView_p instanceof EdgeTarget) && (targetVeiw instanceof EdgeTarget)) {
          // check if edge exist
          if (!diagramServices.isEdgeOnDiagram((EdgeTarget) sourceView_p, (EdgeTarget) targetVeiw, sourceTarget)) {
            List<EdgeMapping> edgeMappings = getEdgeMappingFromGivenDiagram(sourceTarget, diagram, providedLink, requiredLink);
            for (EdgeMapping edgeMapping2 : edgeMappings) {
              diagramServices.createEdge(edgeMapping2, (EdgeTarget) sourceView_p, (EdgeTarget) targetVeiw, sourceTarget);
            }
          }
        }
      }
    }
  }

  /**
   * Create Edge view and target view if needed in the diagram
   * @param sourceView_p
   * @param diagram
   * @param link_p
   */
  private void createEdgeViewWithTargetViewIfNeeded(EObject sourceView_p, DDiagram diagram, EObject link_p) {
    DiagramServices diagramServices = DiagramServices.getDiagramServices();

    if (!diagramServices.isOnDiagram(diagram, link_p)) {
      EObject targetElement = InterfaceExt.getTargetElementFromLink(link_p);
      AbstractDNode targetVeiw = null;
      // create or retrieve targetView
      if ((null != targetElement) && diagramServices.isOnDiagram(diagram, targetElement)) {
        // retrieve target view
        EObject diagramElement = diagramServices.getDiagramElement(diagram, targetElement);
        if ((null != diagramElement) && (diagramElement instanceof AbstractDNode)) {
          targetVeiw = (AbstractDNode) diagramElement;
        }
      } else {
        // create target view
        // get targetElement mapping from given diagram
        AbstractNodeMapping mapping = getTargetMappingOfGivenEdgeFromGivenDiagram(diagram, targetElement);
        if (null != mapping) {
          targetVeiw = diagramServices.createAbstractDNodeContainer(mapping, targetElement, diagram, diagram);
        }
      }
      // create Edge
      if ((null != targetVeiw) && (sourceView_p instanceof EdgeTarget) && (targetVeiw instanceof EdgeTarget)) {
        // get edge mapping to be created for given link and diagram
        List<EdgeMapping> edgeMappings = getEdgeMappingFromGivenDiagram(link_p, diagram, false, false);
        for (EdgeMapping edgeMapping : edgeMappings) {
          diagramServices.createEdge(edgeMapping, (EdgeTarget) sourceView_p, (EdgeTarget) targetVeiw, link_p);
        }
      }
    }
  }

  /**
   * return true if actual mapping found for current representation
   * @param representation : an edge
   * @param mappingToCheck_p : list of valid mapping
   * @return
   */
  private boolean isValidActualMapping(DEdge representation, List<String> mappingToCheck_p) {
    IEdgeMapping actualMapping = representation.getActualMapping();
    if (null != actualMapping) {
      if (actualMapping instanceof EdgeMapping) {
        String mappingName = ((EdgeMapping) actualMapping).getName();
        if (mappingToCheck_p.contains(mappingName)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * return existing interface from diagram
   * @param context_p
   * @return
   */
  public List<Interface> getExistingInterfacesFromDiagram(EObject context_p) {
    List<Interface> available = getAvailableInterfacesFromComponentToInsert(context_p);
    List<Interface> result = new ArrayList<Interface>();
    // filter 'context_p' as 'DDiagramElementContainer'
    if (context_p instanceof AbstractDNode) {
      AbstractDNode currentContainer = (AbstractDNode) context_p;
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
   * Return all the available links from CRB Diagram (i.e involvement, extends, includes, CapReal Generalisation, Actor Generalisation)
   * @param context_p
   * @return
   */
  public List<CapellaElement> getAvailableLinksForCRBDiagram(EObject context_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    EObject target = ((DSemanticDecorator) context_p).getTarget();
    if (target instanceof CapabilityRealization) {
      CapabilityRealization element = (CapabilityRealization) target;
      // involvement Systemcomponent
      EList<SystemComponentCapabilityRealizationInvolvement> systemCapRealInvols = element.getOwnedSystemComponentCapabilityRealizations();
      if (!systemCapRealInvols.isEmpty()) {
        result.addAll(systemCapRealInvols);
      }
      // involvement Actor
      EList<ActorCapabilityRealizationInvolvement> ownedActorCapabilityRealizations = element.getOwnedActorCapabilityRealizations();
      if (!ownedActorCapabilityRealizations.isEmpty()) {
        result.addAll(ownedActorCapabilityRealizations);
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
    } else if (target instanceof AbstractActor) {
      AbstractActor element = (AbstractActor) target;
      // Actor generalization
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
  public List<CapellaElement> getExistingLinksFromCRBDiagram(EObject sourceView_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    DDiagram diagramContainer = CapellaServices.getService().getDiagramContainer(sourceView_p);
    EObject sourceTarget = ((DSemanticDecorator) sourceView_p).getTarget();
    if ((null != sourceTarget) && (null != diagramContainer)) {
      List<CapellaElement> availableLinksForCRBDiagram = getAvailableLinksForCRBDiagram(sourceView_p);
      if ((sourceTarget instanceof CapabilityRealization) || (sourceTarget instanceof AbstractActor)) {
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
  public void showHideRelationShipFromCRB(EObject sourceView_p, List<EObject> selectedElements) {
    showHideCRBRelationships(sourceView_p, selectedElements);
  }

  /**
   * @common.odesign show/hide links from CRB Diagram
   */
  public void showHideCRBRelationships(EObject sourceView_p, List<EObject> selectedElements) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView_p);
    DiagramServices diagramServices = DiagramServices.getDiagramServices();
    EObject sourceTarget = ((DSemanticDecorator) sourceView_p).getTarget();
    if (null == diagram) {
      return;
    }

    // Remove Elements From Diagram (only if diagram is synchronized)
    //
    if (!diagram.isSynchronized()) {
      List<CapellaElement> existingElements = getExistingLinksFromCRBDiagram(sourceView_p);
      // existing elements to be removed
      if (null != selectedElements) {
        existingElements.removeAll(selectedElements);
      }
      List<DEdge> edges = new ArrayList<DEdge>();
      if ((sourceTarget instanceof CapabilityRealization) || (sourceTarget instanceof AbstractActor)) {
        // remove use and implementation links
        for (DEdge representation : diagram.getEdges()) {
          EObject target = representation.getTarget();
          // sourceNode check
          EdgeTarget sourceNode = representation.getSourceNode();
          // mapping check (use and implementation)
          List<String> mappingCheckList = new ArrayList<String>();
          mappingCheckList.add(IMappingNameConstants.CRB_INVOLVEMENT_MAPPING);
          mappingCheckList.add(IMappingNameConstants.CRB_EXTENDS_MAPPING);
          mappingCheckList.add(IMappingNameConstants.CRB_INCLIDE_MAPPING);
          mappingCheckList.add(IMappingNameConstants.CRB_CAP_GENERALIZATION_MAPPING);
          mappingCheckList.add(IMappingNameConstants.CRB_ACTOR_GENERALIZATION_MAPPING);
          if ((null != sourceNode) && sourceNode.equals(sourceView_p) && (null != target) && existingElements.contains(target)
              && isValidActualMapping(representation, mappingCheckList)) {
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
    for (EObject selectedElement : selectedElements) {
      // when sourceView_p is Component
      if ((selectedElement instanceof SystemComponentCapabilityRealizationInvolvement) || (selectedElement instanceof ActorCapabilityRealizationInvolvement)
          || (selectedElement instanceof AbstractCapabilityExtend) || (selectedElement instanceof AbstractCapabilityInclude)
          || (selectedElement instanceof AbstractCapabilityGeneralization) || (selectedElement instanceof Generalization)) {
        // create edge view and target if needed
        createEdgeViewWithTargetViewIfNeeded(sourceView_p, diagram, selectedElement);
      }
    }
  }

  /**
   * Return actual mapping based on targetElement(a capella Element) from a Diagram
   * @param diagram_p
   * @param targetElement_p
   * @return
   */
  private AbstractNodeMapping getTargetMappingOfGivenEdgeFromGivenDiagram(DDiagram diagram_p, EObject targetElement_p) {
    AbstractNodeMapping acturalMapping = null;
    if ((null == diagram_p) || (targetElement_p == null)) {
      return acturalMapping;
    }
    DiagramServices diagramServices = DiagramServices.getDiagramServices();
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      if ((targetElement_p instanceof AbstractActor) || (targetElement_p instanceof Component)) {
        acturalMapping = diagramServices.getContainerMapping(diagram_p, IMappingNameConstants.CRB_COMPONENT_MAPPING);
      } else if (targetElement_p instanceof CapabilityRealization) {
        acturalMapping = diagramServices.getNodeMapping(diagram_p, IMappingNameConstants.CRB_CAPABILITY_REALIZATION_MAPPING);
      }
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME)) {
      if (targetElement_p instanceof Interface) {
        acturalMapping = diagramServices.getContainerMapping(diagram_p, IMappingNameConstants.IDB_INTERFACE_MAPPING_NAME);
      }
    }

    return acturalMapping;
  }

  /**
   * get the Generalisation edge mapping from class blank diagram
   */
  private List<EdgeMapping> getEdgeMappingFromGivenDiagram(EObject element_p, DDiagram diagram_p, boolean providedEdge_p, boolean requiredEdge_p) {
    List<EdgeMapping> result = new ArrayList<EdgeMapping>();

    if ((null == element_p) || (null == diagram_p)) {
      return result;
    }
    // CAPABILITY REALIZATION BLANK Diagram
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      if ((element_p instanceof SystemComponentCapabilityRealizationInvolvement) || (element_p instanceof ActorCapabilityRealizationInvolvement)) {
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.CRB_INVOLVEMENT_MAPPING));
      } else if (element_p instanceof AbstractCapabilityExtend) {
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.CRB_EXTENDS_MAPPING));
      } else if (element_p instanceof AbstractCapabilityInclude) {
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.CRB_INCLIDE_MAPPING));
      } else if (element_p instanceof AbstractCapabilityGeneralization) {
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.CRB_CAP_GENERALIZATION_MAPPING));
      } else if (element_p instanceof Generalization) {
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.CRB_ACTOR_GENERALIZATION_MAPPING));
      }
    } else
    // INTERFACES DIAGRAM BLANK
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME)) {
      if (element_p instanceof InterfaceImplementation) {
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.IDB_IMPLEMENTATION_INTERFACE_MAPPING_NAME));
      } else if (element_p instanceof InterfaceUse) {
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.IDB_USE_INTERFACE_MAPPING_NAME));
      } else if ((element_p instanceof ComponentPort) && providedEdge_p && !requiredEdge_p) {
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.IDB_PROVIDED_INTERFACE_MAPPING_NAME));
      } else if ((element_p instanceof ComponentPort) && requiredEdge_p && !providedEdge_p) {
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.IDB_REQUIRED_INTERFACE_MAPPING_NAME));
      } else if ((element_p instanceof ComponentPort) && requiredEdge_p && providedEdge_p) {
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.IDB_PROVIDED_INTERFACE_MAPPING_NAME));
        result.add(DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.IDB_REQUIRED_INTERFACE_MAPPING_NAME));
      }
    }

    return result;
  }

  /**
   * @param diagram_p
   * @param contextualElements_p
   */
  public void showCDBContextualElements(DDiagram diagram_p, Collection<EObject> contextualElements_p) {
    Collection<EObject> contextualTypes = new HashSet<EObject>();
    Collection<EObject> contextualInterfaces = new HashSet<EObject>();
    Collection<EObject> contextualExchangeItems = new HashSet<EObject>();
    Collection<EObject> contextualRelationships = new HashSet<EObject>();

    DDiagramContents content = new DDiagramContents(diagram_p);
    for (EObject contextualElement : contextualElements_p) {
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
        contextualRelationships.addAll(CapellaServices.getService().getRelatedAssociations((Classifier) contextualElement));
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

  public String getFullPathLableConsiderViewContentment(AbstractNamedElement context_p, EObject view_p) {
    List<String> resultList = new ArrayList<String>(0);
    // make sure filter is enable an element in question is capella named element
    if (isDiagramFilterEnable(context_p, view_p, IMappingNameConstants.CDB_SHOW_FULL_PATH)) {
      if (view_p instanceof DDiagramElement) {
        DDiagramElement dElement = (DDiagramElement) view_p;
        EObject dElementContainer = dElement.eContainer();
        if (null != dElementContainer) {
          // container is a diagram
          if (dElementContainer instanceof DDiagram) {
            resultList = getFullNameUntilGivenElement(context_p, null, false);
          }
          // container is a dataPkg
          else if (dElementContainer instanceof DDiagramElement) {
            DDiagramElement dElelementContinerTyped = (DDiagramElement) dElementContainer;
            EObject dElelementContinerTarget = dElelementContinerTyped.getTarget();
            // continue if its named named element
            if ((null != dElelementContinerTarget) && (dElelementContinerTarget instanceof AbstractNamedElement)) {
              resultList = getFullNameUntilGivenElement(context_p, dElelementContinerTarget, true);
            }
          }
        }
      }
    } else {
      return CapellaServices.getService().getEObjectLabelProviderHelper(context_p);
    }

    // result
    String result = ICommonConstants.EMPTY_STRING;
    // reverse the list to get the right order to display
    Collections.reverse(resultList);
    // insert special character "::"
    LinkedList<String> list = new LinkedList<String>();
    list.addAll(resultList);
    for (String string : list) {
      if (list.getLast().equals(string)) {
        result = result + string;
      } else {
        result = result + string + "::"; //$NON-NLS-1$
      }
    }
    return result;
  }

  /**
   * Return full path in a list for give capella element If @param checkUntileGivenElement_p : is true calculate full path until @param
   * dElelementContinerTarget_p
   * @param context_p
   * @param dElelementContinerTarget_p
   * @param checkUntileGivenElement_p
   */
  private List<String> getFullNameUntilGivenElement(AbstractNamedElement context_p, EObject dElelementContinerTarget_p, boolean checkUntileGivenElement_p) {
    List<String> resultList = new ArrayList<String>(0);

    // check if full name need to be calculated until some element
    if (checkUntileGivenElement_p) {
      if ((null == dElelementContinerTarget_p) || context_p.equals(dElelementContinerTarget_p)) {
        return resultList;
      }
    }

    // if root add symbol
    // else simple name
    if (isRoot(context_p)) {
      resultList.add(getSymbol(context_p));
    } else {
      resultList.add(CapellaServices.getService().getEObjectLabelProviderHelper(context_p));
    }

    // if any architecture later encountered
    // return the result
    if ((context_p instanceof ComponentArchitecture) || (context_p instanceof OperationalAnalysis)) {
      return resultList;
    }

    // retrieve container of the currentElement
    // apply recursive call
    EObject eContainer = context_p.eContainer();
    if ((null != eContainer) && (eContainer instanceof AbstractNamedElement)) {
      resultList.addAll(getFullNameUntilGivenElement((AbstractNamedElement) eContainer, dElelementContinerTarget_p, checkUntileGivenElement_p));
    }

    return resultList;
  }

  /**
   * return Symbol
   * @param context_p
   * @return
   */
  private String getSymbol(AbstractNamedElement context_p) {
    if (context_p instanceof Entity) {
      return "[E]"; //$NON-NLS-1$
    } else if (context_p instanceof OperationalContext) {
      return "[OC]";//$NON-NLS-1$
    } else if (context_p instanceof org.polarsys.capella.core.data.ctx.System) {
      return "[S]";//$NON-NLS-1$
    } else if (context_p instanceof SystemContext) {
      return "[SC]";//$NON-NLS-1$
    } else if (context_p instanceof LogicalComponent) {
      return "[LS]";//$NON-NLS-1$
    } else if (context_p instanceof LogicalContext) {
      return "[LC]";//$NON-NLS-1$
    } else if (context_p instanceof PhysicalComponent) {
      return "[PS]";//$NON-NLS-1$
    } else if (context_p instanceof PhysicalContext) {
      return "[PC]";//$NON-NLS-1$
    } else if (context_p instanceof ConfigurationItem) {
      return "[CI]";//$NON-NLS-1$
    } else if (context_p instanceof EPBSContext) {
      return "[EPBSC]";//$NON-NLS-1$
    } else if (context_p instanceof OperationalAnalysis) {
      return "[OA]";//$NON-NLS-1$
    } else if (context_p instanceof SystemAnalysis) {
      return "[SA]";//$NON-NLS-1$
    } else if (context_p instanceof LogicalArchitecture) {
      return "[LA]";//$NON-NLS-1$
    } else if (context_p instanceof PhysicalArchitecture) {
      return "[PA]";//$NON-NLS-1$
    } else if (context_p instanceof EPBSArchitecture) {
      return "[EPBSA]";//$NON-NLS-1$
    } else if (context_p instanceof EntityPkg) {
      return "[EPkg]";//$NON-NLS-1$
    } else if (context_p instanceof ActorPkg) {
      return "[APkg]";//$NON-NLS-1$
    } else if (context_p instanceof LogicalActorPkg) {
      return "[LAPkg]";//$NON-NLS-1$
    } else if (context_p instanceof PhysicalActorPkg) {
      return "[PAPkg]";//$NON-NLS-1$
    } else if (context_p instanceof LogicalComponentPkg) {
      return "[PAPkg]";//$NON-NLS-1$
    } else if (context_p instanceof PhysicalComponentPkg) {
      return "[PAPkg]";//$NON-NLS-1$
    } else if (context_p instanceof ConfigurationItemPkg) {
      return "[PAPkg]";//$NON-NLS-1$
    }
    return null;
  }

  /**
   * check if element is root element in the model
   * @param context_p
   * @return
   */
  private boolean isRoot(AbstractNamedElement context_p) {
    if (context_p instanceof org.polarsys.capella.core.data.ctx.System) {
      return true;
    } else if ((context_p instanceof LogicalComponent) || (context_p instanceof PhysicalComponent) || (context_p instanceof ConfigurationItem)) {
      return ComponentExt.isComponentRoot(context_p);
    } else if ((context_p instanceof ComponentContext) || (context_p instanceof ComponentArchitecture) || (context_p instanceof OperationalAnalysis)) {
      return true;
    } else if ((context_p instanceof ActorPkg) || (context_p instanceof AbstractFunctionalStructure) || (context_p instanceof ConfigurationItemPkg)) {
      EObject eContainer = context_p.eContainer();
      if ((null != eContainer) && ((eContainer instanceof ComponentArchitecture) || (eContainer instanceof OperationalAnalysis))) {
        return true;
      }
    }

    return false;
  }

  /**
   * @used in common.odesign Return true if "Show Full Path"
   * @param assocation_p
   * @param view_p
   * @return
   */
  public boolean isShowFullPathInClassDiagramEnable(EObject assocation_p, EObject view_p) {
    return isDiagramFilterEnable(assocation_p, view_p, IMappingNameConstants.CDB_SHOW_FULL_PATH);
  }

  /**
   * <b>Get all DataValues to insert</b>
   * <p>
   * Get all datavalue content in the selected element used in common.odesign
   * @param context
   * @param containerView
   * @return
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
public List<DataValue> getAvailableDataValuesToInsert(final EObject elementView_p) {

    // Initialization
    List<DataPkg> listPackages = new ArrayList<DataPkg>(1);
    List<DataValue> result = new ArrayList<DataValue>(1);

    // get diagram
    DSemanticDiagram diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(elementView_p);

    /*
     * If Diagram
     */
    if (elementView_p.equals(diagram)) {
      // Get all dataPkg (consider layers)
      EObject diagramTarget = diagram.getTarget();
      // get all dataVales and add to result list
      // OLD CODE
      List<DataValue> l = DataPkgExt.getDataValues(diagramTarget);
      // NEW CODE
      l = (List) QueryDebugger.executeQueryWithInclusionDebug(QueryIdentifierConstants.GET_DATA_VALUES_FOR_LIB, diagramTarget, l);
      // END CODE REFACTOR
      result.addAll(l);
    }
    /*
     * If DNode Container (assume target as DataPkg)
     */
    else if (elementView_p instanceof DNodeContainer) {
      // get target
      EObject containerTarget = ((DNodeContainer) elementView_p).getTarget();
      // if DataPkg
      if (containerTarget instanceof DataPkg) {
        DataPkg currentPkg = (DataPkg) containerTarget;
        // get all dataPkg (recursively from current dataPkg)
        listPackages.add(currentPkg);
        listPackages.addAll(DataPkgExt.getRecursiveSubDataPkgs(currentPkg));

        // get all dataVales and add to result
        result.addAll(DataPkgExt.getDataValues(listPackages));
      }
    }
    /*
     * If DNodeList (Class, DataType, etc)
     */
    else if (elementView_p instanceof DNodeListSpec) {
      // get target
      EObject nodeListTarget = ((DNodeListSpec) elementView_p).getTarget();
      // if Class
      if (nodeListTarget instanceof Class) {
        Class currentClass = (Class) nodeListTarget;
        // add the DataValues from Class to the result list
        Iterator<DataValue> itDataValues = currentClass.getOwnedDataValues().iterator();
        while (itDataValues.hasNext()) {
          result.add(itDataValues.next());
        }
      }
      // if DataType
      else if (nodeListTarget instanceof DataType) {
        DataType currentDataType = (DataType) nodeListTarget;
        // add the DataValues from DataType to the result list
        result.addAll(DataTypeExt.getAllDataValuesFromDataType(currentDataType));
      } else if (nodeListTarget instanceof org.polarsys.capella.core.data.information.Collection) {
        org.polarsys.capella.core.data.information.Collection currentClass = (org.polarsys.capella.core.data.information.Collection) nodeListTarget;
        // add the DataValues from Class to the result list
        Iterator<DataValue> itDataValues = currentClass.getOwnedDataValues().iterator();
        while (itDataValues.hasNext()) {
          result.add(itDataValues.next());
        }
      }
    }
    return result;
  }

  /**
   * <b>Return initial selection of the data value for transfer wizard</b>
   * <p>
   * Used in common.odesign
   * @param elementView_p
   * @return return list of dataValues
   */
  public List<DataValue> getInitialSelectionOfShowHideDataValues(final EObject elementView_p) {
    // Initialization
    List<DataValue> result = new ArrayList<DataValue>(1);

    // get diagram
    DSemanticDiagram diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(elementView_p);
    /*
     * If Diagram or DNodeContainer
     */
    if (elementView_p.equals(diagram)) {
      // get all nodes from the diagram
      EList<DNode> diagramElements = diagram.getNodes();
      for (DNode dDiagramElement : diagramElements) {
        EObject target = dDiagramElement.getTarget();
        // consider only dataValues
        if ((null != target) && (target instanceof DataValue)) {
          result.add((DataValue) target);
        }
      }
    }
    /*
     * If DNodeContainer
     */
    if (elementView_p instanceof DNodeContainer) {
      // get all nodes from the diagram
      EList<DNode> diagramElements = ((DNodeContainer) elementView_p).getNodes();
      for (DNode dDiagramElement : diagramElements) {
        EObject target = dDiagramElement.getTarget();
        // consider only dataValues
        if ((null != target) && (target instanceof DataValue)) {
          result.add((DataValue) target);
        }
      }
    }
    /*
     * If DNodeList (Class, DataType, etc)
     */
    else if (elementView_p instanceof DNodeList) {
      // get target
      DNodeList nodeList = (DNodeList) elementView_p;
      EList<DNodeListElement> ownedElements = nodeList.getOwnedElements();
      for (DNodeListElement dDiagramElement : ownedElements) {
        EObject target = dDiagramElement.getTarget();
        if ((null != target) && (target instanceof DataValue)) {
          result.add((DataValue) target);
        }
      }
    }

    return result;
  }

  public static Object getEILabel(AbstractExchangeItem ei_p, boolean showExchangeItemsParameters) {
    return ExchangeItemExt.getEILabel(ei_p, showExchangeItemsParameters);
  }
}
