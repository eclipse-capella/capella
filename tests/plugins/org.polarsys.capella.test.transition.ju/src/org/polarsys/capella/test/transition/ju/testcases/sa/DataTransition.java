/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.testcases.sa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
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
import org.polarsys.capella.test.transition.ju.CodeHelper;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelCtxLa;

/**
 * Projection Tests on "Data Transition" from System Analysis to Logical Layer
 * <P>
 * This is done with the model "CTX-LA-Projection". The model is created as explained below.
 * 
 * <pre>
 *     Model Used: CTX-LA-Projection
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
 * 
 */
public class DataTransition extends TopDownTransitionTestCase {

  // Data Pkg
  private DataPkg _ctxDataPkg;
  private DataPkg _saMyDataPkg;

  // Classes
  private Class _ctxPrimitiveClass;
  private Class _ctxSuperClass;
  private Class _ctxSubClass;
  private Class _ctxUnion;

  private org.polarsys.capella.core.data.information.Collection _saMyCollection;
  private Service _saMyService;
  private Parameter _saMyParameter;
  private BooleanType _saMyBooleanType;
  private LiteralBooleanValue _saFalseValue;
  private LiteralBooleanValue _saTrueValue;
  private StringType _ctxSuperST;
  private StringType _ctxSubST;

  private Property _ctxProperty1;
  private Service _ctxService1;
  private Parameter _ctxParameter1;
  private Service _ctxService2;
  private Parameter _ctxParameter2;
  private Constraint _ctxConstraint1;

  private DataPkg _laDataPkg;
  private DataPkg _laMyDataPkg;
  // Classes
  private Class _laPrimitiveClass;
  private Class _laSuperClass;
  private Class _laSubClass;
  private Class _laUnion;

  private org.polarsys.capella.core.data.information.Collection _laMyCollection;
  private Service _laMyService;
  private Parameter _laMyParameter;
  private BooleanType _laMyBooleanType;
  private LiteralBooleanValue _laFalseValue;
  private LiteralBooleanValue _laTrueValue;
  private StringType _laSuperST;
  private StringType _laSubST;

  private Property _laProperty1;
  private Service _laService1;
  private Parameter _laParameter1;
  private Service _laService2;
  private Parameter _laParameter2;
  private Constraint _laConstraint1;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  public void performTest() throws Exception {
    // Assign the objects
    _ctxDataPkg = (DataPkg) getObject(ModelCtxLa.ctxDataPkgId);
    _ctxPrimitiveClass = (Class) getObject(ModelCtxLa.primitiveClassId);
    _ctxSuperClass = (Class) getObject(ModelCtxLa.superClassId);
    _ctxSubClass = (Class) getObject(ModelCtxLa.subClassId);
    _ctxSuperST = (StringType) getObject(ModelCtxLa.superSTId);
    _ctxSubST = (StringType) getObject(ModelCtxLa.subSTId);
    _ctxProperty1 = (Property) getObject(ModelCtxLa.property1Id);
    _ctxService1 = (Service) getObject(ModelCtxLa.service1Id);
    _ctxParameter1 = (Parameter) getObject(ModelCtxLa.parameter1Id);
    _ctxService2 = (Service) getObject(ModelCtxLa.service2Id);
    _ctxConstraint1 = (Constraint) getObject(ModelCtxLa.constraint1Id);
    _laDataPkg = (DataPkg) getObject(ModelCtxLa.laDataPkgId);
    _saMyDataPkg = (DataPkg) getObject(ModelCtxLa.saMyDataPkgId);
    _saMyCollection = (org.polarsys.capella.core.data.information.Collection) getObject(
        ModelCtxLa.saMyCollectionId);
    _saMyService = (Service) getObject(ModelCtxLa.saMyServiceId);
    _saMyParameter = (Parameter) getObject(ModelCtxLa.saMyParameterId);
    _saMyBooleanType = (BooleanType) getObject(ModelCtxLa.saMyBooleanTypeId);
    _saFalseValue = (LiteralBooleanValue) getObject(ModelCtxLa.saFalseValueId);
    _saTrueValue = (LiteralBooleanValue) getObject(ModelCtxLa.saTrueValueId);

    performTest1();
    performTest2();
    performTest3();
    performTest4();
    performTest5();
  }

  /**
   * Run the projection test "Data Transition" from MyCollection
   * 
   * <pre>
   * Expected Result:\
   *               1. MyCollection, MyDataPkg and MyBooleanType are transitioned to LA Layer.\
   *               2. Boolean Literals of MyBooleanType are also transitioned\
   *               3. Service1 and Parameter are included in MyCollection of LA Layer\
   *               4. Realization Link is created from LA Layer to Ctx layer
   * </pre>
   */

  public void performTest1() throws Exception {
    performDataTransition(Collections.singletonList(_saMyCollection));
    // MyDataPkg
    _laMyDataPkg = _laDataPkg.getOwnedDataPkgs().get(0);
    assertNotNull(Messages.NullError, _laMyDataPkg);
    assertTrue(NLS.bind(Messages.RealizationError, _laMyDataPkg.getName(), _saMyDataPkg.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laMyDataPkg) == _saMyDataPkg));

