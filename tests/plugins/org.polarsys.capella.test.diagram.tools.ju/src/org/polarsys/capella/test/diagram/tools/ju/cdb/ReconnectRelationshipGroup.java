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
package org.polarsys.capella.test.diagram.tools.ju.cdb;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;

/**
 *
 */
public class ReconnectRelationshipGroup extends CDBCommunication {

  public ReconnectRelationshipGroup(CDBProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    testReconnectRelationship();
  }

  protected void testReconnectRelationship() {
    String class1 = cdb.createClass();
    String class2 = cdb.createClass();
    String class3 = cdb.createClass();
    String class4 = cdb.createClass();
    String class5 = cdb.createClass();
    String union1 = cdb.createUnion();
    String union2 = cdb.createUnion();
    String union3 = cdb.createUnion();
    String collection1 = cdb.createCollection();
    String collection2 = cdb.createCollection();
    String collection3 = cdb.createCollection();

    // create relationships
    String association1 = cdb.createRelationship(class1, class2, CDBDiagram.RelationshipType.ASSOCIATION);
    String generalization1 = cdb.createRelationship(class2, class4, CDBDiagram.RelationshipType.GENERALIZATION);

    String association2 = cdb.createRelationship(union1, union2, CDBDiagram.RelationshipType.ASSOCIATION);
    String generalization2 = cdb.createRelationship(union2, union3, CDBDiagram.RelationshipType.GENERALIZATION);

    String collectionType = cdb.createCollectionType(collection1, collection2);

    // reconnect relationships
    cdb.reconnectRelationshipSource(association1, class3, CDBDiagram.RelationshipType.ASSOCIATION);
    cdb.reconnectRelationshipTarget(association1, class4, CDBDiagram.RelationshipType.ASSOCIATION);
    cdb.reconnectRelationshipSource(association1, class4, CDBDiagram.RelationshipType.ASSOCIATION);
    cdb.reconnectRelationshipSource(generalization1, class5, CDBDiagram.RelationshipType.GENERALIZATION);
    cdb.reconnectRelationshipTarget(generalization1, class3, CDBDiagram.RelationshipType.GENERALIZATION);
    cdb.reconnectRelationshipSource(association2, union3, CDBDiagram.RelationshipType.ASSOCIATION);
    cdb.reconnectRelationshipTarget(association2, class4, CDBDiagram.RelationshipType.ASSOCIATION);
    cdb.reconnectRelationshipSource(generalization2, union1, CDBDiagram.RelationshipType.GENERALIZATION);
    cdb.reconnectRelationshipTarget(generalization2, union2, CDBDiagram.RelationshipType.GENERALIZATION);
      // For COLLECTION_TYPE, only one side is tested, adapt it according to the side that has not the same id than the edge.
    if (cdb.getView(collectionType) instanceof DEdge dEdge) {
      if (dEdge.getSourceNode() instanceof DDiagramElement dde) {
        if (collectionType == cdb.getSemanticIdFromView(dde)) {
          cdb.reconnectRelationshipTarget(collectionType, collection3, CDBDiagram.RelationshipType.COLLECTION_TYPE);
        } else {
          cdb.reconnectRelationshipSource(collectionType, collection3, CDBDiagram.RelationshipType.COLLECTION_TYPE);
        }
      } else {
        fail("The source element of the DEdge must be an instance of DDiagramElement.");
      }
    } else {
      fail("The id used for reconnect must correspond to a DEdge element.");
    }

    // cannot reconnect relationships
    cdb.cannotReconnectRelationshipSource(association1, collection1, CDBDiagram.RelationshipType.ASSOCIATION);
    cdb.cannotReconnectRelationshipSource(generalization1, class3, CDBDiagram.RelationshipType.GENERALIZATION);
    cdb.cannotReconnectRelationshipTarget(generalization1, collection1, CDBDiagram.RelationshipType.GENERALIZATION);
  }
}
