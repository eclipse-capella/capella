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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * Utilitary class for tools on a given session and diagram
 */
public class TableToolHelper {

  protected final Session _session;
  protected final DTable _table;

  /**
   * Constructor
   * 
   * @param session_p
   *          the target session
   * @param table_p
   *          the target table diagram
   */
  public TableToolHelper(Session session_p, DTable table_p) {
    _session = session_p;
    _table = table_p;

  }

  /**
   * Return the Tools so-called toolEClass_p for a given diagram, null if it does not exist
   * <P>
   * Since there is no explicit tools visible for the table (e.g. in the palette) we use the Tool EClass here...
   * 
   * @param toolEClass_p
   *          the tool eclass (like Create Cell Tool, Delete Line tool etc.)
   * @return <code>null</code> if an error occurred.
   */
  public AbstractToolDescription getTool(final EClass toolEClass_p, String toolName_p) {
    final List<AbstractToolDescription> tools = getAllTools(_table.getDescription());
    AbstractToolDescription theAbstractToolDescription = null;

    for (AbstractToolDescription current : tools) {
      if (current.eClass() == toolEClass_p && current.getName().equals(toolName_p)) {
        theAbstractToolDescription = current;
        break;
      }
    }

    return theAbstractToolDescription;
  }

  /**
   * Return the Tools so-called toolEClass_p for a given diagram, null if it does not exist
   * <P>
   * Since there is no explicit tools visible for the table (e.g. in the palette) we use the Tool EClass here...
   * 
   * @param toolEClass_p
   *          the tool eclass (like Create Cell Tool, Delete Line tool etc.)
   * @return <code>null</code> if an error occurred.
   */
  public AbstractToolDescription getTool(final EClass toolEClass_p) {

    final List<AbstractToolDescription> tools = getAllTools(_table.getDescription());
    AbstractToolDescription theAbstractToolDescription = null;

    List<AbstractToolDescription> list = new ArrayList();
    for (AbstractToolDescription current : tools) {
      if (current.eClass() == toolEClass_p) {
        theAbstractToolDescription = current;
        list.add(current);
        break;
      }
    }

    // This is a dummy tool because Delete tool may not be available for a column/line and
    // delete happens through destroy of the line/column
    if (theAbstractToolDescription == null) {
      if (toolEClass_p == DescriptionPackage.Literals.DELETE_COLUMN_TOOL) {
        theAbstractToolDescription = DescriptionFactory.eINSTANCE.createDeleteColumnTool();
      } else if (toolEClass_p == DescriptionPackage.Literals.DELETE_LINE_TOOL) {
        theAbstractToolDescription = DescriptionFactory.eINSTANCE.createDeleteLineTool();
      }
    }
    return theAbstractToolDescription;
  }

  /**
   * Gets all the tools associated with the table description
   * 
   * @param tableDescription_p
   * @return
   */
  private List<AbstractToolDescription> getAllTools(TableDescription tableDescription_p) {
    List<AbstractToolDescription> allTools = new ArrayList<AbstractToolDescription>();

    // Mappings to find any tool available in the mapping apart from Description
    List<TableMapping> tableMappings = new ArrayList<TableMapping>();

    TreeIterator<EObject> iter = tableDescription_p.eAllContents();
    while (iter.hasNext()) {
      EObject obj = iter.next();
      if (obj instanceof AbstractToolDescription) {
        allTools.add((AbstractToolDescription) obj);
      }
      if (obj instanceof TableMapping) {
        tableMappings.add((TableMapping) obj);
      }
    }

    for (TableMapping mapping : tableMappings) {
      TreeIterator<EObject> mappingIter = mapping.eAllContents();
      while (mappingIter.hasNext()) {
        EObject obj = mappingIter.next();
        if (obj instanceof AbstractToolDescription) {
          allTools.add((AbstractToolDescription) obj);
        }
      }
    }
    // FIXME what happens for LabelEditTool which is a TableTool and not AbstractToolDescription
    return removeDuplicates(allTools);
  }

  /**
   * Removes the duplicate entries in the list
   * 
   * @param list
   *          the list with duplicate entries
   * @return list with no duplicates
   */
  static public <T extends EObject> List<T> removeDuplicates(List<T> list) {
    Set<T> set = new HashSet<T>(list);
    list.clear();
    list.addAll(set);
    return list;
  }
}
