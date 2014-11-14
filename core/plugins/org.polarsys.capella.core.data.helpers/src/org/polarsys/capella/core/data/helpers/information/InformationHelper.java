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
package org.polarsys.capella.core.data.helpers.information;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.information.delegates.AbstractBooleanValueHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.AbstractCollectionValueHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.AbstractComplexValueHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.AbstractEnumerationValueHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.AbstractInstanceHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.AbstractNumericValueHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.AbstractStringValueHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.BinaryExpressionHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.ClassHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.CollectionHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.CommunicationItemHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.CommunicationLinkAllocationHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.CommunicationLinkExchangerHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.CommunicationLinkHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.DataTypeHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.ExchangeItemElementHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.ExchangeItemHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.ExchangeItemRealizationHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.OperationAllocationHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.PartitionHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.PartitionableElementHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.PortAllocationHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.PortHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.PortRealizationHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.PropertyHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.ServiceHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.UnaryExpressionHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.UnionHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedRelationshipHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.TypedElementHelper;
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
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.Unit;
import org.polarsys.capella.core.data.information.communication.CommunicationItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkAllocation;
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
import org.polarsys.capella.core.data.information.datavalue.UnaryExpression;
import org.polarsys.capella.core.data.information.datavalue.ValuePart;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.common.tig.model.HelperNotFoundException;
import org.polarsys.capella.common.tig.model.IHelper;

public class InformationHelper implements IHelper {

  /**
   * Default constructor
   */
  public InformationHelper() {
    // Preferences are now loaded automatically when the appropriate plug-in starts due to plug-in dependencies.
    // Hence, it is not required to call checkPreferences to get DataListeners loaded.
  }

