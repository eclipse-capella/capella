/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.common.ui.viewer;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class DiffTreeContentProvider implements ITreeContentProvider
{

   public Object[] getChildren(Object parentElement)
   {
      return null;
   }

   public Object getParent(Object element)
   {
      return null;
   }

   public boolean hasChildren(Object element)
   {
      return false;
   }

   public Object[] getElements(Object inputElement)
   {
      Object[] retValue = null;

      if (inputElement != null)
      {
         retValue = (Object[]) inputElement;
      }
      return retValue;
   }

   public void dispose()
   {
   }

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
   }

}
