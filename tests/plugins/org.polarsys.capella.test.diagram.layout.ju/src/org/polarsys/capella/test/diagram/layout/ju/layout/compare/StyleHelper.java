/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.layout.ju.layout.compare;

import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.FlatContainerStyle;

public class StyleHelper {

  public static String BEGIN_COLOR = "beginColor";
  public static String END_COLOR = "endColor";
  public static String CENTERED_COLOR = "centeredColor";

  public static String TEXT_ALIGNMENT = "textAlignment";
  public static String STROKE_COLOR = "strokeColor";
  public static String BACKGROUND_COLOR = "backgroundColor";
  public static String FOREGROUND_COLOR = "foregroundColor";
  public static String COLOR = "foregroundColor";
  public static String BORDERED_COLOR = "borderColor";
  public static final String EDGE_ROUTING = "edgeRouting";

  public static String getBeginColor(DEdge anElement) {
    try {
      return anElement.getOwnedStyle().getBeginLabelStyle().getLabelColor().toString();
    } catch (Exception e) {
      return null;
    }
  }

  public static String getStrokeColor(DEdge anElement) {
    try {
      return anElement.getOwnedStyle().getStrokeColor().toString();
    } catch (Exception e) {
      return null;
    }
  }

  public static String getCenteredColor(DEdge anElement) {
    try {
      return anElement.getOwnedStyle().getCenterLabelStyle().getLabelColor().toString();
    } catch (Exception e) {
      return null;
    }
  }

  public static String getEndColor(DEdge anElement) {
    try {
      return anElement.getOwnedStyle().getEndLabelStyle().getLabelColor().toString();
    } catch (Exception e) {
      return null;
    }
  }

  public static String getTextAlignment(ShapeStyle currentNote) {
    return ShapeHelper.getNoteTextAlignment(currentNote);
  }

  public static String getBorderedColor(AbstractDNode anElement) {
    try {
      return ((BorderedStyle) anElement.getStyle()).getBorderColor().toString();
    } catch (Exception e) {
      return null;
    }
  }

  public static String getBackgroundColor(AbstractDNode anElement) {
    try {
      return ((FlatContainerStyle) anElement.getStyle()).getBackgroundColor().toString();
    } catch (Exception e) {
      return null;
    }
  }

  public static String getForegroundColor(AbstractDNode anElement) {
    try {
      return ((FlatContainerStyle) anElement.getStyle()).getForegroundColor().toString();
    } catch (Exception e) {
      return null;
    }
  }

  public static String getColor(AbstractDNode anElement) {
    try {
      return (anElement.getStyle().eGet(anElement.getStyle().eClass().getEStructuralFeature("color"))).toString();
    } catch (Exception e) {
      return null;
    }
  }

  public static String getRouting(DEdge anElement) {
    try {
      return (anElement.getOwnedStyle().getRoutingStyle().getLiteral());
    } catch (Exception e) {
      return null;
    }
  }
}
