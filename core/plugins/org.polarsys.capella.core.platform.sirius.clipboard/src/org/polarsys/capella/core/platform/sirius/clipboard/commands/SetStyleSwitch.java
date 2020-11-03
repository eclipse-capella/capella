/*******************************************************************************
 * Copyright (c) 2007, 2020 THALES GLOBAL SERVICES and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.clipboard.commands;

import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.util.DiagramSwitch;
import org.eclipse.sirius.viewpoint.Style;

/**
 * Copied from org.eclipse.sirius.diagram.business.internal.metamodel.helper.StyleHelper$SetStyleSwitch
 * (class was private)
 *
 * Switch to set the style of a diagram element.
 * 
 * @author ymortier
 */
public class SetStyleSwitch extends DiagramSwitch<Object> {

  /**
   * The style to affect.
   */
  private final Style style;

  /**
   * 
   */
  public SetStyleSwitch() {
    this.style = null;
  }
  
  /**
   * Default constructor.
   * 
   * @param style
   *          the style to affect, should be <code>null</code>.
   */
  public SetStyleSwitch(final Style style) {
    this.style = style;
  }

  @Override
  public Object caseDEdge(final DEdge object) {
    object.setOwnedStyle((EdgeStyle) this.style);
    return null;
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseDNode(org.eclipse.sirius.diagram.DNode)
   */
  @Override
  public Object caseDNode(final DNode object) {
    object.setOwnedStyle((NodeStyle) this.style);
    return null;
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseDNodeListElement(org.eclipse.sirius.diagram.DNodeListElement)
   */
  @Override
  public Object caseDNodeListElement(final DNodeListElement object) {
    object.setOwnedStyle((NodeStyle) this.style);
    return null;
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseDNodeContainer(org.eclipse.sirius.diagram.DNodeContainer)
   */
  @Override
  public Object caseDNodeContainer(final DNodeContainer object) {
    object.setOwnedStyle((ContainerStyle) this.style);
    return null;
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseDNodeList(org.eclipse.sirius.diagram.DNodeList)
   */
  @Override
  public Object caseDNodeList(final DNodeList object) {
    object.setOwnedStyle((ContainerStyle) this.style);
    return null;
  }

}
