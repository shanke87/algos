package solver;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class SudokuTextField extends PlainDocument {
private static final long serialVersionUID = -7335829419710877458L;
private int limit;

  SudokuTextField(int limit) {
   super();
   this.limit = limit;
   }
  
  
  public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
    if (str == null) return;
    boolean hasOnlyDigits = true;
    for(int i=0;i<str.length();++i) if(!Character.isDigit(str.charAt(i))) hasOnlyDigits = false;
    if ((getLength() + str.length()) <= limit && hasOnlyDigits) {
      super.insertString(offset, str, attr);
    }
  }
}