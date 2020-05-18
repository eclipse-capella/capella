/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.odesign.typereferencename;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateLineTool;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;

/**
 * Utilitary functions to create tests on Type Name and Reference Name
 */
public class TypeReferenceNameUtils {
  /**
   * create a Type Name test from a diagram, a tool and a type name
   * 
   * @param diagram_p,
   *          the RepresentationDescription containing the type name to test
   * @param tool_p,
   *          the AbstractToolDescription contained in diagram_p and containing the type name to test
   * @param typeName_p,
   *          the String describing the Type Name to test
   */
  public static CheckTypeNameTest getTypeNameTest(final RepresentationDescription diagram_p,
      final AbstractToolDescription tool_p, final String typeName_p) {
    // create a test to check that the type name exists
    CheckTypeNameTest test = new CheckTypeNameTest(typeName_p);
    return test;
  }

  /**
   * create a Reference Name test from a diagram, a tool and a reference name
   * 
   * @param diagram_p,
   *          the RepresentationDescription containing the reference name to test
   * @param tool_p,
   *          the AbstractToolDescription contained in diagram_p and containing the reference name to test
   * @param refName_p,
   *          the String describing the Type Name to test
   */
  public static CheckReferenceNameTest getReferenceNameTest(final RepresentationDescription diagram_p,
      final AbstractToolDescription tool_p, final String refName_p) {
    // create a test to check that the Reference Name
    CheckReferenceNameTest refNameTest = new CheckReferenceNameTest(refName_p);
    return refNameTest;
  }

  /**
   * get the first operation of a given tool
   * 
   * @param tool_p,
   *          the AbstractToolDescription to test
   */
  public static ModelOperation getFistOperation(AbstractToolDescription tool_p) {
    if (tool_p instanceof ToolDescription) {
      return ((ToolDescription) tool_p).getInitialOperation().getFirstModelOperations();
    }
    if (tool_p instanceof ContainerCreationDescription) {
      return ((ContainerCreationDescription) tool_p).getInitialOperation().getFirstModelOperations();
    }
    if (tool_p instanceof NodeCreationDescription) {
      return ((NodeCreationDescription) tool_p).getInitialOperation().getFirstModelOperations();
    }
    if (tool_p instanceof EdgeCreationDescription) {
      return ((EdgeCreationDescription) tool_p).getInitialOperation().getFirstModelOperations();
    }
    if (tool_p instanceof CreateLineTool) {
      return ((CreateLineTool) tool_p).getFirstModelOperation();
    }
    if (tool_p instanceof CreateCellTool) {
      return ((CreateCellTool) tool_p).getFirstModelOperation();
    }
    return null;
  }

  /**
   * create a Type Name test and a Reference Name test for a CreateInstance operation
   * 
   * @param diagram_p,
   *          the RepresentationDescription containing the operation
   * @param tool_p,
   *          the AbstractToolDescription contained in diagram_p and containing the operation
   * @param operation_p,
   *          the ContainerModelOperation to test
   */
  public static List<BasicTestArtefact> getCreateInstanceTests(RepresentationDescription diagram_p,
      AbstractToolDescription tool_p, ContainerModelOperation operation_p) {

    // the list of created tests
    ArrayList<BasicTestArtefact> testsList = new ArrayList<BasicTestArtefact>();

    // create new tests if operation_p is an instance of CreateInstance
    if (operation_p instanceof CreateInstance) {
      CreateInstance createInstance = (CreateInstance) operation_p;
      final String typeName = createInstance.getTypeName();
      final String refName = createInstance.getReferenceName();
      CheckTypeNameTest typeNameTest = getTypeNameTest(diagram_p, tool_p, typeName);
      CheckReferenceNameTest referenceNameTest = getReferenceNameTest(diagram_p, tool_p, refName);

      testsList.add(typeNameTest);
      testsList.add(referenceNameTest);
    }
    return testsList;
  }

  /**
   * find the instance of CreateInstance from an operation and its sub operations (diagram tool) and create related Type
   * Name test and Reference Name test
   * 
   * @param diagram_p,
   *          the RepresentationDescription containing the operation
   * @param tool_p,
   *          the AbstractToolDescription contained in diagram_p and containing the operation
   * @param operation_p,
   *          the ContainerModelOperation to test
   * @param tests_p,
   *          a tests list to add the new tests
   */
  public static void findCreateInstance(RepresentationDescription diagram_p, AbstractToolDescription tool_p,
      ModelOperation operation_p, List<BasicTestArtefact> tests_p) {
    // if the input operation is a CreateInstance
    if (operation_p instanceof CreateInstance) {
      // create tests and add them to the input tests list
      List<BasicTestArtefact> createInstanceTests = getCreateInstanceTests(diagram_p, tool_p,
          (CreateInstance) operation_p);
      tests_p.addAll(createInstanceTests);
    }

    if (operation_p instanceof ContainerModelOperation) {
      ContainerModelOperation cmo = (ContainerModelOperation) operation_p;
      // get the sub operations to find CreateInstance in the sub operations
      EList<ModelOperation> subModelOperations = cmo.getSubModelOperations();
      Iterator<ModelOperation> iterator = subModelOperations.iterator();
      while (iterator.hasNext()) {
        ModelOperation next = iterator.next();
        // if a sub operation is a CreateInstance, create tests
        if (next instanceof CreateInstance) {
          CreateInstance createInstance = (CreateInstance) next;
          List<BasicTestArtefact> createInstanceTests = getCreateInstanceTests(diagram_p, tool_p, createInstance);
          tests_p.addAll(createInstanceTests);

        } else {
          // otherwise check the sub operations
          findCreateInstance(diagram_p, tool_p, next, tests_p);
        }
      }
    }
  }

  /**
   * find the instance of CreateInstance from table lines and create related Type Name test and Reference Name test
   * 
   * @param diagram_p,
   *          the RepresentationDescription containing the operation
   * @param lines_p,
   *          the lines of the table to test
   * @param tests_p,
   *          a tests list to add the new tests
   */
  public static void findCreateInstanceInLineMappings(RepresentationDescription diagram_p, List<LineMapping> lines_p,
      List<BasicTestArtefact> tests_p) {
    for (LineMapping lineMapping : lines_p) {
      EList<CreateLineTool> create = lineMapping.getCreate();
      for (CreateLineTool current : create) {
        ModelOperation fistOperation = getFistOperation(current);
        findCreateInstance(diagram_p, current, fistOperation, tests_p);

      }
    }
  }

  /**
   * find the instance of CreateInstance from table intersections and create related Type Name test and Reference Name
   * test
   * 
   * @param diagram_p,
   *          the RepresentationDescription containing the operation
   * @param intersections_p,
   *          the intersections of the table to test
   * @param tests_p,
   *          a tests list to add the new tests
   */
  public static void findCreateInstanceInIntersectionMappings(RepresentationDescription diagram_p,
      List<IntersectionMapping> intersections_p, List<BasicTestArtefact> tests_p) {
    for (IntersectionMapping intersectionMapping : intersections_p) {
      CreateCellTool create = intersectionMapping.getCreate();
      ModelOperation fistOperation = getFistOperation(create);
      findCreateInstance(diagram_p, create, fistOperation, tests_p);

    }
  }
}
