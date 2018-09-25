/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package ms.design;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtensionProvider;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.Style;

import ms.configuration.services.cs.CsConfigurationServices;
import ms.configuration.services.cs.Images;

public class MsRefreshExtensionProvider implements IRefreshExtensionProvider, IRefreshExtension {

  private static RGBValues conflictColor= RGBValues.create(255, 165, 0);
  private static RGBValues excludeColor= RGBValues.create(220, 220, 220);
  private static RGBValues excludeForegroundColor = RGBValues.create(200, 200, 200);
  private static RGBValues excludeBackroundColor = RGBValues.create(200, 200, 200);
  private static RGBValues excludeLabelColor = RGBValues.create(120, 120, 120);
  
  public MsRefreshExtensionProvider() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public boolean provides(DDiagram diagram) {
    return CsConfigurationServices.isConfigurationsLayerActive(diagram);
  }

  @Override
  public IRefreshExtension getRefreshExtension(DDiagram viewPoint) {
    return this;
  }

  @Override
  public void beforeRefresh(DDiagram dDiagram) {
   
  }

  private void applyConflict(DDiagramElement element) {
    
    Style style = element.getStyle();
   
    if (style instanceof Square) {
      ((Square) style).setColor(conflictColor);
    } else if (style instanceof EdgeStyle) {
      ((EdgeStyle) style).setStrokeColor(conflictColor);
    } else {
      applyExclude(element);
    }
  }
  
  private void applyExclude(DDiagramElement element) {
    
    Style style = element.getStyle();
    if (style instanceof BasicLabelStyle) {
      ((BasicLabelStyle)style).setLabelColor(excludeLabelColor);
    }

    if (style instanceof FlatContainerStyle) {
      ((FlatContainerStyle)style).setBackgroundColor(excludeBackroundColor);
      ((FlatContainerStyle)style).setForegroundColor(excludeForegroundColor);
    } else if (style instanceof Square) {
      ((Square) style).setColor(excludeColor);
    } else if (style instanceof EdgeStyle) {
      ((EdgeStyle) style).setStrokeColor(excludeColor);
      if (((EdgeStyle)style).getCenterLabelStyle() != null){
        ((EdgeStyle) style).getCenterLabelStyle().setLabelColor(excludeLabelColor);
      }
    } else if (style instanceof WorkspaceImage) {
      ((WorkspaceImage) style).setWorkspacePath(Images.getImagePath(element.getTarget(), element));
    }
  }
  
  private void refreshStyle(DDiagramElement element, CsConfigurationServices cs) {

    CsConfigurationServices.CSConfigurationStyle style = cs.refreshStyle(element.getTarget(), element);

    if (CsConfigurationServices.isConsistentIncludeRequired()) {

      // all configs must include the element, otherwise it's greyed out
      
      if (style.hasClass("excluded")) {
      
        if (CsConfigurationServices.isMarkConflictingInclusions() && style.hasClass("included")) {
          
          applyConflict(element);
          
        } else {

          applyExclude(element);
         
        }
        
      }
      
    } else {
     
      // at least one config must include the element, otherwise it's greyed out
      if (!style.hasClass("included") && style.hasClass("excluded")) {
        
        applyExclude(element);
        
      }

    }

   }
  

  @Override
  public void postRefresh(DDiagram dDiagram) {

    CsConfigurationServices cs = new CsConfigurationServices();

    for (DDiagramElement e : dDiagram.getDiagramElements()) {
      if (e instanceof DDiagramElement) {
        refreshStyle(e, cs);
      }
    }
  }
}
