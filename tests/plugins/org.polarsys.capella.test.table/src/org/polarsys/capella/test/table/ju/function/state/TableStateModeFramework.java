/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.table.ju.function.state;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.table.ju.utils.AbstractTableToolStep;
import org.polarsys.capella.test.table.ju.utils.CreateCellFromIntersectionCommand;
import org.polarsys.capella.test.table.ju.utils.TableTestFramework;
import org.polarsys.capella.test.table.ju.utils.TableTestingHelper;

public abstract class TableStateModeFramework extends TableTestFramework {
	protected String modelName = "SF-OA";
	protected Session session;
	protected SessionContext context;

	public static final String SF1 = "69f5c20e-5568-496a-ab5d-52e1bed91b49"; //$NON-NLS-1$
	public static final String SF11 = "3f6d7a1b-3219-4677-8483-1596d74b1490"; //$NON-NLS-1$
	public static final String SF2 = "5bc4bdb3-4002-4d58-9886-4d32911264b3"; //$NON-NLS-1$
	public static final String STATE_1 = "5c468906-f2ea-4cea-8269-f9c471090a34"; //$NON-NLS-1$
	public static final String STATE_11 = "ac3bae35-3104-4be6-8268-492516c9d3ca"; //$NON-NLS-1$
	public static final String STATE_2 = "4f6e1a65-235c-446b-a625-1a69044e50b6"; //$NON-NLS-1$
	public static final String MODE_1 = "7688b258-3b48-446c-9bcc-bb2aa83a2bbc"; //$NON-NLS-1$
	public static final String MODE_11 = "0d40aed2-e162-40d6-8ed1-bda32fc9f8b6"; //$NON-NLS-1$
	public static final String MODE_2 = "20efbb96-db7f-4696-b117-9e16295762f0"; //$NON-NLS-1$
	public static final String CAPABILITY_1 = "20be470c-8c1c-4c0b-9baf-71a5b4ec240e"; //$NON-NLS-1$
	public static final String CAPABILITY_2 = "3d54bd19-1842-4562-a0d2-44a20e705dcc"; //$NON-NLS-1$
	public static final String FUNCTIONALCHAIN_1 = "094ad7da-8335-451e-9b1a-9c0019e0b3e6"; //$NON-NLS-1$
	public static final String SYSTEM_ANALYSIS = "e348c8d5-b00e-4147-a4a8-e1450fef6a4d"; //$NON-NLS-1$

	protected SystemFunction _sf1;
	protected SystemFunction _sf11;
	protected SystemFunction _sf2;

	protected Capability _cp1;
	protected Capability _cp2;
	
	protected FunctionalChain _fc1;

	protected State _state1;
	protected State _state11;
	protected State _state2;

	protected State _mode1;
	protected State _mode11;
	protected State _mode2;

	// Error Messages
	protected String stateModeErrMsg = "State '{0}' is not available in '{1}'";

	protected void init() {
		session = getSession(modelName);
		context = new SessionContext(session);

		_sf1 = context.getSemanticElement(SF1);
		_sf11 = context.getSemanticElement(SF11);
		_sf2 = context.getSemanticElement(SF2);

		_cp1 = context.getSemanticElement(CAPABILITY_1);
		_cp2 = context.getSemanticElement(CAPABILITY_2);
		
		_fc1 = context.getSemanticElement(FUNCTIONALCHAIN_1);

		_state1 = context.getSemanticElement(STATE_1);
		_state11 = context.getSemanticElement(STATE_11);
		_state2 = context.getSemanticElement(STATE_2);

		_mode1 = context.getSemanticElement(MODE_1);
		_mode11 = context.getSemanticElement(MODE_11);
		_mode2 = context.getSemanticElement(MODE_2);

		_cp1 = context.getSemanticElement(CAPABILITY_1);
		_cp2 = context.getSemanticElement(CAPABILITY_2);
	}

	@Override
	public List<String> getRequiredTestModels() {
		return Arrays.asList(modelName);
	}