  /**
   * @see org.polarsys.capella.common.tig.model.IHelper#getValue(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature,
   *      org.eclipse.emf.ecore.EAnnotation)
   */
  public Object getValue(EObject object_p, EStructuralFeature feature_p, EAnnotation annotation_p) {
    Object ret = null;

    if (object_p instanceof ExchangeItemInstance) {
      ret = AbstractInstanceHelper.getInstance().doSwitch((ExchangeItemInstance) object_p, feature_p);
    } else if (object_p instanceof SignalInstance) {
      ret = AbstractInstanceHelper.getInstance().doSwitch((SignalInstance) object_p, feature_p);
    } else if (object_p instanceof Partition) {
      ret = PartitionHelper.getInstance().doSwitch((Partition) object_p, feature_p);
    } else if (object_p instanceof Port) {
      ret = PortHelper.getInstance().doSwitch((Port) object_p, feature_p);
    } else if (object_p instanceof PortRealization) {
      ret = PortRealizationHelper.getInstance().doSwitch((PortRealization) object_p, feature_p);
    } else if (object_p instanceof PortAllocation) {
      ret = PortAllocationHelper.getInstance().doSwitch((PortAllocation) object_p, feature_p);
    } else if (object_p instanceof PartitionableElement) {
      ret = PartitionableElementHelper.getInstance().doSwitch((PartitionableElement) object_p, feature_p);
    } else if (object_p instanceof Property) {
      ret = PropertyHelper.getInstance().doSwitch((Property) object_p, feature_p);
    } else if (object_p instanceof Association) {
      ret = NamedRelationshipHelper.getInstance().doSwitch((Association) object_p, feature_p);
    } else if (object_p instanceof Union) {
      ret = UnionHelper.getInstance().doSwitch((Union) object_p, feature_p);
    } else if (object_p instanceof Class) {
      ret = ClassHelper.getInstance().doSwitch((Class) object_p, feature_p);
    } else if (object_p instanceof Collection) {
      ret = CollectionHelper.getInstance().doSwitch((Collection) object_p, feature_p);
    } else if (object_p instanceof Service) {
      ret = ServiceHelper.getInstance().doSwitch((Service) object_p, feature_p);
    } else if (object_p instanceof ValuePart) {
      ret = CapellaElementHelper.getInstance().doSwitch((ValuePart) object_p, feature_p);
    } else if (object_p instanceof CommunicationItem) {
      ret = CommunicationItemHelper.getInstance().doSwitch((CommunicationItem) object_p, feature_p);
    } else if (object_p instanceof Parameter) {
      ret = TypedElementHelper.getInstance().doSwitch((Parameter) object_p, feature_p);
    } else if (object_p instanceof OperationAllocation) {
      ret = OperationAllocationHelper.getInstance().doSwitch((OperationAllocation) object_p, feature_p);
    } else if (object_p instanceof ExchangeItemRealization) {
      ret = ExchangeItemRealizationHelper.getInstance().doSwitch((ExchangeItemRealization) object_p, feature_p);
    } else if (object_p instanceof KeyPart) {
      ret = RelationshipHelper.getInstance().doSwitch((KeyPart) object_p, feature_p);
    } else if (object_p instanceof Unit) {
      ret = NamedElementHelper.getInstance().doSwitch((Unit) object_p, feature_p);
    } else if (object_p instanceof MessageReference) {
      ret = RelationshipHelper.getInstance().doSwitch((MessageReference) object_p, feature_p);
    } else if (object_p instanceof MessageReferencePkg) {
      ret = StructureHelper.getInstance().doSwitch((MessageReferencePkg) object_p, feature_p);
    } else if (object_p instanceof BinaryExpression) {
      ret = BinaryExpressionHelper.getInstance().doSwitch((BinaryExpression) object_p, feature_p);
    } else if (object_p instanceof UnaryExpression) {
      ret = UnaryExpressionHelper.getInstance().doSwitch((UnaryExpression) object_p, feature_p);
    } else if (object_p instanceof AbstractBooleanValue) {
      ret = AbstractBooleanValueHelper.getInstance().doSwitch((AbstractBooleanValue) object_p, feature_p);
    } else if (object_p instanceof AbstractCollectionValue) {
      ret = AbstractCollectionValueHelper.getInstance().doSwitch((AbstractCollectionValue) object_p, feature_p);
    } else if (object_p instanceof AbstractComplexValue) {
      ret = AbstractComplexValueHelper.getInstance().doSwitch((AbstractComplexValue) object_p, feature_p);
    } else if (object_p instanceof AbstractEnumerationValue) {
      ret = AbstractEnumerationValueHelper.getInstance().doSwitch((AbstractEnumerationValue) object_p, feature_p);
    } else if (object_p instanceof NumericValue) {
      ret = AbstractNumericValueHelper.getInstance().doSwitch((NumericValue) object_p, feature_p);
    } else if (object_p instanceof AbstractStringValue) {
      ret = AbstractStringValueHelper.getInstance().doSwitch((AbstractStringValue) object_p, feature_p);
    } else if (object_p instanceof DataType) {
      ret = DataTypeHelper.getInstance().doSwitch((DataType) object_p, feature_p);
    } else if (object_p instanceof Structure) {
      ret = StructureHelper.getInstance().doSwitch((Structure) object_p, feature_p);
    } else if (object_p instanceof ExchangeItem) {
      ret = ExchangeItemHelper.getInstance().doSwitch((ExchangeItem) object_p, feature_p);
    } else if (object_p instanceof ExchangeItemElement) {
      ret = ExchangeItemElementHelper.getInstance().doSwitch((ExchangeItemElement) object_p, feature_p);
    } else if (object_p instanceof InformationRealization) {
      ret = RelationshipHelper.getInstance().doSwitch((InformationRealization) object_p, feature_p);
    } else if (object_p instanceof CommunicationLink) {
      ret = CommunicationLinkHelper.getInstance().doSwitch((CommunicationLink) object_p, feature_p);
    } else if (object_p instanceof CommunicationLinkAllocation) {
      ret = CommunicationLinkAllocationHelper.getInstance().doSwitch((CommunicationLinkAllocation) object_p, feature_p);
    } else if (object_p instanceof CommunicationLinkExchanger) {
      ret = CommunicationLinkExchangerHelper.getInstance().doSwitch((CommunicationLinkExchanger) object_p, feature_p);
    }

    if (null != ret || feature_p.getUpperBound() == 1)
      return ret;

    throw new HelperNotFoundException();
  }
}
