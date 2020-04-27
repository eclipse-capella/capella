/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.re.ui.subcommands.handlers;

import java.util.Collection;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

/**
 *
 */
public class DeleteChildrenHandler extends DeleteHandler {

  @Override
  protected void delete(Collection<EObject> currentValue, Collection<Object> selectiona) {
    for (Object obj : selectiona) {
      if (obj instanceof EObject) {
        TreeIterator<EObject> childs = ((EObject) obj).eAllContents();
        while (childs.hasNext()) {
          currentValue.remove(childs.next());
        }
      }
      currentValue.remove(obj);
    }
  }
}
