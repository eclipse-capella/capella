package org.polarsys.capella.core.platform.sirius.ui.navigator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;

public class EditorPart2 extends EditorPart {

  public EditorPart2() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public void doSave(IProgressMonitor monitor) {
    // TODO Auto-generated method stub

  }

  @Override
  public void doSaveAs() {
    // TODO Auto-generated method stub

  }

  @Override
  public void init(IEditorSite site, IEditorInput input) throws PartInitException {
    setSite(site);
    setInput(input);
    System.out.println(site.getWorkbenchWindow().getActivePage().getEditors());
    
  }

  @Override
  public boolean isDirty() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isSaveAsAllowed() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void createPartControl(Composite parent) {
    Browser b = new Browser(parent, SWT.EDGE);
    b.setUrl("file:/"+((FileEditorInput)getEditorInput()).getFile().getLocation().toString());
  }

  @Override
  public void setFocus() {
    // TODO Auto-generated method stub

  }

}
