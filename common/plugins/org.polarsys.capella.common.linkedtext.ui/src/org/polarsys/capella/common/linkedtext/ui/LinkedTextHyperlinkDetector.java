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
  public IHyperlink[] detectHyperlinks(ITextViewer textViewer, IRegion region, boolean canShowMultipleHyperlinks) {
    LinkedTextDocument am = (LinkedTextDocument) textViewer.getDocument();
    Collection<IHyperlink> links = new ArrayList<IHyperlink>();
    for (LinkedTextHyperlink hl : am.getHyperlinks()){
      if (hl.getTarget() != null){
        if (hl.overlapsWith(region.getOffset(), region.getLength())){
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

  protected abstract void appendLinksFor(LinkedTextHyperlink hl, Collection<IHyperlink> links);

}
