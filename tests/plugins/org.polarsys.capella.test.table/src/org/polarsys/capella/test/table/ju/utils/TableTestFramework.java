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
package org.polarsys.capella.test.table.ju.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDTableStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDTableStep;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;
import org.polarsys.capella.test.framework.api.NonDirtyTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;

public abstract class TableTestFramework extends NonDirtyTestCase {
  protected String existErrMsg = "'{0}' exists even after delete column command is executed";

  public void hideLine(SessionContext context, DTable table, EObject obj) {
    new AbstractTableTransactionToolStep(context) {
      @Override
      protected void postRunTest() {
        super.postRunTest();
        DLine line = TableTestingHelper.getLine(table, obj);
        assertFalse(NLS.bind(tableEltHiddenErrMsg, line.getLabel()), line.isVisible());
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

  public void hideColumn(SessionContext context, DTable table, EObject obj) {
    new AbstractTableTransactionToolStep(context) {
      @Override
      protected void postRunTest() {
        super.postRunTest();
        DColumn column = TableTestingHelper.getColumn(table, obj);
        assertFalse(NLS.bind(tableEltHiddenErrMsg, column.getLabel()), column.isVisible());
      }

      @Override
      protected AbstractTableActionCommand getTableTransactionalCommand() {
        DColumn column = TableTestingHelper.getColumn(table, obj);
        return new HideTableElementCommand(table, column, TablePackage.Literals.DCOLUMN__VISIBLE);
      }

      @Override
      public Object getResult() {
        return null;
      }
    }.run();
  }

  public void hideLines(SessionContext context, DTable table, List<EObject> visibleElems, List<EObject> hiddenElems) {
    new AbstractTableTransactionToolStep(context) {
      @Override
      protected void postRunTest() {
        super.postRunTest();
        for (EObject obj : visibleElems) {
          DLine line = TableTestingHelper.getLine(table, obj);
          assertTrue(NLS.bind(tableEltHiddenErrMsg, line.getLabel()), line.isVisible());
        }

        for (EObject obj : hiddenElems) {
          DLine line = TableTestingHelper.getLine(table, obj);
          assertFalse(NLS.bind(tableEltHiddenErrMsg, line.getLabel()), line.isVisible());
        }
      }

      @Override
      protected AbstractTableActionCommand getTableTransactionalCommand() {

        return new HideRevealTableElementsCommand(table, TablePackage.Literals.DLINE__VISIBLE) {
          @Override
          public List<DTableElement> getVisibleElements() {
            List<DTableElement> list = new ArrayList<DTableElement>();
            for (EObject obj : visibleElems) {
              list.add(TableTestingHelper.getLine(table, obj));
            }
            return list;
          }
        };
      }

      @Override
      public Object getResult() {
        return null;
      }

    }.run();
  }

  public void hideColumns(SessionContext context, DTable table, List<EObject> visibleElems, List<EObject> hiddenElems) {
    new AbstractTableTransactionToolStep(context) {
      @Override
      protected void postRunTest() {
        super.postRunTest();
        for (EObject obj : visibleElems) {
          DColumn column = TableTestingHelper.getColumn(table, obj);
          assertTrue(NLS.bind(tableEltHiddenErrMsg, column.getLabel()), column.isVisible());
        }

        for (EObject obj : hiddenElems) {
          DColumn column = TableTestingHelper.getColumn(table, obj);
          assertFalse(NLS.bind(tableEltHiddenErrMsg, column.getLabel()), column.isVisible());
        }
      }

      @Override
      protected AbstractTableActionCommand getTableTransactionalCommand() {

        return new HideRevealTableElementsCommand(table, TablePackage.Literals.DCOLUMN__VISIBLE) {
          @Override
          public List<DTableElement> getVisibleElements() {
            List<DTableElement> list = new ArrayList<DTableElement>();
            for (EObject obj : visibleElems) {
              list.add(TableTestingHelper.getColumn(table, obj));
            }
            return list;
          }
        };
      }

      @Override
      public Object getResult() {
        return null;
      }

    }.run();
  }

  public void showAllLines(SessionContext context, DTable table) {
    new AbstractTableTransactionToolStep(context) {
      @Override
      protected void postRunTest() {
        super.postRunTest();

        List<DLine> allLines = TableTestingHelper.getAllLines(table);
        for (DLine line : allLines) {
          assertTrue(NLS.bind(tableEltVisibleErrMsg, line.getLabel()), line.isVisible());
        }
      }

      @Override
      protected AbstractTableActionCommand getTableTransactionalCommand() {
        return new ShowAllTableElementsCommand(table, TablePackage.Literals.DLINE__VISIBLE);
      }

      @Override
      public Object getResult() {
        return null;
      }

    }.run();

  }

  public void showAllColumns(SessionContext context, DTable table) {
    new AbstractTableTransactionToolStep(context) {
      @Override
      protected void postRunTest() {
        super.postRunTest();

        List<DColumn> allColumns = table.getColumns();
        for (DColumn column : allColumns) {
          assertTrue(NLS.bind(tableEltVisibleErrMsg, column.getLabel()), column.isVisible());
        }
      }

      @Override
      protected AbstractTableActionCommand getTableTransactionalCommand() {
        return new ShowAllTableElementsCommand(table, TablePackage.Literals.DCOLUMN__VISIBLE);
      }

      @Override
      public Object getResult() {
        return null;
      }

    }.run();

  }

  public void showAllLines(SessionContext context, DTable table, List<EObject> objToCheck) {
    showAllLines(context, table);
    for (EObject obj : objToCheck) {
      DLine line = TableTestingHelper.getLine(table, obj);
      assertTrue(line.isVisible());
    }
  }

  public void showAllColumns(SessionContext context, DTable table, List<EObject> objToCheck) {
    showAllColumns(context, table);
    for (EObject obj : objToCheck) {
      DColumn column = TableTestingHelper.getColumn(table, obj);
      assertTrue(column.isVisible());
    }
  }

  public void deleteColumn(SessionContext context, DTable table, EObject obj) {
    new AbstractTableToolStep(context, DescriptionPackage.Literals.DELETE_COLUMN_TOOL, table) {
      @Override
      protected void initToolArguments() {
        EObject container = TableTestingHelper.getColumn(table, obj);
        _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, container);
      }

      @Override
      protected void postRunTest() {
        TableTestingHelper.refreshTable(table);
        super.postRunTest();
        TableTestingHelper.assertCheckObjectOnTable(table, Arrays.asList(obj), false);
        assertNull(NLS.bind(existErrMsg, EObjectExt.getText(obj)), obj.eContainer());
      }

      @Override
      public Object getResult() {
        return null;
      }

    }.run();

  }

  public void deleteLine(SessionContext context, DTable table, EObject obj) {
    new AbstractTableToolStep(context, DescriptionPackage.Literals.DELETE_COLUMN_TOOL, table) {
      @Override
      protected void initToolArguments() {
        EObject container = TableTestingHelper.getLine(table, obj);
        _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, container);
      }

      @Override
      protected void postRunTest() {
        TableTestingHelper.refreshTable(table);
        super.postRunTest();
        TableTestingHelper.assertCheckObjectOnTable(table, Arrays.asList(obj), false);
      }

      @Override
      public Object getResult() {
        return null;
      }

    }.run();

  }

  public DTable createTable(SessionContext context, String targetId, String diagName) {
    Object result = new CreateDTableStep(context, targetId, diagName).run();
    assertTrue(result instanceof DTable);
    return (DTable) result;
  }

  public DTable openTable(SessionContext context, String tableName) {
    Object result = new OpenDTableStep(context, tableName).run();
    assertTrue(result instanceof DTable);
    return (DTable) result;
  }
}
