/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.testcases.la;

import java.util.Arrays;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.transition.ju.CodeHelper;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelLaPa;

/**
 * Projection Tests on "Data Transition" from Logical Layer to Physical Layer
 * <P>
 * This is done with the model "LA-PA-Projection". The model is created as explained below.
 * 
 * <pre>
 *     Model Used: LA-PA-Projection
 *     Model is created with the following elements�
 *         1.  Create a new [CDB] Data - Class Diagram Blank diagram on root data package
 *         2.  Create a primitive class "Primitive Class1" in the diagram
 *         3.  Create classes SuperClass and SubClass in the diagram
 *         4.  Create String types SuperST and SubST in the diagram
 *         5.  Create Property1 in SuperClass typed with SubST
 *         6.  Create Service Service1, Service2 in SubClass
 *         7.  Create Parameter parameter1 in Service1 typed with SuperST
 *         8.  Create Generalization link from SubClass to SuperClass (SubClass extends SuperClass)
 *         9.  Create Generalization link from SubST to SuperST (SubST extends SuperST)
 *         10. Create Constraint Constraint1 in the diagram and a Constraint Element link to SubClass
 * 
 * </pre>
 * 
 * And the tests are documented in {@link #performTest()} method
 */
public class DataTransition extends TopDownTransitionTestCase {

  // Data Pkg
  private DataPkg laDataPkg;
  private DataPkg laMyDataPkg;

  // Classes
  private Class laPrimitiveClass;
  private Class laSuperClass;
  private Class laSubClass;
  private Class laUnion;
  private org.polarsys.capella.core.data.information.Collection laMyCollection;
  private Service laMyService;
  private Parameter laMyParameter;
  private BooleanType laMyBooleanType;
  private LiteralBooleanValue laFalseValue;
  private LiteralBooleanValue laTrueValue;
  private StringType laSuperST;
  private StringType laSubST;

  private Property laProperty1;
  private Service laService1;
  private Parameter laParameter1;
  private Service laService2;
  private Parameter laParameter2;
  private Constraint laConstraint1;

  private DataPkg paDataPkg;
  private DataPkg paMyDataPkg;
  // Classes
  private Class paPrimitiveClass;
  private Class paSuperClass;
  private Class paSubClass;
  private Class paUnion;
  private org.polarsys.capella.core.data.information.Collection paMyCollection;
  private Service paMyService;
  private Parameter paMyParameter;
  private BooleanType paMyBooleanType;
  private LiteralBooleanValue paFalseValue;
  private LiteralBooleanValue paTrueValue;
  private StringType paSuperST;
  private StringType paSubST;

  private Property paProperty1;
  private Service paService1;
  private Parameter paParameter1;
  private Service paService2;
  private Constraint paConstraint1;

  private void initSession() {
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    context = new SessionContext(session);

    laDataPkg = getObject(ModelLaPa.laDataPkgId);
    laPrimitiveClass = getObject(ModelLaPa.primitiveClassId);
    laSuperClass = getObject(ModelLaPa.superClassId);
    laSubClass = getObject(ModelLaPa.subClassId);
    laSuperST = getObject(ModelLaPa.superSTId);
    laSubST = getObject(ModelLaPa.subSTId);
    laProperty1 = getObject(ModelLaPa.property1Id);
    laService1 = getObject(ModelLaPa.service1Id);
    laParameter1 = getObject(ModelLaPa.parameter1Id);
    laService2 = getObject(ModelLaPa.service2Id);
    laConstraint1 = getObject(ModelLaPa.constraint1Id);
    paDataPkg = getObject(ModelLaPa.paDataPkgId);
    laMyDataPkg = getObject(ModelLaPa.laMyDataPkgId);
    laMyCollection = getObject(ModelLaPa.laMyCollectionId);
    laMyService = getObject(ModelLaPa.laMyServiceId);
    laMyParameter = getObject(ModelLaPa.laMyParameterId);
    laMyBooleanType = getObject(ModelLaPa.laMyBooleanTypeId);
    laFalseValue = getObject(ModelLaPa.laFalseValueId);
    laTrueValue = getObject(ModelLaPa.laTrueValueId);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  /**
   * Tests on "Realized By Sytem" Projection command should be applied:
   * 
   * <pre>
   *         1. Test on one leaf entity (PrimitiveClass)
   *         2. Test on non leaf entity (SubClass)
   *         4. Repetition Test on Root Data Pkg without any changes
   *         5. Test on SubClass with changes
   * </pre>
   * 
   * @see org.polarsys.capella.test.common.AbstractExtendedTestSuite#getTests()
   */
  @Override
  public void performTest() throws Exception {
    initSession();
    myCollectionTransitionTest();
    primitiveClassTransitionTest();
    subClassTransition1Test();
    laDataPkgTransitionTest();
    subClassTransition2Test();
  }

  /**
   * Run the projection test "Data Transition" from MyCollection
   * 
   * <pre>
   * Expected Result:\
   *               1. MyCollection, MyDataPkg and MyBooleanType are transitioned to PA Layer.\
   *               2. Boolean Literals of MyBooleanType are also transitioned\
   *               3. Service1 and Parameter are included in MyCollection of PA Layer\
   *               4. Realization Link is created from PA Layer to LA layer
   * </pre>
   */
  private void myCollectionTransitionTest() {
    performDataTransition(Arrays.asList(laMyCollection));
    // MyDataPkg
    paMyDataPkg = paDataPkg.getOwnedDataPkgs().get(0);
    assertNotNull(Messages.NullError, paMyDataPkg);
    assertTrue(NLS.bind(Messages.RealizationError, paMyDataPkg.getName(), laMyDataPkg.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paMyDataPkg) == laMyDataPkg));

    // MyBooleanType
    paMyBooleanType = (BooleanType) paMyDataPkg.getOwnedDataTypes().get(0);
    assertNotNull(Messages.NullError, paMyBooleanType);
    assertTrue(NLS.bind(Messages.RealizationError, paMyBooleanType.getName(), laMyBooleanType.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paMyBooleanType) == laMyBooleanType));

