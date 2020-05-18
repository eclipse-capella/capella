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
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;

/**
 *
 */
public class CreateRelationshipGroup extends CDBCommunication {

  public CreateRelationshipGroup(CDBProjectSettings settings) {
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
    String union1 = cdb.createUnion();
    String union2 = cdb.createUnion();
    String union3 = cdb.createUnion();
    String collection1 = cdb.createCollection();
    String collection2 = cdb.createCollection();
    String collection3 = cdb.createCollection();

    // create relationships
    cdb.createRelationship(class1, class2, CDBDiagram.RelationshipType.ASSOCIATION);
    cdb.createRelationship(class2, class3, CDBDiagram.RelationshipType.AGGREGATION);
    cdb.createRelationship(class3, class4, CDBDiagram.RelationshipType.COMPOSITION);
    cdb.createRelationship(class2, class4, CDBDiagram.RelationshipType.GENERALIZATION);

    cdb.createRelationship(union1, union2, CDBDiagram.RelationshipType.ASSOCIATION);
    cdb.createRelationship(class2, union2, CDBDiagram.RelationshipType.AGGREGATION);
    cdb.createRelationship(union1, class4, CDBDiagram.RelationshipType.COMPOSITION);
    cdb.createRelationship(union2, union3, CDBDiagram.RelationshipType.GENERALIZATION);

    cdb.createRelationship(collection1, collection2, CDBDiagram.RelationshipType.GENERALIZATION);
    cdb.createRelationship(union1, collection1, CDBDiagram.RelationshipType.ASSOCIATION);
    cdb.createRelationship(class1, collection2, CDBDiagram.RelationshipType.ASSOCIATION);

    cdb.createRelationship(class1, union1, CDBDiagram.RelationshipType.ASSOCIATION);
    cdb.createRelationship(union2, class3, CDBDiagram.RelationshipType.AGGREGATION);

    // create collection type
    cdb.createCollectionType(collection1, collection2);
    cdb.createCollectionType(collection3, collection2);
  }
}