    // MyBooleanType
    _laMyBooleanType = (BooleanType) _laMyDataPkg.getOwnedDataTypes().get(0);
    assertNotNull(Messages.NullError, _laMyBooleanType);
    assertTrue(NLS.bind(Messages.RealizationError, _laMyBooleanType.getName(), _saMyBooleanType.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laMyBooleanType) == _saMyBooleanType));

    _laFalseValue = _laMyBooleanType.getOwnedLiterals().get(0);
    assertNotNull(Messages.NullError, _laFalseValue);
    assertTrue(NLS.bind(Messages.RealizationError, _laFalseValue.getName(), _saFalseValue.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laFalseValue) == _saFalseValue));
    assertFalse(NLS.bind(Messages.WrongValue, _laFalseValue.getName()),
        _laFalseValue.isValue());

    _laTrueValue = _laMyBooleanType.getOwnedLiterals().get(1);
    assertNotNull(Messages.NullError, _laTrueValue);
    assertTrue(NLS.bind(Messages.RealizationError, _laTrueValue.getName(), _saTrueValue.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laTrueValue) == _saTrueValue));
    assertTrue(NLS.bind(Messages.WrongValue, _laTrueValue.getName()),
        _laTrueValue.isValue());

    // MyCollection
    _laMyCollection = _laDataPkg.getOwnedCollections().get(0);
    assertNotNull(Messages.NullError, _laMyCollection);
    assertTrue(NLS.bind(Messages.RealizationError, _laMyCollection.getName(), _saMyCollection.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laMyCollection) == _saMyCollection));
    assertTrue(NLS.bind(Messages.WrongType, _laMyCollection.getName(), _laMyBooleanType.getName()),
        (_laMyCollection.getType() == _laMyBooleanType));

    // MyService and MyParameter are included in MyCollection of LA Layer\
    _laMyService = (Service) _laMyCollection.getContainedOperations().get(0);
    assertNotNull(Messages.NullError, _laMyService);
    assertTrue(NLS.bind(Messages.RealizationError, _laMyService.getName(), _saMyService.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laMyService) == _saMyService));

    _laMyParameter = _laMyService.getOwnedParameters().get(0);
    assertNotNull(Messages.NullError, _laMyParameter);
    assertTrue(NLS.bind(Messages.RealizationError, _laMyParameter.getName(), _saMyParameter.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laMyParameter) == _saMyParameter));
    assertTrue(NLS.bind(Messages.WrongType, _laMyParameter.getName(), _laMyBooleanType.getName()),
        (_laMyParameter.getType() == _laMyBooleanType));

  }

  /**
   * Run the projection test "Data Transition" from Primitive Class1
   * 
   * <pre>
   * Expected Result:\
   *             1. Primitive Class1 is transitioned to Ctx Layer.\
   *             2. Realization Link is created from Ctx Layer to Logical layer
   * </pre>
   */

  public void performTest2() throws Exception {
    performDataTransition(Collections.singletonList(_ctxPrimitiveClass));
    _laPrimitiveClass = ProjectionTestUtils.getRecentlyAddedClass(_laDataPkg);
    assertNotNull(Messages.NullError, _laPrimitiveClass);

    assertTrue(NLS.bind(Messages.RealizationError, _laPrimitiveClass.getName(), _ctxPrimitiveClass.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laPrimitiveClass) == _ctxPrimitiveClass));
  }

  /**
   * Run the projection test "Data Transition" from SubClass
   * 
   * <pre>
   * Expected Result:\
   *               1. SuperClass and SubClass are transitioned to Logical Layer.\
   *               2.  String types SuperST and SubST are also transitioned\
   *               3.  Property1 of Super Class gets transitioned\
   *               4.  Service1 and Parameter are included in SubClass of Logical Layer\
   *               5.  Constraint1 is also transitioned from Ctx to Logical Layer\
   *               6.  Realization Link is created from Logical Layer to Ctx layer
   * </pre>
   */

  public void performTest3() throws Exception {
    performDataTransition(Collections.singletonList(_ctxSubClass));

    _laSuperST = (StringType) _laDataPkg.getOwnedDataTypes().get(0);
    assertTrue(NLS.bind(Messages.RealizationError, _laSuperST.getName(), _ctxSuperST.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laSuperST) == _ctxSuperST));

    _laSubST = (StringType) _laDataPkg.getOwnedDataTypes().get(1);
    assertTrue(NLS.bind(Messages.RealizationError, _laSubST.getName(), _ctxSubST.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laSubST) == _ctxSubST));

    _laSubClass = (Class) CodeHelper.getChildTracingElement(_laDataPkg, _ctxSubClass);
    assertNotNull(Messages.NullError, _laSubClass);
    assertTrue(NLS.bind(Messages.RealizationError, _laSubClass.getName(), _ctxSubClass.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laSubClass) == _ctxSubClass));

    // Service1 and Parameter are included in SubClass of Ctx Layer\
    _laService1 = (Service) _laSubClass.getContainedOperations().get(0);
    assertNotNull(Messages.NullError, _laService1);
    assertTrue(NLS.bind(Messages.RealizationError, _laService1.getName(), _ctxService1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laService1) == _ctxService1));

    _laParameter1 = _laService1.getOwnedParameters().get(0);
    assertNotNull(Messages.NullError, _laParameter1);
    assertTrue(NLS.bind(Messages.RealizationError, _laParameter1.getName(), _ctxParameter1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laParameter1) == _ctxParameter1));
    assertTrue(NLS.bind(Messages.WrongType, _laParameter1.getName(), _laSuperST.getName()),
        (_laParameter1.getType() == _laSuperST));

    _laService2 = (Service) _laSubClass.getContainedOperations().get(1);
    assertNotNull(Messages.NullError, _laService2);
    assertTrue(NLS.bind(Messages.RealizationError, _laService2.getName(), _ctxService2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laService2) == _ctxService2));

    _laSuperClass = (Class) CodeHelper.getChildTracingElement(_laDataPkg, _ctxSuperClass);
    assertNotNull(Messages.NullError, _laSuperClass);
    assertTrue(NLS.bind(Messages.RealizationError, _laSuperClass.getName(), _ctxSuperClass.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laSuperClass) == _ctxSuperClass));

    // Property1 of Super Class gets transitioned\
    _laProperty1 = _laSuperClass.getContainedProperties().get(0);
    assertNotNull(Messages.NullError, _laSuperClass);
    assertTrue(NLS.bind(Messages.RealizationError, _laProperty1.getName(), _ctxProperty1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laProperty1) == _ctxProperty1));
    assertTrue(NLS.bind(Messages.WrongType, _laProperty1.getName(), _laSubST.getName()),
        (_laProperty1.getType() == _laSubST));

    _laConstraint1 = (Constraint) _laSubClass.getOwnedConstraints().get(0);
    assertNotNull(Messages.NullError, _laConstraint1);
    assertTrue(NLS.bind(Messages.RealizationError, _laConstraint1.getName(), _ctxConstraint1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laConstraint1) == _ctxConstraint1));
  }

  /**
   * Run the projection test "Data Transition" from Root Data Pkg
   * 
   * <pre>
   * Expected Result:\
   *             1. No changes occur, and the elements are intact.
   * </pre>
   */

  public void performTest4() throws Exception {
    performDataTransition(Collections.singletonList(_ctxDataPkg));
    assertEquals(Messages.ProjectionSizeError, _ctxDataPkg.getOwnedClasses().size(),
        _laDataPkg.getOwnedClasses().size());
    assertEquals(Messages.ProjectionSizeError, _ctxDataPkg.getOwnedDataTypes().size(),
        _laDataPkg.getOwnedDataTypes().size());
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
   *                 1. Service1 in Logical Layer/SubClass still exists but the realization link is removed.\
   *                 2.  Union1 is created in Logical Layer with the association link\
   *                 3.  Service2 is added in Logical Layer/SubClass with Parameter1
   * </pre>
   */

  public void performTest5() throws Exception {
    // Delete Service1
    // Create new Union Union1\
    // Create a new Parameter Parameter1 in Service2 of SubClass typed with Union\
    getExecutionManager(_ctxDataPkg).execute(new AbstractReadWriteCommand() {

      public void run() {
        _ctxService1.destroy();
        _ctxUnion = InformationFactory.eINSTANCE.createUnion("Union1"); //$NON-NLS-1$
        _ctxDataPkg.getOwnedClasses().add(_ctxUnion);

        _ctxParameter2 = InformationFactory.eINSTANCE.createParameter("Parameter2"); //$NON-NLS-1$
        _ctxParameter2.setAbstractType(_ctxUnion);
        _ctxService2.getOwnedParameters().add(_ctxParameter2);
      }
    });

    performDataTransition(Collections.singletonList(_ctxSubClass));

    assertNotNull(Messages.NullError, _laService1);
    assertTrue(NLS.bind(Messages.RealizationError, _laService1.getName(), null),
        (ProjectionTestUtils.getRealizedTargetElement(_laService1) == null));

    _laParameter2 = _laService2.getOwnedParameters().get(0);
    assertNotNull(Messages.NullError, _laParameter2);
    assertTrue(NLS.bind(Messages.RealizationError, _laParameter2.getName(), _ctxParameter2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laParameter2) == _ctxParameter2));

    _laUnion = ProjectionTestUtils.getRecentlyAddedClass(_laDataPkg);
    assertNotNull(Messages.NullError, _laUnion);
    assertTrue(NLS.bind(Messages.RealizationError, _laUnion.getName(), _ctxUnion.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laUnion) == _ctxUnion));
  }
}
