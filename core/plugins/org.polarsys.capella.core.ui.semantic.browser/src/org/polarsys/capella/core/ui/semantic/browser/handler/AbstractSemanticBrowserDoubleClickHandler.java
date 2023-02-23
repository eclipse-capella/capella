package org.polarsys.capella.core.ui.semantic.browser.handler;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;

public abstract class AbstractSemanticBrowserDoubleClickHandler {

  /**
   * Indicates whether ctrl key is pressed or not
   * this value is updated when double click occurs in the view
   */
  protected boolean isCtrlKeyPressed = false;
  
  public void setControlKeyPressed(boolean value) {
    isCtrlKeyPressed = value;
  }
  
  /**
   * Handle double-click event, on the selectedElement on the given view
   * @param view
   * @param event
   * @param selectedElement
   */
  public void handle(SemanticBrowserView view, DoubleClickEvent event, Object selectedElement) {
    
  }
  
  public boolean isCtrlKeyPressed() {
    return isCtrlKeyPressed;
  }

}
