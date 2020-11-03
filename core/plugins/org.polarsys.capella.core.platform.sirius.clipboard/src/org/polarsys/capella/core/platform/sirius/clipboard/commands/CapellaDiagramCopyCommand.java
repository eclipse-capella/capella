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
package org.polarsys.capella.core.platform.sirius.clipboard.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.platform.sirius.clipboard.Messages;
import org.polarsys.capella.core.platform.sirius.clipboard.util.LayerUtil;
import org.polarsys.capella.core.platform.sirius.clipboard.util.CapellaDiagramClipboard;
import org.polarsys.capella.core.platform.sirius.clipboard.util.MiscUtil;

/**
 * A command which copies Capella diagrammatic elements using a dedicated clipboard
 */
public class CapellaDiagramCopyCommand extends AbstractResultCommand {

  // INSTANCE PART
  
  // The GMF elements to copy
  private List<? extends View> _toCopyGmf;
  
  /**
   * Constructor
   * @param toCopy_p the elements to copy, in the GMF layer
   */
  public CapellaDiagramCopyCommand(List<? extends EObject> toCopy_p) {
    assert toCopy_p != null;
    _toCopyGmf = MiscUtil.filter(toCopy_p, View.class);
  }
  
  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {
    List<View> toCopy = minimizeByPartTypeContainment(_toCopyGmf);
    CapellaDiagramClipboard.getInstance().copy(toCopy);
  }
  
  /**
   * Return a minimized copy of the given list of Views, where Views whose semantic
   * element is contained in the type of a Part being the semantic element of
   * another View have been removed.
   */
  private List<View> minimizeByPartTypeContainment(List<? extends View> views_p) {
    List<View> result = new ArrayList<View>(views_p);
    List<Part> parts = MiscUtil.filter(LayerUtil.toSemanticLevel(views_p), Part.class);
    List<AbstractType> partTypes = new ArrayList<AbstractType>();
    for (Part part : parts)
      partTypes.add(part.getAbstractType());
    for (View view : views_p) {
      EObject semanticElement = LayerUtil.toSemanticLevel(view);
      if (EcoreUtil.isAncestor(partTypes, semanticElement))
        result.remove(view);
    }
    return result;
  }
  
  @Override
  public String getName() {
    return Messages.CapellaDiagramCopyCommand_Name;
  }
}
