/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.ui.decorators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.RePackage;

public class InstanciationLabelDecorator implements ILabelDecorator {

  @Override
  public void addListener(ILabelProviderListener listener) {
	  // Do nothing
  }

  @Override
  public void dispose() {
	// Do nothing
  }

  @Override
  public boolean isLabelProperty(Object element, String property) {
    return false;
  }

  @Override
  public void removeListener(ILabelProviderListener listener) {
	// Do nothing
  }

  @Override
  public Image decorateImage(Image image, Object element) {
    // null means no decoration
    return null;
  }

  @Override
  /** This method is used for decorating project manager nodes as well as sirius diagram nodes. */
  public String decorateText(String text, Object element) {
    StringBuilder res = new StringBuilder(text);

    List<CatalogElement> elts = new ArrayList<>();
    Collection<EObject> links = EObjectExt.getReferencers((EObject) element, RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET);
    for (EObject link : links) {
      if (link instanceof CatalogElementLink && link.eContainer() != null) {
          CatalogElementLink reLink = (CatalogElementLink) link;
          elts.add(reLink.getSource());
      }
    }
    for (CatalogElement replicableElement : elts) {
      if (replicableElement != null) {
        res.append(" [" + replicableElement.getKind() + "]");
      }
    }
    return res.toString();
  }
}
