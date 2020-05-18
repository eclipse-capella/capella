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
package org.polarsys.capella.test.table.ju.utils;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.test.framework.api.CommonTestMessages;

/**
 * Utility class for table tests
 * 
 */
public class TableTestingHelper {

  /**
   * Gets the line with the semantic element as the target
   * 
   * @param table_p
   * @param semanticElement_p
   * @return
   */
  public static final DLine getLine(DTable table_p, EObject semanticElement_p) {

    List<DLine> lines = getAllLines(table_p);
    for (DLine line : lines) {
      if ((line != null) && semanticElement_p.equals(line.getTarget())) {
        return line;
      }
    }
    return null;
  }

  /**
   * Gets the column with semantic element as the target
   * 
   * @param table_p
   * @param semanticElement_p
   * @return
   */
  public static final DColumn getColumn(DTable table_p, EObject semanticElement_p) {
    for (DColumn column : table_p.getColumns()) {
      if (semanticElement_p.equals(column.getTarget())) {
        return column;
      }
    }
    return null;
  }

  /**
   * Gets all the lines in the line container
   * 
   * @param container_p
   * @return
   */
  public static final List<DLine> getAllLines(LineContainer container_p) {
    List<DLine> returnValue = new ArrayList<DLine>();
    if (container_p != null) {
      List<DLine> lines = container_p.getLines();
      if (lines != null) {
        returnValue.addAll(lines);
        for (DLine line : lines) {
          returnValue.addAll(getAllLines(line));
        }
      }
    }
    return returnValue;
  }

  /**
   * Gets the intersection cell between the line and column
   * 
   * @param line_p
   * @param column_p
   * @return
   */
  public static final DCell getIntersectionCell(DLine line_p, DColumn column_p) {
    Option<DCell> cell = TableHelper.getCell(line_p, column_p);
    return cell.get();
  }

  /**
   * Generic approach to get the recently created capella element. Also, size == 0 case is handled
   * <P>
   * 
   * <pre>
   *     e.g. to get the recently created outgoing trace in a root OA,
   *     TableTestingHelper.getRecentlyCreatedCapellaElement(_rootOA, ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES)
   * </pre>
   * 
   * @param <T>
   *          any class that extends EObject (Capella Element in our case)
   * @param parent_p
   *          the parent EObject
   * @param reference_p
   *          the Structural Feature
   * @return the recently created element from the parent
   */
  @SuppressWarnings("unchecked")
  public static final <T extends EObject> T getRecentlyCreatedCapellaElement(EObject parent_p, EReference reference_p) {
    if (!reference_p.isMany()) {
      throw new IllegalArgumentException("Reference \"" + reference_p.getName() + "\" should be many"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    List<T> createdObjects = (List<T>) parent_p.eGet(reference_p);

    int size = createdObjects.size();
    return (size == 0) ? null : createdObjects.get(size - 1);
  }

  /**
   * Utility method that checks whether some semantics objects should have a representation (or not) onto a table.
   * 
   * @param table_p
   *          the target {@link DTable}
   * @param list_p
   *          the list of semantic Object
   * @param shouldBeAvailable_p
   *          defines whether the objects contained into the list should have a representation or not on the target
   *          diagram
   */
  public static void assertCheckObjectOnTable(DTable table_p, List<EObject> list_p, boolean shouldBeAvailable_p) {

    String errMsg;
    EObject eObject = null;

    errMsg = shouldBeAvailable_p ? CommonTestMessages.objectRepresentationNotAvailableOnDiagram
        : CommonTestMessages.objectRepresentationStillAvailableOnDiagram;

    for (EObject current : list_p) {
      eObject = getLine(table_p, current);
      if (eObject == null) {
        eObject = getColumn(table_p, current);
      }
      assertTrue(shouldBeAvailable_p ? eObject != null : eObject == null);
    }

    return;
  }

  public static void refreshTable(final DTable table) {
    final AbstractCommand cmd = new AbstractReadWriteCommand() {

      @Override
      public void run() {
        DialectManager.INSTANCE.refresh(table, new NullProgressMonitor());
      }
    };
    // Let's perform the job
    TransactionHelper.getExecutionManager(table).execute(cmd);
  }
}
