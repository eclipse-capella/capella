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
package org.polarsys.capella.test.table.ju.function;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.table.ju.utils.AbstractTableToolStep;
import org.polarsys.capella.test.table.ju.utils.CreateCellFromIntersectionCommand;
import org.polarsys.capella.test.table.ju.utils.TableTestFramework;
import org.polarsys.capella.test.table.ju.utils.TableTestingHelper;

public abstract class FunctionTableTestFramework extends TableTestFramework {
  protected String modelName = "SF-OA";
  protected Session session;
  protected SessionContext context;
  protected String diagramID = "e348c8d5-b00e-4147-a4a8-e1450fef6a4d";

  protected OperationalActivity _oa1;
  protected OperationalActivity _oa11;
  protected OperationalActivity _oa2;
  protected OperationalActivity _oa21;
  protected OperationalActivity _oa3;

  protected SystemFunction _sf1;
  protected SystemFunction _sf11;
  protected SystemFunction _sf2;
  protected SystemFunction _sf21;
  protected SystemFunction _sf3;

  protected String tableName = "New System Functions - Operational Activities";

  // Operational Activities
  protected String rootOAId = "4c119ea2-911e-41b6-80a4-72229d0605d7";
  protected String oa1Id = "e5bd33f1-2b66-40ce-b583-2843b27cf608";
  protected String oa11Id = "620be8c3-542d-4b71-b914-d42168312361";
  protected String oa2Id = "b96f55f2-41a5-4922-b5a8-e52525fbb80d";
  protected String oa21Id = "db01f744-be96-4e4c-89a2-2f3f2025bbb2";
  protected String oa3Id = "84000579-6b35-4650-a851-cc9d51cfd0af";

  // System Functions
  protected String rootSFId = "06d45db4-d7cd-4d94-9d90-6115e88523f5";
  protected String sf1Id = "69f5c20e-5568-496a-ab5d-52e1bed91b49";
  protected String sf11Id = "3f6d7a1b-3219-4677-8483-1596d74b1490";
  protected String sf2Id = "5bc4bdb3-4002-4d58-9886-4d32911264b3";
  protected String sf21Id = "293d6037-08df-4b5c-aeda-e3cba1a5ee58";
  protected String sf3Id = "75bdd58b-c89b-4fc1-9bb1-dd45ac6dfa11";

  // Error Messages
  protected String realizationNullErrMsg = "OA Realization on '{0}' is null";
  protected String realizedOAErrMsg = "Realized OA of SF '{0}' is not '{1}'";

  protected void init() {
    session = getSession(modelName);
    context = new SessionContext(session);

    _oa1 = context.getSemanticElement(oa1Id);
    _oa11 = context.getSemanticElement(oa11Id);
    _oa2 = context.getSemanticElement(oa2Id);
    _oa21 = context.getSemanticElement(oa21Id);
    _oa3 = context.getSemanticElement(oa3Id);
    _sf1 = context.getSemanticElement(sf1Id);
    _sf11 = context.getSemanticElement(sf11Id);
    _sf2 = context.getSemanticElement(sf2Id);
    _sf21 = context.getSemanticElement(sf21Id);
    _sf3 = context.getSemanticElement(sf3Id);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(modelName);
  }

  public void createCellValue(DTable table, EObject lineObj, EObject colObj) {
    new AbstractTableToolStep(context,
        DescriptionPackage.Literals.CREATE_CELL_TOOL, table) {
      @Override
      protected void initToolArguments() {
        DLine line = TableTestingHelper.getLine(table, lineObj);
        DColumn column = TableTestingHelper.getColumn(table, colObj);
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
        FunctionRealization oaRealization = TableTestingHelper.getRecentlyCreatedCapellaElement(_sf1,
            FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS);

        assertNotNull(realizationNullErrMsg, oaRealization);

        assertEquals(NLS.bind(realizedOAErrMsg, _sf1.getName(), _oa1.getName()), _oa1,
            oaRealization.getAllocatedFunction());
      }

      @Override
      public Object getResult() {
        return null;
      }
    }.run();
  }
  
}
