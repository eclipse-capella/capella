/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.cdb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import junit.framework.Test;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramCreationExecutionContext;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramOpenExecutionContext;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramToolExecutionContext;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.ITransfertWizardResult;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.AbstractToolStep;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType_Enum;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;

/**
 *
 */
public class CDBCommunicationTestSuite extends AbstractDiagramTestCase {

  //
  // Target object id, useful
  //
  private String _rootDataPkgId = "fdcf745f-cad8-49d5-b65f-6591dafd95fb";
  private String _class1Id = "8b8cd52d-8605-4013-9077-219d2949a035";
  private String _class2Id = "7c6c5883-5f15-4890-a693-bb5818adce81";

  // Classes
  private Class _class1;
  private Class _class2;

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    loadSemanticObjects(getSessionForTestModel(getRequiredTestModel()),
        Arrays.asList(_rootDataPkgId, _class1Id, _class2Id));

    // Initialize the target objects
    _class1 = (Class) getSemanticObjectMap().get(_class1Id);
    _class2 = (Class) getSemanticObjectMap().get(_class2Id);
  }

  @Override
  protected void tearDown() throws Exception {
    // Classes
    _class1 = null;
    _class2 = null;

    super.tearDown();
  }

  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    EObject semanticElement = getSemanticObjectMap().get(_rootDataPkgId);

    // Let's create the new diagram of type Class Diagram Blank
    CreateDiagramStep createDiagram = new CreateDiagramStep(
        new DiagramCreationExecutionContext(session, semanticElement, CDBCommunicationMessages.cdbDiagramName,
            DiagramHelper.getMatchingRepresentationDescription(session, semanticElement, DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME)));
    createDiagram.run();

    DDiagram diagram = (DDiagram) createDiagram.getResult();

    // OPEN DIAGRAM
    OpenDiagramStep openDiagram = new OpenDiagramStep(new DiagramOpenExecutionContext(session, diagram));
    openDiagram.run();

    // ////////////////////INSERT CLASSES TESTS///////////////////////

    // Insert Class1 and Class2 in the diagram
    // Class1 and Class2 are visible in the diagram
    AbstractToolStep executeToolInsertTypes = new AbstractToolStep(new DiagramToolExecutionContext(session, diagram, IToolNameConstants.TOOL_CDB_INSERT_REMOVE_TYPE)) {
      /**
       * @see org.polarsys.capella.test.diagram.common.ju.steps.AbstractExecuteToolCmdStep.tool.AbstractExecuteToolCmdTest#preTestRun()
       */
      @Override
      protected void preRunTest() {
        ITransfertWizardResult op = new ITransfertWizardResult() {

          @SuppressWarnings({ "unchecked", "synthetic-access", "rawtypes" })
          public Object getResult(java.util.Collection<? extends EObject> selections_p, Map<String, Object> parameters_p) {
            List list = new ArrayList();
            list.add(_class1);
            list.add(_class2);
            return list;
          }
        };

        HeadlessResultOpProvider.INSTANCE.setCurrentOp(op);
        super.preRunTest();
      }

      /**
       * @see org.polarsys.capella.test.diagram.common.ju.steps.AbstractExecuteToolCmdStep.tool.AbstractExecuteToolCmdTest#initToolArguments()
       */
      protected void initToolArguments() {
        EObject container = ((DiagramToolExecutionContext) getExecutionContext()).getDiagram();
        _toolWrapper.setArgumentValue(ArgumentType_Enum.CONTAINER, container);
      }

      /**
       * @see org.polarsys.capella.test.common.AbstractExtendedTest#postTestRun()
       */
      @SuppressWarnings("synthetic-access")
      @Override
      protected void postRunTest() {
        super.postRunTest();
        List<EObject> list = new ArrayList<EObject>();
        list.add(_class1);
        list.add(_class2);
        DiagramHelper.assertCheckObjectOnDiagram(((DiagramToolExecutionContext) getExecutionContext()).getDiagram(), list, true);
      }
    };
    executeToolInsertTypes.run();
  }

  protected String getRequiredTestModel() {
    return "CDBCommunication"; //$NON-NLS-1$
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getRequiredTestModel());
  }

  public static Test suite() {
    return new CDBCommunicationTestSuite();
  }
}
