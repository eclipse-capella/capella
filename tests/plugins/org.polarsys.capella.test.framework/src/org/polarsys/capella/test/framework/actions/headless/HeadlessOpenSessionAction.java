/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.framework.actions.headless;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.polarsys.capella.core.sirius.ui.actions.OpenSessionAction;

/**
 * @author Erwan Brottier
 */
public class HeadlessOpenSessionAction extends OpenSessionAction {

    protected List<IFile> sessionFiles = new ArrayList<IFile>();

    public HeadlessOpenSessionAction(List<IFile> sessionFiles_p) {
        super(false);
        sessionFiles = sessionFiles_p;
    }

    @Override
    public IStructuredSelection getStructuredSelection() {
        return new StructuredSelection(sessionFiles);
    }

}
