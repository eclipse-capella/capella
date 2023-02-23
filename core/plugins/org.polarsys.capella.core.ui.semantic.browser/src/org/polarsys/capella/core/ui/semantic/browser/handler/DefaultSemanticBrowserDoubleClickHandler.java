package org.polarsys.capella.core.ui.semantic.browser.handler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;

public class DefaultSemanticBrowserDoubleClickHandler extends AbstractSemanticBrowserDoubleClickHandler{

  @Override
  public void handle(SemanticBrowserView view,DoubleClickEvent event, Object selectedElement) {    
    if (selectedElement instanceof EObject) {
      if (isCtrlKeyPressed()) {
        if (view.getRootElement() != selectedElement) {
          // CTRL is pressed, let's navigate...
          view.setInput(selectedElement);
          // Set and reveal the focused element.
          view.getCurrentViewer().setSelection(new StructuredSelection(selectedElement), true);
        }
      } else {
        runAction(view, event, selectedElement);
      }
    }
  }
  
  protected void runAction(SemanticBrowserView view, DoubleClickEvent event, Object selectedElement) {
    //Open properties wizard on selected element
    CapellaUIPropertiesPlugin.getDefault().openWizard(event, (EObject) selectedElement);
  }
}