    paFalseValue = paMyBooleanType.getOwnedLiterals().get(0);
    assertNotNull(Messages.NullError, paFalseValue);
    assertTrue(NLS.bind(Messages.RealizationError, paFalseValue.getName(), laFalseValue.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paFalseValue) == laFalseValue));
    assertFalse(NLS.bind(Messages.WrongValue, paFalseValue.getName()),
        paFalseValue.isValue());

    paTrueValue = paMyBooleanType.getOwnedLiterals().get(1);
    assertNotNull(Messages.NullError, paTrueValue);
    assertTrue(NLS.bind(Messages.RealizationError, paTrueValue.getName(), laTrueValue.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paTrueValue) == laTrueValue));
    assertTrue(NLS.bind(Messages.WrongValue, paTrueValue.getName()),
        paTrueValue.isValue());

    // MyCollection
    paMyCollection = paDataPkg.getOwnedCollections().get(0);
    assertNotNull(Messages.NullError, paMyCollection);
    assertTrue(NLS.bind(Messages.RealizationError, paMyCollection.getName(), laMyCollection.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paMyCollection) == laMyCollection));
    assertTrue(NLS.bind(Messages.WrongType, paMyCollection.getName(), paMyBooleanType.getName()),
        (paMyCollection.getType() == paMyBooleanType));

    // MyService and MyParameter are included in MyCollection of PA payer\
    paMyService = (Service) paMyCollection.getContainedOperations().get(0);
    assertNotNull(Messages.NullError, paMyService);
    assertTrue(NLS.bind(Messages.RealizationError, paMyService.getName(), laMyService.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paMyService) == laMyService));

    paMyParameter = paMyService.getOwnedParameters().get(0);
    assertNotNull(Messages.NullError, paMyParameter);
    assertTrue(NLS.bind(Messages.RealizationError, paMyParameter.getName(), laMyParameter.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paMyParameter) == laMyParameter));
    assertTrue(NLS.bind(Messages.WrongType, paMyParameter.getName(), paMyBooleanType.getName()),
        (paMyParameter.getType() == paMyBooleanType));
  }

  /**
   * Run the projection test "Data Transition" from Primitive Class1
   * 
   * <pre>
   * Expected Result:\
   *             1. Primitive Class1 is transitioned to Physical Layer.\
   *             2. Realization Link is created from Logical Layer to Physical layer
   * </pre>
   */
  private void primitiveClassTransitionTest() {
    performDataTransition(Arrays.asList(laPrimitiveClass));
    paPrimitiveClass = ProjectionTestUtils.getRecentlyAddedClass(paDataPkg);
    assertNotNull(Messages.NullError, paPrimitiveClass);

    assertTrue(NLS.bind(Messages.RealizationError, paPrimitiveClass.getName(), laPrimitiveClass.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paPrimitiveClass) == laPrimitiveClass));
  }

  /**
   * Run the projection test "Data Transition" from SubClass
   * 
   * <pre>
   * Expected Result:\
   *               1. SuperClass and SubClass are transitioned to Logical Layer.\
   *               2. String types SuperST and SubST are also transitioned\
   *               3. Property1 of Super Class gets transitioned\
   *               4. Service1 and Parameter are included in SubClass of Physical Layer\
   *               5. Constraint1 is also transitioned from Physical to Physical Layer\
   *               6. Realization Link is created from Logical Layer to Physical layer
   * </pre>
   */
  private void subClassTransition1Test() {
    performDataTransition(Arrays.asList(laSubClass));
    paSuperST = (StringType) paDataPkg.getOwnedDataTypes().get(0);
    assertTrue(NLS.bind(Messages.RealizationError, paSuperST.getName(), laSuperST.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paSuperST) == laSuperST));

    paSubST = (StringType) CodeHelper.getChildTracingElement(paDataPkg, laSubST);
    assertTrue(NLS.bind(Messages.RealizationError, paSubST.getName(), laSubST.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paSubST) == laSubST));

    paSubClass = (Class) CodeHelper.getChildTracingElement(paDataPkg, laSubClass);
    assertNotNull(Messages.NullError, paSubClass);
    assertTrue(NLS.bind(Messages.RealizationError, paSubClass.getName(), laSubClass.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paSubClass) == laSubClass));

    // Service1 and Parameter are included in SubClass of Logical Layer\
    paService1 = (Service) paSubClass.getContainedOperations().get(0);
    assertNotNull(Messages.NullError, paService1);
    assertTrue(NLS.bind(Messages.RealizationError, paService1.getName(), laService1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paService1) == laService1));

    paParameter1 = paService1.getOwnedParameters().get(0);
    assertNotNull(Messages.NullError, paParameter1);
    assertTrue(NLS.bind(Messages.RealizationError, paParameter1.getName(), laParameter1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paParameter1) == laParameter1));
    assertTrue(NLS.bind(Messages.WrongType, paParameter1.getName(), paSuperST.getName()),
        (paParameter1.getType() == paSuperST));

    paService2 = (Service) paSubClass.getContainedOperations().get(1);
    assertNotNull(Messages.NullError, paService2);
    assertTrue(NLS.bind(Messages.RealizationError, paService2.getName(), laService2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paService2) == laService2));

    paSuperClass = (Class) CodeHelper.getChildTracingElement(paDataPkg, laSuperClass);
    assertNotNull(Messages.NullError, paSuperClass);
    assertTrue(NLS.bind(Messages.RealizationError, paSuperClass.getName(), laSuperClass.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paSuperClass) == laSuperClass));

    // Property1 of Super Class gets transitioned\
    paProperty1 = paSuperClass.getContainedProperties().get(0);
    assertNotNull(Messages.NullError, paSuperClass);
    assertTrue(NLS.bind(Messages.RealizationError, paProperty1.getName(), laProperty1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paProperty1) == laProperty1));
    assertTrue(NLS.bind(Messages.WrongType, paProperty1.getName(), paSubST.getName()),
        (paProperty1.getType() == paSubST));

    paConstraint1 = (Constraint) paSubClass.getOwnedConstraints().get(0);
    assertNotNull(Messages.NullError, paConstraint1);
    assertTrue(NLS.bind(Messages.RealizationError, paConstraint1.getName(), laConstraint1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paConstraint1) == laConstraint1));
  }

  /**
   * Run the projection test "Data Transition" from Root Data Pkg
   * 
   * <pre>
   * Expected Result:\
   *             1. No changes occur, and the elements are intact.
   * </pre>
   */
  private void laDataPkgTransitionTest() {
    performDataTransition(Arrays.asList(laDataPkg));
    assertEquals(Messages.ProjectionSizeError, laDataPkg.getOwnedClasses().size(), paDataPkg.getOwnedClasses().size());
    assertEquals(Messages.ProjectionSizeError, laDataPkg.getOwnedDataTypes().size(),
        paDataPkg.getOwnedDataTypes().size());
  }

  /**
   * Run the projection test "Data Transition" from SubClass again after making the changes.
   * 
   * <pre>
   * Make the following changes in the elements\
   *                 1.  Delete Service1\
   *                 2.  Create new Union Union1\
   *                 3.  Create a new Parameter Parameter1 in Service2 of SubClass typed with Union\
   * Expected Result:\
   *                 1.  Service1 in Physical Layer/SubClass still exists but the realization link is removed.\
   *                 2.  Union1 is created in Physical Layer with the association link\
   *                 3.  Service2 is added in Physical Layer/SubClass with Parameter1
   * </pre>
   */
  private void subClassTransition2Test() {
    // Delete Service1
    laService1.destroy();
    // Create new Union Union1\
    // Create a new Parameter Parameter1 in Service2 of SubClass typed with Union\
    laUnion = InformationFactory.eINSTANCE.createUnion("Union1"); //$NON-NLS-1$
    laDataPkg.getOwnedClasses().add(laUnion);

    laParameter2 = InformationFactory.eINSTANCE.createParameter("Parameter2"); //$NON-NLS-1$
    laParameter2.setAbstractType(laUnion);
    laService2.getOwnedParameters().add(laParameter2);
    performDataTransition(Arrays.asList(laSubClass));
    assertNotNull(Messages.NullError, paService1);
    assertTrue(NLS.bind(Messages.RealizationError, paService1.getName(), null),
        (ProjectionTestUtils.getRealizedTargetElement(paService1) == null));

    // _paParameter2 = _paService2.getOwnedParameters().get(0);
    // assertNotNull(Messages.NullError, _paParameter2);
    // assertTrue(NLS.bind(Messages.RealizationError, _paParameter2.getName(), _laParameter2.getName()),
    // (ProjectionTestUtils.getRealizedTargetElement(_paParameter2) == _laParameter2));

    paUnion = ProjectionTestUtils.getRecentlyAddedClass(paDataPkg);
    assertNotNull(Messages.NullError, paUnion);
    assertTrue(NLS.bind(Messages.RealizationError, paUnion.getName(), laUnion.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paUnion) == laUnion));
  }

}
