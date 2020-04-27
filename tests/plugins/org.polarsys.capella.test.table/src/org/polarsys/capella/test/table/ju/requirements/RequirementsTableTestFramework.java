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
package org.polarsys.capella.test.table.ju.requirements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.requirement.Requirement;
import org.polarsys.capella.core.data.requirement.RequirementPackage;
import org.polarsys.capella.core.data.requirement.RequirementsPkg;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;
import org.polarsys.capella.core.data.requirement.SystemFunctionalInterfaceRequirement;
import org.polarsys.capella.core.data.requirement.SystemFunctionalRequirement;
import org.polarsys.capella.core.data.requirement.SystemNonFunctionalInterfaceRequirement;
import org.polarsys.capella.core.data.requirement.SystemNonFunctionalRequirement;
import org.polarsys.capella.core.data.requirement.SystemUserRequirement;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.table.ju.utils.AbstractTableActionCommand;
import org.polarsys.capella.test.table.ju.utils.AbstractTableToolStep;
import org.polarsys.capella.test.table.ju.utils.AbstractTableTransactionToolStep;
import org.polarsys.capella.test.table.ju.utils.CreateCellFromIntersectionCommand;
import org.polarsys.capella.test.table.ju.utils.HideTableElementCommand;
import org.polarsys.capella.test.table.ju.utils.TableTestFramework;
import org.polarsys.capella.test.table.ju.utils.TableTestingHelper;

public abstract class RequirementsTableTestFramework extends TableTestFramework {
  protected String modelName = "CrossTable-OA";
  protected Session session;
  protected SessionContext context;
  protected String pABDiagramName = "";
  protected String rootReqPkgId = "d2e2d479-ef25-433b-a922-34de67211ef0";

  // Requirements
  protected RequirementsPkg _rootReqPkg;
  protected Requirement _systemUserReq;
  protected Requirement _systemFuncReq;
  protected Requirement _systemNonFuncReq;
  protected Requirement _systemFuncInterReq;
  protected Requirement _systemNonFuncInterReq;
  protected RequirementsPkg _subReqPkg;
  protected String crossTableOAName = "New Operational Activities - Requirements";
  protected String requirementsName = "New Requirements";

  protected String operationalAnalysisId = "03308ea5-1659-41ae-ab94-1b0b9c098dc5";
  protected String deliverMetInfoOAId = "62d5d005-54f0-44a1-bb9b-aabbdf369736";
  protected String captureMetDataOAId = "e40d356a-7f66-4ef4-b6ab-bf90529dfe04";
  protected String captureMetDataReqId = "0e75b6fb-dbc6-4d4b-a91d-57e344116276";
  protected String publishMetReportOAId = "d0fcd409-2f60-4409-9ead-907d905098fb";
  protected String buildMetReportOAId = "b5682772-0698-488c-bba3-b10a09f209d2";
  protected String elabMetForecastOAId = "c1c8004e-98d6-4adf-8fa6-e66df914d735";
  protected String elabCurrSituationOAId = "a4e551ca-1d6d-4cda-ae16-63c1d594103e";
  protected String accessMetReportOAId = "b8881ae7-95fa-4762-b607-6e5bf330d4c8";
  protected String makeMetDataEvolveOAId = "7a12df46-8c90-444f-bb18-53b719912386";

  // requirements
  protected String publishMetReportReqId = "871f791b-c246-44bf-8e27-787cb6acc74f";
  protected String buildMetReportReqId = "22866ea7-6aeb-46cf-8c41-2991dae9dcc2";
  protected String elabMetForecastReqId = "bde8a3ea-2879-46b1-870b-05437d6e3d07";
  protected String elabCurrSituationReqId = "b36524a8-16e7-49a6-b9ce-72feed229fe2";
  protected String accessMetReportReqId = "ae23499e-23f7-417e-96f6-b50b0674729b";
  protected String makeMetDataEvolveReqId = "d58542df-d56e-4cbc-97e8-f9900bd9c0e3";

  // error messages
  protected String reqNullErrMsg = "Created requirement is null";
  protected String traceNullErrMsg = "Requirements Trace is null";
  protected String traceTypeErrMsg = "Created Trace is not instanceof RequirementsTrace";
  protected String sourceEltErrMsg = "Source Element of Trace {0} is not {1}";
  protected String targetEltErrMsg = "Target Element of Trace {0} is not {1}";

  protected OperationalActivity _deliverMetInfoOA;
  protected OperationalActivity _captureMetDataOA;
  protected OperationalActivity _publishMetReportOA;
  protected OperationalActivity _buildMetReportOA;
  protected OperationalActivity _elabMetForecastOA;
  protected OperationalActivity _elabCurrSituationOA;
  protected OperationalActivity _accessMetReportOA;
  protected OperationalActivity _makeMetDataEvolveOA;

