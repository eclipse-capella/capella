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
package org.polarsys.capella.test.diagram.tools.ju.sdfb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import junit.framework.Test;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramOpenExecutionContext;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramToolExecutionContext;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.ITransfertWizardResult;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.AbstractToolStep;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType_Enum;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;

/**
 * Test the case when a category is displayed at the parent function level. Then The switch category/functional exchange
 * tool should display the functional exchange of the children functions at the parent function.
 */
public class SwitchCategoryTestSuite extends AbstractDiagramTestCase {

  //
  // Target object id, useful
  //

  private String _rootSystemFunctionPkgId = "015492b3-98d6-4507-8b0a-6bccc844e653";
  private String _categoryId = "75a95691-eac6-460b-938c-fed7e5dd80ff";
  private String _functionalExchangeId = "20b2edb7-49ad-4295-a020-89af7974897e";

  // Category and Functional Exchange to test
  private FunctionalExchange _functionalExchange;
  private ExchangeCategory _exchangeCategory;

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    loadSemanticObjects(getSessionForTestModel(getRequiredTestModel()), Arrays.asList(_rootSystemFunctionPkgId, _categoryId, _functionalExchangeId));

    // Initialize the target objects
    _functionalExchange = (FunctionalExchange) getSemanticObjectMap().get(_functionalExchangeId);
    _exchangeCategory = (ExchangeCategory) getSemanticObjectMap().get(_categoryId);
  }

  @Override
  protected void tearDown() throws Exception {
    _functionalExchange = null;
    _exchangeCategory = null;

    super.tearDown();
  }

  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());

    DRepresentation dRepresentation = DiagramHelper.getDRepresentation(session, "[SDFB] Root System Function - System Data Flow Blank");
    DDiagram diagram = (DDiagram) dRepresentation;

    // OPEN DIAGRAM
    OpenDiagramStep openDiagram = new OpenDiagramStep(new DiagramOpenExecutionContext(session, diagram));
    openDiagram.run();

    // ////////////////////SWITCH CATEGORY / FUNCTIONAL EXCHANGE TESTS///////////////////////

    // Switch category / functional exchange
    AbstractToolStep executeToolInsertTypes = new AbstractToolStep(new DiagramToolExecutionContext(session, diagram,
        IToolNameConstants.TOOL_SDFB_SHOW_HIDE_FUNCTIONAL_EXCH_CATEGORIES)) {
      /**
       * @see org.polarsys.capella.test.diagram.common.ju.steps.AbstractExecuteToolCmdStep.tool.AbstractExecuteToolCmdTest#preTestRun()
       */
      @Override
      protected void preRunTest() {
        ITransfertWizardResult op = new ITransfertWizardResult() {

          @SuppressWarnings({ "unchecked", "synthetic-access", "rawtypes" })
          public Object getResult(java.util.Collection<? extends EObject> selections_p, Map<String, Object> parameters_p) {
            List list = new ArrayList();
            list.add(_exchangeCategory);
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
        list.add(_functionalExchange);
        DiagramHelper.assertCheckObjectOnDiagram(((DiagramToolExecutionContext) getExecutionContext()).getDiagram(), list, true);
      }
    };
    executeToolInsertTypes.run();
  }

  protected String getRequiredTestModel() {
    return "SwitchCategory"; //$NON-NLS-1$
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getRequiredTestModel());
  }

  public static Test suite() {
    return new SwitchCategoryTestSuite();
  }
}
