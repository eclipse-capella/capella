/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.capella;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.information.datavalue.OpaqueExpression;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;

public class OpaqueExpressionMigrationContribution extends AbstractMigrationContribution {
  @Override
  public void unaryMigrationExecute(EObject object, MigrationContext context) {
    super.unaryMigrationExecute(object, context);
    if (object instanceof OpaqueExpression) {
      OpaqueExpression expression = (OpaqueExpression) object;
      if (expression.getLanguages().isEmpty()) {
        expression.getLanguages().add("capella:linkedText");
      }
      if (expression.getBodies().isEmpty()) {
        expression.getBodies().add(ICommonConstants.EMPTY_STRING);
      }
    }
  }
}
