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

import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;

/**
 *
 */
public class ConstraintsSection extends CDBCommunication {

  public static final String constraint1 = "CONSTRAINT_1";
  public static final String constraint2 = "CONSTRAINT_2";

  public ConstraintsSection(CDBProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    testConstraints();
  }

  protected void testConstraints() {
    String dPkg1 = cdb.createDataPackage();
    String dPkg2 = cdb.createDataPackage(dPkg1);

    String cls = cdb.createClass();
    String union = cdb.createUnion();
    String collection = cdb.createCollection();

    cdb.createConstraint(constraint1);
    cdb.createConstraint(constraint2);

    cdb.createConstrainedElement(constraint1, cls);
    cdb.createConstrainedElement(constraint1, union);
    cdb.createConstrainedElement(constraint2, collection);
    cdb.createConstrainedElement(constraint2, dPkg1);
    cdb.createConstrainedElement(constraint2, dPkg2);

    cdb.removeConstraint(constraint1, cls);
    cdb.insertConstraint(constraint1, cls);
    cdb.removeConstraint(constraint1, union);
    cdb.insertConstraint(constraint1, union);
    cdb.removeConstraint(constraint2, collection);
    cdb.insertConstraint(constraint2, collection);
    cdb.removeConstraint(constraint2, dPkg1);
    cdb.insertConstraint(constraint2, dPkg1);
    cdb.removeConstraint(constraint2, dPkg2);
    cdb.insertConstraint(constraint2, dPkg2);
  }
}
