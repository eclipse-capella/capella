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
package org.polarsys.capella.common.helpers.export.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;

/**
 * CSV writer
 */
public class CSVWriter {

	private PrintWriter _outputStream = null;

	private String _fileName = null;

	private boolean _firstColumn = true;

	private boolean _useCustomRecordDelimiter = false;

	private Charset _charset = null;

	/** User setting */
	private UserSettings _userSettings = new UserSettings();

	private boolean _initialized = false;

	private boolean _closed = false;

	private static final String DEFAULT_CHARSET = "ISO-8859-1"; //$NON-NLS-1$
	private static final int ESCAPE_MODE_DOUBLED = 1;
	private static final int ESCAPE_MODE_BACKSLASH = 2;

	
	
	/**
	 * Creates a {@link CsvWriter} object using a file as the data destination.
	 * @param fileName The path to the file to output the data.
	 * @param delimiter The character to use as the column delimiter.
	 * @param charset The {@link java.nio.charset.Charset Charset} to use while writing the data.
	 */
	public CSVWriter(String fileName, char delimiter, Charset charset) {
		if (fileName == null) {
			throw new IllegalArgumentException(CSVWriterMessages.errNullFileName); 
		}

		if (charset == null) {
			throw new IllegalArgumentException(CSVWriterMessages.errNullCharset); 
		}

		_fileName = fileName;
		_userSettings.Delimiter = delimiter;
		_charset = charset;
	}

	/**
	 * Creates a {@link CsvWriter} object using a file as the data destination.&nbsp;Uses a comma as the column
	 * delimiter and ISO-8859-1 as the {@link java.nio.charset.Charset Charset}.
	 * @param fileName The path to the file to output the data.
	 */
	public CSVWriter(String fileName) {
		this(fileName, ITextConstants.COMMA, Charset.forName(DEFAULT_CHARSET)); 
	}

	/**
	 * Creates a {@link CsvWriter} object using a Writer to write data to.
	 * @param outputStream The stream to write the column delimited data to.
	 * @param delimiter The character to use as the column delimiter.
	 */
	public CSVWriter(Writer outputStream, char delimiter) {
		if (outputStream == null) {
			throw new IllegalArgumentException(CSVWriterMessages.errNullOutputStream); 
		}

		_outputStream = new PrintWriter(outputStream);
		_userSettings.Delimiter = delimiter;
		_initialized = true;
	}

	 /**
   * Creates a {@link CsvWriter} object using a Writer to write data to.
   * @param outputStream The stream to write the column delimited data to.
   * @param delimiter The character to use as the column delimiter.
   */
  public CSVWriter(OutputStream outputStream, char delimiter) {
    if (outputStream == null) {
      throw new IllegalArgumentException(CSVWriterMessages.errNullOutputStream); 
    }

    _outputStream = new PrintWriter(outputStream);
    _userSettings.Delimiter = delimiter;
    _initialized = true;
  }
	
	/**
	 * Creates a {@link CsvWriter} object using an OutputStream to write data to.
	 * @param outputStream The stream to write the column delimited data to.
	 * @param delimiter The character to use as the column delimiter.
	 * @param charset The {@link java.nio.charset.Charset Charset} to use while writing the data.
	 */
	public CSVWriter(OutputStream outputStream, char delimiter, Charset charset) {
		this(new OutputStreamWriter(outputStream, charset), delimiter);
	}

	/**
	 * Gets the character being used as the column delimiter.
	 * @return The character being used as the column delimiter.
	 */
	public char getDelimiter() {
		return _userSettings.Delimiter;
	}

	/**
	 * Sets the character to use as the column delimiter.
	 * @param delimiter The character to use as the column delimiter.
	 */
	public void setDelimiter(char delimiter) {
		_userSettings.Delimiter = delimiter;
	}

	public char getRecordDelimiter() {
		return _userSettings.RecordDelimiter;
	}

	/**
	 * Sets the character to use as the record delimiter.
	 * @param recordDelimiter The character to use as the record delimiter. Default is combination 
	 * of standard end of line characters for Windows, Unix, or Mac.
	 */
	public void setRecordDelimiter(char recordDelimiter) {
		_useCustomRecordDelimiter = true;
		_userSettings.RecordDelimiter = recordDelimiter;
	}

	/**
	 * Gets the character to use as a text qualifier in the data.
	 * @return The character to use as a text qualifier in the data.
	 */
	public char getTextQualifier() {
		return _userSettings.TextQualifier;
	}

	/**
	 * Sets the character to use as a text qualifier in the data.
	 * @param textQualifier The character to use as a text qualifier in the data.
	 */
	public void setTextQualifier(char textQualifier) {
		_userSettings.TextQualifier = textQualifier;
	}

	/**
	 * Whether text qualifiers will be used while writing data or not.
	 * @return Whether text qualifiers will be used while writing data or not.
	 */
	public boolean getUseTextQualifier() {
		return _userSettings.UseTextQualifier;
	}

