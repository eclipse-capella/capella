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
package org.polarsys.capella.vp.ms.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.vp.ms.BooleanOperation;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.InStateExpression;
import org.polarsys.capella.vp.ms.Result;
import org.polarsys.capella.vp.ms.Situation;
import org.polarsys.capella.vp.ms.impl.ResultImpl;

public class CompareTo extends ResultImpl implements IObjectActionDelegate {
  private Shell shell;
  private Result destinationResult;
  private List<CSConfiguration> configList = new ArrayList<CSConfiguration>();
  private List<CSConfiguration> configListFiltered = new ArrayList<CSConfiguration>();
  private List<AbstractFunction> functionListIncluded = new ArrayList<AbstractFunction>();
  private List<AbstractFunction> functionListExcluded = new ArrayList<AbstractFunction>();

  public CompareTo() {
    // TODO Auto-generated constructor stub
    super();
  }

  @Override
  public void run(IAction action) {
    // TODO Auto-generated method stub
    configList.clear();
    configListFiltered.clear();
    functionListExcluded.clear();
    functionListIncluded.clear();
    Situation situCompare = this.destinationResult.getSituation().get(0);

    for (EObject iObj : situCompare.eContents()) {
      if (iObj instanceof BooleanOperation) {
        BooleanOperation boolObj = (BooleanOperation) iObj;
        EObject tObj = boolObj.eContainer();
        EObject vObj = tObj.eContainer();
        for (EObject jObj : vObj.eContents()) {
          if (jObj instanceof CSConfiguration) {
            if (!configList.contains((CSConfiguration) jObj)) {
              configList.add((CSConfiguration) jObj);
            }
          } else if (jObj instanceof Component) {
            for (EObject childComponent : ((Component) jObj).eContents()) {
              if (childComponent instanceof CSConfiguration) {
                if (!configList.contains((CSConfiguration) childComponent)) {
                  configList.add((CSConfiguration) childComponent);
                }
              }
            }
          }
        }
        for (EObject jObj : boolObj.eContents()) {
          if (jObj instanceof InStateExpression) {
            for (CSConfiguration configObject : configList) {
              for (AbstractState pObj : configObject.getSupportedModes()) {
                if (((InStateExpression) jObj).getState() instanceof Mode) {
                  Mode modeState = (Mode) ((InStateExpression) jObj).getState();
                  if (modeState.equals(pObj)) {
                    if (!configListFiltered.contains(configObject)) {
                      configListFiltered.add(configObject);
                    }
                  }
                } else if (((InStateExpression) jObj).getState() instanceof State) {
                  State modeState = (State) ((InStateExpression) jObj).getState();
                  if (modeState.equals(pObj)) {
                    if (!configListFiltered.contains(configObject)) {
                      configListFiltered.add(configObject);
                    }
                  }
                }
              }
            }
          }
        }
      }
      for (CSConfiguration configObject : configListFiltered) {
        for (AbstractFunction jObj : configObject.getFunctions()) {
          if (configObject.getSelector().equals(configObject.getSelector().INCLUSION)) {
            functionListIncluded.add(jObj);
          } else {
            functionListExcluded.add(jObj);
          }
        }
      }

      for (AbstractFunction fct : functionListIncluded) {
        if (functionListExcluded.contains(fct)) {
          // MS_StateMachine_Configuration toto= null;
        }
      }
    }
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    // TODO Auto-generated method stub
    if (selection instanceof IStructuredSelection) {
      IStructuredSelection ss = (IStructuredSelection) selection;
      for (Object obj : ss.toList()) {
        if (obj instanceof ResultImpl) {
          destinationResult = (Result) obj;
        }
      }
    }
  }

  @Override
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    // TODO Auto-generated method stub
    shell = targetPart.getSite().getShell();
  }

}
