package org.polarsys.capella.test.business.queries.ju.views;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.edit.ui.provider.UnwrappingSelectionProvider;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class BqViewPart extends ViewPart {

  public BqViewPart() {
  }
  
  @Override
  public void createPartControl(Composite parent) {
    TreeViewer viewer = new TreeViewer(parent);
    viewer.setContentProvider(new BqContentProvider());
    viewer.setLabelProvider(new BqLabelProvider());
    
    viewer.setAutoExpandLevel(4);
    
    ISelectionService service = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getService(ISelectionService.class);
    service.addSelectionListener(new ISelectionListener() {
      
      @Override
      public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        if (part != BqViewPart.this && selection instanceof IStructuredSelection) {
         List<?> files = (List)((IStructuredSelection)selection).toList().stream().filter(IFile.class::isInstance).collect(Collectors.toList());
         if (files.size()>0 && !viewer.getTree().isDisposed()) {
           viewer.setInput(files);
         }
        }
      }
    });
    MenuManager menu = new MenuManager();
    viewer.getTree().setMenu(menu.createContextMenu(viewer.getTree()));
    getSite().registerContextMenu(menu, viewer);
    getSite().setSelectionProvider(viewer);
  }

  @Override
  public void setFocus() {
    
  }

}