	/**
	 * Sets whether text qualifiers will be used while writing data or not. 
	 * @param useTextQualifier Whether to use a text qualifier while writing data or not.
	 */
	public void setUseTextQualifier(boolean useTextQualifier) {
		_userSettings.UseTextQualifier = useTextQualifier;
	}

	public int getEscapeMode() {
		return _userSettings.EscapeMode;
	}

	public void setEscapeMode(int escapeMode) {
		_userSettings.EscapeMode = escapeMode;
	}

	public void setComment(char comment) {
		_userSettings.Comment = comment;
	}

	public char getComment() {
		return _userSettings.Comment;
	}

	/**
	 * Whether fields will be surrounded by the text qualifier even if the qualifier is not necessarily needed to escape
	 * this field.
	 * @return Whether fields will be forced to be qualified or not.
	 */
	public boolean getForceQualifier() {
		return _userSettings.ForceQualifier;
	}

	/**
	 * Use this to force all fields to be surrounded by the text qualifier even if the qualifier is not necessarily
	 * needed to escape this field. Default is false.
	 * @param forceQualifier Whether to force the fields to be qualified or not.
	 */
	public void setForceQualifier(boolean forceQualifier) {
		_userSettings.ForceQualifier = forceQualifier;
	}

	/**
	 * Writes another column of data to this record.
	 * @param content The data for the new column.
	 * @param preserveSpaces Whether to preserve leading and trailing whitespace in this column of data.
	 * @exception IOException
	 */
	public void write(String content, boolean preserveSpaces) throws IOException {
		checkClosed();

		checkInit();

		String buffer = content;
		
		if (buffer == null) {
		  buffer = ITextConstants.EMPTY_STRING;
		}

		if (!_firstColumn) {
			_outputStream.write(_userSettings.Delimiter);
		}

		boolean textQualify = _userSettings.ForceQualifier;

		if (!preserveSpaces && buffer.length() > 0) {
		  buffer = buffer.trim();
		}

		if (!textQualify
				&& _userSettings.UseTextQualifier
				&& (buffer.indexOf(_userSettings.TextQualifier) > -1 || buffer.indexOf(_userSettings.Delimiter) > -1 || !_useCustomRecordDelimiter
						&& (buffer.indexOf(ITextConstants.LF) > -1 || buffer.indexOf(ITextConstants.CR) > -1) || _useCustomRecordDelimiter
						&& buffer.indexOf(_userSettings.RecordDelimiter) > -1 || _firstColumn && buffer.length() > 0
						&& buffer.charAt(0) == _userSettings.Comment || _firstColumn && buffer.length() == 0)) {
			textQualify = true;
		}

		if (_userSettings.UseTextQualifier && !textQualify && buffer.length() > 0 && preserveSpaces) {
			char firstLetter = buffer.charAt(0);

			if (firstLetter == ITextConstants.SPACE || firstLetter == ITextConstants.TAB) {
				textQualify = true;
			}

			if (!textQualify && buffer.length() > 1) {
				char lastLetter = buffer.charAt(buffer.length() - 1);

				if (lastLetter == ITextConstants.SPACE || lastLetter == ITextConstants.TAB) {
					textQualify = true;
				}
			}
		}

		if (textQualify) {
			_outputStream.write(_userSettings.TextQualifier);

			if (_userSettings.EscapeMode == ESCAPE_MODE_BACKSLASH) {
			  buffer = replace(buffer, ITextConstants.EMPTY_STRING + ITextConstants.BACKSLASH, ITextConstants.EMPTY_STRING + ITextConstants.BACKSLASH + ITextConstants.BACKSLASH);
			  buffer = replace(buffer, ITextConstants.EMPTY_STRING + _userSettings.TextQualifier, ITextConstants.EMPTY_STRING + ITextConstants.BACKSLASH + _userSettings.TextQualifier);
			} else {
			  buffer = replace(buffer, ITextConstants.EMPTY_STRING + _userSettings.TextQualifier, ITextConstants.EMPTY_STRING + _userSettings.TextQualifier
						+ _userSettings.TextQualifier);
			}
		} else if (_userSettings.EscapeMode == ESCAPE_MODE_BACKSLASH) {
			buffer = replace(buffer, ITextConstants.EMPTY_STRING + ITextConstants.BACKSLASH, ITextConstants.EMPTY_STRING + ITextConstants.BACKSLASH + ITextConstants.BACKSLASH);
			buffer = replace(buffer, ITextConstants.EMPTY_STRING + _userSettings.Delimiter, ITextConstants.EMPTY_STRING + ITextConstants.BACKSLASH + _userSettings.Delimiter);

			if (_useCustomRecordDelimiter) {
				buffer = replace(buffer, ITextConstants.EMPTY_STRING + _userSettings.RecordDelimiter, ITextConstants.EMPTY_STRING + ITextConstants.BACKSLASH
						+ _userSettings.RecordDelimiter);
			} else {
				buffer = replace(buffer, ITextConstants.EMPTY_STRING + ITextConstants.CR, ITextConstants.EMPTY_STRING + ITextConstants.BACKSLASH + ITextConstants.CR);
				buffer = replace(buffer, ITextConstants.EMPTY_STRING + ITextConstants.LF, ITextConstants.EMPTY_STRING + ITextConstants.BACKSLASH + ITextConstants.LF);
			}

			if (_firstColumn && buffer.length() > 0 && buffer.charAt(0) == _userSettings.Comment) {
				if (buffer.length() > 1) {
					buffer = ITextConstants.EMPTY_STRING + ITextConstants.BACKSLASH + _userSettings.Comment + buffer.substring(1);
				} else {
					buffer = ITextConstants.EMPTY_STRING + ITextConstants.BACKSLASH + _userSettings.Comment;
				}
			}
		}

		_outputStream.write(buffer);

		if (textQualify) {
			_outputStream.write(_userSettings.TextQualifier);
		}

		_firstColumn = false;
	}

