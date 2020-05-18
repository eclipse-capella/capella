/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.information;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedRelationshipHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.TypedElementHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.AbstractBooleanValueHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.AbstractCollectionValueHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.AbstractComplexValueHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.AbstractEnumerationValueHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.AbstractInstanceHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.NumericValueHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.AbstractStringValueHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.BinaryExpressionHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.ClassHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.CollectionHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.CommunicationItemHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.CommunicationLinkExchangerHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.CommunicationLinkHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.DataTypeHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.ExchangeItemElementHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.ExchangeItemHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.ExchangeItemRealizationHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.OpaqueExpressionHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.OperationAllocationHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.PortAllocationHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.PortHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.PortRealizationHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.PropertyHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.ServiceHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.UnaryExpressionHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.UnionHelper;
import org.polarsys.capella.core.data.information.AbstractCollectionValue;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.information.ExchangeItemRealization;
import org.polarsys.capella.core.data.information.InformationRealization;
import org.polarsys.capella.core.data.information.KeyPart;
import org.polarsys.capella.core.data.information.OperationAllocation;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.Unit;
import org.polarsys.capella.core.data.information.communication.CommunicationItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger;
import org.polarsys.capella.core.data.information.communication.MessageReference;
import org.polarsys.capella.core.data.information.communication.MessageReferencePkg;
import org.polarsys.capella.core.data.information.communication.SignalInstance;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractComplexValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractEnumerationValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractStringValue;
import org.polarsys.capella.core.data.information.datavalue.BinaryExpression;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.information.datavalue.OpaqueExpression;
import org.polarsys.capella.core.data.information.datavalue.UnaryExpression;
import org.polarsys.capella.core.data.information.datavalue.ValuePart;

public class InformationHelper implements IHelper {

  /**
   * Default constructor
   */
  public InformationHelper() {
    // Preferences are now loaded automatically when the appropriate plug-in starts due to plug-in dependencies.
    // Hence, it is not required to call checkPreferences to get DataListeners loaded.
  }

  /**
   * @see org.polarsys.capella.common.model.helpers.IHelper#getValue(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature,
   *      org.eclipse.emf.ecore.EAnnotation)
   */
  public Object getValue(EObject object, EStructuralFeature feature, EAnnotation annotation) {
    Object ret = null;

    if (object instanceof ExchangeItemInstance) {
      ret = AbstractInstanceHelper.getInstance().doSwitch((ExchangeItemInstance) object, feature);
    } else if (object instanceof SignalInstance) {
      ret = AbstractInstanceHelper.getInstance().doSwitch((SignalInstance) object, feature);
    } else if (object instanceof Port) {
      ret = PortHelper.getInstance().doSwitch((Port) object, feature);
    } else if (object instanceof PortRealization) {
      ret = PortRealizationHelper.getInstance().doSwitch((PortRealization) object, feature);
    } else if (object instanceof PortAllocation) {
      ret = PortAllocationHelper.getInstance().doSwitch((PortAllocation) object, feature);
    } else if (object instanceof Property) {
      ret = PropertyHelper.getInstance().doSwitch((Property) object, feature);
    } else if (object instanceof Association) {
      ret = NamedRelationshipHelper.getInstance().doSwitch((Association) object, feature);
    } else if (object instanceof Union) {
      ret = UnionHelper.getInstance().doSwitch((Union) object, feature);
    } else if (object instanceof Class) {
      ret = ClassHelper.getInstance().doSwitch((Class) object, feature);
    } else if (object instanceof Collection) {
      ret = CollectionHelper.getInstance().doSwitch((Collection) object, feature);
    } else if (object instanceof Service) {
      ret = ServiceHelper.getInstance().doSwitch((Service) object, feature);
    } else if (object instanceof ValuePart) {
      ret = CapellaElementHelper.getInstance().doSwitch((ValuePart) object, feature);
    } else if (object instanceof CommunicationItem) {
      ret = CommunicationItemHelper.getInstance().doSwitch((CommunicationItem) object, feature);
    } else if (object instanceof Parameter) {
      ret = TypedElementHelper.getInstance().doSwitch((Parameter) object, feature);
    } else if (object instanceof OperationAllocation) {
      ret = OperationAllocationHelper.getInstance().doSwitch((OperationAllocation) object, feature);
    } else if (object instanceof ExchangeItemRealization) {
      ret = ExchangeItemRealizationHelper.getInstance().doSwitch((ExchangeItemRealization) object, feature);
    } else if (object instanceof KeyPart) {
      ret = RelationshipHelper.getInstance().doSwitch((KeyPart) object, feature);
    } else if (object instanceof Unit) {
      ret = NamedElementHelper.getInstance().doSwitch((Unit) object, feature);
    } else if (object instanceof MessageReference) {
      ret = RelationshipHelper.getInstance().doSwitch((MessageReference) object, feature);
    } else if (object instanceof MessageReferencePkg) {
      ret = StructureHelper.getInstance().doSwitch((MessageReferencePkg) object, feature);
    } else if (object instanceof BinaryExpression) {
      ret = BinaryExpressionHelper.getInstance().doSwitch((BinaryExpression) object, feature);
    } else if (object instanceof UnaryExpression) {
      ret = UnaryExpressionHelper.getInstance().doSwitch((UnaryExpression) object, feature);
    } else if (object instanceof AbstractBooleanValue) {
      ret = AbstractBooleanValueHelper.getInstance().doSwitch((AbstractBooleanValue) object, feature);
    } else if (object instanceof AbstractCollectionValue) {
      ret = AbstractCollectionValueHelper.getInstance().doSwitch((AbstractCollectionValue) object, feature);
    } else if (object instanceof AbstractComplexValue) {
      ret = AbstractComplexValueHelper.getInstance().doSwitch((AbstractComplexValue) object, feature);
    } else if (object instanceof AbstractEnumerationValue) {
      ret = AbstractEnumerationValueHelper.getInstance().doSwitch((AbstractEnumerationValue) object, feature);
    } else if (object instanceof NumericValue) {
      ret = NumericValueHelper.getInstance().doSwitch((NumericValue) object, feature);
    } else if (object instanceof AbstractStringValue) {
      ret = AbstractStringValueHelper.getInstance().doSwitch((AbstractStringValue) object, feature);
    } else if (object instanceof DataType) {
      ret = DataTypeHelper.getInstance().doSwitch((DataType) object, feature);
    } else if (object instanceof Structure) {
      ret = StructureHelper.getInstance().doSwitch((Structure) object, feature);
    } else if (object instanceof ExchangeItem) {
      ret = ExchangeItemHelper.getInstance().doSwitch((ExchangeItem) object, feature);
    } else if (object instanceof ExchangeItemElement) {
      ret = ExchangeItemElementHelper.getInstance().doSwitch((ExchangeItemElement) object, feature);
    } else if (object instanceof InformationRealization) {
      ret = RelationshipHelper.getInstance().doSwitch((InformationRealization) object, feature);
    } else if (object instanceof CommunicationLink) {
      ret = CommunicationLinkHelper.getInstance().doSwitch((CommunicationLink) object, feature);
    } else if (object instanceof CommunicationLinkExchanger) {
      ret = CommunicationLinkExchangerHelper.getInstance().doSwitch((CommunicationLinkExchanger) object, feature);
    } else if (object instanceof OpaqueExpression){
      ret = OpaqueExpressionHelper.getInstance().doSwitch((OpaqueExpression) object, feature);
    }

    if (null != ret || feature.getUpperBound() == 1)
      return ret;

    throw new HelperNotFoundException();
  }
}
