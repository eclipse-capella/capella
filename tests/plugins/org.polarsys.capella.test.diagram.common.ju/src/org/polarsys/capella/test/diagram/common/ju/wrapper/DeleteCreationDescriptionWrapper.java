/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.wrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

/**
 *
 */
public class DeleteCreationDescriptionWrapper extends AbstractCommonToolWrapper {
	
	public DeleteCreationDescriptionWrapper(AbstractToolDescription tool,
			IDiagramCommandFactory commandFactory) {
		super(tool, commandFactory);
	}

	/**
	 * @see org.polarsys.capella.test.common.ju.tool.AbstractCmdToolWrapper#createCommand()
	 */
	@Override
	public Command createCommand() {
		
		Command cmd = UnexecutableCommand.INSTANCE;
		
		if ( isContextOk() ) {
		
			DDiagramElement element = (DDiagramElement) _arguments.get(ArgumentType.TARGET);
			
			boolean isDeletedOnDiagramOnly = 
				((Boolean) _arguments.get(ArgumentType.ON_DIAGRAM_ONLY)).booleanValue()
			;
			
			cmd = isDeletedOnDiagramOnly ?
				_diagramCommandFactory.buildDeleteFromDiagramCommand(element) :
				_diagramCommandFactory.buildDeleteDiagramElement(element)
			;
		}
		
		return cmd;
	}

	/**
	 * @see org.polarsys.capella.test.common.ju.tool.AbstractCmdToolWrapper#getArgumentTypes()
	 */
	@Override
	public List<ArgumentData> getArgumentTypes() {

		List<ArgumentData> ret = null;
		
		if ( null == _argumentTypes ) {
			List<ArgumentData> list = new ArrayList<ArgumentData>();
			Collections.addAll(
				list,
				new AbstractToolWrapper.ArgumentData(ArgumentType.TARGET, DiagramPackage.Literals.DDIAGRAM_ELEMENT),
				new AbstractToolWrapper.ArgumentData(ArgumentType.ON_DIAGRAM_ONLY)
			);
			ret = Collections.unmodifiableList(list);
		} else {
			ret = _argumentTypes;
		}
		
		return ret;
	}

	/**
	 * @see org.polarsys.capella.test.common.ju.tool.AbstractCmdToolWrapper#isContextOk()
	 */
	@Override
	public boolean isContextOk() {
	
		return isArgumentsAreSet();
	}
	
}
