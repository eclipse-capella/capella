/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.re.re2rpl.activities;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.core.transition.common.activities.AbstractActivity;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class RemoveRECSuffixActivity extends AbstractActivity {

  public static final String ID = RemoveRECSuffixActivity.class.getCanonicalName();

  @Override
  protected IStatus _run(ActivityParameters activityParams) {

    IContext context = (IContext) activityParams.getParameter(ITransposerWorkflow.TRANSPOSER_CONTEXT).getValue();

    // get the suffix's RPL
    Object selection = context.get(ITransitionConstants.TRANSITION_SELECTION);
    if (selection instanceof List) {
      List<?> list = (List<?>) selection;
      if (!list.isEmpty()) {
        Object object = list.get(0);
        if (object instanceof CatalogElement) {
          CatalogElement rpl = (CatalogElement) object;
          String suffix = rpl.getSuffix();

          // remove the suffix on all elements of the REC
          CatalogElement rec = rpl.getOrigin();
          if (rec != null) {
            for (CatalogElementLink link : rec.getOwnedLinks()) {
              if (link.isSuffixed()) {
                EObject target = link.getTarget();
                if (target != null) {
                  EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
                  String name = (String) target.eGet(feature);
                  if ((name != null) && name.endsWith(suffix)) {
                    target.eSet(feature, name.substring(0, name.length() - suffix.length()));
                  }
                }
              }
            }
          }
        }
      }
    }

    return Status.OK_STATUS;
  }
}
