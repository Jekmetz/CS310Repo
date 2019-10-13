public enum Token {
	// NUMBERS
	INT_LIT, // integer literal

	// OPERATORS
	SCOLON_OP, 		// semicolon operator
	ASSIGN_OP, 		// assignment operator
	ADD_OP, 		// addition operator
	SUB_OP, 		// subtraction operator
	MULT_OP, 		// multiplication operator
	DIV_OP, 		// division operator
	EQ_OP, 			// equality operator
	NEQ_OP, 		// not equals operator

	// PARENS
	L_PAREN, 		// left parenthesis
	R_PAREN, 		// right parenthesis

	// KEYS
	IDENT, 			// identifier
	PROCEDURE_KEY,	// procedure keyword
	BEGIN_KEY, 		// begin keyword
	END_KEY, 		// end keyword
	IF_KEY, 		// if keyword
	THEN_KEY, 		// then keyword
	ELSE_KEY, 		// else keyword
	ENDIF_KEY,		// endif keyword
	
	//END OF INPUT
	EOI,
	
	//BAD TOKEN
	ERROR
}