  protected Requirement _captureMetDataReq;
  protected Requirement _publishMetReportReq;
  protected Requirement _buildMetReportReq;
  protected Requirement _elabMetForecastReq;
  protected Requirement _elabCurrSituationReq;
  protected Requirement _accessMetReportReq;
  protected Requirement _makeMetDataEvolveReq;

  protected EClass createCellToolClass = DescriptionPackage.Literals.CREATE_CELL_TOOL;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(modelName);
  }

  protected void init() {
    session = getSession(modelName);
    context = new SessionContext(session);
    _rootReqPkg = context.getSemanticElement(rootReqPkgId);
    _deliverMetInfoOA = (OperationalActivity) context.getSemanticElement(deliverMetInfoOAId);
    _captureMetDataOA = (OperationalActivity) context.getSemanticElement(captureMetDataOAId);
    _publishMetReportOA = (OperationalActivity) context.getSemanticElement(publishMetReportOAId);
    _buildMetReportOA = (OperationalActivity) context.getSemanticElement(buildMetReportOAId);
    _elabMetForecastOA = (OperationalActivity) context.getSemanticElement(elabMetForecastOAId);
    _elabCurrSituationOA = (OperationalActivity) context.getSemanticElement(elabCurrSituationOAId);
    _accessMetReportOA = (OperationalActivity) context.getSemanticElement(accessMetReportOAId);
    _makeMetDataEvolveOA = (OperationalActivity) context.getSemanticElement(makeMetDataEvolveOAId);

    _captureMetDataReq = (Requirement) context.getSemanticElement(captureMetDataReqId);
    _publishMetReportReq = (Requirement) context.getSemanticElement(publishMetReportReqId);
    _buildMetReportReq = (Requirement) context.getSemanticElement(buildMetReportReqId);
    _elabMetForecastReq = (Requirement) context.getSemanticElement(elabMetForecastReqId);
    _elabCurrSituationReq = (Requirement) context.getSemanticElement(elabCurrSituationReqId);
    _accessMetReportReq = (Requirement) context.getSemanticElement(accessMetReportReqId);
    _makeMetDataEvolveReq = (Requirement) context.getSemanticElement(makeMetDataEvolveReqId);
  }

  /*
   * Create requirement Trace between obj and reqObj
   */
  public void createRequirementTrace(DTable table, EObject obj, EObject reqObj) {
    new AbstractTableToolStep(context, createCellToolClass, table) {

      @Override
      protected void initToolArguments() {
        DLine line = TableTestingHelper.getLine(table, obj);
        DColumn column = TableTestingHelper.getColumn(table, reqObj);
        // Cell creation in the intersection cell otherwise there is no
        // cell
        CreateCellFromIntersectionCommand cellCommand = new CreateCellFromIntersectionCommand(table, line, column,
            IToolNameConstants.TABLE_TOOL_CREATE_CELL_VALUE);
        cellCommand.execute();

        EObject container = TableTestingHelper.getIntersectionCell(line, column);
        String tableCellMask = IToolNameConstants.TABLE_TOOL_CREATE_CELL_VALUE;

        _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, container);
        _toolWrapper.setArgumentValue(ArgumentType.TABLE_CELL_MASK, tableCellMask);

      }

      @Override
      protected void postRunTest() {
        super.postRunTest();

        Trace reqTrace = (Trace) TableTestingHelper.getRecentlyCreatedCapellaElement(obj,
            ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES);
        assertNotNull(traceNullErrMsg, reqTrace);
        assertTrue(traceTypeErrMsg, (reqTrace instanceof RequirementsTrace));
        assertEquals(NLS.bind(sourceEltErrMsg, reqTrace, EObjectExt.getText(obj)), obj, reqTrace.getSourceElement());
        assertEquals(NLS.bind(targetEltErrMsg, reqTrace, EObjectExt.getText(reqObj)), reqObj,
            reqTrace.getTargetElement());
      }

      @Override
      public Object getResult() {
        return null;
      }

    }.run();

  }

  public void hideLineWithChildren(DTable table, EObject obj) {
    new AbstractTableTransactionToolStep(context) {
      @Override
      protected void postRunTest() {
        super.postRunTest();

        DLine parent = TableTestingHelper.getLine(table, obj);
        assertFalse(NLS.bind(tableEltHiddenErrMsg, parent.getLabel()), parent.isVisible());

        DLine child1 = TableTestingHelper.getLine(table, _publishMetReportOA);
        DLine child2 = TableTestingHelper.getLine(table, _buildMetReportOA);
        DLine child3 = TableTestingHelper.getLine(table, _elabMetForecastOA);
        DLine child4 = TableTestingHelper.getLine(table, _elabCurrSituationOA);

        assertFalse(NLS.bind(tableEltHiddenErrMsg, child1.getLabel()), child1.isVisible());
        assertTrue(NLS.bind(tableEltHiddenErrMsg, child2.getLabel()), child2.isVisible());
        assertTrue(NLS.bind(tableEltHiddenErrMsg, child3.getLabel()), child3.isVisible());
        assertTrue(NLS.bind(tableEltHiddenErrMsg, child4.getLabel()), child4.isVisible());
      }

      @Override
      protected AbstractTableActionCommand getTableTransactionalCommand() {
        DLine line = TableTestingHelper.getLine(table, obj);
        return new HideTableElementCommand(table, line, TablePackage.Literals.DLINE__VISIBLE);
      }

      @Override
      public Object getResult() {
        return null;
      }

    }.run();

  }

  public void deleteRequirementTrace(DTable table, EObject obj, EObject reqObj) {
    new AbstractTableToolStep(context, createCellToolClass, table) {

      @Override
      protected void initToolArguments() {
        DLine line = TableTestingHelper.getLine(table, obj);
        DColumn column = TableTestingHelper.getColumn(table, reqObj);

        EObject container = TableTestingHelper.getIntersectionCell(line, column);
        String tableCellMask = IToolNameConstants.TABLE_TOOL_DELETE_CELL_VALUE;

        _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, container);
        _toolWrapper.setArgumentValue(ArgumentType.TABLE_CELL_MASK, tableCellMask);

      }

      @Override
      protected void postRunTest() {
        super.postRunTest();

        Trace reqTrace = TableTestingHelper.getRecentlyCreatedCapellaElement(_captureMetDataOA,
            ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES);
        assertNull(traceNullErrMsg, reqTrace);

      }

      @Override
      public Object getResult() {
        return null;
      }

    }.run();

  }

  public Requirement createRequirementSUR(DTable table, RequirementsPkg req) {
    return createRequirement(table, req, IToolNameConstants.TABLE_TOOL_REQ_SUR, SystemUserRequirement.class);
  }

  public Requirement createRequirementSFR(DTable table, RequirementsPkg req) {
    return createRequirement(table, req, IToolNameConstants.TABLE_TOOL_REQ_SFR, SystemFunctionalRequirement.class);
  }

  public Requirement createRequirementSFIR(DTable table, RequirementsPkg req) {
    return createRequirement(table, req, IToolNameConstants.TABLE_TOOL_REQ_SFIR,
        SystemFunctionalInterfaceRequirement.class);
  }

  public Requirement createRequirementSNFR(DTable table, RequirementsPkg req) {
    return createRequirement(table, req, IToolNameConstants.TABLE_TOOL_REQ_SNFR, SystemNonFunctionalRequirement.class);
  }

  public Requirement createRequirementSNFIR(DTable table, RequirementsPkg req) {
    return createRequirement(table, req, IToolNameConstants.TABLE_TOOL_REQ_SNFIR,
        SystemNonFunctionalInterfaceRequirement.class);
  }

  public Requirement createRequirementREQPKG(DTable table, RequirementsPkg req) {
    return createRequirement(table, req, IToolNameConstants.TABLE_TOOL_REQ_REQ_PKG, null);
  }

  public Requirement createRequirement(DTable table, RequirementsPkg req, String toolName,
      Class<? extends Requirement> createdReqClass) {
    return (Requirement) new AbstractTableToolStep(context, toolName, DescriptionPackage.Literals.CREATE_LINE_TOOL,
        table) {
      protected Requirement requirement;

      @Override
      protected void initToolArguments() {
        EObject container = TableTestingHelper.getLine(table, req);
        EObject semanticContainer = req;

        _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, container);
        _toolWrapper.setArgumentValue(ArgumentType.CONTAINER, semanticContainer);
      }

      @Override
      protected void postRunTest() {
        super.postRunTest();
        requirement = TableTestingHelper.getRecentlyCreatedCapellaElement(req,
            RequirementPackage.Literals.REQUIREMENTS_PKG__OWNED_REQUIREMENTS);
        assertNotNull(reqNullErrMsg, requirement);
        assertTrue(requirement.eContainer() == req);
        if (createdReqClass != null)
          assertTrue(createdReqClass.isInstance(requirement));
      }

      @Override
      public Object getResult() {
        return requirement;
      }

    }.run();

  }

  public void deleteRequirement(DTable table, Requirement req) {
    new AbstractTableToolStep(context, null, DescriptionPackage.Literals.DELETE_LINE_TOOL, table) {

      @Override
      protected void initToolArguments() {
        EObject container = TableTestingHelper.getLine(table, req);
        _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, container);
      }

      @Override
      protected void postRunTest() {
        super.postRunTest();
        List<EObject> list = new ArrayList<EObject>();
        list.add(req);
        TableTestingHelper.assertCheckObjectOnTable(table, list, false);
        assertNull(reqNullErrMsg, req.eContainer());
      }

      @Override
      public Object getResult() {
        return null;
      }

    }.run();

  }
}
