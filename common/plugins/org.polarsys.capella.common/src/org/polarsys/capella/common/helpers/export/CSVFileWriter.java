/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 ******************************************************************************/
package org.polarsys.capella.common.helpers.export;

import java.io.OutputStream;
import java.io.PrintWriter;

import org.polarsys.capella.common.helpers.ICommonConstants2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * CSV file writer
 */
public class CSVFileWriter {

	private PrintWriter _outputStream = null;
	private String _lineDelimiter = ICommonConstants.EMPTY_STRING;
	private String[] _charactersToEscape = {
		ICommonConstants2.BACKSLASH,
		ICommonConstants2.COMMA,
		ICommonConstants2.CR,
		ICommonConstants2.LF
	};

	/**
	 * Constructor
	 * 
	 * @param outputStream the output stream
	 * @param lineDelimiter character used as a line delimiter
	 */
	public CSVFileWriter(OutputStream outputStream, String lineDelimiter) {
		_outputStream = new PrintWriter(outputStream);
		_lineDelimiter = lineDelimiter;
	}

	/**
	 * Writes a new line
	 * 
	 * @param lineValues string array containing all column values for a single line
	 */
	public void writeLine(String[] lineValues) {
		if (lineValues != null) {
			for (int i = 0; i< lineValues.length; i++) {
				String columnValue = (lineValues[i] != null) ? lineValues[i] : ICommonConstants.EMPTY_STRING;

				columnValue = columnValue.trim();
				for (String characterToEscape : _charactersToEscape) {
					columnValue = columnValue.replace(characterToEscape, ICommonConstants2.BACKSLASH + characterToEscape);
				}

				if (i != 0) {
					_outputStream.write(ICommonConstants2.COMMA);
				}
				_outputStream.write(columnValue);
			}
		}
		_outputStream.write(_lineDelimiter);
	}

	/**
	 * Flushes the output stream buffers
	 */
	public void flush() {
		_outputStream.flush();
	}

	/**
	 * Ensures the output stream is closed
	 * 
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() {
		_outputStream.close();
		_outputStream = null;
	}
}
