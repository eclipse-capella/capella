/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.interfaces.generateInterfaces;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.projection.common.CommonRule;
import org.polarsys.capella.core.projection.interfaces.InterfaceGeneration;
import org.polarsys.capella.core.tiger.ITransfo;

public abstract class InterfaceGenerationRule extends CommonRule {

  public InterfaceGenerationRule(EClass sourceType, EClass targetType) {
    super(sourceType, targetType);
  }

  /*
   * Always call transformElement()
   */
  @Override
  protected boolean transformIsRequired(EObject element, ITransfo transfo) {
    return true;
  }

  @Override
  /*
   * Always call transformElement()
   */
  public boolean requireTransformation(EObject element, ITransfo transfo) {
    return true;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void firstAttach(EObject element_p, ITransfo transfo_p) {
    // nothing to do, interfaces are attached during transformElement
  }

  @Override
  protected void doAddContainer(EObject element_p, List<EObject> result_p) {
    //Nothing to do
  }

  @Override
  protected void doGoDeep(EObject element_p, List<EObject> result_p) {
    //Nothing to do
  }

  @Override
  public void update_(EObject e, ITransfo transfo) {
    // nothing, everything is handled during transformElement, which is always called
  }

  protected final Object transformElement(EObject element, ITransfo t){
    
    Collection<InterfaceInfo> infos = transformToInterfaceInfo(element, t);
    InterfaceGeneration.getResult(t).getInterfaceInfos().addAll(infos);

    if (!t.isDryRun()){
      for (InterfaceInfo i : infos){
        i.getInterface(true);
        i.update(InterfaceGeneration.getPreferences(t));
      }
    }

    return null;
  }

  protected abstract Collection<InterfaceInfo> transformToInterfaceInfo(EObject element, ITransfo t);
  
}
