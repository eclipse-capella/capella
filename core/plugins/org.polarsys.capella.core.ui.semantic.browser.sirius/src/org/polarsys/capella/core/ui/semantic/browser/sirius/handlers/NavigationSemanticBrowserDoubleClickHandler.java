package org.polarsys.capella.core.ui.semantic.browser.sirius.handlers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.EObjectWrapper;
import org.polarsys.capella.core.commands.preferences.ui.sirius.DoubleClickBehaviourUtil;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.OpenRelatedDiagramAction;
import org.polarsys.capella.core.ui.semantic.browser.handler.DefaultSemanticBrowserDoubleClickHandler;
import org.polarsys.capella.core.ui.semantic.browser.sirius.actions.DiagramOpenAction;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;

public class NavigationSemanticBrowserDoubleClickHandler extends DefaultSemanticBrowserDoubleClickHandler{
  
  @Override
  public void handle(SemanticBrowserView view, DoubleClickEvent event, Object selectedElement) {
    //Get selection of currently selected viewer
    //Right now this is the only way to get the selection from Referenced or Referencing viewer
    IStructuredSelection selection = (IStructuredSelection) view.getSite().getSelectionProvider().getSelection();

    if (!selection.isEmpty()) {      
      if(selection.size() == 1 ){
        if(isCtrlKeyPressed()) { 
          //If CTRL is pressed on double-click on a single element, it shall be put as the current element
          super.handle(view, event, selectedElement);
        } else {
          //If it wasn't pressed, run navigation action
          runAction(view, event, selectedElement);
        }       
      }else{
        //If multiple elements in the selection, run action on each of them
        for(Object element : selection.toList()) {
          runAction(view, event, element);          
        }
      }
    }
  }
  
  protected void runAction(SemanticBrowserView view, DoubleClickEvent event, Object selectedElement) {
    if (selectedElement instanceof EObjectWrapper) {
      selectedElement = ((EObjectWrapper) selectedElement).getElement();
    }   
    if(! (selectedElement instanceof EObject)) {
      //Not handled
      return; 
    }
    
    EObject elementAsEObject = (EObject) selectedElement;
    if(shouldOpenDiagram(elementAsEObject)) {
      openDiagram(view, (DRepresentationDescriptor)elementAsEObject);
    }else {
      if(shouldNavigate(elementAsEObject)) {
        openRelatedDiagrams(elementAsEObject);
      }else{ 
        // Should open wizard
        super.runAction(view, event, elementAsEObject);
      }      
    }
                 
    
  }
  
  /**
   * Returns true if double-click on selectedElement should execute the navigation action
   * @param selectedElement
   * @return
   */
  public boolean shouldNavigate(EObject selectedElement) {      
    return DoubleClickBehaviourUtil.INSTANCE.shouldOpenRelatedDiagramsOnDoubleClick(selectedElement);
  }
  
  /**
   * Returns true if double-click on selectedElement should open the properties wizard
   * @param selectedElement
   * @return
   */
  public boolean shouldOpenPropertyWizard(EObject selectedElement) {
    if(shouldOpenDiagram(selectedElement))
      return false;
   return !shouldNavigate(selectedElement);  
  }
  
  /**
   * Returns true if double-click on selectedElement should open the diagram
   * @param selectedElement
   * @return
   */
  public boolean shouldOpenDiagram(EObject selectedElement) {
    return selectedElement instanceof DRepresentationDescriptor;
  }
  
  /**
   * Open diagram of given diagram descriptor
   * @param diagramToOpen
   */
  protected void openDiagram(SemanticBrowserView view, DRepresentationDescriptor diagramToOpen) {
    DiagramOpenAction action = new DiagramOpenAction();
    // Open related diagram editor.
    action.init(view);
    action.selectionChanged(null, new StructuredSelection(diagramToOpen));
    action.run(null);
  }

  /**
   * Run the OpenRelatedDiagramAction on selected element 
   * @param selectedElement
   */
  protected void openRelatedDiagrams(EObject selectedElement) {   
    OpenRelatedDiagramAction action = new OpenRelatedDiagramAction(selectedElement);
    action.run();       
  }

}
