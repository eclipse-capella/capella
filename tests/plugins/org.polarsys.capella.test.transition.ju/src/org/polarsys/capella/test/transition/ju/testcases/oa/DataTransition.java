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
package org.polarsys.capella.test.transition.ju.testcases.oa;

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
import org.polarsys.capella.test.transition.ju.model.ModelOaSa;

/**
 * Projection Tests on "Data Transition" from Operational Analysis to System Analysis
 * <P>
 * This is done with the model "OA-SA-Projection". The model is created as explained below.
 * 
 * <pre>
 *     Model Used: OA-SA-Projection
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
  private DataPkg _oaDataPkg;
  private DataPkg _oaMyDataPkg;

  // Classes
  private Class _oaPrimitiveClass;
  private Class _oaSuperClass;
  private Class _oaSubClass;
  private Class _oaUnion;

  private org.polarsys.capella.core.data.information.Collection _oaMyCollection;
  private Service _oaMyService;
  private Parameter _oaMyParameter;
  private BooleanType _oaMyBooleanType;
  private LiteralBooleanValue _oaFalseValue;
  private LiteralBooleanValue _oaTrueValue;
  private StringType _oaSuperST;
  private StringType _oaSubST;

  private Property _oaProperty1;
  private Service _oaService1;
  private Parameter _oaParameter1;
  private Service _oaService2;
  private Parameter _oaParameter2;
  private Constraint _oaConstraint1;

  private DataPkg _saDataPkg;
  private DataPkg _saMyDataPkg;
  // Classes
  private Class _saPrimitiveClass;
  private Class _saSuperClass;
  private Class _saSubClass;
  private Class _saUnion;

  private org.polarsys.capella.core.data.information.Collection _saMyCollection;
  private Service _saMyService;
  private Parameter _saMyParameter;
  private BooleanType _saMyBooleanType;
  private LiteralBooleanValue _saFalseValue;
  private LiteralBooleanValue _saTrueValue;
  private StringType _saSuperST;
  private StringType _saSubST;

  private Property _saProperty1;
  private Service _saService1;
  private Parameter _saParameter1;
  private Service _saService2;
  private Parameter _saParameter2;
  private Constraint _saConstraint1;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  public void performTest() throws Exception {
    // Assign the objects
    _oaDataPkg = (DataPkg) getObject(ModelOaSa.oaDataPkgId);
    _oaPrimitiveClass = (Class) getObject(ModelOaSa.primitiveClassId);
    _oaSuperClass = (Class) getObject(ModelOaSa.superClassId);
    _oaSubClass = (Class) getObject(ModelOaSa.subClassId);
    _oaSuperST = (StringType) getObject(ModelOaSa.superSTId);
    _oaSubST = (StringType) getObject(ModelOaSa.subSTId);
    _oaProperty1 = (Property) getObject(ModelOaSa.property1Id);
    _oaService1 = (Service) getObject(ModelOaSa.service1Id);
    _oaParameter1 = (Parameter) getObject(ModelOaSa.parameter1Id);
    _oaService2 = (Service) getObject(ModelOaSa.service2Id);
    _oaConstraint1 = (Constraint) getObject(ModelOaSa.constraint1Id);
    _saDataPkg = (DataPkg) getObject(ModelOaSa.saDataPkgId);
    _oaMyDataPkg = (DataPkg) getObject(ModelOaSa.oaMyDataPkgId);
    _oaMyCollection = (org.polarsys.capella.core.data.information.Collection) getObject(
        ModelOaSa.oaMyCollectionId);
    _oaMyService = (Service) getObject(ModelOaSa.oaMyServiceId);
    _oaMyParameter = (Parameter) getObject(ModelOaSa.oaMyParameterId);
    _oaMyBooleanType = (BooleanType) getObject(ModelOaSa.oaMyBooleanTypeId);
    _oaFalseValue = (LiteralBooleanValue) getObject(ModelOaSa.oaFalseValueId);
    _oaTrueValue = (LiteralBooleanValue) getObject(ModelOaSa.oaTrueValueId);

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
   *               1. MyCollection, MyDataPkg and MyBooleanType are transitioned to Ctx Layer.\
   *               2. Boolean Literals of MyBooleanType are also transitioned\
   *               3. Service1 and Parameter are included in MyCollection of Ctx Layer\
   *               4. Realization Link is created from Ctx Layer to OA layer
   * </pre>
   */

  public void performTest1() throws Exception {
    performDataTransition(Collections.singletonList(_oaMyCollection));
    // MyDataPkg
    _saMyDataPkg = _saDataPkg.getOwnedDataPkgs().get(1);
    assertNotNull(Messages.NullError, _saMyDataPkg);
    assertTrue(NLS.bind(Messages.RealizationError, _saMyDataPkg.getName(), _oaMyDataPkg.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saMyDataPkg) == _oaMyDataPkg));

    // MyBooleanType
    _saMyBooleanType = (BooleanType) _saMyDataPkg.getOwnedDataTypes().get(0);
    assertNotNull(Messages.NullError, _saMyBooleanType);
    assertTrue(NLS.bind(Messages.RealizationError, _saMyBooleanType.getName(), _oaMyBooleanType.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saMyBooleanType) == _oaMyBooleanType));

    _saFalseValue = _saMyBooleanType.getOwnedLiterals().get(0);
    assertNotNull(Messages.NullError, _saFalseValue);
    assertTrue(NLS.bind(Messages.RealizationError, _saFalseValue.getName(), _oaFalseValue.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saFalseValue) == _oaFalseValue));
    assertFalse(NLS.bind(Messages.WrongValue, _saFalseValue.getName()),
        _saFalseValue.isValue());

    _saTrueValue = _saMyBooleanType.getOwnedLiterals().get(1);
    assertNotNull(Messages.NullError, _saTrueValue);
    assertTrue(NLS.bind(Messages.RealizationError, _saTrueValue.getName(), _oaTrueValue.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saTrueValue) == _oaTrueValue));
    assertTrue(NLS.bind(Messages.WrongValue, _saTrueValue.getName()),
        _saTrueValue.isValue());

    // MyCollection
    _saMyCollection = _saDataPkg.getOwnedCollections().get(0);
    assertNotNull(Messages.NullError, _saMyCollection);
    assertTrue(NLS.bind(Messages.RealizationError, _saMyCollection.getName(), _oaMyCollection.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saMyCollection) == _oaMyCollection));
    assertTrue(NLS.bind(Messages.WrongType, _saMyCollection.getName(), _saMyBooleanType.getName()),
        (_saMyCollection.getType() == _saMyBooleanType));

    // MyService and MyParameter are included in MyCollection of Ctx Layer\
    _saMyService = (Service) _saMyCollection.getContainedOperations().get(0);
    assertNotNull(Messages.NullError, _saMyService);
    assertTrue(NLS.bind(Messages.RealizationError, _saMyService.getName(), _oaMyService.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saMyService) == _oaMyService));

    _saMyParameter = _saMyService.getOwnedParameters().get(0);
    assertNotNull(Messages.NullError, _saMyParameter);
    assertTrue(NLS.bind(Messages.RealizationError, _saMyParameter.getName(), _oaMyParameter.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saMyParameter) == _oaMyParameter));
    assertTrue(NLS.bind(Messages.WrongType, _saMyParameter.getName(), _saMyBooleanType.getName()),
        (_saMyParameter.getType() == _saMyBooleanType));

  }

  /**
   * Run the projection test "Data Transition" from Primitive Class1
   * 
   * <pre>
   * Expected Result:\
   *             1. Primitive Class1 is transitioned to Ctx Layer.\
   *             2. Realization Link is created from Ctx Layer to OA layer
   * </pre>
   */

  public void performTest2() throws Exception {
    performDataTransition(Collections.singletonList(_oaPrimitiveClass));

    _saPrimitiveClass = ProjectionTestUtils.getRecentlyAddedClass(_saDataPkg);
    assertNotNull(Messages.NullError, _saPrimitiveClass);

    assertTrue(NLS.bind(Messages.RealizationError, _saPrimitiveClass.getName(), _oaPrimitiveClass.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saPrimitiveClass) == _oaPrimitiveClass));
  }

  /**
   * Run the projection test "Data Transition" from SubClass
   * 
   * <pre>
   * Expected Result:\
   *               1. SuperClass and SubClass are transitioned to Ctx Layer.\
   *               2. String types SuperST and SubST are also transitioned\
   *               3. Property1 of Super Class gets transitioned\
   *               4. Service1 and Parameter are included in SubClass of Ctx Layer\
   *               5. Constraint1 is also transitioned from OA to Ctx Layer\
   *               6. Realization Link is created from Ctx Layer to OA layer
   * </pre>
   */

  public void performTest3() throws Exception {

    performDataTransition(Collections.singletonList(_oaSubClass));
    _saSuperST = (StringType) _saDataPkg.getOwnedDataTypes().get(0); // WTF?????
    assertTrue(NLS.bind(Messages.RealizationError, _saSuperST.getName(), _oaSuperST.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saSuperST) == _oaSuperST));

    _saSubST = (StringType) _saDataPkg.getOwnedDataTypes().get(1);
    assertTrue(NLS.bind(Messages.RealizationError, _saSubST.getName(), _oaSubST.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saSubST) == _oaSubST));

    _saSubClass = (Class) CodeHelper.getChildTracingElement(_saDataPkg, _oaSubClass);
    assertNotNull(Messages.NullError, _saSubClass);
    assertTrue(NLS.bind(Messages.RealizationError, _saSubClass.getName(), _oaSubClass.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saSubClass) == _oaSubClass));

    // Service1 and Parameter are included in SubClass of Ctx Layer\
    _saService1 = (Service) _saSubClass.getContainedOperations().get(0);
    assertNotNull(Messages.NullError, _saService1);
    assertTrue(NLS.bind(Messages.RealizationError, _saService1.getName(), _oaService1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saService1) == _oaService1));

    _saParameter1 = _saService1.getOwnedParameters().get(0);
    assertNotNull(Messages.NullError, _saParameter1);
    assertTrue(NLS.bind(Messages.RealizationError, _saParameter1.getName(), _oaParameter1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saParameter1) == _oaParameter1));
    assertTrue(NLS.bind(Messages.WrongType, _saParameter1.getName(), _saSuperST.getName()),
        (_saParameter1.getType() == _saSuperST));

    _saService2 = (Service) _saSubClass.getContainedOperations().get(1);
    assertNotNull(Messages.NullError, _saService2);
    assertTrue(NLS.bind(Messages.RealizationError, _saService2.getName(), _oaService2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saService2) == _oaService2));

    _saSuperClass = (Class) CodeHelper.getChildTracingElement(_saDataPkg, _oaSuperClass);
    assertNotNull(Messages.NullError, _saSuperClass);
    assertTrue(NLS.bind(Messages.RealizationError, _saSuperClass.getName(), _oaSuperClass.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saSuperClass) == _oaSuperClass));

    // Property1 of Super Class gets transitioned\
    _saProperty1 = _saSuperClass.getContainedProperties().get(0);
    assertNotNull(Messages.NullError, _saSuperClass);
    assertTrue(NLS.bind(Messages.RealizationError, _saProperty1.getName(), _oaProperty1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saProperty1) == _oaProperty1));
    assertTrue(NLS.bind(Messages.WrongType, _saProperty1.getName(), _saSubST.getName()),
        (_saProperty1.getType() == _saSubST));

    _saConstraint1 = (Constraint) _saSubClass.getOwnedConstraints().get(0);
    assertNotNull(Messages.NullError, _saConstraint1);
    assertTrue(NLS.bind(Messages.RealizationError, _saConstraint1.getName(), _oaConstraint1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saConstraint1) == _oaConstraint1));
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
    performDataTransition(Collections.singletonList(_oaDataPkg));
    assertEquals(Messages.ProjectionSizeError, _oaDataPkg.getOwnedClasses().size(),
        _saDataPkg.getOwnedClasses().size());
    assertEquals(Messages.ProjectionSizeError, _oaDataPkg.getOwnedDataTypes().size(),
        _saDataPkg.getOwnedDataTypes().size());
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
   *                 1.  Service1 in Ctx Layer/SubClass still exists but the realization link is removed.\
   *                 2.  Union1 is created in Ctx Layer with the association link\
   *                 3.  Service2 is added in Ctx Layer/SubClass with Parameter1
   * </pre>
   */

  public void performTest5() throws Exception {
    // Delete Service1
    getExecutionManager(_oaDataPkg).execute(new AbstractReadWriteCommand() {

      public void run() {
        _oaService1.destroy();
        _oaUnion = InformationFactory.eINSTANCE.createUnion("Union1"); //$NON-NLS-1$
        _oaDataPkg.getOwnedClasses().add(_oaUnion);

        _oaParameter2 = InformationFactory.eINSTANCE.createParameter("Parameter2"); //$NON-NLS-1$
        _oaParameter2.setAbstractType(_oaUnion);
        _oaService2.getOwnedParameters().add(_oaParameter2);
      }
    });

    performDataTransition(Collections.singletonList(_oaSubClass));

    assertNotNull(Messages.NullError, _saService1);
    assertTrue(NLS.bind(Messages.RealizationError, _saService1.getName(), null),
        (ProjectionTestUtils.getRealizedTargetElement(_saService1) == null));

    _saParameter2 = _saService2.getOwnedParameters().get(0);
    assertNotNull(Messages.NullError, _saParameter2);
    assertTrue(NLS.bind(Messages.RealizationError, _saParameter2.getName(), _oaParameter2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saParameter2) == _oaParameter2));

    _saUnion = ProjectionTestUtils.getRecentlyAddedClass(_saDataPkg);
    assertNotNull(Messages.NullError, _saUnion);
    assertTrue(NLS.bind(Messages.RealizationError, _saUnion.getName(), _oaUnion.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saUnion) == _oaUnion));
  }
}
