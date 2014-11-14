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
package org.polarsys.capella.common.linkedtext.ui;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.hyperlink.AbstractHyperlinkDetector;
import org.eclipse.jface.text.hyperlink.IHyperlink;


public abstract class LinkedTextHyperlinkDetector extends AbstractHyperlinkDetector {

  /**
   * {@inheritDoc}
   */
  @Override
  public IHyperlink[] detectHyperlinks(ITextViewer textViewer_p, IRegion region_p, boolean canShowMultipleHyperlinks_p) {
    LinkedTextDocument am = (LinkedTextDocument) textViewer_p.getDocument();
    Collection<IHyperlink> links = new ArrayList<IHyperlink>();
    for (LinkedTextHyperlink hl : am.getHyperlinks()){
      if (hl.getTarget() != null){
        if (hl.overlapsWith(region_p.getOffset(), region_p.getLength())){
          appendLinksFor(hl, links);
          break;
        }
      }
    }
    if (links.size() > 0){
      return links.toArray(new IHyperlink[links.size()]);
    }
    return null;
  }

  protected abstract void appendLinksFor(LinkedTextHyperlink hl, Collection<IHyperlink> links_p);

}
