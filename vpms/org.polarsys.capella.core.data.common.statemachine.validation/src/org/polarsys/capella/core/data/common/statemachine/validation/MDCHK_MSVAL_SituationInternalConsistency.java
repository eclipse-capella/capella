/*******************************************************************************
 * Copyright (c) 2017 ALTRAN.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Altran - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.common.statemachine.validation;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.vp.ms.AndOperation;
import org.polarsys.capella.vp.ms.BooleanExpression;
import org.polarsys.capella.vp.ms.NotOperation;
import org.polarsys.capella.vp.ms.OrOperation;
import org.polarsys.capella.vp.ms.Situation;


public class MDCHK_MSVAL_SituationInternalConsistency extends AbstractModelConstraint {

  Collection<IStatus> objectsIrregularList = new ArrayList<IStatus>();

  @Override
  public IStatus validate(IValidationContext ctx) {
    objectsIrregularList.clear();
    EObject eObj = ctx.getTarget();
    if (eObj instanceof Situation) {
      checkOrAnd(((Situation) eObj).getExpression(), ctx);
      checkNot(((Situation) eObj).getExpression(), ctx);
    }
    if (objectsIrregularList.size() > 0) {
      return ConstraintStatus.createMultiStatus(ctx, objectsIrregularList);
    } else {
      return ctx.createSuccessStatus();
    }
  }

  private void checkOrAnd(BooleanExpression be, IValidationContext ctx){
    String error = "You can't have an 'AND' or 'OR' operator with less than 2 values.";
    int size = objectsIrregularList.size();
    if(be.eContents().size() < 2 && (be instanceof OrOperation || be instanceof AndOperation) && size == 0){
      objectsIrregularList.add(ctx.createFailureStatus(error));
    }else{
      for(EObject obj : be.eContents()){
        if((BooleanExpression)obj instanceof OrOperation || (BooleanExpression)obj instanceof AndOperation){
          checkOrAnd((BooleanExpression)obj, ctx);
        }
      }
    }
  }

  private void checkNot(BooleanExpression be, IValidationContext ctx){
    String error = "You can't have an 'NOT' operator with more than 1 value.";
    int size = objectsIrregularList.size();
    if(be.eContents().size() > 1 && be instanceof NotOperation && size == 0){
      objectsIrregularList.add(ctx.createFailureStatus(error));
    }else{
      for(EObject obj : be.eContents()){
        if((BooleanExpression)obj instanceof NotOperation){
          checkNot((BooleanExpression)obj, ctx);
        }
      }
    }
  }



}
