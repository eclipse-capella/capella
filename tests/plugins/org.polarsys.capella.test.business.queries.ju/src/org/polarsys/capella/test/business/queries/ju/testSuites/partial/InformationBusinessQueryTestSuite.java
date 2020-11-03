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
package org.polarsys.capella.test.business.queries.ju.testSuites.partial;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.information.*;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

/**
 * @author Erwan Brottier
 */
public class InformationBusinessQueryTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new InformationBusinessQueryTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new BinaryExpression_LeftOperand());
    tests.add(new BinaryExpression_RightOperand());
    tests.add(new BinaryExpression_Type());
    tests.add(new BinaryExpression_Unit());
    tests.add(new BooleanReference_AbstractType());
    tests.add(new BooleanReference_ReferencedProperty());
    tests.add(new BooleanReference_ReferencedValue());
    tests.add(new BooleanType_DefaultValue());
    tests.add(new BooleanType_InheritedBooleanType());
    tests.add(new BooleanType_RealizedBooleanType());
    tests.add(new Class_InheritedClasses());
    tests.add(new Class_RealizedClasses());
    tests.add(new Collection_DefaultValue());
    tests.add(new Collection_Index());
    tests.add(new Collection_InheritedCollection());
    tests.add(new Collection_MaxCardinality());
    tests.add(new Collection_MaxValue());
    tests.add(new Collection_MinCardinality());
    tests.add(new Collection_MinValue());
    tests.add(new Collection_NullValue());
    tests.add(new Collection_Type());
    tests.add(new CollectionValue_Type());
    tests.add(new CollectionValueReferenceType());
    tests.add(new CollectionValueRefReferencedProperty());
    tests.add(new CollectionValueRefReferencedValue());
    tests.add(new CommunicationLink_ExchangeItems());
    tests.add(new ComplexValue_Type());
    tests.add(new ComplexValueReferenceType());
    tests.add(new ComplexValueRefReferencedProperty());
    tests.add(new ComplexValueRefReferencedValue());
    tests.add(new Enumeration_DefaultValue());
    tests.add(new Enumeration_DomainType());
    tests.add(new Enumeration_InheritedEnumeration());
    tests.add(new Enumeration_MaxValue());
    tests.add(new Enumeration_MinValue());
    tests.add(new Enumeration_NullValue());
    tests.add(new Enumeration_RealizedEnumeration());
    tests.add(new EnumerationLiteralAbstractType());
    tests.add(new EnumerationLiteralDomainValue());
    tests.add(new EnumerationReference_AbstractType());
    tests.add(new EnumerationReference_ReferencedProperty());
    tests.add(new EnumerationReference_ReferencedValue());
    tests.add(new Exception_InheritedException());
    tests.add(new ExchangeItem_InheritedExchangeItem());
    tests.add(new ExchangeItem_RealizedInformations());
    tests.add(new ExchangeItemElement_ReferencedProperties());
    tests.add(new ExchangeItemElement_Type());
    tests.add(new ExchangeItemElementMaxCard());
    tests.add(new ExchangeItemElementMinCard());
    tests.add(new LiteralBooleanValue_AbstractType());
    tests.add(new LiteralNumericValue_AbstractType());
    tests.add(new LiteralNumericValue_Unit());
    tests.add(new LiteralStringValue_AbstractType());
    tests.add(new NumericReference_AbstractType());
    tests.add(new NumericReference_ReferencedProperty());
    tests.add(new NumericReference_ReferencedValue());
    tests.add(new NumericType_DefaultValue());
    tests.add(new NumericType_InheritedNumericType());
    tests.add(new NumericType_MaxValue());
    tests.add(new NumericType_MinValue());
    tests.add(new NumericType_NullValue());
    tests.add(new NumericType_RealizedNumericType());
    tests.add(new NumericValue_Type());
    tests.add(new NumericValueReference_Unit());
    tests.add(new Parameter_DefaultValue());
    tests.add(new Parameter_DefaultValue());
    tests.add(new Parameter_MaxCardinality());
    tests.add(new Parameter_MaxValue());
    tests.add(new Parameter_MinCardinality());
    tests.add(new Parameter_MinValue());
    tests.add(new Parameter_NullValue());
    tests.add(new Parameter_Type());
    tests.add(new PhysicalQuantity_DefaultValue());
    tests.add(new PhysicalQuantity_InheritedType());
    tests.add(new PhysicalQuantity_MaxValue());
    tests.add(new PhysicalQuantity_MinValue());
    tests.add(new PhysicalQuantity_NullValue());
    tests.add(new PhysicalQuantity_RealizedPhysicalQuantity());
    tests.add(new PhysicalQuantity_Unit());
    tests.add(new Property_DefaultValue());
    tests.add(new Property_MaxCardinality());
    tests.add(new Property_MaxLength());
    tests.add(new Property_MaxValue());
    tests.add(new Property_MinCardinality());
    tests.add(new Property_MinLength());
    tests.add(new Property_MinValue());
    tests.add(new Property_NullValue());
    tests.add(new Property_Type());
    tests.add(new Service_ExchangeItemRealization());
    tests.add(new Service_ReferencedMessage());
    tests.add(new Service_ThrownException());
    tests.add(new StringReference_AbstractType());
    tests.add(new StringReference_ReferencedProperty());
    tests.add(new StringReference_ReferencedValue());
    tests.add(new StringType_DefaultValue());
    tests.add(new StringType_InheritedStringType());
    tests.add(new StringType_MaxLength());
    tests.add(new StringType_MinLength());
    tests.add(new StringType_NullValue());
    tests.add(new StringType_RealizedStringType());
    tests.add(new StringValue_Type());
    tests.add(new UnaryExpression_Operand());
    tests.add(new UnaryExpression_Type());
    tests.add(new UnaryExpression_Unit());
    tests.add(new Union_DefaultProperty());
    tests.add(new Union_Discriminant());
    tests.add(new Union_InheritedClasses());
    tests.add(new UnionProperty_DefaultValue());
    tests.add(new UnionProperty_MaxCardinality());
    tests.add(new UnionProperty_MaxLength());
    tests.add(new UnionProperty_MaxValue());
    tests.add(new UnionProperty_MinCardinality());
    tests.add(new UnionProperty_MinLength());
    tests.add(new UnionProperty_MinValue());
    tests.add(new UnionProperty_NullValue());
    tests.add(new UnionProperty_Qualifiers());
    tests.add(new UnionProperty_Type());    
    return tests;
  }
}
