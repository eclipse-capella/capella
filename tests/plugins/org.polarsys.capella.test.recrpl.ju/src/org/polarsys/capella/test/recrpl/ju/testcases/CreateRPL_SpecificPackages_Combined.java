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
package org.polarsys.capella.test.recrpl.ju.testcases;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.re.handlers.location.CapellaModelSkeleton;

public class CreateRPL_SpecificPackages_Combined extends CreateRPL_SpecificPackages {

  @Override
  protected Collection<EObject> getRecElements(CapellaModelSkeleton project){

   Collection<EObject> elems = new ArrayList<EObject>();

   elems.addAll(new CreateRPL_SpecificPackages_Data().getRecElements(project));
   elems.addAll(new CreateRPL_SpecificPackages_LA().getRecElements(project));
   elems.addAll(new CreateRPL_SpecificPackages_SA().getRecElements(project));
   elems.addAll(new CreateRPL_SpecificPackages_PA().getRecElements(project));
   elems.addAll(new CreateRPL_SpecificPackages_OA().getRecElements(project));

   return elems;
  }

}