	/**
	 * Writes another column of data to this record.&nbsp;Does not preserve leading and trailing whitespace in this
	 * column of data.
	 * @param contentThe data for the new column.
	 * @exception IOException
	 */
	public void write(String content) throws IOException {
		write(content, false);
	}

	/**
	 * Write a csv comment.
	 * @param commentText the comment itself
	 * @throws IOException
	 */
	public void writeComment(String commentText) throws IOException {
		checkClosed();

		checkInit();

		_outputStream.write(_userSettings.Comment);

		_outputStream.write(commentText);

		if (_useCustomRecordDelimiter) {
			_outputStream.write(_userSettings.RecordDelimiter);
		} else {
			_outputStream.println();
		}

		_firstColumn = true;
	}

	/**
	 * Writes a new record using the passed in array of values.
	 * @param values Values to be written.
	 * @param preserveSpaces Whether to preserver leading and trailing spaces in columns while writing out to the record or not.
	 * @throws IOException
	 */
	public void writeRecord(String[] values, boolean preserveSpaces) throws IOException {
		if (values != null && values.length > 0) {
			for (String element : values) {
				write(element, preserveSpaces);
			}

			endRecord();
		}
	}

	/**
	 * Writes a new record using the passed in array of values. 
	 * @param values Values to be written.
	 * @throws IOException
	 */
	public void writeRecord(String[] values) throws IOException {
		writeRecord(values, false);
	}

	/**
	 * Ends the current record by sending the record delimiter.
	 * @exception IOException
	 */
	public void endRecord() throws IOException {
		checkClosed();

		checkInit();

		if (_useCustomRecordDelimiter) {
			_outputStream.write(_userSettings.RecordDelimiter);
		} else {
			_outputStream.println();
		}

		_firstColumn = true;
	}

  private void checkInit() throws IOException {
    try (FileOutputStream fileOutputStream = new FileOutputStream(_fileName)) {
      if (!_initialized) {
        if (_fileName != null) {
          _outputStream = new PrintWriter(new OutputStreamWriter(fileOutputStream, _charset));
        }
        _initialized = true;
      }
    } catch (Exception e) {
      // Fail silently
    }
  }

	/**
	 * Clears all buffers for the current writer and causes any buffered data to be written to the underlying device.
	 */
	public void flush() {
		_outputStream.flush();
	}

	/**
	 * Closes and releases all related resources.
	 */
	public void close() {
		if (!_closed) {
			close(true);

			_closed = true;
		}
	}

	private void close(boolean closing) {
		if (!_closed) {
			if (closing) {
				_charset = null;
			}

			try {
				if (_initialized) {
					_outputStream.close();
				}
			} catch (Exception e) {
				
			}

			_outputStream = null;

			_closed = true;
		}
	}

	private void checkClosed() throws IOException {
		if (_closed) {
			throw new IOException(CSVWriterMessages.errOutputStreamAlreadyClosed); 
		}
	}

	/**
	 * Ensure closing of the stream...
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		close(false);

		super.finalize();
	}

	/** Convenient internal class */
	private class UserSettings {

		public char TextQualifier;

		public boolean UseTextQualifier;

		public char Delimiter;

		public char RecordDelimiter;

		public char Comment;

		public int EscapeMode;

		public boolean ForceQualifier;

		public UserSettings() {
			TextQualifier = ITextConstants.QUOTE;
			UseTextQualifier = true;
			Delimiter = ITextConstants.COMMA;
			RecordDelimiter = ITextConstants.NULL;
			Comment = ITextConstants.POUND;
			EscapeMode = ESCAPE_MODE_DOUBLED;
			ForceQualifier = false;
		}
	}

	/**
	 * Utility method.
	 * @param original
	 * @param pattern
	 * @param replace
	 * @return
	 */
	public static String replace(String original, String pattern, String replace) {
		final int len = pattern.length();
		int found = original.indexOf(pattern);

		if (found > -1) {
			StringBuilder sb = new StringBuilder();
			int start = 0;

			while (found != -1) {
				sb.append(original.substring(start, found));
				sb.append(replace);
				start = found + len;
				found = original.indexOf(pattern, start);
			}

			sb.append(original.substring(start));

			return sb.toString();
		}
		
		return original;
	}
}
