/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.progress.view;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;


import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class InfoProgressShell 
  extends Observable 
  implements Observer {

	Shell sShell = null;
	ProgressBar progressBar = null;
	private CLabel message = null;
	Text textArea = null;
	private Button details = null;
	private Button cancelOrClose = null;
  private Button pauseOrResume = null;

  Composite top;

  private Rectangle bounds;
  private Rectangle topBounds;
  
  protected boolean disposeAtEnd = false;
  protected boolean isStoppable = false;
  protected boolean isRunning = true;

  final Display display;
  
  StringBuffer detailedMessageCache = null;

  Observer model = null;

  /**
   * Default constructor (just for the VE designer)
   */
  public InfoProgressShell() {
    this.display = new Display();
    init();
  } 
  
  
  public InfoProgressShell(Display display, Observer observer) {
    this.display = display;
    this.model = observer;
    this.addObserver(model);
    if (model instanceof Observable) {
      Observable observable = (Observable) model;
      observable.addObserver(this);
    }

    init();
  }
  
  
  private void init() {
    createSShell();
    Rectangle rect = sShell.getBounds();
    topBounds = new Rectangle(rect.x, rect.y, 600, 140);
    bounds = new Rectangle(rect.x, rect.y, topBounds.width, topBounds.height + 300);
    sShell.setBounds(topBounds);
    setDetailMode(false);
    setStoppable(false);
    detailedMessageCache = new StringBuffer();
  }
  
  /**
   * @see org.polarsys.capella.core.common.refinement.merge.ui.progress.view.IProgressListener#setTitle(java.lang.String)
   */
  public void setTitle(String titleString) {
    sShell.setText(titleString);
  }
  
  /**
   * @see org.polarsys.capella.core.common.refinement.merge.ui.progress.view.IProgressListener#getMaximum()
   */
  public int getMaximum() {
    return progressBar.getMaximum();
  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.ui.progress.view.IProgressListener#setMaximum(int)
   */
  public void setMaximum(int valueI) {
    progressBar.setMaximum(valueI);
  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.ui.progress.view.IProgressListener#setSelection(int)
   */
  public void setSelection(int valueI) {
    progressBar.setSelection(valueI);
    float pourcent = ((float) valueI / (float) getMaximum()) * 100.0F;
    sShell.setText( (int)pourcent + "%"); //$NON-NLS-1$
  }
  
  /**
   * @see org.polarsys.capella.core.common.refinement.merge.ui.progress.view.IProgressListener#setStoppable(boolean)
   */
  public void setStoppable(boolean isStoppable) {
    this.isStoppable = isStoppable;
    cancelOrClose.setEnabled(this.isStoppable);
  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.ui.progress.view.IProgressListener#setDisposeAtEnd(boolean)
   */
  public void setDisposeAtEnd(boolean disposeAtEnd) {
    this.disposeAtEnd = disposeAtEnd;
  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.ui.progress.view.IProgressListener#setVisible(boolean)
   */
  public void setVisible(boolean visibleB) {
    sShell.setVisible(visibleB);
  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.ui.progress.view.IProgressListener#setDetailMode(boolean)
   */
  @SuppressWarnings("nls")
  public void setDetailMode(boolean showDetailB) {
    textArea.setVisible(showDetailB);

    Rectangle rect = sShell.getBounds();
    topBounds = new Rectangle(rect.x, rect.y, topBounds.width, topBounds.height);
    bounds = new Rectangle(rect.x, rect.y, bounds.width, bounds.height);

    if(!showDetailB) {
      details.setText(">>");
      sShell.setBounds(topBounds);
    } else {
      details.setText("<<");
      sShell.setBounds(bounds);
    }  
  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.ui.progress.view.IProgressListener#isDetailMode()
   */
  public boolean isDetailMode() {
    return textArea.isVisible();
  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.ui.progress.view.IProgressListener#isDisposed()
   */
  public boolean isDisposed() {
    return sShell.isDisposed();
  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.ui.progress.view.IProgressListener#setMessage(java.lang.String)
   */
  public void setMessage(String messageString) {
    message.setText(messageString);
  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.ui.progress.view.IProgressListener#appendDetailedMessage(java.lang.String)
   */
  public void appendDetailedMessage(String messageString) {
    detailedMessageCache.append(messageString);
    detailedMessageCache.append(System.getProperty("line.separator")); //$NON-NLS-1$
    
    try {
      display.asyncExec(new Runnable() {
        public void run() {
          if (isDisposed())
            return;
          
          synchronized (detailedMessageCache) {
            textArea.append(detailedMessageCache.toString());
            detailedMessageCache.setLength(0);
          }
        }
      });
    } catch (Exception e) {
      // 
    }
	}
  
  /**
   * @see org.polarsys.capella.core.common.refinement.merge.ui.progress.view.IProgressListener#clearDetailedMessage()
   */
  public void clearDetailedMessage() {
    try {
      display.asyncExec(new Runnable() {
        public void run() {
          if (isDisposed())
            return;
          textArea.setText(""); //$NON-NLS-1$
          detailedMessageCache.setLength(0);
        }
      });
    } catch (Exception e) {
      // 
    }
  }
  
  /**
   * @see org.polarsys.capella.core.common.refinement.merge.ui.progress.view.IProgressListener#open()
   */
  public void open() {
    Monitor monitor = display.getMonitors()[0];
    
    Rectangle screen = monitor.getBounds();
    
    Rectangle rect = sShell.getBounds();
    sShell.setBounds(
        (screen.width-rect.width)/2,
        (screen.height-rect.height)/2,
        rect.width,
        rect.height
    );
    
    sShell.open();
    
    // Waiting for ending
    while (!sShell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
  }
  

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.ui.progress.view.IProgressListener#update(java.util.Observable, java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  public void update(Observable o, final Object arg) {
    
    if (display.isDisposed())
      return;
    
    try {
      if (arg instanceof Integer) {
        final Integer progress = (Integer) arg;
        display.asyncExec(new Runnable() {
          @SuppressWarnings("boxing")
          public void run() {
            if (isDisposed())
              return;
            setSelection(progress);
          }
        });
      }
      else {
        if (arg instanceof HashMap) {
          HashMap<String,Object> progress = (HashMap<String,Object>) arg;
          update(progress);
        }
      }
    } catch (Exception e) {
      // TODO: handle exception
    }    
  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.ui.progress.view.IProgressListener#update(java.util.HashMap)
   */
  public void update(final HashMap<String, Object> properties) {
    String detailedMessage = (String) properties.get("DetailedMessage"); //$NON-NLS-1$
    if(detailedMessage!=null)
      appendDetailedMessage(detailedMessage);
    
    display.asyncExec(new Runnable() {
      @SuppressWarnings("boxing")
      public void run() {
        if(isDisposed())
          return;
        
        Integer min = (Integer)properties.get("Min");//$NON-NLS-1$
        if(min != null)
          progressBar.setMinimum(min);
        
        if(isDisposed())
          return;
        
        Integer max = (Integer)properties.get("Max");//$NON-NLS-1$
        if(max != null)
          setMaximum(max);
        
        if(isDisposed())
          return;          
        
        Integer progress = (Integer)properties.get("Progress");//$NON-NLS-1$
        if(progress != null)
          setSelection(progress);
        
        if(isDisposed())
          return;              
        
        Boolean finished = (Boolean)properties.get("Finished");//$NON-NLS-1$
        if(finished != null)
          setFinished(finished);
        
        if(isDisposed())
          return;              
        
        String message = (String)properties.get("Message");//$NON-NLS-1$
        if(message != null)
          setMessage(message);
        
      }
    });    
  }
  
  /**
   * @param finished
   */
  protected void setFinished(boolean finished) {
    if(finished) {
      setSelection(getMaximum());
      
      if(disposeAtEnd) {
        close();
      } else {
        cancelOrClose.setText("Close"); //$NON-NLS-1$
        cancelOrClose.setEnabled(true);      
      }
    }
  }

  @SuppressWarnings("nls")
  private void createTop() {
    GridData gridData15 = new GridData();
    gridData15.grabExcessHorizontalSpace = false;
    gridData15.verticalAlignment = GridData.BEGINNING;
    gridData15.horizontalAlignment = GridData.FILL;
    GridData gridData14 = new GridData();
    gridData14.grabExcessHorizontalSpace = true;
    gridData14.verticalAlignment = GridData.CENTER;
    gridData14.horizontalSpan = 3;
    gridData14.horizontalAlignment = GridData.FILL;
    GridData gridData1 = new GridData();
    gridData1.widthHint = 100;
    gridData1.verticalAlignment = GridData.CENTER;
    gridData1.grabExcessHorizontalSpace = false;
    gridData1.grabExcessVerticalSpace = false;
    gridData1.horizontalAlignment = GridData.END;
    GridData gridData = new GridData();
    gridData.heightHint = -1;
    gridData.horizontalAlignment = GridData.END;
    gridData.verticalAlignment = GridData.CENTER;
    gridData.grabExcessHorizontalSpace = true;
    gridData.widthHint = 100;
    GridData gridData4 = new GridData();
    gridData4.horizontalAlignment = GridData.END;
    gridData4.grabExcessHorizontalSpace = false;
    gridData4.widthHint = 100;
    gridData4.verticalAlignment = GridData.CENTER;
    GridData gridData3 = new GridData();
    gridData3.verticalAlignment = GridData.CENTER;
    gridData3.grabExcessHorizontalSpace = true;
    gridData3.horizontalSpan = 3;
    gridData3.horizontalAlignment = GridData.FILL;
    GridLayout gridLayout1 = new GridLayout();
    gridLayout1.numColumns = 3;
    top = new Composite(sShell, SWT.NONE);
    top.setLayout(gridLayout1);
    top.setLayoutData(gridData15);
    
    
    message = new CLabel(top, SWT.NONE);
    message.setText("");
    message.setLayoutData(gridData14);
    progressBar = new ProgressBar(top, SWT.SMOOTH);
    progressBar.setLayoutData(gridData3);
    details = new Button(top, SWT.NONE);
    details.setText(">>");
    details.setLayoutData(gridData);
    pauseOrResume = new Button(top, SWT.NONE);
    pauseOrResume.setText("Pause");
    pauseOrResume.setLayoutData(gridData4);
    cancelOrClose = new Button(top, SWT.NONE);
    cancelOrClose.setText("Cancel");
    cancelOrClose.setLayoutData(gridData1);
    pauseOrResume.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
      @Override
      public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
        switchActive();
      }
    });
    cancelOrClose.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
      @Override
      public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
        close();
        
      }
    });
    details.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
      @Override
      public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
        switchDetail();
      }
    });
  }

  /**
   * 
   */
  protected void close() {
    this.deleteObserver(model);
    if (model instanceof Observable) {
      Observable observable = (Observable) model;    
      observable.deleteObserver(this);    
    }
    sShell.dispose();
  }
  
  /**
   * 
   */
  @SuppressWarnings("nls")
  protected void switchActive() {
    isRunning = !isRunning;
    
    setChanged();
    notifyObservers();
    
    if(isRunning) {
      pauseOrResume.setText("Pause");
    } else {
      pauseOrResume.setText("Resume");
    }
  }

  /**
   * 
   */
  protected void switchDetail() {
    setDetailMode(!isDetailMode());
  }
  
  /**
   * This method initializes sShell
   */
  @SuppressWarnings("nls")
  private void createSShell() {
  	GridData gridData2 = new GridData();
  	gridData2.grabExcessHorizontalSpace = true;
  	gridData2.verticalAlignment = GridData.FILL;
  	gridData2.grabExcessVerticalSpace = true;
  	gridData2.horizontalAlignment = GridData.FILL;
  	sShell = new Shell(SWT.ON_TOP | SWT.CLOSE | SWT.RESIZE | SWT.APPLICATION_MODAL);
  	sShell.setText("");
  	sShell.setLayout(new GridLayout());
  
    createTop();
    textArea = new Text(sShell, SWT.MULTI | SWT.V_SCROLL | SWT.BORDER | SWT.READ_ONLY);
  	textArea.setEditable(false);
  	textArea.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
  	textArea.setLayoutData(gridData2);
  	textArea.setEnabled(true);
  }
}
