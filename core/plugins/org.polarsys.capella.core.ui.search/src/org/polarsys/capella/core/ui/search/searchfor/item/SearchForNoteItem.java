/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search.searchfor.item;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.core.ui.search.CapellaSearchConstants;

@SuppressWarnings("restriction")
public class SearchForNoteItem extends SearchForClassItem {
  
  protected EAttribute contentAttribute;
  
  public SearchForNoteItem(Object eClass) {
    super(eClass);
    this.contentAttribute = NotationPackage.eINSTANCE.getDescriptionStyle_Description();
  }

  @Override
  public String getText() {
    return CapellaSearchConstants.Note_Label;
  }

  @Override
  public List<Object> getAttributes() {
    return Collections.emptyList();
  }

  @Override
  public boolean covers(Object eObj) {
    return eObj instanceof Shape && ViewType.NOTE.equals(((Shape) eObj).getType());
  }

  @Override
  public Image getImage() {
    return ExtendedImageRegistry.INSTANCE
        .getImage(DiagramUIPlugin.getInstance().getBundle().getEntry("icons/note.gif"));
  }
  
  @Override
  public Object getRelevantSearchData(EObject searchTarget) {    
    return searchTarget.eGet(contentAttribute);
  }
  
  public EAttribute getContentAttribute() {
    return contentAttribute;
  }
    
}