	public void createCellValue(DTable table, EObject lineObj, EObject colObj) {
		new AbstractTableToolStep(context, DescriptionPackage.Literals.CREATE_CELL_TOOL, table) {
			@Override
			protected void initToolArguments() {
				DLine line = TableTestingHelper.getLine(table, lineObj);
				DColumn column = TableTestingHelper.getColumn(table, colObj);
				// Cell creation in the intersection cell otherwise there is no
				// cell
				CreateCellFromIntersectionCommand cellCommand = new CreateCellFromIntersectionCommand(table, line,
						column, IToolNameConstants.TABLE_TOOL_CREATE_CELL_VALUE);
				cellCommand.execute();

				EObject container = TableTestingHelper.getIntersectionCell(line, column);

				String tableCellMask = IToolNameConstants.TABLE_TOOL_CREATE_CELL_VALUE;

				_toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, container);
				_toolWrapper.setArgumentValue(ArgumentType.TABLE_CELL_MASK, tableCellMask);
			}

			@Override
			protected void postRunTest() {
				super.postRunTest();
				DLine line = TableTestingHelper.getLine(table, lineObj);
				DColumn column = TableTestingHelper.getColumn(table, colObj);
				DCell cell = TableTestingHelper.getIntersectionCell(line, column);

				assertEquals(cell.getLabel(), "X");

				assertTrue(lineObj instanceof State);
				if (colObj instanceof AbstractFunction) {
					AbstractFunction function = (AbstractFunction) colObj;
					assertTrue(
							NLS.bind(stateModeErrMsg, ((State) lineObj).getName(),
									function.getClass().getName() + " " + function.getName()),
							function.getAvailableInStates().contains(lineObj));
				}
				if (colObj instanceof AbstractCapability) {
					AbstractCapability capability = (AbstractCapability) colObj;
					assertTrue(
							NLS.bind(stateModeErrMsg, ((State) lineObj).getName(),
									capability.getClass().getName() + " " + capability.getName()),
							capability.getAvailableInStates().contains(lineObj));
				}
				if (colObj instanceof FunctionalChain) {
					FunctionalChain fc = (FunctionalChain) colObj;
					assertTrue(
							NLS.bind(stateModeErrMsg, ((State) lineObj).getName(),
									fc.getClass().getName() + " " + fc.getName()),
							fc.getAvailableInStates().contains(lineObj));
				}
			}

			@Override
			public Object getResult() {
				return null;
			}
		}.run();
	}

	public void deleteCellValue(DTable table, EObject lineObj, EObject colObj) {
		new AbstractTableToolStep(context, DescriptionPackage.Literals.CREATE_CELL_TOOL, table) {
			@Override
			protected void initToolArguments() {
				DLine line = TableTestingHelper.getLine(table, lineObj);
				DColumn column = TableTestingHelper.getColumn(table, colObj);
				// Cell creation in the intersection cell otherwise there is no
				// cell
				CreateCellFromIntersectionCommand cellCommand = new CreateCellFromIntersectionCommand(table, line,
						column, IToolNameConstants.TABLE_TOOL_DELETE_CELL_VALUE);
				cellCommand.execute();

				EObject container = TableTestingHelper.getIntersectionCell(line, column);

				String tableCellMask = IToolNameConstants.TABLE_TOOL_DELETE_CELL_VALUE;

				_toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, container);
				_toolWrapper.setArgumentValue(ArgumentType.TABLE_CELL_MASK, tableCellMask);
			}

			@Override
			protected void postRunTest() {
				super.postRunTest();
				DLine line = TableTestingHelper.getLine(table, lineObj);
				DColumn column = TableTestingHelper.getColumn(table, colObj);
				DCell cell = TableTestingHelper.getIntersectionCell(line, column);

				assertTrue(cell == null);
				assertTrue(lineObj instanceof State);
				if (colObj instanceof AbstractFunction) {
					AbstractFunction function = (AbstractFunction) colObj;
					assertFalse(
							NLS.bind(stateModeErrMsg, ((State) lineObj).getName(),
									function.getClass().getName() + " " + function.getName()),
							function.getAvailableInStates().contains(lineObj));
				}
				if (colObj instanceof AbstractCapability) {
					AbstractCapability capability = (AbstractCapability) colObj;
					assertFalse(
							NLS.bind(stateModeErrMsg, ((State) lineObj).getName(),
									capability.getClass().getName() + " " + capability.getName()),
							capability.getAvailableInStates().contains(lineObj));
				}
				if (colObj instanceof FunctionalChain) {
					FunctionalChain fc = (FunctionalChain) colObj;
					assertFalse(
							NLS.bind(stateModeErrMsg, ((State) lineObj).getName(),
									fc.getClass().getName() + " " + fc.getName()),
							fc.getAvailableInStates().contains(lineObj));
				}

			}

			@Override
			public Object getResult() {
				return null;
			}
		}.run();
	}

}
