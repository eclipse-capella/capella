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
package org.polarsys.capella.test.diagram.tools.ju.cdb;

import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;

/**
 *
 */
public class InsertRemoveRelationships extends CDBCommunication {

  public InsertRemoveRelationships(CDBProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    testCreateRelationship();
  }

  protected void testCreateRelationship() {
    String class1 = cdb.createClass();
    String class2 = cdb.createClass();
    String class3 = cdb.createClass();
    String class4 = cdb.createClass();

    // create relationships
    String rel1 = cdb.createRelationship(class1, class2, CDBDiagram.RelationshipType.ASSOCIATION);
    String rel2 = cdb.createRelationship(class2, class3, CDBDiagram.RelationshipType.AGGREGATION);
    String rel3 = cdb.createRelationship(class3, class4, CDBDiagram.RelationshipType.COMPOSITION);
    String rel4 = cdb.createRelationship(class2, class4, CDBDiagram.RelationshipType.GENERALIZATION);

    DiagramHelper.setSynchronized(cdb.getDiagram(), false);

    // insert/remove relationships
    cdb.removeRelationship(rel1, class1);
    cdb.insertRelationship(rel1, class2);
    cdb.removeRelationship(rel2, class3);
    cdb.insertRelationship(rel2, class3);
    cdb.removeRelationship(rel3, class3);
    cdb.insertRelationship(rel3, class3);
    cdb.removeRelationship(rel4, class2);
    cdb.insertRelationship(rel4, class4);

    cdb.removeType(class1, class3, class4);
    cdb.hasntViews(rel1, rel2, rel3);
    cdb.insertRelationship(rel1, class2);
    cdb.insertRelationship(rel2, class2);
    cdb.insertRelationship(rel3, class3);
    cdb.hasViews(class1, class3, class4);

    DiagramHelper.setSynchronized(cdb.getDiagram(), true);
  }
}
