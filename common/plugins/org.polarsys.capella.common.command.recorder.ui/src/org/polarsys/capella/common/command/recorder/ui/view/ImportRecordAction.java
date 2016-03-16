/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.command.recorder.ui.view;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import org.polarsys.capella.common.command.recorder.core.output.OutputHelper;

/**
 * Record action importer.
 */
public class ImportRecordAction extends Action implements IMenuCreator {

  /** the target record view */
  private final AbstractRecorderView _recorderView;
  
  /** the tool bar */
	private Menu _toolbarMenu = null;

	/** actions */
	private Collection<Action> _actions;
	
	private File _selected = null;
	
	/**
	 * The load record action
	 *
	 */
  class RecordAction extends Action {
    private File _file;

    /** Accessor on file */
    public File getFile() {
      return _file;
    }
    
    
    /**
     * Constructor
     */
    public RecordAction(File file) {
      
      super(file.getName(), IAction.AS_CHECK_BOX);

      _file = file;
      
      String id = file.getPath(); // An unique Id for this action
      
      setId(id);
      
      setChecked(false);
    }
        
    @SuppressWarnings("synthetic-access")
    @Override
    public void run() {

      boolean result = _recorderView.loadRecord(_file);
      
      if (result) {
        for ( Iterator<Action> it = _actions.iterator(); it.hasNext();) {
          it.next().setChecked(false);
        }
        _selected = getFile();
        setChecked(true);
      }
      
      return;
    }
    
  }
  
  /**
   * constructor
   */
	public ImportRecordAction(AbstractRecorderView recordView, String text) {
		super(text);
		_recorderView = recordView;
		_actions = new HashSet<Action>();
		
		setMenuCreator(this);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
  public void run() {
	  String path = _recorderView.selectRecord();
	  
	  if (null != path) {	    
	    _selected = new Path(path).toFile();
	  }
	  
	  return;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Control)
	 */
	public Menu getMenu(Control parent) {
	  
	  if ( null != _toolbarMenu) {
	    dispose();
	  }
	  
	  _toolbarMenu = new Menu(parent);
		createMenuItems(_toolbarMenu);
		
		return _toolbarMenu;
	}

  /**
   * {@inheritDoc}
   */
  public Menu getMenu(Menu parent) {
    return null;
  }

	/**
	 * Builds menu of ImportLogAction actions from log files provided by LogFilesManager.
	 * 
	 * @see IMenuCreator#getMenu(Control)
	 */
	protected void createMenuItems(Menu menu) {

	  _actions.clear();
	  
	  //
    // We hereby suppose a one level storage
    //
    File root = OutputHelper.getRootDirectoryForStorage();
    List<File> subdirectory = OutputHelper.getDirectSubDirectory(root);
		
    for (File dir: subdirectory) {
      
      List<File> files = OutputHelper.getRecordFiles(dir);
      
      // we only add sub-menu for filled folder
      Action action = null;
      if ( !files.isEmpty() ) {
        IMenuManager subMenu = new MenuManager(dir.getName());
        subMenu.fill(menu, -1);        
        for (File file: files) {
          action = new RecordAction(file);
          subMenu.add(action);          
          if ( _selected != null && _selected.equals(file) ) {
            action.setChecked(true);
          }
        }
      }
    }
    
    return;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.IMenuCreator#dispose()
	 */
	public void dispose() {
		if (_toolbarMenu != null) {
		  
		  MenuItem[] items = _toolbarMenu.getItems();
		  for (int i = 0; i < items.length; i++) {
		    items[i].dispose();
		  }
		  
			_toolbarMenu.dispose();
			_toolbarMenu = null;
			
			_actions.clear();
		}
		
		return;
	}

}
